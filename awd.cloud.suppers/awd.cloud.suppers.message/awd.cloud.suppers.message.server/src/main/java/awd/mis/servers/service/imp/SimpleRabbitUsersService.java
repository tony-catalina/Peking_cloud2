package awd.mis.servers.service.imp;
import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.RabbitUsersDao;
import awd.mis.servers.entity.RabbitQueuesEntity;
import awd.mis.servers.entity.RabbitUsersEntity;
import awd.mis.servers.service.RabbitQueuesService;
import awd.mis.servers.service.RabbitUsersService;
import awd.mis.servers.utils.AwdMqUtils;
@Service("rabbitUserService")
public class SimpleRabbitUsersService extends GenericEntityService<RabbitUsersEntity,String>implements RabbitUsersService{

        @Autowired
        private RabbitUsersDao rabbitUserDao;
        
    	@Autowired
    	private RabbitQueuesService rabbitQueuesService;
    	
        @Override
        protected IDGenerator<String> getIDGenerator(RabbitUsersEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public RabbitUsersDao getDao(){
                return rabbitUserDao;
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
        public String insert(RabbitUsersEntity entity){
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(RabbitUsersEntity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<RabbitUsersEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public RabbitUsersEntity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<RabbitUsersEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(RabbitUsersEntity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<RabbitUsersEntity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,RabbitUsersEntity entity){
        	return super.updateByPk(pk,entity);
        }
        
        /**
         * 	插入数据库并且添加设置rabbitmq的用户
         * @param entity
         * @throws ClientProtocolException
         * @throws IOException
         */
        @TxTransaction
        @Override
        public void addRabbitUser(RabbitUsersEntity entity) throws ClientProtocolException, IOException {
        	this.insert(entity);
        	AwdMqUtils.get().addRabbitUser(entity);
        	AwdMqUtils.get().setRabbitUserPermission(entity);
		}
        
        /**
         * 	插入数据库并且添加设置rabbitmq的用户
         * @param entity
         * @throws ClientProtocolException
         * @throws IOException
         */
        @TxTransaction
        @Override
        public void updateRabbitUser(String id,RabbitUsersEntity entity) throws ClientProtocolException, IOException {
        	this.updateByPk(id,entity);
        	AwdMqUtils.get().addRabbitUser(entity);
        }
        
        
        @TxTransaction
        @Override
        public void deleteUser(RabbitUsersEntity entity) throws ClientProtocolException, IOException {
        	this.deleteByPk(entity.getId());
        	AwdMqUtils.get().deleteRabbitUser(entity.getUsername());
        }
        
        @TxTransaction
        @Override
        public int deleteByList(List<RabbitUsersEntity> users) {
        	int i = 0;
        	for (RabbitUsersEntity user : users) {
        		try {
        			this.deleteByPk(user.getId());
					AwdMqUtils.get().deleteRabbitUser(user.getUsername());
					i++;
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
        	return i;
        }

		@Override
		@TxTransaction
		public void addUserAndQueue(RabbitUsersEntity user, RabbitQueuesEntity queue) throws ClientProtocolException, IOException {
			//新增rabbit用户
			AwdMqUtils.get().addRabbitUser(user);
			//给用户划分权限
        	AwdMqUtils.get().setRabbitUserPermission(user);
			//增加消息队列
        	AwdMqUtils.get().addRabbitQueue(queue);
			//向数据库增加一条用户数据
        	this.insert(user);
        	//向数据库增加一条队列数据
        	rabbitQueuesService.insert(queue);
		}
        
        
}
