define("app/src/explorer_wap/ui", [],
function(e) {
	var t = e("./tpl/file_list_make_wap.html"),
	i = function() {
		$(".fileContiner .picture img").lazyload({
			placeholder: G.static_path + "images/common/loading_tree.gif",
			container: $(".bodymain")
		})
	},
	a = function(e, t) {
		var t = "down" == t ? -1 : 1;
		return function(i, a) {
			return i = i[e],
			a = a[e],
			a > i ? -1 * t: i > a ? 1 * t: undefined
		}
	},
	n = e("./tpl/photoSwipe.html");
	$(n).appendTo("body"),
	e.async(["lib/PhotoSwipe/photoswipe.min.js", "lib/PhotoSwipe/photoswipe-ui-default.min.js", "lib/PhotoSwipe/photoswipe.css", "lib/PhotoSwipe/default-skin/default-skin.css"],
	function() {});
	var openImage = function(e) {
		var t = [],
		i = 0;
		$(".picasaImage img").each(function(a) {
			var n = pathHashDecode($(this).parent().parent().attr("data-path")),
			o = $(this).parent().attr("picasa"),
			s = $(this).attr("data-original"),
			r = 30;
			t.push({
				src: o,
				msrc: s,
				w: $(this).width() * r,
				h: $(this).height() * r
			}),
			e == n && (i = a)
		});
		var a = {
			history: false,
			focus: true,
			index: i,
			bgOpacity: .8,
			maxSpreadZoom: 5,
			closeOnScroll: false,
			shareEl: false,
			showHideOpacity: true,
			showAnimationDuration: 300,
			hideAnimationDuration: 0,
			getThumbBoundsFn: function(e) {
				var t = $(".picasaImage img")[e],
				i = window.pageYOffset || document.documentElement.scrollTop,
				a = t.getBoundingClientRect();
				return {
					x: a.left,
					y: a.top + i,
					w: a.width
				}
			}
		},
		n = new PhotoSwipe($(".pswp").get(0), PhotoSwipeUI_Default, t, a);
		n.init(),
		$(".pswp__caption__center").css({
			"text-align": "center"
		})
	},
	s = function(e) {
		for (var a = template.compile(t), n = "", o = G.json_data.folderlist.concat(G.json_data.filelist), s = 0; o.length > s; s++) {
			var r = "folder" == o[s].type ? "list_folder": "list_file",
			l = {
				LNG: LNG,
				G: G,
				list: o[s],
				index: s,
				type: r
			};
			o[s].icon && "icon_file" == l.type && "oexe" == o[s].ext && (l.oexe_icon = o[s].icon, "string" == $.type(o[s].icon) && -1 == o[s].icon.search(G.static_path) && "http" != o[s].icon.substring(0, 4) && (l.oexe_icon = G.static_path + "images/file_icon/icon_app/" + o[s].icon)),
			n += a(l)
		}
		"" == n && (n = '<div style="text-align:center;color:#aaa;">' + "文件夹为空，可拖拽文件上传" + "</div>"),
		n += "<div style='clear:both'></div>",
		e ? $(Config.FileBoxSelector).hide().html(n).fadeIn(Config.AnimateTime) : $(Config.FileBoxSelector).html(n);
		var c = $(Config.FileBoxClass).not(".systemBox");
		$('<i class="file-action icon-font icon-ellipsis-horizontal"></i>').appendTo(c),
		$(Config.FileBoxSelector + " .file:nth-child(2n)").addClass("file2"),
		i()
	},
	f5 = function(e, t) {
		var i = "explorer/pathList?path=" + urlEncode(G.this_path);
		G.user && (i = "share/pathList?user=" + G.user + "&sid=" + G.sid + "&path=" + urlEncode(G.this_path)),
		undefined == e && (e = true),
		undefined == t && (t = false),
		e ? $.ajax({
			url: i,
			dataType: "json",
			beforeSend: function() {
				$(".tools-left .msg").stop(true, true).fadeIn(100)
			},
			success: function(e) {
				return $(".tools-left .msg").fadeOut(100),
				e.code ? (G.json_data = c(e.data), s(t), u(), ui.header.addressSet(), undefined) : (Tips.tips(e), $(Config.FileBoxSelector).html(""), false)
			},
			error: function(e, t, i) {
				$(".tools-left .msg").fadeOut(100),
				$(Config.FileBoxSelector).html(""),
				core.ajaxError(e, t, i)
			}
		}) : (s(t), u())
	},
	f5Callback = function(e) {
		r(true, false, e)
	},
	c = function(e) {
		e = d(e);
		var t = e.folderlist,
		i = e.filelist;
		return t = t.sort(a("name", "up")),
		i = i.sort(a("name", "up")),
		e.folderlist = t,
		e.filelist = i,
		e
	},
	d = function(e) {
		if (!e) return e;
		if (undefined != e.share_list && (self_share = e.share_list), e.filter_success === true) return e;
		for (var t in e) if ("filelist" == t || "folderlist" == t) for (var i = 0; e[t].length > i; i++) {
			var a = e[t][i];
			if (a.name = htmlEncode(a.name), a.mtime && 11 >= ("" + a.mtime).length) if (a.atime = date("Y\/m\/d H:i:s", a.atime), a.ctime = date("Y\/m\/d H:i:s", a.ctime), e.info && e.info.path_type == G.AWD_USER_SHARE && -1 == trim(e.this_path, "/").indexOf("/")) {
				var n = parseInt(a.num_view);
				n = isNaN(n) ? 0 : n;
				var o = parseInt(a.num_download);
				o = isNaN(o) ? 0 : o;
				var s = date("Y/m/d ", a.mtime) + "  ";
				s += "浏览:" + n + "  " + "下载:" + o,
				a.mtime = s
			} else a.mtime = date("Y\/m\/d H:i:s", a.mtime);
			"number" == typeof a.is_readable && 0 == a.is_readable ? a.mode = "[" + "不可读"+ "] " + a.mode: "number" == typeof a.is_writeable && 1 == a.is_writeable ? a.mode = "[" + "可读写" + "] " + a.mode: "number" == typeof a.is_readable && 1 == a.is_readable && (a.mode = "[" + "只读" + "] " + a.mode)
		}
		return e.filter_success = true,
		e
	},
	p = function(e, t) {
		var i = pathHashDecode(e.attr("data-path")),
		a = e.find(".ico").attr("filetype");
		switch (t) {
		case "action_copy":
			ui.path.copy(i, a);
			break;
		case "action_rname":
			ui.path.rname(i);
			break;
		case "action_download":
			ui.path.download(i, a);
			break;
		case "action_remove":
			ui.path.remove(i, a);
			break;
		default:
		}
	},
	f = function() {
		$(window).bind("hashchange",
		function() {
			var e = window.location.href,
			t = e.split("#");
			"" != t[1] && t[1] != G.this_path && t[1] != urlEncode(G.this_path) && ui.path.list(urlDecode(t[1]))
		}),
		$(".fileContiner .file").die("click").live("click",
		function(e) {
			if ($(".fileContiner .file .file_action_menu").animate({
				left: "100%"
			},
			300, 0,
			function() {
				$(this).remove()
			}), $(this).find(".file_action_menu").length > 0) {
				if ($(e.target).hasClass("action_menu")) {
					var t = $(e.target).attr("data-action");
					p($(this), t)
				}
				if ($(e.target).parent().hasClass("action_menu")) {
					var t = $(e.target).parent().attr("data-action");
					p($(this), t)
				}
			} else {
				if ($(e.target).hasClass("file-action")) {
					var i = $(".file_menu .file_action_menu").clone(),
					a = true;
					return $(e.target).parent().hasClass("file_not_writeable") ? a = false : G.json_data.info && G.json_data.info.can_upload || (a = false),
					a || (i.find("[data-action=action_remove]").remove(), i.find("[data-action=action_rname]").remove()),
					i.appendTo($(this)),
					i.removeClass("hidden").css({
						left: "100%"
					}).animate({
						left: "0%"
					},
					300, 0,
					function() {}),
					undefined
				}
				var n = $(this).find(".ico").attr("filetype"),
				o = pathHashDecode($(this).attr("data-path"));
				ui.path.open(o, n),
				stopPP(e)
			}
		}),
		$(".address li").die("click").live("click",
		function(e) {
			var t = $(this).find("a").attr("data-path");
			ui.path.list(t),
			stopPP(e)
		})
	},
	u = function() {
		if (G.json_data.info) {
			var e = G.json_data.info,
			t = e.path_type,
			i = G.json_data.path_read_write;
			undefined != i && "writeable" != i || t == G.AWD_USER_RECYCLE || t == G.AWD_USER_SHARE || t == G.AWD_GROUP_SHARE ? G.json_data.info.can_upload = false : (G.json_data.info.can_upload = true, 1 != G.is_root && t == G.AWD_GROUP_PATH && "guest" == e.role && (G.json_data.info.can_upload = false)),
			G.json_data.info.can_upload ? $("[data-action=upload],[data-action=newfolder],[data-action=past]").removeClass("hidden") : $("[data-action=upload],[data-action=newfolder],[data-action=past]").addClass("hidden")
		}
	},
	h = function() {
		$.ajax({
			url: "./explorer/treeList?app=explorer&type=init",
			dataType: "json",
			success: function(e) {
				e.code || Tips.tips(e);
				for (var t = "",
				i = 0; e.data.length > i; i++) {
					var a = e.data[i];
					t += '<li data-action="pathOpen" data-path="' + a.path + '"><i class="x-item-file x-' + a.ext + '"></i><span>' + a.name + "</span></li>"
				}
				t += '<li data-action="exit"><i class="x-item-file x-setting"></i><span>' + "退出" + "</span></li>",
				$(".left_menu_path").html(t),
				$(".panel-menu li").bind("click",
				function() {
					$("body").removeClass("menu-open");
					var e = $(this).attr("data-action");
					switch (e) {
					case "pathOpen":
						ui.path.list($(this).attr("data-path"));
						break;
					case "exit":
						window.location.href = "./user/logout";
						break;
					default:
					}
				})
			}
		})
	};
	return {
		f5:	r,
		f5Callback: l,
		openImage: o,
		init: function() {
			if ("" == G.this_path) {
				var e = window.location.href.split("#");
				if (2 == e.length && "" != trim(urlDecode(e[1]))) G.this_path = urlDecode(e[1]);
				else {
					var t = G.user_id || G.sid,
					i = LocalData.get("this_path_" + t);
					G.this_path = i ? i: G.myhome
				}
			}
			l(function() {
				r(false, true)
			}),
			h(),
			f(),
			ui.header.bindEvent()
		},
		header: {
			bindEvent: function() {
				$(".left_tool").on("click",
				function() {
					$("body").toggleClass("menu-open")
				}),
				$(".panel-mask").on("click",
				function() {
					$("body").toggleClass("menu-open")
				}),
				$(".right_tool").on("click",
				function(e) {
					$(this).parent().toggleClass("open"),
					stopPP(e)
				}),
				$("body").on("click",
				function(e) {
					$(e).hasClass("right_tool") || $(".menu_group").removeClass("open")
				}),
				$(".menu-right_tool li").on("click",
				function(e) {
					$(".menu_group").removeClass("open");
					var t = $(this).attr("data-action");
					switch (t) {
					case "upload":
						core.upload();
						break;
					case "newfolder":
						ui.path.newFolder();
						break;
					case "newfile":
						ui.path.newFile("txt");
						break;
					case "search":
						core.search("", G.this_path);
						break;
					case "past":
						ui.path.past();
						break;
					default:
					}
					return stopPP(e),
					false
				}),
				G.share_info && G.share_info.can_upload && $("[data-action=upload]").removeClass("hidden")
			},
			addressSet: function() {
				var e = G.this_path,
				t = function(e) {
					var t = '<li class="yarnlet first"><a title="@1@" data-path="@1@" style="z-index:{$2};"><span class="left-yarn"></span>{$3}</a></li>\n',
					i = '<li class="yarnlet "><a title="@1@" data-path="@1@" style="z-index:{$2};">{$3}</a></li>\n';
					e = e.replace(/\/+/g, "/");
					var a = e.split("/");
					"" == a[a.length - 1] && a.pop();
					var n = a[0] + "/",
					o = t.replace(/@1@/g, n),
					s = a[0],
					r = "";
					if (G.json_data.info && G.json_data.info.path_type && "" != a[0]) {
						var l = core.getPathIcon(G.json_data.info, G.json_data.info.name);
						r = '<span class="address_ico">' + core.iconSmall(l.icon) + "</span>",
						s = l.name
					}
					o = o.replace("{$2}", a.length),
					o = o.replace("{$3}", r + '<span class="title_name">' + htmlEncode(s) + "</span>");
					for (var c = o,
					d = 1,
					p = a.length - 1; a.length > d; d++, p--) n += htmlEncode(a[d]) + "/",
					o = i.replace(/@1@/g, n),
					o = o.replace("{$2}", p),
					o = o.replace("{$3}", '<span class="title_name">' + htmlEncode(urlDecode(a[d])) + "</span>"),
					c += o;
					return c
				};
				$(".frame-main .address ul").html(t(e))
			},
			gotoPath: function() {
				var e = $("input.path").val();
				e = e.replace(/\\/g, "/"),
				$("input.path").val(e),
				"/" != e.substr(e.length - 1, 1) && (e += "/"),
				ui.path.list(e),
				ui.header.addressSet()
			}
		}
	}
});
