package awd.cloud.platform.model.kss;

import awd.bj.base.model.Variables;
import awd.bj.kss.model.ClcsModel;
import awd.bj.kss.model.JbxxModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kss_ClcsInfoModel {
	private String taskid;
	private String jsbh;
	private JbxxModel jbxxEntity;
	private ClcsModel clcsEntity;
	private Variables variables;

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	public JbxxModel getJbxxEntity() {
		return jbxxEntity;
	}

	public void setJbxxEntity(JbxxModel jbxxEntity) {
		this.jbxxEntity = jbxxEntity;
	}

	public ClcsModel getClcsEntity() {
		return clcsEntity;
	}

	public void setClcsEntity(ClcsModel clcsEntity) {
		this.clcsEntity = clcsEntity;
	}

	public Variables getVariables() {
		return variables;
	}

	public void setVariables(Variables variables) {
		this.variables = variables;
	}

}
