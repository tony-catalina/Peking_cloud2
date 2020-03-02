/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import awd.mis.servers.tools.CacheUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class ZfingerEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Zfinger";
    public static final String ALIAS_ID = "编号";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_RYBH = "人员编号";
    public static final String ALIAS_SZBH = "手指编号（RYSZBH）";
    public static final String ALIAS_ZWTX = "指纹图像";
    public static final String ALIAS_ZWTZ = "指纹特征";
    public static final String ALIAS_ZWTXURL = "指纹图像地址";
    public static final String ALIAS_ZWTZURL = "指纹特征地址";
    public static final String ALIAS_STATE = "状态  R3删除  R2正常";
    public static final String ALIAS_SCBZ = "上传标志";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";

    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    @NotNull(message = "监所编号不能为空", groups = CreateGroup.class)
    @Length(max = 9, message = "监所编号最大长度9位", groups = CreateGroup.class)
    private java.lang.String jsbh;

    @NotNull(message = "人员编号不能为空", groups = CreateGroup.class)
    @Length(max = 21, message = "人员编号最大长度21位", groups = CreateGroup.class)
    private java.lang.String rybh;

    @Length(max = 1, message = "手指编号（RYSZBH）最大长度1位", groups = CreateGroup.class)
    private java.lang.String szbh;

    //		@Length(max=2147483647,message="指纹图像最大长度2147483647位" ,groups=CreateGroup.class)
    private byte[] zwtx;

    //		@Length(max=2147483647,message="指纹特征最大长度2147483647位" ,groups=CreateGroup.class)
    private byte[] zwtz;

    @Length(max = 255, message = "指纹图像地址最大长度255位", groups = CreateGroup.class)
    private java.lang.String zwtxurl;

    @Length(max = 255, message = "指纹特征地址最大长度255位", groups = CreateGroup.class)
    private java.lang.String zwtzurl;

    @Length(max = 2, message = "状态  R3删除  R2正常最大长度2位", groups = CreateGroup.class)
    private java.lang.String state;

    @Length(max = 1, message = "上传标志最大长度1位", groups = CreateGroup.class)
    private java.lang.String scbz;

    @NotNull(message = "创建人不能为空", groups = CreateGroup.class)
    @Length(max = 50, message = "创建人最大长度50位", groups = CreateGroup.class)
    private java.lang.String creator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    @Length(max = 50, message = "更新人最大长度50位", groups = CreateGroup.class)
    private java.lang.String updator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;

    //columns END


    public ZfingerEntity() {
    }

	/*public ZfingerEntity(
		java.lang.String id
	){
		this.id = id;
	}*/



	/*public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}*/


    public java.lang.String getJsbh() {
        return this.jsbh;
    }

    public void setJsbh(java.lang.String value) {
        this.jsbh = value;
    }

    public java.lang.String getRybh() {
        return this.rybh;
    }

    public void setRybh(java.lang.String value) {
        this.rybh = value;
    }

    public java.lang.String getSzbh() {
        return this.szbh;
    }

    public void setSzbh(java.lang.String value) {
        this.szbh = value;
    }

    public byte[] getZwtx() {
        return this.zwtx;
    }

    public void setZwtx(byte[] value) {
        this.zwtx = value;
    }

    public byte[] getZwtz() {
        return this.zwtz;
    }

    public void setZwtz(byte[] value) {
        this.zwtz = value;
    }

    public java.lang.String getZwtxurl() {
        return this.zwtxurl;
    }

    public void setZwtxurl(java.lang.String value) {
        this.zwtxurl = value;
    }

    public java.lang.String getZwtzurl() {
        return this.zwtzurl;
    }

    public void setZwtzurl(java.lang.String value) {
        this.zwtzurl = value;
    }

    public java.lang.String getState() {
        return this.state;
    }

    public void setState(java.lang.String value) {
        this.state = value;
    }

    public java.lang.String getScbz() {
        return this.scbz;
    }

    public void setScbz(java.lang.String value) {
        this.scbz = value;
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

    //开始生成字典的getString方法
    public String getJsbhString() {
        return CacheUtils.get().getJsbhString(this.jsbh);
    }
    //字典的get方法生成结束
}

