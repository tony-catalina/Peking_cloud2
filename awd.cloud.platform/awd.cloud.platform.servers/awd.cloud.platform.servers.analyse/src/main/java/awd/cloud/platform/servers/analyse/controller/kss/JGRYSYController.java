package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.jls.Jls_JGRYSYMonthService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_JGRYSYService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_JGRYSYMonthService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_JGRYSYService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-11-13 13:19
 * Description：<描述>
 */
@Controller
@Api(description = "全市被监管人员收押情况 看守所 日、拘留守所 日、看守所月、拘留所月")
@RequestMapping("/kss/JGRYSYController")
public class JGRYSYController {

    @Autowired
    private Kss_JGRYSYService service;//全市被监管人员收押情况 看守所 日
    @Autowired
    private Jls_JGRYSYService jls_jgrysyService;//全市被监管人员收押情况 拘留守所 日
    @Autowired
    private Kss_JGRYSYMonthService kss_jgrysyMonthService;//全市被监管人员收押情况  看守所月
    @Autowired
    private Jls_JGRYSYMonthService jls_jgrysyMonthService; //全市被监管人员收押情况 拘留所月

    //全市被监管人员收押情况
    @ResponseBody
    @GetMapping("/select_JGRYSY")
    @OpenAPI
    @CrossOrigin
    public HashMap<String,Object> select_JGRYSY(){
        try {
            ArrayList< Object> jgrysy = service.find_JGRYSY();//看守所 日

            HashMap<String, Object> mapk = new HashMap<>();
            mapk.put("name","看守所");
            mapk.put("list",jgrysy);


            ArrayList< Object> jgrysy1 = jls_jgrysyService.find_JGRYSY();//拘留所 日
            System.out.println("jgrysy1=="+jgrysy1.toString());
            HashMap<String, Object> mapj = new HashMap<>();
            mapj.put("name","拘留所");
            mapj.put("list",jgrysy1);

            ArrayList<Object> list = new ArrayList<>();
            list.add(mapk);
            list.add(mapj);


            HashMap<String, Object> map = new HashMap<>();
            map.put("name","日");
            map.put("list",list);


            //看守所月
            ArrayList<Object> jgrysyMonth = kss_jgrysyMonthService.find_JGRYSYMonth();
            HashMap<String, Object> mapkM = new HashMap<>();
            mapkM.put("name","看守所");
            mapkM.put("list",jgrysyMonth);

            //拘留所月
            ArrayList< Object> jgrysy2= jls_jgrysyMonthService.find_JGRYSY();
            HashMap<String, Object> mapJM = new HashMap<>();
            mapJM.put("name","拘留所");
            mapJM.put("list",jgrysy2);

            ArrayList<Object> listM = new ArrayList<>();
            listM.add(mapkM);
            listM.add(mapJM);


            HashMap<String, Object> mapM = new HashMap<>();
            mapM.put("name","月");
            mapM.put("list",listM);


            ArrayList<Object> list1 = new ArrayList<>();
            list1.add(map);
            list1.add(mapM);

            HashMap<String,Object> reMap = new HashMap<>();
            reMap.put("code",200);
            reMap.put("data",54);
            reMap.put("msg","成功");
            reMap.put("result",true);
            reMap.put("totalSize",null);
            reMap.put("day",list1);


            return reMap;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("JGRYSY", "全市被监管人员收押情况");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;

        }


    }
}
