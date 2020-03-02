define('app/common/tpl/app_edit.html', [],
"<div class='appbox'>\
	<div class='appline name'>\
		<div class='left'>名称</div>\
		<div class='right'><input type='text' name='name' value='{{data.name}}'/></div>\
		<div style='clear:both;'></div>\
	</div>\
	<div class='appline desc'>\
		<div class='left'>描述</div>\
		<div class='right'><input type='text' name='desc' value='{{data.desc}}'/></div>\
		<div style='clear:both;'></div>\
	</div>\
	<div class='appline icon'>\
		<div class='left'>应用图标</div>\
		<div class='right'><input type='text' name='icon' class=\'app_edit_select_icon_input\' value='{{data.icon}}'/>\
			<button class='btn btn-default btn-sm open app_edit_select_icon btn-right'>\
				<i class=\'font-icon icon-folder-open\'></i>\
			</button>\
		</div>\
		<div style='clear:both;'></div>\
	</div>\
	<div class='appline group'>\
		<div class='left'>应用分组</div>\
		<div class='right'>\
		<select name='group'>\
			<option value ='others'>其他</option>\
			<option value ='game'>游戏</option>\
			<option value ='tools'>工具</option>\
			<option value ='reader'>阅读</option>\
			<option value ='movie'>影视</option>\
			<option value ='music'>app_group_music</option>\
			<option value ='life'>生活</option>\
		<select>\
		</div>\
		<div style='clear:both;'></div>\
	</div>\
	<div class='appline type'>\
		<div class='left'>类型</div>\
		<div class='right'>\
			<input class='w20 kui-radio size-small' type='radio' id='url{{uuid}}' apptype='url' value='url' name='{{uuid}}type' {{if data.type=='url'}}checked='checked'{{/if}}/>\
			<label for='url{{uuid}}'>链接</label>\
		<input class='w20 kui-radio size-small' type='radio' id='app{{uuid}}' apptype='app' value='app' name='{{uuid}}type' {{if data.type=='app'}}checked='checked'{{/if}}/>\
			<label for='app{{uuid}}'>js扩展</label>\
			<input class='w20 kui-radio size-small' type='radio' id='app_link{{uuid}}' apptype='app_link' value='app_link' name='{{uuid}}type' {{if data.type=='app_link'}}checked='checked'{{/if}}/>\
			<label for='app_link{{uuid}}'>快捷方式</label>\
		</div>\
		<div style='clear:both;'></div>\
	</div>\
	<div class='appline' data-type='url'>\
		<div class='left'>外观</div>\
		<div class='right'>\
			<input class='w20 kui-checkbox size-small' type='checkbox' id='simple{{uuid}}' name='simple' {{if data.simple}}checked='true'{{/if}} />\
			<label for='simple{{uuid}}'>无边框(选中即无边框)</label>\
			<input class='w20 kui-checkbox size-small' type='checkbox' id='resize{{uuid}}' name='resize' {{if data.resize}}checked='true'{{/if}} />\
			<label for='resize{{uuid}}'>调整大小(选中即可调整)</label>\
		</div>\
		<div style='clear:both;'></div>\
	</div>\
	<div class='appline' data-type='url'>\
		<div class='left'>尺寸</div>\
		<div class='right'>\
			<input class='w30' type='text' name='width'  value='{{data.width}}'/>(宽)&nbsp;&nbsp;\
			<input class='w30' type='text' name='height' value='{{data.height}}'/>(高)\
	\
			<input class='w20 kui-checkbox size-small size-full' type='checkbox' id='size-full{{uuid}}' \
				{{if data.width=='100%' && data.height=='100%'}}checked='true'{{/if}} />\
			<label for='size-full{{uuid}}'>全屏</label>\
		</div>\
		<div style='clear:both;'></div>\
	</div>\
	<div class='appline content'>\
		<div class='left hidden' data-type='app'>js代码</div>\
		<div class='left hidden' data-type='app_link'>js代码</div>\
		<div class='left' data-type='url'>链接地址</div>\
		<div class='right'><textarea name='content'>{{data.content}}</textarea></div>\
		<div style='clear:both;'></div>\
	</div>\
</div>"
);
