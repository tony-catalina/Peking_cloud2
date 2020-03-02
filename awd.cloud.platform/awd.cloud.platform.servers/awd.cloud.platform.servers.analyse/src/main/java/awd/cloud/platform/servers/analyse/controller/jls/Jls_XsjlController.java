package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_XsjlService;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/jls_xsjl")
@RefreshScope
@Api(tags = "jls_xsjl",description = "巡视记录记录")
public class Jls_XsjlController {
    @Autowired
    private Jls_XsjlService jls_XsjlService;


    @GetMapping("/jlsdpjqwgqst")
    @ApiOperation("监区违规趋势图")
    @OpenAPI
    public Map<String, Object> jlsdpjqwgqst( @RequestParam(value = "jsbh", required = false) String jsbh) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> list = jls_XsjlService.jlsdpjqwgqst(jsbh);
    	map.put("code", 200);
    	map.put("msg", "查询成功");
    	map.put("data", list);
		return map;
    }
    
    @GetMapping("/jlsdpjqwgry")
    @ApiOperation("违规程度")
    @OpenAPI
    public Map<String, Object> dpjqwgry( @RequestParam(value = "jsbh", required = false) String jsbh) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Map<String, Object>> list = jls_XsjlService.dpjqwgry(jsbh);
    	map.put("code", 200);
    	map.put("msg", "查询成功");
    	map.put("data", list);
		return map;
    }

    @GetMapping("/xsjlNum")
    @ApiOperation("违规情况")
    @OpenAPI
    public Map<String, Object> getTsdjnum(@RequestParam(value="starttime", required = false) String starttime,
                                          @RequestParam(value="endtime", required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<AnalysisJlsResultVO> list= jls_XsjlService.queryXsjlList(starttime, endtime);
        if(list==null || list.size() == 0) {
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
}