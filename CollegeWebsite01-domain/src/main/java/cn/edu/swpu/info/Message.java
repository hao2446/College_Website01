package cn.edu.swpu.info;



import java.util.Date;

public class Message {

    private Integer messageid;

    private String messagetitle;

    private String messagetype;

    private String messagecontent;

    private String author;

   // private String data;
    private String messageimag;

    private String createdate;

    private Date modifydate;

    private Integer status;

    public String getMessageimag() {
        return messageimag;
    }

    public void setMessageimag(String messageimag) {
        this.messageimag = messageimag;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getMessagetitle() {
        return messagetitle;
    }

    public void setMessagetitle(String messagetitle) {
        this.messagetitle = messagetitle;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String  messagetype) {
        this.messagetype = messagetype;
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
