define("app/src/setting/system/system", [
		"lib/contextMenu/jquery-contextMenu",
		"lib/ztree/ztree",
		"./system_member", 
		"./system_group", 
		"./system_role", 
		"./system_group_role"
], function(require) {
  require("lib/contextMenu/jquery-contextMenu");
  require("lib/ztree/ztree");
  systemMember = require("./system_member");
  systemGroup = require("./system_group");
  systemRole = require("./system_role");
  systemGroupRole = require("./system_group_role");
  var _init = function() {
	showtab("system_group");
    tabchange();
    systemGroupRole.init(function() {
      systemRole.init();
      systemGroup.init();
    });
  };
  var showtab = function(hash) {
    $(".system_conennt .this").removeClass("this");
    $(".system_conennt #" + hash).addClass("this");
    $(".left_content").addClass("hidden");
    $("." + hash).removeClass("hidden");
    $(".right_frame").addClass("hidden");
    $("#content_" + hash).removeClass("hidden");
  };
  var tabchange = function() {
    $(".left_header .tab").die("click").live("click", function() {
      var hash = $(this).attr("id");
      showtab(hash); 
      
    });
  };
  var _sizeUse = function(contacts) {
    contacts.each(function() {
      var distanceToUserValue = core.userSpaceHtml($(this).html());
      $(this).html(distanceToUserValue);
    });
  };

  var _openPath = function(req) {
    var doc = G.user_path + req.path + "/home/";
    if (req.group_id) {
      doc = G.group_path + req.path + "/home/";
    }
    if (req.home_path) {
      doc = req.home_path;
    }
    if (window.parent && (window.parent.Config && "explorer" == window.parent.Config.pageApp)) {
      window.parent.ui.path.list(doc);
      Tips.tips("成功在文件管理中打开！", true);
    } else {
      core.explorer(doc);
    }
  };
  return{
    init : _init,
    sizeUse : _sizeUse,
    openPath : _openPath,
    dataList : core.tools.systemData,
    systemMember : systemMember,
    systemGroup : systemGroup,
    systemRole : systemRole,
    systemGroupRole : systemGroupRole
  };
});
