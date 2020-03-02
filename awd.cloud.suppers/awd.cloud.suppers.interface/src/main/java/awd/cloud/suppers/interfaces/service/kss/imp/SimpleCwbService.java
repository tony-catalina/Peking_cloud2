package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.CwbService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("cwbService")
public class SimpleCwbService implements CwbService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Autowired
    private KssCoreServersApi kssCoreServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getCwb(Map<String, Object> map) {
        QueryParam cwParam = new QueryParam();//床位条件

        ResponseMessage<PagerResult<Map<String, Object>>> resultMessage = kssCoreServersApi.queryPwgl(cwParam);
        List<Map<String, Object>> data = resultMessage.getResult().getData();

        Map<String,Object> resultMap = Maps.newHashMap();
        List<Map<String,Object>> cwList = new ArrayList<>();

        for (Map<String, Object> m : data) {
            Map<String,Object> result = Maps.newHashMap();
            result.put("cwh",m.get("cwh"));

            QueryParam xxParam = new QueryParam();//基本信息条件
            xxParam.and("rybh", m.get("rybh"));
            ResponseMessage<PagerResult<Map<String, Object>>> xxSesult = kssBaseServersApi.queryJbxx(xxParam);
            String xm = (String)xxSesult.getResult().getData().get(0).get("xm");

            result.put("xm",xm);

            cwList.add(result);
        }

        resultMap.put("cwList",cwList);
        resultMap.put("jsh",map.get("jsh"));
        resultMap.put("cwbjfs","床位布局方式,待定");

        resultMessage.getResult().getData().clear();
        resultMessage.getResult().getData().add(resultMap);

        return resultMessage;
    }

    @Override
    public ResponseMessage<String> saveCwb(Map<String, Object> map) {

        return kssBaseServersApi.saveCwb(map);
    }

    @Override
    public ResponseMessage<String> updateCwbById(String id,Map<String, Object> map) {

        return kssBaseServersApi.updateCwbById(id,map);
    }
}
