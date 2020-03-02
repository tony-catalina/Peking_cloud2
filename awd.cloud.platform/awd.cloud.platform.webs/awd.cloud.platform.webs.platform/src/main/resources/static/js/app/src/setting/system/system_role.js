define("app/src/setting/system/system_role", [
	"./tpl/roleflow.html.js"
],
function(require) {
	var e, roleid;
	var _init = function() {
		$.ajax({
			url: "system_role/get",
			dataType: "json",
			async: false,
			success: function(result) {
				if(result.code){
					e = result.data;
					refreshList();
					if(undefined == roleid){
						roleid = "0"
					}
					_setSelect(roleid);
					return ;
				}else{
					Tips.tips(result);
					return ;
				}
				 
			}
		});
	};
	var refreshList = function() {
        var html = "";
        var jslx={'1':'看守所','2':'拘留所','3':'戒毒所','4':'收教所','5':'安康医院','0':'监管总队','9':'其他'};
		$.each(e,function(e, i) {
			html += '<li class="role_cell" data-role-id="' + e + '">' + "<span>" + i.name + '</span><i class="sub_menu icon-angle-right"></i></li>';
		});
		$(".role_list_cell").html(html);
	};
	var _setSelect = function(i) {
		var a;
		var t = i;
		$(".system_role li.role_cell").removeClass("select");
		$(".system_role [data-role-id=" + i + "]").addClass("select");
		$("#content_system_role [data-action=role_delete]").show();
		$("#content_system_role .group_title .label-info").show();
		if("0" == i){
			a = {
					name: ""
				};
			$("#content_system_role [data-action=role_delete]").hide(); 
			$("#content_system_role .group_title .label-info").hide(); 
			$("#content_system_role .role_title").html("添加角色身份");
		}else{
			a = e[i];
			$("#content_system_role .role_title").html(a.name);
			$("#content_system_role .role_id").html(i);			
			$.ajax({
				url: "system_role/roleflow",
				data:{role:i},
				dataType: "json",
				async: false,
				success: function(result) {
					if(result.code){
						//e = result.data;
						var tmp = require("./tpl/roleflow.html");		
						var gen=template.compile(tmp);
						var mainhtml = gen({
							roles:e,
							rolecode:i,
							allflow:result.data.allflow,
							allnode:result.data.allnode
						});
						$("#flow").html(mainhtml);
						$("#roleflow_save").die("click").live("click",roleflowSaveAction);
						return ;
					}else{
						Tips.tips(result);
						return ;
					}
					 
				}
			});
		}
		$(".group_editor #name").val(a.name).textFocus()
	};
	var roleflowSaveAction=function(){
		var data={};
		var ids=[]
		$(".group_editor").find("input[checked='checked']").each(function(){
			ids.push($(this).val());
		});
		data={role:roleid,data,ids};
		console.log(data);
		$.ajax({
			url: "system_role/roleNodeSet",
			data:data,
			type:"post",
			dataType: "json",
			async: false,
			success: function(result) {
				if(result.code){
					Tips.tips(result.data);
					return ;
				}else{
					Tips.tips(result.data);
					return ;
				}
				 
			}
		});
		
	}
	var revert_all = function() {
		$(".group_editor .tag").each(function() {
			if($(this).hasClass("this")){
				$(this).removeClass("this");
				$(this).find("input").removeAttr("checked");
			}else{
				$(this).addClass("this");
				$(this).find("input").attr("checked", true);
			}
			if(!$(".group_editor .combox:eq(0) .tag:eq(0)").hasClass("this")){
				$(".group_editor .combox:eq(0) .tag").removeClass("this");
				$(".group_editor .combox:eq(0) .tag").find("input").removeAttr("checked");
			}
			if(!$(".group_editor .combox:eq(1) .tag:eq(0)").hasClass("this")){
				$(".group_editor .combox:eq(1) .tag").removeClass("this");
				$(".group_editor .combox:eq(1) .tag").find("input").removeAttr("checked");
			}
		})
	};
	var bindEvent = function() {
		$(".group_editor .tag").live("click",function() {
			var e = $(this);
			var select = false;
			e.toggleClass("this");
			if(e.hasClass("this")){
				select = true;
				e.find("input").attr("checked", true);
			}else{
				select = false;
				e.find("input").removeAttr("checked");
			}
			if ( e.parent().hasClass("combox")) {
				var t = e.index();
				1 == t && 0 == select && (e.parent().find(".tag").removeClass("this"), e.parent().find("input").removeAttr("checked")),
				1 != t && 1 == select && (e.parent().find(".tag:eq(0)").addClass("this"), e.parent().find("input:eq(0)").attr("checked", true))
			}
		});
		$(".system_role li.role_cell").live("click",function() {
			roleid=$(this).attr("data-role-id");
			_setSelect($(this).attr("data-role-id"));
		});
		$("#content_system_role [data-action]").live("click",function(e) {
			var i = $(this).attr("data-action");
			switch ($(this), i) {
			case "revert_all":
				revert_all();
				break;
			default:
			}
			stopPP(e);
		});
	};
	var _getTreeList=function(){
		console.log(e);
		return listToTree(e);
	}
	
	var listToTree = function(data) {
		var children=[];
		
		for(key in data){  
//		    alert(key + data[key]); 
		    var child={};
			child.id=data[key].code;
			child.name=data[key].name;
			child.pid="0";
			children.push(child);
		}  
		var tree = [];
		var root={id:"0",name:"全部角色",pid:"0",children:children}
		tree.push(root);
		return tree;
	}

	var _getList = function() {
		var list = {};
		$.each(e,function(e, i) {
			list[e] = i.name;
		});
		return list;
	};
	bindEvent();
	return {
		init: _init,
		getList: _getList,
		getTreeList:_getTreeList,
		setSelect: _setSelect
	}
});
