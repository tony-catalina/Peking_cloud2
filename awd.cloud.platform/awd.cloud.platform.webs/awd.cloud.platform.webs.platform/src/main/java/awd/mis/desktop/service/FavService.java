package awd.mis.desktop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service("favService")
public class FavService {

	public Map<String, Object> get() {
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> sad = new HashMap<>();
		Map<String, Object> floder = new HashMap<>();
		floder.put("name", "sad");
		floder.put("path", "das");
		floder.put("type", "folder");
		sad.put("sad", floder);
		data.put("data", sad);
		return data;
	}

}
