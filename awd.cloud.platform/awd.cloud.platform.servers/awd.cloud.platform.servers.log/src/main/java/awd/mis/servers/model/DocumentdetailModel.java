/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.mis.servers.entity.DocumentEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public class DocumentdetailModel implements Model {

    //alias
    public static final String TABLE_ALIAS = "Documentdetail";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_HFSJ = "回复时间";
    public static final String ALIAS_HFNR = "回复内容";
    public static final String ALIAS_HFR = "回复人";
    public static final String ALIAS_HFRJH = "回复人警号";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_FLAG = "是否已回复(SHFO)";

    private java.lang.String id;
    private java.lang.String jsbh;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date hfsj;

    private java.lang.String hfnr;

    private java.lang.String hfr;

    private java.lang.String hfrjh;

    private java.lang.String creator;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    private java.lang.String flag;

    private java.lang.String flagString;

    private java.lang.String uuid;

    private java.lang.String updator;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private DocumentEntity documentEntity;

    public DocumentdetailModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public Date getHfsj() {
        return hfsj;
    }

    public void setHfsj(Date hfsj) {
        this.hfsj = hfsj;
    }

    public String getHfnr() {
        return hfnr;
    }

    public void setHfnr(String hfnr) {
        this.hfnr = hfnr;
    }

    public String getHfr() {
        return hfr;
    }

    public void setHfr(String hfr) {
        this.hfr = hfr;
    }

    public String getHfrjh() {
        return hfrjh;
    }

    public void setHfrjh(String hfrjh) {
        this.hfrjh = hfrjh;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagString() {
        return flagString;
    }

    public void setFlagString(String flagString) {
        this.flagString = flagString;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public DocumentEntity getDocumentEntity() {
        return documentEntity;
    }

    public void setDocumentEntity(DocumentEntity documentEntity) {
        this.documentEntity = documentEntity;
    }
}

