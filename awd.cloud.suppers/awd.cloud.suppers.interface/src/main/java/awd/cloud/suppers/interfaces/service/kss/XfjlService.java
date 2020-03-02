package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface XfjlService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getXfjl(Map<String, Object> map);

    public ResponseMessage<String> saveXfjl(Map<String, Object> map);

    public ResponseMessage<String> updateXfjlById(String id ,Map<String, Object> map);
}
