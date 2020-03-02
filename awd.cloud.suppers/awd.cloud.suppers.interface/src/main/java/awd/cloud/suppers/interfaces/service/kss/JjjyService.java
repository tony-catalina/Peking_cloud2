package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface JjjyService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getJjjy(Map<String, Object> map);

    public ResponseMessage<String> saveJjjy(Map<String, Object> map);

    public ResponseMessage<String> updateJjjyById(String id ,Map<String, Object> map);
}
