var pageEntry = {
        "": "app",
        "index.html": "app",
        "pages/demo-ps.html": "app.ps"
    },				        // 多页面脚本文件主入口配置
    pname = "zd.mapall";		// 项目名称   必须配置 否则会出错


requirejs.config({
    baseUrl: getBasePath("zd/mapall/static/js"),
    // 模块加载最大等待事件
    waitSeconds: "20",
    // 模块路径配置
    paths: {
        // 项目配置信息
        app_config: "../config/params",

        // 提示工具
        yer: "../utils/dper/yer.min",
        // 模板渲染
        rendarTpl: "../utils/dper/dperUtils/rendarTpl",
        // 视图解决方案
        DperV: "../utils/dper/dperView",
        // 自动滚动
        DperRoll: "../utils/dper/dperRoll",
        // 数据请求工具
        dperAjax: "../utils/dper/dperAjax",

        // jQuery
        jquery: [ "../utils/jquery/jquery.min"],
        // echarts
        echarts: ["../utils/echarts/echarts.min"],
        // 数字滚动
        CountUp: "../utils/countUp/countUp.min",
        UnderScore: ["../utils/underscore/underscore-min"],
        Swiper: '../utils/swiper/swiper.min',
        // require插件
        // 读取文本
        text: [ "../utils/requirejs/text.min"],
        // 节点加载完毕
        domready: ["../utils/requirejs/domReady.min"],
        // css加载
        loader_css: [ "../utils/requirejs/css.min"],

        // 浏览器版本检查
        browserV: "../utils/versionCheck/browser",
        // 小于IE9 视图处理(此处为跳转到指定页面)
        versionCheck: "../utils/versionCheck/ltie9",
    },
    // 非AMD 模块加载配置
    shim: {
        DperV: {exports: "DperV"},
        DperRoll: {exports: "AutoRoll"}
    },
    // 模块 引用插件不同版本的解决方案/路径映射
    map: {
        // "a": {
        //     "jquery": "https://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"
        // },
        // "b": {
        //     "jquery": "https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"
        // }
        "*": {
            "css_app": "../css",
            "css_utils": "../utils",
            "dperUtils": "../utils/dper/dperUtils"
        }
    },
    // 子模块所需配置
    config: {
        "app": {
            name: "项目初始化..."
        }
    }
});
require([getEntryApp()]);

// 获取模块根路径 （多页面）
function getBasePath(base) {
    var p_ = window.location.pathname;
    p_ = p_.substring(p_.indexOf(pname)-1);
    var sr_ = "";
    for (var i = 0; i < (p_.split('/').length - 3); i++) {
        sr_ += "../";
    }
    return  sr_ + base;
}

// 得知指定页面入口脚本文件
function getEntryApp() {
    var p_ = window.location.pathname;
    var et_ = "";
    for (var pr_ in pageEntry) {
        if(p_.indexOf(pr_) != -1){
            et_ = pageEntry[pr_];
        }
    }
    return et_;
}
