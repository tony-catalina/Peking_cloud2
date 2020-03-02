package awd.mis.servers.service.imp;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.datasource.orm.core.dsl.Query;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.dao.MfingerDao;
import awd.mis.servers.entity.MfaceEntity;
import awd.mis.servers.entity.MfingerEntity;
import awd.mis.servers.service.MfingerService;
import awd.mis.servers.tools.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service("mfingerService")
public class SimpleMfingerService extends GenericEntityService<MfingerEntity, String> implements MfingerService {

    @Autowired
    private MfingerDao mfingerDao;

    @Override
    protected IDGenerator<String> getIDGenerator(MfingerEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public MfingerDao getDao() {
        return mfingerDao;
    }

    @Override
    @UseDataSource("biometric_ds")
    public int deleteByPk(String pk) {
        return super.deleteByPk(pk);
    }

    @Override
    @UseDataSource("biometric_ds")
    public String getSEQUID(String jsbh) {
        return super.getSEQUID(jsbh);
    }

    @Override
    @UseDataSource("biometric_ds")
    public String insert(MfingerEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        String i = super.insert(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    public String saveOrUpdate(MfingerEntity entity) {
        String i = super.saveOrUpdate(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<MfingerEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public MfingerEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<MfingerEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("biometric_ds")
    protected int updateByPk(MfingerEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(List<MfingerEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(String pk, MfingerEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }

    @Override
    @UseDataSource("biometric_ds")
    public MfingerEntity selectByPk(String zjh, String szbh) {
        return null == zjh ? null : (MfingerEntity) ((Query) this.createQuery().where("zjh", zjh).and("szbh", szbh)).single();
    }

    @Override
    @UseDataSource("biometric_ds")
    public MfingerEntity selectSingle(Entity param) {
        return super.selectSingle(param);
    }

    @Override
    @UseDataSource("biometric_ds")
    public List<MfingerEntity> select(Entity param) {
        return super.select(param);
    }

    @Override
    @UseDataSource("biometric_ds")
    public void cached() {
        QueryParamEntity queryParamEntity = QueryParamEntity.empty();
        queryParamEntity.excludes("zwtx");
        List<MfingerEntity> list = this.select(queryParamEntity);
        CacheUtils.get().removePattern(CacheUtils.CACHE_M_FINGER + "*");
        for (MfingerEntity entity : list) {
            String key = CacheUtils.CACHE_M_FINGER + entity.getZjh() + entity.getJh()+entity.getJsbh();
            CacheUtils.get().set(key, JSONUtil.toJson(entity));
        }
    }
}
