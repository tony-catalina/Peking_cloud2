package awd.cloud.suppers.finger.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import awd.cloud.suppers.finger.entity.BzwtzmEntity;
import awd.cloud.suppers.finger.entity.MzwtzmEntity;
import awd.cloud.suppers.finger.service.BzwtzmService;
import awd.cloud.suppers.finger.service.CacheService;
import awd.cloud.suppers.finger.service.MzwtzmService;
import awd.cloud.suppers.finger.tools.CacheUtils;
import awd.cloud.suppers.finger.tools.FingerUtil;
import sun.misc.BASE64Encoder;

@Component	
public class SimpleCacheService implements CacheService {

	@Autowired
	private BzwtzmService bzwtzmService;
	@Autowired
	private MzwtzmService mzwtzmService;
	
	@Override
	public void add(String key, Object value, int expiration) {
		
	}

	/**
	 * 加载民警刘表和人员列表缓存
	 */
	public void loadMjOrRyCache() {
		String loadjsbhs = FingerUtil.getJsbhs();
		String[] jsbhArray = loadjsbhs.split(",");
		Map<String,List<?>> rybhsMap = new HashMap<String,List<?>>();
		Map<String,List<?>> mjzjhsMap = new HashMap<String,List<?>>();
		
		for(int k = 0; k < jsbhArray.length; k++){
			String jsbh = jsbhArray[k];
			try{
				List<String> rybhList = new ArrayList<String>();
				List<BzwtzmEntity> bjgry_enryList = bzwtzmService.getEntyListByJsbh(jsbh);
				
				for(int j = 0; bjgry_enryList != null && j < bjgry_enryList.size(); j++){
					String rybh = bjgry_enryList.get(j).getRybh().toString();
					rybhList.add(rybh);
				}				
				rybhsMap.put(jsbh,rybhList);
				System.err.println("---加载监所---" + jsbh + "--人员人数:" + rybhList.size());
			}catch(Exception e){
				e.printStackTrace();
			}
			
			List<String> mjzjhs = new ArrayList<String>();
			try{
				Map<String,Object> tzmMap = new HashMap<String,Object>();
				List<MzwtzmEntity> mj_entyList = mzwtzmService.getEntyListByJsbh(jsbh);
				for(int j = 0; mj_entyList != null&& j < mj_entyList.size(); j++){
					String zjh = mj_entyList.get(j).getRybh().toString();
					mjzjhs.add(zjh);
				}				
				mjzjhsMap.put(jsbh,mjzjhs);
				System.err.println("---加载监所---" + jsbh + "--民警人数:" + mjzjhs.size());
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		CacheUtils.add("RYJSBH", rybhsMap);
		CacheUtils.add("MJJSBH", mjzjhsMap);
		System.err.println("-----------------人员缓存加载完成-----------------");
		
	}
	
	/**
	 * 加载民警和被关押人员指纹码
	 */
	public void loadAllZwAndTzm() {
		CacheUtils.deleteAll();
		String loadjsbhs = FingerUtil.getJsbhs();
		String[] jsbhArray = loadjsbhs.split(",");
		
		Map<String,List<?>> rybhsMap = new HashMap<String,List<?>>();
		Map<String,List<?>> mjzjhsMap = new HashMap<String,List<?>>();
		for(int k = 0; k < jsbhArray.length; k++){
			String jsbh = jsbhArray[k];
			System.err.println("----------开始加载监所:" + jsbh + " 在押人员指纹特征码：------------");
			BASE64Encoder base64Encoder=new BASE64Encoder();
			try{
				Map<String,Object> tzmMap = new HashMap<String,Object>();
				List<String> rybhList = new ArrayList<String>();
				List<BzwtzmEntity> bjgry_enryList = bzwtzmService.getEntyListByJsbh(jsbh);
				
				for(int j = 0; bjgry_enryList != null && j < bjgry_enryList.size(); j++){
					List<String> tzmlist=new ArrayList<String>();
					String rybh = bjgry_enryList.get(j).getRybh().toString();
					if(bjgry_enryList.get(j).getZwtz1() == null){
						System.err.println("----error--------" + rybh + "--特征码1为空");
					}else{
						tzmlist.add(FingerUtil.replaceBlank(base64Encoder.encode((byte[]) bjgry_enryList.get(j).getZwtz1())));
					}
					if(bjgry_enryList.get(j).getZwtz2() == null){
						System.err.println("----error--------" + rybh + "--特征码2为空");
					}else{
						tzmlist.add(FingerUtil.replaceBlank(base64Encoder.encode((byte[]) bjgry_enryList.get(j).getZwtz2())));
					}
					tzmlist.add((String) bjgry_enryList.get(j).getZwtzxh1());
					tzmlist.add((String) bjgry_enryList.get(j).getZwtzxh2());
					rybhList.add(rybh);
					tzmMap.put(rybh,tzmlist);
					CacheUtils.add(rybh,tzmMap);
					tzmMap.clear();
				}				
				rybhsMap.put(jsbh,rybhList);
				System.err.println("---加载监所---" + jsbh + "--人员人数:" + rybhList.size());
			}catch(Exception e){
				e.printStackTrace();
			}
			System.err.println("----------监所:" + jsbh + " 在押人员指纹特征码加载结束------------");
			System.err.println("----------开始加载监所:" + jsbh + " 民警指纹特征码：------------");
			
			List<String> mjzjhs = new ArrayList<String>();
			try{
				Map<String,Object> tzmMap = new HashMap<String,Object>();
				List<MzwtzmEntity> mj_entyList = mzwtzmService.getEntyListByJsbh(jsbh);
				for(int j = 0; mj_entyList != null&& j < mj_entyList.size(); j++){
					List<String> tzmlist=new ArrayList<String>();
					String zjh = mj_entyList.get(j).getRybh().toString();
					
					if(mj_entyList.get(j).getZwtz1() == null){
						System.err.println("----error--------" + zjh + "--特征码1为空");
					}else{
						tzmlist.add(FingerUtil.replaceBlank(base64Encoder.encode((byte[]) mj_entyList.get(j).getZwtz1())));
					}
					if(mj_entyList.get(j).getZwtz2() == null){
						System.err.println("----error--------" + zjh + "--特征码2为空");
					}else{
						tzmlist.add(FingerUtil.replaceBlank(base64Encoder.encode((byte[]) mj_entyList.get(j).getZwtz2())));
					}
					
					tzmlist.add((String) mj_entyList.get(j).getZwtzxh1());
					tzmlist.add((String) mj_entyList.get(j).getZwtzxh2());
					mjzjhs.add(zjh);
					tzmMap.put(zjh,tzmlist);
					CacheUtils.add(zjh,tzmMap);
					tzmMap.clear();
				}				
				mjzjhsMap.put(jsbh,mjzjhs);
				System.err.println("---加载监所---" + jsbh + "--民警人数:" + mjzjhs.size());
				
			}catch(Exception e){
				e.printStackTrace();
			}
			System.err.println("----------监所:" + jsbh + " 民警指纹特征码加载结束------------");
		}
		
		CacheUtils.add("RYJSBH", rybhsMap);
		CacheUtils.add("MJJSBH", mjzjhsMap);
		System.err.println("-----------------全部监所加载完成-----------------");
	}
	
	
	/**
	 * 加载人员相关缓存
	 */
	public void loadRyCache() {
		String loadjsbhs = FingerUtil.getJsbhs();
		String[] jsbhArray = loadjsbhs.split(",");
		Map<String,List<?>> rybhsMap = new HashMap<String,List<?>>();
		for(int k = 0; k < jsbhArray.length; k++){
			String jsbh = jsbhArray[k];
			System.err.println("----------开始加载监所:" + jsbh + " 在押人员指纹特征码：------------");
			BASE64Encoder base64Encoder=new BASE64Encoder();
			try{
				Map<String,Object> tzmMap = new HashMap<String,Object>();
				List<String> rybhList = new ArrayList<String>();
				List<BzwtzmEntity> bjgry_enryList = bzwtzmService.getEntyListByJsbh(jsbh);
				
				for(int j = 0; bjgry_enryList != null && j < bjgry_enryList.size(); j++){
					List<String> tzmlist=new ArrayList<String>();
					String rybh = bjgry_enryList.get(j).getRybh().toString();
					if(bjgry_enryList.get(j).getZwtz1() == null){
						System.err.println("----error--------" + rybh + "--特征码1为空");
					}else{
						tzmlist.add(FingerUtil.replaceBlank(base64Encoder.encode((byte[]) bjgry_enryList.get(j).getZwtz1())));
					}
					if(bjgry_enryList.get(j).getZwtz2() == null){
						System.err.println("----error--------" + rybh + "--特征码2为空");
					}else{
						tzmlist.add(FingerUtil.replaceBlank(base64Encoder.encode((byte[]) bjgry_enryList.get(j).getZwtz2())));
					}
					tzmlist.add((String) bjgry_enryList.get(j).getZwtzxh1());
					tzmlist.add((String) bjgry_enryList.get(j).getZwtzxh2());
					rybhList.add(rybh);
					tzmMap.put(rybh,tzmlist);
					CacheUtils.add(rybh,tzmMap);
					tzmMap.clear();
				}				
				rybhsMap.put(jsbh,rybhList);
				System.err.println("---加载监所---" + jsbh + "--人员人数:" + rybhList.size());
			}catch(Exception e){
				e.printStackTrace();
			}
			System.err.println("----------监所:" + jsbh + " 在押人员指纹特征码加载结束------------");
			System.err.println("----------开始加载监所:" + jsbh + " 民警指纹特征码：------------");
		}
	}
	
	
	/**
	 * 加载民警相关缓存
	 */
	public void loadMjCache() {
		String loadjsbhs = FingerUtil.getJsbhs();
		String[] jsbhArray = loadjsbhs.split(",");
		Map<String,List<?>> mjzjhsMap = new HashMap<String,List<?>>();
		for(int k = 0; k < jsbhArray.length; k++){
			String jsbh = jsbhArray[k];
			System.err.println("----------开始加载监所:" + jsbh + " 在押人员指纹特征码：------------");
			BASE64Encoder base64Encoder=new BASE64Encoder();
			
			List<String> mjzjhs = new ArrayList<String>();
			try{
				Map<String,Object> tzmMap = new HashMap<String,Object>();
				List<MzwtzmEntity> mj_entyList = mzwtzmService.getEntyListByJsbh(jsbh);
				for(int j = 0; mj_entyList != null&& j < mj_entyList.size(); j++){
					List<String> tzmlist=new ArrayList<String>();
					String zjh = mj_entyList.get(j).getRybh().toString();
					
					if(mj_entyList.get(j).getZwtz1() == null){
						System.err.println("----error--------" + zjh + "--特征码1为空");
					}else{
						tzmlist.add(FingerUtil.replaceBlank(base64Encoder.encode((byte[]) mj_entyList.get(j).getZwtz1())));
					}
					if(mj_entyList.get(j).getZwtz2() == null){
						System.err.println("----error--------" + zjh + "--特征码2为空");
					}else{
						tzmlist.add(FingerUtil.replaceBlank(base64Encoder.encode((byte[]) mj_entyList.get(j).getZwtz2())));
					}
					
					tzmlist.add((String) mj_entyList.get(j).getZwtzxh1());
					tzmlist.add((String) mj_entyList.get(j).getZwtzxh2());
					mjzjhs.add(zjh);
					tzmMap.put(zjh,tzmlist);
					CacheUtils.add(zjh,tzmMap);
					tzmMap.clear();
				}				
				mjzjhsMap.put(jsbh,mjzjhs);
				System.err.println("---加载监所---" + jsbh + "--民警人数:" + mjzjhs.size());
				
			}catch(Exception e){
				e.printStackTrace();
			}
			System.err.println("----------监所:" + jsbh + " 民警指纹特征码加载结束------------");
		}
		
		CacheUtils.add("MJJSBH", mjzjhsMap);
		System.err.println("-----------------全部监所民警信息加载完成-----------------");
	}
	
	
	
}
