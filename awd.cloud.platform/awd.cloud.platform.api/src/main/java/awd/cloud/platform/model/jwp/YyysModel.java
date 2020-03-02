/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.jwp;

import awd.cloud.platform.utils.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class YyysModel implements Model {
    // columns START

    private java.lang.String id;

    private java.lang.String appcode;

    private java.lang.String jsbh;

    private java.lang.String jsh;

    private java.lang.String ysfs;

    private java.lang.String ysfsString;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date yssj;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date qqsj;

    private java.lang.String state;

    private java.lang.String creator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    private java.lang.String updator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;

    // columns END
    private String xm;

    private String rybh;

    private String yssc;


    public void setId(java.lang.String value) {
        this.id = value;
    }

    public java.lang.String getId() {
        return this.id;
    }

    public java.lang.String getAppcode() {
        return this.appcode;
    }

    public void setAppcode(java.lang.String value) {
        this.appcode = value;
    }

    public java.lang.String getJsbh() {
        return this.jsbh;
    }

    public void setJsbh(java.lang.String value) {
        this.jsbh = value;
    }

    public java.lang.String getJsh() {
        return this.jsh;
    }

    public void setJsh(java.lang.String value) {
        this.jsh = value;
    }

    public java.lang.String getYsfs() {
        return this.ysfs;
    }

    public void setYsfs(java.lang.String value) {
        this.ysfs = value;
    }

    public java.util.Date getYssj() {
        return this.yssj;
    }

    public void setYssj(java.util.Date value) {
        this.yssj = value;
    }

    public java.util.Date getQqsj() {
        return this.qqsj;
    }

    public void setQqsj(java.util.Date value) {
        this.qqsj = value;
    }

    public java.lang.String getState() {
        return this.state;
    }

    public void setState(java.lang.String value) {
        this.state = value;
    }

    public java.lang.String getCreator() {
        return this.creator;
    }

    public void setCreator(java.lang.String value) {
        this.creator = value;
    }

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }

    public java.lang.String getUpdator() {
        return this.updator;
    }

    public void setUpdator(java.lang.String value) {
        this.updator = value;
    }

    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(java.util.Date value) {
        this.updatetime = value;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh;
    }

    public String getYssc() {
        return yssc;
    }

    public void setYssc(String yssc) {
        this.yssc = yssc;
    }

    public String getYsfsString() {
        return ysfsString;
    }

    public void setYsfsString(String ysfsString) {
        this.ysfsString = ysfsString;
    }
}

