package com.tiejun.ge.zero.system.authenticate.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tiejun.ge.zero.common.constant.Constants;
import com.tiejun.ge.zero.common.redis.RedisCache;
import com.tiejun.ge.zero.system.authenticate.domain.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: zero
 * @description: token service
 * @author: getiejun
 * @create: 2024-12-15 17:07
 **/
@Component
@Slf4j
public class TokenService {

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Resource
    private RedisCache redisCache;

    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StrUtil.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                String uuid = (String) claims.get(Constants.Authentication.LOGIN_USER_KEY); // 获取redis user key
                String userKey = getTokenKey(uuid);
                LoginUser user = redisCache.getCacheObject(userKey);
                return user;
            } catch (Exception e) {
                log.error("获取用户信息异常：{}", e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 从令牌中获取声明数据
     * @param token
     * @return
     */
    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 获取请求中的token
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(this.header);
        if (StrUtil.isNotEmpty(token) && token.startsWith(Constants.Authentication.TOKEN_PREFIX)) {
            token = token.replace(Constants.Authentication.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 创建令牌
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        // 1.生成UUid 2. 续期 redis:uuid:loginUser
        String token = IdUtil.fastUUID();
        loginUser.setToken(token);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.Authentication.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 刷新token
     * @param loginUser
     */
    private void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        String userKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String uuid) {
        return Constants.CacheConstants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser)
    {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 清理登录用户信息
     * @param token
     */
    public void delLoginUser(String token) {
        if (ObjectUtil.isNotEmpty(token)) {
            String tokenKey = getTokenKey(token);
            redisCache.deleteObject(tokenKey);
        }
    }
}
