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

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DxsphjModel implements Model {


    //columns START

    private String id;


    private String jsbh;

    private String rybh;

    private String hjrid;

    private String hjly;

    private String jyqjbx;

    private String sfhcqk;

    private String hjlx;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date hjkssj;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date hjjssj;

    private String bhjrdd;

    private String bhjrbx;

    private String hjrdd;

    private String hjrbx;

    private String jsyy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date swhzksj;

    private String lxrid;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date lxsj;

    private String lxdh;

    private String lxqk;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date nhjsj;

    private String cjry;

    private String zdzxm;

    private String zdzyj;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date zdzpssj;

    private String ldxm;

    private String ldyj;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date ldpssj;

    private String ywlcid;

    private String psbz;

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
    //columns END


    public DxsphjModel() {
    }

    public DxsphjModel(String id) {
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

    public String getHjrid() {
        return this.hjrid;
    }

    public void setHjrid(String value) {
        this.hjrid = value;
    }

    public String getHjly() {
        return this.hjly;
    }

    public void setHjly(String value) {
        this.hjly = value;
    }

    public String getJyqjbx() {
        return this.jyqjbx;
    }

    public void setJyqjbx(String value) {
        this.jyqjbx = value;
    }

    public String getSfhcqk() {
        return this.sfhcqk;
    }

    public void setSfhcqk(String value) {
        this.sfhcqk = value;
    }

    public String getHjlx() {
        return this.hjlx;
    }

    public void setHjlx(String value) {
        this.hjlx = value;
    }

    public java.util.Date getHjkssj() {
        return this.hjkssj;
    }

    public void setHjkssj(java.util.Date value) {
        this.hjkssj = value;
    }


    public java.util.Date getHjjssj() {
        return this.hjjssj;
    }

    public void setHjjssj(java.util.Date value) {
        this.hjjssj = value;
    }

    public String getBhjrdd() {
        return this.bhjrdd;
    }

    public void setBhjrdd(String value) {
        this.bhjrdd = value;
    }

    public String getBhjrbx() {
        return this.bhjrbx;
    }

    public void setBhjrbx(String value) {
        this.bhjrbx = value;
    }

    public String getHjrdd() {
        return this.hjrdd;
    }

    public void setHjrdd(String value) {
        this.hjrdd = value;
    }

    public String getHjrbx() {
        return this.hjrbx;
    }

    public void setHjrbx(String value) {
        this.hjrbx = value;
    }

    public String getJsyy() {
        return this.jsyy;
    }

    public void setJsyy(String value) {
        this.jsyy = value;
    }

    public java.util.Date getSwhzksj() {
        return this.swhzksj;
    }

    public void setSwhzksj(java.util.Date value) {
        this.swhzksj = value;
    }

    public String getLxrid() {
        return this.lxrid;
    }

    public void setLxrid(String value) {
        this.lxrid = value;
    }

    public java.util.Date getLxsj() {
        return this.lxsj;
    }

    public void setLxsj(java.util.Date value) {
        this.lxsj = value;
    }

    public String getLxdh() {
        return this.lxdh;
    }

    public void setLxdh(String value) {
        this.lxdh = value;
    }

    public String getLxqk() {
        return this.lxqk;
    }

    public void setLxqk(String value) {
        this.lxqk = value;
    }

    public java.util.Date getNhjsj() {
        return this.nhjsj;
    }

    public void setNhjsj(java.util.Date value) {
        this.nhjsj = value;
    }

    public String getCjry() {
        return this.cjry;
    }

    public void setCjry(String value) {
        this.cjry = value;
    }

    public String getZdzxm() {
        return this.zdzxm;
    }

    public void setZdzxm(String value) {
        this.zdzxm = value;
    }

    public String getZdzyj() {
        return this.zdzyj;
    }

    public void setZdzyj(String value) {
        this.zdzyj = value;
    }

    public java.util.Date getZdzpssj() {
        return this.zdzpssj;
    }

    public void setZdzpssj(java.util.Date value) {
        this.zdzpssj = value;
    }

    public String getLdxm() {
        return this.ldxm;
    }

    public void setLdxm(String value) {
        this.ldxm = value;
    }

    public String getLdyj() {
        return this.ldyj;
    }

    public void setLdyj(String value) {
        this.ldyj = value;
    }

    public java.util.Date getLdpssj() {
        return this.ldpssj;
    }

    public void setLdpssj(java.util.Date value) {
        this.ldpssj = value;
    }

    public String getYwlcid() {
        return this.ywlcid;
    }

    public void setYwlcid(String value) {
        this.ywlcid = value;
    }

    public String getPsbz() {
        return this.psbz;
    }

    public void setPsbz(String value) {
        this.psbz = value;
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


}

