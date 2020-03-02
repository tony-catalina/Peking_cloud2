package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_JbxxService;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/jls_jbxx")
@RefreshScope
@Api(tags = "jls_jbxx",description = "基本信息")
public class Jls_JbxxController {
	@Autowired
	private Jls_JbxxService jls_jbxxService;


	@GetMapping("/jls_swj_ryflfxCount")
	@ApiOperation("人员分类分析=上位机版本")
	@OpenAPI
	public ResponseMessage<?> swj_ryflfxCount(@RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime) {

		try {
			List<Map<String, Object>> maps = jls_jbxxService.swj_hyzklh(starttime, endtime);
			List<Map<String, Object>> maps1 = jls_jbxxService.swj_hyzkso(starttime, endtime);
			List<Map<String, Object>> maps2 = jls_jbxxService.swj_jkzkslqx(starttime, endtime);
			List<Map<String, Object>> maps3 = jls_jbxxService.swj_jkzkcj(starttime, endtime);
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
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//离婚人数
			List<Map<String, Object>> lhlist = jls_jbxxService.hyzklh();
			int[] lh = new int[]{0, 0, 0, 0, 0};
			for (Map<String, Object> maps : lhlist) {
				for (int i = 0; i < jsbh.length; i++) {
					if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
						lh[i] = lh[i] + Integer.parseInt(maps.get("sl").toString());
					}
				}
			}
			//丧偶人数
			List<Map<String, Object>> solist = jls_jbxxService.hyzkso();
			int[] so = new int[]{0, 0, 0, 0, 0};
			for (Map<String, Object> maps : solist) {
				for (int i = 0; i < jsbh.length; i++) {
					if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
						System.err.println(maps.get("jsbh"));
						so[i] = so[i] + Integer.parseInt(maps.get("sl").toString());
					}
				}
			}
			//有生理缺陷
			List<Map<String, Object>> slqxlist = jls_jbxxService.jkzkslqx();
			int[] slqx = new int[]{0, 0, 0, 0, 0};
			for (Map<String, Object> maps : slqxlist) {
				for (int i = 0; i < jsbh.length; i++) {
					if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
						slqx[i] = slqx[i] + Integer.parseInt(maps.get("sl").toString());
					}
				}
			}
			//残疾人数
			List<Map<String, Object>> cjlist = jls_jbxxService.jkzkcj();
			int[] cz = new int[]{0, 0, 0, 0, 0};
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

	@GetMapping("/clgyfx")
	@ApiOperation("超量关押分析")
	@OpenAPI
	public Map<String, Object> getClgyfxnum(@RequestParam(value="starttime", required = false) String starttime,@RequestParam(value="endtime", required = false) String endtime){
		Map<String, Object> result = new HashMap<String, Object>();
		if(starttime != null){
			starttime += " 00:00:00";
		}
		if(endtime != null){
			endtime += " 23:59:59";
		}
		List<Map<String, Object>> list = jls_jbxxService.queryClgyfxList(starttime,endtime);
		if (list == null ||list.size() == 0) {
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

	@GetMapping("/zdryCx")
	@ApiOperation("重点人员分析")
	@OpenAPI
	public Map<String , Object> zdry_rq(@RequestParam(value = "starttime",required = false) String starttime,
										@RequestParam(value = "endtime",required = false) String endtime){
		if(starttime != null){
			starttime += " 00:00:00";
		}
		if(endtime != null ){
			endtime += " 23:59:59";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list= jls_jbxxService.Zdry_rqFx(starttime ,endtime);
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

	@GetMapping("/find_wgcd")
	@ApiOperation("违规程度分析")
	@OpenAPI
	public ResponseMessage< ArrayList<Object>> select_wgcd( @RequestParam(required = false,value = "jsbh") String jsbh)
			 {
		return jls_jbxxService.select_wgcd(jsbh);

	}

	@GetMapping("/select_wglx")
	@ApiOperation("违规情况分析")
	@OpenAPI
	public ResponseMessage<?> select_wglx( @RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime)
	{
		try {
			ArrayList<HashMap<String, Object>> hashMaps = jls_jbxxService.select_wglx(starttime, endtime);
			return ResponseMessage.ok(hashMaps);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}

	}

	@GetMapping("/swj_wglx")
	@ApiOperation("上位机违规情况分析")
	@OpenAPI
	public ResponseMessage<?> swj_wglx( @RequestParam(required = false, value = "starttime") String starttime, @RequestParam(required = false, value = "endtime") String endtime)
	{
		try {
			List<HashMap<String, Object>> hashMaps = jls_jbxxService.swj_wglx(starttime, endtime);
			return ResponseMessage.ok(hashMaps);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}

	}

    @GetMapping("/swj_cqjyCount")
    @ApiOperation("超期羁押=上位机版本")
    @OpenAPI
    public ResponseMessage<?> swj_cqjyCount(@RequestParam(required = false,value = "starttime") String starttime,  @RequestParam(required = false,value = "endtime") String endtime) {

        try {
            List<AnalysisJlsResultVO> maps = jls_jbxxService.swj_cqjyCount(starttime, endtime);
            return ResponseMessage.ok(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }

	@GetMapping("/cqjyCount")
	@ApiOperation("jls超期羁押")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> cqjyCount() {
		//定义地区  与前台顺序对应
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//超期羁押数量
			List<Map<String, Object>> cqjylist = jls_jbxxService.cqjyCount();
			int[] cqjy = new int[]{0, 0, 0, 0, 0};
			for (Map<String, Object> maps : cqjylist) {
				for (int i = 0; i < jsbh.length; i++) {
					if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
						cqjy[i] = cqjy[i] + Integer.parseInt(maps.get("sl").toString());
					}
				}
			}
			//关押总数
			List<Map<String, Object>> alljbxxlist = jls_jbxxService.alljbxxCount();

			int[] alljbxx = new int[]{0, 0, 0, 0, 0};
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
			Double[] value = new Double[5];
			for (int i = 0; i < jsbh.length; i++) {
				if (alljbxx[i] != 0 ) {
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


	@GetMapping("/jls_rscx")
	@ApiOperation(value = "jls入所统计")
	@OpenAPI
	public Map<String,Object> jls_rscx(@RequestParam(value = "starttime",required = false) String starttime, @RequestParam(value = "endtime",required = false) String endtime ){

		Map<String, Object> result=new HashMap<String, Object>();
		if(starttime != null){
			starttime += " 00:00:00";
		}
		if(endtime != null){
			endtime += " 23:59:59";
		}
		List<AnalysisJlsResultVO> list= jls_jbxxService.jls_rscx(starttime,endtime);
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




	@GetMapping("/zcrsCount")
	@ApiOperation("业务动态")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> zcrsCount(@RequestParam(value = "jsbh", required = false) String jsbh,
														  @RequestParam(value = "starttime", required = false) String starttime,
														  @RequestParam(value = "endtime", required = false) String endtime) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<Map<String, Object>> list = jls_jbxxService.zcrsCount(jsbh, starttime, endtime);
			result.put("zcrs", list);
			return ResponseMessage.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("执行失败");
		}
	}

	@GetMapping("/jsxkh")
	@ApiOperation("及时性考核")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> jsxkh(@RequestParam(value = "jsbh", required = false) String jsbh,
													  @RequestParam(value = "starttime", required = false) String starttime) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<Map<String, Object>> list = jls_jbxxService.jsxkh(jsbh, starttime);
			result.put("jsxkh", list);
			return ResponseMessage.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("执行失败");
		}
	}

	@GetMapping("/dpzsry")
	@ApiOperation("大屏在所人员")
	@OpenAPI
	public Map<String, Object> dpzsry(@RequestParam(value = "jsbh", required = false) String jsbh) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, Object> datelist = new HashMap<String, Object>();
			Map<String, Object> list = jls_jbxxService.dpzsry(jsbh);
			List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
			for (String key : list.keySet()) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("name", key);
				maps.put("msg", list.get(key));
				if ("今日入所".equals(key)) {
					maps.put("class", "people");
				}
				maplist.add(maps);
			}
			datelist.put("name", "在所人员");
			datelist.put("list", maplist);
			Map<String, Object> msp = new HashMap<String, Object>();
			msp.put("zsry", datelist);
			result.put("data", msp);
			result.put("code", 200);
			result.put("msg", "查询成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
			result.put("code", 500);
			result.put("msg", "查询失败");
			return result;
		}
	}

	@GetMapping("/jlsdpaqgl")
	@ApiOperation("大屏安全管理")
	@OpenAPI
	public Map<String, Object> dpaqgl(@RequestParam(value = "jsbh", required = false) String jsbh) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			Map<String, Object> list = jls_jbxxService.dpaqgl(jsbh);
			Map<String, Object> data = new HashMap<String, Object>();
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
					result.add(maps);
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
				result.add(maps);
			}
			data.put("aqgl", result);
			map.put("code", 200);
			map.put("data", data);
			map.put("msg", "查询成功");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
			result.put("code", 500);
			result.put("msg", "查询失败");
			return result;
		}
	}

	@GetMapping("/jlsdptxhj")
	@ApiOperation("大屏提讯会见")
	@OpenAPI
	public Map<String, Object> dptxhj(@RequestParam(value = "jsbh", required = false) String jsbh) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, Object> list = jls_jbxxService.dptxhj(jsbh);
			List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
			for (String key : list.keySet()) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("name", key);
				maps.put("msg", list.get(key));
				listmap.add(maps);
			}
			result.put("msg", "查询成功");
			result.put("code", 200);
			result.put("data", listmap);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
			result.put("code", 500);
			result.put("msg", "查询失败");
			return result;
		}
	}

	@GetMapping("/jlsdpcqjy")
	@ApiOperation("大屏关押期限")
	@OpenAPI
	public Map<String, Object> dpcqjy(@RequestParam(value = "jsbh", required = false) String jsbh) {
		try {
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> list = jls_jbxxService.dpcqjy(jsbh);
			for (String key : list.keySet()) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("name", key);
				maps.put("msg", list.get(key));
				result.add(maps);
			}
			map.put("msg", "查询成功");
			map.put("code", 200);
			map.put("data", result);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
			result.put("code", 500);
			result.put("msg", "查询失败");
			return result;
		}
	}

	@GetMapping("/jlsdprsxz")
	@ApiOperation("大屏诉讼情况分类")
	@OpenAPI
	public Map<String, Object> dprsxz(@RequestParam(value = "jsbh", required = false) String jsbh) {
		try {
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> list = jls_jbxxService.dprsxz(jsbh);
			for (String key : list.keySet()) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("name", key);
				maps.put("value", list.get(key));
				result.add(maps);
			}
			map.put("msg", "查询成功");
			map.put("code", 200);
			map.put("data", result);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
			result.put("code", 500);
			result.put("msg", "查询失败");
			return result;
		}
	}

	@GetMapping("/jlsdpajlb")
	@ApiOperation("大屏案由情况分类")
	@OpenAPI
	public Map<String, Object> dpajlb(@RequestParam(value = "jsbh", required = false) String jsbh) {
		try {
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> list = jls_jbxxService.dpajlb(jsbh);
			int i = 0;
			for (String key : list.keySet()) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("name", key);
				maps.put("value", list.get(key));
				i += Integer.parseInt(list.get(key).toString());
				result.add(maps);
			}
			map.put("msg", "查询成功");
			map.put("code", 200);
			map.put("sum", i);
			map.put("data", result);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
			result.put("code", 500);
			result.put("msg", "查询失败");
			return result;
		}
	}

	@GetMapping("/jlsdpjqhdkp")
	@ApiOperation("大屏各监区数据")
	@OpenAPI
	public Map<String, Object> jlsdpjqhdkp(@RequestParam(value = "jsbh", required = false) String jsbh) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = jls_jbxxService.dpjqhdkp(jsbh);
			map.put("msg", "查询成功");
			map.put("code", 200);
			map.put("data", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
			result.put("code", 500);
			result.put("msg", "查询失败");
			return result;
		}
	}

	@ApiOperation("大屏监区违规程度分析")
	@GetMapping("/jlsdpzyryFxys")
	@OpenAPI
	public Map<String, Object> dpzyryFxys(@RequestParam(required = false, value = "jsbh") String jsbh,
										  @RequestParam(required = false, value = "rybh") String rybh) {
		Map<String, Object> map = jls_jbxxService.dpzyryFxys(rybh);
		return map;
	}

	@ApiOperation("大屏在押人员案件分析")
	@GetMapping("/select_ajfx")
	@OpenAPI
	public Map<String, Object> select_ajfx(@RequestParam(required = false, value = "jsbh") String jsbh,
										   @RequestParam(required = false, value = "rybh") String rybh) {
		Map<String, Object> map = jls_jbxxService.select_ajfx(rybh);
		return map;
	}

	@ApiOperation("大屏在押人员健康详情")
	@GetMapping("/select_jy")
	@OpenAPI
	public Map<String, Object> select_jy(@RequestParam(required = false, value = "jsbh") String jsbh,
										 @RequestParam(required = false, value = "rybh") String rybh) {
		Map<String, Object> map = jls_jbxxService.select_jy(rybh);
		return map;
	}

	@GetMapping("/zyrygyl")
	@ApiOperation("在押人员关押量分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> zyrygyl() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jls_jbxxService.zyrygyl();
			int[] man = new int[]{0, 0, 0, 0, 0};
			int[] woman = new int[]{0, 0, 0, 0, 0};
			int[] unknow = new int[]{0, 0, 0, 0, 0};
			int[] zs = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
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
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zs[i] = zs[i] + Integer.parseInt(maps.get("zs").toString());
						}
					}
				}
				map.put("man", man);
				map.put("woman", woman);
				map.put("unknow", unknow);
				map.put("zs", zs);
				return ResponseMessage.ok(map);
			} else {
				map.put("man", 0);
				map.put("woman", 0);
				map.put("unknow", 0);
				map.put("zs", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/hjdfx")
	@ApiOperation("户籍地分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> hjdfx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jls_jbxxService.hjdfx();
			int[] bs = new int[]{0, 0, 0, 0, 0};
			int[] ws = new int[]{0, 0, 0, 0, 0};
			int[] wg = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							bs[i] = bs[i] + Integer.parseInt(maps.get("bs").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ws[i] = ws[i] + Integer.parseInt(maps.get("ws").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							wg[i] = wg[i] + Integer.parseInt(maps.get("wg").toString());
						}
					}
				}
				map.put("bs", bs);
				map.put("ws", ws);
				map.put("wg", wg);
				return ResponseMessage.ok(map);
			} else {
				map.put("bs", 0);
				map.put("ws", 0);
				map.put("wg", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/gjfx")
	@ApiOperation("国籍分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> gjfx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jls_jbxxService.gjfx();
			int[] zg = new int[]{0, 0, 0, 0, 0};
			int[] zgtw = new int[]{0, 0, 0, 0, 0};
			int[] zgxg = new int[]{0, 0, 0, 0, 0};
			int[] zgam = new int[]{0, 0, 0, 0, 0};
			int[] wg = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zg[i] = zg[i] + Integer.parseInt(maps.get("zg").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zgtw[i] = zgtw[i] + Integer.parseInt(maps.get("zgtw").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zgxg[i] = zgxg[i] + Integer.parseInt(maps.get("zgxg").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zgam[i] = zgam[i] + Integer.parseInt(maps.get("zgam").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							wg[i] = wg[i] + Integer.parseInt(maps.get("wg").toString());
						}
					}
				}
				map.put("zg", zg);
				map.put("zgtw", zgtw);
				map.put("zgxg", zgxg);
				map.put("zgam", zgam);
				map.put("wg", wg);
				return ResponseMessage.ok(map);
			} else {
				map.put("zg", zg);
				map.put("zgtw", zgtw);
				map.put("zgxg", zgxg);
				map.put("zgam", zgam);
				map.put("wg", wg);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}

	@GetMapping("/nlfx")
	@ApiOperation("年龄分析")
	@OpenAPI
	public ResponseMessage<List<Map<String, Object>>> nlfx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			//<!--按照年龄分 -->
			List<Map<String, Object>> gyqxfxCountlist = jls_jbxxService.nlfx();
			for (int i = 0; i < jsbh.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				int[] s = new int[]{0, 0, 0, 0};
				for (Map<String, Object> maps : gyqxfxCountlist) {
					if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
						s[0] = s[0] + Integer.parseInt(maps.get("sn").toString());
						s[1] = s[1] + Integer.parseInt(maps.get("qn").toString());
						s[2] = s[2] + Integer.parseInt(maps.get("zn").toString());
						s[3] = s[3] + Integer.parseInt(maps.get("zln").toString());
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

	@GetMapping("/whcd")
	@ApiOperation("文化程度分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> whcd() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jls_jbxxService.whcd();
			int[] wm = new int[]{0, 0, 0, 0, 0};
			int[] xx = new int[]{0, 0, 0, 0, 0};
			int[] zx = new int[]{0, 0, 0, 0, 0};
			int[] dzys = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							wm[i] = wm[i] + Integer.parseInt(maps.get("wm").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							xx[i] = xx[i] + Integer.parseInt(maps.get("xx").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zx[i] = zx[i] + Integer.parseInt(maps.get("zx").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							dzys[i] = dzys[i] + Integer.parseInt(maps.get("dzys").toString());
						}
					}
				}
				map.put("wm", wm);
				map.put("xx", xx);
				map.put("zx", zx);
				map.put("dzys", dzys);
				return ResponseMessage.ok(map);
			} else {
				map.put("wm", wm);
				map.put("xx", xx);
				map.put("zx", zx);
				map.put("dzys", dzys);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/sffx")
	@ApiOperation("身份分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> sffx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jls_jbxxService.sffx();
			int[] gjgwy = new int[]{0, 0, 0, 0, 0};
			int[] qyglry = new int[]{0, 0, 0, 0, 0};
			int[] gr = new int[]{0, 0, 0, 0, 0};
			int[] nm = new int[]{0, 0, 0, 0, 0};
			int[] zxxs = new int[]{0, 0, 0, 0, 0};
			int[] gtgsry = new int[]{0, 0, 0, 0, 0};
			int[] ltxry = new int[]{0, 0, 0, 0, 0};
			int[] wyry = new int[]{0, 0, 0, 0, 0};
			int[] jr = new int[]{0, 0, 0, 0, 0};
			int[] qt = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gjgwy[i] = gjgwy[i] + Integer.parseInt(maps.get("gjgwy").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qyglry[i] = qyglry[i] + Integer.parseInt(maps.get("qyglry").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gr[i] = gr[i] + Integer.parseInt(maps.get("gr").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							nm[i] = nm[i] + Integer.parseInt(maps.get("nm").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zxxs[i] = zxxs[i] + Integer.parseInt(maps.get("zxxs").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							gtgsry[i] = gtgsry[i] + Integer.parseInt(maps.get("gtgsry").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ltxry[i] = ltxry[i] + Integer.parseInt(maps.get("ltxry").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							wyry[i] = wyry[i] + Integer.parseInt(maps.get("wyry").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							jr[i] = jr[i] + Integer.parseInt(maps.get("jr").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							qt[i] = qt[i] + Integer.parseInt(maps.get("qt").toString());
						}
					}
				}
				map.put("gjgwy", gjgwy);
				map.put("qyglry", qyglry);
				map.put("gr", gr);
				map.put("nm", nm);
				map.put("zxxs", zxxs);
				map.put("gtgsry", gtgsry);
				map.put("ltxry", ltxry);
				map.put("wyry", wyry);
				map.put("jr", jr);
				map.put("qt", qt);
				return ResponseMessage.ok(map);
			} else {
				map.put("gjgwy", 0);
				map.put("qyglry", 0);
				map.put("gr", 0);
				map.put("nm", 0);
				map.put("zxxs", 0);
				map.put("gtgsry", 0);
				map.put("ltxry", 0);
				map.put("wyry", 0);
				map.put("jr", 0);
				map.put("qt", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/jkqkfx")
	@ApiOperation("健康情况分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> jkqkfx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jls_jbxxService.jkqkfx();
			int[] jkhlh = new int[]{0, 0, 0, 0, 0};
			int[] ybhjr = new int[]{0, 0, 0, 0, 0};
			int[] yb = new int[]{0, 0, 0, 0, 0};
			int[] slqx = new int[]{0, 0, 0, 0, 0};
			int[] cf = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							jkhlh[i] = jkhlh[i] + Integer.parseInt(maps.get("jkhlh").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ybhjr[i] = ybhjr[i] + Integer.parseInt(maps.get("ybhjr").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							yb[i] = yb[i] + Integer.parseInt(maps.get("yb").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							slqx[i] = slqx[i] + Integer.parseInt(maps.get("slqx").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							cf[i] = cf[i] + Integer.parseInt(maps.get("cf").toString());
						}
					}
				}
				map.put("jkhlh", jkhlh);
				map.put("ybhjr", ybhjr);
				map.put("yb", yb);
				map.put("slqx", slqx);
				map.put("cf", cf);
				return ResponseMessage.ok(map);
			} else {
				map.put("jkhlh", 0);
				map.put("ybhjr", 0);
				map.put("yb", 0);
				map.put("slqx", 0);
				map.put("cf", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
	@GetMapping("/bhfx")
	@ApiOperation("病号分析")
	@OpenAPI
	public ResponseMessage<Map<String, Object>> bhfx() {
		int[] jsbh = new int[]{110000121, 110101121, 110105121, 110108121, 110111121};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> lslsfxCountlist = jls_jbxxService.bhfx();
			int[] zdbh = new int[]{0, 0, 0, 0, 0};
			int[] ptbh = new int[]{0, 0, 0, 0, 0};
			int[] jk = new int[]{0, 0, 0, 0, 0};
			if (lslsfxCountlist.size() > 0 && !lslsfxCountlist.isEmpty()) {
				for (Map<String, Object> maps : lslsfxCountlist) {
					for (int i = 0; i < jsbh.length; i++) {
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							zdbh[i] = zdbh[i] + Integer.parseInt(maps.get("zdbh").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							ptbh[i] = ptbh[i] + Integer.parseInt(maps.get("ptbh").toString());
						}
						if (maps.get("jsbh").toString().contains(String.valueOf(jsbh[i]))) {
							jk[i] = jk[i] + Integer.parseInt(maps.get("jk").toString());
						}
					}
				}
				map.put("zdbh", zdbh);
				map.put("ptbh", ptbh);
				map.put("jk", jk);
				return ResponseMessage.ok(map);
			} else {
				map.put("zdbh", 0);
				map.put("ptbh", 0);
				map.put("jk", 0);
				return ResponseMessage.ok(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败");
		}
	}
}