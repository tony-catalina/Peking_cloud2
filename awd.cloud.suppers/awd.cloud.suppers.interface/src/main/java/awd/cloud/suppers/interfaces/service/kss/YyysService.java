package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface YyysService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getYyys(Map<String, Object> map);

    public ResponseMessage<String> saveYyys(Map<String, Object> map);

    public ResponseMessage<String> updateYyysById(String id ,Map<String, Object> map);
}
