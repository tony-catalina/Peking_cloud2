define("app/src/setting/system/base/hjs.html", [],
		'<div class=\'content_box\'>\
				<div class=\'title\'>\
					<div class="titleinfo">\
						<i class="font-icon icon-plus"></i>创建房间\
					</div>\
				</div>\
				<div class=\'content_info\'>\
					<input  type="hidden" name="id" value="{{info.id}}" />\
					<div class="input_line">\
						<span class="input_title">房间号<font color="red">*</font>:</span>\
						<input  type="text" name="fjhm" value="{{info.fjhm}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">房间类型<font color="red">*</font>:</span>\
						<select class="form-control" name="fjlx" style="width:135px;height:23px;" >\
							<option value="1" {{if info.fjlx=="1"}} selected {{/if}}>提审室</option>\
							<option value="2" {{if info.fjlx=="2"}} selected {{/if}}>律师会见室</option>\
							<option value="2" {{if info.fjlx=="3"}} selected {{/if}}>家属会见室</option>\
						</select>\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">房间名称<font color="red">*</font>:</span>\
						<input  type="text" name="fjmc" value="{{info.fjmc}}" />\
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
					<button type="button" class="btn btn-primary" id="fjsz_save">保存</button>\
				</div>\
		</div>');