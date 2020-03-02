define("app/src/explorer_wap/path", ["../../common/pathOperate", "../../common/pathOpen", "../../common/myPlayer"],
function(e) {
	var t = e("../../common/pathOperate"),
	a = e("../../common/pathOpen"),
	n = function(e, t, i) {
		if (undefined != e) {
			if (e == G.this_path) return undefined != t && "" != t && Tips.tips("所打开路径和当前路径一样！", "info"),
			undefined;
			"undefined/" == e && (e = G.myhome),
			G.this_path = e.replace(/\\/g, "/"),
			G.this_path = e.replace(/\/+/g, "/"),
			"/" != G.this_path.substr(G.this_path.length - 1) && (G.this_path += "/");
			var a = window.location.origin + window.location.pathname + window.location.search;
			window.location.href = a + "#" + urlEncode(G.this_path),
			ui.f5Callback(function() {
				"function" == typeof i && i()
			})
		}
	},
	o = function(e, t) {
		if (undefined != e) {
			if ("folder" == t) return ui.path.list(e + "/"),
			undefined;
			var i = core.path2url(e),
			a = ["pdf", "html", "htm"];
			if (a = a.concat(core.filetype.music), a = a.concat(core.filetype.movie), "oexe" == t) if ("string" == typeof e) {
				var n = e;
				"string" != typeof e && (n = e.content.split("'")[1]),
				core.fileGet(n,
				function(e) {
					var t = jsonDecode(e);
					t.name = core.pathThis(n),
					core.openApp(t)
				})
			} else core.openApp(e);
			else if ("pdf" == t) {
				var i = "./lib/plugins/pdfjs/web/viewer.html?file=" + urlEncode(core.path2url(e));
				window.location.href = i
			} else if (inArray(core.filetype.doc, t)) {
				var i = "./explorer/officeView?access_token=" + G.access_token + "&path=" + urlEncode(e);
				G.share_page !== undefined && (i = G.app_host + "share/officeView?user=" + G.user + "&sid=" + G.sid + "&path=" + urlEncode(e)),
				window.location.href = i
			} else if (inArray(a, t)) window.location.href = i;
			else if (inArray(core.filetype.text, t)) {
				var i = G.app_host + "editor/edit?filename=" + urlEncode(e);
				G.share_page !== undefined && (i = G.app_host + "share/edit?user=" + G.user + "&sid=" + G.sid + "#filename=" + urlEncode(e)),
				window.location.href = i
			} else if (inArray(core.filetype.image, t)) ui.openImage(e);
			else {
				var i = G.app_host + "explorer/fileDownload?path=" + urlEncode(e);
				G.share_page !== undefined && (i = G.app_host + "share/fileDownload?user=" + G.user + "&sid=" + G.sid + "&path=" + urlEncode(e));
				var o = '<div class="unknow_file" style="width:200px;word-break: break-all;"><span>' + "暂不支持打开" + "<br/>" + '</span><br/><a class="btn btn-success btn-sm" href="' + i + '"> ' + "下载" + " </a></div>";
				$.dialog({
					fixed: true,
					icon: "warning",
					width: 30,
					lock: true,
					background: "#000",
					opacity: .2,
					title: "文件打开提示!",
					padding: 10,
					content: o,
					cancel: true
				})
			}
		}
	},
	s = function(e, t, a) {
		var n = e.length;
		for (i = 0; n > i; i++) if (e[i][t] == a) return e[i]
	},
	r = function(e) {
		var t = "",
		i = 0;
		return null != G.json_data.filelist && (t = s(G.json_data.filelist, "name", e), null != t && (i = 1)),
		null != G.json_data.folderlist && (t = s(G.json_data.folderlist, "name", e), null != t && (i = 1)),
		i
	},
	l = function(e, t) {
		var i, a = 0;
		if (undefined == t) {
			if (!r(e)) return e;
			for (i = e + "(0)"; r(i);) a++,
			i = e + "(" + a + ")";
			return i
		}
		if (!r(e + "." + t)) return e + "." + t;
		for (i = e + "(0)." + t; r(i);) a++,
		i = e + "(" + a + ")." + t;
		return i
	},
	c = function() {
		$.dialog.prompt("",
		function(e) {
			t.newFolder(G.this_path + e,
			function() {
				ui.f5()
			})
		},
		l("folder"))
	},
	d = function() {
		$.dialog.prompt("",
		function(e) {
			t.newFile(G.this_path + e,
			function() {
				ui.f5()
			})
		},
		l("file", "txt"))
	},
	p = function(e) {
		$.dialog.prompt("",
		function(i) {
			var a = core.pathFather(e) + i;
			t.rname(e, a,
			function() {
				ui.f5()
			})
		},
		core.pathThis(e))
	},
	f = function(e, t, i) {
		return "folder" != i && (i = "file"),
		e ? [{
			path: t,
			type: i
		}] : {
			path: t,
			type: i
		}
	};
	return {
		pathOperate: t,
		pathOpen: a,
		download: function(e, i) {
			"folder" == i ? t.zipDownload([{
				path: e,
				type: "folder"
			}]) : a.download(e)
		},
		remove: function(e, i) {
			t.remove(f(true, e, i), ui.f5)
		},
		copy: function(e, i) {
			t.copy(f(true, e, i))
		},
		cute: function(e, i) {
			t.cute(f(true, e, i), ui.f5)
		},
		info: function(e, i) {
			t.info(f(true, e, i))
		},
		past: function() {
			t.past(G.this_path, ui.f5)
		},
		open: o,
		list: n,
		newFolder: c,
		newFile: d,
		rname: p
	}
});
