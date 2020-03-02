package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.SwqrService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("swqrService")
public class SimpleSwqrService implements SwqrService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getSwqr(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getSwqr(queryParam);
    }

    @Override
    public ResponseMessage<String> saveSwqr(Map<String, Object> map) {

        return kssBaseServersApi.saveSwqr(map);
    }

    @Override
    public ResponseMessage<String> updateSwqrById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateSwqrById(id,map);
    }
}
