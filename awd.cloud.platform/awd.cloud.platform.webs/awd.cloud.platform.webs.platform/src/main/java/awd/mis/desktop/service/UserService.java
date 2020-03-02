package awd.mis.desktop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service("userService")
public class UserService {

	public Map<String, Object> getdata() {
		Map<String , Object> data = new HashMap<>();
		Map<String , Object> floder = new HashMap<>();
		floder.put("can_upload", "");
		floder.put("code_read", "");
		floder.put("mtime", 1521457161);
		floder.put("name", "新建文件夹");
		floder.put("not_download", "");
		floder.put("path", "/desktop/新建文件夹/");
		floder.put("share_password", "");
		floder.put("show_name", "新建文件夹");
		floder.put("sid", "z3UVpFjv");
		floder.put("time_to", "");
		floder.put("type", "folder");
		data.put("data", floder);
		return data;
	}

	public Map<String, Object> getInfo() {
		Map<String , Object> data = new HashMap<>();
		Map<String , Object> floder = new HashMap<>();
		Map<String , Object> map = new HashMap<>();
		map.put("can_upload", "");             
		map.put("code_read", "");              
		map.put("mtime", 1521457161);          
		map.put("name", "新建文件夹");              
		map.put("not_download", "");           
		map.put("path", "/desktop/新建文件夹/");    
		map.put("share_password", "");         
		map.put("show_name", "新建文件夹");         
		map.put("sid", "z3UVpFjv");            
		map.put("time_to", "");                
		map.put("type", "folder");     
		floder.put("z3UVpFjv", map);
		data.put("data", floder);
		return data;
	}

}
