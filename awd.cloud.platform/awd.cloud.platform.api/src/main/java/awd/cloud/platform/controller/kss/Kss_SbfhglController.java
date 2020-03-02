package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.PwglModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.Kss_JbxxModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/sbfhgl")
@Api(tags = "kss-sbfhgl",description = "Sbfhgl")
public class Kss_SbfhglController extends PublicService {
    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {get} /v4/kss/sbfhgl/findCountBySbfh 根据识别服号查询
     * @apiVersion 0.4.0
     * @apiName findCountBySbfh
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 根据识别服号查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}result										    result
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576826568061
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "yfh":"1111"
     *        }
     *
     *
     */
    @OpenAPI
    @ApiOperation("根据识别服号查询")
    @GetMapping("/findCountBySbfh")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> findCountBySbfh(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //{"yfh":"1111"}
        //result
        String interfaceId = "/v4/kss/sbfhgl/findCountBySbfh";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            String yfh = (String) maps.getResult().get("yfh");

            if (StringUtils.isNullOrEmpty(yfh)) {
                return ResponseMessage.error("yfh不能为空!");
            }

            ResponseMessage<String> result = kssServerApis.findCountBySbfh(yfh);
            List lists=new ArrayList();
            lists.add(result);
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", lists);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", lists.size());
            maplist.put("page", request.getParameter("page"));

            System.err.println("result" + JSON.toJSONString(maplist));

            ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }


    /**
     * @api {post} /v4/kss/sbfhgl/addsbfhgl 添加识别服号管理
     * @apiVersion 0.4.0
     * @apiName addsbfhgl
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 添加识别服号管理.
     *

     * @apiParam {String} appcode 					    应用代码(必填)
     * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 							保存参数集(必填)
     *
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *  "message": "保存成功!",
     *  "result": "11000011420200113000070",
     *  "status": 200,
     *  "timestamp": 1578892709281
     *}
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       	"entity":[{
     *                  "id":"id(最大长度:23)"
     *                  "tbr":"填表人(最大长度:30)"
     *                  "tbrq":"填表日期(格式:yyyy-MM-dd hh:mm:ss)"
     *                  "jsh":"监室号(最大长度:4)"
     *                  "xm": "姓名(最大长度:30)",
     *                  "bm": "别名(最大长度:30)",
     *                  "xb": "性别(最大长度:1)",
     *                  "csrq": "出生日期(格式:yyyy-MM-dd hh:mm:ss)",
     *                  "rsrq": "入所日期(格式:yyyy-MM-dd hh:mm:ss)",
     *                  "bahj": "办案环节(最大长度:50)",
     *                  "ay": "案由(最大长度:50)",
     *                  "hjd": "户籍地(最大长度:120)",
     *                  "sbfh":"识别服号(必填;最大长度:4)"
     *                  "rybh":"人员编号(最大长度:21)"
     *                  "jbxxId":"基本信息id(最大长度:23)"
     *          		}]
     *     	  }
     */

    //{"sbfh":"\\d{1,4}"}
    //{"entity":[{"id":"","tbr":"aa","tbrq":"2020-01-15","jsh":"9009","xm":"123","bm":"","xb":"1","csrq":"1966-06-10","rsrq":"2019-10-28","bahj":"23","ay":"010130","hjd":"110101","sbfh":"1111","rybh":"110000114201912040014","jbxxId":"11000011420191204000424"}]}

    @ApiOperation("添加识别服号管理")
    @PostMapping("/addsbfhgl")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addsbfhgl(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/sbfhgl/addsbfhgl";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            String yfh = (String) rslist.get(0).get("sbfh");
            String jbxxId = (String) rslist.get(0).get("jbxxId");
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            Kss_JbxxModelDO jbxx = JSONObject.parseObject(rslist.get(0).toString(),Kss_JbxxModelDO.class);
            ResponseMessage<String> result = kssServerApis.update(jbxxId,jbxx);
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }



}
