package awd.cloud.platform.servers.analyse.service.kss;


import awd.cloud.platform.servers.analyse.dao.kss.JbxxDao;
import awd.cloud.platform.servers.analyse.dao.kss.XjDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class kss_AqglService {
    @Autowired
    private JbxxDao jbxxDao;
    
    @Autowired
    private XjDao xjDao;

   @UseDataSource("kss_ds")
   public Map<String,Object> getAqglCount(){
       Map<String,Object> jbxxMap=jbxxDao.aqglQuery();
       Map<String,Object>  xjMap=xjDao.aqglQuery();
       jbxxMap.put("dzjl",xjMap.get("dzjl").toString());
       return jbxxMap;
   }
}
