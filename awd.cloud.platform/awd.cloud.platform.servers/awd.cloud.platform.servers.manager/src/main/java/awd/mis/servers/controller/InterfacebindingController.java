/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.expands.logging.AccessLogger;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.mis.servers.entity.InterfacebindingEntity;
import awd.mis.servers.service.InterfacebindingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RefreshScope
@RequestMapping("/interfacebinding")
@AccessLogger("Interfacebinding")
@Api(tags = "awd-interfacebinding", description = "提供用户绑定接口查询功能") 
public class InterfacebindingController implements GenericEntityController<InterfacebindingEntity, String, QueryParamEntity, InterfacebindingEntity> {

	private InterfacebindingService interfacebindingService;


	@Override
    public InterfacebindingEntity modelToEntity(InterfacebindingEntity model, InterfacebindingEntity entity) {
        return model;
    }

    @Autowired
    public void setInterfacebindingService(InterfacebindingService interfacebindingService) {
        this.interfacebindingService = interfacebindingService;
    }
	 
	@Override
	public CrudService<InterfacebindingEntity, String> getService() {
		return interfacebindingService;
	}

	@Override
	public ResponseMessage<String> saveOrUpdate(@RequestBody InterfacebindingEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody InterfacebindingEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	@ApiOperation("保存用户申请的接口")
	public ResponseMessage<String> save(@RequestBody InterfacebindingEntity data) {
		return GenericEntityController.super.save(data);
	}

	@OpenAPI
	@ApiOperation("自定义查询已申请的接口")
	@Override
	public ResponseMessage<PagerResult<InterfacebindingEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}
	
	@PostMapping("/updateInterfaceBdByCode")
	@OpenAPI
	@ApiOperation("api接口申请审批")
	public ResponseMessage<String> apiSp(String bdzt,String appcode,@RequestBody String[] interface_id) {
		//循环数组更新数据
		for(String str:interface_id) {
			 interfacebindingService.updateInterfaceBdByCode(bdzt, appcode, str);
		}
		return ResponseMessage.ok("审批成功");
	}

	
	@PostMapping("/saveinterfacebinding")
	@OpenAPI
	@ApiOperation("保存用户申请的接口")
	public ResponseMessage<String> save(@RequestBody List<Map<String,Object>> list) {
		System.err.println(list.size());
		String msg = "";
		try {
			for(Map<String,Object> m : list) {
				InterfacebindingEntity ie = new InterfacebindingEntity();
				ie.setAppcode(m.get("appcode").toString());
				ie.setInterfaceId(m.get("id").toString());
				ie.setBdzt("1");
				interfacebindingService.insert(ie);
				msg="保存成功";
			}
		}catch (Exception e) {
			e.printStackTrace();
			msg="保存失败";
				
		}
		return ResponseMessage.ok(msg);
	}
	
	

}
