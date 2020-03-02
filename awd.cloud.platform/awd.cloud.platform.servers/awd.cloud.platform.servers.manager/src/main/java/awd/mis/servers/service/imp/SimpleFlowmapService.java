/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.dao.FlowmapDao;
import awd.mis.servers.entity.FlowmapEntity;
import awd.mis.servers.entity.FlownodeEntity;
import awd.mis.servers.entity.RequestParameters;
import awd.mis.servers.service.FlowmapService;
import awd.mis.servers.service.FlownodeService;
import awd.mis.servers.tools.CacheUtils;

@Service("flowmapService")
@SuppressWarnings("unchecked")
public class SimpleFlowmapService extends GenericEntityService<FlowmapEntity, String> implements FlowmapService {

	@Autowired
    private FlowmapDao flowmapDao;

	@Autowired
	private FlownodeService flownodeService;
	
	@Autowired
	private RedisUtils redisUtils;
    
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(FlowmapEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public FlowmapDao getDao() {
        return flowmapDao;
    }    
    
    @Override
    public String queryForId(String mapname) {
		return flowmapDao.queryForId(mapname);
    }
    
    
    
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public 	boolean loopAdd(Map<String,Object> maps) {

		String[] mapName = null;
		List<FlowmapEntity> flowmapEntityLists = Lists.newArrayList();
		List<Object> flowmapEntityMaps = (List<Object>) maps.get("map") ;
		Map<String,List<FlownodeEntity> > flownodeMap = Maps.newHashMap();
		//JSON转化成对象
		flowmapEntityMaps.forEach((Object o) -> flowmapEntityLists.add(JSONObject.toJavaObject((JSON) o, FlowmapEntity.class)));
		((Map<String, List<Object>>) maps.get("node")).forEach((k,v) -> {
			List<FlownodeEntity> a= Lists.newArrayList();
				v.forEach((b) -> {	
					a.add(JSONObject.toJavaObject((JSON) b, FlownodeEntity.class));
				});
			flownodeMap.put(k, a);
		});
   		  for (FlowmapEntity en : flowmapEntityLists) {
   			if (StringUtils.isNullOrEmpty(en.getMapname())) {
   				return false;
   			}
   			//判断数据是否存在 不存在执行保存
   			mapName = new String[] { en.getMapname() };
   			Map<String, String[]> map = Maps.newHashMap();
   			map.put("mapname$eq", mapName);
   			List<FlowmapEntity> list = this.select(QueryParamEntity.processRequestParams(map));
   			String flowmapId;
   			if (list.size() > 0) {
   				continue;
   			}else {
   				 flowmapId =this.saveOrUpdate(en);
   			}
   			String mapkey = en.getMapkey();
   			//保存节点信息
   			flownodeService.saveNodeForMap(mapkey,flowmapId,flownodeMap.get(en.getMapname()));
	   			//缓存管理
	   			String key = CacheUtils.CACHE_FLOWMAP + en.getJsbh() + "_" + en.getMapkey();
	   			JSONObject redisStr = CacheUtils.get().getFlowMap(en.getJsbh(),en.getMapkey());
	   			String json =JSONObject.toJSONString(en);
	   			boolean a = redisUtils.set(key, json);

   		}  
    	
    	return true;
    }


    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public void updateFlowMapFlowNodeCache(RequestParameters requestParameters){

		String jsbh = requestParameters.getJsbh();
		QueryParamEntity queryParamEntity = new QueryParamEntity();
		FlowmapEntity entity= requestParameters.getFlowmapEntity();

		queryParamEntity.and("jsbh",jsbh).and("state","R2").and("mapkey",entity.getMapkey());
		
		FlowmapEntity flowmapEntity= this.selectSingle(queryParamEntity);
		
		/*System.err.println(flowmapEntity.getJsbh());
		System.err.println(flowmapEntity.getId());
		System.err.println(flowmapEntity.getMapkey());
		System.err.println(flowmapEntity);*/
		//如果为空则执行添加操作-------(更新或者保存，防止id重复)

		if(StringUtils.isNullOrEmpty(flowmapEntity)){
			System.err.println("--添加之前---->："+entity.getId());
			String id = this.saveOrUpdate(entity);
			System.err.println("--添加之后---->："+id);
			entity.setId(id);

		}else{
			entity.setId(flowmapEntity.getId());
			this.updateByPk(entity.getId(),entity);
		}
		String key = CacheUtils.CACHE_FLOWMAP+entity.getMapkey();
		System.err.println(key);
		redisUtils.set(key,JSONObject.toJSONString(entity));

		//流程图获取的节点信息
		List<FlownodeEntity > nodeNew = requestParameters.getFlownodeEntityList();
		for(FlownodeEntity fd:nodeNew) {
			System.err.println(fd.getJsbh());
		}
		//数据库查询出来的节点信息信息
		List<FlownodeEntity > nodeOld = flownodeService.select(QueryParamEntity.single("flowmapid",entity.getId()).and("state","R2"));
		
		if(nodeOld.size()==0){

			for (FlownodeEntity nnew : nodeNew) {

				nnew.setFlowmapid(entity.getId());

				String id = flownodeService.insert(nnew);

				System.err.println("无数据插入--> id为"+ id);

			}

		}else{

			//新流程节点数量大于数据节点数量
			if(nodeOld.size()<nodeNew.size()){

				for (FlownodeEntity nnew : nodeNew) {

					nnew.setFlowmapid(entity.getId());

					boolean flag = true ;

					for (FlownodeEntity old : nodeOld) {

						if(nnew.getNodecode()!=null&&nnew.getNodecode().equals(old.getNodecode())){

							nnew.setId(old.getId());

							System.err.println("新的node	 ID:   "+nnew.getId());

							flownodeService.saveOrUpdate(nnew);

							flag = false;
						}
					}
					if(flag){

						flownodeService.insert(nnew);
					}
				}


			}

			//新流程节点数量小于等于数据节点数量
			if(nodeOld.size()>=nodeNew.size()){

				for (FlownodeEntity old : nodeOld) {

					boolean  flag = true ;

					for (FlownodeEntity nnew : nodeNew) {

						if(nnew.getNodecode()!=null&&nnew.getNodecode().equals(old.getNodecode())){

							nnew.setFlowmapid(entity.getId());

							nnew.setId(old.getId());

							System.err.println("新的node	 ID:   "+nnew.getId());

							flownodeService.saveOrUpdate(nnew);

							flag = false ;
						}
					}
					if(flag){

						int i=  flownodeService.removeByPk(old.getId());

						System.err.println("一共删除了  " +i);
					}
				}


			}

		}
	}

	/** 
	 * @Description: 流程图初始化缓存加载
	 * @param  
	 * @return: void 
	 * @Author: 王帅 
	 * @Date: 2018/4/26 11:33 
	 **/ 
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void initFlowMapCache(){
    	QueryParamEntity queryParamEntity = QueryParamEntity.single("state","R2");
    	List<FlowmapEntity > flowmapEntityList = this.select(queryParamEntity);
    	String key = CacheUtils.CACHE_FLOWMAP;
    	flowmapEntityList.stream()
				.filter(p->"".equals(redisUtils.get(key+p.getJsbh()+"_"+p.getMapkey())))
					.forEach(p->redisUtils.set(key+p.getJsbh()+"_"+p.getMapkey(),JSONObject.toJSONString(p)));
	}

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String  deleteByMapname(String mapname) {
    	String id = flowmapDao.queryForId(mapname);
		int i = this.deleteByPk(id);
		if(!(i>0)) {
			return "";
		}
		int n = flownodeService.deletByFlowmapid(id);
		if(!(n>0)) {
			return "";
		}
		return id;
    }

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int deleteByPk(String pk) {
		return super.deleteByPk(pk);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String getSEQUID(String jsbh) {
		return super.getSEQUID(jsbh);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String insert(FlowmapEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(FlowmapEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	public List<FlowmapEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}

	@Override
	@UseDataSource("read_ds")
	public FlowmapEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	

	@Override
	@UseDataSource("read_ds")
	public PagerResult<FlowmapEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(FlowmapEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<FlowmapEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, FlowmapEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}
    
    
    
    
}
