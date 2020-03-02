package awd.cloud.platform.model.jls;

import awd.bj.base.model.Model;
import awd.bj.jls.model.ShgxModel;
import awd.bj.jls.model.WpglModel;
import awd.bj.jls.model.WpjsModel;
import awd.bj.jls.model.XjjsModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SqswInfoModel implements Model {

	private String taskid;
	private String jsbh;
	private ShgxModel shgxEntity;
	private XjjsModel xjjsEmtity;
	private WpjsModel wpjsEntity;
	private List<WpglModel> wpglList;

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

	public ShgxModel getShgxEntity() {
		return shgxEntity;
	}

	public void setShgxEntity(ShgxModel shgxEntity) {
		this.shgxEntity = shgxEntity;
	}

	public XjjsModel getXjjsEmtity() {
		return xjjsEmtity;
	}

	public void setXjjsEmtity(XjjsModel xjjsEmtity) {
		this.xjjsEmtity = xjjsEmtity;
	}

	public WpjsModel getWpjsEntity() {
		return wpjsEntity;
	}

	public void setWpjsEntity(WpjsModel wpjsEntity) {
		this.wpjsEntity = wpjsEntity;
	}

	public List<WpglModel> getWpglList() {
		return wpglList;
	}

	public void setWpglList(List<WpglModel> wpglList) {
		this.wpglList = wpglList;
	}
}
