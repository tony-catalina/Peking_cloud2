/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import awd.framework.common.utils.StringUtils;
import awd.mis.servers.utils.CacheUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;

import java.io.UnsupportedEncodingException;
import java.util.Base64;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgboxEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "消息表";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_FSRJH = "发送人警号";
    public static final String ALIAS_XXJB = "消息级别";
    public static final String ALIAS_FSSJ = "发送时间";
    public static final String ALIAS_FSNR = "消息内容";
    public static final String ALIAS_JSRJH = "接收人警号";
    public static final String ALIAS_STATE = "删除状态";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_UPDATETIME = "更新时间";
    public static final String ALIAS_UPDATOR = "更新人";

    //date formats
    public static final String FORMAT_FSSJ = TIMESTAMP_FORMAT;

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

    @NotNull(message = "监所编号不能为空", groups = SaveGroup.class)
    @Length(max = 9, message = "监所编号最大长度9位", groups = SaveGroup.class)
    private java.lang.String jsbh;
    @Length(max = 20, message = "发送人警号最大长度20位", groups = SaveGroup.class)
    private java.lang.String fsrjh;
    @Length(max = 2, message = "消息级别最大长度2位", groups = SaveGroup.class)
    private java.lang.String xxjb;

    @NotNull(message = "发送时间不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date fssj;

    @Length(max = 2000, message = "消息内容最大长度2000位", groups = SaveGroup.class)
    private java.lang.String fsnr;
    @Length(max = 20, message = "接收人警号最大长度20位", groups = SaveGroup.class)
    private java.lang.String jsrjh;
    private java.lang.String fsrxm;
    private java.lang.String msgid;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date jssj;
    @Length(max = 2, message = "删除状态最大长度2位", groups = SaveGroup.class)
    private java.lang.String state;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;
    @Length(max = 50, message = "创建人最大长度50位", groups = SaveGroup.class)
    private java.lang.String creator;

    private String jsrxm;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;
    @Length(max = 50, message = "更新人最大长度50位", groups = SaveGroup.class)
    private java.lang.String updator;
    //columns END


    public MsgboxEntity() {
    }

    public MsgboxEntity(
            java.lang.String id
    ) {
        super.setId(id);
    }

    public java.lang.String getJsbh() {
        return this.jsbh;
    }

    public void setJsbh(java.lang.String value) {
        this.jsbh = value;
    }

    public java.lang.String getFsrjh() {
        return this.fsrjh;
    }

    public void setFsrjh(java.lang.String value) {
        this.fsrjh = value;
    }

    public java.lang.String getXxjb() {
        return this.xxjb;
    }

    public java.lang.String getXxjbString() {
        return CacheUtils.get().getDictionary("XXJB", this.xxjb);
    }

    public void setXxjb(java.lang.String value) {
        this.xxjb = value;
    }

    public String getFssjString() {
        return date2String(getFssj(), FORMAT_FSSJ);
    }

    public java.util.Date getFssj() {
        return this.fssj;
    }

    public void setFssj(java.util.Date value) {
        this.fssj = value;
    }

    public java.lang.String getFsnr() {
        return this.fsnr;
    }

    public java.lang.String getFsnrString() {
        if(StringUtils.isNullOrEmpty(this.fsnr)){
            return "";
        }
        String fsnrString = "";
        try {
            fsnrString = new String(Base64.getDecoder().decode(this.fsnr), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            return fsnrString;
        }

    }

    public void setFsnr(java.lang.String value) {
        this.fsnr = value;
    }

    public java.lang.String getJsrjh() {
        return this.jsrjh;
    }

    public void setJsrjh(java.lang.String value) {
        this.jsrjh = value;
    }

    public java.lang.String getState() {
        return this.state;
    }

    public void setState(java.lang.String value) {
        this.state = value;
    }

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }

    public java.lang.String getCreator() {
        return this.creator;
    }

    public void setCreator(java.lang.String value) {
        this.creator = value;
    }

    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(java.util.Date value) {
        this.updatetime = value;
    }

    public java.lang.String getUpdator() {
        return this.updator;
    }

    public void setUpdator(java.lang.String value) {
        this.updator = value;
    }

    public java.util.Date getJssj() {
        return jssj;
    }

    public void setJssj(java.util.Date jssj) {
        this.jssj = jssj;
    }

    public String getFsrxm() {
        return fsrxm;
    }

    public void setFsrxm(String fsrxm) {
        this.fsrxm = fsrxm;
    }

    public String getJsrxm() {
        return jsrxm;
    }

    public void setJsrxm(String jsrxm) {
        this.jsrxm = jsrxm;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}

