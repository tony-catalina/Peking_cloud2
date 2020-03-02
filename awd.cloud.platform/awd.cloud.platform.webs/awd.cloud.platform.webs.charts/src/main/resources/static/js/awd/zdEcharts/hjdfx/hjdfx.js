define("awd/zdEcharts/hjdfx/hjdfx",[
        "../../../awd/zd_echarts"
    ],
    function (require) {
        var  echarts=require("../../zd_echarts");
        var initview = function () {
            echarts.hjdChar("hjdfx","","户籍地分析");
            echarts.jlsHjdChar("jlsHjdfx","","拘留所户籍地分析");
        }
        initview();
    }) 