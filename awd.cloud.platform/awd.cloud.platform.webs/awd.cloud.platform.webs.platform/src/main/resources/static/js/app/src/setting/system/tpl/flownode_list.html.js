define("app/src/setting/system/tpl/flownode_list.html", [],
'<div class="flownode_list">\
	<table id="flownodeTable" align="center" border="0" cellspacing="0" cellpadding="0" data-toggle="table"\
        	data-url="/examples/bootstrap_table/data"\
            data-height="400"\
            data-side-pagination="server"\
            data-pagination="true"\
            data-page-list="[5, 10, 20, 50, 100, 200]"\
            data-search="true">\
	<tbody>\
		<tr class="title">\
			<td class="select"><input type=\'checkbox\' class="flownode_select_set kui-checkbox size-small"/></td>\
			<td class="space">代码</td>\
			<td class="name">名称</td>\
			<td class="space">菜单</td>\
		</tr>\
	</tbody>\
	</table>\
</div>'
);