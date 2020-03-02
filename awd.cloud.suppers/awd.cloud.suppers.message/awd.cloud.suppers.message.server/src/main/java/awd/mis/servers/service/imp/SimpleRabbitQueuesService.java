package awd.mis.servers.service.imp;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.dao.RabbitQueuesDao;
import awd.mis.servers.entity.RabbitQueuesEntity;
import awd.mis.servers.service.MsgSubscriptionService;
import awd.mis.servers.service.RabbitQueuesService;
import awd.mis.servers.utils.AwdMqUtils;
@Service("rabbitQueuesService")
public class SimpleRabbitQueuesService extends GenericEntityService<RabbitQueuesEntity,String>implements RabbitQueuesService{

        @Autowired
        private RabbitQueuesDao rabbitQueuesDao;
        
        @Autowired
        private MsgSubscriptionService msgSubscriptionService;

        @Override
        protected IDGenerator<String> getIDGenerator(RabbitQueuesEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public RabbitQueuesDao getDao(){
                return rabbitQueuesDao;
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
        public String insert(RabbitQueuesEntity entity){
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(RabbitQueuesEntity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<RabbitQueuesEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public RabbitQueuesEntity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<RabbitQueuesEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(RabbitQueuesEntity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<RabbitQueuesEntity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,RabbitQueuesEntity entity){
        	return super.updateByPk(pk,entity);
        }

		@Override
		@UseDataSource("write_ds")
		public Map<String, Object> deleteQueueEntity(String id, String vhost, String queuename) {
			Map<String,Object> map = Maps.newHashMap();
			int num = deleteByPk(id);
			String msg = "删除失败";
			try {
				Map<String, Object> res = AwdMqUtils.get().deleteQueue(vhost,queuename);
				msg = (String) res.get("msg");
				/**
				 *	 删除消息队列之后 还要清除队列的绑定信息
				 * rabbitmq会自动删除绑定
				 * 	但是我们保存到数据库的订阅规则还在，所以要删除表中的数据
				 */
				msgSubscriptionService.deleteBindByQueuename(vhost,queuename);
			} catch (IOException e) {
				//e.printStackTrace();
			}
			map.put("num", num);
			map.put("msg", msg);
			return map;
		}

		@Override
		public String getAppnameByQueuename(String queuename) {
			QueryParamEntity queryAppname = new QueryParamEntity();
			queryAppname.and("queuename", queuename);
			RabbitQueuesEntity queue = selectSingle(queryAppname);
			if (!StringUtils.isNullOrEmpty(queue)) {
				return queue.getAppname();
			}
			return null;
		}
        

}
