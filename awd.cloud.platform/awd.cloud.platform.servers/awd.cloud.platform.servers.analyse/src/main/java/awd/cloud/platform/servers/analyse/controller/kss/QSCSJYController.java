package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_CSJYPMService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_QSCSJYService;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-13 9:38
 * Description：<描述>
 */
@Controller
@Api(description = "大屏全市出所就医、出所就医排名")
@RequestMapping("/kss/qscsjy")
public class QSCSJYController {

    @Autowired
    private Kss_QSCSJYService service; //全市出所就医
    @Autowired
    private Kss_CSJYPMService kss_csjypmService; //出所就医排名


    /*全市出所就医 swjy 所外就医  1是  0否,csjylx出所就医类型 1门诊  2住院,  jzyy就诊医院*/
    @ResponseBody
    @GetMapping("/select_QSCSJY")
    @OpenAPI
    public HashMap<String, Object> select_QSCSJY(@RequestParam(required = false, value = "jsbh") String jsbh) {


        try {
            ArrayList<Object> qscsjy = service.find_QSCSJY();//公安医院


            //String strify = request.getParameter("strify");
            List<String> list = JSON.parseArray(jsbh, String.class);

            ArrayList<Object> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String jsbh1 = list.get(i);

                if (!jsbh1.equals("110000121") && !jsbh1.equals("110101121") && !jsbh1.equals("110105121") && !jsbh1.equals("110111121")&& !jsbh1.equals("110108121")) {
                    Map<String, Object> csjypm = kss_csjypmService.find_CSJYPM(jsbh1);//就医排名
                    HashMap<String, Object> map = new HashMap<>();
                    String jsbhString = CacheUtils.get().getKssmcByKss(jsbh1);
                    map.put("name", jsbhString);
                    map.put("value", csjypm.get("csjy"));

                    list1.add(map);

                } else if (jsbh1.equals("110000121") || jsbh1.equals("110101121") || jsbh1.equals("110105121") || jsbh1.equals("110111121") || jsbh1.equals("110108121")) {
                    Map<String, Object> jls_csjypm = kss_csjypmService.find_jls_CSJYPM(jsbh1);
                    HashMap<String, Object> map = new HashMap<>();
                    if (jsbh1.equals("110000121")) {
                        map.put("name", "北京市拘留所");
                        map.put("value", jls_csjypm.get("csjy"));
                    } else if (jsbh1.equals("110101121")) {
                        map.put("name", "东城区拘留所");
                        map.put("value", jls_csjypm.get("csjy"));
                    } else if (jsbh1.equals("110105121")) {
                        map.put("name", "朝阳区拘留所");
                        map.put("value", jls_csjypm.get("csjy"));
                    } else if (jsbh1.equals("110111121")) {
                        map.put("name", "房山区拘留所");
                        map.put("value", jls_csjypm.get("csjy"));
                    }else if (jsbh1.equals("110108121")) {
                        map.put("name", "海定区拘留所");
                        map.put("value", jls_csjypm.get("csjy"));
                    }

                    list1.add(map);
                }

            }
            HashMap<String, Object> hashMap = new HashMap<>();

            hashMap.put("l", qscsjy);
            hashMap.put("r", list1);

            HashMap<String, Object> reMap = new HashMap<>();
            reMap.put("code", 200);
            reMap.put("data", 54);
            reMap.put("msg", "成功");
            reMap.put("result", true);
            reMap.put("totalSize", null);
            reMap.put("kss", hashMap);
            return reMap;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("QSCSJY", "全市出所就医");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;

        }

    }
}