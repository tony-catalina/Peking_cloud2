package awd.cloud.suppers.interfaces.service.interfaces.imp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.cloud.suppers.interfaces.dao.UserappDao;
import awd.cloud.suppers.interfaces.entity.UserappEntity;
import awd.cloud.suppers.interfaces.service.interfaces.UserappService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
@Service("userappService")
public class SimpleUserappService extends GenericEntityService<UserappEntity,String>implements UserappService{

        @Autowired
        private UserappDao userappDao;

        @Override
        protected IDGenerator<String> getIDGenerator(UserappEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public UserappDao getDao(){
                return userappDao;
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
        public String insert(UserappEntity entity){
        	entity.setCreatetime(Calendar.getInstance().getTime());
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(UserappEntity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<UserappEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public UserappEntity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<UserappEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(UserappEntity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<UserappEntity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,UserappEntity entity){
        	entity.setUpdatetime(Calendar.getInstance().getTime());
        	return super.updateByPk(pk,entity);
        }
        

}
