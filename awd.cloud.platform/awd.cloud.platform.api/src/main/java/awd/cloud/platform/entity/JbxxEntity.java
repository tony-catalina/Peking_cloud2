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
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JbxxEntity extends SimpleGenericEntity<String> {

	private static final long serialVersionUID = 1698323349307720051L;

	public static final String TABLE_ALIAS = "基本信息";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_GCBH = "过程编号";
	public static final String ALIAS_WBRYBH = "网办人员编号";
	public static final String ALIAS_RYBH = "人员编号";
	public static final String ALIAS_DAH = "档案编号";
	public static final String ALIAS_JSBH = "监所编号";
	public static final String ALIAS_SNBH = "人员所内编号";
	public static final String ALIAS_JSH = "监室号";
	public static final String ALIAS_XM = "姓名";
	public static final String ALIAS_XMPY = "姓名拼音";
	public static final String ALIAS_XMPYSZM = "姓名拼音首字母";
	public static final String ALIAS_BM = "别名";
	public static final String ALIAS_BMTY = "别名同音";
	public static final String ALIAS_MZ = "民族(MZ)";
	public static final String ALIAS_GJ = "国籍(GJ)";
	public static final String ALIAS_XB = "性别(XB)";
	public static final String ALIAS_CSRQ = "出生日期";
	public static final String ALIAS_ZJLX = "证件类型(ZJLX)";
	public static final String ALIAS_ZJH = "证件号";
	public static final String ALIAS_ZZMM = "政治面貌(ZZMM)";
	public static final String ALIAS_HYZK = "婚姻状况(HYZK)";
	public static final String ALIAS_ZUC = "足长";
	public static final String ALIAS_SG = "身高";
	public static final String ALIAS_JG = "籍贯(XZQH)";
	public static final String ALIAS_HJD = "户籍地(XZQH)";
	public static final String ALIAS_HJDXZ = "户籍地详址";
	public static final String ALIAS_XZD = "现住地(XZQH)";
	public static final String ALIAS_XZDXZ = "现住地详址";
	public static final String ALIAS_WHCD = "文化程度(WHCD)";
	public static final String ALIAS_ZC = "专长(ZC)";
	public static final String ALIAS_SF = "身份(SF)";
	public static final String ALIAS_TSSF = "特殊身份(TSSF)";
	public static final String ALIAS_ZY = "职业(ZY)";
	public static final String ALIAS_GZDW = "(原)工作单位";
	public static final String ALIAS_JKZK = "健康情况(JKZK)";
	public static final String ALIAS_BHLX = "病号类型(BHLX)";
	public static final String ALIAS_AZB = "艾滋病(SHFO)";
	public static final String ALIAS_RSRQ = "入所日期";
	public static final String ALIAS_RSXZ = "入所性质(RSXZ)";
	public static final String ALIAS_ZRDW = "转入单位";
	public static final String ALIAS_SHID = "手环ID";
	public static final String ALIAS_SYDW = "送押单位";
	public static final String ALIAS_SYR = "送押人";
	public static final String ALIAS_SY = "收押人";
	public static final String ALIAS_BYZD = "收押非拘捕人员";
	public static final String ALIAS_SYPZWSH = "收押凭证文书号";
	public static final String ALIAS_SYPZ = "收押凭证(SYPZ)";
	public static final String ALIAS_JYRQ = "羁押日期";
	public static final String ALIAS_GYQX = "关押期限";
	public static final String ALIAS_AY = "主要案由(AJLB)";
	public static final String ALIAS_XHAY = "细化案由(AJLB)";
	public static final String ALIAS_FZJL = "犯罪经历";
	public static final String ALIAS_JYAQ = "简要案情";
	public static final String ALIAS_ZXF = "重刑犯(ZXF)";
	public static final String ALIAS_WXDJ = "危险等级(FXDJ)";
	public static final String ALIAS_CAAJ = "从案类型(AJLB)";
	public static final String ALIAS_CYLX = "成员类型(CYLX)";
	public static final String ALIAS_BAR = "办案人 多个以逗号或空格分开";
	public static final String ALIAS_BARJH = "办案人警号 多个以逗号或空格分开";
	public static final String ALIAS_BAHJ = "办案环节(BAJD)";
	public static final String ALIAS_BARDH = "办案民警电话";
	public static final String ALIAS_CZDH = "办案传真电话";
	public static final String ALIAS_ZZQSRQ = "暂住起始日期";
	public static final String ALIAS_JLRQ = "拘留日期";
	public static final String ALIAS_DBRQ = "逮捕日期";
	public static final String ALIAS_SCQSRQ = "审查起诉日期";
	public static final String ALIAS_YSFYRQ = "移送法院日期";
	public static final String ALIAS_BADW = "办案单位(BADW)";
	public static final String ALIAS_DWLX = "办案单位类型(DWLX)";
	public static final String ALIAS_HX = "缓刑";
	public static final String ALIAS_HXQX = "缓刑期限";
	public static final String ALIAS_CSYY = "出所原因(CSYY)";
	public static final String ALIAS_CSQX = "出所去向(CSQX)";
	public static final String ALIAS_CSZWYZ = "出所指纹验证(SHFO)";
	public static final String ALIAS_CSSJ = "出所时间";
	public static final String ALIAS_XQ = "刑期";
	public static final String ALIAS_CLJG = "处理结果(CLJG)";
	public static final String ALIAS_FJX = "附加刑(FJX)";
	public static final String ALIAS_ZWBH = "指纹编号";
	public static final String ALIAS_JCQK = "奖惩情况";
	public static final String ALIAS_YKSS = "原看守所";
	public static final String ALIAS_LSYY = "留所原因(LSYY)";
	public static final String ALIAS_RYGLLB = "人员管理类别(RYGLLB)";
	public static final String ALIAS_ZSZT = "在所状态(ZSZT) 12,13都属于已决";
	public static final String ALIAS_LSCSYY = "临时出所原因(LSCS)";
	public static final String ALIAS_LSCSSJ = "上一次临时出所时间";
	public static final String ALIAS_LSCSHSSJ = "上一次临时出所回所时间";
	public static final String ALIAS_CRJBJ = "出入监标记(CJBJ)";
	public static final String ALIAS_RKBHGBJ = "入库合格不合格标记(SHFO)";
	public static final String ALIAS_RKBHGYY = "不合格入库原因";
	public static final String ALIAS_LRSFJS = "是否及时录入标记(SHFO)";
	public static final String ALIAS_ZYRYXGQK = "在押人员相关情况 (ZYRYXGQK)";
	public static final String ALIAS_EMLX = "耳目类型(EMLX)";
	public static final String ALIAS_RSJCLX = "妊娠检测类型(RSJCLX)";
	public static final String ALIAS_JJ = "戒具标志位(JJ)";
	public static final String ALIAS_JB = "禁闭标志位(JB)";
	public static final String ALIAS_LDF = "劳动犯标志位(LDF)";
	public static final String ALIAS_YGRY = "严管人员标志位(YGRY)";
	public static final String ALIAS_ZDRY = "重点人员(ZDRY)";
	public static final String ALIAS_SLAJ = "是否属于三类案件(SHFO)";
	public static final String ALIAS_SYKZRQ = "收押开证日期(经办日期)";
	public static final String ALIAS_ISJDWM = "是否戒毒未满(SHFO)";
	public static final String ALIAS_CYPJFZ = "处遇评鉴分值";
	public static final String ALIAS_SFYPJ = "是否已评鉴,(SHFO)";
	public static final String ALIAS_SFRZ = "是否认罪(SHFO)";
	public static final String ALIAS_SFYXJSLX = "是否允许家属联系(SHFO)";
	public static final String ALIAS_DAGDBJ = "档案归案标记";
	public static final String ALIAS_CLSYPZR = "超龄收押批准人";
	public static final String ALIAS_RYZWBJ = "人员指纹标记(RYZWBJ)";
	public static final String ALIAS_WCNBJ = "未成年标记(SHFO)";
	public static final String ALIAS_GRJL = "个人经历";
	public static final String ALIAS_DRJSR = "带入监室人";
	public static final String ALIAS_DRJSSJ = "带入监室时间";
	public static final String ALIAS_LASTFXPGFZ = "最后一次风险评估分值";
	public static final String ALIAS_STATE = "状态(RYSTATE)";
	public static final String ALIAS_BZ = "备注";
	public static final String ALIAS_JSLY = "拒收理由(入所健康检查结果)";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_UPDATOR = "更新人";
	public static final String ALIAS_UPDATETIME = "更新时间";
	public static final String ALIAS_JYGZZLPG = "教育质量改造评估(SHFO)";
	public static final String ALIAS_FLWS = "法律文书打印";
	public static final String ALIAS_ZYAF = "重要案犯(ZYAF)";
	public static final String ALIAS_SFCXCY = "是否抽血采样(SHFO)";
	public static final String ALIAS_SFFY = "是否需要发药(SHFO)";
	public static final String ALIAS_FYKSRQ = "发药开始日期";
	public static final String ALIAS_FYJSRQ = "发药结束日期";
	public static final String ALIAS_WPSFLQ = "物品是否领取完(SHFO)";
	public static final String ALIAS_SFWXJC = "五项检查是否及时(SHFO)";
	public static final String ALIAS_YWLCID = "业务流程ID";
	public static final String ALIAS_SYRDH = "送押人电话";
	public static final String ALIAS_TABH = "同案编号";
	public static final String ALIAS_ZA = "专案";
	public static final String ALIAS_YFH = "衣服号";
	public static final String ALIAS_ZW = "职务";
	public static final String ALIAS_ZWJB = "职务级别";
	public static final String ALIAS_TZ = "体重";
	public static final String ALIAS_CWH = "床位号";
	public static final String ALIAS_TBTSBJ = "体表特殊标记";
	public static final String ALIAS_CLJGRQ = "处理结果日期";
	public static final String ALIAS_PJZM = "判决罪名";
	public static final String ALIAS_ZXTZSSDRQ = "执行通知书送达日期";
	public static final String ALIAS_TZCSBZ = "通知出所标志(SHFO)";
	public static final String ALIAS_JE = "金额";
	public static final String ALIAS_JBR = "经办人";
	public static final String ALIAS_JBRQ = "经办日期";
	public static final String ALIAS_SFSWRY = "是否涉维人员(SHFO)";
	public static final String ALIAS_SWPGZB = "涉维评估指标";
	public static final String ALIAS_SWPGR = "涉维评估人";
	public static final String ALIAS_SWPGSJ = "涉维评估时间";
	public static final String ALIAS_JSYCBZ="精神异常标志(SHFO)";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START
	@Length(max=30,message="过程编号最大长度30位" ,groups=CreateGroup.class)
	private String gcbh;

	@Length(max=30,message="网办人员编号最大长度30位" ,groups=CreateGroup.class)
	private String wbrybh;

	@Length(max=21,message="人员编号最大长度21位" ,groups=CreateGroup.class)
	private String rybh;

	@Length(max=30,message="档案编号最大长度30位" ,groups=CreateGroup.class)
	private String dah;

	@Length(max=9,message="监所编号最大长度9位" ,groups=CreateGroup.class)
	private String jsbh;

	@Length(max=8,message="人员所内编号最大长度8位" ,groups=CreateGroup.class)
	private String snbh;

	@Length(max=4,message="监室号最大长度4位" ,groups=CreateGroup.class)
	private String jsh;

	@Length(max=50,message="姓名最大长度50位" ,groups=CreateGroup.class)
	private String xm;

	@Length(max=100,message="姓名拼音最大长度100位" ,groups=CreateGroup.class)
	private String xmpy;

	@Length(max=10,message="姓名拼音首字母最大长度10位" ,groups=CreateGroup.class)
	private String xmpyszm;

	@Length(max=50,message="别名最大长度50位" ,groups=CreateGroup.class)
	private String bm;

	@Length(max=160,message="别名同音最大长度160位" ,groups=CreateGroup.class)
	private String bmty;

	@Length(max=2,message="民族(MZ)最大长度2位" ,groups=CreateGroup.class)
	private String mz;

	@Length(max=3,message="国籍(GJ)最大长度3位" ,groups=CreateGroup.class)
	private String gj;

	@Length(max=1,message="性别(XB)最大长度1位" ,groups=CreateGroup.class)
	private String xb;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date csrq;

	@Length(max=2,message="证件类型(ZJLX)最大长度2位" ,groups=CreateGroup.class)
	private String zjlx;

	@Length(max=50,message="证件号最大长度50位" ,groups=CreateGroup.class)
	private String zjh;

	@Length(max=2,message="政治面貌(ZZMM)最大长度2位" ,groups=CreateGroup.class)
	private String zzmm;

	@Length(max=1,message="婚姻状况(HYZK)最大长度1位" ,groups=CreateGroup.class)
	private String hyzk;

	@Length(max=6,message="足长最大长度6位" ,groups=CreateGroup.class)
	private String zuc;

	@Length(max=5,message="身高最大长度5位" ,groups=CreateGroup.class)
	private String sg;

	@Length(max=6,message="籍贯(XZQH)最大长度6位" ,groups=CreateGroup.class)
	private String jg;

	@Length(max=6,message="户籍地(XZQH)最大长度6位" ,groups=CreateGroup.class)
	private String hjd;

	@Length(max=100,message="户籍地详址最大长度100位" ,groups=CreateGroup.class)
	private String hjdxz;

	@Length(max=6,message="现住地(XZQH)最大长度6位" ,groups=CreateGroup.class)
	private String xzd;

	@Length(max=100,message="现住地详址最大长度100位" ,groups=CreateGroup.class)
	private String xzdxz;

	@Length(max=2,message="文化程度(WHCD)最大长度2位" ,groups=CreateGroup.class)
	private String whcd;

	@Length(max=2,message="专长(ZC)最大长度2位" ,groups=CreateGroup.class)
	private String zc;

	@Length(max=2,message="身份(SF)最大长度2位" ,groups=CreateGroup.class)
	private String sf;

	@Length(max=2,message="特殊身份(TSSF)最大长度2位" ,groups=CreateGroup.class)
	private String tssf;

	@Length(max=4,message="职业(ZY)最大长度4位" ,groups=CreateGroup.class)
	private String zy;

	@Length(max=40,message="(原)工作单位最大长度40位" ,groups=CreateGroup.class)
	private String gzdw;

	@Length(max=2,message="健康情况(JKZK)最大长度2位" ,groups=CreateGroup.class)
	private String jkzk;

	@Length(max=1,message="病号类型(BHLX)最大长度1位" ,groups=CreateGroup.class)
	private String bhlx;

	@Length(max=1,message="艾滋病(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String azb;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rsrq;

	@Length(max=2,message="入所性质(RSXZ)最大长度2位" ,groups=CreateGroup.class)
	private String rsxz;

	@Length(max=60,message="转入单位最大长度60位" ,groups=CreateGroup.class)
	private String zrdw;

	@Length(max=30,message="手环ID最大长度30位" ,groups=CreateGroup.class)
	private String shid;

	@Length(max=60,message="送押单位最大长度60位" ,groups=CreateGroup.class)
	private String sydw;

	@Length(max=60,message="送押人最大长度60位" ,groups=CreateGroup.class)
	private String syr;

	@Length(max=40,message="收押人最大长度40位" ,groups=CreateGroup.class)
	private String sy;

	@Length(max=20,message="收押非拘捕人员最大长度20位" ,groups=CreateGroup.class)
	private String byzd;

	@Length(max=60,message="收押凭证文书号最大长度60位" ,groups=CreateGroup.class)
	private String sypzwsh;

	@Length(max=2,message="收押凭证(SYPZ)最大长度2位" ,groups=CreateGroup.class)
	private String sypz;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date jyrq;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gyqx;

	@Length(max=34,message="主要案由(AJLB)最大长度34位" ,groups=CreateGroup.class)
	private String ay;

	@Length(max=30,message="细化案由(AJLB)最大长度30位" ,groups=CreateGroup.class)
	private String xhay;

	@Length(max=65535,message="犯罪经历最大长度65535位" ,groups=CreateGroup.class)
	private String fzjl;

	@Length(max=2000,message="简要案情最大长度2000位" ,groups=CreateGroup.class)
	private String jyaq;

	@Length(max=1,message="重刑犯(ZXF)最大长度1位" ,groups=CreateGroup.class)
	private String zxf;

	@Length(max=2,message="危险等级(FXDJ)最大长度2位" ,groups=CreateGroup.class)
	private String wxdj;

	@Length(max=100,message="从案类型(AJLB)最大长度100位" ,groups=CreateGroup.class)
	private String caaj;

	@Length(max=2,message="成员类型(CYLX)最大长度2位" ,groups=CreateGroup.class)
	private String cylx;

	@Length(max=30,message="办案人 多个以逗号或空格分开最大长度30位" ,groups=CreateGroup.class)
	private String bar;

	@Length(max=30,message="办案人警号 多个以逗号或空格分开最大长度30位" ,groups=CreateGroup.class)
	private String barjh;

	@Length(max=2,message="办案环节(BAJD)最大长度2位" ,groups=CreateGroup.class)
	private String bahj;

	@Length(max=40,message="办案民警电话最大长度40位" ,groups=CreateGroup.class)
	private String bardh;

	@Length(max=50,message="办案传真电话最大长度50位" ,groups=CreateGroup.class)
	private String czdh;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date zzqsrq;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date jlrq;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dbrq;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date scqsrq;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date ysfyrq;

	@Length(max=60,message="办案单位(BADW)最大长度60位" ,groups=CreateGroup.class)
	private String badw;

	@Length(max=1,message="办案单位类型(DWLX)最大长度1位" ,groups=CreateGroup.class)
	private String dwlx;

	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hx;

	@Length(max=20,message="缓刑期限最大长度20位" ,groups=CreateGroup.class)
	private String hxqx;

	@Length(max=2,message="出所原因(CSYY)最大长度2位" ,groups=CreateGroup.class)
	private String csyy;

	@Length(max=40,message="出所去向最大长度40位" ,groups=CreateGroup.class)
	private String csqx;

	@Length(max=1,message="出所指纹验证(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String cszwyz;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cssj;

	@Length(max=12,message="刑期最大长度12位" ,groups=CreateGroup.class)
	private String xq;

	@Length(max=3,message="处理结果(CLJG)最大长度3位" ,groups=CreateGroup.class)
	private String cljg;

	@Length(max=2,message="附加刑(FJX)最大长度2位" ,groups=CreateGroup.class)
	private String fjx;

	@Length(max=30,message="指纹编号最大长度30位" ,groups=CreateGroup.class)
	private String zwbh;

	@Length(max=40,message="奖惩情况最大长度40位" ,groups=CreateGroup.class)
	private String jcqk;

	@Length(max=30,message="原看守所最大长度30位" ,groups=CreateGroup.class)
	private String ykss;

	@Length(max=2,message="留所原因(LSYY)最大长度2位" ,groups=CreateGroup.class)
	private String lsyy;

	@Length(max=2,message="人员管理类别(RYGLLB)最大长度2位" ,groups=CreateGroup.class)
	private String rygllb;

	@Length(max=2,message="在所状态(ZSZT) 12,13都属于已决最大长度2位" ,groups=CreateGroup.class)
	private String zszt;

	@Length(max=2,message="临时出所原因(LSCS)最大长度2位" ,groups=CreateGroup.class)
	private String lscsyy;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lscssj;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lscshssj;

	@Length(max=2,message="出入监标记(CJBJ)最大长度2位" ,groups=CreateGroup.class)
	private String crjbj;

	@Length(max=1,message="入库合格不合格标记(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String rkbhgbj;

	@Length(max=100,message="不合格入库原因最大长度100位" ,groups=CreateGroup.class)
	private String rkbhgyy;

	@Length(max=1,message="是否及时录入标记(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String lrsfjs;

	@Length(max=100,message="在押人员相关情况 (ZYRYXGQK)最大长度100位" ,groups=CreateGroup.class)
	private String zyryxgqk;

	@Length(max=2,message="耳目类型(EMLX)最大长度2位" ,groups=CreateGroup.class)
	private String emlx;

	@Length(max=1,message="妊娠检测类型(RSJCLX)最大长度1位" ,groups=CreateGroup.class)
	private String rsjclx;

	@Length(max=2,message="戒具标志位(JJ)最大长度2位" ,groups=CreateGroup.class)
	private String jj;

	@Length(max=2,message="禁闭标志位(JB)最大长度2位" ,groups=CreateGroup.class)
	private String jb;

	@Length(max=2,message="劳动犯标志位(LDF)最大长度2位" ,groups=CreateGroup.class)
	private String ldf;

	@Length(max=2,message="严管人员标志位(YGRY)最大长度2位" ,groups=CreateGroup.class)
	private String ygry;

	@Length(max=1,message="重点人员(ZDRY)最大长度1位" ,groups=CreateGroup.class)
	private String zdry;

	@Length(max=1,message="是否属于三类案件(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String slaj;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sykzrq;

	@Length(max=1,message="是否戒毒未满(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String isjdwm;

	@Length(max=5,message="处遇评鉴分值最大长度5位" ,groups=CreateGroup.class)
	private String cypjfz;

	@Length(max=1,message="是否已评鉴,(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String sfypj;

	@Length(max=1,message="是否认罪(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String sfrz;

	@Length(max=1,message="是否允许家属联系(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String sfyxjslx;

	@Length(max=100,message="档案归案标记最大长度100位" ,groups=CreateGroup.class)
	private String dagdbj;

	@Length(max=50,message="超龄收押批准人最大长度50位" ,groups=CreateGroup.class)
	private String clsypzr;

	@Length(max=1,message="人员指纹标记(RYZWBJ)最大长度1位" ,groups=CreateGroup.class)
	private String ryzwbj;

	@Length(max=1,message="未成年标记(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String wcnbj;

	@Length(max=65535,message="个人经历最大长度65535位" ,groups=CreateGroup.class)
	private String grjl;

	@Length(max=30,message="带入监室人最大长度30位" ,groups=CreateGroup.class)
	private String drjsr;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date drjssj;

	@Length(max=5,message="最后一次风险评估分值最大长度5位" ,groups=CreateGroup.class)
	private String lastfxpgfz;

	@Length(max=2,message="状态(RYSTATE)最大长度2位" ,groups=CreateGroup.class)
	private String state;

	@Length(max=100,message="备注最大长度100位" ,groups=CreateGroup.class)
	private String bz;

	@Length(max=100,message="拒收理由(入所健康检查结果)最大长度100位" ,groups=CreateGroup.class)
	private String jsly;

	@Length(max=50,message="创建人最大长度50位" ,groups=CreateGroup.class)
	private String creator;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;

	@Length(max=50,message="更新人最大长度50位" ,groups=CreateGroup.class)
	private String updator;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;

	@Length(max=1,message="教育质量改造评估(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String jygzzlpg;

	@Length(max=2,message="法律文书打印最大长度2位" ,groups=CreateGroup.class)
	private String flws;

	@Length(max=1,message="重要案犯(ZYAF)最大长度1位" ,groups=CreateGroup.class)
	private String zyaf;

	@Length(max=1,message="是否抽血采样(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String sfcxcy;

	@Length(max=1,message="是否需要发药(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String sffy;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fyksrq;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fyjsrq;

	@Length(max=1,message="物品是否领取完(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String wpsflq;

	@Length(max=1,message="五项检查是否及时(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String sfwxjc;

	@Length(max=100,message="业务流程ID最大长度100位" ,groups=CreateGroup.class)
	private String ywlcid;

	@Length(max=40,message="送押人电话最大长度40位" ,groups=CreateGroup.class)
	private String syrdh;

	@Length(max=30,message="同案编号最大长度30位" ,groups=CreateGroup.class)
	private String tabh;

	@Length(max=30,message="专案最大长度30位" ,groups=CreateGroup.class)
	private String za;

	@Length(max=30,message="衣服号最大长度30位" ,groups=CreateGroup.class)
	private String yfh;

	@Length(max=2,message="职务最大长度2位" ,groups=CreateGroup.class)
	private String zw;

	@Length(max=4,message="职务级别最大长度4位" ,groups=CreateGroup.class)
	private String zwjb;

	private Short tz;

	@Length(max=4,message="床位号大长度4位" ,groups=CreateGroup.class)
	private String cwh;

	@Length(max=2000,message="体表特殊标记最大长度2000位" ,groups=CreateGroup.class)
	private String tbtsbj;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cljgrq;

	@Length(max=200,message="判决罪名最大长度200位" ,groups=CreateGroup.class)
	private String pjzm;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date zxtzssdrq;

	@Length(max=1,message="通知出所标志(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String tzcsbz;

	private BigDecimal je;

	@Length(max=30,message="经办人最大长度30位" ,groups=CreateGroup.class)
	private String jbr;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date jbrq;

	@Length(max=1,message="是否涉维人员(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String sfswry;

	@Length(max=200,message="涉维评估指标最大长度200位" ,groups=CreateGroup.class)
	private String swpgzb;

	@Length(max=30,message="涉维评估人最大长度30位" ,groups=CreateGroup.class)
	private String swpgr;


	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date swpgsj;

	@Length(max=1,message="精神异常标志(SHFO)最大长度1位" ,groups=CreateGroup.class)
	private String jsycbz;

	// columns END

	public JbxxEntity() {
	}

	public JbxxEntity(String id) {
		super.setId(id);
	}

	public String getGcbh() {
		return this.gcbh;
	}

	public void setGcbh(String value) {
		this.gcbh = value;
	}

	public String getWbrybh() {
		return this.wbrybh;
	}

	public void setWbrybh(String value) {
		this.wbrybh = value;
	}

	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}


	public String getTzcsbz() {
		return tzcsbz;
	}

	public String getTzcsbzString() {
		return CacheUtils.get().getDictionary("SHFO", this.getTzcsbz());
	}

	public void setTzcsbz(String tzcsbz) {
		this.tzcsbz = tzcsbz;
	}

	public String getDah() {
		return this.dah;
	}

	public void setDah(String value) {
		this.dah = value;
	}

	public String getJsbh() {
		return this.jsbh;
	}

	public String getJsbhString() {
		return CacheUtils.get().getKssmcByKss(this.getJsbh());
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}


	public String getSnbh() {
		return this.snbh;
	}

	public void setSnbh(String value) {
		this.snbh = value;
	}

	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}

	public String getXmpy() {
		return this.xmpy;
	}

	public void setXmpy(String value) {
		this.xmpy = value;
	}

	public String getBm() {
		return this.bm;
	}

	public void setBm(String value) {
		this.bm = value;
	}

	public String getBmty() {
		return this.bmty;
	}

	public void setBmty(String value) {
		this.bmty = value;
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

	public String getGj() {
		return this.gj;
	}

	public String getGjString() {
		return CacheUtils.get().getDictionary("GJ", this.getGj());
	}

	public void setGj(String value) {
		this.gj = value;
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

	public Date getCsrq() {
		return this.csrq;
	}

	public void setCsrq(Date value) {
		this.csrq = value;
	}

	public String getZjlx() {
		return this.zjlx;
	}

	public String getZjlxString() {
		return CacheUtils.get().getDictionary("ZJLX", this.getZjlx());
	}

	public void setZjlx(String value) {
		this.zjlx = value;
	}

	public String getZjh() {
		return this.zjh;
	}

	public void setZjh(String value) {
		this.zjh = value;
	}

	public String getZzmm() {
		return this.zzmm;
	}

	public String getZzmmString() {
		return CacheUtils.get().getDictionary("ZZMM", this.getZzmm());
	}

	public void setZzmm(String value) {
		this.zzmm = value;
	}

	public String getHyzk() {
		return this.hyzk;
	}

	public String getHyzkString() {
		return CacheUtils.get().getDictionary("HYZK", this.getHyzk());
	}

	public void setHyzk(String value) {
		this.hyzk = value;
	}

	public String getZuc() {
		return this.zuc;
	}

	public void setZuc(String value) {
		this.zuc = value;
	}

	public String getSg() {
		return this.sg;
	}

	public void setSg(String value) {
		this.sg = value;
	}

	public String getJg() {
		return this.jg;
	}

	public String getJgString() {
		return CacheUtils.get().getDictionary("XZQH", this.getJg());
	}

	public void setJg(String value) {
		this.jg = value;
	}

	public String getHjd() {
		return this.hjd;
	}

	public String getHjdString() {
		return CacheUtils.get().getDictionary("XZQH", this.getHjd());
	}

	public void setHjd(String value) {
		this.hjd = value;
	}

	public String getHjdxz() {
		return this.hjdxz;
	}

	public void setHjdxz(String value) {
		this.hjdxz = value;
	}

	public String getXzd() {
		return this.xzd;
	}

	public String getXzdString() {
		return CacheUtils.get().getDictionary("XZQH", this.getXzd());
	}

	public void setXzd(String value) {
		this.xzd = value;
	}

	public String getXzdxz() {
		return this.xzdxz;
	}

	public void setXzdxz(String value) {
		this.xzdxz = value;
	}

	public String getWhcd() {
		return this.whcd;
	}

	public String getWhcdString() {
		return CacheUtils.get().getDictionary("WHCD", this.getWhcd());
	}

	public void setWhcd(String value) {
		this.whcd = value;
	}

	public String getZc() {
		return this.zc;
	}

	public String getZcString() {
		return CacheUtils.get().getDictionary("ZC", this.getZc());
	}

	public void setZc(String value) {
		this.zc = value;
	}

	public String getSf() {
		return this.sf;
	}

	public String getSfString() {
		return CacheUtils.get().getDictionary("SF", this.getSf());
	}

	public void setSf(String value) {
		this.sf = value;
	}

	public String getTssf() {
		return this.tssf;
	}

	public String getTssfString() {
		return CacheUtils.get().getDictionary("TSSF", this.getTssf());
	}

	public void setTssf(String value) {
		this.tssf = value;
	}

	public String getZy() {
		return this.zy;
	}

	public String getZyString() {
		return CacheUtils.get().getDictionary("ZY", this.getZy());
	}

	public void setZy(String value) {
		this.zy = value;
	}

	public String getGzdw() {
		return this.gzdw;
	}

	public void setGzdw(String value) {
		this.gzdw = value;
	}

	public String getJkzk() {
		return this.jkzk;
	}

	public String getJkzkString() {
		return CacheUtils.get().getDictionary("JKZK", this.getJkzk());
	}

	public void setJkzk(String value) {
		this.jkzk = value;
	}

	public String getBhlx() {
		return this.bhlx;
	}

	public String getBhlxString() {
		return CacheUtils.get().getDictionary("BHLX", this.getBhlx());
	}

	public void setBhlx(String value) {
		this.bhlx = value;
	}

	public String getAzb() {
		return this.azb;
	}

	public String getAzbString() {
		return CacheUtils.get().getDictionary("SHFO", this.getAzb());
	}

	public void setAzb(String value) {
		this.azb = value;
	}

	public Date getRsrq() {
		return this.rsrq;
	}

	public void setRsrq(Date value) {
		this.rsrq = value;
	}

	public String getRsxz() {
		return this.rsxz;
	}

	public String getRsxzString() {
		return CacheUtils.get().getDictionary("RSXZ", this.getRsxz());
	}

	public void setRsxz(String value) {
		this.rsxz = value;
	}

	public String getZrdw() {
		return this.zrdw;
	}

	public void setZrdw(String value) {
		this.zrdw = value;
	}

	public String getSydw() {
		return this.sydw;
	}

	public void setSydw(String value) {
		this.sydw = value;
	}

	public String getSyr() {
		return this.syr;
	}

	public void setSyr(String value) {
		this.syr = value;
	}

	public String getSy() {
		return this.sy;
	}

	public void setSy(String value) {
		this.sy = value;
	}

	public String getByzd() {
		return this.byzd;
	}

	public void setByzd(String value) {
		this.byzd = value;
	}

	public String getSypzwsh() {
		return this.sypzwsh;
	}

	public void setSypzwsh(String value) {
		this.sypzwsh = value;
	}

	public String getSypz() {
		return this.sypz;
	}

	public String getSypzString() {
		return CacheUtils.get().getDictionary("SYPZ", this.getSypz());
	}

	public void setSypz(String value) {
		this.sypz = value;
	}

	public Date getJyrq() {
		return this.jyrq;
	}

	public void setJyrq(Date value) {
		this.jyrq = value;
	}

	public Date getGyqx() {
		return this.gyqx;
	}

	public void setGyqx(Date value) {
		this.gyqx = value;
	}

	public String getAy() {
		return this.ay;
	}

	public String getAyString() {
		String str = CacheUtils.get().findDictionarys("AJLB", this.getAy());
		return str;
	}

	public void setAy(String value) {
		this.ay = value;
	}

	public String getXhay() {
		return this.xhay;
	}

	public String getXhayString() {
		return CacheUtils.get().findDictionarys("AJLB", this.getXhay());
	}

	public void setXhay(String value) {
		this.xhay = value;
	}

	public String getFzjl() {
		return this.fzjl;
	}

	public void setFzjl(String value) {
		this.fzjl = value;
	}

	public String getJyaq() {
		return this.jyaq;
	}

	public void setJyaq(String value) {
		this.jyaq = value;
	}

	public String getCaaj() {
		return this.caaj;
	}

	public String getCaajString() {
		return CacheUtils.get().getDictionary("AJLB", this.getCaaj());
	}

	public void setCaaj(String value) {
		this.caaj = value;
	}

	public String getCylx() {
		return this.cylx;
	}

	public String getCylxString() {
		return CacheUtils.get().getDictionary("CYLX", this.getCylx());
	}

	public void setCylx(String value) {
		this.cylx = value;
	}

	public String getBar() {
		return this.bar;
	}

	public void setBar(String value) {
		this.bar = value;
	}

	public String getBarjh() {
		return this.barjh;
	}

	public void setBarjh(String value) {
		this.barjh = value;
	}

	public String getBahj() {
		return this.bahj;
	}

	public String getBahjString() {
		return CacheUtils.get().getDictionary("BAJD", this.getBahj());
	}

	public void setBahj(String value) {
		this.bahj = value;
	}

	public String getBardh() {
		return this.bardh;
	}

	public void setBardh(String value) {
		this.bardh = value;
	}

	public Date getZzqsrq() {
		return this.zzqsrq;
	}

	public void setZzqsrq(Date value) {
		this.zzqsrq = value;
	}

	public Date getJlrq() {
		return this.jlrq;
	}

	public void setJlrq(Date value) {
		this.jlrq = value;
	}

	public Date getDbrq() {
		return this.dbrq;
	}

	public void setDbrq(Date value) {
		this.dbrq = value;
	}

	public String getBadw() {
		return this.badw;
	}

	public void setBadw(String value) {
		this.badw = value;
	}

	public String getDwlx() {
		return this.dwlx;
	}

	public String getDwlxString() {
		return CacheUtils.get().getDictionary("DWLX", this.getDwlx());
	}

	public void setDwlx(String value) {
		this.dwlx = value;
	}

	public String getHxqx() {
		return this.hxqx;
	}

	public void setHxqx(String value) {
		this.hxqx = value;
	}

	public String getCsyy() {
		return this.csyy;
	}

	public String getCsyyString() {
		return CacheUtils.get().getDictionary("CSYY", this.getCsyy());
	}

	public void setCsyy(String value) {
		this.csyy = value;
	}

	public String getCsqx() {
		return this.csqx;
	}

	public String getCsqxString() {
		return CacheUtils.get().getDictionary("CSQX", this.getCsqx());
	}

	public void setCsqx(String value) {
		this.csqx = value;
	}

	public String getCszwyz() {
		return this.cszwyz;
	}

	public String getCszwyzString() {
		return CacheUtils.get().getDictionary("SHFO", this.getCszwyz());
	}

	public void setCszwyz(String value) {
		this.cszwyz = value;
	}

	public Date getCssj() {
		return this.cssj;
	}

	public Date getHx() {
		return hx;
	}

	public void setHx(Date hx) {
		this.hx = hx;
	}

	public void setCssj(Date value) {
		this.cssj = value;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getCljg() {
		return this.cljg;
	}

	public String getCljgString() {
		return CacheUtils.get().getDictionary("CLJG", this.getCljg());
	}

	public void setCljg(String value) {
		this.cljg = value;
	}

	public String getFjx() {
		return this.fjx;
	}

	public String getFjxString() {
		return CacheUtils.get().getDictionary("FJX", this.getFjx());
	}

	public void setFjx(String value) {
		this.fjx = value;
	}

	public String getZwbh() {
		return this.zwbh;
	}

	public void setZwbh(String value) {
		this.zwbh = value;
	}

	public String getRygllb() {
		return this.rygllb;
	}

	public String getRygllbString() {
		return CacheUtils.get().getDictionary("RYGLLB", this.getRygllb());
	}

	public void setRygllb(String value) {
		this.rygllb = value;
	}

	public String getZszt() {
		return this.zszt;
	}

	public String getZsztString() {
		return CacheUtils.get().getDictionary("ZSZT", this.getZszt());
	}

	public void setZszt(String value) {
		this.zszt = value;
	}

	public String getLscsyy() {
		return this.lscsyy;
	}

	public String getLscsyyString() {
		return CacheUtils.get().getDictionary("LSCS", this.getLscsyy());
	}


	public void setLscsyy(String value) {
		this.lscsyy = value;
	}

	public Date getLscssj() {
		return this.lscssj;
	}

	public void setLscssj(Date value) {
		this.lscssj = value;
	}

	public Date getLscshssj() {
		return this.lscshssj;
	}

	public void setLscshssj(Date value) {
		this.lscshssj = value;
	}

	public String getCrjbj() {
		return this.crjbj;
	}

	public String getCrjbjString() {
		return CacheUtils.get().getDictionary("CJBJ", this.getCrjbj());
	}

	public void setCrjbj(String value) {
		this.crjbj = value;
	}

	public String getRkbhgbj() {
		return this.rkbhgbj;
	}

	public String getRkbhgbjString() {
		return CacheUtils.get().getDictionary("SHFO", this.getRkbhgbj());
	}

	public void setRkbhgbj(String value) {
		this.rkbhgbj = value;
	}

	public String getRkbhgyy() {
		return this.rkbhgyy;
	}

	public void setRkbhgyy(String value) {
		this.rkbhgyy = value;
	}

	public String getLrsfjs() {
		return this.lrsfjs;
	}

	public String getLrsfjsString() {
		return CacheUtils.get().getDictionary("SHFO", this.getLrsfjs());
	}

	public void setLrsfjs(String value) {
		this.lrsfjs = value;
	}

	public String getZyryxgqk() {
		return this.zyryxgqk;
	}

	public String getZyryxgqkString() {
		return CacheUtils.get().getDictionary("ZYRYXGQK", this.getZyryxgqk());
	}

	public void setZyryxgqk(String value) {
		this.zyryxgqk = value;
	}

	public String getEmlx() {
		return this.emlx;
	}

	public String getEmlxString() {
		return CacheUtils.get().getDictionary("EMLX", this.getEmlx());
	}

	public void setEmlx(String value) {
		this.emlx = value;
	}

	public String getRsjclx() {
		return this.rsjclx;
	}

	public String getRsjclxString() {
		return CacheUtils.get().getDictionary("RSJCLX", this.getRsjclx());
	}

	public void setRsjclx(String value) {
		this.rsjclx = value;
	}

	public String getJj() {
		return this.jj;
	}

	public String getJjString() {
		return CacheUtils.get().getDictionary("JJ", this.getJj());
	}

	public void setJj(String value) {
		this.jj = value;
	}

	public String getJb() {
		return this.jb;
	}

	public String getJbString() {
		return CacheUtils.get().getDictionary("JB", this.getJb());
	}

	public void setJb(String value) {
		this.jb = value;
	}

	public String getLdf() {
		return this.ldf;
	}

	public String getLdfString() {
		return CacheUtils.get().getDictionary("LDF", this.getLdf());
	}

	public void setLdf(String value) {
		this.ldf = value;
	}

	public String getYgry() {
		return this.ygry;
	}

	public String getYgryString() {
		return CacheUtils.get().getDictionary("YGRY", this.getYgry());
	}

	public void setYgry(String value) {
		this.ygry = value;
	}

	public String getZdry() {
		return this.zdry;
	}

	public String getZdryString() {
		return CacheUtils.get().getDictionary("ZDRY", this.getZdry());
	}

	public void setZdry(String value) {
		this.zdry = value;
	}

	public String getZyaf() {
		return this.zyaf;
	}

	public String getZyafString() {
		return CacheUtils.get().getDictionary("ZYAF", this.getZyaf());
	}

	public void setZyaf(String value) {
		this.zyaf = value;
	}

	public String getSlaj() {
		return this.slaj;
	}

	public String getSlajString() {
		return CacheUtils.get().getDictionary("SHFO", this.getSlaj());
	}

	public void setSlaj(String value) {
		this.slaj = value;
	}

	public Date getSykzrq() {
		return this.sykzrq;
	}

	public void setSykzrq(Date value) {
		this.sykzrq = value;
	}

	public String getIsjdwm() {
		return this.isjdwm;
	}

	public String getIsjdwmString() {
		return CacheUtils.get().getDictionary("SHFO", this.getIsjdwm());
	}

	public void setIsjdwm(String value) {
		this.isjdwm = value;
	}

	public String getCypjfz() {
		return this.cypjfz;
	}

	public void setCypjfz(String value) {
		this.cypjfz = value;
	}

	public String getSfypj() {
		return this.sfypj;
	}

	public String getSfypjString() {
		return CacheUtils.get().getDictionary("SHFO", this.getSfypj());
	}

	public void setSfypj(String value) {
		this.sfypj = value;
	}

	public String getSfcxcy() {
		return this.sfcxcy;
	}

	public String getSfcxcyString() {
		return CacheUtils.get().getDictionary("SHFO", this.getSfcxcy());
	}

	public void setSfcxcy(String value) {
		this.sfcxcy = value;
	}

	public String getSffy() {
		return this.sffy;
	}

	public String getSffyString() {
		return CacheUtils.get().getDictionary("SHFO", this.getSffy());
	}

	public void setSffy(String value) {
		this.sffy = value;
	}

	public Date getFyksrq() {
		return fyksrq;
	}

	public void setFyksrq(Date fyksrq) {
		this.fyksrq = fyksrq;
	}

	public Date getFyjsrq() {
		return fyjsrq;
	}

	public void setFyjsrq(Date fyjsrq) {
		this.fyjsrq = fyjsrq;
	}

	public String getSfrz() {
		return this.sfrz;
	}

	public String getSfrzString() {
		return CacheUtils.get().getDictionary("SHFO", this.getSfrz());
	}

	public void setSfrz(String value) {
		this.sfrz = value;
	}

	public String getSfyxjslx() {
		return this.sfyxjslx;
	}

	public String getSfyxjslxString() {
		return CacheUtils.get().getDictionary("SHFO", this.getSfyxjslx());
	}

	public void setSfyxjslx(String value) {
		this.sfyxjslx = value;
	}

	public String getDagdbj() {
		return this.dagdbj;
	}

	public void setDagdbj(String value) {
		this.dagdbj = value;
	}

	public String getClsypzr() {
		return this.clsypzr;
	}

	public void setClsypzr(String value) {
		this.clsypzr = value;
	}

	public String getRyzwbj() {
		return this.ryzwbj;
	}

	public String getRyzwbjString() {
		return CacheUtils.get().getDictionary("RYZWBJ", this.getRyzwbj());
	}

	public void setRyzwbj(String value) {
		this.ryzwbj = value;
	}

	public String getWcnbj() {
		return this.wcnbj;
	}

	public String getWcnbjString() {
		return CacheUtils.get().getDictionary("SHFO", this.getWcnbj());
	}

	public void setWcnbj(String value) {
		this.wcnbj = value;
	}

	public String getGrjl() {
		return this.grjl;
	}

	public void setGrjl(String value) {
		this.grjl = value;
	}

	public String getLastfxpgfz() {
		return this.lastfxpgfz;
	}

	public void setLastfxpgfz(String value) {
		this.lastfxpgfz = value;
	}

	public String getState() {
		return this.state;
	}

	public String getStateString() {
		return CacheUtils.get().getDictionary("STATE", this.getState());
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

	public String getJsly() {
		return this.jsly;
	}

	public void setJsly(String value) {
		this.jsly = value;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date value) {
		this.createtime = value;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String value) {
		this.updator = value;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date value) {
		this.updatetime = value;
	}

	public String getWpsflq() {
		return wpsflq;
	}

	public String getWpsflqString() {
		return CacheUtils.get().getDictionary("SHFO", this.getWpsflq());
	}

	public void setWpsflq(String wpsflq) {
		this.wpsflq = wpsflq;
	}

	public String getYwlcid() {
		return ywlcid;
	}

	public void setYwlcid(String ywlcid) {
		this.ywlcid = ywlcid;
	}

	public String getSfwxjc() {
		return sfwxjc;
	}

	public String getSfwxjcString() {
		return CacheUtils.get().getDictionary("SHFO", this.getSfwxjc());
	}

	public void setSfwxjc(String sfwxjc) {
		this.sfwxjc = sfwxjc;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getJygzzlpg() {
		return jygzzlpg;
	}

	public String getJygzzlpgString() {
		return CacheUtils.get().getDictionary("SHFO", this.getJygzzlpg());
	}

	public void setJygzzlpg(String jygzzlpg) {
		this.jygzzlpg = jygzzlpg;
	}

	public String getXmpyszm() {
		return xmpyszm;
	}

	public void setXmpyszm(String xmpyszm) {
		this.xmpyszm = xmpyszm;
	}

	public String getZxf() {
		return zxf;
	}

	public String getZxfString() {
		return CacheUtils.get().getDictionary("ZXF", this.getZxf());
	}

	public void setZxf(String zxf) {
		this.zxf = zxf;
	}

	public String getWxdj() {
		return wxdj;
	}

	public String getWxdjString() {
		return CacheUtils.get().getDictionary("FXDJ", this.getWxdj());
	}

	public void setWxdj(String wxdj) {
		this.wxdj = wxdj;
	}

	public String getCzdh() {
		return czdh;
	}

	public void setCzdh(String czdh) {
		this.czdh = czdh;
	}

	public Date getScqsrq() {
		return scqsrq;
	}

	public void setScqsrq(Date scqsrq) {
		this.scqsrq = scqsrq;
	}

	public Date getYsfyrq() {
		return ysfyrq;
	}

	public void setYsfyrq(Date ysfyrq) {
		this.ysfyrq = ysfyrq;
	}

	public String getJcqk() {
		return jcqk;
	}

	public void setJcqk(String jcqk) {
		this.jcqk = jcqk;
	}

	public String getYkss() {
		return ykss;
	}

	public void setYkss(String ykss) {
		this.ykss = ykss;
	}

	public String getLsyy() {
		return lsyy;
	}

	public String getLsyyString() {
		return CacheUtils.get().getDictionary("LSYY", this.getLsyy());
	}

	public void setLsyy(String lsyy) {
		this.lsyy = lsyy;
	}

	public String getDrjsr() {
		return drjsr;
	}

	public void setDrjsr(String drjsr) {
		this.drjsr = drjsr;
	}

	public Date getDrjssj() {
		return drjssj;
	}

	public void setDrjssj(Date drjssj) {
		this.drjssj = drjssj;
	}

	public String getFlws() {
		return flws;
	}

	public void setFlws(String flws) {
		this.flws = flws;
	}

	public String getSyrdh() {
		return syrdh;
	}

	public void setSyrdh(String syrdh) {
		this.syrdh = syrdh;
	}

	public String getTabh() {
		return tabh;
	}

	public void setTabh(String tabh) {
		this.tabh = tabh;
	}

	public String getZa() {
		return za;
	}

	public void setZa(String za) {
		this.za = za;
	}

	public String getYfh() {
		return yfh;
	}

	public void setYfh(String yfh) {
		this.yfh = yfh;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getZwjb() {
		return zwjb;
	}

	public void setZwjb(String zwjb) {
		this.zwjb = zwjb;
	}

	public Short getTz() {
		return tz;
	}

	public void setTz(Short tz) {
		this.tz = tz;
	}


	public String getCwh() {
		return cwh;
	}

	public void setCwh(String cwh) {
		this.cwh = cwh;
	}

	public String getTbtsbj() {
		return tbtsbj;
	}

	public void setTbtsbj(String tbtsbj) {
		this.tbtsbj = tbtsbj;
	}

	public Date getCljgrq() {
		return cljgrq;
	}

	public void setCljgrq(Date cljgrq) {
		this.cljgrq = cljgrq;
	}

	public String getPjzm() {
		return pjzm;
	}

	public void setPjzm(String pjzm) {
		this.pjzm = pjzm;
	}

	public Date getZxtzssdrq() {
		return zxtzssdrq;
	}

	public void setZxtzssdrq(Date zxtzssdrq) {
		this.zxtzssdrq = zxtzssdrq;
	}

	public BigDecimal getJe() {
		return je;
	}

	public void setJe(BigDecimal je) {
		this.je = je;
	}

	public String getJbr() {
		return jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public Date getJbrq() {
		return jbrq;
	}

	public void setJbrq(Date jbrq) {
		this.jbrq = jbrq;
	}

	public String getSfswry() {
		return sfswry;
	}

	public String getSfswryString() {
		return CacheUtils.get().getDictionary("SHFO", this.getSfswry());
	}

	public void setSfswry(String sfswry) {
		this.sfswry = sfswry;
	}

	public String getSwpgzb() {
		return swpgzb;
	}

	public void setSwpgzb(String swpgzb) {
		this.swpgzb = swpgzb;
	}

	public String getSwpgr() {
		return swpgr;
	}

	public void setSwpgr(String swpgr) {
		this.swpgr = swpgr;
	}

	public Date getSwpgsj() {
		return swpgsj;
	}

	public void setSwpgsj(Date swpgsj) {
		this.swpgsj = swpgsj;
	}


	public String getJsycbz() {
		return jsycbz;
	}

	public String getJsycbzString() {
		return CacheUtils.get().getDictionary("SHFO", this.getJsycbz());
	}

	public void setJsycbz(String jsycbz) {
		this.jsycbz = jsycbz;
	}
}
