define("app/src/setting/system/system_member", [
	"./tpl/user.html.js",
	"./tpl/user_list.html.js",
	"./tpl/user_import.html.js",
	"./tpl/group_select.html.js",
	"./tpl/role_select.html.js",
	"./tpl/stop_app.html.js",
	"./tpl/stop_menu.html.js",
	],
function(e) {
	var t, _groupid;
	var loadList = function(groupid) {
		$.ajax({
			url: "./system_member/get?group_id="+$("span .group_id").html(),
			dataType: "json",
			success: function(result) {
				if(result.code){
					t = System.dataList(result, "data");
					for(var user in t){
						t[user].role=jQuery.parseJSON(t[user].role);
					}
					resetUserList(groupid);
					return ;
				}else{
					Tips.tips(result);
					return;
				}
			},
			error: function() {
				return false;
			}
		});
		return;
	};
	var resetUserList = function(groupid) {
		if("" == groupid || undefined == groupid){
			groupid = _groupid;
		}
		_groupid = groupid;
		var tmp = e("./tpl/user_list.html");
		var htmlcompile = template.compile(tmp);
		var html = htmlcompile({
			LNG: LNG,
			select_group: groupid,
			user_list: t,
			group_role_list: System.systemGroupRole.getList(),
			group_list: System.systemGroup.getList(),
			role_list: System.systemRole.getList()
		});
		$(".user_liser_content").html(html);
		$(".button_aciton_muti button").addClass("disabled");
		System.sizeUse($("#content_system_group .user_list_cell .space"));
	};
	var q=function(action, ids,value){
		if(ids!=undefined){
			if(typeof ids!="object"){
				ids = [ids];
			}
			var operators = {
					del: "确认删除用户？<br\/>删除后该用户目录会彻底删除",
					status_set: "",
					group_set: "确认更改所选用户的权限组？",
					group_reset: "您确认重置用户分组吗？",
					role_remove_from: "确定清空选用户所有角色吗？",
					role_add: ""
			};
			var resetGroup = function(){
				console.log("+++++++++++++++++++"+action);
				console.log("+++++++++++++++++++"+ids);
				console.log("+++++++++++++++++++"+value);
				
				$.ajax({
					url: "/system_groupApp/changeUserGroup",
					type:'POST',
					data:'userid='+ids+"&param="+value,
					success:function(result){
						Tips.tips(result.data);
						$.dialog.list.share_dialog && $.dialog.list.share_dialog.close();
						t = undefined;
						loadList(_groupid);
					}
					
				});
			}
			if("" == operators[action]){
				resetGroup();
			}else{
				$.dialog({
					id: "dialog_user_confirm",
					fixed: true,
					icon: "question",
					padding: 30,
					width: 250,
					lock: true,
					title: "",
					background: "#000",
					opacity: .2,
					content: operators[action],
					ok: function() {
						resetGroup();
					},
					cancel: true
				});
			}
		}
		
	}
	
	var o = function(action, ids, value) {
		if (undefined != ids) {
			if("object" != typeof ids){
				ids = [ids];
			}
			var operators = {
				del: "确认删除用户？<br\/>删除后该用户目录会彻底删除",
				status_set: "",
				group_set: "确认更改所选用户的权限组？",
				group_reset: "",
				role_remove_from: "确定清空选用户所有角色吗？",
				role_add: ""
			};
			var updateAction = function() {
				$.ajax({
					url: "./system_member/do_action?action=" + action,
					type: "POST",
					data: "user_id=" + jsonEncode(ids) + "&param=" + value,
					dataType: "json",
					beforeSend: function() {
						Tips.loading();
					},
					error: core.ajaxError,
					success: function(result) {
						Tips.close(result);
						$.dialog.list.share_dialog && $.dialog.list.share_dialog.close();
						t = undefined;
						loadList(_groupid);
					}
				})
			};
			if("" == operators[action]){
				updateAction();
			}else{
				$.dialog({
					id: "dialog_user_confirm",
					fixed: true,
					icon: "question",
					padding: 30,
					width: 250,
					lock: true,
					title: "",
					background: "#000",
					opacity: .2,
					content: operators[action],
					ok: function() {
						updateAction()
					},
					cancel: true
				});
			}
		}
	};
	var r = function(groupid) {
		var userinfo = {
			user_id: "",
			name: "",
			password: "123456",
			isadmin:"0",
			role: "",
			group: groupid,
			config: {
				size_max: "1.5",
				size_use: "0"
			}
		};
		userEdit(userinfo, true);
		$("#role_input").show();
		$("#group_input").show();
	};
	var l = function(groupid) {
		var userinfo = {
			user_id: "",
			name: "",
			password: "123456",
			isadmin:"0",
			role: "",
			group: groupid,
			config: {
				size_max: "1.5",
				size_use: "0"
			}
		};
		userEdit(userinfo);
		$("#role_input").show();
		$("#group_input").show();
	};

	var userEdit = function(userinfo, mutli) {
		var rolelist = System.systemRole.getList();
		var tmp = e("./tpl/user.html");
		if(mutli){
			tmp = e("./tpl/user_import.html");
		}
		var htmlcompile = template.compile(tmp);
		var html = htmlcompile({
			LNG: LNG,
			user_info: userinfo,
			role_list: rolelist,
			group_list:System.systemGroup.getList()
		});
		var userdialog = $.dialog({
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
		System.sizeUse($(".share_view_info"));
		$("#role").val(JSON.stringify(userinfo.role));
		$(".dlg_role_select").unbind("click").bind("click",function() {
			groupAction($("#role").val(),function(e) {
				$("#role").val(e);
			});
		});
		$(".input_line #name").textFocus();
		var actionurl = "./system_member/add";
		if(mutli){
			actionurl = "./system_member/add?isImport=1";
		}else{
			if("" == userinfo.name){
				$(".share_action .remove_button").hide() ;				
			}else{
				actionurl = "./system_member/add";
			}
       }

		$("#system_save").unbind("click").bind("click",function() {
			var imported = "close";
			var name = $("#name").val();
			var password = $("#password").val();
			var regPassword=/^[0-9a-zA-Z]+$/;
			var jh = $("input[name=jh]").val();
			var sfzh = $("input[name=sfzh]").val();
			var mhyh = $("#mhyh").val();
			if(name == "" || name == undefined){
				layer.alert('用户名不能为空', {
					icon: 5,
					title: "提示"
				});
			}else if (password.length!=0 && !regPassword.test(password)){
				layer.alert('密码只能为数字和字母', {
					icon: 5,
					title: "提示"
				});
			}else if (jh == "" || jh == undefined){
				if(mhyh != undefined ){
					save();
				}else{
				layer.alert('警号不能为空', {
					icon: 5,
					title: "提示"
				});
				}
			}else if(jh.length>6){
				layer.alert('警号长度不能超过6位', {
					icon: 5,
					title: "提示"
				});
			}else if (sfzh == "" || sfzh == undefined){
				layer.alert('身份证不能为空', {
					icon: 5,
					title: "提示"
				});
			}else {
				var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			    if(reg.test(sfzh) == false){
					layer.alert('输入的身份证号有误，请仔细核对！',{
						icon:5,
						skin:'layer-ext-moon'
					});
					$(".input_line #sfzh").val("");
			    }else{
					save(imported);
				}
			}
		});
		$(".select_drop_menu a").unbind("click").bind("click",function() {
			$(this).parent().parent().find("a").removeClass("selected");
			$(this).addClass("selected");
			$(".select_drop_menu .role_title").html($(this).html());
			$("#role").val($(this).attr("data-role-id"));
		});
		$(".remove_button").unbind("click").bind("click",function() {
			layer.confirm('您确定要删除'+"<span style='color: red;'>"+userinfo.name+"</span>吗？",{btn: ['确定', '取消'],title:"提示"}, function(){
				var id = userinfo.id;

				$.ajax({
					url:'/user/delete',
					type:'post',
					data:{"id":id},
					success:function(result){
						layer.alert('删除成功', {
							icon: 1,
							title: "提示"
						});
						userdialog.close();
						loadList(_groupid);
					}
				})
			})
			//o("del", userinfo.user_id, "");
		});
		$(".dlg_goto_path").unbind("click").bind("click",function() {
			System.openPath(userinfo);
		});
		$(".content_box input").keyEnter(function() {
			save(true);
		});
		$("#system_save_goon_add").unbind("click").bind("click",function() {
			var imported = "noChange";
			var name = $("#name").val();
			var password = $("#password").val();
			var regPassword=/^[0-9a-zA-Z]+$/;
			var jh = $("input[name=jh]").val();
			var sfzh = $("input[name=sfzh]").val();
			var mhyh = $("#mhyh").val();
				if(name == "" || name == undefined){
					layer.alert('用户名不能为空', {
						icon: 5,
						title: "提示"
					});
				}else if (password == "" || password == undefined){
					layer.alert('密码不能为空', {
						icon: 5,
						title: "提示"
					});
				}else if (password.length!=0 && !regPassword.test(password)){
					layer.alert('密码只能为数字和字母', {
						icon: 5,
						title: "提示"
					});
				}else if (jh == "" || jh == undefined){
					if(mhyh != undefined ){
						save();
					}else{
						layer.alert('警号不能为空', {
							icon: 5,
							title: "提示"
						});
					}
				}else if(jh.length>6){
					layer.alert('警号长度不能超过6位', {
						icon: 5,
						title: "提示"
					});
				}else if (sfzh == "" || sfzh == undefined){
					layer.alert('身份证不能为空', {
						icon: 5,
						title: "提示"
					});
				}else {
					var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
				    if(reg.test(sfzh) == false){
						layer.alert('输入的身份证号有误，请仔细核对！',{
							icon:5,
							skin:'layer-ext-moon'
						});
						$(".input_line #sfzh").val("");
				    }else{
						save(imported);
					}
				}
		});

		var save= function(imported) {
//			if(mutli){
//				imported = false;
//			}
			var data = {};
			
			$(".share_dialog .content_info [name]").each(function() {
				var value = urlEncode($(this).val());
				if("" != value){
					data[$(this).attr("name")] = value;
				}
			});
			if(data.glybz == undefined){
				data.glybz='0';
			}
			console.log(data)
			if(data.role.length < 7 && data.role != "null"){
				layer.alert('角色不能为空', {
					icon: 5,
					title: "提示"
				});
			}else {
				$.ajax({
					url: actionurl,
					data: data,
					type: "POST",
					dataType: "json",
					beforeSend: function() {
//						Tips.loading()
					},
					error: function(result){
						layer.alert(result.data, {
							icon: 5,
							title: "提示"
						});
					},
					success: function(result) {
						Tips.close(result);
						if(result.code=="true"){
							t = undefined;
							loadList(_groupid);
							if(mutli){
								userdialog.close();
							}else{
//								if("" != userinfo.name || 1 != imported){
//									console.log("role===》"+result.name);
//									userdialog.close()
//									//l(group);
//									//保存并继续添加
//								}else{
//									console.log("第二个else");
//									$(".input_line #name").val("").textFocus();
									if(imported=="close"){
										userdialog.close();
									}else{
										$(".input_line #name").val("").textFocus();
										$("#jh").val("");
										$("#sfzh").val("");
									}
//								}
								return;
							}
							return;
						}else{
							if(mutli){
								$("#name").val(result.info);
								return;
							}
						}
					}
				});
			}
		}
	};
	var groupAction = function(t, i) {
		var roletreedata = System.systemRole.getTreeList();
		var rolelistdata = System.systemRole.getList();
		var selectrole = jsonDecode(t);
		if($.isArray(selectrole)){
			selectrole = {};
		}
		var o = {
			view: {
				showLine: false,
				selectedMulti: false,
				dblClickExpand: false,
				addDiyDom: function(e, t) {
					var i = 12;
					var a = $("#" + e + " #" + t.tId + "_switch");
					var n = $("#" + e + " #" + t.tId + "_ico");
					n.before(a).after('<i class="font-icon group_select_box icon-sort"></>')
					.before('<span class="tree_icon button">' + core.iconSmall("groupGuest") + "</span>")
					.removeClass("ico_docu")
					.addClass("group_icon")
					.remove();
					if (t.level >= 1) {
						var o = "<span class='space' style='display:inline-block;width:" + i * t.level + "px'></span>";
						a.before(o);
					}
					$("#" + e + " #" + t.tId + "_a").attr("data_group_id", t.id);
				}
			},
			callback: {
				onClick: function(e, i, a) {
					if(!selectrole){
						selectrole = {};
					}
					$("#" + a.tId + "_a").hasClass("this") ? delete selectrole[a.id] : selectrole[a.id] = a.name;
					refreshgroup();
				}
			}
		};
		var treefresh = function() {
			var roletreeid = $("#user_role_select");
			$.fn.zTree.init(roletreeid, o, roletreedata);
			var roletree = $.fn.zTree.getZTreeObj("user_role_select");
			if(roletree){
				roletree.expandAll(true);
			}
			refreshgroup();
		};
		var l = function() {
			var tmp = e("./tpl/role_select.html");
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				LNG: LNG
			});
			$.dialog({
				id: "select_usre_group_dlg",
				title: "编辑角色",
				padding: "0",
				width: 540,
				lock: true,
				background: "#fff",
				opacity: .1,
				fixed: true,
				content: html,
				ok: function() {
					i(jsonEncode(selectrole));
				},
				cancel: true
			});
			treefresh();
		};
		var refreshgroup = function() {
			var html = "";
			$("#user_group_select .curSelectedNode").removeClass("curSelectedNode");
			$("#user_group_select a[data_group_id]").removeClass("this");
			for (var a in selectrole){
				if(rolelistdata[a]){
					$("#user_group_select a[data_group_id=" + a + "]").addClass("this");
					html += '<li class="group_self" group-id="' + a + '">' + '    <span class="title"><i class="font-icon icon-group"></i>' + rolelistdata[a] + "</span>" +'    <i class="font-icon icon-remove remove"></i></li>';
				}
			}
			$(".select_group_right").html(html);
		};
		var d = function() {
			$(".right_content .group_self .remove").die("click").live("click",function() {
				var e = $(this).parent().attr("group-id");
				delete selectrole[e];
				refreshgroup();
			});
			$(".group_self .dropdown-menu li").die("click").live("click",function() {
				var e = $(this).attr("data-info");
				var i = $(this).parent().attr("data-current");
				var a = $(this).parent().parent().parent().attr("group-id");
				if(i != e){
					selectrole[a] = e;
				}
			});
		};
		l();
		d();
	};
	var bindMenu = function() {
		$(".context-menu-list").filter(":visible").trigger("contextmenu:hide")
	};
	var bindTreeMenu = function() {
		$("body").click(bindMenu).contextmenu(bindMenu);
		$.contextMenu({
			zIndex: 9999,
			selector: ".user_action_menu",
			items: {
				user_list_edit: {
					name: "编辑",
					icon: "edit",
					accesskey: "e"
				},
				sep1: "--------",
				user_remove: {
					name: "删除",
					icon: "trash",
					accesskey: "d"
				},
				user_status_close: {
					name: "禁用",
					icon: "ban-circle",
					accesskey: "c"
				},
				user_status_open: {
					name: "启用",
					icon: "circle-blank",
					accesskey: "o"
				},
				sep2: "--------",
				role_remove_from: {
					name: "清空所有角色",
					icon: "remove-sign",
					accesskey: "g"
				},
				role_add: {
					name: "添加角色",
					icon: "plus",
					accesskey: "a"
				},
				group_reset: {
					name: "重置分组",
					icon: "spinner",
					accesskey: "i"
				}
			},
			callback: function(e, t) {
				var id = t.$trigger.attr("data-id");	
				$("#content_system_group .group_id").html();
				var user = $(this).text();
				console.log(user.match("管理员") != null)
				if(user.match("管理员") == null){
					var ids = [id];
					action(e, ids, "");
				}else{
					layer.alert('管理员账户不支持此操作', {
						icon: 5,
						title: "提示"
					});
				}
				
			}
		});
	};
	var bindAction = function() {
		$("#content_system_group .content [data-action]").live("click",function(e) {
			if (!$(e.target).is("input")) {
				var obj = $(this);
				var operate = obj.attr("data-action");
				var userids = [];
				$("#content_system_group .user_select:checked").each(function() {
					userids.push($(this).parent().parent().attr("data-id"))
				});
				if ("user_list_edit" == operate) {
					var id = obj.parent().parent().attr("data-id");
					userids = [id]
				}
				action(operate, userids, obj, e);
				return true;
			}
		});
	};
	var action= function(action, i, a, n) {
		var groupid = $("#content_system_group .group_id").html();
		switch (action) {
		case "user_add":
			l(groupid);
			break;
		case "user_import":
			r(groupid);
			break;
		case "role_remove_from":
			o("role_remove_from", i, "");
			break;
        case "role_add":
			groupAction(JSON.stringify(i),	function(e) {
			o("role_add", i, e);
			});
			break;
		case "group_reset":
			q("group_reset",i,"");
			break;
		case "group_set":
			var roleid = a.attr("data-group-id");
			o("group_set", i, roleid);
			break;
		case "user_status_open":
			o("status_set", i, 1);
			break;
		case "user_status_close":
			o("status_set", i, 0);
			break;
		case "user_app_close":
			forbidaction("app_close", i, "");
			break;
		case "user_appmenu_close":
			forbidaction("menu_close", i, "");
			break;
		case "user_zw":
			layer.alert('指纹版本过低，请联系管理员', {
				icon: 5,
				title: "提示"
			});
			break;
		case "user_remove":
			layer.confirm('您确定要删除'+"<span style='color: red;'>"+t[i[0]].name+"</span>吗？",{btn: ['确定', '取消'],title:"提示"}, function(){
				var id = t[i[0]].id;
				$.ajax({
					url:'/user/delete',
					type:'post',
					data:{"id":id},
					success:function(result){
						layer.alert('删除成功', {
							icon: 1,
							title: "提示"
						});
						loadList(_groupid);
					}
					
				})
			})
			break;
		case "user_finger":
			o("finger_set", i, "");
			break;	
		case "user_list_select":
			console.log(a)
			var userselect = a.find(".user_select");
			if(userselect.attr("checked")){
				userselect.removeAttr("checked");
			}else{
				userselect.attr("checked", "true");
			}
			enableAction();
			break;
		case "user_list_edit":
			userEdit(t[i[0]]);
			stopPP(n);
			$("#role_input").hide();
			$("#group_input").hide();
			break;
		default:
		}
	};
	
	var  forbidaction=function(action,userids,datas){
		var appTree,selectData,selectMenu;
		var applistData;
		var treeConfig = {
				view: {
					showLine: false,
					selectedMulti: false,
					dblClickExpand: true,
					addDiyDom: function(e, t) {
						var i = 12;
						var a = $("#" + e + " #" + t.tId + "_switch");
						var n = $("#" + e + " #" + t.tId + "_ico");
						if(t.level==0){
							n.before(a).before('<span class="tree_icon button"><i class="font-icon icon-archive"></i></span>').remove();
						}else if(t.level==1){
							n.before(a).before('<span class="tree_icon button"><i class="font-icon icon-folder-close"></i></span>').remove();
						}else if(t.level==2){
							n.before(a).before('<span class="tree_icon button"><i class="font-icon icon-folder-close-alt"></i></span>').remove();
						}else if(t.level==3){
							n.before(a).before('<span class="tree_icon button"><i class="font-icon icon-folder-open"></i></span>').remove();
						}else if(t.level==4){
							n.before(a).before('<span class="tree_icon button"><i class="font-icon icon-folder-open-alt"></i></span>').remove();
						}
						if ( t.level >= 1) {
							var o = "<span class='space' style='display: inline-block;width:" + i * t.level + "px'></span>";
							a.before(o);
						}
						$("#" + e + " #" + t.tId + "_a").addClass("menuGroup").attr("data_group_id", t.id);
					}
				},
				callback: {
					onClick: function(e, t, i) {
						treeClick(t, i.id);
					},
					beforeRightClick: function(e, t) {
						treeClick(e, t.id);
					}
				}
			}
		var treeClick = function(e, id) {
			var a = appTree.getNodeByParam("id", id, null);
			appTree.selectNode(a);
			if(a.isParent==false){
				if(!selectData){
					selectData = {};
				}
				$("#" + a.tId + "_a").hasClass("this") ? delete selectData[a.id] : selectData[a.id] = a.name;
			}
			refreshApp();
			
			if("group_parent_select" == e){
				$("#group_parent").val(i);
				$(".select_group").addClass("hidden");
			}
		}
		var listToTree = function(e) {
			var t = function(e) {
				for (var i = 0; e.length > i; i++){
					if(undefined != e[i]){
						e[i].pid = e[i].parent_id;
						e[i].id = e[i].id;
						delete e[i].children;
						delete e[i].parent_id;
//						delete e[i].id;
						if(e[i].child){
							e[i].children = e[i].child;
							delete e[i].child;
							t(e[i].children);
						}
					}else{
						delete e[i];
					}
				}
			};
			var i = [];
			var a = $.extend(true, {},e);
			for (var n in a) {
				var o = a[n],
				s = o.parent_id;
				if (a[s]) a[s].child || (a[s].child = []),
					a[s].child.push(a[o.id]);				
				else {
					var r = a[o.id];
					if(r){
						i.push(r);
					}
				}
			}
			t(i);
			return i;
		}
		var dialogtitle="";
		var tmphtml="";
		var getmues="0";
		if(userids.length>1){
			Tips.tips("只能选择一人！");
			return;
		}
		switch (action) {
		case "app_close":			
			tmphtml="./tpl/stop_app.html";
			dialogtitle="应用禁用";
			getmues="0";
			break;
		case "menu_close":
			tmphtml="./tpl/stop_menu.html";
			dialogtitle="菜单禁用";
			getmues="1";
			break;
		default:	
		}
		var tmp = e(tmphtml);
		var htmlcompile = template.compile(tmp);
		var html = htmlcompile({
			LNG: LNG
		});
		
		$.dialog({
			id: "select_usre_group_dlg",
			title: dialogtitle,
			padding: "0",
			width: 540,
			lock: true,
			background: "#fff",
			opacity: .1,
			fixed: true,
			content: html,
			ok: function() {
				if(getmues=="0"){
					forbidApp(userids,jsonEncode(selectData));
				}
				if(getmues=="1"){
					var data={};
					for(var key in selectData){  
						data[key]=applistData[key].parent_id;  
		            }
					forbidMenu(userids,jsonEncode(data));
				}
			},
			cancel: true
		});	
		
		$.ajax({
			url: "./app/getJsApp",
			data:{menu:getmues,userid:userids[0]},
			dataType: "json",
			error: function() {
				$("#js_app_select").html('<div style="text-align:center;">' + "系统错误" + "</div>")
			},
			success: function(e) {
				if(e.code){
					var listData = System.dataList(e, "data");
					var treeData = listToTree(listData);
					applistData=listData;
					$.fn.zTree.init($("#js_app_select"), treeConfig, treeData);
					appTree = $.fn.zTree.getZTreeObj("js_app_select");
					appTree.expandAll(true);
					appId='0';
					appTree.selectNode(appTree.getNodeByParam("id", appId, null));
					
					if(tmphtml=="./tpl/stop_app.html"){
						$.ajax({
							url: "./app/getforbidApp",
							dataType: "json",
							data:{userid:userids[0]},
							success: function(e) {
								if(e.code){
									var listData = System.dataList(e, "data");
									selectData=listData;
									refreshApp();
									return;
								}else{
									return;
								}
							}
						});
					}else{
						$.ajax({
							url: "./app/getforbidMenu",
							dataType: "json",
							data:{userid:userids[0]},
							success: function(e) {
								if(e.code){
									var listData = System.dataList(e, "data");
									if(!selectData){
										selectData = {};
									}
									for(var key in listData){  
										selectData[key]=applistData[key].name;  
						            }
									refreshApp();
									return;
								}else{
									return;
								}
							}
						});
					}
					return;
				}else{
					$("#js_app_select").html('<div style="text-align:center;">' + "系统错误" + "</div>");
					return;
				}
			}
		});
		
		var forbidApp=function(ids,data){
			$.ajax({
				url: "./system_member/do_action?action=forbidapp",
				type: "POST",
				data: "user_id=" + jsonEncode(ids) + "&param=" + data,
				dataType: "json",
				beforeSend: function() {
					Tips.loading();
				},
				error: core.ajaxError,
				success: function(result) {
					Tips.close(result);
					$.dialog.list.share_dialog && $.dialog.list.share_dialog.close();					
				}
			});
		}
		
		
		var forbidMenu=function(ids,data){			 			
			$.ajax({
				url: "./system_member/do_action?action=forbidmenu",
				type: "POST",
				data: "user_id=" + jsonEncode(ids) + "&param=" + data,
				dataType: "json",
				beforeSend: function() {
					Tips.loading();
				},
				error: core.ajaxError,
				success: function(result) {
					Tips.close(result);
					$.dialog.list.share_dialog && $.dialog.list.share_dialog.close();					
				}
			});
		}
		
		var refreshApp = function() {
			var html = "";
			$("#js_app_select .curSelectedNode").removeClass("curSelectedNode");
			$("#js_app_select a[data_group_id]").removeClass("this");
			for (var a in selectData){
				if(applistData[a]){
					html += '<li class="group_self" group-id="' + a + '">' + '    <span class="title"><i class="font-icon icon-archive"></i>' + applistData[a].name + "</span>" +'    <i class="font-icon icon-remove remove"></i></li>';
				}
			}
			$(".select_group_right").html(html);
		};
		
		var bindAction=function(){
			
			$(".right_content .group_self .remove").die("click").live("click",function() {
				var e = $(this).parent().attr("group-id");
				delete selectData[e];
				refreshApp();
			});
		}
		bindAction();
		
	};
	
	
	var enableAction = function() {
		if($("#content_system_group .user_select:checked").length >= 1){
			$(".button_aciton_muti button").removeClass("disabled");
		}else{
			$(".button_aciton_muti button").addClass("disabled");
		}
		$("#content_system_group .user_list_cell ").removeClass("selected");
		$("#content_system_group .user_select:checked").each(function() {
			$(this).parent().parent().addClass("selected");
		})
	};
	var bindUserSelect = function() {
		$("#content_system_group .user_select_set").live("click",function() {
			if($(this).attr("checked")){
				$("#content_system_group .user_select").attr("checked", "true");
			}else{
				$("#content_system_group .user_select").removeAttr("checked");
			}
			enableAction();
		});
		$("#content_system_group .user_select").live("click",function() {
			enableAction();
		});
	};
	
	bindUserSelect();
	bindAction();
	bindTreeMenu();
	
	return{
		resetUserList: resetUserList,
		resetList: function() {
			t = undefined
		},
		loadList: loadList,
		add: l
	}
});
