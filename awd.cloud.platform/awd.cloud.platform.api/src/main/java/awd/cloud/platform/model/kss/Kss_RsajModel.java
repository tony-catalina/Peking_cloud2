package awd.cloud.platform.model.kss;
import awd.bj.kss.model.JbxxInfo;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
public class Kss_RsajModel extends JbxxInfo {
    private String id;
    private String gcbh;
    private String rybh;
    private String jsbh;
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
    private String wjpxq;
    private String sqxq;
    private String jcry;
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
    private Date jcrq;
    private String zbmj;
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
    private Date djsj;
    private String bz;
    private String jsbhString;

    public Kss_RsajModel() {
    }
    public Kss_RsajModel(String id) {
        this.id = id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getId() {
        return this.id;
    }

    public String getGcbh() {
        return this.gcbh;
    }

    public void setGcbh(String value) {
        this.gcbh = value;
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

    public String getWjpxq() {
        return this.wjpxq;
    }

    public void setWjpxq(String value) {
        this.wjpxq = value;
    }

    public String getSqxq() {
        return this.sqxq;
    }

    public void setSqxq(String value) {
        this.sqxq = value;
    }

    public String getJcry() {
        return this.jcry;
    }

    public void setJcry(String value) {
        this.jcry = value;
    }

    public Date getJcrq() {
        return this.jcrq;
    }

    public void setJcrq(Date value) {
        this.jcrq = value;
    }

    public String getZbmj() {
        return this.zbmj;
    }

    public void setZbmj(String value) {
        this.zbmj = value;
    }

    public Date getDjsj() {
        return this.djsj;
    }

    public void setDjsj(Date value) {
        this.djsj = value;
    }

    public String getBz() {
        return this.bz;
    }

    public void setBz(String value) {
        this.bz = value;
    }

    public String getJsbhString() {
        return this.jsbhString;
    }
}
