package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_XsjlDao;
import awd.cloud.platform.servers.analyse.model.jls.JqModel;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class Jls_XsjlService {
    @Autowired
    private Jls_XsjlDao jls_XsjlDao;

    @Autowired
    private Jls_JqService jls_JqDao;
    
    @UseDataSource("jls_ds")
    public Map<String, Object> jlsdpjqwgqst(String jsbh) {
    	List<JqModel> jqlist = jls_JqDao.jqlist(jsbh);
    	List<Map<String, Object>> xsjllist = jls_XsjlDao.dpjqwg(jsbh);
    	List<Map<String, Object>> msp1 = new ArrayList<Map<String,Object>>();
    	List<Map<String, Object>> msp2 = new ArrayList<Map<String,Object>>();
    	List<Map<String, Object>> msp3 = new ArrayList<Map<String,Object>>();
    	int i =0;
    	int j = 0;
    	int s = 0;
    	Map<String, Object> listmnap = new LinkedHashMap<String, Object>();
    	Map<String, Object> listmnap1 = new LinkedHashMap<String, Object>();
    	Map<String, Object> listmnap2 = new LinkedHashMap<String, Object>();
    	List<Map<String, Object>> lis = new ArrayList<Map<String,Object>>();
    	if(jqlist.size()>0) {
    		String a = "";
	    		for (JqModel jqModel : jqlist) {
					for (Map<String, Object> map2 : xsjllist) {
						if(map2.get("jsh").toString().startsWith(jqModel.getJqh())&&"5".equals(map2.get("jjcd").toString())) {
							a =jqModel.getJqh()+","+a;
							Map<String, Object> map = new LinkedHashMap<String, Object>();
							map.put("name", jqModel.getJqmc());
							map.put("value", Integer.parseInt(map2.get("sl").toString()));
							i+=Integer.parseInt(map2.get("sl").toString());
							map.put("jsh", map2.get("jsh").toString().subSequence(0, 2));
							map.put("jjcd", "5");
							msp1.add(map);
						}
					}
					if(!a.contains(jqModel.getJqh())) {
						System.err.println("--"+jqModel.getJqh());
						Map<String, Object> map111 = new LinkedHashMap<String, Object>();
						map111.put("name", jqModel.getJqmc());
						map111.put("value", 0);
						map111.put("jsh", jqModel.getJqh().subSequence(0, 2));
						map111.put("jjcd", "5");
						msp1.add(map111);
					}
				}
	    		listmnap.put("name", "件");
	    		listmnap.put("min", i);
	    		listmnap.put("list", msp1);
	    		String b = "";
	    		for (JqModel jqModel : jqlist) {
					for (Map<String, Object> map2 : xsjllist) {
						if(map2.get("jsh").toString().startsWith(jqModel.getJqh())&&"4".equals(map2.get("jjcd").toString())) {
							b =jqModel.getJqh()+","+b;
							Map<String, Object> map = new LinkedHashMap<String, Object>();
							map.put("name", jqModel.getJqmc());
							map.put("value", Integer.parseInt(map2.get("sl").toString()));
							j+=Integer.parseInt(map2.get("sl").toString());
							map.put("jsh", map2.get("jsh").toString().subSequence(0, 2));
							map.put("jjcd", "4");
							msp2.add(map);
						}
					}
					if(!b.contains(jqModel.getJqh())) {
						System.err.println("--"+jqModel.getJqh());
						Map<String, Object> map111 = new LinkedHashMap<String, Object>();
						map111.put("name", jqModel.getJqmc());
						map111.put("value", 0);
						map111.put("jsh", jqModel.getJqh().subSequence(0, 2));
						map111.put("jjcd", "4");
						msp2.add(map111);
					}
				}
	    		listmnap1.put("name", "件");
	    		listmnap1.put("min", j);
	    		listmnap1.put("list", msp2);
	    		String c = "";
	    		for (JqModel jqModel : jqlist) {
					for (Map<String, Object> map2 : xsjllist) {
						if(map2.get("jsh").toString().startsWith(jqModel.getJqh())&&"2".equals(map2.get("jjcd").toString())) {
							c +=jqModel.getJqh()+",";
							Map<String, Object> map = new LinkedHashMap<String, Object>();
							map.put("name", jqModel.getJqmc());	
							map.put("value", Integer.parseInt(map2.get("sl").toString()));
							s+=Integer.parseInt(map2.get("sl").toString());
							map.put("jsh", map2.get("jsh").toString().subSequence(0, 2));
							map.put("jjcd", "2");
							msp3.add(map);
						}
					}
					if(!c.contains(jqModel.getJqh())) {
						System.err.println("--"+jqModel.getJqh());
						Map<String, Object> map111 = new LinkedHashMap<String, Object>();
						map111.put("name", jqModel.getJqmc());
						map111.put("value", 0);
						map111.put("jsh", jqModel.getJqh().subSequence(0, 2));
						map111.put("jjcd", "2");
						msp3.add(map111);
					}
				}
	    		listmnap2.put("name", "件");
	    		listmnap2.put("min", s);
	    		listmnap2.put("list", msp3);
	    		lis.add(listmnap);
	    		lis.add(listmnap1);
	    		lis.add(listmnap2);
    	}
    	System.err.println("msp--"+JSON.toJSONString(msp1));
    	Map<String, Object> wpmap = new HashMap<String, Object>();
    	int all = i+j+s;
    	wpmap.put("text", lis);
    	List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
    	ArrayList<Map<String, Object>> namelist = new ArrayList<Map<String,Object>>();
    	Map<String, Object> maplist = new LinkedHashMap<String, Object>();
    	maplist.put("name", "一般");
    	maplist.put("value", i);
    	Map<String, Object> maplist1 = new LinkedHashMap<String, Object>();
    	maplist1.put("name", "急");
    	maplist1.put("value", j);
    	Map<String, Object> maplist2 = new LinkedHashMap<String, Object>();
    	maplist2.put("name", "特急");
    	maplist2.put("value", s);
    	namelist.add(maplist);
    	namelist.add(maplist1);
    	namelist.add(maplist2);
    	wpmap.put("title", namelist);
		return wpmap;
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> dpjqwgry(String jsbh) {
    	List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
    	Map<String, Object> xsjllist = jls_XsjlDao.dpjqwgry(jsbh);
        for (String key : xsjllist.keySet()) {
			Map<String, Object> maps = new HashMap<String, Object>();
			if("一般".equals(key)) {
				maps.put("id", 0);
				maps.put("type", 0);
				maps.put("rule", "轻");
			}else if("急".equals(key)) {
				maps.put("id", 1);
				maps.put("type", 1);
				maps.put("rule", "中");
			}else if("特急".equals(key)) {
				maps.put("id", 2);
				maps.put("type", 2);
				maps.put("rule", "重");
			}
			if(!"yg".equals(key)) {
				maps.put("name", key);
				if("0".equals(xsjllist.get(key).toString())) {
					maps.put("deci", 0);
				}else {
					maps.put("deci", String.format("%.2f", (float) Integer.parseInt(xsjllist.get(key).toString()) / (float) Integer.parseInt(xsjllist.get("yg").toString()) * 100));
				}
				maps.put("person", xsjllist.get(key));
				result.add(maps);
			}
		}
		return result;
    }

	@UseDataSource("jls_ds")
	public List<AnalysisJlsResultVO> queryXsjlList(String starttimt, String endtime){
		return jls_XsjlDao.queryXsjlNum(starttimt, endtime);
	}

}
