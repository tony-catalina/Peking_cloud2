/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.MenusEntity;
import awd.mis.servers.tools.Result;

public interface MenusService extends CrudService<MenusEntity, String> {

	void cached();

	String initMenu(String appcode, String menu, String fname, String name, String url, String creator);
	
	String addMenusPl(List<Map<String,Object>> list);
	
	Result<String> initMenuList(String appcode, List<Map<String, Object>> menuList, String creator);

	boolean menuIsExist(String menu);
}
