package awd.mis.servers.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.mis.servers.api.MessageServersApi;
import awd.mis.servers.model.MsgSubscriptionModel;
import awd.mis.servers.model.MsgtypeModel;
import awd.mis.servers.model.RabbitQueuesModel;
import awd.mis.servers.model.RabbitUserAndQueueModel;
import awd.mis.servers.model.RabbitUsersModel;
import awd.mis.servers.utils.DefaultQueryParam;

@Controller
@RefreshScope
public class MessageController {

	@Autowired
	private MessageServersApi messageServersApi;
	
	/**
	 * 查询消息类型
	 * @param request
	 * @return
	 */
	@GetMapping("/msg_msgtype")
	@ResponseBody
	public ResponseMessage<?> getMsgtype(HttpServletRequest request) {
		QueryParamEntity queryParamEntity = DefaultQueryParam.getQueryParamEntity(request);
        return messageServersApi.getMsgtype(queryParamEntity);
    }
	
	/**
	 * 添加消息类型
	 * @param model
	 * @return
	 */
    @PostMapping("/msg_msgtype/addMsgType")
    @ResponseBody
    public ResponseMessage<?> addMsgType(@RequestBody MsgtypeModel model) {
    	return messageServersApi.addMsgType(model);
    }
    
    /**
     * 	根据id修改消息类型
     * @param id
     * @param model
     * @return
     */
    @PostMapping("/msg_msgtype/updateMsgType/{id}")
    @ResponseBody
    public ResponseMessage<?> updateMsgType(@PathVariable("id") String id,@RequestBody MsgtypeModel model) {
    	return messageServersApi.updateMsgType(id,model);
    }
    
    /**
     * 	根据id和routinkey 删除消息类型
     * @param id
     * @param routingkey
     * @return
     */
    @PostMapping("/msg_msgtype/deleteMsgType")
    @ResponseBody
    public ResponseMessage<?> deleteMsgType(@RequestParam("id") String id, @RequestParam("routingkey") String routingkey) {
    	return messageServersApi.deleteMsgType(id,routingkey);
    }
	
	/**
	 * 获取消息队列
	 * @param request
	 * @return
	 */
	@GetMapping("/msg_rabbitQueues")
	@ResponseBody
	public ResponseMessage<?> getMsgRabbitQueues(HttpServletRequest request) {
		QueryParamEntity queryParamEntity = DefaultQueryParam.getQueryParamEntity(request);
		return messageServersApi.getMsgRabbitQueues(queryParamEntity);
	}
	
	/**
	 * 新增消息队列
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/msg_rabbitQueues")
	@ResponseBody
	public ResponseMessage<?> addMsgRabbitQueues(HttpServletRequest request,@RequestBody RabbitQueuesModel model) {
		return messageServersApi.addMsgRabbitQueues(model);
	}
	
	/**
	 * 查询消息用户
	 * @param request
	 * @return
	 */
	@GetMapping("/msg_rabbitUsers")
	@ResponseBody
	public ResponseMessage<?> getMsgRabbitUsers(HttpServletRequest request) {
		QueryParamEntity queryParamEntity = DefaultQueryParam.getQueryParamEntity(request);
		return messageServersApi.getMsgRabbitUsers(queryParamEntity);
	}
	
	/**
	 * 直接新增用户与消息队列
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/msg_rabbitUsers/addUserAndQueue")
	@ResponseBody
	public ResponseMessage<?> addUserAndQueue(HttpServletRequest request,@RequestBody RabbitUserAndQueueModel model) {
		return messageServersApi.addUserAndQueue(model);
	}
	
	/**
	 * 新增消息用户
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/msg_rabbitUsers")
	@ResponseBody
	public ResponseMessage<?> addMsgRabbitUsers(HttpServletRequest request,@RequestBody RabbitUsersModel model) {
		return messageServersApi.addMsgRabbitUsers(model);
	}
	
	/**
	 * 更新用户名密码
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/msg_rabbitUsers/updateRabbitUser")
	@ResponseBody
	public ResponseMessage<?> updateRabbitUser(HttpServletRequest request,@RequestBody RabbitUsersModel model) {
		return messageServersApi.updateMsgRabbitUsers(model.getId(),model);
	}
	
	/**
	 * 更新消息用户
	 * @param request
	 * @param model
	 * @return
	 */
	@PutMapping("/msg_rabbitUsers/{id}")
	@ResponseBody
	public ResponseMessage<?> updateMsgRabbitUsers(@PathVariable("id") String id,@RequestBody RabbitUsersModel model) {
		return messageServersApi.updateMsgRabbitUsers(id,model);
	}
	
