package awd.mis.desktop.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.App;
import awd.mis.desktop.bean.File;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.AppModel;
import awd.mis.desktop.model.CloudfileModel;
import awd.mis.desktop.model.FileModel;
import awd.mis.desktop.model.GroupappModel;
import awd.mis.desktop.model.JsappModel;
import awd.mis.desktop.model.UserappModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import awd.mis.desktop.tools.Term;
import awd.mis.desktop.tools.TermType;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;


@Service("exploreService")
public class ExploreService  {
	@Autowired
	ManagerService managerService;
	@Autowired
	LogsService logsService;
	
	@Autowired
    private DiscoveryClient client;

	@SuppressWarnings("finally")
	public List<AppModel> getAppModelList(User currentUser){
		List<AppModel> list = null;
		QueryParam filter=new QueryParam();
		//如果该用户不是管理员用户
		if(currentUser.getUserinfo().getGlybz().equals("0")) {
			QueryParam qParam=new QueryParam();
			qParam.and("groupid", currentUser.getGroup().getId());
			qParam.setPaging(false);
			ResponseMessage<PagerResult<GroupappModel>> rm = managerService.groupAppQuery(qParam);
			//当该用户组有禁用的应用时，就不查询该应用
			if(rm.getResult().getTotal() != 0) {
				for(GroupappModel model:rm.getResult().getData()) {
					filter.and("appcode", TermType.not, model.getAppcode());
				}
			}
			//当该用户有被禁止的应用时
			QueryParam qp=new QueryParam();
			qp.and("userid", currentUser.getUserinfo().getId());
			qp.setPaging(false);
			ResponseMessage<PagerResult<UserappModel>> userrm = managerService.userAppQuery(qp);
			if(userrm.getResult().getTotal() != 0) {
				for(UserappModel model:userrm.getResult().getData()) {
					filter.and("appcode", TermType.not, model.getAppcode());
				}
			}
		}
		Map<String, Object> treeList=new HashMap<>();
		filter.and("jsbh", TermType.eq,currentUser.getJsbh());
		ResponseMessage<PagerResult<JsappModel>> respone=managerService.jsAppQuery(filter);
		try {
			list = managerService.getApplistWithJsappByJsbh(filter).getResult();
		} catch (Exception e) {
			System.err.println("获取app信息出错");
		}finally {
			return list;
		}
	}
	
	public Map<String, Object> getDesktop(User currentUser) {
		Map<String, Object> data=new HashMap<>();
		List<Map<String, Object>> list=new ArrayList<>();		
		data.put("folderlist", list);		
		/*if (this.getAppModelList(currentUser) != null) {
			currentUser.setApps(this.getAppModelList(currentUser));
		}*/
		data.put("filelist",getApp(currentUser.getApps()));	
		Map<String, Object> info=new HashMap<String, Object>();
		info.put("path_type", "");
		info.put("role", "");
		info.put("id", "");
		info.put("name", "");
		data.put("info",info);	
		data.put("path_read_write", "");
		data.put("thisPath", "/desktop/");
		Map<String, Object> user_space=new HashMap<>();
		user_space.put("size_max", "5");
		user_space.put("size_use", "1048576");
		data.put("user_space",user_space);
		return data;
	}

	private List<App> getApp(List<AppModel> apps) {
		List<App> applist=new ArrayList<>();
		if(apps!=null) {
			for (AppModel appmodel : apps) {
				App app=new App();
				app.setAtime(DateUtil.formatDate(appmodel.getCreatetime()));
				app.setContent(appmodel.getUrl());
				app.setCtime(DateUtil.formatDate(appmodel.getCreatetime()));
				app.setExt("oexe");
				app.setHeight("max");
				app.setDownload_path(appmodel.getExeurl());
				app.setIcon("./images/file_icon/icon_app/"+appmodel.getIcon());
				app.setIsParent("false");
				app.setIsReadable("1");
				app.setIsWriteable("1");
				app.setMode("-rw- rw- rw-(0666)");
				app.setMtime(DateUtil.formatDate(appmodel.getUpdatetime()));
				app.setName(appmodel.getName());
				app.setPath(appmodel.getUrl());
				app.setResize("1");
				app.setSimple("0");
				app.setSize("0");
				app.setType("url");
				app.setWidth("max");
				applist.add(app);
			}
		}		
		return applist;
	}

