define("framework/easyui/awd.combo", [
	"../../plugins/easyui/jquery-easyui-1.5.1/jquery.easyui.min", 
	"../../plugins/easyui/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN",
	],
function(require) {
	var _combotree=function(select,option){
		var _multiple=(option==null||option.multiple==undefined?true:option.multiple);
		var _checkbox=(option==null||option.checkbox==undefined?true:option.checkbox);
		var _url=(option==null||option.url==undefined?"":option.url);
		var _method=(option==null||option.method==undefined?"GET":option.method);
		var _drag=(option==null||option.drag==undefined?true:option.drag);
		var select;
		if(select==undefined){
			return ;
		}
		$(select).each(function(){
			var id=$(this).attr("id");
			$(this).combotree({  
			    mode:'remote',
			    url:'',
		        method:"GET",
		        animate:true, //展开合并时动画
		        lines: true, //虚线
		        queryParams:{"type":"select"},
		        dnd:_drag,
		        multiple:_multiple,	//定义是否多选
		        checkbox:_checkbox,  //定义是否在每一个借点之前都显示复选框
	            onShowPanel : function(){
	            	var thisSelected = $(this).combotree('tree').tree('getSelected');
	            	if(thisSelected==null){
	                   var tt = $(this).combotree('reload', _url);
	                }
	            },	
	            onCheck : function(node,checked){
	            	if(node.leaf=="false"){
						if(checked){
	                        $(this).tree(node.state ='expand', node.target);
	                    }
	                }
	            },
	            loadFilter: function(data){
	                if (data.result){
	                    return data.result;
	                } else {
	                    return data;
	                }
	            }            
			});			
		});				
	}
	
	return {
		init_tree:_combotree		
	}

});