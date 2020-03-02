/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.entity;

import awd.cloud.platform.utils.CacheUtils;
import awd.framework.common.entity.SimpleGenericEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class JsswEntity extends SimpleGenericEntity<String>{
	
	//alias
	public static final String TABLE_ALIAS = "Jssw";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_JSZJH = "家属证件号";
	public static final String ALIAS_SQYY = "申请原因";
	public static final String ALIAS_SQSJ = "申请时间";
	public static final String ALIAS_SQWP = "申请物品";
	public static final String ALIAS_SPR = "管教审批人";
	public static final String ALIAS_SPNR = "审批内容";
	public static final String ALIAS_SPBZ = "审批标志";
	public static final String ALIAS_SPSJ = "审批时间";
	public static final String ALIAS_GJQRR = "管教确认人";
	public static final String ALIAS_GJQRSJ = "管教确认时间";
	public static final String ALIAS_YJJSR = "邮寄家属人";
	public static final String ALIAS_YJJSSJ = "邮寄家属时间";
	public static final String ALIAS_QTBLSJ = "前台办理时间";
	public static final String ALIAS_QTBLR = "前台办理人";
	public static final String ALIAS_QTWPQR = "前台确认寄回物品是否与申请物品一致（SHFO）";
	public static final String ALIAS_AJSJ = "安检时间";
	public static final String ALIAS_AJR = "安检人";
	public static final String ALIAS_AJQK = "安检是否异常（SHFO）";
	public static final String ALIAS_AJYCQK = "安检异常情况";
	public static final String ALIAS_GJSWSJ = "管教送物时间";
	public static final String ALIAS_GJSWR = "管教送物人";
	public static final String ALIAS_QRBZ = "在押人员确认领取标志（SHFO）";
	public static final String ALIAS_YWZT = "业务状态（1申请，2管教审批，3管教确认，4邮寄家属，5前台办理，6安检机安检，7管教送物，8在押人员确认，9审批未通过）";
	public static final String ALIAS_LQSJ = "领取时间";
	public static final String ALIAS_LQBZ = "领取标志";
	
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
	private String jsbh;

	@NotNull(message="人员编号不能为空",groups=SaveGroup.class)
    @Length(max=21,message="人员编号最大长度21位" ,groups=SaveGroup.class)
	private String rybh;

	@NotNull(message="家属证件号不能为空",groups=SaveGroup.class)
    @Length(max=18,message="家属证件号最大长度18位" ,groups=SaveGroup.class)
	private String jszjh;

    @Length(max=255,message="申请原因最大长度255位" ,groups=SaveGroup.class)
	private String sqyy;

	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sqsj;

    @Length(max=255,message="申请物品最大长度255位" ,groups=SaveGroup.class)
	private String sqwp;

    @Length(max=20,message="管教审批人最大长度20位" ,groups=SaveGroup.class)
	private String spr;

    @Length(max=65535,message="审批内容最大长度65535位" ,groups=SaveGroup.class)
	private String spnr;

    @Length(max=1,message="审批标志最大长度1位" ,groups=SaveGroup.class)
	private String spbz;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date spsj;

    @Length(max=20,message="管教确认人最大长度20位" ,groups=SaveGroup.class)
	private String gjqrr;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gjqrsj;

    @Length(max=20,message="邮寄家属人最大长度20位" ,groups=SaveGroup.class)
	private String yjjsr;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date yjjssj;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date qtblsj;

    @Length(max=20,message="前台办理人最大长度20位" ,groups=SaveGroup.class)
	private String qtblr;

    @Length(max=1,message="前台确认寄回物品是否与申请物品一致（SHFO）最大长度1位" ,groups=SaveGroup.class)
	private String qtwpqr;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ajsj;

    @Length(max=20,message="安检人最大长度20位" ,groups=SaveGroup.class)
	private String ajr;

    @Length(max=1,message="安检是否异常（SHFO）最大长度1位" ,groups=SaveGroup.class)
	private String ajqk;

    @Length(max=65535,message="安检异常情况最大长度65535位" ,groups=SaveGroup.class)
	private String ajycqk;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gjswsj;

    @Length(max=20,message="管教送物人最大长度20位" ,groups=SaveGroup.class)
	private String gjswr;

    @Length(max=1,message="在押人员确认领取标志（SHFO）最大长度1位" ,groups=SaveGroup.class)
	private String qrbz;

    @Length(max=1,message="业务状态（1申请，2管教审批，3管教确认，4邮寄家属，5前台办理，6安检机安检，7管教送物，8在押人员确认，9审批未通过）最大长度1位" ,groups=SaveGroup.class)
	private String ywzt;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lqsj;

	@Length(max=1,message="领取标志最大长度1位" ,groups=SaveGroup.class)
	private String lqbz;


	//columns END


	public JsswEntity(){
	}

	public JsswEntity(
		String id
	){
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

	public String getJszjh() {
		return this.jszjh;
	}

	public void setJszjh(String value) {
		this.jszjh = value;
	}

	public String getSqyy() {
		return this.sqyy;
	}

	public void setSqyy(String value) {
		this.sqyy = value;
	}
	public Date getSqsj() {
			return this.sqsj;
			}

	public void setSqsj(Date value) {
			this.sqsj = value;
			}

	public String getSqwp() {
		return this.sqwp;
	}

	public void setSqwp(String value) {
		this.sqwp = value;
	}

	public String getSpr() {
		return this.spr;
	}

	public void setSpr(String value) {
		this.spr = value;
	}

	public String getSpnr() {
		return this.spnr;
	}

	public void setSpnr(String value) {
		this.spnr = value;
	}

	public String getSpbz() {
		return this.spbz;
	}

	public void setSpbz(String value) {
		this.spbz = value;
	}
	public Date getSpsj() {
			return this.spsj;
			}

	public void setSpsj(Date value) {
			this.spsj = value;
			}

	public String getGjqrr() {
		return this.gjqrr;
	}

	public void setGjqrr(String value) {
		this.gjqrr = value;
	}
	public Date getGjqrsj() {
			return this.gjqrsj;
			}

	public void setGjqrsj(Date value) {
			this.gjqrsj = value;
			}

	public String getYjjsr() {
		return this.yjjsr;
	}

	public void setYjjsr(String value) {
		this.yjjsr = value;
	}
	public Date getYjjssj() {
			return this.yjjssj;
			}

	public void setYjjssj(Date value) {
			this.yjjssj = value;
			}
	public Date getQtblsj() {
			return this.qtblsj;
			}

	public void setQtblsj(Date value) {
			this.qtblsj = value;
			}

	public String getQtblr() {
		return this.qtblr;
	}

	public void setQtblr(String value) {
		this.qtblr = value;
	}

	public String getQtwpqr() {
		return this.qtwpqr;
	}

	public void setQtwpqr(String value) {
		this.qtwpqr = value;
	}
	public Date getAjsj() {
			return this.ajsj;
			}

	public void setAjsj(Date value) {
			this.ajsj = value;
			}

	public String getAjr() {
		return this.ajr;
	}

	public void setAjr(String value) {
		this.ajr = value;
	}

	public String getAjqk() {
		return this.ajqk;
	}

	public void setAjqk(String value) {
		this.ajqk = value;
	}

	public String getAjycqk() {
		return this.ajycqk;
	}

	public void setAjycqk(String value) {
		this.ajycqk = value;
	}
	public Date getGjswsj() {
			return this.gjswsj;
			}

	public void setGjswsj(Date value) {
			this.gjswsj = value;
			}

	public String getGjswr() {
		return this.gjswr;
	}
	
	public void setGjswr(String value) {
		this.gjswr = value;
	}

	public String getQrbz() {
		return this.qrbz;
	}
	
	public void setQrbz(String value) {
		this.qrbz = value;
	}

	public String getYwzt() {
		return this.ywzt;
	}
	
	public void setYwzt(String value) {
		this.ywzt = value;
	}

	public Date getLqsj() {
		return lqsj;
	}

	public void setLqsj(Date lqsj) {
		this.lqsj = lqsj;
	}

	public String getLqbz() {
		return lqbz;
	}

	public void setLqbz(String lqbz) {
		this.lqbz = lqbz;
	}
}

