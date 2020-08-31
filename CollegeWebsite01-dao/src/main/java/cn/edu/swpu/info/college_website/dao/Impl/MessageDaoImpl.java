package cn.edu.swpu.info.college_website.dao.Impl;

import cn.edu.swpu.info.college_website.dao.MessageDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;


@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message, Integer>implements MessageDao {
    public final static String  NAMESPACE="cn.edu.swpu.info.college_website.dao.MessageDaoImpl.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

}
