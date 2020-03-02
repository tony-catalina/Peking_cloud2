package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.GjthService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("gjthService")
public class SimpleGjthService implements GjthService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;


    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getGjth(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getGjth(queryParam);
    }

    @Override
    public ResponseMessage<String> saveGjth(Map<String, Object> map) {

        return kssBaseServersApi.saveGjth(map);
    }

    @Override
    public ResponseMessage<String> updateGjthById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateGjthById(id,map);
    }
}
