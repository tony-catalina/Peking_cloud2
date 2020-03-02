define("app/common/tpl/fileinfo/file_info.html",[],
"<div class='pathinfo'>\
{{if download_path}}\
<div class='p'>\
	<div class='title' style=\"line-height: 30px;\">外链地址:</div>\
	<div class=\"content input-group\">\
		<input type=\"text\" class=\"download_url\" value='{{download_path}}'>\
		<div class=\"input-group-btn\">\
			<button type=\"button\" class=\"btn btn-default open_window\">打开</button>\
			<button type=\"button\" class=\"btn btn-default qrcode\"><i class=\"icon-qrcode\"></i></button>\
		</div>\
	</div>\
	<div style='clear:both'></div>\
</div>\
<div class='line'></div>\
{{/if}}\
<div class='p'>\
	<div class='title'>位置:</div>\
	<div class='content' id='id_fileinfo_path'>{{path |window.htmlEncode}}</div>\
	<div style='clear:both'></div>\
</div>\
<div class='p'>\
	<div class='title'>大小:</div>\
	<div class='content'>{{size_friendly}}  ({{size}} Byte)</div>\
	<div style='clear:both'></div>\
</div>\
{{if file_md5}}\
<div class='p'>\
	<div class='title'>MD5:</div>\
	<div class='content {{if file_md5 == \"...\"}}file_md5_loading{{/if}}'>{{file_md5}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
{{if image_size}}\
<div class='p'>\
	<div class='title'>图片尺寸:</div>\
	<div class='content'>{{image_size.width}} × {{image_size.height}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
<div class='line'></div>\
{{if ctime}}\
<div class='p'>\
	<div class='title'>创建时间</div>\
	<div class='content'>{{ctime}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
{{if mtime}}\
<div class='p'>\
	<div class='title'>修改时间</div>\
	<div class='content'>{{mtime}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
{{if atime}}\
<div class='p'>\
	<div class='title'>最后访问</div>\
	<div class='content'>{{atime}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
{{if owner}}\
<div class='p'>\
	<div class='title'>所有者</div>\
	<div class='content'>{{owner}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
{{if group}}\
<div class='p'>\
	<div class='title'>组</div>\
	<div class='content'>{{group}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
{{if mode}}\
<div class='line'></div>\
<div class='p change_permission'>\
	<div class='title'>权限!:</div>\
	<div class='content'>{{mode}}</div>\
	<div style='clear:both'></div>\
</div>\
{{/if}}\
</div>"
);
