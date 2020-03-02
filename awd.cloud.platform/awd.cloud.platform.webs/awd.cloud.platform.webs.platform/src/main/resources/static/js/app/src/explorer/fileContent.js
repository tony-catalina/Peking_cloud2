define("app/src/explorer/fileContent", [
	"./tpl/file_list_make.html.js"
],
function(require, exports) {
	var myPicasa = new Picasa,
	tpl = require("./tpl/file_list_make.html"),
	pageLoadMax = 200,
	ajaxLive = function() {
		ui.fileLight.init(),
		"desktop" == Config.pageApp && ui.resetDesktopIcon(),
		"list_split" == G.user_config.list_type && ui.fileListResize.bindSplitResize(),
		lazyLoadImage()
	},
	lazyLoadImage = function() {
		var e = $(".bodymain");
		"list_split" == G.user_config.list_type && (e = $(".split_box").last().find(".content")),
		e.find(".lazyload_ready").lazyload({
			failure_limit: 10,
			threshold: 200,
			placeholder: G.static_path + "images/common/loading_circle.gif",
			skip_invisible: false,
			effect: "fadeIn",
			container: e,
			load: function() {
				$(this).removeClass("lazyload_ready")
			}
		}).on("error",
		function() {
			var e = $(this).data("error_reload");
			e || ($(this).attr("src", $(this).attr("src") + "#" + UUID()), $(this).data("error_reload", "reload"))
		})
	},
	sortBy = function(e, i) {
		var i = "down" == i ? -1 : 1;
		return function(t, a) {
			var t = t[e],
			a = a[e];
			return ui.path.pathOperate.strSort(t, a) * i
		}
	},
	mainSetData = function(e) {
		var i = makeHtml(G.json_data, 0, getPageNumber() - 1);
		if ("desktop" == Config.pageApp) {
			var t = "";
			$(".systemBox").each(function() {
				t += $(this).get(0).outerHTML
			}),
			i = t + i
		}
		i = htmlListAction(G.json_data, i, false),
		"list_split" == G.user_config.list_type && (i = '<div class="split_box" data-path="' + pathHashEncode(G.this_path) + '"><div class="content">' + i + '<div class="content_more"></div> </div><div class="split_drag"></div></div>'),
		e ? $(Config.FileBoxSelector).hide().html(i).fadeIn(Config.AnimateTime) : $(Config.FileBoxSelector).html(i),
		"list_split" == G.user_config.list_type && $(".split_box").data("jsonData", G.json_data),
		ajaxLive()
	},
	scrollDelayTimer = "",
	bindScrollLoadMore = function() {
		var e = $(".bodymain");
		e.scroll(function() {
			clearTimeout(scrollDelayTimer),
			scrollDelayTimer = false,
			scrollDelayTimer = setTimeout(function() {
				0 != e.scrollTop() && loadMore()
			},
			100)
		}),
		$(".splitLoadMore").live("dblclick",
		function() {
			$("#set_list").click()
		})
	},
	getPageNumber = function() {
		var e = ui.fileLight.fileListAll().last(),
		i = $(".bodymain .fileContinerMore");
		if (0 == e.length) return pageLoadMax;
		var t = G.json_data.folderlist.length + G.json_data.filelist.length;
		if (i.css("top", 0), pageLoadMax > t || "list_split" == G.user_config.list_type) return pageLoadMax;
		var e = ui.fileLight.fileListAll().last(),
		a = e.outerWidth() + $sizeInt(e.css("margin-right")) + 3.5,
		n = parseInt($(".fileContiner").width() / a);
		"icon" != G.user_config.list_type && (n = 1);
		var o = e.outerHeight() + $sizeInt(e.css("margin-bottom")),
		s = Math.ceil($(Config.BodyContent).height() / o),
		r = Math.ceil(t / n) * o;
		return i.css("top", r),
		s * n
	},
	resetTotalHeight = function() {
		var e = ".bodymain .fileContiner > .file",
		i = $(e).last(),
		t = $(".bodymain .fileContinerMore");
		if (0 != i.length) {
			var a = G.json_data.folderlist.length + G.json_data.filelist.length;
			if (t.css("top", 0), !(pageLoadMax > a || "list_split" == G.user_config.list_type)) {
				var n = i.outerWidth() + $sizeInt(i.css("margin-right")),
				o = parseInt($(".fileContiner").width() / n);
				"icon" != G.user_config.list_type && (o = 1);
				var s = i.outerHeight() + $sizeInt(i.css("margin-bottom"));
				Math.ceil($(Config.BodyContent).height() / s);
				var r = Math.ceil(a / o) * s;
				t.css("top", r)
			}
		}
	},
	loadMoreDelayTimer,
	loadMore = function() {
		var e = $(".bodymain .fileContiner > .file"),
		i = e.last(),
		t = e.length - 1,
		a = G.json_data.folderlist.length + G.json_data.filelist.length;
		if (! (t >= a - 1 || "list_split" == G.user_config.list_type)) {
			var n = $(".bodymain").scrollTop(),
			o = $(".bodymain").height(),
			s = $(".bodymain").offset().top;
			$(".fileContiner").offset().top;
			var r = i.outerHeight() + $sizeInt(i.css("margin-bottom")),
			l = s + o - r;
			if (l > i.offset().top) {
				var c = l - i.offset().top,
				d = getPageNumber(),
				p = Math.ceil(c / o),
				u = p * d + t;
				u > a && (u = a),
				u - t > 1e3 ? ($(".init_loading").show(), clearTimeout(loadMoreDelayTimer), loadMoreDelayTimer = setTimeout(function() {
					loadMoreSet(t + 1, u),
					$(".bodymain").scrollTop(n)
				},
				300)) : loadMoreSet(t + 1, u)
			}
		}
	},
	loadMoreSet = function(e, i) {
		var t = makeHtml(G.json_data, e, i),
		a = $(t);
		a.appendTo(".fileContiner"),
		ui.fileLight.fileListAll($(Config.FileBoxClass)),
		ui.fileLight.menuAction("clear"),
		lazyLoadImage(),
		$(".init_loading").hide()
	},
	makeHtml = function(e, i, t) {
		var a = template.compile(tpl),
		n = "",
		o = [];
		o = "up" == G.user_config.list_sort_order ? e.folderlist.concat(e.filelist) : e.filelist.concat(e.folderlist),
		(!t || t >= o.length - 1) && (t = o.length - 1);
		for (var s = i; t >= s; s++) {
			var r = "folder" == o[s].type ? "_folder": "_file",
			l = {
				LNG: LNG,
				G: G,
				list: o[s],
				index: s,
				type: G.user_config.list_type + r
			};
			o[s].icon && "icon_file" == l.type && "oexe" == o[s].ext && (l.oexe_icon = o[s].icon, "string" == $.type(o[s].icon) && -1 == o[s].icon.search(G.static_path) && "http" != o[s].icon.substring(0, 4) && (l.oexe_icon = G.static_path + "images/file_icon/icon_app/" + o[s].icon)),
			n += a(l)
		}
		return n
	},
	pathChildrenTree = function(e, i) {
		if ("string" == $.type(e)) var t = $('.file[data-path="' + pathHashEncode(e) + '"]');
		else {
			var t = e;
			e = ui.fileLight.path(t)
		}
		if (1 == t.length) {
			var a = t.find(".children_more"),
			n = t.find(".children_more_cert"),
			o = $('.children_list[data-path-children="' + pathHashEncode(e) + '"]'),
			s = 23;
			if (n.toggleClass("cert_open"), o.toggleClass("hidden"), o.hasClass("child_aredy_init")) return pathListOdd(),
			undefined;
			t.addClass("loading_children"),
			pathGet(e,	function(e) {
				t.removeClass("loading_children");
				var n = makeHtml(e, 0, getPageNumber() - 1);
				"" != n && (n = htmlListAction(e, n, true)),
				o.html(n),
				ajaxLive(),
				o.addClass("child_aredy_init");
				var r = s + parseInt(a.css("padding-left"));
				o.find(".file .children_more").css("padding-left", r),
				pathListOdd(),
				"function" == typeof i && i(e)
			})
		}
	},
	htmlListAction = function(e, i, t) {
		if ("" == i) return i = '<div style="text-align:center;color:#aaa;">' + "文件夹为空，可拖拽文件上传" + "</div>";
		var a = e.folderlist.concat(e.filelist);
		if (a.length > pageLoadMax) {
			var n = core.pathFather(a[0].path);
			"list" == G.user_config.list_type && t ? i += '<div data-path-children="' + pathHashEncode(n) + '" class="file folderBox" data-size="0">' + '<div class="filename" style="width: 424px;">' + '<span class="children_more"></span>' + '<div class="ico" filetype="folder"><i class="icon-plus-sign"></i></div>' + '<span class="title">' + "双击加载全部……" + "</span>" + "</div>" + "</div>": "list_split" == G.user_config.list_type && (i += '<div data-path-children="' + pathHashEncode(n) + '" class="file folderBox splitLoadMore" data-size="0">' + '<div class="filename">' + '<div class="ico" filetype="folder"><i class="icon-plus-sign"></i></div>' + '<span class="title">' + "双击加载全部……" + "(to list)</span>" + "</div>" + "</div>")
		}
		return i
	},
	pathListOdd = function() {
		var e = 0;
		ui.fileLight.fileListAll().each(function() {
			0 == $(this).parents(".hidden").length && (0 == e % 2 ? $(this).addClass("file2") : $(this).removeClass("file2"), e++)
		})
	},
	pathChildrenSplit = function(e, i) {
		var t = $('.file[data-path="' + pathHashEncode(e) + '"]'),
		a = $(".fileList_list_split .split_box[data-path='" + pathHashEncode(e) + "']");
		if (0 == t.length) return "function" == typeof i && i(),
		undefined;
		if (1 == a.length) return a.nextAll().remove(),
		"function" == typeof i && i(),
		undefined;
		var n = t.parent().parent();
		pathSplitCreate(e, i, n)
	},
	pathSplitCreate = function(e, i, t) {
		pathGet(e,function(a) {
			if ("not_exists" == a.path_read_write) return i(a);
			var n = makeHtml(a, 0, getPageNumber() - 1);
			if (n = htmlListAction(a, n, true), t) if (t.nextAll(".split_box").length > 0) {
				var o = t.next(".split_box");
				o.attr("data-path", pathHashEncode(e)).find(".content").html(n),
				o.nextAll().remove()
			} else n = '<div class="split_box" data-path="' + pathHashEncode(e) + '"><div class="content">' + n + '<div class="content_more"></div></div><div class="split_drag"></div></div>',
			$(n).insertAfter(t).data("jsonData", a);
			else n = '<div class="split_box" data-path="' + pathHashEncode(e) + '"><div class="content">' + n + '<div class="content_more"></div></div><div class="split_drag"></div></div>',
			$(n).appendTo(".fileList_list_split").data("jsonData", a);
			ajaxLive(),
			"function" == typeof i && i()
		})
	},
	beforeSelectFileArr = {},
	beforeListOpenArr = {},
	beforeListOpen = {},
	beforeListSplitSelect = "",
	beforeScrollerLeft = 0,
	f5Before = function() {
		if (! ("icon" == G.user_config.list_type || beforeListOpenArr.length > 0)) if (beforeListOpenArr = {},
		beforeListOpen = {},
		"list" == G.user_config.list_type) {
			var e = $(".child_aredy_init:visible");
			if (1 > e.length) return;
			e.each(function() {
				var e = $(this),
				i = beforeListOpenArr,
				t = ui.fileLight.path(e, "data-path-children");
				beforeListOpen[t] = false;
				for (var a = [t]; 0 != e.parents(".children_list").length;) e = e.parents(".children_list"),
				a.push(ui.fileLight.path(e, "data-path-children"));
				for (var n = a.length - 1; n >= 0; n--) {
					var o = a[n];
					i[o] !== undefined ? i = i[o] : i[o] = {}
				}
			})
		} else if ("list_split" == G.user_config.list_type) {
			var i = beforeListOpenArr;
			beforeScrollerLeft = $(".html5_drag_upload_box").scrollLeft(),
			beforeListSplitSelect = ui.fileLight.path($(".fileList_list_split .split_box.split_select")),
			$(".fileContiner .split_box").each(function() {
				var e = ui.fileLight.path($(this));
				"" != e && (i[e] = {},
				i = i[e], beforeListOpen[e] = false)
			})
		}
	},
	f5After = function(e) {
		return "icon" == G.user_config.list_type || 0 == Object.keys(beforeListOpenArr).length ? (f5AfterReloadFinished(e), undefined) : ("list_split" == G.user_config.list_type && $(".fileList_list_split .split_box").remove(), f5AfterReload(beforeListOpenArr, e), undefined)
	},
	f5AfterReload = function(e, i) {
		$.each(e,
		function(e, t) {
			var a = pathChildrenTree;
			"list_split" == G.user_config.list_type && (a = pathSplitCreate),
			a(e,
			function() {
				beforeListOpen[e] = true,
				0 != Object.keys(t).length ? f5AfterReload(t, i) : f5AfterReloadFinished(i)
			})
		}),
		f5AfterReloadFinished(i)
	},
	f5AfterReloadFinished = function(e) {
		for (var i in beforeListOpen) if (beforeListOpen[i] === false) return;
		$(".html5_drag_upload_box").scrollLeft(beforeScrollerLeft),
		ui.path.setSelectByFilename(beforeSelectFileArr),
		ui.fileSelect.selectSplit(beforeListSplitSelect),
		beforeListOpenArr = {},
		beforeListOpen = {},
		beforeSelectFileArr = {},
		beforeListSplitSelect = "",
		"function" == typeof e && e()
	},
	f5 = function(e, i, t) {
		if (undefined == e && (e = true), undefined == i && (i = false), jsonDataSortTitle(), f5Before(), beforeSelectFileArr = ui.fileLight.getAllName(), e ? pathGet(G.this_path,
		function(e) {
			G.json_data = e,
			mainSetData(i),
			pathTypeChange(G.json_data),
			loadMore(),
			resetTotalHeight(),
			f5After(t),
			"desktop" != Config.pageApp ? ui.headerAddress.addressSet() : checkRecycle()
		},
		function() {
			$(Config.FileBoxSelector).html("")
		}) : (G.json_data = jsonDataSort(G.json_data), mainSetData(i), pathTypeChange(G.json_data), loadMore(), resetTotalHeight(), f5After(t)), "desktop" != Config.pageApp) {
			var a = G.user_id || G.sid;
			LocalData.set("this_path_" + a, G.this_path)
		}
	},
	jsonDataSort = function(e) {
		e = jsonDatafilter(e);
		var i = e.folderlist,
		t = e.filelist;
		return i = "size" == G.user_config.list_sort_field || "ext" == G.user_config.list_sort_field ? i.sort(sortBy("name", G.user_config.list_sort_order)) : i.sort(sortBy(G.user_config.list_sort_field, G.user_config.list_sort_order)),
		t = t.sort(sortBy(G.user_config.list_sort_field, G.user_config.list_sort_order)),
		e.folderlist = i,
		e.filelist = t,
		e
	},
	pathGet = function(url, i, t) {
		var ajaxurl = "explorer/pathList?path=" + urlEncode(url);
		$.ajax({
			url: ajaxurl,
			dataType: "json",
			beforeSend: function() {
				$(".tools-left .msg").stop(true, true).fadeIn(200)
			},
			success: function(result) {
				if ($(".tools-left .msg").fadeOut(300), !result || !result.code) return Tips.tips(result),
				"function" == typeof t && t(),
				false;
				var a = jsonDataSort(result.data);
				"function" == typeof i && i(a)
			},
			error: function(e, i, a) {
				$(".tools-left .msg").fadeOut(300),
				core.ajaxError(e, i, a),
				"function" == typeof t && t()
			}
		})
	},
	f5Callback = function(e) {
		f5(true, false, e)
	},
	jsonDatafilter = function(e) {
		if (!e) return json_data;
		if (undefined != e.share_list && (self_share = e.share_list), e.filter_success === true) return e;
		for (var i in e) if ("filelist" == i || "folderlist" == i) for (var t = 0; e[i].length > t; t++) {
			var a = e[i][t];
			if (a.mtime && 11 >= ("" + a.mtime).length) if (a.atime = date("Y\/m\/d H:i:s", a.atime), a.ctime = date("Y\/m\/d H:i:s", a.ctime), e.info && e.info.path_type == G.AWD_USER_SHARE && -1 == trim(e.this_path, "/").indexOf("/")) {
				var n = parseInt(a.num_view);
				n = isNaN(n) ? 0 : n;
				var o = parseInt(a.num_download);
				o = isNaN(o) ? 0 : o;
				var s = date("Y/m/d ", a.mtime) + "  ";
				s += "浏览:" + n + "  " + "下载:" + o,
				a.mtime = s
			} else a.mtime = date("Y\/m\/d H:i:s", a.mtime);
			a.name = htmlEncode(a.name),
			a.sid && "file" == a.type && (a.ext = htmlEncode(core.pathExt(a.path))),
			pathIsShare(a.path) ? a.meta_info = "path_self_share": pathIsFav(a.path) && (a.meta_info = "treeFav"),
			"number" == typeof a.is_readable && 0 == a.is_readable ? a.mode = "[" + "不可读"+ "] " + a.mode: "number" == typeof a.is_writeable && 1 == a.is_writeable ? a.mode = "[" + "可读写" + "] " + a.mode: "number" == typeof a.is_readable && 1 == a.is_readable && (a.mode = "[" + "只读" + "] " + a.mode),
			e.info && e.info.path_type == G.AWD_USER_RECYCLE && trim(e.this_path, "/") == G.AWD_USER_RECYCLE && (a.menuType = "menuRecyclePath")
		}
		return e.filter_success = true,
		e
	},
	jsonDataSortTitle = function() {
		var up = '<i class="font-icon icon-chevron-up"></i>',
		down = '<i class="font-icon icon-chevron-down"></i>';
		$("#main_title .this").toggleClass("this").attr("id", "").find("span").html(""),
		$("#main_title div[field=" + G.user_config.list_sort_field + "]").addClass("this").attr("id", G.user_config.list_sort_order).find("span").html(eval(G.user_config.list_sort_order))
	},
	pathIsShare = function(e) {
		for (var i in G.self_share) if (core.pathClear(G.self_share[i].path) == core.pathClear(e)) return ! 0;
		return ! 1
	},
	pathIsFav = function(e) {
		var i = G.fav_list;
		for (var t in i) if (core.pathClear(t) == core.pathClear(e)) return ! 0;
		return ! 1
	},
	checkRecycle = function() {
		$.ajax({
			url: "explorer/pathList?type=desktop&path=" + G.AWD_USER_RECYCLE,
			dataType: "json",
			error: core.ajaxError,
			success: function(e) {
				if (!e.code) return ! 1;
				var i = core.icon("recycle_full");
				0 == e.data.folderlist.length && 0 == e.data.filelist.length && (i = core.icon("recycle")),
				$(".menuRecycleButton .ico").html(i)
			}
		})
	},
	pathTypeChange = function(e) {
		if (e.info) {
			var i = e.info,
			t = i.path_type,
			a = e.path_read_write,
			n = "menuBodyMain menuRecycleBody menuShareBody",
			o = $(".html5_drag_upload_box");
			i.can_upload = true,
			(undefined != a && "writeable" != a || t == G.AWD_GROUP_SHARE && "owner" != i.role && 1 != G.is_root || t == G.AWD_USER_SHARE && "owner" != i.role && 1 != G.is_root || t == G.AWD_GROUP_PATH && "guest" == i.role && 1 != G.is_root || t == G.AWD_USER_FAV || t == G.AWD_USER_RECYCLE || t == G.AWD_GROUP_ROOT_ALL || t == G.AWD_GROUP_ROOT_SELF) && (i.can_upload = false);
			var s = [G.AWD_USER_SHARE, G.AWD_USER_FAV, G.AWD_GROUP_ROOT_SELF, G.AWD_GROUP_ROOT_ALL];
			t == G.AWD_USER_RECYCLE ? (o.removeClass(n).addClass("menuRecycleBody"), 
					$(".tools-left>.btn-group").addClass("hidden").parent().find(".awd_recycle_tool").removeClass("hidden")) : -1 !== s.indexOf(t) ? -1 === core.pathClear(rtrim(G.this_path, "/")).indexOf("/") ? (o.removeClass(n).addClass("menuShareBody"), 
							$(".tools-left>.btn-group").addClass("hidden").parent().find(".awd_share_tool").removeClass("hidden"),
							i.id == G.user_id ? ($(".menuSharePathMenu").find(".open_the_path,.share_edit,.remove").removeClass("hidden"), 
							$(".menuSharePathMore").find(".remove").removeClass("hidden")) : ($(".menuSharePathMenu").find(".open_the_path,.share_edit,.remove").addClass("hidden"), 
							$(".menuSharePathMore").find(".remove").addClass("hidden"))) : (o.removeClass(n).addClass("menuBodyMain"), 
							$(".tools-left>.btn-group").addClass("hidden").parent().find(".awd_path_tool").removeClass("hidden")) : (o.removeClass(n).addClass("menuBodyMain"),
							$(".tools-left>.btn-group").addClass("hidden").parent().find(".awd_path_tool").removeClass("hidden")),
			currentPathMenu(e)
		}
	},
	currentPathMenu = function(e) {
		var i = e.info,
		t = e.path_read_write,
		a = i.path_type,
		n = ".createLink,.createProject,.cute,.remove,.rname,.zip,.unzip_this,.unzip_folder,.newfile,.newfolder,.newfileOther,.app_create,.app_install,.past,.upload,.clone",
		o = "disable";
		if (i.can_upload ? (
				$("ul.menufolder,ul.menuMore,ul.menufile,ul.fileContiner_menu").find(n).removeClass(o), 
				$(".path_tips").hide(), 
				$(".awd_path_tool>button").removeClass("disabled")) : ($(".awd_path_tool>button").addClass("disabled"),
						$("ul.menufolder,ul.menuMore,ul.menufile,ul.fileContiner_menu").find(n).addClass(o), 
						$(".path_tips span").html("只读"), 
						a == G.AWD_USER_RECYCLE || a == G.AWD_USER_SHARE ? ($(".path_tips").hide(), 
						$(".awd_path_tool>button").removeClass("disabled"), 
						a == G.AWD_USER_SHARE && G.user_id != i.id && $(".awd_path_tool>button").addClass("disabled")) : $(".path_tips").show()), 
						(a == G.AWD_GROUP_PATH || a == G.AWD_GROUP_SHARE) && G.is_root || a == G.AWD_GROUP_PATH && "owner" == i.role) {
			var s = e.group_space_use;
			if (s) {
				var r = core.userSpaceHtml(s.size_use + "/" + s.size_max);
				$(".group_space_use").removeClass("hidden").html(r)
			} else $(".group_space_use").addClass("hidden")
		} else $(".group_space_use").addClass("hidden");
		if (e.user_space) {
			var s = e.user_space,
			r = core.userSpaceHtml(s.size_use + "/" + s.size_max);
			$(".user_space_info").html(r)
		}
		if ("not_exists" == t && ($(".path_tips span").html("不存在"), $(".path_tips").show()), $(".role_label_box").html(""), a == G.AWD_GROUP_SHARE) {
			var l = "<span class='label label-grey-light' title-timeout='0' title='" + "您不是该群组成员，<br\/>仅能访问[群组 共享目录]下面的内容,只读权限." + "'>" + "访客" + "<span>";
			$(".role_label_box").html(l),
			G.is_root && $(".role_label_box").html("")
		} else if (a == G.AWD_GROUP_PATH && i.group_role) {
			var l = "<span class='label label-" + i.group_role.style + "' title-timeout='0' title='" + "您是该群组成员，<br\ />群内文档所有操作权限君由管理员分配" + "'>" + i.group_role.name + "<span>";
			$(".role_label_box").html(l)
		} (a == G.AWD_GROUP_ROOT_ALL || a == G.AWD_GROUP_ROOT_SELF || a == G.AWD_USER_FAV || a == G.AWD_GROUP_SHARE) && $(".path_tips").hide(),
		1 == G.is_root && i.admin_real_path ? $(".admin_real_path").removeClass("hidden") : $(".admin_real_path").addClass("hidden")
	};
	return {
		f5: f5,
		f5Callback: f5Callback,
		pathTypeChange: pathTypeChange,
		pathChildrenTree: pathChildrenTree,
		pathChildrenSplit: pathChildrenSplit,
		myPicasa: myPicasa,
		init: function() {
			$(window).bind("resize",
			function() {
				resetTotalHeight(),
				"desktop" == Config.pageApp ? ui.resetDesktopIcon() : ui.headerAddress.resetWidth(),
				"none" != $("#PicasaView").css("display") && myPicasa.setFrameResize()
			}),
			bindScrollLoadMore(),
			myPicasa.init(".picasaImage"),
			myPicasa.initData()
		}
	}
});
