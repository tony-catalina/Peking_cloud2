/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;
import java.util.Map;

import awd.mis.servers.model.UserInfoOther;
import org.apache.ibatis.annotations.Param;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.UserinfoEntity;

public interface UserinfoDao extends CrudDao<UserinfoEntity, String> {
	
	List<UserinfoEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

	UserinfoEntity findByNameAndPass(@Param("jsbh")String jsbh, @Param("name")String name, @Param("pwd")String pwd, @Param("state")String state);

	List<UserinfoEntity > getUserByJsbhRole(@Param("jsbh") String jsbh ,@Param("role") String role);
	
	/**
	 * 根据多个role查询用户
	 * @param jsbhs
	 * @param roles
	 * @return
	 */
	List<UserinfoEntity> getUserByJsbhsRoles(@Param("jsbhs") String jsbhs ,@Param("roles") String roles);

	/**
	 * 根据监所编号获取用户名
	 * author: 宋帛阳
	 * Date： 2019-11-14 09:41:24
	 * @param jsbh
	 * @return
	 */
	List<Map<String,String>> getUserByJsbh(@Param("jsbh") String jsbh, @Param("zdbh") String zdbh);

	/**
	 * 通过警号获取用户手机号
	 * @param jh
	 * @return
	 */
	Map<String,Object> getUserinfoDh(@Param("jh") String jh);

	List<UserInfoOther> getUserRole(Entity queryEntity);

	int getUserRoleCount(Entity queryEntity);
}
