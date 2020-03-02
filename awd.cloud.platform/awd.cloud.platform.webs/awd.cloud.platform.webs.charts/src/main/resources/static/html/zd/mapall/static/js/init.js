define(function (require, exports, module) {

    // 浏览器版本校验
    require("versionCheck");

    // 加载样式重置       样式
    require("loader_css!css_utils/reset/normalize.min");
    // 加载动画          样式
     require("loader_css!css_utils/animate/animate.min");
    // // 加载工具          样式
     require("loader_css!css_utils/dper/dper.utils");
});