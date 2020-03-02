package awd.mis.servers.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class MsgboxModel implements Model {
	
	private java.lang.String jsbh;
	private java.lang.String fsrjh;
	private java.lang.String xxjb;
	private java.util.Date fssj;
	private java.lang.String fssjString;
	private java.lang.String fsnr;
	private java.lang.String jsrjh;
	private java.lang.String state;
	private java.util.Date createtime;
	private java.lang.String creator;
	private java.lang.String fsrxm;
	private java.lang.String jsrxm;
	//columns END
	
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
	
	public java.lang.String getFssjString() {
		return this.fssjString;
	}
	public void setFssjString(java.lang.String value) {
		this.fssjString=value;
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
	
	
}

