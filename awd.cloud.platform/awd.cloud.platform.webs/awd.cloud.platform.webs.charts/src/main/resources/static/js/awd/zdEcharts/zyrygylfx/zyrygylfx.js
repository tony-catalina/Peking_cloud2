define("awd/zdEcharts/zyrygylfx/zyrygylfx",[
        "../../../awd/zd_echarts"
    ],
    function (require) {
        var  echarts=require("../../zd_echarts");
        var initview = function () {
            echarts.zyrygylfxChar("zyrygylfx","","在押人员关押量分析");
            echarts.jlsZyrygylfxChar("jlsZyrygylfx","","在押人员关押量分析")
        }
        initview();
    })