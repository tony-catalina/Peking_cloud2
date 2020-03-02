define("app/common/tpl/share.html", [],
'<div class=\'content_box\'>\
	<div class=\'title\'>\
		<div class="titleinfo">资源共享</div>\
		<div class="share_view_info"></div>\
	</div>\
	<div class=\'content_info\'>\
		<div class="input_line">\
			<span class="input_title">共享路径:</span>\
			<input id="share_name" type="text" name="path" value="" />\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line">\
			<span class="input_title">共享标题:</span>\
			<input type="hidden" name="sid"/>\
		<input type="hidden" name="type"/>\
			<input type="hidden" name="name"/>\
			<input id="share_name" type="text" placeholder="共享标题" name="show_name"/>\
			<a href="javascript:void(0);" class="share_more">更多<b class="caret"></b></a>\
			<div style="clear:both"></div>\
		</div>\
	\
		<div class="share_setting_more hidden">\
			<div class="input_line">\
				<span class="input_title">到期时间:</span>\
				<input id="share_time" type="text" placeholder="到期时间" name="time_to"/>\
				<i class="desc">为空则不设置</i>\
				<div style="clear:both"></div>\
			</div>\
			<div class="input_line">\
				<span class="input_title">提取密码:</span>\
				<input type="text" placeholder="提取密码" name="share_password"/>\
				<i class="desc">为空则不设置密码</i>\
				<div style="clear:both"></div>\
			</div>\
			<div class="input_line share_others">\
				<span class="input_title">其他:</span>\
				<label class="label_code_read">\
					<input type="checkbox" name="code_read" class="kui-checkbox size-small" value="">\
					<span>代码阅读</span>\
				</label>\
				<label>\
					<input type="checkbox" name="not_download" class="kui-checkbox size-small" value="">\
					<span>禁止下载</span>\
				</label>\
				<label class="label_can_upload">\
					<input type="checkbox" name="can_upload" class="kui-checkbox size-small" value="">\
					<span>允许上传</span>\
				</label>\
	\
				<div style="clear:both"></div>\
			</div>\
		</div>\
	\
		<div class="input_line share_has_url clear">\
			<span class="input_title">共享地址:</span>\
			<div class="input-group">\
			  <input type="text" class="share_url" aria-label="Text input with segmented button dropdown">\
			  <div class="input-group-btn">\
				<button type="button" class="btn btn-default open_window">打开</button>\
				<button type="button" class="btn btn-default qrcode"><i class="icon-qrcode"></i></button>\
			  </div>\
			  <!-- <div class="share_jiathis_box"></div> -->\
			</div>\
			<div style="clear:both"></div>\
		</div>\
	</div>\
	<div class="share_action">\
		<button type="button" class="btn btn-primary share_create_button">创建公开链接</button>\
		<a type="button" href="javascript:void(0);" class="share_remove_button">取消共享</a>\
	</div>\
</div>'
);
