package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_WgsjclService;
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
@RequestMapping("kss/wgsjcl")
public class WgsjclController {
    @Autowired
    private Kss_WgsjclService kss_WgsjclService;

    @GetMapping("/wgqkfxCount")
    @ApiOperation("违规事件分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> wgqkfxCount(){
    	//定义地区  与前台顺序对应
    	int[] jsbh = new int[] {1100001,110101111,110102111,110105111,110106111,110107111,110108111,110109111,11011111,110112111,110113111,110221111,110224111,110226111,110227111,110228111,110229111};
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    	List<Map<String, Object>> yzjbfxCountlist = kss_WgsjclService.wgqkfxCount();
    	int[] jqwg = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    	int[] jswg = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    	int[] rywg = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    	for (Map<String, Object> maps : yzjbfxCountlist) {
    		for(int i =0;i<jsbh.length;i++) {
    			if(maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
    				jqwg[i]=jqwg[i]+Integer.parseInt(maps.get("jqwg").toString());
    			}
    			if(maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
    				jswg[i]=jswg[i]+Integer.parseInt(maps.get("jswg").toString());
    			}
    			if(maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
    				rywg[i]=rywg[i]+Integer.parseInt(maps.get("rywg").toString());
    			}
    		}
    	}
    	map.put("rywg", rywg);
    	map.put("jswg", jswg);
    	map.put("jqwg", jqwg);
    	return ResponseMessage.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
    }
	@GetMapping("/bzwgrs")
	@ApiOperation("本周违规人数")
	@OpenAPI
	public ResponseMessage<Number []> getBzwgrs(String jsbh,String jsh){
    	return ResponseMessage.ok(kss_WgsjclService.getBzwgrs(jsbh,jsh));
	}
	@GetMapping("/bzjsYclwgsj")
	@ApiOperation("本周监室已处理违规事件数量")
	@OpenAPI
	public ResponseMessage<Number []> getBzjsYclwgsj(String jsbh,String jsh){
    	String cllx="1";
    	return ResponseMessage.ok(kss_WgsjclService.getBzjswgsj(jsbh,jsh,cllx));
	}
	@GetMapping("/bzjsWclwgsj")
	@ApiOperation("本周监室未处理违规事件数量")
	@OpenAPI
	public ResponseMessage<Number []> getBzjsWclwgsj(String jsbh,String jsh){
		String cllx="0";
		return ResponseMessage.ok(kss_WgsjclService.getBzjswgsj(jsbh,jsh,cllx));
	}
}
