package awd.mis.servers.service.imp;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.datasource.orm.core.dsl.Query;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.dao.ZfingerDao;
import awd.mis.servers.entity.MirisEntity;
import awd.mis.servers.entity.ZfaceEntity;
import awd.mis.servers.entity.ZfingerEntity;
import awd.mis.servers.service.ZfingerService;
import awd.mis.servers.tools.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service("zfingerService")
public class SimpleZfingerService extends GenericEntityService<ZfingerEntity, String> implements ZfingerService {

    @Autowired
    private ZfingerDao zfingerDao;

    @Override
    protected IDGenerator<String> getIDGenerator(ZfingerEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public ZfingerDao getDao() {
        return zfingerDao;
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
    public String insert(ZfingerEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        String i = super.insert(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    public String saveOrUpdate(ZfingerEntity entity) {
        String i = super.saveOrUpdate(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<ZfingerEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public ZfingerEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<ZfingerEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("biometric_ds")
    protected int updateByPk(ZfingerEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(List<ZfingerEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(String pk, ZfingerEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }

    /**
     * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
     */

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public ResponseMessage<PagerResult<Map<String, Object>>> jbxxlist(Entity param) {
        PagerResult page = new PagerResult<List<Map<String, Object>>>();
        List<Map<String, Object>> list = getDao().jbxxlist(param);
        list.forEach(x -> {
            if (!StringUtils.isNullOrEmpty(x.get("xb"))) {
                x.put("xbString", CacheUtils.get().getDictionary("XB", x.get("xb").toString()));
            }
            if (!StringUtils.isNullOrEmpty(x.get("hjd"))) {
                x.put("hjdString", CacheUtils.get().getDictionary("XZQH", x.get("hjd").toString()));
            }
            if (!StringUtils.isNullOrEmpty(x.get("ay"))) {
                x.put("ayString", CacheUtils.get().getDictionary("JLSAJLB", x.get("ay").toString()));
            }
            if (!StringUtils.isNullOrEmpty(x.get("bahj"))) {
                x.put("bahjString", CacheUtils.get().getDictionary("BAJD", x.get("bahj").toString()));
            }
            if (!StringUtils.isNullOrEmpty(x.get("rsrq"))) {
                x.put("rsrqString", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.get("rsrq")));
            }
            if (!StringUtils.isNullOrEmpty(x.get("cssj"))) {
                x.put("cssjString", new SimpleDateFormat("yyyy-MM-dd").format(x.get("cssj")));
            }

        });

        page.setData(list);
        page.setTotal(getDao().jbxxcount(param));
        return ResponseMessage.ok(page);
    }

    @Override
    @UseDataSource("biometric_ds")
    public ZfingerEntity selectByPk(String rybh, String szbh) {
        return null == rybh ? null : (ZfingerEntity) ((Query) this.createQuery().where("rybh", rybh).and("szbh", szbh)).single();
    }

    @Override
    @UseDataSource("biometric_ds")
    public void cached() {
        QueryParamEntity queryParamEntity = QueryParamEntity.empty();
        queryParamEntity.excludes("zwtx");
        List<ZfingerEntity> list = this.select(queryParamEntity);
        CacheUtils.get().removePattern(CacheUtils.CACHE_Z_FINGER + "*");
        for (ZfingerEntity entity : list) {
            String key = CacheUtils.CACHE_Z_FINGER + entity.getRybh() + entity.getJsbh();
            CacheUtils.get().set(key, JSONUtil.toJson(entity));
        }
    }
}
