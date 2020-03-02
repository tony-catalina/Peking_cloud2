/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;


import java.util.List;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.UserinfoEntity;
import awd.mis.servers.model.GroupInfo;
import awd.mis.servers.model.JsInfo;
import awd.mis.servers.model.RoleInfo;
import awd.mis.servers.model.Userinfo;

public interface AuthenticationService extends CrudService<UserinfoEntity, String> {
	
	/**
	 * 禁用app
	 * @param userid
	 * @param appids
	 * @return
	 */
	public boolean  forbidApp(String userid,List<String> appids);
	
	/**
	 * 禁用app菜单
	 * @param userid
	 * @param appid
	 * @param menuids
	 * @return
	 */
	public boolean  forbidMenu(String userid,String appid ,List<String> menuids);
	/**
	 * 根据用户组编号获取用户组信息
	 * @param groupid
	 * @return
	 */
	public GroupInfo getGroup(String groupid);
	/**
	 * 根据监所编号 获取应用信息
	 * @param jsbh
	 * @return
	 */
	public JsInfo    getJsApp(String jsbh);
	
	/**
	 * 根据角色代码获取角色信息
	 * @param roleid
	 * @return
	 */
	public RoleInfo  getRole(String rolecode);
	
	
	public Userinfo getUserinfo(String jsbh, String username);
	/**
	 * 根据userid 获取一个人的所有权限
	 * @param userid
	 * @return
	 */
	public Userinfo getUserinfo(String id);
	

}
