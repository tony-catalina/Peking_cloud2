define("app/src/setting/system/tpl/user_list.html", [],
'<div class="user_tool_bar">\
<div class="btn-group btn-group-sm ml-10">\
	<button type="button" class="btn btn-default" data-action="user_add">新建用户</button>\
	<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\
		<span class="caret"></span>&nbsp;\
		<span class="sr-only">Dropdown</span>\
	</button>\
	<ul class="dropdown-menu">\
		<li><a href="javascript:void(0);" data-action="user_import">批量添加</a></li>\
	</ul>\
</div>\
<div class="btn-group btn-group-sm ml-10 button_aciton_muti">\
	<button class="btn btn-default" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\
		<span class="role_title pr-5">批量设置角色</span><span class="caret"></span>\
	</button>\
	<ul class="dropdown-menu">\
		<li><a href="javascript:void(0);" data-action="role_remove_from">移除角色</a></li>\
		<li><a href="javascript:void(0);" data-action="role_add">添加到角色</a></li>\
	</ul>\
</div>\
<div class="btn-group btn-group-sm ml-5 button_aciton_muti">\
	<button class="btn btn-default" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\
		<span class="role_title pr-5">用户组</span><span class="caret"></span>\
	</button>\
	<ul class="dropdown-menu">\
		{{each group_list as value key}}\
		<li><a href="javascript:void(0);" data-action="group_set" data-group-id="{{key}}">{{value.name}}</a></li>\
		{{/each}}\
	</ul>\
</div>\
\
<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
	<button class="btn btn-default" data-action="user_status_close">禁用</button>\
	<button class="btn btn-default" data-action="user_status_open">启用</button>\
</div>\
\
<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
	<button class="btn btn-default" data-action="user_app_close">应用禁用</button>\
	<button class="btn btn-default" data-action="user_appmenu_close">菜单禁用</button>\
</div>\
<div class="btn-group btn-group-sm button_aciton_muti ml-2">\
	<button class="btn btn-default" data-action="user_zw" disabled="disabled" style="background:#FFFFFF;">指纹</button>\
</div>\
</div>\
<div class="user_list">\
<table id="list" align="center" border="0" cellspacing="0" cellpadding="0">\
	<tbody>\
		<tr class="title">\
			<td class="select"><input type=\'checkbox\' class="user_select_set kui-checkbox size-small"/></td>\
			<td class="name" style="width:17%;">用户名</td>\
			<td class="jh">警号</td>\
			<td class="name">权限角色</td>\
			<td class="group">所在组</td>\
			<td class="group">状态</td>\
		</tr>\
		{{each user_list as v i}}\
			{{if v  }}\
			<tr data-id="{{v.id}}" data-action="user_list_select" class="user_action_menu user_list_cell {{if v.status=="0"}}unuse{{/if}}">\
				<td class="select">\
					{{if v.isadmin!=\'1\'}}<input type=\'checkbox\' class="user_select kui-checkbox size-small"/>{{/if}}\
				</td>\
				<td class="name">\
					<a data-action="user_list_edit" href="javascript:void(0);">{{v.username}}</a>\
				</td>\
				<td class="jh">{{v.jh}}</td>\
				<td class="name">\
				{{each v.roles as role_id}}\
					<span {{role_id.id}}  class="ml-5 label label-blue-deep" \
						title-timeout=\'50\' title="{{role_id.name}}">\
						{{role_id.name}}\
					</span>\
				{{/each}}\
				</td>\
				<td class="group">{{group_list[v.groupid].name}}</td>\
				<td class="group">{{if v.state==\'R2\'}}启用 {{else}}禁用{{/if}}</td>\
			</tr>\
			{{/if}}\
		{{/each}}\
	</tbody>\
</table>\
</div><!-- 用户列表 -->');
