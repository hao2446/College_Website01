package cn.edu.swpu.info.college_website.web;

import cn.edu.swpu.info.Img;
import cn.edu.swpu.info.ResponseMessage;
import cn.edu.swpu.info.college_website.common.savePicture;
import cn.edu.swpu.info.college_website.services.ImgServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Img")
public class ImgController {

    @Resource
    ImgServiceImpl imgServiceImpl;

    /**
     * 添加轮播图信息
     * @param img
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertImg" ,method = RequestMethod.POST)
    public ResponseMessage insertImgControler(@RequestBody Img img){
        int number=imgServiceImpl.insertImg(img);
        ResponseMessage<Integer> responseMessage=new ResponseMessage<>();
        if (number!=0){
            responseMessage.setCode(200);
            responseMessage.setMsg("添加成功");
        }else {
            responseMessage.setCode(1000);
            responseMessage.setMsg("添加失败");
        }
        return  responseMessage;
    }

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> uploadImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        //System.out.println(m);
        String path= savePicture.uploadPicture(multipartFile);
       // String path="";
        System.out.println(path);
        String realPath="/message/show/"+path;
        Img img=new Img();
        img.setImgname(realPath);
        int number=imgServiceImpl.insertImg(img);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(1000);
        //responseMessage.setData("/message/show/"+path);
        responseMessage.setMsg("成功");
       // System.out.println(responseMessage.getData());
        return responseMessage;
    }

    /**
     * 根据主键删除轮播图相关的信息
     * @param Imgid
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteImg")
    public ResponseMessage deleteImgController(@RequestParam("Imgid") Integer Imgid){
        int number=imgServiceImpl.deleteImg(Imgid);
        ResponseMessage<Integer> responseMessage=new ResponseMessage<>();
        if(number!=0){
            responseMessage.setCode(200);
            responseMessage.setMsg("删除成功");
        }else {
            responseMessage.setCode(1000);
            responseMessage.setMsg("删除失败");
        }
        return responseMessage;
    }

    /**
     * 查询所有的轮播图信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllImg")
    public List<Img> selectAllImgController(){
        List<Img> ImgList=imgServiceImpl.selectImgs();
        System.out.println(ImgList.toString());
        return  ImgList;
    }

    /**
     * 根据主键查询轮播图信息
     * @param Imgid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getImg")
    public ResponseMessage selectImgController(@RequestParam("Imgid") Integer Imgid){
        Img img=imgServiceImpl.selectImg(Imgid);
        ResponseMessage<Img> responseMessage=new ResponseMessage<>();
        if (img!=null){
            responseMessage.setCode(200);
            responseMessage.setMsg("查询成功");
            responseMessage.setData(img);
        }else{
            responseMessage.setCode(1000);
            responseMessage.setMsg("查询失败");
        }
        return responseMessage;
    }
    /**
     * 测试shiro
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("loginName") String userName,
                        @RequestParam("AdminPassword") String passWord){
        Subject currentuser= SecurityUtils.getSubject();

        if (!currentuser.isAuthenticated()){
            UsernamePasswordToken token=new UsernamePasswordToken(userName,passWord);
            token.setRememberMe(true);
            try {
                currentuser.login(token);
            }catch (AuthenticationException ae){
                System.out.println("登录失败"+ae.getMessage());
            }
        }
        return "redirect:/add.jsp";
    }
}
