define("app/src/setting/system/tpl/wgzd_list.html", [],
'<div class="wgzd_list">\
	<table id="list" align="center" border="0" cellspacing="0" cellpadding="0">\
	<tbody>\
		<tr class="title">\
			<td class="select"><input type=\'checkbox\' class="wgzd_select_set kui-checkbox size-small"/></td>\
			<td class="space">代码</td>\
			<td class="name">内容</td>\
			<td class="space">可修改</td>\
		</tr>\
		{{each wgzdlist as v i}}\
		<tr data-id="{{v.id}}" data-action="wgzd_list_select" class="wgzd_action_menu wgzdr_list_cell ">\
			<td class="select">\
				<input type=\'checkbox\' class="wgzd_select kui-checkbox size-small"/>\
			</td>\
			<td class="space">{{v.code}}</td>\
			<td class="name">{{v.content}}</td>\
			{{}}\
			<td class="space"></td>\
		</tr>\
	{{/each}}\
	</tbody>\
	</table>\
</div>'
);