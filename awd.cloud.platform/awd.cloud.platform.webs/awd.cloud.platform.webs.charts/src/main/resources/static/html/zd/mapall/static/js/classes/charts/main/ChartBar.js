define(function (require, exports, module) {
    // 引入echarts
    var echarts = require("echarts");
    var BaseChart = require("classes/charts/BaseChart");

    var ChartBar = function (dom) {
        BaseChart.call(this, dom);
        this.option = {
            grid: {
                left: '0%',
                right: '5%',
                top: '15%',
                bottom: '0%',
                containLabel: true
            },
            xAxis: {
                axisLine: {
                    lineStyle: {
                        color: '#878bad',
                        width: 2
                    }
                },
                axisTick: {show: false},
                axisLabel: {
                    interval: 0,
                    fontSize: this.autoSize(12),
                    color: '#fff',
                    rotate: 45
                },
                splitLine: {show: false},
                data: []
            },
            yAxis: {
                name: "(户)",
                nameTextStyle: {
                    color: '#fff',
                    fontSize: this.autoSize(12),
                    padding: -10
                },
                axisLine: {
                    lineStyle: {
                        color: '#878bad',
                        width: 2
                    }
                },
                axisTick: {show: false},
                axisLabel: {
                    fontSize: this.autoSize(12),
                    color: '#fff'
                },
                splitLine: {
                    lineStyle: {
                        color: ['#333a73'],
                        width: 1,
                        shadowColor: '#333a73',
                        shadowBlur: this.autoSize(10)
                    }
                }
            },
            series: [
                {
                    type: 'bar',
                    barWidth: '40%',
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
                    data: []
                }
            ],
            color: [
                new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: '#fcc104'
                }, {
                    offset: 1,
                    color: '#ef7018'
                }])
            ],
            animationDelay: function (idx) {
                return idx * 150;
            },
            animationDurationUpdate: function (idx) {
                return idx * 150;
            }
        };
        this.init()
    }
    ChartBar.prototype = {
        constructor: ChartBar,
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
            var _self = this;
            if (this.myChart === null) {
                this.myChart = echarts.init(this.dom);
            }

            // 数据配置清空
            this.option.xAxis.data = [];
            this.option.series[0].data = [];

            // 配置修改
            this.option.series[0].label.formatter = function (itm) {
                var rel_ = "";
                if(itm.value == data.max){
                    rel_ = itm.value;
                } else if(itm.value == data.min){
                    rel_ = itm.value;
                }
                if(rel_ != ""){
                    rel_ = "{a|" + rel_ + "}";
                }else {
                    rel_ = "{b|" + rel_ + "}";
                }
                return rel_;
            };

            // 数据装载
            this.option.xAxis.data = data.xAxis;
            _.each(data.data,function(itm){
                _self.option.series[0].data.push({
                    name:itm.name,
                    value: itm.value,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                offset: 0,
                                color: itm.col0
                            }, {
                                offset: 1,
                                color:  itm.col1
                            }])
                    }
                })
            });

            // this.myChart.clear();
            this.myChart.setOption(this.option)
        }
    }
    module.exports = ChartBar;
});
