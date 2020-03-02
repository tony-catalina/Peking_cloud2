package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_lscsService;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
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

/**
 * Author：YaoBen
 * Date：2020-02-07 14:05
 * Description：<描述>
 */
@RestController
@RequestMapping("/jls/lscs")
public class Jls_lscsController {

    @Autowired
    private Jls_lscsService jls_lscsService;

    @GetMapping("/jls_swj_lslsfxCount")
    @ApiOperation("临时出所=上位机版本")
    @OpenAPI
    public ResponseMessage<?> jls_swj_lslsfxCount(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime){
        try {
            List<AnalysisJlsResultVO> maps = jls_lscsService.swj_lslsfxCount(starttime, endtime);
            return ResponseMessage.ok(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }


    @GetMapping("/lscsYwdt")
    @ApiOperation("拘留所处理出所业务动态")
    @OpenAPI
    public ResponseMessage<?> lscsYwdt(@RequestParam(value = "jsbh",required =false )String jsbh){

        try {

            List<Map<String,Object>> zrcsList= jls_lscsService.lscsZrcs(jsbh);
            List<Map<String,Object>>  zrcswgList=jls_lscsService.lscsZrcswg(jsbh);
            List<Map<String,Object>> jrcsList=jls_lscsService.lscsJrcs(jsbh);
            List<Map<String,Object>> jrcswgList=jls_lscsService.lscsJrcswg(jsbh);
            List<Map<String,Object>> bzcsList=jls_lscsService.lscsBzcs(jsbh);
            List<Map<String,Object>> bzcswgList=jls_lscsService.lscsBzcswg(jsbh);
            List<Map<String,Object>> bycsList=jls_lscsService.lscsBycs(jsbh);
            List<Map<String,Object>> bycswgList=jls_lscsService.lscsBycswg(jsbh);

            Map<String,Object> map = new HashMap();
            map.put("zrcs",zrcsList);
            map.put("zrcswg",zrcswgList);
            map.put("jrcs",jrcsList);
            map.put("jrcswg",jrcswgList);
            map.put("bzcs",bzcsList);
            map.put("bzcswg",bzcswgList);
            map.put("bycs",bycsList);
            map.put("bycswg",bycswgList);
            return ResponseMessage.ok(map);
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }


}
