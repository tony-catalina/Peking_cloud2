package awd.mis.activiti.utils;

import java.util.HashMap;
import java.util.Map;

import awd.mis.activiti.entity.Variables;

public class ProcessUtil {
	public static Map<String,String> processParams(Variables var){
		Map<String ,String > map = new HashMap<String,String>();
		if(var != null) {
			if (var.getJsbh()!=null) {
				map.put("jsbh", var.getJsbh());
			}
			if(var.getJsbh()!=null) {
				map.put("rybh", var.getRybh());
			}
			if(var.getYwlcid()!=null) {
				map.put("ywlcid", var.getYwlcid());
			}
			if(var.getParams()!=null) {
	    		Map<String , Object> params = var.getParams();
	    		for(Map.Entry<String, Object> entry : params.entrySet()) {
	    			if(StringUtil.isNotNullOrEmpty(entry.getValue())) {
	    				map.put(entry.getKey(),entry.getValue().toString());
	    			}
	    		}
	    	}
		}
		return map;
	}
}
