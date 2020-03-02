define(function (require, exports, module) {
  require("sJI");
  // 引入echarts
  var echarts = require("echarts");
  var BaseChart = require("./../BaseChart");

  var ChartBar = BaseChart.extend({
    init: function (el) {
      this._super(el);
      this.option = {
        series: [
          {
            name:'各当前环节数',
            type:'pie',
            radius: ['40%', '60%'],
            center: ['52%','54%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: 'center',
                formatter: '{b}\n{c}次',
                color: "#fff"
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: this.autoSize(12),
                  fontWeight: 'bold'
                }
              }
            },
            data:[]
          },
          {
            type: 'pie',
            radius: ['31%', '32%'],
            center: ['52%','54%'],
            data: [1],
            selectedMode: false,
            silent: true,
            label: {
              show:false
            },
            itemStyle: {
              color: '#055db2'
            },
            labelLine: {
              show: false
            }
          },
          {
            type: 'pie',
            radius: ['71%', '72%'],
            center: ['52%','54%'],
            data: [1],
            selectedMode: false,
            silent: true,
            label: {
              show:false
            },
            itemStyle: {
              color: '#055db2'
            },
            labelLine: {
              show: false
            }
          }
        ]
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

      this.option.series[0].name = "";
      this.option.series[0].data = [];
      this.option.series[0].name = data.name;
      // 数据装载
      _.each(data.list,function(itm){
        _self.option.series[0].data.push({
          name: itm.name,
          value: itm.value,
          itemStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
              offset: 0,
              color: itm.col0
            }, {
              offset: 1,
              color: itm.col1
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
