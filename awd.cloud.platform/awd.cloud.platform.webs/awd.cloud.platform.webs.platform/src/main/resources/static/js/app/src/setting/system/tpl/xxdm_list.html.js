define("app/src/setting/system/tpl/xxdm_list.html", [],
'<div class="xxdm_list">\
	<table id="xxdmTable" align="center" border="0" cellspacing="0" cellpadding="0" data-toggle="table"\
        	data-url="/examples/bootstrap_table/data"\
            data-height="400"\
            data-side-pagination="server"\
            data-pagination="true"\
            data-page-list="[5, 10, 20, 50, 100, 200]"\
            data-search="true">\
	<tbody>\
		<tr class="title">\
			<td class="select"><input type=\'checkbox\' class="xxmd_select_set kui-checkbox size-small"/></td>\
			<td class="space">代码</td>\
			<td class="name">内容</td>\
			<td class="space">可修改</td>\
		</tr>\
		{{each xxdmlist as v i}}\
		<tr data-id="{{v.id}}" data-action="xxdm_list_select" class="user_action_menu user_list_cell ">\
			<td class="select">\
				<input type=\'checkbox\' class="xxmd_select kui-checkbox size-small"/>\
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