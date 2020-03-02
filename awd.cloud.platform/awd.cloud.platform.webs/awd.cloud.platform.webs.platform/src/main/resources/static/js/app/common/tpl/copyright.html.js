define("app/common/tpl/copyright.html", [],
'<div class="copyright_dialog_content">\
		<div class="title">\
		<div class="logo"><i class="icon-cloud"></i>{{if core.versionType==\'A\'}}AwdExplorer {{else}} {{G.CLOUD_NAME}} {{/if}} v{{G.version}}</div>\
		<div class=\'info\'>——{{G.CLOUD_NAME}}</div>\
	</div>\
	<div class="content">\
		<p>{{G.CLOUD_NAME}}是南京安威德科技有限公司新一代的监管信息系统的升级版,使得监管业务升级到云服务中，提升业务办理能力。</p>\
		<div>问题联系: 025-85520688 85520588 85525188 <a href=\"javascript:void(0)\" style=\"color:red;\"> 问题反馈<\/a></div>\
		<div>Copyright © 南京安威德科技有限公司 All rightsreserved.</div> \
	</div>\
</div>');
