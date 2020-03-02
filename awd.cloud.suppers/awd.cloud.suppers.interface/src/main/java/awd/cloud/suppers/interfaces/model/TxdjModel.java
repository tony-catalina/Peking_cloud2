package awd.cloud.suppers.interfaces.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
public class TxdjModel {

    public static final String TABLE_ALIAS = "Tsdj";
    public static final String ALIAS_ID = "id";
    public static final String ALIAS_JSBH = "监室编号";
    public static final String ALIAS_RYBH = "人员编号";
    public static final String ALIAS_BLLX = "办理类型(TSBLLX)";
    public static final String ALIAS_DW = "提审单位(DWLX)";
    public static final String ALIAS_ZJLX = "证件类型(TSZJLX)";
    public static final String ALIAS_KSSJ = "开始时间";
    public static final String ALIAS_JSSJ = "结束时间";
    public static final String ALIAS_TSZBR = "提审值班人";
    public static final String ALIAS_JSR = "收监民警";
    public static final String ALIAS_BZ = "备注";
    public static final String ALIAS_DCMJ = "带出民警";
    public static final String ALIAS_TSS = "提审室";
    public static final String ALIAS_BAJGLXFS = "办案机关联系方式";
    public static final String ALIAS_BAJG = "办案机关";
    public static final String ALIAS_BARXM1 = "办案人姓名1";
    public static final String ALIAS_GZZJHM1 = "工作证件号码1";
    public static final String ALIAS_LXFS1 = "联系方式1";
    public static final String ALIAS_BARXM2 = "办案人姓名2";
    public static final String ALIAS_GZZJHM2 = "工作证件号码2";
    public static final String ALIAS_LXFS2 = "联系方式2";
    public static final String ALIAS_XWAY = "讯问案由(JLSAJLB)";
    public static final String ALIAS_JSXBH = "介绍信编号";
    public static final String ALIAS_DJR = "登记人";
    public static final String ALIAS_DJSJ = "登记时间";
    public static final String ALIAS_LDPSBZ = "领导批示标志(PSBZ)";
    public static final String ALIAS_LDYJ = "领导意见";
    public static final String ALIAS_LDXM = "领导姓名";
    public static final String ALIAS_LDPSSJ = "领导批示时间";
    public static final String ALIAS_YWLCID = "业务流程ID";
    public static final String ALIAS_TASKID = "业务进程ID";
    public static final String ALIAS_STATE = "删除状态(YWSTATE)";
    public static final String ALIAS_SJZLJSBZ = "及时标志(JSBZ)";
    public static final String ALIAS_SCBZ = "上传标志(SHFO)";
    public static final String ALIAS_OPERATOR = "操作人";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";

    @ApiModelProperty(value="id",required=true)
    private String id;

    @ApiModelProperty(value="监所编号",required=true)
    private String jsbh;

    @ApiModelProperty(value="人员编号",required=true)
    private String rybh;
    private String bllx;
    private String dw;
    private String zjlx;
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date kssj;
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date jssj;
    private String tszbr;
    private String jsr;
    private String bz;
    private String dcmj;
    private String tss;
    private String bajglxfs;
    private String bajg;
    private String barxm1;
    private String gzzjhm1;
    private String lxfs1;
    private String barxm2;
    private String gzzjhm2;
    private String lxfs2;
    private String xway;
    private String jsxbh;
    private String djr;
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date djsj;
    private String ldpsbz;
    private String ldyj;
    private String ldxm;
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date ldpssj;
    private String ywlcid;
    private String taskid;
    private String state;
    private String sjzljsbz;
    private String scbz;
    private String operator;

    @ApiModelProperty(value="创建人",required=true)
    private String creator;
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty(value="创建时间",required=true)
    private Date createtime;
    private String updator;
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updatetime;
    private String jsbhString;
    private String bllxString;
    private String dwString;
    private String zjlxString;
    private String xwayString;
    private String ldpsbzString;
    private String stateString;
    private String sjzljsbzString;
    private String scbzString;

    public TxdjModel() {
    }