	public Map<String, Object> getsearch() {
		
		Map<String, Object> data=new HashMap<>();
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> list=new ArrayList<>();
		Map<String, Object> folder=new HashMap<>();
		
		folder.put("atime", 1521114000);
		folder.put("ctime", 1521114000);
		folder.put("ext", "jpg");
		folder.put("is_readable", 1);
		folder.put("is_writeable", 1);
		folder.put("mode", "-rw- rw- rw-(0666)");
		folder.put("mtime", "1521114000");
		folder.put("name", "ChMkJ1bKysuIRKpdAAUBkGruMksAALIhQESasMABQGo232.jpg");
		folder.put("path", "/desktop/wallpage/ChMkJ1bKysuIRKpdAAUBkGruMksAALIhQESasMABQGo232.jpg");
		folder.put("size", 319119);
		folder.put("type", "file");
		map.put("0", folder);
		map.put("folderlist", "[]");
		data.put("data", map);
		return data;
	}

	public Map<String, Object> getPathInfo(User currentUser, String type, String path) {
		Map<String, Object> map=new HashMap<>();	
		if("folder".equals(type)) {
			QueryParam param=new QueryParam();
			param.setPaging(false);
			param.and("isdir", TermType.eq, "1")
			.and("jsbh", TermType.eq, currentUser.getJsbh())
			.and("dir", TermType.eq, path)
			.and("creator", TermType.eq,currentUser.getId());
			ResponseMessage<PagerResult<CloudfileModel>> respone=logsService.cloudFileQuery(param);
			if(respone!=null&&respone.getStatus()==200) {
				List<CloudfileModel> list=respone.getResult().getData();
				if(list!=null&&list.size()==1) {
					map.put("atime", list.get(0).getUptime());
					map.put("ctime", list.get(0).getUptime());
					map.put("download_path", "");
					map.put("is_readable", 1);
					map.put("is_writeable", 1);
					map.put("mode", "drwx rwx rwx(0777)");
					map.put("mtime", list.get(0).getUptime());
					map.put("name", list.get(0).getUpdator());
					map.put("path",list.get(0).getDir());
					map.put("size", 0);
					map.put("type", "folder");
				}
				
			}
			
		}
		if("file".equals(type)) {
			if(path.indexOf("http")>-1) {
				QueryParam param=new QueryParam();
				param.setPaging(false);
				param.and("isdir", TermType.eq, "0")
				.and("jsbh", TermType.eq, currentUser.getJsbh())
				.and("filename", TermType.eq, path)
				.and("creator", TermType.eq,currentUser.getId());
				ResponseMessage<PagerResult<CloudfileModel>> respone=logsService.cloudFileQuery(param);
				if(respone!=null&&respone.getStatus()==200) {
					List<CloudfileModel> list=respone.getResult().getData();
					if(list!=null&&list.size()==1) {
						map.put("atime", DateUtil.formatDateTime(list.get(0).getUptime()));
						map.put("ctime", DateUtil.formatDateTime(list.get(0).getUptime()));
						map.put("download_path", "");
						map.put("is_readable", 1);
						map.put("is_writeable", 1);
						map.put("mode", "drwx rwx rwx(0777)");
						map.put("mtime", DateUtil.formatDateTime(list.get(0).getUptime()));
						map.put("name", list.get(0).getUpdator());
						map.put("path",list.get(0).getDir());
						map.put("size", list.get(0).getBz());
						map.put("type", "file");
					}
					
				}
			}else {
				QueryParam param=new QueryParam();
				param.setPaging(false);
				param.and("url", TermType.eq, path);
				ResponseMessage<PagerResult<AppModel>> respone=managerService.appQuery(param);
				if(respone!=null&&respone.getStatus()==200) {
					List<AppModel> list=respone.getResult().getData();
					if(list!=null&&list.size()==1) {
						List<ServiceInstance> applist = client.getInstances(list.get(0).getUrl());
						Map<String, Object> result=new HashMap<String, Object>();
				        for (ServiceInstance serviceInstance : applist) {
				        	System.out.println(serviceInstance.getHost()+":"+serviceInstance.getPort());
						}
				        if (applist != null && applist.size() > 0 ) {   
				        	map.put("download_path","http://"+applist.get(0).getUri().getHost()+":"+applist.get(0).getUri().getPort());
				        }
						map.put("atime", DateUtil.formatDateTime(list.get(0).getUpdatetime()));
						map.put("ctime", DateUtil.formatDateTime(list.get(0).getCreatetime()));
						map.put("ext", "oexe");
						
						map.put("is_readable", 1);
						map.put("is_writeable", 1);
						map.put("mode", "drwx rwx rwx(0777)");
						map.put("mtime", DateUtil.formatDateTime(list.get(0).getUpdatetime()));
						map.put("name", list.get(0).getName());
						map.put("path",list.get(0).getUrl());
						map.put("type", "file");
					}
					
				}
			}
			
		}

			
		
		return map;
	}

