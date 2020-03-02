package awd.cloud.suppers.interfaces.service.kss.imp;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.service.kss.JyjlService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("jyjlService")
public class SimpleJyjlService implements JyjlService {

    @Autowired
    private KssCoreServersApi kssCoreServersApi;

    @Autowired
    private KssBaseServersApi kssBaseServersApi;

    @Override
    public ResponseMessage<PagerResult<Map<String, Object>>> getJyjl(Map<String, Object> map) {
        QueryParam jyjlParam = new QueryParam();//就医记录查询条件
        QueryParam jbxxParam = new QueryParam();//基本信息查询条件

        jyjlParam.and("rybh", map.get("rybh"));
        jyjlParam.setPageIndex(Integer.parseInt((String) map.get("page")));
        jyjlParam.setPageSize(Integer.parseInt((String) map.get("pageSize")));
        ResponseMessage<PagerResult<Map<String, Object>>> jyjlList = kssCoreServersApi.getJyjl(jyjlParam);

        jbxxParam.and("rybh", map.get("rybh"));
        ResponseMessage<PagerResult<Map<String, Object>>> jbxxList = kssBaseServersApi.queryJbxx(jbxxParam);

        Map<String, Object> resultMap = Maps.newHashMap();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map<String, Object> result : jyjlList.getResult().getData()) {
            Map<String, Object> m = Maps.newHashMap();
            m.put("zdrq", result.get("zdrq"));//诊断日期
            m.put("ys", result.get("ysxm"));//医生姓名
            m.put("ly", result.get("ly"));//来源
            m.put("bq", result.get("brbq"));//病情
            m.put("zd", result.get("zdqk"));//诊断
//            m.put("yz", result.get(""));//医嘱(没有对应字段)
            m.put("cljg", result.get("cljg"));//处理结果
            m.put("bhlx", result.get("bhlx"));//病号类型
            m.put("bz", result.get("bz"));//备注
            m.put("ptmj", result.get("ptmj"));//陪同民警
            m.put("jzyy", result.get("jzyy"));//就诊医院

            resultList.add(m);
        }

        resultMap.put("result", resultList);
        resultMap.put("rybh", map.get("rybh"));//人员编号
        resultMap.put("jsh", map.get("jsh"));//监室号
        resultMap.put("total", jyjlList.getResult().getTotal());//总条数
        resultMap.put("page", map.get("page"));//当前页数
        resultMap.put("xm", jbxxList.getResult().getData().get(0).get("xm"));//姓名
        resultMap.put("xb", jbxxList.getResult().getData().get(0).get("xb"));//性别

        jyjlList.getResult().getData().clear();
        jyjlList.getResult().getData().add(resultMap);

        return jyjlList;
    }

    @Override
    public ResponseMessage<String> saveJyjl(Map<String, Object> map) {

        return kssCoreServersApi.saveJyjl(map);
    }

    @Override
    public ResponseMessage<String> updateJyjlById(String id, Map<String, Object> map) {

        return kssCoreServersApi.updateJyjlById(id, map);
    }
}
