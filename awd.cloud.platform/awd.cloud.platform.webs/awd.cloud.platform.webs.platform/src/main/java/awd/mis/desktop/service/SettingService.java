package awd.mis.desktop.service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import awd.framework.common.utils.StringUtils;
import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.SettingModel;
import awd.mis.desktop.model.UserinfoModel;
import awd.mis.desktop.model.UsersettingModel;
import awd.mis.desktop.service.SettingService;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import awd.mis.desktop.tools.TermType;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@Service("settingService")
public class SettingService {
	//平台编号
	private String platformid="eyJ0eXBlIjoiYXBwIiwid2lkdGgiOiIiLCJoZWlnaHQiOiIiLCJjb250ZW50IjoiY29yZS5kZXNrdG9wKCk7In0=";
		
	@Autowired
	private  ManagerService managerService;
	@Autowired
	private LogsService  logsService;
	
	public Map<String, Object> getSetting(String slider) {
		Map<String, String> menuname=new HashMap<String, String>();
		menuname.put("search", "超级搜索");
		menuname.put("desktop", "桌面");
		menuname.put("explorer", "文件管理");
		menuname.put("message","信息中心");
		Map<String, Object> setting=new HashMap<>();
		Map<String, Object> system_init=list2map(managerService.getSettingByGroup(platformid,"SYSTEM_INIT").getResult());
		if("system".equals(slider)) {
			setting.put("system_name", 	system_init.get("system_name")==null?"AwdCloud":system_init.get("system_name"));
			setting.put("system_desc", 	system_init.get("system_desc")==null?"监管应用云平台":system_init.get("system_desc"));
			setting.put("first_in", 	system_init.get("first_in")==null?"desktop":system_init.get("first_in"));
			setting.put("new_user_folder", system_init.get("new_user_folder")==null?"公文,文档,回收站":system_init.get("new_user_folder"));
			
			Map<String, Object> system_menu=list2map(managerService.getSettingByGroup(platformid,"SYSTEM_MENU").getResult());
			List<Map<String, Object>>menus=new ArrayList<>();
			Set<String> keys=system_menu.keySet();			
			for (String key : keys) {
				Map<String, Object> menu= new HashMap<>();
				JSONObject object=JSONUtil.parseObj(system_menu.get(key));
				menu.put("name", object.get("name"));
				menu.put("nameString", menuname.get(object.get("name")));
				menu.put("type", object.get("type"));
				menu.put("url", object.get("url"));
				menu.put("target", object.get("target"));
				menu.put("use", object.get("use"));
				menus.add(menu);
			}			
			setting.put("menu", menus);
		}
		
		if("user".equals(slider)) {//个人设置
			Map<String, Object> users=new HashMap<>();
			Map<String, Object> system_config=list2map(managerService.getSettingByGroup(platformid,"CONFIG").getResult());
			setting.put("codefontall", system_config.get("codefontall")==null?"Consolas,Courier,DejaVu Sans Mono,Liberation Mono,Menlo,Monaco,Monospace,Source Code Pro":system_config.get("codefontall"));
			setting.put("codethemeall", system_config.get("codethemeall")==null?"chrome,clouds,crimson_editor,eclipse,github,kuroir,solarized_light,tomorrow,xcode,ambiance,monokai,idle_fingers,pastel_on_dark,solarized_dark,twilight,tomorrow_night_blue,tomorrow_night_eighties":system_config.get("codethemeall"));
			setting.put("themeall", system_config.get("themeall")==null?"mac,win10,win7,metro,metro_green,metro_purple,metro_pink,metro_orange,alpha_image,alpha_image_sun,alpha_image_sky":system_config.get("themeall"));
			setting.put("wallall", system_config.get("wallall")==null?"1,2,3,4,5,6,7,8,9,10,11,12,13":system_config.get("wallall"));
			
			users.put("animate_open", "1");
			users.put("file_icon_size", "80");
			users.put("file_repeat", "replace");
			users.put("list_sort_field", "name");
			users.put("list_sort_order", "up");
			users.put("list_type", "icon");
			users.put("recycle_open", "1");
			users.put("resize_config", "{\"filename\":250,\"filetype\":80,\"filesize\":80,\"filetime\":215,\"editor_left_tree_width\":200,\"explorer_left_tree_width\":200}");
			users.put("sound_open", "0");
			users.put("theme", "win7");
			users.put("wall", "8");
			setting.put("user", users);
		}
		if("theme".equals(slider)||"wall".equals(slider)) {
			Map<String, Object> setting_all=new HashMap<>();
			Map<String, Object> system_config=list2map(managerService.getSettingByGroup(platformid,"CONFIG").getResult());
			Map<String, Object> users=new HashMap<>();
			setting_all.put("codefontall", "Consolas,Courier,DejaVu Sans Mono,Liberation Mono,Menlo,Monaco,Monospace,Source Code Pro");
			setting_all.put("codethemeall", "chrome,clouds,crimson_editor,eclipse,github,kuroir,solarized_light,tomorrow,xcode,ambiance,monokai,idle_fingers,pastel_on_dark,solarized_dark,twilight,tomorrow_night_blue,tomorrow_night_eighties");
			setting_all.put("themeall", "mac,win10,win7,metro,metro_green,metro_purple,metro_pink,metro_orange,alpha_image,alpha_image_sun,alpha_image_sky");
			setting_all.put("wallall", "1,2,3,4,5,6,7,8,9,10,11,12,13");
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			QueryParam param = new QueryParam();
			Map<String, String> settings=new HashMap<>();
			param.and("jsbh", TermType.eq, currentUser.getJsbh());
			param.and("userid", TermType.eq, currentUser.getId());
			param.and("app", TermType.eq, platformid);
			ResponseMessage<PagerResult<UsersettingModel>> response=managerService.userSettingQuery(param);
			for (UsersettingModel usersetting : response.getResult().getData()) {
				settings.put(usersetting.getKey(), usersetting.getValue());
			}
			String field="name";
			String order="up";
			if(!StringUtils.isNullOrEmpty(setting.get("list_sort_field,list_sort_order"))) {
			String fields = (String) setting.get("list_sort_field,list_sort_order");
			String[] all = fields.split(",");
			for(int i=0;i<all.length;i++) {
				if(all[i].equals("up")||all[i].equals("down")) {
					order=all[i];
				}else {
					field=all[i];
				}
			}
			}
			users.put("animate_open", "1");
			users.put("file_icon_size", StrUtil.isBlank(settings.get("file_icon_size"))?"80":settings.get("file_icon_size"));
			users.put("file_repeat", "replace");
			users.put("list_sort_field", order);
			users.put("list_sort_order", field);
			users.put("list_type", "icon");
			users.put("recycle_open", "1");
			users.put("resize_config", "{\"filename\":250,\"filetype\":80,\"filesize\":80,\"filetime\":215,\"editor_left_tree_width\":200,\"explorer_left_tree_width\":200}");
			users.put("sound_open", "0");
			users.put("theme", StrUtil.isBlank(settings.get("theme"))?"win10":settings.get("theme"));
			users.put("wall", StrUtil.isBlank(settings.get("wall"))?"8":settings.get("wall"));
			setting.put("setting_all", setting_all);
			setting.put("user", users);
		}		
		
		return setting;
	}

