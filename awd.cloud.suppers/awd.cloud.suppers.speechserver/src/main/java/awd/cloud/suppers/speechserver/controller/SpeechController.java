package awd.cloud.suppers.speechserver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import awd.cloud.suppers.speechserver.service.SpeechService;

@RestController
public class SpeechController {
	
	@Autowired
	private SpeechService speechService;
	
	@GetMapping("/speech")
	public Map<String, Object> speech(String content){
		Map<String, Object> result=new HashMap<String, Object>();
		
		String path=speechService.getVoice(content);
		if(StringUtils.isEmpty(path)) {
			result.put("code", 400);
			result.put("msg","生成失败");
			result.put("succes", false);
		}else {
			result.put("code", 200);
			result.put("data",path);
			result.put("msg","生成成功");
			result.put("succes", true);
		}		
		return result;		
	}

}
