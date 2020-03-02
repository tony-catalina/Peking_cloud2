package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.ZrbService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("zrbService")
public class SimpleZrbService implements ZrbService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Autowired
    private KssCoreServersApi kssCoreServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getZrb(Map<String, Object> map) {
        QueryParam Param = new QueryParam();
        Param.and("jsh",map.get("jsh"));
        ResponseMessage<PagerResult<Map<String, Object>>> resultList = kssCoreServersApi.queryZrap(Param);

//        System.out.println(list);

        List<Map<String,Object>> list = new ArrayList();
        for (Map<String, Object> result : resultList.getResult().getData()) {

            Map<String,Object> m = Maps.newHashMap();
            m.put("apsj",result.get("apsj"));//安排时间
            m.put("week",result.get("week"));//值日时间

            List<String> zrr = new ArrayList();//值日人
            zrr.add((String)result.get("zbr1"));
            zrr.add((String)result.get("zbr2"));
            zrr.add((String)result.get("zbr3"));
            zrr.add((String)result.get("zbr4"));
            zrr.add((String)result.get("zbr5"));

            m.put("zrrxm",zrr);//值日人姓名
            m.put("jsh",map.get("jsh"));//监室号

            list.add(m);
        }

        resultList.getResult().setData(list);

        return resultList;
    }

    @Override
    public ResponseMessage<String> saveZrb(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseMessage<String> updateZrbById(String id,Map<String, Object> map) {
        return null;
    }
}
