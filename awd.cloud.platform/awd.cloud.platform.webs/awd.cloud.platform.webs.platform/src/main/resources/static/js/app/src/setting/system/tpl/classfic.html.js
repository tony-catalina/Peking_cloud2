define("app/src/setting/system/tpl/classfic.html", [],
'<div class=\'content_box\'>\
	<div class=\'title\'>\
		<div class="titleinfo">\
			<i class="font-icon icon-group"></i>新建分类\
		</div>    \
	</div>\
    <div class=\'content_info\'>\
        <input id="id" type="hidden" name="id" value="{{info.id}}" value="{{info.id}}" />\
		<div class="input_line">\
			<span class="input_title">类型<font style=\'color:red\'>*</font>:</span>\
			<input id="lx" type="text" name="lx" value="{{info.lx}}" value="{{info.lx}}" />\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line">\
			<span class="input_title">父类编号<font style=\'color:red\'>*</font>:</span>\
			<input id="code" type="text" name="parentid" value="{{info.parentid}}" />\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line">\
			<span class="input_title">分类编号<font style=\'color:red\'>*</font>:</span>\
			<input id="name" type="text" name="classid" value="{{info.classid}}" />\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line">\
			<span class="input_title">名称<font style=\'color:red\'>*</font>:</span>\
			<input id="desc" type="text" name="name" value="{{info.name}}" />\
			<div style="clear:both"></div>\
		</div>\
	</div>\
	<div class="share_action">\
		<button type="button" class="btn btn-primary" id="system_classfic_save">保存</button>\
	</div>\
</div>'		
);		