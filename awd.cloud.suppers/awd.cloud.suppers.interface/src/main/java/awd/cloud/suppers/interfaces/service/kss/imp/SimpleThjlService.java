package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.ThjlService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("thjlService")
public class SimpleThjlService implements ThjlService {

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Autowired
    private KssCoreServersApi kssCoreServersApi;

    @Override
    public Map<String, Object> getThjl(Map<String, Object> map) {
        QueryParam thParam = new QueryParam();//谈话教育查询条件
        QueryParam xxParam = new QueryParam();//人员信息查询条件

        thParam.and("rybh",map.get("rybh"));
        thParam.setPageIndex(Integer.parseInt((String)map.get("page")));
        thParam.setPageSize(Integer.parseInt((String)map.get("pageSize")));
        ResponseMessage<PagerResult<Map<String, Object>>> thjlList = kssCoreServersApi.getThjl(thParam);

        xxParam.and("rybh",map.get("rybh"));
        ResponseMessage<PagerResult<Map<String, Object>>> xxList = kssBaseServersApi.queryJbxx(xxParam);

        Map<String , Object> result = Maps.newHashMap();
        List<Map<String , Object>> list = new ArrayList<>();
        for (Map<String, Object> resultMap : thjlList.getResult().getData()) {
            Map<String, Object> m = Maps.newHashMap();
            m.put("thmj",resultMap.get("fzmj"));//谈话民警
            m.put("thkssj",resultMap.get("kssj"));//谈话开始时间
            m.put("thjssj",resultMap.get("jssj"));//谈话结束时间
            m.put("thnr",resultMap.get("thnr"));//谈话内容
            list.add(m);
        }

        result.put("result",list);
        result.put("rybh",map.get("rybh"));//被监管人员编号
        result.put("jsh",map.get("jsh"));//监室号
        result.put("xm",xxList.getResult().getData().get(0).get("xm"));//姓名
        result.put("xb",xxList.getResult().getData().get(0).get("xb"));//性别
        result.put("total",thjlList.getResult().getTotal());//总条数
        result.put("page",map.get("page"));//当前页数

        return result;
    }

    @Override
    public ResponseMessage<String> saveThjl(Map<String, Object> map) {
        return kssCoreServersApi.saveThjl(map);
    }

    @Override
    public ResponseMessage<String> updateThjlById(String id,Map<String, Object> map) {
        return kssCoreServersApi.updateThjlById(id,map);
    }
}
