package awd.mis.servers.service.imp;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.datasource.orm.core.dsl.Query;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.GenericEntity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.dao.MirisDao;
import awd.mis.servers.entity.MfingerEntity;
import awd.mis.servers.entity.MirisEntity;
import awd.mis.servers.service.MirisService;
import awd.mis.servers.tools.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service("mirisService")
public class SimpleMirisService extends GenericEntityService<MirisEntity, String> implements MirisService {

    @Autowired
    private MirisDao mirisDao;

    @Override
    protected IDGenerator<String> getIDGenerator(MirisEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public MirisDao getDao() {
        return mirisDao;
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
    public String insert(MirisEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        String i = super.insert(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    public String saveOrUpdate(MirisEntity entity) {

        String i = super.saveOrUpdate(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<MirisEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public MirisEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<MirisEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("biometric_ds")
    protected int updateByPk(MirisEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(List<MirisEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(String pk, MirisEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }

    @Override
    @UseDataSource("biometric_ds")
    public MirisEntity selectByPk(String zjh, String hmwzbh) {
        return null == zjh ? null : (MirisEntity) ((Query) this.createQuery().where("zjh", zjh).and("hmwzbh", hmwzbh)).single();
    }

    @Override
    @UseDataSource("biometric_ds")
    public List<MirisEntity> select(Entity param) {
        return super.select(param);
    }

    @Override
    @UseDataSource("biometric_ds")
    public List<MirisEntity> select() {
        return super.select();
    }

    @Override
    @UseDataSource("biometric_ds")
    public void cached() {
        QueryParamEntity queryParamEntity = QueryParamEntity.empty();
        queryParamEntity.excludes("hmtx");
        List<MirisEntity> list = this.select(queryParamEntity);
        CacheUtils.get().removePattern(CacheUtils.CACHE_M_IRIS + "*");
        for (MirisEntity entity : list) {
            String key = CacheUtils.CACHE_M_IRIS + entity.getZjh() + entity.getJh() + entity.getJsbh() + entity.getId();
            CacheUtils.get().set(key, JSONUtil.toJson(entity));
        }
    }
}
