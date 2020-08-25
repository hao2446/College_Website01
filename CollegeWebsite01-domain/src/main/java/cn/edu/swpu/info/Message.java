package cn.edu.swpu.info;

import java.util.Date;

public class Message {
    private Integer messageid;

    private String messagetitle;

    private Integer messagetype;

    private String messageimag;

    private Date createdate;

    private Date modifydate;

    private Integer status;

    private String messagecontent;

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
        this.messagetitle = messagetitle == null ? null : messagetitle.trim();
    }

    public Integer getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(Integer messagetype) {
        this.messagetype = messagetype;
    }

    public String getMessageimag() {
        return messageimag;
    }

    public void setMessageimag(String messageimag) {
        this.messageimag = messageimag == null ? null : messageimag.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
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

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent == null ? null : messagecontent.trim();
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageid=" + messageid +
                ", messagetitle='" + messagetitle + '\'' +
                ", messagetype=" + messagetype +
                ", messageimag='" + messageimag + '\'' +
                ", createdate=" + createdate +
                ", modifydate=" + modifydate +
                ", status=" + status +
                ", messagecontent='" + messagecontent + '\'' +
                '}';
    }
}