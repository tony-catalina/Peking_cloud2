package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.JsryxxService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("jsryxxService")
public class SimpleJsryxxServiceq implements JsryxxService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getJsryxx(Map<String, Object> map) {
        QueryParam queryParam = new QueryParam();
        queryParam.and("jsh",map.get("jsh"));
        ResponseMessage<PagerResult<Map<String, Object>>> ryxxList = kssBaseServersApi.queryJbxx(queryParam);

        Map<String,Object> resultMap = Maps.newHashMap();
        List<Map<String,Object>> resultList = new ArrayList<>();

        for (Map<String, Object> ryMap : ryxxList.getResult().getData()) {
            Map<String,Object> m = Maps.newHashMap();
            m.put("xm",ryMap.get("xm"));//姓名
            m.put("sfzj",ryMap.get("state"));//是否在监
            m.put("rybh",ryMap.get("rybh"));//在押人员编号
            m.put("zyryhmxx","暂无");//在押人员虹膜信息
            m.put("zyryrlxx","暂无");//在押人员人脸信息

            resultList.add(m);
        }

        resultMap.put("jsh",map.get("jsh"));//监室号
        resultMap.put("result",resultList);

        ryxxList.getResult().getData().clear();
        ryxxList.getResult().getData().add(resultMap);

        return ryxxList;
    }

    @Override
    public ResponseMessage<String> saveJsryxx(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseMessage<String> updateJsryxxById(String id, Map<String, Object> map) {
        return null;
    }
}
