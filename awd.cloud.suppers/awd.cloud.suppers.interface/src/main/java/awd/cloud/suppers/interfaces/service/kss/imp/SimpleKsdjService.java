package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.KsdjService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("ksdjService")
public class SimpleKsdjService implements KsdjService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getKsdj(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getKsdj(queryParam);
    }

    @Override
    public ResponseMessage<String> saveKsdj(Map<String, Object> map) {

        return kssBaseServersApi.saveKsdj(map);
    }

    @Override
    public ResponseMessage<String> updateKsdjById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateKsdjById(id,map);
    }
}
