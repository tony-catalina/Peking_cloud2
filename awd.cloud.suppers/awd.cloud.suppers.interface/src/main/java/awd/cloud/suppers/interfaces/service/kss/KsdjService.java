package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface KsdjService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getKsdj(Map<String, Object> map);

    public ResponseMessage<String> saveKsdj(Map<String, Object> map);

    public ResponseMessage<String> updateKsdjById(String id ,Map<String, Object> map);

}
