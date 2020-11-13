package cn.edu.swpu.info.college_website.dao.Impl;

import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.dao.UserRepository;
import cn.edu.swpu.info.college_website.dao.base.BaseDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl extends BaseDaoImpl<admin,Integer> implements UserRepository {
    public final static String  NAMESPACE="cn.edu.swpu.info.college_website.dao.Impl.UserRepositoryImpl.";
    @Override
    public admin findByUsername(String username) {
        return sqlSessionTemplate.selectOne("selectByUserName",username);
    }

    @Override
    public admin findByUserId(Integer userId) {
        return sqlSessionTemplate.selectOne("selectByUserId",userId);
    }

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
}
