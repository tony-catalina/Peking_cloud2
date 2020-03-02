package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface ZddmService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getZddm(Map<String, Object> map);

    public ResponseMessage<String> saveZddm(Map<String, Object> map);

    public ResponseMessage<String> updateZddmById(String id ,Map<String, Object> map);
}
