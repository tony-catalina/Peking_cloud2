define(function (require, exports, module) {
  require("sJI");
  // 引入echarts
  var echarts = require("echarts");
  var BaseChart = require("./../BaseChart");

  var ChartBar = BaseChart.extend({
    init: function (el) {
      this._super(el);
      this.option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: 'line',
            lineStyle: {
              type: 'dashed'
            }
          },
          backgroundColor: "rgb(22,27,75)",
          borderColor: '#192060',
          borderWidth: this.autoSize(1),
          formatter: function (va) {
            var res = '';
            _.each(va, function (key) {
              res = key.name + ': ' + key.value + '件'
            });
            return res
          },
          confine: false
        },
        grid: {
          top: '12%',
          left: '3%',
          right: '3%',
          bottom: '6%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: '#fff'
            }
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: '#fff',
            fontSize: this.autoSize(14),
            margin: this.autoSize(14)
          },
          splitLine: {
            show: false
          },
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
          type: 'value',
          name: '件',
          min: 0,
          nameTextStyle: {
            color: '#fff',
            fontSize: this.autoSize(14)
          },
          axisLine: {
            lineStyle: {
              color: '#fff'
            }
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: '#fff',
              fontSize: this.autoSize(14)
            }
          },
          splitLine: {
            show: false
          },
        },
        series: {
          name: "",
          type: 'line',
          data: [1, 2, 2, 3, 4, 5],
          symbol: "circle",
          symbolSize: this.autoSize(8),
          itemStyle: {
            color: "#0b154f",
            borderColor: "#00cfff",
            borderWidth: this.autoSize(2)
          },
          lineStyle: {
            width: this.autoSize(2),
            color: "#00cfff",
            shadowBlur: this.autoSize(2),
            shadowColor: 'rgba(0,25,64,.3)',
            shadowOffsetX: this.autoSize(0),
            shadowOffsetY: this.autoSize(8)
          },
          areaStyle: {
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [{
                offset: 0, color: "rgba(0,207,255,.4)" // 0% 处的颜色
              }, {
                offset: 1, color: "rgba(0,207,255,.1)"
              }]
            }
          },
          smooth: true
        }
      };

      this.myChart = echarts.init(this.el);
    },
    /**
     * 图表渲染
     * @param data
     *
     */
    render: function (data) {
      (this.myChart === null) && (this.myChart = echarts.init(this.el));
      this.option.xAxis.data = [];
      this.option.yAxis.min = 0;
      this.option.yAxis.name = "";
      this.option.series.data = [];
      // 数据装载
      this.option.xAxis.data = data.axis;
      this.option.yAxis.min = data.min;
      this.option.yAxis.name = data.name;
      this.option.series.data = data.seriesData;

      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
