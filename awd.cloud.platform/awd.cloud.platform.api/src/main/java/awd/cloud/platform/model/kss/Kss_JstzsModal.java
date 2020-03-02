package awd.cloud.platform.model.kss;

import awd.cloud.platform.entity.JbxxEntity;
import awd.cloud.platform.entity.JstzEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kss_JstzsModal implements Serializable {

	private String lcid;
	private List<JstzEntity> jstzEntitys;
	private List<JbxxEntity> jbxxEntities;

	public List<JstzEntity> getJstzEntitys() {
		return jstzEntitys;
	}

	public void setJstzEntitys(List<JstzEntity> jstzEntitys) {
		this.jstzEntitys = jstzEntitys;
	}

	public List<JbxxEntity> getJbxxEntities() {
		return jbxxEntities;
	}

	public void setJbxxEntities(List<JbxxEntity> jbxxEntities) {
		this.jbxxEntities = jbxxEntities;
	}

	public String getLcid() {
		return lcid;
	}

	public void setLcid(String lcid) {
		this.lcid = lcid;
	}

}
