/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.WgTree;
import awd.mis.servers.entity.WgzdEntity;
import awd.mis.servers.service.WgzdService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/wgzd")
@AccessLogger("Wgzd")
@Api(tags = "awd-wgzd", description = "提供违规字典管理功能") 
public class WgzdController implements GenericEntityController<WgzdEntity, String, QueryParamEntity, WgzdEntity> {

	private WgzdService wgzdService;


	@Override
    public WgzdEntity modelToEntity(WgzdEntity model, WgzdEntity entity) {
        return model;
    }

    @Autowired
    public void setWgzdService(WgzdService wgzdService) {
        this.wgzdService = wgzdService;
    }
	 
	@Override
	public CrudService<WgzdEntity, String> getService() {
		return wgzdService;
	}
	
	@ApiOperation("根据前台传递的kplx,JSBH获取违规树")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})	
	@HystrixCommand
	@PostMapping("/extMultiCheckboxTree")
	@OpenAPI
    public List<WgTree> extTree(@RequestParam(value="id",required=false) String id){
		String jsbh="310000113";
		 Map<String,String[]> map =Maps.newHashMap();
		 map.put("jsbh", new String []{jsbh});
		 List<WgTree> list = null;
		 List<WgzdEntity> wgzdEntity=wgzdService.select(QueryParamEntity.processRequestParams(map));
		 	WgTree wgtrees =null;
		 	if("1".equals(id)) {
		 		list = new ArrayList<WgTree>();
		 		for(int i =0;i<=5;i++){
		 			wgtrees=new WgTree();
		 			wgtrees.setId("2");
		 			wgtrees.setLeaf("false");
		 			wgtrees.setQtip("hah");
		 			wgtrees.setState("closed");
		 			wgtrees.setText("很严重的违规"+i);
		 			list.add(wgtrees);
		 		}
		 			return list;
		 	}else if("2".equals(id)){
		 		list = new ArrayList<WgTree>(); 
		 		for(int i =0;i<=10;i++) {
			 		wgtrees=new WgTree();
		 			wgtrees.setId("3");
		 			wgtrees.setLeaf("true");
		 			wgtrees.setQtip("hah");
		 			//wgtrees.setState("closed");
		 			wgtrees.setText("最后了"+i);
		 			list.add(wgtrees);
		 		}
	 			
		 		return list;
		 	}else {
			 		list = new ArrayList<WgTree>();
			 		for(int i=0;i<=5;i++) {
			 			int count=0;
			 			if(i%2 == 1) {
			 				count = 1;
			 			}else {
			 				count = 2;
			 			}
			 			wgtrees=new WgTree();
			 			wgtrees.setId("1");
			 			wgtrees.setLeaf("false");
			 			wgtrees.setQtip("hah");
			 			wgtrees.setState("closed");
			 			wgtrees.setText("违规等级"+i);
			 			list.add(wgtrees);
					}
					return list;	
		 	}
			
	 
    }

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<WgzdEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody WgzdEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody WgzdEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody WgzdEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<WgzdEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}
	
	
	
	

}
