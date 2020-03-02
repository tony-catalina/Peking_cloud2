package awd.mis.servers.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;

import awd.mis.servers.dao.YyDao;
import awd.mis.servers.service.PublicService;
import awd.mis.servers.utils.FTPUtils;
import awd.mis.servers.utils.FileUtil;

@Service
public class PublicServiceImpl  implements PublicService {

	
	@Autowired
	private FileUtil file;
	@Autowired
	private FTPUtils fTPUtils;
	
	@Autowired
	private YyDao yyDao;


	/**
	 * 从ftp服务器 下载文件到本地，并返回文件路径
	 */
	@Override
	public String getFtpFileByName(String fileName) {
		String fileContent = file.getFtpFileByName(fileName);
		return fileContent;
	}
	
	@Override
	public String uploadPulicFileToFtp(String fileName, String fileContent) {
		String filePath = file.uploadPulicFileToFtp(fileName, fileContent);
		return filePath;
	}

	@Override
	public String deleteFtpFolder(String remotePath) {
		fTPUtils.deleteFtpFolder(remotePath);
		return "";
	}

	@Override
	public String test(String param) {
		String targetURL = "http://192.168.4.110:9991/oracle/uploadJsonToFtp";
		try {
            HttpClient client = HttpClients.createDefault();
            HttpPost request = new HttpPost(targetURL);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
            nvps.add(new BasicNameValuePair("json", param));  
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, "utf-8");
            request.setEntity(formEntity);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
            	System.err.println("接口调用失败！");
			}
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "test";
	}
	
	public String readFtpFileToEntity() {
		List<String> jsonObjList = file.getPublicObjList();
		String outmsg = "";
		for (String jsonStr : jsonObjList) {
			System.err.println("jsonStr==="+jsonStr);
			try {
				List<Map<String, Object>> list = (List<Map<String, Object>>) parseStringToList(jsonStr);
		    	System.err.println("list:="+JSON.toJSONString(list));
		    	//循环获取到的list
		    	for (Map<String, Object> map : list) {
		    		Set<Entry<String, Object>> entries = map.entrySet();
		    		Iterator<Entry<String, Object>> it = entries.iterator();
		    		while(it.hasNext()) {
		    			Entry<String, Object> next = it.next();
		    			//判断数据库中是否有该监所的记录
		    			System.out.println(next.getValue().toString()+":"+ next.getKey().toString());
		    			yyDao.updateFjsl(next.getKey().toString(),next.getValue().toString());
		    		}
				}
			} catch (Exception e) {
				System.err.println("这个 文件 转换bean失败啦！");
			}
		}
		System.err.println(outmsg);
		return outmsg;
	}	
	
	public List<? extends Map> parseStringToList(String jsonString) {
    	String listStr = jsonString.replace("[", "{").replace("]", "}")
    			.replace("{{", "[{").replace("}}", "}]")
    			.replace(",", ":").replace("}:{", "},{");
    	Map<String, Object> map = Maps.newHashMap();
    	List<? extends Map> jsonList = JSONArray.parseArray(listStr, map.getClass());
		return jsonList;
	}
	
	
	
}
