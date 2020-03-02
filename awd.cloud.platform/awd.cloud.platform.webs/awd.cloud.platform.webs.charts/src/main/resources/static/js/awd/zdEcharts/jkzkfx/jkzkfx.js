define("awd/zdEcharts/jkzkfx/jkzkfx",[
    "../../../awd/zd_echarts"
],
function (require) {
    var  echarts=require("../../zd_echarts");
    var initview = function () {
        echarts.jkzkChar("jkzkfx","/","健康状况分析");
        echarts.jlsJkzkChar("jlsJkzkfx","/","拘留所健康状况分析");
    }
    initview();
});










