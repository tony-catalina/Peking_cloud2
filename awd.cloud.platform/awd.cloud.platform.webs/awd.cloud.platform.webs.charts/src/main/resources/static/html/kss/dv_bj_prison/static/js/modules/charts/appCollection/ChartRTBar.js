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
          top: '10%',
          left: '6%',
          right: '3%',
          bottom: '16%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          boundaryGap: true,
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: false
          },
          splitLine: {
            show: false
          }
        },
        yAxis: {
          type: 'category',
          axisLine: {
            show:false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: '#00c0ff',
              fontSize: this.autoSize(14),
              fontWeight: 'bold'
            }
          },
          splitLine: {
            show: false
          },
          data: []
        },
        color: [
          '#249cf9',
          '#67e0e3'
        ],
        series: {
          name: '',
          type: 'bar',
          itemStyle:{
            barBorderRadius: 100
          },
          barWidth: "25%",
          label: {
            show: true,
            position: ['90%', '-200%'],
            color: '#fff'
          },
          data: []
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
      this.option.yAxis.data = [];
      this.option.series.data = [];
      // 数据装载
      _.each(data.list.reverse(),function(itm){
        _self.option.yAxis.data.push(itm.name);
        _self.option.series.data.push({
          name: itm.name,
          value: itm.value,
          itemStyle:{
            color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
              offset: 0,
              color: itm.co0
            }, {
              offset: 1,
              color: itm.co1
            }])
          }
        });
      });
      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
