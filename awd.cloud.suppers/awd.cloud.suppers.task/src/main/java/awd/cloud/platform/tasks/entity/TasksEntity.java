/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.tasks.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import awd.framework.common.entity.SimpleGenericEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class TasksEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Tasks";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TASKTYPE = "任务类型";
	public static final String ALIAS_TASKNAME = "任务名称";
	public static final String ALIAS_URL = "服务地址";
	public static final String ALIAS_PARAMS = "参数";
	public static final String ALIAS_SCHEDULE = "任务调度";
	public static final String ALIAS_EXTIME = "最后一次执行时间";
	public static final String ALIAS_STOPTIME = "禁止时间段内运行";
	public static final String ALIAS_EXSTATE = "最后一次执行状态";
	public static final String ALIAS_EXRESULT = "最后一次返回结果";
	public static final String ALIAS_EXTIMES = "已执行次数";
	public static final String ALIAS_ENABLE = "是否启用";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START

	@NotNull(message="id不能为空",groups=CreateGroup.class)
    @Length(max=23,message="id最大长度23位" ,groups=CreateGroup.class)
	private java.lang.String id;

    @Length(max=30,message="任务类型最大长度30位" ,groups=CreateGroup.class)
	private java.lang.String tasktype;

    @Length(max=500,message="任务名称最大长度500位" ,groups=CreateGroup.class)
	private java.lang.String taskname;

    @Length(max=65535,message="服务地址最大长度65535位" ,groups=CreateGroup.class)
	private java.lang.String url;

    @Length(max=65535,message="参数最大长度65535位" ,groups=CreateGroup.class)
	private java.lang.String params;

    @Length(max=65535,message="任务调度最大长度65535位" ,groups=CreateGroup.class)
	private java.lang.String schedule;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date extime;

    @Length(max=200,message="禁止时间段内运行最大长度200位" ,groups=CreateGroup.class)
	private java.lang.String stoptime;

    @Length(max=20,message="最后一次执行状态最大长度20位" ,groups=CreateGroup.class)
	private java.lang.String exstate;

    @Length(max=2147483647,message="最后一次返回结果最大长度2147483647位" ,groups=CreateGroup.class)
	private java.lang.String exresult;

    @Length(max=20,message="已执行次数最大长度20位" ,groups=CreateGroup.class)
	private java.lang.String extimes;

    @Length(max=1,message="是否启用最大长度1位" ,groups=CreateGroup.class)
	private java.lang.String enable;

	//columns END


	public TasksEntity(){
	}

	/*public TasksEntity(
		java.lang.String id
	){
		this.id = id;
	}*/



	public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}


	public java.lang.String getTasktype() {
		return this.tasktype;
	}

	public void setTasktype(java.lang.String value) {
		this.tasktype = value;
	}

	public java.lang.String getTaskname() {
		return this.taskname;
	}

	public void setTaskname(java.lang.String value) {
		this.taskname = value;
	}

	public java.lang.String getUrl() {
		return this.url;
	}

	public void setUrl(java.lang.String value) {
		this.url = value;
	}

	public java.lang.String getParams() {
		return this.params;
	}

	public void setParams(java.lang.String value) {
		this.params = value;
	}

	public java.lang.String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(java.lang.String value) {
		this.schedule = value;
	}
	public java.util.Date getExtime() {
			return this.extime;
			}

	public void setExtime(java.util.Date value) {
			this.extime = value;
			}

	public java.lang.String getStoptime() {
		return this.stoptime;
	}

	public void setStoptime(java.lang.String value) {
		this.stoptime = value;
	}

	public java.lang.String getExstate() {
		return this.exstate;
	}

	public void setExstate(java.lang.String value) {
		this.exstate = value;
	}

	public java.lang.String getExresult() {
		return this.exresult;
	}

	public void setExresult(java.lang.String value) {
		this.exresult = value;
	}

	public java.lang.String getExtimes() {
		return this.extimes;
	}

	public void setExtimes(java.lang.String value) {
		this.extimes = value;
	}

	public java.lang.String getEnable() {
		return this.enable;
	}

	public void setEnable(java.lang.String value) {
		this.enable = value;
	}
}

