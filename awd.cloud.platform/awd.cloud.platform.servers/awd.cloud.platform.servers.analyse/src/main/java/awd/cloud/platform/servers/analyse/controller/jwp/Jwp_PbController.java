package awd.cloud.platform.servers.analyse.controller.jwp;


import awd.cloud.platform.servers.analyse.model.jwp.JsModel;
import awd.cloud.platform.servers.analyse.service.jwp.Jwp_PbService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
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
@RequestMapping("jwp/pb")
@Api(tags = "jwp_pb",description = "屏保信息查询")
public class Jwp_PbController {

    @Autowired
    private Jwp_PbService Jwp_pbService;

    @GetMapping("zyCount")
    @ApiOperation("在押人员数量")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> zycount (@RequestParam(value = "jsbh", required = false )String jsbh,
                                                        @RequestParam(value = "jsh", required = false )String jsh
                                                       ){

        try {
            Map<String, Object> list = Jwp_pbService.zyCount(jsbh,jsh);
            if("0".equals(list.get("zgmj"))) {
            	list.put("zgmj", "无");
            }
            if("0".equals(list.get("xgmj"))) {
            	list.put("xgmj", "无");
            }
            return ResponseMessage.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }

   /* @GetMapping("wxdjCount")
    @ApiOperation("分类危险等级人员数量")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> wxdjCount (@RequestParam(value = "wxdj", required = false )String wxdj){
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            List<Map<String, Object>> list = Jwp_pbService.wxdjCount(wxdj);
            result.put("wxdjCount", list);
            return ResponseMessage.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }


    @GetMapping("bhlxCount")
    @ApiOperation("重病号人员数量")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> bhlxCount (@RequestParam(value = "bhlx", required = false )String bhlx){
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            List<Map<String, Object>> list = Jwp_pbService.bhlxCount(bhlx);
            result.put("bhlxCount", list);
            return ResponseMessage.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }


    @GetMapping("jjCount")
    @ApiOperation("戒具使用数量")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> jjCount (@RequestParam(value = "jj", required = false )String jj){
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            List<Map<String, Object>> list = Jwp_pbService.jjCount(jj);
            result.put("jjCount", list);
            return ResponseMessage.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }

    @GetMapping("yjCount")
    @ApiOperation("眼镜使用数量")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> yjCount (@RequestParam(value = "jsbh", required = false )String jsbh){
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            List<Map<String, Object>> list = Jwp_pbService.yjCount(jsbh);
            result.put("yjCount", list);
            return ResponseMessage.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }

    @GetMapping("ybCount")
    @ApiOperation("用笔数量")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> ybCount (@RequestParam(value = "jsbh", required = false )String jsbh){
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            List<Map<String, Object>> list = Jwp_pbService.ybCount(jsbh);
            result.put("ybCount", list);
            return ResponseMessage.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }

    @GetMapping("mjxxCount")
    @ApiOperation("民警信息")
    @OpenAPI
    public ResponseMessage<Map<String,Object>> mjxx (@RequestParam(value = "jsbh", required = false )String jsbh,
                                                          @RequestParam(value = "jsh", required = false )String jsh){
        String zpurl ="http://192.168.4.50:8888/storagegroup/M00/00/05/wKgEMl1RJdmAGvRZAAOpFi24yJQ260.jpg";
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            JsModel jsModel = new JsModel("000000001",jsbh,jsh,"测试001","测试002",zpurl);
            System.out.println("jsbh-----------------------------"+jsbh);
            System.out.println("jsh-----------------------------"+jsh);
            System.err.println("list----------------"+jsModel);
            result.put("jsModel",jsModel);
            return ResponseMessage.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }
*/

}
