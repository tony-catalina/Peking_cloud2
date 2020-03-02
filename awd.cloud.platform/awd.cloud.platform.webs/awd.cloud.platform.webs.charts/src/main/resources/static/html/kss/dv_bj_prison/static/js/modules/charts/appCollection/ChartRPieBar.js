define(function (require, exports, module) {
  require("sJI");
  // 引入echarts
  var echarts = require("echarts");
  var BaseChart = require("./../BaseChart");

  var ChartBar = BaseChart.extend({
    init: function (el) {
      this._super(el);
      this.option = {
        legend: {
          orient: 'vertical',
          left: '6%',
          bottom: '12%',
          itemWidth: this.autoSize(12),
          itemHeight: this.autoSize(4),
          textStyle: {
            color: '#fff',
            fontSize: this.autoSize(16)
          },
          data: []
        },
        tooltip: {
          show: true,
          formatter: '{b}: {c} 人'
        },
        grid: {
          top: '3%',
          left: '50%',
          right: '3%',
          bottom: '6%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
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
            color: '#49f7ce',
            rotate: 45,
            width: '50%',
            height: '20%',
            fontSize: this.autoSize(14),
            margin: this.autoSize(14)
          },
          splitLine: {
            show: false
          },
          data: []
        },
        yAxis: {
          type: 'value',
          min: 0,
          axisLine: {
            show:false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: '#fff',
              fontSize: this.autoSize(14),
              fontWeight: 'bold'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#0b5c70',
              width: this.autoSize(1)
            }
          },
        },
        color: [],
        series: []
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
      this.option.legend.data = [];
      this.option.color = [];
      this.option.series = [];
      this.option.xAxis.data = [];
      // 数据装载
      _.each(data.llist, function (itm,idx) {
      _self.option.color.push(itm.co0);
        _self.option.legend.data.push({
          name: itm.name,
          icon: 'roundRect'
        })
        _self.option.series.push({
          name: itm.name,
          type: 'pie',
          radius: itm.radius,
          center: ['20%', '40%'],
          avoidLabelOverlap: false,
          label: {
            show: false
          },
          labelLine: {
            show: false
          },
          data: [
            {
              name: itm.name,
              value: itm.value,
              itemStyle: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                  offset: 0,
                  color: itm.co0
                }, {
                  offset: 1,
                  color: itm.co1
                }])
              }
            },
            {
              name: idx,
              value: data.sum - itm.value,
              itemStyle: {
                opacity: 0
              },
              emphasis: {
                label: {
                  show:false
                }
              },
              tooltip: {
                formatter: function(resp){}
              }
            }
          ]
        })
      });
      this.option.xAxis.data = (function(){
        var arr = [];
        _.each(data.rlist,function(itm){
          arr.push(itm.name)
        });
        return arr
      })();
      this.option.series.push({
        type: 'bar',
        barWidth: '25%',
        data: (function(){
            var arr = [];
            _.each(data.rlist,function(itm){
              arr.push({
                name: itm.name,
                value: itm.value,
                itemStyle: {
                  color: '#49f7ce'
                }
              });
            });
          return arr
        })()
      });
      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
