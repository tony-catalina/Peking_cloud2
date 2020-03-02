package awd.cloud.platform.webs.charts.controller.kss;

import awd.bj.base.model.Variables;
import awd.cloud.platform.webs.charts.api.AwdApi;
import awd.cloud.platform.webs.charts.modal.JbxxModelDO;
import awd.cloud.platform.webs.charts.modal.User;
import awd.cloud.platform.webs.charts.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss")
public class JbxxController {

	@Autowired
	private AwdApi api;
	
	
	
	
	
	
	/////////////////////////////////////////////////总队级页面统计/////////////////////////////////////////////////////////////////////////
	/**
	 * 	根据参数查询人员详细信息
	 * @param request
	 * @return
	 */
	@GetMapping("/queryJbxxList")
	@OpenAPI
	public Result<Object> queryJbxxList(HttpServletRequest request) {
		QueryParam queryParam = new QueryParam();
		ResponseMessage<PagerResult<JbxxModelDO>> result = api.kss_queryJbxxForPage(queryParam);
		if (result.getStatus() == 200) {
			if(result.getResult()!=null) {
				return ResultUtils.success(result.getResult().getTotal(), result.getResult().getData());
			}else {
				return ResultUtils.error("查询失败");
			}
			
		} else {
			return ResultUtils.error("查询失败");
		}
	}
	
	
	/////////////////////////////////////////////////所级页面统计/////////////////////////////////////////////////////////////////////////
	//在所人员统计----统计  在押总数  男 女 当日入所  当日出所 已决数  外籍  一级风险  二级风险  三级风险	
	//安全管理统计----统计 单独关押  涉恐 涉黑 涉恶 加戴械具 严管对象 囚车使用情况 电子脚镣使用情况
	//医疗管理统计----统计 重病号 所内就医 公安医院住院 其他医院住院
	//提讯会见统计----统计 提讯  家属会见  律师会见
	/**
	 *  	提讯会见统计	
	 * @param request
	 * @param jsbh
	 * @return
	 */
	@GetMapping("/countTxhj")
	@OpenAPI
	public Result<Object> countTxhj(HttpServletRequest request,String jsbh){
		if(StringUtils.isNullOrEmpty(jsbh)) {
			User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			jsbh=users.getJsbh();
		}
		System.err.println(jsbh);
		ResponseMessage<Long> tsresult = api.kss_queryTaskCount(jsbh, "kss_tsdj".toUpperCase(), "");
		ResponseMessage<Long> jshjresult = api.kss_queryTaskCount(jsbh, "kss_jshj".toUpperCase(), "");
		ResponseMessage<Long> lshjresult = api.kss_queryTaskCount(jsbh, "kss_lshj".toUpperCase(), "");
		
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> ts=new HashMap<String, Object>();
		ts.put("name", "提讯");
		ts.put("msg", tsresult.getResult());		
		ts.put("list", queryTaskList(jsbh,"kss_tsdj".toUpperCase(),Integer.valueOf(tsresult.getResult().toString())));
		list.add(ts);
		Map<String, Object> jshj=new HashMap<String, Object>();
		jshj.put("name", "家属会见");
		jshj.put("msg", jshjresult.getResult());
		jshj.put("list", queryTaskList(jsbh,"kss_jshj".toUpperCase(),Integer.valueOf(tsresult.getResult().toString())));
		list.add(jshj);
		Map<String, Object> lshj=new HashMap<String, Object>();
		lshj.put("name", "律师会见");
		lshj.put("msg", lshjresult.getResult());
		lshj.put("list", queryTaskList(jsbh,"kss_lshj".toUpperCase(),Integer.valueOf(tsresult.getResult().toString())));
		list.add(lshj);
		
		Map<String, Object> index=new HashMap<String, Object>();
		Map<String, Object> lt=new HashMap<String, Object>();
		Map<String, Object> b4=new HashMap<String, Object>();
		
		b4.put("name", "提讯会见");
		b4.put("list", list);		
		lt.put("b4", b4);
		System.err.println(b4);
		index.put("lt", lt);
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("index", index);
		
		return ResultUtils.success("",1,result);
		
	}

