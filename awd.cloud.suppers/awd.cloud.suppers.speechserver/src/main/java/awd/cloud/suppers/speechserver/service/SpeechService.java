package awd.cloud.suppers.speechserver.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;

@Service
public class SpeechService {
	@Value("${speech.balabolka}")
	private String balabolka;
	
	@Value("${speech.voicedir}")
	private String voicedir;

	@Value("${speech.server}")
	private String server;
	
	public String getVoice(String content) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");		
		String cmd=balabolka+" -snq \""+content+"\" \""+voicedir+uuid+".mp3"+"\"";
		System.err.println(cmd);
		String str = RuntimeUtil.execForStr(cmd);
		System.err.println(str);
		boolean has=FileUtil.exist(voicedir+uuid+".mp3");
		if(has) {			
			return server+"/voice/"+uuid+".mp3";
		}else {
			return null;
		}		
		
	}

}
