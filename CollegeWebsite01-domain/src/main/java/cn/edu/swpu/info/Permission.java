package cn.edu.swpu.info;

public class Permission {
    /**
     * 权限id
     */
    private  Integer id;
    /**
     * 权限类型
     */
    private  String premissionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPremissionType() {
        return premissionType;
    }

    public void setPremissionType(String premissionType) {
        this.premissionType = premissionType;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", premissionType=" + premissionType +
                '}';
    }
}
