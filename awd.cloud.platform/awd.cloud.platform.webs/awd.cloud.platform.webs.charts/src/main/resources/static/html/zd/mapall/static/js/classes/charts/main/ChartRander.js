define(function (require, exports, module) {
    // 引入echarts
    var echarts = require("echarts");
    var BaseChart = require("classes/charts/BaseChart");

    var ChartRander = function (dom) {
        BaseChart.call(this, dom);
        this.option = {
            grid: {
                left: '0',
                right: '0',
                bottom: '0',
                top: '0',
                containLabel: true
            },
            radar: [
                {
                    indicator: [],
                    name: {
                        fontSize: this.autoSize(12),
                        padding: -this.autoSize(6),
                        color: "#fff"
                    },
                    center: ['78%', '50%'],
                    radius: '50%',
                    splitNumber: 4,
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        show: false
                    },
                    splitArea: {
                        show: true,
                        areaStyle: {
                            color: [
                                'rgba(75, 219, 255, .5)',
                                'rgba(75, 219, 255, .4)',
                                'rgba(75, 219, 255, .3)',
                                'rgba(75, 219, 255, .2)'
                            ]
                        }
                    },
                }
            ],
            series: [
                {
                    name: '基础库',
                    type: 'radar',
                    lineStyle: {
                        opacity: 0
                    },
                    areaStyle: {
                        color: 'rgba(75,238,162,.8)',
                        shadowColor: '#50eba8',
                        shadowBlur: 70,
                    },
                    symbolSize: 0,
                    data: []
                }
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
                    color: '#ff6d00'
                }, {
                    offset: 1,
                    color: '#ff027f'
                }])
            ]
        }
        this.init()
    }
    ChartRander.prototype = {
        constructor: ChartRander,
        init: function () {
            this.myChart = echarts.init(this.dom);
        },
        /**
         * 图表渲染
         * @param data
         *
         *   indicator: [],
             name: "",
             data: [
             {
                 name: '',
                 value: 0
             }
             ]
         *
         */
        render: function (data) {
            if (this.myChart === null) {
                this.myChart = echarts.init(this.dom);
            }

            // 配置修改
            this.option.radar[0].name.formatter = function (itm) {
                if (itm.length>4){
                    return itm.substr(0,4) + " \n " + itm.substr(4,itm.length)
                }
                else if(itm.length>8){
                    return itm.substr(0,6) + " \n " + itm.substr(6,itm.length)
                }
                else {
                    return itm
                }
                // + "  " + data.data[data.legend.indexOf(itm)];
            }

            // 数据装载
            this.option.radar[0].indicator = data.indicator;
            this.option.series[0].data = [
                {
                    name: data.name,
                    value: data.data
                }
            ];
            this.myChart.setOption(this.option);

        },
        resize: function () {
            this.myChart.setOption(this.option);
            this.myChart.resize();
        }
    }
    module.exports = ChartRander;
});
