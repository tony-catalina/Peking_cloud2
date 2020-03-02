package awd.mis.activiti.entity;

import java.util.Date;
import java.util.Map;

import lombok.Data;

/**
 * 流程中需要用到的变量
 * @author 123
 *
 */
@Data
public class Variables {
	//监所编号
	private String jsbh;
	//人员编号
	private String rybh;
	//任务创建时间
	private Date createtime;
	//任务标识
	private String taskDefinitionKey;
	//流程标识
	private String processDefinitionKey;
	//流程id
	private String processDefinitionId;
	//流程实例id
	private String processInstanceId;
	//taskId
	private String taskId;
	//任务名称
	private String name;
	//任务负责人
	private String assignee;
	//业务中使用到的参数
	private Map<String ,Object > params;
	//查询条件不等于值
	private Map<String ,Object > notEqualsMap;
	//查询条件大于等于值
	private Map<String ,Object > greaterThanOrEqualMap;
	//查询条件小于等于值
	private Map<String ,Object > lessThanOrEqualMap;
	
	//业务流程使用id
	private String ywlcid;

//	@JsonIgnore
	private String start;

//	@JsonIgnore
	private String limit;

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Map<String, Object> getNotEqualsMap() {
		return notEqualsMap;
	}

	public void setNotEqualsMap(Map<String, Object> notEqualsMap) {
		this.notEqualsMap = notEqualsMap;
	}

	public Map<String, Object> getGreaterThanOrEqualMap() {
		return greaterThanOrEqualMap;
	}

	public void setGreaterThanOrEqualMap(Map<String, Object> greaterThanOrEqualMap) {
		this.greaterThanOrEqualMap = greaterThanOrEqualMap;
	}

	public Map<String, Object> getLessThanOrEqualMap() {
		return lessThanOrEqualMap;
	}

	public void setLessThanOrEqualMap(Map<String, Object> lessThanOrEqualMap) {
		this.lessThanOrEqualMap = lessThanOrEqualMap;
	}

	public String getYwlcid() {
		return ywlcid;
	}

	public void setYwlcid(String ywlcid) {
		this.ywlcid = ywlcid;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	

}
