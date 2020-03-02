define("app/src/message/fileLight", [],
function() {
	var e = $(),
	i = $(),
	init = function() {
		var e;
		e = "list_split" != G.user_config.list_type ? $(".bodymain .fileContiner .file") : $(".bodymain .fileContiner .split_select .file"),
		i = e,
		menuAction("clear"),
		n()
	},
	select = function() {
		var i = $(Config.SelectClass);
		e = i,
		i.length > 1 && setMenu(i),
		menuAction("menufile"),
		o(),
		menuResetMore()
	},
	n = function() {
		if (G.json_data && G.json_data.filelist) {
			var e = G.json_data.filelist.length + G.json_data.folderlist.length;
			$(".file_select_info .item_num").html(e + "个项目")
		}
	},
	o = function() {
		var i = "",
		t = 0,
		a = e;
		0 != a.length && (i = a.length + "个选中", a.each(function() {
			t += parseInt($(this).attr("data-size"))
		}), 0 != t && (i = i + " (" + core.fileSize(t) + ")")),
		$(".file_select_info .item_select").html(i)
	},
	setInView = function(i) {
		var t = e;
		if (undefined == i && t && t.length >= 1 && (i = $(t[t.length - 1])), undefined != i && !i.inScreen()) {
			var a = $(".bodymain");
			"list_split" == G.user_config.list_type && (a = i.parent());
			var n = i.offset().top - a.offset().top - a.height() / 2 + a.scrollTop();
			a.stop(true).animate({
				scrollTop: n
			},
			100)
		}
	},
	name = function(e) {
		return core.pathThis(path(e))
	},
	type = function(e) {
		return e.find(".ico").attr("filetype")
	},
	setMenu = function(e) {
		if (G.json_data.info) switch (G.json_data.info.path_type) {
		case G.AWD_USER_RECYCLE:
			return;
		case G.AWD_USER_FAV:
			return e.removeClass("menuFavPath").addClass("menuFavPathMore"),
			undefined;
		case G.AWD_USER_SHARE:
			if ( - 1 == trim(G.this_path, "/").search("/")) return e.removeClass("menuSharePath").addClass("menuSharePathMore"),
			undefined;
		case G.AWD_GROUP_ROOT_SELF:
		case G.AWD_GROUP_ROOT_ALL:
			return e.removeClass("menuGroupRoot").addClass("menuGroupRootMore"),
			undefined;
		default:
		}
		e.removeClass("menufile menufolder").addClass("menuMore"),
		menuAction()
	},
	resumeMenu = function(e) {
		var i = {
			fileBox: "menufile",
			folderBox: "menufolder",
			menuRecyclePath: "menuRecyclePath",
			menuSharePathMore: "menuSharePath",
			menuFavPathMore: "menuFavPath",
			menuGroupRootMore: "menuGroupRoot",
			menuDefault: "menuDefault"
		};
		e.removeClass("menuMore");
		for (var t in i) e.hasClass(t) && e.addClass(i[t]);
		menuAction()
	},
	getAllName = function() {
		var i = [];
		if (0 != e.length) return e.each(function() {
			i.push(path($(this)))
		}),
		i
	},
	clear = function() {
		if (0 != e.length) {
			var i = e;
			i.removeClass(Config.SelectClassName),
			i.each(function() {
				resumeMenu($(this))
			}),
			e = $(),
			menuAction(),
			o(),
			menuResetMore()
		}
	},
	menuAction = function() {
		0 == e.length ? ($(".drop-menu-action li").addClass("disabled"), $(".drop-menu-action #past").removeClass("disabled"), $(".drop-menu-action #info").removeClass("disabled")) : $(".drop-menu-action li").removeClass("disabled")
	},
	menuResetMore = function() {
		var i = ".close_item,.refresh,.newfile,.past,.info",
		t = ".open_ie",
		a = ".explorer,.createProject,.openProject",
		n = ".close_item,.newfile,.refresh,.past,.down,.copy,.cute,.remove,.more_action,.clone,.info,.zip,.zip_zip,.zip_tar,.zip_tgz",
		o = ".newfile,.cute,.past,.rname,.zip,.remove,.clone,.createLinkHome,.createLink,.createProject",
		s = $(".menuToolPath"),
		r = "disabled";
		s.find(".context-menu-item").addClass(r),
		0 == e.length ? s.find(i).removeClass(r) : 1 == e.length ? (s.find(".context-menu-item").removeClass(r), "folder" == type(e) ? s.find(t).addClass(r) : s.find(a).addClass(r)) : e.length > 1 && s.find(n).removeClass(r),
		G.json_data && G.json_data.info && G.json_data.info.can_upload === false && s.find(o).filter(":not(." + r + ")").addClass(r)
	},
	path = function(e, i) {
		return undefined == i && (i = "data-path"),
		undefined != e.attr("data-path-children") && (i = "data-path-children"),
		pathHashDecode(e.attr(i))
	};
	return {
		init: init,
		name: name,
		path: path,
		type: type,
		fileListSelect: function(i) {
			return i && (e = i), e
		},
		fileListAll: function(e) {
			return e && (i = e),i
		},
		select: select,
		setInView: setInView,
		setMenu: setMenu,
		menuResetMore: menuResetMore,
		resumeMenu: resumeMenu,
		getAllName: getAllName,
		clear: clear,
		menuAction: menuAction
	}
});
