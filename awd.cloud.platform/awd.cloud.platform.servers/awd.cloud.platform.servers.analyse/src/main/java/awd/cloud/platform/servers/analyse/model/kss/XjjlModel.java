/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.kss
        ;

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
public class XjjlModel implements Model {


    //columns START

    private String id;


    private String jsbh;

    private String type;

    private String name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date xjrq;

    private String xjr;

    private String ywyc;

    private String ycqk;

    private String bz;

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

    private String dbrzsfzc;

    private String fsrzsfzc;

    private String zsjcsbsfzc;
    //columns END


    public XjjlModel() {
    }

    public XjjlModel(String id) {
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

    public String getType() {
        return this.type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public java.util.Date getXjrq() {
        return this.xjrq;
    }

    public void setXjrq(java.util.Date value) {
        this.xjrq = value;
    }

    public String getXjr() {
        return this.xjr;
    }

    public void setXjr(String value) {
        this.xjr = value;
    }

    public String getYwyc() {
        return this.ywyc;
    }

    public void setYwyc(String value) {
        this.ywyc = value;
    }

    public String getYcqk() {
        return this.ycqk;
    }

    public void setYcqk(String value) {
        this.ycqk = value;
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

    public String getDbrzsfzc() {
        return this.dbrzsfzc;
    }

    public void setDbrzsfzc(String value) {
        this.dbrzsfzc = value;
    }

    public String getFsrzsfzc() {
        return this.fsrzsfzc;
    }

    public void setFsrzsfzc(String value) {
        this.fsrzsfzc = value;
    }

    public String getZsjcsbsfzc() {
        return this.zsjcsbsfzc;
    }

    public void setZsjcsbsfzc(String value) {
        this.zsjcsbsfzc = value;
    }

}

