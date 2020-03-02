define(function (require, exports, module) {
    // 引入echarts
    var echarts = require("echarts");
    var BaseChart = require("classes/charts/BaseChart");
    var config = require("app_config");
    var dperAjax = require("dperAjax");
    var fillter = require("dperUtils/filter");

    var ChartMap = function (dom) {
        BaseChart.call(this, dom);
        this.option = {
            grid: {
                left: 0,
                bottom: 0,
                right: 0,
                top: 0,
                containLabel: false
            },
            xAxis: {
                type: 'value',
                min: 0,
                max: 860,
                axisLine: {show: false},
                axisTick: {show: false},
                axisLabel: {show: false},
                splitLine: {show: false}
            },
            yAxis: {
                type: 'value',
                min: 0,
                max: 647,
                axisLine: {show: false},
                axisTick: {show: false},
                axisLabel: {show: false},
                splitLine: {show: false}
            },
            series: [
                {
                    type: 'effectScatter',
                    symbolSize: 15,
                    data: [],
                    rippleEffect: {
                        period: 3,
                        scale: 4,
                        brushType: 'fill'
                    }
                }
            ],
            // animationEasing: 'quadraticOut',
            // animationEasingUpdate: 'quadraticOut',
            // color: [
            //     '#90b0ff',
            //     '#24fcff',
            // ]
        };
        this.init();
        // 定时器
        this.inter = null;
        // 地图对应数据
        this.map_da = [];
    };
    ChartMap.prototype = {
        constructor: ChartMap,
        init: function () {
            this.myChart = echarts.init(this.dom);
        },
        /**
         * 图表渲染
         * @param data
         */
        render: function (data, name, arr) {
            if (this.myChart === null) {
                this.myChart = echarts.init(this.dom);
            }
            var _self = this;
            this.map_da = [];
            this.map_da = data;
            _self.option.series = [];
            _.each(arr, function (itm,idx) {
                _self.option.series.push({
                    name: itm.name,
                    type: 'effectScatter',
                    symbolSize: 11,
                    data: [[itm.value[0], itm.value[1]], itm.name],
                    rippleEffect: {
                        period: 3,
                        scale: 4,
                        brushType: 'fill'
                    },
                    label: {
                        show: true,
                        position: 'right',
                        color: "#fff",
                        padding: _self.autoSize(10),
                        fontSize: _self.autoSize(16),
                        formatter: function () {
                            return itm.name
                        }
                    },
                    itemStyle: {
                        color: itm.color
                    }
                });
            });
            console.info(_self.option.series)
            // // 详情展示
            _self.getArea(name);
            _self.myChart.setOption(_self.option);

        },
        // 对应数据展示
        getArea: function (areaId) {
            var dom = "dper-map-area";
            var _self = this;
            if (areaId === "" || areaId === undefined || areaId === null) {
                App.state(dom, 0, "区域ID不存在");
                return;
            }
            for (var i = 0; i < this.map_da.length; i++) {
                var resp = _self.map_da[i];
                if (resp.name === areaId) {
                    App.getView(dom).querySelector('.dper-chdti').innerText = fillter.filter_text(resp.name);
                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = 345;
                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = 224;
                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = 121;

                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = 3;
                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = 2;
                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = 0;

                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = 10;
                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = 24;
                    App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = 4;
                    App.state(dom, 2);
                }
            }
        }
    };
    module.exports = ChartMap;
});
