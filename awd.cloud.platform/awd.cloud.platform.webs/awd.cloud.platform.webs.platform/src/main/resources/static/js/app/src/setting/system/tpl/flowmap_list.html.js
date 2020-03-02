define("app/src/setting/system/tpl/flowmap_list.html", [],
'<div class="flowmap_list">\
	<table id="flowmapTable" align="center" border="0" cellspacing="0" cellpadding="0" data-toggle="table"\
        	data-url="/examples/bootstrap_table/data"\
            data-height="400"\
            data-side-pagination="server"\
            data-pagination="true"\
            data-page-list="[5, 10, 20, 50, 100, 200]"\
            data-search="true">\
	<tbody>\
		<tr class="title">\
			<td class="select"><input type=\'checkbox\' class="flowmap_select_set kui-checkbox size-small"/></td>\
			<td class="name">代码</td>\
			<td class="name">ID</td>\
			<td class="name">流程名称</td>\
			<td class="name">版本号</td>\
			<td class="name">互斥流程</td>\
			<td class="name">时间限制</td>\
			<td class="name">月次数限制</td>\
		</tr>\
	</tbody>\
	</table>\
</div>'
);