	public Map<String, Object> getRecycle(User currentUser) {
		Map<String, Object> data=new HashMap<>();
		data.put("folderlist", getRecycleFiles(currentUser.getId(),"folder"));		
		data.put("filelist",getRecycleFiles(currentUser.getId(),"file"));
			Map<String, Object> info=new HashMap<String, Object>();
			info.put("path_type", "");
			info.put("role", "");
			info.put("id", "");
			info.put("name", "");
		data.put("info",info);	
		data.put("path_read_write", "");
		data.put("thisPath", "{user_recycle}/");
			Map<String, Object> user_space=new HashMap<>();
			user_space.put("size_max", "5");
			user_space.put("size_use", getLeftFileSize(currentUser.getId()));
		data.put("user_space",user_space);
		return data;
	}
	
	//根据用户id 获取剩余网盘大小
	private long getLeftFileSize(String userid) {
		return 0l;
	}

	//根据用户id 查询用户回站 文件夹及文件
	private List<FileModel> getRecycleFiles(String userid,String type) {
		List<FileModel> filelist=new ArrayList<FileModel>();
		return filelist;
	}

	public List<Map<String, Object>> getFileTree(User currentUser, String type, String path) {
		List<Map<String, Object>> allDir=new ArrayList<Map<String,Object>>();
		if("init".equals(type)) {
			QueryParam param=new QueryParam();
			param.setPaging(false);
			param.and("jsbh", TermType.eq, currentUser.getJsbh())
			.and("creator", TermType.eq, currentUser.getId())
			.and("isdir", TermType.eq, "1")
			.and("fdir", TermType.empty,"1");
			ResponseMessage<PagerResult<CloudfileModel>> respone=logsService.cloudFileQuery(param);
			if(respone!=null&&respone.getStatus()==200) {
				List<CloudfileModel> list=respone.getResult().getData();
				for (CloudfileModel cloudfileModel : list) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("name", cloudfileModel.getFilename());
					if("收藏夹".equals(cloudfileModel.getFilename())) {
						map.put("ext", "treeFav");
						map.put("menuType", "menuTreeFavRoot");
						map.put("path", cloudfileModel.getDir());
					}
					if("文档".equals(cloudfileModel.getFilename())) {
						map.put("ext", "treeSelf");
						map.put("menuType", "menuTreeRoot");
						map.put("path", cloudfileModel.getDir());
					}
					if("公文".equals(cloudfileModel.getFilename())) {
						map.put("ext", "groupPublic");
						map.put("menuType", "menuTreeGroupRoot");
						map.put("path", cloudfileModel.getDir());
					}
					if("回收站".equals(cloudfileModel.getFilename())) {
						map.put("ext", "recycle");
						map.put("menuType", "menuTreeGroupRoot");
						map.put("path", "{user_recycle}/");
					}
					map.put("children", null);					
					map.put("type", "folder");
					map.put("open", true);
					map.put("isParent", true);
					allDir.add(map);
				}
				
			}
		}
		if(StrUtil.isNotEmpty(path)) {
			QueryParam param=new QueryParam();
			param.setPaging(false);
			param.and("jsbh", TermType.eq, currentUser.getJsbh())
			.and("creator", TermType.eq, currentUser.getId())
			.and("isdir", TermType.eq, "1")
			.and("fdir", TermType.eq,path);
			ResponseMessage<PagerResult<CloudfileModel>> respone=logsService.cloudFileQuery(param);
			if(respone!=null&&respone.getStatus()==200) {
				List<CloudfileModel> list=respone.getResult().getData();
				for (CloudfileModel cloudfileModel : list) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("name", cloudfileModel.getFilename());
					map.put("path", cloudfileModel.getDir());
					map.put("is_readable", "1");	
					map.put("is_writeable", "1");
					map.put("mode", "drwx rwx rwx(0777)");
					map.put("type", "folder");
					map.put("open", true);
					map.put("isParent", true);
					allDir.add(map);
				}
				
			}
		}		
		
		return allDir;
	}

	public String mkdir(User currentUser, String path) {
		String fdir=path.substring(0,path.lastIndexOf("/"));
		String dir=path.substring(path.indexOf("/")+1, path.length());
		ResponseMessage<String> respone=logsService.mkdir(currentUser.getJsbh(),currentUser.getId(),fdir,dir);
		if(respone!=null&&respone.getStatus()==200) {
			return dir;
		}		
		return null;
	}

	public Map<String, Object> getfiles(User currentUser, String path) {
		Map<String, Object> data=new HashMap<>();
		QueryParam param=new QueryParam();
		param.setPaging(false);
		param.and("jsbh", TermType.eq, currentUser.getJsbh())
		.and("creator", TermType.eq, currentUser.getId());
		Term term1=new Term();
		term1.and("isdir", TermType.eq, "1")
		.and("fdir", TermType.eq,path.substring(0,path.lastIndexOf("/")));
		Term term2=new Term();
		term2.and("isdir", TermType.eq, "0")
		.and("dir", TermType.eq,path.substring(0,path.lastIndexOf("/")));
		param.addTerm(term1).orNest().addTerm(term2);
		ResponseMessage<PagerResult<CloudfileModel>> respone=logsService.cloudFileQuery(param);
		if(respone!=null&&respone.getStatus()==200) {
			data.put("folderlist", getFolders(respone.getResult().getData()));		
			data.put("filelist",getFiles(respone.getResult().getData()));
			Map<String, Object> info=new HashMap<String, Object>();
			info.put("id", "");
			info.put("role","");
			if(path.indexOf("文档")>-1) {
				info.put("path_type", "{group}");
			}else {
				info.put("path_type", "");
			}
			info.put("name", "");
			data.put("info",info);	
			if(path.indexOf("文档")>-1) {
				data.put("path_read_write", "writeable");
			}else {
				data.put("path_read_write", "");
			}
			data.put("thisPath", path);
			Map<String, Object> user_space=new HashMap<>();
			user_space.put("size_max", "5");
			user_space.put("size_use", getAllFileSize(currentUser.getId()));
			data.put("user_space",user_space);
		}
		return data;
	}

	private long getAllFileSize(String id) {
		return 1l;
	}

	private List<File> getFiles(List<CloudfileModel> data) {
		List<File> list=new ArrayList<>();	
		for (CloudfileModel cloudfile : data) {
			if(!cloudfile.getIsdir().equals("1")) {
				File file=new File();
				file.setIsReadbale(1);
				file.setIsWriteable(1);
				file.setExt(cloudfile.getFiletype().toLowerCase());
				file.setAtime(DateUtil.formatDateTime(cloudfile.getUptime()));
				file.setCtime(DateUtil.formatDateTime(cloudfile.getUptime()));
				file.setMode("-rw- rw- rw-(0666)");
				file.setMtime(DateUtil.formatDateTime(cloudfile.getUptime()));
				file.setName(cloudfile.getUpdator());
				file.setPath(cloudfile.getFilename());
				file.setSize(Long.valueOf(StrUtil.isNotEmpty(cloudfile.getBz())?cloudfile.getBz():"0"));
				file.setType("file");
				list.add(file);
			}
		}
		return list;
	}

	private List<File> getFolders(List<CloudfileModel> data) {
		List<File> list=new ArrayList<>();	
		for (CloudfileModel cloudfile : data) {
			if(cloudfile.getIsdir().equals("1")) {
				File file=new File();
				file.setIsReadbale(1);
				file.setIsWriteable(1);
				file.setAtime(DateUtil.formatDateTime(cloudfile.getUptime()));
				file.setCtime(DateUtil.formatDateTime(cloudfile.getUptime()));
				file.setMode("drwx rwx rwx(0777)");
				file.setMtime(DateUtil.formatDateTime(cloudfile.getUptime()));
				file.setName(cloudfile.getUpdator());
				file.setPath(cloudfile.getDir());
				file.setSize(Long.valueOf(StrUtil.isNotEmpty(cloudfile.getBz())?cloudfile.getBz():"0"));
				file.setType("folder");
				list.add(file);
			}
		}
		return list;
		
	}

	public String filepathRname(User currentUser, String path, String rname_to) {
		QueryParam param=new QueryParam();
		param.setPaging(false);
		param.and("jsbh", TermType.eq, currentUser.getJsbh())
		.and("dir", TermType.eq, rname_to)
		.and("creator",TermType.eq,currentUser.getId());
		ResponseMessage<PagerResult<CloudfileModel>> respone=logsService.cloudFileQuery(param);
		if(respone!=null&&respone.getStatus()==200) {
			System.out.println("查询同名文件夹："+JSONUtil.toJsonStr(respone.getResult()));
			if(respone.getResult().getData().size()>0) {				
				return "已有该文件夹，请重新起名!";
			}else {
				QueryParam cparam=new QueryParam();
				cparam.setPaging(false);
				cparam.and("jsbh", TermType.eq, currentUser.getJsbh())
				.and("dir", TermType.eq, path)
				.and("creator",TermType.eq,currentUser.getId());
				ResponseMessage<PagerResult<CloudfileModel>> crespone=logsService.cloudFileQuery(cparam);
				if(crespone!=null&&crespone.getStatus()==200) {					
					List<CloudfileModel> list=crespone.getResult().getData();
					System.out.println("查询文件夹："+JSONUtil.toJsonStr(crespone.getResult()));
					if(list!=null&&list.size()==1) {
						CloudfileModel cloudfile=list.get(0);
						cloudfile.setDir(rname_to);
						cloudfile.setFilename(rname_to.substring(rname_to.lastIndexOf("/")+1,rname_to.length()));
						cloudfile.setUpdator(rname_to.substring(rname_to.lastIndexOf("/")+1,rname_to.length()));
						logsService.cloudFileUpdate(cloudfile);
					}
				}
				
				
			}
		}
		
		return "更新成功";
	}

	public String mkFile(User currentUser, String path) throws IOException  {
		String dir=ClassUtils.getDefaultClassLoader().getResource("").getPath();
		String filename=path.substring(path.lastIndexOf("/")+1, path.length());
		String ext=filename.substring(filename.indexOf(".")+1,filename.length() );
		java.io.File file = new java.io.File(dir+"static\\others\\newfile-tpl\\newfile."+ext);
		System.err.println(dir+"static/others/newfile-tpl/"+filename);
		System.err.println(file.length());
        // 需要导入commons-fileupload的包
        FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()),false,filename,(int)file.length(),file.getParentFile());
        byte[] buffer = new byte[4096];
        int n;
        try (
        	InputStream inputStream = new FileInputStream(file); 
        	OutputStream os = fileItem.getOutputStream()){
        	while ( (n = inputStream.read(buffer,0,4096)) != -1){
               os.write(buffer,0,n);
        		}
            //也可以用IOUtils.copy(inputStream,os);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            logsService.upload(multipartFile, currentUser.getJsbh(), path.substring(0,path.lastIndexOf("/")), "", "0", currentUser.getId());
            return multipartFile.getName();
        }catch (IOException e){
            e.printStackTrace();
        }
		return "";
	}

	public String uploadFile(User currentUser, MultipartFile file, String path) {
		 logsService.upload(file, currentUser.getJsbh(), path.substring(0,path.lastIndexOf("/")), "", "0", currentUser.getId());
		 return file.getName();
	}

}
