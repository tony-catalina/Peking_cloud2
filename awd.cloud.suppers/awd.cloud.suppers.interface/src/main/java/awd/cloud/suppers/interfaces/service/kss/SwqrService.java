package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface SwqrService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getSwqr(Map<String, Object> map);

    public ResponseMessage<String> saveSwqr(Map<String, Object> map);

    public ResponseMessage<String> updateSwqrById(String id ,Map<String, Object> map);
}
