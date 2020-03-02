package awd.cloud.suppers.interfaces.service.jls;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface CsjkqkService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getCsjkqk();

    public ResponseMessage<String> saveCsjkqk(Map<String,Object> map);

    public ResponseMessage<String> updateCsjkqkById(String id, Map<String,Object> map);
}
