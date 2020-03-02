package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface ZddmsjService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getZddmsj(Map<String, Object> map);

    public ResponseMessage<String> saveZddmsj(Map<String, Object> map);

    public ResponseMessage<String> updateZddmsjById(String id ,Map<String, Object> map);
}
