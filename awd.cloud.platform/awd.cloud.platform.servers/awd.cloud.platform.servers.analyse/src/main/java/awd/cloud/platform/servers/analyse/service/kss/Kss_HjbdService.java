		package awd.cloud.platform.servers.analyse.service.kss;

        import awd.cloud.platform.servers.analyse.dao.kss.HjbdDao;
        import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
        import awd.framework.common.datasource.api.annotation.UseDataSource;
        import org.apache.commons.collections.map.HashedMap;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@Service
public class Kss_HjbdService {
    @Autowired
    private HjbdDao hjbdDao;

    /**
     * 查询环节变动数量
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> queryHjbdList(String starttime, String endtime){
        System.out.println("*******************"+starttime+"***"+endtime+"********************");
        return hjbdDao.queryHjbdNum(starttime, endtime);
    }



    /**
     * 环节变动业务动态
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> hjbdYwdt( String startTime, String endTime,String jsbh) {
        return     hjbdDao.hjbdYwdt(startTime, endTime, jsbh);
    }
    /**
     * 在押人员诉讼情况
     */
    @UseDataSource("kss_ds")
    public Map<String,Object> select_ssqk( String rybh) {
        Map map1 =new HashMap();
        Map map2=new HashMap();
        Map map3=new HashMap();
        List list=new ArrayList();
        List list1=new ArrayList();
        Map<String,Object> map4=new HashMap();
        List<Map<String,Object>> listmap = hjbdDao.zyryss(rybh);
        for(int i=0;i<listmap.size();i++){
            Map<String,Object> map=new HashMap<>();
            String bahj = listmap.get(i).get("办案环节").toString();
            if (bahj.equals("11")){
                 map.put("name","刑事拘留");
            }
            if (bahj.equals("12")){
                map.put("name","逮捕");
            }
            if (bahj.equals("13")||bahj.equals("14")){
                map.put("name","公安补充侦查");
            }
            if (bahj.equals("24")){
                map.put("name","检查补充侦查");
            }
            if (bahj.equals("31")){
                map.put("name","一审");
            }
            if (bahj.equals("32")){
                map.put("name","二审");
            }
            if (bahj.equals("21")||bahj.equals("22")||bahj.equals("23")){
                map.put("name","审查起诉");
            }
            if (bahj.equals("33")){
                map.put("name","发起重审");
            }
            if (bahj.equals("34")){
                map.put("name","死刑复核");
            }
            if (bahj.equals("35")){
                map.put("name","待执行");
            }
            if (bahj.equals("36")){
                map.put("name","已决");
            }
            String basj = listmap.get(i).get("办案时间").toString();
            map.put("time",basj);
            map.put("dit","");
            list.add(map);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("name","");
        Map<String,Object> map5=new HashedMap();
        map5.put("name","日期");
        Map map6=new HashedMap();
        map6.put("name","");
        Map map7=new HashedMap();
        map7.put("name","进展情况");
        list1.add(map);
        list1.add(map5);
        list1.add(map6);
        list1.add(map7);
        map1.put("head",list1);
        map1.put("body",list);
        map1.put("name","诉讼情况");
        map2.put("rb",map1);
        map3.put("manage",map2);
        map4.put("code",200);
        map4.put("msg","");
        map4.put("data",map3);
        return  map4;
    }
}
