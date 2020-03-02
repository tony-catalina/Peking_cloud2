define("app/src/setting/system/base/ls.html", [],
		'<div class=\'content_box\'>\
				<div class=\'title\'>\
					<div class="titleinfo">\
						<i class="font-icon icon-plus"></i>登记律师\
					</div>\
				</div>\
				<div class=\'content_info\'>\
					<input  type="text" name="id" value="{{info.id}}" hidden/>\
					<div class="input_line">\
						<span class="input_title">律师证号<font color="red">*</font>:</span>\
						<input id="name" type="text" name="lszh" value="{{info.lszh}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">姓名<font color="red">*</font>:</span>\
						<input id="name" type="text" name="xm" value="{{info.xm}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">单位:</span>\
						<input id="name" type="text" name="dw" value="{{info.dw}}" />\
						<div style="clear:both"></div>\
					</div>\
				</div>\
				\
				<div class="share_action">		\
					<button type="button" class="btn btn-primary" id="ls_save">保存</button>\
				</div>\
		</div>');