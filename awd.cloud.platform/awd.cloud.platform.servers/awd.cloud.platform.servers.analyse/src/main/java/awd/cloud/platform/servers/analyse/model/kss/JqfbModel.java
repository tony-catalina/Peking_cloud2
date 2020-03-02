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
public class JqfbModel implements Model {


    //columns START

    private String id;


    private String jsbh;

    private String xm;

    private String jh;

    private String fbr;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date fbsj;

    private String fbdd;

    private String jqnr;

    private String cljg;

    private String bz;

    private String ly;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date clsj;

    private String clr;

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

    private String state;
    //columns END


    public JqfbModel() {
    }

    public JqfbModel(String id) {
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

    public String getXm() {
        return this.xm;
    }

    public void setXm(String value) {
        this.xm = value;
    }

    public String getJh() {
        return this.jh;
    }

    public void setJh(String value) {
        this.jh = value;
    }

    public String getFbr() {
        return this.fbr;
    }

    public void setFbr(String value) {
        this.fbr = value;
    }

    public java.util.Date getFbsj() {
        return this.fbsj;
    }

    public void setFbsj(java.util.Date value) {
        this.fbsj = value;
    }

    public String getFbdd() {
        return this.fbdd;
    }

    public void setFbdd(String value) {
        this.fbdd = value;
    }

    public String getJqnr() {
        return this.jqnr;
    }

    public void setJqnr(String value) {
        this.jqnr = value;
    }

    public String getCljg() {
        return this.cljg;
    }

    public void setCljg(String value) {
        this.cljg = value;
    }

    public String getBz() {
        return this.bz;
    }

    public void setBz(String value) {
        this.bz = value;
    }

    public String getLy() {
        return this.ly;
    }

    public void setLy(String value) {
        this.ly = value;
    }

    public java.util.Date getClsj() {
        return this.clsj;
    }

    public void setClsj(java.util.Date value) {
        this.clsj = value;
    }

    public String getClr() {
        return this.clr;
    }

    public void setClr(String value) {
        this.clr = value;
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

    public String getState() {
        return this.state;
    }

    public void setState(String value) {
        this.state = value;
    }

}

