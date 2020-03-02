package awd.mis.servers.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.RabbitMqServersApi;
import awd.mis.servers.utils.DefaultQueryParam;


@Controller
public class RabbitMqController {
	
	@Autowired
	private RabbitMqServersApi rabbitMqServersApi;
	
	@GetMapping("/getByRabbitApi")
    @ResponseBody
    public ResponseMessage<?> getByRabbitApi(String url) {
    	return rabbitMqServersApi.getByRabbitApi(url);
    }
    
    @PutMapping("/putByRabbitApi")
    @ResponseBody
    public ResponseMessage<?> putByRabbitApi(String url,String param) {
    	return rabbitMqServersApi.putByRabbitApi(url,param);
    }
    
    @DeleteMapping("/deleteByRabbitApi")
    @ResponseBody
    public ResponseMessage<?> deleteByRabbitApi(String url,String param) {
    	return rabbitMqServersApi.deleteByRabbitApi(url,param);
    }
    
    @GetMapping("/getQueues")
    @ResponseBody
    public ResponseMessage<?> getQueues(HttpServletRequest request) {
    	Map<String, String> params = DefaultQueryParam.requestToParam(request);
    	return rabbitMqServersApi.getQueues(params);
    }
    
    @GetMapping("/getQueuesByExchange")
    @ResponseBody
    public ResponseMessage<?> getQueuesByExchange(HttpServletRequest request) {
    	Map<String, String> params = DefaultQueryParam.requestToParam(request);
    	return rabbitMqServersApi.getQueuesByExchange(params);
    }
    
    @GetMapping("/getAwdQueues")
    @ResponseBody
    public ResponseMessage<?> getAwdQueues() {
    	return rabbitMqServersApi.getAwdQueues();
    }
    
    @GetMapping("/getVhosts")
    @ResponseBody
    public ResponseMessage<?> getVhosts() {
    	return rabbitMqServersApi.getVhosts();
    }
    
    @GetMapping("/getExchanges")
    @ResponseBody
    public ResponseMessage<?> getExchanges(HttpServletRequest request) {
    	Map<String, String> params = DefaultQueryParam.requestToParam(request);
    	return rabbitMqServersApi.getExchanges(params);
    }
    
    @GetMapping("/getExchangesGroupByVhost")
    @ResponseBody
    public ResponseMessage<?> getExchangesGroupByVhost(String vhost) {
    	return rabbitMqServersApi.getExchangesGroupByVhost(vhost);
    }
    
    @PostMapping("/addQueue")
    @ResponseBody
    public ResponseMessage<?> addQueue(HttpServletRequest request) {
    	String vhost = request.getParameter("vhost");
    	String queuename = request.getParameter("queuename");
    	String type = request.getParameter("type");
    	String durable = request.getParameter("durable");
    	String auto_delete = request.getParameter("auto_delete");
    	
    	return rabbitMqServersApi.addQueue(vhost, queuename, type, durable, auto_delete);
    }
    
    @PostMapping("/deleteQueue")
    @ResponseBody
    public ResponseMessage<?> deleteQueue(String vhost,String queuename) {
		System.err.println(vhost);
		System.err.println(queuename);
    	return rabbitMqServersApi.deleteQueue(vhost,queuename);
    }
    
    @PostMapping("/addExchange")
    @ResponseBody
    public ResponseMessage<?> addExchange(HttpServletRequest request) {
    	String vhost = request.getParameter("vhost");
    	String name = request.getParameter("name");
    	String type = request.getParameter("type");
    	String durable = request.getParameter("durable");
    	String auto_delete = request.getParameter("auto_delete");
    	
    	return rabbitMqServersApi.addExchange(vhost, name, type, durable, auto_delete);
    }
    
    @PostMapping("/deleteExchange")
    @ResponseBody
    public ResponseMessage<?> deleteExchange(String vhost,String exchange) {
    	return rabbitMqServersApi.deleteExchange(vhost,exchange);
    }
    
    @PostMapping("/bindQueueAndExchange")
    @ResponseBody
    public ResponseMessage<?> bindQueueAndExchange(String vhost,String queuename,String routingkey,String exchange) {
    	return rabbitMqServersApi.bindQueueAndExchange(vhost,queuename,routingkey,exchange);
    }
    
    @PostMapping("/unbindQueueAndExchange")
    @ResponseBody
    public ResponseMessage<?> unbindQueueAndExchange(String vhost,String queuename,String propertieskey,String exchangname) {
    	return rabbitMqServersApi.unbindQueueAndExchange(vhost,queuename,propertieskey,exchangname);
    }
    
    @RequestMapping(value = "/getMsg",method = {RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<?> getMsg(String queuename) {
    	return rabbitMqServersApi.getMsg(queuename);
    }
    
    @RequestMapping(value = "/sendMsg",method = {RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<?> sendMsg(String routingkey,String message) {
    	return rabbitMqServersApi.sendMsg(routingkey,message);
    }
}
