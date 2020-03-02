package awd.mis.appstore.api.hystrix;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.mis.appstore.api.MessageServersApi;
import awd.mis.appstore.model.MsgSubscriptionModel;
import awd.mis.appstore.model.MsgtypeModel;
import awd.mis.appstore.model.RabbitQueuesModel;
import awd.mis.appstore.model.RabbitUserAndQueueModel;
import awd.mis.appstore.model.RabbitUsersModel;
import feign.hystrix.FallbackFactory;

@Component("messageServersApi")
public class MessageApiFallBackFactory implements FallbackFactory<MessageServersApi> {

    public static Logger logger = Logger.getLogger(MessageServersApi.class);

    @Override
    public MessageServersApi create(Throwable throwable) {
        return new MessageServersApi() {
			@Override
			public ResponseMessage<PagerResult<MsgtypeModel>> getMsgtype(QueryParamEntity queryParamEntity) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				//return ResponseMessage.ok();
				return null;
			}

			@Override
			public ResponseMessage<?> getMsgRabbitQueues(QueryParamEntity queryParamEntity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> addMsgRabbitQueues(RabbitQueuesModel model) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getMsgtypeByQueuename(String queuename,Map<String, String> param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, Object> changeQueueBindOrUnbindExchange(MsgSubscriptionModel model) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getUnbindMsgtypeByQueuename(String queuename, 
																	Map<String, String> param,
																	String vhost, 
																	String exchange) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> deleteQueueEntity(String id, String vhost, String queuename) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<RabbitUsersModel>> getMsgRabbitUsers(QueryParamEntity queryParamEntity) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> addMsgRabbitUsers(RabbitUsersModel model) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> deleteByList(List<RabbitUsersModel> list) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> updateMsgType(String id, MsgtypeModel model) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> deleteMsgType(String id, String routingkey) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> deleteMsgRabbitUsers(RabbitUsersModel model) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> updateMsgRabbitUsers(String id, RabbitUsersModel model) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> addUserAndQueue(RabbitUserAndQueueModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> addMsgType(MsgtypeModel model) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<?> getQueueByMsgtype(String queuename,Map<String, String> param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> saveListByStore(List<String> routingkeyList, String vhost, String queuename,
					String exchange) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<MsgSubscriptionModel>> getMsgMsgtype(QueryParamEntity queryParamEntity) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public ResponseMessage<?> adminAudit(List<String> ids,String shbz) {
				// TODO Auto-generated method stub
				return null;
			}

		};
    }
}
