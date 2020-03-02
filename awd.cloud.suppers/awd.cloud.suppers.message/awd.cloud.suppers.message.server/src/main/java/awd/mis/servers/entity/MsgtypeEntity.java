/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgtypeEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Msgtype";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_APPID = "APPID";
    public static final String ALIAS_APPNAME = "APP名称";
    public static final String ALIAS_MSGTYPE = "消息分类";
    public static final String ALIAS_ROLECODE = "角色（岗位）code，与manager数据库role关联";
    public static final String ALIAS_BUSINESSID = "业务ID";
    public static final String ALIAS_BUSINESSNAME = "业务名称";
    public static final String ALIAS_DESCRIPTION = "消息描述";
    public static final String ALIAS_ROUTINGKEY = "队列绑定key";
    

    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    private java.lang.String appid;

    @Length(max = 50, message = "APP名称最大长度50位", groups = CreateGroup.class)
    private java.lang.String appname;

    @Length(max = 30, message = "消息分类最大长度30位", groups = CreateGroup.class)
    private java.lang.String msgtype;

    @Length(max = 3, message = "角色（岗位）code，与manager数据库role关联最大长度3位", groups = CreateGroup.class)
    private java.lang.String rolecode;

    @Length(max = 50, message = "业务ID最大长度50位", groups = CreateGroup.class)
    private java.lang.String businessid;

    @Length(max = 255, message = "业务名称最大长度255位", groups = CreateGroup.class)
    private java.lang.String businessname;
    
    @Length(max = 99999, message = "业务名称最大长度99999位", groups = CreateGroup.class)
    private java.lang.String description;
    
    @Length(max = 300, message = "业务名称最大长度300位", groups = CreateGroup.class)
    private java.lang.String routingkey;

    public MsgtypeEntity() {
    }

    public java.lang.String getAppid() {
        return this.appid;
    }

    public void setAppid(java.lang.String value) {
        this.appid = value;
    }

    public java.lang.String getAppname() {
        return this.appname;
    }

    public void setAppname(java.lang.String value) {
        this.appname = value;
    }

    public java.lang.String getMsgtype() {
        return this.msgtype;
    }

    public void setMsgtype(java.lang.String value) {
        this.msgtype = value;
    }

    public java.lang.String getRolecode() {
        return this.rolecode;
    }

    public void setRolecode(java.lang.String value) {
        this.rolecode = value;
    }

    public java.lang.String getBusinessid() {
        return this.businessid;
    }

    public void setBusinessid(java.lang.String value) {
        this.businessid = value;
    }

    public java.lang.String getBusinessname() {
        return this.businessname;
    }

    public void setBusinessname(java.lang.String value) {
        this.businessname = value;
    }

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getRoutingkey() {
		return routingkey;
	}

	public void setRoutingkey(java.lang.String routingkey) {
		this.routingkey = routingkey;
	}
}

