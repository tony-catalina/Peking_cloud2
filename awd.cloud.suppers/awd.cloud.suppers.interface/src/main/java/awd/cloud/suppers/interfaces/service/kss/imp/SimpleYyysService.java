package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.YyysService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("yyysService")
public class SimpleYyysService implements YyysService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getYyys(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getYyys(queryParam);
    }

    @Override
    public ResponseMessage<String> saveYyys(Map<String, Object> map) {

        return kssBaseServersApi.saveYyys(map);
    }

    @Override
    public ResponseMessage<String> updateYyysById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateCzjlById(id,map);
    }
}
