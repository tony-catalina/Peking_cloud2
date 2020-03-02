package awd.cloud.platform.tasks.service.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.cloud.platform.tasks.dao.TasksDao;
import awd.cloud.platform.tasks.entity.TasksEntity;
import awd.cloud.platform.tasks.quartz.job.ScheduleJob;
import awd.cloud.platform.tasks.quartz.service.ScheduleJobService;
import awd.cloud.platform.tasks.service.TasksService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
@Service("tasksService")
public class SimpleTasksService extends GenericEntityService<TasksEntity,String>implements TasksService{

        @Autowired
        private TasksDao tasksDao;
        
        @Autowired 
    	private ScheduleJobService scheduleJobService;

        @Override
        protected IDGenerator<String> getIDGenerator(TasksEntity entity){
                String jsbh="";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public TasksDao getDao(){
                return tasksDao;
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
        public String insert(TasksEntity entity){
        		String id=super.insert(entity);
        		ScheduleJob job=new ScheduleJob();
        		job.setJobId(id);
        		job.setCronExpression(entity.getSchedule());
        		job.setDesc(entity.getTaskname());
        		job.setInterfaceName("");
        		job.setJobData(entity.getParams());
        		job.setJobGroup(entity.getTasktype());
        		job.setJobName(entity.getTaskname());
        		job.setJobStatus(entity.getExstate());
        		job.setLastDate(null);
        		try {
					scheduleJobService.saveOrupdate(job);
				} catch (Exception e) {
					e.printStackTrace();
				}        		
                return id;
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(TasksEntity entity){
                return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<TasksEntity> selectByPk(List<String> id){
        return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public TasksEntity selectByPk(String pk){
        return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<TasksEntity> selectPager(Entity arg0){
        return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(TasksEntity entity){
        return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<TasksEntity> data){
        return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,TasksEntity entity){
        return super.updateByPk(pk,entity);
        }
}
