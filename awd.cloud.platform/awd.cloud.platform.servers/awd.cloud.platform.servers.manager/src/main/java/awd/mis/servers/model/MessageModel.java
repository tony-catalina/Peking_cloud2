package awd.mis.servers.model;

import java.io.Serializable;

/**
 * @program: awd.cloud
 * @description: 消息对象
 * @author: WS
 * @create: 2019-03-19
 **/

public class MessageModel implements Serializable {

    private static final long serialVersionUID = 6705194485555778241L;
    /**
     * 消息ID
     */
    private String messageId;

    //JSBH
    private String jsbh;

    //级别 1、紧急 2、普通
    private String level;

    //时间戳
    private Long timestamp;

    //接收者
    private String receiver;

    //接收者编号
    private String receiverIdentifier;

    //发送者编号
    private String senderIdentifier;

    //发送者姓名
    private String senderName;

    //角色
    private String role;

    //所有用户
    private boolean allUsers = false;

    //监所用户
    private boolean jsUsers = false;

    //消息内容
    private byte[] content;

    public MessageModel() {
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAllUsers() {
        return allUsers;
    }

    public void setAllUsers(boolean allUsers) {
        this.allUsers = allUsers;
    }

    public boolean isJsUsers() {
        return jsUsers;
    }

    public void setJsUsers(boolean jsUsers) {
        this.jsUsers = jsUsers;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getSenderIdentifier() {
        return senderIdentifier;
    }

    public void setSenderIdentifier(String senderIdentifier) {
        this.senderIdentifier = senderIdentifier;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverIdentifier() {
        return receiverIdentifier;
    }

    public void setReceiverIdentifier(String receiverIdentifier) {
        this.receiverIdentifier = receiverIdentifier;
    }
}
