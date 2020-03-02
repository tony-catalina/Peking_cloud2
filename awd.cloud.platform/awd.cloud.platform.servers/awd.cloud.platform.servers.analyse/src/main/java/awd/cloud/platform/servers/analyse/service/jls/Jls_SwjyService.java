package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JqDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_SnjyDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_SwjyDao;
import awd.cloud.platform.servers.analyse.model.jls.JqModel;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Jls_SwjyService {
    @Autowired
    private Jls_SwjyDao jls_SwjyDao;

    @Autowired
    private Jls_SnjyDao jls_SnjyDao;
    
    @Autowired
    private Jls_JqDao jls_JqDao;
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> swjyCount(String jsbh, String starttime,String endtime) {
        return  jls_SwjyDao.swjyCount(jsbh,starttime,endtime);
    }
    
    @UseDataSource("jls_ds")
    public Map<String, Object> dpylgl(String jsbh) {
        return  jls_SwjyDao.dpylgl(jsbh);
    }
    
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> dpjqbb(String jsbh) {
    	List<Map<String, Object>> snjy = jls_SnjyDao.snjyBym(jsbh);
    	List<Map<String, Object>> swjy = jls_SwjyDao.swjyBym(jsbh);
    	List<String> monthlist = getLastMonths(6);
    	List<JqModel> jqlist = jls_JqDao.jqlist(jsbh);
    	List<Map<String, Object>> jqlists = new ArrayList<Map<String,Object>>();
    	if(jqlist.size()>0) {
    		for (JqModel jqModel : jqlist) {
    			String jq = "";
    			Map<String, Object> maps = new LinkedHashMap<String, Object>();
    			maps.put("name", jqModel.getJqmc());
    			maps.put("icon", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAKCAYAAABrGwT5AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkVDNEZBMzVCQUVCNzExRTlBRTk3QjM1QUIwMTEwMUZEIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkVDNEZBMzVDQUVCNzExRTlBRTk3QjM1QUIwMTEwMUZEIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RUM0RkEzNTlBRUI3MTFFOUFFOTdCMzVBQjAxMTAxRkQiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RUM0RkEzNUFBRUI3MTFFOUFFOTdCMzVBQjAxMTAxRkQiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4N0xl3AAAAg0lEQVR42pyQPQ5AQBCFx0bpOBxA7+ccytVS0nIX9GpZx7E1b5JRiWLnJV8z875sZqN7b0hSAgsykNA3F3BgAhsPjCwGsID8RySZ836VPsWgAB2FhfsHv9ySLpblVCmnhvSJWD6VsmN5Vsqzka8fA0Xur+/NPajBDvyP4GVfSZ8eAQYAILsWvVWWYFYAAAAASUVORK5CYII=");
				List<Map<String, Object>> lisy = new ArrayList<Map<String,Object>>();
    			for (String month : monthlist) {
					Map<String, Object> map  = new LinkedHashMap<String, Object>();
					map.put("name", month);
					map.put("value", "0");
					for (Map<String, Object> mapsn : snjy) {
						if(month.equals(mapsn.get("month"))&&jqModel.getJqh().equals(mapsn.get("jq"))) {
							map.put("value", Integer.parseInt(mapsn.get("sl").toString()));
						}
					}
					for (Map<String, Object> mapsw : swjy) {
						if(month.equals(mapsw.get("month"))&&jqModel.getJqh().equals(mapsw.get("jq"))) {
							map.put("value", Integer.parseInt(map.get("value").toString())+Integer.parseInt(mapsw.get("sl").toString()));
						}
					}
					lisy.add(map);
				}
    			maps.put("list", lisy);
    			jqlists.add(maps);
    		}
    	}else {
    		Map<String, Object> maps = new LinkedHashMap<String, Object>();
    		maps.put("list", "");
			jqlists.add(maps);
    	}
    	System.err.println(jqlists);
        return  jqlists;
    }
    //获取前几个月
    public static List<String> getLastMonths(int size) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        List<String> list = new ArrayList(size);
        for (int i=0;i<size;i++) {
            c.setTime(new Date());
            c.add(Calendar.MONTH, -i);
            Date m = c.getTime();
            list.add(sdf.format(m));
        }
        Collections.reverse(list);
        return list;

    }

     //jls 总队全市出所就医
    @UseDataSource("jls_ds")
    public Map<String, Object> find_jls_QSCSJY(){
        Map<String, Object> jls_qscsjy = jls_SwjyDao.select_jls_QSCSJY();
       /* int jls_swjy = Integer.parseInt(jls_qscsjy.get("swjy").toString());
        int jls_mqzy = Integer.parseInt(jls_qscsjy.get("mqzy").toString());
        int jls_gayy = Integer.parseInt(jls_qscsjy.get("gayy").toString());*/

        return jls_qscsjy;
    }
}
