package awd.cloud.platform.model.jwp;

import awd.bj.kss.model.SpdetailorderModel;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

public class SpdetailorderModelDO extends SpdetailorderModel {
    private String spmc;
    private Map<String,String> ldspxx;
    private String snbh;
    private String jsh;
    private String xm;
    private String taskid;
    private java.lang.Double sj;

    public Map<String, String> getLdspxx() {
        return ldspxx;
    }

    public void setLdspxx(Map<String, String> ldspxx) {
        this.ldspxx = ldspxx;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSnbh() {
        return snbh;
    }

    public void setSnbh(String snbh) {
        this.snbh = snbh;
    }

    public String getJsh() {
        return jsh;
    }

    public void setJsh(String jsh) {
        this.jsh = jsh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

	public java.lang.Double getSj() {
		return sj;
	}

	public void setSj(java.lang.Double sj) {
		this.sj = sj;
	}
    
}
