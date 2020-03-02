define("app/src/setting/setting", [
	"./page/about.html.js",
	"./page/fav.html.js",
	"./page/help.html.js",
	"./page/member.html.js",
	"./page/system.html.js",
	"./page/theme.html.js",
	"./page/user.html.js",
	"./page/wall.html.js",
	"./page/appsetting.html.js",
	],
function(e) {
	seajs.use("lib/layui/css/layui.css");
	var setting; 
	var xxdmTable;
	var _setTheme = function(skin) {
		core.setSkin(skin);
		ShareData.frameTop("",function(e) {e.ui.setTheme(skin)});
		if("diy" != skin){
			$(".theme_diy_setting").addClass("hidden");
		}else{
			$(".theme_diy_setting").removeClass("hidden");
		}
	};
	var setThemeSelf = function(skin) {
		core.setSkin(skin);
	};
	template.helper("menu_info_decode",	function(e) {
			var html = htmlEncode(urlDecode(e));
			return html;
	});
	
	var getTemplate = function(html) {
		var template = {
			about: e("./page/about.html"),
			fav: e("./page/fav.html"),
			help: e("./page/help.html"),
			member: e("./page/member.html"),
			system: e("./page/system.html"),
			theme: e("./page/theme.html"),
			user: e("./page/user.html"),
			wall: e("./page/wall.html"),
			appsetting:e("./page/appsetting.html")
		};
		return template[html];
	};
	var _setGoto = function(page) {
		if("" == page || undefined == page){
			page = "user";
		}
		setting = page;
		if("fav&" == page.substring(0, 4)){
			page = "fav";
		}
		$(".selected").removeClass("selected");
		$("ul.setting a#" + page).addClass("selected");
		var href = window.location.href; 
		if(- 1 != href.indexOf("#")){
			href = href.substr(0, href.indexOf("#"));
		}
		window.location.href = href + "#" + page;
		$.ajax({
			url: "./setting/slider?slider=" + page,
			beforeSend: function() {
				$(".main").html("<img src='" + G.static_path + "images/common/loading.gif'/>");
			},
			success: function(result) {
				var menu_left = $(".menu_left .selected").clone();
				
				menu_left.find(".ripple_father").remove();
				var headhtml = "<div class='h1'>" + menu_left.html() + "</div>";
				if(menu_left.html()==null){
					headhtml="<div class='h1'>平台设置中心</div>";
				}
				var temp= getTemplate(page);
				var gen = template.compile(temp);
				var mainhtml = gen({
					urlDecode: urlDecode,
					LNG: LNG,
					G: G,
					data: result.data,
					info: result.info
				});
				$(".main").html(headhtml + mainhtml);
				$(".main").fadeIn("fast");
				if("member" == page) System.init();
				if("theme" == page)  themeInit();				
				if("appsetting" == page)  AppSetting.init();
				setting = page;
				$("a,img").attr("draggable", "false");
			},
			complete:function(){
			}
		});
		if("user" == page){
			layui.use(['form'], function() {
				form=layui.form;
			});
			setTimeout(function(){loadAppSelect()},500)
		}
	};
	var themeInit = function() {		
		seajs.use("lib/bootstrap-slider/bootstrap-slider.css");
		seajs.use("lib/colorpicker/css/colorpicker.css");
		e.async("lib/bootstrap-slider/bootstrap-slider.js",function() {
			$("#colorRotate").slider().on("slide", _setTheme);
		});
		e.async("lib/colorpicker/js/colorpicker",function() {
			$(".colorpicker").remove();
			$(".color_picker").ColorPicker({
				onBeforeShow: function(e) {
					$(e).attr("input-name", $(this).attr("name"));
					$(this).ColorPickerSetColor(this.value);
				},
				onShow: function(e) {
					 $(e).fadeIn(100);
					 return false
				},
				onHide: function(e) {
					$(e).fadeOut(100);
					return false;
				},
				onChange: function(e, t) {
					var name = $("input[name=" + $(this).attr("input-name") + "]");
					name.val("#" + t);
					_setTheme();
				}
			});
			$(".color_picker").bind("keyup",function() {
				$(this).ColorPickerSetColor(this.value)
			});
		});
		var diysetting = $(".theme_diy_setting");
		diysetting.find("input[name]").unbind("change").bind("change",function() {
			var name = $(this).attr("name");
			if("bg_type" == name){
				$(".theme_bg_type_image,.theme_bg_type_color").addClass("hidden");
				$(".theme_bg_type_" + $(this).val()).removeClass("hidden");
			}
			if(!$(this).attr("data-slider-value")){
				setdiy();
			}
			
		});
		diysetting.find(".theme_diy_save").unbind("click").bind("click",function() {
			var theme_diy = G.user_config.theme_diy;
			$.ajax({
				url: "setting/set?key=theme_diy&v=" + urlEncode(jsonEncode(theme_diy)),
				dataType: "json",
				success: function(data) {
					Tips.tips(data)
				}
			})
		});
		diysetting.find(".color_list").each(function() {
			var colors = jsonDecode($(this).attr("data-color"));
			$(this).css("background-image", "linear-gradient(" + colors.color_rotate + "deg," + colors.start_color + "," + colors.end_color + ")");
		});
		diysetting.find(".color_list").unbind("click").bind("click",function() {
			var colors = jsonDecode($(this).attr("data-color"));
			$.each(colors,function(key, value) {
				var name = diysetting.find("input[name=" + key + "]");
				if("color_rotate" == key){
					$("#colorRotate").slider("setValue", parseInt(value))
				}else{
					name.val(value);
				}
				setdiy();
			});
		});
		var setdiy = function() {
			if ("diy" == LocalData.get("theme")) {
				var config = {};
				diysetting.find("input[name]").each(function() {
					var name = $(this).attr("name");
					var value = $(this).val();
					if("checkbox" == $(this).attr("type")){
						value = Number($(this).is(":checked"))
					}else{
						if("radio" == $(this).attr("type")){
							value = diysetting.find("[name=" + name + "]:checked").val();
						}
					}
					config[name] = value;
				}),
				LocalData.setConfig("awd_diy_style", config);
				core.setSkin("diy");
				ShareData.frameTop("",function(e) {e.ui.setTheme("diy")});
			}
		}
	};
	var init = function() {
		if(G.user_type !="0"){
            $("ul.setting #system").remove();
		}
		if(G.is_root ){
			$("ul.setting #member").show();
		}else{
			$("ul.setting #member").hide();
		}
        setting = location.hash.split("#", 2)[1];
        if(G.user_type!="0"){
            if(G.is_admin=="0"&&setting=="system")setting="appsetting";      
        }      
		_setGoto(setting);
		$("ul.setting a").click(function() {
			if(setting != $(this).attr("id")){
				setting = $(this).attr("id");
				_setGoto(setting);
			}
		});
		$("#password_new").keyEnter(function() {
			Setting.tools();
		});
		$(".user_config_setting .form_row input").die("change").live("change",function() {
			var obj = $(this);
			var name = obj.attr("name");
			var value = obj.val();
			if("checkbox" == obj.attr("type") ){
				value = obj.prop("checked") ? "1": "0";
			}
			setkey(name, value);
		});
		$(".path_select").die("click").live("click",function() {
			core.api.pathSelect({
				type: "file",
				title: "请选择图片...",
				allowExt: "png|jpg|bmp|gif|jpeg|ico|svg|tiff"
			},function(path) {
				var url = core.path2url(path);
				$(".path_select").parent().find("input[type=text]").val(url).trigger("change");
				Setting.tools();
			});
		});
		$(".randomImage").die("click").live("click",function() {
			var obj = $(this);
			var downImage = function(url) {
				var path = G.my_desktop + "wallpage/";
				$.get("./explorer/mkdir?repeat_type=replace&path=" + path,	function() {
					$.get("./explorer/serverDownload?type=download&save_path=" + path + "&url=" + urlEncode(url))
				});
			}
			core.api.randomImage(function(url) {
				obj.addClass("moveCircle");
				obj.parent().find("input[type=text]").val(url).trigger("change");
				if(1 == $('.box[data-type="wall"]').length){
					Setting.tools();
				}
				setTimeout(function() {
					obj.removeClass("moveCircle")
				},1e3);
				downImage(url);
			});
		});
		$(".box .list").live("hover",function() {
			$(this).addClass("listhover");
			$(this).toggleClass("listhover");
		});
		$(".box .list").live("click",function() {
			var obj = $(this);
			var parent = obj.parent();
			var type = parent.attr("data-type");
			var value = obj.attr("data-value");
			parent.find(".this").removeClass("this");
			obj.addClass("this");
			switch (type) {
			case "wall":
				var path = G.static_path + "images/wall_page/" + value + ".jpg";
				$("#wall_url").val("");
				ShareData.frameTop("",function(e) {e.ui.setWall(path)});
				break;
			case "theme":
				_setTheme(value);
				break;
			default:
			}
			setkey(type, value);
		});
		$(".nav a").live("click",function() {
			$(".nav a").removeClass("this"),
			$(this).addClass("this");
			var page = $(this).attr("data-page");
			$(this).parent().parent().find(".panel").addClass("hidden");
			$(this).parent().parent().find("." + page).removeClass("hidden");
			//alert(page);
			if("setting_xxdm"==page){
				SystemSetting.freshXxdmTree();
			}
			if("setting_wgzd"==page){
				SystemSetting.freshWgzdTree();
			}
			if("setting_role"==page){
				SystemSetting.freshRoleList();
			}
			if("setting_classfic"==page){
				SystemSetting.freshClassfic();
			}
			if("setting_flow"==page){
				SystemSetting.freshflowmap();
			}
			if("setting_node"==page){
				SystemSetting.freshflowmapTree();
			}
		});
		/*layui.use(['form'], function() {
			form=layui.form;
		});
		setTimeout(function(){loadAppSelect()},500)*/
		
		
	};
	
	//加载默认开启应用下拉框的数据
	var loadAppSelect = function(){
		layui.use(['form'], function() {
			form=layui.form;
		$.ajax({
			url:'/app/getJsApp',
			type:'GET',
			data:{"menu":"2","userid":"1"},//随意定义的参数无用,后台需接收判断
			success:function(res){
				if(res.code=="true"){
					$("#appSelect").append("<option >请选择一个应用</option>");
					$("#appSelect").append("<option >无</option>");
					$("#appSelect").append("<option value='AWD-MIS-APPSTORE-WEB' style='font-weight: bold;'>应用商店</option>");
					form.render('select');
				var val = res.data;
				var keyList = [];
				for(var key in val){
					keyList.push(key)
				}
				 var url;
				for(var i= 1;i<keyList.length;i++){
					if("领导办公" == res.data[keyList[i]].name){
						url="AWD-KSS-LDBG-WEB"
					}else if("应用商城" == res.data[keyList[i]].name){
						url="AWD-MIS-APPSTORE-WEB"
					}else if("领导岗位" == res.data[keyList[i]].name){
						url="AWD-MIS-LDBG-WEB"
					}else if("收押岗位" == res.data[keyList[i]].name){
						url="AWD-KSS-SYQT-WEB"
					}else if("管教岗位" == res.data[keyList[i]].name){
						url="AWD-KSS-GJXT-WEB"
					}else if("巡控岗位" == res.data[keyList[i]].name){
						url="AWD-KSS-XSJK-WEB"
					}else if("医务岗位" == res.data[keyList[i]].name){
						url="AWD-KSS-YWGL-WEB"
					}else if("财务岗位" == res.data[keyList[i]].name){
						url="AWD-KSS-CWGL-WEB"
					}else if("综合岗位" == res.data[keyList[i]].name){
						url="AWD-KSS-ZHBL-WEB"
					}else if("查询统计" == res.data[keyList[i]].name){
						url="AWD-KSS-TONGJI-WEB"
					}else if("流程配置管理工具" == res.data[keyList[i]].name){
						url="AWD-MIS-ACTIVITI-SERVER"
					}else if("智慧分析平台" == res.data[keyList[i]].name){
						url="AWD-MIS-CHARTS-WEB"
					}
					$("#appSelect").append("<option value='" + url+ "' style='font-weight: bold;'>" +  res.data[keyList[i]].name + "</option>");
				}
					form.render('select');
				 }else{
					//手动添加默认应用，以防止没有数据下拉框渲染失败
						$("#appSelect").append("<option >请选择一个应用</option>");
						$("#appSelect").append("<option >无</option>");
						$("#appSelect").append("<option value='AWD-MIS-APPSTORE-WEB' style='font-weight: bold;'>应用商店</option>");
						form.render('	select');
				 }
				
			},
			error:function(){
				Tips.tips("获取应用失败，请联系管理员","error");
			}
		});
		//下拉框选中事件
		form.on('select(appSelect)', function(data){   
			var val=data.value;
			if(val == "请选择一个应用" || val == "无"){
				val="0";
			}
		//保存用户想默认打开的应用
			$.ajax({
				url:"/setting/set",
				type:'get',
				data:{"key":"openFirst","v":val},
				success:function(res){
					if(res.code == "true"){
						Tips.tips(res.data);
					}
				},
				error:function(){
					Tips.tips("设置失败，请联系管理员","error");
				}
			})
			
		  });
		
		})
	}
	var setkey = function(key, value) {
		var path = "setting/set?key=" + key + "&v=" + value;
		$.ajax({
			url: path,
			dataType: "json",
			success: function(data) {
				if(data.code){
					Tips.tips(data)
				}else{
					if(core.authCheck("setting:set")){
						Tips.tips("错误，文件没有写权限！", false);
					}else{
						Tips.tips("配置保存失败,管理员禁止了此权限!", false);
					}
				}
			}
		});
	};
	var _tools = function() {
		var seleced = $(".selected").attr("id");
		switch (seleced) {
		case "user":
			var password_now = urlEncode($("#password_now").val());
			var password_new = urlEncode($("#password_new").val());
			var password_new_second=urlEncode($("#password_new_second").val());
			if ("" == password_new || "" == password_now) {
				Tips.tips("密码不能为空!", "error");
				break
			}
			if(password_new!=password_new_second){
				Tips.tips("你两次输入的密码不一致，请重新输入!", "error");
				$("#password_now").val("");
				$("#password_new").val("");
				$("#password_new_second").val("");
				break
			}
			$.ajax({
				url: "user/changePassword?password_now=" + password_now + "&password_new=" + password_new,
				dataType: "json",
				success: function(data) {
					Tips.tips(data.info,"success");
					if (data.code=="true") {
						var top = ShareData.frameTop();
						top.location.href = "./user/logout";
					}
				}
			});
			break;
		case "wall":
			var warll_url = $("#wall_url").val();
			if ("" == warll_url) {
				Tips.tips("图片地址不能为空!", "error");
				break
			}
			ShareData.frameTop("",function(e) {	e.ui.setWall(warll_url)});
			$(".box").find(".this").removeClass("this");
			$.ajax({
				url: "setting/set?key=wall&v=" + urlEncode(warll_url),
				dataType: "json",
				success: function(data) {
					Tips.tips(data)
				}
			});
		default:
		}
	};
	init();
	return{
		setGoto: _setGoto,
		tools: _tools,
		setThemeSelf: setThemeSelf,
		setTheme: _setTheme
	}
});
