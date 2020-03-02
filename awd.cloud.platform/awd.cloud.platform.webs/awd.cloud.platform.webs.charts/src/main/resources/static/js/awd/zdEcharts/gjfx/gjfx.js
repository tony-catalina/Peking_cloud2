define("awd/zdEcharts/gjfx/gjfx",[
    "../../../awd/zd_echarts"
],
function (require) {
    var  echarts=require("../../../awd/zd_echarts");
    var initview = function () {
        echarts.gjfxChar("gjfx","","国籍分析");
        echarts.jlsGjfxChar("jlsGjfx","","拘留所国籍分析");
    }
    initview();
})