	private Object queryTaskList(String jsbh, String processDefinitionKey,int count) {
		Map<String, Object> result=new HashMap<String, Object>();
		Variables variables = new Variables();
		variables.setStart(String.valueOf(0));
		variables.setLimit(String.valueOf(count));
		variables.setJsbh(jsbh);
		variables.setProcessDefinitionKey(processDefinitionKey);
		ResponseMessage<PagerResult<JbxxModelDO>> jbxxlist=api.kss_getListCustom(variables);
		if(jbxxlist.getStatus()==200&&jbxxlist.getResult()!=null) {
			result.put("data", formateData(jbxxlist.getResult().getData()));
		}else {
			result.put("data", new ArrayList<Map<String, Object>>());
		}
		
		result.put("seriesNum", 7);
		List<Map<String, Object>> titles=new ArrayList<Map<String,Object>>();
		Map<String, Object> xl=new HashMap<String, Object>();
		xl.put("series", "xl");xl.put("name", "序号");titles.add(xl);
		Map<String, Object> item1=new HashMap<String, Object>();
		item1.put("series", "item1");item1.put("name", "姓名");titles.add(item1);
		Map<String, Object> item2=new HashMap<String, Object>();
		item2.put("series", "item2");item2.put("name", "性别");titles.add(item2);
		Map<String, Object> item3=new HashMap<String, Object>();
		item3.put("series", "item3");item3.put("name", "健康情况");titles.add(item3);
		Map<String, Object> item4=new HashMap<String, Object>();
		item4.put("series", "item4");item4.put("name", "办案阶段");titles.add(item4);
		Map<String, Object> item5=new HashMap<String, Object>();
		item5.put("series", "item5");item5.put("name", "案由");titles.add(item5);
		Map<String, Object> item6=new HashMap<String, Object>();
		item6.put("series", "item6");item6.put("name", "风险等级");titles.add(item6);
		result.put("titles", titles);
		return result;
	}

	private List<Map<String, Object>> formateData(List<JbxxModelDO> data) {		
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		int i=0;
		for (JbxxModelDO jbxxModelDO : data) {
			i++;
			Map<String, Object> ryxx=new HashMap<String, Object>();
			ryxx.put("xl", i);
			ryxx.put("item1", jbxxModelDO.getXm());
			ryxx.put("item2", jbxxModelDO.getXbString());
			ryxx.put("item3", jbxxModelDO.getJkzkString());
			ryxx.put("item4", jbxxModelDO.getBahjString());
			ryxx.put("item5", jbxxModelDO.getAyString());
			ryxx.put("item6", jbxxModelDO.getWxdjString());
			result.add(ryxx);
		}
		return result;
	}
	//关押期限统计----三日内到期   超期
	//各监区报病情况趋势----统计最近7个月 各个监区的报病数量
	//各监区违规情况趋势----统计各个监区的违规情
	//值班信息查询---当日值班领导、在岗民警数、在岗辅警数 值班电话
	//重点关注对象查询---各监区一级风险人员信息 及照片查询
	//监区信息当前状态查询----在押总数 女性  当日入所  当日出所 一二三级风险人数及占比
	//诉讼阶段占比统计
	//案件类型分析
	//全所违规程度分析----轻微、一般、严重违规人数及占比
	
	
	
	
	/////////////////////////////////////////////////其他页面统计/////////////////////////////////////////////////////////////////////////

	/**
	 * 	获取在押人员数量
	 * @param request
	 * @return
	 */
	@GetMapping("/queryJbxxCount")
	@OpenAPI
	public Result<Object> queryJbxxCount(HttpServletRequest request) {
		String jsbh = request.getParameter("jsbh");
		ResponseMessage<Long> result = api.kss_queryJbxxCount(jsbh);
		if(result.getStatus()==200) {
			return ResultUtils.success(result.getResult());
		}else {
			return ResultUtils.error("查询错误");
		}
		
	}

