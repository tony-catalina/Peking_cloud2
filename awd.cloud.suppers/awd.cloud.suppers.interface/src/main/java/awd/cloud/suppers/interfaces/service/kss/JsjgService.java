package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface JsjgService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getJsjg(Map<String, Object> map);

    public ResponseMessage<String> saveJsjg(Map<String, Object> map);

    public ResponseMessage<String> updateJsjgById(String id ,Map<String, Object> map);
}
