define("app/src/setting/system/system_appsetting", [
	"../base/jqlist.html.js",
	"../base/jq.html.js",
	"../base/jslist.html.js",
	"../base/js.html.js",
	"../base/barlist.html.js",
	"../base/bar.html.js",
	"../base/datawarelist.html.js",
	"../base/hjslist.html.js",
	"../base/hjs.html.js",
	"../base/lslist.html.js",
	"../base/ls.html.js",
	"./tpl/app_setting.html.js",	
	
],
function(e) {
	
	var appId,appData,appTree;	
	var dialog;
	var _init=function(){
		showtab("system_appset");
		tabchange();
		appTreeRefresh();
		basesettingBind();
	}
	var treeConfig = {
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
		appId=id;
		var a = appTree.getNodeByParam("id", id, null);
		appTree.selectNode(a);
		if(a.isParent==false){
			refreshAppSetting(id,a.name);
		}
		
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
	var appTreeRefresh=function(){
		$.ajax({
			url: "./app/getJsApp",
			dataType: "json",
			error: function() {
				$("#appTree").html('<div style="text-align:center;">' + "系统错误" + "</div>")
			},
			success: function(e) {
				if(e.code){
					var listData = System.dataList(e, "data");
					var treeData = listToTree(listData);
					$.fn.zTree.init($("#appTree"), treeConfig, treeData);
					appTree = $.fn.zTree.getZTreeObj("appTree");
					appTree.expandAll(false);
					appId='0';
					appTree.selectNode(appTree.getNodeByParam("id", appId, null));
					return;
				}else{
					$("#appTree").html('<div style="text-align:center;">' + "系统错误" + "</div>");
					return;
				}
			}
		});
	}
	var refreshAppSetting=function(id,name){
		$("#content_system_appset .app_title").html(name);
		$.ajax({
			url: "./app/getsetting",
			dataType: "json",
			data:{appcode:id},
			error: function() {
				$(".appset_liser_content").html('<div style="text-align:center;">' + "获取配置失败" + "</div>")
			},
			success: function(data) {
				if(data.code&&data.data&&data.data.allsetting.length!=0){
					var listData = System.dataList(data, "data");
					var tmp = e("./tpl/app_setting.html");		
					var gen=template.compile(tmp);
					var mainhtml = gen({
						allsetting:listData.allsetting,
						mysetting:listData.mysetting
					});
					$(".appset_liser_content").html(mainhtml);
					$(".appset_liser_content").fadeIn("fast");
					
					$(".app_setting_save_button").die("click").live("click",function() {
						var data = {};
						$(".app_setting input[name]").each(function() {
							var t = $(this);
							if ("radio" == t.attr("type")) {
								if(t.attr("checked")=="checked"){
									data[t.attr("name")] = t.val();
								}								
							} else {
								data[t.attr("name")] = urlEncode(t.val());
							}
						});
						sytermSave(id,data);
					});
					return;
				}else{
					$(".appset_liser_content").html('<div style="text-align:center;">' + "无配置项" + "</div>")
					return;
				}
			}
		});
		
		
	}
	var showtab = function(hash) {
	    $(".system_conennt .this").removeClass("this");
	    $(".system_conennt #" + hash).addClass("this");
	    $(".left_content").addClass("hidden");
	    $("." + hash).removeClass("hidden");
	    $(".right_frame").addClass("hidden");
	    $("#content_" + hash).removeClass("hidden");
	  };
	
	var getTemplate = function(html) {
			var template = {
				jqsz: e("./base/jqlist.html"),
				jssz: e("./base/jslist.html"),
				hjssz: e("./base/hjslist.html"),
				bar: e("./base/barlist.html"),
				lsgl: e("./base/lslist.html")
			};
			return template[html];
	}  
	  
	var basesettingBind=function(){		
		$('.system_baseset li').die("click").live("click",function(){		
			var name=$("span", this).html();
			var tempstr=$(this).attr("data-role-id");
			var temp= getTemplate(tempstr);
			var gen = template.compile(temp);
			var mainhtml = gen({
				
			});
			
			$(".basesetting_liser_content").html(mainhtml);
			$(".basesetting_title").html(name);
			refreshTable(tempstr);
		});
	}
	var refreshTable=function(id){
		switch(id){
		case 'jqsz':
			layui.use('table', function(){
				layui.table.render({
					id:'jq'
				    ,elem: '#'+id+'Table'
				    ,height: 400
				    ,url: '/system_jq/getPage' //数据接口
				    ,page: true //开启分页
				    ,response: {//后台返回的数据
				        countName: 'total' //数据总数的字段名称，默认:count
				    }
				    ,cols: [[ //表头
				      {checkbox: true,fixed: true}	
				      ,{field: 'jqh', title: '监区号', width:80 ,templet:function(d){return '<div><img src="'+d.jqicons+'" style="width:23px;height:23px;"/>'+d.jqh+'</div>';}}
				      ,{field: 'jqmc', title: '监区名称', width:100}
				      ,{field: 'jsnum', title: '监室数', width:80}
				    ]]
				
				  });
			});	
			break;
		case 'jssz':
			layui.use('table', function(){
				layui.table.render({
					id:'js'
				    ,elem: '#'+id+'Table'
				    ,height: 400
				    ,url: '/system_js/getPage' //数据接口
				    ,page: true //开启分页
				    ,response: {//后台返回的数据
				        countName: 'total' //数据总数的字段名称，默认:count
				    }
				    ,cols: [[ //表头
				      {checkbox: true,fixed: true}	
				      ,{field: 'jqh', title: '监区号', width:80}
				      ,{field: 'jsh', title: '监室号', width:80}
				      ,{field: 'jsmc', title: '监室名称', width:100}
				      ,{field: 'jslbString', title: '监室类别', width:100}
				      ,{field: 'typeString', title: '男女监', width:80}
				      ,{field: 'innum', title: '在押数', width:80}
				    ]]
				  });
			});	
			break;
		case 'hjssz':
			layui.use('table', function(){
				layui.table.render({
					id:'fjsz'
				    ,elem: '#'+id+'Table'
				    ,height: 400
				    ,url: '/system_fjsz/getPage' //数据接口
				    ,page: true //开启分页
				    ,response: {//后台返回的数据
				        countName: 'total' //数据总数的字段名称，默认:count
				    }
				    ,cols: [[ //表头
				      {checkbox: true,fixed: true}	
				      ,{field: 'fjhm', title: '房间号', width:80}
				      ,{field: 'fjmc', title: '房间名称', width:100}
				      ,{field: 'fjlxString', title: '房间类型', width:100}
				      ,{field: 'syztString', title: '使用状态', width:100}
				      ,{field: 'syry', title: '使用人员', width:100}
				    ]]
				  });
			});
			break;
		case 'bar':
			layui.use('table', function(){
				layui.table.render({
					id:'bar'
				    ,elem: '#'+id+'Table'
				    ,height: 400
				    ,url: '/system_bar/getPage' //数据接口
				    ,page: true //开启分页
				    ,response: {//后台返回的数据
				        countName: 'total' //数据总数的字段名称，默认:count
				    }
				    ,cols: [[ //表头
				      {checkbox: true,fixed: true}	
				      ,{field: 'sfzh', title: '身份证号', width:100}
				      ,{field: 'xm', title: '姓名', width:80}
				      ,{field: 'dwlxString', title: '单位类型', width:100}
				      ,{field: 'badw', title: '单位名称', width:100}
				      ,{field: 'zwString', title: '指纹是否采集', width:120}
				    ]]
				  });
			});
			break;
		case 'lsgl':
			layui.use('table', function(){
				layui.table.render({
					id:'ls'
				    ,elem: '#'+id+'Table'
				    ,height: 400
				    ,url: '/system_lsgl/getPage' //数据接口
				    ,page: true //开启分页
				    ,response: {//后台返回的数据
				        countName: 'total' //数据总数的字段名称，默认:count
				    }
				    ,cols: [[ //表头
				      {checkbox: true,fixed: true}	
				      ,{field: 'lszh', title: '律师证号', width:100}
				      ,{field: 'xm', title: '姓名', width:80}
				      ,{field: 'dw', title: '单位名称', width:100}
				      ,{field: 'zwString', title: '指纹是否采集', width:120}
				    ]]
				  });
			});
			break;			
		default:			
		}
		bindAction();
	}
	
	var bindAction=function(){
		//jqaction
		$("#content_system_baseset .user_tool_bar [data-action]").die("click").live("click",function() {
			var search=$('.user_tool_bar input[name="search"]').val();
			var obj = $(this);
			var operate = obj.attr("data-action");
			var table=obj.attr("data-table");
			var ids=[];
			var checkStatus = layui.table.checkStatus(table);
			if(checkStatus.data.length>0){
				$.each(checkStatus.data,function(key,value){  
					ids.push(checkStatus.data[key]);
				}) 
			}
			baseaction(operate,search,ids);
		});		
		
		
		
		
		$('.content_info input[name="jqh"]').die("change").live("change",function(){
			if($('.content_info input[name="jqmc"]')){
				$('.content_info input[name="jqmc"]').val("监区"+$('.content_info input[name="jqh"]').val());
			}
			if($('.content_info input[name="jsh"]')){
				$('.content_info input[name="jsh"]').val($('.content_info input[name="jqh"]').val());
			}				
		});
		
		$('.content_info input[name="jsh"]').die("change").live("change",function(){
			if($('.content_info input[name="jsmc"]')){
				$('.content_info input[name="jsmc"]').val("监室"+$('.content_info input[name="jsh"]').val());
			}				
		});
		
		$("#jq_save").die("click").live("click",function(){
			var flag=true;
			var data={};
			$('.content_box').find('.content_info').find('input,select,textarea').each(function() {
				var t = $(this);
				t[0].value=t[0].value.replace(" ",'');
				if(t[0].name == 'jqh'){
					if(t[0].value.length > 2 || t[0].value.length == 0){
						layer.alert('监区号的长度应在1到2之间', {
							icon: 5,
							title: "提示"
							});
						flag=false;
					}
				}else if(t[0].name == 'jqh'){
					
				}
				data[t.attr("name")] = t.val();
			});
			if(flag){
			saveAction("./system_jq/save",data,function(){
				layui.table.reload('jq',{where:{search:""}});
			});
			}
		});
		
		$("#js_save").die("click").live("click",function(){
			var flag=true;
			var data={};
			$('.content_box').find('.content_info').find('input,select,textarea').each(function() {
				var t = $(this);
				t[0].value=t[0].value.replace(" ",'');
				if(t[0].name == 'jsh'){
					if(t[0].value.length > 4 || t[0].value.length == 0){
						layer.alert('监室号的长度应在1到4之间', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}else if(t[0].name == 'jqh'){
					if(t[0].value.length > 2 || t[0].value.length == 0){
						layer.alert('监区号的长度应在1到2之间', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}else if(t[0].name == 'jsmc'){
					if(t[0].value.length == 0){
						layer.alert('请输入监室名称', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}
				data[t.attr("name")] = t.val();
			});
			if(flag){
			saveAction("./system_js/save",data,function(){
				layui.table.reload('js',{where:{search:""}});
			});
			}
		});
		
		$("#fjsz_save").die("click").live("click",function(){
			var data={};
			var flag=true;
			$('.content_box').find('.content_info').find('input,select,textarea').each(function() {
				var t = $(this);
				t[0].value=t[0].value.replace(" ",'');
				if(t[0].name == 'fjhm'){
					if(t[0].value.length > 4 || t[0].value.length == 0){
						layer.alert('房间号长度应在1到4之间', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}else if(t[0].name == 'fjmc'){
					if(t[0].value.length == 0){
						layer.alert('请输入房间名称', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}
				data[t.attr("name")] = t.val();
			});
			if(flag){
			saveAction("./system_fjsz/save",data,function(){
				layui.table.reload('fjsz',{where:{search:""}});
			});
			}
		});
		
		$("#bar_save").die("click").live("click",function(){
			var data={};
			var flag=true;
			$('.content_box').find('.content_info').find('input,select,textarea').each(function() {
				var t = $(this);
				t[0].value=t[0].value.replace(" ",'');
				if(t[0].name == 'sfzh'){
					if(t[0].value.length == 0){
						layer.alert('请输入身份证号', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}else if(t[0].name == 'xm'){
					if(t[0].value.length == 0){
						layer.alert('请输入姓名', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}else if(t[0].name == 'badw'){
					if(t[0].value.length == 0){
						layer.alert('请输入办案单位', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}
				data[t.attr("name")] = t.val();
			});
			if(flag){
			saveAction("./system_bar/save",data,function(){
				layui.table.reload('bar',{where:{search:""}});
			});
			}
		});
		$("#ls_save").die("click").live("click",function(){
			var data={};
			var flag=true;
			$('.content_box').find('.content_info').find('input,select,textarea').each(function() {
				var t = $(this);
				t[0].value=t[0].value.replace(" ",'');
				if(t[0].name == 'lszh'){
					if(t[0].value.length == 0){
						layer.alert('请输入律师份证号', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}else if(t[0].name == 'xm'){
					if(t[0].value.length == 0){
						layer.alert('请输入律师姓名', {
							icon: 5,
							title: "提示"
							});
						flag=false;
						return;
					}
				}
				data[t.attr("name")] = t.val();
			});
			if(flag){
			saveAction("./system_ls/save",data,function(){
				layui.table.reload('ls',{where:{search:""}});
			});
			}
		});
		
	}
	var saveAction=function(url,data,callback){
		$.ajax({
			url: url,
			data: data,
			type: "POST",
			dataType: "json",
			beforeSend: function() {
				Tips.loading()
			},
			error: function(){
				layer.alert('执行失败，请联系管理员', {
					icon: 5,
					title: "提示"
					});
			},
			success: function(result) {
				Tips.close(result.data);
				if(result.code=="true"){
					if(callback)callback();
					dialog.close();
					return;
				}
			}
		});
	}
	var baseaction=function(operate,search,ids){
		switch (operate) {
		case "jq_search":
			layui.table.reload('jq',{where:{search:search}});
			break;
		case "jq_add":
			showdialog('jq',{});
			break;	
		case "jq_edit":
			if(ids[0]){				
				var jq=ids[0];				
				showdialog('jq',jq);	
			}else{
				Tips.tips("错误，没有监区被选中！", "error");
			}
			break;
		case 'jq_delete':			
			if(ids[0]){
				layer.confirm('确认删除所选内容吗？',{btn:['确定','取消'],icon:2,title:"提示"},function(){
					layer.closeAll('dialog');
					deleteAction("./system_jq/delete",ids);
					setTimeout(function(){
						layui.table.reload('jq',{where:{search:search}});
					},1000)
				})
			}else{
				Tips.tips("错误，没有分类选中！", "error");
			}
			break;
		case "js_search":
			layui.table.reload('js',{where:{search:search}});
			break;
		case "js_add":
			showdialog('js',{});
			break;	
		case "js_edit":
			if(ids[0]){				
				var js=ids[0];				
				showdialog('js',js);	
			}else{
				Tips.tips("错误，没有监室被选中！", "error");
			}
			break;
		case 'js_delete':			
			if(ids[0]){
				layer.confirm('确认删除所选内容吗？',{btn:['确定','取消'],icon:2,title:"提示"},function(){
					layer.closeAll('dialog');
					deleteAction("./system_js/delete",ids);
					setTimeout(function(){
						layui.table.reload('js',{where:{search:search}});
					},1000)
				})
			}else{
				Tips.tips("错误，没有分类选中！", "error");
			}
			break;
			
		case "hjs_search":
			layui.table.reload('fjsz',{where:{search:search}});
			break;
		case "hjs_add":
			showdialog('fjsz',{});
			break;	
		case "hjs_edit":
			if(ids[0]){				
				var hjs=ids[0];				
				showdialog('fjsz',hjs);	
			}else{
				Tips.tips("错误，没有房间被选中！", "error");
			}
			break;
		case 'hjs_delete':			
			if(ids[0]){
				layer.confirm('确认删除所选内容吗？',{btn:['确定','取消'],icon:2,title:"提示"},function(){
					layer.closeAll('dialog');
					deleteAction("./system_fjsz/delete",ids);
					setTimeout(function(){
						layui.table.reload('fjsz',{where:{search:search}});
					},1000)
				})
			}else{
				Tips.tips("错误，没有房间选中！", "error");
			}
			break;
			
		case "bar_search":
			layui.table.reload('bar',{where:{search:search}});
			break;
		case "bar_add":
			showdialog('bar',{});
			break;	
		case "bar_edit":
			if(ids[0]){				
				var bar=ids[0];				
				showdialog('bar',bar);	
			}else{
				Tips.tips("错误，没有办案人被选中！", "error");
			}
			break;
		case 'bar_delete':			
			if(ids[0]){	
					layer.confirm('确认删除所选内容吗？',{btn:['确定','取消'],icon:2,title:"提示"},function(){
						layer.closeAll('dialog');
						deleteAction("./system_bar/delete",ids);
					setTimeout(function(){
						layui.table.reload('bar',{where:{search:search}});
					},1000)
				})
			}else{
				Tips.tips("错误，没有办案人选中！", "error");
			}
			break;
		case "ls_search":
			layui.table.reload('ls',{where:{search:search}});
			break;
		case "ls_add":
			showdialog('ls',{});
			break;	
		case "ls_edit":
			if(ids[0]){				
				var ls=ids[0];				
				showdialog('ls',ls);	
			}else{
				Tips.tips("错误，没有律师被选中！", "error");
			}
			break;
		case 'ls_delete':			
			if(ids[0]){
				layer.confirm('确认删除所选内容吗？',{btn:['确定','取消'],icon:2,title:"提示"},function(){
					layer.closeAll('dialog');
					deleteAction("./system_ls/delete",ids);
					setTimeout(function(){
						layui.table.reload('ls',{where:{search:search}});
					},1000)
				})
			}else{
				Tips.tips("错误，没有律师人选中！", "error");
			}
			break;	
		default:
			break;
		}
	}
	var deleteAction=function(url,objects,callback){
		var ids=[];
		$.each(objects,function(index,value,array){
			ids.push(value.id);
		});
		$.ajax({
			url: url,
			dataType: "json",
			type: "POST",
			data:{id:ids},
			success: function(e) {
				if(e.code=="true"){
					Tips.tips(e.data);
					if(callback)callback();
				}else{
					Tips.tips(e.data, "error");
				}
			},
			error: function(){
				layer.alert('删除失败，请联系管理员', {
					icon: 5,
					title: "提示"
					});
			}
		});
		
	}
	var showdialog=function(tmpname,data){
		if("jq"==tmpname){
			tmp= e("./base/jq.html");
			url="/system_jq/save";
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				info:data
			});
			dialog = $.dialog({
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
			
			bindAction();
		}
		if("js"==tmpname){
			tmp= e("./base/js.html");
			url="/system_js/save";
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				info:data
			});
			dialog = $.dialog({
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
			bindAction();
		}
		if("fjsz"==tmpname){
			tmp= e("./base/hjs.html");
			url="/system_fjsz/save";
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				info:data
			});
			dialog = $.dialog({
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
			bindAction();
		}
		if("bar"==tmpname){
			tmp= e("./base/bar.html");
			url="/system_bar/save";
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				info:data
			});
			dialog = $.dialog({
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
			bindAction();
		}
		if("ls"==tmpname){
			tmp= e("./base/ls.html");
			url="/system_ls/save";
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				info:data
			});
			dialog = $.dialog({
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
			bindAction();
		}
	}
	var tabchange = function() {
		$(".left_header .tab").die("click").live("click", function() {
		  var hash = $(this).attr("id");
		  showtab(hash);      
		});
	}  
	var _gotobase = function(base) {
		var tmp="";
		if("jq"==base){
			tmp=e("../base/jq.html");		
		}
		if("js"==base){
			tmp=e("../base/js.html");		
		}
		var htmlcompile = template.compile(tmp);
		var html = htmlcompile({
				
			}
		);
		$(".basesetting_liser_content").html(html);
		
	};
	var sytermSave = function(id,data) {
		$.ajax({
			url: "setting/user_setting",
			type: "POST",
			data: "access_token=" + G.access_token + "&data=" + urlEncode(jsonEncode(data))+"&appcode="+urlEncode(id),
			dataType: "json",
			success: function(data) {
				Tips.tips(data);
			}
		});
	};
	return {	
		init:_init,
		apptreeRefresh:appTreeRefresh
	}
});