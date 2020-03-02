/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class XjModel implements Model {

    //columns START

    private String id;


    private String jsbh;

    private String rybh;

    private String lsh;

    private String ly;

    private String xjmc;

    private Byte jjsl;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date kssj;

    private Short syts;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date jssj;

    private String bllx;

    private String blr;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date blrq;

    private String ysxm;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date yspssj;

    private String ysyj;

    private String zdzxm;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date zdzpssj;

    private String zdzyj;

    private String ldxm;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date ldpssj;

    private String ldyj;

    private String bz;

    private String psbz;

    private String ywlcid;

    private String state;

    private String creator;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;

    private String updator;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;

    private String pastable;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date starttime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date endtime;
    //columns END




    public XjModel() {
    }

    public XjModel(String id) {
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

    public String getLsh() {
        return this.lsh;
    }

    public void setLsh(String value) {
        this.lsh = value;
    }

    public String getLy() {
        return this.ly;
    }

    public void setLy(String value) {
        this.ly = value;
    }

    public String getXjmc() {
        return this.xjmc;
    }

    public void setXjmc(String value) {
        this.xjmc = value;
    }

    public Byte getJjsl() {
        return this.jjsl;
    }

    public void setJjsl(Byte value) {
        this.jjsl = value;
    }

    public java.util.Date getKssj() {
        return this.kssj;
    }

    public void setKssj(java.util.Date value) {
        this.kssj = value;
    }

    public Short getSyts() {
        return this.syts;
    }

    public void setSyts(Short value) {
        this.syts = value;
    }

    public java.util.Date getJssj() {
        return this.jssj;
    }

    public void setJssj(java.util.Date value) {
        this.jssj = value;
    }

    public String getBllx() {
        return this.bllx;
    }

    public void setBllx(String value) {
        this.bllx = value;
    }

    public String getBlr() {
        return this.blr;
    }

    public void setBlr(String value) {
        this.blr = value;
    }

    public java.util.Date getBlrq() {
        return this.blrq;
    }

    public void setBlrq(java.util.Date value) {
        this.blrq = value;
    }

    public String getYsxm() {
        return this.ysxm;
    }

    public void setYsxm(String value) {
        this.ysxm = value;
    }

    public java.util.Date getYspssj() {
        return this.yspssj;
    }

    public void setYspssj(java.util.Date value) {
        this.yspssj = value;
    }

    public String getYsyj() {
        return this.ysyj;
    }

    public void setYsyj(String value) {
        this.ysyj = value;
    }

    public String getZdzxm() {
        return this.zdzxm;
    }

    public void setZdzxm(String value) {
        this.zdzxm = value;
    }

    public java.util.Date getZdzpssj() {
        return this.zdzpssj;
    }

    public void setZdzpssj(java.util.Date value) {
        this.zdzpssj = value;
    }

    public String getZdzyj() {
        return this.zdzyj;
    }

    public void setZdzyj(String value) {
        this.zdzyj = value;
    }

    public String getLdxm() {
        return this.ldxm;
    }

    public void setLdxm(String value) {
        this.ldxm = value;
    }

    public java.util.Date getLdpssj() {
        return this.ldpssj;
    }

    public void setLdpssj(java.util.Date value) {
        this.ldpssj = value;
    }

    public String getLdyj() {
        return this.ldyj;
    }

    public void setLdyj(String value) {
        this.ldyj = value;
    }

    public String getBz() {
        return this.bz;
    }

    public void setBz(String value) {
        this.bz = value;
    }

    public String getPsbz() {
        return this.psbz;
    }

    public void setPsbz(String value) {
        this.psbz = value;
    }

    public String getYwlcid() {
        return this.ywlcid;
    }

    public void setYwlcid(String value) {
        this.ywlcid = value;
    }

    public String getState() {
        return this.state;
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

    public String getUpdator() {
        return this.updator;
    }

    public void setUpdator(String value) {
        this.updator = value;
    }

    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(java.util.Date value) {
        this.updatetime = value;
    }

    public String getPastable() {
        return this.pastable;
    }

    public void setPastable(String value) {
        this.pastable = value;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

}

