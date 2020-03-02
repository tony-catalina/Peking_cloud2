package awd.mis.servers.dao;

import awd.framework.common.dao.api.CrudDao;
import awd.mis.servers.entity.JbxxEntity;

public interface JbxxDao extends CrudDao<JbxxEntity, String> {
    JbxxEntity selectByrybh(String rybh);
}
