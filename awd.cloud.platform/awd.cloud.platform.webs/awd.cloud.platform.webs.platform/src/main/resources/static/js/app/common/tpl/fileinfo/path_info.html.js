define("app/common/tpl/fileinfo/path_info.html", [],
"<div class='pathinfo'>\
<div class='p'>\
	<div class='title'>位置:</div>\
	<div class='content'>{{path |window.htmlEncode}}</div>\
	<div style='clear:both'></div>\
</div>\
<div class='p'>\
	<div class='title'>大小:</div>\
	<div class='content'>{{size_friendly}}  ({{size}} Byte)</div>\
	<div style='clear:both'></div>\
</div>\
<div class='p'>\
	<div class='title'>包含:</div> \
	<div class='content'>{{file_num}}  文件,{{folder_num}}  文件夹</div>\
	<div style='clear:both'></div>\
</div>\
\
<div class='line'></div>\
{{if ctime}}\
<div class='p'>\
	<div class='title'>创建时间</div>\
	<div class='content'>{{ctime}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
\
{{if mtime}}\
<div class='p'>\
	<div class='title'>修改时间</div>\
	<div class='content'>{{mtime}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
\
{{if atime}}\
<div class='p'>\
	<div class='title'>最后访问</div>\
	<div class='content'>{{atime}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
\
{{if owner}}\
	<div class='p'>\
	<div class='title'>所有者</div>\
	<div class='content'>{{owner}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
\
{{if group}}\
<div class='p'>\
	<div class='title'>组</div>\
	<div class='content'>{{group}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
\
{{if mode}}\
	<div class='line'></div>\
	<div class='p'>\
		<div class='title'>权限!:</div>\
		<div class='content'>{{mode}}</div>\
		<div style='clear:both'></div>\
	</div>\
	{{if is_root==\"1\"}}\
	<div class='p'>\
		<div class='title'>修改权限:</div>\
		<div class='content'><input type='text' class='info_chmod' value='755'/>\
		<button class='btn btn-default btn-sm edit_chmod' type='button'>保存</button></div>\
		<div style='clear:both'></div>\
	</div>\
	{{/if}}\
{{/if}}\
</div>"
);
