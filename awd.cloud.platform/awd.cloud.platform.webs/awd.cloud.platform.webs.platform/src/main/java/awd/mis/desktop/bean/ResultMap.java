package awd.mis.desktop.bean;

import java.io.Serializable;
public class ResultMap implements Serializable {
	float use_time;
	Object info;
	String code;
	Object data;
	
	public float getUse_time() {
		return use_time;
	}
	public void setUse_time(float use_time) {
		this.use_time = use_time;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}

	
	

}
