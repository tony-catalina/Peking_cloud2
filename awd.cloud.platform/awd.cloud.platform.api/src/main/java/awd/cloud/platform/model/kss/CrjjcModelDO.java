package awd.cloud.platform.model.kss;

import awd.bj.kss.model.CrjjcModel;

public class CrjjcModelDO extends CrjjcModel {
    private String psbz;
    private Integer count;
    private String blsjForCount;
    private String shid;

    public String getPsbz() {
        return psbz;
    }

    public void setPsbz(String psbz) {
        this.psbz = psbz;
    }


    public Integer getCount() {
        return count;
    }


    public void setCount(Integer count) {
        this.count = count;
    }


    public String getBlsjForCount() {
        return blsjForCount;
    }


    public void setBlsjForCount(String blsjForCount) {
        this.blsjForCount = blsjForCount;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }
}
