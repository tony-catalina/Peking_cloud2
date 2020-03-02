define("app/common/tpl/search.html", [],
'<div class="do_search">\
	<div class="search_header">\
		<div class="s_br">\
			<input type="text" id="search_value"/><button class=\"btn btn-default btn-sm btn-right\"><i class=\"font-icon icon-search\"></i></button>\
			<div style="float:right">路径:<input type="text" id="search_path" title=\"\" title-data=\"#search_path\" title-timeout=\"100\"/></div>\
		</div>\
		<div class="s_br">\
			<input type="checkbox" id="search_is_content" class=\"kui-checkbox size-small\"/>\
			<label for="search_is_content">搜索文件内容</label>\
			<input type="checkbox" id="search_is_case" class=\"kui-checkbox size-small\"/>\
			<label for="search_is_case">区分大小写</label>\
			<div style="float:right">\
				文件类型:<input type="text" id="search_ext" title="用|隔开;例如php|js|css<br\/>不填则搜索默认文本文件" title-timeout=\"100\"/>\
			</div>\
		</div>\
	</div>\
	<div class=\"search_desc\"></div>\
	<div class="search_result">\
		<ul class=\"file-items\"></ul>\
	</div>\
</div>'
);
