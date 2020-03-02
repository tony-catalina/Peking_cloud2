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
    Swiper = require("Swiper"),
    ChartFill = require("./modules/charts/appIndex/ChartFill"), // 波浪图
    ChartLLine = require("./modules/charts/appIndex/ChartLline"), // 左边折线图
    ChartPie = require("./modules/charts/appIndex/ChartPie"), // 右边 top 饼图
    ChartRLine = require("./modules/charts/appIndex/ChartRline"), // 右边 bottom 折线图

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
    chart: {
      child0: null,
      child1: null,
      child2: null,
      child3: null,
      child4: null,
      child5: null,
      child6: null
    },
    time: 5000,
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

    // left top top
    lTTFn("box0");
    // left top bottom
    lTBFn("box1");
    // left bottom top
    lBTFn("box2");
    // left bottom bottom
    lBBFn("box3");
    // right top top
    rTTFn("box4");
    // right top bottom
    rTBFn("box5");
    // right center
    rCFn("box6");
    // right bottom
    rBFn("box7");
    // center top
    cTFn("box8");
    // center center
    cCFn("box9");
    // center bottom
    cBFn("box10");
    var tmt;
    $(window).on('resize',function(resp){
      clearTimeout(tmt);
      tmt = setTimeout(function(){
        _.each(basedata.chart,function(itm){
          if(itm.myChart) itm.myChart.resize()
        })
      },500)
    })
    // 重新请求接口数据不触发页面加载动画
    basedata.mark_.loadFirst = false;
  };
  // left top top
  var lTTFn = function (el){
      var $el = $(App.getView(el)),
        $swiper = '' +
        '              <div class="swiper-container dper-full-h" id="swiper0">\n' +
        '                <div class="swiper-wrapper dper-full-h">\n' +
        '' +
        '                </div>\n' +
        '              </div>';
    $el.empty();
    $el.append($swiper);
    gainLTTFn(el,renderLTTFn)
  };
  var renderLTTFn =function (el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $t = $(App.getView(el)).parent().parent().siblings().find('.la-title'),
      $ft = $(App.getView(el)).parent().parent().parent().parent().siblings('.child-header').find('.la-title'),
      $child = '' +
        '<div class="swiper-slide dper-por">' +
        ' <div class="child-itm malign-center-left dper-box itm-{{type}}">' +
        '   <span class="malign-center-text text-overflow-e">{{name}}</span>' +
        ' </div>' +
        ' <span class="child-itm malign-center-left co-0ff text-overflow-e">{{msg}}</span>' +
        ' <p class="child-itm malign-center-right">' +
        '   <span class="malign-center-text co-999  text-overflow-e font-12">{{time}}</span>' +
        ' </p>' +
        '</div>',mySwiper = null;
    $t.text('');
    $ft.text('');
    $ft.text(resp.name);
    $t.text(resp.t.name);
    _.each(resp.t.list,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    mySwiper = new Swiper("#swiper0",{
      direction: 'vertical',
      loop:true,
      autoplay: {
        delay: basedata.time
      },
      grabCursor: true,
      slidesPerView: 4,
      slidesPerGroup: 4
    });
    $('#swiper0').on('mouseenter',function(){
      mySwiper.autoplay.stop();
    });
    $('#swiper0').on('mouseleave',function(){
      mySwiper.autoplay.start();
    })
  };
  var gainLTTFn = function (el,cb){
    basefn(el,{
      App:App,
      desc: "left top top",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.lt;
        cb&&cb(el,resp)
      }
    })

  };
  // left top bottom
  var lTBFn = function (el){
    var $el = $(App.getView(el)),
      $swiper = '' +
        '              <div class="swiper-container dper-full-h" id="swiper1">\n' +
        '                <div class="swiper-wrapper dper-full-h">\n' +
        '' +
        '                </div>\n' +
        '              </div>';
    $el.empty();
    $el.append($swiper);
    gainLTBFn(el,renderLTBFn)
  };
  var renderLTBFn =function (el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $t = $(App.getView(el)).parent().parent().siblings().find('.la-title'),
      $child = '' +
        '<div class="swiper-slide dper-por">' +
        ' <div class="child-itm malign-tl dper-box img-{{img}} bg-img-full" ' +
        '   style="background-image: url({{img}});">' +
        '   <div class="malign-center-bottom dper-full-w person-name-bg">' +
        '     <span class="malign-center-text text-overflow-e font-12 ">{{name}}</span>' +
        '   </div>' +
        ' </div>' +
        ' <div class="child-itm malign-tl co-0ff">' +
        '   <p>{{msg}}</p>' +
        ' </div>' +
        ' <p class="child-itm malign-br">' +
        '   <span class="malign-center-text co-999  text-overflow-e font-12">{{time}}</span>' +
        ' </p>' +
        '</div>',mySwiper = null;
    $t.text('');
    $t.text(resp.b.name);
    _.each(resp.b.list,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    mySwiper = new Swiper("#swiper1",{
      direction: 'vertical',
      loop:true,
      autoplay: {
        delay: basedata.time
      },
      grabCursor: true,
      slidesPerView: 2,
      slidesPerGroup: 2
    });
    $('#swiper1').on('mouseenter',function(){
      mySwiper.autoplay.stop();
    });
    $('#swiper1').on('mouseleave',function(){
      mySwiper.autoplay.start();
    })
  };
  var gainLTBFn = function (el,cb){
    basefn(el,{
      App:App,
      desc: "left top bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.lt;
        cb&&cb(el,resp)
      }
    })

  };

  //left bottom top
  var lBTFn = function (el){
    gainLBTFn(el,renderLBTFn)
  };
  var renderLBTFn =function(el,resp){
    var $el = $(App.getView(el).querySelector('.fill-box')),
      $t =  $(App.getView(el)).parent().parent().parent().siblings('.child-header').find('.la-title'),
        $child = '<div class="fill-itm fl dper-full-h dper-por align-center">' +
          '   <div class="fill-chart dper-full-h" id="fill{{id}}"></div>' +
          '    <span class="fill-text malign-center-bottom dper-full-w text-overflow-e">{{name}}</span>' +
          '</div>';
    $el.empty();
    $t.text("");
    $t.text(resp.name);
    _.each(resp.t.list,function(itm,idx){
      itm.id = idx;
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    renderFillFn($el,resp.t.list)
  };
  var renderFillFn =function ($el,resp){
    if (!basedata.chart.child0) basedata.chart.child0 = new ChartFill($el.find('#fill0')[0]);
    if (!basedata.chart.child1) basedata.chart.child1 = new ChartFill($el.find('#fill1')[0]);
    if (!basedata.chart.child2) basedata.chart.child2 = new ChartFill($el.find('#fill2')[0]);
    if (!basedata.chart.child3) basedata.chart.child3 = new ChartFill($el.find('#fill3')[0]);
    basedata.chart.child0.render({
      name: resp[0].name,
      value: resp[0].value,
      col: "#0080ff",
      bg: "rgba(0,128,255,.3)"
    })
    basedata.chart.child1.render({
      name: resp[1].name,
      value: resp[1].value,
      col: "#00d8ae",
      bg: "rgba(0,216,174,.3)"
    })
    basedata.chart.child2.render({
      name: resp[2].name,
      value: resp[2].value,
      col: "#2cd7ff",
      bg: "rgba(44,214,255,.3)"
    })
    basedata.chart.child3.render({
      name: resp[3].name,
      value: resp[3].value,
      col: "#ffe05f",
      bg: "rgba(255,224,95,.3)"
    })
  };
  var gainLBTFn = function (el,cb){
    basefn(el,{
      App:App,
      desc: "left bottom top",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.lb;
        cb&&cb(el,resp)
      }
    })

  };
  //   left bottom bottom
  var lBBFn = function(el){
    if (!basedata.chart.child4) basedata.chart.child4 = new ChartLLine(App.getView(el));
    gainLBBFn(el,renderLineFn)
  };
  var renderLineFn = function(el,resp){
    var $resp = {
      axis: [],
      seriesData: [],
      min: resp.min,
      name: resp.name,
    }
    _.each(resp.list,function(itm){
      $resp.axis.push(itm.name);
      $resp.seriesData.push({name: itm.name,value:itm.value});
    })
    basedata.chart.child4.render($resp)
  };
  var gainLBBFn = function (el,cb){
    basefn(el,{
      App:App,
      desc: "left bottom bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.lb.b;
        cb&&cb(el,resp)
      }
    })
  };
  // right top top
  var rTTFn = function(el){
    if(!basedata.chart.child5)basedata.chart.child5 = new ChartPie(App.getView(el).querySelector('.pie-chart'));
    gainRTTFn(el,renderRTTFn)
  };
  var renderRTTFn =function(el,resp){
    var $el = $(App.getView(el).querySelector('.legend-box')),
      $t = $(App.getView(el)).parent().parent().parent().siblings('.child-header').find('.la-title');
        $child = '<div class="malign-tl legend-itm font-12">\n' +
          '              <i class="legend-icon dper-box malign-center-left"' +
          '                 style="background: -webkit-linear-gradient(left,{{col0}},{{col1}});' +
          '                 background: linear-gradient(left,{{col0}},{{col1}});"></i>\n' +
          '              <span class="legend-write malign-center-left text-overflow-e">{{name}}</span>\n' +
          '              <span class="legend-val dper-fw malign-center-right" style="color:{{col0}}">{{deci}} %</span>' +
          '            </div>';
    $el.empty();
    $t.text('');
    $t.text(resp.name);
    _.each(resp.list,function(itm){
        $el.append(rendarTpl(itm,$child,'itm'))
    });
    basedata.chart.child5.render(resp)
  };
  var gainRTTFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "right top top",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.rt;
        var col = [
          {
            co0: '#fbbd05',
            co1: '#ef7117'
          },
          {
            co0: '#f0f083',
            co1: '#d3d31a'
          },
          {
            co0: '#9745f4',
            co1: '#630cc6'
          },
          {
            co0: '#00fcc3',
            co1: '#00c7f8'
          },
          {
            co0: '#ff610e',
            co1: '#ff1668'
          },
          {
            co0: '#03de8b',
            co1: '#14c048'
          },
          {
            co0: '#01a0ff',
            co1: '#0345ff'
          },
          {
            co0: '#ff8dcc',
            co1: '#ff70cc'
          },
          {
            co0: '#8339ff',
            co1: '#b914ff'
          },
          {
            co0: '#fb4df3',
            co1: '#ec2ac2'
          },
          {
            co0: '#2bedb7',
            co1: '#10e2b7'
          }
        ],$resp = {
          name: resp.name,
          list: [],
          sum: 0
        };
        _.each(resp.t,function(itm){
          $resp.sum += Number(itm.value)
        });
        _.each(resp.t,function(itm,idx){
          $resp.list.push({
            name: itm.name,
            value: itm.value,
            deci: ((Number(itm.value)/$resp.sum)*100).toFixed(2),
            col0: col[idx].co0,
            col1: col[idx].co1,
          })
        });
        cb&&cb(el,$resp)
      }
    })
  };
  // right top bottom
  var rTBFn = function(el){
    var $el = $(App.getView(el)),
      $swiper = '<div class="swiper-container dper-full-h" id="swiper2">' +
        '   <div class="swiper-wrapper"></div>' +
        '</div>';
    $el.empty();
    $el.append($swiper);
    gainRTBFn(el,renderRTBFn)
  };
  var renderRTBFn =function(el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $child = '' +
        '<div class="swiper-slide dper-por font-12">' +
        '   <span class="pro-name malign-center-right text-overflow-e align-right">{{name}}</span>' +
        '   <div class="progress-itm malign-center-left">' +
        '      <div class="pro-bar-box pro-bg-img bg-img-full dper-full-h dper-por dper-overh">' +
        '         <div class="pro-bar pro-bar-img bg-img-full malign-tl dper-full-h dper-full-w {{cla}}" data-pro="{{deci}}"></div>' +
        '      </div>' +
        '   </div>' +
        '   <span class="pro-value malign-center-left font-14 dper-fw text-overflow-e {{col}}">{{value}}</span>' +
        '</div>',mySwiper = null;
    $el.empty();
    _.each(resp.list,function(itm){
        $el.append(rendarTpl(itm,$child,'itm'))
    });
    _.each($el.find('.pro-bar'),function(itm,idx){
      var number = 100 - Number($(itm).data("pro"));
      setTimeout(function(){
        $(itm).css("transform","translateX(-"+number+"%)")
      },50*idx)
    });
    mySwiper = new Swiper("#swiper2",{
      direction: 'vertical',
      grabCursor: true,
      slidesPerView: 10,
      slidesPerGroup: 10
    })
  };
  var gainRTBFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "right top bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.rt.b;
        var $resp = {
          sum: Number(resp.sum),
          list: []
        };
        _.each(resp.list,function(itm,idx){
            $resp.list.push({
              name: itm.name,
              value: itm.value,
              deci: ((Number(itm.value)/$resp.sum)*100).toFixed(2),
              cla: idx < 3? "pro-co-"+idx+"-img": "pro-co-img",
              col: idx < 3? "col-"+idx: "col"
            })
        });
        cb&&cb(el,$resp)
      }
    })
  };
