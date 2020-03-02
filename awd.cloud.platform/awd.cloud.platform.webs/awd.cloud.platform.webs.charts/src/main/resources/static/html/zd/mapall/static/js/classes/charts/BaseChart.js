define(function (require, exports, module) {
    var BaseChart = function (dom) {
        this.dom = dom;
        this.myChart = null;
        this.autoSize = function (px) {
            var wi_ = window.innerWidth < 1220?1220:window.innerWidth;
            var times = wi_ / 1920;
            return Number(px) * times;
        }
    }
    module.exports = BaseChart;
});