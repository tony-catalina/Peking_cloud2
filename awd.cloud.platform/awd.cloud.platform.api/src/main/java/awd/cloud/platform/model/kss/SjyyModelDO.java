package awd.cloud.platform.model.kss;

import awd.bj.kss.model.SjyyModel;

public class SjyyModelDO extends SjyyModel {
    private Integer count;
    private String jsbh;
    private String asblsjForCount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public String getJsbh() {
        return jsbh;
    }


    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getAsblsjForCount() {
        return asblsjForCount;
    }

    public void setAsblsjForCount(String asblsjForCount) {
        this.asblsjForCount = asblsjForCount;
    }
}
