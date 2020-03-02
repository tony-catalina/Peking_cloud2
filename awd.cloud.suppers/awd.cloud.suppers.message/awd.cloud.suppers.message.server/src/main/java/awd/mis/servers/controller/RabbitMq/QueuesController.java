package awd.mis.servers.controller.RabbitMq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.config.AwdMqInit;
import awd.mis.servers.config.AwdRabbitQueuesProperties;
import awd.mis.servers.utils.AwdMqUtils;
import awd.mis.servers.utils.DoPagin;
import awd.mis.servers.utils.MQUtils;
import awd.mis.servers.utils.DoPagin.PaginList;
import io.swagger.annotations.Api;


/**
 * @author WS
 * @Description: 队列controller
 * @date 2019/11/14 下午3:42
 */
@RestController
@Api(tags = "Queues-Controller", description = "rabbitmq的java操作接口")
@RequestMapping("/rabbitmq")
public class QueuesController {

    @Autowired
    private AwdRabbitQueuesProperties rabbitMqProperties;
    
    
    @RequestMapping(value = "/getByRabbitApi",method = {RequestMethod.GET})
    @OpenAPI
    public ResponseMessage<?> getByRabbitApi(String url,String param) {
    	try {
    		String re = MQUtils.get().getByRabbitApi(url,param);
    		return ResponseMessage.ok(re.toString());
    	} catch (Exception e) {
    		return ResponseMessage.error("失败");
    	}
    }
    
    @RequestMapping(value = "/putByRabbitApi",method = {RequestMethod.PUT})
    @OpenAPI
    public ResponseMessage<?> putByRabbitApi(String url,String param) {
    	try {
    		String re = MQUtils.get().putByRabbitApi(url,param);
    		return ResponseMessage.ok(re.toString());
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResponseMessage.error("失败");
    	}
    }
    
    @RequestMapping(value = "/deleteByRabbitApi",method = {RequestMethod.GET})
    @OpenAPI
    public ResponseMessage<?> deleteByRabbitApi(String url,String param) {
    	try {
    		String re = MQUtils.get().deleteByRabbitApi(url,param);
    		return ResponseMessage.ok(re.toString());
    	} catch (Exception e) {
    		return ResponseMessage.error("失败");
    	}
    }
    
    @PostMapping("/getQueues")
    @OpenAPI
    public ResponseMessage<?> getQueues(@RequestBody(required = false) Map<String, String> params) {
    	Map<String, Object> re = Maps.newHashMap();
    	try {
    		List<?> list = AwdMqUtils.get().getQueues(params);
    		re.put("code", 200);
    		re.put("msg", "队列信息获取成功");
    		String pageIndex = params.get("pageIndex");
			String pageSize = params.get("pageSize");
			if (!StringUtils.isNullOrEmpty(pageIndex) && !StringUtils.isNullOrEmpty(pageSize)) {
				PaginList<?> pagerList = DoPagin.getPaginList(list,pageIndex,pageSize);
				re.put("rows", pagerList.getData());
				re.put("total", pagerList.getTotal());
			}else {
				re.put("rows", list);
				re.put("total", list.size());
			}
    	} catch (Exception e) {
    		re.put("code", 500);
    		re.put("msg", "未获取到队列信息");
    		re.put("rows", new ArrayList<>());
    		re.put("total", 0);
    	}
		return ResponseMessage.ok(re);
    }
    
    @RequestMapping(value = "/getAwdQueues",method = {RequestMethod.GET})
    @OpenAPI
    public ResponseMessage<?> getAwdQueues(String appname) {
    	Map<String, Object> re = Maps.newHashMap();
    	try {
    		List<?> list = rabbitMqProperties.getAwdQueues(appname);
    		re.put("code", 200);
    		re.put("msg", "队列信息获取成功");
    		re.put("rows", list);
    		re.put("total", list.size());
    	} catch (Exception e) {
    		re.put("code", 500);
    		re.put("msg", "未获取到队列信息");
    		re.put("rows", new ArrayList<>());
    		re.put("total", 0);
    	}
    	return ResponseMessage.ok(re);
    }
    
