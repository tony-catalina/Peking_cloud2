define("app/src/setting/system/base/js.html", [],
		'<div class=\'content_box\'>\
				<div class=\'title\'>\
					<div class="titleinfo">\
						<i class="font-icon icon-plus"></i>创建监室\
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
						<span class="input_title">监室号<font color="red">*</font>:</span>\
						<input  type="text" name="jsh" value="{{info.jsh}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">监室名<font color="red">*</font>:</span>\
						<input  type="text" name="jsmc" value="{{info.jsmc}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">额定押量:</span>\
						<input  type="text" name="bznum" value="{{info.bznum}}" />\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">监室类别<font color="red">*</font>:</span>\
						<select class="form-control" name="jslb" style="width:135px;height:23px;" >\
							<option value="1" {{if info.type=="1"}} selected {{/if}}>普通</option>\
							<option value="2" {{if info.type=="2"}} selected {{/if}}>少年</option>\
							<option value="3" {{if info.type=="3"}} selected {{/if}}>过渡</option>\
							<option value="4" {{if info.type=="4"}} selected {{/if}}>已决</option>\
							<option value="5" {{if info.type=="5"}} selected {{/if}}>重点</option>\
							<option value="6" {{if info.type=="6"}} selected {{/if}}>病号</option>\
						</select>\
						<div style="clear:both"></div>\
					</div>\
					<div class="input_line">\
						<span class="input_title">男监女监<font color="red">*</font>:</span>\
						<select class="form-control" name="type" style="width:135px;height:23px;" >\
							<option value="1" {{if info.type=="1"}} selected {{/if}}>男监</option>\
							<option value="2" {{if info.type=="2"}} selected {{/if}}>女监</option>\
						</select>\
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
					<button type="button" class="btn btn-primary" id="js_save">保存</button>\
				</div>\
		</div>');