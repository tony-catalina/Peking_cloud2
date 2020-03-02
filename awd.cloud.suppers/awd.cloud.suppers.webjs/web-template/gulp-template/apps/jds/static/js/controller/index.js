define(function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('uikit');
    var menu=require('modules/menus');
    $(function () {
      menu.init();
    });
});
