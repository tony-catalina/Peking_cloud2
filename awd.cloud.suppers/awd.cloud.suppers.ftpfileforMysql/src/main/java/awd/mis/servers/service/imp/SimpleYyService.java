package awd.mis.servers.service.imp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.YyDao;
import awd.mis.servers.entity.YyEntity;
import awd.mis.servers.service.YyService;
@Service("yyService")
public class SimpleYyService extends GenericEntityService<YyEntity,String>implements YyService{

        @Autowired
        private YyDao yyDao;

        @Override
        protected IDGenerator<String> getIDGenerator(YyEntity entity){
                String jsbh=entity.getJsbh();
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public YyDao getDao(){
                return yyDao;
        }
        @Override
        public int deleteByPk(String pk){
                return super.deleteByPk(pk);
        }

        @Override
        public String getSEQUID(String jsbh){
                return super.getSEQUID(jsbh);
        }

        @Override
        @UseDataSource("mysql_ds")
        public String insert(YyEntity entity){
                entity.setCreatetime(Calendar.getInstance().getTime());
                return super.insert(entity);
        }

        @Override
        @UseDataSource("mysql_ds")
        public String saveOrUpdate(YyEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<YyEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public YyEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<YyEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("mysql_ds")
        protected int updateByPk(YyEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("mysql_ds")
        public int updateByPk(List<YyEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("mysql_ds")
        public int updateByPk(String pk,YyEntity entity){
        return super.updateByPk(pk,entity);
        }
        
       /* @Override
        @UseDataSource("mysql_ds")
        public YyEntity selectSingle(Entity param) {
    		return super.selectSingle(param);
    	}*/
        
        /**
         * 从mysql的数据库中读取数据
         */
        @UseDataSource("mysql_ds")
        @Override
        public List<YyEntity> selectByMysql(Entity entity) {
        	System.err.println("从mysql的数据库中读取数据");
        	return super.select(entity);
		}
}
