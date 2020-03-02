/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;
import java.util.Map;

import awd.mis.servers.model.InterfaceModel;
import org.apache.ibatis.annotations.Param;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.InterfaceEntity;

public interface InterfaceDao extends CrudDao<InterfaceEntity, String> {
	
	List<InterfaceEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    List<InterfaceEntity> selectByappcode(@Param("appcode") String appcode,@Param("bdzt") String bdzt,@Param("start") String start,@Param("end") String end);
    int selectByappcodeCount(@Param("appcode") String appcode,@Param("bdzt") String bdzt);
    
    List<InterfaceEntity>  getApiParentNode();

    //获取接口信息与appcode对应的接口绑定状态
    List<InterfaceModel> selectInterfaceAndBdzt(Map<String,Object> param);

    int selectInterfaceCount(Map<String,Object> param);

    List<String> getTableGroup(@Param("type") String type);
}
