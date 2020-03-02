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
import awd.mis.servers.dao.MfaceDao;
import awd.mis.servers.entity.ContrastipEntity;
import awd.mis.servers.entity.MfaceEntity;
import awd.mis.servers.service.MfaceService;
import awd.mis.servers.tools.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service("mfaceService")
public class SimpleMfaceService extends GenericEntityService<MfaceEntity, String> implements MfaceService {

    @Autowired
    private MfaceDao mfaceDao;

    @Override
    protected IDGenerator<String> getIDGenerator(MfaceEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public MfaceDao getDao() {
        return mfaceDao;
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
    public String insert(MfaceEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        String n = super.insert(entity);
        cached();
        return n;
    }

    @Override
    @UseDataSource("biometric_ds")
    public String saveOrUpdate(MfaceEntity entity) {
        String n = super.saveOrUpdate(entity);
        cached();
        return n;
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<MfaceEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public MfaceEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<MfaceEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("biometric_ds")
    protected int updateByPk(MfaceEntity entity) {
        int i = super.updateByPk(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(List<MfaceEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(String pk, MfaceEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }

    @Override
    @UseDataSource("biometric_ds")
    public MfaceEntity selectByZjh(String pk) {
        return null == pk ? null : (MfaceEntity) ((Query) this.createQuery().where("zjh", pk)).single();
    }

    @Override
    @UseDataSource("biometric_ds")
    public MfaceEntity selectSingle(Entity param) {
        return super.selectSingle(param);
    }

    @Override
    @UseDataSource("biometric_ds")
    public List<MfaceEntity> select(Entity param) {
        return super.select(param);
    }

    @Override
    @UseDataSource("biometric_ds")
    public void cached() {
        QueryParamEntity queryParamEntity = QueryParamEntity.empty();
        queryParamEntity.excludes("rltx");
        List<MfaceEntity> list = this.select(queryParamEntity);
        CacheUtils.get().removePattern(CacheUtils.CACHE_M_FACE + "*");
        for (MfaceEntity entity : list) {
            String key = CacheUtils.CACHE_M_FACE + entity.getZjh() + entity.getJh()+entity.getJsbh();
            CacheUtils.get().set(key, JSONUtil.toJson(entity));
        }
    }
}
