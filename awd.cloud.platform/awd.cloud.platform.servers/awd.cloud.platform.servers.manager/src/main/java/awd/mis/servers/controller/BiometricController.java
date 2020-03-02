package awd.mis.servers.controller;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.utils.Base64Utils;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.api.LogsService;
import awd.mis.servers.entity.*;
import awd.mis.servers.service.*;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sykean.client.ClientManger;
import com.sykean.client.DeviceService;
import com.sykean.client.base.CallBack;
import com.sykean.client.model.*;
import com.sykean.client.utils.StringUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author WS
 * @Description:
 * @date 2019/12/30 上午9:46
 */
@RestController
@RequestMapping("/biometric")
public class BiometricController {

    @Autowired
    private MfaceService mfaceService;

    @Autowired
    private MjjbxxService mjjbxxService;

    @Autowired
    private JbxxService jbxxService;

    @Autowired
    private MirisService mirisService;

    @Autowired
    private MfingerService mfingerService;

    @Autowired
    private ZfaceService zfaceService;

    @Autowired
    private ZirisService zirisService;

    @Autowired
    private ZfingerService zfingerService;

    @Autowired
    private LogsService logsService;

    //采集模式(ModeType) 1-虹膜2-人脸3-指纹4-指静脉5-IC(刷卡) 6-身份证7-高拍仪
    //采集模式(CollecType) 1-注册2-比对

