package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface FyqrService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getFyqr(Map<String, Object> map);

    public ResponseMessage<String> saveFyqr(Map<String, Object> map);

    public ResponseMessage<String> updateFyqrById(String id ,Map<String, Object> map);
}
