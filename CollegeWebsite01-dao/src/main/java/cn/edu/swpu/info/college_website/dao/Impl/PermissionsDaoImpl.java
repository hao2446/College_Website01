package cn.edu.swpu.info.college_website.dao.Impl;

import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.dao.PermissionsDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionsDaoImpl extends BaseDaoImpl<admin, String> implements PermissionsDao {
    public final static String NAMESPACE="cn.edu.swpu.info.college_website.dao.Impl.PermissionsDaoImpl." ;
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
    //根据用户登录时提供的登录信息，进行验证查询
    public admin selectAdminInfo(String  loginName){
        admin admin=null;
        try{
            if (loginName!=null){
                admin=sqlSessionTemplate.selectOne("selectSomeInfo",loginName);
                System.out.println(admin);
                //return admin1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return admin;
    }
}
