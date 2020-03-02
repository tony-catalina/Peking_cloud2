package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;

/**
 * Author：YaoBen
 * Date：2019-11-23 16:25
 * Description：<描述>
 */
public class BJSDY_kssModel implements Model {

    private String  jsbh;
    private String xm;
    private String  jsh;
    private String   bahj;
    private String  jqh;
    private String  rybh;

    public BJSDY_kssModel() {
    }

    public BJSDY_kssModel(String jsbh, String xm, String jsh, String bahj, String jqh, String rybh) {
        this.jsbh = jsbh;
        this.xm = xm;
        this.jsh = jsh;
        this.bahj = bahj;
        this.jqh = jqh;
        this.rybh = rybh;
    }

    @Override
    public String toString() {
        return "BJSDY_kssModel{" +
                "jsbh='" + jsbh + '\'' +
                ", xm='" + xm + '\'' +
                ", jsh='" + jsh + '\'' +
                ", bahj='" + bahj + '\'' +
                ", jqh='" + jqh + '\'' +
                ", rybh='" + rybh + '\'' +
                '}';
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getJsh() {
        return jsh;
    }

    public void setJsh(String jsh) {
        this.jsh = jsh;
    }

    public String getBahj() {
        return bahj;
    }

    public void setBahj(String bahj) {
        this.bahj = bahj;
    }

    public String getJqh() {
        return jqh;
    }

    public void setJqh(String jqh) {
        this.jqh = jqh;
    }

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh;
    }
}
