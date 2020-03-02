package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface JyjlService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getJyjl(Map<String, Object> map);

    public ResponseMessage<String> saveJyjl(Map<String, Object> map);

    public ResponseMessage<String> updateJyjlById(String id ,Map<String, Object> map);
}
