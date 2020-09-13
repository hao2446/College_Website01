package cn.edu.swpu.info.college_website.web;

import cn.edu.swpu.info.Message;
import cn.edu.swpu.info.ResponseMessage;
import cn.edu.swpu.info.college_website.common.picturePath;
import cn.edu.swpu.info.college_website.common.savePicture;
import cn.edu.swpu.info.college_website.common.showPictureImpl;
import cn.edu.swpu.info.college_website.services.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 新闻相关操作
 */
@RequestMapping("/message")
@Controller
public class MessageController {
    @Autowired
    MessageServiceImpl messageServiceImpl;
    @Autowired
    showPictureImpl showPictures;

    /**
     * 根据前端传入的Messageid
     * 根据MessaageId进行查找
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMessage")
    public ResponseMessage getMessage(@RequestParam("messageid") Integer  messageid){
        Message message= messageServiceImpl.getMessageContent(messageid);
        ResponseMessage<Message> responseMessage=new ResponseMessage<>();
        if (message!=null){
            messageServiceImpl.updateMessageClick(message);//用于更改点击量
            responseMessage.setCode(200);
            responseMessage.setData(message);
            responseMessage.setMsg("查询成功");
        }else {
            responseMessage.setCode(1000);
            responseMessage.setMsg("不存在这个新闻");
        }
        return responseMessage;
    }

    /**
     * 查询上一条新闻
     * @param messageid
     * @return
     */
    @ResponseBody
    @RequestMapping("/lastMessage")
    public ResponseMessage getLastMessage(@RequestParam("messageid")Integer messageid){
        ResponseMessage<Integer> responseMessage=new ResponseMessage<>();
        int message=messageServiceImpl.getLastMessage(messageid);
        if (message!=-1){
            responseMessage.setCode(200);
            responseMessage.setData(message);
            responseMessage.setMsg("查询成功");
        }else {
            responseMessage.setCode(1000);
            responseMessage.setMsg("不存在这个新闻");
        }
        return responseMessage;
    }

    /**
     * 返回下一条新闻
     * @param messageid
     * @return
     */
    @ResponseBody
    @RequestMapping("/nextMessage")
    public ResponseMessage getNextMessage(@RequestParam("messageid") Integer messageid){
        ResponseMessage<Integer> responseMessage=new ResponseMessage<>();
        int message=messageServiceImpl.getNextMessage(messageid);
        if (message!=-1){
            responseMessage.setCode(200);
            responseMessage.setData(message);
            responseMessage.setMsg("查询成功");
        }else {
            responseMessage.setCode(1000);
            responseMessage.setMsg("不存在这个新闻");
        }
        return responseMessage;
    }
    /**
     * 根据前端传入的Messagetype
     * 根据新闻类型进行查询，并返回数据
<<<<<<< Updated upstream
     * @param messagetype
=======
     * @param
>>>>>>> Stashed changes
     * @return
     */
    @ResponseBody
    @RequestMapping("/getNews")
    public List<Message> getMessages(@RequestParam("messagetype") String messagetype){
        Message message=new Message();
        //message.setMessagetype();
        message.setMessagetype(messagetype);
        List<Message> newsList= messageServiceImpl.getNews(message);
        return newsList;
    }

    /**
     * 根据文章的标题进行模糊查询得到新闻
     * @param title
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllNews")
    public List<Message> getAllMessages( @RequestParam("messagetitle") String title){
        Message message=new Message();
        //message.setMessagetype();
        message.setMessagetitle(title);
        List<Message> newsList= messageServiceImpl.getAllNews(message);
        System.out.println(newsList);
        return newsList;
    }
    /**
     * 进入首页直接获取
     * 首页加载数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/FirstPageResource" )
    public List<Message> getFirstPage(){
        List<Message> list= messageServiceImpl.getFirstPage();
        return list;
    }

    /**
     * 添加新闻
     * 根据后端先行查询总的记录数来确定插入时的主键
     * @param message
     * @return
     */
    @RequestMapping(value = "/insertMessage",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> insertMessage(@RequestBody Message  message) throws IOException {
        System.out.println(message.toString());
       // message.setMessageid(messageServiceImpl.getMaxId()+1);
        String msg=messageServiceImpl.addMessage(message);
       // model.addAttribute("msg",msg);
//        model.addAttribute("image",multipartFile.getOriginalFilename());
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(1000);
        responseMessage.setMsg(msg);
        return responseMessage;
    }

    /**
     * 上传图片
     * 返回图片的url
     * @return
     */
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> uploadImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String path= savePicture.uploadPicture(multipartFile);
        System.out.println(path);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(1000);
        responseMessage.setData("/message/show/"+path);
        responseMessage.setMsg("success");
        return responseMessage;
    }

    /**
     * 删除新闻
     * 根据前端提供的messageid数组进行删除操作
     * @param messageid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMessages")
    public ResponseMessage deleteMessages(@RequestParam("messageid") Integer messageid){
        int result=0;
        result= messageServiceImpl.deleteMessage(messageid);
        ResponseMessage<Integer> responseMessage=new ResponseMessage<>();
        if (result==1){
            responseMessage.setCode(200);
            responseMessage.setMsg("删除成功");
            responseMessage.setData(result);
        }else if(result==0){
                responseMessage.setCode(1000);
                responseMessage.setData(result);
                responseMessage.setMsg("删除失败");
        }
        return responseMessage;
    }

    /**
     * 更新新闻
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateMessage" ,method = RequestMethod.POST)
    public ResponseMessage updateMessage(@RequestBody Message message){
        int result=0;
        result=messageServiceImpl.updateMessage(message);
        ResponseMessage<Integer> responseMessage=new ResponseMessage<>();
        if (result==1){
            responseMessage.setCode(200);
            responseMessage.setMsg("修改成功");
        }else {
            responseMessage.setMsg("修改失败");
            responseMessage.setCode(1000);
        }
        return responseMessage;
    }
    /**
     * 显示图片
     * @param filename
     * @param suffix
     * @param response
     * @throws IOException
     */
    @RequestMapping("/show/{filename}.{suffix}")
    public void showPicture(@PathVariable("filename") String filename,
                            @PathVariable("suffix") String suffix,
                            HttpServletResponse response) throws IOException {
        System.out.println("运行啦");
        File imagefile=new File(picturePath.path+filename+"."+suffix);
        showPictures.responseFile(response,imagefile);
    }

}
