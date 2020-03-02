package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface KxspxxService {
    public ResponseMessage<PagerResult<Map<String, Object>>> getKxspxx(Map<String, Object> map);

    public ResponseMessage<String> saveKxspxx(Map<String, Object> map);

    public ResponseMessage<String> updateKxspxxById(String id,Map<String, Object> map);
}
