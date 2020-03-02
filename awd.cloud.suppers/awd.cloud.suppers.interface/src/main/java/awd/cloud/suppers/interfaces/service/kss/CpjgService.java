package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface CpjgService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getCpjg(Map<String, Object> map);

    public ResponseMessage<String> saveCpjg(Map<String, Object> map);

    public ResponseMessage<String> updateCpjgById(String id,Map<String, Object> map);
}
