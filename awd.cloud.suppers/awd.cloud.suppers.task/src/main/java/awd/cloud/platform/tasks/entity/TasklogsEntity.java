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


public class TasklogsEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Tasklogs";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TASKID = "任务编号";
	public static final String ALIAS_EXKSSJ = "执行开始时间";
	public static final String ALIAS_EXJSSJ = "执行结束时间";
	public static final String ALIAS_EXHS = "耗时";
	public static final String ALIAS_EXSTATE = "执行状态";
	public static final String ALIAS_EXRESULT = "执行结果";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START

	@NotNull(message="id不能为空",groups=CreateGroup.class)
    @Length(max=23,message="id最大长度23位" ,groups=CreateGroup.class)
	private java.lang.String id;

    @Length(max=23,message="任务编号最大长度23位" ,groups=CreateGroup.class)
	private java.lang.String taskid;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date exkssj;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date exjssj;

    @Length(max=12,message="耗时最大长度12位" ,groups=CreateGroup.class)
	private java.lang.Float exhs;

    @Length(max=20,message="执行状态最大长度20位" ,groups=CreateGroup.class)
	private java.lang.String exstate;

    @Length(max=2147483647,message="执行结果最大长度2147483647位" ,groups=CreateGroup.class)
	private java.lang.String exresult;

	//columns END


	public TasklogsEntity(){
	}

	/*public TasklogsEntity(
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


	public java.lang.String getTaskid() {
		return this.taskid;
	}

	public void setTaskid(java.lang.String value) {
		this.taskid = value;
	}
	public java.util.Date getExkssj() {
			return this.exkssj;
			}

	public void setExkssj(java.util.Date value) {
			this.exkssj = value;
			}
	public java.util.Date getExjssj() {
			return this.exjssj;
			}

	public void setExjssj(java.util.Date value) {
			this.exjssj = value;
			}

	public java.lang.Float getExhs() {
		return this.exhs;
	}

	public void setExhs(java.lang.Float value) {
		this.exhs = value;
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
}

