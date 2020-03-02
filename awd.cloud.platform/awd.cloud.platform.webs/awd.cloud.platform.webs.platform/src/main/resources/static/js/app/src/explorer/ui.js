define("app/src/explorer/ui", ["./fileContent"],
function(require, exports) {
	var fileContent = require("./fileContent"),
	f5 = fileContent.f5,
	f5Callback = fileContent.f5Callback,
	MyPicasa = new Picasa,
	initListType = function(e) {
		G.user_config.list_type = e,
		LocalData.set("list_type", e),
		$(".set_icon_size").hide(),
		$(".tools-right button").removeClass("active"),
		$("#set_" + e).addClass("active"),
		$("#list_type_list,.list_split_box").addClass("hidden"),
		$(".set-file-icon-size").hide(),
		$(Config.FileBoxSelector).removeClass("fileList_icon fileList_list fileList_list_split"),
		"list" == e ? ($(Config.FileBoxSelector).addClass("fileList_list"), $("#list_type_list").removeClass("hidden"), ui.fileListResize.bindHeaderResize()) : "icon" == e ? ($(Config.FileBoxSelector).addClass("fileList_icon"), $(".set_icon_size").show(), $(".set-file-icon-size").show()) : "list_split" == e && ($(Config.FileBoxSelector).addClass("fileList_list_split"), $(".list_split_box").removeClass("hidden")),
		$(".menu_seticon").removeClass("selected"),
		$(".set_set" + e).addClass("selected"),
		$(".fileContinerMore").css("top", 0);
		var i = $(".frame-right-main .tools").outerHeight();
		"list" == e && (i += 26),
		$(".frame-header").is(":visible") && (i += $(".frame-header").outerHeight()),
		$(".bodymain").css("top", i)
	},
	setListType = function(e) {

		initListType(e),
		f5(false, false),
		undefined === G.sid && $.get("setting/set?key=list_type&v=" + e)
	},
	setListSort = function(e, i) {
		0 != e ? (G.user_config.list_sort_field = e, $(".menu_set_sort").removeClass("selected"), $(".set_sort_" + e).addClass("selected")) : e = G.user_config.list_sort_field,
		0 != i ? (G.user_config.list_sort_order = i, $(".menu_set_desc").removeClass("selected"), $(".set_sort_" + i).addClass("selected")) : i = G.user_config.list_sort_order,
		LocalData.set("list_sort_field", e),
		LocalData.set("list_sort_order", i),
		f5(false, true),
		$.ajax({
			url: "setting/set?key=list_sort_field,list_sort_order&v=" + e + "," + i
		})
		f5(false, true),
		ui.f5()
	},
	bindEventView = function() {
		$(".menuRecycleButton").bind("mouseenter",
		function() {
			$(this).addClass("recycle_hover")
		}).bind("mouseleave",
		function() {
			$(this).removeClass("recycle_hover")
		}).bind("click",
		function() {
			ui.path.list("{user_recycle}")
		}),
		$(".menuShareButton").bind("mouseenter",
		function() {
			$(this).addClass("share_hover")
		}).bind("mouseleave",
		function() {
			$(this).removeClass("share_hover")
		}).bind("click",
		function() {
			ui.path.list("公文/")
		})
	},
	bindEventSort = function() {
		$("#main_title div").die("click").live("click",
		function() {
			$(this).hasClass("resize") || ("up" == $(this).attr("id") ? $(this).attr("id", "down") : $(this).attr("id", "up"), setListSort($(this).attr("field"), $(this).attr("id")))
		})
	},
	bindEventTools = function() {
		$(".tools a,.tools button").bind("click",
		function() {
			var e = $(this).attr("id");
			toolsAction(e)
		})
	},
	bindEventTheme = function() {
		$(".dropdown-menu-theme li").click(function() {
			var e = $(this).attr("theme");
			ui.setTheme(e),
			$.ajax({
				url: "setting/set?key=theme&v=" + e,
				dataType: "json",
				success: function(e) {
					if (!e.code) {
						var i = "错误，文件没有写权限！";
						core.authCheck("setting:set") || (i = "配置保存失败,管理员禁止了此权限!"),
						Tips.tips(i, false)
					}
				}
			})
		})
	},
	bindEventMenu = function() {
		$(".dlg_goto_path").bind("click",
		function() {
			var e = G.json_data.info.admin_real_path;
			ui.path.list(e)
		}),
		$(".toolPathMore").die("click").live("click",
		function() {
			if ($(this).hasClass("active")) return $(".menuToolPath").trigger("contextmenu:hide"),
			$(this).removeClass("active"),
			undefined;
			$(this).addClass("active"),
			$(".menuToolPath").removeClass("fadIn").addClass("menuShow");
			var e = $(this).offset();
			$(this).contextMenu({
				x: e.left - 4,
				y: e.top + $(this).outerHeight() - 1
			})
		}),
		$("body").bind("click",
		function() {
			$(".toolPathMore").removeClass("active"),
			$(".menuToolPath").trigger("contextmenu:hide")
		})
	},
	getRowfileNumber = function() {
		if ("icon" != G.user_config.list_type) return 1;
		var e = $(Config.FileBoxSelector).width(),
		i = $(Config.FileBoxClass).outerWidth() + $sizeInt($(Config.FileBoxClass).css("margin-right"));
		return parseInt(e / i)
	},
	getPagefileNumber = function() {
		var e = getRowfileNumber(),
		i = $(Config.BodyContent).outerHeight(),
		t = $(Config.FileBoxClass).outerHeight() + $sizeInt($(Config.FileBoxClass).css("margin-bottom"));
		return Math.ceil(i / t) * e
	},
	getColfileNumberDesktop = function() {
		var e = $(Config.FileBoxSelector).outerHeight() - 48,
		i = $(Config.FileBoxClass).outerHeight() + 10;
		return parseInt(e / i)
	},
	toolsAction = function(e) {
		switch (e) {
		case "recycle_clear":
			ui.path.recycleClear();
			break;
		case "newfile":
			ui.path.newFile();
			break;
		case "refresh":
			ui.f5();
			break;
		case "newfolder":
			ui.path.newFolder();
			break;
		case "upload":
			core.upload();
			break;
		case "selectAll":
			ui.fileSelect.selectPos("all");
			break;
		case "download":
			ui.path.download();
			break;
		case "set_icon":
			$("#set_icon").hasClass("active") || setListType("icon");
			break;
		case "set_list":
			$("#set_list").hasClass("active") || setListType("list");
			break;
		case "set_list_split":
			$("#set_list_split").hasClass("active") || setListType("list_split");
			break;
		default:
		}
	},
	bindHotKeySelectFile = function() {
		var e, i = 0,
		t = "",
		a = 300;
		Mousetrap.bind(["1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+", "[", "{", "]", "}", "|", "/", "?", ".", ">", ",", "<", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"],
		function(n) {
			var o = String.fromCharCode(n.charCode);
			return 0 == i ? (i = timeFloat(), t = o, e = setTimeout(function() {
				ui.path.setSelectByChar(t),
				i = 0
			},
			a), undefined) : o == t.substr( - 1) ? (ui.path.setSelectByChar(t), i = 0, undefined) : (a > timeFloat() - i && (i = timeFloat(), t += o, clearTimeout(e), e = setTimeout(function() {
				ui.path.setSelectByChar(t),
				i = 0
			},
			a)), undefined)
		})
	},
	bindHotKey = function() {
		bindHotKeySelectFile(),
		Mousetrap.bind(["f1", "alt+left", "backspace", "alt+right", "ctrl+backspace", "command+backspace", "ctrl+shift+r", "f5", "left", "up", "right", "down", "home", "end", "shift+left", "shift+up", "shift+right", "shift+down", "shift+home", "shift+end", "pageup", "pagedown", "ctrl+a", "command+a", "ctrl+shift+n", "ctrl+shift+f", "del", "shift+del", "f2", "ctrl+enter", "command+enter", "shift+enter", "space", "enter", "ctrl+u", "command+u", "ctrl+e", "command+e", "ctrl+c", "command+c", "ctrl+x", "command+x", "ctrl+v", "command+v", "ctrl+f", "command+f", "f3", "ctrl+i", "alt+i", "alt+n", "alt+m", "alt+enter", "ctrl+s", "command+s", "alt+f4"],
		function(e, i) {
			if ("none" != $("#PicasaView").css("display")) return ! 0;
			if (ui.isEdit()) return ! 0;
			if (rightMenu.isDisplay()) return ! 0;
			if ($(".dialog_path_remove").length > 0) return ! 0;
			var t = ["ctrl+c", "command+c"];
			switch (inArray(t, i) || stopPP(e), i) {
			case "f1":
				core.setting("help");
				break;
			case "alt+left":
			case "backspace":
				ui.path.history.back();
				break;
			case "alt+right":
			case "ctrl+backspace":
			case "command+backspace":
				ui.path.history.next();
				break;
			case "ctrl+shift+r":
			case "f5":
				ui.f5(true, true);
				break;
			case "left":
			case "up":
			case "right":
			case "down":
			case "home":
			case "end":
			case "shift+left":
			case "shift+up":
			case "shift+right":
			case "shift+down":
			case "shift+home":
			case "pageup":
			case "pagedown":
			case "shift+end":
				ui.fileSelect.selectPos(i);
				break;
			case "ctrl+a":
			case "command+a":
				ui.fileSelect.selectPos("all");
				break;
			case "ctrl+shift+n":
				ui.path.newFolder();
				break;
			case "ctrl+shift+f":
				ui.path.newFile();
				break;
			case "del":
				ui.path.remove();
				break;
			case "shift+del":
				ui.path.remove(false, true);
				break;
			case "f2":
			case "ctrl+enter":
			case "command+enter":
				ui.path.rname();
				break;
			case "shift+enter":
				ui.path.download();
				break;
			case "space":
				ui.path.open();
				break;
			case "enter":
				ui.path.open();
				break;
			case "ctrl+u":
			case "command+u":
				core.upload();
				break;
			case "ctrl+e":
			case "command+e":
				ui.path.openEditor();
				break;
			case "ctrl+c":
			case "command+c":
				ui.path.copy();
				break;
			case "ctrl+x":
			case "command+x":
				ui.path.cute();
				break;
			case "ctrl+v":
			case "command+v":
				ui.path.past();
				break;
			case "f3":
			case "ctrl+f":
			case "command+f":
				core.search($(".header-right input").val(), G.this_path);
				break;
			case "alt+enter":
			case "ctrl+i":
			case "alt+i":
				ui.path.info();
				break;
			case "alt+n":
				ui.path.newFile();
				break;
			case "alt+m":
				ui.path.newFolder();
				break;
			case "ctrl+s":
			case "command+s":
				ShareData.frameTop("OpenopenEditor",
				function(e) {
					e.Editor.save()
				});
				break;
			default:
			}
		})
	},
	imageRotate = function(e) {
		var i = $("#PV_Items li.current img").attr("src"),
		t = "image&path=",
		a = i.substr(i.search(t) + t.length),
		n = $('[src="' + i + '"],[data-original="' + i + '"]'),
		o = "./explorer/imageRotate&rotate=" + e + "&path=" + a;
		$.ajax({
			url: o,
			dataType: "json",
			beforeSend: function() {
				Tips.loading("操作中...")
			},
			error: core.ajaxError,
			success: function(e) {
				if (!e) return Tips.close(LNG.php_env_error_gd, false),
				undefined;
				if (Tips.close(e), e.code) {
					var t = function(e) {
						var i = "&picture=",
						t = e.search(i);
						return - 1 === t ? e + i + UUID() : e.substr(0, t) + i + UUID()
					},
					a = t(i),
					o = t($("#PV_Picture").attr("src"));
					n.attr("src", a),
					n.attr("data-original", a),
					ui.picasa.resetImage(o)
				}
			}
		})
	},
	resetDesktopIcon = function() {
		if ("desktop" == Config.pageApp) {
			var e = 20,
			i = 20,
			t = parseInt($(".file").css("height")),
			a = t - 30,
			n = 10,
			o = 15,
			s = $(document).height() - 50,
			r = Math.floor((s - e) / (t + n)),
			l = 0,
			c = 0,
			d = 0,
			p = 0,
			u = (s - e - r * (t + n) - n) / r;
			u > 0 && (n += u),
			$(".fileContiner .file").css("position", "absolute"),
			$(".fileContiner .file").each(function(s) {
				l = s % r,
				c = Math.floor(s / r),
				d = i + (a + o) * c,
				p = e + (t + n) * l,
				$(this).css({
					left: d,
					top: p
				})
			})
		}
	};
	return {
		f5: f5,
		f5Callback: f5Callback,
		fileContent: fileContent,
		picasa: fileContent.myPicasa,
		setListSort: setListSort,
		setListType: setListType,
		getRowfileNumber: getRowfileNumber,
		getPagefileNumber: getPagefileNumber,
		getColfileNumberDesktop: getColfileNumberDesktop,
		resetDesktopIcon: resetDesktopIcon,
		imageRotate: imageRotate,
		setTheme: function(theme) {
			G.user_config.theme = theme;
			core.setSkin(theme);
			ShareData.frameTop("OpenopenEditor",function(i) {
				i.Editor.setTheme(theme)
			});
			ShareData.frameTop("Opensetting_mode",function(i) {
				i.Setting.setThemeSelf(theme)
			});
			ShareData.frameTop("",function(i) {
				i.ui.setTheme(theme)
			});
			$(".dropdown-menu-theme .list").removeClass("this");
			$('.dropdown-menu-theme .list[theme="' + theme + '"]').addClass("this");
		},
		setWall: function(path, callback) {
			$(".background").attr("src", path).one("load",function() {
				$(".desktop").css("background-image", "url(" + path + ")");
				if("function" == typeof callback){
					callback();
				}
			})
		},
		setFileIconSize: function(e) {
			ui.fileListResize.setFileIconSize(e, true);
			if("desktop" == Config.pageApp){
				ui.f5();
			}
		},
		isEdit: function() {
			var e = $(document.activeElement).get(0);
			if (e){
				e = e.tagName
				if("INPUT" == e || "TEXTAREA" == e){
					return true;
				}else{
					if($(".file.file_icon_edit").length > 0){
						return true;
					}else{
						return false;
					}
				}
			}
		},
		init: function() {
			if (G.sid) {
				if(LocalData.get("theme")){
					G.user_config.theme = LocalData.get("theme");
				}
				if(LocalData.get("list_type")){
					G.user_config.list_type = LocalData.get("list_type");
				}
				if(LocalData.get("list_sort_field")){
					G.user_config.list_sort_field = LocalData.get("list_sort_field");
				}
				if(LocalData.get("list_sort_order")){
					G.user_config.list_sort_order = LocalData.get("list_sort_order");
				}
				LocalData.set("theme", G.user_config.theme);
				LocalData.set("list_type", G.user_config.list_type);
				LocalData.set("list_sort_field", G.user_config.list_sort_field);
				LocalData.set("list_sort_order", G.user_config.list_sort_order);
				var url_path = window.location.href.split("#");
				if(2 == url_path.length){
					G.this_path = urlDecode(url_path[1]);
				}
			}
			if (ui.setTheme(G.user_config.theme), "" == G.this_path) {
				var pre = G.user_id || G.sid,
				localPath = LocalData.get("this_path_" + pre);
				G.this_path = localPath ? localPath: G.myhome
			}
			fileContent.init();
			resetDesktopIcon();
			ui.path.history.add(G.this_path);
			f5Callback(function() {
				resetDesktopIcon()
			});
			bindEventView();
			bindEventSort();
			bindEventTheme();
			bindEventTools();
			bindHotKey();
			bindEventMenu();
		}
	}
});
