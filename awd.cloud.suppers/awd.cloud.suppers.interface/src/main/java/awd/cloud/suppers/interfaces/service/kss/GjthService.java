package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface GjthService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getGjth(Map<String, Object> map);

    public ResponseMessage<String> saveGjth(Map<String, Object> map);

    public ResponseMessage<String> updateGjthById(String id ,Map<String, Object> map);
}
