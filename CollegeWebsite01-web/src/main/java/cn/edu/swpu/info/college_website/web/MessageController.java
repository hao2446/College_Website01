package cn.edu.swpu.info.college_website.web;

import cn.edu.swpu.info.ResponseMessage;
import cn.edu.swpu.info.college_website.common.savePicture;
import cn.edu.swpu.info.college_website.common.showPictureImpl;
import cn.edu.swpu.info.college_website.services.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


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
    public ResponseMessage<String> insertMessage(@RequestBody Message message) throws IOException {
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
//        System.out.println(multipartFile.getOriginalFilename());
//        String path= picturePath.path+multipartFile.getOriginalFilename();
//        multipartFile.transferTo(new File(path));
//        message.setMessageimag(path);
        // message.setMessageid(messageServiceImpl.getMaxId()+1);
        //String msg=messageServiceImpl.addMessage(message);
        // model.addAttribute("msg",msg);
//        model.addAttribute("image",multipartFile.getOriginalFilename());
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
    @RequestMapping(value = "/deleteMessages")
    public String deleteMessages(@RequestBody List<Integer> messageid){
        int result=0;
        result= messageServiceImpl.deleteMessage(messageid);
        return "get";
    }
    @RequestMapping("/show/{filename}.{suffix}")
    public void showPicture(@PathVariable("filename") String filename,
                            @PathVariable("suffix") String suffix,
                            HttpServletResponse response) throws IOException {
        File imagefile=new File("D:\\Users\\lenovo\\Desktop\\CollegeWebsite01\\file\\img\\"+filename+"."+suffix);
        showPictures.responseFile(response,imagefile);
    }

}
