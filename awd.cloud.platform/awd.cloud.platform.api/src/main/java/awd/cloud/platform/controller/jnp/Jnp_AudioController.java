package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.ThjlModel;
import awd.cloud.platform.model.kss.AudioModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/audio")
@Api(tags = "jnp-audio",description = "audio")
public class Jnp_AudioController extends PublicService {
    //定义音频文件大小，最大为50Mb(50*1024*1024)
    public static final long MAX_AUDIOFILE_SIZE = 52428800L;

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {post} /v4/jnp/audio/audioUpload 音频文件上传
     * @apiVersion 0.4.0
     * @apiName audioUpload
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 音频文件上传
     *
     * @apiParam {String} appcode                                        应用代码(必填)
     * @apiParam {String} jsbh                                           监所编号(必填; 最大字段长度：9)
     * @apiParam {MultipartFile} [audio]                                 音频文件(必填)
     *
     * @apiSuccess {String}result                                        返回结果
     * @apiSuccess {String}page                                          返回页数
     * @apiSuccess {String}status                                        返回状态
     * @apiSuccess {String}timestamp                                     时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *     "message": "文件上传成功!",
     *     "result": {
     *         "audioUrl": "http://192.168.4.50:8888/storagegroup/M00/00/27/wKgEMl4SyK-AQ2FZAAEGnV1YK7w764.m4a"
     *     },
     *     "status": 200,
     *     "timestamp": 1578289328723
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *     appcode:"应用代码(必填)",
     *     jsbh:"监所编号(必填; 最大字段长度：9)",
     *     audio:"音频文件(必填; 文件最大50Mb)"

     */
    @OpenAPI
    @ApiOperation("上传音频文件")
    @PostMapping("/audioUpload")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> audioUpload(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh) {
        System.err.println("appcodel-Url========="+appcode);
        //接口id
        String interfaceId = "/v4/jnp/audio/audioUpload";
        String json = "{}";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
            MultipartFile audioFile = multiRequest.getFile("audio");
            if (StringUtils.isNullOrEmpty(audioFile)){
                return ResponseMessage.error("请上传音频文件!");
            }
            System.err.println("Size---------------"+audioFile.getSize());
            System.err.println("MAX_AUDIOFILE_SIZE---------------"+MAX_AUDIOFILE_SIZE);
            if (audioFile.getSize()>MAX_AUDIOFILE_SIZE){
                return ResponseMessage.error("上传文件过大(请上传小于50Mb的音频文件)");
            }
            String fileName = audioFile.getOriginalFilename();
            String ext =fileName.substring(fileName.lastIndexOf(".") + 1);
            AudioModel audioModel = new AudioModel();
            audioModel.setFileName(fileName);
            audioModel.setFile(audioFile.getBytes());
            audioModel.setExt(ext);
            ResponseMessage<Map<String,Object>>  result =  kssServerApis.audioUpload(audioModel);
            if(result.getStatus() == 200){
                result.setMessage("文件上传成功!");
                return result;
            }else{
                return ResponseMessage.error("服务异常,上传文件失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("服务异常,上传文件失败!");
        }
    }
}
