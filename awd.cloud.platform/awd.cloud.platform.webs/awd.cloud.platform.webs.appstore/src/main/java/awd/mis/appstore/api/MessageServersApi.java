package awd.mis.appstore.api;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.mis.appstore.api.hystrix.MessageApiFallBackFactory;
import awd.mis.appstore.model.MsgSubscriptionModel;
import awd.mis.appstore.model.MsgtypeModel;
import awd.mis.appstore.model.RabbitQueuesModel;
import awd.mis.appstore.model.RabbitUserAndQueueModel;
import awd.mis.appstore.model.RabbitUsersModel;


@FeignClient(name = "AWD-MIS-MESSAGE-SERVER",url = "192.168.4.65:14107",fallbackFactory = MessageApiFallBackFactory.class)

@Component
public interface MessageServersApi {

    @GetMapping("/msg_msgtype")
    ResponseMessage<PagerResult<MsgtypeModel>> getMsgtype(QueryParamEntity queryParamEntity);

    @PostMapping("/msg_msgtype/addMsgType")
	ResponseMessage<?> addMsgType(MsgtypeModel model);

    @PutMapping("/msg_msgtype/updateMsgType/{id}")
	ResponseMessage<?> updateMsgType(@PathVariable("id") String id, MsgtypeModel model);

    @DeleteMapping("/msg_msgtype/deleteMsgType")
	ResponseMessage<?> deleteMsgType(@RequestParam("id") String id, @RequestParam("routingkey") String routingkey);

    
    @GetMapping("/msg_rabbitQueues")
    ResponseMessage<?> getMsgRabbitQueues(QueryParamEntity queryParamEntity);

    @PostMapping("/msg_rabbitQueues")
    ResponseMessage<?> addMsgRabbitQueues(@RequestBody RabbitQueuesModel model);
    
    @GetMapping("/msg_rabbitUsers")
    ResponseMessage<PagerResult<RabbitUsersModel>> getMsgRabbitUsers(QueryParamEntity queryParamEntity);
    
    @PostMapping("/msg_rabbitUsers/addUserAndQueue")
    ResponseMessage<?> addUserAndQueue(@RequestBody RabbitUserAndQueueModel data);
    
    @PostMapping("/msg_rabbitUsers")
    ResponseMessage<?> addMsgRabbitUsers(@RequestBody RabbitUsersModel model);
    
    @PostMapping("/msg_msgsubscription/getMsgtypeByQueuename")
	ResponseMessage<?> getMsgtypeByQueuename(@RequestParam("queuename") String queuename,
												@RequestBody Map<String, String> param);
    
    @GetMapping("/msg_msgsubscription")
    ResponseMessage<PagerResult<MsgSubscriptionModel>> getMsgMsgtype(QueryParamEntity queryParamEntity);
    
    @PostMapping("/msg_msgsubscription/getQueueByMsgtype")
    ResponseMessage<?> getQueueByMsgtype(@RequestParam("msgType") String queuename,
    										@RequestBody Map<String, String> param);

    @PostMapping("/msg_msgsubscription/changeQueueBindOrUnbindExchange")
	Map<String, Object> changeQueueBindOrUnbindExchange(MsgSubscriptionModel model);

    @PostMapping("/msg_msgtype/getUnbindMsgtypeByQueuename")
	ResponseMessage<?> getUnbindMsgtypeByQueuename(@RequestParam("queuename") String queuename,
													@RequestBody Map<String, String> param,
													@RequestParam("vhost") String vhost,
													@RequestParam("exchange") String exchange);

    @PostMapping("/msg_rabbitQueues/deleteQueueEntity")
	ResponseMessage<?> deleteQueueEntity(@RequestParam("id") String id, 
										@RequestParam("vhost") String vhost, 
										@RequestParam("queuename") String queuename);

    @PutMapping("/msg_rabbitUsers/{id}")
	ResponseMessage<?> updateMsgRabbitUsers(@PathVariable("id") String id,@RequestBody RabbitUsersModel model);

    @DeleteMapping("/msg_rabbitUsers/deleteUser")
    ResponseMessage<?> deleteMsgRabbitUsers(@RequestBody RabbitUsersModel model);

    @DeleteMapping("/msg_rabbitUsers/deleteByList")
	ResponseMessage<?> deleteByList(@RequestBody List<RabbitUsersModel> list);
    
    @PostMapping("/msg_msgsubscription/saveListByStore")
    ResponseMessage<String> saveListByStore(@RequestBody List<String> routingkeyList,
			@RequestParam("vhost") String vhost,
			@RequestParam("queuename") String queuename,
			@RequestParam(value = "exchange",required = false) String exchange);
    @PostMapping("/msg_msgsubscription/updateListByStore")
    ResponseMessage<?> adminAudit(@RequestBody List<String> ids,@RequestParam("shbz") String shbz);

}