    public TxdjModel(String id) {
        this.id = id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getId() {
        return this.id;
    }

    public String getJsbh() {
        return this.jsbh;
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

    public String getBllx() {
        return this.bllx;
    }

    public void setBllx(String value) {
        this.bllx = value;
    }

    public String getDw() {
        return this.dw;
    }

    public void setDw(String value) {
        this.dw = value;
    }

    public String getZjlx() {
        return this.zjlx;
    }

    public void setZjlx(String value) {
        this.zjlx = value;
    }

    public Date getKssj() {
        return this.kssj;
    }

    public void setKssj(Date value) {
        this.kssj = value;
    }

    public Date getJssj() {
        return this.jssj;
    }

    public void setJssj(Date value) {
        this.jssj = value;
    }

    public String getTszbr() {
        return this.tszbr;
    }

    public void setTszbr(String value) {
        this.tszbr = value;
    }

    public String getJsr() {
        return this.jsr;
    }

    public void setJsr(String value) {
        this.jsr = value;
    }

    public String getBz() {
        return this.bz;
    }

    public void setBz(String value) {
        this.bz = value;
    }

    public String getDcmj() {
        return this.dcmj;
    }

    public void setDcmj(String value) {
        this.dcmj = value;
    }

    public String getTss() {
        return this.tss;
    }

    public void setTss(String value) {
        this.tss = value;
    }

    public String getBajglxfs() {
        return this.bajglxfs;
    }

    public void setBajglxfs(String value) {
        this.bajglxfs = value;
    }

    public String getBajg() {
        return this.bajg;
    }

    public void setBajg(String value) {
        this.bajg = value;
    }

    public String getBarxm1() {
        return this.barxm1;
    }

    public void setBarxm1(String value) {
        this.barxm1 = value;
    }

    public String getGzzjhm1() {
        return this.gzzjhm1;
    }

    public void setGzzjhm1(String value) {
        this.gzzjhm1 = value;
    }

    public String getLxfs1() {
        return this.lxfs1;
    }

    public void setLxfs1(String value) {
        this.lxfs1 = value;
    }

    public String getBarxm2() {
        return this.barxm2;
    }

    public void setBarxm2(String value) {
        this.barxm2 = value;
    }

    public String getGzzjhm2() {
        return this.gzzjhm2;
    }

    public void setGzzjhm2(String value) {
        this.gzzjhm2 = value;
    }

    public String getLxfs2() {
        return this.lxfs2;
    }

    public void setLxfs2(String value) {
        this.lxfs2 = value;
    }

    public String getXway() {
        return this.xway;
    }

    public void setXway(String value) {
        this.xway = value;
    }

    public String getJsxbh() {
        return this.jsxbh;
    }

    public void setJsxbh(String value) {
        this.jsxbh = value;
    }

    public String getDjr() {
        return this.djr;
    }

    public void setDjr(String value) {
        this.djr = value;
    }

    public Date getDjsj() {
        return this.djsj;
    }

    public void setDjsj(Date value) {
        this.djsj = value;
    }

    public String getLdpsbz() {
        return this.ldpsbz;
    }

    public void setLdpsbz(String value) {
        this.ldpsbz = value;
    }

    public String getLdyj() {
        return this.ldyj;
    }

    public void setLdyj(String value) {
        this.ldyj = value;
    }

    public String getLdxm() {
        return this.ldxm;
    }

    public void setLdxm(String value) {
        this.ldxm = value;
    }

    public Date getLdpssj() {
        return this.ldpssj;
    }

    public void setLdpssj(Date value) {
        this.ldpssj = value;
    }

    public String getYwlcid() {
        return this.ywlcid;
    }

    public void setYwlcid(String value) {
        this.ywlcid = value;
    }

    public String getTaskid() {
        return this.taskid;
    }

    public void setTaskid(String value) {
        this.taskid = value;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getSjzljsbz() {
        return this.sjzljsbz;
    }

    public void setSjzljsbz(String value) {
        this.sjzljsbz = value;
    }

    public String getScbz() {
        return this.scbz;
    }

    public void setScbz(String value) {
        this.scbz = value;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String value) {
        this.operator = value;
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

    public String getJsbhString() {
        return this.jsbhString;
    }

    public String getBllxString() {
        return this.bllxString;
    }

    public void setBllxString(String value) {
        this.bllxString = value;
    }

    public String getDwString() {
        return this.dwString;
    }

    public void setDwString(String value) {
        this.dwString = value;
    }

    public String getZjlxString() {
        return this.zjlxString;
    }

    public void setZjlxString(String value) {
        this.zjlxString = value;
    }

    public String getXwayString() {
        return this.xwayString;
    }

    public void setXwayString(String value) {
        this.xwayString = value;
    }

    public String getLdpsbzString() {
        return this.ldpsbzString;
    }

    public void setLdpsbzString(String value) {
        this.ldpsbzString = value;
    }

    public String getStateString() {
        return this.stateString;
    }

    public void setStateString(String value) {
        this.stateString = value;
    }

    public String getSjzljsbzString() {
        return this.sjzljsbzString;
    }

    public void setSjzljsbzString(String value) {
        this.sjzljsbzString = value;
    }

    public String getScbzString() {
        return this.scbzString;
    }

    public void setScbzString(String value) {
        this.scbzString = value;
    }
}
