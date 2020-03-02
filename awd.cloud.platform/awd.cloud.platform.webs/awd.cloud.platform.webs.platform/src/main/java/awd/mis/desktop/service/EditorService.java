package awd.mis.desktop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service("editorService")
public class EditorService  {

	public Map<String, Object> getEditor(String slider) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAbout() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getEditor() {
		Map<String, Object> data = new HashMap<>();
		
		Map<String, Object> folder = new HashMap<>();
		
		folder.put("base64", true);
		folder.put("charset", "utf-8");
		folder.put("content", "eyJ0eXBlIjoiYXBwX2xpbmsiLCJjb250ZW50IjoiY29yZS5leHBsb3JlckNvZGUoJy9kZXNrdG9wL+aWsOW7uuaWh+S7tuWkuS8nKTsiLCJpY29uIjoiZm9sZGVyLnBuZyJ9");
		folder.put("ext", "oexe");
		folder.put("filename", "/desktop/新建文件夹_project.oexe");
		folder.put("name", "鏂板缓鏂囦欢澶筥project.oexe");
		data.put("data", folder);
		return data;
	}

}
