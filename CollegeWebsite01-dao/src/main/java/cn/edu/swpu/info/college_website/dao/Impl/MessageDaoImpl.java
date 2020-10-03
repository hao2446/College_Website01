package cn.edu.swpu.info.college_website.dao.Impl;

import cn.edu.swpu.info.Message;
import cn.edu.swpu.info.college_website.dao.MessageDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message, Integer>implements MessageDao {
    public final static String  NAMESPACE="cn.edu.swpu.info.college_website.dao.MessageDaoImpl.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    /**
     * 返回上一条新闻
     * @param message
     * @return
     */
    public List<Integer> getLastMessage(Message message){
        List<Integer> messageidList=null;
        try{
            if (message!=null){
                messageidList=sqlSessionTemplate.selectList("selectLastMessage",message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messageidList;
    }

    /**
     * 返回下一条新闻
     * @param message
     * @return
     */
    public List<Integer> getNextMessage(Message message){
        List<Integer> messageidList=null;
        try{
            if (message!=null){
                messageidList=sqlSessionTemplate.selectList("selectNextMessage",message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messageidList;
    }
}
