package awd.cloud.suppers.interfaces.controller.kss;

import awd.cloud.suppers.interfaces.model.TxdjModel;
import awd.cloud.suppers.interfaces.service.kss.TxjlService;
import awd.cloud.suppers.interfaces.utils.MapUtil;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/kss/txjl")
@AccessLogger("kss")
@Api(value = "kss",description = "提讯记录")
public class TxjlController {

    @Autowired
    private TxjlService txjlService;

    @OpenAPI
    @ApiOperation(value = "提讯记录查询")
    @RequestMapping(value = "/getTxdj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseMessage<PagerResult<Map<String, Object>>> getTxdj(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return txjlService.getTxjl(param);
    }

    @OpenAPI
    @ApiOperation(value = "保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveTxdj", method = {RequestMethod.POST})
    public ResponseMessage<String> saveTxdj(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数

        return txjlService.saveTxjl(param);
    }

    @OpenAPI
    @ApiOperation(value = "根据id更新")
    @RequestMapping(value = "/updateTxdjById", method = {RequestMethod.POST})
    public ResponseMessage<String> updateTxdjById(@RequestParam String json) {

        Map<String, Object> map = JSONUtil.toMap(json, Object.class);

        Map<String, Object> appcodeAndJsbhMap = Maps.newHashMap();
        appcodeAndJsbhMap.put("appcode", map.get("appcode"));//appcode
        appcodeAndJsbhMap.put("jsbh", map.get("jsbh"));//监所编号
        String appcodeAndJsbh = JSON.toJSONString(appcodeAndJsbhMap);//appcode和监所编号Json字符串

        Map<String, Object> param = (Map) map.get("param");//条件参数
        String id = (String)map.get("id");

        return txjlService.updateTxjlById(id,param);
    }
}