	public Boolean set(User currentUser, String k, String v) {
			QueryParam param = new QueryParam();
			param.and("jsbh", TermType.eq, currentUser.getJsbh());
			param.and("userid", TermType.eq, currentUser.getId());
			param.and("key", TermType.eq, k);
			param.and("app", TermType.eq, platformid);
			
			ResponseMessage<PagerResult<UsersettingModel>> response=managerService.userSettingQuery(param);
			if(response.getStatus()==200) {
				UsersettingModel usersetting;
				if(response.getResult().getData().size()>0) {
				usersetting=response.getResult().getData().get(0);
			}else {
				usersetting=new UsersettingModel();
				usersetting.setApp(platformid);
				usersetting.setJsbh(currentUser.getJsbh());
				usersetting.setKey(k);
				usersetting.setUserid(currentUser.getId());
				usersetting.setState("R2");
				usersetting.setCreator(currentUser.getUsername());
			}
			usersetting.setValue(v);
			managerService.userSettingSaveOrUpdate(usersetting);
			//监管用户修改壁纸会同步更新所级用户壁纸
			//判断用户是否想修改壁纸
			if("wall".equals(k)) {
				String jsType = String.valueOf(currentUser.getJsbh().toCharArray()[7]);
				System.err.println("---------------------------------------jsType: "+jsType);
			//判断监所编号
			if("0".equals(jsType)) {
				QueryParam qParam = new QueryParam();
				//获取所有所级用户
				qParam.setPaging(false);
				ResponseMessage<PagerResult<UserinfoModel>> res= managerService.userInfoQuery(qParam);
				List<Map<String,Object>> list = new ArrayList<>();
				for(UserinfoModel model : res.getResult().getData()) {
					System.err.println(model.getId()+"---");
					Map<String,Object> map = new HashMap<>();
					QueryParam Param = new QueryParam();
					Param.and("app", platformid);
					Param.and("userid", model.getId());
					Param.and("key", "wall");
					Param.setPaging(false);
					ResponseMessage<PagerResult<UsersettingModel>>  rm=managerService.userSettingQuery(Param);
					if(rm.getResult().getTotal() != 0) {
						map.put("id", rm.getResult().getData().get(0).getId());
					}
					map.put("userid", model.getId());
					map.put("key",k);
					map.put("value", v);
					map.put("app", platformid);
					map.put("jsbh", model.getJsbh());
					list.add(map);
				}
				System.err.println(list.size());
				System.err.println(list.size());
				System.err.println(list.size());
				managerService.setWallForList(list);
			}
			}
		}else {
			return false;
		}
		return true;
	}

