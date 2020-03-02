package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface QlywService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getQlyw(Map<String, Object> map);

    public ResponseMessage<String> saveQlyw(Map<String, Object> map);

    public ResponseMessage<String> updateQlywById(String id ,Map<String, Object> map);
}
