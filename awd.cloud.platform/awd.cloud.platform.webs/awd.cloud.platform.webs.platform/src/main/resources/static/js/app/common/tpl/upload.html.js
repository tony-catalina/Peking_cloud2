define("app/common/tpl/upload.html", [],
		'<div class=\'file_upload\'>\
		<div class=\'top_nav\'>\
		   <a href=\'javascript:void(0);\' class=\'menu this tab_upload\'>本地上传</a>\
		   <a href=\'javascript:void(0);\' class=\'menu tab_download hidden\' >远程下载</a>\
		   <div style=\'clear:both\'></div>\
		</div>\
		<div class=\'upload_box\'>\
			<div class=\'btns\'>\
				<div class="upload-btns">\
					<div id=\'picker\'>选择文件</div>\
					\
					<div id=\'picker_folder\' class="hidden">select Folder</div>\
					<div class="upload_cert_box hidden">\
						<button title="More" type="button" class="upload_cert dropdown-toggle" data-toggle="dropdown">\
							<span class="caret"></span>\
						</button>\
						<ul class="dropdown-menu pull-left animated menuShow">\
							<li><a href="javascript:void(0);" class="drag_upload_folder" draggable="false">文件夹</a></li>\
						</ul>\
					</div>\
				</div>\
				\
				<div class="upload_box_tips">\
					<div class="btn-group btn-group-xs">\
						<button title="清空所有" type="button" class="btn btn-default upload_box_clear_all">清空所有</button>\
						<button id="set_icon" title="清空已完成" type="button" class="btn btn-default upload_box_clear">清空已完成</button>\
					</div>\
				</div>\
				<div style=\'clear:both\'></div>\
			</div>\
			<div class=\'uploader-content\'>\
				<div class=\'uploader-list\'></div>\
			</div>\
		</div>\
		<div class=\'download_box hidden\'>\
			<div class=\'list\'>外链地址<input type=\'text\' name=\'url\'/>\
			<div class="download_btn_group btn-group">\
				<button class=\'btn btn-default btn-sm download_start\' type=\'button\'>下载</button>\
				<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\
					<span class="caret"></span>&nbsp;\
					<span class="sr-only">Dropdown</span>\
				</button>\
				<ul class="dropdown-menu">\
					<li><a href="javascript:void(0);" class="download_start_all">批量添加</a></li>\
				</ul>\
			</div>\
		\
			</div>\
			<div style=\'clear:both\'></div>\
			<div id=\'downloader\'>\
				<div class=\'download_list\'></div>\
			</div>\
		</div>\
	</div>'
);
