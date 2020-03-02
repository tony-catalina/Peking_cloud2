package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.BryyService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.Param;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("bryyService")
public class SimpleBryyService implements BryyService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getBryy(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getBryy(queryParam);
    }

    @Override
    public ResponseMessage<String> saveBryy(Map<String, Object> map) {

        return kssBaseServersApi.saveBryy(map);
    }

    @Override
    public ResponseMessage<String> updateBryyById(String id, Map<String, Object> map) {

        return kssBaseServersApi.updateBryyById(id,map);
    }
}
