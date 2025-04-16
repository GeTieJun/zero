package com.tiejun.ge.zero.admin.app;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tiejun.ge.zero.admin.domain.bo.SysMenuBO;
import com.tiejun.ge.zero.admin.domain.dto.UserLoginDTO;
import com.tiejun.ge.zero.admin.domain.po.SysUser;
import com.tiejun.ge.zero.admin.domain.vo.MetaVo;
import com.tiejun.ge.zero.admin.domain.vo.RouterVo;
import com.tiejun.ge.zero.admin.domain.vo.UserInfoVO;
import com.tiejun.ge.zero.admin.server.SysPermissionServer;
import com.tiejun.ge.zero.common.constant.Constants;
import com.tiejun.ge.zero.common.constant.UserConstants;
import com.tiejun.ge.zero.common.exception.enums.UserExceptionEnum;
import com.tiejun.ge.zero.common.exception.module.UserException;
import com.tiejun.ge.zero.common.utils.BeanBuilder;
import com.tiejun.ge.zero.common.utils.SecurityUtils;
import com.tiejun.ge.zero.system.authenticate.domain.LoginUser;
import com.tiejun.ge.zero.system.authenticate.holder.AuthenticationContextHolder;
import com.tiejun.ge.zero.system.authenticate.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: zero
 * @description: 系统登陆应用层
 * @author: getiejun
 * @create: 2024-12-06 21:09
 **/
@Component
@Slf4j
public class SysLoginApp {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenService tokenService;

    @Resource
    private SysPermissionServer sysPermissionServer;

    /**
     * 用户登录
     *
     * @param userLoginDTO 用户登录信息DTO
     * @return 用户登录后token
     */
    public String login(UserLoginDTO userLoginDTO) {
        // TODO 验证码校验，用户名校验
        Authentication authenticate;
        try {
            // 使用springSecurity认证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUserName(),
                    userLoginDTO.getPassword());
            // 设置context holder，在UserDetailsServiceImpl.loadUserByUsername验证用户名密码
            AuthenticationContextHolder.setContextHolder(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        } catch (Exception e) {
            log.error(">>SysLoginApp login method error", e);
            throw new UserException(UserExceptionEnum.USER_LOGIN_ERROR);
        } finally {
            AuthenticationContextHolder.clearContextHolder();
        }

        // 记录操作日志
        // 生成token 返回前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return tokenService.createToken(loginUser);
    }

    /**
     * 获取用户信息（用户基本信息、角色信息、权限信息）
     *
     * @return UserInfoVO
     */
    public UserInfoVO getUserInfo() {
        // 获取当前用户
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 获取当前用户权限
        Set<String> permissions = sysPermissionServer.selectPermsByUserId(user.getUserId());
        // 获取当前用户角色
        Set<String> roles = sysPermissionServer.selectRolesByUserId(user.getUserId());

        UserInfoVO userInfoVO = BeanBuilder.of(UserInfoVO.class)
                .with(UserInfoVO::setUser, user)
                .with(UserInfoVO::setPermissions, permissions)
                .with(UserInfoVO::setRoles, roles)
                .build();

        return userInfoVO;
    }

    /**
     * 获取用户对应的路由信息
     *
     * @return List<RouterVo>
     */
    public List<RouterVo> getRouters() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysMenuBO> sysMenuBOList;
        if (SecurityUtils.isAdmin(user.getUserId())) {
            sysMenuBOList = sysPermissionServer.selectAllSysMenu();
        } else {
            sysMenuBOList = sysPermissionServer.selectSysMenuByUserId(user.getUserId());
        }
        List<SysMenuBO> childPerms = getChildPerms(sysMenuBOList, 0);
        return buildMenus(childPerms);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    private List<SysMenuBO> getChildPerms(List<SysMenuBO> list, int parentId) {
        List<SysMenuBO> returnList = new ArrayList<SysMenuBO>();
        for (Iterator<SysMenuBO> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuBO t = (SysMenuBO) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list 分类表
     * @param t    子节点
     */
    private void recursionFn(List<SysMenuBO> list, SysMenuBO t) {
        // 得到子节点列表
        List<SysMenuBO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuBO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuBO> getChildList(List<SysMenuBO> list, SysMenuBO t) {
        List<SysMenuBO> tlist = new ArrayList<SysMenuBO>();
        Iterator<SysMenuBO> it = list.iterator();
        while (it.hasNext()) {
            SysMenuBO n = (SysMenuBO) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuBO> list, SysMenuBO t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    private List<RouterVo> buildMenus(List<SysMenuBO> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuBO menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StrUtil.equals("1", String.valueOf(menu.getIsCache())), menu.getPath()));
            List<SysMenuBO> cMenus = menu.getChildren();
            if (CollUtil.isNotEmpty(cMenus) && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(getRouteName(menu.getRouteName(), menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StrUtil.equals("1", String.valueOf(menu.getIsCache())), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(getRouteName(menu.getRouteName(), routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    private String getRouteName(SysMenuBO menu) {
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            return "";
        }
        return getRouteName(menu.getRouteName(), menu.getPath());
    }

    /**
     * 获取路由名称，如没有配置路由名称则取路由地址
     *
     * @param name 路由名称
     * @param path 路由地址
     * @return 路由名称（驼峰格式）
     */
    private String getRouteName(String name, String path) {
        String routerName = StrUtil.isNotEmpty(name) ? name : path;
        return capitalize(routerName);
    }

    private static String capitalize(String str) {
        int strLen = ObjectUtil.isEmpty(str) ? 0 : str.length();
        if (strLen == 0) {
            return str;
        }
        return StrUtil.upperFirst(str);
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    private String getRouterPath(SysMenuBO menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    private String getComponent(SysMenuBO menu) {
        String component = UserConstants.LAYOUT;
        if (StrUtil.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StrUtil.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = UserConstants.INNER_LINK;
        } else if (StrUtil.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isMenuFrame(SysMenuBO menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isInnerLink(SysMenuBO menu) {
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && StrUtil.startWithAny(menu.getPath(), Constants.Common.HTTP, Constants.Common.HTTPS);
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isParentView(SysMenuBO menu) {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return 替换后的内链域名
     */
    private String innerLinkReplaceEach(String path) {
        return path.replaceAll("www.|http://|https://", "").replaceAll(".|:", "/");
    }
}

