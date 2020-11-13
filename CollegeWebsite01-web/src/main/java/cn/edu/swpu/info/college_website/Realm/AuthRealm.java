package cn.edu.swpu.info.college_website.Realm;

import cn.edu.swpu.info.Permission;
import cn.edu.swpu.info.SysToken;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.common.DateUtils;
import cn.edu.swpu.info.college_website.services.ShiroServiceImpl;
import cn.edu.swpu.info.college_website.services.PermissionsServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class AuthRealm extends AuthorizingRealm {

    @Resource
    PermissionsServiceImpl permissionsServiceImpl;
    @Autowired
    private ShiroServiceImpl shiroService;

    @Override
    /**
     * 授权 获取用户的角色和权限
     *@param  [principals]
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roles=new HashSet<>();
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        admin user = (admin) principals.getPrimaryPrincipal();
        //Integer userId = user.getUserId();
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Permission permission =permissionsServiceImpl.selectPermission(user);
        // System.out.println(permission.toString());
        String str[]=permission.getPremissionType().split(",");
        //System.out.println(str);
        for (int i=0;i<str.length;i++){
            System.out.println(str[i]);
            roles.add(str[i]);
        }
        simpleAuthorizationInfo.setRoles(roles);
//        for (Role role : user.getRoles()) {
//            //2.1添加角色
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            for (Permission permission : role.getPermissions()) {
//                //2.1.1添加权限
//                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
//            }
//        }
        return simpleAuthorizationInfo;
    }

    @Override
    /**
     * 认证 判断token的有效性
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.findByToken(accessToken);
        //LocalDateTime tokenEntitys= DateUtils.asLocalDateTime(tokenEntity.getExpireTime());
        //2. token失效
        if (tokenEntity == null || DateUtils.asLocalDateTime(tokenEntity.getExpireTime()).isBefore(LocalDateTime.now())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        admin user = shiroService.findByUserId(tokenEntity.getUserId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }
}