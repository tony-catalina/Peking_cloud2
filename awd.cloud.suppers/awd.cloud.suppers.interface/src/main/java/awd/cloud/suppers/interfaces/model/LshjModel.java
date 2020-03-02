package awd.cloud.suppers.interfaces.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.esotericsoftware.kryo.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
public class LshjModel {

    public static final String TABLE_ALIAS = "Lshj";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_RYBH = "人员编号";
    public static final String ALIAS_TBR = "填表人";
    public static final String ALIAS_TBRQ = "填表日期";
    public static final String ALIAS_LSXM = "律师/代理人姓名";
    public static final String ALIAS_ZJLX = "证件类型(ZJLX)";
    public static final String ALIAS_ZJH = "律师/代理人证件号";
    public static final String ALIAS_DW = "律师/代理人单位(LSDW)";
    public static final String ALIAS_LXFS = "律师/代理人联系方式";
    public static final String ALIAS_PZDW = "批准单位";
    public static final String ALIAS_PZDWLXFS = "批准单位联系方式";
    public static final String ALIAS_LSZYZM = "律师执业证明是否到期(SHFO)";
    public static final String ALIAS_LSZYZMYXQ = "律师执业证明有效期";
    public static final String ALIAS_LSSWSBH = "律师事务所介绍信编号";
    public static final String ALIAS_SWSZJHM = "事务所证件号码";
    public static final String ALIAS_SWSDZ = "事务所地址";
    public static final String ALIAS_WTSLX = "委托书类型(WTSLX) 1 委托书，2 授权委托书";
    public static final String ALIAS_WTRXM = "委托人姓名";
    public static final String ALIAS_RS = "人数";
    public static final String ALIAS_HJYXQ = "会见有效期";
    public static final String ALIAS_QTRY = "其他人员";
    public static final String ALIAS_ZBMJ = "值班民警";
    public static final String ALIAS_DJSJ = "登记时间";
    public static final String ALIAS_HJDD = "会见地点";
    public static final String ALIAS_APHJSJ = "安排会见时间";
    public static final String ALIAS_HJSJ = "会见开始时间";
    public static final String ALIAS_JSSJ = "结束结束时间";
    public static final String ALIAS_YWLCID = "业务流程ID";
    public static final String ALIAS_TASKID = "业务进程ID";
    public static final String ALIAS_STATE = "删除状态(YWSTATE)";
    public static final String ALIAS_SJZLJSBZ = "数据质量—及时标记(JSBZ)";
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
    private String tbr;
    @JSONField(
            format = "yyyy-MM-dd"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date tbrq;
    private String lsxm;
    private String zjlx;
    private String zjh;
    private String dw;
    private String lxfs;
    private String pzdw;
    private String pzdwlxfs;
    private String lszyzm;
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
    private Date lszyzmyxq;
    private String lsswsbh;
    private String swszjhm;
    private String swsdz;
    private String wtslx;
    private String wtrxm;
    private String rs;
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
    private Date hjyxq;
    private String qtry;
    private String zbmj;
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
    private String hjdd;
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
    private Date aphjsj;
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
    private Date hjsj;
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
    private String zjlxString;
    private String dwString;
    private String lszyzmString;
    private String wtslxString;
    private String stateString;
    private String sjzljsbzString;
    private String scbzString;

    public LshjModel() {
    }

    public LshjModel(String id) {
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

    public String getTbr() {
        return this.tbr;
    }

    public void setTbr(String value) {
        this.tbr = value;
    }

    public Date getTbrq() {
        return this.tbrq;
    }

    public void setTbrq(Date value) {
        this.tbrq = value;
    }

    public String getLsxm() {
        return this.lsxm;
    }

    public void setLsxm(String value) {
        this.lsxm = value;
    }

    public String getZjlx() {
        return this.zjlx;
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

    public String getDw() {
        return this.dw;
    }

    public void setDw(String value) {
        this.dw = value;
    }

    public String getLxfs() {
        return this.lxfs;
    }

    public void setLxfs(String value) {
        this.lxfs = value;
    }

    public String getPzdw() {
        return this.pzdw;
    }

    public void setPzdw(String value) {
        this.pzdw = value;
    }

    public String getPzdwlxfs() {
        return this.pzdwlxfs;
    }

    public void setPzdwlxfs(String value) {
        this.pzdwlxfs = value;
    }

    public String getLszyzm() {
        return this.lszyzm;
    }

    public void setLszyzm(String value) {
        this.lszyzm = value;
    }

    public Date getLszyzmyxq() {
        return this.lszyzmyxq;
    }

    public void setLszyzmyxq(Date value) {
        this.lszyzmyxq = value;
    }

    public String getLsswsbh() {
        return this.lsswsbh;
    }

    public void setLsswsbh(String value) {
        this.lsswsbh = value;
    }

    public String getSwszjhm() {
        return this.swszjhm;
    }

    public void setSwszjhm(String value) {
        this.swszjhm = value;
    }

    public String getSwsdz() {
        return this.swsdz;
    }

    public void setSwsdz(String value) {
        this.swsdz = value;
    }

    public String getWtslx() {
        return this.wtslx;
    }

    public void setWtslx(String value) {
        this.wtslx = value;
    }

    public String getWtrxm() {
        return this.wtrxm;
    }

    public void setWtrxm(String value) {
        this.wtrxm = value;
    }

    public String getRs() {
        return this.rs;
    }

    public void setRs(String value) {
        this.rs = value;
    }

    public Date getHjyxq() {
        return this.hjyxq;
    }

    public void setHjyxq(Date value) {
        this.hjyxq = value;
    }

    public String getQtry() {
        return this.qtry;
    }

    public void setQtry(String value) {
        this.qtry = value;
    }

    public String getZbmj() {
        return this.zbmj;
    }

    public void setZbmj(String value) {
        this.zbmj = value;
    }

    public Date getDjsj() {
        return this.djsj;
    }

    public void setDjsj(Date value) {
        this.djsj = value;
    }

    public String getHjdd() {
        return this.hjdd;
    }

    public void setHjdd(String value) {
        this.hjdd = value;
    }

    public Date getAphjsj() {
        return this.aphjsj;
    }

    public void setAphjsj(Date value) {
        this.aphjsj = value;
    }

    public Date getHjsj() {
        return this.hjsj;
    }

    public void setHjsj(Date value) {
        this.hjsj = value;
    }

    public Date getJssj() {
        return this.jssj;
    }

    public void setJssj(Date value) {
        this.jssj = value;
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

    public String getZjlxString() {
        return this.zjlxString;
    }

    public void setZjlxString(String value) {
        this.zjlxString = value;
    }

    public String getDwString() {
        return this.dwString;
    }

    public void setDwString(String value) {
        this.dwString = value;
    }

    public String getLszyzmString() {
        return this.lszyzmString;
    }

    public void setLszyzmString(String value) {
        this.lszyzmString = value;
    }

    public String getWtslxString() {
        return this.wtslxString;
    }

    public void setWtslxString(String value) {
        this.wtslxString = value;
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
