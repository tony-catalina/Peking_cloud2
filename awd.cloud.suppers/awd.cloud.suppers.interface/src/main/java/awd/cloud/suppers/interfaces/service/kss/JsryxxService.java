package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface JsryxxService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getJsryxx(Map<String, Object> map);

    public ResponseMessage<String> saveJsryxx(Map<String, Object> map);

    public ResponseMessage<String> updateJsryxxById(String id,Map<String, Object> map);
}
