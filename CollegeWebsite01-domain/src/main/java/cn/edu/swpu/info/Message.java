package cn.edu.swpu.info;



import java.security.PrivilegedExceptionAction;
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
    private Integer clickrate;
    private Integer total;
    private Integer start;
    private Integer last;
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getClickrate() {
        return clickrate;
    }

    public void setClickrate(Integer clickrate) {
        this.clickrate = clickrate;
    }

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

    @Override
    public String toString() {
        return "Message{" +
                "messageid=" + messageid +
                ", messagetitle='" + messagetitle + '\'' +
                ", messagetype='" + messagetype + '\'' +
                ", messagecontent='" + messagecontent + '\'' +
                ", author='" + author + '\'' +
                ", messageimag='" + messageimag + '\'' +
                ", createdate='" + createdate + '\'' +
                ", modifydate=" + modifydate +
                ", status=" + status +
                ", clickrate=" + clickrate +
                ", total*********=" + total +
                ", start=" + start +
                ", last=" + last +
                ", page=" + page +
                '}';
    }
}
