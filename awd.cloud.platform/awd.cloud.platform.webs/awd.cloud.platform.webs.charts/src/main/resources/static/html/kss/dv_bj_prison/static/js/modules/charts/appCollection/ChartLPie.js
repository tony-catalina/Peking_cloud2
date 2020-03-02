define(function (require, exports, module) {
  require("sJI");
  // 引入echarts
  var echarts = require("echarts");
  var BaseChart = require("./../BaseChart");

  var ChartBar = BaseChart.extend({
    init: function (el) {
      this._super(el);
      this.option = {
        // tooltip:{
        //   show: true,
        //   formatter: '{c}: {d} 人'
        // },
        series: {
          name: '各当前环节数',
          type: 'pie',
          radius: ['50%', '60%'],
          center: ['50%', '50%'],
          avoidLabelOverlap: false,
          label: {
            show: true
          },
          labelLine: {
            show: true
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
      this.option.series.name = '';
      this.option.series.data = [];
      // 数据装载
      this.option.series.name = data.name;
      _.each(data.list,function(itm,idx){
        _self.option.series.data.push({
          name: itm.name,
          value: itm.value,
          label: {
            show: idx === 0?true:false,
            formatter: '{c}\n{d}%',
            textStyle: {
              color: '#fff'
            }
          },
          labelLine: {
            show: idx === 0?true:false,
            length: 2,
            length2: 2
          },
          itemStyle: {
            color: itm.col
          }
        })
      });
      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
