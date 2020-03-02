define("/ui/js/pages/process/main", [
	"/ui/js/plugins/easyui/jquery-easyui-1.5.1/jquery.easyui.min",
	"/ui/js/plugins/easyui/jquery-easyui-1.5.1/easyloader",
	"/ui/js/pages/process/form",
	"/ui/js/plugins/artTemplate/artTemplate",
	"/ui/js/common/tools",
	"/ui/js/common/componts",
	],
function(require) {
    var comonts=require("/ui/js/common/componts");
    var formHtml=require("/ui/js/pages/process/form");
	var tableID="processID";

    var columns = [
        {field: 'ooid', title: '', width: '',formatter: function(value, rowData, rowIndex){return '<input type="radio" name="ryRadio" id="ryRadio' + rowIndex + '" value="' + rowData.ooid + '" />'}},
        {field : 'id',title : '流程ID',width : 200,sortable : 'true',formatter : function(value, row, index) {return '<span title='+ (row.id == null ? "": row.id)+ '>'+ (row.id == null ? "": row.id) + '</span>'}},
        {field : 'deploymentId',title : '部署ID',width : 200,sortable : 'true',formatter : function(value, row, index) {return '<span title='+ (row.deploymentId == null ? "未部署": row.deploymentId)+ '>'+ (row.deploymentId == null ? "未部署": row.deploymentId) + '</span>'}},
        {field : 'name',title : '流程名称',width : 200,sortable : 'true',formatter : function(value, row, index) {return '<span title='+ (row.name == null ? "": row.name)+ '>'+ (row.name == null ? "": row.name) + '</span>'}},
        {field : 'version',title : '版本号',width : 100,sortable : 'true',formatter : function(value, row, index) {return '<span title='+ (row.version == null ? "": row.version)+ '>'+ (row.version == null ? "": row.version) + '</span>'}},
     ];
    var selectid;

	var initTable=function(){
        comonts.initTable(tableID,{
         	title:"模块管理",
			url:"/models/list/page",
            firstLoad:true,
			toolbar:'#toolbar',
			onDblClickRow:function(rowIndex, rowData){
				if (rowData.rybh != undefined) {
					$("input[name='ryRadio']")[rowIndex].checked = true;
					$("#"+tableID).datagrid("selectRow", rowIndex);
					var jbxxS=$("#"+tableID).datagrid('getSelected');
				}
			},
			onClickRow:function(rowIndex, rowData){
				if(selectid!=rowData.id){
		   			$("input[name='ryRadio']")[rowIndex].checked = true;
		   			selectid=rowData.id;
		   		}else{		   			
		   			$("#"+tableID).datagrid("unselectRow",rowIndex);
			        $("input[name='ryRadio']")[rowIndex].checked = false;
			        $('#'+tableID).datagrid('clearSelections');
			        selectid="";
		   		}
			},
			onSelect:function(rowIndex, rowData){
				var jbxx = $('#'+tableID).datagrid('getSelected');
				var flag = $("input[name='ryRadio']")[rowIndex].checked;

			},
			rightmenu:function(){
				//kss.initJbxxRightMenu(jbxxrightmenuid);
			},
            onLoadSuccess:function(data){
                $("a[name='opera']").linkbutton({text:'部署',plain:true,iconCls:'icon-add',height:24});
            },
            columns:columns
		});
        var opts = $('#'+tableID).datagrid("options");//点击查询加载数据
        opts.url = '/models/list/page';
		hh = ($(window).height());
	}


	var query =function(){
        var id= $("#lcid").textbox('getValue');
        var name= $("#name").textbox('getValue');
		 $('#'+tableID).datagrid('clearSelections');
		 $('#'+tableID).datagrid('load',{"id":id,"name":name});

	}

	var initToolAction=function(){


		//回车事件
		$(window).keydown(function(event) {
			if (event.keyCode == 13) {
				fingJbxx();
			}
		});
		//点击“查询”按钮事件
		$("#query_process").on("click",function(){
			query();
		});	
		//导出到excel
		$("#report_process").on("click",function(){
            var data = $('#'+tableID).datagrid('getSelected');
            if(data) {
                var id = data.id;
                window.location.href = "/models/export/" + id;
            }

		});
		//新增
		$("#add_process").on("click",function(){
             addTab("新增流程实例","/models/0");
		});
		//修改
		$("#edit_process").on("click",function(){
			var row = $('#'+tableID).datagrid('getSelected');
			if(row){
				var id = row.id;
                addTab(row.name+"修改","/models/"+id);
            }else{
				$.messager.alert('提示','请选择要修改的记录!','info');
			}
		});
        $("#import_process").on("click",function(){
            formHtml.show();
        });
		//部署
		$("#deploy_process").on("click",function(){
            var data = $('#'+tableID).datagrid('getSelected');
            if(data){
                var id =  data.id;//JSON.stringify();
                console.log(id);
                if(data.deploymentId!=null){
                    $.messager.alert('提示','该流程已部署!','info');
                    return ;
                }
                if(data){
                    $.ajax({
                        type: "POST",
                        url: "/models/"+id+"/deployment",
                        data: {},
                        mimeType: "multipart/form-data",
                        contentType: false,
                        cache: false,
                        processData: false
                    }).success(function () {
                        //成功提交
                        $.messager.alert('提示','部署成功!','success');
                        query();
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                        $.messager.alert('提示','部署失败!','error');
                        //错误信息
                    });
                }else{
                    $.messager.alert('提示','请选择要部署的数据!','info');

                }
            }else{
                $.messager.alert('提示','请选择要部署的数据!','info');
            }

        });
        //删除
		$("#delete_process").on("click",function(){
			var data = $('#'+tableID).datagrid('getSelected');
			if(data){
                var id = data["id"]
                var deploymentId = data["deploymentId"];
                console.log("deploymentId: "+deploymentId);
                if(deploymentId != "" && deploymentId != null && deploymentId != undefined){
                    $.messager.alert('提示','改model已部署再用，请先删除部署，在删除model!','success');
                    return;
                }

                if(data){
                    $.messager.confirm('提示', '确定要删除所选中的记录吗？', function(r){
                        if(r){
                            $.ajax({
                                type: "DELETE",
                                url: "/models/"+id+"",
                                data: {},
                                mimeType: "multipart/form-data",
                                contentType: false,
                                cache: false,
                                processData: false
                            }).success(function () {
                                //成功提交
                                $.messager.alert('提示','删除成功!','success');
                                query();
                            }).fail(function (jqXHR, textStatus, errorThrown) {
                                $.messager.alert('提示','删除失败!','error');
                                //错误信息
                            });
                        }
                    })
                }else{
                    $.messager.alert('提示','请选择要删除的数据!','info');
                }
            }else {
                $.messager.alert('提示','请选择要删除的数据!','info');

            }

		});	
	}
	/*var deployfff = function(id) {
		var data = $("#"+id).datagrid('getSelected');
        $.messager.alert('提示',data,'success');
	}*/
	//添加tab
    var addTab = function(title, url){
        var jq = top.jQuery;
        if (jq("#tt").tabs('exists', title)){
            jq("#tt").tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
            jq("#tt").tabs('add',{
                title:title,
                content:content,
                closable:true
            });
        }
    }
	var initView=function(){
		initTable();
		initToolAction();
		console.log("00");

	}
	initView();

});