define("app/common/tpl/fileinfo/path_info_more.html", [],
"<div class='pathinfo'>\
<div class='p' style='line-height:40px;'>\
	<div class='title'>属性:</div>\
	<div class='content'>\
		{{file_num}}  文件,{{folder_num}}  文件夹</div>\
	<div style='clear:both'></div>\
</div>\
<div class='line'></div>\
<div class='p'>\
	<div class='title'>大小:</div>\
	<div class='content'>{{size_friendly}} ({{size}} Byte)</div>\
	<div style='clear:both'></div>\
</div>\
\
<div class='line'></div>\
<div class='p'>\
	<div class='title'>权限!:</div>\
	<div class='content'>{{mode}}</div>\
	<div style='clear:both'></div>\
</div>\
{{if is_admin==\"1\"}}\
<div class='p'>\
	<div class='title'>修改权限:</div>\
	<div class='content'><input type='text' class='info_chmod' value='755'/>\
	<button class='btn btn-default btn-sm edit_chmod' type='button'>保存</button></div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
</div>"
);
