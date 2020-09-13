package cn.edu.swpu.info.college_website.web;



import cn.edu.swpu.info.ResponseMessage;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.services.AdminServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/Admin")
@Controller
public class AdminController {
    @Autowired
    AdminServiceImpl adminServiceImpl;

    /**
     * 根据前端传入的账户名
     * 根据usrename进行查找
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseMessage getAdmin(@RequestBody admin  admin){
        System.out.println(admin.toString());
        admin admin1= adminServiceImpl.getAdminContent(admin.getLoginName());
        System.out.println(admin1);
        ResponseMessage<String> responseMessage=new ResponseMessage<>();
        if (admin1==null){
            responseMessage.setMsg("登录失败");
            return responseMessage;
        }
        if (admin1.getAdminPassword().equals(admin1.getAdminPassword()))
        {
            responseMessage.setCode(200);
            responseMessage.setMsg("登录成功");
        }else
            {
                responseMessage.setMsg("账户名或密码错误");
                responseMessage.setCode(1000);
            }

        return responseMessage;
    }

    /**
     * 添加新注册的用户
     * 根据后端先行查询总的记录数来确定插入时的主键
     * @return
     */
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<String> registerUser(@RequestParam("adminPassword") String password,@RequestParam("loginName") String loginName)  {
//        System.out.println(password+loginName);
       admin admin=new admin();
       admin.setAdminPassword(password);
       admin.setLoginName(loginName);
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
//    @ResponseBody
    @RequestMapping(value = "/modifyRight",method = RequestMethod.POST)
    public ResponseMessage modifyRight(admin admin)  {
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
