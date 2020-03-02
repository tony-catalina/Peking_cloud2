define("app/src/setting/page/member.html", [],
'<div class="system_conennt">\
<div class="left_freame">\
	<div class="left_header">\
        <div class="tab this" id="system_group">分组管理</div>\
        {{if (G.user_type=="1"||G.user_type=="2" ||G.user_type=="3"||G.user_type=="4")&&G.is_root=="1" }}\
        <div class="tab" id="system_role">角色流程</div>\
        {{/if}}\
		<div style="clear:both"></div>\
	</div>\
	<div class="left_content system_group">\
		<div id="folderList"  class="ztree"></div>\
	</div>\
\
	<div class="left_content system_role">\
		<div class="role_box">\
			<ul class="role_list_cell"></ul>\
		</div>\
	</div>\
</div>\
<!-- left_frame end -->\
\
<div class="right_frame" id="content_system_group">\
	<div class="header_content">\
		<div class="group_title">\
			<a href="javascript:void(0);" class="group_title_span title_tooltip" title="编辑" data-action="group_edit">--</a>\
			<span class="label label-info" style="font-size: 12px;"><em class="group_id" hidden></em></span>\
			<a href="javascript:void(0);" class="font-icon-label ml-20 title_tooltip" title="添加子分组" data-action="group_add_child"><i class="font-icon icon-plus"></i></a>\
		</div>\
	</div>\
	<div class="content user_liser_content">\
	</div>\
</div>\
<!-- content_system_group end -->\
\
<div class="right_frame" id="content_system_role">\
	<div class="header_content">\
		<div class="group_title">\
			<span class="role_title"></span>\
			<span class="label label-info" style="font-size: 12px;" ><em class="role_id"></em></span>\
		</div>\
	</div>\
\
	<div class="section group_editor">\
		<div class="together input">\
			<div class="title"><i>角色名</i></div>\
			<input type="text" id=\'name\' data-before="" readOnly="true"/>\
			<div style="clear:both;"></div>\
		</div>\
		<div id="flow"></div>\
	</div>\
\
</div>\
<!-- content_system_role end -->\
</div>\
</div><!-- 父元素结束 -->');
