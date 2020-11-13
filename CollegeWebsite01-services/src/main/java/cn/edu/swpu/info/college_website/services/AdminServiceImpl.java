package cn.edu.swpu.info.college_website.services;

import cn.edu.swpu.info.Message;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.common.MD5util;
import cn.edu.swpu.info.college_website.dao.Impl.AdminDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl {
    @Resource
    private AdminDaoImpl adminDaoImpl;
    /**
     * 获取用户表中的最大的id
     * @return
     */
    public Integer getMaxId(){
        return adminDaoImpl.selectMaxId();
    }
    /**
     * 根据主键进行查找是否存在该用户
     * @return
     */
    public admin getAdminContent(String loginName){
        return adminDaoImpl.selectObject(loginName);
    }
    /**
     * 查询所有的用户
     * @return
     */
    public List<admin> getAllAdmin(admin admin){
        return adminDaoImpl.selectObiectList(admin);
    }

    /**
     * 向数据库写入新注册的用户
     * @param admin
     * @return
     */
    public String registerUser(admin admin){
        String msg=null;
        admin.setAdminId(getMaxId()+1);
        //将用户密码进行加密存储
        if (admin.getAdminPassword()!=null){
            String password=(String) MD5util.stringMD5(admin.getLoginName(),(Object) admin.getAdminPassword());
            admin.setAdminPassword(password);
            System.out.println(admin.getAdminPassword());
        }
        System.out.println(admin.getAdminId()+"成功获取到了最大的id");
        if (adminDaoImpl.insertObject(admin)==1){
            msg="注册成功";
        }else {
            msg="注册失败";
        }
        return  msg;
    }

    /**
     * 修改权限
     * @param admin
     * @return
     */
    public int  modifyRight(admin admin){
        if (admin.getAdminPermissions()==1){
            admin.setAdminPermissions(2);
        }else {
            admin.setAdminPermissions(1);
        }
        return adminDaoImpl.updateObject(admin);
    }

}
