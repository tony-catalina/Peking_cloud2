package awd.cloud.platform.model.jwp;

import awd.cloud.platform.utils.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class XlpcModel implements Model {
    //columns START

    private String id;


    private String appcode;

    private String jsbh;

    private String rybh;

    private String jsh;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date cpsj;

    private String sjid;

    private String da;

    private Long df;

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



    public XlpcModel(){
    }
    public XlpcModel(String id) {
        this.id = id;
    }


    public void setId(String value) {
        this.id = value;
    }

    public String getId() {
        return this.id;
    }

    public String getAppcode() {
        return this.appcode;
    }

    public void setAppcode(String value) {
        this.appcode = value;
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
    public String getJsh() {
        return this.jsh;
    }

    public void setJsh(String value) {
        this.jsh = value;
    }

    public java.util.Date getCpsj() {
        return this.cpsj;
    }

    public void setCpsj(java.util.Date value) {
        this.cpsj = value;
    }

    public String getSjid() {
        return sjid;
    }

    public void setSjid(String sjid) {
        this.sjid = sjid;
    }

    public String getDa() {
        return this.da;
    }

    public void setDa(String value) {
        this.da = value;
    }
    public Long getDf() {
        return this.df;
    }

    public void setDf(Long value) {
        this.df = value;
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
