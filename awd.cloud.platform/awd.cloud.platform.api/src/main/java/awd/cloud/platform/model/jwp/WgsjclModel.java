package awd.cloud.platform.model.jwp;
import awd.cloud.platform.utils.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WgsjclModel implements Model{
    //columns START

    private String id;


    private String appcode;

    private String jsbh;

    private String wgqk;

    private String wgqkcon;

    private String clqk;

    private String dxbh;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date wgsj;

    private String state;

    private String creator;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createtime;


    //columns END
    public WgsjclModel(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getWgqk() {
        return wgqk;
    }

    public void setWgqk(String wgqk) {
        this.wgqk = wgqk;
    }

    public String getWgqkcon() {
        return wgqkcon;
    }

    public void setWgqkcon(String wgqkcon) {
        this.wgqkcon = wgqkcon;
    }

    public String getClqk() {
        return clqk;
    }

    public void setClqk(String clqk) {
        this.clqk = clqk;
    }

    public String getDxbh() {
        return dxbh;
    }

    public void setDxbh(String dxbh) {
        this.dxbh = dxbh;
    }

    public Date getWgsj() {
        return wgsj;
    }

    public void setWgsj(Date wgsj) {
        this.wgsj = wgsj;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
