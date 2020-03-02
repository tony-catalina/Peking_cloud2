package awd.mis.servers.model;

import java.util.List;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.mis.servers.entity.AppEntity;
import awd.mis.servers.entity.MenusEntity;
import awd.mis.servers.entity.RoleEntity;

public class RoleInfo extends SimpleGenericEntity<String>{
	
	private RoleEntity role;
	private List<AppEntity> apps;
	private List<MenusEntity> menus;
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public List<AppEntity> getApps() {
		return apps;
	}
	public void setApps(List<AppEntity> apps) {
		this.apps = apps;
	}
	public List<MenusEntity> getMenus() {
		return menus;
	}
	public void setMenus(List<MenusEntity> menus) {
		this.menus = menus;
	}
	
	
 
}
