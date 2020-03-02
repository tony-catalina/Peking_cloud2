/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.kss;

import awd.bj.kss.model.JsswModel;
import awd.cloud.platform.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class Kss_JsswModel extends JsswModel {

	private String jbxxJsh;
	private String xm;
	private String snbh;

	public String getJbxxJsh() {
		return jbxxJsh;
	}
	public void setJbxxJsh(String jbxxJsh) {
		this.jbxxJsh = jbxxJsh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSnbh() {
		return snbh;
	}
	public void setSnbh(String snbh) {
		this.snbh = snbh;
	}
	 
}

