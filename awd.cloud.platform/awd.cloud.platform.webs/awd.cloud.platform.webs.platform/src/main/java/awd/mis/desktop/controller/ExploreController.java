package awd.mis.desktop.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import awd.mis.desktop.bean.ResultMap;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.AppModel;
import awd.mis.desktop.service.AppService;
import awd.mis.desktop.service.AuthorService;
import awd.mis.desktop.service.ExploreService;
import awd.mis.desktop.service.FileService;
import awd.mis.desktop.views.GlobalVar;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

@RestController
public class ExploreController {
	@Autowired
	AppService appService;
	@Autowired
	AuthorService authorService;
	@Autowired
	FileService fileService;
	@Autowired
	ExploreService exploreService;
	
	@Autowired
    private DiscoveryClient client;
	
	/**
	 * 新建txt文件
	 * @param request path :文件路径
	 * @return
	 */
	
	@GetMapping(value = "/explorer/mkfile", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap mkfile(HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String path = request.getParameter("path");		
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData("新建成功");		
		try {
			result.setInfo(exploreService.mkFile(currentUser,path));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
		
	}
	
	/**
	 * 新建文件夹
	 */
	@GetMapping(value = "/explorer/mkdir", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap mkdir(HttpServletRequest request) {		
		ResultMap result=new ResultMap();	
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String path=request.getParameter("path");
		result.setCode("true");		
		result.setData("新建成功");		
		result.setInfo(exploreService.mkdir(currentUser,path));		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());			
		return result;
		
	}
	/**
	 * 文件查询  应用查询
	 * @param type  查询类型
	 * @param path  文件路径
	 * @return
	 */
	@RequestMapping(value = "/explorer/pathList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathList(HttpServletRequest request) {	
		try {
			Thread.sleep(200); //1000 毫秒，也就是1秒.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		Date start=Calendar.getInstance().getTime();
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String type =request.getParameter("type");
		String path =request.getParameter("path");		
		ResultMap result=new ResultMap();	
		result.setCode("true");
		System.err.println("---------------------------");
		System.err.println(path);
		if("/desktop/".equals(path)) {
			List<AppModel> appModelList = exploreService.getAppModelList(currentUser);
			if (appModelList != null) {
				currentUser.setApps(appModelList);
			}
			result.setData(exploreService.getDesktop(currentUser));	
		}else
		if("{user_recycle}".equals(path)||"{user_recycle}/".equals(path)) {
			result.setData(exploreService.getRecycle(currentUser));	
		}else
		if("/explorer/".equals(path)) {
			result.setData(exploreService.getDesktop(currentUser));	
		}else {
			result.setData(exploreService.getfiles(currentUser,path));	
		}
		Date end=Calendar.getInstance().getTime();
		result.setInfo("查询成功！");
		result.setUse_time(DateUtil.betweenMs(start,end));		
		return result;
		
	}
	/**
	 * 树目录
	 * @param type
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/explorer/treeList",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap treeList(@ModelAttribute ( "type" ) String type,@ModelAttribute ( "path" ) String path) {
		Date start=Calendar.getInstance().getTime();
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();	
		result.setData(exploreService.getFileTree(currentUser,type,path));
		Date end=Calendar.getInstance().getTime();
		result.setCode("true");	
		result.setInfo("查询成功！");
		result.setUse_time(DateUtil.betweenMs(start,end));		
		return result;		
	}
	/**
	 * 桌面
	 * @param type  类型
	 * @param path  路径
	 * @return
	 */
	@RequestMapping(value = "/explorer",produces = "application/json;charset=UTF-8")
	public ModelAndView explorer(ModelMap model ,HttpServletRequest request,HttpServletResponse respose) {
		String type=request.getParameter("type");
		String path=request.getParameter("path");	
		if(!StringUtils.isEmpty(type)) {
			model.put("type", type);
		}	
		if(StringUtils.isEmpty(path)) {
			path="explorer";
		}
		GlobalVar.getConfig().put("my_desktop", "\\/"+path+"\\/");
		return new ModelAndView("explorer/explorer");
	}
	
	
	/**
	 * 清空回所站
	 * @param post_empty
	 * @return
	 */
	@RequestMapping(value = "/explorer/pathDeleteRecycle", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathDeleteRecycle(@ModelAttribute ( "post_empty" ) String post_empty) {
		ResultMap result=new ResultMap();	
		result.setCode("true");
		result.setData("清空回收站成功！");
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	
	/**
	 * 应用信息
	 * @param post_empty
	 * @return
	 */
	@PostMapping(value = "/explorer/pathInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathInfo(HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long start=Calendar.getInstance().getTimeInMillis();
		ResultMap result=new ResultMap();		
		String data_arr=request.getParameter("data_arr");
		String type=JSONUtil.parseArray(data_arr).getJSONObject(0).getStr("type");
		String path=JSONUtil.parseArray(data_arr).getJSONObject(0).getStr("path");
		result.setCode("true");
		result.setData(exploreService.getPathInfo(currentUser,type,path));
		result.setUse_time(Calendar.getInstance().getTimeInMillis()-start);		
		return result;
		
	}
	
	private String getjsonstr(String data) {
		return URLDecoder.decode(data).substring(URLDecoder.decode(data).indexOf("=")+1, URLDecoder.decode(data).length()).trim();
	}
	private String trimkouhao(String param) {
		return param.replace("{", "").replace("}", "").replace("%7B", "").replace("%7D", "");
	}
	
	@PostMapping(value = "/explorer/pathRname", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathRname(HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long start=Calendar.getInstance().getTimeInMillis();
		ResultMap result=new ResultMap();	
		String path=request.getParameter("path");
		String rname_to=request.getParameter("rname_to");
		result.setCode("true");
		result.setData(exploreService.filepathRname(currentUser,path,rname_to));
		result.setUse_time(Calendar.getInstance().getTimeInMillis()-start);		
		return result;
		
	}
	
	/**
	 * 获取文件类型图片
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/explorer/image", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public byte[] image(HttpServletRequest request) {		
		Date start=Calendar.getInstance().getTime();
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String path =request.getParameter("path");	
//		path=URLDecoder.decode(path);
        byte[] bytes=null;
        try {
        	URL url = new URL(path);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection(); //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();  
            //获取自己数组
            bytes = readInputStream(inputStream);    
            if(inputStream!=null){
                inputStream.close();
            }
        }catch (Exception e) {
			e.printStackTrace();
		}        

        return bytes;
		
	}
	
	private byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    } 
	
	/**
	 * 上传
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/fileUpload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();	
		String path=request.getParameter("upload_to");
		String name=request.getParameter("name");
		result.setCode("true");		
		result.setData("upload_success");		
		result.setInfo(exploreService.uploadFile(currentUser,file,path));		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
		
	}
	
	/**
	 * 创建副本
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/explorer/pathCopyDrag", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathCopyDrag(HttpServletRequest request) {
		ResultMap result=new ResultMap();	
		result.setCode("true");
		result.setData("操作成功");
		result.setInfo("[\"/document/libglesv2(1).dll\"]");
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
		
	}
	/**
	 * 下载
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/explorer/serverDownload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap serverDownload(HttpServletRequest request) {
		ResultMap result=new ResultMap();	
		result.setCode("true");
		result.setData("下载成功！");
		result.setInfo("/desktop/wallpage/ChMkJ1diE9iIV1jBAAitjqQd-doAASp-wBxFo8ACK2m576.jpg");
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
		
	}
	/**
	 * 清空回收站成功！
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/pathDeleteRecycle", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathDeleteRecycle(HttpServletRequest request) {
		String post_empty = request.getParameter("1");
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData("清空回收站成功！");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
		
	}
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/search", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap search(HttpServletRequest request) {
		String search = request.getParameter("sa");
		String path = request.getParameter("/desktop/wallpage/");
		String is_content = request.getParameter("0");
		String is_case = request.getParameter("0");
		String ext = request.getParameter("");
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData(exploreService.getsearch());		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());	
		return result;
		
	}
	/**
	 * 查看剪粘板
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/explorer/clipboard", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap clipboard(HttpServletRequest request) {
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData("[]");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
		
	}
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/pathDelete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathDelete(HttpServletRequest request) {
		String data_arr = request.getParameter("[{\"type\":\"folder\",\"path\":\"/desktop/新建文件夹/\"}]");
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData("删除成功！");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
		
	}
	/**
	 * 压缩文件下载
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/zipDownload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap zipDownload(HttpServletRequest request) {
		String data_arr = request.getParameter("[{\"type\":\"folder\",\"path\":\"/desktop/新建文件夹/\"}]");
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData("压缩完成");		
		result.setInfo("新建文件夹.zip");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
		
	}
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/explorer/changePassword", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap changePassword(HttpServletRequest request) {		
		ResultMap result=new ResultMap();	
		result.setCode("false");		
		result.setData("原密码错误！");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 复制
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/pathCopy", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathCopy(HttpServletRequest request) {
		String data_arr = request.getParameter("[{\"type\":\"folder\",\"path\":\"/desktop/新建文件夹/\"}]");
		ResultMap result=new ResultMap();		
		result.setCode("true");		
		result.setData("【复制】—— 覆盖剪贴板成功!");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 剪切
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/pathCute", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathCute(HttpServletRequest request) {
		String data_arr = request.getParameter("[{\"type\":\"folder\",\"path\":\"/desktop/新建文件夹/\"}]");
		ResultMap result=new ResultMap();		
		result.setCode("true");		
		result.setData("【剪切】—— 覆盖剪贴板成功!");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 重命名
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/pathRename", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathRename(HttpServletRequest request) {
		String path = request.getParameter("/desktop/新建文件夹");
		String rname_to = request.getParameter("/desktop/sa");
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData("重命名成功!");		
		result.setInfo("/desktop/sa");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 压缩文件
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/explorer/zip", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap zip(HttpServletRequest request) {
		String data_arr = request.getParameter("[{\"type\":\"folder\",\"path\":\"/desktop/新建文件夹/\"}]");
		
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData("压缩完成大小:120 B");		
		result.setInfo("/desktop/新建文件夹.zip");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 粘贴
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/explorer/pathPast", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap pathPast(HttpServletRequest request) {
		String path = request.getParameter("/desktop/");		
		ResultMap result=new ResultMap();	
		result.setCode("false");		
		result.setData("<b>剪切操作完成</b>(源文件被删除,剪贴板清空)<li>新建文件夹来源不存在</li>");		
		result.setInfo("[]");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 下载
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/explorer/serverDowmload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap serverDowmload(HttpServletRequest request) {
		String type = request.getParameter("download");		
		String save_path = request.getParameter("/desktop/wallpage/");		
		String url = request.getParameter("http://img.infinitynewtab.com/randomBlur/482.jpg");		
		ResultMap result=new ResultMap();	
		result.setCode("true");		
		result.setData("下载成功！");		
		result.setInfo("/desktop/wallpage/482.jpg");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	
	/**
	 * 根据服务名获取访问地址
	 * @param serverName
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/getWebURL",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> getWebUrl(@RequestParam("serverName") String serverName,@RequestParam(value="param",required=false)String param) {
		List<ServiceInstance> list = client.getInstances(serverName);
		Map<String, Object> result=new HashMap<String, Object>();
        System.out.println("------------------所有服务--------------------------------");
        for (ServiceInstance serviceInstance : list) {
        	System.out.println(serviceInstance.getHost()+":"+serviceInstance.getPort());
		}
//        System.out.println("-------------------------------------------------------");
        if (list != null && list.size() > 0 ) {   
        	if(StrUtil.isEmpty(param)) {
        		result.put("url","http://"+list.get(0).getUri().getHost()+":"+list.get(0).getUri().getPort());
        	}else {
        		result.put("url","http://"+list.get(0).getUri().getHost()+":"+list.get(0).getUri().getPort()+"?"+param);
        	}
            
        }
        return result;
	}
}
