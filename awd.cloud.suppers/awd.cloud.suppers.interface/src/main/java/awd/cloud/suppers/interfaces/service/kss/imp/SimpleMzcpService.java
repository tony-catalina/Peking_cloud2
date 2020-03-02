package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.MzcpService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("mzcpService")
public class SimpleMzcpService implements MzcpService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getMzcp(Map<String, Object> map) {
        QueryParam param = new QueryParam();

        return kssBaseServersApi.getMzcp(param);
    }

    @Override
    public ResponseMessage<String> saveMzcp(Map<String, Object> map) {
        return kssBaseServersApi.saveMzcp(map);
    }

    @Override
    public ResponseMessage<String> updateMzcpById(String id,Map<String, Object> map) {
        return kssBaseServersApi.updateMzcpById(id,map);
    }
}
