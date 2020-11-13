package cn.edu.swpu.info.college_website.services;

import cn.edu.swpu.info.Permission;
import cn.edu.swpu.info.admin;
import cn.edu.swpu.info.college_website.dao.Impl.P_PermissionDaoImpl;
import cn.edu.swpu.info.college_website.dao.Impl.PermissionsDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 权限管理
 *
 */
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
//    授权管理时，需要查询相关信息
    public Permission selectPermission(admin admin){
        admin admin1=permissionsDaoImpl.selectAdminInfo(admin.getLoginName());
        Permission permission=p_permissionDaoImpl.selectObject(admin1.getAdminPermissions());
        return  permission;
    }
//    登录时，需要根据数据库信息进行验证
    public admin selectInformation(admin admin){
        return permissionsDaoImpl.selectAdminInfo(admin.getLoginName());
    }
}
