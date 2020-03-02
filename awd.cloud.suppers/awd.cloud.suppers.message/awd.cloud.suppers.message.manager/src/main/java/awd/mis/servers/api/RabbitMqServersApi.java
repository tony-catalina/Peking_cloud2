package awd.mis.servers.api;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.hystrix.RabbitMqApiFallBackFactory;
import awd.mis.servers.model.MsgSubscriptionModel;


@FeignClient(name = "AWD-MIS-MESSAGE-SERVER",fallbackFactory = RabbitMqApiFallBackFactory.class)

@Component
@RequestMapping("/rabbitmq")
public interface RabbitMqServersApi {

    @RequestMapping(value = "/getByRabbitApi",method = {RequestMethod.GET})
	ResponseMessage<?> getByRabbitApi(String url);

    @RequestMapping(value = "/putByRabbitApi",method = {RequestMethod.GET})
	ResponseMessage<?> putByRabbitApi(@RequestParam("url") String url,
										@RequestParam("param") String param);

    @RequestMapping(value = "/deleteByRabbitApi",method = {RequestMethod.GET})
	ResponseMessage<?> deleteByRabbitApi(@RequestParam("url") String url,
										@RequestParam("param") String param);

    @PostMapping("/getQueues")
	ResponseMessage<?> getQueues(@RequestBody Map<String, String> params);
    
    @GetMapping("/getAwdQueues")
    ResponseMessage<?> getAwdQueues();

    @GetMapping("/getVhosts")
	ResponseMessage<?> getVhosts();

    @PostMapping("/getExchanges")
	ResponseMessage<?> getExchanges(@RequestBody Map<String, String> params);
    
    @PostMapping("/getQueuesByExchange")
    ResponseMessage<?> getQueuesByExchange(@RequestBody Map<String, String> params);

    @PostMapping("/addExchange")
	ResponseMessage<?> addExchange(@RequestParam("vhost") String vhost,
			   @RequestParam("name") String name,
			   @RequestParam(value = "type",defaultValue = "direct") String type,
			   @RequestParam(value = "durable",defaultValue = "true") String durable,
			   @RequestParam(value = "auto_delete",defaultValue = "false") String auto_delete);
	
    @PostMapping("/getExchangesGroupByVhost")
	ResponseMessage<?> getExchangesGroupByVhost(String vhost);
    
    @PostMapping("/addQueue")
    ResponseMessage<?> addQueue(@RequestParam("vhost") String vhost,
    		@RequestParam("queuename") String queuename,
    		@RequestParam(value = "type",defaultValue = "direct") String type,
    		@RequestParam(value = "durable",defaultValue = "true") String durable,
    		@RequestParam(value = "auto_delete",defaultValue = "false") String auto_delete);

    @PostMapping("/deleteQueue")
	ResponseMessage<?> deleteQueue(@RequestParam("vhost") String vhost,
									@RequestParam("queuename") String queuename);

    @PostMapping("/deleteExchange")
	ResponseMessage<?> deleteExchange(@RequestParam("vhost") String vhost,
									@RequestParam("exchange") String exchange);

    @PostMapping("/bindQueueAndExchange")
    ResponseMessage<?> bindQueueAndExchange(@RequestParam("vhost") String vhost, 
								@RequestParam("queuename") String queuename, 
								@RequestParam("routingkey") String routingkey, 
								@RequestParam("exchange") String exchange);

    @PostMapping("/unbindQueueAndExchange")
    ResponseMessage<?> unbindQueueAndExchange(@RequestParam("vhost") String vhost, 
    							@RequestParam("queuename") String queuename, 
								@RequestParam("propertieskey") String propertieskey, 
								@RequestParam("exchange") String exchange);
    
    @RequestMapping(value = "/getMsg",method = {RequestMethod.POST})
	ResponseMessage<?> getMsg(String queuename);

    @RequestMapping(value = "/sendMsg",method = {RequestMethod.POST})
	ResponseMessage<?> sendMsg(@RequestParam("routingkey") String routingkey,
								@RequestParam("message")  String message);


}
