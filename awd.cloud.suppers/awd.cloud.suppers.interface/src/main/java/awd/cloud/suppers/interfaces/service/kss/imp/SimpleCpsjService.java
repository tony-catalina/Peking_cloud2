package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.CpsjService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("cpsjService")
public class SimpleCpsjService implements CpsjService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getCpsj(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getCpsj(queryParam);
    }

    @Override
    public ResponseMessage<String> saveCpsj(Map<String, Object> map) {

        return kssBaseServersApi.saveCpsj(map);
    }

    @Override
    public ResponseMessage<String> updateCpsjById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateCpsjById(id,map);
    }
}
