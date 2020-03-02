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
    Swiper = require("Swiper"),                     // swiper
    ChartRadar = require("./modules/charts/appManage/ChartRadar"), // 风险画像
    Gauge = require("./modules/charts/appManage/Gauge"),  //  圆盘进度条
    GaugeDefault = require("./modules/charts/appManage/GaugeDefault"),  //圆盘进度条
    ChartSankey = require("./modules/charts/appManage/ChartSankey"),  // 人员关系

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
      child1: null
    },
    gauge:{
      child0: null,
      child1: null,
      child2: null,
      child3: null
    },
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
    // 一级是0 二级是1 三级是2
    //0是蓝色 1是红色

    // left top
    lTFn("box0");
    // left center
    lCFn("box1");
    // left bottom
    lBFn("box2");
    // right top
    rTFn("box3");
    // right bottom
    rBFn("box4");
    // center top left
    cTLFn("box5");
    // center top right
    cTRFn("box6");
    // center body
    cBFn("box7");
    // 菜单初始化
    MenuTool.initMenu(document.querySelector('.dper-menus-left'), document.querySelector('.dper-menus-right'));

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
  // left top
  var lTFn = function(el){
    var $el = $(App.getView(el).querySelector('.right-list')),
      $swiper = "<div class='swiper-container dper-full-h' id='swiper0'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainLTFn(el,renderLTFn)
  };
  var renderLTFn =function(el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
       $img = $(App.getView(el).querySelector('.prison-person-size')),
      $msgWrite = $(App.getView(el)).find('.msg-write'),
      $t = $(App.getView(el)).parent().parent().parent().siblings('.child-header').find('.la-title'),
        $child = "<div class='swiper-slide dper-por font-16'>" +
          " <div class='msg-box malign-center dper-full-w'>" +
          "   <div class='left-msg left-msg-img bg-img-full dper-full-h malign-center-left'>" +
          "     <span class='malign-center-right text-overflow-e dper-full-w align-right'>{{name}}</span>" +
          "   </div>" +
          "   <div class='right-msg right-msg-img bg-img-full dper-full-h malign-center-right'>" +
          "      <span class='malign-center-left dper-fw co-00aeff text-overflow-e dper-full-w'>{{msg}}</span>" +
          "   </div>" +
          " </div>" +
          "</div>",
    mySwiper = null;
    $el.empty();
    $t.text("");
    $msgWrite.text("");
    $t.text(resp.name);
    $msgWrite.text(resp.msg);
    $img.css("backgroundImage","url("+resp.img+")");
    _.each(resp.msgList,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    })

    mySwiper = new Swiper("#swiper0",{
      direction: "vertical",
      grabCursor: true,
      slidesPerView: 12,
      slidesPerGroup: 12
    })
  };
  var gainLTFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "left top",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.manage.lt;
        cb&&cb(el,resp)
      }
    })
  };
  //   left center
  var lCFn = function(el){
    var $el = $(App.getView(el).querySelector('.side-top')).find(".body"),
      $elB = $(App.getView(el).querySelector('.side-bottom')).find(".body"),
        $swiper = "<div class='swiper-container dper-full-h' id='swiper1'>" +
          " <div class='swiper-wrapper dper-full-h'></div>" +
          "</div>",
        $swiperB = "<div class='swiper-container dper-full-h' id='swiper2'>" +
          " <div class='swiper-wrapper dper-full-h'></div>" +
          "</div>";
    $el.empty();
    $el.append($swiper);
    $elB.empty();
    $elB.append($swiperB);
    gainLCFn(el,renderLCFn)
  };
  var renderLCFn =function(el,resp){
    var $elT = $(App.getView(el).querySelector('.side-top')).find('.swiper-wrapper'),
      $elB = $(App.getView(el).querySelector('.side-bottom')).find('.swiper-wrapper'),
      $t=$(App.getView(el)).parent().parent().parent().siblings('.child-header').find('.la-title'),
      $tT=$(App.getView(el).querySelector('.top-msg')),
      $tB=$(App.getView(el).querySelector('.bottom-msg')),
      $childT = "<div class='swiper-slide dper-por'>" +
        " <div class='inside-swiper malign-center dper-full-w'></div>" +
        "</div>",
      $childB = "<div class='swiper-slide dper-por'>" +
        " <div class='inside-swiper malign-center dper-full-w'></div>" +
        "</div>",
      $childS = "<div class='child-itm dper-full-h dper-por'>" +
        " <div class='child-left child-left-img bg-img-full malign-center-left dper-full-h'>" +
        " <span class='malign-center-right dper-full-w align-left text-overflow-e'>{{name}}</span></div>" +
        " <div class='child-right child-right-img bg-img-full malign-center-right dper-full-h'>" +
        " <span class='malign-center-left co-00aeff text-overflow-e dper-full-w'>{{value}}</span></div>" +
        "</div>" +
        "</div>",
      mySwiper = null,
      mySwiperB = null
    ;
    $t.text("");
    $tT.text("");
    $tB.text("");
    $t.text(resp.name);
    $tT.text(resp.t.name);
    $tB.text(resp.b.name);
    _.each(resp.t.list,function(itm,idx){
      $elT.append(rendarTpl(itm,$childT,'itm'));
      _.each(itm,function(key){
        $elT.find(".inside-swiper").eq(idx).append(rendarTpl(key,$childS,'key'))
      })
    });
    _.each(resp.b.list,function(itm,idx){
      $elB.append(rendarTpl(itm,$childB,'itm'));
      _.each(itm,function(key){
        $elB.find(".inside-swiper").eq(idx).append(rendarTpl(key,$childS,'key'))
      })
    });
    mySwiper = new Swiper("#swiper1",{
      direction: "vertical",
      grabCursor: true,
      slidesPerView: 1,
      slidesPerGroup: 1
    });
    mySwiperB = new Swiper("#swiper2",{
      direction: "vertical",
      grabCursor: true,
      slidesPerView: 2,
      slidesPerGroup: 2
    })
  };
  var gainLCFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "left center",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.manage.lc;
        resp.t.list = tranLCFn(resp.t.list,2);
        resp.b.list = tranLCFn(resp.b.list,2);
        cb&&cb(el,resp)
      }
    })
  };
  var tranLCFn = function (resp,number) {
    var r = [], tem = [], arr = [];
    _.each(resp, function (itm, idx) {
      tem.push(itm);
      if ((idx + 1) % number === 0 && idx !== 0) {
        r.push(tem);
        tem = [];
      }
      if (resp.length % number === 0 && resp.length !== 0) {
        return false
      } else {
        arr = tem;
      }
    });
    if (arr.length >= 1) {
      r.push(arr);
    }
    return r
  };
  //   left bottom
  var lBFn = function(el){
    if (!basedata.chart.child0) basedata.chart.child0 = new ChartRadar(App.getView(el));
    gainLBFn(el,renderLBFn)
  };
  var renderLBFn =function(el,resp){
    var $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title');
    $t.text('');
    $t.text(resp.name);
    basedata.chart.child0.render(resp)
  };
  var gainLBFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "left bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.manage.lb;
        var $resp = {
          name: resp.name,
          list: [],
          data: [],
          unit: resp.unit
        };
        _.each(resp.list,function(itm){
          $resp.list.push({
            name: itm.name,
            value: itm.value,
            max: 20
          });
          $resp.data.push(itm.value)
        });
        cb&&cb(el,$resp)
      }
    })
  };
  //  right top
  var rTFn = function(el){
    if(!basedata.chart.child1) basedata.chart.child1 = new ChartSankey(App.getView(el));
    gainRTFn(el,renderRTFn)
  };
  var renderRTFn =function(el,resp){
    var $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title');
    $t.text('');
    $t.text(resp.name);
    basedata.chart.child1.render(resp.list)
  };
  var gainRTFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "right top",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.manage.rt;
        cb&&cb(el,resp)
      }
    })
  };
  //  right bottom
  var rBFn = function(el){
    var $el = $(App.getView(el).querySelector('.body')),
      $swiper = '<div class="swiper-container dper-full-h" id="swiper3">' +
        ' <div class="swiper-wrapper dper-full-h"></div>' +
        ' <div class="swiper-scrollbar"></div>' +
        '</div>';
    $el.empty();
    $el.append($swiper);
    gainRBFn(el,renderRBFn)
  };
  var renderRBFn =function(el,resp){
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $elTitle = $(App.getView(el).querySelector('.head')),
      $t = $(App.getView(el)).parent().parent().siblings('.child-header').find('.la-title'),
      $childHead = '<div class="child-itm fl dper-full-h dper-por"><span class="deci-write {{cla}}">{{name}}</span></div>',
    $child = '<div class="swiper-slide">' +
      ' <div class="child-itm fl dper-full-h"></div>' +
      ' <div class="child-itm fl dper-full-h dper-por">' +
      '   <span class="malign-center-text text-overflow-e">{{time}}</span> ' +
      ' </div>' +
      ' <div class="child-itm fl dper-full-h dper-por">' +
      '   <div class="circle-box malign-center">' +
      '     <div class="circle-side malign-center"></div>' +
      '   </div> ' +
      '   <div class="circle-line-top malign-center-top"></div>' +
      '   <div class="circle-line-bottom malign-center-bottom"></div>' +
      '</div>' +
      ' <div class="child-itm fl dper-full-h dper-por">' +
      '   <span class="malign-center-left co-00aeff">{{name}}</span>' +
      ' </div>' +
      '</div>',
    mySwiper = null;
    $t.text('');
    $t.text(resp.name);
    $elTitle.empty();
    $el.empty();
    _.each(resp.head,function(itm,idx){
      itm.cla=  idx===1?'malign-center-text':'malign-center-left';
      $elTitle.append(rendarTpl(itm,$childHead,'itm'))
    });
    _.each($elTitle.find('.deci-write'),function(itm){
      ($(itm).text() === '--')&&$(itm).text('')
    });
    _.each(resp.body,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    mySwiperB = new Swiper("#swiper3",{
      direction: "vertical",
      scrollbar: {
        el: '.swiper-scrollbar',
        hide: true,
        draggable: true
      },
      grabCursor: true,
      mousewheel: true,
      slidesPerView: 9
    })
  };
  var gainRBFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "right bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.manage.rb;
        cb&&cb(el,resp)
      }
    })
  };
  // center top left
  var cTLFn = function(el){
    if (!basedata.gauge.child0) basedata.gauge.child0 = Gauge({el: App.getView(el).querySelector('.gauge-active')});
    if (!basedata.gauge.child1) basedata.gauge.child1 = GaugeDefault({el: App.getView(el).querySelector('.gauge-default-box')});
    gainCTLFn(el,renderCTLFn)
  };
  var renderCTLFn =function(el,resp){
    var $el = $(App.getView(el).querySelector('.number-box')),
        $child = "<span class='number-itm malign-tl co-999 font-18'>{{name}}</span>",
        $detail = $(App.getView(el).querySelector('.detail-num')),
        $detailName = $(App.getView(el).querySelector('.detail-name'))
    ;
    $el.empty();
    $detail.text('');
    $detailName.text('');
    $detail.text(resp.value+'%');
    $detailName.text(resp.name);
    _.each(resp.list,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    basedata.gauge.child0.render(resp.value,'');
    basedata.gauge.child1.render(100,'');
    gaugeRotate(App.getView(el).querySelectorAll('.gauge-pointer'), resp.value)
  };
  var gainCTLFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "center top left",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.manage.ctl;
        cb&&cb(el,resp)
      }
    })
  };
  // center top right
  var cTRFn = function(el){
    if (!basedata.gauge.child2) basedata.gauge.child2 = Gauge({el: App.getView(el).querySelector('.gauge-active')});
    if (!basedata.gauge.child3) basedata.gauge.child3 = GaugeDefault({el: App.getView(el).querySelector('.gauge-default-box')});
    gainCTRFn(el,renderCTRFn)
  };
  var renderCTRFn =function(el,resp){
    var $el = $(App.getView(el).querySelector('.number-box')),
      $child = "<span class='number-itm malign-tl co-999 font-18'>{{name}}</span>",
      $val = Number(resp.type)*50,
      $detail = $(App.getView(el).querySelector('.detail-num')),
      $detailName = $(App.getView(el).querySelector('.detail-name'))
    ;
    $el.empty();
    $detail.text('');
    $detailName.text('');
    $detail.text(resp.value);
    $detailName.text(resp.name);
    _.each(resp.list,function(itm){
      $el.append(rendarTpl(itm,$child,'itm'))
    });
    basedata.gauge.child2.render($val,'');
    basedata.gauge.child3.render(100,'');
    gaugeRotate(App.getView(el).querySelectorAll('.gauge-pointer'), $val)
  };
  var gainCTRFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "center top right",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.manage.ctr;
        cb&&cb(el,resp)
      }
    })
  };
  // center bottom
  var cBFn = function(el){
    gainCBFn(el,renderCBFn)
  };
  var renderCBFn =function(el,resp){
    var $el = $(App.getView(el)),
      $child = '<div class="sex-itm malign-tl">' +
        ' <div class="sex-left {{cla}} {{cla2}} bg-img-full">' +
        '   <span class="malign-center-text text-overflow-e">{{name}}</span> ' +
        ' </div>' +
        ' <div class="sex-right sex-right-img-{{id}} bg-img-full {{cla3}}"></div>' +
        '</div>';
    $el.empty();
    _.each(resp,function(itm,idx){
      itm.id = idx;
      $el.append(rendarTpl(itm,$child,'itm'))
    })
  };
  var gainCBFn = function(el,cb){
    basefn(el,{
      App:App,
      desc: "center bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp){
        resp = resp.data.manage.cb;
        var $resp = [];
        _.each(resp,function(itm,idx){
          $resp.push({
            name: itm.name,
            cla: 'sex-type-'+itm.type,
            cla2: idx < 3?'malign-tl':'malign-tr',
            cla3: idx < 3?'malign-tr':'malign-tl'
          })
        });
        cb&&cb(el,$resp)
      }
    })
  };

  // 仪表盘指针旋转角度转换
  function gaugeRotate(el, num) {
    var min = -90, rotate;
    rotate = min + Number(num) * 1.8;
    rotate = Number(rotate.toFixed(0));
    $(el).css({
      "transform": "translate(-50%,14%) rotate(" + rotate + "deg)",
      "-webkit-transform": "translate(-50%,14%) rotate(" + rotate + "deg)",
      "-moz-transform": "translate(-50%,14%) rotate(" + rotate + "deg)",
      "-ms-transform": "translate(-50%,14%) rotate(" + rotate + "deg)",
      "-o-transform": "translate(-50%,14%) rotate(" + rotate + "deg)"
    });
  }
});
