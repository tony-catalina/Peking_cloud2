package awd.mis.servers.model;

import awd.framework.common.model.Model;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.tools.CacheUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author WS
 * @Description:
 * @date 2020/1/15 下午6:01
 */
public class UserInfoOther implements Model {
    private String id;

    private java.lang.String usertype;
    private java.lang.String jsbh;
    private java.lang.String loginname;
    private java.lang.String loginpass;
    private java.lang.String sfzh;
    private java.lang.String jh;
    private java.lang.String email;
    private java.lang.String xm;
    private java.lang.String glybz;
    private java.lang.String cardID;
    private String role_id;


    public java.lang.String getUsertype() {
        return this.usertype;
    }

    public java.lang.String getUsertypeString() {
        if (StringUtils.isNullOrEmpty(this.usertype)) {
            return "";
        } else {
            return CacheUtils.get().getDictionary("USERTYPE", this.usertype);
        }
    }

    public void setUsertype(java.lang.String value) {
        this.usertype = value;
    }

    public java.lang.String getJsbh() {
        return this.jsbh;
    }

    public java.lang.String getJsbhString() {
        if (StringUtils.isNullOrEmpty(this.jsbh)) {
            return "";
        } else {
            return CacheUtils.get().getJsbhString(this.jsbh);
        }
    }

    public void setJsbh(java.lang.String value) {
        this.jsbh = value;
    }

    public java.lang.String getLoginname() {
        return this.loginname;
    }

    public void setLoginname(java.lang.String value) {
        this.loginname = value;
    }

    public java.lang.String getLoginpass() {
        return this.loginpass;
    }

    public void setLoginpass(java.lang.String value) {
        this.loginpass = value;
    }

    public java.lang.String getSfzh() {
        return this.sfzh;
    }

    public void setSfzh(java.lang.String value) {
        this.sfzh = value;
    }

    public java.lang.String getJh() {
        return this.jh;
    }

    public void setJh(java.lang.String value) {
        this.jh = value;
    }

    public java.lang.String getEmail() {
        return this.email;
    }

    public void setEmail(java.lang.String value) {
        this.email = value;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public java.lang.String getGlybz() {
        return this.glybz;
    }

    public java.lang.String getGlybzString() {
        if (StringUtils.isNullOrEmpty(this.glybz)) {
            return "";
        } else {
            return CacheUtils.get().getDictionary("SHFO", this.glybz);
        }
    }

    public void setGlybz(java.lang.String value) {
        this.glybz = value;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIris_url1() {
        return CacheUtils.get().getMirisUrl(this.sfzh, "1");
    }

    public String getIris_url2() {
        return CacheUtils.get().getMirisUrl(this.sfzh, "2");
    }


    public String getIris_url4() {
        return CacheUtils.get().getMirisUrl(this.sfzh, "4");
    }

    public String getIris_url5() {
        return CacheUtils.get().getMirisUrl(this.sfzh, "5");
    }

    public String getFace_url() {
        MfaceModel mfaceModel = CacheUtils.get().getMface(this.sfzh);
        if (mfaceModel != null) {
            return mfaceModel.getRltxurl();

        }
        return "";
    }

    public String getFingerprint() {
        List<MfingerModel> mfingerModels = CacheUtils.get().getMfingerModel(this.getSfzh());
        String str = "";
        if(mfingerModels==null){
            return "";
        }
        for (MfingerModel model : mfingerModels) {
            str += model.getZwtxurl() + ",";
        }
        if (!"".equals(str)) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return CacheUtils.get().getRole(this.role_id);
    }
}
