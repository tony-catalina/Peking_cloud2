package awd.cloud.platform.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.SysexMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.util.StringUtil;

import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.jwp.SpdetailorderModelDO;
import awd.cloud.platform.model.manager.Manager_InterfaceModel;
import awd.cloud.platform.model.manager.Manager_InterfacebindingModel;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.Sort;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.entity.Entity;
import awd.framework.common.utils.StringUtils;

/**
 * @author P
 *
 */
public abstract class PublicService{
	@Autowired
	private ManagerService managerService;

	
	//用户权限
	public ResponseMessage<Map<String, Object>> userAuthorizatio(HttpServletRequest request,String json,String interfaceId){
		try {
			JSONObject jsonStr= JSONObject.parseObject(json);
			System.err.println(jsonStr);
			Map<String, Object> map = new HashMap<String, Object>();
			System.err.println(json);
			if(json!=null) {
				map = JSONObject.parseObject(json, HashMap.class);
			}
			String ip = request.getHeader("X-Real-IP");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		        ip = request.getHeader("X-Forwarded-For");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		        ip = request.getHeader("Proxy-Client-IP");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		        ip = request.getHeader("WL-Proxy-Client-IP");
		    }
		    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		        ip = request.getRemoteAddr();
		    }
		    // 处理多IP的情况（只取第一个IP）
		    if (ip != null && ip.contains(",")) {
		        String[] ipArray = ip.split(",");
		        ip = ipArray[0];
		    }
			System.err.println(ip);
			//查询app表判断url是否一样
			System.err.println("appcode------------"+request.getParameter("appcode"));
			if(StringUtils.isNullOrEmpty(request.getParameter("appcode"))) {
	        	return ResponseMessage.error("appcode必传！");
	        }
			QueryParam param = new QueryParam();
			param.and("appcode", TermType.eq, request.getParameter("appcode"));
			String url = CacheUtils.get().geturlString(request.getParameter("appcode"));
			if(StringUtils.isNullOrEmpty(url)) {
				return ResponseMessage.error("appcode错误！");
			}
			//部分原因特殊处理比如我们的应用 用不含数字判断
			if(HasDigit(url)) {
				if(url.contains(ip)==false) {
					return ResponseMessage.error("该设备无操作权限！");
				}
			}
	        map.put("interfaceId", interfaceId);
	        System.err.println("请求参数为：" + json);
	        if(StringUtils.isNullOrEmpty(request.getParameter("jsbh"))) {
	        	return ResponseMessage.error("jsbh必传！");
	        }
	        //校验监所是否存在
	        String kssmc = CacheUtils.get().getKssmcByKss(request.getParameter("jsbh"));
			if(StringUtils.isNullOrEmpty(kssmc)) {
				return ResponseMessage.error("jsbh错误，该监所不存在！");
			}
	        //校验参数
	        QueryParam queryParam = new QueryParam();
	        //判断此人是否有权限
	        JSONObject interfacebinding = CacheUtils.get().getinterfacebindingString(request.getParameter("appcode"), interfaceId, "2");
	        if(StringUtils.isNullOrEmpty(interfacebinding)) {
	        	return ResponseMessage.error("无接口权限！");
	        }
	      //判断接口是否启用
	        QueryParam queryParams = new QueryParam();
	        //判断此人是否有权限
	        JSONObject interfaces = CacheUtils.get().getinterfaceString(interfaceId, "1");
	        if(StringUtils.isNullOrEmpty(interfaces)) {
	        	return ResponseMessage.error("该接口未启用！");
	        }
	       //条件参数处理
			return ResponseMessage.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("数据不是json格式！");
		}
	}
	
	//开放字段
	public  ResponseMessage<Map<String, Object>> kfzdShow(Map<String, Object> map){
		Object list = map.get("entity");
		System.err.println("list--"+JSON.toJSONString(list));
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> zdlist = new ArrayList<Map<String,Object>>();
		if (StringUtils.isNullOrEmpty(list)){
			result.put("total",0);
			result.put("page","1");
			result.put("data",zdlist);
			return ResponseMessage.ok(result,"查询成功!");
		}
		//查出的数据处理
		List<Map<String, Object>> entitylist = (List<Map<String, Object>>) JSON.parse(JSON.toJSONString(list));
		System.err.println("mapss--"+JSON.toJSONString(entitylist));
		//获取设置的开发字段
		JSONObject interfaceModel = CacheUtils.get().getinterfaceString(map.get("interfaceId").toString(), "1");
		System.err.println("interfaceModel--"+interfaceModel.getString("kfzd"));
		String kfzd = interfaceModel.getString("kfzd");
		String[] kfzds = kfzd.split(",");

		result.put("total", map.get("total"));
		if(StringUtils.isNullOrEmpty(map.get("page"))) {
			result.put("page", "1");
		}else {
			result.put("page", Integer.parseInt(map.get("page").toString())+1);
		}

		if(!"0".equals(map.get("total").toString())){
			for (Map<String, Object> entity : entitylist) {
				Map<String, Object> model = new HashMap<String, Object>();
				for (String zd : kfzds) {
					if(StringUtils.isNullOrEmpty(entity.get(zd))) {
						//为空则塞入空
						model.put(zd, null);
					}else {
						if("jgnr".equals(zd)) {
							model.put(zd, entity.get(zd).toString().replace("\\n", "\n"));
						}else if("qlywnr".equals(zd)) {
							model.put(zd, entity.get(zd).toString().replace("\\n", "\n"));
						}else{
							model.put(zd, entity.get(zd));
						}
					}

				}
				zdlist.add(model);
			}
		}
		System.out.println("000000000000000000000======================="+JSON.toJSONString(zdlist));
		result.put("data", zdlist);
		return ResponseMessage.ok(result,"查询成功!");
		
	}
	
	
	//存在问题无法使用
	/**
	 * 把一些默认参数封装进qParam，state、page、rows、sort、order等
	 * @param request
	 * @param qParam
	 */
	public void addDefaultQueryParams(HttpServletRequest request ,QueryParam qParam,String state,String interfaceId) {
		//String state = request.getParameter("state");
		if(state != null) {
			qParam.and("state", TermType.in, state);
		}else {
			qParam.and("state", TermType.eq, "R2");
		}
		String page = request.getParameter("page");
		String rows = request.getParameter("pageSize");
		int pageIndex = 0;
		int pageSize = 10;
		try {
			pageIndex = Integer.valueOf(page).intValue() - 1;
			pageSize = Integer.valueOf(rows).intValue();
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			System.err.println("分页参数出错！");
		}finally {
			qParam.setPageIndex(pageIndex);
			qParam.setPageSize(pageSize);
		}
		
        String sortName = request.getParameter("sort");
        if (sortName != null && sortName.indexOf("String") > 0) {
        	sortName = sortName.substring(0, sortName.length() - 6);
        }
        String orderBy = request.getParameter("order");
        
        List<Sort> sorts = new ArrayList<>();
        Sort sort = new Sort();
        if(sortName != null && orderBy != null) {
        	sort.setName(sortName);
        	sort.setOrder(orderBy);
        }else {
        	sort.setName("id");
        	sort.setOrder("desc");
		}
        sorts.add(sort);
        qParam.setSorts(sorts);
        if(!StringUtils.isNullOrEmpty(interfaceId)) {
	        QueryParam param = new QueryParam();
			param.and("interfaceId", interfaceId);
			ResponseMessage<PagerResult<Manager_InterfaceModel>> interfaceModel = managerService.interface_query(param);
			System.err.println("interfaceModel--"+interfaceModel);
			String kfzd = interfaceModel.getResult().getData().get(0).getKfzd();
			Set<String> set = new HashSet<>();;
			String[] ks = kfzd.split(",");
			for (String kfzds : ks) { 
				set.add(kfzds);
			}
			//set.add("rybh");
			//qParam.setIncludes(set);
        }
	}
	
	
	// 保存方法参数验证
		public ResponseMessage<String> modelYz(Map<String, Object> map) {
			//String gz = "\\d{1,5}";  //长度1-5的数字
	        //String fk = "\\S{1,5}";  //不能有空格的校验
	       // String zw = ".{1,5}";  //string校验
	        //String xs = "^\\d+(.[0-9]{2,3})?$";  //小数长度
	       // String time = "\\d{4}-\\d{2}-\\d{2}";//定义匹配规则不带时分秒
	       // String times = "^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$";//定义匹配规则带时分秒
	        List<Map<String, Object>> list = toListMap(map.get("entity").toString());
	        //获取校验字段
	        JSONObject interfaceModel = CacheUtils.get().getinterfaceString(map.get("interfaceId").toString(), "1");
	        System.err.println(interfaceModel.getString("kfzd"));
	        Map<String, Object> maps= new HashMap<String, Object>();
			if(interfaceModel.getString("kfzd")!=null) {
				maps = JSON.parseObject(interfaceModel.getString("kfzd"), HashMap.class);
				//判断字段是否符合要求
				for (Map<String, Object> map2 : list) {
					String zdjy = "";
					String flag = "false";
					
					//字典规范zd具体要求
					for (String zd : maps.keySet()) {
						if(maps.get(zd).toString().matches("^[A-Z]+$")) {
								//判断字典格式是否正确
						/*
						 * String s = CacheUtils.get().getDictionary(maps.get(zd).toString(),
						 * map2.get(zd).toString()); System.err.println(s+"---+");
						 */
						/*
						 * if(StringUtils.isNullOrEmpty(s)) { return ResponseMessage.error(zd+"字典错误！");
						 * }
						 */
						}else {
							String error = "";
							if(!map2.get(zd).toString().matches(maps.get(zd).toString())) {
								//数字类型
								if(maps.get(zd).toString().startsWith("\\d")) {
									//截取长度
									String str2 = maps.get(zd).toString().substring(maps.get(zd).toString().indexOf(",") + 1,maps.get(zd).toString().length()-1);
									error = zd +"数字长度不能超过"+str2;
									
								}
								//字符串
								if(maps.get(zd).toString().startsWith(".{")) {
									//截取长度
									String str2 = maps.get(zd).toString().substring(maps.get(zd).toString().indexOf(",") + 1,maps.get(zd).toString().length()-1);
									error = zd +"字符串长度不能超过"+str2;
									
								}
								//小数
								if(maps.get(zd).toString().startsWith("^\\d+(.")) {
									//截取长度
									String str2 = maps.get(zd).toString().substring(maps.get(zd).toString().indexOf(","),maps.get(zd).toString().length()-4);
									error = zd +"字符串长度不能超过"+str2;
									
								}
								//时间不带时分秒
								if(maps.get(zd).toString().startsWith("\\d{4}-\\d{2}-\\d{2}")) {
									//截取长度
									error = zd +"时间不需要带时分秒";
									
								}
								//时间带时分秒
								if(maps.get(zd).toString().startsWith("^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$")) {
									//截取长度
									error = zd +"时间需要带时分秒";
									
								}
								
								zdjy = zd+"," + error;
								flag = "true";
							}
						}
					}
					if(flag == "true") {
//						zdjy = zdjy.substring(1, zdjy.length());
						return ResponseMessage.error("字段"+zdjy+"！");
					}
				}
			}
		    return ResponseMessage.ok();
		}
		

		  
		// 判断一个字符串是否含有数字
		public boolean HasDigit(String content) {
		    boolean flag = false;
		    Pattern p = Pattern.compile(".*\\d+.*");
		    Matcher m = p.matcher(content);
		    if (m.matches()) {
		        flag = true;
		    }
		    return flag;
		}
		//json转List<Map<String, Object>>
		public static List<Map<String, Object>> toListMap(String json){
	        List<Object> list =JSON.parseArray(json);
	        List< Map<String,Object>> listw = new ArrayList<Map<String,Object>>();
	        for (Object object : list){
	            Map<String,Object> ageMap = new HashMap<String,Object>();
	            Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
	            listw.add(ret);
	        }
	        return listw;
	         
	    }
}
