define("app/src/setting/page/system.html", [],
'<div class="nav">{{if G.is_admin==\'1\'}}\
    <a href="javascript:;" class="this" data-page="setting">系统设置</a>\
    <a href="javascript:;" class="" data-page="setting_role">系统角色</a>\
	<a href="javascript:;" class="" data-page="setting_menu">菜单管理</a>{{/if}}\
	<a href="javascript:;" class="" data-page="setting_classfic">分类管理</a>\
	<a href="javascript:;" class="" data-page="setting_xxdm">数据字典</a>\
    <a href="javascript:;" class="" data-page="setting_wgzd">违规字典</a>\
	<a href="javascript:;" class="" data-page="setting_flow">业务流程</a>\{{if G.is_admin==\'1\'}}\
	<a href="javascript:;" class="" data-page="setting_node">业务节点</a>\{{/if}}\
    <div style="clear:both;"></div>\
</div>\
\
<div class="panel panel-default setting system_setting">\
	<div class="panel-body ">\
		<div class="form_row">\
			<div class=\'setting_title\'>程序名称<font style=\'color:red\'>*</font>:</div>\
			<div class="setting_content">\
				<input type="text" name="system_name" value="{{data.system_name | window.htmlEncode}}" /><i class="desc">程序logo标题</i>\
			</div>\
		</div>\
\
		<div class="form_row">\
			<div class=\'setting_title\'>程序描述<font style=\'color:red\'>*</font>:</div>\
			<div class="setting_content">\
				<input type="text" name="system_desc" value="{{data.system_desc}}" /><i class="desc">程序描述</i>\
			</div>\
		</div>\
		<div class="form_row">\
			<div class=\'setting_title\'>目录排除:</div>\
			<div class="setting_content">\
				<input type="text" name="path_hidden" value="{{data.path_hidden}}" /><i class="desc">默认不显示的目录和文件,逗号隔开</i>\
			</div>\
		</div>\
		<div class="form_row">\
			<div class=\'setting_title\'>新用户默认创建目录<font style=\'color:red\'>*</font>:</div>\
			<div class="setting_content">\
				<input type="text" name="new_user_folder" value="{{data.new_user_folder}}" /><i class="desc">用逗号隔开</i>\
			</div>\
		</div>\
\
		<div class="form_row">\
			<div class=\'setting_title\'>登录后默认进入<font style=\'color:red\'>*</font>:</div>\
			<div class="setting_content">\
				<label><input type="radio" name="first_in" value="desktop" class="kui-radio"\
					{{if data.first_in==\'desktop\'}}checked="checked"{{/if}}/>\
					<span>桌面</span>\
				</label>\
				<label><input type="radio" name="first_in" value="explorer" class="kui-radio"\
					{{if data.first_in==\'explorer\'}}checked="checked"{{/if}}/>\
					<span>文件管理</span>\
				</label>\
				<label><input type="radio" name="first_in" value="search"  class="kui-radio"\
					{{if data.first_in==\'search\'}}checked="checked"{{/if}}/>\
					<span>超级搜索</span>\
				</label>\
				<label><input type="radio" name="first_in" value="message"  class="kui-radio"\
					{{if data.first_in==\'message\'}}checked="checked"{{/if}}/>\
					<span>信息中心</span>\
				</label>\
			</div>\
		</div>\
\
		<!-- 提交 -->\
		<div class="form_row from_row_submit">\
			<div class="setting_content">\
				<button class="btn btn-primary system_save">保存</button>\
			</div>\
		</div>\
	</div>\
</div>\
\
<div class="panel panel-default setting_menu hidden">\
	<table id="list" align="center" border="0" cellspacing="0" cellpadding="0">\
		<tbody>\
		<tr class="title">\
			<td width="10%">菜单名</td>\
			<td>url地址<span>(url地址或js代码)</span></td>\
			<td>操作</td>\
		</tr>\
\
		{{if data.menu.push({\'nameString\':\'\',\'type\':\'\',\'url\':\'\',\'target\':\'_blank\',\'use\':\'1\'}) }}{{/if}}\
		{{each data.menu as value key}}\
		{{if menu_system = value[\'type\'] == \'system\' ? \'menu_system\':\'\'}}{{/if}}\
		{{if menu_show = value[\'use\']  == \'1\' ? \'menu_show\':\'menu_hidden\'}}{{/if}}\
		{{if menu_add = value[\'name\']  == \'\' ? \'menu_default hidden\':\'\'}}{{/if}}\
		<tr class="menu_list {{menu_system}} {{menu_show}} {{menu_add}}">\
            <td class="name">\
                <input type="hidden" name="name" value="{{value.name | menu_info_decode}}"/>\
				<input type="text" name="nameString" value="{{value.nameString | menu_info_decode}}"/>\
				<span>{{value.nameString}}</span>\
			</td>\
			<td class="url">\
				<input type="text" name="url" value="{{value.url | menu_info_decode}}">\
				<span>{{value.url}}</span>\
				<label>\
					<input type="checkbox" name="target" class="kui-checkbox size-small" value="{{value.target}}"\
					{{if value.target==\'_blank\'}}checked="checked"{{/if}}/>\
					<span>新窗口打开</span>\
				</label>\
			</td>\
			<td class="action">\
				<button class=\'btn btn-default btn-sm move_up\'><i class="font-icon icon-arrow-up"></i></button>\
				<button class=\'btn btn-default btn-sm move_down\'><i class="font-icon icon-arrow-down"></i></button>\
				<button class=\'btn btn-default btn-sm move_hidden\'>\
					{{if value.use==\'1\'}} 隐藏 {{else}} 显示 {{/if}}\
				</button>\
				<button class=\'btn btn-default btn-sm move_del\'>删除</button>\
			</td>\
		</tr>\
		{{/each}}\
		</tbody>\
	</table>\
	\
	<a href="javascript:void(0)" class="add system_menu_add "><i class="icon-plus pr-10"></i>添加</a>\
	<div class="form_row">\
		<button class="btn btn-primary system_menu_save">保存</button>\
	</div>\
</div>\
\
<div class=\'class="panel panel-default setting_classfic hidden \'>\
	<div class="panel setting_classfic system_conennt" style="margin-top:52px;">\
		<div class="left_freame" >\
			<div id="classficTree"  class="ztree"></div>\
		</div>\
		<div class="right_frame" id="content_system_classfic">\
			<div class="user_tool_bar">\
				<div class="btn-group btn-group-sm ml-10">\
					<input type="text" name="classficseach"  placeholder="名称"  style="float:left;height:26px;"/>\
					<button class="btn btn-default  title="搜索"  data-action="classfic_search">\
						<i class="font-icon icon-search">搜索</i>\
					</button>\
				</div>\
				<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
					<button class="btn btn-default" data-action="classfic_add">新建</button>\
					<button class="btn btn-default" data-action="classfic_edit">修改</button>\
					<button class="btn btn-default" data-action="classfic_delete">删除</button>\
				</div>\
			</div>\
			<div class="user_list">\
				<table id="classficTable" ></table>\
			</div>\
		</div>\
	</div>\
</div>\
\
<div class=\'class="panel panel-default setting_xxdm hidden \'>\
	<div class="panel setting_xxdm system_conennt" style="margin-top:52px;">\
		<div class="left_freame">\
			<div id="xxdmList"  class="ztree"></div>\
		</div>\
		<div class="right_frame" id="content_system_xxdm">\
			<div class="user_tool_bar">\
				<div class="btn-group btn-group-sm ml-10">\
					<input type="text" name="xxdmseach"  placeholder="字典内容"  style="float:left;height:26px;"/>\
					<button class="btn btn-default  title="搜索"  data-action="xxdm_search">\
						<i class="font-icon icon-search">搜索</i>\
					</button>\
				</div>\
				<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
					<button class="btn btn-default" {{if G.is_admin==\'0\'}} style="display:none" {{/if}} data-action="xxdm_add">新建</button>\
					<button class="btn btn-default" data-action="xxdm_edit">修改</button>\
					<button class="btn btn-default" {{if G.is_admin==\'0\'}} style="display:none" {{/if}} data-action="xxdm_delete">删除</button>\
				</div>\
				{{if G.is_admin==\'1\'}}\
				<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
					<button class="btn btn-default" data-action="xxdm_nomodify">禁止修改</button>\
					<button class="btn btn-default" data-action="xxdm_modify">可修改</button>\
				</div>\
				{{/if}}\
			</div>\
			<div class="user_list">\
				<table id="xxdmTableRow" ></table>\
			</div>\
		</div>\
	</div>\
</div>\
\
<div class=\'class="panel panel-default setting_wgzd hidden \'>\
	<div class="panel  setting_wgzd system_conennt" style="margin-top:52px;">\
		<div class="left_freame">\
			<div id="wgzdList"  class="ztree"></div>\
		</div>\
		<div class="right_frame" id="content_system_wgzd">\
			<div class="user_tool_bar">\
				<div class="btn-group btn-group-sm ml-10">\
					<input type="text" name="wgzdseach"  style="float:left;height:26px;"/>\
					<button class="btn btn-default  id="wgzd_search" data-action="wgzd_search" title="搜索" type="button">\
						<i class="font-icon icon-search">搜索</i>\
					</button>\
				</div>\
				<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
					<button class="btn btn-default" {{if G.is_admin==\'0\'}} style="display:none"  {{/if}} data-action="wgzd_add">新建</button>\
					<button class="btn btn-default" data-action="wgzd_edit">修改</button>\
					<button class="btn btn-default" {{if G.is_admin==\'0\'}} style="display:none;" {{/if}} data-action="wgzd_delete">删除</button>\
				</div>\
				{{if G.is_admin==\'1\'}}\
				<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
					<button class="btn btn-default" data-action="wgzd_nomodify">禁止修改</button>\
					<button class="btn btn-default" data-action="wgzd_modify">可修改</button>\
				</div>\
		 		{{/if}}\
			</div>\
			<div class="user_list">\
				<table id="wgzdTableRow" ></table>\
			</div>\
		</div>\
	</div>\
</div>\
\
<div class=\'class="panel panel-default setting_flow hidden \'>\
	<div class="panel  setting_flow system_conennt" style="margin-top:52px;">\
		<div  id="content_system_flow">\
			<div class="user_tool_bar" style="margin-top:10px;">\
				<div class="btn-group btn-group-sm ml-10">\
					<input type="text" name="flowmapseach"  style="float:left;height:26px;"/>\
					<button class="btn btn-default  id="flowmap_search" data-action="flowmap_search" title="搜索" type="button">\
						<i class="font-icon icon-search">搜索</i>\
					</button>\
				</div>\
				<div class="btn-group btn-group-sm button_aciton_muti ml-10">\{{if G.is_admin==\'1\'}}\
					<button class="btn btn-default" data-action="flow_muntex">互斥流程</button>\{{/if}}\
					<button class="btn btn-default" data-action="flow_timelimit">间隔时间限制</button>\
					<button class="btn btn-default" data-action="flow_monthlimit">月重复次数限制</button>\
				</div>\
			</div>\
			<div class="user_list">\
				<table id="flowmapTableRow" ></table>\
			</div>\
		</div>\
	</div>\
</div>\
\
<div class=\'class="panel panel-default setting_node hidden \'>\
	<div class="panel  setting_node system_conennt" style="margin-top:52px;">\
		<div class="left_freame">\
			<div id="flowmapTree"  class="ztree"></div>\
		</div>\
		<div class="right_frame" id="content_system_node">\
			<div class="user_tool_bar">\
				<div class="btn-group btn-group-sm ml-10">\
					<input type="text" name="flownodeseach"  style="float:left;height:26px;"/>\
					<button class="btn btn-default  id="flownode_search" data-action="node_search" title="搜索" type="button">\
						<i class="font-icon icon-search">搜索</i>\
					</button>\
				</div>\
				<div class="btn-group btn-group-sm button_aciton_muti ml-10">\
					<button class="btn btn-default" data-action="node_bindmenu">绑定菜单</button>\
				</div>\
			</div>\
			<div class="user_list">\
				<table id="flownodeTableRow" ></table>\
			</div>\
		</div>\
	</div>\
</div>\
\
<div class="panel panel-default setting_role hidden">\
		<div class="content role_liser_content">\
		</div>\
</div>'	
);
