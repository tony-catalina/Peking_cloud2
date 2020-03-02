define(function (require, exports, module) {
  require("sJI");
  // 引入echarts
  var echarts = require("echarts");
  var BaseChart = require("./../BaseChart");

  var ChartBar = BaseChart.extend({
    init: function (el) {
      this._super(el);
      this.option = {
        legend:{
          top: '3%',
          right: '3%',
          itemWidth: this.autoSize(16),
          itemHeight: this.autoSize(10),
          textStyle: {
            color: '#fff',
            fontSize: this.autoSize(14)
          },
          data: []
        },
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
            res = va[0].name;
            _.each(va, function (key) {
              res += "<br/>"+key.seriesName + ': ' + key.value + ''
            });
            return res
          },
          confine: false
        },
        grid: {
          top: '30%',
          left: '4%',
          right: '4%',
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
          name: "1",
          type: 'line',
          symbol: "circle",
          symbolSize: this.autoSize(8),
          itemStyle: {
            opacity: 0
          },
          lineStyle: {
            width: this.autoSize(2),
            color: "#00cfff"
          },
          data: [1, 2, 2, 3, 4, 5]
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
      var _self = this
      this.option.xAxis.data = [];
      this.option.legend.data = [];
      this.option.yAxis.min = 0;
      this.option.series = [];
      // // 数据装载
      this.option.xAxis.data = data.axis;
      this.option.yAxis.min = data.min;
      _.each(data.series,function(itm){
        _self.option.series.push({
          name: itm.name,
          type: 'line',
          symbol: "circle",
          itemStyle: {
            opacity: 0
          },
          lineStyle: {
            width: _self.autoSize(2),
            color: itm.col
          },
          data: itm.value
        });
        _self.option.legend.data.push({
          name: itm.name,
          icon: "image://"+itm.icon
        })
      });

      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
