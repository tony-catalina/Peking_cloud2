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
import awd.mis.servers.dao.ZirisDao;
import awd.mis.servers.entity.MirisEntity;
import awd.mis.servers.entity.ZfingerEntity;
import awd.mis.servers.entity.ZirisEntity;
import awd.mis.servers.service.ZirisService;
import awd.mis.servers.tools.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service("zirisService")
public class SimpleZirisService extends GenericEntityService<ZirisEntity, String> implements ZirisService {

    @Autowired
    private ZirisDao zirisDao;

    @Override
    protected IDGenerator<String> getIDGenerator(ZirisEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public ZirisDao getDao() {
        return zirisDao;
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
    public String insert(ZirisEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        String i = super.insert(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    public String saveOrUpdate(ZirisEntity entity) {
        String i = super.saveOrUpdate(entity);
        cached();
        return i;
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<ZirisEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public ZirisEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("biometric_ds")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<ZirisEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("biometric_ds")
    protected int updateByPk(ZirisEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(List<ZirisEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("biometric_ds")
    public int updateByPk(String pk, ZirisEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }

    @Override
    @UseDataSource("biometric_ds")
    public ZirisEntity selectByPk(String rybh, String hmwzbh) {
        return null == rybh ? null : (ZirisEntity) ((Query) this.createQuery().where("rybh", rybh).and("hmwzbh", hmwzbh)).single();
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
    public void cached() {
        QueryParamEntity queryParamEntity = QueryParamEntity.empty();
        queryParamEntity.excludes("hmtx");
        List<ZirisEntity> list = this.select(queryParamEntity);
        CacheUtils.get().removePattern(CacheUtils.CACHE_Z_IRIS + "*");
        for (ZirisEntity entity : list) {
            String key = CacheUtils.CACHE_Z_IRIS + entity.getRybh() + entity.getJsbh()+entity.getId();
            CacheUtils.get().set(key, JSONUtil.toJson(entity));
        }
    }
}
