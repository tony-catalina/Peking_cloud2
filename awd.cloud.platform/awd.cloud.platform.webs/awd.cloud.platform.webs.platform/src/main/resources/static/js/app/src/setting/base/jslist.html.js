define("app/src/setting/system/base/jslist.html", [],
		'<div class="user_tool_bar">\
		<div class="btn-group btn-group-sm ml-10">\
			<input type="text" name="search"  placeholder="名称"  style="float:left;height:26px;"/>\
			<button class="btn btn-default  title="搜索"  data-action="js_search" data-table="js">\
				<i class="font-icon icon-search">搜索</i>\
			</button>\
		</div>\
		<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
			<button class="btn btn-default" data-action="js_add" data-table="js">新建</button>\
			<button class="btn btn-default" data-action="js_edit" data-table="js">修改</button>\
			<button class="btn btn-default" data-action="js_delete" data-table="js">删除</button>\
		</div>\
	</div>\
	<div class="user_list">\
		<table id="jsszTable" ></table>\
	</div>');