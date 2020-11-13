package cn.edu.swpu.info.college_website.services;


import cn.edu.swpu.info.SysToken;
import cn.edu.swpu.info.admin;
import org.springframework.stereotype.Component;

import java.util.Map;

public interface ShiroService {
    /**
     * Find user by username
     * @param username
     * @return
     */
    admin findByUsername(String username);

    /**
     * create token by userId
     * @param userId
     * @return
     */
    Map<String,Object> createToken(Integer userId);

    /**
     * logout
     * @param token
     */
    void logout(String token);

    /**
     * find token by token
     * @param accessToken
     * @return
     */
    SysToken findByToken(String accessToken);

    /**
     * find user by userId
     * @param userId
     * @return
     */
    admin findByUserId(Integer userId);
}
