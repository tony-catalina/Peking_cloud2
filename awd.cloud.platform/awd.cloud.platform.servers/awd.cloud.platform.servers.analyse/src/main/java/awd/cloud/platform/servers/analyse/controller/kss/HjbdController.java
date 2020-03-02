package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_HjbdService;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/hjbd")
public class HjbdController {
    @Autowired
    private Kss_HjbdService kss_hjbdService;

    @GetMapping("/hjbdnum")
    @ApiOperation("环节变动")
    @OpenAPI
    public Map<String, Object> getHjbdnum(@RequestParam(value="starttime", required = false) String starttime,
                                         @RequestParam(value="endtime", required = false) String endtime) {
        Map<String, Object> result = new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<AnalysisResultVO> list = kss_hjbdService.queryHjbdList(starttime, endtime);
        if (list == null ||list.size() == 0) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        } else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }
        return result;
    }



    @GetMapping("/hjbdYwdt")
    @ApiOperation("环节变动业务动态")
    @OpenAPI
    public ResponseMessage<?> hjbdYwdt (String startTime, String endTime, String jsbh){
        try {
            List<Map<String,Object>> hjbdYwdtList=kss_hjbdService.hjbdYwdt(startTime, endTime, jsbh) ;
            Map<String,Object> map=new HashMap<>();
            map.put("hjbdYwdt",hjbdYwdtList);
            return ResponseMessage.ok(map);
        }  catch (Exception e)  {
               e.printStackTrace();
               return ResponseMessage.error("失败");
        }

    }
    @GetMapping("/select_ssqk")
    @ApiOperation("在押人员诉讼情况")
    @OpenAPI
    public Map<String,Object> select_ssqk (@RequestParam(value="rybh", required = false) String rybh) {
        try {
            return kss_hjbdService.select_ssqk(rybh);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
