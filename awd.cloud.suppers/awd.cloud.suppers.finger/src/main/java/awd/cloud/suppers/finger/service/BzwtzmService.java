/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.service;

import java.util.List;
import java.util.Map;

import awd.cloud.suppers.finger.entity.BzwtzmEntity;
import awd.framework.common.service.api.CrudService;

public interface BzwtzmService extends CrudService<BzwtzmEntity, String> {

	BzwtzmEntity getEntyByRybh(String rybh,String state);
	
	List<BzwtzmEntity> getEntyListByRybh(String rybh ,String state);
	
	List<Map<String, Object>> getParamListByJsbh(String jsbh);
	
	List<BzwtzmEntity> getEntyListByJsbh(String jsbh);
	
	int fingerfeatureRegist(BzwtzmEntity entity);
	
	int deleteByRybh(String rybh);
	
	void cacheBzwtzm();
	
	boolean entyIsExist(String rybh);
}
