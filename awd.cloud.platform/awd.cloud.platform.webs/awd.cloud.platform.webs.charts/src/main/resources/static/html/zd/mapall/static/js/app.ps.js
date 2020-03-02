define(function (require) {
    // 页面初始化
    require("init");
    // 引入jquery
    require("jquery");

    var config =
        require("app_config"),      // 项目配置
        DperV = require("DperV"),   // 视图解决方案
        init_Application,           // 初始化函数
        basedata,                   // 基础数据
        yer = require('yer'),       // 提示工具
        dperAjax = require("dperAjax"),// 数据请求
        evenbind;                   // 事件绑定


    // 提示工具风格配置
    yer.prototype.options_global = {
        style: 1
    }

    require(['domready'], function () {
        DperV("app", function (app) {
            window.App = app;
            init_Application();
        })
    })

    // 初始化数据
    basedata = {}


    // 事件绑定
    evenbind = function () {

    }

    // 应用初始化方法
    init_Application = function () {
        evenbind();
        App.state("dper-demo", 1);
        setTimeout(function () {
            App.state("dper-demo", 2);
        }, 2000);
    }
});