	public String getHelp() {		
		String helphtml="<div class=\"box\">↵    <div class=\"title\"><span>文件管理</span></div>↵    <p><i class=\"icon-tags\"></i>文件选择：单选,鼠标框选,shift连选,ctrl随意选择,键盘上下左右、home、end选择。</p>↵    <p><i class=\"icon-folder-open\"></i>文件操作：选择文件后,可以进行复制,剪切,删除,属性查看,压缩,重命名,打开预览等操作……</p>↵    <p><i class=\"icon-cloud-upload\"></i>文件上传：多文件批量上传；html5拖拽上传(拖拽到窗口实现无缝上传,支持文件夹拖拽)</p>↵    <p><i class=\"icon-cogs\"></i>右键功能：文件右键,文件夹右键,多选后右键操作,桌面右键,树目录右键操作,右键菜单绑定快捷键<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;↵    (全选——复制——剪切——粘贴——删除——重命名,设置……)</p>↵    <p><i class=\"icon-sitemap\"></i>文件浏览：列表模式,图标模式；双击进入子文件夹；地址栏操作；打开文件夹记录逆势操作记录（前进后退）</p>↵    <p><i class=\"icon-move\"></i>支持拖拽操作：选中后拖拽,实现剪切到指定文件夹功能</p>↵    <p><i class=\"icon-reply\"></i>快捷键操作：delete删除,ctrl+A全选,ctrl+C复制,ctrl+X剪切,文件搜索(内容中搜索)</p>↵</div> ↵<div class=\"box\">↵    <div class=\"title\"><span>文件预览</span></div>↵    <p><i class=\"icon-edit\"></i>文件预览：文本文件内容查看编辑保存；html,swf文件预览,</p>↵    <p><i class=\"icon-picture\"></i>图片预览：自动生成缩略图,图片幻灯片播放；</p>↵    <p><i class=\"icon-music\"></i>音频播放：在线播放音乐,视频文件；支持mp3,wma,mid,aac,wav;mp4,</p>↵    <p><i class=\"icon-play\"></i>视频播放：在线视频文件播放,支持格式：flv,f4v,3gp</p>↵    <p><i class=\"icon-play\"></i>office：office在线预览,支持格式：doc,docx,ppt,pptx,xls,xlsx</p>↵</div>↵<div class=\"box\">↵    <div class=\"title\"><span>快捷键</span></div>↵    <p><i class=\"icon-tags\"></i>enter 打开</p>↵    <p><i class=\"icon-tags\"></i>ctrl+a 全选</p>↵    <p><i class=\"icon-tags\"></i>ctrl+c 复制选中</p>↵    <p><i class=\"icon-tags\"></i>ctrl+v 粘贴</p>↵    <p><i class=\"icon-tags\"></i>ctrl+x 剪切</p>↵    <p><i class=\"icon-tags\"></i>ctrl+f 当前目录进行搜索</p>↵    <p><i class=\"icon-tags\"></i>alt+n 新建文件</p>↵    <p><i class=\"icon-tags\"></i>alt+m 新建文件夹</p>↵    <p><i class=\"icon-tags\"></i>delete 删除选中</p>↵    <p><i class=\"icon-tags\"></i>backspace 后退</p>↵    <p><i class=\"icon-tags\"></i>ctrl+backspace 前进</p>↵    <p><i class=\"icon-tags\"></i>f2 重命名选中(文件文件夹)</p>↵    <p><i class=\"icon-tags\"></i>home/end/up/down/left/right 选择文件</p>↵    <p><i class=\"icon-tags\"></i>anykey  选中以按下字符首字母的文件&文件夹  自动循环选中</p>↵</div>↵";
		return helphtml;
	}
	
