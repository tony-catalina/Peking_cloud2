define("awd/zdEcharts/yzjbryfx/yzjbryfx",[
    "../../../awd/zd_echarts"
],
function (require) {
    var  echarts=require("../../../awd/zd_echarts");
    var initview = function () {
        echarts.yzjbryfxChar("yzjbryfx","","严重疾病人员分析");
        echarts.jlsYzjbryfxChar("jlsYzjbryfx","","拘留所严重疾病人员分析");
    }
    initview();
})