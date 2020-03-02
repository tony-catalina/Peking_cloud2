package awd.cloud.platform.controller.kss;
import awd.bj.base.model.R;
import awd.bj.kss.model.PhotosModel;
import awd.cloud.platform.api.KssServerApis;
import awd.bj.base.model.Variables;
import awd.cloud.platform.api.WorkFlowService;
import awd.cloud.platform.model.kss.*;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping("/v4/kss/kss_rsdj")
@Api(tags = "kss-rsdj",description = "rsdj")
public class Kss_RsdjController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;
    @Autowired
    private WorkFlowService workFlowService;

    /**
     * @api {post} /v4/kss/kss_rsdj/rsdjJbxxAdd 入所登记基本信息添加
     * @apiVersion 0.4.0
     * @apiName rsdjJbxxAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 入所登记基本信息添加.
     * @apiParam {String} appcode 					    应用代码(必填)
     * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 							保存参数集(必填)
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * "entity":[
     * {
     * "id":"id(必填;最大长度:23)",
     * "rybh":"人员编号(必填;最大长度:21)",
     * "snbh":"人员所内编号(必填;最大长度:8)",
     * "xm":"姓名(必填;最大长度:30)",
     * "xb":"性别(必填;最大长度:1)",
     * "gj":"国籍(必填；最大长度:3)",
     * "whcd":"文化程度(必填；最大长度:2)",
     * "sf":"身份(必填；最大长度:2)",
     * "mz":"民族(必填；最大长度:2)",
     * "dah":"档案编号(必填；最大长度:20)",
     * "zjlx":"证件类型(必填；最大长度:30)",
     * "zjh":"证件号(必填；最大长度:18)",
     * "zzmm":"政治面貌(必填；最大长度:2)",
     * "jkzk":"健康情况(必填;最大长度:2)",
     * "csrq":"出生日期(必填；格式:yyyy-MM-dd hh:mm:ss)(必填;最大长度:30)",
     * "hyzk":"婚姻状况(必填；最大长度:1)",
     * "tssf":"特殊身份(必填;最大长度:10)",
     * "sypz":"收押凭证(必填；最大长度:1)",
     * "sypzwsh":"收押凭证文书号(必填；最大长度:40)",
     * "rsxz":"入所原因(必填；最大长度:2)",
     * "jyrq":""羁押日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
     * "jlrq":"扣留日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
     * "dbrq":"逮捕日期(格式:yyyy-MM-dd hh:mm:ss)",
     * "dwlx":"办案单位类型(必填;最大长度:1)",
     * "badw":"办案单位(必填;最大长度:60)",
     * "bahj":"办案环节(必填;最大长度:2)",
     * "cylx":"成员类型(必填;最大长度:2)",
     * "sydw":"送押单位(必填;最大长度:60)",
     * "syr":"送押人(必填；最大长度:30)",
     * "rsrq":"入所时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     * "gyqx":"关押期限(必填；格式:yyyy-MM-dd hh:mm:ss)",
     * "jsh":"监室号(必填；最大长度:4)",
     * "drjsr":"带入监室人(必填;最大长度:30)",
     * "drjsj":"带入监室时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     * "ay":"主要案由(必填；最大长度:34)",
     * "jyaq":"简要案情(必填；最大长度:255)"
     * }
     * ]
     * }
     */

    //{"id":"\\d{1,23}","rybh":"\\d{1,21}","snbh":"\\d{1,8}","xm":"\\S{1,30}","xb":"\\d{1}","gj":"\\d{1,3}","whcd":"\\d{1,2}","sf":"\\d{1,2}","mz":"\\d{1,2}","dah":"\\d{1,20}","zjlx":"\\d{1,30}","zjh":"\\d{1,18}","zzmm":"\\d{1,2}","jkzk":"\\d{1,2}","csrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","hyzk":"\\d{1}","tssf":"\\d{1,10}","sypz":"\\d{1}","sypzwsh":"\\d{1,40}","rsxz":"\\d{1,2}","jyrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jlrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","dbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","dwlx":"\\d{1}","badw":"\\S{1,60}","cylx":"\\d{1,2}","sydw":"\\S{1,60}","syr":"\\S{1,30}","rsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","gyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jsh":"\\d{1,4}","drjsr":"\\S{1,30}","drjsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ay":"\\d{1,34}","jyaq":"\\S{1,255}"}
    //{"entity":[{"id":"31000011120190620000123","rybh":"310000111201906200004","snbh":"20190035","xm":"汪枫桦","xb":"1","gj":"156","whcd":"90","sf":"09","mz":"01","dah":"","zjlx":"99","zjh":"321300197812313138","zzmm":"02","jkzk":"3","csrq":"1997-06-18 10:10:10","hyzk":"3","tssf":"01","sypz":"5","sypzwsh":"1231232","rsxz":"13","jyrq":"2019-10-10 10:10:10","jlrq":"2019-10-10 10:10:10","dbrq":"2019-10-10 10:10:10","dwlx":"3","badw":"北京市公安局国内安全保卫局","bahj":"13","sydw":"北京市公安局国内安全保卫局","syr":"sdsa","rsrq":"2019-10-10 10:10:10","gyqx":"2019-10-10 10:10:10","jsh":"1201","drjsr":"dsa","drjsj":"2019-10-10 10:10:10","ay":"010130","jyaq":"dsdasddsad"}]}
    @ApiOperation("入所登记基本信息添加")
    @PostMapping("/rsdjJbxxAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveZbsy(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/kss_rsdj/rsdjJbxxAdd";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist = toListMap(map.get("entity").toString());
            String taskid = (String) rslist.get(0).get("taskid");
            System.err.println("taskid--" + taskid);
            if (StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不能为空！");
            }
            System.err.println("map--" + map.get("entity").toString());
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<Kss_JbxxModelDO> jbxxModels = JSONArray.parseArray(map.get("entity").toString(), Kss_JbxxModelDO.class);
            System.err.println("jbxxModels--" + JSON.toJSONString(jbxxModels.get(0)));
            jbxxModels.get(0).setCreatetime(new Date());
            jbxxModels.get(0).setState("R8");
            jbxxModels.get(0).setJsbh(jsbh);
            jbxxModels.get(0).setCreator("管理者");
            Kss_JbxxModelDO jbxxModel = jbxxModels.get(0);
            System.err.println("jbxxModel--" + JSON.toJSONString(jbxxModel));
            ResponseMessage<String> result = kssServerApis.update(jbxxModel.getId(), jbxxModel);
            System.err.println("result--" + result.getResult());
            if (result != null) {
                String jbxx = JSONUtil.toJson(jbxxModel);
                Map<String, Object> params = JSONUtil.toMap(jbxx);
                Variables variables = new Variables();
                variables.setJsbh(jbxxModel.getJsbh());
                variables.setRybh(jbxxModel.getRybh());
                variables.setParams(params);
                R r = workFlowService.execute(taskid, variables);
                if (r != null) {
                    result.setMessage("保存成功!");
                } else {
                    result.setMessage("服务异常,保存失败!");
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/kss/kss_rsdj/saveForRsdjZp 入所登记照片保存
     * @apiVersion 0.4.0
     * @apiName saveForRsdjZp
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 入所登记照片保存.
     * @apiParam {String} appcode 					    应用代码(必填)
     * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 							保存参数集(必填)
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * "entity":[
     * {
     * "jsh":"监室号(必填;最大长度:4)",
     * "rybh":"人员编号(必填;最大长度:21)",
     * "xm":"姓名(必填;最大长度:30)",
     * "csrq":"出生日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
     * "jlrq":"拘留日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
     * "whcdString":"文化程度(最大长度:60)",
     * "jljssjString":"拘留截止日期(必填；格式:yyyy-MM-dd hh:mm:ss)"
     * }]
     * }
     */

    //{"jsh":"\\d{1,4}","rybh":"\\d{1,21}","xm":"\\S{1,30}","whcdString":"\\S{1,60}","csrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jljssjString":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jlrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    //{"entity":[{"qm":"","zm":"","ym":"","zp1id":"","zp2id":"","zp3id":"","formData":{"rybh":"110000114201912040014","jsh":"9009","xm":"123","csrq":"1966-06-10 10:10:10","jlrq":"2019-12-05 10:10:10","whcdString":"相当大学毕业","jljssjString":"2020-01-22 10:10:10"}}]}
    @ApiOperation("入所登记照片保存")
    @PostMapping("/saveForRsdjZp")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveForRsdjZp(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/kss_rsdj/saveForRsdjZp";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist = toListMap(map.get("entity").toString());
            Map<String, Object> formData = (Map<String, Object>) rslist.get(0).get("formData");
            Map<String, Object> mapls=new HashMap<String, Object>();
            mapls.put("entity",formData);
            String jsons="{'entity':["+ JSON.toJSONString(formData)+"]}";
            Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
            maplss.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(maplss);
            if (msg.getStatus() != 200) {
                return ResponseMessage.error(msg.getMessage());
            }
            String rybh = (String) formData.get("rybh");
            String xm = (String) formData.get("xm");
            String jsh = (String) formData.get("jsh");
            String zpi1id = (String) rslist.get(0).get("zp1id");
            String zpi2id = (String) rslist.get(0).get("zp2id");
            String zpi3id = (String) rslist.get(0).get("zp3id");
            List<PhotosModel> photosModels = new ArrayList<PhotosModel>();
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
            MultipartFile photo0 = multiRequest.getFile("qm");
            MultipartFile photo1 = multiRequest.getFile("zm");
            MultipartFile photo2 = multiRequest.getFile("ym");
            String photoUrl = null;
            List<MultipartFile> file = new ArrayList<MultipartFile>();
            List<String> zpIdList = new ArrayList<String>();
            if (!StringUtils.isNullOrEmpty(photo0)) {
                file.add(photo0);
                zpIdList.add(zpi1id);
            }
            if (!StringUtils.isNullOrEmpty(photo1)) {
                file.add(photo1);
                zpIdList.add(zpi2id);
            }
            if (!StringUtils.isNullOrEmpty(photo2)) {
                file.add(photo2);
                zpIdList.add(zpi3id);
            }
            String fileName = "";
            String ext = "";
            PhotosModel photosModel;
            try {
                if (file.size() >= 1) {
                    for (int i = 0; i < file.size(); i++) {
                        if (file.get(i) != null) {
                            photosModel = new PhotosModel();
                            photosModel.setPhoto(file.get(i).getBytes());
                            fileName = file.get(i).getOriginalFilename();
                            ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                            try {

                                photoUrl = kssServerApis.uploadZpDfs(fileName, file.get(i).getBytes(), ext);
                                if (photoUrl == null) {
                                    photoUrl = "";
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (!StringUtils.isNullOrEmpty(rybh)) {
                                photosModel.setRybh(rybh);
                            }
                            photosModel.setPhotoUrl(photoUrl);
                            photosModel.setJsbh(jsbh);
                            photosModel.setId(zpIdList.get(i));
                            photosModels.add(photosModel);
                        }
                    }
                }
                Kss_RsdjModel rsdjModal = new Kss_RsdjModel();
                Kss_JbxxModelDO jbxxModel = new Kss_JbxxModelDO();
                jbxxModel.setRybh(rybh);
                jbxxModel.setXm(xm);
                jbxxModel.setJsbh(jsbh);
                jbxxModel.setJsh(jsh);
                rsdjModal.setJbxxEntity(jbxxModel);
                rsdjModal.setPhotosEntities(photosModels);
                ResponseMessage<String> result = kssServerApis.saveForRsdjZp(rsdjModal);
                if (result.getStatus() == 200) {
                    result.setMessage("保存成功!");
                } else {
                    result.setMessage("服务异常,保存失败!");
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseMessage.error("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}


