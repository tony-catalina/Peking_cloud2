package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.ZddmService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("zddmService")
public class SimpleZddmService implements ZddmService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getZddm(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getZddm(queryParam);
    }

    @Override
    public ResponseMessage<String> saveZddm(Map<String, Object> map) {

        return kssBaseServersApi.saveZddm(map);
    }

    @Override
    public ResponseMessage<String> updateZddmById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateZddmById(id,map);
    }
}
