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
                axisLine: { show: false },
                axisTick: { show: false },
                axisLabel: { show: false },
                splitLine: { show: false }
            },
            yAxis: {
                type: 'value',
                min: 0,
                max: 647,
                axisLine: { show: false },
                axisTick: { show: false },
                axisLabel: { show: false },
                splitLine: { show: false }
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
            var _self = this;
            if (this.myChart === null) {
                this.myChart = echarts.init(this.dom);
            }
            _self.option.series = [];
            _.each(arr, function (itm, idx) {
                // console.log("打印数据");
                // console.log(itm);
                _self.option.series.push({
                    name: itm.name,
                    type: 'effectScatter',
                    symbolSize: 14,
                    data: [[itm.value[0], itm.value[1], itm.jsbh,itm.kssName], itm.nameAdd],
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
                            return itm.nameAdd
                        }
                    },
                    itemStyle: {
                        color: itm.color
                    }
                });
            });
            // // 详情展示
            _self.getArea(data, name);
            _self.myChart.clear();
            _self.myChart.setOption(_self.option);

        },
        // 对应数据展示
        getArea: function (data, areaId) {
            var dom = "dper-map-area";
            var _self = this;
            this.map_da = [];
            this.map_da = data;
            if (areaId === "" || areaId === undefined || areaId === null) {
                App.state(dom, 0, "区域ID不存在");
                return;
            }
            for (var i = 0; i < this.map_da.length; i++) {
                var resp = _self.map_da[i];
                if (resp.nameAdd === areaId) {
                    // App.getView(dom).querySelector('.dper-chdti').innerText = fillter.filter_text(resp.mmp);
                    if (localStorage.getItem("idjsbhxfname") == null || '') {
                        App.getView(dom).querySelector('.dper-chdti').innerText = fillter.filter_text("延庆区(看)");
                    } else {
                        App.getView(dom).querySelector('.dper-chdti').innerText = fillter.filter_text(localStorage.getItem("idjsbhxfname"));
                    }

                    if (resp.nameAdd == "北京市第一看守所") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "北京市第二看守所") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "北京市第三看守所") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "朝阳区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "东城区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "西城区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "丰台区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "石景山区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "海淀区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "门头沟区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "房山区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "通州区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "顺义区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "昌平区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "大兴区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "平谷区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "怀柔区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "密云区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else if (resp.nameAdd == "延庆区(看)") {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    } else {
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[0].innerText = localStorage.getItem("idzyzs");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[1].innerText = localStorage.getItem("idman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[2].innerText = localStorage.getItem("idwoman");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = localStorage.getItem("idzats");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = localStorage.getItem("idlshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = localStorage.getItem("idjshj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[6].innerText = localStorage.getItem("idyj");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[7].innerText = localStorage.getItem("idej");
                        App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[8].innerText = localStorage.getItem("idsj");
                    }


                    // App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[3].innerText = 3;
                    // App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[4].innerText = 2;
                    // App.getView(dom).querySelectorAll('.dper-cvi .dper-cleft .dper-cleft-itm .dper-val')[5].innerText = 0;

                    App.state(dom, 2);
                }
            }
        }
    };
    module.exports = ChartMap;
});
