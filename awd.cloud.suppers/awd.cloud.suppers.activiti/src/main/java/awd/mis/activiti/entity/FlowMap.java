package awd.mis.activiti.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: wangshuai
 * Date: 2018/8/13 14:38
 *
 * @description:
 **/

@Data
public class FlowMap implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String id;

    private java.lang.String jsbh;

    private java.lang.String mapname;

    private java.lang.String mapkey;

    private java.lang.Integer version;

    private java.lang.String mapmutex;

    private java.lang.String timelimit;

    private java.lang.Integer monthlylimit;

    private java.lang.String memo;

    private java.lang.String flag;

    private java.lang.String creator;

    private java.util.Date createtime;

    private java.lang.String updator;

    private java.util.Date updatetime;

    private java.lang.String menu;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getJsbh() {
		return jsbh;
	}

	public void setJsbh(java.lang.String jsbh) {
		this.jsbh = jsbh;
	}

	public java.lang.String getMapname() {
		return mapname;
	}

	public void setMapname(java.lang.String mapname) {
		this.mapname = mapname;
	}

	public java.lang.String getMapkey() {
		return mapkey;
	}

	public void setMapkey(java.lang.String mapkey) {
		this.mapkey = mapkey;
	}

	public java.lang.Integer getVersion() {
		return version;
	}

	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	public java.lang.String getMapmutex() {
		return mapmutex;
	}

	public void setMapmutex(java.lang.String mapmutex) {
		this.mapmutex = mapmutex;
	}

	public java.lang.String getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(java.lang.String timelimit) {
		this.timelimit = timelimit;
	}

	public java.lang.Integer getMonthlylimit() {
		return monthlylimit;
	}

	public void setMonthlylimit(java.lang.Integer monthlylimit) {
		this.monthlylimit = monthlylimit;
	}

	public java.lang.String getMemo() {
		return memo;
	}

	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}

	public java.lang.String getFlag() {
		return flag;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}

	public java.lang.String getCreator() {
		return creator;
	}

	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public java.lang.String getUpdator() {
		return updator;
	}

	public void setUpdator(java.lang.String updator) {
		this.updator = updator;
	}

	public java.util.Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public java.lang.String getMenu() {
		return menu;
	}

	public void setMenu(java.lang.String menu) {
		this.menu = menu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    

}
