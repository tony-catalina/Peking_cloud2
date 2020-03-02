define("app/src/setting/system/tpl/roleflow.html", [],
	'<div class="section group_editor app_setting">\
		{{each allflow as value i}}\
		<div class="together">\
			<div class="title" ><i>{{value.memo}}:</i></div>\
			<div class="tagdiv">\
				{{if  nodes=allnode[value.id]}} {{/if}}\
				{{each nodes as values key}}\
				{{if hasrole=values.role.indexOf(rolecode)>-1}} {{/if}}\
				<a class="tag {{if hasrole }}this{{/if}}" href="javascript:;" data-role="explorer:mkfile;app:user_app">\
					<input type="checkbox" name="{{values.nodename}}" class=" kui-checkbox size-smallx blue" {{if hasrole }}checked="checked" {{/if}}   value="{{values.id}}" ><span>\
						{{values.nodename}}\
					</span>\
				</a>\
				{{/each}}\
				<div style="clear:both;"></div>\
			 </div>\
			 <div style="clear:both;"></div>\
		</div>\
		{{/each}}\
	</div>\
	<div class="form_row from_row_submit">\
		<button class="btn btn-primary role_save_button" id="roleflow_save">保存提交</button>\
		<button class="btn btn-link revert" data-action="revert_all">全选\/反选</button>\
	</div>');