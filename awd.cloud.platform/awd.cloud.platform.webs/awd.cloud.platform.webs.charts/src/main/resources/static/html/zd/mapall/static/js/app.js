define(function (require) {
    // 页面初始化
    require("init");
    // 引入jquery
    require("jquery");
    require("UnderScore");

    var config =
        require("app_config"),      // 项目配置
        DperV = require("DperV"),   // 视图解决方案
        yer = require('yer'),       // 提示工具
        dperAjax = require("dperAjax"),// 数据请求
        rendarTpl = require("rendarTpl"),               // 模板渲染
        showTime = require("dperUtils/date").showTime, // 日期工具
        ChartRing = require("classes/charts/main/ChartRing"),    // 圆环
        ChartBar = require("classes/charts/main/ChartBar"),      // 柱状图
        ChartLine = require("classes/charts/main/ChartLine"),    // 趋势图
        ChartMap = require("classes/charts/main/ChartMap"),      // 地图
        ChartPie = require("classes/charts/main/ChartPie"),      // 饼图
        ChartRander = require("classes/charts/main/ChartRander"),// 雷达图
        ChartRanderPop = require("classes/charts/main/ChartRanderPop"),// 雷达图 弹框
        DperStr = require('dperUtils/string'),
        DperArr = require('dperUtils/array'),
        Swiper = require('Swiper'),

        init_Application,           // 初始化函数
        basedata,                   // 基础数据
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
    basedata = {
        // 图表
        chart: {
            // 圆环
            // ring: null,
            // 柱状图
            bar: null,
            // 趋势图
            line: null,
            line2: null,
            line3: null,
            // 地图
            map: null,
            // 饼图
            pie: null,
            pie1: null,
            pie2: null,
            // 雷达图
            radar: null,
            // 弹框雷达图
            pop_radar: null
        },
        // 标记
        mark_: {
            loadFirst: true
        },
        // 切换开关
        off_: {
            map: false
        }
    };

    // 事件绑定
    evenbind = function () {
        // 地图切换按钮
        btFn();
        // 地图弹框消失事件
        $(window).off("click");
        $(window).on("click", function () {
            if ($("#pop").hasClass("active-pop")) {
                $("#pop").removeClass("active-pop");
                $('#starMsg').css('opacity', 0)
                // if($('#dper-map').hasClass("active"))getMap();
                // else getCityMap();
            }
        });
        //    列表弹框事件 2019 8 6
        popClickFn()
    };

    // 应用初始化方法
    init_Application = function () {
        evenbind();
        // 时间
        //showTimes();
        //setInterval(showTimes, 1000);
        // 星空
        star();
        // 看守所 选项卡
        tabControlFn();
        //今日监所警力部署情况
        costCondFn();
        // 弹框雷达图
        basedata.chart.pop_radar = new ChartRanderPop(App.getView("radar-pop"));

        // 全市监所安全管控情况
        getDisreJob("dper-dissta-jb");
        //setInterval(getDisreJob, config.inter.ref_);

        // 全市监所羁押分布情况
        basedata.chart.bar = new ChartBar(App.getView("dper-dissta-area"));
        getDisreArea();
        // setInterval(getDisreArea, config.inter.ref_);
        // 今日全市出所就医情况
        getRegion();
        // 全市被监管人员年度收押情况
        getTaskRevUpSt();
        // setInterval(getTaskRevUpSt, config.inter.ref_);

        // 地图
        basedata.chart.map = new ChartMap(App.getView("dper-map"));
        getMap(0);
        // basedata.chart.map_pop = new ChartMap(App.getView("dper-map-inside"));

        // 全市被监管人员案由分析情况
        getTaskRevStru();
        // setInterval(getTaskRevStru, config.inter.ref_);

        // 执法质量考评
        basedata.chart.radar = new ChartRander(App.getView("dper-dissta-state").querySelector('.dper-chart'));
        getDisreSta();
        // setInterval(getDisreSta, config.inter.ref_);

        // 关押量
        getExpTheYear();
        // setInterval(getExpTheYear, config.inter.exptyer);

        // 非初始化加载 不显示加载动画
        basedata.mark_.loadFirst = false;

        // 图表自适应
        var tmt;
        $(window).on('resize', function (resp) {
            clearTimeout(tmt);
            tmt = setTimeout(function () {
                _.each(basedata.chart, function (itm) {
                    if (itm.myChart) itm.myChart.resize()
                })
            }, 500)
        });

    };

    var popClickFn = function () {
        var $backBtn = $('#backBtn'),
            $popOne = $('#popBtn0'),
            $popTwo = $('#popBtn1'),
            $elPop = $('#popBox'),
            $elH = $(App.getView('listPop').querySelector('.table-header')),
            $elB = $(App.getView('listPop').querySelector('.table-body')),
            $swiper = "<div class='swiper-container dper-full-h' id='popSwiper'>" +
                "   <div class='swiper-wrapper dper-full-h'></div>" +
                "    <div class='swiper-scrollbar' id='popScr'></div>" +
                "</div>"
            ;
        $elPop.css("display", "none");
        $popOne.off("click");
        $popTwo.off("click");
        $backBtn.off("click");
        $popOne.on("click", function () {
        	console.log(this)
            $elPop.css("display", "block");
            $elB.empty();
            $elB.append($swiper);
            $elH.addClass("one-header");
            $elB.addClass("one-body");
            gainPopDataFn('listPop', 'one', renderPopListFn)
        });
        $popTwo.on("click", function () {
        	console.log(this)
            $elPop.css("display", "block");
            $elB.empty();
            $elB.append($swiper);
            $elH.addClass("two-header");
            $elB.addClass("two-body");
            gainPopDataFn('listPop', 'two', renderPopListFn)
        });
        $backBtn.on('click', function () {
        	console.log(this)
            $elH.empty();
            $elB.empty();
            $elB.append($swiper);
            if ($elH.hasClass("one-header")) $elH.removeClass("one-header");
            if ($elH.hasClass("two-header")) $elH.removeClass("two-header");
            if ($elB.hasClass("one-body")) $elB.removeClass("one-body");
            if ($elB.hasClass("two-body")) $elB.removeClass("two-body");
            $elPop.css("display", "none")
        })
    };
    var renderPopListFn = function (el, type, resp) {
        var $elH = $(App.getView('listPop').querySelector('.table-header')),
            $elB = $(App.getView('listPop').querySelector('.table-body')).find('.swiper-wrapper'),
            $childH = "<div class='pop-child-itm fl dper-full-h dper-por'><span class='text-overflow-e malign-center-text'>{{name}}</span></div>",
            $childB = "<div class='swiper-slide'>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{name}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{msg}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{time}}</span></div>" +
                "</div>",
            $childTB = "<div class='swiper-slide font-20'>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{name}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{msg}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{type}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{location}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{timeIn}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{timeOut}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{police}}</span></div>" +
                "   <div class='pop-child-itm dper-por fl dper-full-h'><span class='malign-center-text text-overflow-e'>{{judge}}</span></div>" +
                "</div>",
            mySwiper = null
            ;
        $elH.empty();
        $elB.empty();
        _.each(resp.head, function (itm) {
            $elH.append(rendarTpl(itm, $childH, 'itm'))
        })
        if (type === "one") {
            _.each(resp.data, function (itm) {
                $elB.append(rendarTpl(itm, $childB, 'itm'))
            })
        } else {
            _.each(resp.data, function (itm) {
                $elB.append(rendarTpl(itm, $childTB, 'itm'))
            })
        }
        mySwiper = new Swiper('#popSwiper', {
            autoplay: {
                delay: 5000
            },
            scrollbar: {
                el: '#popScr'
            },
            direction: 'vertical',
            slidesPerView: 10
        });

    };
    var gainPopDataFn = function (el, type, cb) {
        basefn(el, "弹框数据", 
        	config.interface.kss_jyByjbxx+"?type="+type, 
        	function (resp) {
        	 console.log(resp);
            resp = resp;
            $resp = resp.msg;
            cb && cb(el, type, $resp)
        })
    };
    /**
     * 弹框雷达图
     */
    // function PopRadarFn(el, type) {
    function PopRadarFn(el, type) {
        localStorage.getItem("idjsbhxf")
        var strify = localStorage.getItem("idjsbhxf")
        // basefn("radar-pop", "弹框雷达图", config.interface.kss_dd+"?strify="+strify, function (resp, dom) {
        basefn("radar-pop", "弹框雷达图", config.interface.kss_dd+"?strify="+strify, function (resp, dom) {
            // console.log("打印resp")
            // console.log(resp.mapResp)
            // if (type === 0) {
            //     // resp = resp.mapResp[0].l
            //     resp = 
            //     [
            //         {
            //             name: "朝阳区(看)",
            //             mmp: "朝阳区看守所",
            //             jsbh: 110105111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "朝阳区(拘)",
            //             mmp: "朝阳区拘留所",
            //             jsbh: 110105121,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "东城区(看)",
            //             mmp: "东城区看守所",
            //             jsbh: 110101111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "东城区(拘)",
            //             mmp: "东城区拘留所",
            //             jsbh: 110101121,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "西城区(看)",
            //             mmp: "西城区看守所",
            //             jsbh: 110102111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "海淀区(看)",
            //             mmp: "海淀区看守所",
            //             jsbh: 110108111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "海淀区(拘)",
            //             mmp: "海淀区拘留所",
            //             // jsbh:
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "丰台区(看)",
            //             mmp: "丰台区看守所",
            //             jsbh: 110106111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "石景山区(看)",
            //             mmp: "石景山区看守所",
            //             jsbh: 110107111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "平谷区(看)",
            //             mmp: "平谷区看守所",
            //             jsbh: 110226111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "密云区(看)",
            //             mmp: "密云区看守所",
            //             jsbh: 110228111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "顺义区(看)",
            //             mmp: "顺义区看守所",
            //             jsbh: 110113111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "怀柔区(看)",
            //             mmp: "怀柔区看守所",
            //             jsbh: 110227111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "通州区(看)",
            //             mmp: "通州区看守所",
            //             jsbh: 110112111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "昌平区(看)",
            //             mmp: "昌平区看守所",
            //             jsbh: 110221111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "延庆区(看)",
            //             mmp: "延庆区看守所",
            //             jsbh: 110229111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "大兴区(看)",
            //             mmp: "大兴区看守所",
            //             jsbh: 110224111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "门头沟区(看)",
            //             mmp: "门头沟区看守所",
            //             jsbh: 110109111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "房山区(看)",
            //             mmp: "房山区看守所",
            //             jsbh: 110111111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "房山区(拘)",
            //             mmp: "房山区拘留所",
            //             jsbh: 110111121,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         }
            //     ];
            // } else if (type === 1) {
            //     // resp = resp.mapResp[0].l
            //     resp = [
            //         {
            //             name: "朝阳区(看)",
            //             mmp: "朝阳区看守所",
            //             jsbh: 110105111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "朝阳区(拘)",
            //             mmp: "朝阳区拘留所",
            //             jsbh: 110105121,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "东城区(看)",
            //             mmp: "东城区看守所",
            //             jsbh: 110101111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "东城区(拘)",
            //             mmp: "东城区拘留所",
            //             jsbh: 110101121,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "西城区(看)",
            //             mmp: "西城区看守所",
            //             jsbh: 110102111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "海淀区(看)",
            //             mmp: "海淀区看守所",
            //             jsbh: 110108111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "海淀区(拘)",
            //             mmp: "海淀区拘留所",
            //             // jsbh:
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "丰台区(看)",
            //             mmp: "丰台区看守所",
            //             jsbh: 110106111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "石景山区(看)",
            //             mmp: "石景山区看守所",
            //             jsbh: 110107111,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         }
            //     ];
            // } else {
            //     // resp = resp.mapResp[0].l
            //     resp = [
            //         {
            //             name: "北京市第一看守所",
            //             mmp: "北京市第一看守所",
            //             jsbh: 110000114,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "北京市第二看守所",
            //             mmp: "北京市第二看守所",
            //             jsbh: 110000112,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "北京市第三看守所",
            //             mmp: "北京市第三看守所",
            //             jsbh: 110000113,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "北京市拘留所",
            //             mmp: "北京市拘留所",
            //             jsbh: 110000121,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "北京市强制隔离戒毒所",
            //             mmp: "北京市强制隔离戒毒所",
            //             jsbh: 130000030,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "北京市收容教育所",
            //             mmp: "北京市收容教育所",
            //             jsbh: 120000060,
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "北京市监护医疗所",
            //             mmp: "北京市监护医疗所",
            //             // jsbh:
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         },
            //         {
            //             name: "北京市强制医疗所",
            //             mmp: "北京市强制医疗所",
            //             // jsbh:
            //             // 均值
            //             value1: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //             // 市值
            //             value2: (function () {
            //                 var res = [];
            //                 var num = 5;
            //                 while (num--) {
            //                     res.push((Math.random() * 300).toFixed(0))
            //                 }
            //                 return res;
            //             })(),
            //         }
            //     ];
            // }
            // $.each(resp, function (idx, itm) {
            //     console.log("打印数据")
            //     // console.log(resp)
            //     itm.legend = itm.name.substring(0, 2);
            //     itm.personName = "1111"
            //     itm.phone = 1354872546 + (Math.random() * 10).toFixed(0);
            //     itm.site = '地址' + (Math.random() * 100000).toFixed(0);
            //     itm.per1 = (DperStr.Random(15, 0) * 100) + '人';
            //     itm.per2 = DperStr.Random(150, 0) + '人';
            //     itm.img = ''
            // });
            // $.each(resp, function (idx, itm) {
            //     if (el == itm.name) {
            //         basedata.chart.pop_radar.render(itm);
            //     }
            // });
        })
    }

    /**
     * 地图切换按钮
     */
    function btFn() {
        var $el = $("#btnBox").find('.lg-cut-child'), $fa = $('#mapBg'),
            $elBtn = $('#starFull'), $faBtn = $('#starMsg')
            ;
        if (!$('body').data('msgT')) $('body').data('msgT', 0);
        if (!$('body').data('msgB')) $('body').data('msgB', 2);
        if (!$('body').data('dataA')) $('body').data('dataA', 0);
        if (!$('body').data('dataB')) $('body').data('dataB', 0);
        $el.off('click');
        $el.on('click', function () {
            $("#pop").css({
                'opacity': 0,
                'z-index': -1
            });
            if ($(this).data('number') === 0) {
                $faBtn.css('opacity', 0);
                if ($('body').data('msgB') === 2) {
                    if ($(this).data('off')) {
                        $('body').data('msgT', 1);
                        $('body').data('dataA', 1);
                        $(this).data('off', false)
                    } else {
                        $('body').data('msgT', 0);
                        $('body').data('dataA', 0);
                        $(this).data('off', true)
                    }
                } else if ($('body').data('msgB') === 1) {
                    if ($(this).data('off')) {
                        $('body').data('msgT', 2);
                        $('body').data('dataA', 1);
                        $(this).data('off', false)
                    } else {
                        $('body').data('msgT', 0);
                        $('body').data('dataA', 2);
                        $(this).data('off', true)
                    }
                } else if ($('body').data('msgB') === 0) {
                    if ($(this).data('off')) {
                        $('body').data('msgT', 1);
                        $('body').data('dataA', 2);
                        $(this).data('off', false)
                    } else {
                        $('body').data('msgT', 2);
                        $('body').data('dataA', 0);
                        $(this).data('off', true)
                    }
                }
                removeClassFn($(this), $fa);
                $(this).addClass('map-small-' + $('body').data('msgT'));
                $fa.addClass('map-' + $('body').data('dataA'));
                getMap($('body').data('dataA'))
            } else {
                if ($('body').data('msgT') === 0) {
                    if ($(this).data('off')) {
                        $('body').data('msgB', 1);
                        $('body').data('dataB', 2);
                        $(this).data('off', false)
                    } else {
                        $('body').data('msgB', 2);
                        $('body').data('dataB', 0);
                        $(this).data('off', true)
                    }
                } else if ($('body').data('msgT') === 1) {
                    if ($(this).data('off')) {
                        $('body').data('msgB', 0);
                        $('body').data('dataB', 2);
                        $(this).data('off', false)
                    } else {
                        $('body').data('msgB', 2);
                        $('body').data('dataB', 1);
                        $(this).data('off', true)
                    }
                } else if ($('body').data('msgT') === 2) {
                    if ($(this).data('off')) {
                        $('body').data('msgB', 0);
                        $('body').data('dataB', 0);
                        $(this).data('off', false)
                    } else {
                        $('body').data('msgB', 1);
                        $('body').data('dataB', 1);
                        $(this).data('off', true)
                    }
                }
                removeClassFn($(this), $fa);
                $(this).addClass('map-small-' + $('body').data('msgB'));
                $fa.addClass('map-' + $('body').data('dataB'));
                getMap($('body').data('dataB'))
            }
        });
    }

    var removeClassFn = function (el, fa) {
        if (el.hasClass('map-small-0')) {
            el.removeClass('map-small-0')
        } else if (el.hasClass('map-small-1')) {
            el.removeClass('map-small-1')
        } else {
            el.removeClass('map-small-2')
        }
        if (fa.hasClass('map-0')) {
            fa.removeClass('map-0')
        } else if (fa.hasClass('map-1')) {
            fa.removeClass('map-1')
        } else {
            fa.removeClass('map-2')
        }
    }
    /**
     * 时间展示
     */
    function showTimes() {
        showTime(".dper-time-t", "yyyy年M月d日");
        showTime(".dper-time-b", "HH:mm:ss");
    }

    /**
     * 星空
     */
    function star() {
        var stars = 800;
        var $stars = $('.stars');
        var r = 800;
        var sen_start = 0;
        for (var i = 0; i < stars; i++) {
            if (window.CP.shouldStopExecution(1)) {
                break;
            }
            var $star = $('<div/>').addClass("star");
            $stars.append($star);
        }

        window.CP.exitedLoop(1);
        $('.star').each(function () {
            var cur = $(this);
            var s = 0.2 + Math.random() * 1;
            var curR = r + Math.random() * 300;
            cur.css({
                transformOrigin: '0 0 ' + curR + 'px',
                transform: ' translate3d(0,0,-' + curR + 'px) rotateY(' + Math.random() * 360 + 'deg) rotateX(' + Math.random() * -50 + 'deg) scale(' + s + ',' + s + ')'
            });
        });
    }

    /**
     * 数据请求基础方法
     * @param dom  呈现视图容器ID
     * @param desc 接口描述
     * @param url  接口地址
     * @param cb   成功回调函数
     */
    var basefn = function (dom, desc, url, cb) {
        if (basedata.mark_.loadFirst) {
            App.state(dom, 1);
        }
        dperAjax(desc, url, {
            successfn: function (resp) {

                if (url == "/zddp/findKss_dsjPT") {
                    // if (url == "http://192.168.4.76:12104/kss/gqsxx/select_GQSXX") {
                    console.info("basefn---resp---1")
                    console.info(url)
                    console.info(resp)
                    console.info("basefn---resp---2")
                }

                typeof cb(resp, dom) === 'undefined' && (App.state(dom, 2));
            },
            completefn: function (state, resp) {
                (!state) && App.state(dom, 0, resp[config.ajax.MESSAGE]);
            }
        })
    };
    // 看守所 选项卡
    var tabControlFn = function () {
        var $el = $('#tab .tab-title'),
            $elBL = $('#tab .tab-body').find('.t-b-left'),
            $elBR = $('#tab .tab-body').find('.t-b-right'),
            $child = '' +
                '<div class="tab-itm fl dper-por">\n' +
                '    <span class="malign-center-text text-overflow-e">{{name}}</span>\n' +
                '</div>',
            $childL = '' +
                '<div class="l-itm fl font-16 co-white align-center dper-por">\n' +
                '    <div class="malign-center-text">\n' +
                '        <p class="text-overflow-e">{{name}}</p>\n' +
                '        <p class="text-overflow-e "><span class="font-24 num-an">{{value}}</span> 人</p>\n' +
                '    </div>\n' +
                '</div>',
            $childR = '' +
                '<div class="r-itm font-16 co-white dper-por align-left">\n' +
                '    <div class="malign-center-left itm-left">\n' +
                '        <p class="text-overflow-e other-color">{{name}}</p>\n' +
                '        <p class="co-52c8ed text-overflow-e "><span class="font-24 num-an">{{value}}</span> 人</p>\n' +
                '    </div>\n' +
                '    <div class="malign-center-right itm-right">\n' +
                '        <p class="text-overflow-e">{{pro}}</p>\n' +
                '        <p class="co-52c8ed text-overflow-e"><span class="font-24 deci-number">{{pValue}}</span> %</p>\n' +
                '    </div>\n' +
                '</div>'
            ;
        $el.empty();
        $elBL.empty();
        $elBR.empty();
        tabControlGainDataFn(function (resp) {
            _.each(resp, function (itm) {
                $el.append(rendarTpl(itm, $child, 'itm'))
            });
            $el.children('').eq(0).addClass('active');
            _.each(resp[0].cont.l, function (itm) {
                $elBL.append(rendarTpl(itm, $childL, 'itm'))
            });
            _.each(resp[0].cont.r, function (itm) {
                $elBR.append(rendarTpl(itm, $childR, 'itm'))
            });
            $el.children('').off('click');
            $el.children('').on('click', function () {
                var name = $(this).text().replace(/(^\s*)|(\s*$)/g, "");
                $elBL.empty();
                $elBR.empty();
                $(this).addClass('active').siblings('').removeClass('active');
                _.each(resp, function (itm) {
                    if (name === itm.name) {
                        _.each(itm.cont.l, function (key) {
                            $elBL.append(rendarTpl(key, $childL, 'key'))
                        });
                        _.each(itm.cont.r, function (key) {
                            $elBR.append(rendarTpl(key, $childR, 'key'))
                        });
                        _.each($elBL.find('.num-an'), function (key) {
                            var number = Number($(key).text());
                            new DperStr.NumAnimate(key, number, '', 0).init()
                        });
                        _.each($elBR.find('.num-an'), function (key) {
                            var number = Number($(key).text());
                            new DperStr.NumAnimate(key, number, '', 0).init()
                        });
                        _.each($elBR.find('.deci-number'), function (key) {
                            var number = Number($(key).text());
                            new DperStr.NumAnimate(key, number, '', 2).init()
                        });
                    }
                });
            });
            _.each($elBL.find('.num-an'), function (itm) {
                var number = Number($(itm).text());
                new DperStr.NumAnimate(itm, number, '', 0).init()
            });
            _.each($elBR.find('.num-an'), function (itm) {
                var number = Number($(itm).text());
                new DperStr.NumAnimate(itm, number, '', 0).init()
            });
            _.each($elBR.find('.deci-number'), function (key) {
                var number = Number($(key).text());
                new DperStr.NumAnimate(key, number, '', 2).init()
            });
        })
    };

    //首页看守所
    /* config.interface.kss_url,*/
    /* config.interface.demo,*/
    var tabControlGainDataFn = function (cb) {
        dperAjax('看守所', config.interface.kss_url, {
            successfn: function (resp) {
                var data = resp;

                resp = [
                    /*{
                        name: '看守所',
                        cont: {
                            l: [

                               {
                                    name: '在押总数',
                                    value:1010,
                                    unit: '人'
                                },
                                {
                                    name: '其中女性',
                                    value:  2111,
                                    unit: '人'
                                },
                                {
                                    name: '今日入所',
                                    value:  10,
                                    unit: '人'
                                },
                                {
                                    name: '今日出所',
                                    value:  2,
                                    unit: '人'
                                }
                            ],
                            r: [
                                {
                                    name: '一级风险人数',
                                    value: 332,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 1.89,
                                },
                                {
                                    name: '二级风险人数',
                                    value: 366,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 2.08,
                                },
                                {
                                    name: '三级风险人数',
                                    value: 1142,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 6.48,
                                },
                            ]
                        }
                    },
                    {
                        name: '拘留所',
                        cont: {
                            l: [
                                {
                                    name: '在押总数',
                                    value:  1782,
                                    unit: '人'
                                },
                                {
                                    name: '其中女性',
                                    value:  245,
                                    unit: '人'
                                },
                                {
                                    name: '今日入所',
                                    value:  12,
                                    unit: '人'
                                },
                                {
                                    name: '今日出所',
                                    value:  6,
                                    unit: '人'
                                }
                            ],
                            r: [
                                {
                                    name: '一级风险人数',
                                    value: 112,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 1.65,
                                },
                                {
                                    name: '二级风险人数',
                                    value: 137,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 8.95,
                                },
                                {
                                    name: '三级风险人数',
                                    value: 206,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 9.41,
                                },
                            ]
                        }
                    },
                    {
                        name: '戒毒所',
                        cont: {
                            l: [
                                {
                                    name: '在押总数',
                                    value:  2305,
                                    unit: '人'
                                },
                                {
                                    name: '其中女性',
                                    value:  406,
                                    unit: '人'
                                },
                                {
                                    name: '今日入所',
                                    value:  4,
                                    unit: '人'
                                },
                                {
                                    name: '今日出所',
                                    value:  0,
                                    unit: '人'
                                }
                            ], 
                            r: [
                                {
                                    name: '一级风险人数',
                                    value: 112,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 4.86,
                                },
                                {
                                    name: '二级风险人数',
                                    value: 67,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 2.91,
                                },
                                {
                                    name: '三级风险人数',
                                    value: 342,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 9.84,
                                },
                            ]
                        }
                    },
                    {
                        name: '强疗所',
                        cont: {
                            l: [
                                {
                                    name: '在押总数',
                                    value:  1105,
                                    unit: '人'
                                },
                                {
                                    name: '其中女性',
                                    value:  206,
                                    unit: '人'
                                },
                                {
                                    name: '今日入所',
                                    value:  1,
                                    unit: '人'
                                },
                                {
                                    name: '今日出所',
                                    value:  3,
                                    unit: '人'
                                }
                            ],
                            r: [
                                {
                                    name: '一级风险人数',
                                    value: 112,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 9.14,
                                },
                                {
                                    name: '二级风险人数',
                                    value: 35,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 3.17,
                                },
                                {
                                    name: '三级风险人数',
                                    value: 456,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 9.20,
                                },
                            ]
                        }
                    },
                    {
                        name: '收教所',
                        cont: {
                            l: [
                                {
                                    name: '在押总数',
                                    value:  856,
                                    unit: '人'
                                },
                                {
                                    name: '其中女性',
                                    value:  54,
                                    unit: '人'
                                },
                                {
                                    name: '今日入所',
                                    value:  0,
                                    unit: '人'
                                },
                                {
                                    name: '今日出所',
                                    value:  0,
                                    unit: '人'
                                }
                            ],
                            r: [
                                {
                                    name: '一级风险人数',
                                    value: 12,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 1.40,
                                },
                                {
                                    name: '二级风险人数',
                                    value: 35,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 4.05,
                                },
                                {
                                    name: '三级风险人数',
                                    value: 104,
                                    unit: '人',
                                    pro: '占比',
                                    pValue: 8.15,
                                }
                            ]
                        }
                    }*/
                ];
                resp.push(data.kss);
                resp.push(data.jls);

                cb && cb(resp);
            },
            completefn: function (state, resp) {
                /*console.info("fdfdfdfdf")
                console.info(state)
                console.info(resp)
                console.info("888888888888888")*/

                (!state) && yer(resp[config.ajax.MESSAGE], 0)
            }
        })
    };
    // 今日监所警力部署情况
    var costCondFn = function () {
        var $el = $('#cond'),
            $swiper = '<div class="swiper-container dper-full-h font-16 co-white dper-cursor" id="swiper0">' +
                '   <div class="swiper-wrapper dper-full-h"></div>' +
                '</div>'
            ;
        $el.empty();
        $el.append($swiper);
        constCondGainDataFn(renderCondFn)
    };
    var renderCondFn = function (resp) {
        var $el = $('#cond').find('.swiper-wrapper'),
            $child = '<div class="swiper-slide">' +
                '   <div class="slide-itm dper-por dper-full-h fl">' +
                '       <div class="cont-center malign-center dper-full-w">' +
                '           <p class="dper-full-w text-overflow-e">{{depart}}</p>' +
                '           <p class="dper-fw  text-overflow-e">{{departB}}</p>' +
                '       </div>' +
                '   </div>' +
                '   <div class="slide-itm dper-por dper-full-h fl">' +
                '       <div class="cont-center malign-center dper-full-w">' +
                '           <p class="dper-full-w text-overflow-e">值班所领导：</p>' +
                '           <p class="dper-fw text-overflow-e">{{name}}</p>' +
                '       </div>' +
                '   </div>' +
                '   <div class="slide-itm dper-por dper-full-h fl">' +
                '       <div class="cont-center malign-center dper-full-w">' +
                '       <p class="dper-full-w text-overflow-e">在岗警力：</p>' +
                '       <p class="dper-fw text-overflow-e">{{number}}</p>' +
                '       </div>' +
                '   </div>' +
                '   <div class="slide-itm dper-por dper-full-h fl">' +
                '       <div class="cont-center malign-center dper-full-w">' +
                '       <p class="dper-full-w text-overflow-e">联系方式：</p>' +
                '       <p class="dper-fw text-overflow-e">{{phone}}</p>' +
                '       </div>' +
                '   </div>' +
                '</div>',
            mySwiper = null;
        // _.each($el.find('.num-an'),function(itm){
        //     var number = Number($(itm).text());
        //     new DperStr.NumAnimate(itm,number,'  ',0).init()
        // })
        _.each(resp, function (itm) {
            $el.append(rendarTpl(itm, $child, 'itm'))
        });
        mySwiper = new Swiper('#swiper0', {
            autoplay: {
                delay: 5000,
                disableOnInteraction: false
            },
            loop: true,
            direction: 'vertical',
            slidesPerView: 3
        });

        $('#swiper0').on('mouseenter', function () {
            mySwiper.autoplay.stop();
        });
        $('#swiper0').on('mouseleave', function () {
            mySwiper.autoplay.start();
        })
    };
    /*config.interface.demo,*/
    var constCondGainDataFn = function (cb) {
        var arr = ["110000114", "110000112", "110000113", "110000116", "110101111", "110102111", "110105111",
            "110106111", "110107111", "110108111", "110109111", "110111111", "110111112", "110112111", "110113111",
            "110221111", "110224111", "110226111", "110227111", "110228111", "110229111","110000121","110101121","110105121","110111121"
        ]
        var strify = JSON.stringify(arr);//将数组转化为json字符串
        dperAjax('今日监所警力部署情况', config.interface.kss_url6 + "?strify=" + strify, {
            successfn: function (resp) {
                //resp = resp.data;
                var data = resp;
                resp = data.JSJL1
                // [

                /* {
                     depart: '北京市',
                     departB: '第二看守所',
                     name: '李所长',
                     phone: DperStr.Random(900000000,0),
                     number: 54
                 },
                 {
                     depart: '北京市',
                     departB: '第三看守所',
                     name: '陈所长',
                     phone: DperStr.Random(900000000,0),
                     number: 23
                 },
                 {
                     depart: '北京市',
                     departB: '第一看守所',
                     name: '肖所长',
                     phone: DperStr.Random(900000000,0),
                     number: 125
                 },
                 {
                     depart: '北京市',
                     departB: '第六看守所',
                     name: '陈所长',
                     phone: DperStr.Random(900000000,0),
                     number: 34
                 },
                 {
                     depart: '北京市',
                     departB: '拘留所',
                     name: '王所长',
                     phone: DperStr.Random(900000000,0),
                     number: 45
                 },
                 {
                     depart: '东城区',
                     departB: '看守所',
                     name: '陈所长',
                     phone: DperStr.Random(900000000,0),
                     number: 34
                 },
                 {
                     depart: '东城区',
                     departB: '拘留所',
                     name: '王所长',
                     phone: DperStr.Random(900000000,0),
                     number: 86
                 },
                 {
                     depart: '西城区',
                     departB: '看守所',
                     name: '王所长',
                     phone: DperStr.Random(900000000,0),
                     number: 45
                 },
                 {
                     depart: '朝阳区',
                     departB: '看守所',
                     name: '王所长',
                     phone: DperStr.Random(900000000,0),
                     number: 187
                 },
                 {
                     depart: '朝阳区',
                     departB: '拘留所',
                     name: '张所长',
                     phone: DperStr.Random(900000000,0),
                     number: 123
                 },
                 {
                     depart: '丰台区',
                     departB: '看守所',
                     name: '张所长',
                     phone: DperStr.Random(900000000,0),
                     number: 56
                 },
                 {
                     depart: '石景山区',
                     departB: '看守所',
                     name: '张所长',
                     phone: DperStr.Random(900000000,0),
                     number: 67
                 },
                 {
                     depart: '海淀区',
                     departB: '看守所',
                     name: '齐所长',
                     phone: DperStr.Random(900000000,0),
                     number: 143
                 },
                 {
                     depart: '海淀区',
                     departB: '拘留所',
                     name: '何所长',
                     phone: DperStr.Random(900000000,0),
                     number: 45
                 },
                 {
                     depart: '门头沟区',
                     departB: '看守所',
                     name: '庞所长',
                     phone: DperStr.Random(900000000,0),
                     number: 22
                 },
                 {
                     depart: '房山区',
                     departB: '看守所',
                     name: '曹所长',
                     phone: DperStr.Random(900000000,0),
                     number: 45
                 },
                 {
                     depart: '房山区',
                     departB: '第二看守所',
                     name: '李所长',
                     phone: DperStr.Random(900000000,0),
                     number: 56
                 },
                 {
                     depart: '房山区',
                     departB: '拘留所',
                     name: '张所长',
                     phone: DperStr.Random(900000000,0),
                     number: 67
                 },
                 {
                     depart: '通州区',
                     departB: '看守所',
                     name: '张所长',
                     phone: DperStr.Random(900000000,0),
                     number: 67
                 },
                 {
                     depart: '顺义区',
                     departB: '看守所',
                     name: '梁所长',
                     phone: DperStr.Random(900000000,0),
                     number: 23
                 },
                 {
                     depart: '昌平区',
                     departB: '看守所',
                     name: '张所长',
                     phone: DperStr.Random(900000000,0),
                     number: 45
                 },
                 {
                     depart: '大兴区',
                     departB: '看守所',
                     name: '张所长',
                     phone: DperStr.Random(900000000,0),
                     number: 136
                 },
                 {
                     depart: '平谷区',
                     departB: '看守所',
                     name: '谌所长',
                     phone: DperStr.Random(900000000,0),
                     number: 34
                 },
                 {
                     depart: '怀柔区',
                     departB: '看守所',
                     name: '何所长',
                     phone: DperStr.Random(900000000,0),
                     number: 34
                 },
                 {
                     depart: '密云区',
                     departB: '看守所',
                     name: '陈所长',
                     phone: DperStr.Random(900000000,0),
                     number: 45
                 },
                 {
                     depart: '延庆区',
                     departB: '看守所',
                     name: '杨所长',
                     phone: DperStr.Random(900000000,0),
                     number: 54
                 },
                 {
                     depart: '北京',
                     departB: '收容教育所',
                     name: '李所长',
                     phone: DperStr.Random(900000000,0),
                     number: 24
                 },
                 {
                     depart: '北京市',
                     departB: '强制隔离戒毒所',
                     name: '王所长',
                     phone: DperStr.Random(900000000,0),
                     number: 35
                 }*/
                // ]
                // resp.push(data.JSJL1);
                // resp.push(data.JSJL2);
                // resp.push(data.JSJL3);
                // resp.push(data.JSJL4);


                cb && cb(resp)
            },
            completefn: function (state, resp) {
                (!state) && yer(resp[config.ajax.MESSAGE], 0)
            }
        })
    };
    // 全市监所安全管控情况
    function getDisreJob(el) {
        var $el = $(App.getView(el).querySelectorAll('.list-body')),
            $swiper = "<div class='swiper-container dper-full-h' id='lSO'>" +
                "   <div class='swiper-wrapper dper-full-h'></div>" +
                "</div>",
            $swiper1 = "<div class='swiper-container dper-full-h' id='lS1'>" +
                "   <div class='swiper-wrapper dper-full-h'></div>" +
                "</div>";
        $el.eq(0).empty();
        $el.eq(1).empty();
        $el.eq(0).append($swiper);
        $el.eq(1).append($swiper1);
        gainDisreJobFn(el, renderDisreJobFn)
    };
    var renderDisreJobFn = function (el, resp) {

        var $el = $(App.getView(el).querySelectorAll('.list-body')).find('.swiper-wrapper'),
            $child = "<div class='swiper-slide dper-por'>" +
                "  <div class='malign-center-text text-overflow-e {{cla}} align-left'>" +
                "       <span class='rank dper-fw font-18'>{{id}}</span>" +
                "       <span class='rank-name'>{{name}}</span>" +
                "       <span class='rank-value malign-center-left'>{{value}}人</span>" +
                "   </div> " +
                "</div>",
            mySwiper = null,
            mySwiper2 = null
            ;
        $el.eq(0).empty();
        $el.eq(1).empty();
        _.each(resp.l, function (itm, idx) {
            itm.id = idx + 1
            itm.cla = idx < 3 ? 'pev-mmp-' + idx : 'pev-mmp';
            $el.eq(0).append(rendarTpl(itm, $child, 'itm'))
        })
        _.each(resp.r, function (itm, idx) {
            itm.id = idx + 1
            itm.cla = idx < 3 ? 'pev-mmp-' + idx : 'pev-mmp';
            $el.eq(1).append(rendarTpl(itm, $child, 'itm'))
        })
        mySwiper = new Swiper("#lSO", {
            autoplay: {
                delay: 5000,
                disableOnInteraction: false
            },
            loop: true,
            direction: 'vertical',
            slidesPerView: 4
        });
        mySwiper2 = new Swiper("#lS1", {
            autoplay: {
                delay: 5000,
                disableOnInteraction: false
            },
            loop: true,
            direction: 'vertical',
            slidesPerView: 4
        })
    };

    /*大屏单独关押 械具使用情况 config.interface.demo,*/
    var gainDisreJobFn = function (el, cb) {

        var arr = ["110000114", "110000112", "110000113", "110000116", "110101111", "110102111", "110105111",
            "110106111", "110107111", "110108111", "110109111", "110111111", "110111112", "110112111", "110113111",
            "110221111", "110224111", "110226111", "110227111", "110228111", "110229111","110000121","110101121",
            "110105121","110111121"
        ]
        var strify = JSON.stringify(arr);//将数组转化为json字符串
        basefn(el, '全市看守所办案环节分布情况',
            config.interface.kss_url3 + "?strify=" + strify,
            //config.interface.demo,
            function (resp) {

                /* resp = {
                 l: [
                     {
                         name:"市第一看守所",
                         value: 1
                     },
                     {
                         name:"朝阳区看守所",
                         value: 1
                     },
                     {
                         name:"西城区看守所",
                         value: 1
                     },
                     {
                         name:"平谷区看守所",
                         value: 1
                     },
                     {
                         name:"密云区看守所",
                         value: 1
                     }
                 ],
                 r: [
                     {
                         name:"市第一看守所",
                         value: 22
                     },
                     {
                         name:"朝阳区看守所",
                         value: 22
                     },
                     {
                         name:"门头沟区看守所",
                         value: 22
                     },
                     {
                         name:"大兴区看守所",
                         value: 22
                     },
                     {
                         name:"东城区看守所",
                         value: 22
                     },
                     {
                         name:"西城区看守所",
                         value: 22
                     },
                     {
                         name:"平谷区看守所",
                         value: 22
                     },
                     {
                         name:"密云区看守所",
                         value: 22
                     }
                 ]
             }*/
                cb && cb(el, resp.mapResp)
            });
    }

    /*全市监所羁押分布情况  config.interface.demo, config.interface.kss_url2, */
    function getDisreArea() {
        var arr = ["110101111", "110102111", "110000113", "110105111", "110106111", "110108111", "110109111",
            "110111111", "110111112", "110112111", "110113111", "110221111", "110224111", "110226111", "110227111",
            "110228111", "110229111","110000114","110000112","110000113","110000116","110000121","110101121","110105121","110111121"
        ]

        var strify = JSON.stringify(arr);//将数组转化为json字符串
        basefn("dper-dissta-area", "全市监所羁押分布情况", config.interface.kss_url2 + "?strify=" + strify, function (resp, dom) {
            var data = resp;
            // 模拟数据
            resp = data.JSJY1
            // [
            /* {
                 name: "延庆区",
                 constNumber: 3000,
                 alineNumber: 213
             },
             {
                 name: "怀柔区",
                 constNumber: 3000,
                 alineNumber: 209
             },
             {
                 name: "昌平区",
                 constNumber: 3000,
                 alineNumber: 989
             },
             {
                 name: "门头沟",
                 constNumber: 3000,
                 alineNumber: 146
             },
             {
                 name: "房山区",
                 constNumber: 3000,
                 alineNumber: 823
             },
             {
                 name: "石景山",
                 constNumber: 3000,
                 alineNumber: 308
             },
             {
                 name: "海淀区",
                 constNumber: 1000,
                 alineNumber: 3008
             },
             {
                 name: "西城区",
                 constNumber: 3000,
                 alineNumber: 856
             },
             {
                 name: "东城区",
                 constNumber: 3000,
                 alineNumber: 985
             },
             {
                 name: "朝阳区",
                 constNumber: 3000,
                 alineNumber: 3633
             },
             {
                 name: "大兴区",
                 constNumber: 3000,
                 alineNumber: 1205
             },
             {
                 name: "丰台区",
                 constNumber: 3000,
                 alineNumber: 1599
             },
             {
                 name: "密云区",
                 constNumber: 3000,
                 alineNumber: 282
             },
             {
                 name: "平谷区",
                 constNumber: 3000,
                 alineNumber: 313
             },
             {
                 name: "顺义区",
                 constNumber: 3000,
                 alineNumber: 811
             },
             {
                 name: "通州区",
                 constNumber: 3000,
                 alineNumber: 856
             }*/
            // ];

            // resp.push(data.JSJY1);
            // resp.push(data.JSJY2);
            // resp.push(data.JSJY3);
            // resp.push(data.JSJY4);
            // resp.push(data.JSJY5);
            // resp.push(data.JSJY6);
            // resp.push(data.JSJY7);
            // resp.push(data.JSJY8);
            // resp.push(data.JSJY9);
            // resp.push(data.JSJY10);
            // resp.push(data.JSJY11);
            // resp.push(data.JSJY12);
            // resp.push(data.JSJY13);
            // resp.push(data.JSJY14);
            // resp.push(data.JSJY15);
            // resp.push(data.JSJY16);





            var resp_data = {
                max: 0,
                min: 0,
                xAxis: [],
                data: []
            };
            resp.sort(DperArr.compare('alineNumber')).reverse();

            // TODO 数据处理
            for (var i = 0; i < resp.length; i++) {

                var itm = resp[i];
                if (i === 0) {
                    resp_data.min = Number(itm.alineNumber);
                    resp_data.max = Number(itm.alineNumber);
                }
                if (resp_data.max < Number(itm.alineNumber)) {
                    resp_data.max = itm.alineNumber;
                }
                if (resp_data.min > Number(itm.alineNumber)) {
                    resp_data.min = itm.alineNumber;
                }
                resp_data.xAxis.push(itm.name);
                if (Number(itm.alineNumber) / Number(itm.constNumber) <= .7) {
                    resp_data.data.push({
                        name: itm.name,
                        value: itm.alineNumber,
                        col0: '#2eff31',
                        col1: '#00d556'
                    });
                }
                else if (Number(itm.alineNumber) / Number(itm.constNumber) > 1) {
                    resp_data.data.push({
                        name: itm.name,
                        value: itm.alineNumber,
                        col0: '#f00',
                        col1: '#840000'
                    });
                } else {
                    resp_data.data.push({
                        name: itm.name,
                        value: itm.alineNumber,
                        col0: '#ffc74b',
                        col1: '#ef7018'
                    });
                }
            }
            if (resp_data.data.length < 1) {
                App.state(dom, 0, "暂无相关数据");
                return false;
            }
            basedata.chart.bar.render(resp_data);
            // console.log(resp_data);
        });
    }
    // 今日全市出所就医情况 config.interface.demo,
    var getRegion = function () {
        var arr = ["110000114", "110000112", "110000113", "110000116", "110101111", "110102111", "110105111",
            "110106111", "110107111", "110108111", "110109111", "110111111", "110111112", "110112111", "110113111",
            "110221111", "110224111", "110226111", "110227111", "110228111", "110229111","110000121","110101121","110105121","110111121"
        ]
        var strify = JSON.stringify(arr);//将数组转化为json字符串
        dperAjax('今日全市出所就医情况', config.interface.kss_url4 + "?strify=" + strify, {
            successfn: function (resp) {
                //resp = resp.data;
                var data = resp;
                resp = data.kss;
                /* {
                  /!*l: [
                      {
                          name: '出所就医人数',
                          value: 9,
                          unit: '人'
                      },
                      {
                          name: '目前住院人数',
                          value: 133,
                          unit: '人'
                      },
                      {
                          name: '公安医院人数',
                          value: 120,
                          unit: '人'
                      }
                  ],
                  r: [
                      {
                          name: '朝阳区看守所',
                          value: 0
                      },
                     {
                          name: '朝阳区拘留所',
                          value: DperStr.Random(100,0)
                      },
                      {
                          name: '海淀区看守所',
                          value: 1
                      },
                      {
                          name: '顺义区看守所',
                          value: 1
                      },
                      {
                          name: '通州区看守所',
                          value: 2
                      },
                      {
                          name: '市第三看守所',
                          value: 1
                      },
                      {
                          name: '海淀区拘留所',
                          value: DperStr.Random(100,0)
                      },
                      {
                          name: '丰台区看守所',
                          value: 0
                      },
                      {
                          name: '延庆区看守所',
                          value: 0
                      },
                      {
                          name: '市第一看守所',
                          value: 1
                      },
                      {
                          name: '房山区看守所',
                          value: 0
                      },
                      {
                          name: '门头沟区看守所',
                          value: 0
                      },
                      {
                          name: '大兴区看守所',
                          value: 3
                      },
                      {
                          name: '东城区看守所',
                          value: 0
                      }
                  ]*!/
              };
              resp.push(data.kss);*/


                var $elL = $('#rank .lg-left').children('.dper-zview'),
                    $elR = $('#rank .lg-right').children('.dper-zview'),
                    $swiper = '<div class="swiper-container dper-full-h co-white font-16" id="swiper1">' +
                        '   <div class="swiper-wrapper dper-full-h"></div>' +
                        '</div>',
                    $swiper2 = '<div class="swiper-container dper-full-h co-white font-16" id="rcrSwiper">' +
                        '   <div class="swiper-wrapper dper-full-h"></div>' +
                        '</div>'
                    ;
                $elL.empty();
                $elR.empty();
                $elL.append($swiper);
                $elR.append($swiper2);
                renderRegionLeftFn(resp)
            },
            completefn: function (state, resp) {
                (!state) && yer(resp[config.ajax.MESSAGE], 0)
            }
        })
    };
    var renderRegionLeftFn = function (resp) {
        var $el = $('#rank .lg-left').find('.swiper-wrapper'),
            $elT = $('#rank .lg-right').find('.swiper-wrapper'),
            $child = '<div class="swiper-slide dper-por font-16">' +
                ' <p class="prison-write malign-center-left text-overflow-e ">{{name}}：</p>' +
                ' <p class="prison-write malign-center-left text-overflow-e">{{value}}</p>' +
                ' <p class="prison-write malign-center-right text-overflow-e ">{{unit}}</p>' +
                '</div>',
            $childT = '<div class="swiper-slide {{cla}}">' +
                ' <div class="prison-name fl dper-full-h dper-por font-24 dper-fw">' +
                '   <span class="malign-center-text text-overflow-e">{{id}}</span>' +
                ' </div>' +
                ' <div class="prison-name fl dper-full-h dper-por">' +
                '   <span class="malign-center-left text-overflow-e">{{name}}</span>' +
                '</div>' +
                ' <div class="prison-name fl dper-full-h dper-por">' +
                '   <span class="malign-center-text text-overflow-e">{{value}}人</span>' +
                '</div>' +
                '</div>',
            mySwiper = null,
            mySwiperT = null
            ;
        resp.r.sort(DperArr.compare('value')).reverse();
        $el.empty();
        $elT.empty();
        _.each(resp.l, function (itm, idx) {
            itm.cla = idx < 3 ? "dper-prison-" + idx : "dper-prison";
            $el.append(rendarTpl(itm, $child, 'itm'))
        })
        _.each(resp.r, function (itm, idx) {
            itm.cla = idx < 3 ? "dper-sort-" + idx : "dper-sort";
            itm.id = idx + 1
            $elT.append(rendarTpl(itm, $childT, 'itm'))
        })
        mySwiper = new Swiper('#swiper1', {
            // autoplay: {
            //     delay: 5000
            // },
            // loop: true,
            direction: 'vertical',
            slidesPerView: 3
        })
        mySwiperT = new Swiper('#rcrSwiper', {
            autoplay: {
                delay: 5000,
                disableOnInteraction: false
            },
            loop: true,
            direction: 'vertical',
            slidesPerView: 3
        })
    };
    // 全市被监管人员年度收押情况
    function getTaskRevUpSt() {
        basedata.chart.line = new ChartLine($(App.getView("dper-sta-da").querySelectorAll('.line-itm')).eq(0)[0]);
        basedata.chart.line2 = new ChartLine($(App.getView("dper-sta-da").querySelectorAll('.line-itm')).eq(1)[0]);
        basedata.chart.line3 = new ChartLine($(App.getView("dper-sta-da").querySelectorAll('.line-itm')).eq(2)[0]);
        var $btn = $(App.getView("dper-sta-da").querySelector('.year-btn')).find(".year-itm");
        wrapperFn();
        // setInterval(wrapperFn,3000);
        $btn.on('click', function () {
            $(this).addClass('active').siblings().removeClass('active');
            // gainTaskRevUpStFn("dper-sta-da", $(this).data('name'), renderTaskRevUpStFn)
            if ($(this).data('name') === '日') {
                $(App.getView("dper-sta-da").querySelector('.wrapper-box')).animate({ top: -288 + 'px' }, 500);
                gainTaskRevUpStFn("dper-sta-da", "日", renderTaskRevUpStFn)
            } else {
                $(App.getView("dper-sta-da").querySelector('.wrapper-box')).animate({ top: -144 + 'px' }, 500);
                gainTaskRevUpStFn("dper-sta-da", "月", renderTaskRevUpStFn)
            }
        });
        gainTaskRevUpStFn("dper-sta-da", "日", renderTaskRevUpStFn)
    }
    var wrapperFn = function () {
        var $el = $(App.getView("dper-sta-da").querySelector('.wrapper-box')),
            $child = $(App.getView("dper-sta-da").querySelectorAll('.line-itm')),
            $num = 0,
            $tmt = null,
            $h = $child.eq(0).height();
        if (!$el.data('num')) $el.data('num', 0);
        $el.on('mouseenter', function () {
            clearInterval($tmt)
        });
        $el.on('mouseleave', function () {
            $tmt = setInterval(function () {
                $num = Number($el.data('num'));
                $num++;
                if ($num === $child.length) {
                    $num = 1;
                    $el.css('top', 0 + 'px');
                }
                $el.animate({ top: -$num * $h + 'px' }, 500);
                $el.data('num', $num);
            }, 5000);
        });
        $tmt = setInterval(function () {
            $num++;
            if ($num === $child.length) {
                $num = 1;
                $el.css('top', 0 + 'px')
            }
            $el.animate({ top: -$num * $h + 'px' }, 500);
            $el.data('num', $num);
        }, 5000);
    };
    var renderTaskRevUpStFn = function (el, resp) {
        _.each(resp, function (itm) {
            if (itm.name === '日') {
                basedata.chart.line.render(itm);
                basedata.chart.line3.render(itm)
            } else {
                basedata.chart.line2.render(itm);
            }
        })
    };

    /*全市被监管人员收押情况*/
    //config.interface.demo,
    var gainTaskRevUpStFn = function (el, type, cb) {
        basefn(el, "全市被监管人员年度增长情况", config.interface.kss_url7, function (resp, dom) {
            // 模拟数据
            //var data = resp;
            // console.log(resp)

            //console.info(data.day);
            //console.info("daole===3232=====")
            //resp=data.day;
            /*  resp=[
                 {
                      name: "日",
                      list: [
                          {
                              name: '看守所',
                              list: []
                          },
                          {
                              name: '拘留所',
                              list: []
                          }
                      ]
                  },
                  {
                      name: "月",
                      list:  [
                          {
                              name: '看守所',
                              list: []
                          },
                          {
                              name: '拘留所',
                              list: []
                          }
                      ]
                  }
              ];*/

            /*var data = resp;*/
            /* resp.push(data.day);
             resp.push(data.month);*/

            /* var resp_data;
             if (type === '月'){
                 _.each(resp,function(itm){
                     console.info(itm+"====2507=====")
                     if(itm.name === type){
                         _.each(itm.list,function(key){
                             if (key.name === '看守所') {
                                 key.list.push((function(){
                                     var arr = [],
                                         month = Number(new Date().pattern("M"));
                                     for(var i = month-4; i <= month; i++){
                                         arr.push({
                                             name: i+"月",
                                             value: 1000+DperStr.Random(1000,0)
                                         })
                                     }
                                     return arr
                                 })())
                             }else {
                                 key.list.push((function(){
                                     var arr = [],
                                         month = Number(new Date().pattern("M"));
                                     for(var i = month-4; i <= month; i++){
                                         arr.push({
                                             name: i+"月",
                                             value: 1000+DperStr.Random(1000,0)
                                         })
                                     }
                                     return arr
                                 })())
                             }
                         })
                     }
                 })
             }
             else {
                 _.each(resp,function(itm){
                     if(itm.name === type){
                         _.each(itm.list,function(key){
                             if (key.name === '看守所') {
                                 key.list.push((function(){
                                     var arr = [],
                                         day = Number(new Date().pattern("d")),
                                         month = Number(new Date().pattern("M"));
                                     for(var i=31-23+day; i<=31;i++){
                                         arr.push({
                                             name: (month-1)+'月'+i+'日',
                                             value: 10+DperStr.Random(40,0)
                                         })
                                     }
                                     for(var j = 1; j<=day; j++){
                                         arr.push({
                                             name: month+'月'+j+'日',
                                             value: 10+DperStr.Random(40,0)
                                         })
                                     }
                                     return arr
                                 })())
                             }else {
                                 key.list.push((function(){
                                     var arr = [],
                                         day = Number(new Date().pattern("d")),
                                         month = Number(new Date().pattern("M"));
                                     for(var i=31-23+day; i<=31;i++){
                                         arr.push({
                                             name: (month-1)+'月'+i+'日',
                                             value: 10+DperStr.Random(40,0)
                                         })
                                     }
                                     for(var j = 1; j<=day; j++){
                                         arr.push({
                                             name: month+'月'+j+'日',
                                             value: 10+DperStr.Random(40,0)
                                         })
                                     }
                                     return arr
                                 }))
                             }
                         })
                     }
                 })
             }*/
            /* if(type === '月'){
                 _.each(resp,function(itm){
                     if (itm.name === type){
                         resp_data = itm.list
                     }
                 })
             }
             else {
                 _.each(resp,function(itm){
                     if (itm.name === type){
                         resp_data = itm.list
                     }
                 })
             }*/
            // TODO 数据格式转换

            if (resp.data.length < 1) {
                App.state(dom, 0, "暂无相关数据");
                return false;
            }


            cb && cb(el, resp.day);
            // console.log(resp.day)

        });
    };

    // 全市被监管人员案由分析情况
    function getTaskRevStru() {
        basedata.chart.pie = new ChartPie(App.getView("dper-sta-gf").querySelectorAll(".dper-chart")[0]);
        basedata.chart.pie1 = new ChartPie(App.getView("dper-sta-gf").querySelectorAll(".dper-chart")[1]);
        basedata.chart.pie2 = new ChartPie(App.getView("dper-sta-gf").querySelectorAll(".dper-chart")[2]);
        // basedata.chart.pie3 = new ChartPie(App.getView("dper-sta-gf").querySelectorAll(".dper-chart")[3]);

        /*config.interface.demo,*/
        basefn("dper-sta-gf", "全市被监管人员案由分析情况", config.interface.kss_url5, function (resp, dom) {
            // 模拟数据
            var data = resp;
            resp = [];
            resp.push(data.kss);
            resp.push(data.jls);

            /*[
             {
                 name: '看守所',
                 list: [
                     {
                         name: '盗窃',
                         value: 25
                     },
                     {
                         name: '贩卖毒品',
                         value: 20
                     },
                     {
                         name: '故意伤害',
                         value: 30
                     },
                     {
                         name: '危险驾驶',
                         value: 5
                     },
                     {
                         name: '其他案由',
                         value: 10
                     }
                 ]
             },
             {
                 name: '拘留所',
                 list: [
                     {
                         name: '盗窃',
                         value: 15
                     },
                     {
                         name: '贩卖毒品',
                         value: 21
                     },
                     {
                         name: '故意伤害',
                         value: 22
                     },
                     {
                         name: '危险驾驶',
                         value: 32
                     },
                     {
                         name: '其他案由',
                         value: 20
                     }
                 ]
             }
         ];*/

            var $sum = 0, $sum2 = 0
                ;
            _.each(resp[0].list, function (itm) {
                $sum += itm.value
            });
            _.each(resp[1].list, function (itm) {
                $sum2 += itm.value
            });
            // TODO 数据格式转换
            var el_str = "",
                el_two = "";
            _.each(resp[0].list, function (itm) {
                el_str += "" +
                    "\n" +
                    "                                <div class=\"dper-lgd\">\n" +
                    "                                    <span class=\"dper-pct font-18 dper-fnm dper-fit\">" + ((itm.value / $sum) * 100).toFixed(2) + "</span>\n" +
                    "                                    <span class=\"dper-unit co-99ff font-12\">%</span>\n" +
                    "                                </div>" +
                    "";
            });
            _.each(resp[1].list, function (itm) {
                el_two += "" +
                    "\n" +
                    "                                <div class=\"dper-lgd\">\n" +
                    "                                    <span class=\"dper-pct font-18 dper-fnm dper-fit\">" + ((itm.value / $sum2) * 100).toFixed(2) + "</span>\n" +
                    "                                    <span class=\"dper-unit co-99ff font-12\">%</span>\n" +
                    "                                </div>" +
                    "";
            });
            App.getView(dom).querySelectorAll('.dper-legend')[0].innerHTML = el_str;
            App.getView(dom).querySelectorAll('.dper-legend')[1].innerHTML = el_two;
            App.getView(dom).querySelectorAll('.dper-legend')[2].innerHTML = el_str;
            basedata.chart.pie.render(resp[0].list);
            basedata.chart.pie1.render(resp[1].list);
            basedata.chart.pie2.render(resp[0].list);
            pieWrapperFn(dom)
        });
    }
    var pieWrapperFn = function (dom) {
        var $el = $(App.getView(dom).querySelector('.wrapper-box')),
            $child = $el.find('.chart-box'),
            $num = 0,
            $h = $child.eq(0).height(),
            $tmt = null;
        if (!$el.data('num')) $el.data('num', 0);
        $el.on('mouseenter', function () {
            clearInterval($tmt)
        });
        $el.on('mouseleave', function () {
            $tmt = setInterval(function () {
                $num = Number($el.data('num'));
                $num++;
                if ($num === $child.length) {
                    $num = 1;
                    $el.css('top', 0 + 'px');
                }
                $el.animate({ top: -$num * $h + 'px' }, 500);
                $el.data('num', $num);
            }, 5000);
        });
        $tmt = setInterval(function () {
            $num++;
            if ($num === $child.length) {
                $num = 1;
                $el.css('top', 0 + 'px')
            }
            $el.animate({ "top": -$num * $h + 'px' }, 500);
            $el.data('num', $num)
        }, 5000);
    };
    // 执法质量考评
    /*config.interface.demo,*/
    function getDisreSta() {
        basefn("dper-dissta-state", "执法质量考评", config.interface.kss_url8, function (resp, dom) {
            // 模拟数据
            /* resp = [
                 {
                     name: "未落实安全大检查",
                     value: 42
                 },
                 {
                     name: "监控民警履职不到位",
                     value: 41
                 },
                 {
                     name: "押解民警履职不到位",
                     value: 38
                 },
                 {
                     name: "基础建设问题",
                     value: 34
                 },
                 {
                     name: "出所就医问题",
                     value: 31
                 },
                 {
                     name: "医疗卫生问题",
                     value: 28
                 },
                 {
                     name: "重点人管控问题",
                     value: 26
                 },
                 {
                     name: "其他",
                     value: 15
                 }
             ];
 */
            resp = resp.kss;


            var resp_data = {
                indicator: [],
                legend: [],
                name: "全市管户分状态分布情况",
                data: []
            };

            // TODO 数据格式化
            var max = 0,
                sum = 0,
                el_str = "",
                $el = $(App.getView(dom).querySelector('.lg-pie-legend')),
                $swiper = '<div class="swiper-container dper-full-h" id="lgPieSwiper">' +
                    '   <div class="swiper-wrapper dper-full-h"></div>' +
                    '</div>'
                ;
            $el.empty();
            $el.append($swiper);
            for (var i = 0; i < resp.length; i++) {
                var itm = resp[i];
                var itm_va = Number(itm.value);
                if (i === 0) {
                    max = itm_va;
                }
                if (max < itm_va) {
                    max = itm_va;
                }
                sum += itm_va;
            }
            for (var i = 0; i < resp.length; i++) {
                var itm = resp[i];
                var itm_va = Number(itm.value);

                resp_data.indicator.push({
                    name: itm.name,
                    max: max
                });
                resp_data.legend.push(itm.name);
                resp_data.data.push(itm_va);
            }
            renderPieLegendFn($el, sum, resp);
            basedata.chart.radar.render(resp_data);
        });
    }
    var renderPieLegendFn = function (el, sum, resp) {
        var i = 1;
        var $el = el.find('.swiper-wrapper'),
            $child = '<div class="swiper-slide dper-por">' +
                '   <i class="mmp-icon malign-center-left"></i>' +
                '   <span class="mmp-name malign-center-left text-overflow-e">{{name}}</span>' +
                '   <span class="mmp-value malign-center-left text-overflow-e">{{value}}</span>' +
                '   <span class="mmp-deci malign-center-right text-overflow-e">%</span>' +
                '</div>',
            $childone = '<div class="swiper-slide dper-por">' +
                '   <i class="mmp-icon malign-center-left"></i>' +
                '   <span class="mmp-name malign-center-left text-overflow-e fxone">{{name}}</span>' +
                '   <span class="mmp-value malign-center-left text-overflow-e">{{value}}</span>' +
                '   <span class="mmp-deci malign-center-right text-overflow-e">%</span>' +
                '</div>',
            $childtwo = '<div class="swiper-slide dper-por">' +
                '   <i class="mmp-icon malign-center-left"></i>' +
                '   <span class="mmp-name malign-center-left text-overflow-e fxtwo">{{name}}</span>' +
                '   <span class="mmp-value malign-center-left text-overflow-e">{{value}}</span>' +
                '   <span class="mmp-deci malign-center-right text-overflow-e">%</span>' +
                '</div>',
            mySwiper = null,
            $arr = []
            ;
        $el.empty();
        _.each(resp, function (itm) {
            $arr.push({
                name: itm.name,
                value: ((Number(itm.value) / Number(sum)) * 100).toFixed(2)
            })
        })
        _.each($arr, function (itm) {
            if (i == 1) {
                $el.append(rendarTpl(itm, $childone, 'itm'))
            } else if (i == 2) {
                $el.append(rendarTpl(itm, $childtwo, 'itm'))
            } else {
                $el.append(rendarTpl(itm, $child, 'itm'))
            }
            i++;
        })
        mySwiper = new Swiper('#lgPieSwiper', {
            direction: 'vertical',
            slidesPerView: 8
        });
    };
    // 地图数据
    function getMap(number) {
        localStorage.getItem("idjsbhxf")
        var strify = localStorage.getItem("idjsbhxf")
        basefn("dper-map", "上海地图数据", config.interface.kss_dd + "?strify=" + strify, function (resp, dom) {
            // 模拟数据
            resp = [
                {
                    type: 0,
                    list: [
                        {
                            name: "朝阳区",
                            nameAdd: "朝阳区(看)",
                            mmp: "朝阳区看守所",
                            jsbh: 110105111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "朝阳区",
                            nameAdd: "朝阳区(拘)",
                            mmp: "朝阳区拘留所",
                            jsbh: 110105121,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "东城区",
                            nameAdd: "东城区(看)",
                            mmp: "东城区看守所",
                            jsbh: 110101111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "东城区",
                            nameAdd: "东城区(拘)",
                            mmp: "东城区拘留所",
                            jsbh: 110101121,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "西城区",
                            nameAdd: "西城区(看)",
                            mmp: "西城区看守所",
                            jsbh: 110102111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "海淀区",
                            nameAdd: "海淀区(看)",
                            mmp: "海淀区看守所",
                            jsbh: 110108111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "海淀区",
                            nameAdd: "海淀区(拘)",
                            mmp: "海淀区拘留所",
                            // jsbh:
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "丰台区",
                            nameAdd: "丰台区(看)",
                            mmp: "丰台区看守所",
                            jsbh: 110106111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "石景山区",
                            nameAdd: "石景山区(看)",
                            mmp: "石景山区看守所",
                            jsbh: 110107111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "平谷区",
                            nameAdd: "平谷区(看)",
                            mmp: "平谷区看守所",
                            jsbh: 110226111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "密云区",
                            nameAdd: "密云区(看)",
                            mmp: "密云区看守所",
                            jsbh: 110228111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "顺义区",
                            nameAdd: "顺义区(看)",
                            mmp: "顺义区看守所",
                            jsbh: 110113111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "怀柔区",
                            nameAdd: "怀柔区(看)",
                            mmp: "怀柔区看守所",
                            jsbh: 110227111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "通州区",
                            nameAdd: "通州区(看)",
                            mmp: "通州区看守所",
                            jsbh: 110112111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "昌平区",
                            nameAdd: "昌平区(看)",
                            mmp: "昌平区看守所",
                            jsbh: 110221111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "延庆区",
                            nameAdd: "延庆区(看)",
                            mmp: "延庆区看守所",
                            jsbh: 110229111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "大兴区",
                            nameAdd: "大兴区(看)",
                            mmp: "大兴区看守所",
                            jsbh: 110224111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "门头沟区",
                            nameAdd: "门头沟区(看)",
                            mmp: "门头沟区看守所",
                            jsbh: 110109111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "房山区",
                            nameAdd: "房山区(看)",
                            mmp: "房山区看守所",
                            jsbh: 110111111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(5000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "房山区",
                            nameAdd: "房山区(拘)",
                            mmp: "房山区拘留所",
                            jsbh: 110111121,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(5000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        }
                    ]
                },
                {
                    type: 1,
                    list: [
                        {
                            name: "朝阳区",
                            nameAdd: "朝阳区(看)",
                            mmp: "朝阳区看守所",
                            jsbh: 110105111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "朝阳区",
                            nameAdd: "朝阳区(拘)",
                            mmp: "朝阳区拘留所",
                            jsbh: 110105121,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "东城区",
                            nameAdd: "东城区(看)",
                            mmp: "东城区看守所",
                            jsbh: 110101111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "东城区",
                            nameAdd: "东城区(拘)",
                            mmp: "东城区拘留所",
                            jsbh: 110101121,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "西城区",
                            nameAdd: "西城区(看)",
                            mmp: "西城区看守所",
                            jsbh: 110102111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "海淀区",
                            nameAdd: "海淀区(看)",
                            mmp: "海淀区看守所",
                            jsbh: 110108111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "海淀区",
                            nameAdd: "海淀区(拘)",
                            mmp: "海淀区拘留所",
                            // jsbh:
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "丰台区",
                            nameAdd: "丰台区(看)",
                            mmp: "丰台区看守所",
                            jsbh: 110106111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "石景山区",
                            nameAdd: "石景山区(看)",
                            mmp: "石景山区看守所",
                            jsbh: 110107111,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        }
                    ]
                },
                {
                    type: 2,
                    list: [
                        {
                            name: "北京市第一看守所",
                            nameAdd: "北京市第一看守所",
                            mmp: "北京市第一看守所",
                            jsbh: 110000114,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "北京市第二看守所",
                            nameAdd: "北京市第二看守所",
                            mmp: "北京市第二看守所",
                            jsbh: 110000112,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "北京市第三看守所",
                            nameAdd: "北京市第三看守所",
                            mmp: "北京市第三看守所",
                            jsbh: 110000113,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "北京市拘留所",
                            nameAdd: "北京市拘留所",
                            mmp: "北京市拘留所",
                            jsbh: 110000121,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "北京市强制隔离戒毒所",
                            nameAdd: "北京市强制隔离戒毒所",
                            mmp: "北京市强制隔离戒毒所",
                            jsbh: 130000030,
                            // jsbh:
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "北京市收容教育所",
                            nameAdd: "北京市收容教育所",
                            mmp: "北京市收容教育所",
                            jsbh: 120000060,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "北京市监护医疗所",
                            nameAdd: "北京市监护医疗所",
                            mmp: "北京市监护医疗所",
                            jsbh: 110121141,
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        },
                        {
                            name: "北京市强制医疗所",
                            nameAdd: "北京市强制医疗所",
                            mmp: "北京市强制医疗所",
                            // jsbh:
                            // 关押量额定
                            constNumber: 3000,
                            // 实际额定
                            alineNumber: DperStr.Random(3000, 0),
                            // 税收收入
                            sre: (Math.random() * 10000000).toFixed(0),
                            // 收入占比
                            pct_income: (Math.random() * 100).toFixed(2),
                            // 同比增长
                            pct_ud: (Math.random() * 100).toFixed(2),
                            // 增值税
                            add_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 企业所得税
                            cin_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            },
                            // 车辆购置税
                            atpuse_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: false
                            },
                            // 消费税
                            excise_tax: {
                                val: (Math.random() * 1000000).toFixed(0),
                                pct_zb: (Math.random() * 100).toFixed(0),
                                pct_ud: (Math.random() * 100).toFixed(0),
                                isUp: true
                            }
                        }
                    ]
                }
            ];
            var $respData = [],
                resp_data = [],
                $arr = [],
                mapPosition = [
                    {
                        type: 0,
                        list: [
                            {
                                name: "平谷区(看)",
                                jsbh: 110226111,
                                value: [708, 332],
                                kssName:"平谷区看守所"
                            },
                            {
                                name: "密云区(看)",
                                jsbh: 110228111,
                                value: [610, 424],
                                kssName:"密云区看守所"
                            },
                            {
                                name: "顺义区(看)",
                                jsbh: 110113111,
                                value: [538, 316],
                                kssName:"顺义区看守所"
                            },
                            {
                                name: "怀柔区(看)",
                                jsbh: 110227111,
                                value: [456, 408],
                                kssName:"怀柔区看守所"
                            },
                            {
                                name: "通州区(看)",
                                jsbh: 110112111,
                                value: [530, 192],
                                kssName:"通州区看守所"
                            },
                            {
                                name: "朝阳区(看)",
                                jsbh: 110105111,
                                value: [450, 266],
                                kssName:"朝阳区看守所"
                            },
                            {
                                name: "朝阳区(拘)",
                                jsbh: 110105121,
                                value: [430, 270],
                                kssName:"朝阳区拘留所"
                            },
                            {
                                name: "东城区(看)",
                                jsbh: 110101111,
                                value: [418, 234],
                                kssName:"东城区看守所"
                            },
                            {
                                name: "东城区(拘)",
                                jsbh: 110101121,
                                value: [426, 250],
                                kssName:"东城区拘留所"
                            },
                            {
                                name: "西城区(看)",
                                jsbh: 110102111,
                                value: [396, 222],
                                kssName:"西城区看守所"
                            },
                            {
                                name: "海淀区(看)",
                                jsbh: 110108111,
                                value: [340, 292],
                                kssName:"海淀区看守所"
                            },
                            {
                                name: "海淀区(拘)",
                                // jsbh:
                                value: [350, 280],
                                kssName:"海淀区拘留所"
                            },
                            {
                                name: "丰台区(看)",
                                jsbh: 110106111,
                                value: [364, 200],
                                kssName:"丰台区看守所"
                            },
                            {
                                name: "石景山区(看)",
                                jsbh: 110107111,
                                value: [328, 240],
                                kssName:"石景山区看守所"
                            },
                            {
                                name: "昌平区(看)",
                                jsbh: 110221111,
                                value: [365, 355],
                                kssName:"昌平区看守所"
                            },
                            {
                                name: "延庆区(看)",
                                jsbh: 110229111,
                                value: [260, 430],
                                kssName:"延庆区看守所"
                            },
                            {
                                name: "大兴区(看)",
                                jsbh: 110224111,
                                value: [404, 136],
                                kssName:"大兴区看守所"
                            },
                            {
                                name: "门头沟区(看)",
                                jsbh: 110109111,
                                value: [210, 268],
                                kssName:"门头沟区看守所"
                            },
                            {
                                name: "房山区(看)",
                                jsbh: 110111111,
                                value: [238, 156],
                                kssName:"房山区看守所"
                            },
                            {
                                name: "房山区(拘)",
                                jsbh: 110111121,
                                value: [200, 130],
                                kssName:"房山区拘留所"
                            }
                        ]
                    },
                    {
                        type: 1,
                        list: [
                            {
                                name: "朝阳区(看)",
                                jsbh: 110105111,
                                value: [650, 310],
                                kssName:"朝阳区看守所"
                            },
                            {
                                name: "朝阳区(拘)",
                                jsbh: 110105121,
                                value: [630, 360],
                                kssName:"朝阳区拘留所"
                            },
                            {
                                name: "东城区(看)",
                                jsbh: 110101111,
                                value: [532, 257],
                                kssName:"东城区看守所"
                            },
                            {
                                name: "东城区(拘)",
                                jsbh: 110101121,
                                value: [520, 230],
                                kssName:"东城区拘留所"
                            },
                            {
                                name: "西城区(看)",
                                jsbh: 110102111,
                                value: [450, 290],
                                kssName:"西城区看守所"
                            },
                            {
                                name: "海淀区(看)",
                                jsbh: 110108111,
                                value: [340, 400],
                                kssName:"海淀区看守所"
                            },
                            {
                                name: "海淀区(拘)",
                                // jsbh:
                                value: [330, 350],
                                kssName:"海淀区拘留所"
                            },
                            {
                                name: "丰台区(看)",
                                jsbh: 110106111,
                                value: [360, 200],
                                kssName:"丰台区看守所"
                            },
                            {
                                name: "石景山区(看)",
                                jsbh: 110107111,
                                value: [268, 286],
                                kssName:"石景山区看守所"
                            }
                        ]
                    },
                    {
                        type: 2,
                        list: [
                            {
                                name: '北京市第一看守所',
                                jsbh: 110000114,
                                value: [555, 360],
                                kssName:"北京市第一看守所"
                            },
                            {
                                name: '北京市第二看守所',
                                jsbh: 110000112,
                                value: [602, 330],
                                kssName:"北京市第二看守所"
                            },
                            {
                                name: '北京市第三看守所',
                                jsbh: 110000113,
                                value: [547, 190],
                                kssName:"北京市第三看守所"
                            },
                            {
                                name: '北京市拘留所',
                                jsbh: 110000121,
                                value: [482, 160],
                                kssName:"北京市拘留所"
                            },
                            {
                                name: '北京市强制隔离戒毒所',
                                jsbh: 130000030,
                                value: [586, 260],
                                kssName:"北京市强制隔离戒毒所"
                            },
                            {
                                name: '北京市收容教育所',
                                jsbh: 120000060,
                                value: [618, 300],
                                kssName:"北京市收容教育所"
                            },
                            {
                                name: '北京市监护医疗所',
                                jsbh: 110121141,
                                value: [312, 218],
                                kssName:"北京市监护医疗所"
                            },
                            {
                                name: '北京市强制医疗所',
                                // jsbh:
                                value: [648, 440],
                                kssName:"北京市强制医疗所"
                            }
                        ]
                    }
                ],
                $resp = {
                    name: '李维明',
                    number: '87395110'
                }, $something = [],
                $el = $(App.getView('dper-map')),
                $elBtn = $('#starFull'), $faBtn = $('#starMsg');
            _.each(resp, function (itm) {
                if (itm.type === number) {
                    $respData = itm.list
                }
            });
            // 五星点击事件
            starClickFn($resp);
            // TODO 数据格式转换
            resp_data = $respData;
            // console.log("数据转换");
            // console.log(resp_data);
            $.each(mapPosition, function (idx, itm) {
                if (itm.type === number) {
                    $something = itm.list
                }
            });
            if ($something.length === 20) {
                $elBtn.removeClass('a-1');
                $faBtn.removeClass('a-11');
                $elBtn.addClass('a-0');
                $faBtn.addClass('a-00');
                $elBtn.css('opacity', 1);
            } else if ($something.length === 9) {
                $elBtn.removeClass('a-0');
                $faBtn.removeClass('a-00');
                $elBtn.addClass('a-1');
                $faBtn.addClass('a-11');
                $elBtn.css('opacity', 1);
            } else {
                $elBtn.removeClass('a-0');
                $faBtn.removeClass('a-00');
                $elBtn.removeClass('a-1');
                $faBtn.removeClass('a-11');
                $elBtn.css('opacity', 0);
                $faBtn.css('opacity', 0)
            }
            _.each($something, function (item) {
                _.each(resp_data, function (key) {
                    if (key.nameAdd === item.name) {
                        if ((Number(key.alineNumber) / Number(key.constNumber)) <= .7) {
                            $arr.push({
                                name: item.name,
                                value: item.value,
                                jsbh: item.jsbh,
                                color: '#2eff31',
                                nameAdd: key.nameAdd,
                                kssName:item.kssName
                            })
                        } else if ((Number(key.alineNumber) / Number(key.constNumber)) > 1) {
                            $arr.push({
                                name: item.name,
                                value: item.value,
                                color: '#f00',
                                nameAdd: key.nameAdd,
                                jsbh: item.jsbh,
                                kssName:item.kssName
                            })
                        } else {
                            $arr.push({
                                name: item.name,
                                value: item.value,
                                color: '#ffc74b',
                                nameAdd: key.nameAdd,
                                jsbh: item.jsbh,
                                kssName:item.kssName
                            })
                        }
                    }
                })
            });
            // console.log("打印....");
            // console.log($arr);
            basedata.chart.map.render(resp_data, resp_data[0].nameAdd, $arr);
            basedata.chart.map.myChart.on('mouseover', function (el) {
                // console.log(el)

                var e = e || event;
                $("#pop").css({
                    "left": e.zrX + 'px',
                    "top": e.zrY + 'px',
                    'opacity': 1,
                    'z-index': 99
                });
                PopRadarFn(el.seriesName, number);
                //  console.log("获取el");
                // console.log(el.data[2]);
                localStorage.setItem("idjsbhxf", el.data[2]);
                localStorage.setItem("kssName", el.data[3]);
                localStorage.setItem("idjsbhxfname", el.seriesName);
                basedata.chart.map.getArea(resp_data, el.seriesName);
            });
            //地图圆点点击跳转事件
            basedata.chart.map.myChart.on('click', function (el) {
                
                if(el.data[2]==110105121||el.data[2]==110101121||el.data[2]==110111121||el.data[2]==110000121){
                    window.location.href = "/html/jls/pages/appIndex.html";
                }else{
                    window.location.href = "/html/zd/wxjs/pages/appIndex.html";
                }

                
                PopRadarFn(el.seriesName, number);
            });
            $('.lg-mmp-sb').on('mouseenter', function () {
                $("#pop").css({
                    'opacity': 0,
                    'z-index': -1
                });
            })
        });
    }
    var autoSize = function (px) {
        var wi_ = window.innerWidth < 1220 ? 1220 : window.innerWidth;
        var times = wi_ / 1920;
        return Number(px) * times;
    };
    // 红点点击
    var starClickFn = function (resp) {
        var $el = $('#starFull'),
            $fa = $('#starMsg'),
            $child = '<p class="text-overflow-e">总队值班领导：{{name}}</p>' +
                '<p class="text-overflow-e">联系电话：{{number}}</p>';
        $fa.empty();
        $fa.append(rendarTpl(resp, $child, 'resp'));
        $el.data('off', true);
        $el.off('click');
        $el.on('click', function () {
            console.log($(this));
            if ($(this).data('off')) {
                $fa.css({
                    'zIndex': 999,
                    'opacity': 1
                });
                $el.data('off', false)
            } else {
                $fa.css({
                    'zIndex': -1,
                    'opacity': 0
                });
                $el.data('off', true)
            }
        });
    };
    // 关押量
    function getExpTheYear() {
        dperAjax("关押量", config.interface.zyrs_url, {
            successfn: function (resp) {
                // console.log("ajaxresp"+resp.data);
                // 模拟数据、
console.info("ccccccc=="+resp);
                console.info("aaaaaaa=="+resp.data);
                resp = {

                    val: resp.data,
                    ud: (Math.random() * 10).toFixed(2)
                };
                var $el = $('#numAnimate'),
                    resp_data = {
                        val: 0,
                        ud: 0
                    },
                    $arr = [],
                    $child = '<div class="dper-num bg-img-full fl">\n' +
                        ' <span class="malign-center-text no align-center dper-fwr font-40 co-white">{{value}}</span>\n' +
                        ' </div>'
                    ;
                $el.empty();
                // TODO 数据格式化
                resp_data.val = Number(resp.val);

                if (resp_data.val > 10000000) {
                    console.warn("关押量溢出, " + resp_data.val);
                    resp_data.val = 9999999;
                }
                _.each(resp_data.val.toString().split(''), function (itm) {
                    $arr.push({
                        value: itm
                    })
                });
                _.each($arr, function (itm) {
                    $el.append(rendarTpl(itm, $child, 'itm'))
                });
                $('#mmpSb').css({
                    'width': (144 + (($el.find('.dper-num').length * 44) + 12 * ($el.find('.dper-num').length - 1)) + $('#mmpSb').find('.dper-unit').width()) / $('#mmpSb').parent().width() * 100 + '%',
                    'marginLeft': 'auto',
                    'marginRight': 'auto'
                })
                $('#mmpSb').find('.dper-bnzs').css({
                    'width': (120 / $('#mmpSb').width()) * 100 + '%',
                    "marginRight": (24 / $('#mmpSb').width()) * 100 + '%'
                });
                $('#mmpSb').find('.dper-unit').css('width', (58 / $('#mmpSb').width()) * 100 + '%');
                $el.css({
                    'width': ((($el.find('.dper-num').length * 44) + 12 * ($el.find('.dper-num').length - 1)) / $el.parent().width()) * 100 + '%',
                    'marginLeft': 'auto',
                    'marginRight': 'auto'
                });
                _.each($el.find('.dper-num'), function (itm, idx) {
                    $(itm).css({
                        'width': (44 / $el.width()) * 100 + '%',
                        'marginLeft': (12 / $el.width()) * 100 + '%',
                        'marginRight': 0
                    });
                    if (idx === 0) {
                        $(itm).css('marginLeft', 0);
                    }
                })
            }
        });
    }
});
