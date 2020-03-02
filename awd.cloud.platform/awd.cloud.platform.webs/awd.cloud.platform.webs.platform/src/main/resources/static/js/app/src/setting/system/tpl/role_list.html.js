define("app/src/setting/system/tpl/role_list.html", [],
'<table id="list" align="center" border="0" cellspacing="0" cellpadding="0">\
<tbody>\
	<tr class="title">\
		<td class="">监所类型</td>\
		<td class="">代码</td>\
		<td class="">名称</td>\
		<td class="name">描述</td>\
		<td class="action">操作</td>\
	</tr>\
	{{each role_list as v i}}\
		<tr data-id="{{v.id}}" data-action="role_list_select" class="role_action_menu role_list_cell ">\
			<td class="">{{jslx[v.jslx]}}</td>\
			<td class="">{{v.code}}</td>\
			<td class="">{{v.name}}</td>\
			<td class="name">{{v.desc}}</td>\
			<td class="action"><a type="button" href="javascript:void(0);" class="remove_button" data-id="{{v.id}}">删除</a></td>\
		</tr>\
	{{/each}}\
</tbody>\
</table>\
		\
<a href="javascript:void(0)" class="add system_role_add "><i class="icon-plus pr-10"></i>添加</a>');