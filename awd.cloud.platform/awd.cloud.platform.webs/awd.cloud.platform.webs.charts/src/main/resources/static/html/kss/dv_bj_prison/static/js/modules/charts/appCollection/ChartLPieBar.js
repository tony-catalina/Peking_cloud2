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
          formatter: '{b}:{c} 起'
        },
        grid: {
          top: '10%',
          left: '40%',
          right: '6%',
          bottom: '10%',
          containLabel: true
        },
        xAxis: [
          {
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
              color: '#fff',
              fontSize: this.autoSize(14),
              margin: this.autoSize(14),
              rotate: 45
            },
            splitLine: {
              show: true,
              lineStyle: {
                color:'#0b5c70'
              }
            },
            data: []
          },
          {
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
              color: '#fff',
              fontSize: this.autoSize(14),
              margin: this.autoSize(14)
            },
            splitLine: {
              show: true,
              lineStyle: {
                color:'#0b5c70'
              }
            },
          }
        ],
        yAxis: [
          {
            type: 'value',
            min: 0,
            axisLine: {
              show:true,
              lineStyle: {
                color: '#0b5c70'
              }
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
              show: true,
              lineStyle: {
                color:'#0b5c70'
              }
            },
            data: []
          },
          {
            type: 'value',
            min: 0,
            axisLine: {
              show:true,
              lineStyle: {
                color: '#0b5c70'
              }
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
              show: true,
              lineStyle: {
                color:'#0b5c70'
              }
            },
            data: []
          }
        ],
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
      this.option.xAxis[0].data = [];
      this.option.series = [];
      // 数据装载
      this.option.series.push({
        type: 'pie',
        center: ['20%','26%'],
        radius: ['20%', '25%'],
        avoidLabelOverlap: false,
        label: {
          show: true
        },
        labelLine: {
          show: true
        },
        data: (function(){
          var arr = []
            _.each(data.pie0,function(itm,idx){
              arr.push({
                name: itm.name,
                value: itm.value,
                label: {
                  show: idx === 0 ? true: false,
                  formatter: '{c}',
                  textStyle: {
                    color: '#fff'
                  }
                },
                itemStyle: {
                  color: idx===0?'#ed7d31':'#3ffff8'
                },
                labelLine:{
                  show: idx === 0 ? true: false
                }
              })
            })
          return arr
        })()
      });
      this.option.series.push({
        type: 'pie',
        center: ['20%','70%'],
        radius: ['27%', '35%'],
        avoidLabelOverlap: false,
        label: {
          show: true
        },
        labelLine: {
          show: true
        },
        data: (function(){
          var arr = []
            _.each(data.pie1,function(itm,idx){
              arr.push({
                name: itm.name,
                value: itm.value,
                label: {
                  show: false
                },
                itemStyle: {
                  color: idx===0?'#ed7d31':'#3ffff8'
                },
                labelLine:{
                  show: false,
                }
              })
            })
          return arr
        })()
      });
      this.option.series.push({
        type: 'bar',
        barWidth: '35%',
        data: (function(){
          var arr = []
            _.each(data.list,function(itm,idx){
              arr.push({
                name: itm.name,
                value: itm.value,
                itemStyle: {
                  color: '#3ffff8',
                  barBorderRadius: [100,100,0,0]
                }
              })
            })
          return arr
        })()
      });
      _.each(data.list,function(itm){
          _self.option.xAxis[0].data.push(itm.name)
      })
      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
