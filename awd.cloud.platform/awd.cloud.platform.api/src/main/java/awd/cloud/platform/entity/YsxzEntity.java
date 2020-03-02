/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.entity;

import awd.cloud.platform.utils.CacheUtils;
import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class YsxzEntity extends SimpleGenericEntity<String> implements JbxxInfo{
    //alias
    public static final String TABLE_ALIAS = "医生巡诊";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_RYBH = "人员编号";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_XY = "血压";
    public static final String ALIAS_XL = "心率";
    public static final String ALIAS_JBMC = "疾病名称(JBMC)";
    public static final String ALIAS_ZZ = "诊治情况";
    public static final String ALIAS_XZRQ = "巡诊日期";
    public static final String ALIAS_SFKYZ = "是否开医嘱(SHFO)";
    public static final String ALIAS_STATE = "删除状态(STATE)";
    public static final String ALIAS_BZ = "备注";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间 ";


    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    //columns START



    @Length(max = 21, message = "人员编号最大长度21位", groups = CreateGroup.class)
    private String rybh;


    @Length(max = 9, message = "监所编号最大长度9位", groups = CreateGroup.class)
    private String jsbh;


    @Length(max = 50, message = "疾病类型最大长度50位", groups = CreateGroup.class)
    private String jblx;


    @Length(max = 50, message = "血压最大长度50位", groups = CreateGroup.class)
    private String xy;


    //@Length(max=0,message="心率最大长度0位" ,groups=CreateGroup.class)
    private Integer xl;

    private String jbmc;

    @Length(max = 100, message = "诊治情况最大长度100位", groups = CreateGroup.class)
    private String zz;


    //巡诊日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date xzrq;

    @Length(max = 2, message = "是否开医嘱(SHFO)最大长度2位", groups = CreateGroup.class)
    private String sfkyz;

    @Length(max = 2, message = "删除状态(STATE)最大长度2位", groups = CreateGroup.class)
    private String state;


    @Length(max = 300, message = "备注最大长度300位", groups = CreateGroup.class)
    private String bz;


    @Length(max = 50, message = "创建人最大长度50位", groups = CreateGroup.class)
    private String creator;


    // @Length(max=11,message="创建时间最大长度11位" ,groups=CreateGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;


    @Length(max = 50, message = "更新人最大长度50位", groups = CreateGroup.class)
    private String updator;


    //@Length(max=11,message="更新时间 最大长度11位" ,groups=CreateGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;

    //columns END


    public YsxzEntity() {
    }

    public YsxzEntity(
            String id
    ) {
    	  super.setId(id);
    }



    public String getRybh() {
        return this.rybh;
    }

    public void setRybh(String value) {
        this.rybh = value;
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

    public String getJblx() {
        return this.jblx;
    }

    public void setJblx(String value) {
        this.jblx = value;
    }

    public String getXy() {
        return this.xy;
    }

    public void setXy(String value) {
        this.xy = value;
    }

    public Integer getXl() {
        return this.xl;
    }

    public void setXl(Integer value) {
        this.xl = value;
    }

    public String getJbmc() {
        return jbmc;
    }

    public void setJbmc(String jbmc) {
        this.jbmc = jbmc;
    }

    public String getJbmcString() {
        return CacheUtils.get().getDictionary("JBMC", this.getJbmc());
    }

    public String getZz() {
        return this.zz;
    }

    public void setZz(String value) {
        this.zz = value;
    }



    public java.util.Date getXzrq() {
        return this.xzrq;
    }

    public void setXzrq(java.util.Date value) {
        this.xzrq = value;
    }

    public String getSfkyz() {
        return sfkyz;
    }

    public void setSfkyz(String sfkyz) {
        this.sfkyz = sfkyz;
    }

    public String getSfkyzString() {
        return CacheUtils.get().getDictionary("SHFO", this.getSfkyz());
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

    public String getBz() {
        return this.bz;
    }

    public void setBz(String value) {
        this.bz = value;
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
    
    @Override
	public String getJbxxSnbh() {
		// if(CacheUtils.get().getJbxx(this.rybh)==null) {
		// 	return "";
		// }
		// return CacheUtils.get().getJbxx(this.rybh).getString("snbh")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("snbh");
        return null;
	}

	@Override
	public String getJbxxXm() {
		// if(CacheUtils.get().getJbxx(this.rybh)==null) {
		// 	return "";
		// }
		// return CacheUtils.get().getJbxx(this.rybh).getString("xm")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("xm");
        return null;
	}

	@Override
	public String getJbxxJsh() {
		// if(CacheUtils.get().getJbxx(this.rybh)==null) {
		// 	return "";
		// }
		// return CacheUtils.get().getJbxx(this.rybh).getString("jsh")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("jsh");
        return null;
	}

	@Override
	  public String getJbxxRsrq() {
	// 	if(CacheUtils.get().getJbxx(this.rybh)==null) {
	// 		return "";
	// 	}
	// 	return CacheUtils.get().getJbxx(this.rybh).getString("rsrq")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("rsrq");
    return null;
	}

	@Override
	 public String getJbxxCsrq() {
	// 	if(CacheUtils.get().getJbxx(this.rybh)==null) {
	// 		return "";
	// 	}
	// 	return CacheUtils.get().getJbxx(this.rybh).getString("csrq")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("csrq");
        return null;
	}

	@Override
	public String getJbxxHjd() {
		// if(CacheUtils.get().getJbxx(this.rybh)==null) {
		// 	return "";
		// }
		// return CacheUtils.get().getJbxx(this.rybh).getString("hjd")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("hjd");
        return null;
	}


}

