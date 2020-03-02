define(function (require) {
  // 页面初始化
  require("init");
  require("jquery");
  require("UnderScore");

  localStorage.getItem("idjsbhxf")
  var strify = localStorage.getItem("idjsbhxf")
  // console.log(strify+"----")
  var config =
    require("app_config"),                      // 项目配置
    DperV = require("DperV"),                       // 视图解决方案
    yer = require('yer'),                           // 提示工具
    basefn = require('dperUtils/function'),         // 快码
    rendarTpl = require("rendarTpl"),               // 模板渲染
    DperDate = require("dperUtils/date"),           // 时间工具
    StringUtil = require("dperUtils/string"),       // 字符串工具
    ObjectUtil = require("dperUtils/object"),       // 对象工具
    // showTime = require("dperUtils/date").showTime, // 日期工具
    MenuTool = require("./menus.init"),             // 菜单工具
    Swiper = require("Swiper"),
    ChartFill = require("./modules/charts/appIndex/ChartFill"), // 波浪图
    ChartLLine = require("./modules/charts/appIndex/ChartLline"), // 左边折线图
    ChartPie = require("./modules/charts/appIndex/ChartPie"), // 右边 top 饼图
    ChartRLine = require("./modules/charts/appIndex/ChartRline"), // 右边 bottom 折线图
    toastr = require("../utils/toastr/toastr.min"), //信息 弹出框
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
  var wgjq = "";
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
    // 安全管理
    // lT3Fn("box11");
    // 提讯会见
    lT4Fn("box12");
    // 关押期限
    lT5Fn("box13");
    // left bottom bottom
    lBBFn("box7");
    // right top top
    rTTFn("box4");
    // right top bottom
    rTBFn("box5");
    // right center
    rCFn("box6");
    // right bottom
    rBFn("box3");
    // center top
    cTFn("box8");
    // center center
    cCFn("box9");
    // center bottom
    cBFn("box10");
    var tmt;
    $(window).on('resize', function (resp) {
      clearTimeout(tmt);
      tmt = setTimeout(function () {
        _.each(basedata.chart, function (itm) {
          if (itm) itm.myChart.resize()
        })
      }, 500)
    })
    // 重新请求接口数据不触发页面加载动画
    basedata.mark_.loadFirst = false;
  };
  // left top top
  var lTTFn = function (el) {
    var $el = $(App.getView(el)),
      $html = '' +
        '              <ul class="lefttop">\n' +
        '              </ul>';
    $el.empty();
    $el.append($html);
    gainLTTFn(el, renderLTTFn)
  };
  var renderLTTFn = function (el, resp) {

    var $el = $(App.getView(el).querySelector('.lefttop')),
      $ft = $(App.getView(el)).parent().parent().parent().parent().siblings('.child-header').find('.la-title'),
      $child = '' +
        '<li class="">' +
        ' <span class="li-name">{{name}}</span>' +
        ' <span class="li-msg"><span class="co-blue-person {{class}}">{{msg}}</span>人</span>' +
        '</li>';
    $ft.text('');
    $ft.text(resp.name);
    _.each(resp.list, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'))
    });
    $("#box0 .co-blue-person").on("click", function () {
      if ($(this).hasClass("people")) {
        $(".mask").removeClass("hid");
        //点击返回
        $(".back").on("click", function () {
          $(".mask").addClass("hid")
        })
        var data = resp.list.find(item => item.name == "今日入所");
        var data = "&rsrq=now"
        MaskFn("pop", data);
      }

    })
  };
  var gainLTTFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "left top top",
      url: config.interface.box0 + "?jsbh=" + strify,
      // url: config.interface.box0,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        // console.log(resp)
        // console.log(resp)
        resp = resp.data.zsry;
        console.log(resp)
        cb && cb(el, resp)
      }
    })

  };

  // left top bottom
  var lTBFn = function (el) {
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper-container swiper-li' id='swiper7'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainLTBFn(el, renderLTBFn)
  };
  var renderLTBFn = function (el, resp) {
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),

      $ft = $(App.getView(el)).parent().parent().parent().siblings('.child-header').find('.la-title'),
      $child1 = '<div class="swiper-slide dper-full-h dper-por font-16">' +
        '<li class="link-li">' +
        ' <span class="li-name">{{name1}}</span>' +
        ' <span class="li-msg {{class1}}"><span class="co-blue-person " style="cursor:pointer" data-id="{{name1}}">{{msg1}}</span>人</span>' +
        '</li>' +
        '<li class="link-li">' +
        ' <span class="li-name">{{name2}}</span>' +
        ' <span class="li-msg {{class2}}"><span class="co-blue-person " style="cursor:pointer" data-id="{{name2}}">{{msg2}}</span>人</span>' +
        '</li>' +
        '</div>',
      mySwiper = null;
    $ft.text('');
    $ft.text("安全管理");
    _.each(resp.aqgl, function (itm) {
      $el.append(rendarTpl(itm, $child1, 'itm'))
    });
    mySwiper = new Swiper("#swiper7", {
      loop: true,
      // autoplay:false,
      autoplay: {
        delay: basedata.time
      },
      direction: "vertical",
      grabCursor: true,
      slidesPerView: 2,
      slidesPerGroup: 2
    });
    $('#swiper7').on('mouseenter', function () {
      mySwiper.autoplay.stop();
    });
    $('#swiper7').on('mouseleave', function () {
      mySwiper.autoplay.start();
    })
  };
  var gainLTBFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "left top bottom",
      url: config.interface.box1 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp.data;
        cb && cb(el, resp)
      }
    })

  };

  //left bottom top
  var lBTFn = function (el) {
    var $el = $(App.getView(el)),
      $html = '' +
        '              <ul class="lefttop-b">\n' +
        '              </ul>';
    $swiper = "<div class='swiper-container swiper-li' id='swiper8'>" +
      " <div class='swiper-wrapper dper-full-h'></div>" +
      "</div>";
    $el.empty();
    $el.append($html);
    gainLBTFn(el, renderLBTFn)
  };
  var renderLBTFn = function (el, resp) {
    // var $el = $(App.getView(el).querySelector('.swiper-wrapper')),

    //   $ft = $(App.getView(el)).parent().parent().parent().siblings('.child-header').find('.la-title'),
    //     $child1 = '<div class="swiper-slide dper-full-h dper-por font-16">' +
    //     '<li class="link-li">' +
    //     ' <span class="li-name">{{name1}}</span>' +
    //     ' <span class="li-msg {{class1}}"><span class="co-blue-person " style="cursor:pointer" data-id="{{name1}}">{{msg1}}</span>人</span>' +
    //     '</li>'+
    //     '<li class="link-li">' +
    //     ' <span class="li-name">{{name2}}</span>' +
    //     ' <span class="li-msg {{class2}}"><span class="co-blue-person " style="cursor:pointer" data-id="{{name2}}">{{msg2}}</span>人</span>' +
    //     '</li>' +
    //     '</div>',
    //   mySwiper = null;

    var $el = $(App.getView(el).querySelector('.lefttop-b')),
      $ft = $(App.getView(el)).parent().parent().parent().parent().siblings('.child-header').find('.la-title'),
      $child1 = '' +
        '<li class="">' +
        ' <span class="li-name">{{name}}</span>' +
        ' <span class="li-msg"><span class="co-blue-person">{{msg}}</span>人</span>' +
        '</li>';
    $ft.text('');
    $ft.text("医疗管理");
    _.each(resp.list, function (itm) {
      $el.append(rendarTpl(itm, $child1, 'itm'))
    });
    // mySwiper = new Swiper("#swiper8",{
    //   loop:true,
    //   // autoplay:false,
    //   autoplay: {
    //     delay: basedata.time
    //   },
    //   direction:"vertical",
    //   grabCursor: true,
    //   slidesPerView:1,
    //   slidesPerGroup: 1
    // });
    // $('#swiper8').on('mouseenter',function(){
    //   mySwiper.autoplay.stop();
    // });
    // $('#swiper8').on('mouseleave',function(){
    //   mySwiper.autoplay.start();
    // })
  };
  var renderFillFn = function ($el, resp) {
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
  var gainLBTFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "left top bottom",
      url: config.interface.box2 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp.data.ylgl;
        cb && cb(el, resp)
      }
    })

  };
  //安全管理
  var lT3Fn = function (el) {
    var $el = $(App.getView(el)),
      $html = '' +
        '              <ul class="lefttop-b">\n' +
        '              </ul>';
    $swiper = "<div class='swiper-container swiper-li' id='swiper9'>" +
      " <div class='swiper-wrapper dper-full-h'></div>" +
      "</div>";
    $el.empty();
    $el.append($html);
    gainlT3Fn(el, renderlT3Fn)
  };
  var renderlT3Fn = function (el, resp) {
    // var $el = $(App.getView(el).querySelector('.swiper-wrapper')),

    // $ft = $(App.getView(el)).parent().parent().parent().siblings('.child-header').find('.la-title'),
    //   $child1 = '<div class="swiper-slide dper-full-h dper-por font-16">' +
    //   '<li class="link-li">' +
    //   ' <span class="li-name">{{name1}}</span>' +
    //   ' <span class="li-msg {{class1}}"><span class="co-blue-person " style="cursor:pointer" data-id="{{name1}}">{{msg1}}</span>人</span>' +
    //   '</li>'+
    //   '<li class="link-li">' +
    //   ' <span class="li-name">{{name2}}</span>' +
    //   ' <span class="li-msg {{class2}}"><span class="co-blue-person " style="cursor:pointer" data-id="{{name2}}">{{msg2}}</span>人</span>' +
    //   '</li>' +
    //   '</div>',
    // mySwiper = null;
    var $el = $(App.getView(el).querySelector('.lefttop-b')),
      $ft = $(App.getView(el)).parent().parent().parent().parent().siblings('.child-header').find('.la-title'),
      $x = '' +
        '<li class="">' +
        ' <span class="li-name">{{name}}</span>' +
        ' <span class="li-msg"><span class="co-blue-person">{{msg}}</span>人</span>' +
        '</li>';
    $ft.text('');
    $ft.text(resp.b3.name);
    _.each(resp.b3.list, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'))
    });
    // mySwiper = new Swiper("#swiper9",{
    //   loop:true,
    //   // autoplay:false,
    //   autoplay: {
    //     delay: basedata.time
    //   },
    //   direction:"vertical",
    //   grabCursor: true,
    //   slidesPerView:1,
    //   slidesPerGroup: 1
    // });
    // $('#swiper9').on('mouseenter',function(){
    //   mySwiper.autoplay.stop();
    // });
    // $('#swiper9').on('mouseleave',function(){
    //   mySwiper.autoplay.start();
    // })
  };
  var gainlT3Fn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "left top bottom",
      url: config.interface.demo,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp.data.index.lt;
        cb && cb(el, resp)
      }
    })

  };

  //   弹窗
  var MaskFn = function (el, data) {
    // console.log(data)
    var $body = "<div class='mask-t-head'></div><div class='mask-t-body'></div>"
    // $('.mask-t-body').css('background','red')
    $("#pop").empty();
    $("#pop").append($body);
    gainMaskFn(el, data, renderMaskFn)
  };

  

  var rendererPage = function (data) {
    console.log("测试data")
    console.log(data)
    // renderer data item
    var $elt = $('#pop .mask-t-head'),
      $elb = $('#pop .mask-t-body'),
      $elTitle = "<span class='mask-t-title'>{{name}}</span>",
      $elBody = "<div class='mask-b-item'  data-id='{{item7}}'><span>{{xl}}</span><span>{{item1}}</span><span>{{item2}}</span><span>{{item3}}</span><span>{{item4}}</span><span>{{item5}}</span><span>{{item6}}</span></div>";
    $elt.empty();
    $elb.empty();


    _.each(data.titles, function (itm) {
      $elt.append(rendarTpl(itm, $elTitle, 'itm'))
    });
    _.each(data.data, function (itm) {
      $elb.append(rendarTpl(itm, $elBody, 'itm'))
    });
    $(".mask-b-item").on("click", function () {
    	var rybh = $(this)[0].dataset.id;
    	localStorage.setItem("ryxm", rybh);
        window.location.href = /*window.BASEURL*/ "/html/jls/pages/appManage.html";
    });
  };
  var MaskFnWithPaginition = function (el, data) {
    var $body = "<div class='mask-t-head'></div><div class='mask-t-body'></div>";
    // $('.mask-t-body').css('background','red')
    $("#pop").empty();
    $("#pop").append($body);
    // 在 #pop 里面添加分页控件
    var originalData = data.data;
    var currentPage = 0;
    var pageSize = 10;
    var oDiv = document.createElement("div");
    oDiv.className = 'paginition';
    oDiv.padding = '10px 60px 0 60px'
    var oul = "<ul style='overflow: hidden;'>" + Array.apply(null, { length: 5 }).map(function (_, index) {
      return "<li><a></a></li>";
    }).join('') + "</ul>";
    oDiv.innerHTML = oul;
    $("#pop").append(oDiv);
    data.data = originalData.slice(0, pageSize);
    rendererPage(data);
  };
  var renderMaskFn = function (el, resp) {
    var $elt = $('#pop .mask-t-head'),
      $elb = $('#pop .mask-t-body'),
      $elTitle = "<span class='mask-t-title'>{{name}}</span>",
      $elBody = "<div class='mask-b-item' data-id='{{item7}}'><span>{{xl}}</span><span>{{item1}}</span><span>{{item2}}</span><span>{{item3}}</span><span>{{item4}}</span><span>{{item5}}</span><span>{{item6}}</span></div>";
    $elt.empty();
    $elb.empty();

    _.each(resp.titles, function (itm) {

      $elt.append(rendarTpl(itm, $elTitle, 'itm'))
    });
    _.each(resp.data, function (itm) {

      $elb.append(rendarTpl(itm, $elBody, 'itm'))
    });
	    $(".mask-b-item").on("click", function () {
	    	var rybh = $(this)[0].dataset.id;
	    	localStorage.setItem("ryxm", rybh);
	    	console.log(rybh)
	    	if(rybh!=null&&rybh!=undefined&&rybh!=""&&rybh!="{{item7}}"){
	    		window.location.href = /*window.BASEURL*/ "/html/jls/pages/appManage.html";
	    	}
	    })
  };
  //用于查询数据(重点人员关注)
