package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_JyService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/jy")
public class JyController {

	@Autowired
	private Kss_JyService jyService;
	
    @RequestMapping(value = "/getJycount/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("近五个月就医概况")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> getJycount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/getCjbCount/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("七个常见疾病，四个监区的情况")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> getCjbCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh,
            @ApiParam(name = "cjb", value = "逗号分隔的常见病", required = true)@PathVariable("cjb") String cjb){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }


    @RequestMapping(value = "/getJqJyCount/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("近八个月各监区报病情况")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> getJqJyCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh,
            @ApiParam(name = "jqhs", value = "逗号分隔监区", required = true)@PathVariable("jqhs") String jqhs){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }


    @RequestMapping(value = "/getZdbhInfo/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("重大三级风险人员对象")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> getZdbhInfo(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }
    
    
    //医疗情况分析
    @GetMapping("/ylwsCount")
    @ApiOperation("医疗卫生分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ylwsCount(){
    	//定义地区  与前台顺序对应
    	int[] jsbh = new int[] {1100001,110101111,110102111,110105111,110106111,110107111,110108111,110109111,11011111,110112111,110113111,110221111,110224111,110226111,110227111,110228111,110229111};
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    	//所外就医人数
    	List<Map<String, Object>> swjylist = jyService.swjyCount();
    	int[] swjy = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    	for (Map<String, Object> maps : swjylist) {
    		for(int i =0;i<jsbh.length;i++) {
    			if(maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
    				swjy[i]=swjy[i] + Integer.parseInt(maps.get("sl").toString());
    			}
    		}
    	}
    	//所内就医总数
    	List<Map<String, Object>> snjylist = jyService.snjyCount();
    	int[] snjy = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    	for (Map<String, Object> maps : snjylist) {
    		for(int i =0;i<jsbh.length;i++) {
    			if(maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
    				snjy[i]=snjy[i] +Integer.parseInt(maps.get("sl").toString());
    			}
    		}
    	}
    	//就医总数
    	int[] jys = new int[17];
    	for(int i =0;i<jsbh.length;i++) {
    		System.err.println(i);
    		jys[i] = snjy[i]+swjy[i];
    		System.err.println(jys[i]);
    	}
    	map.put("jys", jys);
    	map.put("swjy", swjy);
    	map.put("snjy", snjy);
    	return ResponseMessage.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
    }


}
