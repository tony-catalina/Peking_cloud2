define("app/src/setting/system/base/bar.html", [],
		'<div class=\'content_box\'>\
				<div class=\'title\'>\
					<div class="titleinfo">\
						<i class="font-icon icon-plus"></i>登记办案人\
					</div>\
				</div>\
				<div class=\'content_info\'>\
					<input  type="text" name="id" value="{{info.id}}" hidden/>\
					<div class="input_line">\
						<span class="input_title">身份证号<font color="red">*</font>:</span>\
						<input id="name" type="text" name="sfzh" value="{{info.sfzh}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">姓名<font color="red">*</font>:</span>\
						<input id="name" type="text" name="xm" value="{{info.xm}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">单位类型:</span>\
						<select class="form-control" name="dwlx" style="width:135px;height:23px;">\
							<option value="1">公安机关</option>\
							<option value="2">检察院</option>\
							<option value="3">法院</option>\
							<option value="4">安全机关</option>\
							<option value="9">其他</option>\
						</select>\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">单位<font color="red">*</font>:</span>\
						<input id="name" type="text" name="badw" value="{{info.badw}}" />\
						<div style="clear:both"></div>\
					</div>\
				</div>\
				\
				<div class="share_action">		\
					<button type="button" class="btn btn-primary" id="bar_save">保存</button>\
				</div>\
		</div>');