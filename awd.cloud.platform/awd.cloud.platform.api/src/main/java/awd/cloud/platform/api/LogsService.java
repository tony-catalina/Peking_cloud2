package awd.cloud.platform.api;

import awd.bj.logs.model.ExceptiondictionaryModel;
import awd.bj.logs.model.MsgboxModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import awd.cloud.platform.api.hystrix.LogsFallBackFactory;
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


@FeignClient(name = "${awd.api:AWD-MIS-GATEWAY-SERVER}",fallbackFactory=LogsFallBackFactory.class)
public interface LogsService {
	@PostMapping("/exceptiondictionary")
	awd.framework.common.controller.message.ResponseMessage<String> exceptionDictionarySave(@RequestBody ExceptiondictionaryModel data);

	@PostMapping("/msgbox")
	awd.framework.common.controller.message.ResponseMessage<String> save(@RequestBody MsgboxModel data);
	///////////////////////////////////////////////////////////////////////////////////////////
		
	@GetMapping("/logs/anandlb")
	ResponseMessage<PagerResult<Logs_AnandlbModel>> anandlb_query(QueryParam queryParam);
	
	@PostMapping("/logs/anandlb")
	ResponseMessage<String> anandlb_save(@RequestBody Logs_AnandlbModel data);
	
	@PatchMapping("/logs/anandlb/{id}")
	ResponseMessage<String> anandlb_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_AnandlbModel data);
	
	@DeleteMapping("/logs/anandlb/{id}")
	ResponseMessage<Integer> anandlb_deleteByKey(@PathVariable(value = "id") String id);
	
	@GetMapping("/logs/anandlb/{id}")
	ResponseMessage<Logs_AnandlbModel> anandlb_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/cloudfile")
	ResponseMessage<PagerResult<Logs_CloudfileModel>> cloudfile_query(QueryParam queryParam);
	
	@PostMapping("/logs/cloudfile")
	ResponseMessage<String> cloudfile_save(@RequestBody Logs_CloudfileModel data);

	@PatchMapping("/logs/cloudfile/{id}")
	ResponseMessage<String> cloudfile_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_CloudfileModel data);

	@DeleteMapping("/logs/cloudfile/{id}")
	ResponseMessage<Integer> cloudfile_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/cloudfile/{id}")
	ResponseMessage<Logs_CloudfileModel> cloudfile_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/documentdetail")
	ResponseMessage<PagerResult<Logs_DocumentdetailModel>> documentdetail_query(QueryParam queryParam);
	
	@PostMapping("/logs/documentdetail")
	ResponseMessage<String> documentdetail_save(@RequestBody Logs_DocumentdetailModel data);

	@PatchMapping("/logs/documentdetail/{id}")
	ResponseMessage<String> documentdetail_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_DocumentdetailModel data);

	@DeleteMapping("/logs/documentdetail/{id}")
	ResponseMessage<Integer> documentdetail_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/documentdetail/{id}")
	ResponseMessage<Logs_DocumentdetailModel> documentdetail_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/documentfile")
	ResponseMessage<PagerResult<Logs_DocumentfileModel>> documentfile_query(QueryParam queryParam);
	
	@PostMapping("/logs/documentfile")
	ResponseMessage<String> documentfile_save(@RequestBody Logs_DocumentfileModel data);

	@PatchMapping("/logs/documentfile/{id}")
	ResponseMessage<String> documentfile_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_DocumentfileModel data);

	@DeleteMapping("/logs/documentfile/{id}")
	ResponseMessage<Integer> documentfile_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/documentfile/{id}")
	ResponseMessage<Logs_DocumentfileModel> documentfile_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/document")
	ResponseMessage<PagerResult<Logs_DocumentModel>> document_query(QueryParam queryParam);
	
	@PostMapping("/logs/document")
	ResponseMessage<String> document_save(@RequestBody Logs_DocumentModel data);

	@PatchMapping("/logs/document/{id}")
	ResponseMessage<String> document_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_DocumentModel data);

	@DeleteMapping("/logs/document/{id}")
	ResponseMessage<Integer> document_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/document/{id}")
	ResponseMessage<Logs_DocumentModel> document_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/exceptiondictionary")
	ResponseMessage<PagerResult<Logs_ExceptiondictionaryModel>> exceptiondictionary_query(QueryParam queryParam);
	
	@PostMapping("/logs/exceptiondictionary")
	ResponseMessage<String> exceptiondictionary_save(@RequestBody Logs_ExceptiondictionaryModel data);

	@PatchMapping("/logs/exceptiondictionary/{id}")
	ResponseMessage<String> exceptiondictionary_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_ExceptiondictionaryModel data);

	@DeleteMapping("/logs/exceptiondictionary/{id}")
	ResponseMessage<Integer> exceptiondictionary_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/exceptiondictionary/{id}")
	ResponseMessage<Logs_ExceptiondictionaryModel> exceptiondictionary_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/exceptionmessage")
	ResponseMessage<PagerResult<Logs_ExceptionmessageModel>> exceptionmessage_query(QueryParam queryParam);
	
	@PostMapping("/logs/exceptionmessage")
	ResponseMessage<String> exceptionmessage_save(@RequestBody Logs_ExceptionmessageModel data);

	@PatchMapping("/logs/exceptionmessage/{id}")
	ResponseMessage<String> exceptionmessage_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_ExceptionmessageModel data);

	@DeleteMapping("/logs/exceptionmessage/{id}")
	ResponseMessage<Integer> exceptionmessage_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/exceptionmessage/{id}")
	ResponseMessage<Logs_ExceptionmessageModel> exceptionmessage_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/hf")
	ResponseMessage<PagerResult<Logs_HfModel>> hf_query(QueryParam queryParam);
	
	@PostMapping("/logs/hf")
	ResponseMessage<String> hf_save(@RequestBody Logs_HfModel data);

	@PatchMapping("/logs/hf/{id}")
	ResponseMessage<String> hf_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_HfModel data);

	@DeleteMapping("/logs/hf/{id}")
	ResponseMessage<Integer> hf_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/hf/{id}")
	ResponseMessage<Logs_HfModel> hf_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/hystrixrecord")
	ResponseMessage<PagerResult<Logs_HystrixrecordModel>> hystrixrecord_query(QueryParam queryParam);
	
	@PostMapping("/logs/hystrixrecord")
	ResponseMessage<String> hystrixrecord_save(@RequestBody Logs_HystrixrecordModel data);

	@PatchMapping("/logs/hystrixrecord/{id}")
	ResponseMessage<String> hystrixrecord_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_HystrixrecordModel data);

	@DeleteMapping("/logs/hystrixrecord/{id}")
	ResponseMessage<Integer> hystrixrecord_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/hystrixrecord/{id}")
	ResponseMessage<Logs_HystrixrecordModel> hystrixrecord_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/intetfacelogs")
	ResponseMessage<PagerResult<Logs_IntetfacelogsModel>> intetfacelogs_query(QueryParam queryParam);
	
	@PostMapping("/logs/intetfacelogs")
	ResponseMessage<String> intetfacelogs_save(@RequestBody Logs_IntetfacelogsModel data);

	@PatchMapping("/logs/intetfacelogs/{id}")
	ResponseMessage<String> intetfacelogs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_IntetfacelogsModel data);

	@DeleteMapping("/logs/intetfacelogs/{id}")
	ResponseMessage<Integer> intetfacelogs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/intetfacelogs/{id}")
	ResponseMessage<Logs_IntetfacelogsModel> intetfacelogs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/loginlogs")
	ResponseMessage<PagerResult<Logs_LoginlogsModel>> loginlogs_query(QueryParam queryParam);
	
	@PostMapping("/logs/loginlogs")
	ResponseMessage<String> loginlogs_save(@RequestBody Logs_LoginlogsModel data);

	@PatchMapping("/logs/loginlogs/{id}")
	ResponseMessage<String> loginlogs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_LoginlogsModel data);

	@DeleteMapping("/logs/loginlogs/{id}")
	ResponseMessage<Integer> loginlogs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/loginlogs/{id}")
	ResponseMessage<Logs_LoginlogsModel> loginlogs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/msgbox")
	ResponseMessage<PagerResult<Logs_MsgboxModel>> msgbox_query(QueryParam queryParam);
	
	@PostMapping("/logs/msgbox")
	ResponseMessage<String> msgbox_save(@RequestBody Logs_MsgboxModel data);

	@PatchMapping("/logs/msgbox/{id}")
	ResponseMessage<String> msgbox_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_MsgboxModel data);

	@DeleteMapping("/logs/msgbox/{id}")
	ResponseMessage<Integer> msgbox_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/msgbox/{id}")
	ResponseMessage<Logs_MsgboxModel> msgbox_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/operatelogs")
	ResponseMessage<PagerResult<Logs_OperatelogsModel>> operatelogs_query(QueryParam queryParam);
	
	@PostMapping("/logs/operatelogs")
	ResponseMessage<String> operatelogs_save(@RequestBody Logs_OperatelogsModel data);

	@PatchMapping("/logs/operatelogs/{id}")
	ResponseMessage<String> operatelogs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_OperatelogsModel data);

	@DeleteMapping("/logs/operatelogs/{id}")
	ResponseMessage<Integer> operatelogs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/operatelogs/{id}")
	ResponseMessage<Logs_OperatelogsModel> operatelogs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/pl")
	ResponseMessage<PagerResult<Logs_PlModel>> pl_query(QueryParam queryParam);
	
	@PostMapping("/logs/pl")
	ResponseMessage<String> pl_save(@RequestBody Logs_PlModel data);

	@PatchMapping("/logs/pl/{id}")
	ResponseMessage<String> pl_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_PlModel data);

	@DeleteMapping("/logs/pl/{id}")
	ResponseMessage<Integer> pl_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/pl/{id}")
	ResponseMessage<Logs_PlModel> pl_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/tasklogs")
	ResponseMessage<PagerResult<Logs_TasklogsModel>> tasklogs_query(QueryParam queryParam);
	
	@PostMapping("/logs/tasklogs")
	ResponseMessage<String> tasklogs_save(@RequestBody Logs_TasklogsModel data);

	@PatchMapping("/logs/tasklogs/{id}")
	ResponseMessage<String> tasklogs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_TasklogsModel data);

	@DeleteMapping("/logs/tasklogs/{id}")
	ResponseMessage<Integer> tasklogs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/tasklogs/{id}")
	ResponseMessage<Logs_TasklogsModel> tasklogs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/tasks")
	ResponseMessage<PagerResult<Logs_TasksModel>> tasks_query(QueryParam queryParam);
	
	@PostMapping("/logs/tasks")
	ResponseMessage<String> tasks_save(@RequestBody Logs_TasksModel data);

	@PatchMapping("/logs/tasks/{id}")
	ResponseMessage<String> tasks_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_TasksModel data);

	@DeleteMapping("/logs/tasks/{id}")
	ResponseMessage<Integer> tasks_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/tasks/{id}")
	ResponseMessage<Logs_TasksModel> tasks_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/logs/updatelogs")
	ResponseMessage<PagerResult<Logs_UpdatelogsModel>> updatelogs_query(QueryParam queryParam);
	
	@PostMapping("/logs/updatelogs")
	ResponseMessage<String> updatelogs_save(@RequestBody Logs_UpdatelogsModel data);

	@PatchMapping("/logs/updatelogs/{id}")
	ResponseMessage<String> updatelogs_updateByKey(@PathVariable(value = "id") String id, @RequestBody Logs_UpdatelogsModel data);

	@DeleteMapping("/logs/updatelogs/{id}")
	ResponseMessage<Integer> updatelogs_deleteByKey(@PathVariable(value = "id") String id);

	@GetMapping("/logs/updatelogs/{id}")
	ResponseMessage<Logs_UpdatelogsModel> updatelogs_getByKey(@PathVariable(value = "id") String id);	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	



}
