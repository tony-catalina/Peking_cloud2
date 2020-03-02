define("awd/zdEcharts/mjslfx/mjslfx",[
    "../../../awd/zd_echarts"
],
function (require) {
    var  echarts=require("../../../awd/zd_echarts");
    var initview = function () {
        echarts.mjslfxChar("mjslfx","/","民警数量分析");
    }
    initview();
})