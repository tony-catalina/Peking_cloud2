package awd.mis.servers.service.imp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.DateTimeUtils;
import awd.framework.common.utils.StringUtils;
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
        @UseDataSource("oracle_ds")
        public int deleteByPk(String pk){
                return super.deleteByPk(pk);
        }

        @Override
    	@UseDataSource("write_ds")
    	public String getSEQUID(String jsbh) {
    		String squid = "";
    		if (StringUtils.isNullOrEmpty(jsbh)) {
    			jsbh = "999999999";
    		}

    		String rq = DateTimeUtils.format(Calendar.getInstance().getTime(), "yyyyMMdd");
    		squid = jsbh + rq + StringUtils.lpad(this.getDao().sequid(), 4, "0");
    		return squid;
    	}
        
        
        @Override
        @UseDataSource("oracle_ds")
        public String insert(YyEntity entity){
                entity.setCreatetime(Calendar.getInstance().getTime());
                return super.insert(entity);
        }

        @Override
        @UseDataSource("oracle_ds")
        public String saveOrUpdate(YyEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("oracle_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<YyEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @UseDataSource("oracle_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public YyEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("oracle_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<YyEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("oracle_ds")
        protected int updateByPk(YyEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("oracle_ds")
        public int updateByPk(List<YyEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("oracle_ds")
        public int updateByPk(String pk,YyEntity entity){
        return super.updateByPk(pk,entity);
        }

        /**
         * 从oracle的数据库中读取数据
         */
        @UseDataSource("oracle_ds")
        @Override
		public List<YyEntity> selectByOracle(Entity entity) {
        	System.err.println("entity=="+JSON.toJSONString(entity));
			System.err.println("从oracle的数据库中读取数据");
        	return yyDao.query(entity);
		}
}
