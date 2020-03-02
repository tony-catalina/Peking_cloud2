package awd.mis.servers.service.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.rabbitmq.client.Connection;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.config.AwdRabbitQueuesProperties;
import awd.mis.servers.config.RabbitMQConnection;
import awd.mis.servers.dao.MsgSubscriptionDao;
import awd.mis.servers.entity.MsgSubscriptionEntity;
import awd.mis.servers.entity.MsgtypeEntity;
import awd.mis.servers.service.MsgSubscriptionService;
import awd.mis.servers.service.MsgtypeService;
import awd.mis.servers.service.RabbitQueuesService;
import awd.mis.servers.utils.AwdMqUtils;
import awd.mis.servers.utils.DefaultQueryParam;
@Service("msgSubscriptionService")
public class SimpleMsgSubscriptionService extends GenericEntityService<MsgSubscriptionEntity,String>implements MsgSubscriptionService{

        @Autowired
        private MsgSubscriptionDao msgSubscriptionDao;

        @Autowired
        private MsgtypeService msgtypeService;
        
        @Autowired
        private AwdRabbitQueuesProperties rabbitMqProperties;
        
        @Autowired
        private RabbitQueuesService rabbitQueuesService;
        
        @Override
        protected IDGenerator<String> getIDGenerator(MsgSubscriptionEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public MsgSubscriptionDao getDao(){
                return msgSubscriptionDao;
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
        public String insert(MsgSubscriptionEntity entity){
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(MsgSubscriptionEntity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<MsgSubscriptionEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public MsgSubscriptionEntity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<MsgSubscriptionEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(MsgSubscriptionEntity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<MsgSubscriptionEntity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,MsgSubscriptionEntity entity){
        	return super.updateByPk(pk,entity);
        }

		@Override
        @UseDataSource("read_ds")
        public List<MsgSubscriptionEntity> select(){
        	return super.select();
        }
		
		@Override
		@UseDataSource("read_ds")
		public List<Map<String, Object>> getMsgtypeByQueuename(String queuename,Map<String, String> params) {
			QueryParamEntity queryParamEntity = DefaultQueryParam.getQueryParamEntity(params);
			queryParamEntity.and("queuename", queuename);
			List<MsgSubscriptionEntity> list = select(queryParamEntity);
			List<Map<String, Object>> msgtypeList = new ArrayList<Map<String, Object>>();
			list.forEach(entity->{
				QueryParamEntity queryMsgtype = DefaultQueryParam.getQueryParamEntity(params);
				queryMsgtype.and("routingkey", entity.getRoutingkey());
				queryMsgtype.and("shbz", "1");
				MsgtypeEntity msgtypeEntity= msgtypeService.selectSingle(queryMsgtype);
				if (!StringUtils.isNullOrEmpty(msgtypeEntity)) {
					Map<String, Object> map = Maps.newHashMap();
					try {
						map = BeanUtils.javaBeanToMap(msgtypeEntity);
						map.put("id", entity.getId());
						map.put("queuename", queuename);
						map.put("vhost", entity.getVhost());
						map.put("exchange", entity.getExchange());
						msgtypeList.add(map);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return msgtypeList;
		}
		

		@Override
		@UseDataSource("read_ds")
		public List<?> getQueueByMsgtype(String msgType,Map<String, String> params) {
			QueryParamEntity queryParamEntity = DefaultQueryParam.getQueryParamEntity(params);
			queryParamEntity.and("routingkey", msgType).and("shbz", "1");
			List<MsgSubscriptionEntity> list = select(queryParamEntity);
			List<Map<String, Object>> queueList = new ArrayList<Map<String, Object>>();
			list.forEach(entity->{
				Map<String, Object> map = Maps.newHashMap();
				String appname = null;
				try {
					map = BeanUtils.javaBeanToMap(entity);
					//根据queuename 找appname
					//先从awd的配置服务中找
					appname = rabbitMqProperties.getAppnameByQueuename(entity.getQueuename());
					if (appname == null) {
						appname = rabbitQueuesService.getAppnameByQueuename(entity.getQueuename());
					}
				} catch (Exception e) {
					//e.printStackTrace();
					appname = entity.getQueuename();
				}
				if (appname == null) {
					appname = entity.getQueuename();
				}
				map.put("appname", appname);
				queueList.add(map);
			});
			return queueList;
		}
		
		/**
		 * 批量绑定
		 */
		@Override
		@UseDataSource("write_ds")
		@TxTransaction
		public List<Map<String, Object>> bindingQueues(List<MsgSubscriptionEntity> list) {
			List<Map<String, Object>> returnList = new ArrayList<>();
			try {
				Connection connection = RabbitMQConnection.get().getConnection();
				list.forEach(entity->{
					AwdMqUtils.get().bindQueueAndExchange(connection,
							returnList, 
							entity.getQueuename(),
							entity.getExchange(), 
							entity.getRoutingkey());
				});
				connection.close();
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
			return returnList;
		}
		
		/**
		 * 绑定，并新增订阅记录
		 */
		@Override
		@UseDataSource("write_ds")
		@TxTransaction
		public Map<String, Object> bindQueueAndExchange(MsgSubscriptionEntity entity) {
			Map<String, Object> map = Maps.newHashMap();
			insert(entity);
			try {
				map = AwdMqUtils.get().
						bindQueueAndExchange(entity.getQueuename(), 
											entity.getExchange(),
											entity.getRoutingkey());
			} catch (IOException e) {
				map.put("msg", "绑定失败");
		    	map.put("code", 500);
			}
			return map;
		}
		
		/**
		 * 解绑，并删除订阅记录
		 */
		@Override
		@UseDataSource("write_ds")
		@TxTransaction
		public Map<String, Object> unbindQueueAndExchange(MsgSubscriptionEntity entity) {
			deleteByPk(entity.getId());
			Map<String, Object> map = Maps.newHashMap();
			try {
				map = AwdMqUtils.get().
						unbindQueueAndExchange(entity.getQueuename(), 
											entity.getExchange(),
											entity.getRoutingkey());
			} catch (IOException e) {
				map.put("msg", "解绑失败");
				map.put("code", 500);
			}
			return map;
		}
		
		/**
		 * 	绑定或者解绑——根据MsgSubscriptionEntity的id来区分，
		 * 	有id 就是解绑，需要删除该条数据，没有id 就是绑定
		 * @param entity
		 * @return
		 * @throws IOException 
		 */
        @Override
        @UseDataSource("write_ds")
        @TxTransaction
		public Map<String, Object> bindOrUnbind(MsgSubscriptionEntity entity) {
			Map<String, Object> map = Maps.newHashMap();
				if (StringUtils.isNullOrEmpty(entity.getId())) {
					try {
						map = AwdMqUtils.get().
								bindQueueAndExchange(entity.getQueuename(), 
													entity.getExchange(),
													entity.getRoutingkey());
						if ((int) map.get("code") == 200) {
							insert(entity);
						}
					} catch (Exception e) {
						e.printStackTrace();
						map.put("msg", "绑定失败");
						map.put("code", 500);
					}
				}else {
					try {
						System.err.println(JSON.toJSON(entity));
						map = AwdMqUtils.get().
								unbindQueueAndExchange(entity.getQueuename(), 
										entity.getExchange(),
										entity.getRoutingkey());
						if ((int) map.get("code") == 200) {
							deleteByPk(entity.getId());
						}
					} catch (IOException e) {
						map.put("msg", "解绑失败");
						map.put("code", 500);
					}
				}
			return map;
		}

		@Override
		public void deleteBindByQueuename(String vhost, String queuename) {
			msgSubscriptionDao.deleteBindByQueuename(vhost,queuename);
		}
		
		/**
		 * 判断该消息类型是否已被该队列订阅
		 */
		@Override
		public boolean isSubscript(String vhost,String exchange,String queuename,String routingkey) {
			int count = msgSubscriptionDao.hasSubscript(vhost, exchange, queuename, routingkey);
			if (count != 0) {
				return true;
			}
			return false;
		}

		/**
		 * 商城应用申请订阅——应用申请，等待审核
		 */
		@Override
		@TxTransaction
		public void saveListByStore(List<String> routingkeyList, String vhost, String queuename, String exchange) {
			routingkeyList.forEach(routingkey->{
				MsgSubscriptionEntity entity = new MsgSubscriptionEntity();
				entity.setVhost(vhost);
				entity.setQueuename(queuename);
				entity.setExchange(exchange);
				entity.setRoutingkey(routingkey);
				entity.setShbz("0");
				insert(entity);
			});
		}
		
		/**
		 * 商城应用申请订阅——管理员审核
		 */
		@Override
		@TxTransaction
		public void updateListByStore(List<String> ids, String shbz) {
			//shzb 为1 代表通过，其他都为不通过，删除该条数据
			ids.forEach(id->{
				if ("1".equals(shbz)) {
					MsgSubscriptionEntity entity = this.selectByPk(id);
					try {
						AwdMqUtils.get().
						bindQueueAndExchange(entity.getQueuename(), 
											entity.getExchange(),
											entity.getRoutingkey());
					} catch (IOException e) {
						e.printStackTrace();
					}
					entity.setShbz("1");
					this.updateByPk(id, entity);
				}else {
					this.deleteByPk(id);
				}
			});
		}

		
		
}
