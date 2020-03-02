define("awd/zdEcharts/nlfx/nlfx",[
        "../../../awd/zd_echarts"
    ],
    function (require) {
        var  echarts=require("../../zd_echarts");
        var initview = function () {
            echarts.nlfxChar("nlfx","/","年龄分析");
            echarts.jlsNlfxChar("jlsNlfx","/","年龄分析");
        }
        initview();
    });










