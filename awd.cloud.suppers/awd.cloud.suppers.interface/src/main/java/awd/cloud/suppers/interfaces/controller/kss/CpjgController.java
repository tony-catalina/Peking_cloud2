package awd.cloud.suppers.interfaces.controller.kss;

import awd.cloud.suppers.interfaces.api.KssBaseServersApi;
import awd.cloud.suppers.interfaces.service.kss.CpjgService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/kss/cpjg")
@AccessLogger("kss")
@Api(value = "kss",description = "测评结果")
public class CpjgController {

    @Autowired
    private CpjgService cpjgService;

    @OpenAPI
    @ApiOperation(value = "测评结果查询")
    @RequestMapping(value = "/getCpjg", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<PagerResult<Map<String, Object>>> getCpjg(@RequestParam String json) {

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");//条件参数

        return cpjgService.getCpjg(param);
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveCpjg",method = {RequestMethod.POST})
    public ResponseMessage<String> saveCpjg(@RequestParam String json){

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");

        return cpjgService.saveCpjg(param);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateCpjgById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateCpjgById(@RequestParam String json){

        Map<String,Object> map = JSONUtil.toMap(json,Object.class);

        Map<String,Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode",map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh",map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String,Object> param = (Map)map.get("param");
        String id = (String)map.get("id");

        return cpjgService.updateCpjgById(id,param);
    }
}
