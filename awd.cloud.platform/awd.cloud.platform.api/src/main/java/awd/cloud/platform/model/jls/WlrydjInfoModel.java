package awd.cloud.platform.model.jls;

import awd.bj.base.model.Model;
import awd.bj.jls.model.WlrydjClxxModel;
import awd.bj.jls.model.WlrydjModel;
import awd.bj.jls.model.WlrydjRyxxModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WlrydjInfoModel implements Model {

	private String jsbh;
	private WlrydjModel wlrydjEntity;
	private List<WlrydjRyxxModel> ryxxList;
	private List<WlrydjClxxModel> clxxList;

	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	public WlrydjModel getWlrydjEntity() {
		return wlrydjEntity;
	}

	public void setWlrydjEntity(WlrydjModel wlrydjEntity) {
		this.wlrydjEntity = wlrydjEntity;
	}

	public List<WlrydjRyxxModel> getRyxxList() {
		return ryxxList;
	}

	public void setRyxxList(List<WlrydjRyxxModel> ryxxList) {
		this.ryxxList = ryxxList;
	}

	public List<WlrydjClxxModel> getClxxList() {
		return clxxList;
	}

	public void setClxxList(List<WlrydjClxxModel> clxxList) {
		this.clxxList = clxxList;
	}
}
