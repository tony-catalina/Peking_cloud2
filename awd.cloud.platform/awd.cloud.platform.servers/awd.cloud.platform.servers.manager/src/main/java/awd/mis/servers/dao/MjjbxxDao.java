package awd.mis.servers.dao;

import awd.framework.common.dao.api.CrudDao;
import awd.mis.servers.entity.MjjbxxEntity;

public interface MjjbxxDao extends CrudDao<MjjbxxEntity, String> {
    MjjbxxEntity selectByJh(String jh);
}
