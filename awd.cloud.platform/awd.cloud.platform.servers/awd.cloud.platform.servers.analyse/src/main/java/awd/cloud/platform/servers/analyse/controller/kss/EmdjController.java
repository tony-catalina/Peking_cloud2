package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_EmdjService;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by wuyu on 2019/7/4 14:47
 **/
@RestController
@RequestMapping("/kss/emdj")
public class EmdjController {
    @Autowired
    private Kss_EmdjService kss_emdjService;
    @GetMapping("/rqcxE")
    @ApiOperation("耳目情况")
    @OpenAPI
    public Map<String, Object> emdj_rq(@RequestParam(value="starttime",required = false) String starttime,
                                       @RequestParam(value="endtime",required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<Map<String, Object>> list= kss_emdjService.emdj_rqcxE(starttime,endtime);
        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }

    @GetMapping("/swj_rqcxE")
    @ApiOperation("上位机_耳目情况")
    @OpenAPI
    public Map<String, Object> swj_emdj_rq(@RequestParam(value="starttime",required = false) String starttime,
                                       @RequestParam(value="endtime",required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<AnalysisResultVO> list= kss_emdjService.swj_emdj_rqcxE(starttime,endtime);
        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }

    @GetMapping("/emYwdt")
    @ApiOperation("耳目业务动态")
    @OpenAPI
    public ResponseMessage<?> emYwdt (String jqh, String jsbh){
        try {
            List<Map<String,Object>> emzs=kss_emdjService.emZs(jqh,jsbh);
            List<Map<String,Object>> emTj=kss_emdjService.emTj(jqh, jsbh);
            List<Map<String,Object>> queryJq=kss_emdjService.queryJq(jsbh);
            List<Map<String,Object>> emYbjjs=kss_emdjService.emYbjjs(jsbh);
           List<Map<String,Object>> emWbjjs=kss_emdjService.emWbjjs(jsbh);
            Map<String,Object> map=new HashMap<>();
            map.put("emzs",emzs);
            map.put("emTj",emTj);
            map.put("queryJq",queryJq);
            map.put("emYbjjs",emYbjjs);
        map.put("emWbjjs",emWbjjs);
            return  ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }


    @GetMapping("/queryJq")
    @ApiOperation("查询监区号监区名称")
    @OpenAPI
    public  ResponseMessage<?> queryJq(String jsbh){
        try {
            List<Map<String,Object>> queryJq=kss_emdjService.queryJq(jsbh);
            Map<String,Object> map=new HashMap<>();
            map.put("queryJq",queryJq);
            return ResponseMessage.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }

    }
}


