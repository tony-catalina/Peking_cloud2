package awd.cloud.platform.servers.analyse.service.kss;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.cloud.platform.servers.analyse.dao.kss.AqjcDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;

@Service
public class Kss_AqjcService {

	@Autowired
	private AqjcDao aqjcDao;
	
	@UseDataSource("kss_ds")
	public List<Map<String, Object>> aqjc_rqFx(String jsbh) {
		
		return aqjcDao.rqfx(jsbh);
	}

}
