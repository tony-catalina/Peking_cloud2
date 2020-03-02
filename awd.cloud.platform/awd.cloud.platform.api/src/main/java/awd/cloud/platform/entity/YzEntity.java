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
import javax.validation.constraints.NotNull;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class YzEntity extends SimpleGenericEntity<String> implements JbxxInfo{

    //alias
    public static final String TABLE_ALIAS = "医嘱";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_RYBH = "人员编号";
    public static final String ALIAS_YSZT = "医生嘱托";
    public static final String ALIAS_BRBQ = "病人病情";
    public static final String ALIAS_ZDJG = "诊断结果";
    public static final String ALIAS_YSXM = "医生姓名";
    public static final String ALIAS_LY = "来源";
    public static final String ALIAS_SFYX = "是否有效(SHFO)";
    public static final String ALIAS_STATE = "删除状态(STATE)";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";
    public static final String ALIAS_YZSJ = "医嘱时间";


    //所有组
    @GroupSequence({CreateGroup.class, UpdateGroup.class})
    public interface All {
    }

    //columns START


    @Length(max = 9, message = "监所编号最大长度9位", groups = CreateGroup.class)
    private String jsbh;


    @Length(max = 21, message = "人员编号最大长度21位", groups = CreateGroup.class)
    private String rybh;


    @Length(max = 2000, message = "医生嘱托最大长度2000位", groups = CreateGroup.class)
    private String yszt;


    @Length(max = 2000, message = "病人病情最大长度2000位", groups = CreateGroup.class)
    private String brbq;


    @Length(max = 2000, message = "诊断结果最大长度2000位", groups = CreateGroup.class)
    private String zdjg;


    @Length(max = 50, message = "医生姓名最大长度50位", groups = CreateGroup.class)
    private String ysxm;


    @Length(max = 200, message = "来源最大长度200位", groups = CreateGroup.class)
    private String ly;


    @Length(max = 2, message = "是否有效(SHFO)最大长度2位", groups = CreateGroup.class)
    private String sfyx;


    @Length(max = 2, message = "删除状态(STATE)最大长度2位", groups = CreateGroup.class)
    private String state;


    @NotNull(message = "创建时间不能为空", groups = CreateGroup.class)
//    @Length(max = 11, message = "创建时间最大长度11位", groups = CreateGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;


    @NotNull(message = "创建人不能为空", groups = CreateGroup.class)
    @Length(max = 30, message = "创建人最大长度30位", groups = CreateGroup.class)
    private String creator;


    @Length(max = 30, message = "更新人最大长度30位", groups = CreateGroup.class)
    private String updator;


//    @Length(max = 11, message = "更新时间最大长度11位", groups = CreateGroup.class)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;

    //医嘱时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date yzsj;
    //columns END


    public YzEntity() {
    }

    public YzEntity(
            String id
    ) {
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

    public String getRybh() {
        return this.rybh;
    }

    public void setRybh(String value) {
        this.rybh = value;
    }

    public String getYszt() {
        return this.yszt;
    }

    public void setYszt(String value) {
        this.yszt = value;
    }

    public String getBrbq() {
        return this.brbq;
    }

    public void setBrbq(String value) {
        this.brbq = value;
    }

    public String getZdjg() {
        return this.zdjg;
    }

    public void setZdjg(String value) {
        this.zdjg = value;
    }

    public String getYsxm() {
        return this.ysxm;
    }

    public void setYsxm(String value) {
        this.ysxm = value;
    }

    public String getLy() {
        return this.ly;
    }

    public void setLy(String value) {
        this.ly = value;
    }

    public String getSfyx() {
        return this.sfyx;
    }

    public void setSfyx(String value) {
        this.sfyx = value;
    }

    public String getSfyxString() {
        return CacheUtils.get().getDictionary("SHFO", this.getSfyx());
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

    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(java.util.Date value) {
        this.createtime = value;
    }


    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String value) {
        this.creator = value;
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

    public java.util.Date getYzsj() {
        return this.yzsj;
    }

    public void setYzsj(java.util.Date value) {
        this.yzsj = value;
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
		// if(CacheUtils.get().getJbxx(this.rybh)==null) {
		// 	return "";
		// }
		// return CacheUtils.get().getJbxx(this.rybh).getString("rsrq")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("rsrq");
        return null;
	}

	@Override
	public String getJbxxCsrq() {
		// if(CacheUtils.get().getJbxx(this.rybh)==null) {
		// 	return "";
		// }
		// return CacheUtils.get().getJbxx(this.rybh).getString("csrq")==null?"":CacheUtils.get().getJbxx(this.rybh).getString("csrq");
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

