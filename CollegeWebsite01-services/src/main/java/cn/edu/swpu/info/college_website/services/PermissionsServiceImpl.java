package cn.edu.swpu.info.college_website.services;

import cn.edu.swpu.info.Permission;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.dao.Impl.P_PermissionDaoImpl;
import cn.edu.swpu.info.college_website.dao.Impl.PermissionsDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionsServiceImpl {
    @Resource
    private PermissionsDaoImpl permissionsDaoImpl;
    @Resource
    private P_PermissionDaoImpl p_permissionDaoImpl;

    /**
     * 根据用户登录名来查询用户的信息
     * @param admin
     * @return
     */
    public Permission selectPermission(admin admin){
        admin admin1=permissionsDaoImpl.selectAdminInfo(admin.getLoginName());
        Permission permission=p_permissionDaoImpl.selectObject(admin1.getAdminPermissions());
        return  permission;
    }
    public admin selectInformation(admin admin){
        return permissionsDaoImpl.selectAdminInfo(admin.getLoginName());
    }
}
