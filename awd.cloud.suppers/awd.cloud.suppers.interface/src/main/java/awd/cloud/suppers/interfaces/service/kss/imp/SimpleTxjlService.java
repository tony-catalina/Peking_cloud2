package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.TxjlService;
import awd.cloud.suppers.interfaces.utils.GetAge;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("txjlService")
public class SimpleTxjlService implements TxjlService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getTxjl(Map<String, Object> map) {

        QueryParam tsParam = new QueryParam();//提审查询条件
        tsParam.and("rybh", map.get("rybh"));
        tsParam.setPageIndex(Integer.parseInt((String) map.get("page")));
        tsParam.setPageSize(Integer.parseInt((String) map.get("pageSize")));

        ResponseMessage<PagerResult<Map<String, Object>>> tsResult = kssBaseServersApi.getTsdj(tsParam);

        QueryParam xxParam = new QueryParam();//人员信息查询条件
        xxParam.and("rybh", map.get("rybh"));
        ResponseMessage<PagerResult<Map<String, Object>>> xxResult = kssBaseServersApi.queryJbxx(xxParam);
        int age = 0;
        try {
            age = GetAge.getAge((String)xxResult.getResult().getData().get(0).get("csrq"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> lshjList = new ArrayList<>();
        Map<String, Object> result = Maps.newHashMap();
        for (Map<String, Object> m : tsResult.getResult().getData()) {
            Map<String, Object> lshjMap = Maps.newHashMap();
            lshjMap.put("txkssj", m.get("kssj"));//提讯开始时间
            lshjMap.put("txjssj", m.get("jssj"));//提讯结束时间
            lshjMap.put("txmj", "数据库无该字段");//提讯民警 (数据库无该字段)

            lshjList.add(lshjMap);
        }

        result.put("result", lshjList);
        result.put("rybh", map.get("rybh"));//被监管人员编号
        result.put("xm", xxResult.getResult().getData().get(0).get("xm"));//被监管人员姓名
        result.put("nl", age);//被监管人员年龄
        result.put("jsh", map.get("jsh"));//监室号
        result.put("page", map.get("page"));//当前页数

        tsResult.getResult().getData().clear();
        tsResult.getResult().getData().add(result);

        return tsResult;
    }

    @Override
    public ResponseMessage<String> saveTxjl(Map<String, Object> map) {
        return kssBaseServersApi.saveTsdj(map);
    }

    @Override
    public ResponseMessage<String> updateTxjlById(String id,Map<String, Object> map) {
        return kssBaseServersApi.updateTsdjById(id,map);
    }
}
