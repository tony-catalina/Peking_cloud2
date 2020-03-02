define("app/src/message/fileListResize", [],
function() {
	var e = {
		filename: 250,
		filetype: 80,
		filesize: 80,
		filetime: 150,
		explorer_left_tree_width: 199,
		editor_left_tree_width: 199
	},
	i = {
		filename: 150,
		filetype: 60,
		filesize: 60,
		filetime: 120,
		explorer_left_tree_width: 2,
		editor_left_tree_width: 2
	},
	t = e,
	a = function() {
		if (LocalData.get("resize_config")) t = jsonDecode(LocalData.get("resize_config"));
		else {
			G.user_config.resize_config !== undefined && (t = jsonDecode(htmlDecode(G.user_config.resize_config)));
			var a = jsonEncode(t);
			LocalData.set("resize_config", a)
		}
		$.each(e,
		function(a) { (!t[a] || t[a] < i[a]) && (t[a] = e[a])
		})
	},
	n = function() {
		if (!r()) {
			var e = jsonEncode(t);
			LocalData.set("resize_config", e),
			$.get("setting/set?key=resize_config&v=" + e)
		}
	},
	o = function(e) {
		if ("icon" != G.user_config.list_type) {
			e || (e = t);
			var a = "",
			n = 0;
			$.each(e,
			function(e, t) {
				0 == e.indexOf("file") && (i[e] >= t && (t = i[e]), n += t, a += ".fileList_list .file ." + e + ",#main_title ." + e + "{width:" + t + "px;}")
			}),
			a += ".fileList_list .file{width:" + (n + 50) + "px;}",
			$.setStyle(a, "header_resize_width")
		}
	},
	s = function(a, o, s) {
		if (!$(".frame-left").is(":hidden")) {
			var r = Config.pageApp + "_left_tree_width",
			l = $.extend(true, {},
			t);
			l[r] += a,
			l[r] <= i[r] && (l[r] = i[r]);
			var c = l[r],
			d = $(".frame-left"),
			p = $(".frame-resize"),
			u = $(".frame-right"),
			f = e[r];
			if (c > f - 8 && f + 8 > c && (c = f + 1), s) {
				var h = 400;
				d.animate({
					width: c
				},
				h),
				p.animate({
					left: c - 5
				},
				h),
				u.animate({
					left: c
				},
				h)
			} else d.css("width", c),
			p.css("left", c - 5),
			u.css("left", c);
			ui.setStyle !== undefined && ui.setStyle(),
			o && (t = l, n())
		}
	},
	r = function() {
		return undefined != $.getUrlParam("type") ? true : false
	},
	l = function(e, a, s) {
		var r = $.extend(true, {},
		t);
		r[e] += a,
		o(r),
		s && (t = r, $.each(t,
		function(e, a) {
			i[e] >= a && (t[e] = i[e])
		}), n())
	},
	c = function() {
		$("#main_title").hasClass("bind_init") || (o(t), $("#main_title").addClass("bind_init"), $.each(e,
		function(e) {
			$("#main_title ." + e + "_resize").drag({
				start: function() {},
				move: function(i) {
					l(e, i, false)
				},
				end: function(i) {
					l(e, i, true)
				}
			})
		}))
	},
	d = function() {
		var e = $(".frame-resize");
		e.drag({
			start: function() {
				e.addClass("active"),
				$(".resizeMask").css("display", "block")
			},
			move: function(e) {
				s(e, false, false)
			},
			end: function(i) {
				s(i, true, false),
				e.removeClass("active"),
				$(".resizeMask").css("display", "none")
			}
		})
	},
	p = function() {
		var e = G.user_config.file_icon_size;
		e || (e = "75"),
		h(e, false),
		u(e)
	},
	u = function(e) {
		$(".set-file-icon-size .file-icon-size").removeClass("selected");
		for (var i = [["40", "box-size-smallx"], ["60", "box-size-small"], ["80", "box-size-default"], ["150", "box-size-big"], ["220", "box-size-bigx"]], t = 10, a = "", n = 0; i.length > n; n++) {
			var o = parseInt(i[n][0]);
			if (e >= o - t && o + t >= e) {
				a = i[n][1];
				break
			}
		}
		"" != a && $("." + a).addClass("selected")
	},
	f = function(e) {
		G.user_config.file_icon_size = e,
		u(e),
		$.get("setting/set?key=file_icon_size&v=" + e)
	},
	h = function(e, i) {
		var t = e,
		a = 105,
		n = 30,
		o = 250;
		"desktop" == Config.pageApp && (n = 50, o = 120),
		t = n >= t ? n: t,
		t = t >= o ? o: t;
		var s = (e - n) * a / (o - n),
		r = 20,
		l = 10,
		c = parseInt(t),
		d = c + 2 * r - l + 5,
		p = c - l,
		u = c - l,
		h = .4 * c,
		m = c + 3 * r - l,
		v = ".fileList_icon div.file{height:" + d + "px;width:" + c + "px;}";
		"desktop" == Config.pageApp && (d -= 5, v = "div.fileList_icon div.file{height:" + d + "px;width:" + c + "px;}"),
		$.browser.mozilla && (u -= 4);
		var _ = "div.fileList_icon div.file{max-height:" + m + "px;}" + v + "			.fileList_icon .meta_info{height:" + h + "px;width:" + h + "px;				margin-right:" + .16 * h + "px;margin-top:-" + 1.1 * h + "px;}			.fileList_icon div.file .filename{width:" + c + "px;}			.fileList_icon div.file .filename #pathRenameTextarea,			.fileList_icon div.file .filename .newfile{width:" + c + "px;}			.fileList_icon div.file .ico{padding-left:" + l / 2 + "px;height:" + u + "px;width:" + p + "px}        	.fileList_icon div.file .ico.picasaImage{width:" + p + "px;padding-left:" + l / 2 + "px;overflow:hidden;}";
		$.setStyle(_, "file_icon_resize"),
		$(".slider_handle").css("top", s),
		i && f(e)
	},
	m = function() {
		var e, i = $(".slider_handle");
		$(".set_icon_size_slider").bind("click",
		function(e) {
			return stopPP(e),
			false
		});
		var t = function(i) {
			var t = 0,
			a = 105,
			n = 30,
			o = 250,
			s = e + i;
			s = t > s ? t: s,
			s = s > a ? a: s;
			var r = parseInt(s / a * (o - n) + n);
			return h(r, false),
			r
		};
		i.drag({
			start: function() {
				i.addClass("active"),
				e = parseInt(i.css("top"))
			},
			move: function(e, i) {
				t(i)
			},
			end: function(e, a) {
				i.removeClass("active"),
				f(t(a), true)
			}
		});
		var a = $(".slider_bg");
		$(".slider_bg").unbind("click").bind("click",
		function(i) {
			var n = i.clientY - a.offset().top;
			e = 0,
			t(n)
		})
	},
	v = function() {
		var e = function(e, i) {
			var t = e.parent(),
			a = $(".split_box").index(t),
			n = parseInt(t.data("before_width")) + i;
			if (! (150 > n)) {
				$($(".split_line").get(a)).css("width", n),
				t.css("width", n),
				$(".split_box:gt(" + a + ")").each(function() {
					$(this).hasClass("is_drag_split") || $(this).css("left", parseInt($(this).data("before_left")) + i + "px")
				});
				var o = [];
				$(".split_box").each(function() {
					o.push({
						left: $(this).css("left"),
						width: $(this).width()
					})
				}),
				LocalData.set("split_box_size", jsonEncode(o))
			}
		};
		$(".bodymain .fileList_list_split .split_drag").drag({
			start: function(e, i) {
				var t = i.parent();
				t.addClass("is_drag_split").data("before_width", t.width()),
				$(".split_box,.split_line").each(function() {
					$(this).data("before_left", $(this).css("left"))
				})
			},
			move: function(i, t, a, n) {
				e(n, i)
			},
			end: function(e, i, t, a) {
				a.parent().removeClass("is_drag_split")
			}
		},
		true),
		$(".file.select_split_parent").removeClass("select_split_parent"),
		$(".split_box").each(function() {
			$('.file[data-path="' + $(this).attr("data-path") + '"]').addClass("select_split_parent")
		}),
		_()
	},
	_ = function() {
		var e = jsonDecode(LocalData.get("split_box_size")),
		i = 0;
		e || (e = []);
		var t = function(t, a) {
			var n = e[a];
			n || (n = {
				width: 250,
				left: i
			}),
			i += n.width + 1,
			t.css({
				width: n.width + "px",
				left: n.left
			})
		};
		i = 0,
		$(".split_box").each(function(e) {
			t($(this), e)
		}),
		i = 0,
		$(".split_line").each(function(e) {
			t($(this), e)
		}),
		$(".bodymain").scrollLeft(1e5)
	};
	return {
		init: function() {
			a(),
			r() && (t = e),
			o(t),
			d(),
			s(0, false, true),
			m()
		},
		initFileSize: p,
		bindSplitResize: v,
		bindHeaderResize: c,
		setFileIconSize: h
	}
});