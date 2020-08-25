package cn.edu.swpu.info.college_website.web;

import cn.edu.swpu.info.Message;
import cn.edu.swpu.info.college_website.services.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/message")
@Controller
public class MessageController {
    @Autowired
    MessageServiceImpl messageServiceImpl;

    /**
     * 根据前端传入的Messageid
     * 根据MessaageId进行查找
     * @param model
     * @return
     */
    @RequestMapping("/getMessage")
    public String getMessage(@RequestParam String  messageid, Model model){
        Message message= messageServiceImpl.getMessageContent(Integer.parseInt(messageid));
        //System.out.println(message);
        model.addAttribute("getContent",message);
        return "get";
    }

    /**
     * 根据前端传入的Messagetype
     * 根据新闻类型进行查询，并返回数据
     * @param model
     * @return
     */
    @RequestMapping("/getNews")
    public String getMessages(Model model){
        Message message=new Message();
        message.setMessagetype(1);
        List<Message> newsList= messageServiceImpl.getNews(message);
        model.addAttribute("news",newsList);
        return "get";
    }

    /**
     * 进入首页直接获取
     * 首页加载数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/FirstPageResource")
    public List<Message> getFirstPage(){
        List<Message> list= messageServiceImpl.getFirstPage();
        return list;
    }
}
