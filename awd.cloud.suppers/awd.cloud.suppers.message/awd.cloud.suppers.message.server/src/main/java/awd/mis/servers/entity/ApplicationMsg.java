package awd.mis.servers.entity;

import awd.mis.servers.enums.ClassifyEnum;
import awd.mis.servers.enums.TypeEnum;

import java.io.Serializable;

/**
 * @Description
 * @Author WS
 * @Date 2019-04-29 13:43
 */
public class ApplicationMsg implements Serializable {
    private static final long serialVersionUID = 4973871127697577825L;

    private String msgId;

    //消息生产服务
    private String produce;

    //消息类别
    private String classify;

    //发送角色
    private String role;
    
    //发送业务id
    private String businessid;
    
    //时间戳
    private Long timestamp;

    //消息体
    private MessageContent messageContent;
    
    public ApplicationMsg() {
    	this.timestamp = System.currentTimeMillis();
    }


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}


	public String getProduce() {
		return produce;
	}


	public void setProduce(String produce) {
		this.produce = produce;
	}


	public String getClassify() {
		return classify;
	}


	public void setClassify(String classify) {
		this.classify = classify;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getBusinessid() {
		return businessid;
	}


	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}


	public Long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}


	public MessageContent getMessageContent() {
		return messageContent;
	}


	public void setMessageContent(MessageContent messageContent) {
		this.messageContent = messageContent;
	}
    
    
}
