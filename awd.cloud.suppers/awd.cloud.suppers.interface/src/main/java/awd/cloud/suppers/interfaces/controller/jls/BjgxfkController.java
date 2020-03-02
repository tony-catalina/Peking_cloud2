package awd.cloud.suppers.interfaces.controller.jls;

import awd.cloud.suppers.interfaces.service.jls.BjgxfkService;
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
@RequestMapping("/jls/bjgxfk")
@AccessLogger("jls")
@Api(value = "jls")
public class BjgxfkController {

    @Autowired
    private BjgxfkService bjgxfkService;

    @OpenAPI
    @ApiOperation(value="自定义查询")
    @RequestMapping(value = "/getBjgxfk",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseMessage<PagerResult<Map<String, Object>>> getBjgxfk() {

        return bjgxfkService.getBjgxfk();
    }

    @OpenAPI
    @ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
    @RequestMapping(value = "/saveBjgxfk",method = {RequestMethod.POST})
    public ResponseMessage<String> saveBjgxfk() {

        Map<String,Object> map = Maps.newHashMap();

        return bjgxfkService.saveBjgxfk(map);
    }

    @OpenAPI
    @ApiOperation(value="根据id更新")
    @RequestMapping(value = "/updateBjgxfkById",method = {RequestMethod.POST})
    public ResponseMessage<String> updateBjgxfkById() {

        String id = "";
        Map<String,Object> map = Maps.newHashMap();

        return bjgxfkService.updateBjgxfkById(id,map);
    }
}
