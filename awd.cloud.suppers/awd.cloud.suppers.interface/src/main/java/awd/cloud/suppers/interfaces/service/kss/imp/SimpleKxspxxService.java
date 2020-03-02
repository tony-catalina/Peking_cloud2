package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.KxspxxService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("kxspxxService")
public class SimpleKxspxxService implements KxspxxService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getKxspxx(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getKxspxx(queryParam);
    }

    @Override
    public ResponseMessage<String> saveKxspxx(Map<String, Object> map) {

        return kssBaseServersApi.saveKxspxx(map);
    }

    @Override
    public ResponseMessage<String> updateKxspxxById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateKxspxxById(id,map);
    }
}
