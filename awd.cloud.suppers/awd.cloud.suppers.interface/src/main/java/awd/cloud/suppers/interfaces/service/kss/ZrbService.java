package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.List;
import java.util.Map;

public interface ZrbService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getZrb(Map<String, Object> map);

    public ResponseMessage<String> saveZrb(Map<String, Object> map);

    public ResponseMessage<String> updateZrbById(String id, Map<String, Object> map);
}
