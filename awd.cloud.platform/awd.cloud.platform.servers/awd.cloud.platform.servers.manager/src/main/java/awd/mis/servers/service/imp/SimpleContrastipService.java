package awd.mis.servers.service.imp;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.dao.ContrastipDao;
import awd.mis.servers.entity.ContrastipEntity;
import awd.mis.servers.service.ContrastipService;
import awd.mis.servers.tools.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contrastipService")
public class SimpleContrastipService extends GenericEntityService<ContrastipEntity, String> implements ContrastipService {

    @Autowired
    private ContrastipDao contrastipDao;

    @Override
    protected IDGenerator<String> getIDGenerator(ContrastipEntity entity) {
        String jsbh = "000000000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public ContrastipDao getDao() {
        return contrastipDao;
    }

    @Override
    @UseDataSource("write_ds")
    public int deleteByPk(String pk) {
        return super.deleteByPk(pk);
    }

    @Override
    @UseDataSource("write_ds")
    public String getSEQUID(String jsbh) {
        return super.getSEQUID(jsbh);
    }

    @Override
    @UseDataSource("write_ds")
    public String insert(ContrastipEntity entity) {
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
    public String saveOrUpdate(ContrastipEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<ContrastipEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public ContrastipEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<ContrastipEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
    protected int updateByPk(ContrastipEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
    public int updateByPk(List<ContrastipEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
    public int updateByPk(String pk, ContrastipEntity entity) {
        return super.updateByPk(pk, entity);
    }

    @Override
    @UseDataSource("write_ds")
    public void cached() {
        List<ContrastipEntity> list = this.select();
        CacheUtils.get().removePattern(CacheUtils.CACHE_CONTRAST_IP+"*");
        for (ContrastipEntity entity : list) {
            String key = CacheUtils.CACHE_CONTRAST_IP + entity.getUserip() + entity.getId();
            CacheUtils.get().set(key, JSONUtil.toJson(entity));
        }
    }
}
