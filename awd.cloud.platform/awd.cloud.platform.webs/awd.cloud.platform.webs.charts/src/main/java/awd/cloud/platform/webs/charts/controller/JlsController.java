package awd.cloud.platform.webs.charts.controller;

import awd.cloud.platform.webs.charts.api.AnalyseApis;
import awd.cloud.platform.webs.charts.api.JlsServersApi;
import awd.cloud.platform.webs.charts.modal.jls.FlwsbgModel;
import awd.cloud.platform.webs.charts.modal.jls.JbxxModel;
import awd.cloud.platform.webs.charts.modal.jls.PhotosModel;
import awd.cloud.platform.webs.charts.modal.jls.XsjlModel;
import awd.cloud.platform.webs.charts.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/jls")
public class JlsController {
	@Autowired
	private AnalyseApis analyseApis;
	
	@Autowired
	private JlsServersApi jlsServersApi;
	/**
	 * 拘留所大屏人员查询
	 * @param request
	 * @return
	 */
	@GetMapping("/jbxxlist")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏人员查询")
	public Map<String, Object> jbxxlist(HttpServletRequest request) {
		QueryParam param = new QueryParam();
		param.and("state", "R8");
		String jsbh = request.getParameter("jsbh");
		String rsxz = request.getParameter("rsxz");
		String crjbj = request.getParameter("crjbj");
		String zdzy = request.getParameter("zdzy");
		String jqh = request.getParameter("jqh");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isNullOrEmpty(request.getParameter("rsrq"))){
			 param.and("rsrq", TermType.gte,sd.format(new Date())+" 00:00:00");
			 param.and("rsrq", TermType.lte,sd.format(new Date())+" 23:59:59");
		}
		if(!StringUtils.isNullOrEmpty(crjbj)) {
			param.and("crjbj", crjbj);
		}
		if(!StringUtils.isNullOrEmpty(rsxz)){
			 if(!"qt".equals(rsxz)) {
				 param.and("rsxz", rsxz);
			 }else {
				 param.and("rsxz", TermType.nin, "10,11,12,13,14,15,16,17,18,19,20,21");
			 }
		}
		param.and("jsbh", jsbh);
		if(!StringUtils.isNullOrEmpty(jqh)) {
			param.and("jsh", TermType.like,jqh+"%");
		}
		ResponseMessage<PagerResult<JbxxModel>> list = jlsServersApi.jbxxQueryForPage(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("msg", "查询成功");
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		int i = 0;
		for (JbxxModel JbxxModel : list.getResult().getData()) {
			System.out.println("----------------"+"我进来了");
			i++;
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("xl", i);
			maps.put("item1", JbxxModel.getXm());
			maps.put("item2", JbxxModel.getXbString());
			maps.put("item3", JbxxModel.getJsh());
			maps.put("item4", JbxxModel.getRsxzString());
			maps.put("item5", JbxxModel.getAyString());
			maps.put("item6", JbxxModel.getFxdjString());
			maps.put("item7", JbxxModel.getRybh());
			listmap.add(maps);
		}
		data.put("data", listmap);
		Map<String, Object> msssp = new HashMap<String, Object>();
		data.put("seriesNum", "7");
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> msp = new HashMap<String, Object>();
		msp.put("series", "xl");
		msp.put("name", "序列");
		Map<String, Object> msp1 = new HashMap<String, Object>();
		msp1.put("series", "item1");
		msp1.put("name", "姓名");
		Map<String, Object> msp2 = new HashMap<String, Object>();
		msp2.put("series", "item2");
		msp2.put("name", "性别");
		Map<String, Object> msp3 = new HashMap<String, Object>();
		msp3.put("series", "item3");
		msp3.put("name", "监室号");
		Map<String, Object> msp4 = new HashMap<String, Object>();
		msp4.put("series", "item4");
		msp4.put("name", "入所性质");
		Map<String, Object> msp5 = new HashMap<String, Object>();
		msp5.put("series", "item5");
		msp5.put("name", "案由");
		Map<String, Object> msp6 = new HashMap<String, Object>();
		msp6.put("series", "item6");
		msp6.put("name", "风险等级");
		maps.add(msp);
		maps.add(msp1);
		maps.add(msp2);
		maps.add(msp3);
		maps.add(msp4);
		maps.add(msp5);
		maps.add(msp6);
		data.put("titles", maps);
		Map<String, Object> ms = new HashMap<String, Object>();
		map.put("zsyrjbxx", data);
		return map;
	}
	
