/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;
import java.util.Map;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.DictionaryEntity;
import org.apache.ibatis.annotations.Param;

public interface DictionaryDao extends CrudDao<DictionaryEntity, String> {

    List<DictionaryEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

    List<DictionaryEntity> findByField(String filedname);

    DictionaryEntity findByFieldCode(@Param("fieldname") String filedname, @Param("code") String code);

    List<Map<String, Object>> getAllFields();

}
