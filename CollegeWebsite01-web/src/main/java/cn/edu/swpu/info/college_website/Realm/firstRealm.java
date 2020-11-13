package cn.edu.swpu.info.college_website.Realm;

import cn.edu.swpu.info.Permission;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.services.PermissionsServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class firstRealm extends AuthorizingRealm {
    @Resource
    PermissionsServiceImpl permissionsServiceImpl;

    //实现登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        //强行进行转换
        UsernamePasswordToken token=(UsernamePasswordToken) Token;
        String userName=token.getUsername();
        //从数据库中查询该用户的相关信息
        admin admin1=new admin();
        admin1.setLoginName(token.getUsername());
        String str=new String(token.getPassword());
        admin1.setAdminPassword(str);
        //从数据库中查询信息
        admin admin=permissionsServiceImpl.selectInformation(admin1);
        if (admin==null){
            throw  new UnknownAccountException("用户不存在");
        }
//        if ("monster".equals(userName)){
//            throw  new LockedAccountException("该用户已被锁住");
//        }
        //根据用户情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为：SimpleAuthenticationInfo
        //principal：认证是实体信息，可以是userName，也可以是数据表中对应的实体对象
        Object principal=admin.getLoginName();
        //credentials：数据库取得的密码
        Object credentials=admin.getAdminPassword();
        //realName：当前realm对象的name，调用父类的getName()方法即可
        String realName=getName();
        //盐值
        ByteSource credentialsSalt=ByteSource.Util.bytes(userName);
        System.out.println(realName);
        SimpleAuthenticationInfo info=null;
        info=new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realName);
        System.out.println("皮"+info);
        //System.out.println(SecurityUtils.getSubject().getPrincipal());
        info.getCredentials();
        System.out.println(info.getCredentials());
        System.out.println(info.getCredentialsSalt());
        System.out.println(info.getPrincipals());
        return info;
    }
    //实现授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从PrincipalCollection中获取用户的登录信息
        Object principal=principalCollection.getPrimaryPrincipal();
        System.out.println(principal.toString());
       // System.out.println(principal);
        //利用用户的登录信息来获取用户的权限信息
        Set <String> roles=new HashSet<>();
        admin admin1=new admin();
        admin1.setLoginName((String)principal);
        Permission permission =permissionsServiceImpl.selectPermission(admin1);
       // System.out.println(permission.toString());
        String str[]=permission.getPremissionType().split(",");
        //System.out.println(str);
        for (int i=0;i<str.length;i++){
            System.out.println(str[i]);
            roles.add(str[i]);
        }
        //roles.add(String.valueOf(admin.getAdminPermissions()));
        //SimpleAuthorizationInfo，并设置其roles属性，
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo(roles);
        //返回SimpleAuthenticationInfo对象
        return info;
    }
}
