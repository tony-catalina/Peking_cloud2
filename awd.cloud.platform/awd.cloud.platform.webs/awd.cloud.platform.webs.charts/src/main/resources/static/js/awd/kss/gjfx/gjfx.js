define("awd/kss/gjfx/gjfx",[
        "../../../awd/echarts"
    ],
    function (require) {
        var  echarts=require("../../../awd/echarts");
        var initview = function () {
            echarts.gjfxChar("gjfx","","国籍分析");
        }
        initview();
    })