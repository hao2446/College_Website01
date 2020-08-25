package cn.edu.swpu.info.college_website.services;

import cn.edu.swpu.info.Message;
import cn.edu.swpu.info.college_website.dao.Impl.MessageDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl {
    @Resource
   private MessageDaoImpl messageDaoImpl;

    /**
     * 根据主键进行查找新闻
     * @return
     */
    public Message getMessageContent(Integer messageid){
        return messageDaoImpl.selectObject(messageid);
    }

    /**
     * 根据新闻的类型查找新闻
     * @param message
     * @return
     */
    public List<Message> getNews(Message message){
        return messageDaoImpl.selectObiectList(message);
    }

    /**
     * 返回在加载首页时，所需要的数据
     * @return
     */
    public List<Message> getFirstPage(){
        List<Message> messageList=null;
        messageList=messageDaoImpl.selectObiectList(new Message());
        int a=0,b=0,c=0,d=0,e=0;
        for (Message message: messageList) {
            if (message.getMessagetype()==1 && a<=6){
                messageList.add(message);
                a++;
            }
            if (message.getMessagetype()==2 && b<=6){
                messageList.add(message);
                b++;
            }
            if (message.getMessagetype()==3 && c<=6){
                messageList.add(message);
                c++;
            }
            if (message.getMessagetype()==4 && d<=6){
                messageList.add(message);
                d++;
            }if (message.getMessagetype()==5 && e<=6){
                messageList.add(message);
                e++;
            }

        }
        return  messageList;
    }

    /**
     * 添加新闻
     * @param message
     * @return
     */
    public String addMessage(Message message){
        String msg=null;
        if (messageDaoImpl.insertObject(message)==1){
            msg="添加成功";
        }else {
             msg="添加失败";
        }
        return  msg;
    }
}
