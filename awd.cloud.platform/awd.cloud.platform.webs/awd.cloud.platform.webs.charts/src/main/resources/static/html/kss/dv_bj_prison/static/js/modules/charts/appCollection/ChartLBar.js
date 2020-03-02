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
          show: true,
          formatter: '{b}:{c}'
        },
        grid: {
          top: '10%',
          left: '6%',
          right: '6%',
          bottom: '6%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          boundaryGap: true,
          axisLine: {
            lineStyle: {
              color: '#0b5c70'
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
            show: true,
            lineStyle: {
              color: '#0b5c70',
              width: this.autoSize(1)
            }
          }
        },
        yAxis: [
          {
            type: 'category',
            min: 0,
            show: false,
            axisLine: {
              show:false
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: '#49f7ce',
                fontSize: this.autoSize(14)
              }
            },
            splitLine: {
              show: false
            },
            data: []
          },
          {
            type: 'category',
            min: 0,
            axisLine: {
              show:false
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: '#49f7ce',
                fontSize: this.autoSize(14)
              }
            },
            splitLine: {
              show: false
            },
            data: []
          }
        ],
        color: ['#49f7ce'],
        series: {
          name: '12',
          type: 'bar',
          data: [],
          barWidth: '25%'
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
      var _self = this;
      this.option.yAxis[1].data = [];
      this.option.series.data = [];
      // 数据装载
      _.each(data,function(itm){
        _self.option.series.data.push({
          name: itm.name,
          value: itm.value
        });
        _self.option.yAxis[1].data.push(itm.name)
      });
      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
