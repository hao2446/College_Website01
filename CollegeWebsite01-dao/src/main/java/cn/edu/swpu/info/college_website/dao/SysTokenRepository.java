package cn.edu.swpu.info.college_website.dao;

import cn.edu.swpu.info.SysToken;

public interface SysTokenRepository {
    /**
     * 通过token查找
     * @param token
     * @return
     */
    SysToken findByToken(String token);

    /**
     * 通过userID查找
     * @param userId
     * @return
     */
    SysToken findByUserId(Integer userId);
    int save(SysToken sysToken);
}
