package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.FyqrService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("fyqrService")
public class SimpleFyqrService implements FyqrService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getFyqr(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getFyqr(queryParam);
    }

    @Override
    public ResponseMessage<String> saveFyqr(Map<String, Object> map) {

        return kssBaseServersApi.saveFyqr(map);
    }

    @Override
    public ResponseMessage<String> updateFyqrById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateFyqrById(id,map);
    }
}
