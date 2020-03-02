define(function (require, exports, module) {
    // 引入echarts
    var echarts = require("echarts");
    var BaseChart = require("classes/charts/BaseChart");

    var ChartRanderPop = function (dom) {
        BaseChart.call(this, dom);
        this.option = {
            legend: {
                bottom: '0',
                itemWidth: 16,
                itemHeight: 2,
                textStyle: {
                    color: '#27bcf1',
                },
                data: [
                    {
                        name: '均值',
                        icon: 'rect'
                    },
                    {
                        name: '浦东',
                        icon: 'rect'
                    }
                ]
            },
            tooltip: {
                show: true,
                confine: true
            },
            radar: {
                radius: "50%",
                name: {
                    textStyle: {
                        color: '#27bcf1',
                    }
                },
                nameGap: 5,
                indicator: [
                    {name: '民警管理', max: 300},
                    {name: '客观因素', max: 300},
                    {name: '在押人员', max: 300},
                    {name: '基础设施', max: 300},
                    {name: '安防设施', max: 300}
                ],
                axisLine: {
                    lineStyle: {
                        color: "#072571"
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: "#072571"
                    }
                },
                splitArea: {
                    areaStyle: {
                        color: 'transparent'
                    }
                }
            },
            series: {
                name: '上海市',
                type: 'radar',
                // areaStyle: {normal: {}},
                itemStyle: {
                    opacity: 0
                },
                color: ['#27bcf1','#4b13a1'],
                data: [
                    {
                        value: [123, 211, 123, 221, 112],
                        name: '均值',
                        lineStyle: {
                            color: "#24afe6"
                        }
                    },
                    {
                        value: [156, 234, 145, 112, 123],
                        name: '浦东',
                        lineStyle: {
                            color: "#4b13a1"
                        },
                        areaStyle: {
                            color: 'rgba(17,39,146,.6)'
                        }
                    }
                ]
            }
        }
        this.init()
    }
    ChartRanderPop.prototype = {
        constructor: ChartRanderPop,
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
            $.each(this.option.series.data,function(idx,itm){
                itm.value = [];
            });
            this.option.series.data[1].name = '';
            this.option.legend.data[1].name = '';
            // 数据装载
            this.option.series.data[0].value = data.value1;
            this.option.series.data[1].value = data.value2;
            this.option.series.data[1].name = data.legend;
            this.option.legend.data[1].name = data.legend;

            this.resize();
            this.Getpop(data);
        },
        resize: function () {
            this.myChart.setOption(this.option);
            this.myChart.resize();
        },
        Getpop: function(data){
            var header = $("#pop .header");
            header.text(data.mmp);
            var cont = $("#pop .post-data");
            cont.eq(0).text(data.per1);
            cont.eq(1).text(data.per2);
            cont.eq(2).text(data.personName);
            cont.eq(3).text(data.phone);
            cont.eq(4).text(data.site);
            $("#pop").addClass("active-pop");
        }
    };
    module.exports = ChartRanderPop;
});
