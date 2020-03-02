define("app/src/setting/system/tpl/user_import.html", [],
'<div class=\'content_box\'>\
<div class=\'title\'>\
	<div class="titleinfo">\
		<i class="font-icon icon-group" id="pltj" value="批量添加"></i>批量添加\
	</div>\
</div>\
<div class=\'content_info\'>\
	<div class="input_line">\
		<span class="input_title">用户名<font style=\'color:red\'>*</font>:</span>\
		<textarea id="name" type="text" name="name" value="{{user_info.name}}"></textarea>\
		<i class="desc" style="position: absolute;line-height:1.5em;" ><span id="mhyh">用户名用","分隔,否则将被视为一个用户,</span> <br\/>已存在的会导致添加失败</i>\
		<div style="clear:both"></div>\
	</div>\
		<div class="input_line">\
		<span class="input_title">密码<font style=\'color:red\'>*</font>:</span>\
		<input id="password" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,\'\')" name="password" \
		value="{{if !user_info.name}}{{user_info.password}}{{/if}}" \
		placeholder="{{if user_info.name}}不填则不修改{{/if}}"/>\
		{{if user_info.name}}<i class="desc">不填则不修改</i>{{/if}}\
		<div style="clear:both"></div>\
    </div>\
    <div class="input_line">\
		<span class="input_title">角色<font style=\'color:red\'>*</font>:</span>\
		<input id="role" type="hidden" name="role" value="{{user_info.role}}" />\
		<div class="dlg_group_display">\
			<div class="cell"></div>\
			<button class="btn btn-default btn-sm dlg_role_select" type="button">\
				<i class="font-icon icon-pencil"></i><span class="group_title pl-10">编辑角色</span>\
			</button>\
		</div>\
		<div style="clear:both"></div>\
	</div>\
	<div class="input_line">\
		<span class="input_title">用户组<font style=\'color:red\'>*</font>:</span>\
		<input type="hidden" id="group" name="group" value="{{user_info.group}}"/>\
		<div class="btn-group select_drop_menu">\
		  <button class="btn btn-default btn-xs" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" disabled="disabled">\
			<span class="role_title pr-5">\
			{{if group_list[user_info.group]}}\
			{{group_list[user_info.group].name}}\
			{{else}}\
			<i>选择</i>\
			{{/if}}\
			</span><span class="caret"></span>\
		  </button>\
		  <ul class="dropdown-menu">\
			{{each group_list as value key}}\
				{{if key==user_info.group}}\
				<li><a href="javascript:void(0);" class="selected" data-role-id="{{key}}">{{value.name}}</a></li>\
				{{else}}\
				<li><a href="javascript:void(0);" data-role-id="{{key}}">{{value.name}}</a></li>\
				{{/if}}\
			{{/each}}\
		  </ul>\
		</div>\
	</div>\
	<input id="isadmin" type="hidden" name="isadmin" value="0" />\
</div>\
\
<div class="share_action">\
	<button type="button" class="btn btn-primary" id="system_save">添加</button>\
</div>\
</div>');
