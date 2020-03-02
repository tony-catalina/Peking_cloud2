define("app/src/setting/system/tpl/xxdm.html", [],
'<div class=\'content_box\'>\
	<div class=\'title\'>\
		<div class="titleinfo">\
			<i class="font-icon icon-group"></i>数据字典\
		</div>    \
	</div>\
	<div class=\'content_info\'>\
		<input id="id"  name="id" value="{{info.id}}" type="hidden"/>\
		<div class="input_line">\
			<span class="input_title">监所类型<font style=\'color:red\'>*</font>:</span>\
			<input id="jslx"  name="jslx" value="{{info.jslx}}" type="hidden" />\
			<div class="btn-group select_drop_menu">\
			  	<button {{if info.is_admin=="0"}} disabled {{/if}} class="btn btn-default btn-xs" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\
				<span class="xxdm_title pr-5">\
				{{if jslx[info.jslx]}}\
				{{jslx[info.jslx]}}\
				{{else}}\
				<i>选择</i>\
				{{/if}}\
				</span><span class="caret"></span>\
			  </button>\
			  <ul class="dropdown-menu">\
				<li><a href="javascript:void(0);" {{if info.jslx=="1"}}class="selected" {{/if}} data-role-id="1">看守所</a></li>\
				<li><a href="javascript:void(0);" {{if info.jslx=="2"}}class="selected" {{/if}} data-role-id="2">拘留所</a></li>\
				<li><a href="javascript:void(0);" {{if info.jslx=="3"}}class="selected" {{/if}} data-role-id="3">戒毒所</a></li>\
				<!--<li><a href="javascript:void(0);" {{if info.jslx=="4"}}class="selected" {{/if}} data-role-id="4">收教所</a></li>-->\
				<li><a href="javascript:void(0);" {{if info.jslx=="5"}}class="selected" {{/if}} data-role-id="5">安康医院</a></li>\
			  </ul>\
			</div>\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line">\
			<span class="input_title">字典类型<font style=\'color:red\'>*</font>:</span>\
			<input id="fieldname" {{if info.is_admin=="0"}} disabled {{/if}} type="text" name="fieldname" value="{{info.fieldname}}"/>\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line">\
			<span class="input_title">字典代码<font style=\'color:red\'>*</font>:</span>\
				<input id="code" {{if info.is_admin=="0"}} disabled {{/if}} type="text" name="code" value="{{info.code}}"/>\
			<div style="clear:both"></div>\
		</div>\
		<div class="input_line">\
			<span class="input_title">字典内容<font style=\'color:red\'>*</font>:</span>\
			<input id="content" type="text" name="content" value="{{info.content}}" />\
			<div style="clear:both"></div>\
		</div>\
	</div>\
	<div class="share_action">\
		<button type="button" class="btn btn-primary" id="system_xxdm_save">保存</button>\
	</div>\
</div>'		
);