package awd.cloud.suppers.finger.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.awd.finger.hongda.FuncAOutput;
import com.awd.finger.hongda.ID_Fpr;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.suppers.finger.config.InitHongdaZwy;
import awd.cloud.suppers.finger.service.CacheService;
import awd.cloud.suppers.finger.tools.CacheUtils;
import awd.cloud.suppers.finger.tools.FingerUtil;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@RequestMapping("/hongda")
@AccessLogger("HongDa")
@Api(value = "HongDa") 
public class HongDa {

	static{
		//ID_Fpr.FP_Begin();
		
		String msg = InitHongdaZwy.init();
		System.err.println("------------指纹仪" + msg + "------------");
		InitHongdaZwy.initClose();
		ID_Fpr.FP_Begin();//调用算法
	}
	
	@Autowired
	private CacheService cacheService;
	
	
	@GetMapping("/FP_Begin")
	@OpenAPI
	@ApiOperation("调用算法")
	public ResponseMessage<?> FP_Begin() {
		ID_Fpr.FP_Begin();
		return ResponseMessage.ok("调用算法");
	}
	@GetMapping("/FP_End")
	@OpenAPI
	@ApiOperation("停止算法")
	public ResponseMessage<?> FP_End() {
		ID_Fpr.FP_End();
		return ResponseMessage.ok("停止算法");
	}
	
	@GetMapping("/Init_FP_Begin")
	@OpenAPI
	@ApiOperation("初始化宏达指纹仪插件")
	public ResponseMessage<?> InitZwy() {
		String msg = InitHongdaZwy.init();
		//InitZwy.initClose();
		return ResponseMessage.ok(msg);
	}
	
	@PostMapping("/Init_FP_End")
	@OpenAPI
	@ApiOperation("关闭宏达指纹仪插件")
	public ResponseMessage<?> UnInitZwy() {
		int msg = InitHongdaZwy.initClose();
		if (msg == 1) {
			return ResponseMessage.ok("插件停止成功");
		}
		return ResponseMessage.error("插件停止失败");
	}
	
	@PostMapping("/test")
	@ApiOperation("一个测试用接口")
	public String test(String jsbh,String rybh,int type) {
		String str = "";
		/*Map<String, Object> map = CacheUtils.getMap(rybh);
		System.err.println("map---"+map);
		List<?> tzmList = (List<?>) map.get(rybh);
		String tzm1 = tzmList.get(0).toString();
		String tzm2 = tzmList.get(1).toString();*/
		FingerUtil.moveList(jsbh,rybh,type);
		return str;
	}
	
	
	@PostMapping("/removeRyListCacheByKey")
	@ApiOperation("根据type清除指定人员缓存 type=1 是民警，type=0 是被关押人员")
	public void removeRyListCacheByKey(int type) {
		String cacheName = "";
		if(type == 0){
			cacheName ="RYJSBH";
		}else if(type == 1){
			cacheName ="MJJSBH";
		}	
		CacheUtils.add(cacheName, "");
	}
	
	@PostMapping("/loadAllCache")
	@OpenAPI
	@ApiOperation("加载所有缓存")
	public void loadAllCache() {
		cacheService.loadAllZwAndTzm();
	}
	
	@PostMapping("/loadRyCache")
	@OpenAPI
	@ApiOperation("加载人员相关缓存")
	public void loadRyCache() {
		cacheService.loadRyCache();
	}
	
	@PostMapping("/loadMjCache")
	@OpenAPI
	@ApiOperation("加载民警相关缓存")
	public void loadMjCache() {
		cacheService.loadMjCache();
	}
	
	@PostMapping("/loadMjOrRyCache")
	@OpenAPI
	@ApiOperation("加载民警刘表和人员列表缓存")
	public void loadMjOrRyCache() {
		cacheService.loadMjOrRyCache();
	}
	
	@PostMapping("/removeAllCache")
	@ApiOperation("删除所有缓存")
	public void removeAllCache() {
		CacheUtils.deleteAll();
	}
	
	@PostMapping("/addRybhOrZjhCache")
	@ApiOperation("添加人员编号缓存")
	public void addRybhOrZjhCache(String jsbh,String rybhOrZjh,int type) {
		FingerUtil.moveList(jsbh, rybhOrZjh, type);
	}
	