    @RequestMapping(value = "/getVhosts",method = {RequestMethod.GET})
    @OpenAPI
    public ResponseMessage<?> getVhosts() {
    	Map<String, Object> re = Maps.newHashMap();
    	try {
    		List<?> list = AwdMqUtils.get().getVhostsByCache();
    		re.put("code", 200);
    		re.put("msg", "队列信息获取成功");
    		re.put("rows", list);
    		re.put("total", list.size());
    	} catch (Exception e) {
    		re.put("code", 500);
    		re.put("msg", "未获取到队列信息");
    		re.put("rows", new ArrayList<>());
    		re.put("total", 0);
    	}
    	return ResponseMessage.ok(re);
    }
    
    @PostMapping("/getExchanges")
    @OpenAPI
    public ResponseMessage<?> getExchanges(@RequestBody(required = false) Map<String, String> params) {
    	Map<String, Object> re = Maps.newHashMap();
    	try {
    		List<?> list = AwdMqUtils.get().getExchangesByCache(params);
    		re.put("code", 200);
    		re.put("msg", "队列信息获取成功");
    		String pageIndex = params.get("pageIndex");
			String pageSize = params.get("pageSize");
			if (!StringUtils.isNullOrEmpty(pageIndex) && !StringUtils.isNullOrEmpty(pageSize)) {
				PaginList<?> pagerList = DoPagin.getPaginList(list,pageIndex,pageSize);
				re.put("rows", pagerList.getData());
				re.put("total", pagerList.getTotal());
			}else {
				re.put("rows", list);
				re.put("total", list.size());
			}
    		
    	} catch (Exception e) {
    		re.put("code", 500);
    		re.put("msg", "未获取到队列信息");
    		re.put("rows", new ArrayList<>());
    		re.put("total", 0);
    	}
    	return ResponseMessage.ok(re);
    }
    
    @PostMapping("/getQueuesByExchange")
    @OpenAPI
    public ResponseMessage<?> getQueuesByExchange(@RequestBody(required = false) Map<String, String> params) {
    	Map<String, Object> re = Maps.newHashMap();
    	try {
    		List<?> list = AwdMqUtils.get().getQueuesByExchange(params);
    		re.put("code", 200);
    		re.put("msg", "队列信息获取成功");
    		String pageIndex = params.get("pageIndex");
    		String pageSize = params.get("pageSize");
    		if (!StringUtils.isNullOrEmpty(pageIndex) && !StringUtils.isNullOrEmpty(pageSize)) {
    			PaginList<?> pagerList = DoPagin.getPaginList(list,pageIndex,pageSize);
    			re.put("rows", pagerList.getData());
    			re.put("total", pagerList.getTotal());
    		}else {
    			re.put("rows", list);
    			re.put("total", list.size());
    		}
    		
    	} catch (Exception e) {
    		re.put("code", 500);
    		re.put("msg", "未获取到队列信息");
    		re.put("rows", new ArrayList<>());
    		re.put("total", 0);
    	}
    	return ResponseMessage.ok(re);
    }
    
