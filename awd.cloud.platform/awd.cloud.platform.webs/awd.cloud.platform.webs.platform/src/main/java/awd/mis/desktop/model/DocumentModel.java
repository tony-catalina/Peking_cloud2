/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

import awd.mis.desktop.tools.CacheUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class DocumentModel implements Model {

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

    private static final long serialVersionUID = 4363594596922323144L;


    private String id;
    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "主题不能为空")
    private String theme;

    @NotNull(message = "主送单位不能为空")
    private String zsdw;

    @NotNull(message = "主送单位不能为空")
    private String csdw;

    @NotNull(message = "呈报单位不能为空")
    private String cbdw;
    private String cbdwString;

    @NotNull(message = "签发人不能为空")
    private String qfr;


    @NotNull(message = "签发日期不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date qfrq;

    @NotNull(message = "文件字号不能为空")
    private String wjzh;

    @NotNull(message = "保密级别不能为空")
    private String bmjb;
    private String bmjbString;

    @NotNull(message = "缓急不能为空")
    private String hj;

    private String hjString;

    @NotNull(message = "拟稿人不能为空")
    private String ngr;

    @NotNull(message = "拟稿单位不能为空")
    private String ngdw;

    @NotNull(message = "UUID不能为空")
    private String uuid;

    private String state;

    private String creator;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    private String updator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;

    //columns END
    //是否回复
    private String hfbj;


    public String getHfbj() {
		return hfbj;
	}

	public void setHfbj(String hfbj) {
		this.hfbj = hfbj;
	}

	public DocumentModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getZsdw() {
        return this.zsdw;
    }

    public void setZsdw(String value) {
        this.zsdw = value;
    }

    public String getCsdw() {
        return this.csdw;
    }

    public void setCsdw(String value) {
        this.csdw = value;
    }

    public String getCbdw() {
        return this.cbdw;
    }

    public void setCbdw(String value) {
        this.cbdw = value;
    }

    public String getQfr() {
        return this.qfr;
    }

    public void setQfr(String value) {
        this.qfr = value;
    }

    public java.util.Date getQfrq() {
        return this.qfrq;
    }

    public void setQfrq(java.util.Date value) {
        this.qfrq = value;
    }

    public String getWjzh() {
        return this.wjzh;
    }

    public void setWjzh(String value) {
        this.wjzh = value;
    }

    public String getHj() {
        return this.hj;
    }

    public void setHj(String value) {
        this.hj = value;
    }

    public String getNgr() {
        return this.ngr;
    }

    public void setNgr(String value) {
        this.ngr = value;
    }

    public String getNgdw() {
        return this.ngdw;
    }

    public void setNgdw(String value) {
        this.ngdw = value;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String value) {
        this.creator = value;
    }

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }

    public String getUpdator() {
        return this.updator;
    }

    public void setUpdator(String value) {
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

    public void setBmjb(String bmjb) {
        this.bmjb = bmjb;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBmjbString() {
        return CacheUtils.get().getDictionary("BMJB", this.bmjb);

    }

    public String getHjString() {
        return CacheUtils.get().getDictionary("HJCD", this.hj);

    }

    public String getCbdwString() {
        String cbdw = CacheUtils.get().getJsbhString(this.cbdw).toString();
        JSONObject object = JSONObject.parseObject(cbdw);
        if (object != null) {
            return object.getString("kssmc");
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "DocumentModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", theme='" + theme + '\'' +
                ", zsdw='" + zsdw + '\'' +
                ", csdw='" + csdw + '\'' +
                ", cbdw='" + cbdw + '\'' +
                ", qfr='" + qfr + '\'' +
                ", qfrq=" + qfrq +
                ", wjzh='" + wjzh + '\'' +
                ", bmjb='" + bmjb + '\'' +
                ", bmjbString='" + bmjbString + '\'' +
                ", hj='" + hj + '\'' +
                ", hjString='" + hjString + '\'' +
                ", ngr='" + ngr + '\'' +
                ", ngdw='" + ngdw + '\'' +
                ", uuid='" + uuid + '\'' +
                ", state='" + state + '\'' +
                ", creator='" + creator + '\'' +
                ", createtime=" + createtime +
                ", updator='" + updator + '\'' +
                ", updatetime=" + updatetime +
                '}';
    }
}

