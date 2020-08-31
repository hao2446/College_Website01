package cn.edu.swpu.info.college_website.dao.Impl;

import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.dao.adminDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;


@Repository
public class AdminDaoImpl extends BaseDaoImpl<admin, String>implements adminDao {
    public final static String  NAMESPACE="cn.edu.swpu.info.college_website.dao.AdminDaoImpl.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

}
