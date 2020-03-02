package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.YrzxService;
import awd.cloud.suppers.interfaces.utils.DefaultQueryParam;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("yrzxService")
public class SimpleYrzxService implements YrzxService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getYrzx(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();
        for (String key : map.keySet()) {
            queryParam.and(key, map.get(key));
        }

        DefaultQueryParam.addDefaultQueryParam(null, queryParam, "");

        System.err.println("queryParam---"+ JSON.toJSONString(queryParam));

        ResponseMessage<PagerResult<Map<String, Object>>> list = kssBaseServersApi.getYrzx(queryParam);


        System.err.println("list.getIncludes()---"+JSON.toJSONString(list.getIncludes()));
        System.err.println("list---"+JSON.toJSONString(list));

        return list;
    }

    @Override
    public ResponseMessage<String> saveYrzx(Map<String, Object> map) {
        return kssBaseServersApi.saveYrzx(map);
    }

    @Override
    public ResponseMessage<String> updateYrzxById(String id,Map<String, Object> map) {
        return kssBaseServersApi.updateYrzxById(id,map);
    }
}
