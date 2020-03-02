package awd.mis.servers.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.mis.servers.service.PublicService;
import awd.mis.servers.utils.FileUtil;

@Service
public class PublicServiceImpl implements PublicService {

	
	@Autowired
	private FileUtil file;
	
	/**
	 * 从ftp服务器 下载文件到本地，并返回文件路径
	 */
	@Override
	public String getFtpFileByName(String fileName) {
		String fileContent = file.getFtpFileByName(fileName);
		return fileContent;
	}
	
	@Override
	public String uploadPulicFileToFtp(String fileName, String fileContent) {
		String filePath = file.uploadPulicFileToFtp(fileName, fileContent);
		return filePath;
	}
	
}
