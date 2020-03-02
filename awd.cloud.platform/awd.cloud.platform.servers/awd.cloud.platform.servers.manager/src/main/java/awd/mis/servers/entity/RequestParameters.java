package awd.mis.servers.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.mis.servers.tools.CacheUtils;


/**
 * Created with IntelliJ IDEA.
 * User: wangshuai
 * Date: 2018/4/26 13:35
 *
 * @description: 参数
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestParameters implements Serializable {

    private String jsbh;

    private FlowmapEntity flowmapEntity;

    private List<FlowmapEntity > flowmapEntityList;

    private FlownodeEntity flownodeEntity;

    private List<FlownodeEntity > flownodeEntityList;

    public String getJsbh() {
        return jsbh;
    }
    
    public String getJsbhString() {
    	return CacheUtils.get().getJsbhString(this.jsbh);
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public FlowmapEntity getFlowmapEntity() {
        return flowmapEntity;
    }

    public void setFlowmapEntity(FlowmapEntity flowmapEntity) {
        this.flowmapEntity = flowmapEntity;
    }

    public List<FlowmapEntity> getFlowmapEntityList() {
        return flowmapEntityList;
    }

    public void setFlowmapEntityList(List<FlowmapEntity> flowmapEntityList) {
        this.flowmapEntityList = flowmapEntityList;
    }

    public FlownodeEntity getFlownodeEntity() {
        return flownodeEntity;
    }

    public void setFlownodeEntity(FlownodeEntity flownodeEntity) {
        this.flownodeEntity = flownodeEntity;
    }

    public List<FlownodeEntity> getFlownodeEntityList() {
        return flownodeEntityList;
    }

    public void setFlownodeEntityList(List<FlownodeEntity> flownodeEntityList) {
        this.flownodeEntityList = flownodeEntityList;
    }
}