    @OpenAPI
    @PostMapping("/mcardCJ")
    @ApiOperation("民警卡采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("mcardCJ")
    @HystrixCommand
    public ResponseMessage<String> mcardCJ(@RequestBody @Validated(CreateGroup.class) MjjbxxEntity mjjbxxEntity, @RequestParam("ip") String ip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<String> li = Lists.newArrayList("5");
        boolean flag = DeviceService.getInstance().connect(ip);
        if (!flag) {
            return ResponseMessage.error("设备连接失败，请检测IP是否设置正确！");
        }
        String deviceIpCmd = StringUtils.getDeviceIpCmd(ip, "1119");
        if (ClientManger.getInstance().getDeviceCollectFlag(deviceIpCmd)) {
            return ResponseMessage.error("设备采集中,请稍后！");
        }
        DeviceService.getInstance().collect(ip, li, "1", this.callBack(ip, JSONUtil.toJson(mjjbxxEntity), MjjbxxEntity.class));
        return ResponseMessage.ok("采集已经开始，请留意机器语音！");
    }

    @OpenAPI
    @PostMapping("/mfaceCJ")
    @ApiOperation("民警人脸采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("mfaceCJ")
    @HystrixCommand
    public ResponseMessage<String> mfaceCJ(@RequestBody @Validated(CreateGroup.class) MfaceEntity mfaceEntity, @RequestParam("ip") String ip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<String> li = Lists.newArrayList("2");
        boolean flag = DeviceService.getInstance().connect(ip);
        if (!flag) {
            return ResponseMessage.error("设备连接失败，请检测IP是否设置正确！");
        }
        String deviceIpCmd = StringUtils.getDeviceIpCmd(ip, "1119");
        if (ClientManger.getInstance().getDeviceCollectFlag(deviceIpCmd)) {
            return ResponseMessage.error("设备采集中,请稍后！");
        }
        DeviceService.getInstance().collect(ip, li, "2", this.callBack(ip, JSONUtil.toJson(mfaceEntity), MfaceEntity.class));
        return ResponseMessage.ok("采集已经开始，请留意机器语音！");
    }

    @OpenAPI
    @PostMapping("/mIrisCJ")
    @ApiOperation("民警虹膜采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("mIrisCJ")
    @HystrixCommand
    public ResponseMessage<String> mIrisCJ(@RequestBody @Validated(CreateGroup.class) MirisEntity mirisEntity, @RequestParam("ip") String ip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<String> li = Lists.newArrayList("1");
        boolean flag = DeviceService.getInstance().connect(ip);
        if (!flag) {
            return ResponseMessage.error("设备连接失败，请检测IP是否设置正确！");
        }
        String deviceIpCmd = StringUtils.getDeviceIpCmd(ip, "1119");
        if (ClientManger.getInstance().getDeviceCollectFlag(deviceIpCmd)) {
            return ResponseMessage.error("设备采集中,请稍后！");
        }
        DeviceService.getInstance().collect(ip, li, "1", this.mjIrisCallback(ip, JSONUtil.toJson(mirisEntity), MirisEntity.class));
        return ResponseMessage.ok("采集已经开始，请留意机器语音！");
    }

    @OpenAPI
    @PostMapping("/mfingerCJ")
    @ApiOperation("民警指纹采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("mfingerCJ")
    @HystrixCommand
    public ResponseMessage<String> mfingerCJ(@RequestBody @Validated(CreateGroup.class) MfingerEntity mfingerEntity, @RequestParam("ip") String ip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<String> li = Lists.newArrayList("3");
        boolean flag = DeviceService.getInstance().connect(ip);
        if (!flag) {
            return ResponseMessage.error("设备连接失败，请检测IP是否设置正确！");
        }
        String deviceIpCmd = StringUtils.getDeviceIpCmd(ip, "1119");
        if (ClientManger.getInstance().getDeviceCollectFlag(deviceIpCmd)) {
            return ResponseMessage.error("设备采集中,请稍后！");
        }
        DeviceService.getInstance().collect(ip, li, "1", this.callBack(ip, JSONUtil.toJson(mfingerEntity), MfingerEntity.class));
        return ResponseMessage.ok("采集已经开始，请留意机器语音！");
    }


    @OpenAPI
    @PostMapping("/zcardCJ")
    @ApiOperation("在押人员卡采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("zcardCJ")
    @HystrixCommand
    public ResponseMessage<String> zcardCJ(@RequestBody @Validated(CreateGroup.class) JbxxEntity jbxxEntity, @RequestParam("ip") String ip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<String> li = Lists.newArrayList("5");
        boolean flag = DeviceService.getInstance().connect(ip);
        if (!flag) {
            return ResponseMessage.error("设备连接失败，请检测IP是否设置正确！");
        }
        String deviceIpCmd = StringUtils.getDeviceIpCmd(ip, "1119");
        if (ClientManger.getInstance().getDeviceCollectFlag(deviceIpCmd)) {
            return ResponseMessage.error("设备采集中,请稍后！");
        }
        DeviceService.getInstance().collect(ip, li, "1", this.callBack(ip, JSONUtil.toJson(jbxxEntity), JbxxEntity.class));
        return ResponseMessage.ok("采集已经开始，请留意机器语音！");
    }

    @OpenAPI
    @PostMapping("/zfaceCJ")
    @ApiOperation("在押人员人脸采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("zfaceCJ")
    @HystrixCommand
    public ResponseMessage<String> zfaceCJ(@RequestBody @Validated(CreateGroup.class) ZfaceEntity zfaceEntity, @RequestParam("ip") String ip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<String> li = Lists.newArrayList("2");
        boolean flag = DeviceService.getInstance().connect(ip);
        if (!flag) {
            return ResponseMessage.error("设备连接失败，请检测IP是否设置正确！");
        }
        String deviceIpCmd = StringUtils.getDeviceIpCmd(ip, "1119");
        if (ClientManger.getInstance().getDeviceCollectFlag(deviceIpCmd)) {
            return ResponseMessage.error("设备采集中,请稍后！");
        }
        DeviceService.getInstance().collect(ip, li, "2", this.callBack(ip, JSONUtil.toJson(zfaceEntity), ZfaceEntity.class));
        return ResponseMessage.ok("采集已经开始，请留意机器语音！");
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
    public ResponseMessage<String> zIrisCJ(@RequestBody @Validated(CreateGroup.class) ZirisEntity zirisEntity, @RequestParam("ip") String ip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<String> li = Lists.newArrayList("1");
        boolean flag = DeviceService.getInstance().connect(ip);
        if (!flag) {
            return ResponseMessage.error("设备连接失败，请检测IP是否设置正确！");
        }
        String deviceIpCmd = StringUtils.getDeviceIpCmd(ip, "1119");
        if (ClientManger.getInstance().getDeviceCollectFlag(deviceIpCmd)) {
            return ResponseMessage.error("设备采集中,请稍后！");
        }
        DeviceService.getInstance().collect(ip, li, "1", this.callBack(ip, JSONUtil.toJson(zirisEntity), ZirisEntity.class));
        return ResponseMessage.ok("采集已经开始，请留意机器语音！");
    }

    @OpenAPI
    @PostMapping("/zfingerCJ")
    @ApiOperation("在押人员指纹采集")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("zfingerCJ")
    @HystrixCommand
    public ResponseMessage<String> zfingerCJ(@RequestBody @Validated(CreateGroup.class) ZfingerEntity zfingerEntity, @RequestParam("ip") String ip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        List<String> li = Lists.newArrayList("3");
        boolean flag = DeviceService.getInstance().connect(ip);
        if (!flag) {
            return ResponseMessage.error("设备连接失败，请检测IP是否设置正确！");
        }
        String deviceIpCmd = StringUtils.getDeviceIpCmd(ip, "1119");
        if (ClientManger.getInstance().getDeviceCollectFlag(deviceIpCmd)) {
            return ResponseMessage.error("设备采集中,请稍后！");
        }
        DeviceService.getInstance().collect(ip, li, "1", this.callBack(ip, JSONUtil.toJson(zfingerEntity), ZfingerEntity.class));
        return ResponseMessage.ok("采集已经开始，请留意机器语音！");
    }

    /**
     * description:
     *
     * @return com.sykean.client.base.CallBack<com.sykean.client.model.BiometricResult>
     * @params ip
     * @params json
     * @params t
     * @author by: WS
     * @createtime: 2019/12/30
     */
    private CallBack<BiometricResult> callBack(String ip, String json, Class t) {

        CallBack<BiometricResult> callBack = new CallBack<BiometricResult>(300) {
            @Override
            public void onCallBack(ResponseResult<BiometricResult> responseResult) {
                CallBack<BiometricResult> ca = new CallBack<BiometricResult>() {
                    @Override
                    public void onCallBack(ResponseResult<BiometricResult> responseResult) {
                    }
                };
                System.err.println("----------------__________------------------");
                System.err.println(JSONUtil.toJson(responseResult));
                String msg = "采集成功";
                if ("0".equals(responseResult.getResult())) {
                    BiometricResult biometricResult = responseResult.getData();
                    if (biometricResult != null && biometricResult.getFaceInfo() != null) {
                        FaceInfo faceInfo = responseResult.getData().getFaceInfo();
                        Map<String, String> templates = faceInfo.getTemplates();
                        Map<String, String> photos = faceInfo.getPhotos();
                        if (templates.isEmpty() || photos.isEmpty()) {
                            DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                            return;
                        }
                        switch (t.getSimpleName()) {


                            case "MfaceEntity":
                                MfaceEntity mfaceEntity = JSONUtil.toBean(json, MfaceEntity.class);
                                mfaceEntity.setCjfs("0");
                                try {
                                    byte[] bytes = Base64Utils.decode(photos.get("0"));
                                    mfaceEntity.setRltx(bytes);
                                    mfaceEntity.setRltz(Base64Utils.decode(templates.get("0")));
                                    MfaceEntity m = mfaceService.selectByZjh(mfaceEntity.getZjh());
                                    if (m != null && m.getId() != null) {
                                        mfaceEntity.setId(m.getId());
                                        msg = "已成功替换人脸信息";
                                    }
                                    ResponseMessage<String> res = logsService.upload(bytes, mfaceEntity.getZjh() + System.currentTimeMillis(), "jpg");
                                    if (res.getStatus() == 200) {
                                        mfaceEntity.setRltxurl(res.getResult());
                                    }
                                    String i = mfaceService.saveOrUpdate(mfaceEntity);
                                    System.err.println(i);
                                    senResult(ip, msg, ca);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                                    return;
                                }
                                break;
                            case "ZfaceEntity":
                                ZfaceEntity zfaceEntity = JSONUtil.toBean(json, ZfaceEntity.class);
                                zfaceEntity.setCjfs("0");
                                try {
                                    byte[] bytes = Base64Utils.decode(photos.get("0"));
                                    zfaceEntity.setRltx(bytes);
                                    zfaceEntity.setRltz(Base64Utils.decode(templates.get("0")));
                                    ZfaceEntity z = zfaceService.selectByRybh(zfaceEntity.getRybh());
                                    if (z != null && z.getId() != null) {
                                        zfaceEntity.setId(z.getId());
                                        msg = "已成功替换人脸信息";
                                    }
                                    ResponseMessage<String> res = logsService.upload(bytes, zfaceEntity.getRybh() + System.currentTimeMillis(), "jpg");
                                    if (res.getStatus() == 200) {
                                        zfaceEntity.setRltxurl(res.getResult());
                                    }
                                    String i = zfaceService.saveOrUpdate(zfaceEntity);
                                    System.err.println(i);
                                    senResult(ip, msg, ca);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                                    return;
                                }
                                break;
                        }

                    } else if (biometricResult != null && biometricResult.getIrisInfo() != null) {
                        IrisInfo irisInfo = responseResult.getData().getIrisInfo();
                        Map<String, String> templates = irisInfo.getTemplates();
                        Map<String, String> photos = irisInfo.getPhotos();
                        if (templates.isEmpty() || photos.isEmpty()) {
                            DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                            return;
                        }
                        switch (t.getSimpleName()) {
                            case "MirisEntity":
                                MirisEntity mirisEntity = null;
                                if (templates.size() == photos.size()) {
                                    for (String s : templates.keySet()) {
                                        mirisEntity = JSONUtil.toBean(json, MirisEntity.class);
                                        mirisEntity.setHmwzbh(s);
                                        try {
                                            byte[] bytes = Base64Utils.decode(photos.get(s));
                                            mirisEntity.setHmtx(bytes);
                                            mirisEntity.setHmtz(Base64Utils.decode(templates.get(s)));
                                            ResponseMessage<String> res = logsService.upload(bytes, mirisEntity.getZjh() + System.currentTimeMillis(), "jpg");
                                            if (res.getStatus() == 200) {
                                                mirisEntity.setHmtxurl(res.getResult());
                                            }
                                            MirisEntity m = mirisService.selectByPk(mirisEntity.getZjh(), mirisEntity.getHmwzbh());

                                            if (m != null) {
                                                mirisEntity.setId(m.getId());
                                                msg = "已成功替换虹膜信息";
                                            }
                                            mirisService.saveOrUpdate(mirisEntity);
                                            senResult(ip, msg, ca);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                break;
                            case "ZirisEntity":
                                ZirisEntity zirisEntity = null;
                                if (templates.size() == photos.size()) {
                                    for (String s : templates.keySet()) {
                                        zirisEntity = JSONUtil.toBean(json, ZirisEntity.class);
                                        zirisEntity.setHmwzbh(s);
                                        try {
                                            byte[] bytes = Base64Utils.decode(photos.get(s));
                                            zirisEntity.setHmtx(bytes);
                                            zirisEntity.setHmtz(Base64Utils.decode(templates.get(s)));
                                            ResponseMessage<String> res = logsService.upload(bytes, zirisEntity.getRybh() + System.currentTimeMillis(), "jpg");
                                            if (res.getStatus() == 200) {
                                                zirisEntity.setHmtxurl(res.getResult());
                                            }
                                            ZirisEntity z = zirisService.selectByPk(zirisEntity.getRybh(), zirisEntity.getHmwzbh());
                                            if (z != null) {
                                                zirisEntity.setId(z.getId());
                                                msg = "已成功替换虹膜信息";
                                            }
                                            zirisService.saveOrUpdate(zirisEntity);
                                            senResult(ip, msg, ca);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                break;
                        }

                    } else if (biometricResult != null && biometricResult.getFingerPrintInfo() != null) {
                        FingerPrintInfo fingerPrintInfo = responseResult.getData().getFingerPrintInfo();
                        Map<String, String> templates = fingerPrintInfo.getTemplates();
                        Map<String, String> photos = fingerPrintInfo.getPhotos();
                        if (templates.isEmpty() || photos.isEmpty()) {
                            DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                            return;
                        }
                        switch (t.getSimpleName()) {
                            case "MfingerEntity":
                                MfingerEntity mfingerEntity = null;
                                if (templates.size() == photos.size()) {
                                    for (String s : templates.keySet()) {
                                        mfingerEntity = JSONUtil.toBean(json, MfingerEntity.class);
                                        try {
                                            byte[] bytes = Base64Utils.decode(photos.get(s));
                                            mfingerEntity.setZwtx(bytes);
                                            mfingerEntity.setZwtz(Base64Utils.decode(templates.get(s)));
                                            ResponseMessage<String> res = logsService.upload(bytes, mfingerEntity.getZjh() + System.currentTimeMillis(), "jpg");
                                            if (res.getStatus() == 200) {
                                                mfingerEntity.setZwtxurl(res.getResult());
                                            }
                                            MfingerEntity m = mfingerService.selectByPk(mfingerEntity.getZjh(), mfingerEntity.getSzbh());
                                            if (m != null) {
                                                mfingerEntity.setId(m.getId());
                                                msg = "已成功替换指纹信息";
                                            }
                                            mfingerService.saveOrUpdate(mfingerEntity);
                                            senResult(ip, msg, ca);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                break;
                            case "ZfingerEntity":
                                ZfingerEntity zfingerEntity = null;
                                if (templates.size() == photos.size()) {
                                    for (String s : templates.keySet()) {
                                        zfingerEntity = JSONUtil.toBean(json, ZfingerEntity.class);
//                                        zfingerEntity.setHmwzbh(s);
                                        try {
                                            byte[] bytes = Base64Utils.decode(photos.get(s));
                                            zfingerEntity.setZwtx(bytes);
                                            zfingerEntity.setZwtz(Base64Utils.decode(templates.get(s)));
                                            ResponseMessage<String> res = logsService.upload(bytes, zfingerEntity.getRybh() + System.currentTimeMillis(), "jpg");
                                            if (res.getStatus() == 200) {
                                                zfingerEntity.setZwtxurl(res.getResult());
                                            }
                                            ZfingerEntity z = zfingerService.selectByPk(zfingerEntity.getRybh(), zfingerEntity.getSzbh());
                                            if (z != null) {
                                                zfingerEntity.setId(z.getId());
                                                msg = "已成功替换指纹信息";
                                            }
                                            zfingerService.saveOrUpdate(zfingerEntity);
                                            senResult(ip, msg, ca);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                break;
                        }

                    } else if (biometricResult != null && biometricResult.getIcCardInfo() != null) {
                        System.out.println("------------------++00:"+biometricResult.getIcCardInfo());
                        IcCardInfo icCardInfo = responseResult.getData().getIcCardInfo();
                        String card = icCardInfo.getCardNumber();
                        if (card.isEmpty()) {
                            DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                            return;
                        }
                        switch (t.getSimpleName()) {
                            case "MjjbxxEntity":
                                MjjbxxEntity mjjbxxEntity = JSONUtil.toBean(json, MjjbxxEntity.class);
                                mjjbxxEntity.setXkid(card);
                                try {
                                    MjjbxxEntity m = mjjbxxService.selectByJh(mjjbxxEntity.getJh());
                                    if (m != null && m.getId() != null) {
                                        mjjbxxEntity.setId(m.getId());
                                        msg = "已成功替换卡信息";
                                    }

                                    String i = mjjbxxService.saveOrUpdate(mjjbxxEntity);
                                    System.err.println(i);
                                    senResult(ip, msg, ca);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                                    return;
                                }
                                break;
                            case "JbxxEntity":
                                System.err.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
                                JbxxEntity jbxxEntity = JSONUtil.toBean(json, JbxxEntity.class);
                                jbxxEntity.setShid(card);
                                try {

                                    JbxxEntity m = jbxxService.selectByrybh(jbxxEntity.getRybh());
                                    if (m != null && m.getId() != null) {
                                        jbxxEntity.setId(m.getId());
                                        msg = "已成功替换卡信息";
                                    }

                                    String i = jbxxService.saveOrUpdate(jbxxEntity);
                                    System.err.println(i);
                                    senResult(ip, msg, ca);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                                    return;
                                }

                                break;
                        }


                    } else {
                        DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                    }
                } else {
                    DeviceService.getInstance().sendCollectionResult(ip, "-1", responseResult.getMessage(), ca);
                }
            }
        };
        return callBack;
    }

    private CallBack<BiometricResult> mjIrisCallback(String ip, String json, Class t) {
        CallBack<BiometricResult> callBack = new CallBack<BiometricResult>(30) {
            @Override
            public void onCallBack(ResponseResult<BiometricResult> responseResult) {
                CallBack<BiometricResult> ca = new CallBack<BiometricResult>() {
                    @Override
                    public void onCallBack(ResponseResult<BiometricResult> responseResult) {
                    }
                };
                System.err.println("----------------mjIrisCallback------------------");
                System.err.println(JSONUtil.toJson(responseResult));
                String msg = "采集成功";
                if ("0".equals(responseResult.getResult())) {
                    IrisInfo irisInfo = responseResult.getData().getIrisInfo();
                    Map<String, String> templates = irisInfo.getTemplates();
                    Map<String, String> photos = irisInfo.getPhotos();
                    if (templates.isEmpty() || photos.isEmpty()) {
                        DeviceService.getInstance().sendCollectionResult(ip, "-1", "采集失败", ca);
                        return;
                    }
                    switch (t.getSimpleName()) {
                        case "MirisEntity":
                            MirisEntity mirisEntity = null;
                            if (templates.size() == photos.size()) {
                                for (String s : templates.keySet()) {
                                    mirisEntity = JSONUtil.toBean(json, MirisEntity.class);
                                    mirisEntity.setHmwzbh(s);
                                    try {
                                        byte[] bytes = Base64Utils.decode(photos.get(s));
                                        mirisEntity.setHmtx(bytes);
                                        mirisEntity.setHmtz(Base64Utils.decode(templates.get(s)));
                                        ResponseMessage<String> res = logsService.upload(bytes, mirisEntity.getZjh() + System.currentTimeMillis(), "jpg");
                                        if (res.getStatus() == 200) {
                                            mirisEntity.setHmtxurl(res.getResult());
                                        }
                                        MirisEntity m = mirisService.selectByPk(mirisEntity.getZjh(), mirisEntity.getHmwzbh());

                                        if (m != null) {
                                            mirisEntity.setId(m.getId());
                                            msg = "已成功替换虹膜信息";
                                        }
                                        mirisService.saveOrUpdate(mirisEntity);
                                        senResult(ip, msg, ca);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            break;
                        case "ZirisEntity":
                            ZirisEntity zirisEntity = null;
                            if (templates.size() == photos.size()) {
                                for (String s : templates.keySet()) {
                                    zirisEntity = JSONUtil.toBean(json, ZirisEntity.class);
                                    zirisEntity.setHmwzbh(s);
                                    try {
                                        byte[] bytes = Base64Utils.decode(photos.get(s));
                                        zirisEntity.setHmtx(bytes);
                                        zirisEntity.setHmtz(Base64Utils.decode(templates.get(s)));
                                        ResponseMessage<String> res = logsService.upload(bytes, zirisEntity.getRybh() + System.currentTimeMillis(), "jpg");
                                        if (res.getStatus() == 200) {
                                            zirisEntity.setHmtxurl(res.getResult());
                                        }
                                        ZirisEntity z = zirisService.selectByPk(zirisEntity.getRybh(), zirisEntity.getHmwzbh());
                                        if (z != null) {
                                            zirisEntity.setId(z.getId());
                                            msg = "已成功替换虹膜信息";
                                        }
                                        zirisService.saveOrUpdate(zirisEntity);
                                        senResult(ip, msg, ca);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            break;
                    }
                } else {
                    DeviceService.getInstance().sendCollectionResult(ip, "-1", responseResult.getMessage(), ca);
                }
            }
        };
        return callBack;
    }

    private void senResult(String ip, String msg, CallBack<BiometricResult> ca) {
        DeviceService.getInstance().sendCollectionResult(ip, "-1", msg, ca);
    }
}
