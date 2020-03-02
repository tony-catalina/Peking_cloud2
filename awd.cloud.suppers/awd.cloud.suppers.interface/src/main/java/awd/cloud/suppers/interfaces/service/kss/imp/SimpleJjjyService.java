package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.JjjyService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("jjjyService")
public class SimpleJjjyService implements JjjyService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getJjjy(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getJjjy(queryParam);
    }

    @Override
    public ResponseMessage<String> saveJjjy(Map<String, Object> map) {

        return kssBaseServersApi.saveJjjy(map);
    }

    @Override
    public ResponseMessage<String> updateJjjyById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateJjjyById(id,map);
    }
}
