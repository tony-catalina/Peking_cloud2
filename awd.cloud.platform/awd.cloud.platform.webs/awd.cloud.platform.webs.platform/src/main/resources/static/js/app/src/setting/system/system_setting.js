define("app/src/setting/system/system_setting", [
	"./tpl/role_list.html.js",
	"./tpl/xxdm.html.js",
	"./tpl/wgzd.html.js",
	"./tpl/role.html.js",
	"./tpl/classfic.html.js",
	"./tpl/flowmap_list.html.js",
	"./tpl/flownode_list.html.js"
],
function(require) {
	var xxdmTreeId,xxdmTree,xxdmlist,rolelist,wgzdlist,wgzdTreeId,wgzdTree,classficTree,classficTreeId,flowmapTree,flowmapTreeId;
	var url,dialog;
	var init = function() {	
		seajs.use("lib/layui/css/layui.css");
		$("input[name='first_in']").live("click",function() {
			$("input[name='first_in']").removeAttr("checked");
			$(this).attr("checked", "checked");
		});
		$(".system_save").die("click").live("click",function() {
			var data = {};
			$(".system_setting .form_row [name]").each(function() {
				var t = $(this);
				if ("checkbox" == t.attr("type")) {
					var i = undefined == t.attr("checked") ? "0": "1";
					data[t.attr("name")] = i
				} else {
					if("radius" != t.attr("type")) {
						data[t.attr("name")] = urlEncode(t.val());
					}
				}
			});
			data.first_in = $("input[name='first_in'][checked]").val();
			sytermSave(data);
		});		
		bindMenu();
		bindDictionaryAction();
		bindWgzdAction();
		bindRoleAction();
        bindClassficAction();
        bindFlowmapAction();
        bindFlownodeAction();
	};
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
		if ("xxdmList" == e) {
			xxdmTreeId = id;
			var a = xxdmTree.getNodeByParam("id", id, null);
			xxdmTree.selectNode(a);	
			refreshXxdm(id,'');
		}else if ("wgzdList" == e) {
			wgzdTreeId = id;
			var a = wgzdTree.getNodeByParam("id", id, null);
			wgzdTree.selectNode(a);
			refreshWgzd(id,'');
		}else if ("classficTree" == e) {
			classficTreeId = id;
			var a = classficTree.getNodeByParam("id", id, null);
			classficTree.selectNode(a);
			refreshClassfic(id,'');
		}else if ("flowmapTree" == e) {
			flowmapTreeId = id;
			var a = flowmapTree.getNodeByParam("id", id, null);
			flowmapTree.selectNode(a);
			refreshFlownode(id,'');
		} else{
			if("group_parent_select" == e){
				$("#group_parent").val(i);
				$(".select_group").addClass("hidden");
			}		 
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
	var bindClassficTree=function(){
		$.ajax({
			url: "./system_classfic/getAll",
			dataType: "json",
			error: function() {
				$("#classficTree").html('<div style="text-align:center;">' + "系统错误" + "</div>")
			},
			success: function(e) {
				if(e.code){
					var listData = System.dataList(e, "data");
					var treeData = listToTree(listData);
					$.fn.zTree.init($("#classficTree"), treeConfig, treeData);
					classficTree = $.fn.zTree.getZTreeObj("classficTree");
					classficTree.expandAll(false);
					classficTreeId='0';
					classficTree.selectNode(classficTree.getNodeByParam("id", classficTreeId, null));
					return;
				}else{
					$("#classficTree").html('<div style="text-align:center;">' + "系统错误" + "</div>");
					return;
				}
			}
		});
		
		layui.use('table', function(){
			layui.table.render({
				id:'classfic'
			    ,elem: '#classficTable'
			    ,height: 400
			    ,url: '/system_classfic/getPage' //数据接口
			    ,page: false //开启分页
			    ,cols: [[ //表头
			      {checkbox: true,fixed: true}	
			      ,{field: 'parentid', title: '父类', width:40}
			      ,{field: 'classid', title: '编号', width:80}
			      ,{field: 'name', title: '名称', width:180, sort: true}
			    ]]
			  });
		});
	}
	var bindDicationaryTree=function(){
		//获取字典类型
		$.ajax({
			url: "./system_dictionary/getType",
			dataType: "json",
			error: function() {
				$("#xxdmList").html('<div style="text-align:center;">' + "系统错误" + "</div>")
			},
			success: function(e) {
				if(e.code){
					var xxdmlistData = System.dataList(e, "data");
					var xxdmTreeData = listToTree(xxdmlistData);
					$.fn.zTree.init($("#xxdmList"), treeConfig, xxdmTreeData);
					xxdmTree = $.fn.zTree.getZTreeObj("xxdmList");
					xxdmTree.expandAll(false);
					xxdmTreeId='0';
					xxdmTree.selectNode(xxdmTree.getNodeByParam("id", xxdmTreeId, null));
					return;
				}else{
					$("#xxdmList").html('<div style="text-align:center;">' + "系统错误" + "</div>");
					return;
				}
			}
		});
		layui.use('table', function(){
			layui.table.render({
				id:'xxdm'
			    ,elem: '#xxdmTableRow'
			    ,height: 400
			    ,url: '/system_dictionary/getPage' //数据接口
			    ,page: false //开启分页
			    ,cols: [[ //表头
			      {checkbox: true,fixed: true}	
			      ,{field: 'jslxString', title: '监所类型', width:80}
			      ,{field: 'fieldnameString', title: '字典类型', width:80}
			      ,{field: 'code', title: '代码', width:80, sort: true}
			      ,{field: 'content', title: '内容', width:80} 
			      ,{field: 'editableString', title: '可编辑', width: 80}
			    ]]
			  });
		});
	}
	var bindWgzdTree=function(){
		//获取字典类型
		$.ajax({
			url: "./system_wgzd/getType",
			dataType: "json",
			error: function() {
				$("#wgzdList").html('<div style="text-align:center;">' + "系统错误" + "</div>")
			},
			success: function(e) {
				if(e.code){
					var wgzdlistData = System.dataList(e, "data");
					var wgzdTreeData = listToTree(wgzdlistData);
					$.fn.zTree.init($("#wgzdList"), treeConfig, wgzdTreeData);
					wgzdTree = $.fn.zTree.getZTreeObj("wgzdList");
					wgzdTree.expandAll(false);
					wgzdTreeId='0';
					wgzdTree.selectNode(wgzdTree.getNodeByParam("id", wgzdTreeId, null));
					return;
				}else{
					$("#wgzdList").html('<div style="text-align:center;">' + "系统错误" + "</div>");
					return;
				}
			}
		});
		layui.use('table', function(){
			var table = layui.table;
			layui.table.render({
				id:'wgzd'
			    ,elem: '#wgzdTableRow'
			    ,height: 400
			    ,url: '/system_wgzd/getPage' //数据接口
			    ,page: false //开启分页
			    ,cols: [[ //表头
			      {checkbox: true,fixed: true}	
			      ,{field: 'jslxString', title: '监所类型', width:80}
			      ,{field: 'code', title: '代码', width:80, sort: true}
			      ,{field: 'content', title: '内容', width:80} 
			      ,{field: 'kffz', title: '扣分分值', width: 80}
			      ,{field: 'editableString', title: '可编辑', width: 80}
			    ]],
			  });
		});
	}
	var bindDictionaryAction=function(){
		$('#content_system_xxdm .user_tool_bar [data-action]').die("click").live("click",function(){
			var search=$('.user_tool_bar input[name="xxdmseach"]').val();
			var obj = $(this);
			var operate = obj.attr("data-action");
			var xxmdIds=[];
			var checkStatus = layui.table.checkStatus('xxdm');
			if(checkStatus.data.length>0){
				$.each(checkStatus.data,function(key,value){  
					xxmdIds.push(checkStatus.data[key]);
				}) 
			}			
			xxdmAction(operate,xxdmTreeId,search,xxmdIds);
		});
		$(".select_drop_menu a").die("click").live("click",function() {
			$(this).parent().parent().find("a").removeClass("selected");
			$(this).addClass("selected");
			$(".select_drop_menu .xxdm_title").html($(this).html());
			$("#jslx").val($(this).attr("data-role-id"));
		});
		
		$("#system_xxdm_save").die("click").live("click",function() {
			var data={};
			$(".share_dialog .content_info [name]").each(function() {
				var value = urlEncode($(this).val());
				if("" != value){
					data[$(this).attr("name")] = value;
				}
			});	$.ajax({
				url: url,
				data: data,
				type: "POST",
				dataType: "json",
				beforeSend: function() {
					//Tips.loading()
				},
				error: core.ajaxError,
				success: function(result) {
					Tips.tips("成功","success");
					//Tips.close(result);
					if(result.code){
						refreshXxdm(xxdmTreeId,'');
						dialog.close();
						return;
					}
				}
			});
		});		
				 
	}
	var bindWgzdAction =function(){
		$('#content_system_wgzd .user_tool_bar [data-action]').die("click").live("click",function(){
			var search=$('.user_tool_bar input[name="wgzdseach"]').val();
			var obj = $(this);
			var operate = obj.attr("data-action");
			var ids=[];
			var checkStatus = layui.table.checkStatus('wgzd');
			if(checkStatus.data.length>0){
				$.each(checkStatus.data,function(key,value){ 
					ids.push(checkStatus.data[key]);
				}) 
			}	
			wgzdAction(operate,wgzdTreeId,search,ids);
		});
		$("#system_wgzd_save").die("click").live("click",function() {
			var data={};
			$(".share_dialog .content_info [name]").each(function() {
				var value = urlEncode($(this).val());
				if("" != value){
					data[$(this).attr("name")] = value;
				}
			});
			addorUpdateWgzd(data);
		});
	}
	var bindClassficAction=function(){
		$('#content_system_classfic .user_tool_bar [data-action]').die("click").live("click",function(){
			var search=$('.user_tool_bar input[name="classficseach"]').val();
			var obj = $(this);
			var operate = obj.attr("data-action");
			var classIds=[];
			var checkStatus = layui.table.checkStatus('classfic');
			if(checkStatus.data.length>0){
				$.each(checkStatus.data,function(key,value){  
					classIds.push(checkStatus.data[key]);
				}) 
			}			
			classficAction(operate,classficTreeId,search,classIds);
		});
		$("#system_classfic_save").die("click").live("click",function() {
			var data={};
			$(".share_dialog .content_info [name]").each(function() {
				var value = urlEncode($(this).val());
				if("" != value){
					data[$(this).attr("name")] = value;
				}
			});
			
			$.ajax({
				url: url,
				data: data,
				type: "POST",
				dataType: "json",
				beforeSend: function() {
//					Tips.loading()
				},
				error: core.ajaxError,
				success: function(result) {
					Tips.tips("成功","success");
//					Tips.close(result);
					if(result.code){
						refreshClassfic(classficTreeId,'');
						dialog.close();
						return;
					}
				}
			});
		});	
	}
	
	var bindFlowmapAction=function(action,search,ids){
		//获取字典类型
		layui.use('table', function(){
			layui.table.render({
				id:'flowmap'
			    ,elem: '#flowmapTableRow'
			    ,height: 400
			    ,url: '/system_flowmap/getPage?search=' //数据接口
			    ,page: false //开启分页
			    ,cols: [[ //表头
			      {checkbox: true,fixed: false}	
			      ,{field: 'mapkey', title: '代码', width:80}
//			      ,{field: 'mapname', title: 'ID', width:80}
			      ,{field: 'memo', title: '流程名称', width:80, sort: true} 
			      ,{field: 'version', title: '版本号', width: 80}
			      ,{field: 'mapmutex', title: '互斥流程', width: 80}
			      ,{field: 'timelimit', title: '时间限制', width: 80}
			      ,{field: 'monthlylimit', title: '月次数限制', width: 80}
			    ]]
			  });
		});
		$('#content_system_flow .user_tool_bar [data-action]').die("click").live("click",function(){
			var search=$('.user_tool_bar input[name="flowmapseach"]').val();
			var obj = $(this);
			var operate = obj.attr("data-action");
			flowmapAction(operate,search);
		});
	}
	var bindFlownodeAction=function(action,treeid,search,ids){
		$.ajax({
			url: "./system_flowmap/getAllTree",
			dataType: "json",
			error: function() {
				$("#flowmapTree").html('<div style="text-align:center;">' + "系统错误" + "</div>")
			},
			success: function(e) {
				if(e.code){
					var listData = System.dataList(e, "data");
					var treeData = listToTree(listData);
					$.fn.zTree.init($("#flowmapTree"), treeConfig, treeData);
					flowmapTree = $.fn.zTree.getZTreeObj("flowmapTree");
					if(flowmapTree!=null){
						flowmapTree.expandAll(true);
						flowmapTreeId='0';
						flowmapTree.selectNode(flowmapTree.getNodeByParam("id", flowmapTreeId, null));
					}
					
					return;
				}else{
					$("#flowmapTree").html('<div style="text-align:center;">' + "系统错误" + "</div>");
					return;
				}
			}
		});
		
		layui.use('table', function(){
			layui.table.render({
				id:'flownode'
			    ,elem: '#flownodeTableRow'
			    ,height: 400
			    ,url: '/system_flownode/getPage' //数据接口
			    ,page: false //开启分页
			    ,cols: [[ //表头
			      {checkbox: true,fixed: true}	
			      ,{field: 'nodecode', title: '代码', width:120}
			      ,{field: 'nodename', title: '名称', width:120}
			      ,{field: 'menu', title: '菜单代码', width:80, sort: true}
			    ]]
			  });
		});
		$('#content_system_node .user_tool_bar [data-action]').die("click").live("click",function(){
			var search=$('.user_tool_bar input[name="flownodeseach"]').val();
			var obj = $(this);
			var operate = obj.attr("data-action");
			flownodeAction(operate,flowmapTreeId,search);
		});
	}
	var flownodeAction=function(operate,id,conext){
		switch(operate){
		case 'node_search':			
			layui.table.reload('flownode',{where:{flowmapid:id,search:conext}});
			break;
		case 'node_bindmenu':
			flownode_bindmenu();
		default:
			
		}
	}
	var flownode_bindmenu=function(){
		var flownodeIds=[];
		var checkStatus = layui.table.checkStatus('flownode');
		if(checkStatus.data.length>0){
			$.each(checkStatus.data,function(key,value){  
				flownodeIds.push(checkStatus.data[key]);
			}) 
		}	
		if(flownodeIds.length!=1){
			Tips.tips("错误，必须选中一个节点！", "error");
		}else{
			layer.prompt({
	            formType: 2,
	            placeholder: '输入菜单编号',
	            title: '请输入菜单编号' //自定义文本域宽高
	        }, function(value, index, elem){
	        	$.ajax({
	    			url: "./system_flownode/bindmenu",
	    			dataType: "json",
	    			type: "POST",
	    			data:{id:flownodeIds[0].id,menu:value},
	    			success: function(e) {
	    				if(e.code){
	    					Tips.tips(e.info, true);
	    					refreshFlownode(flownodeIds[0].flowmapid,'');
	    				}
	    			}
	    		});
//	            alert(value); //得到value
	            layer.close(index);
	        });
		}
		
	}
	var mapaction=function(title,url){
		var flowmapIds=[];
		var checkStatus = layui.table.checkStatus('flowmap');
		if(checkStatus.data.length>0){
			$.each(checkStatus.data,function(key,value){  
				flowmapIds.push(checkStatus.data[key]);
			}) 
		}	
		if(flowmapIds.length<1){
			Tips.tips("错误，必须至少选中一个流程！", "error");
		}else{
			layer.prompt({
	            formType: 2,
	            title: title //自定义文本域宽高
	        }, function(value, index, elem){
	        	$.ajax({
	    			url: url,
	    			dataType: "json",
	    			type: "POST",
	    			data:{id:flowmapIds[0].id,value:value},
	    			success: function(e) {
	    				if(e.code){
	    					Tips.tips(e.info, true);
	    					layui.table.reload('flowmap',"");
	    				}
	    			}
	    		});
//	            alert(value); //得到value
	            layer.close(index);
	        });
		}
	}
	var flowmapAction=function(operate,conext){
		switch(operate){
		case 'flowmap_search':			
			layui.table.reload('flowmap',{where:{search:conext}});
			break;
		case 'flow_muntex':
			mapaction("请输入互斥流程代码（,分割）","/system_flowmap/action?field=mapmutex");
			break;
		case 'flow_timelimit':
			mapaction("请输入流程时间开启时间间隔(分钟)","/system_flowmap/action?field=timelimit");
			break;
		case 'flow_monthlimit':
			mapaction("请输入流程月重复次数","/system_flowmap/action?field=monthlylimit");
			break;
		default:
			
		}
	}
	var refreshFlownode=function(id,conext){
		layui.table.reload('flownode',{where:{flowmapid:id,search:conext}});
	}
	var wgzdAction =function(action,treeid,search,ids){
		switch(action){
		case 'wgzd_search':			
			refreshWgzd(treeid,search);
			break;
		case 'wgzd_add':
            var node = wgzdTree.getNodeByParam("id",wgzdTreeId );
            if(node==undefined){
                Tips.tips("错误，必须选中一个具体字典！", "error");
				return false;
            }
			if(node.isParent==true){
				Tips.tips("错误，没有选择具体字典！", "error");
				return false;
			}
			showdialog('wgzd',{classid:wgzdTreeId,classidString:node.name,dj:node.dj});
			break;
		case 'wgzd_edit':
			if(ids[0]){
				var wgzd=ids[0];
				if(ids[0].editable=="0" && G.is_admin==0){
					Tips.tips("该字典不允许修改！", "error");
					return;
				}
				showdialog('wgzd',wgzd);	
			}else{
				Tips.tips("错误，没有字典被选中！", "error");
			}
			break;
		case 'wgzd_delete':
			if(ids[0]){
				var  idarray=[];
				$.each(ids,function(key,value){  
					idarray.push(ids[key].id);  
				});
				console.log(idarray);
				deleteWgzdAction(idarray);
			}else{
				Tips.tips("错误，没有字典被选中！", "error");
			}
			break;
		case 'wgzd_nomodify':			
			if(ids[0]){	
				if(ids[0].jslx=="0"){
					Tips.tips("公共字典不允许修改！", "error");
					return;
				}
				wgzdModify(ids,false);
			}else{
				Tips.tips("错误，没有字典被选中！", "error");
			}	
			break;
		case 'wgzd_modify':			
			if(ids[0]){
				if(ids[0].jslx=="0"){
					Tips.tips("公共字典不允许修改！", "error");
				    return;
				}
				wgzdModify(ids,true);
			}else{
				Tips.tips("错误，没有字典被选中！", "error");
			}	
			break;
		default:
			
		}	
	}
	var xxdmAction=function(action,treeid,search,ids){
		switch(action){
		case 'xxdm_search':
			var node = xxdmTree.getNodeByParam("id",treeid );
			if(node.level==0){
				Tips.tips("错误，没有选择具体字典！", "error");
				return false;
			}
			refreshXxdm(treeid,search);
			break;
		case 'xxdm_add':
			showdialog('xxdm',{});
			break;
		case 'xxdm_edit':			
			if(ids[0]){
				if(ids[0].editable=="0" && G.is_admin==0){
					Tips.tips("该字典不允许修改！", "error");
					return;
				}	
				var xxdm=ids[0];
				showdialog('xxdm',xxdm);	
			}else{
				Tips.tips("错误，没有字典被选中！", "error");
			}			
			break;
		case 'xxdm_delete':			
			if(ids[0]){
				if(ids[0].jslx=="0"){
					Tips.tips("公共字典不允许删除！", "error");
					return;
                }                
				deleteXxdmAction(ids);
			}else{
				Tips.tips("错误，没有字典被选中！", "error");
			}
			break;
		case 'xxdm_nomodify':			
			if(ids[0]){	
				if(ids[0].jslx=="0"){
					Tips.tips("公共字典不允许修改！", "error");
					return;
				}
				xxdmModify(ids,false);
			}else{
				Tips.tips("错误，没有字典被选中！", "error");
			}	
			break;
		case 'xxdm_modify':			
			if(ids[0]){
				if(ids[0].jslx=="0"){
					Tips.tips("公共字典不允许修改！", "error");
				    return;
				}
				xxdmModify(ids,true);
			}else{
				Tips.tips("错误，没有字典被选中！", "error");
			}	
			break;
		default:
			
		}
	}
	var classficAction=function(action,treeid,search,ids){
		console.log(action);
		switch(action){
		case 'classfic_search':			
			refreshClassfic(treeid,search);
			break;
		case 'classfic_add':
			showdialog('classfic',{});
			break;
		case 'classfic_edit':			
			if(ids[0]){				
				var classfic=ids[0];				
				showdialog('classfic',classfic);	
			}else{
				Tips.tips("错误，没有分类被选中！", "error");
			}			
			break;
		case 'classfic_delete':			
			if(ids[0]){				
				deleteClassficAction(ids[0].id);
			}else{
				Tips.tips("错误，没有分类选中！", "error");
			}
			break;		
		default:
			
		}
	}
	var xxdmModify= function(ids,modify){
		$.ajax({
			url: "./system_dictionary/modify",
			dataType: "json",
			type: "POST",
			data:{ids:JSON.stringify(ids),modify:modify},
			success: function(e) {
				if(e.code){
					Tips.tips(e.info, true);
					refreshXxdm(xxdmTreeId,'');
				}
			}
		});
	}
	var wgzdModify= function(ids,modify){
		$.ajax({
			url: "./system_wgzd/modify",
			dataType: "json",
			type: "POST",
			data:{ids:JSON.stringify(ids),modify:modify},
			success: function(e) {
				if(e.code){
					Tips.tips(e.info, true);
					refreshWgzd(wgzdTreeId,'');
				}
			}
		});
	}
	var deleteXxdmAction= function(ids){
        var delids=[];
        $.each(ids,function(key,value){  
					delids.push(ids[key].id);
				});
		$.ajax({
			url: "./system_dictionary/delete",
			dataType: "json",
			type: "POST",
			data:{ids:delids},
			success: function(e) {
				if(e.code){
					Tips.tips(e.info, true);
					refreshXxdm(xxdmTreeId,'');
				}
			}
		});
	}
	var deleteWgzdAction=function(ids){
		$.ajax({
			url: "./system_wgzd/delete",
			dataType: "json",
			type: "POST",
			data:{ids:ids},
			success: function(e) {
				if(e.code){
					Tips.tips(e.info, true);
					refreshWgzd(wgzdTreeId,'');
				}
			}
		});
	}
	var deleteRoleAction= function(id){
		$.ajax({
			url: "./system_role/delete",
			dataType: "json",
			type: "GET",
			data:{id:id},
			success: function(e) {
				if(e.code){
					Tips.tips(e.info, true);
					_freshRoleList();
				}
			}
		});
	}
	var deleteClassficAction=function(id){
		$.ajax({
			url: "./system_classfic/delete",
			dataType: "json",
			type: "POST",
			data:{ids:[id]},
			success: function(e) {
				if(e.code){
					Tips.tips(e.info, true);
					refreshClassfic();
				}
			}
		});
	}
	var refreshXxdm=function(fieldname,conext){
		layui.table.reload('xxdm',{where:{field:fieldname,search:conext}});
	}
	var refreshWgzd=function(fieldname,conext){
		layui.table.reload('wgzd',{where:{field:fieldname,search:conext}});
	}
	var refreshClassfic=function(fieldname,conext){
		layui.table.reload('classfic',{where:{field:fieldname,search:conext}});
	}
	var bindMenu = function() {
		$('.setting_menu .menu_list input[name="target"]').live("click",function() {
			if("_blank" == $(this).val()){
				$(this).val("_self");
				$(this).removeAttr("checked"); 
			}else{
				$(this).val("_blank");
				$(this).attr("checked", "checked");
			}
		});
		$(".setting_menu .system_menu_add").die("click").live("click",function() {
			var e = $(".menu_default").clone().removeClass("menu_default hidden").addClass("menu_list");
			e.insertAfter(".setting_menu .menu_list:last");
		});
		$(".setting_menu .menu_list .move_up").die("click").live("click",function() {
			var e = $(this).parent().parent();
			if(e.prev().hasClass("menu_list")){
				e.insertBefore(e.prev());
			}
		});
		$(".setting_menu .menu_list .move_down").die("click").live("click",function() {
			var e = $(this).parent().parent();
			if(e.next().hasClass("menu_list")){
				e.insertAfter(e.next());
			}
		});
		$(".setting_menu .menu_list .move_hidden").die("click").live("click",function() {
			var e = $(this).parent().parent();
			if(e.hasClass("menu_hidden")){
				e.removeClass("menu_hidden");
				$(this).text("隐藏");
			}else{
				e.addClass("menu_hidden");
				$(this).text("显示");
			}
		});
		$(".setting_menu .menu_list .move_del").die("click").live("click",function() {
			var e = $(this).parent().parent();
			e.remove()
		});
		$(".system_menu_save").die("click").live("click",function() {
			var e = [];
			$(".setting_menu .menu_list").each(function() {
				var t = $(this),
				i = {};
				if(!t.hasClass("menu_default")){
					t.find("input").each(function() {
						i[$(this).attr("name")] = urlEncode($(this).attr("value"))
					});
					if("" != i.name){
						i.use = "1";
						i.type = "";
						if(t.hasClass("menu_hidden")){
							i.use = "0";
						}
						if(t.hasClass("menu_system")){
							i.type = "system";
						}
					    e.push(i);
					}
				}
			});
			sytermSave({
				menu: e
			});
		});
	}
	var sytermSave = function(data) {
		$.ajax({
			url: "setting/system_setting",
			type: "POST",
			data: "access_token=" + G.access_token + "&data=" + urlEncode(jsonEncode(data)),
			dataType: "json",
			success: function(data) {
				Tips.tips("成功","success");
			}
		});
	};
	var bindRoleAction=function(){
		$(".role_liser_content a.system_role_add").die("click").live("click",function() {
			showdialog("role",{});
		});
		$(".role_action_menu.role_list_cell a.remove_button").die("click").live("click",function(){
			deleteRoleAction($(this).attr("data-id"));
		});
		$("#role_save").die("click").live("click",function(){
			var data={};
			$(".share_dialog .content_info [name]").each(function() {
				var value = urlEncode($(this).val());
				if("" != value){
					data[$(this).attr("name")] = value;
				}
			});
			addRole(data);
		});
		
	}
	var addRole =function(data){
		$.ajax({
			url: "/system_role/save",
			type: "POST",
			data: data,
			dataType: "json",
			success: function(data) {
				Tips.tips("成功","success");
				if(data.code){
					dialog.close();
					_freshRoleList();
				}
				
			}
		});
	}
	var addorUpdateWgzd=function(data){
		$.ajax({
			url: "/system_wgzd/save",
			type: "POST",
			data: data,
			dataType: "json",
			success: function(data) {
				Tips.tips("成功","success");
				if(data.code){
					dialog.close();
					refreshWgzd();
				}
				
			}
		});
	}
	var showdialog=function(tmpname,data){
		var tmp,data;
		data["is_admin"] = G.is_admin;
//	    var UserType = G.is_admin;
//	    alert(UserType == 1);
//	    if(UserType == 1){
//	    	
//	    }else {
//	    	$("#SelectButton").prop("dis",true);
//	    }
		if("xxdm"==tmpname){
			tmp= require("./tpl/xxdm.html");
			url="/system_dictionary/save";
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				jslx:{'1':'看守所','2':'拘留所','3':'戒毒所','4':'收教所','5':'安康医院','0':'公共字典'},
				wglb:{},
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
			bindDictionaryAction();
		}
		if("wgzd"==tmpname){
			tmp= require("./tpl/wgzd.html");
			url="/system_wgzd/save";
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				jslx:{'1':'看守所','2':'拘留所','3':'戒毒所','4':'收教所','5':'安康医院','0':'公共字典'},
				wglb:{},
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
			bindWgzdAction();
		} 
		if("role"==tmpname){
			tmp= require("./tpl/role.html");
			url="/system_role/save";
			
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				jslx:{'1':'看守所','2':'拘留所','3':'戒毒所','4':'收教所','5':'安康医院','0':'公共字典'},
				wglb:{},
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
			bindRoleAction();
		}
		
		if("classfic"==tmpname){
			tmp= require("./tpl/classfic.html");
			url="/system_classfic/save";
			
			var htmlcompile = template.compile(tmp);
			var html = htmlcompile({
				jslx:{'1':'看守所','2':'拘留所','3':'戒毒所','4':'收教所','5':'安康医院','0':'公共字典'},
				wglb:{},
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
			bindClassficAction();
		}
		
		
		
		
		
	}
	var _freshRoleList=function(){
		$.ajax({
			url: "./system_role/getAll",
			dataType: "json",			
			success: function(e) {
				if(e.code){
					rolelist=System.dataList(e, "data");;
					var tmp = require("./tpl/role_list.html");
					var htmlcompile = template.compile(tmp);
					var html = htmlcompile({
						LNG: LNG,
						jslx:{'1':'看守所','2':'拘留所','3':'戒毒所','4':'收教所','5':'安康医院','0':'监管总队','9':'其他'},
						role_list: rolelist,
					});
					$(".role_liser_content").html(html);
				}
			}
		});
	}
	
	if(1 == G.is_root){
		init();		
	}
	return{
		freshXxdmTree:bindDicationaryTree,
		freshWgzdTree:bindWgzdTree,
		freshRoleList:_freshRoleList,
		freshClassfic:bindClassficTree,
		freshflowmap:bindFlowmapAction,
		freshflowmapTree:bindFlownodeAction
	}
});
