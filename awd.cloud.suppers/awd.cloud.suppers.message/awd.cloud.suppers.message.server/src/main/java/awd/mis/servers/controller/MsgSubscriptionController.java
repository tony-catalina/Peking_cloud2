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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.config.AwdMqInit;
import awd.mis.servers.entity.MsgSubscriptionEntity;
import awd.mis.servers.service.MsgSubscriptionService;
import awd.mis.servers.utils.DoPagin;
import awd.mis.servers.utils.DoPagin.PaginList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/msg_msgsubscription")
@AccessLogger("MsgSubscription")
@Api(tags = "MsgSubscription-Controller",description = "消息订阅") 
public class MsgSubscriptionController implements GenericEntityController<MsgSubscriptionEntity, String, QueryParamEntity, MsgSubscriptionEntity> {

	private MsgSubscriptionService msgSubscriptionService;

	@Override
    public MsgSubscriptionEntity modelToEntity(MsgSubscriptionEntity model, MsgSubscriptionEntity entity) {
        return model;
    }

    @Autowired
    public void setRabbitUserService(MsgSubscriptionService msgSubscriptionService) {
        this.msgSubscriptionService = msgSubscriptionService;
    }
	 
	@Override
	public CrudService<MsgSubscriptionEntity, String> getService() {
		return msgSubscriptionService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<MsgSubscriptionEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return ResponseMessage.ok(msgSubscriptionService.selectPager(arg1));
	}

	@ApiOperation("商城应用申请订阅——应用申请，等待审核")
	@PostMapping("/saveListByStore")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> saveListByStore(@RequestBody List<String> routingkeyList,
												@RequestParam("vhost") String vhost,
												@RequestParam("queuename") String queuename,
												@RequestParam(value = "exchange",required = false) String exchange) {
		if (StringUtils.isNullOrEmpty(exchange)) {
			exchange = AwdMqInit.get().getAppexchange();
		}
		
		try {
			msgSubscriptionService.saveListByStore(routingkeyList, vhost, queuename, exchange);
			return ResponseMessage.ok("申请成功");
		} catch (Exception e) {
			return ResponseMessage.error("申请失败");
		}
	}
	
	@ApiOperation("商城应用申请订阅——管理员审核")
	@PostMapping("/updateListByStore")
	@HystrixCommand
	@OpenAPI
	public ResponseMessage<String> updateListByStore(@RequestBody List<String> ids,@RequestParam("shbz") String shbz) {
		
		try {
			msgSubscriptionService.updateListByStore(ids, shbz);
			return ResponseMessage.ok("审核成功");
		} catch (Exception e) {
			return ResponseMessage.error("审核失败");
		}
	}

	/**
	 * 根据 对列名 查找该队列所订阅的消息类型 
	 */
	@ApiOperation("根据 对列名 查找该队列所订阅的消息类型 ")
	@PostMapping("/getMsgtypeByQueuename")
	@OpenAPI
	public ResponseMessage<?> getMsgtypeByQueuename(@RequestParam("queuename") String queuename,
													@RequestBody(required = false) Map<String, String> params) {
		Map<String, Object> map = Maps.newHashMap();
		List<Map<String, Object>> list = msgSubscriptionService.getMsgtypeByQueuename(queuename,params);
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
	
	/**
	 * 根据消息类型 查找订阅的队列 
	 */
	@ApiOperation(" 根据消息类型 查找订阅的队列 ")
	@PostMapping("/getQueueByMsgtype")
	@OpenAPI
	public ResponseMessage<?> getQueueByMsgtype(@RequestParam("msgType") String msgType,
												@RequestBody(required = false) Map<String, String> params) {
		Map<String, Object> map = Maps.newHashMap();
		List<?> list = msgSubscriptionService.getQueueByMsgtype(msgType,params);
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
	
	 /**
	   * 根据订阅表，批量绑定交换机与队列
	   */
	@ApiOperation("根据订阅表，批量绑定交换机与队列")
	@GetMapping("/bindingQueues")
	@OpenAPI
	public ResponseMessage<?> bindingQueues() {
		Map<String, Object> map = Maps.newHashMap();
		List<MsgSubscriptionEntity> MsgSubscriptList = msgSubscriptionService.select();
		List<Map<String, Object>> list = msgSubscriptionService.bindingQueues(MsgSubscriptList);
		if (StringUtils.isNullOrEmpty(list)) {
			map.put("total", 0);
			map.put("data", new ArrayList<>());
		}else {
			map.put("total", list.size());
			map.put("data", list);
		}
		return ResponseMessage.ok(map);
	}
	
	/**
	 * 绑定、解绑
	 * @param queuename
	 * @param routingkey
	 * @param exchange
	 * @param type
	 * @return
	 */
	@ApiOperation("绑定、解绑 ")
    @RequestMapping(value = "/changeQueueBindOrUnbindExchange",method = {RequestMethod.POST})
    @OpenAPI
    public Map<String, Object> changeQueueBindExchange(@RequestBody MsgSubscriptionEntity entity) {
    	if (StringUtils.isNullOrEmpty(entity.getExchange())) {
    		entity.setExchange(AwdMqInit.get().getAppexchange());
		}
    	if (StringUtils.isNullOrEmpty(entity.getShbz())) {
    		entity.setShbz("0");
    	}
    	return msgSubscriptionService.bindOrUnbind(entity);
    }
	
	
}
