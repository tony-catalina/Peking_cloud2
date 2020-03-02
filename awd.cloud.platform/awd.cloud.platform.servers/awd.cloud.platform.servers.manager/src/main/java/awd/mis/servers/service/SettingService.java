/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;
import java.util.Map;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.SettingEntity;
import awd.mis.servers.tools.Result;

public interface SettingService extends CrudService<SettingEntity, String> {



	List<SettingEntity> getSetting(String appcode, String progroup);

	String getAppSetting(String appcode, String jsbh, String proname);
	
	Result<String> initSettingList(String appcode, List<Map<String, Object>> settingList, String user);
	


	

}
