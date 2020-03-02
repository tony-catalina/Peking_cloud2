define("app/src/setting/system/base/jqlist.html", [],
	'<div class="user_tool_bar">\
		<div class="btn-group btn-group-sm ml-10">\
			<input type="text" name="search"  placeholder="名称"  style="float:left;height:26px;"/>\
			<button class="btn btn-default  title="搜索"  data-action="jq_search" data-table="jq">\
				<i class="font-icon icon-search">搜索</i>\
			</button>\
		</div>\
		<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
			<button class="btn btn-default" data-action="jq_add"  	data-table="jq" >新建</button>\
			<button class="btn btn-default" data-action="jq_edit"  	data-table="jq">修改</button>\
			<button class="btn btn-default" data-action="jq_delete" data-table="jq">删除</button>\
		</div>\
	</div>\
	<div class="user_list">\
		<table id="jqszTable" ></table>\
	</div>');