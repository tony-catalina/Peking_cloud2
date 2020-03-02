package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.WgsjclDao;
import awd.cloud.platform.servers.analyse.utils.DateUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Kss_WgsjclService {
    @Autowired
    private WgsjclDao wgsjclDao;

    /**
     * 
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>>  wgqkfxCount(){
        return wgsjclDao.wgqkfxCount();
    }
    @UseDataSource("kss_ds")
    public Number [] getBzwgrs(String jsbh, String jsh) {
        String bzkssjAndBzjssj = DateUtils.getBzkssjAndBzjssj(new Date());
        String bzkssj=bzkssjAndBzjssj.split(",")[0];
        String bzjssj=bzkssjAndBzjssj.split(",")[1];
        //处理没有违规人数的情况,列表中新增一条数据并默认为0
        LinkedList<Map<String, Object>> bzwgrs = wgsjclDao.getBzwgrs(jsbh, jsh, bzkssj, bzjssj);
        for (int i=1;i<=7;i++){//匹配周一到周日
            int count=0;
            for (int j=0;j<bzwgrs.size();j++){//逐次和已有列表比较，找出没有的day_of_week
                if((Integer)bzwgrs.get(j).get("day_of_week")==i){
                    count++;
                }
            }
            if (count==0){
                HashMap<String,Object> map=new HashMap<>();
                map.put("rs",0);
                map.put("day_of_week",i);
                bzwgrs.add(i-1, map);
            }
        }
        System.err.println("返回结果"+bzwgrs);
        //以特定格式返回数据
        Number [] rs=new Number[7];
        for (int i=0;i<rs.length;i++){
            rs[i]= (Number) bzwgrs.get(i).get("rs");
        }
        return  rs;
    }
    @UseDataSource("kss_ds")
    public Number [] getBzjswgsj(String jsbh, String jsh,String cllx) {
        String bzkssjAndBzjssj = DateUtils.getBzkssjAndBzjssj(new Date());
        String bzkssj=bzkssjAndBzjssj.split(",")[0];
        String bzjssj=bzkssjAndBzjssj.split(",")[1];
        //处理没有违规事件的情况,列表中新增1条或两条数据并默认为sjs=0
        LinkedList<Map<String, Object>> bzwgrs = wgsjclDao.getBzjswgsj(jsbh, jsh, bzkssj, bzjssj);
        for (int i=1;i<=7;i++){//匹配周一到周日
            int count=0;
            for (int j=0;j<bzwgrs.size();j++){//逐次和已有列表比较，找出没有的day_of_week
                if((Integer)bzwgrs.get(j).get("day_of_week")==i){
                    count++;
                }
            }
            if (count==0){//已处理和未处理的均没有，要添加两条
                LinkedHashMap<String,Object> map=new LinkedHashMap<>();
                //插入已处理
                map.put("day_of_week",i);
                map.put("clzt","0");
                map.put("sjs",0);
                bzwgrs.add(2*(i-1), map);
                System.err.println(JSON.toJSONString(map));
                //插入未处理
                LinkedHashMap<String,Object> map1=new LinkedHashMap<>();
                map1.put("day_of_week",i);
                map1.put("clzt","1");
                map1.put("sjs",0);
                System.err.println(JSON.toJSONString(map1));
                bzwgrs.add(2*i-1, map1);
            }
            if(count==1){
                if ( bzwgrs.get(i).get("clzt").equals("0")){
                    //存在未处理的，则插入已处理的
                    LinkedHashMap<String,Object> map=new LinkedHashMap<>();
                    map.put("day_of_week",i);
                    map.put("clzt","1");
                    map.put("sjs",0);
                    bzwgrs.add(2*i-1, map);
                }else {
                    //存在已处理的，则插入未处理的
                    LinkedHashMap<String,Object> map=new LinkedHashMap<>();
                    map.put("day_of_week",i);
                    map.put("clzt","0");
                    map.put("sjs",0);
                    bzwgrs.add(2*(i-1), map);
                }
            }
        }
        //以特定格式返回数据
        Number [] rs=new Number[7];
        for (int i=0;i<rs.length;i++){
            if ("1".equals(cllx)){
                rs[i]= (Number) bzwgrs.get(2*i+1).get("sjs");
            }else {
                rs[i]= (Number) bzwgrs.get(2*i).get("sjs");
            }
        }
        return  rs;
    }
}
