package awd.cloud.suppers.interfaces.service.jls;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface BjgxfkService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getBjgxfk();

    public ResponseMessage<String> saveBjgxfk(Map<String,Object> map);

    public ResponseMessage<String> updateBjgxfkById(String id, Map<String,Object> map);
}
