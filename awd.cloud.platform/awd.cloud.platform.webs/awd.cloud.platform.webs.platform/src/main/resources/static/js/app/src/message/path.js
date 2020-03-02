define("app/src/message/path", [
	"../../common/pathOperate",
	"../../common/pathOpen",
	"../../common/myPlayer"],
function(e) {
	var i = e("../../common/pathOperate"),
	t = e("../../common/pathOpen"),
	a = undefined;
	ui.pathOpen = t;
	var n = function() {
		if (G.json_data.info.path_type == G.AWD_USER_RECYCLE || G.json_data.info.path_type == G.AWD_USER_SHARE) return ! 0;
		var e = ui.fileLight.fileListSelect();
		return e.hasClass("systemBox") ? (Tips.tips("此目录不支持该操作!", "warning"), false) : true
	},
	o = function(e, i, t, a) {
		if (e) {
			if ("explorer" != Config.pageApp) return core.explorer(e),
			undefined;
			if (e == G.this_path) return undefined != i && "" != i && Tips.tips("所打开路径和当前路径一样！", "info"),
			undefined;
			if (G.this_path = e.replace(/\\/g, "/"), G.this_path = e.replace(/\/+/g, "/"), "/" != G.this_path.substr(G.this_path.length - 1) && (G.this_path += "/"), $(".dialog_file_upload").length > 0) {
				var n = "hidden" == $(".dialog_file_upload").css("visibility");
				core.upload(),
				n && $(".dialog_file_upload").css("visibility", "hidden")
			}
			if (G.sid !== undefined && (window.location.href = "#" + urlEncode(G.this_path)), core.playSound("folder_open"), ui.tree.setSelect(G.this_path), a || ui.path.history.add(G.this_path), "list_split" == G.user_config.list_type) {
				var o = $(".split_box .file[data-path=" + pathHashEncode(G.this_path) + "]");
				if (0 != o.length && 0 != o.find(".children_more_cert").length) return o.click(),
				undefined;
				$(".fileList_list_split .split_box").remove()
			}
			ui.f5Callback(function() {
				"function" == typeof t && t()
			})
		}
	},
	s = function() {
		var e = [],
		i = 60,
		t = 0,
		a = function(a) {
			var n = e.length - 1;
			return n == t && e[n] == a ? r() : (t != n && (e = e.slice(0, t + 1)), e[e.length - 1] != a && e.push(a), e.length >= i && (e = e.slice(1)), t = e.length - 1, r(), undefined)
		},
		n = function() {
			e.length - 1 >= t + 1 && (o(e[++t], "", "", true), r())
		},
		s = function() {
			t - 1 >= 0 && (o(e[--t], "", "", true), r())
		},
		r = function() {
			var i = "disable",
			a = e.length - 1;
			$("#history_next").addClass(i),
			$("#history_back").addClass(i),
			(0 != t || 0 != a) && (t > 0 && a >= t && $("#history_back").removeClass(i), t >= 0 && t != a && $("#history_next").removeClass(i))
		};
		return {
			add: a,
			back: s,
			next: n,
			list: function() {
				return e
			}
		}
	} (),
	r = function(e) {
		if (undefined != e) {
			"string" == typeof e && (e = [e]);
			for (var i = 0; e.length > i; i++) e[i] = trim(e[i], "/");
			ui.fileLight.clear(),
			ui.fileLight.fileListAll().each(function() {
				var i = trim(ui.fileLight.path($(this)), "/");
				i && -1 != $.inArray(i, e) && $(this).addClass(Config.SelectClassName)
			}),
			ui.fileLight.select(),
			ui.fileLight.setInView()
		}
	},
	l = function(e) {
		if ("" != e) {
			if (e = e.toLowerCase(), undefined == a || G.this_path != a.path || e != a.key) {
				var i = [];
				ui.fileLight.fileListAll().each(function() {
					var t = ui.fileLight.name($(this)),
					a = ui.fileLight.path($(this));
					t && e == t.substring(0, e.length).toLowerCase() && i.push(a)
				}),
				a = {
					key: e,
					path: G.this_path,
					index: 0,
					list: i
				}
			}
			0 != a.list.length && (r(a.list[a.index++]), a.index == a.list.length && (a.index = 0))
		}
	},
	c = function(e) {
		return "" == e ? (ui.fileLight.clear(), undefined) : (ui.fileLight.clear(), ui.fileLight.fileListAll().each(function(i) {
			var t = ui.fileLight.name($(this)); - 1 != t.toLowerCase().indexOf(e) && $(ui.fileLight.fileListAll()).eq(i).addClass(Config.SelectClassName)
		}), ui.fileLight.select(), ui.fileLight.setInView(), undefined)
	},
	d = function(e, i) {
		var t = G.this_path + e;
		return undefined == i && (t += "/"),
		0 != $('.bodymain .file[data-path="' + pathHashEncode(t) + '"]').length ? true : false
	},
	p = function(e, i) {
		var t, a = 0,
		n = "." + i;
		if ((undefined == i || "" == i) && (n = ""), !d(e + n, i)) return e + n;
		for (t = e + "(0)" + n; d(t, i);) a++,
		t = e + "(" + a + ")" + n;
		return t
	},
	u = function(e, t) {
		var a, n = 0,
		o = G.json_data.folderlist,
		s = G.json_data.filelist,
		r = o,
		l = G.user_config.list_sort_field,
		c = G.user_config.list_sort_order,
		d = {
			name: e,
			size: 0,
			ext: t,
			mtime: date("Y/m/d H:i:s", time())
		};
		for ("desktop" == Config.pageApp && (n += $(".menuDefault").length + 1), "file" == t ? (d.ext = core.pathExt(e), r = s, "up" == c && (n += o.length)) : "down" == c && (n += s.length), a = 0; r.length > a; a++) if ("down" == c) {
			if ( - 1 == i.strSort(r[a][l], d[l])) break
		} else if ( - 1 != i.strSort(r[a][l], d[l])) break;
		return a + n - 1
	},
	f = function(t, a, n) {
		ui.fileLight.clear();
		var o = u(a, t),
		s = $(Config.FileBoxSelector);
		"list_split" == G.user_config.list_type && (s = $(".split_box.split_select").find(".content"));
		var r = e("./tpl/file_create.html"),
		l = template.compile(r),
		c = l({
			type: t,
			newname: a,
			ext: n,
			list_type: G.user_config.list_type
		});
		if ( - 1 == o || 0 == s.find(".file").length) s.html(c + s.html());
		else {
			var p = s.find(".file:eq(" + o + ")");
			0 == p.length && (p = s.find(".file").last()),
			"list_split" == G.user_config.list_type && (p = $(".split_box.split_select .file").last()),
			$(c).insertAfter(p)
		}
		var f = $(".textarea .newfile"),
		h = a.length;
		"folder" != t && -1 != a.indexOf(".") && (h = a.length - n.length - 1),
		f.textSelect(0, h),
		"list_split" == G.user_config.list_type && f.css("width", f.parents(".filename").width() - 40),
		"icon" == G.user_config.list_type ? ($("#makefile").css({
			height: $("#makefile").width() + 15,
			"margin-left": "3px",
			transition: "none"
		}), $("#makefile .textarea").css("margin-top", "-13px")) : $("#makefile .x-item-file").addClass("small"),
		"desktop" == Config.pageApp && ui.resetDesktopIcon();
		var v = function(e) {
			e === false ? $("#makefile").remove() : m(e)
		},
		_ = function(e) {
			if ("" == trim(e)) return $("#makefile").remove(),
			Tips.tips("操作失败", "warning"),
			undefined;
			if (d(e, n)) $("#makefile").remove(),
			Tips.tips("该名称已存在！", "warning");
			else {
				var a = G.this_path;
				"list_split" == G.user_config.list_type && (a = ui.fileLight.path($(".file_icon_edit").parents(".split_box"))),
				"folder" == t ? i.newFolder(a + e, v) : i.newFile(a + e, v)
			}
		};
		ui.fileLight.setInView($(".fileContiner .file_icon_edit")),
		f.focus().autoTextarea(),
		f.unbind("keydown").keydown(function(e) {
			13 == e.keyCode && (stopPP(e), e.preventDefault(), _(f.attr("value"))),
			27 == e.keyCode && $("#makefile").remove()
		}).unbind("blur").blur(function() {
			_(f.attr("value"))
		})
	},
	h = function() {
		var e = "",
		t = ui.fileLight.fileListSelect(),
		a = ui.fileLight.name(t),
		o = core.pathFather(ui.fileLight.path(t)),
		s = ui.fileLight.type(t);
		if (1 == t.length && n()) {
			if (t.hasClass("menuSharePath")) return ui.path.shareEdit(),
			undefined;
			var r = htmlEncode(rtrim(a, ".oexe")),
			l = "<input class='fix' id='pathRenameTextarea' value='" + r + "'/>";
			"icon" == G.user_config.list_type && (l = "<textarea class='fix' id='pathRenameTextarea'>" + r + "</textarea>", t.css({
				height: t.height()
			})),
			$(t).addClass("file_icon_edit").find(".title").html("<div class='textarea'>" + l + "<div>");
			var c = $("#pathRenameTextarea");
			"list_split" == G.user_config.list_type && c.css({
				width: c.parents(".filename").width() - 32,
				height: c.parents(".filename").height() + 1
			});
			var d = a.length;
			"folder" != s && -1 != a.indexOf(".") && (d = a.length - s.length - 1),
			s || 0 != a.indexOf(".") ? c.textSelect(0, d) : c.textSelect(0, a.length);
			var p = function(n) {
				if ("oexe" == s && (n += ".oexe"), n != a) e = o + a,
				n = o + n,
				i.rname(e, n,
				function(e) {
					e === false ? $(t).removeClass("file_icon_edit").find(".title").html(htmlEncode(a)) : m(e)
				});
				else {
					var r = a;
					".oexe" == r.substr( - 5) && (r = r.substr(0, r.length - 5)),
					$(t).removeClass("file_icon_edit").find(".title").html(htmlEncode(r))
				}
			};
			c.focus().autoTextarea(),
			c.keydown(function(e) {
				13 == e.keyCode && (e.preventDefault(), stopPP(e), p(c.attr("value"))),
				27 == e.keyCode && ("oexe" == s && (a = a.replace(".oexe", "")), $(t).removeClass("file_icon_edit").find(".title").html(a))
			}).unbind("blur").blur(function() {
				p(c.val())
			})
		}
	},
	m = function(e) {
		ui.fileLight.clear(),
		ui.f5Callback(function() {
			r(e),
			"explorer" == Config.pageApp && ui.tree.checkIfChange(G.this_path)
		})
	},
	v = function(e) {
		var i = {},
		t = [];
		e.sort(function(e, i) {
			return e.path == i.path ? 0 : e.path > i.path ? 1 : -1
		});
		for (var a = function(e) {
			for (var t = e;
			"" != e;) {
				if (i[e] !== undefined) return 1 == i[e] ? true : t == e ? (i[e] = 1, false) : true;
				e = core.pathFather(e)
			}
			return ! 1
		},
		n = 0; e.length > n; n++) if ("folder" == e[n].type) {
			var o = rtrim(e[n].path, "/") + "/";
			i[o] || a(o) || (i[o] = 0)
		}
		for (var n = 0; e.length > n; n++) {
			var o = e[n].path;
			"folder" == e[n].type && (o = rtrim(o, "/") + "/"),
			a(o) || t.push(e[n])
		}
		return t
	},
	_ = function(e) {
		if (e) {
			var i = [];
			return 0 == ui.fileLight.fileListSelect().length ? i: (ui.fileLight.fileListSelect().each(function() {
				var e = ui.fileLight.path($(this)),
				t = "folder" == ui.fileLight.type($(this)) ? "folder": "file";
				i.push({
					path: e,
					type: t
				})
			}), v(i))
		}
		if (1 != ui.fileLight.fileListSelect().length) return {
			path: "",
			type: ""
		};
		var t = ui.fileLight.fileListSelect(),
		a = ui.fileLight.path(t),
		n = ui.fileLight.type(t);
		return {
			path: a,
			type: n
		}
	},
	g = function(e, i) {
		for (var t in G.json_data) if ("filelist" == t || "folderlist" == t) for (var a = 0; G.json_data[t].length > a; a++) if (G.json_data[t][a][e] == i) return G.json_data[t][a]
	};
	return {
		makeParam: _,
		history: s,
		getJsondataCell: g,
		checkSystemPath: n,
		appEdit: function(e) {
			if (e) i.appEdit(0, 0, "user_add");
			else {
				var t = ui.fileLight.fileListSelect().attr("data-app"),
				a = jsonDecode(base64Decode(t));
				a.path = ui.fileLight.path(ui.fileLight.fileListSelect()),
				i.appEdit(a)
			}
		},
		appList: function() {
			i.appList(_().path)
		},
		appInstall: function() {
			i.appInstall(_().path)
		},
		openEditor: function() {
			t.openEditor(_().path)
		},
		openWindow: function() {
			var e = _();
			return "folder" == e.type && -1 != core.path2url(e.path).search("explorer/fileProxy") ? (Tips.tips("此目录不支持该操作!", false), undefined) : (t.openWindow(e.path), undefined)
		},
		open: function(e) {
			if ("editor" == Config.pageApp) return t.open(e),
			undefined;
			if (undefined != e) return t.open(e),
			undefined;
			if (0 != ui.fileLight.fileListSelect().length) {
				var i = _(),
				a = ui.fileLight.fileListSelect();
				if (inArray(core.filetype.image, i.type)) return ui.picasa.initData(),
				ui.picasa.play($(a).find(".picasaImage")),
				undefined;
				if (0 != $(a).find(".file_not_exists").length) return Tips.tips("共享文件不存在,被删除或者移走了!", false),
				undefined;
				if ("oexe" == i.type) {
					var n = a.attr("data-app");
					i.path = jsonDecode(base64Decode(n))
				} ("list_split" != G.user_config.list_type || "folder" != i.type) && t.open(i.path, i.type)
			}
		},
		play: function() {
			if (! (1 > ui.fileLight.fileListSelect().length)) {
				var e = [];
				ui.fileLight.fileListSelect().each(function() {
					var i = ui.fileLight.type($(this));
					if (inArray(core.filetype.music, i) || inArray(core.filetype.movie, i)) {
						var t = ui.fileLight.path($(this)),
						a = core.path2url(t, false);
						e.push({
							url: a,
							name: core.pathThis(t),
							ext: i
						})
					}
				}),
				t.play(e)
			}
		},
		pathOperate: i,
		share: function() {
			i.share(_())
		},
		setBackground: function() {
			var e = core.path2url(_().path);
			ShareData.frameTop("",
			function(i) {
				i.ui.setWall(e)
			}),
			ui.setWall(e),
			i.setBackground(e)
		},
		createLink: function(e) {
			var t = _(),
			a = ui.fileLight.fileListSelect().last();
			t.name = trim(a.find(".filename").text()),
			i.createLink(t.path, t.name, t.type, e, m)
		},
		createProject: function() {
			i.createProject(_().path, m)
		},
		download: function() {
			var e = _(true);
			1 == e.length && "file" == e[0].type ? t.download(_().path) : i.zipDownload(e)
		},
		shareEdit: function() {
			var e = g("path", _().path);
			try {
				var t = G.json_data.share_list[e.sid];
				i.shareBox(t)
			} catch(a) {}
		},
		shareOpenWindow: function() {
			var e = g("path", _().path),
			i = e.type;
			"folder" == e.type && (i = 1 == e.code_read ? "code_read": "folder");
			var t = "./share/" + i + "?user=" + G.json_data.info.id + "&sid=" + e.sid;
			window.open(t)
		},
		shareOpenPath: function() {
			var e = _(),
			i = g("path", e.path);
			if (!i || !G.json_data.share_list) return t.open(e.path, e.type),
			undefined;
			var a = G.json_data.share_list[i.sid],
			n = core.pathFather(a.path),
			o = core.pathThis(a.path);
			"folder" == a.type ? ui.path.list(a.path, "") : ui.path.list(n, "",
			function() {
				r(o)
			})
		},
		explorer: function() {
			core.explorer(_().path)
		},
		explorerNew: function() {
			window.open("/explorer?path=" + _().path)
		},
		openProject: function() {
			core.explorerCode(_().path)
		},
		search: function() {
			core.search("", _().path)
		},
		fav: function() {
			var e = _(),
			t = ui.fileLight.fileListSelect().last();
			e.name = trim(t.find(".filename").text()),
			i.fav(e)
		},
		recycleClear: function() {
			i.remove([{
				type: "recycle_clear",
				path: "{user_recycle}"
			}],
			function() {
				ui.f5()
			})
		},
		remove: function(e, t, a) {
			if (G.json_data.info && n()) {
				var o = _(true);
				G.json_data.info && G.json_data.info.path_type == G.AWD_USER_SHARE && G.json_data.info.id == G.user_id && -1 == trim(G.this_path, "/").indexOf("/") && $.each(o,
				function(e) {
					var i = g("path", o[e].path);
					undefined != i && (o[e].type = "share", o[e].path = i.sid)
				}),
				a ? i.remove(o, a, e, t) : i.remove(o, m, e, t)
			}
		},
		removeImage: function(e) {
			var i = ".picasaImage",
			t = $(i).eq(e);
			ui.fileLight.clear(),
			t.parents(".file").addClass(Config.SelectClassName),
			ui.fileLight.select(),
			ui.path.remove(false, false,
			function() {
				ui.fileLight.clear(),
				ui.f5Callback(function() {
					var t = $(i).eq(e);
					return 0 == t.length && (t = $(i).last()),
					0 == t.length ? (ui.picasa.close(), undefined) : (ui.picasa.initData(), ui.picasa.play(t), undefined)
				})
			})
		},
		favRemove: function() {
			var e = $(".file.select .filename");
			e.each(function(t) {
				var a = trim($(this).text());
				t != e.length - 1 ? i.favRemove(a, "", true) : i.favRemove(a,
				function(e) {
					Tips.tips(e),
					ui.tree.refreshFav()
				},
				true)
			})
		},
		copy: function() {
			n() && i.copy(_(true))
		},
		cute: function() {
			n() && i.cute(_(true), ui.f5)
		},
		zip: function(e) {
			i.zip(_(true), m, e)
		},
		unZip: function(e) {
			n() && i.unZip(_().path, ui.f5, e)
		},
		cuteDrag: function(e) {
			i.cuteDrag(_(true), e, m)
		},
		copyDrag: function(e, t) {
			i.copyDrag(_(true), e, m, t)
		},
		copyTo: function() {
			core.api.pathSelect({
				type: "folder",
				title: "复制到..."
			},
			function(e) {
				i.copyDrag(_(true), e, m, false)
			})
		},
		cuteTo: function() {
			core.api.pathSelect({
				type: "folder",
				title: "移动到..."
			},
			function(e) {
				i.cuteDrag(_(true), e, m)
			})
		},
		info: function() {
			i.info(_(true))
		},
		past: function() {
			var e = G.this_path;
			"list_split" == G.user_config.list_type && ($containBox = $(".split_box.split_select"), 1 == $containBox.length && (e = ui.fileLight.path($containBox))),
			i.past(e, m)
		},
		newFile: function(e) {
			undefined == e && (e = "txt"),
			f("file", p("newfile", e), e)
		},
		newFolder: function() {
			alert("fgggg")
			f("folder", p("新建文件夹"), "")
		},
		showFile: function() {
			var e = "./share/file&sid=" + G.sid + "&user=" + G.user + "&path=" + _().path;
			window.open(e)
		},
		rname: h,
		list: o,
		setSearchByStr: c,
		setSelectByChar: l,
		setSelectByFilename: r,
		clipboard: i.clipboard
	}
});
