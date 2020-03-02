define("app/src/message/fileSelect", [],
function() {
	var e = false,
	i = false,
	t = false,
	a = function() {
		$(Config.FileBoxClass).die("touchstart").live("touchstart",
		function() {
			$(this).hasClass("select") ? ui.path.open() : (ui.fileLight.clear(), $(this).removeClass("select"), $(this).addClass("select"), ui.fileLight.select())
		});
		var a = function(e) { ! e.hasClass(Config.TypeFolderClass) && !e.hasClass("menuRecycleButton") || e.hasClass(Config.SelectClassName) || ($(".selectDragTemp").removeClass("selectDragTemp"), e.addClass("selectDragTemp"))
		},
		l = function() {};
		$(Config.FileBoxClass).die("mouseenter").live("mouseenter",
		function() {
			i && a($(this)),
			e || i || $(this).addClass(Config.HoverClassName),
			l($(this)),
			$(this).unbind("mousedown").bind("mousedown",
			function(e) {
				if ($(e.target).is("input") || $(e.target).is("textarea")) return ! 0;
				if ($(this).focus(), rightMenu.hidden(), $(e.target).parents(".children_more").length > 0) return ui.fileContent.pathChildrenTree($(this)),
				stopPP(e),
				false;
				if (! (e.ctrlKey || e.metaKey || e.shiftKey || $(this).hasClass(Config.SelectClassName))) return ui.fileLight.clear(),
				$(this).addClass(Config.SelectClassName),
				ui.fileLight.select(),
				true;
				if (3 != e.which || $(this).hasClass(Config.SelectClassName) || (ui.fileLight.clear(), $(this).addClass(Config.SelectClassName), ui.fileLight.select()), (e.ctrlKey || e.metaKey) && ($(this).hasClass(Config.SelectClassName) ? t = true : (ui.fileLight.setMenu($(this)), $(this).addClass(Config.SelectClassName)), ui.fileLight.select()), e.shiftKey) {
					var i = h.fileListAll($(this)),
					a = h.fileListSelect($(this)),
					n = i.index($(this));
					if (0 == a.length) x(0, n, i);
					else {
						var o = i.index(a.first()),
						s = i.index(a.last());
						o > n ? x(n, s, i) : n > s ? x(o, n, i) : x(o, n, i)
					}
				}
				return ! 0
			}).unbind("mouseup").bind("mouseup",
			function() {
				return $(".selectDragTemp").removeClass("selectDragTemp"),
				true
			})
		}).die("mouseleave").live("mouseleave",
		function() {
			$(this).removeClass(Config.HoverClassName),
			i && $(this).removeClass("selectDragTemp")
		}).die("click").live("click",
		function(e) {
			return stopPP(e),
			i ? false : (0 == $(this).find(".textarea").length && 0 == $(".file-draging-box").length && (e.ctrlKey || e.metaKey || e.shiftKey ? (e.ctrlKey || e.metaKey) && t && (t = false, ui.fileLight.resumeMenu($(this)), $(this).removeClass(Config.SelectClassName), ui.fileLight.select()) : (ui.fileLight.clear(), $(this).addClass(Config.SelectClassName), ui.fileLight.select(), r($(this)))), undefined)
		}).die("dblclick").live("dblclick",
		function(e) {
			if (! ($(e.target).is("textarea") || $(e.target).is("input") || $(e.target).hasClass("children_more") || $(e.target).hasClass("children_more_cert"))) if (e.altKey) ui.path.info();
			else {
				if (1 != ui.fileLight.fileListSelect().length) return;
				if ("list_split" == G.user_config.list_type && s($(this))) {
					var i = ui.fileLight.path($(this));
					return G.this_path = "",
					$(".fileList_list_split .split_box").remove(),
					ui.path.list(i),
					undefined
				}
				ui.path.open()
			}
		}),
		n(),
		o(),
		$(Config.FileBoxTittleClass).die("dblclick").live("dblclick",
		function(e) {
			if (!$(this).hasClass("db_click_rename")) return ! 0;
			if (! ($(this).find(".textarea").length > 0)) {
				var i = $(this).parents(".file");
				return i.hasClass("systemBox"),
				ui.path.rname(),
				stopPP(e),
				false
			}
		})
	},
	n = function() {
		var e;
		$("#folderList a").die("mouseenter").live("mouseenter",
		function() {
			if (i) { ($(this).hasClass("menuTreeFolder") || $(this).hasClass("menuTreeFolderFav")) && $(this).addClass("curDropTreeNode"),
				clearTimeout(e),
				e = false;
				var t = ui.tree.zTree(),
				a = t.getNodeByTId($(this).parent().attr("id")); ! a.open && a.isParent && (e = setTimeout(function() {
					t.expandNode(a, true)
				},
				600))
			}
		}).die("mouseup").live("mouseup",
		function() {
			if (i) {
				$(this).removeClass("curDropTreeNode"),
				clearTimeout(e),
				e = false;
				var t = ui.tree.zTree(),
				a = t.getNodeByTId($(this).parent().attr("id"));
				setTimeout(function() {
					a.isParent = true,
					t.reAsyncChildNodes(a, "refresh")
				},
				100)
			}
		}).die("mouseleave").live("mouseleave",
		function() {
			i && ($(this).removeClass("curDropTreeNode"), clearTimeout(e), e = false)
		})
	},
	o = function() {
		$(".header-middle .yarnlet a").die("mouseenter").live("mouseenter",
		function() {
			i && $(this).addClass("curDropToPath")
		}).die("mouseup").live("mouseup",
		function() {
			i && $(this).removeClass("curDropToPath")
		}).die("mouseleave").live("mouseleave",
		function() {
			i && $(this).removeClass("curDropToPath")
		})
	},
	s = function(e) {
		return "icon" == G.user_config.list_type ? e.hasClass("folderBox") || e.hasClass("menuRecycleButton") ? true : false : "list" == G.user_config.list_type ? e.hasClass("folderBox") || e.hasClass("menuRecycleButton") || 0 != e.find(".children_more_cert").length ? true : false : "list_split" == G.user_config.list_type ? e.hasClass("folderBox") || e.hasClass("menuRecycleButton") || 0 != e.find(".children_more_cert").length ? true : false : undefined
	},
	r = function(e) {
		if ("list_split" == G.user_config.list_type && s(e) && 1 == ui.fileLight.fileListSelect().length) {
			var i = ui.fileLight.path(e);
			ui.path.history.add(i),
			ui.fileContent.pathChildrenSplit(i,
			function() {
				c(i)
			})
		}
	},
	l = function() {
		var e = ".fileList_list_split .split_box",
		i = "split_hover";
		$(e).live("mouseenter",
		function() {
			$(e).removeClass(i),
			$(this).addClass(i)
		}).die("mouseleave").live("mouseleave",
		function() {
			$(this).removeClass(i)
		}).die("click").live("click",
		function() {
			c(ui.fileLight.path($(this)))
		}).die("mousedown").live("mousedown",
		function(e) {
			var i = $(e.target).parents(".file"); (0 == i.length || 0 == i.find(".children_open").length) && c(ui.fileLight.path($(this)))
		})
	},
	c = function(e) {
		var i = $(".fileList_list_split .split_box"),
		t = $('.fileList_list_split .split_box[data-path="' + pathHashEncode(e) + '"]'),
		a = $('.fileList_list_split .split_box .file[data-path="' + pathHashEncode(e) + '"]'),
		n = "split_select";
		0 == t.length && (t = i.last()),
		i.removeClass(n),
		t.addClass(n),
		0 == ui.fileLight.fileListSelect().length && a.addClass("select"),
		ui.fileLight.select();
		var o = t.data("jsonData");
		o && e && (ui.fileContent.pathTypeChange(o), G.this_path = e, G.json_data = o, ui.headerAddress.addressSet()),
		ui.fileLight.init()
	},
	d = function(e) {
		return e.hasClass("menuSharePath") || e.hasClass("systemBox") ? false : true
	},
	p = function() {
		var t, a, n, o = 150,
		s = false,
		r = false,
		l = 0,
		c = false,
		p = -15,
		u = 10,
		f = 0,
		h = 0,
		m = "selectDragDraging";
		$(Config.FileBoxClass).die("mousedown").live("mousedown",
		function(i) {
			if (!i.shiftKey) {
				if (ui.isEdit()) return ! 0;
				if (1 != i.which || e) return ! 0;
				t = $(this),
				d(t) && (g(i), $.browser.mozilla || this.setCapture && this.setCapture(), $(document).mousemove(function(e) {
					b(e)
				}), $(document).keydown(function(e) {
					b(e)
				}), $(document).keyup(function(e) {
					b(e)
				}), $(document).one("mouseup",
				function(e) {
					k(e),
					this.releaseCapture && this.releaseCapture()
				}))
			}
		});
		var v, _, g = function(e) {
			rightMenu.hidden(),
			i = true,
			l = $.now(),
			f = e.pageY,
			h = e.pageX,
			a = $(document).height(),
			n = $(document).width(),
			r = $(e.target).parents(".file")
		},
		b = function(e) {
			if (!i) return ! 0;
			if (window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty(), $.now() - l > o && !c && (w(), s = $(".draggable-dragging"), s.attr("data-beforeInfo", s.find("span").html())), c) {
				var t = e.clientX >= n - 50 ? n - 50 : e.clientX,
				r = e.clientY >= a - 50 ? a - 50 : e.clientY;
				return t = 0 >= t ? 0 : t,
				r = 0 >= r ? 0 : r,
				t -= p,
				r -= u,
				s.css({
					left: t,
					top: r
				}),
				y(e),
				j(t - h + p, r - f + u),
				true
			}
		},
		y = function(e) {
			clearTimeout(v),
			v = false,
			v = setTimeout(function() {
				try {
					x(e)
				} catch(i) {}
			},
			10)
		},
		x = function(e) {
			var i = e.ctrlKey || e.metaKey,
			t = function(e, i) {
				G.sid !== undefined && (e = "none"),
				undefined != i && 0 !== i.search(G.AWD_GROUP_PATH) && 0 !== i.search(G.AWD_USER_RECYCLE) && core.isSystemPath(i) && (e = "clear");
				var t = htmlEncode(core.pathThis(i)),
				a = " " + s.attr("data-beforeInfo").replace(/<[^<>]+>/g, ""),
				n = {
					copy_to: '<i class="font-icon bg-ok icon-copy"></i><b>' + "复制到..." + '</b>"' + t + '"',
					move_to: '<i class="font-icon bg-ok icon-share-alt"></i><b>' + "移动到..." + '</b>"' + t + '"',
					remove: '<i class="font-icon bg-error icon-trash"></i><b>' + "删除" + a + "</b>",
					share: '<i class="font-icon bg-ok icon-share-sign"></i><b>' + "共享" + a + "</b>",
					none: '<i class="font-icon bg-error icon-minus"></i><b>' + "该目录没有写权限" + "</b>",
					clear: s.attr("data-beforeInfo")
				};
				i == G.this_path && (n.copy_to = '<i class="font-icon bg-ok icon-copy"></i><b>' + "创建副本" + "</b>"),
				s.find("span").html(n[e]),
				s.attr("data-actionType", e),
				s.attr("data-actionPath", i)
			};
			if (0 != $(".selectDragTemp").length || 0 != $(".curDropTreeNode").length || 0 != $(".curDropToPath").length) {
				var a = ui.fileLight.path($(".selectDragTemp"));
				if (0 != $(".curDropTreeNode").length) {
					var n = ui.tree.zTree(),
					o = n.getNodeByTId($(".curDropTreeNode").parent().attr("id"));
					a = o.path
				}
				0 != $(".curDropToPath").length && (a = $(".curDropToPath").attr("data-path")),
				$(".selectDragTemp").hasClass("menuRecycleButton") ? t("remove") : i ? t("copy_to", a) : t("move_to", a)
			} else if (0 != $(".recycle_hover").length) t("remove");
			else if (0 != $(".share_hover").length && 1 >= ui.fileLight.fileListSelect().length) t("share");
			else if (i) {
				var a = G.this_path;
				"list_split" == G.user_config.list_type && 0 != $(".split_hover").length && (a = ui.fileLight.path($(".split_hover"))),
				t("copy_to", a)
			} else "list_split" != G.user_config.list_type || 0 == $(".split_hover").length || $(".split_hover").hasClass("split_select") ? t("clear") : t("move_to", ui.fileLight.path($(".split_hover")))
		},
		k = function() {
			if (!i) return ! 1;
			if (i = false, c = false, $("body").removeClass(m), s) {
				s.addClass("animated-300").addClass("flipOutXLine").fadeOut(200,
				function() {
					s.remove(),
					s = false
				});
				var e = function(e, i) {
					switch (e) {
					case "copy_to":
						ui.path.copyDrag(i, true);
						break;
					case "move_to":
						ui.path.cuteDrag(i);
						break;
					case "remove":
						ui.path.remove(true);
						break;
					case "share":
						ui.path.share();
						break;
					default:
					}
				},
				t = s.attr("data-actionType"); - 1 != $.inArray(t, ["copy_to", "move_to", "remove", "share"]) ? P(false) : P(true),
				e(t, s.attr("data-actionPath"))
			}
		},
		w = function() {
			$("body").addClass(m);
			var e = ui.fileLight.fileListSelect().length;
			$('<div class="file draggable-dragging"><div class="drag_number">' + e + '</div><span><i class="font-icon bg-default icon-ok"></i>' + e + " " + "文件" + "</span></div>").appendTo("body"),
			c = true,
			setTimeout(S, 20)
		},
		N = 0,
		L = 5,
		C = 35,
		z = 20,
		T = 60,
		S = function() {
			clearTimeout($(".file-draging-box").data("removeDelay")),
			$(".file-draging .file").stop(),
			$(".file-draging-box").remove();
			var e = {
				icon: "fileList_icon",
				list: "fileList_list",
				list_split: "fileList_list_split"
			},
			i = e[G.user_config.list_type];
			$("<div class='fileContiner file-draging-box'><div class='" + i + " file-draging'></div></div>").appendTo("body"),
			_ = $(Config.SelectClass).filter("[data-path!='']");
			var t = _.clone(); (_.length >= T || $.browser.msie) && (t = r.clone()),
			t.appendTo(".file-draging"),
			t.each(function(e) {
				var i = $(".bodymain .fileContiner .file[data-path='" + $(this).attr("data-path") + "']"),
				a = i.offset();
				$(this).css({
					left: a.left,
					top: a.top,
					width: i.width()
				}),
				$(this).data({
					"data-left": a.left,
					"data-top": a.top,
					"data-animateTime": 200 + e * L,
					"data-sizeAdd": N * e
				}),
				$(this).attr("data-path") == r.attr("data-path") && $(this).addClass("handle_target"),
				1 == t.length && ($(this).data({
					"data-animateTime": 0
				}), z = 0)
			}),
			_.addClass("item-file-draging");
			var a = setTimeout(function() {
				$(".file-draging-box").data("animate", "finished");
				var e = $(".draggable-dragging");
				t.each(function() {
					var i = $(this),
					t = $(this).data("data-sizeAdd"),
					a = $(this).data("data-animateTime");
					$(this).data("status", "ready"),
					$(this).animate({
						opacity: 1
					},
					{
						duration: a,
						easing: "swing",
						progress: function(a, n) {
							var o = i.offset(),
							s = e.offset(),
							r = (s.left + t - o.left) * n,
							l = (s.top + t + C - o.top) * n;
							i.css({
								left: o.left + r,
								top: o.top + l
							})
						},
						complete: function() {
							i.data("status", "finished")
						}
					})
				})
			},
			z);
			$(".file-draging-box").data("dragDelay", a),
			$(".file-draging-box").data("animate", "ready")
		},
		j = function(e, i) {
			return "finished" != $(".file-draging-box").data("animate") ? ($(".file-draging .file").each(function() {
				$(this).css({
					left: $(this).data("data-left") + e,
					top: $(this).data("data-top") + i
				})
			}), undefined) : ($(".file-draging .file").each(function() {
				if ("finished" == $(this).data("status")) {
					var e = $(this).data("data-sizeAdd"),
					i = $(".draggable-dragging").offset();
					$(this).css({
						left: i.left + e,
						top: i.top + e + C
					})
				}
			}), undefined)
		},
		P = function(e) {
			var i = $(".file-draging .file").length;
			clearTimeout($(".file-draging-box").data("dragDelay")),
			$(".file-draging .file").each(function(i) {
				var t = i * L,
				a = $(".bodymain .fileContiner .file[data-path='" + $(this).attr("data-path") + "']");
				e ? $(this).stop().animate({
					left: $(this).data("data-left"),
					top: $(this).data("data-top")
				},
				250 + t,
				function() {
					_.removeClass("item-file-draging")
				}).animate({
					opacity: 0
				},
				150,
				function() {
					$(this).remove()
				}) : (a.stop().animate({
					opacity: 1
				},
				100), $(this).stop().animate({
					opacity: 0
				},
				200 + t,
				function() {
					$(this).remove()
				}))
			});
			var t = setTimeout(function() {
				$(".file-draging-box").remove()
			},
			400 + L * i);
			$(".file-draging-box").data("removeDelay", t)
		}
	},
	u = function() {
		var t = null,
		a = null,
		n = null,
		o = 0,
		s = 0,
		r = 0,
		l = 0,
		c = "",
		d = "bodymain";
		"desktop" == Config.pageApp && (d = "fileContiner");
		var p = $("." + d);
		p.die("mousedown").live("mousedown",
		function(e) {
			if (! ($(e.target).hasClass(d) && 20 > $(document).width() - e.pageX)) {
				if (r = $(".fileContiner").outerHeight(), l = p.outerHeight(), ui.isEdit()) return ! 0;
				if (1 != e.which || i) return ! 0;
				u(e),
				this.setCapture && this.setCapture(),
				$(document).unbind("mousemove").mousemove(function(e) {
					f(e)
				}),
				$(document).one("mouseup",
				function(e) {
					clearTimeout(c),
					c = false,
					m(e),
					this.releaseCapture && this.releaseCapture()
				})
			}
		});
		var u = function(i) {
			s = p.offset().left - p.scrollLeft(),
			o = p.offset().top - p.scrollTop(),
			"list_split" == G.user_config.list_type && (o += $(i.target).parents(".split_box").scrollTop()),
			$(i.target).parent().hasClass(Config.FileBoxClassName) || $(i.target).parent().parent().hasClass(Config.FileBoxClassName) || $(i.target).hasClass("fix") || (rightMenu.hidden(), i.ctrlKey || i.metaKey || i.shiftKey || ui.fileLight.clear(), $(i.target).hasClass("ico") || (t = i.pageX - s, a = i.pageY - o, c = setTimeout(function() {
				e = true,
				0 == $("#selContainer").length && $('<div id="selContainer"></div>').appendTo(Config.FileBoxSelector),
				n = $("#selContainer")
			},
			100)))
		},
		f = function(i) {
			if (!e) return ! 0;
			var o = i.pageX - p.offset().left + p.scrollLeft(),
			s = i.pageY - p.offset().top + p.scrollTop(),
			c = Math.abs(o - t),
			d = Math.abs(s - a);
			s > a && d > r - a && r > l && (d = r - a),
			n.css({
				left: Math.min(o, t) + 2,
				top: Math.min(s, a) + 2,
				width: c,
				height: d
			}),
			1e3 > ui.fileLight.fileListAll().length && h()
		},
		h = function() {
			for (var e = n.offset().left - p.offset().left + p.scrollLeft(), i = n.offset().top - p.offset().top + p.scrollTop(), t = e + n.width(), a = i + n.height(), o = ui.fileLight.fileListAll(), s = 0; o.length > s; s++) {
				var r = o[s],
				l = $(o[s]),
				c = l.parent().scrollTop(),
				d = r.offsetLeft,
				u = r.offsetTop - c,
				f = d + l.width(),
				h = u + l.height();
				if ("list_split" == G.user_config.list_type && (d += l.parents(".split_box")[0].offsetLeft, f = d + l.width()), t - e + f - d > Math.abs(e + t - (d + f)) && a - i + h - u > Math.abs(i + a - (u + h))) {
					if (!l.hasClass("selectDragTemp")) {
						if (l.hasClass("selectToggleClass")) continue;
						if (l.hasClass(Config.SelectClassName)) {
							l.removeClass(Config.SelectClassName).addClass("selectToggleClass"),
							ui.fileLight.resumeMenu(l);
							continue
						}
						l.addClass("selectDragTemp")
					}
				} else l.removeClass("selectDragTemp"),
				l.hasClass("selectToggleClass") && l.addClass(Config.SelectClassName).removeClass("selectToggleClass")
			}
		},
		m = function() {
			return e ? (h(), n.remove(), $(".selectDragTemp").addClass(Config.SelectClassName).removeClass("selectDragTemp"), $(".selectToggleClass").removeClass("selectToggleClass"), ui.fileLight.select(), e = false, t = null, a = null, undefined) : false
		}
	},
	f = function(e, i) {
		var t = $(".fileList_list_split .split_box.split_select");
		if (e) t = e.parents(".split_box");
		else if (0 != ui.fileLight.fileListSelect().length) {
			var a = ui.fileLight.fileListSelect().last();
			t = a.parents(".split_box")
		}
		return t.find(i)
	},
	h = {
		fileListAll: function(e) {
			return "list_split" != G.user_config.list_type ? ui.fileLight.fileListAll() : f(e, ".file")
		},
		fileListSelect: function(e) {
			return "list_split" != G.user_config.list_type ? ui.fileLight.fileListSelect() : f(e, ".file.select")
		}
	},
	m = function(e) {
		var i = h.fileListAll(),
		t = h.fileListSelect(),
		a = i.length - 1,
		n = 0,
		o = ui.getColfileNumberDesktop(),
		s = i.index(t.first()),
		r = i.index(t.last());
		switch (e) {
		case "pageup":
		case "up":
			n = 0 >= s || 0 == s % o ? s: s - 1;
			break;
		case "left":
			n = 0 >= s - o ? 0 : s - o;
			break;
		case "pagedown":
		case "down":
			n = r >= a || 0 == (r + 1) % o ? r: r + 1;
			break;
		case "right":
			n = r + o >= a ? a: r + o;
			break;
		default:
		}
		return i.eq(n)
	},
	v = function(e) {
		if ("desktop" == Config.pageApp) return m(e);
		var i = h.fileListAll(),
		t = h.fileListSelect(),
		a = i.length - 1,
		n = 0,
		o = ui.getRowfileNumber(),
		s = ui.getPagefileNumber(),
		r = i.index(t.first()),
		l = i.index(t.last());
		switch (e) {
		case "up":
			n = 0 >= r - o ? 0 : r - o,
			n = _(n, false);
			break;
		case "left":
			n = 0 >= r ? 0 : r - 1;
			break;
		case "down":
			n = l + o >= a ? a: l + o,
			n = _(n, true);
			break;
		case "right":
			n = l >= a ? l: l + 1;
			break;
		case "pageup":
			n = 0 >= r - s ? 0 : r - s,
			n = _(n, false);
			break;
		case "pagedown":
			n = l + s >= a ? a: l + s,
			n = _(n, true);
			break;
		default:
		}
		return i.eq(n)
	},
	_ = function(e, i) {
		for (var t = h.fileListAll(), a = t.eq(e), n = t.length; 0 != a.parents(".hidden").length;) {
			if (i ? e++:e--, 0 >= e || e >= n) return e;
			a = t.eq(e)
		}
		return e
	},
	g = function(e) {
		var i, t = h.fileListAll(),
		a = h.fileListSelect(),
		n = "",
		o = false;
		switch (e.indexOf("shift+") >= 0 && (o = true, e = e.replace("shift+", "")), e) {
		case "home":
			n = a.last(),
			i = t.first();
			break;
		case "end":
			n = a.first(),
			i = t.last();
			break;
		case "left":
			n = a.last(),
			i = v(e);
			break;
		case "up":
			n = a.last(),
			i = v(e);
			break;
		case "right":
			n = a.first(),
			i = v(e);
			break;
		case "down":
			n = a.first(),
			i = v(e);
			break;
		case "pageup":
			n = a.last(),
			i = v(e);
			break;
		case "pagedown":
			n = a.first(),
			i = v(e);
			break;
		case "all":
			i = t;
			break;
		default:
		}
		if (!y(e)) {
			if (o && "" != n) {
				var s = t.index(n),
				r = t.index(i);
				if (s > r) {
					var l = s;
					s = r,
					r = l
				}
				return x(s, r, t),
				undefined
			}
			b(i)
		}
	},
	b = function(e) {
		0 != e.length && (ui.fileLight.clear(), e.addClass(Config.SelectClassName), ui.fileLight.select(), ui.fileLight.setInView(), "list_split" == G.user_config.list_type && 1 == e.length && r($(ui.fileLight.fileListSelect()[0])))
	},
	y = function(e) {
		var i = $(ui.fileLight.fileListSelect()[0]);
		if ("icon" == G.user_config.list_type) return ! 1;
		switch (e) {
		case "left":
			if ("list" == G.user_config.list_type) if (1 == i.find(".children_more_cert.cert_open").length) i.find(".children_more_cert").removeClass("cert_open"),
			i.next().addClass("hidden");
			else {
				var t = i.parent(".children_list").prev(".file");
				b(t)
			} else if ("list_split" == G.user_config.list_type) {
				var t = i.parents(".split_box").prev().find(".select_split_parent");
				b(t)
			}
			break;
		case "right":
			if ("list" == G.user_config.list_type) 1 == i.find(".children_more_cert").length && (ui.fileContent.pathChildrenTree(i), i.find(".children_more_cert").addClass("cert_open"), i.next().removeClass("hidden"));
			else if ("list_split" == G.user_config.list_type) {
				var t = i.parents(".split_box").next().find(".file:eq(0)");
				b(t)
			}
			break;
		default:
			return ! 1
		}
		return ! 0
	},
	x = function(e, i, t) {
		if ("desktop" == Config.pageApp) return k(e, i, t);
		ui.fileLight.clear();
		for (var a = e; i >= a; a++) $(t[a]).addClass(Config.SelectClassName);
		ui.fileLight.select()
	},
	k = function(e, i, t) {
		var a = ui.getColfileNumberDesktop(),
		n = Math.ceil(h.fileListAll().length / a),
		e = {
			row: e % a,
			col: parseInt(e / a)
		},
		i = {
			row: i % a,
			col: parseInt(i / a)
		};
		if (i.row < e.row) {
			var o = i;
			i = e,
			e = o
		}
		var s = function(e, i) {
			var n = i * a + e;
			$(t[n]).addClass(Config.SelectClassName)
		};
		ui.fileLight.clear();
		for (var r = e.row; i.row >= r; r++) {
			var l = 0,
			c = n;
			r == e.row && (l = e.col),
			r == i.row && (c = i.col);
			for (var d = l; c >= d; d++) s(r, d)
		}
		ui.fileLight.select()
	};
	return {
		init: function() {
			a(),
			l(),
			p(),
			u()
		},
		isDraging: function() {
			return i
		},
		selectSplit: c,
		selectPos: g
	}
});
