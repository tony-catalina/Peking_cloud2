package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.JjwpsljlModel;
import awd.bj.kss.model.XjjlModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.JjwpModal;
import awd.cloud.platform.model.kss.Kss_JbxxModelDO;
import awd.cloud.platform.model.kss.Kss_SykzModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
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
@RequestMapping("/v4/kss/jbxx")
@Api(tags = "kss-jbxx", description = "jbxx")
public class Kss_JbxxController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private KssAnalyseApis kssAnalyseApis;

    /**
     * @api {post} /v4/kss/jbxx/setWcshf 误出所恢复
     * @apiVersion 0.4.0
     * @apiName setWcshf
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 误出所恢复.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{"entity":[{
     * "rybh":"人员编号(必填; 最大字段长度：21)",
     * "csyy":"出所原因(必填; 字典：CSYY ; 最大字段长度：2)",
     * "id":"ID(必填; 最大字段长度：23)"
     * }]
     * }
     */
    @ApiOperation("误出所恢复")
    @PostMapping("setWcshf")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveAsShdj(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/jbxx/setWcshf";

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

            List<JbxxModel> jbxxList = JSONArray.parseArray(map.get("entity").toString(), JbxxModel.class);

            jbxxList.get(0).setJsbh(jsbh);
            jbxxList.get(0).setUpdatetime(new Date());
            jbxxList.get(0).setState("R2");

            JbxxModel jbxxEntity = jbxxList.get(0);

            ResponseMessage<String> result = kssServerApis.wcshfSave(jbxxEntity);

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
     * @api {post} /v4/kss/jbxx/jjwpSave 救济物品保存
     * @apiVersion 0.4.0
     * @apiName jjwpSave
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 救济物品保存
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
     *              "rybh":"人员编号(必填; 最大长度:21)",
     * 	            "xm": "人员姓名(必填; 最大长度:50)",
     * 	            "rsrq": "(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 	            "jjrq": "(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 	            "djr": "登记人(必填; 最大长度:100)",
     * 	            "djsj": "登记时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 	            "jsh":"监室号(必填; 最大长度:4)",
     * 	            "snbh": "所内编号(必填; 最大长度:8)"
     *           }
     *        ]
     *      }
     * }
     *
     */
    @ApiOperation("救济物品保存")
    @PostMapping("/jjwpSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jjwpSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/jbxx/jjwpSave";
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
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            String taskid = map.get("taskid").toString();
            JjwpsljlModel jjwpsljlModel = JSONArray.parseArray(map.get("entity").toString(), JjwpsljlModel.class).get(0);
            JbxxModel jbxxModel = JSONArray.parseArray(map.get("entity").toString(), JbxxModel.class).get(0);
            System.err.println("jjwpsljlModel--" + JSON.toJSONString(jjwpsljlModel));
            System.err.println("jbxxModel--" + JSON.toJSONString(jbxxModel));
            jjwpsljlModel.setState("R2");
            jjwpsljlModel.setCreator(jjwpsljlModel.getDjr());
            jjwpsljlModel.setJsbh(jsbh);
            jjwpsljlModel.setPastable("1");
            JjwpModal jjwpModal = new JjwpModal();
            jjwpModal.setJbxxEntity(jbxxModel);
            jjwpModal.setJjwpsljlModel(jjwpsljlModel);
            System.err.println(JSONUtil.toJson(jjwpModal));
            ResponseMessage<String> responseMessage = kssServerApis.jjwpSave(taskid, jjwpModal);
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/kss/jbxx/jbxxlist 基本信息查询
     * @apiVersion 0.4.0
     * @apiName jbxxlist
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 基本信息查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}xbString          				                性别(已转换)
     * @apiSuccess {String}snbh         				                    所内编号
     * @apiSuccess {String}xm          				                        人员姓名
     * @apiSuccess {String}rybh          				                    人员编号
     * @apiSuccess {String}xb          				                        性别
     * @apiSuccess {String}jsbh          				                    监所编号
     * @apiSuccess {String}jsh          				                    监室号
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
     *       {
     *         "xbString": "女性",
     *         "snbh": "20190047",
     *         "xm": "发射点",
     *         "rybh": "110000111201907120003",
     *         "xb": "2",
     *         "jsbh": "110000114",
     *         "jsh": "0101"
     *       }
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
     *    json:{
     *          "zszt":"在所状态(字典:zszt)",
     *          "jsycbz":"精神异常标志(字典:shfo)",
     *          "rsrq":"入所日期(格式:yyyy-MM-dd)",
     *          "gyqx":"关押期限",
     *          "cssj":"出生时间(格式:yyyy-MM-dd)",
     *          "rybh":"人员编号(最大字段长度：21)",
     *          "xm":"姓名",
     *          "bm":"别名",
     *          "bahj":"办案环节(字典:bajd)",
     *          "jsh":"监室号",
     *          "ay":"主要案由(字典:ajlb)",
     *          "tabh":"同案编号",
     *          "badw":"办案单位",
     *          "zjh":"证件号",
     *          "dah":"档案编号",
     *          "snbh":"所内编号",
     *          "shid":"手环id",
     *          "rsrq_start":"入所开始时间(格式:yyyy-MM-dd)",
     *          "rsrq_end":"入所结束时间(格式:yyyy-MM-dd)",
     *          "cssj_start":"出所开始时间(格式:yyyy-MM-dd)",
     *          "cssj_end":"出所结束时间(格式:yyyy-MM-dd)",
     *          "gyqx_start":"关押期限开始时间(格式:yyyy-MM-dd)",
     *          "gyqx_end":"关押期限结束时间(格式:yyyy-MM-dd)",
     *          "csrq_start":"出生日期开始时间(格式:yyyy-MM-dd)",
     *          "csrq_end":"出生日期结束时间(格式:yyyy-MM-dd)",
     *          "jlrq_start":"拘留日期开始时间(格式:yyyy-MM-dd)",
     *          "jlrq_end":"拘留日期结束时间(格式:yyyy-MM-dd)",
     *          "caaj":"从案类型(字典:ajlb)"
     *        }
     *
     */
    @OpenAPI
    @ApiOperation("基本信息查询")
    @PostMapping("/jbxxlist")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jbxxList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/jbxx/jbxxlist";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam qParam = new QueryParam();

            if (!StringUtils.isNullOrEmpty(maps.getResult().get("zszt"))) {
                qParam.and("zszt", TermType.in, maps.getResult().get("zszt"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsycbz"))) {
                qParam.and("jsycbz", TermType.eq, maps.getResult().get("jsycbz"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsycbz"))) {
                qParam.and("jsycbz", TermType.not, maps.getResult().get("jsycbz"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq"))) {
                qParam.and("rsrq", TermType.gte, maps.getResult().get("rsrq"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq"))) {
                qParam.and("rsrq", TermType.lte, maps.getResult().get("rsrq") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx"))) {
                qParam.and("gyqx", TermType.gte, maps.getResult().get("gyqx"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx"))) {
                qParam.and("gyqx", TermType.lte, maps.getResult().get("gyqx") + " 23:59:59");
            }
            //查询今日入所的特定条件
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("cssj"))) {
                qParam.and("cssj", TermType.gte, maps.getResult().get("cssj") + " 00:00:00");
                qParam.and("cssj", TermType.lte, maps.getResult().get("cssj") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                qParam.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
                String xmpx = Pinyin4j.cn2Pinyin(maps.getResult().get("xm").toString());
                if ("0".equals(maps.getResult().get("xmpy"))) {
                    qParam.and("xmpy", TermType.like, "%" + xmpx + "%");
                } else {
                    qParam.and("xm", TermType.like, "%" + maps.getResult().get("xm") + "%");
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bm"))) {
                String bmpx = Pinyin4j.cn2Pinyin(maps.getResult().get("bm").toString());
                if ("0".equals(maps.getResult().get("bm_type"))) {
                    qParam.and("bmty", TermType.like, "%" + bmpx + "%");
                } else {
                    qParam.and("bm", TermType.like, "%" + maps.getResult().get("bm") + "%");
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bahj")) && !StringUtils.isNullOrEmpty(maps.getResult().get("bahj_type"))) {
                if ("0".equals(maps.getResult().get("bahj_type"))) {
                    qParam.and("bahj", TermType.eq, maps.getResult().get("bahj"));
                } else {
                    qParam.and("bahj", TermType.not, maps.getResult().get("bahj"));
                }
            } else if (!StringUtils.isNullOrEmpty(maps.getResult().get("bahj"))) {
                qParam.and("bahj", TermType.eq, maps.getResult().get("bahj"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh")) && !StringUtils.isNullOrEmpty(maps.getResult().get("jsh_type"))) {
                if ("0".equals(maps.getResult().get("jsh_type"))) {
                    qParam.and("jsh", TermType.eq, maps.getResult().get("jsh"));
                } else {
                    qParam.and("jsh", TermType.not, maps.getResult().get("jsh"));
                }
            } else if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                qParam.and("jsh", TermType.like, maps.getResult().get("jsh") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("ay")) && !StringUtils.isNullOrEmpty(maps.getResult().get("ay_type"))) {
                if ("0".equals(maps.getResult().get("ay_type"))) {
                    qParam.and("ay", TermType.eq, maps.getResult().get("ay"));
                } else {
                    qParam.and("ay", TermType.not, maps.getResult().get("ay"));
                }
            } else if (!StringUtils.isNullOrEmpty(maps.getResult().get("ay"))) {
                qParam.and("ay", TermType.eq, maps.getResult().get("ay"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("tabh"))) {
                if ("0".equals(maps.getResult().get("jsh_type"))) {
                    qParam.and("tabh", TermType.eq, maps.getResult().get("tabh"));
                } else {
                    qParam.and("tabh", TermType.not, maps.getResult().get("tabh"));
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xb")) && !StringUtils.isNullOrEmpty(maps.getResult().get("xb_type"))) {
                if ("0".equals(maps.getResult().get("xb_type"))) {
                    qParam.and("xb", TermType.eq,maps.getResult().get("xb"));
                } else {
                    qParam.and("xb", TermType.not, maps.getResult().get("xb"));
                }
            } else if (!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
                qParam.and("xb", TermType.eq, maps.getResult().get("xb"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("badw")) && !StringUtils.isNullOrEmpty(maps.getResult().get("badw_type"))) {
                if ("0".equals(maps.getResult().get("badw_type"))) {
                    qParam.and("badw", TermType.eq, maps.getResult().get("badw"));
                } else {
                    qParam.and("badw", TermType.not, maps.getResult().get("badw"));
                }
            } else if (!StringUtils.isNullOrEmpty(maps.getResult().get("badw"))) {
                qParam.and("badw", TermType.eq, maps.getResult().get("badw"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh")) && !StringUtils.isNullOrEmpty(maps.getResult().get("zjh_type"))) {
                if ("0".equals(maps.getResult().get("zjh_type"))) {
                    qParam.and("zjh", TermType.eq, maps.getResult().get("zjh"));
                } else {
                    qParam.and("zjh", TermType.not, maps.getResult().get("zjh"));
                }
            } else if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))) {
                qParam.and("zjh", TermType.like, maps.getResult().get("zjh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("dah")) && !StringUtils.isNullOrEmpty(maps.getResult().get("dah_type"))) {
                if ("0".equals(maps.getResult().get("dah_type"))) {
                    qParam.and("dah", TermType.eq, maps.getResult().get("dah"));
                } else {
                    qParam.and("dah", TermType.not, maps.getResult().get("dah"));
                }
            } else if (!StringUtils.isNullOrEmpty(maps.getResult().get("dah"))) {
                qParam.and("dah", TermType.eq, maps.getResult().get("dah"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("snbh")) && !StringUtils.isNullOrEmpty(maps.getResult().get("snbh_type"))) {
                if ("0".equals(maps.getResult().get("snbh_type"))) {
                    qParam.and("snbh", TermType.like, "%" + maps.getResult().get("snbh") + "%");
                } else {
                    qParam.and("snbh", TermType.like, "%" + maps.getResult().get("snbh") + "%");
                }
            } else if (!StringUtils.isNullOrEmpty(maps.getResult().get("snbh"))) {
                qParam.and("snbh", TermType.like, "%" + maps.getResult().get("snbh") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("shid"))) {
                qParam.and("shid", TermType.eq, maps.getResult().get("shid"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_start"))) {
                qParam.and("rsrq", TermType.gte, maps.getResult().get("rsrq_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end"))) {
                qParam.and("rsrq", TermType.lte, maps.getResult().get("rsrq_end") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("cssj_start"))) {
                qParam.and("cssj", TermType.gte, maps.getResult().get("cssj_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("cssj_end"))) {
                qParam.and("cssj", TermType.lte, maps.getResult().get("cssj_end") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_start"))) {
                qParam.and("gyqx", TermType.gte, maps.getResult().get("gyqx_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_end"))) {
                qParam.and("gyqx", TermType.lte, maps.getResult().get("gyqx_end") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_start"))) {
                qParam.and("csrq", TermType.gte, maps.getResult().get("csrq_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_end"))) {
                qParam.and("csrq", TermType.lte, maps.getResult().get("csrq_end") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jlrq_start"))) {
                qParam.and("jlrq", TermType.gte, maps.getResult().get("jlrq_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jlrq_end"))) {
                qParam.and("jlrq", TermType.lte, maps.getResult().get("jlrq_end") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("caaj"))) {
                qParam.and("caaj", maps.getResult().get("caaj"));
            }
            if (state == null || "".equals(state)) {
                state = "R8";
            } else {
                //判断是否在7日内出所
                if (!StringUtils.isNullOrEmpty(maps.getResult().get("startTime")) && !StringUtils.isNullOrEmpty(maps.getResult().get("endTime"))) {
                    System.err.println("7日查询");
                    if (state.equals("R7")) {
                        qParam.and("cssj", TermType.gte, maps.getResult().get("startTime") + " 00:00:00");
                        qParam.and("cssj", TermType.lte, maps.getResult().get("endTime") + " 23:59:59");
                    }
                    //判断是否在7日内入所
                    if (state.equals("R8")) {
                        qParam.and("rsrq", TermType.gte, maps.getResult().get("startTime") + " 00:00:00");
                        qParam.and("rsrq", TermType.lte, maps.getResult().get("endTime") + " 23:59:59");
                    }
                }

            }
            DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
            System.err.println("jbxx查询" + JSONUtil.toJson(qParam));
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.queryForPage(qParam);
            System.err.println("--result--" + JSON.toJSONString(result));

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



    /**
     * @api {get} /v4/kss/jbxx/syrsYwdt 收押入所业务动态查询
     * @apiVersion 0.4.0
     * @apiName syrsYwdt
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 收押入所业务动态查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}hjd          				                     户籍地
     * @apiSuccess {String}bs         				                         本省
     * @apiSuccess {String}wj          				                         外籍
     * @apiSuccess {String}ws          				                         外省
     * @apiSuccess {String}whcd          				                     文化程度
     * @apiSuccess {String}xx          				                         小学
     * @apiSuccess {String}gz          				                         高中
     * @apiSuccess {String}wm          				                         文盲
     * @apiSuccess {String}cz          				                         初中
     * @apiSuccess {String}dzys          				                     大专以上
     * @apiSuccess {String}rsxz                                              入所性质
     * @apiSuccess {String}fjbrs          				                     非拘捕入所
     * @apiSuccess {String}rsqt                                              日所其他
     * @apiSuccess {String}zcrs          				                     正常入所
     * @apiSuccess {String}sh          				                         收回
     * @apiSuccess {String}tsws          				                     投送未收
     * @apiSuccess {String}rszrs                                             入所总人数
     * @apiSuccess {String}js                                                拒收
     * @apiSuccess {String}jszrs                                             拒收总人数
     * @apiSuccess {String}xb          				                         性别
     * @apiSuccess {String}nv          				                         女
     * @apiSuccess {String}nan          				                     男
     * @apiSuccess {String}zyzrs          				                     在押总人数

     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *    "data": [
     *       {
     *         "result": {
     *           "hjd": [
     *             {
     *               "bs": 19,
     *               "wj": 18,
     *               "ws": 19
     *             }
     *           ],
     *           "whcd": [
     *             {
     *               "xx": 0,
     *               "gz": 0,
     *               "wm": 14,
     *               "cz": 16,
     *               "dzys": 26
     *             }
     *           ],
     *           "rsxz": [
     *             {
     *               "fjbrs": 1,
     *               "rsqt": 1,
     *               "zcrs": 53,
     *               "sh": 0,
     *               "tsws": 0
     *             }
     *           ],
     *           "rszrs": [
     *             {
     *               "rszrs": 237
     *             }
     *           ],
     *           "js": [
     *             {
     *               "jszrs": 37
     *             }
     *           ],
     *           "xb": [
     *             {
     *               "nv": 8,
     *               "nan": 46
     *             }
     *           ],
     *           "zyzrs": [
     *             {
     *               "zyzrs": 56
     *             }
     *           ]
     *         }
     *       }
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
     *          "starttime":"开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *          "endtime":"结束时间(格式:yyyy-MM-dd hh:mm:ss)"
     *        }
     *
     */
    @ApiOperation("收押入所业务动态查询")
    @GetMapping("/syrsYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> syrsYwdt(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/jbxx/syrsYwdt";

        String state = request.getParameter("state");

        //通过校验获取查询参数
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

            String starttime = (String) maps.getResult().get("starttime");
            String endtime = (String) maps.getResult().get("endtime");

            if (!StringUtils.isNullOrEmpty(starttime)) {
                param.and("starttime", TermType.eq, starttime);
            }
            if (!StringUtils.isNullOrEmpty(endtime)) {
                param.and("endtime", TermType.eq, endtime);
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<Map<String, Object>> result = kssAnalyseApis.syrsYwdt(jsbh, starttime, endtime);

            System.err.println("result" + JSON.toJSONString(result));

               List lists=new ArrayList<>();
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
     * @api {get} /v4/kss/jbxx/jbxxListForYwtz 收押入所业务台账查询
     * @apiVersion 0.4.0
     * @apiName jbxxListForYwtz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 收押入所业务台账查询.
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}xm          				                         姓名
     * @apiSuccess {String}rybh          				                     人员编号
     * @apiSuccess {String}gj          				                         国籍
     * @apiSuccess {String}gjString         				                 国籍（转换后）
     * @apiSuccess {String}xb          				                         性别
     * @apiSuccess {String}xbString          				                 性别(转换后)
     * @apiSuccess {String}csrq          				                     出生日期
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *        "xbString": "男性",
     *         "csrq": "1989-02-01 00:00:00",
     *         "gj": "156",
     *         "gjString": "中国",
     *         "xm": "自行车",
     *         "rybh": "110000111201907120012",
     *         "xb": "1"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576826568061
     * }
     * ],
     * "page": "1"
     * },
     * "status": 200,
     * "timestamp": 1576826568061
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * "rybh":"人员编号(最大字段长度：21)",
     * "bm":"别名(最大字段长度：21)",
     * "xm":"姓名(最大字段长度：50)",
     * "xb":"性别(字典：XB；最大字段长度：1)",
     * "blrqstart":"办理开始日期(格式：yyyy-MM-dd hh:mm:ss)",
     * "blrqend":"办理结束日期(格式：yyyy-MM-dd hh:mm:ss)",
     * "csrq_start":"出所日期开始(格式：yyyy-MM-dd hh:mm:ss)",
     * "csrq_end":"出所日期结束(格式：yyyy-MM-dd hh:mm:ss)"
     * }
     */
    @ApiOperation("收押入所业务台账查询")
    @GetMapping("/jbxxListForYwtz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> jbxxListForYwtz(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/jbxx/jbxxListForYwtz";

        String state = "R8";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数

            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bm"))) {
                param.and("bm", TermType.like, maps.getResult().get("bm"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
                param.and("xb", TermType.eq, maps.getResult().get("xb"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
                param.and("xm", TermType.like, maps.getResult().get("xm"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrqstart"))) {
                param.and("rsrq_start", TermType.gt, maps.getResult().get("blrqstart"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
                param.and("rsrq_end", TermType.lt, maps.getResult().get("blrqend"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_start"))) {
                param.and("csrq_start", TermType.gt, maps.getResult().get("csrq_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_end"))) {
                param.and("csrq_end", TermType.lt, maps.getResult().get("csrq_end"));
            }
            param.and("state", TermType.in, "R7,R8");

            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.queryForPage(param);

            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
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
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/kss/jbxx/getSykz 收押开证保存
     * @apiVersion 0.4.0
     * @apiName getSykz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 收押开证保存.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

     *
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{"entity":[{
     *                     "id":"id(必填;最大长度:23)",
     *                     "gcbh":"过程编号(必填;最大长度:30)",
     *                     "wbrybh":"网办人员编号(必填;最大长度:30)",
     *                     "xm":"姓名(必填;最大长度:30)",
     *                     "xmpy":"姓名拼音(必填;最大长度:100)",
     *                     "xb":"性别(必填;最大长度:1)",
     *                     "gj":"国籍(必填；最大长度:3)",
     *                     "whcd":"文化程度(必填；最大长度:2)",
     *                     "sf":"身份(必填；最大长度:2)",
     *                     "mz":"民族(必填；最大长度:2)",
     *                     "dah":"档案编号(必填；最大长度:20)",
     *                     "zjlx":"证件类型(必填；最大长度:30)",
     *                     "zjh":"证件号(必填；最大长度:18)",
     *                     "zzmm":"政治面貌(必填；最大长度:2)",
     *                     "zuc":"足长(必填;最大长度:6)",
     *                     "sg":"身高(必填;最大长度:5)",
     *                     "bhlx":"病号类型(必填;最大长度:1)",
     *                     "jkzk":"健康情况(必填;最大长度:2)",
     *                     "csrq":"出生日期(必填；格式:yyyy-MM-dd hh:mm:ss)(必填;最大长度:30)",
     *                     "hyzk":"婚姻状况(必填；最大长度:1)",
     *                     "tssf":"特殊身份(必填;最大长度:10)",
     *                     "sypz":"收押凭证(必填；最大长度:1)",
     *                     "sypzwsh":"收押凭证文书号(必填；最大长度:40)",
     *                     "rsxz":"入所原因(必填；最大长度:2)",
     *                     "jyrq":"羁押日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *                     "jlrq":"扣留日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *                     "dbrq":"逮捕日期(格式:yyyy-MM-dd hh:mm:ss)",
     *                     "dwlx":"办案单位类型(必填;最大长度:1)",
     *                     "badw":"办案单位(必填;最大长度:60)",
     *                     "jsly":"拒收理由(必填;最大长度:100)",
     *                     "bahj":"办案环节(必填;最大长度:2)",
     *                     "sydw":"送押单位(必填;最大长度:60)",
     *                     "syr":"送押人(必填；最大长度:30)",
     *                     "rsrq":"入所时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                     "gyqx":"关押期限(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *                     "jsh":"监室号(必填；最大长度:4)",
     *                     "ay":"主要案由(必填；最大长度:34)",
     *                     "jyaq":"简要案情(必填；最大长度:255)"
     *                }]
     *         }
     *
     *
     */

    @ApiOperation("收押开证保存")
    @PostMapping("/getSykz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> getSykz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/jbxx/getSykz";

        //通过校验获取查询参数
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            String taskid=(String) rslist.get(0).get("taskid");
            System.err.println("taskid--"+taskid);
            if (StringUtils.isNullOrEmpty(taskid)){
                return ResponseMessage.error("taskid不能为空！");
            }
            System.err.println("map--"+map.get("entity").toString());
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<Kss_JbxxModelDO> jbxxModels = JSONArray.parseArray(map.get("entity").toString(), Kss_JbxxModelDO.class);
            System.err.println("jbxxModels--"+ JSON.toJSONString(jbxxModels.get(0)));
            jbxxModels.get(0).setCreatetime(new Date());
            jbxxModels.get(0).setState("R8");
            jbxxModels.get(0).setJsbh(jsbh);
            jbxxModels.get(0).setCreator("管理者");
            jbxxModels.get(0).setUpdator("管理者");
            Kss_JbxxModelDO jbxxModel=jbxxModels.get(0);
            Kss_SykzModel sykzModel=new Kss_SykzModel();
            sykzModel.setJbxxEntity(jbxxModel);
            System.err.println("jbxxModel--"+JSON.toJSONString(jbxxModel));
            ResponseMessage<String> result = kssServerApis.getSykz(taskid,sykzModel);
            System.err.println("result--"+result.getResult());
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

    /**
     * @return
     * @api {get} /v4/kss/jbxx/getJsXqList 监室详情查询
     * @apiVersion 0.4.0
     * @apiName getJsXqList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 监室详情查询
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}rs          				                        人数
     * @apiSuccess {String}ayrs                                             案由人数
     * @apiSuccess {String}hjdrs                                            户籍地人数
     * @apiSuccess {String}jsh                                              监室号
     *
     *
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
     * "data": [{
     *            "rs":12,
     *           "ayrs":0,
     *           "hjdrs":0,
     *           "jsh":"0101",
     *           }]
     * "page": "1"
     * },
     * "status": 200,
     * "timestamp": 1576826568061
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *              "hjs":"会见室(最大字段长度：10)",
     *              "jsh":"监室号(最大字段长度：4)",
     *              "ay":"主要案由(字典：AJLB； 最大字段长度：34)",
     *              "type":"类型",
     *      }
     */
    @OpenAPI
    @ApiOperation("监室详情查询")
    @GetMapping("/getJsXqList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> getJsXqList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/jbxx/getJsXqList";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数

             Map<String, Object> map = new HashMap<String, Object>();

            if (!StringUtils.isNullOrEmpty(jsbh)) {
                map.put("jsbh", jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("hjd"))) {
                map.put("hjd", maps.getResult().get("hjd"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                map.put("jsh", maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("ay"))) {
                map.put("ay", maps.getResult().get("ay"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("type"))) {
                map.put("type", maps.getResult().get("type"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                map.put("jsh", "%"+maps.getResult().get("jsh")+"%");
            }

            ResponseMessage<List<Map<String,Object>>> result = kssServerApis.getJsXqList(map);

            System.err.println("result" + JSON.toJSONString(result));



            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult());
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
     * @api {get} /v4/kss/jbxx/getLsjyJtqk 历史羁押具体情况查询
     * @apiVersion 0.4.0
     * @apiName getLsjyJtqk
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 历史羁押具体情况查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}xmmc          				                    项目名称
     * @apiSuccess {String}num          				                    数量
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 5,
     *     "data": [
     *       {
     *         "xmmc": "耳目",
     *         "num": 1
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1579252297670
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "zjh":"证件号(必填)",
     * }
     *
     */
    @ApiOperation("历史羁押具体情况查询")
    @GetMapping("/getLsjyJtqk")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> getLsjyJtqk(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/kss/jbxx/getLsjyJtqk";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if(StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))){
                return ResponseMessage.error("zjh不可为空");
            }
            String zjh = maps.getResult().get("zjh").toString();

            ResponseMessage<List<Map<String, Object>>> result = kssServerApis.findLsjyJtqk(zjh, jsbh);
            System.err.println("--result--" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult());
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
     * @api {post} /v4/kss/jbxx/ryxxUpdate 人员信息修改
     * @apiVersion 0.4.0
     * @apiName ryxxUpdate
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 人员信息修改
     *
     * @apiParam {String} appcode 						    应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			    成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			    时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "修改成功!",
     *   "result": "修改成功",
     *   "status": 200,
     *   "timestamp": 1578886392184
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     *   "entity":[
     *     {
     *      "id":"id(必填; 最大长度:23)",
     *      "xm":"姓名(必填; 最大长度:50)",
     *		"bm": "别名",
     *	    "zjlx": "证件类型(必填; 最大长度:2)",
     *	    "zjh: "证件号(必填; 最大长度:50)",
     *      "xb": "性别(必填; 最大长度:1)",
     *      "csrq": "出生日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *      "mz": "民族(必填; 最大长度:2)",
     *      "gj": "国籍(必填; 最大长度:3)",
     *      "hyzk": "婚姻状况",
     *      "jg": "籍贯(必填; 最大长度:6)",
     *      "hjd": "户籍地(必填; 最大长度:6)",
     *      "hjdxz": "户籍地详址",
     *      "xzd": "现住地(必填; 最大长度:6)",
     *      "xzdxz": "现住地详址",
     *      "zzqsrq": "暂住起始日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *      "whcd": "文化程度(必填;最大长度:2)"
     *      "gzdw":"原工作单位",
     *      "zc": "专长",
     *      "azb": "是否有艾滋病",
     *      "sf": "身份(必填;最大长度:2)"
     *      "tssf": "特殊身份",
     *      "zzmm": "政治面貌(必填;最大长度:2)"
     *      "zy": "职业",
     *      "ay": "主要案由(必填;最大长度:34)",
     *      "xhay": "细化案由",
     *      "sypz": "收押凭证(必填;最大长度:2)",
     *      "caaj": "从案类型",
     *      "cylx": "成员类型",
     *      "rsxz": "入所性质(必填;最大长度:2)"
     *      "bahj": "办案环节(必填;最大长度:2)"
     *      "rsrq": "入所日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *      "jyrq": "羁押日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *      "gyqx": "关押日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *      "badw": "办案单位(必填;最大长度:60)",
     *      "dwlx": "办案单位类型(必填;最大长度:1)",
     *      "bar": "办案人",
     *      "bardh": "办案人电话",
     *      "syr": "收押人",
     *      "sydw": "送押单位（必填；最大长度:60）",
     *      "sy": "送押人",
     *      "zrdw": "转入单位",
     *      "zwbh": "指纹编号",
     *      "dah": "档案编号",
     *      "sypzwsh":  "收押凭证文书号（必填;最大长度:60）",
     *      "gcbh": "过程编号",
     *      "fzjl": "犯罪经历",
     *      "jyaq": "简要案情",
     *      "bz": "备注",
     *      "grjl": "个人简历"
     *     }
     *   ]
     * }
     *
     */
    //{"id":".{1,23}","xm":".{1,50}","zjlx":".{1,2}","zjh":".{1,50}","xb":".{1,1}","csrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","mz":".{1,2}","gj":".{1,3}","jg":".{1,6}","hjd":".{1,6}","xzd":".{1,6}","zzqsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","whcd":".{1,2}","sf":".{1,2}","zzmm":".{1,2}","ay":".{1,34}","sypz":".{1,2}","rsxz":".{1,2}","bahj":".{1,2}","rsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jyrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","gyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","badw":".{1,60}","dwlx":".{1,1}","sydw":".{1,60}","sypzwsh":".{1,60}"}
    @ApiOperation("人员信息修改")
    @PostMapping("ryxxUpdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ryxxUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
        String interfaceId = "/v4/kss/jbxx/ryxxUpdate";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<JbxxModel> list = JSONArray.parseArray(map.get("entity").toString(),JbxxModel.class);
            JbxxModel jbxxModel = list.get(0);
            jbxxModel.setState("R2");
            jbxxModel.setJsbh(jsbh);
            jbxxModel.setUpdatetime(new Date());

            ResponseMessage<String> result = kssServerApis.ryxxUpdate(jbxxModel.getId(),jbxxModel);
            if (result.getStatus() == 200){
                result.setMessage("修改成功！");
            } else {
                result.setMessage("服务异常，修改失败！");
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("修改失败！");
        }
    }
}