    @RequestMapping(value = "/getExchangesGroupByVhost",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> getExchangesGroupByVhost(String vhost) {
    	Map<String, Object> re = Maps.newHashMap();
    	try {
    		List<?> list = AwdMqUtils.get().getExchangesGroupByVhost(vhost);
    		re.put("code", 200);
    		re.put("msg", "队列信息获取成功");
    		re.put("rows", list);
    		re.put("total", list.size());
    	} catch (Exception e) {
    		re.put("code", 500);
    		re.put("msg", "未获取到队列信息");
    		re.put("rows", new ArrayList<>());
    		re.put("total", 0);
    	}
    	return ResponseMessage.ok(re);
    }
    
    @RequestMapping(value = "/addQueue",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> addQueue(@RequestParam("vhost") String vhost,
    		@RequestParam("queuename") String queuename,
    		@RequestParam(value = "type",defaultValue = "direct") String type,
    		@RequestParam(value = "durable",defaultValue = "true") String durable,
    		@RequestParam(value = "auto_delete",defaultValue = "false") String auto_delete) {
    	try {
    		String re = AwdMqUtils.get().addRabbitQueue(vhost, queuename, type, durable, auto_delete);
    		AwdMqUtils.get().getExchanges();	//刷新交换机缓存
    		return ResponseMessage.ok(re);
    	} catch (Exception e) {
    		return ResponseMessage.error("添加失败");
    	}
    }
    
    @RequestMapping(value = "/deleteQueue",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> deleteQueue(String vhost,String queuename) {
    	Map<String, Object> res = Maps.newHashMap();
    	try {
    		res = AwdMqUtils.get().deleteQueue(vhost,queuename);
    	} catch (Exception e) {
    		//e.printStackTrace();
    		res.put("code", "500");
    		res.put("msg", "删除失败");
    	}
    	return ResponseMessage.ok(res);
    }
    
    @RequestMapping(value = "/addExchange",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> addExchange(@RequestParam("vhost") String vhost,
								    		@RequestParam("name") String name,
								    		@RequestParam(value = "type",defaultValue = "direct") String type,
								    		@RequestParam(value = "durable",defaultValue = "true") String durable,
								    		@RequestParam(value = "auto_delete",defaultValue = "false") String auto_delete) {
    	try {
    		String re = AwdMqUtils.get().addRabbitExchange(vhost, name, type, durable, auto_delete);
    		AwdMqUtils.get().getExchanges();	//刷新交换机缓存
    		return ResponseMessage.ok(re);
    	} catch (Exception e) {
    		return ResponseMessage.error("添加失败");
    	}
    }
    
    @RequestMapping(value = "/deleteExchange",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> deleteExchange(String vhost,String exchange) {
    	Map<String, Object> res = Maps.newHashMap();
    	try {
    		res = AwdMqUtils.get().deleteExchange(vhost,exchange);
    	} catch (Exception e) {
    		e.printStackTrace();
    		res.put("code", "500");
    		res.put("msg", "删除失败");
    	}
    	return ResponseMessage.ok(res);
    }
    
    @RequestMapping(value = "/bindQueueAndExchange",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> bindQueueAndExchange(String vhost,String queuename,String routingkey,String exchange) {
		try {
			String re = AwdMqUtils.get().bindQueueAndExchange(vhost,exchange,queuename,routingkey);
			return ResponseMessage.ok(re);
		} catch (Exception e) {
			return ResponseMessage.error("绑定失败");
		}
    }
    
    @RequestMapping(value = "/unbindQueueAndExchange",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> unbindQueueAndExchange(String vhost,String queuename,String propertieskey,String exchange) {
    	try {
			String re = AwdMqUtils.get().unbindQueueAndExchange(vhost,exchange,queuename,propertieskey);
			return ResponseMessage.ok(re);
		} catch (Exception e) {
			return ResponseMessage.error("解绑失败");
		}
    }
    
    
    @RequestMapping(value = "/getMsg",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> getMsg(String queuename) {
    	String msg = "";
    	try {
    		msg = MQUtils.get().getApiMessage(queuename);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return ResponseMessage.ok(msg);
    }
    
    @RequestMapping(value = "/sendMsg",method = {RequestMethod.POST})
    @OpenAPI
    public ResponseMessage<?> sendMsg(String routingkey,String message) {
    	try {
    		AwdMqUtils.get().sendMessage(null, routingkey, message);
    		return ResponseMessage.ok("消息发送成功");
    	} catch (IOException e) {
    		//e.printStackTrace();
    		return ResponseMessage.ok("消息发送失败");
    	}
    }
}