	public String getAbout() {
		String abouthtml="<div class=\"box\">↵    <div class=\"title\"><span>KODExplorer是什么？</span></div>↵    <p>KODExplorer是一个公开源码的基于Web的在线文件管理、代码编辑器。它提供了类windows经典用户界面,一整套在线文件管理、文件预览、编辑、上传下载、在线解压缩、音乐播放功能。让你直接在浏览器端实现web开发、源码文件预览、网站部署的同时拥有与本地操作一样方便、快捷、安全的体验。</p>↵    <p><b>——设计理念——</b></p>↵    <p>传承经典,追求创新,为用户提供方便快捷、安全易用的在线云管理系统。</p>↵    <p>无论何时（when）、何地（where）,拥有web只要你想（want）它就是你的管理利器（4W策略）。</p>↵    <p><b>——面向用户——</b></p>↵    <p>目前KODExplorer系统管理主要定位在个人云主机、中小企业云资源管理、网盘管理、中小型网站管理等。Web开发者&站长（老鸟）：在线编辑、压缩备份、部署,经典windows界面操作,上手容易,远离了主机的SSH、ftp复杂枯燥的命令操作。</p>↵    <p>个人私有云（菜鸟）：管理网盘资源,同样经典windows界面操作,可以就地浏览网盘音乐、视屏文件,上传下载快捷方便。</p>↵</div>↵↵<div class=\"box\">↵    <div class=\"title\"><span>特色</span></div>↵    <p>完备的文件管理,强大的在线文件编辑器</p>↵    <p>无论您在哪里,都可以管理您的文件；并可以在线娱乐,在线写代码！就像适宜用操作系统那样。</p>↵    <p>丰富的本地体验移植,右键操作,拖拽,框中选择,快捷键,文件搜索(内容中搜索)……</p>↵    <p>框中选择,拖拽移动,拖拽上传,在线编辑器,影音播放器,解压缩。全面ajax保证性能和体验！</p>↵    <p>各个功能直接无缝连接；以对话框形式存在,多任务管理等功能</p>↵    <p>编辑器支持多文档；支持ZendCoding  html,css,js代码编写效率更高！</p>  ↵    <p>完美中文支持,各种情况下乱码解决；</p>                    ↵</div>↵↵<div class=\"box\">↵    <div class=\"title\"><span>开源技术采用</span></div>↵    <p><b>1.Jquery:</b>jpuery(plugin:Hotkeys.ztree.contentmenu).js主流开发框架。对Dom操作、BOM操作、css操作,以及Ajax封装</p>↵    <p><b>2.ArtDialog:</b>一个设计优美,对浏览器兼容性极强的对话框插件。使弹出窗口处理,事件以及数据的传输得到了更好的统一管理方式</p>↵    <p><b>3.Ztree:</b>列表树控件,扩展性很强的树形数据操作插件</p>↵    <p><b>4.codemirror:</b>一个代码编辑js插件,支持各种编程语言的高亮处理</p>↵    <p><b>5.zendcoding:</b>一个支持html代码快速编写插件。定义方式简化代码编写。有简单编译功能</p>         ↵    <p><b>6.less:</b>一种高效的,函数式css开发模式,提高前端css样式表的可复用性。有简单编译功能</p>↵    <p><b>7.SWFUpload:</b>flash多文件上传</p>↵    <p><b>8.CMP4:</b>一个非常优秀的flash媒体文件播放插件,支持流媒体,常用音乐视频的播放工具。支持换皮肤,高可配置性的设置。列表xml的动态加载。mms流媒体,rstp开源协议媒体播放支持</p>↵</div>";
		return abouthtml;
	}
	
