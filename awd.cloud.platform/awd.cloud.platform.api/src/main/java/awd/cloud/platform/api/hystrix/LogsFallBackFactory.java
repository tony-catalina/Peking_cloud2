package awd.cloud.platform.api.hystrix;

import awd.bj.logs.model.ExceptiondictionaryModel;
import awd.bj.logs.model.MsgboxModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import awd.cloud.platform.api.LogsService;
import awd.cloud.platform.model.logs.Logs_AnandlbModel;
import awd.cloud.platform.model.logs.Logs_CloudfileModel;
import awd.cloud.platform.model.logs.Logs_DocumentModel;
import awd.cloud.platform.model.logs.Logs_DocumentdetailModel;
import awd.cloud.platform.model.logs.Logs_DocumentfileModel;
import awd.cloud.platform.model.logs.Logs_ExceptiondictionaryModel;
import awd.cloud.platform.model.logs.Logs_ExceptionmessageModel;
import awd.cloud.platform.model.logs.Logs_HfModel;
import awd.cloud.platform.model.logs.Logs_HystrixrecordModel;
import awd.cloud.platform.model.logs.Logs_IntetfacelogsModel;
import awd.cloud.platform.model.logs.Logs_LoginlogsModel;
import awd.cloud.platform.model.logs.Logs_MsgboxModel;
import awd.cloud.platform.model.logs.Logs_OperatelogsModel;
import awd.cloud.platform.model.logs.Logs_PlModel;
import awd.cloud.platform.model.logs.Logs_TasklogsModel;
import awd.cloud.platform.model.logs.Logs_TasksModel;
import awd.cloud.platform.model.logs.Logs_UpdatelogsModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

@Service("logsService")
public class LogsFallBackFactory implements FallbackFactory<LogsService> {
	public static final Logger logger = LoggerFactory.getLogger(LogsService.class);

	@Override
	public LogsService create(Throwable cause) {
		if(cause.getMessage()!=null) {
			cause.printStackTrace();
			logger.info("熔断错误的具体信息: {} " ,cause.getMessage());
		}
		return new LogsService() {

			@Override
			public awd.framework.common.controller.message.ResponseMessage<String> exceptionDictionarySave(ExceptiondictionaryModel data) {
				return null;
			}

			@Override
			public awd.framework.common.controller.message.ResponseMessage<String> save(MsgboxModel data) {
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_AnandlbModel>> anandlb_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> anandlb_save(Logs_AnandlbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> anandlb_updateByKey(String id, Logs_AnandlbModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> anandlb_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_AnandlbModel> anandlb_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_CloudfileModel>> cloudfile_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> cloudfile_save(Logs_CloudfileModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> cloudfile_updateByKey(String id, Logs_CloudfileModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> cloudfile_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_CloudfileModel> cloudfile_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_DocumentdetailModel>> documentdetail_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> documentdetail_save(Logs_DocumentdetailModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> documentdetail_updateByKey(String id, Logs_DocumentdetailModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> documentdetail_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_DocumentdetailModel> documentdetail_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_DocumentfileModel>> documentfile_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> documentfile_save(Logs_DocumentfileModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> documentfile_updateByKey(String id, Logs_DocumentfileModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> documentfile_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_DocumentfileModel> documentfile_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_DocumentModel>> document_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> document_save(Logs_DocumentModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> document_updateByKey(String id, Logs_DocumentModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> document_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_DocumentModel> document_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_ExceptiondictionaryModel>> exceptiondictionary_query(
					QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> exceptiondictionary_save(Logs_ExceptiondictionaryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> exceptiondictionary_updateByKey(String id,
					Logs_ExceptiondictionaryModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> exceptiondictionary_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_ExceptiondictionaryModel> exceptiondictionary_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_ExceptionmessageModel>> exceptionmessage_query(
					QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> exceptionmessage_save(Logs_ExceptionmessageModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> exceptionmessage_updateByKey(String id, Logs_ExceptionmessageModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> exceptionmessage_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_ExceptionmessageModel> exceptionmessage_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_HfModel>> hf_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> hf_save(Logs_HfModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> hf_updateByKey(String id, Logs_HfModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> hf_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_HfModel> hf_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_HystrixrecordModel>> hystrixrecord_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> hystrixrecord_save(Logs_HystrixrecordModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> hystrixrecord_updateByKey(String id, Logs_HystrixrecordModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> hystrixrecord_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_HystrixrecordModel> hystrixrecord_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_IntetfacelogsModel>> intetfacelogs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> intetfacelogs_save(Logs_IntetfacelogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> intetfacelogs_updateByKey(String id, Logs_IntetfacelogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> intetfacelogs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_IntetfacelogsModel> intetfacelogs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_LoginlogsModel>> loginlogs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> loginlogs_save(Logs_LoginlogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> loginlogs_updateByKey(String id, Logs_LoginlogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> loginlogs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_LoginlogsModel> loginlogs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_MsgboxModel>> msgbox_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> msgbox_save(Logs_MsgboxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> msgbox_updateByKey(String id, Logs_MsgboxModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> msgbox_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_MsgboxModel> msgbox_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_OperatelogsModel>> operatelogs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> operatelogs_save(Logs_OperatelogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> operatelogs_updateByKey(String id, Logs_OperatelogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> operatelogs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_OperatelogsModel> operatelogs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_PlModel>> pl_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> pl_save(Logs_PlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> pl_updateByKey(String id, Logs_PlModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> pl_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_PlModel> pl_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_TasklogsModel>> tasklogs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tasklogs_save(Logs_TasklogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tasklogs_updateByKey(String id, Logs_TasklogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tasklogs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_TasklogsModel> tasklogs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_TasksModel>> tasks_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tasks_save(Logs_TasksModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> tasks_updateByKey(String id, Logs_TasksModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> tasks_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_TasksModel> tasks_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<PagerResult<Logs_UpdatelogsModel>> updatelogs_query(QueryParam queryParam) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> updatelogs_save(Logs_UpdatelogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<String> updatelogs_updateByKey(String id, Logs_UpdatelogsModel data) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Integer> updatelogs_deleteByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseMessage<Logs_UpdatelogsModel> updatelogs_getByKey(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			
			
			

		};
	}

}
