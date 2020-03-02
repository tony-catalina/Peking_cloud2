package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.ZbqdService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("zbqdService")
public class SimpleZbqdService implements ZbqdService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getZddm(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getZbqd(queryParam);
    }

    @Override
    public ResponseMessage<String> saveZddm(Map<String, Object> map) {

        return kssBaseServersApi.saveZbqd(map);
    }

    @Override
    public ResponseMessage<String> updateZddmById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateZbqdById(id,map);
    }
}
