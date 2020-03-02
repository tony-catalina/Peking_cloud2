package awd.cloud.suppers.interfaces.service.jls;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface BjgxfkBjxzService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getBjgxfkBjxz();

    public ResponseMessage<String> saveBjgxfkBjxz(Map<String, Object> map);

    public ResponseMessage<String> updateBjgxfkBjxzById(String id, Map<String, Object> map);
}
