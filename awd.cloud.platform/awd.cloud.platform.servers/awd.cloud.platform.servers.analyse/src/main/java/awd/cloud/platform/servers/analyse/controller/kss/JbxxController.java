package awd.cloud.platform.servers.analyse.controller.kss;


import awd.cloud.platform.servers.analyse.dao.kss.JbxxDao;
import awd.cloud.platform.servers.analyse.service.kss.Kss_JbxxService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_LscsService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_PhotosService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_XyrService;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.*;


@RestController
@RequestMapping("/kss/jbxx")
@Api(tags = "kss_jbxx",description = "人员数据统计")
public class JbxxController {
    @Autowired
    private Kss_JbxxService jbxxService;
    @Autowired
    private Kss_LscsService lscsService;
    @Autowired
    private Kss_PhotosService photosService;

    @Autowired
    private Kss_XyrService kss_XyrService;
    @Autowired
    private JbxxDao jbxxDao;

    /**
     * 总队分析
     */
//  TODO  全市人员在押总数

//  TODO  全市各类型人员数量概况（总数，新收，男女，风险比例）
//  TODO  全市各类型所在押百分比
//  TODO  全市不同地区今日出所人员数量情况
//  TODO  全市违规人员情况
//  TODO  全市常见疾病分布情况
//  TODO  全市风险评估趋势
//  TODO  各类型所在押百分比
//  TODO


//  TODO  看守所流水牌
//  TODO  拘留所流水牌
//  TODO  戒毒所流水牌
//  TODO  收教所流水牌
//  TODO  安康医院流水牌