//弹窗
  var MaskFnByzdry = function (el, data) {
    // console.log(data)
    var $body = "<div class='mask-t-head'></div><div class='mask-t-body'></div>"
    // $('.mask-t-body').css('background','red')
    $("#pop").empty();
    $("#pop").append($body);
    gainMaskFnByzdry(el, data, renderMaskFn)
  };
  var gainMaskFnByzdry = function (el, data, cb) {
	    // console.log(id)
	    // localStorage.getItem("idname")
	    // var idname = localStorage.getItem("idname")
	    // console.log(localStorage.getItem("idname"))
	    // console.log(idname)
	    basefn(el, {
	      App: App,
	      desc: "left bottom",
	      url: config.interface.demo,
	      useLoading: basedata.mark_.loadFirst,
	      cb: function (resp) {
	        // resp1 = resp.data.manage.cb;
	    	  console.log(data)
	        var $resp = data.list;
	        cb && cb(el, $resp)
	      }
	    })
	  };
  var gainMaskFn = function (el, data, cb) {
    // console.log(id)
    // localStorage.getItem("idname")
    // var idname = localStorage.getItem("idname")
    // console.log(localStorage.getItem("idname"))
    // console.log(idname)
    basefn(el, {
      App: App,
      desc: "left bottom",
      url: config.interface.jls_jbxx+ "?jsbh=" + strify +data,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        // resp1 = resp.data.manage.cb;
        var $resp = resp.zsyrjbxx
        cb && cb(el, $resp)
      }
    })
  };
  //提讯会见
  var lT4Fn = function (el) {
    var $el = $(App.getView(el)),
      $html = '' +
        '              <ul class="lefttop-b">\n' +
        '              </ul>';
    $el.empty();
    $el.append($html);
    gainlT4Fn(el, renderlT4Fn)
  };
  var renderlT4Fn = function (el, resp) {
    var $el = $(App.getView(el).querySelector('.lefttop-b')),
      $ft = $(App.getView(el)).parent().parent().parent().parent().siblings('.child-header').find('.la-title'),
      $child = '' +
        '<li class="">' +
        ' <span class="li-name">{{name}}</span>' +
        ' <span class="li-msg"><span class="co-blue-person" style="cursor:pointer" id="{{name}}">{{msg}}</span>人</span>' +
        '</li>';
    $child1 = '' +
      '<li class="">' +
      ' <span class="li-name">{{name}}</span>' +
      ' <span class="li-msg"><span class="font-16 co-ff656c" style="cursor:pointer">{{msg}}</span>人</span>' +
      '</li>';
    $ft.text('');
    $ft.text("提讯会见");
    _.each(resp, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'));

    });

    $("#box12 .co-blue-person").on("click", function (id) {
      $(".mask").removeClass("hid");
      //点击返回
      $(".back").on("click", function () {
        $(".mask").addClass("hid")
      })
      var lx = this.id;
      var type = "";
      if(lx == "提讯"){
    	  //会见类型
    	  type="02";
      }else if(lx == "律师会见"){
    	  //会见类型
    	  type="04";
      }else if(lx == "家属会见"){
    	  //会见类型
    	  type="06";
      }
      txhjjbxx("pop",type, MaskFnWithPaginition); 
    })
  };
  //提讯人员查询
  var txhjjbxx = function (el, type,cb) {
	    basefn(el, {
	      App: App,
	      desc: "left top bottom",
	      url: config.interface.jls_jbxx + "?jsbh=" + strify+"&crjbj="+type,
	      useLoading: basedata.mark_.loadFirst,
	      cb: function (resp) {
	        resp = resp.zsyrjbxx;
	        cb && cb(el,resp)
	      }
	    })

  };
	  
  var gainlT4Fn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "left top bottom",
      url: config.interface.box12 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp.data;
        cb && cb(el, resp)
      }
    })

  };


  //关押期限
  var lT5Fn = function (el) {
    var $el = $(App.getView(el)),
      $html = '' +
        '              <ul class="lefttop-b">\n' +
        '              </ul>';
    $el.empty();
    $el.append($html);
    gainlT5Fn(el, renderlT5Fn)
  };
  var renderlT5Fn = function (el, resp) {
    var $el = $(App.getView(el).querySelector('.lefttop-b')),
      $ft = $(App.getView(el)).parent().parent().parent().parent().siblings('.child-header').find('.la-title'),
      $child = '' +
        '<li class="">' +
        ' <span class="li-name">{{name}}</span>' +
        ' <span class="li-msg"><span class="co-blue-person">{{msg}}</span>人</span>' +
        '</li>';
    $ft.text('');
    $ft.text("关押期限");
    _.each(resp, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'))
    });
  };
  var gainlT5Fn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "left top bottom",
      url: config.interface.box13 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
    	resp = resp.data;
        cb && cb(el, resp)
      }
    })

  };
  //   left bottom bottom
  var lBBFn = function (el) {
    if (!basedata.chart.child4) basedata.chart.child4 = new ChartLLine(App.getView(el));
    basedata.chart.child4.myChart.on('click', function (params) {
      // console.log("1")
      var ss = "";
      if(params.data.name == "一般"){
    	  ss="5";
      }else if(params.data.name == "急"){
    	  ss="4";
      }else if(params.data.name == "特急"){
    	  ss="2";
      }
      $(".mask").removeClass("hid");90-
      //点击返回
      $(".back").on("click", function () {
        $(".mask").addClass("hid")
      })
      if(params.data.jsh!=null&&params.data.jsh!=undefined&&params.data.jsh!=""){
    	 ss = params.data.jjcd
      }
      var data = "&jjcd="+ss+"&jsh="+params.data.jsh
      MaskFnBywg("pop", data)
    });
    gainLBBFn(el, renderLineFn)
  };
  
  //违规趋势图弹窗
  var MaskFnBywg = function (el, data) {
    var $body = "<div class='mask-t-head'></div><div class='mask-t-body'></div>"
    // $('.mask-t-body').css('background','red')
    $("#pop").empty();
    $("#pop").append($body);
    gainMaskFnBywg(el, data, renderMaskFn)
  };
  
  //违规趋势图人员查询
  var gainMaskFnBywg = function (el, data, cb) {
	    basefn(el, {
	      App: App,
	      desc: "left bottom",
	      url: config.interface.xsjllist+ "?jsbh=" + strify +data,
	      useLoading: basedata.mark_.loadFirst,
	      cb: function (resp) {
          var $resp = resp.rywgqk
	        cb && cb(el, $resp)
	      }
	    })
	  };
  
  var renderLineFn = function (el, resp) {
    var $resp = {
      axis: [],
      seriesData: [],
      min: resp.min,
      name: resp.name,
    }
    _.each(resp.title, function (itm) {
      $resp.axis.push(itm.name);
      $resp.seriesData.push({ name: itm.name, value: itm.value,jsh:itm.jsh,jjcd:itm.jjcd});
    })
    basedata.chart.child4.render($resp)

  };
  var gainLBBFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "left bottom bottom",
      url: config.interface.box7 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resps) {
        console.log(resps)
        $t = $(".box-7-title"),
        $t.text("");
        $t.text("监区人员违规趋势图");
        //监区违规趋势图数据
        resp = resps.data;
        wgjq = resps.data.text;
        console.log(wgjq)
        cb && cb(el, resp)

      }
    })
  };
  // right top top
  var rTTFn = function (el) {
    if (!basedata.chart.child5) basedata.chart.child5 = new ChartPie(App.getView(el).querySelector('.pie-chart'));

    gainRTTFn(el, renderRTTFn)
  };
  var renderRTTFn = function (el, resp) {
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
    console.log(resp,24335)
    _.each(resp.list, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'))
    });
    basedata.chart.child5.render(resp);
    basedata.chart.child5.myChart.on('click', function (params) {
      var data = resp.list.find(item => item.name == params.name)
      var rsxz = data.name;
      if(rsxz == "司法拘留"){
    	  data = "&rsxz=11"
      }else if(rsxz == "行政拘留"){
    	  data = "&rsxz=10"
      }else if(rsxz == "拘留审查"){
    	  data = "&rsxz=12"
      }else if(rsxz == "现场拘留"){
    	  data = "&rsxz=13"
      }else if(rsxz == "临时寄拘"){
    	  data = "&rsxz=14"
      }else if(rsxz == "外省市转入"){
    	  data = "&rsxz = 15"
      }else if(rsxz == "本省市转入"){
    	  data = "&rsxz=16"
      }else if(rsxz == "异地拘留"){
    	  data = "&rsxz=17"
      }else if(rsxz == "外国籍人待遣"){
    	  data = "&rsxz=18"
      }else if(rsxz == "港澳台居民待遣"){
    	  data = "&rsxz=19"
      }else if(rsxz == "撤销出所治疗"){
    	  data = "&rsxz=20"
      }else if(rsxz == "其他"){
    	  data = "&rsxz=qt"
      }
      $(".mask").removeClass("hid");
      //点击返回
      $(".back").on("click", function () {
        $(".mask").addClass("hid")
      })
      MaskFn("pop", data)
    });
  };
  var gainRTTFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "right top top",
      url: config.interface.box4 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
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
          },
          {
            co0: '#0000FF',
            co1: '#ec2ac2'
          }, {
            co0: '#009933',
            co1: '#ec2ac2'
          }, {
            co0: '#CCCC00',
            co1: '#ec2ac2'
          }, {
            co0: '#FF9999',
            co1: '#ec2ac2'
          }, {
            co0: '#9999FF',
            co1: '#10e2b7'
          }, {
            co0: '#CC3366',
            co1: '#ec2ac2'
          }
        ], $resp = {
          name: "诉讼情况分类与犯罪类型分析",
          list: [],
          sum: 1
        };
        _.each(resp.data, function (itm) {
          $resp.sum += Number(itm.value)
        });
        _.each(resp.data, function (itm, idx) {
          $resp.list.push({
            name: itm.name,
            value: itm.value,
            deci: ((Number(itm.value) / $resp.sum) * 100).toFixed(2),
            col0: col[idx].co0,
            col1: col[idx].co1,
            list: itm.list
          })
        });
        cb && cb(el, $resp)
      }
    })
  };
  // right top bottom
  var rTBFn = function (el) {
    var $el = $(App.getView(el)),
      $swiper = '<div class="swiper-container dper-full-h" id="swiper2">' +
        '   <div class="swiper-wrapper"></div>' +
        '</div>';
    $el.empty();
    $el.append($swiper);
    gainRTBFn(el, renderRTBFn)
  };
  var renderRTBFn = function (el, resp) {
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
        '</div>', mySwiper = null;
    $el.empty();
    _.each(resp.list, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'))
    });
    _.each($el.find('.pro-bar'), function (itm, idx) {
      var number = 100 - Number($(itm).data("pro"));
      setTimeout(function () {
        $(itm).css("transform", "translateX(-" + number + "%)")
      }, 50 * idx)
    });
    mySwiper = new Swiper("#swiper2", {
      direction: 'vertical',
      grabCursor: true,
      slidesPerView: 10,
      slidesPerGroup: 10
    })
  };
  var gainRTBFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "right top bottom",
      url: config.interface.jls_ajlb + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp;
        var $resp = {
          sum: Number(resp.sum),
          list: []
        };
        _.each(resp.data, function (itm, idx) {
          $resp.list.push({
            name: itm.name,
            value: itm.value,
            deci: ((Number(itm.value) / $resp.sum) * 100).toFixed(2),
            cla: idx < 3 ? "pro-co-" + idx + "-img" : "pro-co-img",
            col: idx < 3 ? "col-" + idx : "col"
          })
        });
        cb && cb(el, $resp)
      }
    })
  };
  //   right center
  var rCFn = function (el) {
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper-container' id='swiper3'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainRCFn(el, renderRCFn)
  };




  var renderRCFn = function (el, resp, resp2) {
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
        "       <div class='person-value malign-center-left font-14 font-24 dper-fw person-div' data-id='{{id}}'>" +
        "         <p class='co-blue-person dper-por z-level-5 text-overflow-e dper-full-w' style='cursor:pointer'>{{person}}</p>" +
        "         <p class='co-blue-shadow malign-tl z-level-2 text-overflow-e dper-full-w' style='cursor:pointer'>{{person}}</p>" +
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
        "</div>", mySwiper = null;
    $t.text("");
    $el.empty();
    $t.text("违规程度分析");
    console.log(resp.data)
    _.each(resp.data, function (itm) {
      $el.append(rendarTpl(itm, $child, "itm"));
    });
    mySwiper = new Swiper("#swiper3", {
      direction: 'vertical',
      grabCursor: true,
      slidesPerView: 3,
      slidesPerGroup: 3
    })
    $(".person-div").on("click", function () {
      var index = $(this).data("id");
      let res = resp2[index]
      var $resp = {
        axis: [],
        seriesData: [],
        min: "0",
        name: res.name,
      }
      _.each(res.list, function (itm) {
        $resp.axis.push(itm.name);
        $resp.seriesData.push({ name: itm.name, value: itm.value,jsh:itm.jsh,jjcd:itm.jjcd});
      })
      basedata.chart.child4.render($resp)
    })
  };

  var gainRCFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "right center",
      url: config.interface.box6+"?jsbh="+strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        console.log(resp)
        resp1 = resp;
        resp2 = wgjq;
        cb && cb(el, resp1, resp2)
      }
    })
  };
  var rBFn = function (el) {
    if (!basedata.chart.child6) basedata.chart.child6 = new ChartRLine(App.getView(el));
    gainRBFn(el, renderRBFn)
  };
  // right bottom
  var renderRBFn = function (el, resp) {
    var $t = $('.box-3-title');
    $t.text('');
    $t.text(resp.name);
    basedata.chart.child6.render(resp)
  };
  var gainRBFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "right bottom",
      url: config.interface.box3 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp;
        var $resp = {
          name: "各监区报病情况趋势图",
          min: "0",
          series: [],
          axis: (function () {
            var arr = [];
            _.each(resp.data[0].list, function (itm) {
              arr.push(itm.name)
            })
            return arr
          })()
        }, col = [
          '#ff656c',
          '#ffbf69',
          '#33ff99',
          '#00cfff',
          '#c23531',
          '#2f4554',
          '#61a0a8',
          '#d48265',
          '#91c7ae',
          '#749f83',
          '#ca8622',
          '#bda29a',
          '#6e7074',
          '#546570',
          '#c4ccd3'
        ];
        console.log(resp);
        _.each(resp.data, function (itm, idx) {
          $resp.series.push({
            name: itm.name,
            value: itm.list,
            col: col[idx],
            icon: itm.icon
          });

        });
        cb && cb(el, $resp)
      }
    })
  };
  //   center top
  var cTFn = function (el) {
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper-container dper-full-h' id='swiper4'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainCTFn(el, renderCTFn)
  };
  var renderCTFn = function (el, resp) {
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $child = '<div class="swiper-slide dper-full-h dper-por align-center">' +
        ' <div class="slide-itm malign-center dper-full-w">' +
        ' <div class="l-cont l-cont-img bg-img-full dper-full-h malign-tl">' +
        '   <p class=""><span class="font-30">{{lname}}</span></p>' +
        '   <p class=""><span class="font-30 co-0ff">{{lvalue}}</span></p>' +

        ' </div>' +
       /* ' <div class="r-cont r-cont-img bg-img-full dper-full-h malign-tr">' +
        '   <p class=""><span class="font-30">{{rname}}</span></p>' +
        '   <p class=""><span class="font-30 co-0ff">{{rvalue}}</span></p>' +

        ' </div>' +*/
        '</div>' +
        '</div>',
      mySwiper = null;
    $el.empty();
    _.each(resp, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'))
    });
    mySwiper = new Swiper("#swiper4", {
      //grabCursor: true,
      slidesPerView: 2,
      slidesPerGroup: 2
    });

  };
  var gainCTFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "center top",
      url: config.interface.box8 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp.aqjc;
        console.log(resp)
        cb && cb(el, resp)
      }
    })
  };
  //   center center
  var cCFn = function (el) {
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper5-out-left'>重</br>点</br>关</br>注</br>人</br>员</div><div class='swiper5-out'><div class='swiper-container dper-full-h' id='swiper5'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div></div>";
    $el.empty();
    $el.append($swiper);
    gainCCFn(el, renderCCFn)
  };
  var renderCCFn = function (el, resp) {
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $child = '<div class="swiper-slide dper-full-h dper-por font-16">' +
        ' <div class="img-itm" data-id={{id}}>' +
        ' <div class="person-img bg-img-full dper-full-h dper-por z-level-2" style="background-image: url({{img}})"></div>' +
        ' <div class="person-msg dper-full-w z-level-5 align-center">' +
        '   <p class="text-overflow-e dper-full-w">{{name}}</p>' +
        '   <p class="text-overflow-e dper-full-w">{{area}}</p>' +
        '   <p class="text-overflow-e dper-full-w">{{number}}</p>' +
        '</div>' +
        '</div>' +
        '</div>',
      mySwiper = null;
    $el.empty();
    _.each(resp, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'))
    });
    console.log("1")
    mySwiper = new Swiper("#swiper5", {
      //loop: true,
      // autoplay:false,
      autoplay: {
        delay: basedata.time
      },
      grabCursor: true,
      slidesPerView: 6,
      slidesPerGroup: 6
    });
    $('#swiper5').on('mouseenter', function () {
      mySwiper.autoplay.stop();
    });
    $('#swiper5').on('mouseleave', function () {
      mySwiper.autoplay.start();
    })
    $(".img-itm").on("click", function () {
      var data = resp.find(item => item.id == parseInt($(this).data("id")))
      $(".mask").removeClass("hid");
      //点击返回
      $(".back").on("click", function () {
        $(".mask").addClass("hid")
      })
      MaskFnByzdry("pop", data);
    })
  };
  var gainCCFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "center center",
      url: config.interface.jls_jbxxByphotot + "?jsbh=" + strify + "&zdzy=1",
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp.data;
        cb && cb(el, resp)
      }
    })
  };
  //   center top
  var cBFn = function (el) {
    var $el = $(App.getView(el)),
      $swiper = "<div class='swiper-container dper-full-h' id='swiper6'>" +
        " <div class='swiper-wrapper dper-full-h'></div>" +
        "</div>";
    $el.empty();
    $el.append($swiper);
    gainCBFn(el, renderCBFn)
  };
  var renderCBFn = function (el, resp) {
    var $el = $(App.getView(el).querySelector('.swiper-wrapper')),
      $child = '' +
        '<div class="swiper-slide font-18 align-center">' +
        ' <div class="cap-center-cont dper-por">' +
        '   <div class="cap-styl cap-center-cont-img bg-img-full malign-tl dper-full-w dper-full-h"></div>' +
        '   <div class="head malign-center-top"><span class="malign-center-text text-overflow-e font-22">{{name}}</span></div>' +
        '   <div class="child-itm malign-center-top dper-full-w">' +
        '       <p class="text-overflow-e dper-full-w">' +
        '         <span>在押总数</span>' +
        '         <span data-id={{name}} class="custody font-30 co-0ff">{{val0}}</span>' +
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
        '         <span class="font-28 co-0ff">{{val21}}%</span>' +
        '       </p>' +
        '   </div>' +
        '   <div class="child-itm malign-center-top dper-full-w">' +
        '       <p class="text-overflow-e dper-full-w">' +
        '         <span>二级风险人数:</span>' +
        '         <span class="font-30 co-0ff">{{val3}}</span>' +
        '         <span>人,占比:</span>' +
        '         <span class="font-28 co-0ff">{{val31}}%</span>' +
        '       </p>' +
        '   </div>' +
        '   <div class="child-itm malign-center-top dper-full-w">' +
        '       <p class="text-overflow-e dper-full-w">' +
        '         <span>三级风险人数:</span>' +
        '         <span class="font-30 co-0ff">{{val4}}</span>' +
        '         <span>人,占比:</span>' +
        '         <span class="font-28 co-0ff">{{val41}}%</span>' +
        '       </p>' +
        '   </div>' +
        '</div>' +
        '</div>',
      mySwiper = null;
    $el.empty();
    _.each(resp, function (itm) {
      $el.append(rendarTpl(itm, $child, 'itm'))
    });
    mySwiper = new Swiper("#swiper6", {
      initialSlide: 2,
      grabCursor: true,
      slidesPerView: 3,
      slidesPerGroup: 1,
      centeredSlides: true,
      effect: 'coverflow',
      coverflowEffect: {
        rotate: -15,
        stretch: -30,
        depth: 60,
        modifier: 2,
        slideShadows: false
      },
    })
    $(".custody").on("click", function () {
      $(".mask").removeClass("hid");
      //点击返回
      $(".back").on("click", function () {
        $(".mask").addClass("hid")
      })
      var data = resp.find(item => item.name == $(this).data("id"));
      console.log(data);
      var jqh = "&jqh="+data.jqh;
      MaskFn("pop", jqh);
    })
  };
  var gainCBFn = function (el, cb) {
    basefn(el, {
      App: App,
      desc: "center bottom",
      url: config.interface.box10 + "?jsbh=" + strify,
      useLoading: basedata.mark_.loadFirst,
      cb: function (resp) {
        resp = resp.data;
        cb && cb(el, resp)
      }
    })
  };

  //定时任务 每10秒钟弹出一个弹窗
  $(document).ready(function () {
    setInterval(function () {
      $.ajax({
        type: 'POST',
        url: "/bj_jwp/timeTask",
        data: { jsbh: strify },
        success: function (result) {
          // console.log(result.jieguo);
          if (result.jieguo != undefined && result.jieguo.length > 0) {
            _toastr(result);
          }
          // console.log(result);
          // yer('请求成功:</br>&nbsp;&nbsp;&nbsp;&nbsp;'+result.jieguo, 1);
        }
      });
    }, 5000);
  });
  // ready 事件即 DOMContentLoaded，所以这里只定义了函数，但是函数还没有执行
  // 然后执行 _toastr 的定义
  //  信息弹出框
  var _toastr = function (result) {
    toastr.options = {
      closeButton: true,
      progressBar: true,
      positionClass: "toast-top-center",
      onclick: null,
      showDuration: "1000",
      timeOut: "10000",
      showEasing: "swing",
      hideEasing: "linear",
      showMethod: "fadeIn",
      hideMethod: "fadeOut"
    }
    lT4Fn("box12");
    toastr.info(result.jieguo[0], '业务提示');
  };

  // 执行其他代码

  // 改文件执行完毕

  // 继续渲染 html 结构

  // html 结构渲染完毕

  // 执行添加的 DOMContentLoaded 事件，即

  // 此时 _toas 是已经定义好的

  var ws;//websocket实例
  var lockReconnect = false;//避免重复连接
  var wsUrl = '/webSocket/1';
  function createWebSocket(url) {
    try {
      ws = new WebSocket(url);
      initEventHandle();
    } catch (e) {
      reconnect(url);
    }
  }

  function initEventHandle() {
    ws.onclose = function () {
      reconnect(wsUrl);
    };
    ws.onerror = function () {
      reconnect(wsUrl);
    };
    ws.onopen = function () {
      //心跳检测重置
      heartCheck.reset().start();
    };
    ws.onmessage = function (event) {
      //如果获取到消息，心跳检测重置
      //拿到任何消息都说明当前连接是正常的
      heartCheck.reset().start();
    }
  }

  function reconnect(url) {
    if (lockReconnect) return;
    lockReconnect = true;
    //没连接上会一直重连，设置延迟避免请求过多
    setTimeout(function () {
      createWebSocket(url);
      lockReconnect = false;
    }, 2000);
  }

  //心跳检测
  var heartCheck = {
    timeout: 10000,//60秒
    timeoutObj: null,
    reset: function () {
      clearTimeout(this.timeoutObj);
      return this;
    },
    start: function () {
      this.timeoutObj = setTimeout(function () {
        //这里发送一个心跳，后端收到后，返回一个心跳消息，
        //onmessage拿到返回的心跳就说明连接正常
        ws.send("HeartBeat");
        ws.onopen = function () {
          heartCheck.reset().start(); //心跳检测重置   在open的时候触发心跳检测
          console.log("llws连接成功!" + new Date().toUTCString());
          config.open(ws)
        };
        ws.onmessage = function (event) { //如果获取到消息，心跳检测重置
          heartCheck.reset().start(); //拿到任何消息都说明当前连接是正常的  //如果后端有下发消息，那么就会重置初始化心跳检测，除非超时没下发，那么就会触发onclose
          //然后触发重连
          // _toastr(event.data)
          console.log(event.data);


        };

      }, this.timeout)
    }
  }

  createWebSocket(wsUrl);



});
