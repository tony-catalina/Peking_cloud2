define("/ui/js/pages/flowElement/main", [
	"/ui/js/plugins/easyui/jquery-easyui-1.5.1/jquery.easyui.min",
	"/ui/js/plugins/easyui/jquery-easyui-1.5.1/easyloader",
	"/ui/js/plugins/artTemplate/artTemplate",
	"/ui/js/common/tools",
	"/ui/js/common/componts",
	"/ui/js/pages/flowElement/form",
	],
	
function(require) {
    var comonts=require("/ui/js/common/componts");
    var tools=require("/ui/js/common/tools");
	var flowelementForm=require("/ui/js/pages/flowElement/form");
	var tableID="flowelementId";
	var jslxs=null;
    var columns = [
        /*{
        	field: 'flowid',
        	title: '', width: '',
        	formatter: function(value, row, rowIndex){
        		return '<input type="radio" name="nodeRadio" id="nodeRadio' + 
        		rowIndex + '" value="' + row.flowelementKey + '" />'}},*/
    	
    	 {
         	field : 'flowelementKey',
         	title : '节点key',
         	width : 200,
         	sortable : 'true',
         	formatter : function(value, row, index) {
         		return '<span title='+ 
         		(row.flowelementKey == null ? "": row.flowelementKey)+ '>'+ 
         		(row.flowelementKey == null ? "": row.flowelementKey) + '</span>'}},
        {
        	field : 'name',
        	title : '节点名称',
        	width : 200,
        	sortable : 'true',
        	formatter : function(value, row, index) {
        		return '<span  title='+ 
        		(row.name == null ? "": row.name)+ '>'+ 
        		(row.name == null ? "": row.name) + '</span>'}},
     ];
    var selectid;
    var processId= $("#initflowElementId").val();
	var initTable=function(){
        comonts.initTable(tableID,{
         	title:"流程节点",
			url:"/process/findElementList?processId="+processId,
            method:"get",
			firstLoad:true,
			toolbar:"#toolbar_yxzsl",
			onDblClickRow:function(rowIndex, row){
				
				$("input[name='nodeRadio']")[rowIndex].checked = true;
	   			selectid=row.flowelementKey;
	   			$.messager.alert("提示", selectid);
			},
			onClickRow:function(rowIndex, row){
				if(selectid!=row.flowelementKey){
		   			$("input[name='nodeRadio']")[rowIndex].checked = true;
		   			selectid=row.flowelementKey;
		   		}else{		   			
		   			$("#"+tableID).datagrid("unselectRow",rowIndex);
			        $("input[name='nodeRadio']")[rowIndex].checked = false;
			        $('#'+tableID).datagrid('clearSelections');
			        selectid="";
		   		}
			},
			onSelect:function(rowIndex, row){
				var jbxx = $('#'+tableID).datagrid('getSelected');
				var flag = $("input[name='nodeRadio']")[rowIndex].checked;

			},
			rightmenu:function(){
				//kss.initJbxxRightMenu(jbxxrightmenuid);
			},
            columns:columns
		});
        var opts = $('#'+tableID).datagrid("options");//点击查询加载数据
        opts.url = '/process/findElementList';
		hh = ($(window).height());
	}


	var query =function(){

		$('#'+tableID).datagrid('clearSelections');
		var data = $("#wwww").combogrid("getValues");
		console.info(data);

		 $('#'+tableID).datagrid('load',{"key":	data[0]});

	}

	var initToolAction=function(){


		//回车事件
		$(window).keydown(function(event) {
			if (event.keyCode == 13) {
				fingJbxx();
			}
		});
		//点击“查询”按钮事件
		$("#query_flowElement").on("click",function(){
			query();
		});	
		//导出到excel
		$("#report_flowElement").on("click",function(){
			$.messager.alert("提示", '导出到excel！');
		});	
		//新增
		$("#add_flowElement").on("click",function(){
			var node=$('#'+tableID).datagrid('getSelected');
			$.ajax({
		         type : "post", 
		         url : "/process/findRoleList",
		         contentType : 'application/json', 
		         data : null,
		         success : function(result) {
		              var data=result.result;
		              jslxs=data;
		              flowelementForm.show("添加角色和菜单",node,jslxs);
		           }   
		     });
			
			
		});
		//修改
		$("#edit_flowElement").on("click",function(){
			var row = $('#'+tableID).datagrid('getSelected');
			if(row){
				var id = row.id;
                addTab(row.name+"修改","/models/"+id);
                $.messager.alert('提示','修改!','info');
            }else{
				$.messager.alert('提示','请选择要修改的记录!','info');
			}
		});
		$("#deploy_flowElement").on("click",function(){
            var data = $('#'+tableID).datagrid('getSelected');
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
                    taskProcessData: false
                }).success(function () {
                    //成功提交
                    alert("保存成功");
                    query();
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    alert("保存失败");
                    //错误信息
                });
            }else{
                $.messager.alert('提示','请选择要部署的数据!','info');

            }
        });
        //删除
		$("#delete_flowElement").on("click",function(){
			var jbxxS = $('#'+tableID).datagrid('getSelected');
			if(jbxxS){
				$.messager.confirm('提示', '确定要删除所选中的记录吗？', function(r){
                    $.messager.alert('提示','成功!','info');
                })
			}else{
				$.messager.alert('提示','请选择要删除的数据!','info');	
			}
		});

	}

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
        comonts.initSelectPage("wwww");

        $("#wwww").combogrid({
            onChange: function (n,o) {
                query();
            }
        });
	}
	initView();

});