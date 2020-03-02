define("app/common/tpl/zipview.html", [], 
'<div class="zipViewContent menuEmpty">\
	<div class="header_title">\
		<div class="item name"><span>名称</span></div>\
		<div class="item size"><span>大小</span></div>\
		<div class="item mtime"><span>修改时间</span></div>\
		<div class="clear"></div>\
	</div>\
	<div id="{{treeID}}"  class="ztree"></div>\
	<div class="bottom">\
		<span class="info"></span>\
	</div>\
</div>'
);