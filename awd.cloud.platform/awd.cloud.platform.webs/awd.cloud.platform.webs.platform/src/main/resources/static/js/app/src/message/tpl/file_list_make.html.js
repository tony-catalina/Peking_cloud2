define("app/src/explorer/tpl/file_list_make.html", [],
		"<!-- 图标模式文件夹 -->\
		{{if type=='icon_folder'}}\
		<div data-path=\"{{list.path |window.pathHashEncode}}\"\
		class='file {{list.menuType}}\
		{{if list.menuType}}systemBox{{else}}folderBox menufolder{{/if}}\
		{{if !list.sid && typeof(list.is_readable)!=\"undefined\"}}\
			{{if !list.is_writeable}} file_not_writeable{{/if}}\
			{{if !list.is_readable}} file_not_readable{{/if}}\
		{{/if}}'\
		title='名称:{{list.name}}&#10;权限! : {{list.mode}}&#10;修改时间 : {{list.mtime}}'\
		data-size=\"0\">\
			<div class='ico' filetype='folder'>\
				{{if list.ext}}\
				{{list.ext |core.icon}}\
				{{else}}\
				{{\"folder\" |core.icon}}\
				{{/if}}\
			</div>\
			{{if list.meta_info}}\
				<div class='meta_info {{list.meta_info}}'>{{list.meta_info |core.icon}}</div>\
			{{/if}}\
			<div class='filename'>\
				<span class='title db_click_rename' title=\"双击名称重命名\">\
					{{if typeof(list.exists)=='number' && list.exists==0}}\
						<b style=\"color:red;\" class=\"file_not_exists\">{{list.name}}</b>\
					{{else}}\
						{{list.name}}\
					{{/if}}\
				</span>\
			</div>\
		</div>\
		\
		<!-- 列表模式文件夹 -->\
		{{else if type=='list_folder'}}\
		<div data-path='{{list.path |window.pathHashEncode}}'\
		class='file\
		{{if index%2==0}}file2{{/if}} {{list.menuType}}\
		{{if list.menuType}}systemBox{{else}}folderBox menufolder{{/if}}\
		{{if !list.sid && typeof(list.is_readable)!=\"undefined\"}}\
		{{if !list.is_writeable}} file_not_writeable{{/if}}\
		{{if !list.is_readable}}file_not_readable{{/if}}{{/if}}'\
		title='名称 : {{list.name}}&#10;权限! : {{list.mode}}&#10;修改时间 : {{list.mtime}}'\
		data-size=\"0\">\
			<div class='filename'>\
				{{if list.meta_info}}\
					<div class='meta_info {{list.meta_info}}'>{{list.meta_info |core.icon}}</div>\
				{{/if}}\
				<span class=\"children_more\">\
					{{if list.isParent&&list.is_readable}}<i class=\"font_icon children_more_cert\"></i>{{/if}}\
				</span>\
				<div class='ico' filetype='folder'>\
					{{if list.ext}}\
					{{list.ext |core.iconSmall}}\
					{{else}}\
					{{\"folder\" |core.iconSmall}}\
					{{/if}}\
				</div>\
				<span class='title db_click_rename' title=\"双击名称重命名\">\
					{{if typeof(list.exists)=='number' && list.exists==0}}\
						<b style=\"color:red;\" class=\"file_not_exists\">{{list.name}}</b>\
					{{else}}\
						{{list.name}}\
					{{/if}}\
				</span>\
			</div>\
			<div class='filetype'>文件夹</div>\
			<div class='filesize'></div>\
			<div class='filetime'>{{list.mtime}}</div>\
			<div style='clear:both'></div>\
		</div>\
		{{if list.isParent&&list.is_readable}}\
		<div data-path-children='{{list.path |window.pathHashEncode}}' class=\"children_list hidden\"></div>\
		{{/if}}\
		\
		<!-- 分栏模式文件夹 -->\
		{{else if type=='list_split_folder'}}\
		<div data-path='{{list.path |window.pathHashEncode}}'\
		class='file {{if index%2==0}}file2{{/if}} {{list.menuType}}\
		{{if list.menuType}}systemBox{{else}}folderBox menufolder{{/if}}\
		{{if !list.sid && typeof(list.is_readable)!=\"undefined\"}}\
		{{if !list.is_writeable}} file_not_writeable{{/if}}\
		{{if !list.is_readable}}file_not_readable{{/if}}{{/if}}'\
		title='名称:{{list.name}}&#10;权限! : {{list.mode}}&#10;修改时间 : {{list.mtime}}'\
		data-size=\"0\">\
			<div class='filename'>\
				{{if list.meta_info}}\
					<div class='meta_info {{list.meta_info}}'>{{list.meta_info |core.icon}}</div>\
				{{/if}}\
				<div class='ico' filetype='folder'>\
					{{if list.ext}}\
					{{list.ext |core.iconSmall}}\
					{{else}}\
					{{\"folder\" |core.iconSmall}}\
					{{/if}}\
				</div>\
				<span class='title'>\
					{{if typeof(list.exists)=='number' && list.exists==0}}\
						<b style=\"color:red;\" class=\"file_not_exists\">{{list.name}}</b>\
					{{else}}\
						{{list.name}}\
					{{/if}}\
				</span>\
				<span class=\"children_open\">\
					{{if list.is_readable && typeof(list.menuType)==\"undefined\"}}\
						<i class=\"font_icon children_more_cert\"></i>\
					{{/if}}\
				</span>\
			</div>\
		</div>\
		\
		<!-- 图标模式文件  draggable=\"true\"  ondragstart=\"return false;\"-->\
		{{else if type=='icon_file'}}\
		<div data-path='{{list.path |window.pathHashEncode}}'\
		class='file {{list.menuType}}\
		{{if list.menuType}}systemBox{{else}}fileBox menufile{{/if}}\
		{{if !list.sid && typeof(list.is_readable)!=\"undefined\"}}\
		{{if !list.is_writeable}} file_not_writeable{{/if}}\
		{{if !list.is_readable}}file_not_readable{{/if}}{{/if}}'\
		{{if list.ext=='oexe'}}data-app='{{window.base64Encode(window.jsonEncode(list))}}'{{/if}}\
		title='名称:{{list.name}}&#10;大小:{{list.size |core.fileSize}}&#10;权限! : {{list.mode}}&#10;修改时间 : {{list.mtime}}'\
		data-size=\"{{list.size}}\">\
			{{if window.inArray(core.filetype['image'],list.ext)}}\
				<div class='picasaImage picture ico' filetype='{{list.ext |window.htmlEncode}}' picasa='{{list.path |core.path2url:false|window.htmlEncode}}'>\
				{{if window.G.sid}}\
					<img class=\"lazyload_ready\" data-original='share/image&user={{window.G.user}}&sid={{window.G.sid}}&path={{list.path |window.urlEncode|window.htmlEncode}}' draggable='false' ondragstart=\"return false;\"/>\
				{{else}}\
					<img class=\"lazyload_ready\" data-original='explorer/image?path={{list.path |window.urlEncode|window.htmlEncode}}' draggable='false' ondragstart=\"return false;\"/>\
				{{/if}}\
				</div>\
			{{else if list.type == 'app_link'}}<!-- 快捷方式 -->\
				{{if list.content.search('ui.path.open') == 0}}\
					<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
						{{list.name.replace('.oexe','') |core.pathExt|core.icon}}\
					</div>\
				{{else if list.content.search('ui.path.list') == 0}}\
					<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
						{{list.icon |core.icon}}\
					</div>\
				{{else}}\
					<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>{{\"folder\" |core.icon}}</div>\
				{{/if}}\
				<div class=\"meta_info app_link\">{{'app_link' |core.icon}}</div>\
			{{else}}\
				<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
					 {{if list.ext=='oexe'}}\
					 {{oexe_icon |core.iconSrc}}\
					 {{else}}\
					 {{list.ext |core.icon}}\
					 {{/if}}\
				</div>\
			{{/if}}\
			{{if list.meta_info}}\
				<div class='meta_info {{list.meta_info}}'>{{list.meta_info |core.icon}}</div>\
			{{/if}}\
			<div class='filename'>\
				<span class='title db_click_rename' title=\"双击名称重命名\">\
				{{if typeof(list.exists)=='number' && list.exists==0}}\
					<b style=\"color:red;\" class=\"file_not_exists\">{{list.name}}</b>\
				{{else}}\
					{{if list.ext=='oexe'}}{{list.name.replace('.oexe','')}}{{else}}{{list.name}}{{/if}}\
				{{/if}}\
				</span>\
			</div>\
		</div>\
		\
		<!-- 列表模式文件 -->\
		{{else if type=='list_file'}}\
		<div data-path='{{list.path |window.pathHashEncode}}'\
		class='file {{if index%2==0}}file2{{/if}} {{list.menuType}}\
		{{if list.menuType}}systemBox{{else}}fileBox menufile{{/if}}\
		{{if !list.sid && typeof(list.is_readable)!=\"undefined\"}}\
		{{if !list.is_writeable}} file_not_writeable{{/if}}\
		{{if !list.is_readable}}file_not_readable{{/if}}{{/if}}'\
		{{if list.ext=='oexe'}} data-app='{{window.base64Encode(window.jsonEncode(list))}}'{{/if}}\
		title='名称:{{list.name}}&#10;大小:{{list.size |core.fileSize}}&#10;权限! : {{list.mode}}&#10;修改时间 : {{list.mtime}}'\
		data-size=\"{{list.size}}\">\
			<div class='filename'>\
				<span class=\"children_more\"></span>\
				{{if window.inArray(core.filetype['image'],list.ext)}}\
					<div class='picasaImage picture ico' filetype='{{list.ext |window.htmlEncode}}' picasa='{{list.path |core.path2url:false|window.htmlEncode}}'>\
					{{if window.G.sid}}\
						<img class=\"lazyload_ready\" data-original='share/image?user={{window.G.user}}&sid={{window.G.sid}}&path={{list.path |window.urlEncode|window.htmlEncode}}' draggable='false' ondragstart=\"return false;\"/>\
					{{else}}\
						<img class=\"lazyload_ready\" data-original='explorer/image?path={{list.path |window.urlEncode|window.htmlEncode}}' draggable='false' ondragstart=\"return false;\"/>\
					{{/if}}\
					</div>\
				{{else if list.type == 'app_link'}}<!-- 快捷方式 -->\
					{{if list.content.search('ui.path.open') == 0}}\
						<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
							{{list.name.replace('.oexe','') |core.pathExt|core.iconSmall}}\
						</div>\
					{{else if list.content.search('ui.path.list') == 0}}\
						<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
							{{list.icon |core.icon}}\
						</div>\
					{{else}}\
						<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>{{\"folder\" |core.iconSmall}}</div>\
					{{/if}}\
					<div class=\"meta_info app_link\">{{'app_link' |core.icon}}</div>\
				{{else}}\
					<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
						 {{if list.ext=='oexe'}}\
						 	{{'oexe' |core.iconSmall}}\
						 {{else}}\
						 	{{list.ext |core.iconSmall}}\
						 {{/if}}\
					</div>\
				{{/if}}\
				{{if list.meta_info}}\
					<div class='meta_info {{list.meta_info}}'>{{list.meta_info |core.icon}}</div>\
				{{/if}}\
				<span class='title db_click_rename' title=\"双击名称重命名\">\
				{{if typeof(list.exists)=='number' && list.exists==0}}\
					<b style=\"color:red;\" class=\"file_not_exists\">{{list.name}}</b>\
				{{else}}\
					{{if list.ext=='oexe'}}{{list.name.replace('.oexe','')}}{{else}}{{list.name}}{{/if}}\
				{{/if}}\
				</span>\
			</div>\
			<div class='filetype'>{{list.ext |window.htmlEncode}} 文件</div>\
			<div class='filesize'>{{list.size |core.fileSize}}</div>\
			<div class='filetime'>{{list.mtime}}</div>\
			<div style='clear:both'></div>\
		</div>\
		\
		<!-- 分栏模式文件 -->\
		{{else if type=='list_split_file'}}\
		<div data-path='{{list.path |window.pathHashEncode}}'\
		class='file {{if index%2==0}}file2{{/if}} {{list.menuType}}\
		{{if list.menuType}}systemBox{{else}}fileBox menufile{{/if}}\
		{{if !list.sid && typeof(list.is_readable)!=\"undefined\"}}\
		{{if !list.is_writeable}} file_not_writeable{{/if}}\
		{{if !list.is_readable}}file_not_readable{{/if}}{{/if}}'\
		{{if list.ext=='oexe'}} data-app='{{window.base64Encode(window.jsonEncode(list))}}'{{/if}}\
		title='名称:{{list.name}}&#10;大小:{{list.size |core.fileSize}}&#10;权限! : {{list.mode}}&#10;修改时间 : {{list.mtime}}'\
		data-size=\"{{list.size}}\">\
			<div class='filename'>\
				{{if window.inArray(core.filetype['image'],list.ext)}}\
					<div class='picasaImage picture ico' filetype='{{list.ext |window.htmlEncode}}' picasa='{{list.path |core.path2url:false|window.htmlEncode}}'>\
					{{if window.G.sid}}\
						<img class=\"lazyload_ready\" data-original='share/image?user={{window.G.user}}&sid={{window.G.sid}}&path={{list.path |window.urlEncode|window.htmlEncode}}' draggable='false' ondragstart=\"return false;\"/>\
					{{else}}\
						<img class=\"lazyload_ready\" data-original='explorer/image?path={{list.path |window.urlEncode|window.htmlEncode}}' draggable='false' ondragstart=\"return false;\"/>\
				{{/if}}\
					</div>\
				{{else if list.type == 'app_link'}}<!-- 快捷方式 -->\
					{{if list.content.search('ui.path.open') == 0}}\
						<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
							{{list.name.replace('.oexe','') |core.pathExt|core.iconSmall}}\
						</div>\
					{{else if list.content.search('ui.path.list') == 0}}\
						<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
							{{list.icon |core.icon}}\
						</div>\
					{{else}}\
						<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>{{\"folder\" |core.iconSmall}}</div>\
					{{/if}}\
					<div class=\"meta_info app_link\">{{'app_link' |core.icon}}</div>\
				{{else}}\
					<div class='ico' filetype='{{list.ext |window.htmlEncode}}'>\
						 {{if list.ext=='oexe'}}\
						 	{{'oexe' |core.iconSmall}}\
						 {{else}}\
						 	{{list.ext |core.iconSmall}}\
						 {{/if}}\
					</div>\
				{{/if}}\
		\
				{{if list.meta_info}}\
					<div class='meta_info {{list.meta_info}}'>{{list.meta_info |core.icon}}</div>\
				{{/if}}\
				<span class='title'>\
				{{if typeof(list.exists)=='number' && list.exists==0}}\
					<b style=\"color:red;\" class=\"file_not_exists\">{{list.name}}</b>\
				{{else}}\
					{{if list.ext=='oexe'}}{{list.name.replace('.oexe','')}}{{else}}{{list.name}}{{/if}}\
				{{/if}}\
				</span>\
			</div>\
		</div>\
		{{/if}}"
);
