package awd.cloud.suppers.interfaces.service.jls;

import java.util.Map;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

public interface JbxxService {

	public ResponseMessage<PagerResult<Map<String, Object>>> getJbxx();

	public ResponseMessage<String> saveJbxx(Map<String,Object> map);
	
	public ResponseMessage<String> updateJbxxById(String id, Map<String,Object> map);
}
