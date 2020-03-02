define(function (require) {
  require("jquery");
  require("echarts");
  require("Swiper");
  require("modules/charts/BaseChart");
  require("easyui");
  $(function () {
    handleInitFn();
  });
  var baseData = {
    chart: {
      one: null,
      two: null
    },
    navList: []
  };

  // nav click function
  var event = function () {
    handleNavClickFn();
    handleCreateNav("首页")
  };

  var handleInitFn = function () {
    event();
    //阻止浏览器默认右键点击事件
    document.oncontextmenu = function(){
      return false;
    };
  };

  // nav click function
  var handleNavClickFn = function () {
    var el = $("#navMe").find(".nav-info");
    el.off("click");
    el.on("click", navFn);
  };


// 左边菜单添加
  var navFn = function () {
    var name = $(this).data("name");
    $(this).parent().parent().parent().find(".nav-info").removeClass("active");
    $(this).addClass("active");
    handleCreateNav(name);
  };

  // 右边 上部 菜单关闭 删除
  var handleCreateNav = function (name) {
    var el = $("#navTop");
    if (baseData.navList.indexOf(name) === -1) {
      baseData.navList.push(name);
      if(name === "首页")
        el.append('<div class="swiper-slide r-nav-child dper-por dper-full-h">' + '<div class="malign-center inside-box dper-full-h">' + '   <span class="malign-center-text font-16 text-overflow-e">' + name + '</span><i class="close malign-center-right" data-name="' + name + '"></i></div>' + '</div>');
      else
        el.append('<div class="swiper-slide r-nav-child dper-por dper-full-h select-nav">' + '<div class="malign-center inside-box dper-full-h">' + '   <span class="malign-center-text font-16 text-overflow-e">' + name + '</span><i class="close malign-center-right" data-name="' + name + '">✗</i></div>' + '</div>');
      new Swiper("#Swiper", {
        freeMode: true,
        direction: "horizontal",
        slidesPerView: 10,
        mousewheel: true
      });
    }
    $.each(el.find(".close"), function (idx, itm) {
      if ($(itm).data("name") === name) {
        $(itm).parent().parent().addClass("active").siblings().removeClass("active");
      }
    });
    handleContShowFn(name);
    el.find(".r-nav-child").off("click");
    el.find(".r-nav-child").on("click", handleNavTopClick);
    el.find(".r-nav-child:not(:first)").off("mousedown");
    el.find(".r-nav-child:not(:first)").on("mousedown",youji);
    el.find(".close").off("click");
    el.find(".close").on("click", handleCloseClick);
  };

  // 右边 上部 菜单关闭 删除
  var handleCloseClick = function () {
    var index = $(this).parent().parent().index(),
        $el = $(this).parent().parent();
    if (baseData.navList.indexOf($(this).data("name")) !== -1) {
      baseData.navList.splice(baseData.navList.indexOf($(this).data("name")), 1);
    }
    if ($el.hasClass("active") && index === 1) {
      $el.siblings().eq(0).addClass("active");
      // 右边下部 内容切换
      handleContShowFn($el.siblings().eq(0).find(".close").data("name"));
    } else if ($el.hasClass("active") && index === baseData.navList.length) {
      $el.siblings().eq(baseData.navList.length - 1).addClass("active");
      // 右边下部 内容切换
      handleContShowFn($el.siblings().eq(baseData.navList.length - 1).find(".close").data("name"));
    } else if ($el.hasClass("active") && index !== baseData.navList.length && index !== 1) {
      $el.siblings().eq(index - 1).addClass("active");
      handleContShowFn($el.siblings().eq(index - 1).find(".close").data("name"));
    }
    $el.remove();
  };

  //右击事件
  var youji=function (e) {
    e = e || event;
    // 鼠标点击位置坐标
    var index=$(this).index(),
        mouseX = e.pageX,
        mouseY = e.pageY,
        menuLeft = mouseX  + "px",
        menuTop = mouseY  + "px";
    // 菜单出现
    $(".right-youji").css({
      "left": menuLeft,
      "top": menuTop
    }).show();
    // 点击页面任意地方之后，右键菜单隐藏
    $(document).off("click");
    $(document).on("click",function () {
      $(".right-youji").hide();
    });

    //刷新
    $("#li1").off("click");
    $("#li1").on("click",(function () {
      handleContShowFn($(this).find(".close").data("name"))
    }).bind(this));
    //关闭当前
    $("#li2").off("click");
    $("#li2").on("click",(function () {
      var $el = $(this);
      if (baseData.navList.indexOf($(this).find(".close").data("name")) !== -1) {
        baseData.navList.splice(baseData.navList.indexOf($(this).find(".close").data("name")), 1);
      }
      if ($el.hasClass("active") && index === 1) {
        $el.siblings().eq(0).addClass("active");
        // 右边下部 内容切换
        handleContShowFn($el.siblings().eq(0).find(".close").data("name"));
      } else if ($el.hasClass("active") && index === baseData.navList.length) {
        $el.siblings().eq(baseData.navList.length - 1).addClass("active");
        // 右边下部 内容切换
        handleContShowFn($el.siblings().eq(baseData.navList.length - 1).find(".close").data("name"));
      } else if ($el.hasClass("active") && index !== baseData.navList.length && index !== 1) {
        $el.siblings().eq(index - 1).addClass("active");
        handleContShowFn($el.siblings().eq(index - 1).find(".close").data("name"));
      }
      $(this).remove();
    }).bind(this));
    //关闭其它
    $("#li3").off("click");
    $("#li3").on("click", (function () {
      // nav arr 清空 然后 添加有active 元素
      baseData.navList = [];
      $.each($(this).parent().children(), function (idx, itm) {
        if($(itm).hasClass("active")) {
          $(itm).siblings(".select-nav").remove();
          baseData.navList.push($(itm).find(".close").data("name"));
        }
      })
    }).bind(this));
    //关闭全部
    $("#li4").off("click");
    $("#li4").on("click", (function () {
      baseData.navList = ["首页"];
      if(!$(this).parent().children().eq(0).hasClass("active")) {
        $(this).parent().children().eq(0).addClass("active").siblings().removeClass("active");
        handleContShowFn(baseData.navList[0])
      }
      $(".r-nav-child:not(:first)").remove();
    }).bind(this));
    e.stopPropagation();
  };

  // 右边 上部 菜单切换
  var handleNavTopClick = function () {
    $(this).addClass("active").siblings().removeClass("active");
    handleContShowFn($(this).find('.close').data("name"));
  };

  // 右边下部 内容切换
  var handleContShowFn = function (name) {
    var el = $("#contCut"),
        child = '<div class="right-bottom1 dper-full-w malign-tl">\n' + '         ' +
            '           <div class="right-bottom1-t malign-tl">\n' + '                    ' +
            '    <div class="r1 bg-img malign-center-left"></div>\n' + '                    ' +
            '    <div class="r1-span malign-center-right dper-full-h"><span class="malign-center-text font-24 dper-fw">基本信息</span></div>\n' + '                    </div>\n' + '                    <div class="right-bottom1-c malign-tl">\n' + '                        <div class="c-span1 malign-tl dper-full-h">\n' + '                            <span class="malign-center-left font-16">姓 &nbsp&nbsp名:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h">\n' + '                            <span class="malign-center-left font-16">性 &nbsp&nbsp别:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input2 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <select name="" class="c-span3 malign-tr font-16">\n' + '                            <option value="" selected="selected">未核实</option>\n' + '                            <option value="">已核实</option>\n' + '                        </select>\n' + '                        <div class="c-span4 malign-tr font-16">\n' + '                            <span class="malign-center-text">人口库比对</span>\n' + '                        </div>\n' + '                        <div class="c-span5 malign-tr font-16">\n' + '                            <span class="malign-center-text">在逃对比</span>\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="right-bottom1-b malign-bl">\n' + '                        <div class="c-span1 malign-tl dper-full-h">\n' + '                            <span class="malign-center-left font-16">证件号:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h">\n' + '                            <span class="malign-center-text font-16">限制会见案件:</span>\n' + '                            <div class="tu bg-img malign-center-right"></div>\n' + '                        </div>\n' + '                        <div class="c-span3 malign-tr dper-full-h font-16">\n' + '                            <input class="input1 malign-center-left" type="radio" name="shi"><span class="span1 malign-center-left">是</span>\n' + '                            <input class="input2 malign-center-right" type="radio" name="shi"><span class="malign-center-right">否</span>\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="xian malign-center-bottom"></div>\n' + '                </div>\n' + '                <div class="right-bottom2 dper-full-w malign-tl">\n' + '                    <div class="right-bottom2-t malign-tl">\n' + '                        <div class="r2 bg-img malign-center-left"></div>\n' + '                        <div class="r2-span malign-center-right dper-full-h"><span class="malign-center-text font-24 dper-fw">案件类别</span></div>\n' + '                    </div>\n' + '                    <div class="right-bottom2-c malign-tl">\n' + '                        <div class="c-span1 malign-tl dper-full-h">\n' + '                            <span class="malign-center-left font-16">案件类别:</span>\n' + '                            <div class="tu bg-img malign-center-right"></div>\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h font-16">\n' + '                            <input class="input1 malign-center-left" type="checkbox"><span class="span1 malign-center-left">危害国家安全罪</span>\n' + '                            <input class="input2 malign-center-right" type="checkbox"><span class="span2 malign-center-right">恐怖活动罪</span>\n' + '                        </div>\n' + '                        <div class="c-span3 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">审批单位:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span4 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">审批人:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="right-bottom2-b malign-bl">\n' + '                        <div class="c-span1 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">转入单位:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">办案人:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span3 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">联系电话:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="xian malign-center-bottom"></div>\n' + '                </div>\n' + '                <div class="right-bottom3 dper-full-w malign-bl">\n' + '                    <div class="right-bottom3-t malign-tl">\n' + '                        <div class="r3 bg-img malign-center-left"></div>\n' + '                        <div class="r3-span malign-center-right dper-full-h"><span class="malign-center-text font-24 dper-fw">个人信息</span></div>\n' + '                    </div>\n' + '                    <div class="right-bottom3-t1 malign-tl">\n' + '                        <div class="c-span1 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">别名或绰号:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">国 &nbsp&nbsp&nbsp&nbsp&nbsp籍:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">中国</option>\n' + '                                <option value="">俄罗斯</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span3 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">文化程度:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">初中</option>\n' + '                                <option value="">高中</option>\n' + '                                <option value="">大学</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span4 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">身 &nbsp&nbsp&nbsp份:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">无业人员</option>\n' + '                                <option value="">搬砖</option>\n' + '                            </select>\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="right-bottom3-t2 malign-tl">\n' + '                        <div class="c-span1 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">民&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp族:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">汉族</option>\n' + '                                <option value="">土家族</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">档案编号:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span3 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">证件类型:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">身份证</option>\n' + '                                <option value="">军官证</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span4 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">职 &nbsp&nbsp&nbsp业:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">教师</option>\n' + '                                <option value="">公务员</option>\n' + '                            </select>\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="right-bottom3-t3 malign-tl">\n' + '                        <div class="c-span1 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">出&nbsp生&nbsp日&nbsp期&nbsp:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="date">\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">婚姻状况:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">已婚</option>\n' + '                                <option value="">未婚</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span3 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">户籍所在地:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">重庆</option>\n' + '                                <option value="">北京</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span4 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">籍 &nbsp&nbsp&nbsp贯:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">重庆</option>\n' + '                                <option value="">北京</option>\n' + '                            </select>\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="right-bottom3-t4 malign-tl">\n' + '                        <div class="c-span1 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">户籍所在地详址:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected"></option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">现 住 地 :</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected"></option>\n' + '                            </select>\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="right-bottom3-t5 malign-bl">\n' + '                        <div class="c-span1 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">政&nbsp治&nbsp面&nbsp貌&nbsp:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">群众</option>\n' + '                                <option value="">党员</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">现住地详址:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="xian malign-center-bottom"></div>\n' + '                </div>\n' + '                <div class="right-bottom4 dper-full-w malign-bl">\n' + '                    <div class="right-bottom4-t malign-tl">\n' + '                        <div class="r4 bg-img malign-center-left"></div>\n' + '                        <div class="r4-span malign-center-right dper-full-h"><span class="malign-center-text font-24 dper-fw">业务信息</span></div>\n' + '                    </div>\n' + '                    <div class="right-bottom4-c malign-tl">\n' + '                        <div class="c-span1 malign-tl dper-full-h">\n' + '                            <span class="malign-center-left font-16">别名或绰号:</span>\n' + '                            <div class="tu bg-img malign-center-right"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">国 &nbsp籍:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">中国</option>\n' + '                                <option value="">俄罗斯</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span3 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">文化程度:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">初中</option>\n' + '                                <option value="">高中</option>\n' + '                                <option value="">大学</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span4 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">身 &nbsp份:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">无业人员</option>\n' + '                                <option value="">有业人员</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span5 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">民 &nbsp族:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">汉族</option>\n' + '                                <option value="">土家族</option>\n' + '                                <option value="">苗族</option>\n' + '                            </select>\n' + '                        </div>\n' + '                    </div>\n' + '                    <div class="right-bottom4-b malign-bl">\n' + '                        <div class="c-span1 malign-tl dper-full-h">\n' + '                            <span class="malign-center-left font-16">档 案 编 号:</span>\n' + '                            <div class="tu bg-img malign-center-right"></div>\n' + '                            <input class="input1 malign-center-right" type="text">\n' + '                        </div>\n' + '                        <div class="c-span2 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">证 件 类 型:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">身份证</option>\n' + '                                <option value="">军官证</option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span3 malign-tl dper-full-h font-16">\n' + '                            <span class="malign-center-left">职 &nbsp&nbsp&nbsp&nbsp&nbsp业 :</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected"></option>\n' + '                                <option value=""></option>\n' + '                                <option value=""></option>\n' + '                            </select>\n' + '                        </div>\n' + '                        <div class="c-span4 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">出生日期:</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <input class="input1 malign-center-right font-12" type="date">\n' + '                        </div>\n' + '                        <div class="c-span5 malign-tr dper-full-h font-16">\n' + '                            <span class="malign-center-left">婚 姻 状 况 :</span>\n' + '                            <div class="tu bg-img malign-center-left"></div>\n' + '                            <select name="" class="c-span2-s malign-center-right font-16">\n' + '                                <option value="" selected="selected">已婚</option>\n' + '                                <option value="">未婚</option>\n' + '                            </select>\n' + '                        </div>\n' + '                    </div>\n' + '                </div>\n' + '                <div class="left-xian malign-center-left"></div>\n' + '                <input type="submit" class="sub malign-bl" value="✓  保存">\n' + '                <input type="reset" class="sub1 malign-bl" value="✗  清空">\n' + '                <div class="shui0 shui malign-tl"><span class="malign-center-text font-30">姓名:张三 警号:1020</span></div>\n' + '                <div class="shui1 shui malign-tl"><span class="malign-center-text font-30">姓名:张三 警号:1020</span></div>\n' + '                <div class="shui2 shui malign-tl"><span class="malign-center-text font-30">姓名:张三 警号:1020</span></div>\n' + '                <div class="shui3 shui malign-tl"><span class="malign-center-text font-30">姓名:张三 警号:1020</span></div>\n' + '                <div class="shui4 shui malign-tl"><span class="malign-center-text font-30">姓名:张三 警号:1020</span></div>\n' + '                <div class="shui5 shui malign-tl"><span class="malign-center-text font-30">姓名:张三 警号:1020</span></div>',
        otherChild = "",
        first = "";
        el.empty();
        if (name === "收押入所") {
          el.append(child);
        } else if(name === "首页"){
          first = '<div class="malign-tl dper-full-w dper-full-h font-20" style="color: #000; background-color: #fff;">收押岗位欢迎您   您好！管理员<div/>' ;
          el.append(first);
        }else{
          otherChild = '<iframe class="malign-tl dper-full-w dper-full-h" style="border: none; background-color: #fff;">' + name + '</iframe>';
          el.append(otherChild);
        }
  };


  //菜单点击事件
  $(".left_nav li .t").click(function () {
    $(this).toggleClass("hover");
    $(this).next(".txt").slideToggle();
  });

  //导航点击事件
  $(".right-top .right-top-left .right-t2").on("click", function () {
    $(".right-t1").slideToggle();
    $(this).parent(".right-top-left").next(".right-top-right").slideToggle();
  });
});
