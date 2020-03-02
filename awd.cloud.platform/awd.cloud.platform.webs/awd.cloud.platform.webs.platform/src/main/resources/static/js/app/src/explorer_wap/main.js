define("app/src/explorer_wap/main", [
	"lib/jquery-lib",
	"lib/util", 
	"lib/artDialog/jquery-artDialog", 
	"./ui",
	"../../common/core", 
	"./path", 
	"../../common/pathOperate",
	"../../common/pathOpen", 
	"../../common/myPlayer"],
function(e) {
	Config = {
		BodyContent: ".bodymain",
		FileBoxSelector: ".fileContiner",
		FileBoxClass: ".fileContiner .file",
		FileBoxClassName: "file",
		FileBoxTittleClass: ".fileContiner .title",
		SelectClass: ".fileContiner .select",
		SelectClassName: "select",
		TypeFolderClass: "folderBox",
		TypeFileClass: "fileBox",
		HoverClassName: "hover",
		FileOrderAttr: "number",
		TreeId: "folderList",
		pageApp: "explorer_wap",
		treeAjaxURL: "explorer/treeList?app=explorer",
		AnimateTime: 200
	},
	e("lib/jquery-lib"),
	e("lib/util"),
	e("lib/artDialog/jquery-artDialog"),
	ui = e("./ui"),
	core = e("../../common/core"),
	ui.path = e("./path"),
	ui.pathOpen = ui.path.pathOpen,
	$(document).ready(function() {
		core.init(),
		ui.init(),
		$(".init_loading").fadeOut(450).addClass("pop_fadeout"),
		e.async("lib/webuploader/webuploader-min",
		function() {
			core.uploadInit()
		})
	})
});


