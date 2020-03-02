package awd.cloud.platform.model.kss;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class TafModelIDO{
	//主犯人员编号
	private String zrybh;
	
	//从犯人员编号
	private String frybh;
	
	private String jsbh;
	
	private String ay;
	
	private String creator;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date createtime;

	public String getZrybh() {
		return zrybh;
	}

	public void setZrybh(String zrybh) {
		this.zrybh = zrybh;
	}

	public String getFrybh() {
		return frybh;
	}

	public void setFrybh(String frybh) {
		this.frybh = frybh;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	public String getAy() {
		return ay;
	}

	public void setAy(String ay) {
		this.ay = ay;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	
}
