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
          trigger: 'axis',
          confine: true,
          formatter: function(resp){
            var res = '';
            res = resp.name;
            _.each(resp.value,function(itm){
              res += "<br/>" + itm + "人"
            });
            return  res
          }
        },
        radar: {
          name: {
            textStyle: {
              color: '#00c0ff',
              fontSize: this.autoSize(18),
              fontWeight: 'bold'
            }
          },
          center: ["50%","65%"],
          radius: "90%",
          indicator: [
            { name: '涉爆', max: 20},
            { name: '涉恐', max: 20},
            { name: '涉黑', max: 20}
          ],
          splitNumber: 3,
          axisLine:{
            show: false
          },
          splitLine:{
            lineStyle:{
              color: '#0279e6'
            }
          },
          splitArea:{
            show:false
          }
        },
        series: {
          name: '风险画像',
          type: 'radar',
          tooltip: {
            trigger: 'item'
          },
          symbol: 'circle',
          symbolSize: this.autoSize(12),
          itemStyle:{
            color: '#0b1550',
            borderColor: '#00fffa',
            borderWidth: this.autoSize(2)
          },
          lineStyle:{
            color: '#00fffa'
          },
          areaStyle: {
            color: 'rgba(0,255,250,.6)'
          },
          data : [
            {
              value : [12,13,14],
              name : '风险画像'
            }
          ]
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
      // 数据装载
      this.option.radar.indicator = [];
      this.option.series.data = [];
      this.option.tooltip.formatter = '';
      _.each(data.list,function(itm){
        _self.option.radar.indicator.push({
          name: itm.name,
          max: itm.max
        })
      });
      this.option.tooltip.formatter = function(resp){
        var res = '';
        res = resp.name;
        _.each(data.list,function(key){
          res += "<br/>" + key.name+":"+key.value + data.unit
        });
        return  res
      };
      this.option.series.data.push({
        value: data.data,
        name: data.name
      });

      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
