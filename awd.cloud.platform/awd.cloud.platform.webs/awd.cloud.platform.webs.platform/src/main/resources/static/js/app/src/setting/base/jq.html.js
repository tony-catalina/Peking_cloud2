define("app/src/setting/system/base/jq.html", [],
		'<div class=\'content_box\'>\
				<div class=\'title\'>\
					<div class="titleinfo">\
						<i class="font-icon icon-plus"></i>创建监区\
					</div>\
				</div>\
				<div class=\'content_info\'>\
					<input  type="text" name="id" value="{{info.id}}" hidden/>\
					<div class="input_line">\
						<span class="input_title">监区号<font color="red">*</font>:</span>\
						<input  type="text" name="jqh" value="{{info.jqh}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">监区名<font color="red">*</font>:</span>\
						<input  type="text" name="jqmc" value="{{info.jqmc}}" readOnly="readOnly" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">图标:</span>\
						<select class="form-control" name="jqicons" style="width:135px;height:23px;" onChange="icon_image.src=this.value">\
							<option value="/images/file_icon/icon_app/jq1.png">1</option>\
							<option value="/images/file_icon/icon_app/jq2.png">2</option>\
							<option value="/images/file_icon/icon_app/jq3.png">3</option>\
							<option value="/images/file_icon/icon_app/jq4.png">4</option>\
							<option value="/images/file_icon/icon_app/jq5.png">5</option>\
						</select>\
						<img id="icon_image" src="/images/file_icon/icon_app/jq1.png" style="width:23px;height:23px;margin-left:88px;">\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">备注:</span>\
						<textarea class="form-control" name="bz" rows="3" style="width:135px;">{{info.bz}}</textarea>\
						<div style="clear:both"></div>\
					</div>\
				</div>\
				\
				<div class="share_action">		\
					<button type="button" class="btn btn-primary" id="jq_save">保存</button>\
				</div>\
		</div>');