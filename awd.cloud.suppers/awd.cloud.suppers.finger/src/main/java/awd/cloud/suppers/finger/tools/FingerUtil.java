package awd.cloud.suppers.finger.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.netflix.ribbon.proxy.annotation.Var;

import sun.misc.BASE64Decoder;

public class FingerUtil {
	
	private static FingerUtil fingerUtil;
	
	public static FingerUtil get() {
		if (fingerUtil == null) {
			return new FingerUtil();
		}
		return fingerUtil;
	}
	
	
	/**
	 * 把String转换成byte
	 * 
	 * @param base64bmp
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("all")
	public static byte[] getBmpByte(String base64bmp) throws IOException {
		byte[] bmpbyte = null;
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		bmpbyte = decoder.decodeBuffer(base64bmp);
		return bmpbyte;
	}
	
	/**
	 * 获取比对的得分
	 * @return
	 */
	public static double getSuccessScore(){
		InputStream inputStream = FingerUtil.get().getClass().getClassLoader()
				.getResourceAsStream("awd/cloud/suppers/finger/config/score.properties");
		Properties p = new Properties();
		String success="0.95";
		try {    
			p.load(inputStream);
			success = (!p.getProperty("success").equals("")) ? p.getProperty("success") : "0.95";
		}catch(Exception e){
			e.printStackTrace();
			return 0.95;
		}
		System.err.println("合格得分为:"+success);
		return Double.parseDouble(success);
	}
	
	/**
	 * 获取jsbh
	 * @return
	 */
	public static String getJsbhs(){
		InputStream inputStream = FingerUtil.get().getClass().getClassLoader()
				.getResourceAsStream("awd/cloud/suppers/finger/config/loadjsbh.properties");
		Properties p = new Properties();
		String jsbh="";
		try {    
			p.load(inputStream);
			jsbh = (!p.getProperty("jsbh").equals("")) ? p.getProperty("jsbh") : "";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return jsbh;
	}
	
	//判断监所编号是否存在
	public static boolean jsbhIsExsit(String jsbh) {
		String loadjsbhs = getJsbhs(); 
		String[] jsbhArray = loadjsbhs.split(",");
		boolean flag = false;
		for (int i = 0; i < jsbhArray.length; i++) {
			if (jsbh.equals(jsbhArray[i])) {
				return flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 获取测试用特征码
	 */
	public static String getTestTzm(int num){
		InputStream inputStream = FingerUtil.get().getClass().getClassLoader()
				.getResourceAsStream("awd/cloud/suppers/finger/config/testTzm.properties");
		Properties p = new Properties();
		String testTzm="";
		try {    
			p.load(inputStream);
			testTzm = (!p.getProperty("testTzm"+num).equals("")) ? p.getProperty("testTzm0") : "";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return testTzm;
	}
	
	/**
	 * 去除加载缓存时的换行符
	 */
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	/**
	 * 根据jsbh，获取缓存中的人员
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getJsMaps(String key,String list_Key) {
		String cache_key = CacheUtils.CACHE_KEY_FINGER + "_" + key;
		Map<String, Object> re =Maps.newHashMap();
		JSONObject obj = JSONObject.parseObject(CacheUtils.get().getKey(cache_key).toString());
		List<String> list = (List<String>) obj.get(list_Key);
		re.put(list_Key, list);
		return re;
	}
	
	
	/**
	 * 过滤人员编号
	 * 
	 * @param rybh
	 * @return
	 */
	public static boolean checkRYBH(String rybh) {
		String reg = "^([0-9]{21})|([0-9]{15})|([0-9]{18})|([0-9]{17}(X|x))$";
		boolean tem = false;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(rybh);
		tem = matcher.matches();
		return tem;
	}
	
	
	
	/**
	 * @param rybhOrZjh
	 * @param fingerdata
	 * @param fingercode
	 */
	//2个指纹特征码
	public static void updateCache(String rybhOrZjh,String[] fingerdata,byte[] fingercode){//新增,更新
		Map<String,Object> tzmMap = new HashMap<String,Object>();
		
		List<Object> tzmlist=new ArrayList<Object>();
		tzmlist.add(fingerdata[0].replace("\"", ""));
		tzmlist.add(fingerdata[1].replace("\"", ""));
		tzmlist.add(fingercode[0]);
		tzmlist.add(fingercode[1]);
		
		tzmMap.put(rybhOrZjh,tzmlist);
		CacheUtils.add(rybhOrZjh,tzmMap);
		System.err.println("--缓存更新完成---" + rybhOrZjh);
		
	}
	
	
	public static void moveList(String jsbh,String rybhOrZjh,int type){//将rybh或者mjzjh加入到常用list第一个位置，原来位置向后顺移,list中肯定存在该rybh
			String cacheName = "";
			if(type == 0){
				cacheName ="RYJSBH";
			}else if(type == 1){
				cacheName ="MJJSBH";
			}		
			
			String loadjsbhs = FingerUtil.getJsbhs();
			String[] jsbhArray = loadjsbhs.split(",");
			
			Map<String, Object> maps = Maps.newHashMap();
			for (int k = 0; k < jsbhArray.length; k++) {
				
				Map<String,Object> jsbhMap = FingerUtil.getJsMaps(cacheName, jsbhArray[k]);
				if(jsbhMap == null){
					return;
				}
				List<String> list = (List)jsbhMap.get(jsbhArray[k]);
				List<String> newlist = new ArrayList<String>();
				if (jsbhArray[k].equals(jsbh)) {
					if(list == null){//新所第一个注册人员。
						newlist.add(rybhOrZjh);
					}else {
						newlist.add(rybhOrZjh);
						for(int i=0;i<list.size();i++){
							if(!list.get(i).equals(rybhOrZjh)){
								newlist.add(list.get(i));
							}
						}
					}
				}else {
					newlist = list;
					newlist.remove(rybhOrZjh);//把此编号从旧的监所 编号list中删除
				}
				maps.put(jsbhArray[k], newlist);
			}
			System.err.println(maps);
			CacheUtils.add(cacheName, maps);
			System.err.println("--缓存更新结束---");
			
		}
	
}

