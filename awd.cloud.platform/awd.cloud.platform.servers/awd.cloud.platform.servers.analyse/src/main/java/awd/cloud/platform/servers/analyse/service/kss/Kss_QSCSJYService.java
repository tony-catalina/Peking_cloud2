package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.QSCSJYDao;
import awd.cloud.platform.servers.analyse.service.jls.Jls_SwjyService;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-13 9:34
 * Description：<描述>
 */

@Service
public class Kss_QSCSJYService {

    @Autowired
    private QSCSJYDao qscsjyDao;
    @Autowired
    private Jls_SwjyService jls_swjyService;

    //1kss全市出所就医
    @UseDataSource("kss_ds")
    public ArrayList<Object> find_QSCSJY(){

        Map<String, Object> map = qscsjyDao.select_QSCSJY(); //kss
        Integer swjy = Integer.parseInt(map.get("swjy").toString());
        Integer mqzy = Integer.parseInt(map.get("mqzy").toString());
        Integer gayy = Integer.parseInt(map.get("gayy").toString());
        /*int swjy_kss = Integer.parseInt(this.find_QSCSJY().get("swjy").toString());//kss
        int mqzy_kss = Integer.parseInt(this.find_QSCSJY().get("mqzy").toString());//kss
        int gayy_kss = Integer.parseInt(this.find_QSCSJY().get("gayy").toString());//kss*/
        Map<String, Object> jls_qscsjy = jls_swjyService.find_jls_QSCSJY();
        int jls_swjy = Integer.parseInt(jls_qscsjy.get("swjy").toString());
        int jls_mqzy = Integer.parseInt(jls_qscsjy.get("mqzy").toString());
        int jls_gayy = Integer.parseInt(jls_qscsjy.get("gayy").toString());
        System.out.println("jls_swjy="+jls_swjy);
        System.out.println("jls_mqzy="+jls_mqzy);  System.out.println("jls_gayy="+jls_gayy);
        int i = swjy + jls_swjy;
        int i1 = mqzy + jls_mqzy;
        int i2 = gayy + jls_gayy;


        HashMap<String, Object> map1 = new HashMap<>();//最里层
        map1.put("name", "出所就医人数");
        map1.put("value", i);
        map1.put("unit", "人");
        HashMap<String, Object> map2 = new HashMap<>();//最里层
        map2.put("name", "目前住院人数");
        map2.put("value", i1);
        map2.put("unit", "人");
        HashMap<String, Object> map3 = new HashMap<>();//最里层
        map3.put("name", "公安医院人数");
        map3.put("value", i2);
        map3.put("unit", "人");
        ArrayList<Object> listL = new ArrayList<>();
        listL.add(map1);listL.add(map2);listL.add(map3);


        return listL;
    }
}
