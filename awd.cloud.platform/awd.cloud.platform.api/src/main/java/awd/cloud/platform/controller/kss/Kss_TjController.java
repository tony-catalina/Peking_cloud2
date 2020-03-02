package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.TjdjModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.model.kss.CrjjcModelDO;
import awd.cloud.platform.model.kss.Kss_TjAndCrjjcModel;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
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
import scala.annotation.meta.param;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Author：张延
 * Date：2020-01-08 13:32
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/kss/tj")
@Api(tags = "kss-tj",description = "Tj")
public class Kss_TjController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private ProcessService processService;

    @Autowired
    private KssAnalyseApis kssAnalyseApis;

    /**
     * @api {post} /v4/kss/jwzxjl/jwzxAdd  提解流程核对凭证(流程)
     * @apiVersion 0.4.0
     * @apiName jwzxAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提解流程核对凭证(流程)
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
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
     *  jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *      "xm":"姓名(必填)",
     *      "entity":[{
     *      "creator":"创建人(必填; 最大字段长度：50)",
     *       "rybh":"人员编号(必填;  最大字段长度：21)"
     *       }]
     *       }
     */
    //  {"creator":".{1,50}","rybh":"\\d{1,21}"}
    @ApiOperation("提解流程核对凭证(流程)")
    @PostMapping("/tjHdpz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> tjHdpz(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/tj/tjHdpz";

        //通过校验获取查询参数
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                System.out.println("----------------" + interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }

            List<TjdjModel> tjdjList = JSONArray.parseArray(map.get("entity").toString(), TjdjModel.class);
            TjdjModel tsdjModel = tjdjList.get(0);

            String tsflowKey = CacheUtils.get().getFlowKey("_kss_tj".toUpperCase());
            String tsKey = "kss_tj".toUpperCase();

            String xm = map.get("xm").toString();
            if (null == xm || "".equals(xm)) {
                return ResponseMessage.error("xm必填");
            }

            if (processService.FlowMutex(jsbh, tsdjModel.getRybh(), tsKey, "TJDJ").getStatus() != 200) {
                return processService.FlowMutex(jsbh, tsdjModel.getRybh(), tsKey, "TJDJ");
            }
            tsdjModel.setState("R2");
            tsdjModel.setJsbh(jsbh);
            tsdjModel.setCreator(xm);
            tsdjModel.setCreatetime(new Date());
            System.err.println("tsdjModel=" + JSON.toJSONString(tsdjModel));
            ResponseMessage<String> result = kssServerApis.tjdjAddflow(tsdjModel, tsflowKey);

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
    }


    /**
     * @return
     * @api {get} /v4/kss/tj/tjYwdt 提解业务动态
     * @apiVersion 0.4.0
     * @apiName tjYwdt
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提解业务动态
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}tjYwdt                                            提解业务动态
     *
     * @apiSuccess {String}ktsl                                              开庭审理
     * @apiSuccess {String}qt                                                其他
     * @apiSuccess {String}brxc                                              辨认现场
     * @apiSuccess {String}qzqz                                              取证取脏
     * @apiSuccess {String}sfjd                                              司法鉴定
     * @apiSuccess {String}zrs                                               总人数
     * @apiSuccess {String}flwssd                                            法律文书送达
     * @apiSuccess {String}xbpj                                              宣布判决
     * @apiSuccess {String}lx                                                聆讯
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * "result": {
     *      "tjYwdt": [{
     *       "ktsl": 0,
     *       "qt": 0,
     *       "brxc": 0,
     *       "qzqz": 0,
     *       "sfjd": 0,
     *       "zrs": 0,
     *       "flwssd": 0,
     *        "xbpj": 0,
     *        "lx": 0
     * }]
     * }
     * <p>
     * "page": "1"
     * },
     * "status": 200,
     * "timestamp": 1576826568061
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * 	  json:{
     *             "endTime":"结束时间(必传)"
     *             "startTime":"开始时间(必传)"
     *         }
     *
     *
     */
    @OpenAPI
    @ApiOperation("提解业务动态")
    @GetMapping("/tjYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> tjYwdt(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/tj/tjYwdt";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            if(StringUtils.isNullOrEmpty(maps.getResult().get("startTime"))){
                return ResponseMessage.error("开始时间必传");
            }
            String startTime = maps.getResult().get("startTime").toString();

            if(StringUtils.isNullOrEmpty(maps.getResult().get("endTime"))){
                return ResponseMessage.error("结束时间必传");
            }
            String endTime = maps.getResult().get("endTime").toString();

            ResponseMessage<Map<String, Object>> result = kssAnalyseApis.tjYwdt(jsbh, startTime, endTime);

            System.err.println("result" + JSON.toJSONString(result));

            List lists = new ArrayList<>();
            lists.add(result);

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", lists);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().size());
            maplist.put("page", request.getParameter("page"));

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
     * @api {post} /v4/kss/tj/addTjdj  提解登记保存
     * @apiVersion 0.4.0
     * @apiName addTjdj
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提解登记保存
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
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
     *       "entity":[{
     * 	            "creator":"管理员(必填；最大字段长度：50)",
     * 	            "id":"id(必填；最大字段长度：23)",
     * 	            "lxdh":"联系电话(最大字段长度：50)",
     * 	            "pzr":"批准人(最大字段长度：100)",
     * 	            "qttjry":"其他提解人员(最大字段长度：255)",
     * 	            "rybh":"人员编号(必填；最大字段长度：21)",
     * 	            "tjdw":"提解单位(必填；最大字段长度：60)",
     * 	            "tjjtyy":"提解具体原因(必填；最大字段长度：255)",
     * 	            "tjkh1":"提解卡号1(必填；最大字段长度：50)",
     * 	            "tjkh2":"提解卡号2(最大字段长度：50)",
     * 	            "tjry1":"提解人员1(最大字段长度：50)",
     * 	            "tjry2":"提解人员2(必填；最大字段长度：50)",
     * 	             "tjsj":"提解时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
     * 	             "tjyy":"提解原因(必填；最大字段长度：200)",
     * 	              "updatetime":"跟新时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
     * 	              "updator":"管理员(必填；最大字段长度：50)",
     * 	              "ywlcid":"业务流程ID(必填；最大字段长度：100)",
     * 	              "taskid":"任务id"
     *               }]
     *             }
     */
    //  {"tjsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","id":"\\d{1,23}","creator":".{1,50}","lxdh":"\\d{0,50}","pzr":".{0,100}","qttjry":".{0,255}","tjdw":".{1,60}","rybh":"\\d{1,21}","tjjtyy":".{1,255}","tjkh1":".{1,50}","tjkh2":".{1,50}","tjry1":".{1,50}","tjry2":".{1,50}","tjyy":".{1,200}","updatetime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","id":"\\d{1,23}","updator":".{1,50}","ywlcid":".{1,100}"}
    @ApiOperation("提解登记保存")
    @PostMapping("/addTjdj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addTjdj(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/tj/addTjdj";

        //通过校验获取查询参数
  try {
      //校验权限
      ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
      if (maps.getStatus() != 200) {
          return ResponseMessage.error(maps.getMessage());
      }
      //数据类型校验
      Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
      map.put("interfaceId", interfaceId);
      ResponseMessage<String> msg = this.modelYz(map);
      if (msg.getStatus() != 200) {
          System.out.println("----------------" + interfaceId);
          return ResponseMessage.error(msg.getMessage());
      }

      List<JbxxModelDO> jbxx = JSONArray.parseArray(map.get("entity").toString(), JbxxModelDO.class);
      JbxxModelDO jbxxModelDO = jbxx.get(0);
      if (jbxx.size() != 0) {
          if (!StringUtils.isNullOrEmpty(jbxxModelDO.getTaskid())) {
              List<TjdjModel> tjdj = JSONArray.parseArray(map.get("entity").toString(), TjdjModel.class);
              TjdjModel model=tjdj.get(0);
              model.setJsbh(jsbh);
              model.setUpdator(jbxxModelDO.getXm());
              model.setUpdatetime(new Date());
              model.setRybh(jbxxModelDO.getRybh());
              System.err.println("================张延=============");
              System.err.println(JSONUtil.toJson(model));
              System.err.println(model.getYwlcid());
              System.err.println("============================");

              ResponseMessage<String> result = kssServerApis.addTjdjFlow(jbxxModelDO.getTaskid(), model);

              if (result.getStatus() == 200) {
                  result.setMessage("查询成功");
                  if (result.getResult() == null) {
                      result.setMessage("未查询数据");
                  }
              }
              return result;
             } else {
              return ResponseMessage.error("taskid未传");
                }
              } else {
          return ResponseMessage.error("参数未传递完整");
          }
       }catch (Exception e) {
      e.printStackTrace();
      return ResponseMessage.error("查询失败！");
  }
    }


    /**
     * @api {post} /v4/kss/tj/tjhsaqjcAdd  题解流程提人登记和安全检查公用方法
     * @apiVersion 0.4.0
     * @apiName tjhsaqjcAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 题解流程提人登记和安全检查公用方法
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
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
     *    "entity":[{
     *      "blsj":"办理时间(格式:yyyy-MM-dd hh:mm:ss)"
     *  	"createtime":"创建时间(格式:yyyy-MM-dd hh:mm:ss)",
     * 	    "creator":"创建人(必填；最大字段长度：50)",
     * 	    "dlmj":"代理民警(必填；最大字段长度：50)",
     *   	"rybh":"人员编号(必填；最大字段长度：23)",
     *  	"ywlcid":"业务流程ID(必填；最大字段长度：21)",
     *      "ywqxyc":"有无异常(必填；字典：YWYC；最大字段长度：1)",
     *      "bz":"备注(最大字段长度：255)",
     *  	"id": "id(必填；最大字段长度：23)",
     *      "jcjg":"检查结果，1：正常，2：异常(必填；最大字段长度：1)",
     *      "jcry":"检查人员(必填；最大字段长度：100)",
     *      "jcsj":"检查时间(格式:yyyy-MM-dd hh:mm:ss)",
     *  	"tjjssj":"提解结束时间(格式:yyyy-MM-dd hh:mm:ss)",
     *  	"updatetime":"跟新时间(格式:yyyy-MM-dd hh:mm:ss)",
     *  	"updator":"跟新人(必填；最大字段长度：50)",
     *  	"ycqkdj":"异常情况登记(最大字段长度：250)",
     *      "taskid":"任务id"
     *     }]
     *   }
     *
     *
     */

    //  {"blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","createtime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,50}","dlmj":".{1,50}","rybh":"\\d{1,21}","ywlcid":".{1,21}","ywqxyc":"\\d{1,1}","bz":".{0,255}","id":"\\d{1,23}","jcjg":".{1,1}","jcry":".{1,100}","jcsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","tjjssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","updatetime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","updator":".{1,50}","ycqkdj":".{0,255}"}

    @ApiOperation("题解流程提人登记和安全检查公用方法")
    @PostMapping("/tjhsaqjcAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> tjhsaqjcAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/tj/tjhsaqjcAdd";

        //通过校验获取查询参数
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                System.out.println("----------------" + interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }


            List<CrjjcModelDO> crjjclist = JSONArray.parseArray(map.get("entity").toString(), CrjjcModelDO.class);
            CrjjcModelDO model = crjjclist.get(0);
            List<TjdjModel> tjdjlist = JSONArray.parseArray(map.get("entity").toString(), TjdjModel.class);
            TjdjModel tjdjModel = tjdjlist.get(0);


            Kss_TjAndCrjjcModel tjAndCrjjcModel = new Kss_TjAndCrjjcModel();

            List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            Map<String, Object> entityMap = mapsList.get(0);
            String taskid = entityMap.get("taskid").toString();
            String flag = "2";
            model.setJsbh(jsbh);
            model.setCreator(model.getJbxxXm());
            model.setCreatetime(new Date());
            model.setYwqxyc(tjdjModel.getJcjg());
            model.setQxycqk(tjdjModel.getYcqkdj());
            if (taskid == null || "".equals(taskid)) {
                return ResponseMessage.error(Result.ERR_SAVE, "任务ID:taskid必传");
            }
            System.err.println("taskid:" + taskid);
            System.err.println("监区提人登记表单：" + JSONUtil.toJson(model));
            System.err.println("=======================================");


            tjdjModel.setJcry(model.getDlmj());
            tjdjModel.setJcsj(model.getBlsj());
            tjdjModel.setTjjssj(tjdjModel.getTjjssj());
            tjdjModel.setUpdator(model.getDlmj());
            tjdjModel.setUpdatetime(model.getBlsj());

            tjAndCrjjcModel.setCrjjcModel(model);
            tjAndCrjjcModel.setTjdjModel(tjdjModel);

            System.err.println("监区提人登记：" + JSONObject.toJSONString(tjAndCrjjcModel));
            ResponseMessage<String> result = kssServerApis.addTjCrjFlow(flag, taskid, tjAndCrjjcModel);
            if (result.getStatus() == 200) {
                result.setMessage("查询成功");
                if (result.getResult() == null) {
                    result.setMessage("未查询数据");
                }
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }

    /**
     * @api {get} /v4/kss/tj/jbxxForTj   提解基本信息查询
     * @apiVersion 0.4.0
     * @apiName jbxxForTj
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription    提解基本信息查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}creator          				                人员编号
     * @apiSuccess {String}tjry1                                            提解人员1
     * @apiSuccess {String}csrq                                             出生日期
     * @apiSuccess {String}tjry2                                            提解人员2
     * @apiSuccess {String}jcjg                                             检查时间
     * @apiSuccess {String}stateString                                      状态(已转换)
     * @apiSuccess {String}ycqkdj                                           异常情况登记
     * @apiSuccess {String}ywlcid                                           业务流程ID
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}bz                                               备注
     * @apiSuccess {String}updator                                          创建人
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}tjjssjString                                     提解结束时间
     * @apiSuccess {String}state                                            状态(数字字典:ywstate)
     * @apiSuccess {String}qttjry                                           其他提解人员
     * @apiSuccess {String}tjkssj                                           提解开始时间
     * @apiSuccess {String}lfrxm                                            来访人姓名
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}snbh                                             人员所内编号
     * @apiSuccess {String}tjjtyy                                           提解具体原因
     * @apiSuccess {String}lfrzjh                                           来访人证件号
     * @apiSuccess {String}tjyy                                             提解原因
     * @apiSuccess {String}jcsj                                             检查时间
     * @apiSuccess {String}lxdh                                             联系电话
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}lfsj                                             来访时间
     * @apiSuccess {String}pzr                                              批准人
     * @apiSuccess {String}xb                                               性别(数字字典:xb)
     * @apiSuccess {String}tjkh1                                            提解卡号1
     * @apiSuccess {String}tjkh2                                            提解卡号2
     * @apiSuccess {String}tjsjString                                       提解时间
     * @apiSuccess {String}tjjssj                                           提解结束时间
     * @apiSuccess {String}jcry                                             检查人员
     * @apiSuccess {String}xbString                                         性别(已转换)
     * @apiSuccess {String}tjkssjSring                                      提解开始时间
     * @apiSuccess {String}xm                                               姓名
     * @apiSuccess {String}ayString                                         主要案由(已转换)
     * @apiSuccess {String}ay                                               主要案由(数字字典:ajlb)
     * @apiSuccess {String}lfrpzh                                           来访人凭证文书号
     * @apiSuccess {String}tjsj                                             提解时间
     * @apiSuccess {String}updatetime                                       更新时间
     * @apiSuccess {String}zbmj                                             值班民警
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}tjdw                                             提解单位
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
     *
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 61,
     *     "data": [
     *       {
     *         "tjry1": "121",
     *         "csrq": 728496000000,
     *         "tjry2": "1212",
     *         "jcjg": "null",
     *         "ycqkdj": "null",
     *         "ywlcid": "null",
     *         "tjjssjString": "null",
     *         "qttjry": "11212",
     *         "tjkssjSring": "null",
     *         "tjkssj": 1569570907000,
     *         "lfrxm": "513436200009261563",
     *         "snbh": "20190071",
     *         "bz": "null",
     *         "tjjtyy": "12",
     *         "id": "11000011420190926000043",
     *         "state": "R2",
     *         "lfrzjh": "513436200009261563",
     *         "tjyy": "1",
     *         "jcsj": "null",
     *         "lxdh": "15651672500",
     *         "creator": "管理员",
     *         "createtime": 1569427200000,
     *         "lfsj": 1569465480000,
     *         "pzr": "12",
     *         "xb": "1",
     *         "tjkh1": "12",
     *         "tjkh2": "2",
     *         "tjsjString": "2019-09-26 10:39:25",
     *         "tjjssj": "null",
     *         "jcry": "null",
     *         "xbString": "男性",
     *         "xm": "测试4号",
     *         "ayString": "策动武装暴乱案",
     *         "ay": "010150",
     *         "rybh": "110000111201907150008",
     *         "lfrpzh": "513436200009261563",
     *         "updator": "管理员",
     *         "tjsj": 1569465565000,
     *         "updatetime": 1569570907000,
     *         "zbmj": "管理员",
     *         "jsh": "0102",
     *         "jsbh": "110000114",
     *         "tjdw": "北京市公安局"
     *       },
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1578640551496
     * }


     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * 	  json:{
     *             "jbxx_xm":"姓名"
     *             "jbxx_jsh":"监室号"
     *             "jbxx_xb":"性别"
     *             "blrqstart":"提解时间开始"
     *             "blrqend"提解时间结束"
     *             "rybh":"人员编号"
     *         }
     *
     *
     */

    @ApiOperation("提解基本信息查询")
    @GetMapping("/jbxxForTj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> jbxxForTj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/tj/jbxxForTj";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xm"))) {
                param.and("jbxx_xm",TermType.like,"%"+maps.getResult().get("jbxx_xm").toString()+"%");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_jsh"))) {
                param.and("jbxx_jsh",TermType.like,"%"+maps.getResult().get("jbxx_jsh").toString()+"%");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))) {
                param.and("jbxx_xb",TermType.eq,maps.getResult().get("jbxx_xb").toString());
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqstart"))) {
                param.and("tjsj", TermType.gt, maps.getResult().get("blrqstart").toString()+" 00:00:00");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
                param.and("tjsj", TermType.lt, maps.getResult().get("blrqend").toString()+" 23:59:59");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh").toString());
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String,Object>>> result = kssServerApis.jbxxForTj(param);
            System.err.println("result" + JSON.toJSONString(result));
            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));

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
}





