define("awd/kss/hjdfx/hjdfx",[
        "../../../awd/zdy_echarts"
    ],
    function (require) {
        var  echarts=require("../../zdy_echarts");
        var initview = function () {
            echarts.hjdChar("hjdfx","","户籍地分析");
        }
        initview();
    })