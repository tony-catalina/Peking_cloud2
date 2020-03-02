define("app/src/explorer/main", [
	"lib/jquery-lib",
	"lib/util", 
	"lib/ztree/ztree",
	"lib/contextMenu/jquery-contextMenu",
	"lib/artDialog/jquery-artDialog",
	"lib/picasa/picasa", 
	"../../common/taskTap", 
	"../../common/core", 
	"../../common/rightMenu",
	"./ui", 
	"./fileContent",
	"../../common/tree",
	"../../common/pathOperate",
	"../../common/pathOpen", 
	"../../common/myPlayer", 
	"./path",
	"./fileLight", 
	"./fileSelect", 
	"./fileListResize", 
	"./headerAddress"],
function(e) {
    Config = {
        BodyContent: ".bodymain",
        FileBoxSelector: ".bodymain .fileContiner",
        FileBoxClass: ".bodymain .fileContiner .file",
        FileBoxClassName: "file",
        FileBoxTittleClass: ".bodymain .fileContiner .title",
        SelectClass: ".bodymain .fileContiner .file.select",
        SelectClassName: "select",
        TypeFolderClass: "folderBox",
        TypeFileClass: "fileBox",
        HoverClassName: "hover",
        TreeId: "folderList",
        pageApp: "explorer",
        treeAjaxURL: "explorer/treeList?app=explorer",
        AnimateTime: 200
    },
    e("lib/jquery-lib"),
    e("lib/util"),
    e("lib/ztree/ztree"),
    e("lib/contextMenu/jquery-contextMenu"),
    e("lib/artDialog/jquery-artDialog"),
    e("lib/picasa/picasa"),
    TaskTap = e("../../common/taskTap"),
    core = e("../../common/core"),
    rightMenu = e("../../common/rightMenu"),
    ui = e("./ui"),
    ui.tree = e("../../common/tree"),
    ui.path = e("./path"),
    ui.fileLight = e("./fileLight"),
    ui.fileSelect = e("./fileSelect"),
    ui.fileListResize = e("./fileListResize"),
    ui.headerAddress = e("./headerAddress"),
    $(document).ready(function() {
        function t(e) {
            var t = RegExp("(^|&)" + e + "=([^&]*)(&|$)"),
            i = window.location.search.substr(1).match(t);
            return null != i ? unescape(i[2]) : null
        }
        core.init()
        ui.init()
        ui.tree.init(),
        ui.fileLight.init(),
        ui.fileSelect.init(),
        ui.headerAddress.init(),
        TaskTap.init(),
        rightMenu.initExplorer(),
        ui.fileListResize.init(),
        ui.fileListResize.initFileSize(),
        $(".init_loading").fadeOut(450).addClass("pop_fadeout"),
        e.async("lib/webuploader/webuploader-min",
        function() {
            core.uploadInit()
        }),
        "file_list" == t("type") && ($(".menu-theme-list").remove(), $(".tools .tools-left").remove(), $(".header-middle").prependTo(".tools").css("padding-top", "3px"), $("#yarnball").addClass("btn-left-radius"))
    })
});