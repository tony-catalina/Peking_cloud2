/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.DateTimeUtils;
import awd.mis.servers.tools.CacheUtils;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplunboEntity extends SimpleGenericEntity<String> {

    //alias
    public static final String TABLE_ALIAS = "Applunbo";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_SORT = "排序";
    public static final String ALIAS_APPCODE = "应用编号";
    public static final String ALIAS_STATE = "删除状态";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";

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

    @NotNull(message = "排序不能为空", groups = SaveGroup.class)
    @Length(max = 2, message = "排序最大长度2位", groups = SaveGroup.class)
    private String sort;

    @NotNull(message = "应用编号不能为空", groups = SaveGroup.class)
    @Length(max = 65535, message = "应用编号最大长度65535位", groups = SaveGroup.class)
    private String appcode;

    @NotNull(message = "删除状态不能为空", groups = SaveGroup.class)
    @Length(max = 2, message = "删除状态最大长度2位", groups = SaveGroup.class)
    private String state;

    @NotNull(message = "创建人不能为空", groups = SaveGroup.class)
    @Length(max = 50, message = "创建人最大长度50位", groups = SaveGroup.class)
    private String creator;

    private String photoURL;

    @NotNull(message = "创建时间不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;
    
    //app内字段
    private java.lang.String icon;
    private java.lang.String name;

    //columns END


    public java.lang.String getIcon() {
		return icon;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public ApplunboEntity() {
    }

    public ApplunboEntity(
            String id
    ) {
        super.setId(id);
    }



    public String getSort() {
        return this.sort;
    }

    public void setSort(String value) {
        this.sort = value;
    }

    public String getAppcode() {
        return this.appcode;
    }

    public void setAppcode(String value) {
        this.appcode = value;
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

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}

