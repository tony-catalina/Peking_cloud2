package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.LshjService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("lshjService")
public class SimpleLshjService implements LshjService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getLshj(Map<String, Object> map) {

        QueryParam queryParam = new QueryParam();
        queryParam.and("rybh", map.get("rybh"));
        queryParam.setPageIndex(Integer.parseInt((String) map.get("page")));
        queryParam.setPageSize(Integer.parseInt((String) map.get("pageSize")));

        ResponseMessage<PagerResult<Map<String, Object>>> resultList = kssBaseServersApi.getLshj(queryParam);

        List<Map<String, Object>> lshjList = new ArrayList<>();
        Map<String, Object> result = Maps.newHashMap();
        for (Map<String, Object> m : resultList.getResult().getData()) {
            Map<String, Object> lshjMap = Maps.newHashMap();
            lshjMap.put("lsxm", m.get("lsxm") + " ; " + m.get("lsxm2"));//律师姓名
            lshjMap.put("hjsj", m.get("hjsj"));//会见开始时间
            lshjMap.put("jssj", m.get("jssj"));//会见结束时间
            lshjMap.put("rybh", map.get("rybh"));//被监管人员编号
            lshjMap.put("jsh", map.get("jsh"));//监室号
            lshjList.add(lshjMap);
        }

        result.put("result", lshjList);
        result.put("page", map.get("page"));//当前页数

        resultList.getResult().getData().clear();
        resultList.getResult().getData().add(result);

        return resultList;
    }

    @Override
    public ResponseMessage<String> saveLshj(Map<String, Object> map) {
        return kssBaseServersApi.saveLshj(map);
    }

    @Override
    public ResponseMessage<String> updateLshjById(String id,Map<String, Object> map) {
        return null;
    }
}
