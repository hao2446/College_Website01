package cn.edu.swpu.info;

import java.util.Date;

public class Message {
    /**
     * 新闻编号
     */
    private Integer messageId;
    /**
     * 新闻标题
     */
    private String messageTitle;
    /**
     * 新闻类型
     */
    private Integer messageType;
    /**
     * 新闻内容
     */
    private String messageContent;
    /**
     * 新闻图片地址
     */
    private String messageImag;
    /**
     * 新闻审核日期
     */
    private Date createDate;
    private Date modifyDate;
    /**
     * 新闻审核状态
     */
    private Integer status;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageImag() {
        return messageImag;
    }

    public void setMessageImag(String messageImag) {
        this.messageImag = messageImag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
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
                "messageId=" + messageId +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageType=" + messageType +
                ", messageContent='" + messageContent + '\'' +
                ", messageImag='" + messageImag + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", status=" + status +
                '}';
    }
}
