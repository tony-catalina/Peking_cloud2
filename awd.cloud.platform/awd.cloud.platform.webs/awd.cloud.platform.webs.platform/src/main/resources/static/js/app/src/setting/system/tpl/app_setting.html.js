define("app/src/setting/system/tpl/app_setting.html",[],
		'<div class="section group_editor app_setting">\
			{{each allsetting as value i}}\
			{{if value.provalue.indexOf(",")==-1 }}\
			<div class="together input">\
				<div class="title"><i>{{value.prolist}}</i></div>\
				<input type="text" name="{{value.proname}}" data-before="" value="{{mysetting[value.proname]}}">\
				<div style="clear:both;"></div>\
			</div>\
			{{else}}\
			<div class="together">\
				<div class="title" ><i>{{value.prolist}}</i></div>\
				<div class="tagdiv">\
					{{each value.provalue.split(\',\') as values key}}\
					<a class="tag" href="javascript:;" data-role="explorer:mkfile;app:user_app">\
						<input type="radio" name="{{value.proname}}" class=" kui-checkbox size-smallx blue" {{if mysetting[value.proname]==values.substring(0,values.indexOf(":"))}}checked="checked" {{/if}} value="{{values.substring(0,values.indexOf(":"))}}" disabled=""><span>\
						{{if values.indexOf(":")==-1 }}\
							{{values}}\
						{{else}}\
							{{values.substring(values.indexOf(":"))}}\
						{{/if}}\
						</span>\
					</a>\
					{{/each}}\
					<div style="clear:both;"></div>\
				 </div>\
				 <div style="clear:both;"></div>\
			</div>\
			{{/if}}\
			{{/each}}\
			<div class="form_row from_row_submit">\
				<button class="btn btn-primary app_setting_save_button" data-action="app_setting_save">保存提交</button>\
			</div>\
		</div>');