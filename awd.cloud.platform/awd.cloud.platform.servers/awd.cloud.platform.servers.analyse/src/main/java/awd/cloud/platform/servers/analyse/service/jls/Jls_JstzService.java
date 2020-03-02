package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JstzDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_JstzService {
    @Autowired
    private Jls_JstzDao jls_JstzDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jstzCount(String jsbh, String starttime,String endtime) {
        return  jls_JstzDao.jstzCount(jsbh,starttime,endtime);
    }


    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> queryJstzList(String starttime, String endtime){
        return jls_JstzDao.queryJstzNum(starttime,endtime);
    }


}
