define("app/src/setting/system/tpl/group_app_menu.html", [],
	'<div class="section group_editor app_setting">\
		{{each allmenu as value i}}\
		<div class="together">\
			<div class="title" ><i>{{i}}:</i></div>\
			<div class="tagdiv">\
				{{if  menus=allmenu[i]}} {{/if}}\
				{{each menus as menu key}}\
				{{if hasmenu=groupmenu[menu.id]}} {{/if}}\
				<a class="tag {{if hasmenu }}this{{/if}}" href="javascript:;" data-role="explorer:mkfile;app:user_app">\
					<input type="checkbox" name="{{menu.menu}}" class=" kui-checkbox size-smallx blue" {{if hasmenu }}checked="checked" {{/if}}   value="{{menu.id}}" ><span>\
						{{menu.menuname}}\
					</span>\
				</a>\
				{{/each}}\
				<div style="clear:both;"></div>\
			 </div>\
			 <div style="clear:both;"></div>\
		</div>\
		{{/each}}\
	</div>');