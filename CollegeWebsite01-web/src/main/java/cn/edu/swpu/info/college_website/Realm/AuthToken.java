package cn.edu.swpu.info.college_website.Realm;


import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Shiro自定义token类
 *
 * @Author dxd
 * @Date 2019/3/31 10:58
 * @Vers
 * */
public class AuthToken extends UsernamePasswordToken {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
