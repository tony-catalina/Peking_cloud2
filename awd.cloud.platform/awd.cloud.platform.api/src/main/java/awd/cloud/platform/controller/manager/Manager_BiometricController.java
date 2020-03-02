package awd.cloud.platform.controller.manager;

import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.manager.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * @author WS
 * @Description: 生物特征
 * @date 2020/1/3 上午11:59
 */
@RestController
@RefreshScope
@RequestMapping("/v4/manager/biometric")
@Api(tags = "manager-biometric", description = "生物特征查询采集")
public class Manager_BiometricController {

    @Autowired
    private ManagerService managerService;

    @OpenAPI
    @PostMapping("/mfaceCJ")
    @ApiOperation("民警人脸采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"), @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("mfaceCJ")
    @HystrixCommand
    public ResponseMessage<String> mfaceCJ(HttpServletRequest request,
                                           @RequestParam(name = "appcode") String appcode,
                                           @RequestParam("jsbh") String jsbh,
                                           @RequestParam("zjh") String zjh,
                                           @RequestParam("jh") String jh,
                                           @RequestParam("ip") String ip) {
        Manager_MfaceModel mfaceModel = new Manager_MfaceModel();
        mfaceModel.setJsbh(jsbh);
        mfaceModel.setJh(jh);
        mfaceModel.setZjh(zjh);
        mfaceModel.setCreator(appcode);
        mfaceModel.setCreatetime(Calendar.getInstance().getTime());
        mfaceModel.setState("R2");
        ResponseMessage<String> res = managerService.mfaceCJ(mfaceModel, ip);
        return res;
    }

    @OpenAPI
    @PostMapping("/mIrisCJ")
    @ApiOperation("民警虹膜采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"), @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("mIrisCJ")
    @HystrixCommand
    public ResponseMessage<String> mIrisCJ(HttpServletRequest request,
                                           @RequestParam(name = "appcode") String appcode,
                                           @RequestParam("jsbh") String jsbh,
                                           @RequestParam("zjh") String zjh,
                                           @RequestParam("jh") String jh,
                                           @RequestParam("ip") String ip) {
        Manager_MirisModel mirisModel = new Manager_MirisModel();
        mirisModel.setJsbh(jsbh);
        mirisModel.setJh(jh);
        mirisModel.setZjh(zjh);
        mirisModel.setCreator(appcode);
        mirisModel.setCreatetime(Calendar.getInstance().getTime());
        mirisModel.setState("R2");
        ResponseMessage<String> res = managerService.mIrisCJ(mirisModel, ip);
        return res;
    }

    @OpenAPI
    @PostMapping("/mfingerCJ")
    @ApiOperation("民警指纹采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"), @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("mfingerCJ")
    @HystrixCommand
    public ResponseMessage<String> mfingerCJ(HttpServletRequest request,
                                             @RequestParam(name = "appcode") String appcode,
                                             @RequestParam("jsbh") String jsbh,
                                             @RequestParam("zjh") String zjh,
                                             @RequestParam("jh") String jh,
                                             @RequestParam("ip") String ip) {
        Manager_MfingerModel mfingerModel = new Manager_MfingerModel();
        mfingerModel.setJsbh(jsbh);
        mfingerModel.setJh(jh);
        mfingerModel.setZjh(zjh);
        mfingerModel.setCreator(appcode);
        mfingerModel.setCreatetime(Calendar.getInstance().getTime());
        mfingerModel.setState("R2");
        ResponseMessage<String> res = managerService.mfingerCJ(mfingerModel, ip);
        return res;
    }

    @OpenAPI
    @PostMapping("/zfaceCJ")
    @ApiOperation("在押人员人脸采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"), @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("zfaceCJ")
    @HystrixCommand
    public ResponseMessage<String> zfaceCJ(HttpServletRequest request,
                                           @RequestParam(name = "appcode") String appcode,
                                           @RequestParam(name = "jsbh") String jsbh,
                                           @RequestParam("ip") String ip,
                                           @RequestParam("rybh") String rybh) {
        Manager_ZfaceModel zfaceModel = new Manager_ZfaceModel();
        zfaceModel.setJsbh(jsbh);
        zfaceModel.setRybh(rybh);
        zfaceModel.setState("R2");
        zfaceModel.setCreator(appcode);
        zfaceModel.setCreatetime(Calendar.getInstance().getTime());
        ResponseMessage<String> res = managerService.zfaceCJ(zfaceModel, ip);
        return res;
    }

    @OpenAPI
    @PostMapping("/zIrisCJ")
    @ApiOperation("在押人员虹膜采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("zIrisCJ")
    public ResponseMessage<String> zIrisCJ(HttpServletRequest request,
                                           @RequestParam(name = "appcode") String appcode,
                                           @RequestParam(name = "jsbh") String jsbh,
                                           @RequestParam("ip") String ip,
                                           @RequestParam("rybh") String rybh) {
        Manager_ZirisModel zirisModel = new Manager_ZirisModel();
        zirisModel.setJsbh(jsbh);
        zirisModel.setRybh(rybh);
        zirisModel.setState("R2");
        zirisModel.setCreator(appcode);
        zirisModel.setCreatetime(Calendar.getInstance().getTime());
        ResponseMessage<String> res = managerService.zIrisCJ(zirisModel, ip);
        return res;
    }

    @OpenAPI
    @PostMapping("/zfingerCJ")
    @ApiOperation("在押人员指纹采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"), @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("zfingerCJ")
    @HystrixCommand
    public ResponseMessage<String> zfingerCJ(HttpServletRequest request,
                                             @RequestParam(name = "appcode") String appcode,
                                             @RequestParam(name = "jsbh") String jsbh,
                                             @RequestParam("ip") String ip,
                                             @RequestParam("rybh") String rybh) {
        Manager_ZfingerModel zfingerModel = new Manager_ZfingerModel();
        zfingerModel.setJsbh(jsbh);
        zfingerModel.setRybh(rybh);
        zfingerModel.setState("R2");
        zfingerModel.setCreator(appcode);
        zfingerModel.setCreatetime(Calendar.getInstance().getTime());
        ResponseMessage<String> res = managerService.zfingerCJ(zfingerModel, ip);
        return res;
    }
}
