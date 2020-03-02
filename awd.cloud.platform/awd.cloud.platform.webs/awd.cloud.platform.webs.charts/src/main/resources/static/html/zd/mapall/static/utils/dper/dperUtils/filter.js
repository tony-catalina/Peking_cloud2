define(function (require, exports, module) {
    // 过滤器 空字符
    var filter_text = function (txt) {
        return (txt == "" || typeof txt === "undefined" || txt == null) ? '--' : txt;
    }
    // 过滤器 金额
    var filter_my = function (my, point, unit) {
        !unit && (unit = '')
        if (my == 0) {
            return my + unit;
        } else {
            return my && (my = numFmtCmapo(my, point) + unit);
        }
    }
    module.exports = {
        filter_text: filter_text,
        filter_my: filter_my
    }
});