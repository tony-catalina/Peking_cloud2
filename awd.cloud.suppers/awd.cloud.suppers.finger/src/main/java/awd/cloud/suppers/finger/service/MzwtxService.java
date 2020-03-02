/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.service;

import java.util.List;
import java.util.Map;

import awd.cloud.suppers.finger.entity.MzwtxEntity;
import awd.cloud.suppers.finger.entity.MzwtzmEntity;
import awd.framework.common.service.api.CrudService;

public interface MzwtxService extends CrudService<MzwtxEntity, String> {
	
	String saveMzwtx(MzwtxEntity mzwtxEntity);
	
	String mfingerRegistWithYs(MzwtxEntity mzwtxEntity,MzwtzmEntity mzwtzmEntity);
	
	MzwtxEntity getEntyByZjh(String rybh);
	
	List<MzwtxEntity> queryByZjh(String zjh);

	String deleteZwtxAndTzmByZjh(String zjh); 
	
	String updateZwtxAndTzmByZjh(String zjh ,String state); //状态变为R3
	
	Map<String, Object> selectMjZwCode(String zjh ,String state1, String state2); 
	
	int deleteByZjh(String zjh);
	
	boolean entyIsExist(String zjh);
}