	@GetMapping("/jbxxlistByp")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏人员查询")
	public Map<String, Object> jbxxlistByphotos(HttpServletRequest request) {
		QueryParam param = new QueryParam();
		param.and("state", "R8");
		String jsbh = request.getParameter("jsbh");
		String zdzy = request.getParameter("zdzy");
		if(!StringUtils.isNullOrEmpty(zdzy)) {
			param.and("zdzy", zdzy);
		}
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("code", 200);
		mapa.put("msg", "查询成功");
		param.and("jsbh", jsbh);
		List<Map<String, Object>> jbxxlist = new ArrayList<Map<String,Object>>();
		ResponseMessage<PagerResult<JbxxModel>> list = jlsServersApi.jbxxQueryForPage(param);
		int i = 0;

		if(list.getResult().getTotal()!=0) {
			for (JbxxModel jbxxModel : list.getResult().getData()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", i);
				map.put("type", i);
				map.put("name", jbxxModel.getXm());
				map.put("area", jbxxModel.getFxdjString());
				map.put("number", jbxxModel.getJsh());
				Map<String, Object> lists = new LinkedHashMap<String, Object>();
				lists.put("seriesNum", "7");
				List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
				Map<String, Object> msp = new HashMap<String, Object>();
				msp.put("series", "xl");
				msp.put("name", "序列");
				Map<String, Object> msp1 = new HashMap<String, Object>();
				msp1.put("series", "item1");
				msp1.put("name", "姓名");
				Map<String, Object> msp2 = new HashMap<String, Object>();
				msp2.put("series", "item2");
				msp2.put("name", "性别");
				Map<String, Object> msp3 = new HashMap<String, Object>();
				msp3.put("series", "item3");
				msp3.put("name", "监室号");
				Map<String, Object> msp4 = new HashMap<String, Object>();
				msp4.put("series", "item4");
				msp4.put("name", "入所性质");
				Map<String, Object> msp5 = new HashMap<String, Object>();
				msp5.put("series", "item5");
				msp5.put("name", "案由");
				Map<String, Object> msp6 = new HashMap<String, Object>();
				msp6.put("series", "item6");
				msp6.put("name", "风险等级");
				maps.add(msp);
				maps.add(msp1);
				maps.add(msp2);
				maps.add(msp3);
				maps.add(msp4);
				maps.add(msp5);
				maps.add(msp6);
				Map<String, Object> mapss = new HashMap<String, Object>();
				List<Map<String, Object>> jbxxss = new ArrayList<Map<String,Object>>();
				mapss.put("xl", i+1);
				mapss.put("item1", jbxxModel.getXm());
				mapss.put("item2", jbxxModel.getXbString());
				mapss.put("item3", jbxxModel.getJsh());
				mapss.put("item4", jbxxModel.getRsxzString());
				mapss.put("item5", jbxxModel.getAyString());
				mapss.put("item6", jbxxModel.getFxdjString());
				mapss.put("item7", jbxxModel.getRybh());
				lists.put("titles", maps);
				jbxxss.add(mapss);
				lists.put("data", jbxxss);
				map.put("list", lists);
				QueryParam paramss = new QueryParam();
				paramss.and("state", "R2");
				paramss.and("type", "1");
				paramss.and("jsbh", jbxxModel.getJsbh());
				paramss.and("rybh", jbxxModel.getRybh());
				ResponseMessage<PagerResult<PhotosModel>> photo = jlsServersApi.photosQueryForPage(paramss);
				if(photo.getResult().getTotal()>0) {
					map.put("img", photo.getResult().getData().get(0).getPhotourl());
				}else {
					map.put("img", "");
				}
				jbxxlist.add(map);
				i++;
			}
		}
		mapa.put("data", jbxxlist);
		return mapa;
		
	}
	
	
	@GetMapping("/xsjllist")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏人员违规查询")
	public Map<String, Object> xsjllist(HttpServletRequest request) {
		QueryParam param = new QueryParam();
		param.and("state", "R2");
		String jsbh = request.getParameter("jsbh");
		String jjcd = request.getParameter("jjcd");
		String jsh = request.getParameter("jsh");
		param.and("jsbh", jsbh);
		param.and("wglx", "3");
		param.and("jjcd", jjcd);
		if(!StringUtils.isNullOrEmpty(jsh)&&!"undefined".equals(jsh)) {
			param.and("jsh", TermType.like, jsh+"%");
		}
		ResponseMessage<PagerResult<XsjlModel>> list = jlsServersApi.xsjlQueryForPage(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("msg", "查询成功");
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> listmap = new ArrayList<Map<String,Object>>();
		int i = 0;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		for (XsjlModel xsjlModel : list.getResult().getData()) {
			i++;
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("xl", i);
			maps.put("item1", xsjlModel.getXm());
			maps.put("item2", xsjlModel.getJsh());
			maps.put("item3", sd.format(xsjlModel.getFssj()));
			maps.put("item4", xsjlModel.getJjcdString());
			maps.put("item5", xsjlModel.getJyqk());
			maps.put("item6", xsjlModel.getClr());
			listmap.add(maps);
		}
		data.put("data", listmap);
		Map<String, Object> msssp = new HashMap<String, Object>();
		data.put("seriesNum", "6");
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String, Object> msp = new HashMap<String, Object>();
		msp.put("series", "xl");
		msp.put("name", "序列");
		Map<String, Object> msp1 = new HashMap<String, Object>();
		msp1.put("series", "item1");
		msp1.put("name", "姓名");
		Map<String, Object> msp2 = new HashMap<String, Object>();
		msp2.put("series", "item2");
		msp2.put("name", "监室号");
		Map<String, Object> msp3 = new HashMap<String, Object>();
		msp3.put("series", "item3");
		msp3.put("name", "违规时间");
		Map<String, Object> msp5 = new HashMap<String, Object>();
		msp5.put("series", "item4");
		msp5.put("name", "紧急程度");
		Map<String, Object> msp6 = new HashMap<String, Object>();
		msp6.put("series", "item5");
		msp6.put("name", "简要情况");
		Map<String, Object> msp4 = new HashMap<String, Object>();
		msp4.put("series", "item6");
		msp4.put("name", "处理人");
		maps.add(msp);
		maps.add(msp1);
		maps.add(msp2);
		maps.add(msp3);
		maps.add(msp5);
		maps.add(msp6);
		maps.add(msp4);
		data.put("titles", maps);
		Map<String, Object> ms = new HashMap<String, Object>();
		map.put("rywgqk", data);
		return map;
	}
	
	/**
	 * 拘留所大屏在所人员
	 * @param request
	 * @return
	 */
	@GetMapping("/zsryCount")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏在所人员")
	public Map<String, Object> zsryCount(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.dpzsry(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 拘留所大屏在所人员
	 * @param request
	 * @return
	 */
	@GetMapping("/jlsdpaqgl")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏安全管理")
	public Map<String, Object> jlsdpaqgl(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpaqgl(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 拘留所大屏在所人员
	 * @param request
	 * @return
	 */
	@GetMapping("/jlsdpylgl")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏医疗管理")
	public Map<String, Object> jlsdpylgl(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpylgl(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 拘留所大屏在所人员
	 * @param request
	 * @return
	 */
	@GetMapping("/jlsdptxhj")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏会见管理")
	public Map<String, Object> jlsdptxhj(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdptxhj(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 拘留所大屏在所人员
	 * @param request
	 * @return
	 */
	@GetMapping("/jlsdpcqjy")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏关押期限")
	public Map<String, Object> jlsdpcqjy(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpcqjy(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/jlsdpjqwgqst")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所大屏监区违规趋势图")
	public Map<String, Object> jlsdpjqwgqst(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpjqwgqst(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 大屏诉讼情况分类
	 * @param request
	 * @return
	 */
	@GetMapping("/jlsdprsxz")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏诉讼情况分类")
	public Map<String, Object> jlsdprsxz(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdprsxz(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 大屏案由情况分类
	 * @param request
	 * @return
	 */
	@GetMapping("/jlsdpajlb")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏案由情况分类")
	public Map<String, Object> jlsdpajlb(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpajlb(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/jlsdpjqwgry")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏违规程度")
	public Map<String, Object> jlsdpjqwgry(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpjqwgry(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 拘留所大屏在所人员
	 * @param request
	 * @return
	 */
	@GetMapping("/jlsdpjqhdkp")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏各监区数据")
	public Map<String, Object> jlsdpjqhdkp(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpjqhdkp(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/jlsdpjqbb")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏各监区报病")
	public Map<String, Object> jlsdpjqbb(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpjqbb(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/jlsdpzbld")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏值班领导")
	public Map<String, Object> jlsdpzbld(HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String jsbh = request.getParameter("jsbh");
			Map<String, Object> list = analyseApis.jlsdpzbld(jsbh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/jlsgrxx")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所个人基本信息")
	public Map<String, Object> jlsgrxx(HttpServletRequest request) {
		try {
			QueryParam param=new QueryParam();
			String rybh = request.getParameter("rybh");
			param.and("rybh",rybh);
			param.and("state", "R8");
			Map map5=new HashMap();
			Map map = new HashMap();
			Map map1 = new HashMap();
			Map map2 = new HashMap();
			Map map3 = new HashMap();
			Map map4 = new HashMap();
			ResponseMessage<PagerResult<JbxxModel>> list = jlsServersApi.jbxxQueryForPage(param);
			List list1=new ArrayList();
			for (JbxxModel jbxxModel : list.getResult().getData()) {
				map.put("name","姓名" );
				map.put("msg",jbxxModel.getXm());
				map1.put("name","性别" );
				map1.put("msg",jbxxModel.getXbString());
				map2.put("name","民族" );
				map2.put("msg",jbxxModel.getMzString());
				map3.put("name","身份证号" );
				map3.put("msg",jbxxModel.getZjh());
				map4.put("name","入所原因" );
				map4.put("msg",jbxxModel.getAyString());
                list1.add(map);
                list1.add(map1);
                list1.add(map2);
                list1.add(map3);
				list1.add(map4);
				map5.put("msgList",list1);
				QueryParam paramss = new QueryParam();
				paramss.and("state", "R2");
				paramss.and("type", "1");
				paramss.and("jsbh", jbxxModel.getJsbh());
				paramss.and("rybh", jbxxModel.getRybh());
				ResponseMessage<PagerResult<PhotosModel>> photo = jlsServersApi.photosQueryForPage(paramss);
				if(photo.getResult().getTotal()>0) {
					map5.put("img", photo.getResult().getData().get(0).getPhotourl());
				}else {
					map5.put("img", "");
				}
			}
			map5.put("name","基本信息");
			map5.put("msg","短信关注");
			Map lt=new HashMap();
			lt.put("lt",map5);
			Map map6=new HashMap();
			map6.put("manage",lt);
			map6.put("code",200);
			map6.put("msg","");
			return map6;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/ryqk")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所个人情况")
	public Map<String, Object> ryqk(HttpServletRequest request) {
		try {
			QueryParam param=new QueryParam();
			String rybh = request.getParameter("rybh");
			String jsbh = request.getParameter("jsbh");
			param.and("rybh",rybh);
			param.and("state", "R8");
			ResponseMessage<PagerResult<JbxxModel>> list = jlsServersApi.jbxxQueryForPage(param);
			List list1=new ArrayList();
			Map map=new HashMap();
			Map map1=new HashMap();
			for (JbxxModel jbxxModel : list.getResult().getData()) {
                map.put("name","案由");
				map.put("value",jbxxModel.getAyString());
				map1.put("name","入所时间");
				Date date = jbxxModel.getRsrq();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String format1 = format.format(date);
				map1.put("value",format1);
				list1.add(map);
				list1.add(map1);
				}
			Map map2=new HashMap();
			map2.put("list",list1);
			map2.put("name","案件情况");
			Map<String, Object> jkqk = analyseApis.jkqkCount(jsbh,rybh);
			Map map9=new HashMap();
			map9.put("name","精神状态");
			map9.put("value","暂无精神状态的数据");
			List list2=new ArrayList();
			list2.add(map9);
			Map map10=new HashMap();
			map10.put("name","精神状况");
			map10.put("list",list2);
			Map map6=new HashMap();
			map6.put("j",map10);
			map6.put("b",jkqk);
			map6.put("t",map2);
			map6.put("name","人员情况");
			Map map7 =new HashMap();
			map7.put("lc",map6);
			Map map8=new HashMap();
			map8.put("manage",map7);
			map8.put("code",200);
			map8.put("msg","");
			return map8;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@GetMapping("/findRygx")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所个人人员关系")
	public Map<String, Object> findRygx(HttpServletRequest request) {
		try {
			String jsbh = request.getParameter("jsbh");
			String rybh = request.getParameter("rybh");
			String jsh = request.getParameter("jsh");
			/*String jsbh = "110000121";
			String rybh = "110000121201912060001";
			String jsh = "0201";*/
			Map<String, Object> rygx = analyseApis.findRygx(jsbh, rybh, jsh);
			return rygx;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/jlsflsxbg")
	@ResponseBody
	@OpenAPI
	@ApiOperation("拘留所法律手续变更")
	public Map<String, Object> jlsflsxbg(HttpServletRequest request) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		QueryParam param = new QueryParam();
		String jsbh = request.getParameter("jsbh");
		String rybh = request.getParameter("rybh");
		param.and("state", "R2");
		param.and("jsbh", jsbh);
		param.and("rybh", rybh);
		List<Sort> sorts = new ArrayList<>();
		Sort sort = new Sort();
		sort.setName("createtime");
		sort.setOrder("desc");
		sorts.add(sort);
		param.setSorts(sorts);
		ResponseMessage<PagerResult<FlwsbgModel>> flwslist = jlsServersApi.flsxbgQueryForPage(param);
		List<Map<String, Object>> headlist = new ArrayList<Map<String,Object>>();
		Map<String, Object> headmap2 = new LinkedHashMap<String, Object>();
		headmap2.put("name", "");
		headlist.add(headmap2);
		Map<String, Object> headmap = new LinkedHashMap<String, Object>();
		headmap.put("name", "原拘留日期");
		headlist.add(headmap);
		Map<String, Object> headmap3 = new LinkedHashMap<String, Object>();
		headmap3.put("name", "");
		headlist.add(headmap3);
		Map<String, Object> headmap1 = new LinkedHashMap<String, Object>();
		headmap1.put("name", "现在拘留日期");
		headlist.add(headmap1);
		map.put("head", headlist);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> datalist = new ArrayList<Map<String,Object>>();
		if(flwslist.getResult().getTotal()>0) {
			for (FlwsbgModel flwsbgModel : flwslist.getResult().getData()) {
				Map<String, Object> maps = new LinkedHashMap<String, Object>();
				maps.put("time", sd.format(flwsbgModel.getYjlqx()));
				maps.put("dit", "");
				maps.put("name", sd.format(flwsbgModel.getXjlqx()));
				datalist.add(maps);
			}
		}else {
			Map<String, Object> maps = new LinkedHashMap<String, Object>();
			maps.put("time", "无记录");
			maps.put("dit", "");
			maps.put("name", "无记录");
			datalist.add(maps);
		}
		map.put("body", datalist);
		map.put("msg", "查询成功");
		map.put("code", 200);
		return map;
	}
	@GetMapping("/dpzyryFxys")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏在押人员违规程度")
	public Map<String, Object> dpzyryFxys(HttpServletRequest request) {
		try {
			String jsbh = request.getParameter("jsbh");
			String rybh = request.getParameter("rybh");
			/*String jsbh = "110000121";
			String rybh = "110000121201911260001";*/
			Map<String, Object> list = analyseApis.dpzyryFxys(jsbh,rybh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/select_ajfx")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏在押人员案件分析")
	public Map<String, Object> select_ajfx(HttpServletRequest request) {
		try {
			String jsbh = request.getParameter("jsbh");
			String rybh = request.getParameter("rybh");
			/*String jsbh = "110000121";
			String rybh = "110000121201911260001";*/
			Map<String, Object> list = analyseApis.select_ajfx(jsbh,rybh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/select_jy")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏在押人员健康详情")
	public Map<String, Object> select_jy(HttpServletRequest request) {
		try {
			String jsbh = request.getParameter("jsbh");
			String rybh = request.getParameter("rybh");
			/*String jsbh = "110000121";
			String rybh = "110000121201911260001";*/
			Map<String, Object> list = analyseApis.select_jy(jsbh,rybh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/jlsFxdj")
	@ResponseBody
	@OpenAPI
	@ApiOperation("风险等级")
	public Map<String, Object> jlsFxdj(HttpServletRequest request) {
		try {
			String rybh = request.getParameter("rybh");
			/*String rybh = "110000121201911260002";*/
			Map<String, Object> list = analyseApis.jlsFxdj(rybh);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/select_jls_sjwsry")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls送监未收人员")
	public ResponseMessage<Map<String, Object>> select_jls_sjwsry() {
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_sjwsry();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_ndrs")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls年度入所量比对")
	public ResponseMessage<Map<String, Object>> select_jls_ndrs() {
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_ndrs();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}

	@GetMapping("/select_jls_cqjy")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls超期羁押分析")
	public ResponseMessage<Map<String, Object>> select_jls_cqjy() {
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_cqjy();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}

	@GetMapping("/select_jls_zdry")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls重点人员分析")
	public ResponseMessage<Map<String, Object>> select_jls_zdry() {
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_zdry();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_jy")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls所内、所外就医")
	public ResponseMessage<Map<String, Object>> select_jls_jy() {
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_jy();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}

	@GetMapping("/select_jls_rygl")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls人员管理情况分析")
	public ResponseMessage<Map<String, Object>> select_jls_rygl() {
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_rygl();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_hjrs")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls会见人数分析")
	public ResponseMessage<Map<String, Object>> select_jls_hjrs(){
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_hjrs();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_xdry")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls吸毒人员分析")
	public ResponseMessage<Map<String, Object>> select_jls_xdry(){
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_xdry();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_fxdj")
	@ResponseBody
	@OpenAPI
	@ApiOperation("大屏jls风险情况分析")
	public ResponseMessage<Map<String, Object>> select_jls_fxdj(){
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_fxdj();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}


	@GetMapping("/select_jls_gyqx")
	@ResponseBody
	@OpenAPI
	@ApiOperation("关押期限分析(3月以上   3-6   6-1  1-3  3)")
	public ResponseMessage<Map<String, Object>> select_jls_gyqx(){
		try {
			ResponseMessage<Map<String, Object>> mapResponseMessage = analyseApis.select_jls_gyqx();
			return mapResponseMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务器繁，请重新刷新页面");
		}
	}




}
