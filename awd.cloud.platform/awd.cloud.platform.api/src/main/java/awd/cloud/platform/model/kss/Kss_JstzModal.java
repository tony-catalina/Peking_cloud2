package awd.cloud.platform.model.kss;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.JstzModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kss_JstzModal implements Serializable{

	private String lcid;
	private JstzModel jstzEntity;
	private JbxxModel jbxxEntity;

	public JstzModel getJstzEntity() {
		return jstzEntity;
	}

	public void setJstzEntity(JstzModel jstzEntity) {
		this.jstzEntity = jstzEntity;
	}

	public JbxxModel getJbxxEntity() {
		return jbxxEntity;
	}

	public void setJbxxEntity(JbxxModel jbxxEntity) {
		this.jbxxEntity = jbxxEntity;
	}

	public String getLcid() {
		return lcid;
	}

	public void setLcid(String lcid) {
		this.lcid = lcid;
	}

}