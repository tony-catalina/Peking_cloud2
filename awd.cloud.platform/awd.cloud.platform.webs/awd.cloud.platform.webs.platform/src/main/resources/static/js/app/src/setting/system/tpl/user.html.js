define("app/src/setting/system/tpl/user.html", [],
'<div class=\'content_box\'>\
	<div class=\'title\'>\
		<div class="titleinfo">\
			{{if !user_info.name}}\
			<i class="font-icon icon-group"></i>新建用户\
			{{else}}\
			<i class="font-icon icon-pencil"></i>{{user_info.name}}\
			{{/if}}\
		</div>    \
	</div>\
	<div class=\'content_info\'>\
		<input  type="text" name="id" value="{{user_info.id}}" hidden />\
		<div class="input_line">\
			<span class="input_title">用户名<font style=\'color:red\'>*</font>:</span>\
			<input id="name" type="text" name="name" value="{{user_info.username}}" />\
			<i class="desc">用户名</i>\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line">\
			<span class="input_title">密码<font style=\'color:red\'>*</font>:</span>\
			<input id="password" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,\'\')" name="password" \
			value="{{user_info.password}}" \
			placeholder="{{if user_info.name}}不填则不修改{{/if}}"/>\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line ">\
			<span class="input_title">警号<font style=\'color:red\'>*</font>:</span>\
			<input  type="text" name="jh" id="jh" onkeyup="this.value=this.value.replace(/^ +| +$/g,\'\')" value="{{user_info.jh}}" />\
			<i class="desc label label-default" style="color:#698ebf;background:#E9F3F9;"></i>\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line ">\
			<span class="input_title">EMAIL:</span>\
			<input  type="text" name="email" value="{{user_info.email}}" />\
			<i class="desc label label-default" style="color:#698ebf;background:#E9F3F9;"></i>\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line ">\
			<span class="input_title">身份证号<font style=\'color:red\'>*</font>:</span>\
			<input  type="text" name="sfzh" onkeyup="this.value=this.value.replace(/^ +| +$/g,\'\')" id="sfzh" value="{{user_info.sfzh}}" />\
			<i class="desc label label-default" style="color:#698ebf;background:#E9F3F9;"></i>\
			<div style="clear:both"></div>\
        </div>\
        <div class="input_line" id="role_input">\
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
		<div class="input_line" id="group_input">\
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
		<input type="text" name="glybz" value="{{user_info.isadmin}}" hidden/>\
	</div>\
\
	<div class="share_action">\
		{{if !user_info.name}}\
		<button type="button" class="btn btn-primary" id="system_save">添加</button>\
		<button type="button" class="btn btn-primary" id="system_save_goon_add">保存并继续添加</button>\
		{{else}}\
		<button type="button" class="btn btn-primary" id="system_save">保存</button>\
		<a type="button" href="javascript:void(0);" class="remove_button">删除</a>\
		{{/if}}\
	</div>\
</div>');
