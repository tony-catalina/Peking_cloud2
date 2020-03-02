package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_YqService;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
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
 * Create by wuyu on 2019/7/4 17:03
 **/
@RestController
@RequestMapping("/kss/yq")
public class YqController {
    @Autowired
    private Kss_YqService kss_yqService;
    @GetMapping("/rqcxY")
    @ApiOperation("延期情况")
    @OpenAPI
    public Map<String, Object> yq_rq(@RequestParam(value="starttime",required = false) String starttime,
                                       @RequestParam(value="endtime",required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<AnalysisResultVO> list= kss_yqService.yq_rqcxY(starttime,endtime);
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


    @GetMapping("/yqYwdt")
    @ApiOperation("延期业务动态")
    @OpenAPI
    public ResponseMessage<?> yqYwdt(String starttime, String endtime,String jsbh){
        try {
            List<Map<String,Object>>   yqYwdtList=kss_yqService.yqYwdt(starttime, endtime, jsbh);
            Map<String,Object> map=new HashMap<>();
            map.put("yqYwdt",yqYwdtList);
            System.err.println("yqYwdt++++"+ JSON.toJSONString(yqYwdtList));
            return ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return  ResponseMessage.error("失败");
        }
    }
}

