define("/ui/js/pages/deployedProcess/form.html", [],
'<div id="dlg" class="easyui-window" title="{{title}}"\
		style="width: 600px; height: 130px; padding: 10px 20px; padding-top: 10px; " modal="true" closed="true" >\
<div class="easyui-layout" data-options="fit:true">\
	<div data-options="region:\'center\'" style="padding:10px;">\
		<form id="fm" method="post" novalidate >\
			<div style="text-align:center;">\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: 58px">监所编号<font color="red">*</font>:</label>\
					<input id="jsbhfm" class="easyui-textbox"  name="jsbh" style="width: 265px; " >\
				</div>\
       		</div>\
		</form>\
	</div>\
	<div data-options="region:\'south\',border:false" style="text-align:center;padding:5px 0 0;">\
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="deployedProcessSave">保存</a>\
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" id="deployedProcessClose">关闭</a>\
	</div>\
</div>\
</div>');