	private Map<String, Object>list2map(List<SettingModel> list){
		Map<String, Object> map=new HashMap<>();
		for (SettingModel settingModel : list) {
			map.put(settingModel.getProname(), settingModel.getProvalue());
		}
		return map;
	}

	public boolean setSetting(User currentUser,JSONObject json) {
		Set<String> keys=json.keySet();
		boolean result=false;
		for (String key : keys) {
			if("menu".equals(key)) {
				cn.hutool.json.JSONArray array= JSONUtil.parseArray(json.get("menu"));
				for(int i=0;i<array.size();i++) {
					JSONObject menuJson=(JSONObject) array.get(i);
					//菜单详细
					SettingModel setting=managerService.getSettingByKey(platformid,menuJson.getStr("name")).getResult();
					if(setting==null) {
						setting=new SettingModel();
						setting.setAppcode(platformid);
						setting.setProgroup("SYSTEM_MENU");
						setting.setProtype("1");
						setting.setProname(menuJson.getStr("name"));
						setting.setProvalue(URLDecoder.decode(menuJson.toString()));
						setting.setCreator("管理员");
						ResponseMessage<String> response=managerService.settingSave(setting);
						if(response.getStatus()!=200) {				
							throw new RuntimeException();
						}
					}else {
						setting.setProvalue(URLDecoder.decode(menuJson.toString()));
						ResponseMessage<String> response=managerService.settingUpdate(setting.getId(),setting);
						if(response.getStatus()!=200) {				
							throw new RuntimeException();
						}
					}					
					
					
				}
			}else {
				String value=json.getStr(key);	
				ResponseMessage<SettingModel> respone=managerService.getSettingByKey(platformid,key);
				if(respone.getStatus()==200) {
					SettingModel setting=respone.getResult();
					if(setting==null) {
						setting=new SettingModel();
						setting.setAppcode(platformid);
						setting.setProgroup("SYSTEM_INIT");
						setting.setProtype("1");
						setting.setProname(key);
						setting.setProvalue(URLDecoder.decode(value));
						setting.setCreator(currentUser.getUsername());
						ResponseMessage<String> response=managerService.settingSave(setting);
					}else {
						setting.setProvalue(URLDecoder.decode(value));
						setting.setUpdator(currentUser.getUsername());
						ResponseMessage<String> response=managerService.settingUpdate(setting.getId(),setting);
					}
				}else {				
					throw new RuntimeException();
				}
			}
			
		}	
		result=true;
		return result;
	}

	public boolean setSetting(User currentUser, String appcode, JSONObject json) {
		Set<String> keys=json.keySet();
		boolean result=false;
		for (String key : keys) {
			String value=json.getStr(key);	
			ResponseMessage<UsersettingModel> respone=managerService.getUserSettingByKey(currentUser.getId(),appcode,key);;
			if(respone.getStatus()==200) {
				UsersettingModel usersetting=respone.getResult();
				if(usersetting==null) {
					usersetting=new UsersettingModel();
					usersetting.setApp(appcode);
					usersetting.setJsbh(currentUser.getJsbh());
					usersetting.setKey(key);
					usersetting.setValue(URLDecoder.decode(value));
					usersetting.setUserid(currentUser.getId());
					usersetting.setState("R2");
					usersetting.setCreator(currentUser.getUsername());
					ResponseMessage<String> response=managerService.userSettingSaveOrUpdate(usersetting);
				}else {
					usersetting.setValue(URLDecoder.decode(value));
					usersetting.setUpdator(currentUser.getUsername());
					ResponseMessage<String> response=managerService.userSettingSaveOrUpdate(usersetting);
				}
			}else {				
				throw new RuntimeException();
			}
		}	
		
		result=true;
		return result;
	}
	
	
	
	
		
}
