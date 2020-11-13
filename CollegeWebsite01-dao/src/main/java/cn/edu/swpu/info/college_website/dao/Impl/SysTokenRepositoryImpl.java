package cn.edu.swpu.info.college_website.dao.Impl;


import cn.edu.swpu.info.SysToken;
import cn.edu.swpu.info.college_website.dao.SysTokenRepository;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Component;

@Component
public class SysTokenRepositoryImpl extends BaseDaoImpl<SysToken, String> implements SysTokenRepository {
    public final static String  NAMESPACE="cn.edu.swpu.info.college_website.dao.Impl.SysTokenRepositoryImpl.";
    @Override
    public SysToken findByToken(String token) {
        return sqlSessionTemplate.selectOne("selectByToken",token);
    }

    @Override
    public SysToken findByUserId(Integer userId) {
        return sqlSessionTemplate.selectOne("selectByByUserId",userId);
    }

    @Override
    public int save(SysToken sysToken) {
        return sqlSessionTemplate.insert("saveToken",sysToken);
    }

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
}
