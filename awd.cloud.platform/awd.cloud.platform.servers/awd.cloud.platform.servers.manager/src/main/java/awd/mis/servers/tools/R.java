package awd.mis.servers.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 */
public class R{
	
	private HashMap<String,Object> map;
	
	public R() {
		map = new HashMap<String,Object>();
		map.put("code", "1");
	}
	
	
	public static R error() {
		return error("msg", "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error("msg", msg);
	}
	
	public static R error(String code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		map.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public Object get (String key) {
		return map.get(key);
	}

	public HashMap<String, Object> getMap() {
		return map;
	}


	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}
}
