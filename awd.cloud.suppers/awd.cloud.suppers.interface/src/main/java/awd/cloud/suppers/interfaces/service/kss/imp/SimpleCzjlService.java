package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.CzjlService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("czjlService")
public class SimpleCzjlService implements CzjlService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getCzjl(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getCzjl(queryParam);
    }

    @Override
    public ResponseMessage<String> saveCzjl(Map<String, Object> map) {

        return kssBaseServersApi.saveCzjl(map);
    }

    @Override
    public ResponseMessage<String> updateCzjlById(String id,Map<String, Object> map) {

        return kssBaseServersApi.updateCzjlById(id,map);
    }
}
