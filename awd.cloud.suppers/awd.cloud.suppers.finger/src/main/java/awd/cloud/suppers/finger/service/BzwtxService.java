/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.service;

import java.util.List;
import java.util.Map;

import awd.cloud.suppers.finger.entity.BzwtxEntity;
import awd.cloud.suppers.finger.entity.BzwtzmEntity;
import awd.framework.common.service.api.CrudService;

public interface BzwtxService extends CrudService<BzwtxEntity, String> {

	int fingerRegist(BzwtxEntity entity);
	
	int fingerRegistWithYs(BzwtxEntity entity1,BzwtzmEntity entity2);
	
	int deleteByRybh(String rybh);
	
	BzwtxEntity getEntyByRybh(String rybh,String state);
	
	List<BzwtxEntity> getEntyListByRybh(String rybh , String state);
	
	String deleteZwtxAndTzmByRybh(String rybh); 
	
	String updateZwtxAndTzmByRybh(String rybh ,String old_state,String new_state);
	
	Map<String, Object> selectZwCode(String rybh ,String state1, String state2); 
	
	boolean entyIsExist(String rybh);
	
	boolean ryIsYcs(String rybh);
}
