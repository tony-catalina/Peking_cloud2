package awd.mis.servers.service.imp;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.datasource.orm.core.dsl.Query;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.dao.MjjbxxDao;
import awd.mis.servers.entity.MfaceEntity;
import awd.mis.servers.entity.MjjbxxEntity;
import awd.mis.servers.service.MjjbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
public class SimpleMjjbxxService extends GenericEntityService<MjjbxxEntity, String> implements MjjbxxService {
    @Autowired
    private MjjbxxDao mjjbxxDao;

    @Override
    @UseDataSource("read_mjxx")
    public MjjbxxEntity selectByJh(String jh) {
        return mjjbxxDao.selectByJh(jh);
    }




    @Override
    @UseDataSource("write_mjxx")
    public String saveOrUpdate(MjjbxxEntity mjjbxxEntity){
        System.out.println("===========000:"+ JSONUtil.toJson(mjjbxxEntity));
        return super.saveOrUpdate(mjjbxxEntity);
    }

    @Override
    protected IDGenerator<String> getIDGenerator(MjjbxxEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public MjjbxxDao getDao() {
        return mjjbxxDao;
    }

    @Override
    @UseDataSource("write_mjxx")
    public int deleteByPk(String pk) {
        return super.deleteByPk(pk);
    }

    @Override
    @UseDataSource("write_mjxx")
    public String getSEQUID(String jsbh) {
        return super.getSEQUID(jsbh);
    }

    @Override
    @UseDataSource("write_mjxx")
    public String insert(MjjbxxEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        return super.insert(entity);
    }

   /* @Override
    @UseDataSource("biometric_ds")
    public String saveOrUpdate(MjjbxxEntity entity) {
        return super.saveOrUpdate(entity);
    }*/

    @Override
    @UseDataSource("write_mjxx")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<MjjbxxEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("write_mjxx")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public MjjbxxEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("write_mjxx")
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<MjjbxxEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_mjxx")
    protected int updateByPk(MjjbxxEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_mjxx")
    public int updateByPk(List<MjjbxxEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_mjxx")
    public int updateByPk(String pk, MjjbxxEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }


    @Override
    @UseDataSource("write_mjxx")
    public MjjbxxEntity selectSingle(Entity param) {
        return super.selectSingle(param);
    }
}
