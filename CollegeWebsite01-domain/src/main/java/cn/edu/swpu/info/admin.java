package cn.edu.swpu.info;

public class admin {
<<<<<<< Updated upstream
    /**
     * 管理员Id
     */
    private Integer adminId;
=======
//    /**
//     * 管理员Id
//     */
   private Integer adminId;
>>>>>>> Stashed changes
    /**
     * 管理员登录名
     */
    private String loginName;
    /**
     * 管理员密码
     */
    private String adminPassword;
    /**
     * 管理员权限
     */
    private Integer adminPermissions;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getAdminPermissions() {
        return adminPermissions;
    }

    public void setAdminPermissions(Integer adminPermissions) {
        this.adminPermissions = adminPermissions;
    }

    @Override
    public String toString() {
        return "admin{" +
                "adminId=" + adminId +
                ", loginName='" + loginName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminPermissions=" + adminPermissions +
                '}';
    }
}
