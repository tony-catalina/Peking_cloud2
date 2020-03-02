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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.mis.servers.config.AwdMqInit;
import awd.mis.servers.entity.MsgtypeEntity;
import awd.mis.servers.service.MsgtypeService;
import awd.mis.servers.utils.AwdMqUtils;
import awd.mis.servers.utils.DoPagin;
import awd.mis.servers.utils.DoPagin.PaginList;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/msg_msgtype")
@AccessLogger("Msgtype")
@Api(tags = "Msgtype-Controller", description = "消息类型")
public class MsgtypeController implements GenericEntityController<MsgtypeEntity, String, QueryParamEntity, MsgtypeEntity> {

    private MsgtypeService msgtypeService;

    @Override
    public MsgtypeEntity modelToEntity(MsgtypeEntity model, MsgtypeEntity entity) {
        return model;
    }

    @Autowired
    public void setMsgtypeService(MsgtypeService msgtypeService) {
        this.msgtypeService = msgtypeService;
    }

    @Override
    public CrudService<MsgtypeEntity, String> getService() {
        return msgtypeService;
    }

    @Override
    @OpenAPI
    @ApiOperation("自定义查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<MsgtypeEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        return ResponseMessage.ok(msgtypeService.selectPager(arg1));
    }

    @Override
    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody MsgtypeEntity data) {
        return GenericEntityController.super.save(data);
    }

    @Override
    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody MsgtypeEntity data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

    @Override
    @PatchMapping
    @AccessLogger("{save_or_update}")
    @ApiOperation("保存数据,如果数据不存在则新增一条数据")
    @OpenAPI
    public ResponseMessage<String> saveOrUpdate(@RequestBody MsgtypeEntity data) {
        return GenericEntityController.super.saveOrUpdate(data);
    }

    @Override
    @GetMapping(path = {"/{id:.+}"})
    @ApiOperation("根据主键查询数据")
    @AccessLogger("{get_by_id}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<MsgtypeEntity> getByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.getByPrimaryKey(id);
    }

    @Override
    @DeleteMapping(path = {"/{id:.+}"})
    @AccessLogger("{delete_by_primary_key}")
    @ApiOperation("根据ID删除数据")
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
    @OpenAPI
    public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.deleteByPrimaryKey(id);
    }
    
	/**
	 * 根据 对列名 查找该队列所订阅的消息类型 
	 */
    @ApiOperation("根据 对列名 查找该队列所订阅的消息类型 ")
	@PostMapping("/getUnbindMsgtypeByQueuename")
	@OpenAPI
	public ResponseMessage<?> getUnbindMsgtypeByQueuename(@RequestParam("queuename") String queuename,
															@RequestBody(required = false) Map<String, String> params,
															String vhost,String exchange) {
    	if (StringUtils.isNullOrEmpty(exchange)) {
			exchange = AwdMqInit.get().getAppexchange();
		}
		if (StringUtils.isNullOrEmpty(vhost)) {
			vhost = "awd";
		}
		Map<String, Object> map = Maps.newHashMap();
		List<Map<String, Object>> list = msgtypeService.getUnbindByQueuename(vhost, exchange, queuename,params);
		if (StringUtils.isNullOrEmpty(list)) {
			map.put("total", 0);
			map.put("data", new ArrayList<>());
		}else {
			String pageIndex = params.get("pageIndex");
			String pageSize = params.get("pageSize");
			if (!StringUtils.isNullOrEmpty(pageIndex) && !StringUtils.isNullOrEmpty(pageSize)) {
				PaginList<?> pagerList = DoPagin.getPaginList(list,pageIndex,pageSize);
				map.put("total", pagerList.getTotal());
				map.put("data", pagerList.getData());
			}else {
				map.put("total", list.size());
				map.put("data", list);
			}
		}
		return ResponseMessage.ok(map);
	}
	
    @ApiOperation("添加消息类型")
    @PostMapping("/addMsgType")
    @HystrixCommand
    @OpenAPI
    public ResponseMessage<?> addMsgType(@RequestBody MsgtypeEntity entity) {
        return ResponseMessage.ok(msgtypeService.addMsgType(entity));
    }
    
    @ApiOperation("根据id修改消息类型")
    @PutMapping(path = {"/updateMsgType/{id:.+}"})
    @HystrixCommand
    @OpenAPI
    public ResponseMessage<?> updateMsgType(@PathVariable String id, @RequestBody MsgtypeEntity data) {
        return ResponseMessage.ok(msgtypeService.updateMsgType(id,data));
    }
    
    @ApiOperation("根据id和routinkey 删除消息类型")
    @DeleteMapping(path = {"/deleteMsgType"})
    @HystrixCommand
    @OpenAPI
    public ResponseMessage<?> deleteMsgType(@RequestParam("id") String id, @RequestParam("routingkey") String routingkey) {
    	try {
    		int num = msgtypeService.deleteMsgType(id,routingkey);
    		if (num == 1) {
    			return ResponseMessage.ok("该消息类型删除成功!");
			}
    		return ResponseMessage.error("该消息类型删除失败!");
		} catch (Exception e) {
			return ResponseMessage.error("该消息类型删除失败!");
		}
    }
}
