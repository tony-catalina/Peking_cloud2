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
public class JslxModel implements Model {

    //columns START

    private String id;


    private String rybh;

    private String jsbh;

    private String sqr;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date sqsj;

    private String lyjsx;

    private String lxr;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date lxsj;

    private String lxqk;

    private String lxzt;

    private String rsqsfyjb;

    private String rsqjbxxqk;

    private String sfyjzbs;

    private String jzbsxxqk;

    private String sfcqfyhfyzmyw;

    private String cqfyhfyzmywxxqk;

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

    private String ywlcid;

    private String pastable;

    private String jsxm1;

    private String gx1;

    private String jslxdh1;

    private String jsxm2;

    private String gx2;

    private String jslxdh2;

    private String jsxm3;

    private String gx3;

    private String jslxdh3;
    //columns END


    public JslxModel() {
    }

    public JslxModel(String id) {
        this.id = id;
    }


    public void setId(String value) {
        this.id = value;
    }

    public String getId() {
        return this.id;
    }

    public String getRybh() {
        return this.rybh;
    }

    public void setRybh(String value) {
        this.rybh = value;
    }

    public String getJsbh() {
        return this.jsbh;
    }

    public void setJsbh(String value) {
        this.jsbh = value;
    }

    public String getSqr() {
        return this.sqr;
    }

    public void setSqr(String value) {
        this.sqr = value;
    }

    public java.util.Date getSqsj() {
        return this.sqsj;
    }

    public void setSqsj(java.util.Date value) {
        this.sqsj = value;
    }

    public String getLyjsx() {
        return this.lyjsx;
    }

    public void setLyjsx(String value) {
        this.lyjsx = value;
    }

    public String getLxr() {
        return this.lxr;
    }

    public void setLxr(String value) {
        this.lxr = value;
    }

    public java.util.Date getLxsj() {
        return this.lxsj;
    }

    public void setLxsj(java.util.Date value) {
        this.lxsj = value;
    }

    public String getLxqk() {
        return this.lxqk;
    }

    public void setLxqk(String value) {
        this.lxqk = value;
    }

    public String getLxzt() {
        return this.lxzt;
    }

    public void setLxzt(String value) {
        this.lxzt = value;
    }

    public String getRsqsfyjb() {
        return this.rsqsfyjb;
    }

    public void setRsqsfyjb(String value) {
        this.rsqsfyjb = value;
    }

    public String getRsqjbxxqk() {
        return this.rsqjbxxqk;
    }

    public void setRsqjbxxqk(String value) {
        this.rsqjbxxqk = value;
    }

    public String getSfyjzbs() {
        return this.sfyjzbs;
    }

    public void setSfyjzbs(String value) {
        this.sfyjzbs = value;
    }

    public String getJzbsxxqk() {
        return this.jzbsxxqk;
    }

    public void setJzbsxxqk(String value) {
        this.jzbsxxqk = value;
    }

    public String getSfcqfyhfyzmyw() {
        return this.sfcqfyhfyzmyw;
    }

    public void setSfcqfyhfyzmyw(String value) {
        this.sfcqfyhfyzmyw = value;
    }

    public String getCqfyhfyzmywxxqk() {
        return this.cqfyhfyzmywxxqk;
    }

    public void setCqfyhfyzmywxxqk(String value) {
        this.cqfyhfyzmywxxqk = value;
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

    public String getYwlcid() {
        return this.ywlcid;
    }

    public void setYwlcid(String value) {
        this.ywlcid = value;
    }

    public String getPastable() {
        return this.pastable;
    }

    public void setPastable(String value) {
        this.pastable = value;
    }

    public String getJsxm1() {
        return this.jsxm1;
    }

    public void setJsxm1(String value) {
        this.jsxm1 = value;
    }

    public String getGx1() {
        return this.gx1;
    }

    public void setGx1(String value) {
        this.gx1 = value;
    }

    public String getJslxdh1() {
        return this.jslxdh1;
    }

    public void setJslxdh1(String value) {
        this.jslxdh1 = value;
    }

    public String getJsxm2() {
        return this.jsxm2;
    }

    public void setJsxm2(String value) {
        this.jsxm2 = value;
    }

    public String getGx2() {
        return this.gx2;
    }

    public void setGx2(String value) {
        this.gx2 = value;
    }

    public String getJslxdh2() {
        return this.jslxdh2;
    }

    public void setJslxdh2(String value) {
        this.jslxdh2 = value;
    }

    public String getJsxm3() {
        return this.jsxm3;
    }

    public void setJsxm3(String value) {
        this.jsxm3 = value;
    }

    public String getGx3() {
        return this.gx3;
    }

    public void setGx3(String value) {
        this.gx3 = value;
    }

    public String getJslxdh3() {
        return this.jslxdh3;
    }

    public void setJslxdh3(String value) {
        this.jslxdh3 = value;
    }

}

