define("app/src/setting/system/tpl/group.html", [],
'<div class=\'content_box\'>\
<div class=\'title\'>\
	<div class="titleinfo">\
		{{if !groupInfo.name}}\
		<i class="font-icon icon-plus"></i>创建分组\
		{{else}}\
		<i class="font-icon icon-pencil"></i>{{groupInfo.name}}\
		{{/if}}\
	</div>\
</div>\
<div class=\'content_info\'>\
	<input id="name" type="text" name="id" value="{{groupInfo.id}}" hidden />\
	<div class="input_line">\
		<span class="input_title">组名称:</span>\
		<input id="name" type="text" name="name" value="{{groupInfo.name}}" />\
		<div style="clear:both"></div>\
	</div>\
	<div class="input_line select_parent_content">\
		<span class="input_title">上层组:</span>\
		<input id="group_parent" type="hidden" name="parent_id" value="{{groupInfo.parent_id}}" />\
		<button class="btn btn-default btn-sm" type="button" style="padding: 5px 10px;" disabled="disabled">\
			<i class="font-icon icon-group"></i><span class="group_title pl-10"></span>\
		</button>\
		<ul class="select_group hidden ztree" id="group_parent_select"></ul>\
		<div style="clear:both"></div>\
	</div>\
</div>\
\
<div class="share_action">		\
	{{if !groupInfo.name}}\
	<button type="button" class="btn btn-primary" id="system_save">添加</button>\
	<button type="button" class="btn btn-primary" id="system_save_goon_add">保存并继续添加</button>\
	{{else}}\
	<button type="button" class="btn btn-primary" id="system_save">保存</button>\
	<a type="button" href="javascript:void(0);" class="remove_button">删除</a>\
	{{/if}}\
</div>\
</div>');
