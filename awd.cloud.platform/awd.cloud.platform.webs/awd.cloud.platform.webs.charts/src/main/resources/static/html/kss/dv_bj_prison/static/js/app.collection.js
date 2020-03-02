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
    ChartLPie = require("./modules/charts/appCollection/ChartLPie"), //全市累计收押监管人员 pie
    ChartLBar = require("./modules/charts/appCollection/ChartLBar"), //突发事件情况 bar
    ChartRTBar = require("./modules/charts/appCollection/ChartRTBar"), //智慧型 bar
    ChartRPieBar = require("./modules/charts/appCollection/ChartRPieBar"), //left top bar
    ChartLPieBar = require("./modules/charts/appCollection/ChartLPieBar"), //left bottom pie bar

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
    // 标记
    mark_: {
      loadFirst: true
    }
  };


  // 事件绑定
  evenbind = function () {
    collClickFn('listPop','list0',true);
  };
  console.log("box0")

  // 应用初始化方法
  init_Application = function () {
    evenbind();
    // 时间
    showTimes();
    // left top
    lTFn("box0");
    // left bottom
    lBFn("box1");
    // right top
    rTFn("box2");
    // right bottom
    rBFn("box3");
    // center top
    cTFn("box6");
    // center bottom
    cBLFn("box7");
    // 菜单初始化
    // MenuTool.initMenu(document.querySelector('.dper-menus-left'), document.querySelector('.dper-menus-right'));

    var tmt;
    $(window).on('resize',function(resp){
      clearTimeout(tmt);
      tmt = setTimeout(function(){
        _.each(basedata.chart,function(itm){
          if(itm.myChart) itm.myChart.resize()
        })
      },500)
    });
    // 重新请求接口数据不触发页面加载动画
    basedata.mark_.loadFirst = false;
  };
  // 时间
  var showTimes = function () {
    function st() {
      DperDate.showTime(".dper-time-t", "yyyy年M月d日");
      DperDate.showTime(".dper-time-b", "HH:mm:ss");
    }
    st();
    setInterval(st, 1000);
  };

  var collClickFn  = function(el,type,use){
    var $backBtn = $('#backBtn'),
        $elP = $('#pop'),
      $el = $(App.getView(el).querySelector('.table-body')),
      $elT = $(App.getView(el).querySelector('.table-header')),
      $swiper = '<div class="swiper-container dper-full-h" id="popSwiper">' +
        ' <div class="swiper-wrapper dper-full-h"></div>' +
        ' <div class="swiper-scrollbar" id="popScr"></div>' +
        '</div>'
    ;
    if (use){
      $elP.css({
        'opacity': 0,
        'z-index': -1
      });
    }else {
      $elP.css({
        'opacity': 1,
        'z-index': 99
      });
    }
    $elT.empty();
    $el.empty();
    $el.append($swiper);
    $backBtn.on('click',function(){
      $elT.empty();
      $el.empty();
      $elP.css({
        'opacity': 0,
        'z-index': -1
      })
    });
    gainCollBackDataFn(el,type,renderCollListFn)
  };
  var renderCollListFn = function(el,type,resp){
    var $elT = $(App.getView(el).querySelector('.table-header')),
      $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $head0 = '<div class="itm-0 fl dper-full-h dper-por">' +
        ' <span class="malign-center-text text-overflow-e">{{name}}</span>' +
        '</div>',
      $body0 = '<div class="swiper-slide">' +
        ' <div class="itm-0 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{name}}</span>' +
        ' </div>' +
        ' <div class="itm-0 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value0}}</span>' +
        ' </div>' +
        ' <div class="itm-0 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value1}}</span>' +
        ' </div>' +
        ' <div class="itm-0 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value2}}</span>' +
        ' </div>' +
        '</div>',
      $head1 = '<div class="itm-1 fl dper-full-h dper-por">' +
        ' <div class="malign-center-text text-overflow-e">' +
        '   <p>{{name}}</p>' +
        '   <p class="unit">{{unit}}</p>' +
        ' </div>' +
        '</div>',
      $body1 = '<div class="swiper-slide">' +
        ' <div class="itm-1 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{name}}</span>' +
        ' </div>' +
        ' <div class="itm-1 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value0}}</span>' +
        ' </div>' +
        ' <div class="itm-1 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value1}}</span>' +
        ' </div>' +
        ' <div class="itm-1 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value2}}</span>' +
        ' </div>' +
        ' <div class="itm-1 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value3}}</span>' +
        ' </div>' +
        '</div>',
      $head2 = '<div class="itm-2 fl dper-full-h dper-por">' +
        ' <div class="malign-center-text text-overflow-e">' +
        '   <p>{{name}}</p>' +
        '   <p class="unit">{{unit}}</p>' +
        ' </div>' +
        '</div>',
      $body2 = '<div class="swiper-slide">' +
        ' <div class="itm-2 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{name}}</span>' +
        ' </div>' +
        ' <div class="itm-2 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value0}}</span>' +
        ' </div>' +
        ' <div class="itm-2 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value1}}</span>' +
        ' </div>' +
        '</div>',
      $head3 = '<div class="itm-3 fl dper-full-h dper-por">' +
        ' <span class="malign-center-text text-overflow-e">{{name}}' +
        ' </span>' +
        '</div>',
      $body3 = '<div class="swiper-slide">' +
        ' <div class="itm-3 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{name}}</span>' +
        ' </div>' +
        ' <div class="itm-3 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value}}</span>' +
        ' </div>' +
        '</div>',
      $head4 = '<div class="itm-4 fl dper-full-h dper-por">' +
        ' <span class="malign-center-text text-overflow-e">{{name}}</span>' +
        '</div>',
      $body4 = '<div class="swiper-slide">' +
        ' <div class="itm-4 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{name}}</span>' +
        ' </div>' +
        ' <div class="itm-4 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value}}</span>' +
        ' </div>' +
        ' <div class="itm-4 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value1}}</span>' +
        ' </div>' +
        ' <div class="itm-4 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value2}}</span>' +
        ' </div>' +
        ' <div class="itm-4 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value3}}</span>' +
        ' </div>' +
        ' <div class="itm-4 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value4}}</span>' +
        ' </div>' +
        ' <div class="itm-4 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value5}}</span>' +
        ' </div>' +
        ' <div class="itm-4 fl dper-full-h dper-por">' +
        '   <span class="malign-center-text text-overflow-e">{{value6}}</span>' +
        ' </div>' +
        '</div>',
        mySwiper = null
    ;
    if (type === 'list0'){
      _.each(resp.header,function(itm){
        $elT.append(rendarTpl(itm,$head0,'itm'))
      });
      _.each(resp.body,function(itm){
        $el.append(rendarTpl(itm,$body0,'itm'))
      })
    }
    else if(type === 'list1'){
      _.each(resp.header,function(itm){
        $elT.append(rendarTpl(itm,$head1,'itm'))
      });
      _.each($elT.find('.unit'),function(itm){
        if ($(itm).text() === '--'){
          $(itm).text('')
        }
      });
      _.each(resp.body,function(itm){
        $el.append(rendarTpl(itm,$body1,'itm'))
      })
    }
    else if(type === 'list2'){
      _.each(resp.header,function(itm){
        $elT.append(rendarTpl(itm,$head2,'itm'))
      });
      _.each($elT.find('.unit'),function(itm){
        if ($(itm).text() === '--'){
          $(itm).text('')
        }
      });
      _.each(resp.body,function(itm){
        $el.append(rendarTpl(itm,$body2,'itm'))
      })
    }
    else if(type === 'list3'){
      _.each(resp.header,function(itm){
        $elT.append(rendarTpl(itm,$head3,'itm'))
      });
      _.each(resp.body,function(itm){
        $el.append(rendarTpl(itm,$body3,'itm'))
      })
    }
    else {
      _.each(resp.header,function(itm){
        $elT.append(rendarTpl(itm,$head4,'itm'))
      });
      _.each(resp.body,function(itm){
        $el.append(rendarTpl(itm,$body4,'itm'))
      })
    }

    mySwiper = new Swiper('#popSwiper',{
      // autoplay: {
      //   delay: 5000
      // },
      scrollbar: {
        el: '#popScr'
      },
      direction: 'vertical',
      slidesPerView: 10
    });

  };
  var gainCollBackDataFn = function(el,type,cb){
    basefn(el,{
      App:App,
      desc: 'pop list',
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function(resp){
        resp = resp.data.popCllection[type];
        cb && cb(el,type,resp)
      }
    })
  };
  // left top
  var lTFn = function(el){
    if(!basedata.chart.child3) basedata.chart.child3 = new ChartLBar(App.getView(el).querySelector('.top-bar'));
    gainLTFn(el,renderLTFn)
  };
  var renderLTFn = function(el,resp){
    var  $elT = $(App.getView(el).querySelector('.top-pie')),
      $elB = $(App.getView(el).querySelector('.top-label')).find('.cont-box'),
      $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title'),
      $child = '<div class="pie-itm fl dper-full-h dper-por font-16">' +
        ' <div class="pie-chart dper-full-h dper-por z-level-1" id="pie{{id}}"></div>' +
        ' <div class="pie-name malign-center z-level-2">{{name}}</div>' +
        ' <div id="pieClick{{id}}" class="dper-full-w dper-full-h malign-bl z-level-6 dper-cursor"></div>' +
        '</div>',
      $childB = '<div class="label-itm fl dper-full-h">' +
        ' <p class="dper-por"><span class="malign-center-text text-overflow-e">{{name}}</span></p>' +
        ' <p class="dper-por"><span class="malign-center-text text-overflow-e">{{value}}<em class="unit">{{unit}}</em></span></p>' +
        '</div>'
    ;
    $t.text(resp.name);
    $elT.empty();
    $elB.empty();
    _.each(resp.tlist,function(itm,idx){
      itm.id = idx;
      $elT.append(rendarTpl(itm,$child,'itm'))
    });
    _.each(resp.blist,function(itm){
      $elB.append(rendarTpl(itm,$childB,'itm'))
    });
    _.each($elB.find('.unit'),function(itm){
      if ($(itm).text() === '--'){
        $(itm).text('')
      }
    });
    if(!basedata.chart.child0) basedata.chart.child0 = new ChartLPie($('#pie0')[0]);
    if(!basedata.chart.child1) basedata.chart.child1 = new ChartLPie($('#pie1')[0]);
    if(!basedata.chart.child2) basedata.chart.child2 = new ChartLPie($('#pie2')[0]);
    $('#pieClick1').on('click',function(){
      collClickFn('listPop','list0',false)
    });
    renderChartFn(resp)
  };
  var renderChartFn = function(resp){
    basedata.chart.child0.render(resp.tlist[0]);
    basedata.chart.child1.render(resp.tlist[1]);
    basedata.chart.child2.render(resp.tlist[2]);
    basedata.chart.child3.render(resp.clist)
  };
  var gainLTFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "left top",
      url: config.interface.box0,
      useLoading: basedata.mark_.loadFirst,
      cb: function(resp){
        resp = resp.data.collection.lt;
        cb&&cb(el,resp)
      }
    })
  };
  // left bottom
  var lBFn = function(el){
    if(!basedata.chart.child4) basedata.chart.child4 = new ChartLPieBar(App.getView(el).querySelector('.chart-box'));
    gainLBFn(el,renderLBFn)
  };
  var renderLBFn = function(el,resp){
    var $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title'),
        $smallV = $(App.getView(el)).find('.small-value'),
        $bigV = $(App.getView(el)).find('.big-value'),
        $bigWrite = $(App.getView(el)).find('.big-write')
    ;
    $t.text('');
    $t.text(resp.name);
    $bigWrite.text('');
    $smallV.text('');
    $bigV.text('');
    $smallV.text(resp.pie0[0].value);
    $bigV.text(resp.pie1[0].value);
    $bigWrite.text(resp.pieName);
    basedata.chart.child4.render(resp);
    $smallV.on('click',function(){
      collClickFn('listPop','list3',false)
    });
    $bigV.on('click',function(){
      collClickFn('listPop','list3',false)
    })
  };
  var gainLBFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "left bottom",
      url: config.interface.box1,
      useLoading: basedata.mark_.loadFirst,
      cb: function(resp){
        resp = resp.data.collection.lb;
        cb&&cb(el,resp)
      }
    })
  };
  // right top
  var rTFn = function(el){
    if(!basedata.chart.child5) basedata.chart.child5 = new ChartRTBar(App.getView(el).querySelector('.top-bar'));
    gainRTFn(el,renderRTFn)
  };
  var renderRTFn = function(el,resp){
    var $el = $(App.getView(el).querySelector('.bottom-label')),
      $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title'),
      $child = '<div class="label-itm font-14 fl dper-por dper-full-h">' +
        ' <div class="inside-size l-inside-img bg-img-full malign-center-top">' +
        ' <div class="malign-center-text">' +
        ' <p>{{value}}</p>' +
        ' <p>{{unit}}</p>' +
        '</div>' +
        '</div>' +
        ' <span class="label-name malign-center-bottom text-overflow-e font-16 co-00c0ff">{{name}}</span>' +
        '</div>'
    ;
    $t.text('');
    $t.text(resp.name);
    $el.empty();
    _.each(resp.label,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    basedata.chart.child5.render(resp);
    $(App.getView(el).querySelector('.top-bar')).on('click',function(){
      collClickFn('listPop','list2',false)
    })
  };
  var gainRTFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "right top",
      url: config.interface.box2,
      useLoading: basedata.mark_.loadFirst,
      cb: function(resp){
        resp = resp.data.collection.rt;
        var col = [
          {
            co0: '#754abf',
            co1: '#f75271'
          },
          {
            co0: '#2a54b4',
            co1: '#0cf'
          },
          {
            co0: '#278d65',
            co1: '#62c467'
          },
          {
            co0: '#f66800',
            co1: '#ffb400'
          }
        ];
        _.each(resp.list,function(itm,idx){
          itm.co0 = col[idx].co0;
          itm.co1 = col[idx].co1
        });
        cb&&cb(el,resp)
      }
    })
  };
  // right bottom
  var rBFn = function(el){
    if (!basedata.chart.child6) basedata.chart.child6 = new ChartRPieBar(App.getView(el).querySelector('.pie-bar-chart'));
    gainRBFn(el,renderRBFn)
  };
  var renderRBFn = function(el,resp){
    var $el = $(App.getView(el).querySelector('.roll-box')),
      $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title'),
      $rollL = $(App.getView(el).querySelector('.roll-left')),
      $rollR = $(App.getView(el).querySelector('.roll-right')),
      $rollCL = $(App.getView(el).querySelector('.roll-cont-left')).find('.roll-inside-box'),
      $rollCR = $(App.getView(el).querySelector('.roll-cont-right')).find('.roll-inside-box'),
      $child = '<div class="roll-itm roll-itm-img bg-img-full fl dper-por dper-full-h">' +
        ' <span class="malign-center-text text-overflow-e roll-number">{{value}}</span>' +
        '</div>'
    ;
    $t.text('');
    $rollL.text('');
    $rollR.text('');
    $rollCL.empty();
    $rollCR.empty();
    $t.text(resp.name);
    $rollL.text(resp.lname);
    $rollR.text(resp.rname);
    _.each(resp.arrl,function(itm){
      $rollCL.append(rendarTpl(itm,$child,'itm'))
    });
    _.each(resp.arrr,function(itm){
      $rollCR.append(rendarTpl(itm,$child,'itm'))
    });
    $(App.getView(el).querySelector('.roll-cont-left')).on('click',function(){
      collClickFn('listPop','list4',false)
    });
    basedata.chart.child6.render(resp)
  };
  var gainRBFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "right bottom",
      url: config.interface.box3,
      useLoading: basedata.mark_.loadFirst,
      cb: function(resp){
        resp = resp.data.collection.rb;
        var $numL = [],
          $numR = [],
          col = [
          {
            co0: '#2783cd',
            co1: '#0ecccd'
          },
          {
            co0: '#fd4019',
            co1: '#fe7019'
          },
          {
            co0: '#fca201',
            co1: '#fdaf13'
          }
        ],
          $radius = [
            ['72%', '80%'],
            ['52%', '60%'],
            ['32%', '40%']
          ],
        $sum = 0;
        _.each(resp.lvalue.toString().split(''),function(itm){
          $numL.push({
            value: itm
          })
        });
        _.each(resp.rvalue.toString().split(''),function(itm){
          $numR.push({
            value: itm
          })
        });
        _.each(resp.llist,function(itm,idx){
          $sum += Number(itm.value);
          itm.co0 = col[idx].co0;
          itm.co1 = col[idx].co1;
          itm.radius = $radius[idx]
        });
        resp.sum = $sum;
        resp.arrl = $numL;
        resp.arrr = $numR;
        cb&&cb(el,resp)
      }
    })
  };
  // center top
  var cTFn = function(el){
    gainCTFn(el,renderCTFn)
  };
  var renderCTFn = function(el,resp){
    var $t = $('#la-title'),
      $el = $(App.getView(el)),
      $child = '<div class="center-itm center-itm-img bg-img-full malign-tl dper-cursor">' +
        ' <div class="center-cont malign-center align-center">' +
        ' <p class="font-28 co-blue-head">{{value}}</p>' +
        ' <p class="font-14 text-overflow-e">{{nt}}</p>' +
        ' <p class="font-14 text-overflow-e">{{nb}}</p>' +
        '</div>' +
        '</div>',
      $x = 0,
      $y = 0,
      $deg = 360 / resp.list.length
    ;
    _.each(resp.list,function(itm){
      itm.nt = itm.name.substring(0,5);
      itm.nb = itm.name.substring(5,itm.name.length);
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    _.each(resp.list,function(itm,idx){
      $x = Number( $el.width()/2) + Math.sin($deg*idx*Math.PI/180)*Number($el.width()/2);
      $y = Number( $el.width()/2) - Math.cos($deg*idx*Math.PI/180)*Number($el.width()/2);
      $el.find('.center-itm').eq(idx).css({
        'top':$y - Number($el.find('.center-itm').eq(idx).width()/2)+'px',
        'left':$x - Number($el.find('.center-itm').eq(idx).height()/2)+'px'
      })
    });
    $t.text('');
    $t.text(resp.name);
    $($el.find('.center-itm').eq($el.find('.center-itm').length-1)).on('click',function(){
      collClickFn('listPop','list1',false)
    });
  };
  var gainCTFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "center top",
      url: config.interface.box6,
      useLoading: basedata.mark_.loadFirst,
      cb: function(resp){
        resp = resp.data.collection.ct;
        cb&&cb(el,resp)
      }
    })
  };
  // center bottom
  var cBLFn = function(el){
    var
      $el = $(App.getView(el)),
      $swiper = '<div class="swiper-container dper-full-h" id="swiper0">' +
        '<div class="swiper-wrapper dper-full-h"></div>' +
        '</div>';
    $el.empty();
    $el.append($swiper);
    gainCBLFn(el,renderCBLFn)
  };
  var renderCBLFn = function(el,resp){
    var $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title'),
      $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $child = '<div class="swiper-slide dper-por">' +
        ' <span class="prison-write co-00c0ff font-16 malign-center-left text-overflow-e">{{name}}</span>' +
        ' <div class="prison-bar malign-center-left">' +
        '   <div class="pro-bar {{cla}} malign-center-left" data-value="{{value}}">' +
        '     <span class="pro-value malign-center-right font-16 dper-fit">{{value}}分</span>   ' +
        '   </div>' +
        ' </div>' +
        ' <span class="prison-value malign-center-right text-overflow-e font-16 dper-fit">100分</span>' +
        '</div>',
      mySwiper = null
    ;
    $t.text('');
    $el.empty();
    $t.text(resp.name);
    _.each(resp.list,function(itm,idx){
      itm.cla = idx<3? 'pro-bar-'+idx:'pro-bar-other';
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    _.each($el.find('.pro-bar'),function(itm,idx){
      setTimeout(function(resp){
        $(itm).css('width',(Number($(itm).data('value'))/100)*100+'%')
      },50*idx)
    });
    mySwiper = new Swiper("#swiper0",{
      autoplay: {
        delay: 5000
      },
      direction: "vertical",
      grabCursor: true,
      slidesPerView: 3
    });
    $('#swiper0').on('mouseenter',function(){
      mySwiper.autoplay.stop();
    });
    $('#swiper0').on('mouseleave',function(){
      mySwiper.autoplay.start();
    })
  };
  var gainCBLFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "center bottom left",
      url: config.interface.box7,
      useLoading: basedata.mark_.loadFirst,
      cb: function(resp){
        resp = resp.data.collection.cb;
        cb&&cb(el,resp)
      }
    })
  };
});
