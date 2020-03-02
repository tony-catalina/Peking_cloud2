var pageEntry = {
    "": "app",
    "pages/appIndex.html": "app",
    "pages/appManage.html": "app.manage",
    "pages/appCollection.html": "app.collection",
  },				        // 多页面脚本文件主入口配置
  pname = "dv_bj_prison";		// 项目名称   必须配置 否则会出错


// 基础路径全局化
window.BASEURL = window.location.pathname.substring(0, window.location.pathname.indexOf(pname)) + pname + "/";

requirejs.config({
  baseUrl: getBasePath("static/js"),
  // 模块加载最大等待时间
  waitSeconds: "20",
  // 模块路径配置
  paths: {
    // 项目配置信息
    app_config: "../config/config",
    // 提示工具
    yer: "../utils/dper/yer.min",
    // 数据请求工具
    dperAjax: "../utils/dper/dperAjax",
    // 模板渲染
    rendarTpl: "../utils/dper/dperUtils/rendarTpl",
    // 视图解决方案
    DperV: "../utils/dper/dperView",
    // 自动滚动
    DperRoll: "../utils/dper/dperRoll",
    // 列表播放插件
    DperPlayList: "../utils/dper/dperPlayList",
    // 弹窗
    DperLayer: "../utils/dper/dperLayer",

    // jQuery
    // jquery: ["https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min", "../utils/jquery/jquery.min"],
    jquery: ["../utils/jquery/jquery.min"],
    // echarts
    // echarts: ["https://cdnjs.cloudflare.com/ajax/libs/echarts/4.1.0.rc2/echarts.min", "../utils/echarts/echarts.min"],
    echarts: ["../utils/echarts/echarts.min"],
    // 数字滚动
    CountUp: "../utils/countUp/countUp.min",
    // WebUploader 修改过上传参数
    WebUploader: "../utils/webuploader/webuploader.min",
    // 性能监控
    Stats: "../utils/stats/Stats",
    // swiper css https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.0/css/swiper.min.css utils/swiper/swiper.min
    // Swiper: ["https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.0/js/swiper.min", "../utils/swiper/swiper.min"],
    Swiper: ["../utils/swiper/swiper.min"],
    // underscore
    // UnderScore: ["https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore-min", "../utils/underscore/underscore-min"],
    UnderScore: ["../utils/underscore/underscore-min"],
    // simpleJavascriptIn
    sJI: ["../utils/class/extends"],
    fill: "../utils/fillEcharts/echarts.waterfill",

    // require插件
    // 读取文本
    // text: ["https://cdnjs.cloudflare.com/ajax/libs/require-text/2.0.12/text.min", "../utils/requirejs/text.min"],
    text: ["../utils/requirejs/text.min"],
    // 节点加载完毕
    // domready: ["https://cdnjs.cloudflare.com/ajax/libs/require-domReady/2.0.1/domReady.min", "../utils/requirejs/domReady.min"],
    domready: ["../utils/requirejs/domReady.min"],
    // css加载
    // loader_css: ["https://cdnjs.cloudflare.com/ajax/libs/require-css/0.1.10/css.min", "../utils/requirejs/css.min"],
    loader_css: ["../utils/requirejs/css.min"],
    // 浏览器版本检查
    browserV: "../utils/versionCheck/browser",
    // 小于IE9 视图处理(此处为跳转到指定页面)
    versionCheck: "../utils/versionCheck/ltie9",
  },
  // 非AMD 模块加载配置
  shim: {
    DperRoll: {exports: "AutoRoll"},
    Stats: {exports: "Stats"},
    DperLayer: {deps: ["jquery"]}
  },
  // 模块 引用插件不同版本的解决方案/路径映射
  map: {
    "*": {
      "css_app": "../css",
      "utils": "../utils",
      "dperUtils": "../utils/dper/dperUtils"
    }
  }
});
require([getEntryApp()]);

// 获取模块根路径 （多页面）
function getBasePath(base) {
  var p_ = window.location.pathname;
  p_ = p_.substring(p_.indexOf(pname) - 1);
  var sr_ = "";
  for (var i = 0; i < (p_.split('/').length - 3); i++) {
    sr_ += "../";
  }
  return sr_ + base;
}

// 得知指定页面入口脚本文件
function getEntryApp() {
  var p_ = window.location.pathname;
  var et_ = "";
  for (var pr_ in pageEntry) {
    if (p_.indexOf(pr_) !== -1) {
      et_ = pageEntry[pr_];
    }
  }
  if (et_ === "app" && p_.split('/').length > 3) {
    for (var pr_r in pageEntry) {
      if (p_.indexOf(pr_r.replace('appIndex.html.html', '')) !== -1) {
        et_ = pageEntry[pr_r];
      }
    }
  }
  return et_;
}
