package awd.cloud.suppers.interfaces.service.jls;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface BjjlService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getBjjl();

    public ResponseMessage<String> saveBjjl(Map<String,Object> map);

    public ResponseMessage<String> updateBjjlById(String id, Map<String,Object> map);
}
