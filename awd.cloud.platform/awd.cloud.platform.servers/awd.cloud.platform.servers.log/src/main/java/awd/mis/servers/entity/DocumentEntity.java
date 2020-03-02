/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import awd.framework.common.utils.DateTimeUtils;
import awd.mis.servers.utils.CacheUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class DocumentEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Document";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_TITLE = "标题";
    public static final String ALIAS_THEME = "主题";
    public static final String ALIAS_ZSDW = "主送单位";
    public static final String ALIAS_CSDW = "抄送单位";
    public static final String ALIAS_CBDW = "呈报单位";
    public static final String ALIAS_QFR = "签发人";
    public static final String ALIAS_QFRQ = "签发日期";
    public static final String ALIAS_WJZH = "文件字号";
    public static final String ALIAS_BMJB = "保密级别(BMJB)";
    public static final String ALIAS_HJ = "缓急(HJCD)";
    public static final String ALIAS_NGR = "拟稿人";
    public static final String ALIAS_NGDW = "拟稿单位";
    public static final String ALIAS_UUID = "UUID";
    public static final String ALIAS_STATE = "删除状态";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";


    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    //columns START

    @Length(max = 50, message = "标题最大长度50位", groups = CreateGroup.class)
    @NotNull(message = "标题不能为空", groups = CreateGroup.class)
    private java.lang.String title;

    @Length(max = 500, message = "主题最大长度500位", groups = CreateGroup.class)
    @NotNull(message = "主题不能为空", groups = CreateGroup.class)
    private java.lang.String theme;

    @Length(max = 500, message = "主送单位最大长度500位", groups = CreateGroup.class)
    @NotNull(message = "主送单位不能为空", groups = CreateGroup.class)
    private java.lang.String zsdw;

    @Length(max = 500, message = "抄送单位最大长度500位", groups = CreateGroup.class)
    @NotNull(message = "抄送单位不能为空", groups = CreateGroup.class)
    private java.lang.String csdw;

    @Length(max = 500, message = "呈报单位最大长度500位", groups = CreateGroup.class)
    @NotNull(message = "呈报单位不能为空", groups = CreateGroup.class)
    private java.lang.String cbdw;

    @Length(max = 30, message = "签发人最大长度30位", groups = CreateGroup.class)
    @NotNull(message = "签发人不能为空", groups = CreateGroup.class)
    private java.lang.String qfr;


    @NotNull(message = "签发日期不能为空", groups = CreateGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date qfrq;

    @Length(max = 100, message = "文件字号最大长度100位", groups = CreateGroup.class)
    private java.lang.String wjzh;

    @Length(max = 1, message = "保密级别最大长度1位", groups = CreateGroup.class)
    @NotNull(message = "保密级别不能为空", groups = CreateGroup.class)
    private java.lang.String bmjb;

    @Length(max = 1, message = "缓急最大长度1位", groups = CreateGroup.class)
    @NotNull(message = "缓急不能为空", groups = CreateGroup.class)
    private java.lang.String hj;

    @Length(max = 30, message = "拟稿人最大长度30位", groups = CreateGroup.class)
    private java.lang.String ngr;

    @Length(max = 50, message = "拟稿单位最大长度50位", groups = CreateGroup.class)
    private java.lang.String ngdw;

    @Length(max = 50, message = "UUID", groups = CreateGroup.class)
    @NotNull(message = "UUID不能为空", groups = CreateGroup.class)
    private java.lang.String uuid;

    @Length(max = 2, message = "删除状态最大长度2位", groups = CreateGroup.class)
    @NotNull(message = "删除状态不能为空", groups = CreateGroup.class)
    private java.lang.String state;

    @Length(max = 30, message = "创建人最大长度30位", groups = CreateGroup.class)
    private java.lang.String creator;


    @NotNull(message = "创建时间不能为空", groups = CreateGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    @Length(max = 30, message = "更新人最大长度30位", groups = CreateGroup.class)
    private java.lang.String updator;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;

    //columns END
    private String hfbj;

    public String getHfbj() {
		return hfbj;
	}


	public void setHfbj(String hfbj) {
		this.hfbj = hfbj;
	}


	public DocumentEntity() {
    }


    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public java.lang.String getZsdw() {
        return this.zsdw;
    }

    public void setZsdw(java.lang.String value) {
        this.zsdw = value;
    }

    public java.lang.String getCsdw() {
        return this.csdw;
    }

    public void setCsdw(java.lang.String value) {
        this.csdw = value;
    }

    public java.lang.String getCbdw() {
        return this.cbdw;
    }

    public void setCbdw(java.lang.String value) {
        this.cbdw = value;
    }

    public java.lang.String getQfr() {
        return this.qfr;
    }

    public void setQfr(java.lang.String value) {
        this.qfr = value;
    }

    public java.util.Date getQfrq() {
        return this.qfrq;
    }

    public void setQfrq(java.util.Date value) {
        this.qfrq = value;
    }

    public java.lang.String getWjzh() {
        return this.wjzh;
    }

    public void setWjzh(java.lang.String value) {
        this.wjzh = value;
    }

    public java.lang.String getHj() {
        return this.hj;
    }

    @ApiModelProperty(hidden = true)
    public java.lang.String getHjString() {
        return CacheUtils.get().getDictionary("HJCD", this.hj);
    }

    public void setHj(java.lang.String value) {
        this.hj = value;
    }

    public java.lang.String getNgr() {
        return this.ngr;
    }

    public void setNgr(java.lang.String value) {
        this.ngr = value;
    }

    public java.lang.String getNgdw() {
        return this.ngdw;
    }

    public void setNgdw(java.lang.String value) {
        this.ngdw = value;
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

    public String getBmjb() {
        return this.bmjb;
    }

    @ApiModelProperty(hidden = true)
    public String getBmjbString() {
        return CacheUtils.get().getDictionary("BMJB", this.bmjb);
    }


    public void setBmjb(String bmjb) {
        this.bmjb = bmjb;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

