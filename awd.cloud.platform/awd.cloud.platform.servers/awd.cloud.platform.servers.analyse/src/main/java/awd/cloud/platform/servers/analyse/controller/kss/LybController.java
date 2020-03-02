package awd.cloud.platform.servers.analyse.controller.kss;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/lyb")
public class LybController {


    @RequestMapping(value = "/getGztx/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("岗位工作提示")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> messageNotice(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh,
            @ApiParam(name = "gwlx", value = "岗位类型", required = true)@RequestParam(value="gwlx",required = false)String gwlx){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }


}
