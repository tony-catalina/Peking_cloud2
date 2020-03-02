package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * Author：YaoBen
 * Date：2019-11-08 14:55
 * Description：<描述>
 */
//大屏看守所在押人数实体类
public class DsjPTModel implements Model {

    private String id;
    private String state;
    private String xb;
    private String wxdj;
    private String jb; //禁闭
    private String jj;  //械具
    private String jsbh; //监所编号得到监所名字
    private String crjbj;//出入监标记(CJBJ) 家属会见 律师会见 正在提审
    private String swjy;//所外就医  1是  0否
    private String csjylx;//出所就医类型 1门诊  2住院
    private String jzyy;//就诊医院
    private String code;//案由分析 根据code值得到案由类型


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date zdrq;//诊断日期

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date rsrq;  //入所日期

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date cssj;  //出所日期

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private  java.util.Date jyrq;//羁押日期


    @Override
    public String toString() {
        return "DsjPTModel{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", xb='" + xb + '\'' +
                ", wxdj='" + wxdj + '\'' +
                ", jb='" + jb + '\'' +
                ", jj='" + jj + '\'' +
                ", jsbh='" + jsbh + '\'' +
                ", crjbj='" + crjbj + '\'' +
                ", swjy='" + swjy + '\'' +
                ", csjylx='" + csjylx + '\'' +
                ", jzyy='" + jzyy + '\'' +
                ", code='" + code + '\'' +
                ", zdrq=" + zdrq +
                ", rsrq=" + rsrq +
                ", cssj=" + cssj +
                ", jyrq=" + jyrq +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getWxdj() {
        return wxdj;
    }

    public void setWxdj(String wxdj) {
        this.wxdj = wxdj;
    }

    public String getJb() {
        return jb;
    }

    public void setJb(String jb) {
        this.jb = jb;
    }

    public String getJj() {
        return jj;
    }

    public void setJj(String jj) {
        this.jj = jj;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getCrjbj() {
        return crjbj;
    }

    public void setCrjbj(String crjbj) {
        this.crjbj = crjbj;
    }

    public String getSwjy() {
        return swjy;
    }

    public void setSwjy(String swjy) {
        this.swjy = swjy;
    }

    public String getCsjylx() {
        return csjylx;
    }

    public void setCsjylx(String csjylx) {
        this.csjylx = csjylx;
    }

    public String getJzyy() {
        return jzyy;
    }

    public void setJzyy(String jzyy) {
        this.jzyy = jzyy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getZdrq() {
        return zdrq;
    }

    public void setZdrq(Date zdrq) {
        this.zdrq = zdrq;
    }

    public Date getRsrq() {
        return rsrq;
    }

    public void setRsrq(Date rsrq) {
        this.rsrq = rsrq;
    }

    public Date getCssj() {
        return cssj;
    }

    public void setCssj(Date cssj) {
        this.cssj = cssj;
    }

    public Date getJyrq() {
        return jyrq;
    }

    public void setJyrq(Date jyrq) {
        this.jyrq = jyrq;
    }

    public DsjPTModel() {
    }

    public DsjPTModel(String id, String state, String xb, String wxdj, String jb, String jj, String jsbh, String crjbj, String swjy, String csjylx, String jzyy, String code, Date zdrq, Date rsrq, Date cssj, Date jyrq) {
        this.id = id;
        this.state = state;
        this.xb = xb;
        this.wxdj = wxdj;
        this.jb = jb;
        this.jj = jj;
        this.jsbh = jsbh;
        this.crjbj = crjbj;
        this.swjy = swjy;
        this.csjylx = csjylx;
        this.jzyy = jzyy;
        this.code = code;
        this.zdrq = zdrq;
        this.rsrq = rsrq;
        this.cssj = cssj;
        this.jyrq = jyrq;
    }
}
