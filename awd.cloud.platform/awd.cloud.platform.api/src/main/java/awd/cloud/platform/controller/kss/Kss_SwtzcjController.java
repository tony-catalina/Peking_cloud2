package awd.cloud.platform.controller.kss;

import awd.bj.base.model.Variables;
import awd.bj.kss.model.PhotosModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PhotoService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping("/v4/kss/swtzcj")
@Api(tags = "jnp-swtzcj", description = "swtzcj")
public class Kss_SwtzcjController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private PhotoService photoService;

    /**
     * @api {post} /v4/kss/swtzcj/saveSwtz 生物特征保存
     * @apiVersion 0.4.0
     * @apiName saveSwtz
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 生物特征保存
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * {
     *      "appcode":"应用代码(必填)",
     *      "jsbh":"监所编号(必填; 最大长度:9)",
     *      "entity":[
     *           {
     *              "jsh":"监室号(必填; 最大长度:4)",
     *              "rybh":"人员编号(必填; 最大长度:21)",
     *              "snbh":"所内编号(必填; 最大长度:8)",
     *              "xm":"姓名(必填;)",
     *              "creator":"创建人(必填; 最大长度:50)"
     *           }
     *        ]
     *      }
     * }
     *
     */
    @ApiOperation("生物特征保存")
    @PostMapping("/saveSwtz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveSwtz(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        //接口id
        String interfaceId = "/v4/kss/swtzcj/saveSwtz";
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
            MultipartFile zmz = multiRequest.getFile("zmz");
            MultipartFile cmz1 = multiRequest.getFile("cmz1");
            MultipartFile cmz2 = multiRequest.getFile("cmz2");

			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			Map<String, Object> entityMap = (Map) JSONObject.parseObject(map.get("entity").toString(), List.class).get(0);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}
			System.err.println(map.get("entity").toString());

			List<PhotosModel> modelList = JSONArray.parseArray(map.get("entity").toString(), PhotosModel.class);

			PhotosModel photo = modelList.get(0);
            photo.setJsbh(jsbh);
            photo.setState("R2");
            photo.setCreatetime(new Date());

            Map<String, Object> params = new HashMap<String, Object>();
            Variables variables = new Variables();
            variables.setRybh(entityMap.get("rybh").toString());
            variables.setJsbh(jsbh);
            params.put("rybh", entityMap.get("rybh"));
            params.put("snbh", entityMap.get("snbh"));
            params.put("xm", entityMap.get("xm"));
            params.put("jsh", entityMap.get("jsh"));
            variables.setParams(params);

            String zmzUrl = null;
            String cmz1Url = null;
            String cmz2Url = null;
            PhotosModel photo1 = new PhotosModel();
            PhotosModel photo2 = new PhotosModel();
            PhotosModel photo3 = new PhotosModel();
            if (zmz != null) {
                photo1 = photo;
                photo1.setYwzp("1");
                String fileName = zmz.getOriginalFilename();
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                try {
                    zmzUrl = kssServerApis.uploadZpDfs(fileName, zmz.getBytes(), ext);
                    if (zmzUrl == null) {
                        zmzUrl = "";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.err.println("photo1保存zmzUrl==" + zmzUrl);
                photo1.setPhotoUrl(zmzUrl);
            } else {
                photo1 = photo;
                photo2.setPhotoUrl("");
                photo1.setYwzp("0");
            }
            photo1.setType("1");
            System.err.println("photo1==" + JSONUtil.toJson(photo1.getPhotoUrl()));
            ResponseMessage<String> r1 = photoService.savePhoto(photo1, zmz);
            if (cmz1 != null) {
                photo2 = photo;
                photo2.setYwzp("1");
                String fileName = cmz1.getOriginalFilename();
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                try {
                    cmz1Url = kssServerApis.uploadZpDfs(fileName, cmz1.getBytes(), ext);
                    System.err.println(cmz1Url);
                    if (cmz1Url == null) {
                        cmz1Url = "";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.err.println("photo2保存cmz1Url==" + cmz1Url);
                photo2.setPhotoUrl(cmz1Url);
            } else {
                photo2 = photo;
                photo2.setPhotoUrl("");
                photo2.setYwzp("0");
            }
            photo2.setType("2");
            System.err.println("photo2==" + JSONUtil.toJson(photo2.getPhotoUrl()));
            ResponseMessage<String> r2 = photoService.savePhoto(photo2, cmz1);
            if (cmz2 != null) {
                photo3 = photo;
                photo3.setYwzp("1");
                String fileName = cmz2.getOriginalFilename();
                String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                try {
                    cmz2Url = kssServerApis.uploadZpDfs(fileName, cmz2.getBytes(), ext);
                    if (cmz2Url == null) {
                        cmz2Url = "";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.err.println("photo3保存cmz2Url==" + cmz2Url);
                photo3.setPhotoUrl(cmz2Url);
            } else {
                photo3 = photo;
                photo3.setPhotoUrl("");
                photo3.setYwzp("0");
            }
            photo3.setType("3");
            System.err.println("photo3==" + JSONUtil.toJson(photo3.getPhotoUrl()));
            ResponseMessage<String> r3 = photoService.savePhoto(photo3, cmz2);
            return ResponseMessage.ok("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }
}
