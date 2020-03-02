package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_PjdjService;
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
@RequestMapping("/kss/pjdj")
public class PjdjController {

    @Autowired
    private Kss_PjdjService kss_pjdjService;

    @GetMapping("/pjdjCx")
    @ApiOperation("处理结果")
    @OpenAPI
    public Map<String , Object>pjdj_rq(@RequestParam(value = "starttime",required = false) String starttime,
                                       @RequestParam(value = "endtime",required = false) String endtime){
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list= kss_pjdjService.pjdj_rqFx(starttime ,endtime );
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
    @GetMapping("/pjdjYwdt")
	@ApiOperation("处理结果业务动态")
	@OpenAPI
	public ResponseMessage<Map<String,Object>> pjdjYwdt(@RequestParam(value="startTime",required = false) String starttime,
			@RequestParam(value="endTime",required = false) String endtime,
			@RequestParam(value = "jsbh",required =false )String jsbh){
		try {

			List<Map<String , Object>> pjdjList=kss_pjdjService.pjdjYwdt(starttime, endtime, jsbh);
			Map<String,Object> map=new HashMap<>();
			map.put("pjdj",pjdjList);
			return ResponseMessage.ok(map);
		}catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("执行失败");
		}
	}

    @GetMapping("/pjdjfx")
    @ApiOperation("上位机判决登记分析")
    @OpenAPI
    public Map<String , Object>pjdjfx(@RequestParam(value = "starttime",required = false) String starttime,
                                       @RequestParam(value = "endtime",required = false) String endtime){
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<AnalysisResultVO> list= kss_pjdjService.pjdjFx(starttime ,endtime );
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
    
}
