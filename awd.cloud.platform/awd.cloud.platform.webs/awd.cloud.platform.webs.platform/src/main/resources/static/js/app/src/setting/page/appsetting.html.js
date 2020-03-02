define("app/src/setting/page/appsetting.html", [],
'<div class="system_conennt">\
	<div class="left_freame">\
		<div class="left_header">\
            <div class="tab this" id="system_appset">应用配置</div>\
            {{if G.user_type!="0"&&G.user_type!="8"&&G.user_type!="9"}}\
            <div class="tab" id="system_baseset">基础配置</div>\
            {{/if}}\
			<div style="clear:both"></div>\
		</div>\
		<div class="left_content system_appset">\
			<div class="role_box">\
				<div id="appTree"  class="ztree"></div>\
			</div>\
		</div>\
	\
		<div class="left_content system_baseset">\
			<div class="role_box">\
				<ul class="role_list_cell">\
					{{if G.is_root=="1"&&G.user_type=="1"}}\
					<li class="role_cell" data-role-id="jqsz"><span>监区设置</span><i class="sub_menu icon-angle-right"></i></li>\
					<li class="role_cell" data-role-id="jssz"><span>监室设置</span><i class="sub_menu icon-angle-right"></i></li>\
					<li class="role_cell" data-role-id="hjssz"><span>会见室设置</span><i class="sub_menu icon-angle-right"></i></li>\
					{{/if}}\
					<li class="role_cell" data-role-id="bar"><span>办案人管理</span><i class="sub_menu icon-angle-right"></i></li>\
					<li class="role_cell" data-role-id="lsgl"><span>律师管理</span><i class="sub_menu icon-angle-right"></i></li>\
				</ul>\
			</div>\
		</div>\
	</div>\
	\
	<div class="right_frame" id="content_system_appset" >\
		<div class="header_content">\
			<div class="group_title">\
				<span class="app_title">应用设置</span>\
			</div>\
		</div>\
		<div class="content appset_liser_content"></div>\
	</div>\
	<!-- content_system_appset end -->\
	\
	<div class="right_frame" id="content_system_baseset" >\
		<div class="header_content">\
			<div class="group_title">\
				<span class="basesetting_title">基础设置</span>\
			</div>\
		</div>\
		<div class="content basesetting_liser_content"></div>\
	</div>\
	<!-- content_system_appset end -->\
</div><!-- 父元素结束 -->');
