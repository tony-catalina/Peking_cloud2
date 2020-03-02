package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.JlsServersApi;
import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.TzggService;
import awd.cloud.suppers.interfaces.utils.DefaultQueryParam;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service("tzggService")
public class SimpleTzggService implements TzggService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getTzgg(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();
        for (String key : map.keySet()) {
            queryParam.and(key, map.get(key));
        }

        DefaultQueryParam.addDefaultQueryParam(null, queryParam, "");

        System.err.println("queryParam---"+ JSON.toJSONString(queryParam));

        ResponseMessage<PagerResult<Map<String, Object>>> list = kssBaseServersApi.getTzgg(queryParam);


        System.err.println("list.getIncludes()---"+JSON.toJSONString(list.getIncludes()));
        System.err.println("list---"+JSON.toJSONString(list));

        return list;
    }

    @Override
    public ResponseMessage<String> saveTzgg(Map<String, Object> map) {
        return kssBaseServersApi.saveTzgg(map);
    }

    @Override
    public ResponseMessage<String> updateTzggById(String id,Map<String, Object> map) {
        return kssBaseServersApi.updateTzggById(id,map);
    }
}
