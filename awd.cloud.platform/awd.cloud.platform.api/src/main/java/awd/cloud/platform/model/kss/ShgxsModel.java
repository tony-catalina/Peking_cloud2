package awd.cloud.platform.model.kss;

import awd.bj.kss.model.ShgxModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShgxsModel {

	private String jsbh;
	private String rybh;
	private List<ShgxModel> shgxModels;

	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	public String getRybh() {
		return rybh;
	}

	public void setRybh(String rybh) {
		this.rybh = rybh;
	}

	public List<ShgxModel> getShgxModels() {
		return shgxModels;
	}

	public void setShgxModels(List<ShgxModel> shgxModels) {
		this.shgxModels = shgxModels;
	}

}
