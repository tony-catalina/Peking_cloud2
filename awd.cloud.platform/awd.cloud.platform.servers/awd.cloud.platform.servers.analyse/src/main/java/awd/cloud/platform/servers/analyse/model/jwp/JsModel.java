/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.jwp;

import awd.framework.common.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsModel implements Model {
	
	//columns START
	
	private String id;

	private String jsbh;

	private String jsh;

	private String zgmj;

	private String xgmj;

	private String zpurl;

	public JsModel(){
	}

	public JsModel(String id, String jsbh, String jsh, String zgmj, String xgmj, String zpurl) {
		this.id = id;
		this.jsbh = jsbh;
		this.jsh = jsh;
		this.zgmj = zgmj;
		this.xgmj = xgmj;
		this.zpurl = zpurl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	public String getJsh() {
		return jsh;
	}

	public void setJsh(String jsh) {
		this.jsh = jsh;
	}

	public String getZgmj() {
		return zgmj;
	}

	public void setZgmj(String zgmj) {
		this.zgmj = zgmj;
	}

	public String getXgmj() {
		return xgmj;
	}

	public void setXgmj(String xgmj) {
		this.xgmj = xgmj;
	}

	public String getZpurl() {
		return zpurl;
	}

	public String setZpurl(String zpurl) {
		this.zpurl = zpurl;
		return zpurl;
	}
}

