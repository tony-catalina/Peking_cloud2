package awd.mis.servers.service.imp;
import awd.framework.common.entity.PagerResult;
import awd.mis.servers.dao.ApidocDao;
import awd.mis.servers.entity.ApidocEntity;
import awd.mis.servers.service.ApidocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import awd.framework.common.entity.Entity;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("apidocService")
public class SimpleApidocService extends GenericEntityService<ApidocEntity,String>implements ApidocService {

        @Autowired
        private ApidocDao apidocDao;

        @Override
        protected IDGenerator<String> getIDGenerator(ApidocEntity entity){
                String jsbh=entity.getJsbh();
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public ApidocDao getDao(){
                return apidocDao;
        }
        @Override
        @UseDataSource("write_ds")
        public int deleteByPk(String pk){
            return super.deleteByPk(pk);
        }

        @Override
        @UseDataSource("write_ds")
        public String getSEQUID(String jsbh){
        	return super.getSEQUID(jsbh);
        }

        @Override
        @UseDataSource("write_ds")
        public String insert(ApidocEntity entity){
        	entity.setCreatetime(Calendar.getInstance().getTime());
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(ApidocEntity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<ApidocEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public ApidocEntity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<ApidocEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(ApidocEntity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<ApidocEntity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,ApidocEntity entity){
        	entity.setUpdatetime(Calendar.getInstance().getTime());
        	return super.updateByPk(pk,entity);
        }
        
    	/**
    	 * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
    	 */

}
