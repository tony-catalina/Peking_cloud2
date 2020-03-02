package awd.cloud.platform.servers.analyse.service.all;


import awd.cloud.platform.servers.analyse.dao.jls.Jls_DsjPTDao;
import awd.cloud.platform.servers.analyse.dao.kss.DsjPTDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

//关押总量
@Service
public class Sy_gylService {

    @Autowired
    private DsjPTDao dsjPTDao;//大屏看守所在押人数
    @Autowired
    private Jls_DsjPTDao jlsDsjPTDao; //大屏拘留所在押人数

    @UseDataSource("kss_ds")
    public Integer find_gyl(){
        System.out.println("kss_ds进了");
        Map<String, Object> zyRS = dsjPTDao.zyRS();//大屏看守所在押人数

        int zy = Integer.parseInt(zyRS.get("zy").toString());

  return zy;

    }

    @UseDataSource("jls_ds")
    public Integer find_gyl_jls(){

        Map<String, Object> zyRS_jls = jlsDsjPTDao.zyRS_jls();//大屏拘留所在押人数
        int zy1 = Integer.parseInt(zyRS_jls.get("zy").toString());
        return zy1;
    }








  /*  @Autowired
    private JbxxDao jbxxDao;
    @Autowired
    private Jls_JbxxDao jls_jbxxDao;

    @UseDataSource("kss_ds")
    public int kss_gylQuery(){
        Integer kssRs=jbxxDao.zyrsQuery();
        return kssRs;
    }
    @UseDataSource("jls_ds")
    public int jls_gylQuery(){
        Integer jlsRs=jls_jbxxDao.zyrsQuery();
        return jlsRs;
    }*/

}
