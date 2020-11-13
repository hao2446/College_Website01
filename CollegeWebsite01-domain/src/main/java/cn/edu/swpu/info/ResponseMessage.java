package cn.edu.swpu.info;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ResponseMessage<T> {
    private int code;
    private T data;
    private String msg;
    private UsernamePasswordToken token;

    public UsernamePasswordToken getToken() {
        return token;
    }

    public void setToken(UsernamePasswordToken token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
