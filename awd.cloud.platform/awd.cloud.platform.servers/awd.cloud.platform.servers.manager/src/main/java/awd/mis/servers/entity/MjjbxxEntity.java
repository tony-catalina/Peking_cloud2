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
@SuppressWarnings("serial")
public class MjjbxxEntity extends SimpleGenericEntity<String> {

    // alias
    public static final String TABLE_ALIAS = "民警信息";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_JH = "警号";
    public static final String ALIAS_SFZH = "身份证号";
    public static final String ALIAS_XM = "姓名";
    public static final String ALIAS_XB = "性别";
    public static final String ALIAS_MZ = "民族";
    public static final String ALIAS_CSNY = "出生年月";
    public static final String ALIAS_JX = "警衔";
    public static final String ALIAS_XL = "学历";
    public static final String ALIAS_ZZMM = "政治面貌";
    public static final String ALIAS_CJGMGZSJ = "参加革命时间";
    public static final String ALIAS_HYZK = "婚姻状况";
    public static final String ALIAS_JTZZ = "家庭住址";
    public static final String ALIAS_HJSZD = "户籍所在地";
    public static final String ALIAS_CJGZSJ = "参加工作时间";
    public static final String ALIAS_BM = "部门";
    public static final String ALIAS_BMZW = "职位";
    public static final String ALIAS_GBZWJB = "职位级别";
    public static final String ALIAS_LXDH = "联系电话";
    public static final String ALIAS_XLZXS = "是否有心理咨询师资格";
    public static final String ALIAS_SJH = "手机号";
    public static final String ALIAS_LKRQ = "调动日期";
    public static final String ALIAS_LKYY = "调动原因";
    public static final String ALIAS_BZ = "备注";
    public static final String ALIAS_STATE = "状态";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";
    public static final String ALIAS_ZPURL = "照片Url";

    // 所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    // columns START

    @Length(max = 9, message = "监所编号最大长度9位", groups = CreateGroup.class)
    private String jsbh;

    @Length(max = 20, message = "警号最大长度20位", groups = CreateGroup.class)
    private String jh;

    @Length(max = 18, message = "身份证号最大长度18位", groups = CreateGroup.class)
    private String sfzh;

    @Length(max = 50, message = "姓名最大长度50位", groups = CreateGroup.class)
    private String xm;

    @Length(max = 150, message = "姓名最大长度150位", groups = CreateGroup.class)
    private String xmpy;

    @Length(max = 2, message = "性别最大长度2位", groups = CreateGroup.class)
    private String xb;

    @Length(max = 2, message = "民族最大长度2位", groups = CreateGroup.class)
    private String mz;

