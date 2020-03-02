define("awd/zdEcharts/sffx/sffx",[
    "../../../awd/zd_echarts"
],
function (require) {
    var  echarts=require("../../zd_echarts");
    var initview = function () {
        echarts.sffxChar("sffx","/","身份分析");
        echarts.jlsSffxChar("jlsSffx","/","拘留所身份分析");
    }
    initview();
});










