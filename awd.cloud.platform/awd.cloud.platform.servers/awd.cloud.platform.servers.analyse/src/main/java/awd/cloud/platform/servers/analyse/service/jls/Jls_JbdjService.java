package awd.cloud.platform.servers.analyse.service.jls;



import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbdjDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_JbdjService {
     @Autowired
     private Jls_JbdjDao jls_jbdjDao;

     @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCx (String starttime, String endtime, String jsbh ){
          return  jls_jbdjDao.jbxxCx(starttime, endtime, jsbh);
     }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxa (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxa(starttime, endtime, jsbh);
    }
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxb (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxb(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxc (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxc(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxd (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxd(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxe (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxe(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxf (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxf(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxg (String starttime, String endtime, String jsbh){
        return  jls_jbdjDao.jbxxCxg(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxh (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxh(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxi (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxi(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxj (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxj(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxk (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxk(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxl (String starttime, String endtime, String jsbh ){
        return  jls_jbdjDao.jbxxCxl(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxm (String starttime, String endtime, String jsbh){
        return  jls_jbdjDao.jbxxCxm(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxn (String starttime, String endtime, String jsbh){
        return  jls_jbdjDao.jbxxCxn(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxo (String starttime, String endtime, String jsbh){
        return  jls_jbdjDao.jbxxCxo(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxp (String starttime, String endtime, String jsbh){
        return  jls_jbdjDao.jbxxCxp(starttime, endtime, jsbh);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jbdjCxq (String starttime, String endtime, String jsbh){
        return  jls_jbdjDao.jbxxCxq(starttime, endtime, jsbh);
    }
}
