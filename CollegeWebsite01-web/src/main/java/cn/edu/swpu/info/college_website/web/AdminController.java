package cn.edu.swpu.info.college_website.web;


import cn.edu.swpu.info.ResponseMessage;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.services.AdminServiceImpl;

import cn.edu.swpu.info.college_website.services.ShiroServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/Admin")
@Controller
public class AdminController {
    @Autowired
    AdminServiceImpl adminServiceImpl;
    @Autowired
    ShiroServiceImpl shiroServiceImpl;
    /**
     * 根据前端传入的账户名
     * 根据usrename进行查找
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseMessage getAdmin(@RequestBody admin admin1){
           ResponseMessage<Map<String, Object>> responseMessage=new ResponseMessage<>();
        admin user = shiroServiceImpl.findByUsername(admin1.getLoginName());
        //账号不存在、密码错误
        if (user == null || !user.getAdminPassword().equals(admin1.getAdminPassword())) {
            responseMessage.setCode(400);
            responseMessage.setMsg("账号或密码有误");
        } else {
            //生成token，并保存到数据库
            responseMessage.setData(shiroServiceImpl.createToken(user.getAdminId()));
            responseMessage.setCode(200);
            responseMessage.setMsg("登陆成功");
        }
        System.out.println(user.toString());
        return responseMessage;
//        Subject currentuser= SecurityUtils.getSubject();
//        if (!currentuser.isAuthenticated()){
//            UsernamePasswordToken token=new UsernamePasswordToken(admin.getLoginName(),admin.getAdminPassword());
//            token.setRememberMe(true);
//            try {
//                currentuser.login(token);
//                responseMessage.setMsg("登录成功");
//                responseMessage.setCode(200);
//            }catch (AuthenticationException ae){
//                responseMessage.setMsg("登录失败");
//                responseMessage.setCode(1000);
//                System.out.println("登录失败"+ae.getMessage());
//            }
//        }

//        admin admin1= adminServiceImpl.getAdminContent(admin.getLoginName());
//        System.out.println(admin1);

//        if (admin1==null){
//            responseMessage.setMsg("登录失败");
//            return responseMessage;
//        }
//        if (admin1.getAdminPassword().equals(admin1.getAdminPassword()))
//        {
//            responseMessage.setCode(200);
//            responseMessage.setMsg("登录成功");
//        }else
//            {
//                responseMessage.setMsg("账户名或密码错误");
//                responseMessage.setCode(1000);
//            }

    }
    @ResponseBody
    @RequestMapping(value="/getAllAdmin",method = RequestMethod.POST)
    public List<admin> getAllAdmin(){
        admin admin =new admin();
        List<admin> list =adminServiceImpl.getAllAdmin(admin);
        ResponseMessage<String> responseMessage=new ResponseMessage<>();
        if (list==null){
            responseMessage.setMsg("查询失败");
            return null;
        }else
        {
            return list;
        }
    }
    /**
     * 添加新注册的用户
     * 根据后端先行查询总的记录数来确定插入时的主键
     * @return
     */
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> registerUser(@RequestBody admin admin)   {
//        admin admin=new admin();
//        admin.setLoginName(loginName);
//        admin.setAdminPassword(AdminPassword);
        System.out.println(admin);
//        System.out.println(password+loginName);
//       admin admin=new admin();
//       admin.setAdminPassword(password);
//       admin.setLoginName(loginName);
//       System.out.println(admin.toString());
        String msg=adminServiceImpl.registerUser(admin);
//        System.out.println(msg);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        responseMessage.setCode(1000);
        responseMessage.setMsg(msg);
        return responseMessage;
    }

    /**
     * 修改用户权限，0超级管理员，1管理员，2平民
     * 根据后端先行查询总的记录数来确定插入时的主键
     * @return
     */
    @RequestMapping(value = "/modifyRight",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> modifyRight(@RequestBody admin admin)  {
        int result=0;
        result=adminServiceImpl.modifyRight(admin);
        ResponseMessage<String> responseMessage=new ResponseMessage<>();
        if (result==1){
            responseMessage.setCode(200);
            responseMessage.setMsg("修改权限成功");
        }else {
            responseMessage.setMsg("修改权限失败");
            responseMessage.setCode(1000);
        }
        return responseMessage;
    }


}
