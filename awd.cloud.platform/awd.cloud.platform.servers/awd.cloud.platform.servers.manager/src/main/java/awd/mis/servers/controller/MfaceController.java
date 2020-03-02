/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import awd.framework.common.utils.Base64Utils;
import awd.mis.servers.entity.MfaceEntity;
import awd.mis.servers.service.MfaceService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sykean.client.DeviceService;
import com.sykean.client.base.CallBack;
import com.sykean.client.model.BiometricResult;
import com.sykean.client.model.FaceInfo;
import com.sykean.client.model.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/mface")
@AccessLogger("Mface")
@Api(value = "Mface", description = "民警人脸信息")
public class MfaceController implements GenericEntityController<MfaceEntity, String, QueryParamEntity, MfaceEntity> {

    private MfaceService mfaceService;

    @Override
    public MfaceEntity modelToEntity(MfaceEntity model, MfaceEntity entity) {
        return model;
    }

    @Autowired
    public void setMfaceService(MfaceService mfaceService) {
        this.mfaceService = mfaceService;
    }

    @Override
    public CrudService<MfaceEntity, String> getService() {
        return mfaceService;
    }

    @Override
    @OpenAPI
    @ApiOperation("自定义查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<MfaceEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        arg1.excludes("rltx");
        return ResponseMessage.ok(mfaceService.selectPager(arg1));
    }

    @OpenAPI
    @RequestMapping("/caiji")
    public ResponseMessage<String> caiji() {
        CallBack<BiometricResult> callBack = new CallBack<BiometricResult>() {
            @Override
            public void onCallBack(ResponseResult<BiometricResult> responseResult) {
                System.err.println("==================！");
                System.err.println(JSONObject.toJSONString(responseResult));
                System.err.println("==================！");
                CallBack<BiometricResult> ca = new CallBack<BiometricResult>() {
                    @Override
                    public void onCallBack(ResponseResult<BiometricResult> responseResult) {
                    }
                };
                if ("0".equals(responseResult.getResult())) {
                    BiometricResult biometricResult = responseResult.getData();
                    if (biometricResult != null && biometricResult.getFaceInfo() != null) {
                        FaceInfo faceInfo = responseResult.getData().getFaceInfo();
                        MfaceEntity mfaceEntity = new MfaceEntity();
                        mfaceEntity.setCreatetime(Calendar.getInstance().getTime());
                        mfaceEntity.setCreator("");
                        mfaceEntity.setState("R2");
                        mfaceEntity.setJh("000000001");
                        mfaceEntity.setJsbh("110000114");
                        mfaceEntity.setZjh("321323199402123933");
                        mfaceEntity.setCjfs("");
                        Map<String, String> templates = faceInfo.getTemplates();
                        Map<String, String> photos = faceInfo.getPhotos();
                        if (templates.isEmpty() || photos.isEmpty()) {
                            DeviceService.getInstance().sendCollectionResult("14.66.87.167", "-1", "采集失败", ca);
                            return;
                        }
                        mfaceEntity.setCjfs("0");
                        try {
                            mfaceEntity.setRltx(Base64Utils.decode(photos.get("0")));
                            mfaceEntity.setRltz(Base64Utils.decode(templates.get("0")));
                            String i = mfaceService.insert(mfaceEntity);
                            System.err.println(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                            DeviceService.getInstance().sendCollectionResult("14.66.87.167", "-1", "采集失败", ca);
                            return;
                        }

                    } else {
                        DeviceService.getInstance().sendCollectionResult("14.66.87.167", "-1", "采集失败", ca);
                    }
                }
                DeviceService.getInstance().sendCollectionResult("14.66.87.167", "0", "提交成功", ca);
            }
        };
        List<String> li = Lists.newArrayList("2");
        DeviceService.getInstance().collect("14.66.87.167", li, "2", callBack);
        return ResponseMessage.ok("type");
    }

    @Override
    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody MfaceEntity data) {
        return GenericEntityController.super.save(data);
    }

    @Override
    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody MfaceEntity data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

    @Override
    @PatchMapping
    @AccessLogger("{save_or_update}")
    @ApiOperation("保存数据,如果数据不存在则新增一条数据")
    @OpenAPI
    public ResponseMessage<String> saveOrUpdate(@RequestBody MfaceEntity data) {
        return GenericEntityController.super.saveOrUpdate(data);
    }

    @Override
    @GetMapping(path = {"/{id:.+}"})
    @ApiOperation("根据主键查询数据")
    @AccessLogger("{get_by_id}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<MfaceEntity> getByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.getByPrimaryKey(id);
    }

    @Override
    @DeleteMapping(path = {"/{id:.+}"})
    @AccessLogger("{delete_by_primary_key}")
    @ApiOperation("根据ID删除数据")
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
    @OpenAPI
    public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.deleteByPrimaryKey(id);
    }

    /**
     * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
     */


}
