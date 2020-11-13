package cn.edu.swpu.info.college_website.dao;

import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.dao.base.BaseDao;

public interface UserRepository extends BaseDao<admin,Integer> {
    admin findByUsername(String username);

    admin findByUserId(Integer userId);
}
