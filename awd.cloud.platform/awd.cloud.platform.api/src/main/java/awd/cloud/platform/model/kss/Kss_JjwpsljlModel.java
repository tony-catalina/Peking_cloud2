/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.kss;

import awd.bj.kss.model.JjwpsljlModel;
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


public class Kss_JjwpsljlModel extends JjwpsljlModel {

	private String JbxxSnbh;
	private String JbxxXm;

	public String getJbxxSnbh() {
		return JbxxSnbh;
	}

	public void setJbxxSnbh(String jbxxSnbh) {
		JbxxSnbh = jbxxSnbh;
	}

	public String getJbxxXm() {
		return JbxxXm;
	}

	public void setJbxxXm(String jbxxXm) {
		JbxxXm = jbxxXm;
	}
	 
}

