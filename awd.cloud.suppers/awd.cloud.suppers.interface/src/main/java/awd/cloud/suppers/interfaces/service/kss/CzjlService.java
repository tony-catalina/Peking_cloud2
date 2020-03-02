package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface CzjlService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getCzjl(Map<String, Object> map);

    public ResponseMessage<String> saveCzjl(Map<String, Object> map);

    public ResponseMessage<String> updateCzjlById(String id ,Map<String, Object> map);
}
