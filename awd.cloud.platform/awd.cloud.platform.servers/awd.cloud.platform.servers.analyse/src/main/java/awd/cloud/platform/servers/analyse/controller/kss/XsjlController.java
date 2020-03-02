package awd.cloud.platform.servers.analyse.controller.kss;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/xsjl")
public class XsjlController {


    @RequestMapping(value = "/getRywgCount/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("近五个月的违规数量")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> getRywgCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/getJqwgCount/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("四个监区违规违规级别百分比")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> getJqwgCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh ,
            @ApiParam(name = "jqhs", value = "逗号分隔的监区号", required = true)@PathVariable("jqhs") String jqhs ){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }


    @RequestMapping(value = "/getJswgCount/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("监室违规统计")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> getJswgCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh ,
            @ApiParam(name = "jshs", value = "逗号分隔的监室号", required = true)@PathVariable("jshs") String jqhs ){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/getFxrywgInfo/{jsbh}",method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("风险人员违规情况")
    @ApiParam
    public ResponseMessage<List<Map<String,Object>>> getFxrywgInfo(
            @ApiParam(name = "jsbh", value = "监所编号", required = true)@PathVariable("jsbh") String jsbh ){
        List<Map<String,Object>> data=null;
        return ResponseMessage.ok(data);
    }

}
