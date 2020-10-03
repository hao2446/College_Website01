package cn.edu.swpu.info.college_website.dao.Impl;

import cn.edu.swpu.info.Permission;
import cn.edu.swpu.info.college_website.dao.P_PermissionDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class P_PermissionDaoImpl extends BaseDaoImpl<Permission,Integer> implements P_PermissionDao {
    public final static String NAMESPACE="cn.edu.swpu.info.college_website.dao.Impl.P_PermissionDaoImpl.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
}
