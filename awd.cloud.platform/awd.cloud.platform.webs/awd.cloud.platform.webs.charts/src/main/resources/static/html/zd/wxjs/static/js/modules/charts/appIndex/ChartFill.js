define(function (require, exports, module) {
  require("sJI");
  // 引入echarts
  var echarts = require("echarts");
  var fill = require("fill");
  var BaseChart = require("./../BaseChart");

  var ChartBar = BaseChart.extend({
    init: function (el) {
      this._super(el);
      this.option = {
        series: [{
          name: '',
          type: 'liquidFill',
          data: [{
            value: .6204,
            itemStyle: {
              color: "#09f"
            }
          }],
          center: ["50%", "45%"],
          // amplitude: 0,
          waveAnimation: true,
          radius: "70%",
          backgroundStyle: {
            color: "rgba(0,153,255,.3)"
          },
          outline: {
            borderDistance: 6,
            itemStyle: {
              borderColor: "#09f",
              borderWidth: 2
            },
          },
          label: {
            show: true,
            fontSize: this.autoSize(18),
            formatter: function(el){
              var res = '';
              res += ' {a_| '+(el.value*100).toFixed(1) +'%}';
              return res;
            },
            verticalAlign: "center",
            rich: {
              a_: {
                color: '#fff',
                fontWeight: "bold",
                padding: [0,0,6,0],
                // margin: [0,0,24,0],
              }
            }
          },
          labelStyle: {
            show: false
          },
          itemStyle: {
            borderWidth: 10
          }
        }]
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
      this.option.series[0].name = '';
      this.option.series[0].data[0].value = '';
      this.option.series[0].data[0].itemStyle.color = '';
      this.option.series[0].backgroundStyle.color = '';
      this.option.series[0].outline.itemStyle.borderColor = '';
      // 数据装载
      this.option.series[0].name = data.name;
      this.option.series[0].data[0].value = data.value;
      this.option.series[0].data[0].itemStyle.color = data.col;
      this.option.series[0].backgroundStyle.color = data.bg;
      this.option.series[0].outline.itemStyle.borderColor = data.col;

      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