	/**
	 * 	根据字段 值获取在押人员数量
	 * @param request
	 * @return
	 */
	@GetMapping("/queryJbxxCountByField")
	@OpenAPI
	public Result<Object> queryJbxxCountByField(HttpServletRequest request) {
		String jsbh = request.getParameter("jsbh");
		String field = request.getParameter("field");
		String value = request.getParameter("value");
		ResponseMessage<List<Map<String, Object>>> result = api.kss_queryJbxxCountByField(jsbh, field, value);
		if(result.getStatus()==200) {
			System.err.println(JSONUtil.toJson(result.getResult()));
			return ResultUtils.success(result.getResult());
		}else {
			return ResultUtils.error("查询错误");
		}
	}
	
	
	/**
	 * 	获取待办数量
	 * @param request
	 * @return
	 */
	@GetMapping("/queryTaskCount")
	@OpenAPI
	public Result<Object> queryTaskCount(HttpServletRequest request) {
		String jsbh = request.getParameter("jsbh");
		String flow = request.getParameter("processDefinitionKey");
		String node = request.getParameter("taskDefinitionKey");
		ResponseMessage<Long> result = api.kss_queryTaskCount(jsbh, flow, node);
		if(result.getStatus()==200) {
			return ResultUtils.success(result.getResult());
		}else {
			return ResultUtils.error("查询错误");
		}
	}

