package awd.cloud.platform.servers.analyse.dao.jls;

import awd.cloud.platform.servers.analyse.model.jls.JsModel;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

public interface Jls_JsDao extends Dao {
	List<JsModel> jqlist(@Param("jsbh")String jsbh,@Param("jqh")String jqh);
}
