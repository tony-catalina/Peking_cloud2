package awd.mis.servers.api.hystrix;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.MessageServersApi;
import awd.mis.servers.api.RabbitMqServersApi;
import awd.mis.servers.model.MsgSubscriptionModel;
import feign.hystrix.FallbackFactory;

@Component("rabbitMqServersApi")
public class RabbitMqApiFallBackFactory implements FallbackFactory<RabbitMqServersApi> {

    public static Logger logger = Logger.getLogger(RabbitMqServersApi.class);

    @Override
    public RabbitMqServersApi create(Throwable throwable) {
        return new RabbitMqServersApi() {

			@Override
			public ResponseMessage<?> getByRabbitApi(String url) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> putByRabbitApi(String url, String param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> deleteByRabbitApi(String url, String param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getQueues(@RequestBody Map<String, String> params) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getVhosts() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getExchanges(Map<String, String> param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getExchangesGroupByVhost(String vhost) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> deleteQueue(String vhost, String queuename) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> deleteExchange(String vhost, String exchange) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getMsg(String queuename) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> sendMsg(String routingkey, String message) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getAwdQueues() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> addExchange(String vhost, String name, String type, String durable,
					String auto_delete) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getQueuesByExchange(Map<String, String> params) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> bindQueueAndExchange(String vhost, String queuename, String routingkey, String exchange) {
				return null;
				
			}

			@Override
			public ResponseMessage<?> unbindQueueAndExchange(String vhost, String queuename, String routingkey, String exchange) {
				return null;
			}

			@Override
			public ResponseMessage<?> addQueue(String vhost, String queuename, String type, String durable,
					String auto_delete) {
				// TODO Auto-generated method stub
				return null;
			}
        };
    }
}
