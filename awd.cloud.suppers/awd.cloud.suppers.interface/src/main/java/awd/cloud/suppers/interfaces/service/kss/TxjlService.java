package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface TxjlService {

    public ResponseMessage<PagerResult<Map<String, Object>>> getTxjl(Map<String, Object> map);

    public ResponseMessage<String> saveTxjl(Map<String, Object> map);

    public ResponseMessage<String> updateTxjlById(String id ,Map<String, Object> map);
}
