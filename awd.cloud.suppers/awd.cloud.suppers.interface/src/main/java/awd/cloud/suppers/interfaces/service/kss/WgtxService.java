package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface WgtxService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getWgtx(Map<String, Object> map);

    public ResponseMessage<String> saveWgtx(Map<String, Object> map);

    public ResponseMessage<String> updateWgtxById(String id ,Map<String, Object> map);
}
