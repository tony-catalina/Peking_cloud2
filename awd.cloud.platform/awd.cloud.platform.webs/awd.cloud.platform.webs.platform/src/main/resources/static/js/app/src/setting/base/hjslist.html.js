define("app/src/setting/system/base/hjslist.html", [],
	'<div class="user_tool_bar">\
		<div class="btn-group btn-group-sm ml-10">\
			<input type="text" name="search"  placeholder="名称"  style="float:left;height:26px;"/>\
			<button class="btn btn-default  title="搜索"  data-action="hjs_search" data-table="fjsz" >\
				<i class="font-icon icon-search">搜索</i>\
			</button>\
		</div>\
		<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
			<button class="btn btn-default" data-action="hjs_add" data-table="fjsz">新建</button>\
			<button class="btn btn-default" data-action="hjs_edit" data-table="fjsz">修改</button>\
			<button class="btn btn-default" data-action="hjs_delete" data-table="fjsz">删除</button>\
		</div>\
	</div>\
	<div class="user_list">\
		<table id="hjsszTable" ></table>\
	</div>');