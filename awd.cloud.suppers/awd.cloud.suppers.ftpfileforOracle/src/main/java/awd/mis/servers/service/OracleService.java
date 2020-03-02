/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.Map;

public interface OracleService {

	void uploadFileToFtp();
	
	Map<String, Object> uploadJsonToFtp(String jsonString);
	
	String downLoadFileByFtp();
	
	void getJsonFileByFtp();
	
	String readFtpFileToEntity();

}
