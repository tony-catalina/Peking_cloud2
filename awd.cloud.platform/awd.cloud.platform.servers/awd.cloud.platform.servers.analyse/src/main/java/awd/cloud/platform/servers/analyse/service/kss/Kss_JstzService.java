package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JstzDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * Create by wuyu on 2019/7/5 10:02
 **/
@Service
public class Kss_JstzService {
    @Autowired
    private JstzDao jstzDao;
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> jstz_rqcxJ(String starttime, String endtime) {

        return jstzDao.rqcxJ(starttime, endtime);
    }
    
    
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> Jstzcount(String starttime, String endtime,String jsbh) {
    	List<Map<String, Object>> jstzs = jstzDao.Jstzcount(starttime, endtime,jsbh);
    	System.err.println("jstz"+jstzs);
    	//R3
    	Map<String, Object> jstzmap = jstzs.get(0);
    	List<Map<String, Object>> jstzrs = jstzDao.JstzcountR(jsbh,starttime, endtime);
    	jstzmap.put("wtz", jstzrs.get(0).get("yg"));
    	List<Map<String, Object>> jstzlist = Lists.newArrayList();
    	jstzlist.add(jstzmap);
        return jstzlist;
    }
}
