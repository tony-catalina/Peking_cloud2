define("awd/zdEcharts/whcdfx/whcdfx",[
        "../../../awd/zd_echarts"
    ],
    function (require) {
        var  echarts=require("../../zd_echarts");
        var initview = function () {
            echarts.whcdfxChar("whcdfx","","文化程度分析");
            echarts.jlsWhcdfxChar("jlsWhcdfx","","拘留所文化程度分析");
        }
        initview();
    })