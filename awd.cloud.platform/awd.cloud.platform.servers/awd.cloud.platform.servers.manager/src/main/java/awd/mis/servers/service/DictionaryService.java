package awd.mis.servers.service;

import java.util.List;
import java.util.Map;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.DictionaryEntity;

public interface DictionaryService extends CrudService<DictionaryEntity, String> {


    List<DictionaryEntity> getByField(String filed);

    List<Map<String, Object>> getAllFields();

    void cached();

    DictionaryEntity getDictionaryByFieldCode(String field, String code);


}
