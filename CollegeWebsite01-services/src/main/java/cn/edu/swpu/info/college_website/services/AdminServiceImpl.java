package cn.edu.swpu.info.college_website.services;

import cn.edu.swpu.info.Message;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.dao.Impl.AdminDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl {
    @Resource
   private AdminDaoImpl adminDaoImpl;

    /**
     * 根据主键进行查找新闻
     * @return
     */
    public admin getAdminContent(String loginName){

        return adminDaoImpl.selectObject(loginName);
    }


}
