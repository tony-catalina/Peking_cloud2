package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface MzcpService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getMzcp(Map<String, Object> map);

    public ResponseMessage<String> saveMzcp(Map<String, Object> map);

    public ResponseMessage<String> updateMzcpById(String id ,Map<String, Object> map);

}
