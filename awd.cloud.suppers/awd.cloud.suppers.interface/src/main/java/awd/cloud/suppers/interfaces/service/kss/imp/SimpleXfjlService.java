package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.XfjlService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("xfjlService")
public class SimpleXfjlService implements XfjlService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getXfjl(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();

        return kssBaseServersApi.getXfjl(queryParam);
    }

    @Override
    public ResponseMessage<String> saveXfjl(Map<String, Object> map) {

        return kssBaseServersApi.saveXfjl(map);
    }

    @Override
    public ResponseMessage<String> updateXfjlById(String id,Map<String, Object> map) {

        return kssBaseServersApi.updateXfjlById(id,map);
    }
}
