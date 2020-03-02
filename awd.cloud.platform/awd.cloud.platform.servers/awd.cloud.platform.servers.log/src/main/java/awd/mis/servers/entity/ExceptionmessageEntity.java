/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.DateTimeUtils;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionmessageEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Exceptionmessage";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_MESSAGEID = "消息ID";
    public static final String ALIAS_CONTENT = "消息内容";
    public static final String ALIAS_CSCS = "重试次数";
    public static final String ALIAS_FLAG = "处理状况(异常消息SHFO处理)";
    public static final String ALIAS_CREATETIME = "创建时间";

    //保存组（不需要id验证）
    public static interface SaveGroup {
    }

    ;

    //新增组（需要id验证）
    public static interface UpdateGroup {
    }

    ;

    //所有组
    @GroupSequence({SaveGroup.class, UpdateGroup.class})
    public interface All {
    }

    //columns START

    @Length(max = 50, message = "消息ID最大长度50位", groups = SaveGroup.class)
    private java.lang.String messageid;

    @Length(max = 2147483647, message = "消息内容最大长度2147483647位", groups = SaveGroup.class)
    private java.lang.String content;

    @Length(max = 10, message = "重试次数最大长度10位", groups = SaveGroup.class)
    private java.lang.Integer cscs;

    @Length(max = 1, message = "处理状况(异常消息SHFO处理)最大长度1位", groups = SaveGroup.class)
    private java.lang.String flag;


    @NotNull(message = "创建时间不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    //columns END


    public ExceptionmessageEntity() {
    }

    public ExceptionmessageEntity(
            java.lang.String id
    ) {
        super.setId(id);
    }


    public java.lang.String getMessageid() {
        return this.messageid;
    }

    public void setMessageid(java.lang.String value) {
        this.messageid = value;
    }

    public java.lang.String getContent() {
        return this.content;
    }

    public void setContent(java.lang.String value) {
        this.content = value;
    }

    public java.lang.Integer getCscs() {
        return this.cscs;
    }

    public void setCscs(java.lang.Integer value) {
        this.cscs = value;
    }

    public java.lang.String getFlag() {
        return this.flag;
    }

    public void setFlag(java.lang.String value) {
        this.flag = value;
    }


    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }

}

