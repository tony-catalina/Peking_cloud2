/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.service;

import java.util.List;
import java.util.Map;

import awd.cloud.suppers.finger.entity.MzwtzmEntity;
import awd.framework.common.service.api.CrudService;

public interface MzwtzmService extends CrudService<MzwtzmEntity, String> {
	
	String saveMzwtzm(MzwtzmEntity mzwtzmEntity);
	
	MzwtzmEntity getEntyByZjh(String zjh);
	
	List<MzwtzmEntity> queryMzwtzms(String zjh);
	
	List<MzwtzmEntity> getEntyListByJsbh(String jsbh);
	
	List<?> getParamListByJsbh(String jsbh);
	
	int deleteByZjh(String zjh);
	
	boolean entyIsExist(String zjh);
	
	void cacheMzwtzm();

}
