package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.CpjgService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("cpjgService")
public class SimpleCpjgService implements CpjgService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getCpjg(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getCpjg(queryParam);
    }

    @Override
    public ResponseMessage<String> saveCpjg(Map<String, Object> map) {

        return kssBaseServersApi.saveCpjg(map);
    }

    @Override
    public ResponseMessage<String> updateCpjgById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateCpjgById(id,map);
    }
}
