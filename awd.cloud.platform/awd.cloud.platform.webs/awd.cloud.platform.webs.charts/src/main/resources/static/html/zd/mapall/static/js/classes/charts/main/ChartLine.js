define(function (require, exports, module) {
    // 引入echarts
    var echarts = require("echarts");
    var BaseChart = require("classes/charts/BaseChart");

    var ChartLine = function (dom) {
        BaseChart.call(this, dom);
        this.option = {
            title:{
                text: '123',
                top: '2%',
                left: "center",
                textStyle: {
                    color: '#b4ecff',
                    fontSize: this.autoSize(16)
                }
            },
            legend: {

            },
            grid: {
                top: '26%',
                bottom: '2%',
                left: '4%',
                right: '4%',
                containLabel: true
            },
            xAxis: {
                boundaryGap: false,
                axisLine: {
                    lineStyle: {
                        color: '#cecfcf'
                    }
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: 0,
                    rotate: 45,
                    color: '#fff',
                    fontSize: this.autoSize(12)
                },
                splitLine: {
                    show: false,
                    lineStyle: {
                        color: 'cecfcf'
                    }
                },
                axisPointer: {
                    show: false
                },
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月']
            },
            yAxis: {
                name: "(人)",
                nameTextStyle: {
                    color: '#fff',
                    fontSize: this.autoSize(12),
                    padding: -this.autoSize(8)
                },
                axisLine: {
                    lineStyle: {
                        color: '#878bac'
                    }
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    interval: 0,
                    color: '#fff',
                    fontSize: this.autoSize(12)
                },
                splitLine: {
                    lineStyle: {
                        color: '#333a71'
                    }
                },
                axisPointer: {
                    show: false
                }
            },
            series: [
                {
                    type: 'line',
                    name: '监测趋势图',
                    showAllSymbol: true,
                    clipOverflow: false,
                    smooth: true,
                    symbolSize: 8,
                    symbol: "circle",
                    z: 4,
                    label: {
                        show: true,
                        position: "top",
                        rich: {
                            a: {
                                backgroundColor: '#000',
                                padding: this.autoSize(4),
                                borderRadius: this.autoSize(6),
                                color: '#1ee2ff',
                            },
                            b: {}
                        }
                    },
                    itemStyle: {
                        color: '#4bddff',
                        borderWidth: 2
                    },
                    lineStyle: {
                        color: '#4bddff',
                        width: 2
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#4bddff'
                        }, {
                            offset: 1,
                            color: 'rgba(0,0,0, 0)'
                        }])
                    },
                    data: []
                }

            ],
            animationEasingUpdate: "circularInOut"
        };
        this.init()
    }
    ChartLine.prototype = {
        constructor: ChartLine,
        init: function () {
            this.myChart = echarts.init(this.dom);
        },
        /**
         * 图表渲染
         * @param data
         *
         * {
         *      max: 0,
         *      min: 0,
         *      xAxis: [],
         *      data: [
         *          {
         *              name: "",
         *              value: 0
         *          }
         *      ]
         * }
         *
         */
        render: function (data) {
            // console.log(data)
            var _self = this;
            if (this.myChart === null) {
                this.myChart = echarts.init(this.dom);
            }

            // 配置清空
            this.option.title.text = '';
            this.option.xAxis.data = [];
            this.option.series[0].data = [];
            this.option.title.text= data.name;

            // 配置修改
            _.each(data.list[0].list,function(itm){
                // console.log(data.list[0].list)
                // console.log(itm)
                _self.option.xAxis.data.push(itm.name);
                _self.option.series[0].data.push({
                    name: itm.name,
                    value: itm.value
                })
            })

            // _.dianji(data.list[0].list,function(itm){
            //     console.log(data.list[0].list[0])
            //     // console.log(itm)
            //     _self.option.xAxis.data.push(itm.name);
            //     // console.log(itm.name)
            //     _self.option.series[0].data.push({
            //         name: itm.name,
            //         value: itm.value
            //     })
            //     // console.log(name)
            //     // console.log(value)
            // })
            // 数据装载

            this.myChart.setOption(this.option)
        }
    }
    module.exports = ChartLine;
});
