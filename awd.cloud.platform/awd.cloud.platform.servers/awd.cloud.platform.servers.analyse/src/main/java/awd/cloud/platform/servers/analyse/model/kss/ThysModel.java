/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThysModel implements Model {

	
	//columns START
	
	private String id;


	private String thlx;

	private String nr;

	private String lxmc;
	//columns END



	public ThysModel(){
	}
	public ThysModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getThlx() {
		return this.thlx;
	}

	public void setThlx(String value) {
		this.thlx = value;
	}
	public String getNr() {
		return this.nr;
	}

	public void setNr(String value) {
		this.nr = value;
	}
	public String getLxmc() {
		return this.lxmc;
	}

	public void setLxmc(String value) {
		this.lxmc = value;
	}
	 
}

