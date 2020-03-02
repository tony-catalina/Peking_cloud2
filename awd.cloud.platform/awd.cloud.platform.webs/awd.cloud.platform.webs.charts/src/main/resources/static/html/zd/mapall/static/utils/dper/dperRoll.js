/**
 * 列表滚动
 * @param dom
 * @param opt
 */
var AutoRoll = function (dom, opt) {
    this.opt = {
        // 滚动时间间隔
        time: 2,
        // 元素底部外补白
        mb: 0,
        // 滚动动画间隔 s
        time_amt: .5,
        // 滚动动效果
        play_: 'linear',
        // 自动计算是否滚动?
        isAutoRoll: false,
        // 如果 isAutoRoll 为false 则此变量生效
        minRow: 7
    };
    // 容器
    this.dom = $('#' + dom);
    // 定时器
    this.inter = null;

    // 配置整合
    for (var item in opt) {
        if (this.opt.hasOwnProperty(item)) {
            this.opt[item] = opt[item];
        }
    }
    this.init();
}
AutoRoll.prototype = {
    constructor: AutoRoll,
    init: function () {
        var _self = this;
        // 添加动画类到目标视图
        var sty_ = document.createElement('style');
        sty_.setAttribute("type", "text/css");
        sty_.innerText = ".dper-roll-amt{" +
            "transition: all " + this.opt.time_amt + "s " + this.opt.play_ + ";" +
            "transition: all " + this.opt.time_amt + "s " + this.opt.play_ + ";" +
            "transition: all " + this.opt.time_amt + "s " + this.opt.play_ + ";" +
            "transition: all " + this.opt.time_amt + "s " + this.opt.play_ + ";" +
            "}";
        document.head.appendChild(sty_)
        // 触摸事件
        this.dom.on("mouseover", function () {
            _self.stopScroll();
        }).on("mouseout", function () {
            _self.roll();
        })
        this.roll();
    },
    roll: function () {
        var _self = this;
        var roll = false;
        if(this.dom.children().length > 0){
            if(this.opt.isAutoRoll){
                var itemHeight = this.dom.children()[0].offsetHeight + this.opt.mb;
                var hei_dom = this.dom.height();
                var hei_rows = this.dom.children().length * itemHeight;
                if(hei_rows > hei_dom && (hei_rows - hei_dom) > itemHeight){
                    roll = true;
                }
            }else{
                if(this.dom.children().length > this.opt.minRow){
                    roll = true;
                }
            }
        }
        if(roll){
            var itemHeight = this.dom.children()[0].offsetHeight + this.opt.mb;
            _self.dom.removeClass('dper-roll-amt')
            _self.inter = setInterval(function () {
                _self.dom.children(':lt(1)').clone().appendTo(_self.dom);
                _self.dom.addClass('dper-roll-amt');
                _self.dom.css('transform', 'translateY(-' + itemHeight + 'px)')
                setTimeout(function () {
                    _self.dom.removeClass('dper-roll-amt');
                    _self.dom.children(':lt(1)').remove()
                    _self.dom.css('transform', 'translateY(0)')
                }, 500);
            }, _self.opt.time * 1000);
        }
    },
    /**
     * 停止滚动
     */
    stopScroll: function () {
        clearInterval(this.inter);
    }
}