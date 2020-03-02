package awd.mis.servers.service.imp;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.dao.JbxxDao;
import awd.mis.servers.entity.JbxxEntity;
import awd.mis.servers.entity.MjjbxxEntity;
import awd.mis.servers.service.JbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleJbxxService extends GenericEntityService<JbxxEntity, String> implements JbxxService {
    @Autowired
    private JbxxDao jbxxDao;

    @Override
    protected IDGenerator<String> getIDGenerator(JbxxEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public CrudDao<JbxxEntity, String> getDao() {
        return jbxxDao;
    }

    @Override
    @UseDataSource("read_mjxx")
    public JbxxEntity selectByrybh(String rybh) {
        return jbxxDao.selectByrybh(rybh);
    }

    @Override
    @UseDataSource("write_mjxx")
    public String saveOrUpdate(JbxxEntity jbxxEntity) {
        System.out.println("===========000:"+ JSONUtil.toJson(jbxxEntity));
        return super.saveOrUpdate(jbxxEntity);
    }
}
