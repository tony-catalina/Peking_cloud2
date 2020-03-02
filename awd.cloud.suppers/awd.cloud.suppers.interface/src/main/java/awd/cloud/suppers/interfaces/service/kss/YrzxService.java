package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface YrzxService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getYrzx(Map<String, Object> map);

    public ResponseMessage<String> saveYrzx(Map<String, Object> map);

    public ResponseMessage<String> updateYrzxById(String id ,Map<String, Object> map);
}
