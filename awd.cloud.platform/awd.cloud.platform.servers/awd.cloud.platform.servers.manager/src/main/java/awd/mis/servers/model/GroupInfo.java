package awd.mis.servers.model;

import java.util.List;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.mis.servers.entity.GroupsEntity;
import awd.mis.servers.entity.MenusEntity;

public class GroupInfo extends SimpleGenericEntity<String> {
	private GroupsEntity group;
 	private List<MenusEntity> menus;
	public GroupsEntity getGroup() {
		return group;
	}
	public void setGroup(GroupsEntity group) {
		this.group = group;
	}
	public List<MenusEntity> getMenus() {
		return menus;
	}
	public void setMenus(List<MenusEntity> menus) {
		this.menus = menus;
	}
 	
 	
 	

}
