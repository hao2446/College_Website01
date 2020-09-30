package cn.edu.swpu.info.college_website.services;

import cn.edu.swpu.info.Message;
import cn.edu.swpu.info.college_website.common.Datetool;
import cn.edu.swpu.info.college_website.dao.Impl.MessageDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     * 查询上一条新闻
     * @param messageid
     * @return
     */
    public int  getLastMessage(Integer messageid){
        List<Integer> messageidList=new ArrayList<>();
        //PageHelper.startPage(1,1);
        messageidList=messageDaoImpl.getLastMessage(messageid);
        //PageInfo pageInfo=new PageInfo(messageidList);
        //System.out.println(pageInfo);
        return messageidList.get(0);
    }

    /**
     * 返回下一条新闻
     * @param messageid
     * @return
     */
    public int getNextMessage(Integer messageid){
        List<Integer> messageidList=new ArrayList<>();
        messageidList=messageDaoImpl.getNextMessage(messageid);
        return messageidList.get(0);
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
     * 根据新闻得标题进行模糊查询
     * @return
     */
    public List<Message> getAllNews(Message message){
        return messageDaoImpl.selectObiectList(message);
    }

    /**
     * 获取最大的id
     * @return
     */
    public Integer getMaxId(){
        return messageDaoImpl.selectMaxId();
    }
    /**
     * 查询信息的总数
     * @return
     */
    public Integer getTotal(String messagetype){
        return messageDaoImpl.selectCount(messagetype);
    }
    /**
     * 返回在加载首页时，所需要的数据
     * @return
     */
    public List<Message> getFirstPage(){
        List<Message> messageList=messageDaoImpl.selectObiectList(new Message());
        List<Message> list=new ArrayList<>();
        int a=0,b=0,c=0,d=0,e=0;
        for (Message message: messageList) {
            if (message.getMessagetype().equals("学院新闻") && a<=6){
                list.add(message);
                a++;
            }
            if (message.getMessagetype().equals("通知公告") && b<=6){
                list.add(message);
                b++;
            }
            if (message.getMessagetype().equals("学子风采") && c<=6){
                list.add(message);
                c++;
            }
            if (message.getMessagetype().equals("教育教学") && d<=6){
                list.add(message);
                d++;
            }if (message.getMessagetype().equals("招生就业") && e<=6){
                list.add(message);
                e++;
            }

        }
        return  list;
    }

    /**
     * 添加新闻
     * @param message
     * @return
     */
    public String addMessage(Message message){
        String msg=null;
        message.setCreatedate(Datetool.format());
        message.setMessageid(getMaxId()+1);
        message.setClickrate(0);
        if (messageDaoImpl.insertObject(message)==1){
            msg="添加成功";
        }else {
             msg="添加失败";
        }
        return  msg;
    }

    /**
     * 根据前端提供的messageid数组进行删除
     * @param id
     * @return
     */
    public int deleteMessage(Integer id){
        return  messageDaoImpl.deleteObjectByKey(id);
    }

    /**
     * 更新新闻
     * @param message
     * @return
     */
    public int updateMessage(Message message){
        message.setCreatedate(Datetool.format());
        return messageDaoImpl.updateObject(message);
    }
    public int updateMessageClick(Message message){
       // Message message=new Message();
        message.setClickrate(message.getClickrate()+1);
        return messageDaoImpl.updateObject(message);
    }
}
