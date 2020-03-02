package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.JbxxEntity;


public interface JbxxService extends CrudService<JbxxEntity, String> {
    JbxxEntity selectByrybh(String rybh);

    String saveOrUpdate(JbxxEntity jbxxEntity);
}
