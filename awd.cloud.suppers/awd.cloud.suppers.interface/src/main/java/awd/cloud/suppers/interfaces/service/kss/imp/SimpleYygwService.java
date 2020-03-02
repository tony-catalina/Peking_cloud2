package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.YygwService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("yygwService")
public class SimpleYygwService implements YygwService {

    @Autowired
    private KssCoreServersApi kssCoreServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getYygw(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssCoreServersApi.getSpdetailorder(queryParam);
    }

    @Override
    public ResponseMessage<String> saveYygw(Map<String, Object> map) {

        return kssCoreServersApi.saveSpdetailorder(map);
    }

    @Override
    public ResponseMessage<String> updateYygwById(String id, Map<String, Object> map) {

        return kssCoreServersApi.updateSpdetailorderById(id,map);
    }
}
