package awd.cloud.platform.model.kss;
import awd.bj.kss.model.CrjjcModel;
import awd.cloud.platform.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
public class Kss_CrjjcModelDO extends Kss_CrjjcModel {
    private String psbz;
    private Integer count;
    private String blsjForCount;
    private String shid;

    private String jbxxSnbh;
    private String jbxxXm;
    private String jbxxJsh;
    private String jbxxRsrq;
    private String jbxxCsrq;
    private String jbxxHjd;


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

    public String getJbxxSnbh() {
        return jbxxSnbh;
    }

    public void setJbxxSnbh(String jbxxSnbh) {
        this.jbxxSnbh = jbxxSnbh;
    }

    public String getJbxxXm() {
        return jbxxXm;
    }

    public void setJbxxXm(String jbxxXm) {
        this.jbxxXm = jbxxXm;
    }

    public String getJbxxJsh() {
        return jbxxJsh;
    }

    public void setJbxxJsh(String jbxxJsh) {
        this.jbxxJsh = jbxxJsh;
    }

    public String getJbxxRsrq() {
        return jbxxRsrq;
    }

    public void setJbxxRsrq(String jbxxRsrq) {
        this.jbxxRsrq = jbxxRsrq;
    }

    public String getJbxxCsrq() {
        return jbxxCsrq;
    }

    public void setJbxxCsrq(String jbxxCsrq) {
        this.jbxxCsrq = jbxxCsrq;
    }

    public String getJbxxHjd() {
        return jbxxHjd;
    }

    public void setJbxxHjd(String jbxxHjd) {
        this.jbxxHjd = jbxxHjd;
    }
}
