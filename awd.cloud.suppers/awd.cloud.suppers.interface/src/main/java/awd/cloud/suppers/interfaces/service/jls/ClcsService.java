package awd.cloud.suppers.interfaces.service.jls;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface ClcsService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getClcs();

    public ResponseMessage<String> saveClcs(Map<String,Object> map);

    public ResponseMessage<String> updateClcsById(String id, Map<String,Object> map);
}
