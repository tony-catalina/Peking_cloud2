package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.ZddmsjService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("zddmsjService")
public class SimpleZddmsjService implements ZddmsjService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getZddmsj(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getZddmsj(queryParam);
    }

    @Override
    public ResponseMessage<String> saveZddmsj(Map<String, Object> map) {

        return kssBaseServersApi.saveZddmsj(map);
    }

    @Override
    public ResponseMessage<String> updateZddmsjById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateZddmsjById(id,map);
    }
}
