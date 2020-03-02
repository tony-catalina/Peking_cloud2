/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JqModel implements Model {

	
	//columns START
	
	private String id;
	private String jsbh;
	private String jqh;
	private String jqmc;
	private String jqicons;
	private String bz;

	//columns END
	private String jsbhString;



	public JqModel(){
	}
	public JqModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getJqh() {
		return this.jqh;
	}

	public void setJqh(String value) {
		this.jqh = value;
	}
	public String getJqmc() {
		return this.jqmc;
	}

	public void setJqmc(String value) {
		this.jqmc = value;
	}
	public String getJqicons() {
		return this.jqicons;
	}

	public void setJqicons(String value) {
		this.jqicons = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJsbhString() {
		return jsbhString;
	}

	public void setJsbhString(String jsbhString) {
		this.jsbhString = jsbhString;
	}
}

