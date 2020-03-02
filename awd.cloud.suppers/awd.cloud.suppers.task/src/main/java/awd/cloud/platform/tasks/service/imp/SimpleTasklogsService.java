package awd.cloud.platform.tasks.service.imp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.cloud.platform.tasks.dao.TasklogsDao;
import awd.cloud.platform.tasks.entity.TasklogsEntity;
import awd.cloud.platform.tasks.service.TasklogsService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
@Service("tasklogsService")
public class SimpleTasklogsService extends GenericEntityService<TasklogsEntity,String>implements TasklogsService{

        @Autowired
        private TasklogsDao tasklogsDao;

        @Override
        protected IDGenerator<String> getIDGenerator(TasklogsEntity entity){
                String jsbh="";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public TasklogsDao getDao(){
                return tasklogsDao;
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
        public String insert(TasklogsEntity entity){
                return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(TasklogsEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<TasklogsEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public TasklogsEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<TasklogsEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(TasklogsEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<TasklogsEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,TasklogsEntity entity){
        return super.updateByPk(pk,entity);
        }
}
