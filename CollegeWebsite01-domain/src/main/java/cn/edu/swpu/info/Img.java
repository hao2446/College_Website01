package cn.edu.swpu.info;

public class Img {
    /**
     * 图片存储Id
     */
    private Integer Id;
    /**
     * 图片名称
     */
    private String Imgname;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getImgname() {
        return Imgname;
    }

    public void setImgname(String imgname) {
        Imgname = imgname;
    }

    @Override
    public String toString() {
        return "Img{" +
                "Id=" + Id +
                ", Imgname='" + Imgname + '\'' +
                '}';
    }
}
