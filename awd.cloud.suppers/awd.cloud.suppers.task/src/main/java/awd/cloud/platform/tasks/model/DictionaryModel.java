/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.tasks.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.cloud.platform.tasks.utils.Model;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryModel implements Model {

    //alias
    public static final String TABLE_ALIAS = "字典信息";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_FIELDNAME = "分类名";
    public static final String ALIAS_CODE = "代码";
    public static final String ALIAS_CONTENT = "内容";
    public static final String ALIAS_PY = "拼音";
    public static final String ALIAS_ISGB = "是否是国标";
    public static final String ALIAS_SYPL = "使用频率";
    public static final String ALIAS_EDITABLE = "是否可编辑";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";
    public static final String ALIAS_JSLX = " 监所类型";

    //date formats


    //columns START
    private String id;
    private String fieldname;
    private String code;
    private String content;
    private String py;
    private String isgb;
    private java.math.BigDecimal sypl;
    private String editable;
    private String creator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date createtime;
    private String updator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date updatetime;
    private String jslx;
    //columns END


    private String fieldnameString;
    private String isgbString;
    private String editableString;
    private String createtimeString;
    private String updatetimeString;
    private String jslxString;

    public DictionaryModel() {
    }

    public DictionaryModel(
            String id
    ) {
        this.id = id;
    }


    public void setId(String value) {
        this.id = value;
    }

    public String getId() {
        return this.id;
    }

    public String getFieldname() {
        return this.fieldname;
    }

    public void setFieldname(String value) {
        this.fieldname = value;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String value) {
        this.content = value;
    }

    public String getPy() {
        return this.py;
    }

    public void setPy(String value) {
        this.py = value;
    }

    public String getIsgb() {
        return this.isgb;
    }

    public void setIsgb(String value) {
        this.isgb = value;
    }

    public java.math.BigDecimal getSypl() {
        return this.sypl;
    }

    public void setSypl(java.math.BigDecimal value) {
        this.sypl = value;
    }

    public String getEditable() {
        return this.editable;
    }

    public void setEditable(String value) {
        this.editable = value;
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

    public String getJslx() {
        return this.jslx;
    }

    public void setJslx(String value) {
        this.jslx = value;
    }

    public String getFieldnameString() {
        return fieldnameString;
    }

    public void setFieldnameString(String fieldnameString) {
        this.fieldnameString = fieldnameString;
    }

    public String getIsgbString() {
        return isgbString;
    }

    public void setIsgbString(String isgbString) {
        this.isgbString = isgbString;
    }

    public String getEditableString() {
        return editableString;
    }

    public void setEditableString(String editableString) {
        this.editableString = editableString;
    }

    public String getCreatetimeString() {
        return createtimeString;
    }

    public void setCreatetimeString(String createtimeString) {
        this.createtimeString = createtimeString;
    }

    public String getUpdatetimeString() {
        return updatetimeString;
    }

    public void setUpdatetimeString(String updatetimeString) {
        this.updatetimeString = updatetimeString;
    }

    public String getJslxString() {
        return jslxString;
    }

    public void setJslxString(String jslxString) {
        this.jslxString = jslxString;
    }


}

