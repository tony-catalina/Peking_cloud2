define("app/src/setting/page/user.html", [],
'<div class="nav">\
	<a href="javascript:;"  class="this" data-page="setting_basic">基础设置</a>\
	<a href="javascript:;" class="" data-page="setting_menu">修改密码</a>\
	<div style="clear:both;"></div>\
</div>\
\
<div class="panel panel-default setting_basic">\
	<div class="panel-body">\
		<div class="form_row">\
			<div class=\'setting_title\'>开启回收站:</div>\
			<div class="setting_content">\
				<label class="disable-ripple">\
					<input type="checkbox" class="kui-checkbox-ios size-big" name="recycle_open"\
					 {{if data.user.recycle_open==\'1\'}}checked="checked"{{/if}}/><em></em>\
				</label>\
			</div>\
		</div>\
	\
		<div class="form_row">\
			<div class=\'setting_title\'>开启动画:</div>\
			<div class="setting_content">\
				<label class="disable-ripple">\
					<input type="checkbox" class="kui-checkbox-ios size-big" name="animate_open"\
					 {{if data.user.animate_open !=\'0\'}}checked="checked"{{/if}}/><em></em>\
				</label>\
			</div>\
		</div>\
	\
		<div class="form_row">\
			<div class=\'setting_title\'>开启音效:</div>\
			<div class="setting_content">\
				<label class="disable-ripple">\
					<input type="checkbox" class="kui-checkbox-ios size-big" name="sound_open"\
					 {{if data.user.sound_open ==\'1\'}}checked="checked"{{/if}}/><em></em>\
				</label>\
			</div>\
		</div>\
	\
		<div class="form_row">\
			<div class=\'setting_title\'>同名文件处理:</div>\
			<div class="setting_content">\
				<label>\
					<input type="radio" class="kui-radio" name="file_repeat" value="rename" {{if data.user.file_repeat==\'rename\'}}checked="checked"{{/if}}/>\
					<span>重命名</span>\
				</label>\
				<label>\
					<input type="radio" class="kui-radio" name="file_repeat" value="replace" {{if data.user.file_repeat==\'replace\'}}checked="checked"{{/if}}/>\
					<span>覆盖</span>\
				</label>\
				<label>\
					<input type="radio" class="kui-radio" name="file_repeat" value="skip" {{if data.user.file_repeat==\'skip\'}}checked="checked"{{/if}}/>\
					<span>跳过</span>\
				</label>\
				<div style="clear:both"></div>\
			</div>\
		</div>\
		\
	 <div class="form_row">\
		<div class=\'setting_title\'>默认打开应用:</div>\
		<div class="setting_content" style="height:30px">\
			<div class="layui-form" style="width:200px">\
			<select  id="appSelect" lay-filter="appSelect" style="width:200px">\
			</select>\
			</div>\
			<div style="clear:both"></div>\
		</div>\
		</div>\
		\
	</div>\
</div>\
\
\
<div class="panel panel-default setting_menu hidden">\
	<div class="panel-body">\
		<div class="form_row">\
			<div class=\'setting_title\'>原密码:</div>\
			<div class="setting_content"><input type="password" id="password_now" value="" /></div>\
		</div>\
	\
		<div class="form_row">\
			<div class=\'setting_title\'>新密码:</div>\
			<div class="setting_content"><input type="password" id="password_new" value=""/></div>\
		</div>\
		<div class="form_row">\
		<div class=\'setting_title\'>确认密码:</div>\
		<div class="setting_content"><input type="password" id="password_new_second" value=""/></div>\
	</div>\
	\
		<!-- 提交 -->\
		<div class="form_row">\
			<div class=\'setting_title\'></div>\
			<div class="setting_content">\
				<button onclick="Setting.tools();" class="btn btn-primary save">保存</button>\
			</div>\
		</div>\
	</div>\
</div>');
