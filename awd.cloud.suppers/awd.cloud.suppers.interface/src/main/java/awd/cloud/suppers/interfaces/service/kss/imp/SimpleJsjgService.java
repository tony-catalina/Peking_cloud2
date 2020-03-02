package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.JsjgService;
import awd.cloud.suppers.interfaces.utils.DefaultQueryParam;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("jsjgService")
public class SimpleJsjgService implements JsjgService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getJsjg(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();
        for (String key : map.keySet()) {
            queryParam.and(key, map.get(key));
        }

        DefaultQueryParam.addDefaultQueryParam(null, queryParam, "");

        System.err.println("queryParam---"+ JSON.toJSONString(queryParam));

        ResponseMessage<PagerResult<Map<String, Object>>> list = kssBaseServersApi.getJsjg(queryParam);


        System.err.println("list.getIncludes()---"+JSON.toJSONString(list.getIncludes()));
        System.err.println("list---"+JSON.toJSONString(list));

        return list;
    }

    @Override
    public ResponseMessage<String> saveJsjg(Map<String, Object> map) {
        return kssBaseServersApi.saveJsjg(map);
    }

    @Override
    public ResponseMessage<String> updateJsjgById(String id,Map<String, Object> map) {
        return kssBaseServersApi.updateJsjgById(id,map);
    }
}
