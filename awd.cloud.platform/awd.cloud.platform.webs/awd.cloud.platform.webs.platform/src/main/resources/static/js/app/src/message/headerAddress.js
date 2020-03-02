define("app/src/message/headerAddress", [],
function() {
	var e = function() {
		$("#yarnball li a").die("click").live("click",
		function(e) {
			var t = $(this).attr("data-path");
			a(t),
			stopPP(e)
		}),
		$("#yarnball").die("click").live("click",
		function() {
			return $("#yarnball").css("display", "none"),
			$("#yarnball_input").css("display", "block"),
			$("#yarnball_input input").focus(),
			true
		});
		var e = $("#yarnball_input input");
		e.die("blur").live("blur",
		function() {
			a(e.val())
		}).keyEnter(function() {
			a(e.val())
		}),
		$(".header-right input").keyEnter(function() {
			core.search($(".header-right input").val(), G.this_path)
		}),
		$(".header-right input").bind("keyup focus",
		function() {
			ui.path.setSearchByStr($(this).val())
		}),
		$(".header-content a,.header-content button").click(function() {
			var e = $(this).attr("id");
			switch (e) {
			case "history_back":
				ui.path.history.back();
				break;
			case "history_next":
				ui.path.history.next();
				break;
			case "refresh":
				ui.f5(true, true),
				ui.tree.init();
				break;
			case "home":
				ui.path.list(G.myhome);
				break;
			case "fav":
				ui.path.pathOperate.fav({
					path:
					G.this_path,
					type: "folder",
					name: $("ul.yarnball li:last .title_name").html()
				});
				break;
			case "goto_father":
				o();
				break;
			case "setting":
				core.setting();
				break;
			case "search":
				core.search($(".header-right input").val(), G.this_path);
				break;
			default:
			}
			return ! 0
		})
	},
	t = function(e) {
		var t = G.this_path;
		n(G.this_path),
		$("#yarnball_input").css("display", "none"),
		$("#yarnball").css("display", "block");
		var a = function(e) {
			var t = '<li class="yarnlet first"><a title="@1@" data-path="@1@" style="z-index:{$2};"><span class="left-yarn"></span>{$3}</a></li>\n',
			i = '<li class="yarnlet "><a title="@1@" data-path="@1@" style="z-index:{$2};">{$3}</a></li>\n';
			e = e.replace(/\/+/g, "/");
			var n = e.split("/");
			"" == n[n.length - 1] && n.pop();
			var a = n[0] + "/",
			o = t.replace(/@1@/g, a),
			s = n[0],
			r = "";
			if (G.json_data.info && G.json_data.info.path_type && "" != n[0]) {
				var l = core.getPathIcon(G.json_data.info, G.json_data.info.name);
				r = '<span class="address_ico">' + core.iconSmall(l.icon) + "</span>",
				s = l.name
			}
			o = o.replace("{$2}", n.length),
			o = o.replace("{$3}", r + '<span class="title_name">' + htmlEncode(s) + "</span>");
			for (var c = o,
			d = 1,
			p = n.length - 1; n.length > d; d++, p--) a += htmlEncode(n[d]) + "/",
			o = i.replace(/@1@/g, a),
			o = o.replace("{$2}", p),
			o = o.replace("{$3}", '<span class="title_name">' + htmlEncode(n[d]) + "</span>"),
			c += o;
			return '<ul class="yarnball">' + c + "</ul>"
		};
		undefined == e && $("#yarnball").html(a(t)),
		i()
	},
	i = function() {
		$(".yarnball").stop(true, true);
		var e = $("#yarnball").innerWidth(),
		t = 0;
		$("#yarnball li a").each(function() {
			t += $(this).outerWidth() + parseInt($(this).css("margin-left")) + 5
		});
		var i = e - t;
		0 >= i ? $(".yarnball").css("width", t + "px").css("left", i + "px") : $(".yarnball").css({
			left: "0px",
			width: e + "px"
		})
	},
	n = function(e) {
		var t = $("#yarnball_input .path");
		if (undefined == e) {
			var i = t.val();
			return i = rtrim(core.pathClear(i)) + "/"
		}
		t.val(e)
	},
	a = function(e, i) {
		ui.path.list(e),
		t(i)
	},
	o = function() {
		var e = n();
		if ("/" == e || -1 == e.indexOf("/")) return Tips.tips("已经到根目录了!", "warning"),
		undefined;
		var i = core.pathFather(e);
		ui.path.list(i),
		t()
	};
	return {
		init: e,
		addressSet: t,
		resetWidth: i,
		gotoFather: o
	}
});
