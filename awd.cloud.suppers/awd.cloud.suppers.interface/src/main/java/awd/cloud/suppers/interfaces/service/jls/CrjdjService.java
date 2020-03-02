package awd.cloud.suppers.interfaces.service.jls;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface CrjdjService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getCrjdj();

    public ResponseMessage<String> saveCrjdj(Map<String,Object> map);

    public ResponseMessage<String> updateCrjdjById(String id, Map<String,Object> map);
}
