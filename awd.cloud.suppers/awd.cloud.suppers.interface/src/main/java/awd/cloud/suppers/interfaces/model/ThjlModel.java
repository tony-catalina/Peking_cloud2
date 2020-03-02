package awd.cloud.suppers.interfaces.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ThjlModel {

    public static final String TABLE_ALIAS = "Thjy";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_RYBH = "人员编号";
    public static final String ALIAS_TBR = "填表人";
    public static final String ALIAS_TBRQ = "填表日期";
    public static final String ALIAS_THYY = "谈话原因(THYY)";
    public static final String ALIAS_THKSSJ = "谈话开始时间";
    public static final String ALIAS_THJSSJ = "谈话结束时间";
    public static final String ALIAS_ZRGJ = "责任管教";
    public static final String ALIAS_THR = "谈话人";
    public static final String ALIAS_LYURL = "录音URL";
    public static final String ALIAS_FZMJ = "负责民警";
    public static final String ALIAS_THNR = "谈话内容";
    public static final String ALIAS_JYNR = "教育内容";
    public static final String ALIAS_BZ = "备注";
    public static final String ALIAS_STATE = "删除状态(YWSTATE)";
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
    private String thyy;
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
    private Date thkssj;
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
    private Date thjssj;
    private String zrgj;
    private String thr;
    private String lyurl;
    private String fzmj;
    private String thnr;
    private String jynr;
    private String bz;
    private String state;
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
    private String thyyString;
    private String stateString;
    private String scbzString;

    public ThjlModel() {
    }

    public ThjlModel(String id) {
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

    public String getThyy() {
        return this.thyy;
    }

    public void setThyy(String value) {
        this.thyy = value;
    }

    public Date getThkssj() {
        return this.thkssj;
    }

    public void setThkssj(Date value) {
        this.thkssj = value;
    }

    public Date getThjssj() {
        return this.thjssj;
    }

    public void setThjssj(Date value) {
        this.thjssj = value;
    }

    public String getZrgj() {
        return this.zrgj;
    }

    public void setZrgj(String value) {
        this.zrgj = value;
    }

    public String getThr() {
        return this.thr;
    }

    public void setThr(String value) {
        this.thr = value;
    }

    public String getLyurl() {
        return this.lyurl;
    }

    public void setLyurl(String value) {
        this.lyurl = value;
    }

    public String getFzmj() {
        return this.fzmj;
    }

    public void setFzmj(String value) {
        this.fzmj = value;
    }

    public String getThnr() {
        return this.thnr;
    }

    public void setThnr(String value) {
        this.thnr = value;
    }

    public String getJynr() {
        return this.jynr;
    }

    public void setJynr(String value) {
        this.jynr = value;
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

    public void setState(String value) {
        this.state = value;
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

    public String getThyyString() {
        return this.thyyString;
    }

    public void setThyyString(String value) {
        this.thyyString = value;
    }

    public String getStateString() {
        return this.stateString;
    }

    public void setStateString(String value) {
        this.stateString = value;
    }

    public String getScbzString() {
        return this.scbzString;
    }

    public void setScbzString(String value) {
        this.scbzString = value;
    }
}
