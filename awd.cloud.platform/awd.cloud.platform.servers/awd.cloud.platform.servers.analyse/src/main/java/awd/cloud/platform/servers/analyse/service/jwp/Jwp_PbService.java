package awd.cloud.platform.servers.analyse.service.jwp;

import awd.cloud.platform.servers.analyse.dao.jwp.Jwp_PbDao;

import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jwp_PbService {

    @Autowired
    private Jwp_PbDao Jwp_pbDao;

    @UseDataSource("kss_ds")
    public Map<String, Object> zyCount(String jsbh,String jsh){
        return Jwp_pbDao.zyCount(jsbh,jsh);
    }

   /* @UseDataSource("kss_ds")
    public List<Map<String, Object>> wxdjCount(@Param("wxdj")String wxdj){
        return Jwp_pbDao.wxdjCount(wxdj);
    }

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> bhlxCount(@Param("bhlx")String bhlx){
        return Jwp_pbDao.bhlxCount(bhlx);
    }

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> jjCount(@Param("jj")String jj){
        return Jwp_pbDao.jjCount(jj);
    }


    @UseDataSource("jwp_ds")
    public List<Map<String, Object>> yjCount(@Param("jsbh")String jsbh){
        return Jwp_pbDao.yjCount(jsbh);
    }

    @UseDataSource("jwp_ds")
    public List<Map<String, Object>> ybCount(@Param("jsbh")String jsbh){
        return Jwp_pbDao.ybCount(jsbh);
    }

    @UseDataSource("kss_ds")
    public  List<JsModel> mjxx(@Param("jsbh") String jsbh, @Param("jsh") String jsh){
        return Jwp_pbDao.mjxx(jsbh,jsh);
    }
*/

}
