package awd.cloud.suppers.interfaces.service.jls;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface AqjcService {

	public ResponseMessage<PagerResult<Map<String, Object>>> getAqjc();

	public ResponseMessage<String> saveAqjc(Map<String, Object> map);

	public ResponseMessage<String> updateAqjcById(String id, Map<String, Object> map);
}
