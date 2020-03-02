define("app/common/core.tools", [], function(require) {
  var sel = "上海版";
  var aboutRemove = function() {
	  $(".menu_system_about,.menu_left #about")["remove"]();
  };
  var _init = function() {
    core["icon"] = function(name, isSmall) {
      return '<i class="x-item-file x-' + name + (isSmall ? " small" : "") + '"></i>';
    };
    core["iconSmall"] = function(name) {
      return core["icon"](name, true);
    };
    core["iconSrc"] = iconSrc = function(iconurl) {
      return '<img src="' + iconurl + '" draggable="false" ondragstart="return false;">';
    };
    core["versionType"] = sel;
    core["filetype"] = core["filetypes"];
    core["versionUpdateVip"] = '//www.awd.com/version.html#' + G["lang"];
    aboutRemove();
  };
  var _systemData = function(target, dataAndEvents) {
    var src;
    if(dataAndEvents == "group"){
    	src = target["data"];
    }else{
    	src = target["data"];
    }
    return src;
  };
  return{
    init : _init,
    systemData : _systemData //获取返回结果中某个数据
  };
});
