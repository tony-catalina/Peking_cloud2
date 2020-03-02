package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.MfaceEntity;
import awd.mis.servers.entity.MjjbxxEntity;

public interface MjjbxxService extends CrudService<MjjbxxEntity, String>{
    MjjbxxEntity selectByJh(String jh);

    String saveOrUpdate(MjjbxxEntity mjjbxxEntity);

}
