package awd.cloud.platform.model.jls;

import java.util.List;
import awd.bj.jls.model.WpglModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.bj.base.model.Model;
import awd.bj.base.model.Variables;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WpglInfoModel implements Model {

	private String taskid;
	private String jsbh;
	private List<WpglModel> wpglEntities;
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

	public List<WpglModel> getWpglEntities() {
		return wpglEntities;
	}

	public void setWpglEntities(List<WpglModel> wpglEntities) {
		this.wpglEntities = wpglEntities;
	}

	public Variables getVariables() {
		return variables;
	}

	public void setVariables(Variables variables) {
		this.variables = variables;
	}

}
