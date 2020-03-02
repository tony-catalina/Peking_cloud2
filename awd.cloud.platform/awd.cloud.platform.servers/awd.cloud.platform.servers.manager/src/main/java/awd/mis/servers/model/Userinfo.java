package awd.mis.servers.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.mis.servers.entity.AppEntity;
import awd.mis.servers.entity.GroupsEntity;
import awd.mis.servers.entity.MenusEntity;
import awd.mis.servers.entity.RoleEntity;
import awd.mis.servers.entity.UserinfoEntity;
import awd.mis.servers.entity.UsersettingEntity;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Userinfo implements Serializable {
	private String id;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private List<AppEntity> apps;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private List<MenusEntity> menus;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private UserinfoEntity userinfo;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private GroupsEntity group;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private List<RoleEntity> roles;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private List<UsersettingEntity> setting;
	
	public UserinfoEntity getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserinfoEntity user) {
		this.userinfo = user;
	}
	public GroupsEntity getGroup() {
		return group;
	}
	public void setGroup(GroupsEntity group) {
		this.group = group;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public List<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	public List<UsersettingEntity> getSetting() {
		return setting;
	}
	public void setSetting(List<UsersettingEntity> setting) {
		this.setting = setting;
	}
	
	
}
