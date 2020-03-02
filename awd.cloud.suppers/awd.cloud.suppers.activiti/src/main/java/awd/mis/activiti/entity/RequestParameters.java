package awd.mis.activiti.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangshuai
 * Date: 2018/4/26 13:35
 *
 * @description: 参数
 **/


public class RequestParameters implements Serializable {

    private String jsbh;

    private FlowMap flowmapEntity;

    private List<FlowMap > flowmapEntityList;

    private FlowNode flownodeEntity;

    private List<FlowNode> flownodeEntityList;

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

	public FlowMap getFlowmapEntity() {
		return flowmapEntity;
	}

	public void setFlowmapEntity(FlowMap flowmapEntity) {
		this.flowmapEntity = flowmapEntity;
	}

	public List<FlowMap> getFlowmapEntityList() {
		return flowmapEntityList;
	}

	public void setFlowmapEntityList(List<FlowMap> flowmapEntityList) {
		this.flowmapEntityList = flowmapEntityList;
	}

	public FlowNode getFlownodeEntity() {
		return flownodeEntity;
	}

	public void setFlownodeEntity(FlowNode flownodeEntity) {
		this.flownodeEntity = flownodeEntity;
	}

	public List<FlowNode> getFlownodeEntityList() {
		return flownodeEntityList;
	}

	public void setFlownodeEntityList(List<FlowNode> flownodeModelList) {
		this.flownodeEntityList = flownodeModelList;
	}

  
}
