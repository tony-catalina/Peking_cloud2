package awd.mis.servers.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.expands.redisclient.RedisUtils;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowmapEntity extends SimpleGenericEntity<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2343648664280801187L;
	//alias
	public static final String TABLE_ALIAS = "流程图信息";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_MAPNAME = "流程图名称";
	public static final String ALIAS_VERSION = "版本号";
	public static final String ALIAS_MAPKEY = "流程定义key";
	public static final String ALIAS_MAPMUTEX = "流程图互斥";
	public static final String ALIAS_TIMELIMIT = "时间限制(每隔多少时间办理一次)";
	public static final String ALIAS_MONTHLYLIMIT = "月限制(一月可以办理几起)";
	public static final String ALIAS_MEMO = "描述";
	public static final String ALIAS_FLAG = "禁用标识";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	
	//date formats
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="监所编号不能为空",groups=SaveGroup.class)
    @Length(max=9,message="监所编号最大长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;
	
	@NotNull(message="流程图名称不能为空",groups=SaveGroup.class)
    @Length(max=50,message="流程图名称最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String mapname;
	
	@NotNull(message="流程定义KEY",groups=SaveGroup.class)
    @Length(max=10,message="流程定义KEY最大长度10位" ,groups=SaveGroup.class)
	private java.lang.String mapkey;
	
	@NotNull(message="版本号",groups=SaveGroup.class)
	@Max(value=20)
	@Min(value=1)
	private java.lang.Integer version;
	
    @Length(max=200,message="流程图互斥最大长度200位" ,groups=SaveGroup.class)
	private java.lang.String mapmutex;
    
    @Length(max=50,message="时间限制(每隔多少时间办理一次)最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String timelimit;
    
    @Length(max=2,message="月限制(一月可以办理几起)最大长度2位" ,groups=SaveGroup.class)
	private java.lang.Integer monthlylimit;
    
    @Length(max=500,message="描述最大长度500位" ,groups=SaveGroup.class)
	private java.lang.String memo;
    
    @Length(max=1,message="禁用标识最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String flag;
    
	@NotNull(message="创建人不能为空",groups=SaveGroup.class)
    @Length(max=50,message="创建人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String creator;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	@Length(max=50,message="更新人最大长度50位" ,groups=SaveGroup.class)
	private java.lang.String updator;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;
	public static final String FORMAT_TIMESTAMP = TIMESTAMP_FORMAT;

	//columns END


	public FlowmapEntity(){
	}

	public FlowmapEntity(
		java.lang.String id
	){
		super.setId(id);
	}

	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getMapname() {
		return this.mapname;
	}
	
	public void setMapname(java.lang.String value) {
		this.mapname = value;
	}
	
	public java.lang.String getMapmutex() {
		return this.mapmutex;
	}
	
	public void setMapmutex(java.lang.String value) {
		this.mapmutex = value;
	}
	
	public java.lang.String getTimelimit() {
		return this.timelimit;
	}
	
	public void setTimelimit(java.lang.String value) {
		this.timelimit = value;
	}
	
	public java.lang.Integer getMonthlylimit() {
		return this.monthlylimit;
	}
	
	public void setMonthlylimit(java.lang.Integer value) {
		this.monthlylimit = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
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
	 

	public java.lang.Integer getVersion() {
		return version;
	}

	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	public java.lang.String getMapkey() {
		return mapkey;
	}

	public void setMapkey(java.lang.String mapkey) {
		this.mapkey = mapkey;
	}

	
	
}

