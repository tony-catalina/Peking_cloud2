package awd.cloud.suppers.interfaces.service.jls.imp;

import awd.cloud.suppers.interfaces.api.JlsServersApi;
import awd.cloud.suppers.interfaces.service.jls.BjgxfkService;
import awd.cloud.suppers.interfaces.utils.DefaultQueryParam;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("bjgxfkService")
public class SimpleBjgxfkService implements BjgxfkService {

    @Autowired
    private JlsServersApi jlsServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getBjgxfk() {
        QueryParam queryParam = new QueryParam();

        DefaultQueryParam.addDefaultQueryParam(null, queryParam, "");

        System.err.println("queryParam---" + JSON.toJSONString(queryParam));

        ResponseMessage<PagerResult<Map<String, Object>>> list = jlsServersApi.queryBjgxfk(queryParam);


        System.err.println("list.getIncludes()---" + JSON.toJSONString(list.getIncludes()));
        System.err.println("list---" + JSON.toJSONString(list));

        return list;
    }

    @Override
    public ResponseMessage<String> saveBjgxfk(Map<String, Object> map) {
        return jlsServersApi.saveBjgxfk(map);
    }

    @Override
    public ResponseMessage<String> updateBjgxfkById(String id, Map<String, Object> map) {
        return jlsServersApi.updateBjgxfkById(id,map);
    }
}
