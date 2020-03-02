/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.utils.DateTimeUtils;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BzwtxEntity extends SimpleGenericEntity<String> {
	
	//alias
	public static final String TABLE_ALIAS = "Bzwtx";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_ZWTXXH11 = "第一个手指第一枚指纹序号";
	public static final String ALIAS_ZWTX11 = "第一个手指第一枚指纹图像";
	public static final String ALIAS_ZWTXXH12 = "第一个手指第二枚指纹序号";
	public static final String ALIAS_ZWTX12 = "第一个手指第二枚指纹图像";
	public static final String ALIAS_ZWTXXH13 = "第一个手指第三枚指纹序号";
	public static final String ALIAS_ZWTX13 = "第一个手指第三枚指纹图像";
	public static final String ALIAS_ZWTXXH21 = "第二个手指第一枚指纹序号";
	public static final String ALIAS_ZWTX21 = "第二个手指第一枚指纹图像";
	public static final String ALIAS_ZWTXXH22 = "第二个手指第二枚指纹序号";
	public static final String ALIAS_ZWTX22 = "第二个手指第二枚指纹图像";
	public static final String ALIAS_ZWTXXH23 = "第二个手指第三枚指纹序号";
	public static final String ALIAS_ZWTX23 = "第二个手指第三枚指纹图像";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_OPERATOR = "创建人";
	public static final String ALIAS_UPDATEOPERATOR = "更新人";
	public static final String ALIAS_STATE = "状态  R3删除  R2正常";
	public static final String ALIAS_SCBZ = "上传标志";
	public static final String ALIAS_RYBH = "人员编号";
	
	//保存组（不需要id验证）
	public static interface SaveGroup {};  
	//新增组（需要id验证）
	public static interface UpdateGroup {};
	//所有组
	@GroupSequence({SaveGroup.class, UpdateGroup.class})  
	public interface All {       
	} 

	//columns START

	@NotNull(message="编号不能为空",groups=SaveGroup.class)
    @Length(max=23,message="编号最大长度23位" ,groups=SaveGroup.class)
	private java.lang.String id;

    @Length(max=9,message="监所编号最大长度9位" ,groups=SaveGroup.class)
	private java.lang.String jsbh;

    @Length(max=3,message="第一个手指第一枚指纹序号最大长度3位" ,groups=SaveGroup.class)
	private java.lang.String zwtxxh11;

    @Length(max=2147483647,message="第一个手指第一枚指纹图像最大长度2147483647位" ,groups=SaveGroup.class)
	private byte[] zwtx11;

    @Length(max=3,message="第一个手指第二枚指纹序号最大长度3位" ,groups=SaveGroup.class)
	private java.lang.String zwtxxh12;

    @Length(max=2147483647,message="第一个手指第二枚指纹图像最大长度2147483647位" ,groups=SaveGroup.class)
	private byte[] zwtx12;

    @Length(max=3,message="第一个手指第三枚指纹序号最大长度3位" ,groups=SaveGroup.class)
	private java.lang.String zwtxxh13;

    @Length(max=2147483647,message="第一个手指第三枚指纹图像最大长度2147483647位" ,groups=SaveGroup.class)
	private byte[] zwtx13;

    @Length(max=3,message="第二个手指第一枚指纹序号最大长度3位" ,groups=SaveGroup.class)
	private java.lang.String zwtxxh21;

    @Length(max=2147483647,message="第二个手指第一枚指纹图像最大长度2147483647位" ,groups=SaveGroup.class)
	private byte[] zwtx21;

    @Length(max=3,message="第二个手指第二枚指纹序号最大长度3位" ,groups=SaveGroup.class)
	private java.lang.String zwtxxh22;

    @Length(max=2147483647,message="第二个手指第二枚指纹图像最大长度2147483647位" ,groups=SaveGroup.class)
	private byte[] zwtx22;

    @Length(max=3,message="第二个手指第三枚指纹序号最大长度3位" ,groups=SaveGroup.class)
	private java.lang.String zwtxxh23;

    @Length(max=2147483647,message="第二个手指第三枚指纹图像最大长度2147483647位" ,groups=SaveGroup.class)
	private byte[] zwtx23;

	
	@NotNull(message="创建时间不能为空")
	private java.util.Date createtime;

	
	@NotNull(message="更新时间不能为空")
	private java.util.Date updatetime;

    @Length(max=20,message="创建人最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String operator;

    @Length(max=20,message="更新人最大长度20位" ,groups=SaveGroup.class)
	private java.lang.String updateoperator;

    @Length(max=2,message="状态  R3删除  R2正常最大长度2位" ,groups=SaveGroup.class)
	private java.lang.String state;

    @Length(max=1,message="上传标志最大长度1位" ,groups=SaveGroup.class)
	private java.lang.String scbz;

    @Length(max=21,message="人员编号最大长度21位" ,groups=SaveGroup.class)
	private java.lang.String rybh;

	//columns END


	public BzwtxEntity(){
	}

	public BzwtxEntity(
		java.lang.String id
	){
		this.id = id;
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	

	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}

	public java.lang.String getZwtxxh11() {
		return this.zwtxxh11;
	}
	
	public void setZwtxxh11(java.lang.String value) {
		this.zwtxxh11 = value;
	}

	public byte[] getZwtx11() {
		return this.zwtx11;
	}
	
	public void setZwtx11(byte[] value) {
		this.zwtx11 = value;
	}

	public java.lang.String getZwtxxh12() {
		return this.zwtxxh12;
	}
	
	public void setZwtxxh12(java.lang.String value) {
		this.zwtxxh12 = value;
	}

	public byte[] getZwtx12() {
		return this.zwtx12;
	}
	
	public void setZwtx12(byte[] value) {
		this.zwtx12 = value;
	}

	public java.lang.String getZwtxxh13() {
		return this.zwtxxh13;
	}
	
	public void setZwtxxh13(java.lang.String value) {
		this.zwtxxh13 = value;
	}

	public byte[] getZwtx13() {
		return this.zwtx13;
	}
	
	public void setZwtx13(byte[] value) {
		this.zwtx13 = value;
	}

	public java.lang.String getZwtxxh21() {
		return this.zwtxxh21;
	}
	
	public void setZwtxxh21(java.lang.String value) {
		this.zwtxxh21 = value;
	}

	public byte[] getZwtx21() {
		return this.zwtx21;
	}
	
	public void setZwtx21(byte[] value) {
		this.zwtx21 = value;
	}

	public java.lang.String getZwtxxh22() {
		return this.zwtxxh22;
	}
	
	public void setZwtxxh22(java.lang.String value) {
		this.zwtxxh22 = value;
	}

	public byte[] getZwtx22() {
		return this.zwtx22;
	}
	
	public void setZwtx22(byte[] value) {
		this.zwtx22 = value;
	}

	public java.lang.String getZwtxxh23() {
		return this.zwtxxh23;
	}
	
	public void setZwtxxh23(java.lang.String value) {
		this.zwtxxh23 = value;
	}

	public byte[] getZwtx23() {
		return this.zwtx23;
	}
	
	public void setZwtx23(byte[] value) {
		this.zwtx23 = value;
	}
	@ApiModelProperty(hidden = true)
	public String getCreatetimeString() {
		return DateTimeUtils.format(getCreatetime(), DateTimeUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
	}
	public java.util.Date getCreatetime() {
			return this.createtime;
			}

	public void setCreatetime(java.util.Date value) {
			this.createtime = value;
			}
	@ApiModelProperty(hidden = true)
	public String getUpdatetimeString() {
		return DateTimeUtils.format(getUpdatetime(), DateTimeUtils.YEAR_MONTH_DAY_HOUR_MINUTE_SECOND);
	}
	public java.util.Date getUpdatetime() {
			return this.updatetime;
			}

	public void setUpdatetime(java.util.Date value) {
			this.updatetime = value;
			}

	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}

	public java.lang.String getUpdateoperator() {
		return this.updateoperator;
	}
	
	public void setUpdateoperator(java.lang.String value) {
		this.updateoperator = value;
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

	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	
}