	@PostMapping("/fingerMatchHongdaByOne")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("宏达指纹匹配——根据指定编号一对一比对")
	public ResponseMessage<?> fingerMatchHongdaByOne(String rybhOrZjh,String fingerData) {
		
		byte[] fingerData_byte;
		try {
			fingerData_byte = FingerUtil.getBmpByte(fingerData);
			if (fingerData_byte.length != ID_Fpr.Feature_Size) {
				System.err.println("输入的特征码有错误！");
				return ResponseMessage.error("输入的特征码有错误！");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		FuncAOutput funAOutput = new FuncAOutput();
		
		Map<String, Object> tzmMap = null;
		try {
			tzmMap = CacheUtils.getMap(rybhOrZjh);
		} catch (Exception e1) {
			//e1.printStackTrace();
			return ResponseMessage.error("未获取到" + rybhOrZjh + "缓存数据");
		}
		if(tzmMap == null){
			return ResponseMessage.error("获取缓存失败");
		}
		//System.err.println(tzmMap.get(rybhOrZjh));
		
		String rybhList = tzmMap.get(rybhOrZjh).toString();
		List<String> object = JSONObject.parseArray(rybhList,String.class);
		if(rybhList == null){
			return ResponseMessage.error("人员编号获取失败");
		}
		Object tzm1O = object.get(0);
		Object tzm2O = object.get(1);
		if(tzm1O == null || tzm2O == null){
			return ResponseMessage.error("-1");
		}		
		String tzm1 = tzm1O.toString().replace("\"", "");
		String tzm2 = tzm2O.toString().replace("\"", "");

		try {
			byte[] tzm1_byte = FingerUtil.getBmpByte(tzm1);
			byte[] tzm2_byte = FingerUtil.getBmpByte(tzm2);
			fingerData_byte = FingerUtil.getBmpByte(fingerData);
			if (tzm1_byte.length == ID_Fpr.Feature_Size ) {
				int result = ID_Fpr.FP_FeatureMatch(tzm1_byte, fingerData_byte, funAOutput);
				System.err.println("----特征码1匹配结果: " + result);
				System.err.println("----匹配特征码1得分: " + funAOutput.Get());
				if(result == 1 && funAOutput.Get() > FingerUtil.getSuccessScore()){
					return ResponseMessage.ok("成功匹配到" + rybhOrZjh + "指纹特征码");
				}
			}else {
				System.err.println("----特征码1不合格---- ");
			}
			if(tzm2_byte.length == ID_Fpr.Feature_Size) {
				int result2 = ID_Fpr.FP_FeatureMatch(tzm2_byte, fingerData_byte, funAOutput);
				System.err.println("----特征码2匹配结果: " + result2);
				System.err.println("----匹配特征码2得分: " + funAOutput.Get());
				if(result2 == 1 && funAOutput.Get() > FingerUtil.getSuccessScore()){
					return ResponseMessage.ok("成功匹配到" + rybhOrZjh + "指纹特征码");
				}
			}else {
				System.err.println("----特征码2不合格---- ");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseMessage.error("没有指纹匹配");
		}
		return ResponseMessage.ok("匹配失败");
	}
	
	
	@PostMapping("/fingerMultiMatchHongda")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("宏达指纹根据jsbh批量匹配——根据传入type区分人员与民警")
	public ResponseMessage<?> fingerMultiMatchHongda(String jsbh,String fingerData,int type) {
		
		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		byte[] fingerData_byte;
		try {
			fingerData_byte = FingerUtil.getBmpByte(fingerData);
			if (fingerData_byte.length != ID_Fpr.Feature_Size) {
				System.err.println("输入的特征码有错误！");
				return ResponseMessage.error("输入的特征码有错误！");
			}
		} catch (IOException e1) {
			//e1.printStackTrace();
			return ResponseMessage.error("数据错误！");
		}
		FuncAOutput funAOutput = new FuncAOutput();
		Map<String, Object> jsbhMap = null;
		String ry_type = "";
		try {
			if (type == 0) {
				jsbhMap = FingerUtil.getJsMaps("RYJSBH",jsbh);
				ry_type = "被关押人员";
			}else if (type == 1) {
				jsbhMap = FingerUtil.getJsMaps("MJJSBH",jsbh);
				ry_type = "民警";
			}
		} catch (Exception e1) {
			//e1.printStackTrace();
			return ResponseMessage.error("未获取到监所" + jsbh + "缓存数据");
		}
		if(jsbhMap == null){
			return ResponseMessage.error("-1");
		}
		List<?> list = (List<?>) jsbhMap.get(jsbh);
		if(list == null){
			return ResponseMessage.error("-1");
		}
		System.err.println("--批量匹配开始---"+new Date().getTime());
		for(int i = 0; i < list.size(); i++){
			int j = i + 1;
			System.err.println("--监所：" + jsbh + "第--" + j + "--次比对--");
			String rybhOrZjh = list.get(i).toString();
			Map<String, Object> tzmMap = null;
			try {
				tzmMap = CacheUtils.getMap(rybhOrZjh);
				if(tzmMap != null){
					List<?> ls = (List<?>)tzmMap.get(rybhOrZjh);
					if(ls != null){
						Object tzm1O = ls.get(0);
						Object tzm2O = ls.get(1);
						if(tzm1O == null || tzm2O == null){
							return ResponseMessage.error("-1");
						}		
						String tzm1 = tzm1O.toString();
						String tzm2 = tzm2O.toString();
						try {
							byte[] tzm1_byte = FingerUtil.getBmpByte(tzm1);
							byte[] tzm2_byte = FingerUtil.getBmpByte(tzm2);
							fingerData_byte = FingerUtil.getBmpByte(fingerData);
							if (tzm1_byte.length == ID_Fpr.Feature_Size ) {
								int result = ID_Fpr.FP_FeatureMatch(tzm1_byte, fingerData_byte, funAOutput);
								System.err.println("----特征码1匹配结果: " + result);
								System.err.println("----匹配特征码1得分: " + funAOutput.Get());
								if(result == 1 && funAOutput.Get() > FingerUtil.getSuccessScore()){
									FingerUtil.moveList(jsbh,rybhOrZjh,type);//比对成功，则对rybhlist顺序进行调整
									return ResponseMessage.ok("成功匹配到" + ry_type + rybhOrZjh + "指纹特征码");
								}
							}else {
								System.err.println("----特征码1不合格---- ");
							}
							if(tzm2_byte.length == ID_Fpr.Feature_Size) {
								int result2 = ID_Fpr.FP_FeatureMatch(tzm2_byte, fingerData_byte, funAOutput);
								System.err.println("----特征码2匹配结果: " + result2);
								System.err.println("----匹配特征码2得分: " + funAOutput.Get());
								if(result2 == 1 && funAOutput.Get() > FingerUtil.getSuccessScore()){
									FingerUtil.moveList(jsbh,rybhOrZjh,type);//比对成功，则对rybhlist顺序进行调整
									return ResponseMessage.ok("成功匹配到" + ry_type + rybhOrZjh + "指纹特征码");
								}
							}else {
								System.err.println("----特征码2不合格---- ");
							}
						} catch (IOException e) {
							//e.printStackTrace();
							return ResponseMessage.error("匹配失败");
						}
					}
				}
			} catch (Exception e1) {
				//e1.printStackTrace();
				return ResponseMessage.error("匹配失败");
			}
		}
		return ResponseMessage.ok("没有" + ry_type + "指纹匹配");
	}
	
	@PostMapping("/fingerMultiMatchHongdaByRybhs")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("宏达指纹通过人员编号批量匹配")
	public ResponseMessage<?> fingerMultiMatchHongdaByRybhs(String jsbh,String[] rybhOrZjhList,String fingerData) {

		if (!FingerUtil.jsbhIsExsit(jsbh)) {
			return ResponseMessage.error("没有此监所编号！");
		}
		
		byte[] fingerData_byte;
		try {
			fingerData_byte = FingerUtil.getBmpByte(fingerData);
			if (fingerData_byte.length != ID_Fpr.Feature_Size) {
				System.err.println("输入的特征码有错误！");
				return ResponseMessage.error("输入的特征码有错误！");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		FuncAOutput funAOutput = new FuncAOutput();
		
		System.err.println("--批量匹配开始---"+new Date().getTime());
		if(rybhOrZjhList == null){
			return ResponseMessage.error("-1");
		}
		for(int i = 0; i < rybhOrZjhList.length; i++){
			int j=i+1;
			System.err.println("监所" + jsbh + "--触摸屏："  + "第--" + j + "--次比对--");
			String rybhOrZjh = rybhOrZjhList[i].replace("\"", "").replace(",", "");
			System.err.println("rybh---"+rybhOrZjh);
			Map<String, Object> tzmMap = null;
			try {
				tzmMap = FingerUtil.getJsMaps(rybhOrZjh,rybhOrZjh);
				if(tzmMap != null){
					List<?> ls = (List<?>)tzmMap.get(rybhOrZjh);
					if(ls == null){
						return ResponseMessage.error("-1");
					}
					Object tzm1O = ls.get(0);
					Object tzm2O = ls.get(1);
					if(tzm1O == null || tzm2O == null){
						return ResponseMessage.error("-1");
					}		
					String tzm1 = tzm1O.toString();
					String tzm2 = tzm2O.toString();
					try {
						byte[] tzm1_byte = FingerUtil.getBmpByte(tzm1);
						byte[] tzm2_byte = FingerUtil.getBmpByte(tzm2);
						fingerData_byte = FingerUtil.getBmpByte(fingerData);
						if (tzm1_byte.length == ID_Fpr.Feature_Size ) {
							int result = ID_Fpr.FP_FeatureMatch(tzm1_byte, fingerData_byte, funAOutput);
							System.err.println("----特征码1匹配结果: " + result);
							System.err.println("----匹配特征码1得分: " + funAOutput.Get());
							if(result == 1 && funAOutput.Get() > FingerUtil.getSuccessScore()){
								//FingerUtil.moveList(jsbh,rybh,0);//比对成功，则对rybhlist顺序进行调整
								return ResponseMessage.ok("成功匹配到" + rybhOrZjh + "指纹特征码");
							}
						}else {
							System.err.println("----特征码1不合格---- ");
						}
						if(tzm2_byte.length == ID_Fpr.Feature_Size) {
							int result2 = ID_Fpr.FP_FeatureMatch(tzm2_byte, fingerData_byte, funAOutput);
							System.err.println("----特征码2匹配结果: " + result2);
							System.err.println("----匹配特征码2得分: " + funAOutput.Get());
							if(result2 == 1 && funAOutput.Get() > FingerUtil.getSuccessScore()){
								//FingerUtil.moveList(jsbh,rybh,0);//比对成功，则对rybhlist顺序进行调整
								return ResponseMessage.ok("成功匹配到" + rybhOrZjh + "指纹特征码");
							}
						}else {
							System.err.println("----特征码2不合格---- ");
						}
						
					} catch (IOException e) {
						e.printStackTrace();
						return ResponseMessage.error("没有指纹匹配");
					}
				}else{
					System.err.println(rybhOrZjh + "未录入指纹");
				}
			} catch (Exception e1) {
				//e1.printStackTrace();
				System.err.println("未获取到" + rybhOrZjh + "缓存数据");
				//return ResponseMessage.error("未获取到" + rybh + "缓存数据");
				continue;
			}
		}
		return ResponseMessage.ok("没有指纹匹配");
	}
	
	
	@PostMapping("/getTwoTzm")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("根据编号从缓存中获取两个特征码")
	public ResponseMessage<?> getTwoTzm(String rybhOrZjh) {
		
		try{
			String result = "";
			Map<String, Object> tzmmap = CacheUtils.getMap(rybhOrZjh);
			if(tzmmap == null){
				return ResponseMessage.error("未获取到指纹特征码缓存");
			}
			List<?> tzmlist =(List<?>)tzmmap.get(rybhOrZjh);
			
			if (tzmlist != null) {
				result = tzmlist.get(0).toString() + "," +  tzmlist.get(1).toString();
			}
			System.err.println("-----" + rybhOrZjh + "------两个特征码获取成功！--------------");
			return ResponseMessage.ok(result);
		}catch (Exception e) {
			return ResponseMessage.error("未获取到指纹特征码缓存");
		}
	}
	
	@PostMapping("/getTwoZhiwei")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("根据编号从缓存中获取两个指纹位")
	public ResponseMessage<?> getTwoZhiwei(String rybhOrZjh) {//获取两个指位,type为0表示人员，1表示民警
		
		try{
			String result = "";
			Map<String, Object> tzmmap = CacheUtils.getMap(rybhOrZjh);
			if(tzmmap == null){
				return ResponseMessage.error("未获取到指纹位缓存");
			}
			List<?> tzmlist =(List<?>)tzmmap.get(rybhOrZjh);
			
			if (tzmlist != null) {
				result = tzmlist.get(2).toString() + "," +  tzmlist.get(3).toString();
			}
			System.err.println("-----" + rybhOrZjh + "------两个指纹获取成功！--------------"+ result);
			return ResponseMessage.ok(result);
		}catch (Exception e) {
			return ResponseMessage.error("未获取到指纹位缓存");
		}
			
	}
	
	@PostMapping("/selectCache")
	@HystrixCommand
	@OpenAPI
	@ApiOperation("根据jsbh和类别获取缓存")
	public ResponseMessage<?> selectCache(String type,String jsbh){
		System.err.println("---" + jsbh + "---看守所系统正在初始化指纹特征码缓存--");
		String cachename = "";
		if(type.equals("0")){
			cachename ="RYJSBH";
		}else if(type.equals("1")){
			cachename ="MJJSBH";
		}	
		Map<String, Object> map =Maps.newHashMap();
		
		try {
			Map<String,Object> jsbhMap = FingerUtil.getJsMaps(cachename,jsbh);
			if(jsbhMap == null){
				return ResponseMessage.error("未获取到监所编号缓存！");
			}
			List<?> list = (List<?>)jsbhMap.get(jsbh);
			if(list == null){
				return ResponseMessage.error("未获取到监所编号！");
			}
			String bhStr = "";
			for(int i = 0; i < list.size(); i++){
				bhStr += list.get(i)+",";
			}
			System.err.println("bhs-----" + bhStr);
			map.put("data", bhStr);
		} catch (Exception e) {
			map.put("data", "");
		}
		return ResponseMessage.ok(map);
	}
	
	
	
}
