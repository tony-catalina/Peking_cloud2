package awd.mis.appstore.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.util.StringUtil;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.mis.appstore.api.MessageServersApi;
import awd.mis.appstore.model.MsgSubscriptionModel;
import awd.mis.appstore.model.MsgtypeModel;
import awd.mis.appstore.model.RabbitUsersModel;
import awd.mis.appstore.tools.DefaultQueryParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageServersApi messageServersApi;
	
	
	/**
	 * 用户队列账号
	 * @param request
	 * @return
	 */
	@GetMapping("/getUerQueuename")
	@ResponseBody
	public ResponseMessage<Map<String, Object>> getUerQueuename(HttpServletRequest request) {
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String appcode = request.getParameter("appcode");
			QueryParamEntity param = new QueryParamEntity();
			param.and("appid", TermType.eq,appcode);
			ResponseMessage<PagerResult<RabbitUsersModel>> users = messageServersApi.getMsgRabbitUsers(param);
			if(users.getResult().getTotal()>0) {
				map.put("data", users.getResult().getData().get(0));
				return ResponseMessage.ok(map);
			}else {
				return ResponseMessage.error("用户不存在请联系管理员！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	
	/**
	 * 消息类型查询
	 * @param request
	 * @return
	 */
	@GetMapping("/getXxlx")
	@ResponseBody
	public ResponseMessage<?> getXxlx(HttpServletRequest request) {
		try {
			String appcode = request.getParameter("appcode");
			QueryParamEntity queryParamEntity = DefaultQueryParam.getQueryParamEntity(request);
			Map<String, String> map = DefaultQueryParam.requestToParam(request);
			QueryParamEntity param = new QueryParamEntity();
			param.and("appid", TermType.eq,appcode);
			ResponseMessage<PagerResult<RabbitUsersModel>> users = messageServersApi.getMsgRabbitUsers(param);
			if(users.getResult().getTotal()>0) {
				System.err.println(users.getResult().getData().get(0).getQueuename());
				return messageServersApi.getUnbindMsgtypeByQueuename(users.getResult().getData().get(0).getQueuename(), map,"","");
			}else {
				return messageServersApi.getMsgMsgtype(queryParamEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	
	/**
	 * 消息已订阅
	 * @param request
	 * @return
	 */
	@GetMapping("/getXxydy")
	@ResponseBody
	public ResponseMessage<?> getXxydy(HttpServletRequest request) {
		try {
			String appcode = request.getParameter("appcode");
			QueryParamEntity queryParamEntity = DefaultQueryParam.getQueryParamEntity(request);
			Map<String, String> map = DefaultQueryParam.requestToParam(request);
			QueryParamEntity param = new QueryParamEntity();
			param.and("appid", TermType.eq,appcode);
			ResponseMessage<PagerResult<RabbitUsersModel>> users = messageServersApi.getMsgRabbitUsers(param);
			if(users.getResult().getTotal()>0) {
				return messageServersApi.getMsgtypeByQueuename(users.getResult().getData().get(0).getQueuename(),map);
			}else {
				return ResponseMessage.error("用户不存在请联系管理员！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	
	/**
	 * 消息订阅
	 * @param request
	 * @return
	 */
	@PostMapping("/saveXxDy")
	@ResponseBody
	public ResponseMessage<?> saveXxDy(HttpServletRequest request) {
		try {
			String appcode = request.getParameter("appcode");
			String routingkey = request.getParameter("routingkey");
			if(StringUtils.isNullOrEmpty(appcode)) {
				return ResponseMessage.error("appcode必传！");
			}
			if(StringUtils.isNullOrEmpty(routingkey)) {
				return ResponseMessage.error("routingkey必传！");
			}
			List<String> routingkeyList =  Arrays.asList(routingkey.split(","));
			QueryParamEntity param = new QueryParamEntity();
			param.and("appid", TermType.eq,appcode);
			ResponseMessage<PagerResult<RabbitUsersModel>> users = messageServersApi.getMsgRabbitUsers(param);
			if(users.getResult().getTotal()==0) {
				return ResponseMessage.error("无账号，请联系管理员");
			}
			ResponseMessage<?> mag = messageServersApi.saveListByStore(routingkeyList, "awd",users.getResult().getData().get(0).getQueuename(), "");
			return mag;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	//取消订阅
	@PostMapping("/xxtdDelete")
	@ResponseBody
	public ResponseMessage<Map<String, Object>> xxtdDelete(@ModelAttribute MsgSubscriptionModel msgSubscriptionModel){
		System.err.println("-------------"+JSON.toJSONString(msgSubscriptionModel));
		Map<String, Object> msg = messageServersApi.changeQueueBindOrUnbindExchange(msgSubscriptionModel);
		return ResponseMessage.ok(msg);
	}
	//管理员审核
	@PostMapping("/adminAudit")
	@ResponseBody
	public ResponseMessage<?> adminAudit(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			String shbz = request.getParameter("shbz");
			List<String> ids=Arrays.asList(id.split(",")) ;
			ResponseMessage<?> responseMessage = messageServersApi.adminAudit(ids,shbz);
			return responseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("订阅失败");
		}
	}
}
