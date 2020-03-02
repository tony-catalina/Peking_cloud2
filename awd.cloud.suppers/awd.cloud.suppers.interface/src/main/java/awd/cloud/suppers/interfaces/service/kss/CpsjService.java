package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface CpsjService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getCpsj(Map<String, Object> map);

    public ResponseMessage<String> saveCpsj(Map<String, Object> map);

    public ResponseMessage<String> updateCpsjById(String id,Map<String, Object> map);
}
