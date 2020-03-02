package awd.mis.activiti.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: wangshuai
 * Date: 2018/8/13 15:42
 *
 * @description:
 **/

@Data
public class FlowNode implements Serializable{
	
    public static final String TABLE_ALIAS = "流程节点";
    public static final String ALIAS_ID = "ID";
    public static final String ALIAS_JSBH = "监所编号";
    public static final String ALIAS_NODECODE = "节点代码";
    public static final String ALIAS_NODENAME = "节点名称";
    public static final String ALIAS_FLOWMAPID = "FlowMap表的ID";
    public static final String ALIAS_JDLX = "节点类型";
    public static final String ALIAS_ROLE = "操作角色";
    public static final String ALIAS_MEMO = "描述";
    public static final String ALIAS_CREATOR = "创建人";
    public static final String ALIAS_CREATETIME = "创建时间";
    public static final String ALIAS_UPDATOR = "更新人";
    public static final String ALIAS_UPDATETIME = "更新时间";



    private java.lang.String id;

    private java.lang.String jsbh;

    private java.lang.String nodecode;

    private java.lang.String nodename;

    private java.lang.String flowmapid;

    private java.lang.String jdlx;

    private java.lang.String role;

    private java.lang.String memo;

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

	public java.lang.String getNodecode() {
		return nodecode;
	}

	public void setNodecode(java.lang.String nodecode) {
		this.nodecode = nodecode;
	}

	public java.lang.String getNodename() {
		return nodename;
	}

	public void setNodename(java.lang.String nodename) {
		this.nodename = nodename;
	}

	public java.lang.String getFlowmapid() {
		return flowmapid;
	}

	public void setFlowmapid(java.lang.String flowmapid) {
		this.flowmapid = flowmapid;
	}

	public java.lang.String getJdlx() {
		return jdlx;
	}

	public void setJdlx(java.lang.String jdlx) {
		this.jdlx = jdlx;
	}

	public java.lang.String getRole() {
		return role;
	}

	public void setRole(java.lang.String role) {
		this.role = role;
	}

	public java.lang.String getMemo() {
		return memo;
	}

	public void setMemo(java.lang.String memo) {
		this.memo = memo;
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
    
    
}
