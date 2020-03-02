package awd.cloud.platform.servers.analyse.dao.jwp;

import awd.cloud.platform.servers.analyse.model.jwp.JsModel;
import awd.framework.common.dao.api.Dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Jwp_PbDao  extends Dao {

    Map<String, Object> zyCount(@Param("jsbh")String jsbh,
                                      @Param("jsh")String jsh);

    /*List<Map<String, Object>> wxdjCount(@Param("wxdj")String wxdj);

    List<Map<String, Object>> bhlxCount(@Param("bhlx")String bhlx);

    List<Map<String, Object>> jjCount(@Param("jj")String jj);

    List<Map<String, Object>> yjCount(@Param("jsbh")String jsbh);

    List<Map<String, Object>> ybCount(@Param("jsbh")String jsbh);

    List<JsModel> mjxx(@Param("jsbh")String jsbh, @Param("jsh")String jsh);

*/








}
