package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface YygwService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getYygw(Map<String, Object> map);

    public ResponseMessage<String> saveYygw(Map<String, Object> map);

    public ResponseMessage<String> updateYygwById(String id ,Map<String, Object> map);
}
