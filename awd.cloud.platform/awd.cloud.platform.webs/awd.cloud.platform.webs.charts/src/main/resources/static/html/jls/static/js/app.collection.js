define(function (require) {
  // 页面初始化
  require("init");
  require("jquery");
  require("UnderScore");

  var config =
      require("app_config"),                      // 项目配置
    DperV = require("DperV"),                       // 视图解决方案
    yer = require('yer'),                           // 提示工具
    basefn = require('dperUtils/function'),         // 快码
    rendarTpl = require("rendarTpl"),               // 模板渲染
    DperDate = require("dperUtils/date"),           // 时间工具
    StringUtil = require("dperUtils/string"),       // 字符串工具
    ObjectUtil = require("dperUtils/object"),       // 对象工具
    showTime = require("dperUtils/date").showTime, // 日期工具
    MenuTool = require("./menus.init"),             // 菜单工具

    init_Application,                               // 初始化函数
    basedata,                                       // 基础数据
    evenbind;                                       // 事件绑定

  // 提示工具风格配置
  yer.prototype.options_global = {
    style: 1
  };


  require(['domready'], function () {
    DperV.load("#app", function (app) {
      window.App = app;
      init_Application();
    });
  });

  // 初始化数据
  basedata = {
    // 标记
    mark_: {
      loadFirst: true
    }
  };


  // 事件绑定
  evenbind = function () {

  };

  // 应用初始化方法
  init_Application = function () {
    evenbind();

    // 菜单初始化
    MenuTool.initMenu(document.querySelector('.dper-menus-left'), document.querySelector('.dper-menus-right'));


    // 重新请求接口数据不触发页面加载动画
    basedata.mark_.loadFirst = false;
  };
});
