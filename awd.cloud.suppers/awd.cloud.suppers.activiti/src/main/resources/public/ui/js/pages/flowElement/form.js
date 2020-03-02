define("/ui/js/pages/flowElement/form", [
	'/ui/js/pages/flowElement/form.html.js',
	"/ui/js/common/componts",
	],
function(require){
	var comonts=require("/ui/js/common/componts");
	var dialog;
	var jslxs;
	var _show=function(_title,_data,_jslxs){
		var tmp= require("/ui/js/pages/flowElement/form.html");
		jslxs=_jslxs;
		console.log(jslxs);
		var htmlcompile = template.compile(tmp);
		var html = htmlcompile(
				{
					title:_title,
					data:_data
					
				});
		$("#dlg").dialog('destroy')
		$("#flowelementForm").html(html);	
		$.parser.parse($("#flowelementForm").parent());

		$('#dlg').window('open');
		selects(jslxs);
	}
	function selects(jslxs){
		var dataList = [];
		dataList.push({'text' : '未选择默认0', 'value' : '0','selected':true});
		for (var i = 0; i < jslxs.length; i++) {
				 dataList.push({"value": jslxs[i].jslx,"text":(jslxs[i].jslx == null ? "":"第"+jslxs[i].jslx+"看守所")});
		}
		 $("#jslxfm").combobox("loadData", dataList);
	}

	var bindAction=function(refresh){

		$("#roleMenuClose").on("click",function(){
			$("#dlg").dialog("close");
		});
		

	}	
	return{
		show:_show,
	}
});