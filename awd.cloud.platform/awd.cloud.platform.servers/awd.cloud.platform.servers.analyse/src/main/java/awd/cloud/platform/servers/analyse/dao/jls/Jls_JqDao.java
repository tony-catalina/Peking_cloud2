package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.model.jls.JqModel;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_JqDao extends Dao {
	
	List<JqModel> jqlist(@Param("jsbh") String jsbh);
}
