package cn.edu.swpu.info.college_website.web;

import cn.edu.swpu.info.Img;
import cn.edu.swpu.info.ResponseMessage;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.common.MD5util;
import cn.edu.swpu.info.college_website.common.savePicture;
import cn.edu.swpu.info.college_website.services.ShiroServiceImpl;
import cn.edu.swpu.info.college_website.services.ImgServiceImpl;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Img")
public class ImgController {

    @Resource
    ImgServiceImpl imgServiceImpl;
    @Autowired
    ShiroServiceImpl shiroServiceImpl;
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
        ResponseMessage<String> responseMessage=new ResponseMessage<>();
        if (!currentuser.isAuthenticated()){
            UsernamePasswordToken token=new UsernamePasswordToken(userName,passWord);
            token.setRememberMe(true);
            System.out.println("哈哈"+token);
            System.out.println(token.getHost());
            System.out.println(token.getPassword());
            try {
                currentuser.login(token);
                System.out.println("嘻嘻"+SecurityUtils.getSubject().getPrincipal());
                System.out.println(SecurityUtils.getSubject().getPrincipals());
                String tokenMD5= MD5util.cheackToken(token.getUsername());
                System.out.println("w哇哇"+tokenMD5);
                responseMessage.setToken(token);
                responseMessage.setCode(200);
                responseMessage.setMsg(tokenMD5);
            }catch (AuthenticationException ae){
                System.out.println("登录失败"+ae.getMessage());
            }
        }
        return "redirect:/add.jsp";
    }

    @RequestMapping(value = "/logins",method = RequestMethod.POST)
    public String  logins (@RequestParam("loginName") String userName,
                           @RequestParam("AdminPassword") String passWord){
        System.out.println(userName+passWord);

        Map<String, Object> result = new HashMap<>();
        //用户信息
        admin user = shiroServiceImpl.findByUsername(userName);
        //账号不存在、密码错误
        if (user == null || !user.getAdminPassword().equals(passWord)) {
            result.put("status", 400);
            result.put("msg", "账号或密码有误");
        } else {
            //生成token，并保存到数据库
            result = shiroServiceImpl.createToken(user.getAdminId());
            result.put("status", 200);
            result.put("msg", "登陆成功");
        }
        //return result;
        return "redirect:/add.jsp";
    }
    @RequestMapping("/identifyCode/img")
    public void getIdentifyImage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50);
        lineCaptcha.setGenerator(randomGenerator);
        // 重新生成code
        lineCaptcha.createCode();
        System.out.println("vcode为"+lineCaptcha.getCode());
        Image image=lineCaptcha.createImage(lineCaptcha.getCode());
        ImageIO.write((RenderedImage) image,"JPEG",response.getOutputStream());
    }
}
