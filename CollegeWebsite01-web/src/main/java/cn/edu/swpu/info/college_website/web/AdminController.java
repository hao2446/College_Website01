package cn.edu.swpu.info.college_website.web;


import cn.edu.swpu.info.ResponseMessage;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.services.AdminServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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


}
