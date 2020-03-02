package awd.cloud.suppers.interfaces.service.jls;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface CqjycbbService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getCqjycbb();

    public ResponseMessage<String> saveCqjycbb(Map<String,Object> map);

    public ResponseMessage<String> updateCqjycbbById(String id, Map<String,Object> map);
}
