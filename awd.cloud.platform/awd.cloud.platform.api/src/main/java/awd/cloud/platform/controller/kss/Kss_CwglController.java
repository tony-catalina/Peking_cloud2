package awd.cloud.platform.controller.kss;


import awd.bj.kss.model.*;
import awd.cloud.platform.api.KssServerApis;
import awd.bj.base.model.Variables;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.*;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping("/v4/kss/kss_cwgl")
@Api(tags = "kss-cwgl",description = "cwgl")
public class Kss_CwglController extends PublicService{

    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {post} /v4/kss/kss_cwgl/syFwdj 附物登记保存
     * @apiVersion 0.4.0
     * @apiName syFwdj
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 附物登记保存.
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
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "entity":[{
     *      "xm":" 姓名(必填; 最大字段长度：30)",
     *      "jsh":"监室号(必填; 最大字段长度：4)",
     *      "gcbh":"过程编号(必填;最大字段长度：30)",
     *      "sfwwp":"(必填;)"
     *   }]
     * }
     *
     */
    @ApiOperation("附物登记保存")
    @PostMapping("/syFwdj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> syFwdj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/kss_cwgl/syFwdj";

            //通过校验获取查询参数
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            Kss_FwdjModel fwdjModel=new Kss_FwdjModel();
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            map.put("interfaceId", interfaceId);
            String taskid=(String) rslist.get(0).get("taskid");
            String sfwwp=(String) rslist.get(0).get("sfwwp");
            Map<String, Object> rsmap = new HashMap<String, Object>();
            rsmap.put("sfwwp",sfwwp);
            System.err.println("taskid--"+taskid);
            if (StringUtils.isNullOrEmpty(taskid)){
                return ResponseMessage.error("taskid不能为空！");
            }
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            Variables variables = new Variables();
            String gcbh=(String) rslist.get(0).get("gcbh");
            String xm=(String) rslist.get(0).get("xm");
            String jsh=(String) rslist.get(0).get("jsh");
            variables.setJsbh(jsbh);
            Map<String, Object> params = Maps.newHashMap();
            if (!StringUtils.isNullOrEmpty(gcbh)){
                params.put("rybh", gcbh);
                variables.setRybh(gcbh);
            }
            if (!StringUtils.isNullOrEmpty(xm)){
                params.put("xm", xm);
            }
            if (!StringUtils.isNullOrEmpty(jsh)){
                params.put("jsh", jsh);
            }
            variables.setParams(params);
            ResponseMessage<String> result=new ResponseMessage<String>();
            List<Kss_WpjsModel> wpjsModels = JSONArray.parseArray(map.get("entity").toString(), Kss_WpjsModel.class);
            List<Kss_WpglModel> wpglModels = JSONArray.parseArray(map.get("entity").toString(), Kss_WpglModel.class);
            List<Kss_XjjsModel> xjjsModels = JSONArray.parseArray(map.get("entity").toString(), Kss_XjjsModel.class);
            Kss_WpjsModel wpjsModel=wpjsModels.get(0);
            Kss_WpglModel wpglModel=wpglModels.get(0);
            Kss_XjjsModel xjjsModel=xjjsModels.get(0);
            List<Kss_WpglModel> li = Lists.newArrayList();
            if ("1".equals(sfwwp)) {
                rsmap.put("variables", variables);
                rsmap.put("wpjs", wpjsModel);
                rsmap.put("xjjs", xjjsModel);
                rsmap.put("wpgl", li);
                fwdjModel.setMap(rsmap);
                result = kssServerApis.syFwdj(taskid, fwdjModel);
            }else {
                //有物品
                //物品接收参数处理
                String sl=(String) rslist.get(0).get("sl");
                wpjsModel.setJsbh(jsbh);
                wpjsModel.setJswpmc(wpglModel.getWpmc());
                wpjsModel.setSrlx("1");//设置收入类型为随身携带
                wpjsModel.setState("R2");
                wpjsModel.setCreator("管理员");
                wpjsModel.setCreatetime(new Date());
                wpjsModel.setRybh(gcbh);
                if (StringUtils.isNullOrEmpty(sl)){
                    sl="1";
                }
                rsmap.put("sl", sl);    //数量不填默认为1
                //物品管理参数处理
                wpglModel.setJsbh(jsbh);
                wpglModel.setLqzt("0");
                wpglModel.setCreator("管理员");
                wpglModel.setCreatetime(new Date());
                wpglModel.setRybh(gcbh);
                int i = 1;
                Kss_WpglModel wp = null;
                String lsh = Calendar.getInstance().getTimeInMillis() + "";//流水号
                String wpmc = "";
                while (i < 9) {
                    wpmc=(String) rslist.get(0).get("wpmc"+i);
                    if (!StringUtils.isNullOrEmpty(wpmc)) {
                        wp = new Kss_WpglModel();
                        BeanUtils.copyProperties(wpglModel, wp);
                        String hh = (String) rslist.get(0).get("sl"+i);
                        if (!StringUtils.isNullOrEmpty(hh) && !StringUtils.isNumber(hh)) {
                            return ResponseMessage.error("数量应该是为数字！！");
                        }
                        if (StringUtils.isNullOrEmpty(hh)) {
                            hh = 1 + "";
                        }
                        wp.setWpmc(wpmc);
                        wp.setSl(new BigDecimal(hh));
                        wp.setXh((String) rslist.get(0).get("xh" + i));
                        wp.setTz((String) rslist.get(0).get("tz" + i));
                        wp.setBz((String) rslist.get(0).get("bz" + i));
                        if (!StringUtils.isNullOrEmpty(wp.getWpmc())) {
                            li.add(wp);
                        }
                        wpmc = "" + wpmc + ",";
                    }
                    i++;
                }
                rsmap.put("wpgl", li);

                wpjsModel.setJswpmc(wpmc);
                rsmap.put("wpjs", wpjsModel);

                //现金接收参数处理
                xjjsModel.setJsbh(jsbh);
                xjjsModel.setCreatetime(Calendar.getInstance().getTime());
                xjjsModel.setCreator("管理员");
                xjjsModel.setState("R2");
                xjjsModel.setSrlx("1");//设置收入类型为随身携带
                xjjsModel.setBlr((String) rslist.get(0).get("djr"));
                xjjsModel.setRybh(gcbh);
                rsmap.put("xjjs", xjjsModel);
                rsmap.put("variables", variables);
                fwdjModel.setMap(rsmap);
                System.err.println("附物登记参数："+ JSONUtil.toJson(fwdjModel));
                result = kssServerApis.syFwdj(taskid, fwdjModel);
                System.err.println("json:"+ JSON.toJSONString(result));
            }
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return result;

    }


    /**
     * @api {get} /v4/kss/kss_cwgl/findPwglByCwh 根据床位号查询铺位
     * @apiVersion 0.4.0
     * @apiName findPwglByCwh
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 根据床位号查询铺位.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "szrp": "null",
     *         "szlx": "收入-3",
     *         "rybh": "110000111201907120012",
     *         "czr": "管理员",
     *         "szlxString": "收入-消费退还",
     *         "je": 10,
     *         "ye": 9999928,
     *         "zy": "消费退还"
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
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   “cwh”:"1001"
     * }
     */
    @OpenAPI
    @ApiOperation("根据床位号查询铺位")
    @GetMapping("/findPwglByCwh")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> findPwglByCwh(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/kss_cwgl/findPwglByCwh";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            String cwh=(String) maps.getResult().get("cwh");;
            if (StringUtils.isNullOrEmpty(maps.getResult().get("cwh"))) {
                return ResponseMessage.error("床位号不能空！");
            }
            ResponseMessage<String> result= kssServerApis.findPwglByCwh(cwh);
            List lists=new ArrayList();
            lists.add(result);
            System.err.println("result" + JSON.toJSONString(result));
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
     * @api {post} /v4/kss/kss_cwgl/addcwgl 床位管理保存
     * @apiVersion 0.4.0
     * @apiName addcwgl
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 床位管理保存.
     *
     * @apiParam {String} appcode 					    应用代码(必填)
     * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 							保存参数集(必填)
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *  "message": "保存成功!",
     *  "result": "11000011420200113000070",
     *  "status": 200,
     *  "timestamp": 1578892709281
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "entity":[{
     *      "jsh": "监室号(最大长度:4)",
     *      "xm": "姓名(最大长度:30)",
     *      "bm": "别名(最大长度:30)",
     *      "xb": "性别(最大长度:1)",
     *      "csrq": "出生日期(格式:yyyy-MM-dd hh:mm:ss)",
     *      "rsrq": "入所日期(格式:yyyy-MM-dd hh:mm:ss)",
     *      "bahj": "办案环节(最大长度:50)",
     *      "ay": "案由(最大长度:50)",
     *      "hjd": "户籍地(最大长度:120)",
     *      "cwh": "床位号(必填；最大长度:4)",
     *      "blr":"办理人(必填；最大长度:30)",
     *      "blsj": "办理时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *      "id": "11000011420191204000424",
     *      "rybh": "110000114201912040014"
     *		"lssy":"来所事由(必填；最大长度:50)",
     *      "drmj":"带入民警(必填；最大长度:50)",
     *      "drsj":"带入时间(必填;格式:yyyy-MM-dd hh:mm:ss)"
     *   }]
     * }
     */
    @ApiOperation("床位管理保存")
    @PostMapping("/addcwgl")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addcwgl(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/kss_cwgl/addcwgl";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            PwglModel pwglModel=JSONObject.parseObject(rslist.get(0).toString(),PwglModel.class);
            pwglModel.setState("R2");
            pwglModel.setCreatetime(new Date());
            pwglModel.setJsbh(jsbh);
            pwglModel.setCreatetor((String) rslist.get(0).get("blr"));
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            ResponseMessage<String> result = kssServerApis.pwglSave(pwglModel);
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
