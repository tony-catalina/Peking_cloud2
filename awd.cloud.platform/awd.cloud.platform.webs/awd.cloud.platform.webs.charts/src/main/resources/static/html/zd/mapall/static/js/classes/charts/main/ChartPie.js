define(function (require, exports, module) {
    // 引入echarts
    var echarts = require("echarts");
    var BaseChart = require("classes/charts/BaseChart");

    var ChartPie = function (dom) {
        BaseChart.call(this, dom);
        this.option = {
            title: {
                text: "案由种类",
                left: '66%',
                top: 'center',
                textStyle: {
                    color: '#99ccff',
                    fontSize: this.autoSize(16)
                }
            },
            grid: {
                left: '0',
                right: '0',
                bottom: '0',
                top: '0',
                containLabel: true
            },
            legend: {
                left: '5%',
                top: 'center',
                orient: 'vertical',
                itemWidth: this.autoSize(19),
                itemHeight: this.autoSize(11),
                itemGap: this.autoSize(12),
                textStyle: {
                    color: '#fff',
                    fontSize: this.autoSize(12)
                },
                data: []
            },
            tooltip: {
                formatter: '{b}: {c}人 <br/> 占比：{d}%'
            },
            series: [
                {
                    type: 'pie',
                    center: ['74%', '50%'],
                    radius: ['55%', '85%'],
                    startAngle: 0,
                    label: {show: false},
                    labelLine: {show: false},
                    itemStyle: {},
                    z: 3,
                    data: []
                },
            ],
            color: [
                new echarts.graphic.LinearGradient(1, 1, 0, 0, [{
                    offset: 0,
                    color: '#ef6e18'
                }, {
                    offset: 1,
                    color: '#fcc204'
                }]),
                new echarts.graphic.LinearGradient(1, 1, 0, 0, [{
                    offset: 0,
                    color: '#ff33ff'
                }, {
                    offset: 1,
                    color: '#ff33cc'
                }]),
                new echarts.graphic.LinearGradient(1, 1, 0, 0, [{
                    offset: 0,
                    color: '#00dcb6'
                }, {
                    offset: 1,
                    color: '#58ffb9'
                }]),
                new echarts.graphic.LinearGradient(1, 1, 0, 0, [{
                    offset: 0,
                    color: '#0336ff'
                }, {
                    offset: 1,
                    color: '#01b4ff'
                }]),
                new echarts.graphic.LinearGradient(1, 1, 0, 0, [{
                    offset: 0,
                    color: '#ff8f9e'
                }, {
                    offset: 1,
                    color: '#ff2b81'
                }])
            ]
        }
        this.init()
    }
    ChartPie.prototype = {
        constructor: ChartPie,
        init: function () {
            this.myChart = echarts.init(this.dom);
        },
        /**
         * 图表渲染
         * @param data
         *
         * {
         *  legend: [],
         *  data:
         * [
         *  {
         *      name: "",
         *      value: ""
         *  }
         * ]
         * }
         *
         */
        render: function (data) {
            if (this.myChart === null) {
                this.myChart = echarts.init(this.dom);
            }
            var _self = this
            // 数据装载
            this.option.legend.data = [];
            this.option.series[0].data = [];
            _.each(data,function(itm){
                _self.option.legend.data.push({
                    name: itm.name
                })
                _self.option.series[0].data.push({
                    name: itm.name,
                    value: itm.value
                })
            })
            this.myChart.setOption(this.option);

        },
        resize: function () {
            this.myChart.setOption(this.option);
            this.myChart.resize();
        }
    }
    module.exports = ChartPie;
});
