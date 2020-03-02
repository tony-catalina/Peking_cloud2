package awd.mis.servers.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.dao.MsgtypeDao;
import awd.mis.servers.entity.MsgSubscriptionEntity;
import awd.mis.servers.entity.MsgtypeEntity;
import awd.mis.servers.service.MsgSubscriptionService;
import awd.mis.servers.service.MsgtypeService;
import awd.mis.servers.utils.AwdMqUtils;
import awd.mis.servers.utils.DefaultQueryParam;
import awd.mis.servers.utils.DoPagin;
import awd.mis.servers.utils.DoPagin.PaginList;
@Service("msgtypeService")
public class SimpleMsgtypeService extends GenericEntityService<MsgtypeEntity,String>implements MsgtypeService{

        @Autowired
        private MsgtypeDao msgtypeDao;
        
        @Autowired
        private MsgSubscriptionService msgSubscriptionService;

        @Override
        protected IDGenerator<String> getIDGenerator(MsgtypeEntity entity){
                String jsbh="000000000";
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public MsgtypeDao getDao(){
                return msgtypeDao;
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
        public String insert(MsgtypeEntity entity){
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(MsgtypeEntity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<MsgtypeEntity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public MsgtypeEntity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<MsgtypeEntity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(MsgtypeEntity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<MsgtypeEntity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,MsgtypeEntity entity){
        	return super.updateByPk(pk,entity);
        }
        
        @Override
        @UseDataSource("write_ds")
        public Map<String, Object> addMsgType(MsgtypeEntity entity){
        	Map<String, Object> map = Maps.newHashMap();
        	entity = excute(entity);
        	if (msgtypeDao.hasMsgtype(entity.getRoutingkey()) != 0) {
				map.put("code", 500);
				map.put("msg", "该消息类型"+entity.getBusinessname()+"已存在，请重新添加！");
				return map;
			}
        	insert(entity);
        	map.put("code", 200);
        	map.put("msg", "消息类型"+entity.getBusinessname()+"添加成功！");
        	return map;
        }
        
        @Override
        @UseDataSource("write_ds")
        public Map<String, Object> updateMsgType(String id,MsgtypeEntity entity) {
        	
        	MsgtypeEntity oldEntity = selectByPk(id);
        	String oldRoutingkey = oldEntity.getRoutingkey();
        	//重新处理entity
        	Map<String, Object> map = Maps.newHashMap();
        	BeanUtils.copyProperties(entity, oldEntity);
        	entity = excute(oldEntity);
        	if (msgtypeDao.hasMsgtype(entity.getRoutingkey()) != 0) {
        		map.put("code", 500);
        		map.put("msg", "该消息类型修改未生效，请重新修改！");
        		return map;
			}
        	
        	/**
        	 * 我们Routingkey 是根据MsgtypeEntity 的属性生成的，修改的话也会变更Routingkey，
        	 * 为了使以前订阅该消息的应用还能用，我们需要进行处理，吧以前订阅的Routingkey 换成新的
        	 */
        	QueryParamEntity querySubscript = new QueryParamEntity();
        	querySubscript.and("routingkey", oldRoutingkey);
        	List<MsgSubscriptionEntity> msgSubscriptList = msgSubscriptionService.select(querySubscript);
        	List<MsgSubscriptionEntity> newMsgSubscriptList = new ArrayList<>();	//存放修改之后的订阅记录
        	String newRoutingkey = entity.getRoutingkey();
        	msgSubscriptList.forEach(msgSubscript->{
        		try {
					AwdMqUtils.get().unbindQueueAndExchange(msgSubscript.getQueuename(), msgSubscript.getExchange(), oldRoutingkey);
				} catch (IOException e) {
					//e.printStackTrace();
				}
        		//更新数据库的订阅表
        		msgSubscript.setRoutingkey(newRoutingkey);
        		msgSubscriptionService.saveOrUpdate(msgSubscript);
        		newMsgSubscriptList.add(msgSubscript);
        	});
        	
        	//重新绑定——批量绑定
        	msgSubscriptionService.bindingQueues(msgSubscriptList);
        	
        	saveOrUpdate(entity);
        	map.put("code", 200);
        	map.put("msg", "消息类型修改成功！");
        	return map;
        }
        
        @Override
        @UseDataSource("write_ds")
        public int deleteMsgType(String id,String routingkey) {
        	
        	Map<String, Object> map = Maps.newHashMap();
        	/**
        	 * 删除消息类型，该订阅记录也应该删除
        	 */
        	QueryParamEntity querySubscript = new QueryParamEntity();
        	querySubscript.and("routingkey", routingkey);
        	List<MsgSubscriptionEntity> msgSubscriptList = msgSubscriptionService.select(querySubscript);
        	msgSubscriptList.forEach(msgSubscript->{
        		//解绑，并删除订阅记录
        		msgSubscriptionService.unbindQueueAndExchange(msgSubscript);
        	});
        	return deleteByPk(id);
        }
        
        private MsgtypeEntity excute(MsgtypeEntity entity) {
        	if (StringUtils.isNullOrEmpty(entity.getAppname())) {
        		entity.setAppid("");
			}else {
				entity.setAppid(Pinyin4j.cn2PinyinZt(entity.getAppname()));
			}
        	if (StringUtils.isNullOrEmpty(entity.getMsgtype())) {
        		entity.setMsgtype("");
        	}else {
        		entity.setMsgtype(entity.getMsgtype().toUpperCase());
			}
        	if (StringUtils.isNullOrEmpty(entity.getRolecode())) {
        		entity.setRolecode("");
        	}
        	if (StringUtils.isNullOrEmpty(entity.getBusinessname())) {
        		entity.setBusinessid("");
        	}else {
        		entity.setBusinessid(Pinyin4j.cn2PinyinZt(entity.getBusinessname()));
			}
        	String routingkey = entity.getAppid()+
			        			"_"+entity.getMsgtype()+
			        			"_"+entity.getRolecode()+
			        			"_"+entity.getBusinessid();
        	entity.setRoutingkey(routingkey);
        	return entity;
		}
        
        
		/**
		 * 	根据对列名 获取为订阅的消息规则
		 * @param vhost
		 * @param queuename
		 */
        @Override
        @UseDataSource("read_ds")
		public List<Map<String, Object>> getUnbindByQueuename(String vhost, String exchange,String queuename,Map<String, String> params) {
        	QueryParamEntity queryParamEntity = DefaultQueryParam.getQueryParamEntity(params);
        	List<MsgtypeEntity> entityList = select(queryParamEntity);
			List<Map<String, Object>> msgtypeList = new ArrayList<Map<String, Object>>();
			entityList.forEach(entity->{
				if (!msgSubscriptionService.isSubscript(vhost, exchange, queuename, entity.getRoutingkey())) {
					Map<String, Object> map = Maps.newHashMap();
					try {
						map = BeanUtils.javaBeanToMap(entity);
						map.put("queuename", queuename);
						map.put("vhost", vhost);
						map.put("exchange", exchange);
						msgtypeList.add(map);
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			});
			return msgtypeList;
		}
		
}
