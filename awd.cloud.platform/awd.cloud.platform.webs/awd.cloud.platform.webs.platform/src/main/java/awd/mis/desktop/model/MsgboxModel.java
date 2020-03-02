package awd.mis.desktop.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgboxModel implements Model {
	
	private java.lang.String id;
	private java.lang.String jsbh;
	private java.lang.String fsrjh;
	private java.lang.String xxjb;
	private java.lang.String xxjbString;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fssj;
	private java.lang.String fsnr;
	private java.lang.String fsnrString;
	private java.lang.String jsrjh;
	private java.lang.String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	private java.lang.String updator;
	private java.lang.String fsrxm;
	private java.lang.String jsrxm;
	//columns END


	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getFsrjh() {
		return this.fsrjh;
	}
	
	public void setFsrjh(java.lang.String value) {
		this.fsrjh = value;
	}
	
	public java.lang.String getXxjb() {
		return this.xxjb;
	}
	
	public void setXxjb(java.lang.String value) {
		this.xxjb = value;
	}
	
	public java.util.Date getFssj() {
		return this.fssj;
	}
	
	public void setFssj(java.util.Date value) {
		this.fssj = value;
	}
	
	public java.lang.String getFsnr() {
		return this.fsnr;
	}
	
	public void setFsnr(java.lang.String value) {
		this.fsnr = value;
	}
	
	public java.lang.String getJsrjh() {
		return this.jsrjh;
	}
	
	public void setJsrjh(java.lang.String value) {
		this.jsrjh = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}

	public void setFsrxm(String loginname) {
		this.fsrxm=loginname;		
	}

	public java.lang.String getJsrxm() {
		return jsrxm;
	}

	public void setJsrxm(java.lang.String jsrxm) {
		this.jsrxm = jsrxm;
	}

	public java.lang.String getFsrxm() {
		return fsrxm;
	}

	public String getFsnrString() {
		return fsnrString;
	}

	public String getXxjbString() {
		return xxjbString;
	}

	public void setXxjbString(String xxjbString) {
		this.xxjbString = xxjbString;
	}

	public void setFsnrString(String fsnrString) {
		this.fsnrString = fsnrString;
	}
}