    @NotNull(message = "出生年月不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private java.util.Date csny;

    @Length(max = 40, message = "警衔最大长度2位", groups = CreateGroup.class)
    private String jx;

    @Length(max = 2, message = "学历最大长度2位", groups = CreateGroup.class)
    private String xl;

    @Length(max = 3, message = "政治面貌最大长度3位", groups = CreateGroup.class)
    private String zzmm;

    @NotNull(message = "参加革命时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date cjgmgzsj;

    @Length(max = 3, message = "婚姻状况最大长度3位", groups = CreateGroup.class)
    private String hyzk;

    @Length(max = 100, message = "家庭住址最大长度100位", groups = CreateGroup.class)
    private String jtzz;

    @Length(max = 6, message = "户籍所在地最大长度6位", groups = CreateGroup.class)
    private String hjszd;

    @NotNull(message = "参加工作时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date cjgzsj;

    @Length(max = 20, message = "部门最大长度20位", groups = CreateGroup.class)
    private String bm;

    @Length(max = 20, message = "职位最大长度20位", groups = CreateGroup.class)
    private String bmzw;

    @Length(max = 40, message = "职位级别最大长度40位", groups = CreateGroup.class)
    private String gbzwjb;

    @Length(max = 40, message = "联系电话最大长度40位", groups = CreateGroup.class)
    private String lxdh;

    @Length(max = 2, message = "是否有心理咨询师资格最大长度2位", groups = CreateGroup.class)
    private String xlzxs;
    @Length(max = 30, message = "手机号最大长度30位", groups = CreateGroup.class)
    private String sjh;

    @NotNull(message = "调动日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private java.util.Date lkrq;

    @Length(max = 2, message = "是否在职最大长度2位", groups = CreateGroup.class)
    private String flag;

    @Length(max = 300, message = "调动原因最大长度300位", groups = CreateGroup.class)
    private String lkyy;
    @Length(max = 500, message = "备注最大长度500位", groups = CreateGroup.class)
    private String bz;
    @Length(max = 2, message = "状态最大长度2位", groups = CreateGroup.class)
    private String state;
    @Length(max = 50, message = "创建人最大长度50位", groups = CreateGroup.class)
    private String creator;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;
    @Length(max = 50, message = "更新人最大长度50位", groups = CreateGroup.class)
    private String updator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;
    @Length(max = 255, message = "照片url最大长度255位", groups = CreateGroup.class)
    private String zpurl;
    // columns END
    
    private String xkid;

    public MjjbxxEntity() {
    }

    public MjjbxxEntity(String id) {
    	super.setId(id);
    }

    public String getJsbh() {
        return this.jsbh;
    }
    
    public String getJsbhString() {
    	return CacheUtils.get().getJsbhString(this.jsbh);
    }

    public void setJsbh(String value) {
        this.jsbh = value;
    }

    public String getJh() {
        return this.jh;
    }

    public void setJh(String value) {
        this.jh = value;
    }

    public String getSfzh() {
        return this.sfzh;
    }

    public void setSfzh(String value) {
        this.sfzh = value;
    }

    public String getXm() {
        return this.xm;
    }

    public void setXm(String value) {
        this.xm = value;
    }


    public String getXb() {
        return this.xb;
    }
    
    public String getXbString() {
		return CacheUtils.get().getDictionary("XB", this.getXb());
	}

    public void setXb(String value) {
        this.xb = value;
    }

    public String getMz() {
        return this.mz;
    }

    public String getMzString() {
        return CacheUtils.get().getDictionary("MZ", this.getMz());
    }

    public void setMz(String value) {
        this.mz = value;
    }


    public java.util.Date getCsny() {
        return this.csny;
    }

    public void setCsny(java.util.Date value) {
        this.csny = value;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagString() {
        return CacheUtils.get().getDictionary("SHFO", this.getFlag());
    }

    public String getJx() {
        return this.jx;
    }

    public String getJxString() {
        return CacheUtils.get().getDictionary("JX", this.getJx());
    }

    public void setJx(String value) {
        this.jx = value;
    }

    public String getXl() {
        return this.xl;
    }

    public void setXl(String value) {
        this.xl = value;
    }

    public String getXlString() {
        return CacheUtils.get().getDictionary("WHCD", this.getXl());
    }

    public String getZzmm() {
        return this.zzmm;
    }

    public void setZzmm(String value) {
        this.zzmm = value;
    }

    public String getZzmmString() {
        return CacheUtils.get().getDictionary("ZZMM", this.getZzmm());
    }


    public java.util.Date getCjgmgzsj() {
        return this.cjgmgzsj;
    }

    public void setCjgmgzsj(java.util.Date value) {
        this.cjgmgzsj = value;
    }

    public String getHyzk() {
        return this.hyzk;
    }

    public void setHyzk(String value) {
        this.hyzk = value;
    }

    public String getHyzkString() {
        return CacheUtils.get().getDictionary("HYZK", this.getHyzk());
    }

    public String getJtzz() {
        return this.jtzz;
    }

    public void setJtzz(String value) {
        this.jtzz = value;
    }

    public String getHjszd() {
        return this.hjszd;
    }

    public String getHjszdString() {
        return CacheUtils.get().getDictionary("XZQH", this.getHjszd());
    }

    public void setHjszd(String value) {
        this.hjszd = value;
    }


    public java.util.Date getCjgzsj() {
        return this.cjgzsj;
    }

    public void setCjgzsj(java.util.Date value) {
        this.cjgzsj = value;
    }

    public String getBm() {
        return this.bm;
    }

    public void setBm(String value) {
        this.bm = value;
    }

    public String getBmzw() {
        return this.bmzw;
    }

    public void setBmzw(String value) {
        this.bmzw = value;
    }

    public String getGbzwjb() {
        return this.gbzwjb;
    }

    public void setGbzwjb(String value) {
        this.gbzwjb = value;
    }

    public String getGbzwjbString() {
        return CacheUtils.get().getDictionary("ZWJB", this.getGbzwjb());
    }

    public String getLxdh() {
        return this.lxdh;
    }

    public void setLxdh(String value) {
        this.lxdh = value;
    }

    public String getXlzxs() {
        return this.xlzxs;
    }

    public String getXlzxsString() {
        return CacheUtils.get().getDictionary("SHFO", this.getXlzxs());
    }

    public void setXlzxs(String value) {
        this.xlzxs = value;
    }

    public String getSjh() {
        return this.sjh;
    }

    public void setSjh(String value) {
        this.sjh = value;
    }


    public java.util.Date getLkrq() {
        return this.lkrq;
    }

    public void setLkrq(java.util.Date value) {
        this.lkrq = value;
    }

    public String getLkyy() {
        return this.lkyy;
    }

    public void setLkyy(String value) {
        this.lkyy = value;
    }

    public String getBz() {
        return this.bz;
    }

    public void setBz(String value) {
        this.bz = value;
    }

    public String getState() {
        return this.state;
    }
    
    public String getStateString() {
        return CacheUtils.get().getDictionary("YWSTATE", this.state);
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


    public String getXmpy() {
        return xmpy;
    }

    public void setXmpy(String xmpy) {
        this.xmpy = xmpy;
    }

	public String getZpurl() {
		return zpurl;
	}

	public void setZpurl(String zpurl) {
		this.zpurl = zpurl;
	}

	public String getXkid() {
		return xkid;
	}

	public void setXkid(String xkid) {
		this.xkid = xkid;
	}

}