	/**
	 * 	获取待办业务信息
	 * @param request
	 * @return
	 */
	@GetMapping("/queryTaskList")
	@OpenAPI
	public Result<Object> queryTaskList(HttpServletRequest request,String jsbh) {
		String processDefinitionKey = request.getParameter("processDefinitionKey");
		String taskDefinitionKey = request.getParameter("taskDefinitionKey");
		String limit = request.getParameter("rows");
		String start = request.getParameter("page");
		Variables variables = new Variables();
		variables.setJsbh(jsbh);

		if (!StringUtils.isNullOrEmpty(taskDefinitionKey)) {
			variables.setTaskDefinitionKey(taskDefinitionKey);
		}
		int _pageIndex = 0;
		int _pageSize = 0;
		if (!StringUtils.isNullOrEmpty(start) && !StringUtils.isNullOrEmpty(limit)) {
			_pageIndex = Integer.parseInt(start);
			_pageSize = Integer.parseInt(limit);
			int num = _pageIndex;

			if (1 != _pageIndex && 0 != _pageIndex) {
				_pageIndex = _pageSize * num - _pageSize;
				_pageSize = _pageSize * num;
			} else {
				_pageIndex = 0;
			}
		}
		Map<String, Object> params = Maps.newHashMap();
		Map<String, Object> notEqualsMap = Maps.newHashMap();
		Map<String, Object> greaterThanOrEqualMap = Maps.newHashMap();
		Map<String, Object> lessThanOrEqualMap = Maps.newHashMap();
		variables.setStart(String.valueOf(_pageIndex));
		variables.setLimit(limit);

		String xm = request.getParameter("xm");
		String ay = request.getParameter("ay");
		String bm = request.getParameter("bm");
		String jsh = request.getParameter("jsh");
		String xb = request.getParameter("xb");
		String bahj = request.getParameter("bahj");
		String badw = request.getParameter("badw");
		String dah = request.getParameter("dah");
		String lsdjr = request.getParameter("lsdjr");
		String snbh = request.getParameter("snbh");
		String yjmj = request.getParameter("yjmj");
		String rsrq_start = request.getParameter("rsrq_start");
		String rsrq_end = request.getParameter("rsrq_end");
		String cssj_start = request.getParameter("cssj_start");
		String cssj_end = request.getParameter("cssj_end");
		String csrq_start = request.getParameter("csrq_start");
		String csrq_end = request.getParameter("csrq_end");
		String gyqx_start = request.getParameter("gyqx_start");
		String gyqx_end = request.getParameter("gyqx_end");
		String ncsrq_start = request.getParameter("ncsrq_start");
		String ncsrq_end = request.getParameter("ncsrq_end");
		String xqjsrq_start = request.getParameter("xqjsrq_start");
		String xqjsrq_end = request.getParameter("xqjsrq_end");
		String rsxz = request.getParameter("rsxz");
		String gwjjb_jbgw = request.getParameter("gwjjb_jbgw");
		String caaj = request.getParameter("caaj");
		String sydw = request.getParameter("sydw");
		String zrdw = request.getParameter("sydw");

		if (!StringUtils.isNullOrEmpty(xm)) {
			params.put("xm", xm);
		}

		if (!StringUtils.isNullOrEmpty(ay)) {
			params.put("ay", ay);
		}

		if (!StringUtils.isNullOrEmpty(bm)) {
			params.put("bm", bm);
		}
		if (!StringUtils.isNullOrEmpty(jsh)) {
			params.put("jsh", jsh);
		}
		if (!StringUtils.isNullOrEmpty(xb)) {
			params.put("xb", xb);
		}
		if (!StringUtils.isNullOrEmpty(badw)) {
			params.put("badw", badw);
		}
		if (!StringUtils.isNullOrEmpty(dah)) {
			params.put("dah", dah);
		}
		if (!StringUtils.isNullOrEmpty(lsdjr)) {
			params.put("lsdjr", lsdjr);
		}
		if (!StringUtils.isNullOrEmpty(yjmj)) {
			params.put("yjmj", yjmj);
		}
		if (!StringUtils.isNullOrEmpty(bahj)) {
			params.put("bahj", bahj);
		}
		if (!StringUtils.isNullOrEmpty(snbh)) {
			params.put("snbh", snbh);
		}
		if (!StringUtils.isNullOrEmpty(caaj)) {
			params.put("caaj", caaj);
		}
		if (!StringUtils.isNullOrEmpty(sydw)) {
			params.put("sydw", sydw);
		}
		if (!StringUtils.isNullOrEmpty(zrdw)) {
			params.put("zrdw", zrdw);
		}
		if (!StringUtils.isNullOrEmpty(rsxz)) {
			params.put("rsxz", rsxz);
		}
		if (!StringUtils.isNullOrEmpty(gwjjb_jbgw)) {
			params.put("gwjjb_jbgw", gwjjb_jbgw);
		}
		if (!StringUtils.isNullOrEmpty(rsrq_start)) {
			greaterThanOrEqualMap.put("rsrq", rsrq_start + " 00:00::00");
		}
		if (!StringUtils.isNullOrEmpty(rsrq_end)) {
			lessThanOrEqualMap.put("rsrq", rsrq_end + " 23:59:59");
		}
		if (!StringUtils.isNullOrEmpty(cssj_start)) {
			greaterThanOrEqualMap.put("cssj", cssj_start + " 00:00::00");
		}
		if (!StringUtils.isNullOrEmpty(cssj_end)) {
			lessThanOrEqualMap.put("cssj", cssj_end + " 23:59:59");
		}
		if (!StringUtils.isNullOrEmpty(gyqx_start)) {
			greaterThanOrEqualMap.put("gyqx", gyqx_start);
		}
		if (!StringUtils.isNullOrEmpty(gyqx_end)) {
			lessThanOrEqualMap.put("gyqx", gyqx_end + " 23:59:59");
		}
		if (!StringUtils.isNullOrEmpty(csrq_start)) {
			greaterThanOrEqualMap.put("csrq", csrq_start);
		}
		if (!StringUtils.isNullOrEmpty(csrq_end)) {
			lessThanOrEqualMap.put("csrq", csrq_end + " 23:59:59");
		}
		if (!StringUtils.isNullOrEmpty(ncsrq_start)) {
			lessThanOrEqualMap.put("cssj", ncsrq_start);
		}
		if (!StringUtils.isNullOrEmpty(ncsrq_end)) {
			lessThanOrEqualMap.put("cssj", ncsrq_end + " 23:59:59");
		}
		if (!StringUtils.isNullOrEmpty(xqjsrq_start)) {
			lessThanOrEqualMap.put("xqjsrq", xqjsrq_start);
		}
		if (!StringUtils.isNullOrEmpty(xqjsrq_end)) {
			lessThanOrEqualMap.put("xqjsrq", xqjsrq_end + " 23:59:59");
		}
		if (!processDefinitionKey.contains(",")) {
			variables.setProcessDefinitionKey(processDefinitionKey);
		} else {
			variables.setProcessDefinitionKey("");
			params.put("in", processDefinitionKey);
		}
		variables.setParams(params); // 流程匹配参数
		variables.setNotEqualsMap(notEqualsMap); // 流程不等于匹配参数
		variables.setGreaterThanOrEqualMap(greaterThanOrEqualMap); // 流程大于匹配参数
		variables.setLessThanOrEqualMap(lessThanOrEqualMap); // 流程小于匹配参数

		long procedure_startTime = System.currentTimeMillis(); // 获取开始时间
		ResponseMessage<PagerResult<JbxxModelDO>> result = api.kss_getListCustom(variables);
		if (result.getStatus() == 200) {
			if(result.getResult()!=null) {
				return ResultUtils.success(result.getResult().getTotal(), result.getResult().getData());
			}else {
				return ResultUtils.error("查询失败");
			}
			
		} else {
			return ResultUtils.error("查询失败");
		}
	}






}
