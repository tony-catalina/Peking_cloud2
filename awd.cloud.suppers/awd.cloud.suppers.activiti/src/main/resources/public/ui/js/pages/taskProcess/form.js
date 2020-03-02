define("/ui/js/pages/taskProcess/form", [
	'/ui/js/pages/taskProcess/form.html.js',
	"/ui/js/common/componts",
	],
function(require){
	var comonts=require("/ui/js/common/componts");
	var dialog;
	var jbxxid="jbxxId";
	var _show=function(_jbxx,_data,_title,refresh){
		console.info('sss');
		var tmp= require("/ui/js/pages/taskProcess/form.html");
		var htmlcompile = template.compile(tmp);
		var html = htmlcompile(
				{
					jbxx:_jbxx,
					data:_data,
					title:_title,
				});
		$("#dlg").dialog('destroy')
		$("#rcfxpgdialog").html(html);	
		$.parser.parse($("#rcfxpgdialog").parent());
		
		$('#snbhfm').next().find('input[type=text]').css('background-color','#EBEBE4');	
		$('#jshfm').next().find('input[type=text]').css('background-color','#EBEBE4');
		$('#fxfm').next().find('input[type=text]').css('background-color','#EBEBE4');
		
		$('#dlg').window('open');
		
		//bindAction(refresh);
	}
	
    function saveBqjl(data) {
    	$.ajax({
	         type : "post", 
	         url : "/bqjl/",
	         contentType : 'application/json', 
	         data : data,
	         success : function(result) {
	              if (result.status == '200') {
	                        $('#dlg').dialog('close');
		                    $.messager.alert("提示","保存成功","info");
		            		$("#"+bqjlid).datagrid("clearSelections");
		            		$('#'+bqjlid).datagrid('reload',{rybh$eq:$('#'+jbxxid).datagrid('getSelected').rybh,state$eq:'R2',});
	                  }else{     
		                    $.messager.alert("提示","保存失败","info");                     		    
	                  }     
	              }   
	     });
	}
    
    function updateBqjl(data) {
    	var bqjl = $("#"+bqjlid).datagrid("getSelected");
    	$.ajax({
		    type : "put",    
		    url : "/bqjl/" + bqjl.id,
		    contentType : 'application/json', 
		    data : data,
		    success : function(result) {
				if (result) {
					$('#dlg').dialog('close');
					$.messager.alert("提示","修改成功","info");
					$('#'+bqjlid).datagrid('reload',{rybh$eq:$('#'+jbxxid).datagrid('getSelected').rybh,state$eq:'R2',});
				}else{     
					$.messager.alert("提示","修改失败","info");                     		    
				}     
		    }   
	     });
    }
    
	var bindAction=function(refresh){
		$("#bqjlSave").on("click",function(){
			var validate=$("#dlg").form('validate');
			var flag = $("input[name=sfjxgc]")[0].checked;
			if (flag) {
				$('#wxgcjlrfm').textbox('setValue','');
			}
			if (validate) {
				var dataObj =$("#fm").serializeObject();	
				var data = JSON.stringify(dataObj);
				if (id == undefined) {
					saveBqjl(data);
				}else {
					updateBqjl(data);
				}
			}else{
				 $.messager.alert("提示", '必填项不能为空！',"info");
			}
		});
		
		$("#bqjlClose").on("click",function(){
			$("#dlg").dialog("close");
		});
		
		//表单中的事件
		
	    $("#sfjxgcyes").bind("click",function(event){
	    	$("#jxgcDiv").hide();
	    	$('#wxgcjlrfm').textbox({required:false});
		});
	    
	    $("#sfjxgcno").bind("click",function(event){
	    	$("#jxgcDiv").show();
	    	$('#wxgcjlrfm').textbox({required:true});
		}); 
	}	
	return{
		show:_show,
	}
});