    /**
     * 所级分析
     */
    @RequestMapping(value = "/getTypeByJqCount/{jsbh}", method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("全所按监区查询不同类型（总数，男，女，一级，二级，三级，紧闭，械具，未成年，WZ,新收）人员数量")
    public ResponseMessage<List<Map<String, Object>>> getTypeByJqCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true) @PathVariable("jsbh") String jsbh,
            @ApiParam(name = "jqs", value = "逗号分隔的监区号", required = false) @RequestParam(value = "jqs", required = false) String jqs) {
        List<String> jslist = new ArrayList<String>();
        if (!StringUtils.isNullOrEmpty(jqs)) {
            jslist = new ArrayList<>(Arrays.asList(jqs.split(",")));
        }
        List<Map<String, Object>> data = jbxxService.getTypeByJqCount(jsbh, jslist);
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/getBahjCount/{jsbh}", method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("全所办案环节统计")
    @ApiParam
    public ResponseMessage<List<Map<String, Object>>> getBahjCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true) @PathVariable("jsbh") String jsbh,
            @ApiParam(name = "type", value = "0:全类统计，1:合并统计", required = true) @RequestParam(value = "type", required = false) String type) {
        List<Map<String, Object>> data = jbxxService.getBahjCount(jsbh, type);
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/getAjlbCount/{jsbh}", method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("十大案件类型统计")
    @ApiParam
    public ResponseMessage<List<Map<String, Object>>> getAjlbCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true) @PathVariable("jsbh") String jsbh,
            @ApiParam(name = "ajlbs", value = "指定案件类别统计以逗号分隔", required = true) @RequestParam(value = "ajlbs", required = false) String ajlbs) {
        List<Map<String, Object>> data = jbxxService.getAjlbCount(jsbh, ajlbs);
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/getJyryxx/{jsbh}", method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("出所就医人员信息")
    @ApiParam
    public ResponseMessage<Map<String, Object>> getJyryxx(
            @ApiParam(name = "jsbh", value = "监所编号", required = true) @PathVariable("jsbh") String jsbh) {
        Map<String, Object> data = jbxxService.getJyRyxx(jsbh);
        return ResponseMessage.ok(data);
    }


    @RequestMapping(value = "/getTsLsThCount/{jsbh}", method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("今日提审、律师会见、谈话教育人数")
    @ApiParam
    public ResponseMessage<List<Map<String, Object>>> getTsLsThCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true) @PathVariable("jsbh") String jsbh) {
        List<Map<String, Object>> data = jbxxService.getTsLsThCount(jsbh, Calendar.getInstance().getTime());
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/getLscsCount/{jsbh}", method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("今日临时出所人数")
    @ApiParam
    public ResponseMessage<List<Map<String, Object>>> getLscsCount(
            @ApiParam(name = "jsbh", value = "监所编号", required = true) @PathVariable("jsbh") String jsbh) {
        List<Map<String, Object>> data = lscsService.getLscsCount(jsbh);
        return ResponseMessage.ok(data);
    }

    @RequestMapping(value = "/getTsLsThInfo/{jsbh}", method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("今日提审,律师，谈话人员情况")
    @ApiParam
    public ResponseMessage<List<Map<String, Object>>> getTsLsThInfo(
            @ApiParam(name = "jsbh", value = "监所编号", required = true) @PathVariable("jsbh") String jsbh) {
        List<Map<String, Object>> data = jbxxService.getTsLsThInfo(jsbh);
        return ResponseMessage.ok(data);
    }


    @RequestMapping(value = "/getLscsInfo/{jsbh}", method = RequestMethod.POST)
    @OpenAPI
    @ApiOperation("临时出所人员情况")
    @ApiParam
    public ResponseMessage<List<Map<String, Object>>> getLscsInfo(
            @ApiParam(name = "jsbh", value = "监所编号", required = true) @PathVariable("jsbh") String jsbh) {
        List<Map<String, Object>> data = null;
        return ResponseMessage.ok(data);
    }

    @GetMapping("/queryXbForSyrsYwdz")
    @ApiOperation("收押入所业务动态")
    @OpenAPI
    public ResponseMessage<List<Map<String, Object>>> queryForSyrsYwdz(@RequestParam(value = "jsbh") String jsbh,
                                                                       @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
                                                                       @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime
    ) {
        List<Map<String, Object>> li = jbxxService.queryForSyrsYwdz(jsbh, startTime, endTime);
        return ResponseMessage.ok(li);
    }


    @GetMapping("/queryByXb")
    @ApiOperation("性别查询统计")
    @OpenAPI
    public Map<String, Object> queryByXb(@RequestParam(value = "starttime", required = false) String starttime,
                                         @RequestParam(value = "endtime", required = false) String endtime,
                                         @RequestParam(value = "jsbh", required = false) String jsbh) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (starttime != null) {
            starttime += " 00:00:00";
        }
        if (endtime != null) {
            endtime += " 23:59:59";
        }
        List<Map<String, Object>> list = jbxxService.queryByXb(starttime, endtime, jsbh);
        if (list == null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        } else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }

    @GetMapping("/syrsYwdt")
    @ApiOperation("收押入所业务动态")
    @OpenAPI
    public ResponseMessage<?> syrsYwdt(String startTime, String endTime, String jsbh) {
        try {
            System.out.println("------------------------" + jsbh);
            List<Map<String, Object>> xbList = jbxxService.queryByXb(startTime, endTime, jsbh);
            List<Map<String, Object>> hjdList = jbxxService.queryByHjd(startTime, endTime, jsbh);
            List<Map<String, Object>> rsxzList = jbxxService.queryByRsxz(startTime, endTime, jsbh);
            List<Map<String, Object>> whcdList = jbxxService.queryByWhcd(startTime, endTime, jsbh);
            List<Map<String, Object>> rszrsList = jbxxService.queryRszrs(startTime, endTime, jsbh);
            List<Map<String, Object>> zyzrsList = jbxxService.queryZyzrs(startTime, endTime, jsbh);
            List<Map<String, Object>> jsList = jbxxService.queryJs(startTime, endTime, jsbh);
            //获取数据最多的一个list
            Map<String, Object> map = new HashMap();
            map.put("xb", xbList);
            map.put("hjd", hjdList);
            map.put("rsxz", rsxzList);
            map.put("whcd", whcdList);
            map.put("rszrs", rszrsList);
            map.put("zyzrs", zyzrsList);
            map.put("js", jsList);
            System.out.println("-----------==");
            System.out.print("-----" + JSON.toJSONString(map));
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("失败");
        }
    }


    @GetMapping("/queryByHjd")
    @ApiOperation("户籍地查询统计")
    @OpenAPI
    public Map<String, Object> queryByHjd(@RequestParam(value = "starttime", required = false) String starttime,
                                          @RequestParam(value = "endtime", required = false) String endtime,
                                          @RequestParam(value = "jsbh", required = false) String jsbh) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (starttime != null) {
            starttime += " 00:00:00";
        }
        if (endtime != null) {
            endtime += " 23:59:59";
        }
        List<Map<String, Object>> list = jbxxService.queryByHjd(starttime, endtime, jsbh);
        if (list == null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        } else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }

    ///////////////////////////////////////////////////////所级统计///////////////////////////////////////////////////////////////////////


    @GetMapping("/queryJbxxCount")
    @ApiOperation("获取在押总数")
    @OpenAPI
    public ResponseMessage<Long> queryJbxxR8Count(@RequestParam(value = "jsbh", required = false) String jsbh) {
        return ResponseMessage.ok(jbxxService.getJbxxR8Count(jsbh));
    }

    @GetMapping("/queryJbxxcountByField")
    @ApiOperation("获取指定字段值的在押人员总数")
    @OpenAPI
    public ResponseMessage<List<Map<String, Object>>> queryJbxxR8ByField(@RequestParam(value = "jsbh", required = false) String jsbh,
                                                                         @RequestParam(value = "field", required = true) String field,
                                                                         @RequestParam(value = "value", required = false) String value) {
        return ResponseMessage.ok(jbxxService.getJbxxR8ByField(jsbh, field, value));
    }

    @GetMapping("/queryTaskCount")
    @ApiOperation("获取待办任务数")
    @OpenAPI
    public ResponseMessage<Long> queryTaskCount(@RequestParam(value = "jsbh", required = false) String jsbh,
                                                @RequestParam(value = "flow", required = false) String flow,
                                                @RequestParam(value = "node", required = false) String node) {
        return ResponseMessage.ok(jbxxService.queryTaskCount(jsbh, flow, node));
    }

    @GetMapping("/getRyPhoto")
    @ApiOperation("获取人员照片")
    @OpenAPI
    public String getRyPhoto(@RequestParam(value = "rybh") String rybh,
                             @RequestParam(value = "jsbh") String jsbh) {

        return photosService.getRyPhoto(rybh, jsbh);
    }

    @GetMapping("/tongJi")
    @ApiOperation("统计")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> jianShiFuW(@RequestParam(value = "jsbh", required = false) String jsbh,
                                                           @RequestParam(value = "jsh", required = false) String jsh) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            List<Map<String, Object>> list = jbxxService.jianShiFuW(jsbh, jsh);
            result.put("jianShiFuW", list);
            return ResponseMessage.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("执行失败");
        }
    }


    @GetMapping("/swj_ryflfxCount")
    @ApiOperation("人员分类分析=上位机版本")
    @OpenAPI
    public ResponseMessage<?> swj_ryflfxCount(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime) {

        try {
            List<Map<String, Object>> maps = jbxxService.swj_hyzklh(starttime, endtime);
            List<Map<String, Object>> maps1 = jbxxService.swj_hyzkso(starttime, endtime);
            List<Map<String, Object>> maps2 = jbxxService.swj_jkzkslqx(starttime, endtime);
            List<Map<String, Object>> maps3 = jbxxService.swj_jkzkcj(starttime, endtime);
            HashMap<String, Object> map = new HashMap<>();
            map.put("离婚",maps);
            map.put("丧偶",maps1);
            map.put("生理缺陷",maps2);
            map.put("残疾",maps3);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }


    @GetMapping("/ryflfxCount")
    @ApiOperation("人员分类分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ryflfxCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //离婚人数
            List<Map<String, Object>> lhlist = jbxxService.hyzklh();
            int[] lh = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : lhlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        lh[i] = lh[i] + Integer.parseInt(maps.get("sl").toString());
                    }
                }
            }
            //丧偶人数
            List<Map<String, Object>> solist = jbxxService.hyzkso();
            int[] so = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : solist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        System.err.println(maps.get("jsbh"));
                        so[i] = so[i] + Integer.parseInt(maps.get("sl").toString());
                    }
                }
            }
            //有生理缺陷
            List<Map<String, Object>> slqxlist = jbxxService.jkzkslqx();
            int[] slqx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : slqxlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        slqx[i] = slqx[i] + Integer.parseInt(maps.get("sl").toString());
                    }
                }
            }
            //残疾人数
            List<Map<String, Object>> cjlist = jbxxService.jkzkcj();
            int[] cz = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : cjlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        cz[i] = cz[i] + Integer.parseInt(maps.get("sl").toString());
                    }
                }
            }
            map.put("lh", lh);
            map.put("so", so);
            map.put("slqx", slqx);
            map.put("cz", cz);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

    @GetMapping("/shseCount")
    @ApiOperation("涉黑涉恶人员统计")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> shseCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> seselist = jbxxService.shseCount();
            int[] nan = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] nv = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] qt = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : seselist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        nan[i] = nan[i] + Integer.parseInt(maps.get("nan").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        nv[i] = nv[i] + Integer.parseInt(maps.get("nv").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        qt[i] = qt[i] + Integer.parseInt(maps.get("qt").toString());
                    }
                }
            }
            map.put("nan", nan);
            map.put("nv", nv);
            map.put("qt", qt);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }

    @GetMapping("/zdryCount")
    @ApiOperation("重点人员统计")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> zdryCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> seselist = jbxxService.zdryCount();
            int[] man = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] woman = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] unknow = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : seselist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        man[i] = man[i] + Integer.parseInt(maps.get("man").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        woman[i] = woman[i] + Integer.parseInt(maps.get("woman").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        unknow[i] = unknow[i] + Integer.parseInt(maps.get("unknow").toString());
                    }
                }
            }
            map.put("man", man);
            map.put("woman", woman);
            map.put("unknow", unknow);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }

    @GetMapping("/gjfxCount")
    @ApiOperation("国籍分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> gjfxCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> gjfxlist = jbxxService.gjfxCount();
            int[] zg = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] tw = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] xg = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] am = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] wj = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : gjfxlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        zg[i] = zg[i] + Integer.parseInt(maps.get("zg").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        tw[i] = tw[i] + Integer.parseInt(maps.get("tw").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        xg[i] = xg[i] + Integer.parseInt(maps.get("xg").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        am[i] = am[i] + Integer.parseInt(maps.get("am").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        wj[i] = wj[i] + Integer.parseInt(maps.get("wj").toString());
                    }
                }
            }
            map.put("zg", zg);
            map.put("tw", tw);
            map.put("xg", xg);
            map.put("am", am);
            map.put("wj", wj);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }


    @GetMapping("/swj_cqjyCount")
    @ApiOperation("超期羁押=上位机版本")
    @OpenAPI
    public ResponseMessage<?> swj_cqjyCount(@RequestParam(required = false,value = "starttime") String starttime,  @RequestParam(required = false,value = "endtime") String endtime,@RequestParam(required = false,value = "zszt") String zszt) {

        try {
            List<Map<String, Object>> maps = jbxxService.swj_cqjyCount(starttime, endtime,zszt);
            return ResponseMessage.ok(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

    @GetMapping("/cqjyCount")
    @ApiOperation("超期羁押")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> cqjyCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //超期羁押数量
            List<Map<String, Object>> cqjylist = jbxxService.cqjyCount();
            int[] cqjy = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : cqjylist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        cqjy[i] = cqjy[i] + Integer.parseInt(maps.get("sl").toString());
                    }
                }
            }
            //关押总数
            List<Map<String, Object>> alljbxxlist = jbxxService.alljbxxCount();
            int[] alljbxx = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : alljbxxlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        alljbxx[i] = alljbxx[i] + Integer.parseInt(maps.get("sl").toString());
                    }
                }
            }
            //计算百分比
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            Double[] value = new Double[17];
            for (int i = 0; i < jsbh.length; i++) {
                if (alljbxx[i] != 0) {
                    value[i] = Double.valueOf(numberFormat.format((float) cqjy[i] / (float) alljbxx[i]));
                } else {
                    value[i] = 0.00;
                }
            }
            map.put("cqjy", cqjy);
            map.put("value", value);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

    @GetMapping("/gyqxCount")
    @ApiOperation("关押期限分析(3月以上   3-6   6-1  1-3  3)")
    @OpenAPI
    public ResponseMessage<List<Map<String, Object>>> gyqxCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            //<!-- 3个月以下关押期限 -->
            List<Map<String, Object>> gyqxfxCountlist = jbxxService.gyqxfxCount();
            for (int i = 0; i < jsbh.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                int[] s = new int[]{0, 0, 0, 0, 0};
                for (Map<String, Object> maps : gyqxfxCountlist) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        s[0] = s[0] + Integer.parseInt(maps.get("three").toString());
                        s[1] = s[1] + Integer.parseInt(maps.get("six").toString());
                        s[2] = s[2] + Integer.parseInt(maps.get("oneyear").toString());
                        s[3] = s[3] + Integer.parseInt(maps.get("threeyear").toString());
                        s[4] = s[4] + Integer.parseInt(maps.get("threeyearover").toString());
                    }
                }
                map.put("data", s);
                list.add(map);
            }
            return ResponseMessage.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }


    @GetMapping("/swj_ajqkfxCount")
    @ApiOperation("上位机版本=案件情况分析")
    @OpenAPI
    public ResponseMessage<?> swj_ajqkfxCount(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime, @RequestParam(required = false, value = "ay") String ay) {
        try {
            return  ResponseMessage.ok(jbxxService.swj_ajqkfxCount(starttime, endtime,ay));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }




    @GetMapping("/ajqkfxCount")
    @ApiOperation("案件情况分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ajqkfxCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> ajqkfxCountlist = jbxxService.ajqkfxCount();
            int[] jrwfzza = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] ksdc = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] twhla = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] dqa = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : ajqkfxCountlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        jrwfzza[i] = jrwfzza[i] + Integer.parseInt(maps.get("jrwfzza").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        ksdc[i] = ksdc[i] + Integer.parseInt(maps.get("ksdc").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        twhla[i] = twhla[i] + Integer.parseInt(maps.get("twhla").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        dqa[i] = dqa[i] + Integer.parseInt(maps.get("dqa").toString());
                    }
                }
            }
            map.put("dqa", dqa);
            map.put("twhla", twhla);
            map.put("ksdc", ksdc);
            map.put("jrwfzza", jrwfzza);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }


    @GetMapping("/swj_lsfxfxCount")
    @ApiOperation("上位机版本=留所服刑分析")
    @OpenAPI
    public ResponseMessage<?> lsfxfxCount(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime) {

        try {
            List<Map<String, Object>> maps = jbxxService.swj_lsfxfxCount(starttime, endtime);
            return  ResponseMessage.ok(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseMessage.error("查询失败");
        }
    }

    @GetMapping("/lsfxfxCount")
    @ApiOperation("留所服刑分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> lsfxfxCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> lsfxfxCountlist = jbxxService.lsfxfxCount();
            int[] man = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] woman = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] unknown = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : lsfxfxCountlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        man[i] = man[i] + Integer.parseInt(maps.get("man").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        woman[i] = woman[i] + Integer.parseInt(maps.get("woman").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        unknown[i] = unknown[i] + Integer.parseInt(maps.get("unknown").toString());
                    }
                }
            }
            map.put("man", man);
            map.put("woman", woman);
            map.put("unknown", unknown);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

    @GetMapping("/swj_yzjbfxCount")
    @ApiOperation("严重疾病分析=上位机版本")
    @OpenAPI
    public ResponseMessage<?> swj_yzjbfxCount(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime) {

        try {
            List<Map<String, Object>> maps = jbxxService.swj_yzjbfxCount(starttime, endtime);
            return ResponseMessage.ok(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }

    @GetMapping("/yzjbfxCount")
    @ApiOperation("严重疾病分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> yzjbfxCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> yzjbfxCountlist = jbxxService.yzjbfxCount();
            int[] zd = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] az = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : yzjbfxCountlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        zd[i] = zd[i] + Integer.parseInt(maps.get("zd").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        az[i] = az[i] + Integer.parseInt(maps.get("az").toString());
                    }
                }
            }
            map.put("zd", zd);
            map.put("az", az);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

    @GetMapping("/tswsfxCount")
    @ApiOperation("投送未收分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> tswsfxCount() {
        //定义地区  与前台顺序对应
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> tswsfxCountlist = jbxxService.tswsfxCount();
            List<Map<String, Object>> wsrsCountlist = kss_XyrService.wsrsCount();
            int[] wsrs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] tsjyws = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] tsljsws = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] tssgsws = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] tsakyyws = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] qttsws = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : tswsfxCountlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        tsjyws[i] = tsjyws[i] + Integer.parseInt(maps.get("tsjyws").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        tsljsws[i] = tsljsws[i] + Integer.parseInt(maps.get("tsljsws").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        tssgsws[i] = tssgsws[i] + Integer.parseInt(maps.get("tssgsws").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        tsakyyws[i] = tsakyyws[i] + Integer.parseInt(maps.get("tsakyyws").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        qttsws[i] = qttsws[i] + Integer.parseInt(maps.get("qttsws").toString());
                    }
                }
            }
            for (Map<String, Object> maps : wsrsCountlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        wsrs[i] = wsrs[i] + Integer.parseInt(maps.get("wsrs").toString());
                    }
                }
            }
            map.put("tsjyws", tsjyws);
            map.put("qttsws", qttsws);
            map.put("tsakyyws", tsakyyws);
            map.put("tssgsws", tssgsws);
            map.put("tsljsws", tsljsws);
            map.put("wsrs", wsrs);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

    @GetMapping("/swj_lslsfxCount")
    @ApiOperation("临时离所分析=上位机版本")
    @OpenAPI
    public ResponseMessage<?> swj_lslsfxCount(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime) {
        try {
            List<Map<String, Object>> maps = jbxxService.swj_lslsfxCount(starttime, endtime);
            return  ResponseMessage.ok(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }

    @GetMapping("/swj_sjwsry")
    @ApiOperation("送监未收人员==上位机版本")
    @OpenAPI
    public ResponseMessage<?> swj_sjwsry(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime) {
        try {
            List<Map<String, Object>> maps = jbxxService.swj_sjwsry(starttime, endtime);
            return  ResponseMessage.ok(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }

    @GetMapping("/lslsfxCount")
    @ApiOperation("临时离所分析")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> lslsfxCount() {
        int[] jsbh = new int[]{1100001, 110101111, 110102111, 110105111, 110106111, 110107111, 110108111, 110109111, 11011111, 110112111, 110113111, 110221111, 110224111, 110226111, 110227111, 110228111, 110229111};
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> lslsfxCountlist = jbxxService.lslsfxCount();
            int[] lslsrs = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] ktsl = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] gjfba = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] tq = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] kb = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] zy = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] ld = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int[] qt = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (Map<String, Object> maps : lslsfxCountlist) {
                for (int i = 0; i < jsbh.length; i++) {
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        lslsrs[i] = lslsrs[i] + Integer.parseInt(maps.get("lslsrs").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        ktsl[i] = ktsl[i] + Integer.parseInt(maps.get("ktsl").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        gjfba[i] = gjfba[i] + Integer.parseInt(maps.get("gjfba").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        tq[i] = tq[i] + Integer.parseInt(maps.get("tq").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        kb[i] = kb[i] + Integer.parseInt(maps.get("kb").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        zy[i] = zy[i] + Integer.parseInt(maps.get("zy").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        ld[i] = kb[i] + Integer.parseInt(maps.get("ld").toString());
                    }
                    if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
                        qt[i] = qt[i] + Integer.parseInt(maps.get("qt").toString());
                    }
                }
            }
            map.put("lslsrs", lslsrs);
            map.put("qt", qt);
            map.put("ld", ld);
            map.put("zy", zy);
            map.put("kb", kb);
            map.put("tq", tq);
            map.put("gjfba", gjfba);
            map.put("ktsl", ktsl);
            return ResponseMessage.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }


    }


    @GetMapping("/kssdpaqgl")
    @ApiOperation("大屏安全管理")
    @OpenAPI
    public Map<String, Object> dpaqgl(@RequestParam(value = "jsbh", required = false) String jsbh) {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            Map<String, Object> list = jbxxService.dpaqgl(jsbh);
            List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
            int i = 0;
            String msg2 = "";
            String name2 = "";
            for (String key : list.keySet()) {
                i++;
                if (i == 1) {
                    msg2 = key;
                    name2 = list.get(key).toString();
                } else {
                    Map<String, Object> maps = new HashMap<String, Object>();
                    maps.put("name1", key);
                    maps.put("msg1", list.get(key));
                    maps.put("name2", msg2);
                    maps.put("msg2", name2);
                    maps.put("class1", "");
                    maps.put("class2", "");
                    map.add(maps);
                    i = 0;
                }
            }
            if (list.size() % 2 != 0 && list.size() != 0) {
                Map<String, Object> maps = new HashMap<String, Object>();
                maps.put("name1", msg2);
                maps.put("msg1", name2);
                maps.put("name2", "");
                maps.put("msg2", "");
                maps.put("class1", "");
                maps.put("class2", "");
                map.add(maps);
            }
            Map<String, Object> b = new HashMap<String, Object>();
            b.put("name", "安全管理");
            b.put("list", map);
            Map<String, Object> lt = new HashMap<String, Object>();
            lt.put("b", b);
            lt.put("name", "重大一、二、三级风险对象详情");
            Map<String, Object> index = new HashMap<String, Object>();
            index.put("lt", lt);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("index", index);
            result.put("code", 200);
            result.put("data", data);
            result.put("msg", "查询成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> results = new HashMap<String, Object>();
            results.put("code", 500);
            results.put("data", "");
            results.put("msg", "查询失败");
            return results;
        }
    }


    //大屏看守所在所人员
    @GetMapping("/select_zsry")
    @OpenAPI
    @ApiOperation("大屏看守所在所人员统计")
    public Map<String, Object> select_zsry(@RequestParam(required = false, value = "jsbh") String jsbh) {


       /*参数注解  jbxx表
         jsbh 监所编号;
         jsh 监室号  列:0104
         BAHJ 办案环节(BAJD) 传code值 列：12
         jqh  监区号  列：01 东一监区
         */


        try {
            Map<String, Object> result = new LinkedHashMap<>();
            Map<String, Object> datelist = new LinkedHashMap<>();
            Map<String, Object> list = jbxxService.find_zsry(jsbh);
            List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
            for (String key : list.keySet()) {
                Map<String, Object> maps = new LinkedHashMap<>();
                maps.put("name", key);
                maps.put("msg", list.get(key));
                if ("当日入所".equals(key)) {
                    maps.put("class", "people");
                }
                maplist.add(maps);
            }
            datelist.put("name", "在所人员");
            datelist.put("list", maplist);
            Map<String, Object> msp = new LinkedHashMap<>();
            msp.put("zsry", datelist);
            result.put("data", msp);
            result.put("code", 200);
            result.put("msg", "查询成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("aqjc", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }

    }


    //大屏看守所在所人员信息
    @GetMapping("/select_zsryxx")
    @OpenAPI
    @ApiOperation("大屏看守所在所人员信息")
    public Map<String, Object> select_zsryxx(@RequestParam(required = false, value = "bahj") String bahj,
                                             @RequestParam(required = false, value = "rybh") String rybh,
                                             @RequestParam(required = false, value = "jsbh") String jsbh,
                                             @RequestParam(required = false, value = "rsrq") String rsrq,
                                             @RequestParam(required = false, value = "crjbj") String crjbj,
                                             @RequestParam(required = false, value = "jsh") String jsh) {

        System.out.println("bahj5=" + bahj);
        System.out.println("rybh656=" + rybh);
        System.out.println("rsrq545=" + rsrq);
        System.out.println("jsbh2=" + jsbh);
        System.out.println(" crjbj3=" + crjbj);
        System.out.println("jsh8=" + jsh);


        try {
            String s = "";
            if (!"".equals(jsh) && null != jsh) {
                s = jsh.substring(0, 2) + "%";
            }
            System.out.println("String s = " + s);
            ArrayList<Map<String, Object>> list = jbxxService.find_zsryxx(bahj, rybh,  jsbh,rsrq, crjbj, s);
            Map<String, Object> data = new LinkedHashMap<>();
            List<Object> listmap = new ArrayList<Object>();
            System.out.println("listdgdfdf=" + list.toString());
            for (int i = 0; i < list.size(); i++) {

                Map<String, Object> maps = new LinkedHashMap<>();
                maps.put("xl", i + 1);

                maps.put("item1", (list.get(i).get("xm") != null && !list.get(i).get("xm").equals("")) ? list.get(i).get("xm") : "暂无姓名");
                maps.put("item2", (list.get(i).get("xb") != null && !list.get(i).get("xb").equals("")) ? list.get(i).get("xb") : "暂无性别");
                maps.put("item3", (list.get(i).get("jkzk") != null && !list.get(i).get("jkzk").equals("")) ? list.get(i).get("jkzk") : "暂无健康状况");
                maps.put("item4", (list.get(i).get("bahj") != null && !list.get(i).get("bahj").equals("")) ? list.get(i).get("bahj") : "暂无办案环节");


                String str = null;
                try {
                    str = list.get(i).get("ay").toString();
                    maps.put("item5", CacheUtils.get().getDictionarys("AJLB", str));
                } catch (Exception e) {
                    maps.put("item5", "暂无案由");
                }


                maps.put("item6", (list.get(i).get("wxdj") != null && !list.get(i).get("wxdj").equals("")) ? list.get(i).get("wxdj") : "暂无危险等级");
                maps.put("item7", (list.get(i).get("rybh") != null && !list.get(i).get("rybh").equals("")) ? list.get(i).get("rybh") : "暂无人员编号");

                listmap.add(maps);
            }
            data.put("data", listmap);
            System.out.println("listmap==" + listmap.toString());
            System.out.println("j000--=="+data.toString());
            data.put("seriesNum", "7");


            List<Object> titles = new ArrayList<>();
            Map<String, Object> column0 = new LinkedHashMap<>();
            column0.put("series", "xl");
            column0.put("name", "序列");
            titles.add(column0);
            Map<String, Object> column1 = new LinkedHashMap<>();
            column1.put("series", "item1");
            column1.put("name", "姓名");
            titles.add(column1);
            Map<String, Object> column2 = new LinkedHashMap<>();
            column2.put("series", "item2");
            column2.put("name", "性别");
            titles.add(column2);
            Map<String, Object> column3 = new LinkedHashMap<>();
            column3.put("series", "item3");
            column3.put("name", "健康状况");
            titles.add(column3);
            Map<String, Object> column4 = new HashMap<>();
            column4.put("series", "item4");
            column4.put("name", "办案环节");
            titles.add(column4);
            Map<String, Object> column5 = new HashMap<>();
            column5.put("series", "item5");
            column5.put("name", "案由");
            titles.add(column5);
            Map<String, Object> column6 = new HashMap<>();
            column6.put("series", "item6");
            column6.put("name", "风险等级");
            titles.add(column6);

            data.put("titles", titles);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", 200);
            map.put("msg", "查询成功");
            map.put("zsyrjbxx", data);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("zsyrjbxx", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }

    }


    //医疗情况==上位机版本
    @ApiOperation("医疗情况==上位机版本")
    @GetMapping("/swj_ylgl")
    @OpenAPI
    public ResponseMessage<?> swj_ylgl(@RequestParam(required = false,value = "starttime") String starttime,@RequestParam(required = false,value = "endtime") String endtime) {

        try {
            ArrayList<Map<String, Object>> maps = jbxxService.swj_ylgl(starttime, endtime);
            return  ResponseMessage.ok(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }


        //大屏医疗管理
    @ApiOperation("大屏看守所医疗管理")
    @GetMapping("/select_ylgl")
    @OpenAPI
    public Map<String, Object> select_ylgl(@RequestParam("jsbh") String jsbh) {


        try {
            Map<String, Object> ylgl = jbxxService.find_ylgl(jsbh);
            Map<String, Object> map = new HashMap<>();
            map.put("lt", ylgl);
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("index", map);
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("data", map1);
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("msg", "");
            HashMap<String, Object> map4 = new HashMap<>();
            map4.put("code", 200);
            HashMap<String, Object> map5 = new HashMap<>();
            map5.putAll(map2);
            map5.putAll(map3);
            map5.putAll(map4);
            return map5;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("ylgl", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }


    }


    @ApiOperation("大屏看守所提讯会见")
    @GetMapping("/select_txhj")
    @OpenAPI
    public Map<String, Object> select_txhj(@RequestParam(value = "jsbh") String jsbh) {


        try {
            Map<String, Object> txhj = jbxxService.find_txhj(jsbh);

            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("lt", txhj);
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("index", map1);
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("data", map2);
            HashMap<String, Object> map4 = new HashMap<>();
            map4.put("msg", "");
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("code", 200);
            HashMap<String, Object> map6 = new HashMap<>();
            map6.putAll(map5);
            map6.putAll(map4);
            map6.putAll(map3);

            return map6;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("txhj", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }


    @ApiOperation("大屏看守所关押期限")
    @GetMapping("/select_gyqx")
    @OpenAPI
    public Map<String, Object> select_gyqx(@RequestParam(value = "jsbh") String jsbh) {


        try {
            HashMap<String, Object> gyqx = jbxxService.find_gyqx(jsbh);
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("lt", gyqx);
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("index", map1);
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("data", map2);
            HashMap<String, Object> map4 = new HashMap<>();
            map4.put("msg", "");
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("code", 200);
            HashMap<String, Object> map6 = new HashMap<>();
            map6.putAll(map5);
            map6.putAll(map4);
            map6.putAll(map3);

            return map6;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("gyqx", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }

    }


    @ApiOperation("大屏看守所监区抱病")
    @GetMapping("/select_jqbb")
    @OpenAPI
    public HashMap<String, Object> select_jqbb(@RequestParam(required = false, value = "jsbh") String jsbh) {


        try {

            List<Object> jqbb = jbxxService.find_jqbb(jsbh);
            System.out.println("jqbb===" + jqbb.toString());
            HashMap<String, Object> map = new HashMap<>();
            map.put("min", 0);
            map.put("name", "各监区报病情况趋势图");
            map.put("list", jqbb);

            HashMap<String, Object> map6 = new HashMap<>();
            map6.put("rb", map);

            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("index", map6);
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("data", map2);
            map3.put("msg", "");
            map3.put("code", 200);
            return map3;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("jqbb", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }


    @ApiOperation("大屏看守所值班领导")
    @GetMapping("/select_zbld")
    @OpenAPI
    public Map<String, Object> select_zbld(@RequestParam(value = "jsbh") String jsbh) {


        Map<String, Object> map6 = new HashMap<>();
        try {
            Map<String, Object> zbld = jbxxService.find_zbld(jsbh);
            Map<String, Object> map2 = new HashMap<>();
            map2.put("index", zbld);
            Map<String, Object> map3 = new HashMap<>();
            map3.put("data", map2);
            Map<String, Object> map4 = new HashMap<>();
            map4.put("msg", "");
            Map<String, Object> map5 = new HashMap<>();
            map5.put("code", 200);

            map6.putAll(map5);
            map6.putAll(map4);
            map6.putAll(map3);
           
            return map6;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("zbld", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;

        }

    }


    @ApiOperation("大屏看守所重点关注人员")
    @GetMapping("/select_zdgz")
    @OpenAPI
    public HashMap<String, Object> select_zdgz(@RequestParam(required = false, value = "jsbh") String jsbh) {
        try {
            ArrayList<Object> zdgz = jbxxService.find_zdgz(jsbh);
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("code", 200);
            map2.put("msg", "");
            map2.put("data", zdgz);
            return map2;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("zdgz", "大屏看守所重点关注人员");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }


    @ApiOperation("大屏看守所重点关注人员详细信息")
    @GetMapping("/select_zdgzry")
    @OpenAPI
    public HashMap<String, Object> select_zdgzry(@RequestParam(required = false, value = "jsbh") String jsbh, @RequestParam(required = false, value = "rybh") String rybh) {

        //String strify = request.getParameter("strify");


        try {
            HashMap<String, Object> zdgzry = jbxxService.find_zdgzry(jsbh, rybh);


            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("code", 200);
            map2.put("msg", "");
            map2.put("data", zdgzry);


            return map2;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("zdgzry", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }


    @ApiOperation("大屏看守所滑动卡牌")
    @GetMapping("/select_hdsj")
    @OpenAPI
    public HashMap<String, Object> select_hdsj(@RequestParam(required = false, value = "jsbh") String jsbh) {


        try {
            ArrayList<Object> hdsj = jbxxService.find_hdsj(jsbh);


            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("code", 200);
            map5.put("data", hdsj);
            map5.put("msg", "");


            return map5;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("hdsj", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }


    @ApiOperation("大屏看守诉讼情况情况与犯罪类型分析")
    @GetMapping("/select_ssqq")
    @OpenAPI
    public Map<String, Object> select_ssqq(@RequestParam(required = false, value = "jsbh") String jsbh) {

        try {
            ArrayList<Object> list = jbxxService.find_ssqq(jsbh);//百分比数据
            ArrayList<HashMap<String, Object>> list1 = jbxxDao.select_ssqq(jsbh);//百分比下面的图 带其他的这幅图

            ArrayList<Object> list2 = new ArrayList<>();

            if (list1.size() > 0) {
                for (HashMap<String, Object> bfb : list1) {
                    HashMap<String, Object> map = new HashMap<>();
                    String mc = "暂无名称";
                    String sl = "0";
                    if (bfb != null) {
                        Object mc1 = bfb.get("mc");
                        if (mc1 != null && !"".equals(mc1.toString())) {
                            mc = mc1.toString();
                        }
                        Object SL = bfb.get("sl");
                        if (SL != null && !"".equals(SL.toString())) {
                            sl = SL.toString();
                        }

                    }
                    map.put("name", mc);
                    map.put("value", sl);
                    list2.add(map);
                }
            } else {
                HashMap<String, Object> map = new HashMap<>();
                map.put("name", "暂无名称");
                map.put("value", "0");
                list2.add(map);
            }


            ArrayList<HashMap<String, Object>> list3 = jbxxDao.select_qt(jsbh);//其他数量
            if (list3.size() > 0) {
                for (HashMap<String, Object> qt : list3) {
                    String qtSL = "0";
                    if (qt != null) {
                        Object qtsl = qt.get("其他");
                        if (qtsl.toString() != null && !"".equals(qtsl.toString())) {
                            qtSL = qtsl.toString();
                        }
                    }
                    HashMap<String, Object> map4 = new HashMap<>();
                    map4.put("name", "其他");
                    map4.put("value", qtSL);
                    list2.add(map4);
                }
            } else {
                HashMap<String, Object> map4 = new HashMap<>();
                map4.put("name", "其他");
                map4.put("value", "0");
                list2.add(map4);
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put("sum", 300);
            map.put("list", list2);        // b


            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("b", map);
            map1.put("name", "诉讼情况分类与犯罪类型分析");
            map1.put("t", list);

            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("rt", map1);
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("index", map2);

            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("data", map3);
            map5.put("msg", "");
            map5.put("code", 200);

            return map5;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("ssqq", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }





    @ApiOperation("大屏监区违规情况趋势图")
    @GetMapping("/select_Jqwg")
    @OpenAPI
    public Map<String, Object> select_Jqwg(@RequestParam(required = false, value = "jsbh") String jsbh) {

        try {
            ArrayList<Object> jqwg = jbxxService.find_jqwg(jsbh);
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("data", jqwg);
            map5.put("msg", "");
            map5.put("code", 200);
            return map5;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("Jqwg", "");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }

    }



    @ApiOperation("大屏监区违规情况趋势图人员详细信息")
    @GetMapping("/select_jqwgryxx")
    @OpenAPI
    public Map<String, Object> select_jqwgryxx(@RequestParam(required = false, value = "jsbh") String jsbh, @RequestParam(required = false, value = "jqh") String jqh) {

        try {
            String s = jqh + "%";

            HashMap<String, Object> jqwgryxx = jbxxService.find_jqwgryxx(jsbh, s);
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("data", jqwgryxx);
            map5.put("msg", "");
            map5.put("code", 200);
            return map5;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("jqwgryxx", "大屏监区违规情况趋势图人员详细信息");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }


    @ApiOperation("查看违规情况=上位机版本")
    @GetMapping("/swj_wgqk")
    @OpenAPI
    public ResponseMessage<?> swj_wgqk(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime) {

        try {
            ArrayList<HashMap<String, Object>> hashMaps = jbxxService.swj_wgqk(starttime, endtime);
            return  ResponseMessage.ok(hashMaps);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }

    }



    @ApiOperation("大屏监区违规程度分析")
    @GetMapping("/find_wgcd")
    @OpenAPI
    public Map<String, Object> find_wgcd(@RequestParam(required = false, value = "jsbh") String jsbh) {

        try {
            LinkedHashMap<String, Object> list = jbxxService.select_wgcd(jsbh);
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("data", list);
            map5.put("msg", "");
            map5.put("code", 200);
            return map5;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("wgcd", "大屏监区违规程度分析");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }

    }


    @ApiOperation("大屏监区违规程度点击==根据违规类型=一般违规")
    @GetMapping("/select_wgcd_rybh")
    @OpenAPI
    public LinkedHashMap<String, Object> select_wgcd_rybh(@RequestParam(required = false, value = "jsbh") String jsbh,   @RequestParam(required = false, value = "wgqk") String wgqk
    ) {

        ArrayList<Object> list2 = new ArrayList<>();
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add("0014");
        list1.add("0013");
        int k=0;

        try {

                if (list1.contains(wgqk)) {
                    for (int i = 0; i < list1.size(); i++){
                        ArrayList<HashMap<String, Object>> list = jbxxService.select_wgcd_rybh(jsbh, list1.get(i).toString()); //大屏kss查看违规程度=里面联动下面的数据=数据详查rybh
                        //System.out.println("list77777="+list.toString());
                    if (list.size() > 0) {
                        for (HashMap<String, Object> key : list) {
                            if (key != null && !"".equals(key)) {
                                Object rybh = key.get("rybh");
                                if (rybh != null && !"".equals(rybh.toString())) {
                                    String rybh1 = rybh.toString();
                                    ArrayList<Map<String, Object>> zsryxx = jbxxService.find_zsryxx("", rybh1, jsbh, "", "", "");//掉今日入所方法
                                    if (zsryxx.size() > 0) {
                                        for (Map<String, Object> key1 : zsryxx) {
                                            String xm = "暂无姓名";
                                            String xb = "暂无性别";
                                            String jkzk = "暂无健康状况";
                                            String bahj = "暂无办案环节";
                                            String ay = "暂无案由";
                                            String wxdj = "暂无危险等级";
                                            String rybh2 = "";
                                            if (key1 != null && !"".equals(key1)) {
                                                Object xm1 = key1.get("xm");
                                                if (xm1 != null && !"".equals(xm1.toString())) {
                                                    xm = xm1.toString();
                                                }
                                                Object xb1 = key1.get("xb");
                                                if (xb1 != null && !"".equals(xb1.toString())) {
                                                    xb = xb1.toString();
                                                }
                                                Object jkzk1 = key1.get("jkzk");
                                                if (jkzk1 != null && !"".equals(jkzk1.toString())) {
                                                    jkzk = jkzk1.toString();
                                                }
                                                Object bahj1 = key1.get("bahj");
                                                if (bahj1 != null && !"".equals(bahj1.toString())) {
                                                    bahj = bahj1.toString();
                                                }
                                                Object ay1 = key1.get("ay");
                                                if (ay1 != null && !"".equals(ay1.toString())) {

                                                    ay = CacheUtils.get().getDictionarys("AJLB", ay1.toString());
                                                }
                                                Object wxdj1 = key1.get("wxdj");
                                                if (wxdj1 != null && !"".equals(wxdj1.toString())) {
                                                    wxdj = wxdj1.toString();
                                                }

                                                Object rybh3 = key1.get("rybh");
                                                if (rybh3 != null && !"".equals(rybh3.toString())) {
                                                    rybh2 = rybh3.toString();
                                                }
                                            }
                                            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                                            k++;
                                            map.put("xl", k);
                                            map.put("item1", xm);
                                            map.put("item2", xb);
                                            map.put("item3", jkzk);
                                            map.put("item4", bahj);
                                            map.put("item5", ay);
                                            map.put("item6", wxdj);
                                            map.put("item7", rybh2);
                                            list2.add(map);
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                } else {
                    ArrayList<HashMap<String, Object>> list = jbxxService.select_wgcd_rybh(jsbh, wgqk); //大屏kss查看违规程度=里面联动下面的数据=数据详查rybh
                    System.out.println("list66666="+list.toString());
                    if(list.size()>0){
                        for(HashMap<String, Object> key:list){
                            if(key!=null&&!"".equals(key)){
                                Object rybh = key.get("rybh");
                                if (rybh != null && !"".equals(rybh.toString())) {
                                    String rybh1= rybh.toString();
                                    ArrayList<Map<String, Object>> zsryxx = jbxxService.find_zsryxx("", rybh1, jsbh, "", "", "");//掉今日入所方法
                                    if(zsryxx.size()>0){
                                        for(Map<String, Object> key1:zsryxx){
                                            String xm = "暂无姓名";
                                            String xb = "暂无性别";
                                            String jkzk = "暂无健康状况";
                                            String bahj = "暂无办案环节";
                                            String ay = "暂无案由";
                                            String wxdj = "暂无危险等级";
                                            String rybh2 = "";
                                            if(key1!=null&&!"".equals(key1)){
                                                Object xm1 = key1.get("xm");
                                                if(xm1!=null&&!"".equals(xm1.toString())){
                                                    xm=xm1.toString();
                                                }
                                                Object xb1 = key1.get("xb");
                                                if(xb1!=null&&!"".equals(xb1.toString())){
                                                    xb=xb1.toString();
                                                }
                                                Object jkzk1 = key1.get("jkzk");
                                                if(jkzk1!=null&&!"".equals(jkzk1.toString())){
                                                    jkzk=jkzk1.toString();
                                                }
                                                Object bahj1 = key1.get("bahj");
                                                if(bahj1!=null&&!"".equals(bahj1.toString())){
                                                    bahj=bahj1.toString();
                                                }
                                                Object ay1 = key1.get("ay");
                                                if(ay1!=null&&!"".equals(ay1.toString())){

                                                    ay= CacheUtils.get().getDictionarys("AJLB", ay1.toString());
                                                }
                                                Object wxdj1 = key1.get("wxdj");
                                                if(wxdj1!=null&&!"".equals(wxdj1.toString())){
                                                    wxdj=wxdj1.toString();
                                                }

                                                Object rybh3 = key1.get("rybh");
                                                if(rybh3!=null&&!"".equals(rybh3.toString())){
                                                    rybh2=rybh3.toString();
                                                }
                                            }
                                            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                                            k++;
                                            map.put("xl",k); map.put("item1",xm);
                                            map.put("item2",xb); map.put("item3",jkzk);
                                            map.put("item4",bahj); map.put("item5",ay);
                                            map.put("item6",wxdj); map.put("item7",rybh2);
                                            list2.add(map);
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

            List<Object> titles = new ArrayList<>();
            Map<String, Object> column0 = new LinkedHashMap<>();
            column0.put("series", "xl");
            column0.put("name", "序列");
            titles.add(column0);
            Map<String, Object> column1 = new LinkedHashMap<>();
            column1.put("series", "item1");
            column1.put("name", "姓名");
            titles.add(column1);
            Map<String, Object> column2 = new LinkedHashMap<>();
            column2.put("series", "item2");
            column2.put("name", "性别");
            titles.add(column2);
            Map<String, Object> column3 = new LinkedHashMap<>();
            column3.put("series", "item3");
            column3.put("name", "健康状况");
            titles.add(column3);
            Map<String, Object> column4 = new HashMap<>();
            column4.put("series", "item4");
            column4.put("name", "办案环节");
            titles.add(column4);
            Map<String, Object> column5 = new HashMap<>();
            column5.put("series", "item5");
            column5.put("name", "案由");
            titles.add(column5);
            Map<String, Object> column6 = new HashMap<>();
            column6.put("series", "item6");
            column6.put("name", "风险等级");
            titles.add(column6);

            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("titles",titles);
            map.put("data",list2);

            LinkedHashMap<String, Object> map1 = new LinkedHashMap<>();
            map1.put("data",map);
            map1.put("msg","");
            map1.put("code",200);


            return map1;
        } catch (Exception e) {
            e.printStackTrace();
            LinkedHashMap<String, Object> result = new LinkedHashMap<>();
            result.put("select_wgcd_rybh", "大屏监区违规程度点击==根据违规类型=一般违规");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }
    
    @ApiOperation("在押人员风险因素")
    @GetMapping("/kssdpzyryFxys")
    @OpenAPI
    public Map<String, Object> dpzyryFxys(@RequestParam(required = false, value = "jsbh") String jsbh,@RequestParam(required = false, value = "rybh") String rybh) {
        Map<String, Object> map = jbxxService.dpzyryFxys(jsbh, rybh);
        return map;
    }

    @GetMapping("/rqcxR")
    @ApiOperation("入所情况")
    @OpenAPI
    public Map<String, Object> rsdj_rq(@RequestParam(value="starttime",required = false) String starttime,
                                       @RequestParam(value="endtime",required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<Map<String, Object>> list= jbxxService.jbxx_rqcxR(starttime,endtime);

        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }


    @GetMapping("/swj_rsqk")
    @ApiOperation("入所情况查询=上位机版本")
    @OpenAPI
    public Map<String, Object> swj_rsqk(@RequestParam(value="starttime",required = false) String starttime,
                                       @RequestParam(value="endtime",required = false) String endtime){
        Map<String, Object> result=new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<AnalysisResultVO> list= jbxxService.swj_rsqk(starttime,endtime);

        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }

}