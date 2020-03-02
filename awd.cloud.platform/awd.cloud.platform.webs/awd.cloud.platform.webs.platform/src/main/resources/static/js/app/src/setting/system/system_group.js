define("app/src/setting/system/system_group", [
	"./tpl/group.html.js"
	],
function(e) {
	var t, i, a, n, o = function() {
		p();
		c();
		$(".ztree .switch").die("mouseenter").live("mouseenter",function() {
			$(this).addClass("switch_hover")
		}).die("mouseleave").live("mouseleave",function() {
			$(this).removeClass("switch_hover")
		});
		$(".menuGroup").die("mouseenter").live("mouseenter",function() {
			$(this).addClass("hover")
		}).die("mouseleave").live("mouseleave",function() {
			$(this).removeClass("hover")
		});
		if(G.is_root){
			$("[data-action=group_home").addClass("hidden");
		}
	},
	s = {
		view: {
			showLine: false,
			selectedMulti: false,
			dblClickExpand: true,
			addDiyDom: function(e, t) {
				var i = 12;
				var a = $("#" + e + " #" + t.tId + "_switch");
				var n = $("#" + e + " #" + t.tId + "_ico");
				n.before(a).before('<span class="tree_icon button">' + core.iconSmall("groupGuest") + "</span>").remove();
				if ( t.level >= 1) {
					var o = "<span class='space' style='display: inline-block;width:" + i * t.level + "px'></span>";
					a.before(o);
				}
				$("#" + e + " #" + t.tId + "_a").addClass("menuGroup").append("<i class='sub_menu icon-reorder'><i>").attr("data_group_id", t.id);
			}
		},
		callback: {
			onClick: function(e, t, i) {
				r(t, i.id)
			},
			beforeRightClick: function(e, t) {
				r(e, t.id)
			}
		}	
	},
	r = function(e, i) {
		if ("folderList" == e) {
			n = i;
			var a = t.getNodeByParam("id", i, null);
			t.selectNode(a);
			g(i);
		} else{
			if("group_parent_select" == e){
				$("#group_parent").val(i);
				$(".select_group").addClass("hidden");
				m();
			}		 
		}
	},
	l = function(e) {
		var t = function(e) {
			for (var i = 0; e.length > i; i++){
				if(undefined != e[i]){
					e[i].pid = e[i].parent_id;
					e[i].id = e[i].group_id;
					delete e[i].children;
					delete e[i].parent_id;
					delete e[i].group_id;
					if(e[i].child){
						e[i].children = e[i].child;
						delete e[i].child;
						t(e[i].children);
					}
				}else{
					delete e[i];
				}
			}
		},
		i = [],
		a = $.extend(true, {},e);
		for (var n in a) {
			var o = a[n],
			s = o.parent_id;
			if (a[s]) a[s].child || (a[s].child = []),
				a[s].child.push(a[o.group_id]);				
			else {
				var r = a[o.group_id];
				if(r){
					i.push(r);
				}
			}
		}
		t(i);
		return i;
	},
	c = function() {
		$.ajax({
			url: "./system_group/get",
			dataType: "json",
			error: function() {
				$("#folderList").html('<div style="text-align:center;">' + "系统错误" + "</div>")
			},
			success: function(e) {
				if(e.code){
					a = System.dataList(e, "data");
					i = l(a);
					$.fn.zTree.init($("#folderList"), s, i);
					t = $.fn.zTree.getZTreeObj("folderList");
					t.expandAll(true);
					if(undefined == n){
						n = "1";
					}
					r("folderList", n);
					if(0 != $("#group_parent_select").length) {
						_();
					}
					return;
				}else{
					$("#folderList").html('<div style="text-align:center;">' + "系统错误" + "</div>");
					return;
				}
			}
		})
	},
	d = function() {
		$(".context-menu-list").filter(":visible").trigger("contextmenu:hide")
	},
	p = function() {
		$("body").click(d).contextmenu(d);
		$.contextMenu({
			zIndex: 9999,
			selector: ".menuGroup",
			items: {
				add_child: {
					name: "添加子分组",
					icon: "plus",
					accesskey: "u"
				},
				edit: {
					name: "编辑",
					icon: "edit",
					accesskey: "e"
				},
				app: {
					name: "应用管理",
					icon: "tint",
					accesskey: "o"
				},
				sep1: "--------",
				add_user: {
					name: "新建用户",
					icon: "user",
					accesskey: "g"
				},
				sep2: "--------",
				remove: {
					name: "删除组",
					icon: "remove-sign",
					accesskey: "r"
				}
			},
			callback: function(e, i) {
				var a = i.$trigger.attr("id");
				a = a.replace("_a", "");
				var n = t.getNodeByTId(a);
				switch (e) {
				case "add_child":
					var o = _getGroupInfo();
					o.parent_id = n.pid,
					v(o);
					break;
				case "edit":
					var o = _getGroupInfo(n.id);
					v(o);
					break;
				case "app":
					var o = _getGroupInfo(n.id);
					systemGroupRole.showBox(o);
					break;	
				case "add_user":
					System.systemMember.add(n.id);
					break;
				case "remove":
					u(n.id);
					break;
				default:
				}
			}
		});
		$(".sub_menu").die("click").live("click",function(e) {
			$(this).contextMenu({
				x: e.pageX,
				y: e.pageY
			})
		});
	},
	_getGroupInfo = function(e) {
		if(undefined == e){
			return {
				id:"",
				group_id: "",
				name: "",
				parent_id: "",
				children: "",
				config: {
					size_max: "0",
					size_use: ""
				},
				path: "",
				create_time: ""
			}
		}else{
			return a[e];
		}
	},
	//判断用户组中如果有用户则不允许删除
	u = function(e, i) {
		$.ajax({
			url:'./system_member/get?group_id='+e,
			type:'POST',
			success:function(result){
				var val = result.data;
				var keyList = [];
				for(var key in val){
					keyList.push(key)
				}
				if(keyList.length!=0){
					Tips.tips("当前组中存在用户，无法删除","error");
					return false;
				}else{
					var a = t.getSelectedNodes()[0],
					n = a.getParentNode(),
					o = "./system_group/del?group_id=" + e;
					$.ajax({
						url: o,
						type:'POST',
						success:function(){
							Tips.tips("删除成功!","success");
							setTimeout(function(){
								window.location.reload();
							},1000)
						}
					})
				}
			}
		});
	},
	h = function() {
		var e = 1073741824 * parseFloat($(".size_max_set input").val());
		var t = core.fileSize(e);
		if(0 == e || isNaN(e)){
			$(".size_max_set i").html("(GB) 0则不限制");
		}else{
			$(".size_max_set i").html(t);
		}
	},
	m = function() {
		var e = $("#group_parent_select");
		var t = $("#group_parent").val();
		e.find("a.menuGroup").removeClass("curSelectedNode");
		if ("" == t){
			$(".select_parent_content .group_title").html("is root");
			return false;
		}
		var i = _getGroupInfo(t);
		//$(".select_parent_content .group_title").html(i.name);
		e.find("a[data_group_id=" + t + "]").addClass("curSelectedNode");
		return 	true;
	},
	_ = function() {
		var e = $("#group_parent_select");
		$.fn.zTree.init(e, s, i);
		var t = $.fn.zTree.getZTreeObj("group_parent_select");
		if(t){
			t.expandAll(true);
		}
		if(m()){
			$(".select_parent_content .btn").unbind("click").bind("click",
					function() {
						$(".select_group").toggleClass("hidden")
					});
		}
	},
	v = function(t) {
		if(t.parent_id!="0"&&t.id==""){
			Tips.tips("不是根不能添加!", "error");
			return;
		}
		var tmp = e("./tpl/group.html");
		var htmlcompile = template.compile(tmp);
		var html = htmlcompile({
			LNG: LNG,
			groupInfo: t
		});
		var o = $.dialog({
			id: "share_dialog",
			simple: true,
			resize: false,
			width: 425,
			background: "#000",
			opacity: .1,
			title: "",
			padding: "0",
			fixed: true,
			lock: true,
			content: html
		});
		h();
		System.sizeUse($(".share_view_info"));
		_();
		$(".input_line #name").textFocus();
		var s = "./system_group/add";
		if ("" != t.name) {
			s = "./system_group/edit?id=" + t.id;
		}
		$("#system_save").unbind("click").bind("click",	function() {
			r();
		});
		$(".dlg_goto_path").unbind("click").bind("click",function() {
			System.openPath(t);
		});
		$(".remove_button").unbind("click").bind("click",function() {
			u(t.group_id,function() {
				o.close();
			})
		});
		$(".content_box input").keyEnter(function() {
			r(true);
		});
		$("#system_save_goon_add").unbind("click").bind("click",function() {
			r(true);
		});
		$(".user_setting_more_btn").unbind("click").bind("click",function() {
			$(".user_setting_more").toggleClass("hidden")
		});
		$(".select_path a.select_btn").unbind("click").bind("click",function() {
			var e = this;
			core.api.pathSelect({
				type: "folder",
				title: "请选择文件夹...",
				firstPath: $(".select_path input").val()
			},function(t) {
				$(e).parent().find("input").val(t);
			})
		});
		$(".select_path a.reset").unbind("click").bind("click",function() {
			$(this).parent().find("input").val("");
		});
		var r = function(e) {
			var i = "";
			$(".share_dialog .content_info input[name]").each(function() {
				var e = urlEncode($(this).val());
				if("" != e){
					i += "&" + $(this).attr("name") + "=" + e;
				}
			});
			$.ajax({
				url: s,
				data: i,
				type: "POST",
				dataType: "json",
				beforeSend: function() {
					Tips.loading()
				},
				error: core.ajaxError,
				success: function(i) {
					return Tips.close(i),
					i.code || "version_error" != i.info ? (i.code && (c(), "" != t.name || 1 != e ? o.close() : setTimeout(function() {
						$(".input_line #name").val("").textFocus()
					},
					200)), undefined) : ($.dialog({
						content: i.data,
						padding: "30px 25px",
						width: "300px",
						okVal: "了解更多",
						ok: function() {
							window.open(core.versionUpdateVip)
						}
					}), undefined)
				}
			});
		}
	},
	g = function(e) {
		var t = _getGroupInfo(e);
		if(t){
			$(".group_title .group_title_span").html(t.name);
			$(".group_size").html(t.config.size_use + "/" + t.config.size_max);
			$("#content_system_group .group_id").html(e);
			System.sizeUse($(".group_size"));
			System.systemMember.loadList(e);
			System.systemMember.resetList();
		}
		
	},
	bindHeadGroupAction = function() {
		$(".size_max_set input").live("input", h),
		$("#content_system_group .header_content [data-action]").live("click",function() {
			var action = $(this).attr("data-action");
			var groupid = $("#content_system_group .group_id").html();
			var group = _getGroupInfo(groupid);
			switch (action) {
			case "group_edit":
				v(group);
				break;
			case "group_home":
				System.openPath(group);
				break;
			case "group_add_child":
				var selectgroup = _getGroupInfo();
				selectgroup.parent_id = group.parent_id,
				v(selectgroup);
				break;
			default:
			}
		})
	};
	bindHeadGroupAction();
	return{
		init: o,
		getGroupInfo: _getGroupInfo,
		getListTree: function() {
			return i
		},
		getList: function() {
			return a
		}
	}
});
