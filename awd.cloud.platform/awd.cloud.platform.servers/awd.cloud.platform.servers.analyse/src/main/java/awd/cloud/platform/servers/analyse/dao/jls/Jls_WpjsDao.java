package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jls_WpjsDao extends Dao {

	  int swzsCount( @Param("jsbh")String jsbh,@Param("starttime")String starttime,
	            @Param("endtime")String endtime);
	  
	  int jszsCount( @Param("jsbh")String jsbh,@Param("starttime")String starttime,
	            @Param("endtime")String endtime);
	  
	  int jrswCount( @Param("jsbh")String jsbh);
	  
	  int jrjsCount( @Param("jsbh")String jsbh);


}
