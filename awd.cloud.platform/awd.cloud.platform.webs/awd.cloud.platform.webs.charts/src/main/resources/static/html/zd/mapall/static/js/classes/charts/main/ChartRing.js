define(function (require, exports, module) {
    // 引入echarts
    var echarts = require("echarts");
    var BaseChart = require("classes/charts/BaseChart");
    var ChartRing = function (dom) {
        BaseChart.call(this, dom);
        this.option = {
            grid: {
                left: '0',
                right: '0',
                bottom: '0',
                top: '0',
                containLabel: true
            },
            series: [
                {
                    type: 'pie',
                    hoverAnimation: false,
                    clockwise: false,
                    radius: ['47%', '82%'],
                    avoidLabelOverlap: true,
                    labelLine: {
                        show: false,
                        length: 40,
                        length2: 20
                    },
                    itemStyle: {},
                    label: {
                        show: false
                    },
                    z: 3,
                    data: [1,3,4,5,6]
                },
                // 辅助圆环 外
                {
                    type: 'pie',
                    hoverAnimation: false,
                    clockwise: false,
                    radius: ['91%', '92%'],
                    labelLine: {show: false},
                    itemStyle: {
                        color: '#0851a5'
                    },
                    data: [100]
                },
                // 辅助圆环 内
                {
                    type: 'pie',
                    hoverAnimation: false,
                    clockwise: false,
                    radius: ['37%', '38%'],
                    labelLine: {show: false},
                    itemStyle: {
                        color: '#0851a5'
                    },
                    data: [100]
                }
            ],
            animationEasing: "elasticOut",
            color: [
                '#ef6e18',
                '#fcc204',
                '#5900bc',
                '#ff027f',
                '#0336ff',
                '#ff66cc',
                '#e822b6',
                '#58ffb9',
                '#800000',
                '#BC8F8F',
                '#8B4513',
                '#556B2F'
            ],
            bclr: [
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#ef6e18'
                }, {
                    offset: 1,
                    color: '#fcc204'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#ffffba'
                }, {
                    offset: 1,
                    color: '#cccc00'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#5900bc'
                }, {
                    offset: 1,
                    color: '#9f4efb'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#ff6d00'
                }, {
                    offset: 1,
                    color: '#ff027f'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#0336ff'
                }, {
                    offset: 1,
                    color: '#01b4ff'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#ff99cc'
                }, {
                    offset: 1,
                    color: '#ff66cc'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#ff56ff'
                }, {
                    offset: 1,
                    color: '#e822b6'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#00dcb6'
                }, {
                    offset: 1,
                    color: '#58ffb9'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#000000'
                }, {
                    offset: 1,
                    color: '#696969'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#800000'
                }, {
                    offset: 1,
                    color: '#8B0000'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#CD5C5C'
                }, {
                    offset: 1,
                    color: '#BC8F8F'
                }]),
                new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                    offset: 0,
                    color: '#E9967A'
                }, {
                    offset: 1,
                    color: '#A0522D'
                }])

            ]
        }
        this.init()
    }
    ChartRing.prototype = {
        constructor: ChartRing,
        init: function () {
            this.myChart = echarts.init(this.dom);
        },
        /**
         * 图表渲染
         * @param data
         * [
         *  {
         *      name: "",
         *      value: 0
         *  }
         * ]
         */
        render: function (data) {
            var _self = this;
            if (this.myChart === null) {
                this.myChart = echarts.init(this.dom);
            }
            // 配置数据清空
            this.option.series[0].data = [];
            // 数据重载
            data.forEach(function (itm, idx) {
                itm.itemStyle = {
                    color: _self.option.bclr[idx%(_self.option.bclr.length)]
                }
                _self.option.series[0].data.push(itm)
            });
            this.myChart.setOption(this.option);
        }
    }
    module.exports = ChartRing;

});
