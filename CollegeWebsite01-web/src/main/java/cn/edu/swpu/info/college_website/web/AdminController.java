package cn.edu.swpu.info.college_website.web;


import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.services.AdminServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/Admin")
@Controller
public class AdminController {
    @Autowired
    AdminServiceImpl adminServiceImpl;

    /**
     * 根据前端传入的账户民
     * 根据usrename进行查找
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ModelAndView getAdmin(admin admin){
        String inf;
        System.out.println(admin.toString());
        admin admin1= adminServiceImpl.getAdminContent(admin.getLoginName());
        if (admin1.getAdminPassword().equals(admin.getAdminPassword()))
        {
            inf="登录成功";
        }else inf="账户名或密码错误";
        ModelAndView mav = new ModelAndView("get");
        mav.addObject("inf", inf);
        return mav;
    }


}
