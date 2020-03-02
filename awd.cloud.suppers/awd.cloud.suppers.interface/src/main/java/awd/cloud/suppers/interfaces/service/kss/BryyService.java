package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface BryyService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getBryy(Map<String, Object> map);

    public ResponseMessage<String> saveBryy(Map<String, Object> map);

    public ResponseMessage<String> updateBryyById(String id,Map<String, Object> map);
}
