/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.manager;

import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.manager.Manager_ZfingerModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
@RequestMapping("/zfinger")
@AccessLogger("Zfinger")
@Api(value = "Zfinger", description = "在押人员指纹信息")
public class Manager_ZfingerController {
    @Autowired
    private ManagerService managerService;

    @OpenAPI
    @ApiOperation("分页查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<Manager_ZfingerModel>> zfinger_query(HttpServletRequest request,
                                                                          @RequestParam(name = "appcode") String appcode,
                                                                          @RequestParam(name = "jsbh") String jsbh,
                                                                          @RequestParam(name = "rybh") String rybh
    ) {
        QueryParam queryParam = new QueryParam();
        queryParam.and("rybh", rybh);
        queryParam.and("jsbh", jsbh);
        return managerService.zfinger_query(queryParam);
    }
}
