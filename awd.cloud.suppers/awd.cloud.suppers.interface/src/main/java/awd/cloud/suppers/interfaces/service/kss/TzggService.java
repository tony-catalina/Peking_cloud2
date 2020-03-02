package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface TzggService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getTzgg(Map<String, Object> map);

    public ResponseMessage<String> saveTzgg(Map<String, Object> map);

    public ResponseMessage<String> updateTzggById(String id ,Map<String, Object> map);
}
