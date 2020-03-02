define("app/src/setting/system/system_group_role", [
	"./tpl/group_app_setting.html.js",
	"./tpl/group_app_menu.html.js",
],
function(e) {
	var allApp,appTree,appID, groupMenu,appMenu,selectGroup;
	
	
	var _init = function(event) {
		if("function" == typeof event){
			event();
		}
	};
	
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
//					delete e[i].id;
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
	
	var refreshAppMenu=function(id,name){
		$.ajax({
			url: "./system_group/getGroupSetting",
			dataType: "json",
			type:'post',
			data:{group_id:selectGroup.id,appid:id},
			error: function() {
				$("#group_menu_set").html('<div style="text-align:center;">' + "系统错误" + "</div>")
			},
			success: function(data) {
				if(data.code){
					var listData = System.dataList(data, "data");
					var tmp = e("./tpl/group_app_menu.html");
					var htmlcompile = template.compile(tmp);
					var html = htmlcompile({
						LNG: LNG,
						group:selectGroup,
						allmenu: listData.allmenu,
						groupmenu: listData.groupmenu
					});
                    $("#group_menu_set").html(html);
                    $(".group_editor.app_setting input").die("change").live("change",function(){
                        if($(this).attr("checked")==undefined){
                            groupmenu_forbid(id,selectGroup.id,$(this).val(),"1");
                        }else{
                            groupmenu_forbid(id,selectGroup.id,$(this).val(),"2");
                        }
                    });
					return;
				}else{
					$("#group_menu_set").html('<div style="text-align:center;">' + "系统错误" + "</div>")
					return;
				}
			}
		});
	}
    
    var groupmenu_forbid=function(appid,groupid,menuid,typeid){
                $.ajax({
                        url: "/system_group/forbidmenu",
                        data: {app:appid,group:groupid,menu:menuid,type:typeid},
                        type: "POST",
                        dataType: "json",
                        beforeSend: function() {
                            Tips.loading()
                        },
                        error: core.ajaxError,
                        success: function(result) {
                            Tips.close(result);
                        }
                });
    }
	var appTreeRefresh=function(){
		$.ajax({
			url: "./app/getJsApp",
			dataType: "json",
			error: function() {
				$("#apptree").html('<div style="text-align:center;">' + "系统错误" + "</div>")
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
					
					$.ajax({
						url: "./system_groupApp/getGroupApp",
						dataType: "json",
						type: "POST",
						data:{groupid:selectGroup.id},
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
					return;
				}else{
					$("#apptree").html('<div style="text-align:center;">' + "系统错误" + "</div>");
					return;
				}
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
	
	var forbidApp=function(data){
		$.ajax({
			url: "./system_groupApp/saveGroupApp",
			type: "POST",
			data: "groupid=" + selectGroup.id + "&param=" + data,
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
	
	var _showBox = function(group) {
		var tmp = e("./tpl/group_app_setting.html");
		var htmlcompile = template.compile(tmp);
		selectGroup=group;
		var html = htmlcompile({
			LNG: LNG,
			group:group,
			groupMenuData: groupMenu,
			appMenuData: appMenu
		});
		var dialogtitle="用户组应用管理";
		$.dialog({
			id: "system_role_group_box",
			title: dialogtitle,
			padding: "0",
			width: 540,
			lock: true,
			background: "#fff",
			opacity: .1,
			fixed: true,
			content: html,
			ok: function() {
				var data = {};
				for(var k in selectData){
					var key = encodeURIComponent(k);
					data[key] = selectData[k]
				}
				forbidApp(jsonEncode(data));
			},
			cancel: true
		});
		appTreeRefresh();
		
		var removeApp=function(){
			$(".right_content .group_self .remove").die("click").live("click",function() {
				var e = $(this).parent().attr("group-id");
				delete selectData[e];
				refreshApp();
			});
		}
		removeApp();
	};

	return {
		init: _init,
		showBox: _showBox,
		getList: function() {
			return groupMenu;
		}
	}
});