//   right center
  var rCFn  = function(el){
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper-container' id='swiper3'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainRCFn(el,renderRCFn)
  };
  var renderRCFn = function(el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title'),
      $child = "<div class='swiper-slide dper-box font-16'>" +
        "   <div class='slide-itm fl dper-full-h dper-por'>" +
        "       <div class='l-circle malign-center-left'>" +
        "         <div class='circle-rotate circle-rotate-{{id}}-img bg-img-full'></div>" +
        "         <span class='circle-write malign-center-text  co-rule-{{id}}'>{{rule}}</span>" +
        "       </div>" +
        "       <div class='r-write malign-center-right r-bg-{{id}}-img bg-img-full'>" +
        "         <span class='malign-center-left text-overflow-e'>{{name}}</span>" +
        "       </div>" +
        "   </div>" +
        "   <div class='slide-itm fl dper-full-h dper-por'>" +
        "     <div class='person-box malign-center dper-full-w'>" +
        "       <p class='person-write malign-center-left font-14 text-overflow-e'>人数</p>" +
        "       <div class='person-value malign-center-left font-14 font-24 dper-fw'>" +
        "         <p class='co-blue-person dper-por z-level-5 text-overflow-e dper-full-w'>{{person}}</p>" +
        "         <p class='co-blue-shadow malign-tl z-level-2 text-overflow-e dper-full-w'>{{person}}</p>" +
        "       </div>" +
        "       <p class='person-unit malign-center-left font-14 text-overflow-e co-3dd4fa'>人</p>" +
        "     </div>" +
        "   </div>" +
        "   <div class='slide-itm fl dper-full-h dper-por'>" +
        "     <div class='deci-box malign-center-left dper-full-w'>" +
        "       <p class='deci-write malign-center-left font-14 text-overflow-e'>占比</p>" +
        "       <div class='deci-value malign-center-left font-14 font-24 dper-fw'>" +
        "         <p class='co-blue-person dper-por z-level-5 text-overflow-e dper-full-w'>{{deci}}</p>" +
        "         <p class='co-blue-shadow malign-tl z-level-2 text-overflow-e dper-full-w'>{{deci}}</p>" +
        "       </div>" +
        "       <p class='deci-unit malign-center-left font-14 text-overflow-e co-3dd4fa'>%</p>" +
        "     </div>" +
        "   </div>" +
        "</div>",mySwiper = null;
    $t.text("");
    $el.empty();
    $t.text(resp.name);
    _.each(resp.list,function(itm){
      $el.append(rendarTpl(itm,$child,"itm"));
    });
    mySwiper = new Swiper("#swiper3",{
      direction: 'vertical',
      grabCursor: true,
      slidesPerView: 3,
      slidesPerGroup: 3
    })
  };
  var gainRCFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "right center",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.rc;
        cb&&cb(el,resp)
      }
    })
  };
  var rBFn = function(el){
    if(!basedata.chart.child6) basedata.chart.child6 = new ChartRLine(App.getView(el));
    gainRBFn(el,renderRBFn)
  };
  // right bottom
  var renderRBFn = function(el,resp){
    var $t = $(App.getView(el)).parent().parent().parent().siblings(".child-header").find('.la-title');
    $t.text('');
    $t.text(resp.name);
    basedata.chart.child6.render(resp)
  };
  var gainRBFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "right bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.rb;
        var $resp = {
          name: resp.name,
          min: resp.min,
          series: [],
          axis: (function(){
            var arr = [];
              _.each(resp.list[0].list,function(itm){
                arr.push(itm.name)
              })
            return arr
          })()
        },col = [
          '#ff656c',
          '#ffbf69',
          '#33ff99',
          '#00cfff'
        ];
        _.each(resp.list,function(itm,idx){
          $resp.series.push({
            name: itm.name,
            value: itm.list,
            col: col[idx],
            icon: itm.icon
          });
        });
        cb&&cb(el,$resp)
      }
    })
  };
  //   center top
  var cTFn = function(el){
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper-container dper-full-h' id='swiper4'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainCTFn(el,renderCTFn)
  };
  var renderCTFn = function(el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $child = '<div class="swiper-slide dper-full-h dper-por align-center">' +
        ' <div class="slide-itm malign-center dper-full-w">' +
        ' <div class="l-cont l-cont-img bg-img-full dper-full-h malign-tl">' +
        '   <p class=""><span class="font-30 co-0ff">{{lvalue}}</span>人</p>' +
        '   <p class="">{{lname}}</p>' +
        ' </div>' +
        ' <div class="c-cont c-cont-img bg-img-full malign-center-left">' +
        '   <p class="malign-center-text">{{name}}</p> ' +
        ' </div>' +
        ' <div class="r-cont r-cont-img bg-img-full dper-full-h malign-tr">' +
        '   <p class=""><span class="font-30 co-0ff">{{rvalue}}</span>人</p>' +
        '   <p class="">{{rname}}</p>' +
        ' </div>' +
        '</div>' +
        '</div>',
      mySwiper = null;
    $el.empty();
    _.each(resp,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    mySwiper = new Swiper("#swiper4",{
      grabCursor: true,
      slidesPerView: 2,
      slidesPerGroup: 2
    });

  };
  var gainCTFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "center top",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.ct;
        cb&&cb(el,resp)
      }
    })
  };
  //   center center
  var cCFn = function(el){
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper-container dper-full-h' id='swiper5'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainCCFn(el,renderCCFn)
  };
  var renderCCFn = function(el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $child = '<div class="swiper-slide dper-full-h dper-por font-16">' +
        ' <div class="img-itm malign-center">' +
        ' <div class="person-img bg-img-full dper-full-h dper-por z-level-2" style="background-image: url({{img}})"></div>' +
        ' <div class="person-msg dper-full-h dper-full-w malign-center-top z-level-5 align-center">' +
        '   <p class="malign-tl text-overflow-e dper-full-w">{{name}}</p>' +
        '   <p class="malign-tl text-overflow-e dper-full-w">{{area}}</p>' +
        '   <p class="malign-tl text-overflow-e dper-full-w">{{number}}</p>' +
        '</div>' +
        '</div>' +
        '</div>',
      mySwiper = null;
    $el.empty();
    _.each(resp,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    mySwiper = new Swiper("#swiper5",{
      loop:true,
      autoplay: {
        delay: basedata.time
      },
      grabCursor: true,
      slidesPerView: 6,
      slidesPerGroup: 6
    });
    $('#swiper5').on('mouseenter',function(){
      mySwiper.autoplay.stop();
    });
    $('#swiper5').on('mouseleave',function(){
      mySwiper.autoplay.start();
    })
  };
  var gainCCFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "center center",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.cc;
        cb&&cb(el,resp)
      }
    })
  };
  //   center top
  var cBFn = function(el){
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper-container dper-full-h' id='swiper6'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainCBFn(el,renderCBFn)
  };
  var renderCBFn = function(el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $child = '' +
        '<div class="swiper-slide font-18 align-center">' +
        ' <div class="cap-center-cont dper-por">' +
        '   <div class="cap-styl cap-center-cont-img bg-img-full malign-tl dper-full-w dper-full-h"></div>' +
        '   <div class="head malign-center-top"><span class="malign-center-text text-overflow-e font-22">{{name}}</span></div>' +
        '   <div class="child-itm malign-center-top dper-full-w">' +
        '       <p class="text-overflow-e dper-full-w">' +
        '         <span>在押总数</span>' +
        '         <span class="font-30 co-0ff">{{val0}}</span>' +
        '         <span>人,其中女性</span>' +
        '         <span class="font-30 co-ff656c">{{val01}}</span>' +
        '         <span>人</span>' +
        '       </p>' +
        '   </div>' +
        '   <div class="child-itm malign-center-top dper-full-w">' +
        '       <p class="text-overflow-e dper-full-w">' +
        '         <span>今日入所</span>' +
        '         <span class="font-30 co-0ff">{{val1}}</span>' +
        '         <span>人,今日出所</span>' +
        '         <span class="font-30 co-0ff">{{val11}}</span>' +
        '         <span>人</span>' +
        '       </p>' +
        '   </div>' +
        '   <div class="child-itm malign-center-top dper-full-w">' +
        '       <p class="text-overflow-e dper-full-w">' +
        '         <span>一级风险人数:</span>' +
        '         <span class="font-30 co-0ff">{{val2}}</span>' +
        '         <span>人,占比:</span>' +
        '         <span class="font-30 co-0ff">{{val21}}%</span>' +
        '       </p>' +
        '   </div>' +
        '   <div class="child-itm malign-center-top dper-full-w">' +
        '       <p class="text-overflow-e dper-full-w">' +
        '         <span>二级风险人数:</span>' +
        '         <span class="font-30 co-0ff">{{val3}}</span>' +
        '         <span>人,占比:</span>' +
        '         <span class="font-30 co-0ff">{{val31}}%</span>' +
        '       </p>' +
        '   </div>' +
        '   <div class="child-itm malign-center-top dper-full-w">' +
        '       <p class="text-overflow-e dper-full-w">' +
        '         <span>三级风险人数:</span>' +
        '         <span class="font-30 co-0ff">{{val4}}</span>' +
        '         <span>人,占比:</span>' +
        '         <span class="font-30 co-0ff">{{val41}}%</span>' +
        '       </p>' +
        '   </div>' +
        '</div>' +
        '</div>',
      mySwiper = null;
    $el.empty();
    _.each(resp,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    mySwiper = new Swiper("#swiper6",{
      initialSlide: 1,
      grabCursor: true,
      slidesPerView: 3,
      slidesPerGroup: 1,
      centeredSlides: true,
      effect : 'coverflow',
      coverflowEffect: {
        rotate: -15,
        stretch: -30,
        depth: 60,
        modifier: 2,
        slideShadows : false
      },
    })

  };
  var gainCBFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "center bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.index.cb;
        cb&&cb(el,resp)
      }
    })
  };
});
