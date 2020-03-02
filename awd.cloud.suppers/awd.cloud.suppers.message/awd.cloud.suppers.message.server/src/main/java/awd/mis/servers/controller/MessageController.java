package awd.mis.servers.controller;

import java.util.Arrays;

import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.ApplicationMsgList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.mis.servers.entity.ApplicationMsg;
import awd.mis.servers.entity.MessageContent;
import awd.mis.servers.enums.ClassifyEnum;
import awd.mis.servers.enums.QueueEnum;
import awd.mis.servers.service.SendMessage;
import io.swagger.annotations.Api;


/**
 * @author WS
 * @Description: 队列controller
 * @date 2019/11/14 下午3:42
 */
@RestController
@Api(tags = "Message-Controller", description = "消息发送接口")
@RequestMapping("/awdMessage")
public class MessageController {

	
	@Autowired
	private SendMessage sendMessage;
	
	@RequestMapping(value = "/getAwdMsgApp",method = {RequestMethod.POST})
	@OpenAPI
	public ResponseMessage<?> getAwdMsgApp() {
		System.err.println(Arrays.deepToString(QueueEnum.values()));
		return ResponseMessage.ok(Arrays.deepToString(QueueEnum.values()));
	}
	
    @RequestMapping(value = "/sendMessage/json",method = {RequestMethod.POST})
    @OpenAPI
    @ApiOperation("发送消息")
    @ApiResponses({
    	@ApiResponse(code = 200, message = "查询成功"),
    	@ApiResponse(code = 401, message = "未授权"),
    	@ApiResponse(code = 403, message = "无权限"),
    	@ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("sendMessage/json")
    @HystrixCommand
    public ResponseMessage<?> sendMessage(String msgtype,String message) {
    	try {
    		sendMessage.sendMessage(msgtype,message);
    		return ResponseMessage.ok("消息发送成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseMessage.ok("消息发送失败");
    	}
    }
    
    @RequestMapping(value = "/sendMessage",method = {RequestMethod.POST})
    @OpenAPI
	@ApiOperation("发送消息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "查询成功"),
			@ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"),
			@ApiResponse(code = 404, message = "数据不存在")
	})
	@AccessLogger("sendMessage")
	@HystrixCommand
    public ResponseMessage<?> sendMessage(@RequestBody ApplicationMsg applicationMsg) {
    	try {
    		sendMessage.sendMessage(applicationMsg);
    		return ResponseMessage.ok("消息发送成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseMessage.ok("消息发送失败");
    	}
    }

	@RequestMapping(value = "/sendMessageList",method = {RequestMethod.POST})
	@OpenAPI
	@ApiOperation("批量发送消息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "查询成功"),
			@ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"),
			@ApiResponse(code = 404, message = "数据不存在")
	})
	@AccessLogger("sendMessage")
	public ResponseMessage<?> sendMessageList(@RequestBody ApplicationMsgList applicationMsgList) {
		try {
			sendMessage.sendMessage(applicationMsgList);
			return ResponseMessage.ok("消息发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.ok("消息发送失败");
		}
	}
    @RequestMapping(value = "/sendMessageToAwdApp",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> sendMessageToAwdApp(@RequestParam("receiver") QueueEnum receiver,@RequestBody MessageContent messageContent) {
    	try {
    		sendMessage.sendMessageToAwdApp(receiver,messageContent);
    		return ResponseMessage.ok("消息发送成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseMessage.ok("消息发送失败");
    	}
    }
}
