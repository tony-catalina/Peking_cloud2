package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbjlDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class Jls_JbjlService {
    @Autowired
    private Jls_JbjlDao jls_JbjlDao;

    @UseDataSource("jls_ds")
    public Map<String, Object> dpzbld(String jsbh) {
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	List<Map<String, Object>> list = jls_JbjlDao.dpzbld(jsbh);
    	System.err.println(JSON.toJSON(list));
    	map.put("lname", "值班领导");
    	map.put("lvalue", "无值班领导");
    	if(list.size()>0) {
    		map.put("lvalue", list.get(0).get("值班领导"));
    	}
        return  map;
    }


}
