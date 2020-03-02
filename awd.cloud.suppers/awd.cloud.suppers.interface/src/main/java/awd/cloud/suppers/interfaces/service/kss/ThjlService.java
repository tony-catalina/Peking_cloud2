package awd.cloud.suppers.interfaces.service.kss;

import awd.cloud.suppers.interfaces.utils.ResponseMessage;

import java.util.Map;

public interface ThjlService {

    public Map<String, Object> getThjl(Map<String, Object> map);

    public ResponseMessage<String> saveThjl(Map<String, Object> map);

    public ResponseMessage<String> updateThjlById(String id ,Map<String, Object> map);
}
