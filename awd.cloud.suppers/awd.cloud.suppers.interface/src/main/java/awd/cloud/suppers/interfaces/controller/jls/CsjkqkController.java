package awd.cloud.suppers.interfaces.controller.jls;

import awd.cloud.suppers.interfaces.service.jls.CsjkqkService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/jls/csjkqk")
@AccessLogger("jls")
@Api(value = "jls")
public class CsjkqkController {

    @Autowired
    private CsjkqkService csjkqkService;

    @OpenAPI
    @ApiOperation(value="自定义查询")
    @RequestMapping(value = "/getCsjkqk",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseMessage<PagerResult<Map<String, Object>>> getCsjkqk() {

        return csjkqkService.getCsjkqk();
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveCsjkqk",method = {RequestMethod.POST})
    public ResponseMessage<String> saveCsjkqk() {

        Map<String,Object> map = Maps.newHashMap();
        map.put("id","20191118");

        return csjkqkService.saveCsjkqk(map);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateCsjkqkById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateCsjkqkById() {

        String id = "20191118";
        Map<String,Object> map = Maps.newHashMap();
        map.put("jsbh","110000121");

        return csjkqkService.updateCsjkqkById(id,map);
    }
}
