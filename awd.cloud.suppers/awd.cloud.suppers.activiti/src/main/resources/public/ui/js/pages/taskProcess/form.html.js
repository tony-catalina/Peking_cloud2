define("/ui/js/pages/taskProcess/form.html", [],
'<div id="dlg" class="easyui-window" title="{{title}}"\
		style="width: 600px; height: 530px; padding: 10px 20px; padding-top: 10px; " modal="true" closed="true" >\
<div class="easyui-layout" data-options="fit:true">\
	<div data-options="region:\'center\'" style="padding:10px;">\
		<form id="fm" method="post" novalidate >\
			<input id="id"  value="{{data.id}}" name="id" hidden="hidden"/>\
			<div style="text-align:center;">\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px;display: none">\
					<input id="rybhfm" class="easyui-textbox"  value="{{jbxx.rybh}}" name="rybh\" readonly style="width: 265px">\
				</div>\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: 50px">人员编号:</label>\
					<input id="snbhfm" class="easyui-textbox"  value="{{jbxx.snbh}}" name="snbh" readonly style="width: 265px">\
				</div>\
				<div class="fitem" id="jsh" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left:50px">留置室号:</label>\
					<input id="jshfm" class="easyui-textbox"  value="{{jbxx.jsh}}" name="jsh" readonly style="width: 265px">\
				</div>\
				<div class="fitem" id="jsh" style=" margin-bottom: 10px; margin-right:50px">\
				<label style="margin-left:75px">分数:</label>\
				<input id="fxfm" class="easyui-textbox"  value="{{}}" name="fx" readonly style="width: 265px">\
				</div>\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: 46px">风险评估<font color="red">*</font>:</label>\
					<input id="jshfm" class="easyui-textbox"  value="{{jbxx.jsh}}" name="jsh" readonly style="width: 265px">\
				</div>\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: 46px">评估理由<font color="red">*</font>:</label>\
					<input id="cqcsfm" class="easyui-textbox"  value="{{data.cqcs}}" name="cqcs"  style="width: 265px; height: 60px;" data-options="multiline:true">\
				</div>\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: 46px">管控措施<font color="red">*</font>:</label>\
					<input id="bqlyfm" class="easyui-textbox"  value="{{data.bqly}}" name="bqly"  style="width: 265px;" >\
				</div>\
				<div class="fitem" id="jlsj" style="margin-left: 54px; margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: -8px">评估时间<font color="red">*</font>:</label>\
					<input id="jlsjfm" editable="false" class="easyui-datetimebox"  value="{{data.jlsjString}}" name="jlsj" required style="width: 265px">\
				</div>\
				<div class="fitem" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: 58px">评估人<font color="red">*</font>:</label>\
					<input id="jlrfm" class="easyui-textbox"  value="{{data.jlr}}" name="jlr" required style="width: 265px; " >\
				</div>\
				<div class="fitem" id="bz" style=" margin-bottom: 10px; margin-right:50px">\
					<label style="margin-left: 74px">备注:</label>\
					<input id="bzfm" class="easyui-textbox"  value="{{data.bz}}" name="bz" style="width: 265px; height: 60px;" data-options="multiline:true">\
				</div>\
       		</div>\
		</form>\
	</div>\
	<div data-options="region:\'south\',border:false" style="text-align:right;padding:5px 0 0;">\
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="bqjlSave">保存</a>\
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" id="bqjlClose">关闭</a>\
	</div>\
</div>\
</div>');