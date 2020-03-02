define("app/src/app/page", [],
function() {
	var e, init = function() {
		e = location.hash.split("#", 2)[1],
		e || (e = "all"),
		a(e),
		$("ul.setting a").click(function() {
			e != $(this).attr("id") && (e = $(this).attr("id"), a(e))
		}),
		$(".box .list").live("hover",
		function() {
			$(this).addClass("listhover")
		},
		function() {
			$(this).toggleClass("listhover")
		}).live("click",
		function() {
			var e = "setting/set?key=" + type + "&v=" + value;
			$.ajax({
				url: e,
				dataType: "json",
				success: function(e) {
					Tips.tips(e.data, e.code)
				}
			})
		}),
		$(".create_app").die("click").live("click",
		function() {
			window.parent && window.parent.ui.path.pathOperate.appEdit("", "", "root_add")
		}),
		$(".app-list .app_li").die("click").live("click",
		function(e) {
			if ($(e.target).attr("action")) {
                var i = jsonDecode(base64Decode($(this).attr("data-app")));
				t = $(e.target).attr("action");
				switch (t) {
				case "preview":
					core.openApp(i);
					break;
				case "add":
					var n = "/";
					ShareData.frameTop("",
					function(e) {
						n = e.G.this_path
					});
					var o = urlEncode(n + i.name),
					s = "./app/user_app?action=add&path=" + o;
					$.ajax({
						url: s,
						dataType: "json",
						type: "POST",
						data: {
							data: urlEncode(jsonEncode(i))
						},
						error: core.ajaxError,
						success: function(e) {
							Tips.tips(e.data, e.code),
							e.code && ShareData.frameTop("",
							function(e) {
								e.ui.f5()
							})
						}
					});
					break;
				case "edit":
					ShareData.frameTop("",
					function(e) {
						e.ui.path.pathOperate.appEdit(i, "", "root_edit")
					});
					break;
				case "del":
					$.dialog({
						id:
						"dialog_app_remove",
						icon: "question",
						padding: 20,
						width: 200,
						lock: true,
						background: "#000",
						opacity: .3,
						content: "确认删除选中内容吗？",
						ok: function() {
							$.ajax({
								url: "./app/del?name=" + urlEncode(i.name),
								dataType: "json",
								error: core.ajaxError,
								success: function(e) {
									Tips.tips(e.data, e.code),
									e.code && a()
								}
							})
						},
						cancel: true
					});
					break;
				default:
				}
			}
		})
	},
	t = function(e) {
		var i = "",
		t = "<button type='button' class='btn btn-sm btn-default dropdown-toggle' data-toggle='dropdown'>					<span class='caret'></span>					<span class='sr-only'></span>				</button>				<ul class='dropdown-menu' role='menu'>					<li><a action='edit' href='javascript:;'>" + "编辑" + "</a></li>					<li><a action='del' href='javascript:;'>" + "删除" + "</a></li>				</ul>";
		G.is_root || (t = "");
		for (var a in e) if (e[a]) {
			var n = e[a].icon; - 1 == n.search(G.static_path) && "http" != n.substring(0, 4) && (n = G.static_path + "images/file_icon/icon_app/" + n),
			i += "<li class='app_li' data-app=" + base64Encode(jsonEncode(e[a])) + ">				<a action='preview' href='javascript:;' class='icon'><img action='preview' src='" + n + "'></a>				<p><span class='title'>" + e[a].name + "</span>				<span class='info'>" + e[a].desc + "</span></p>" + "<div class='btn-group'>				<button type='button' class='btn btn-sm btn-default' action='add'>" + "添加" + "</button>" + t + "</div><div style='clear:both;'></div></li>"
		}
		return i += "<div style='clear:both;'></div>"
	},
	reload = function(i) { (undefined == i || "" == i) && (i = e),
		$(".selected").removeClass("selected"),
		$("ul.setting a#" + i).addClass("selected"),
		$(".main").find(".h1").html("<i class='" + $(".selected i").attr("class") + "'></i>" + $(".selected").text());
		var a = $(".main .app-list");
		$.ajax({
			url: "./app/get?group=" + i,
			dataType: "json",
			beforeSend: function() {},
			success: function(e) {
				a.html(t(e.data)),
				$("body").scrollTop(0)
			}
		})
	};
	return {
		reload: reload,
		init: init
	}
});
