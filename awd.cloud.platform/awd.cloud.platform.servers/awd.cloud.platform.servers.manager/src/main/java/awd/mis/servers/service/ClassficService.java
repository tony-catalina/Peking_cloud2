/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.ClassficEntity;

public interface ClassficService  extends CrudService<ClassficEntity, String> {

	List<ClassficEntity> selectByParentIdandName(String parentid, String name);

	void cached();

	List<ClassficEntity> selectChildNode(String lx,String parentid);

	List<ClassficEntity> selectAllChildNode(String lx,String parentid);
	

}
