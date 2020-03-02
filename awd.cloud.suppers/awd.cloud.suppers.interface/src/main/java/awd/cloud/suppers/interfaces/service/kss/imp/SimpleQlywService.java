package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.QlywService;
import awd.cloud.suppers.interfaces.utils.DefaultQueryParam;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("qlywService")
public class SimpleQlywService implements QlywService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getQlyw(Map<String, Object> map) {

        QueryParam qlywParam = new QueryParam();
        qlywParam.and("jsbh", map.get("jsbh"));
        ResponseMessage<PagerResult<Map<String, Object>>> qlywList = kssBaseServersApi.queryQlywgz(qlywParam);

        QueryParam jqjsParam = new QueryParam();
        jqjsParam.and("jsh", map.get("jsh"));
        ResponseMessage<PagerResult<Map<String, Object>>> jqjsList = kssBaseServersApi.queryJs(jqjsParam);

        Map<String, Object> result = Maps.newHashMap();
        result.put("jq", jqjsList.getResult().getData().get(0).get("jqh"));
        result.put("js", map.get("jsh"));
        result.put("qlyw", qlywList.getResult().getData().get(0).get("gznr"));

        qlywList.getResult().getData().clear();
        qlywList.getResult().getData().add(result);

        return qlywList;
    }

    @Override
    public ResponseMessage<String> saveQlyw(Map<String, Object> map) {

        return kssBaseServersApi.saveQlyw(map);
    }

    @Override
    public ResponseMessage<String> updateQlywById(String id,Map<String, Object> map) {

        return kssBaseServersApi.updateQlywById(id,map);
    }
}
