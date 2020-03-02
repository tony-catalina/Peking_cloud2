package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_XsjlDao extends Dao {

	List<Map<String, Object>> dpjqwg(@Param("jsbh") String jsbh);
	
	Map<String, Object> dpjqwgry(@Param("jsbh") String jsbh);

	List<AnalysisJlsResultVO> queryXsjlNum(@Param(value = "starttime")String starttime,
                                           @Param(value = "endtime")String endtime);
}
