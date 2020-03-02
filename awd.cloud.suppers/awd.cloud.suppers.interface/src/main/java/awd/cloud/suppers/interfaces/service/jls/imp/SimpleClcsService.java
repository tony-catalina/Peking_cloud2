package awd.cloud.suppers.interfaces.service.jls.imp;

import awd.cloud.suppers.interfaces.api.JlsServersApi;
import awd.cloud.suppers.interfaces.service.jls.ClcsService;
import awd.cloud.suppers.interfaces.utils.DefaultQueryParam;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("clcsService")
public class SimpleClcsService implements ClcsService {

    @Autowired
    private JlsServersApi jlsServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getClcs() {
        QueryParam queryParam = new QueryParam();

        DefaultQueryParam.addDefaultQueryParam(null, queryParam, "R2");

        System.err.println("queryParam---" + JSON.toJSONString(queryParam));

        ResponseMessage<PagerResult<Map<String, Object>>> list = jlsServersApi.queryClcs(queryParam);

        System.err.println("list.getIncludes()---" + JSON.toJSONString(list.getIncludes()));
        System.err.println("list---" + JSON.toJSONString(list));

        return list;
    }

    @Override
    public ResponseMessage<String> saveClcs(Map<String, Object> map) {
        return jlsServersApi.saveClcs(map);
    }

    @Override
    public ResponseMessage<String> updateClcsById(String id, Map<String, Object> map) {
        return jlsServersApi.updateClcsById(id,map);
    }
}
