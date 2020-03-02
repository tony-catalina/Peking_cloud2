define("/ui/js/pages/flowElement/form.html", [],
'<div id="dlg" class="easyui-window" title="{{title}}"\
		style="width: 600px; height: 530px; padding: 10px 20px; padding-top: 10px; " modal="true" closed="true" >\
<div class="easyui-layout" data-options="fit:true">\
	<div data-options="region:\'center\'" style="padding:10px;">\
		<form id="fm" method="post" novalidate >\
			<input id="id"  value="" name="id" hidden="hidden"/>\
			<div style="text-align:center;">\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px;display: none">\
					<input id="nodeCodefm" class="easyui-textbox"  value="" name="nodeCode\" readonly style="width: 265px">\
				</div>\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: 50px">节点Key:</label>\
					<input id="codefm" class="easyui-textbox"  value="{{data.flowelementKey}}" name="code" readonly style="width: 265px">\
				</div>\
				<div class="fitem" id="codename" style=" margin-bottom: 10px; margin-right:50px">\
				<label style="margin-left:50px">节点名称:</label>\
				<input id="codenamefm" class="easyui-textbox"  value="{{data.name}}" name="codename" readonly style="width: 265px">\
				</div>\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px">\
				<label style="margin-left: 50px">监所类型:</label>\
				<select id="jslxfm" class="easyui-combobox"   name="jslx"   style="width: 265px;display:block">\
				</div>\
       		</div>\
		</form>\
	</div>\
	<div data-options="region:\'south\',border:false" style="text-align:right;padding:5px 0 0;">\
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="roleMenuSave">保存</a>\
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" id="roleMenuClose">关闭</a>\
	</div>\
</div>\
</div>');