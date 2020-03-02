package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface YyglService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getYygl(Map<String, Object> map);

    public ResponseMessage<String> saveYygl(Map<String, Object> map);

    public ResponseMessage<String> updateYyglById(String id ,Map<String, Object> map);

}
