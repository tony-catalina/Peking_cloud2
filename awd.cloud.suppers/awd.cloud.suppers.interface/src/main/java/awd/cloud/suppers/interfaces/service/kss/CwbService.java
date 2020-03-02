package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface CwbService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getCwb(Map<String, Object> map);

    public ResponseMessage<String> saveCwb(Map<String, Object> map);

    public ResponseMessage<String> updateCwbById(String id,Map<String, Object> map);
}
