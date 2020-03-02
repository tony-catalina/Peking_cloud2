package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.WgtxService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("wgtxService")
public class SimpleWgtxService implements WgtxService {

    @Autowired
    private KssCoreServersApi kssCoreServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getWgtx(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();

        return kssCoreServersApi.getWgjl(queryParam);
    }

    @Override
    public ResponseMessage<String> saveWgtx(Map<String, Object> map) {

        return kssCoreServersApi.saveWgjl(map);
    }

    @Override
    public ResponseMessage<String> updateWgtxById(String id, Map<String, Object> map) {

        return kssCoreServersApi.updateWgjlById(id,map);
    }
}
