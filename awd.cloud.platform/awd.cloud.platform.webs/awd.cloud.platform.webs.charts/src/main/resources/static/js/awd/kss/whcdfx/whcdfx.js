define("awd/kss/whcdfx/whcdfx",[
        "../../../awd/zdy_echarts"
    ],
    function (require) {
        var  echarts=require("../../zdy_echarts");
        var initview = function () {
            echarts.whcdfxChar("whcdfx","","文化程度分析");
        }
        initview();
    })