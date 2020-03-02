package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.YyglService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.Param;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("yyglService")
public class SimpleYygeService implements YyglService {

    @Autowired
    private KssCoreServersApi kssCoreServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getYygl(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();

        return kssCoreServersApi.getYy(queryParam);
    }

    @Override
    public ResponseMessage<String> saveYygl(Map<String, Object> map) {

        return kssCoreServersApi.saveYy(map);
    }

    @Override
    public ResponseMessage<String> updateYyglById(String id, Map<String, Object> map) {

        return kssCoreServersApi.updateYyById(id,map);
    }
}
