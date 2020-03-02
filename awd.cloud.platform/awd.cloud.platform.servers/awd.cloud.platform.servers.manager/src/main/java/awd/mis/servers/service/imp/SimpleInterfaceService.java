/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.model.InterfaceModel;
import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.InterfaceDao;
import awd.mis.servers.entity.FxpgEntity;
import awd.mis.servers.entity.InterfaceEntity;
import awd.mis.servers.service.InterfaceService;
import awd.mis.servers.tools.CacheUtils;

@Service("interfaceService")
public class SimpleInterfaceService extends GenericEntityService<InterfaceEntity, String> implements InterfaceService {

	@Autowired
    private InterfaceDao interfaceDao;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(InterfaceEntity entity) {
        return () -> {
            return getSEQUID("");
        };
    }

    @Override
    public InterfaceDao getDao() {
        return interfaceDao;
    }
    @Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void cached() {
		List<InterfaceEntity> interfaceEntity = this.select();
		interfaceEntity.forEach(entity->{
			CacheUtils.get().setInterfaceCache(entity);
		});
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ResponseMessage<PagerResult<InterfaceModel>> interfaceBdztQuery(Map<String, Object> param) {
    	List<InterfaceModel> interfaces = this.getDao().selectInterfaceAndBdzt(param);
    	PagerResult<InterfaceModel> pagerResult = new PagerResult<>();
    	pagerResult.setData(interfaces);
    	pagerResult.setTotal(this.getDao().selectInterfaceCount(param));
    	System.err.println("---------------------"+JSONObject.toJSONString(pagerResult));
		return ResponseMessage.ok(pagerResult);
	}

	@Override
	public List<Integer> apiDshslQuery(String[] appcodes) {
    	List<Integer> nums = new ArrayList<>();
    	for(String appcode:appcodes){
    		int num = this.getDao().selectByappcodeCount(appcode,"1");
    		nums.add(num);
		}
		return nums;
	}

	@Override
	public List<Map<String, Object>> getTree() {
		List<JSONObject> interfaces = CacheUtils.get().getCacheList("CACHE_KEY_INTERFACE");
		List<JSONObject> jslxs = CacheUtils.get().getCacheList("cloud_dictionaryDM");
		System.err.println("-----"+jslxs.size());
		List<Map<String,Object>> resultMaps = new ArrayList<>();
		for (JSONObject jsonObject : jslxs){
			System.err.println("-----"+JSONObject.toJSONString(jsonObject));
			Map<String,Object> map = new HashMap<>();
			if ("0".equals(jsonObject.get("code").toString())){
				map.put("pid", "0");
				map.put("isParent", "true");
				map.put("name", "监管业务指导部门");
				List<Map<String,Object>> tableMaps = jkList("0");
				map.put("children",tableMaps);
				resultMaps.add(map);
			}else if ("1".equals(jsonObject.get("code").toString())){
				map.put("pid", "1");
				map.put("isParent", "true");
				map.put("name", "看守所");
				List<Map<String,Object>> tableMaps = jkList("1");
				map.put("children",tableMaps);
				resultMaps.add(map);
			}else if ("2".equals(jsonObject.get("code").toString())){
				map.put("pid", "2");
				map.put("isParent", "true");
				map.put("name", "拘留所");
				List<Map<String,Object>> tableMaps = jkList("2");
				map.put("children",tableMaps);
				resultMaps.add(map);
			}else if ("3".equals(jsonObject.get("code").toString())){
				map.put("pid", "3");
				map.put("isParent", "true");
				map.put("name", "强制戒毒所");
				List<Map<String,Object>> tableMaps = jkList("3");
				map.put("children",tableMaps);
				resultMaps.add(map);
			}else if ("4".equals(jsonObject.get("code").toString())){
				map.put("pid", "4");
				map.put("isParent", "true");
				map.put("name", "收容教育所");
				List<Map<String,Object>> tableMaps = jkList("4");
				map.put("children",tableMaps);
				resultMaps.add(map);
			}else if ("5".equals(jsonObject.get("code").toString())){
				map.put("pid", "5");
				map.put("isParent", "true");
				map.put("name", "安康医院");
				List<Map<String,Object>> tableMaps = jkList("5");
				map.put("children",tableMaps);
				resultMaps.add(map);
			}else if ("6".equals(jsonObject.get("code").toString())){
				map.put("pid", "6");
				map.put("isParent", "true");
				map.put("name", "拘役所");
				List<Map<String,Object>> tableMaps = jkList("6");
				map.put("children",tableMaps);
				resultMaps.add(map);
			}else if ("9".equals(jsonObject.get("code").toString())){
				map.put("pid", "9");
				map.put("isParent", "true");
				map.put("name", "其他");
				List<Map<String,Object>> tableMaps = jkList("9");
				map.put("children",tableMaps);
				resultMaps.add(map);
			}
		}
		return resultMaps;
	}

	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	@Override
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
	public String insert(InterfaceEntity entity) {
		CacheUtils.get().setInterfaceCache(entity);
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(InterfaceEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<InterfaceEntity> select() {
		return super.select();
	}

	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<InterfaceEntity> select(Entity param) {
		return super.select(param);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<InterfaceEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public InterfaceEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<InterfaceEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public InterfaceEntity selectSingle(Entity param) {
		return super.selectSingle(param);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(InterfaceEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<InterfaceEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, InterfaceEntity entity) {
		return super.updateByPk(pk, entity);
	}
	
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	@Override
	public PagerResult<InterfaceEntity> selectByappcode(String appcode,String bdzt,String start,String end) {
		PagerResult<InterfaceEntity> pr = new PagerResult<>(); 
		pr.setData(interfaceDao.selectByappcode(appcode,bdzt, start, end));
		pr.setTotal(interfaceDao.selectByappcodeCount(appcode, bdzt));
		return pr;
	}

	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<InterfaceEntity>  getApiParentNode() {
		
		return interfaceDao.getApiParentNode();
	}

	public List<Map<String,Object>> jkList(String type){
		List<String> tableNames = this.getDao().getTableGroup(type);
		tableNames.remove(null);
		List<Map<String,Object>> tableMaps = new ArrayList<>();
		System.err.println("--------------size"+tableNames.size());
		if (tableNames.size()>0){
			for (String tabls : tableNames){
				Map<String,Object> kssMap = new HashMap<>();
				if ("QT".equals(tabls)){
					kssMap.put("pid","QT");
					kssMap.put("isParent", "true");
					kssMap.put("name","其它");
					tableMaps.add(kssMap);
				}else {
					int lastIndex = tabls.lastIndexOf("_");
					String km = tabls.substring(0,lastIndex).toUpperCase();
					String bm = tabls.substring(lastIndex+1).toUpperCase();
					System.err.println("-------------tabls"+tabls);
					List<JSONObject> tables =  CacheUtils.get().getCacheList("CACHE_KEY_TABLEINFO"+km+bm);
					System.err.println("tablessssss=-=========="+JSONObject.toJSONString(tables));
					if (tables.size()>0){
						kssMap.put("pid",tables.get(0).get("tablename").toString());
						kssMap.put("isParent", "true");
						kssMap.put("name",tables.get(0).get("tablenamezh").toString());
					}else{
						kssMap.put("pid",bm);
						kssMap.put("isParent", "true");
						kssMap.put("name",bm);
					}

					tableMaps.add(kssMap);
				}

			}
		}else {
			return tableMaps;
		}
    	return tableMaps;
	}
    
	
    
    
    
}