	/**
	 * 根据id删除用户
	 * @param request
	 * @param id
	 * @return
	 */
	@DeleteMapping("/msg_rabbitUsers/deleteUser")
	@ResponseBody
	public ResponseMessage<?> deleteMsgRabbitUsers(HttpServletRequest request,@RequestBody RabbitUsersModel model) {
		return messageServersApi.deleteMsgRabbitUsers(model);
	}
	
	/**
	 * 批量删除用户
	 * @param request
	 * @param list
	 * @return
	 */
	@DeleteMapping("/msg_rabbitUsers/deleteByList")
	@ResponseBody
	public ResponseMessage<?> deleteByList(HttpServletRequest request,@RequestBody List<RabbitUsersModel> list) {
		return messageServersApi.deleteByList(list);
	}
	
	/**
	 * 根据对列名获取绑定的消息类型
	 * @param queuename
	 * @return
	 */
	@GetMapping("/msg_msgsubscription/getMsgtypeByQueuename")
	@ResponseBody
	public ResponseMessage<?> getMsgtypeByQueuename(HttpServletRequest request,@RequestParam("queuename") String queuename) {
		Map<String, String> map = DefaultQueryParam.requestToParam(request);
		return messageServersApi.getMsgtypeByQueuename(queuename,map);
	}
	
	/**
	 * 根据消息类型获取绑定的队列
	 * @param queuename
	 * @return
	 */
	@GetMapping("/msg_msgsubscription/getQueueByMsgtype")
	@ResponseBody
	public ResponseMessage<?> getQueueByMsgtype(HttpServletRequest request,@RequestParam("msgType") String msgType) {
		Map<String, String> map = DefaultQueryParam.requestToParam(request);
		return messageServersApi.getQueueByMsgtype(msgType,map);
	}
	
	/**
	 * 订阅或退订消息类型
	 * @param model
	 * @return
	 */
	@PostMapping("/msg_msgsubscription/changeQueueBindOrUnbindExchange")
	@ResponseBody
	public Map<String, Object> changeQueueBindExchange(@RequestBody MsgSubscriptionModel model) {
		return messageServersApi.changeQueueBindOrUnbindExchange(model);
	}
	
	/**
	 * 根据对列名获取未订阅的消息
	 * @param queuename
	 * @param vhost
	 * @param exchange
	 * @return
	 */
	@GetMapping("/msg_msgtype/getUnbindMsgtypeByQueuename")
	@ResponseBody
	public ResponseMessage<?> getUnbindMsgtypeByQueuename(HttpServletRequest request,String queuename,String vhost,String exchange) {
		Map<String, String> map = DefaultQueryParam.requestToParam(request);
		return messageServersApi.getUnbindMsgtypeByQueuename(queuename,map,vhost,exchange);
	}
	
	/**
	 * 删除队列
	 * @param id
	 * @param vhost
	 * @param queuename
	 * @return
	 */
	@PostMapping("/msg_rabbitQueues/deleteQueueEntity")
	@ResponseBody
	public ResponseMessage<?> deleteQueueEntity(@RequestParam("id") String id,
												@RequestParam("vhost") String vhost,
												@RequestParam("queuename") String queuename){
		return messageServersApi.deleteQueueEntity(id, vhost, queuename);
	}
}
