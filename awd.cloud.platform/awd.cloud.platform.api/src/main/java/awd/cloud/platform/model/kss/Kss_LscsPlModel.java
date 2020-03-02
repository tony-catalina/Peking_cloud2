package awd.cloud.platform.model.kss;

import awd.cloud.platform.model.Model;
import awd.cloud.platform.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Kss_LscsPlModel implements Model {
    private String jbxxid;
    private String ldspid;
    private String taskid;
    private String sqyy;
    private String xm;
    private String bm;
    private String gyqx;
    private String jsh;
    private String rybh;
    private String jsbh;
    private String rsrq;
    private String snbh;
    private String cswsh;
    private String pzdw;
    private String pzr;
    private String csyy;
    private String cssj;
    private String csqx;
    private String ywlcid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.util.Date sqsj;

    public Date getSqsj() {
        return sqsj;
    }

    public void setSqsj(Date sqsj) {
        this.sqsj = sqsj;
    }

    public String getYwlcid() {
        return ywlcid;
    }

    public void setYwlcid(String ywlcid) {
        this.ywlcid = ywlcid;
    }

    public String getLdspid() {
        return ldspid;
    }

    public void setLdspid(String ldspid) {
        this.ldspid = ldspid;
    }

    public String getSqyy() {
        return sqyy;
    }

    public void setSqyy(String sqyy) {
        this.sqyy = sqyy;
    }

    public String getCswsh() {
        return cswsh;
    }
    public String getPzdw() {
        return pzdw;
    }
    public String getPzr() {
        return pzr;
    }
    public String getCsyy() {
        return csyy;
    }
    public String getCssj() {
        return cssj;
    }
    public String getCsqx() {
        return csqx;
    }
    public void setCswsh(String cswsh) {
        this.cswsh = cswsh;
    }
    public void setPzdw(String pzdw) {
        this.pzdw = pzdw;
    }
    public void setPzr(String pzr) {
        this.pzr = pzr;
    }
    public void setCsyy(String csyy) {
        this.csyy = csyy;
    }
    public void setCssj(String cssj) {
        this.cssj = cssj;
    }
    public void setCsqx(String csqx) {
        this.csqx = csqx;
    }
    public String getSnbh() {
        return snbh;
    }
    public void setSnbh(String snbh) {
        this.snbh = snbh;
    }
    public String getRsrq() {
        return rsrq;
    }
    public void setRsrq(String rsrq) {
        this.rsrq = rsrq;
    }
    public String getJbxxid() {
        return jbxxid;
    }
    public String getTaskid() {
        return taskid;
    }
    public void setJbxxid(String jbxxid) {
        this.jbxxid = jbxxid;
    }
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }
    public String getXm() {
        return xm;
    }
    public String getBm() {
        return bm;
    }
    public String getGyqx() {
        return gyqx;
    }
    public String getJsh() {
        return jsh;
    }
    public String getRybh() {
        return rybh;
    }
    public String getJsbh() {
        return jsbh;
    }
    public void setXm(String xm) {
        this.xm = xm;
    }
    public void setBm(String bm) {
        this.bm = bm;
    }
    public void setGyqx(String gxqx) {
        this.gyqx = gxqx;
    }
    public void setJsh(String jsh) {
        this.jsh = jsh;
    }
    public void setRybh(String rybh) {
        this.rybh = rybh;
    }
    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

}
