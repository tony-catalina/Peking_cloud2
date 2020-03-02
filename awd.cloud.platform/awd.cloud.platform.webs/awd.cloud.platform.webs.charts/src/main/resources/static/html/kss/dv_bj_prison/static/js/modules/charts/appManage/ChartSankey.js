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
          formatter: '{b}'
        },
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: {
          type: 'graph',
          layout: 'circular',  //
          // nodeGap: this.autoSize(2),
          focusNodeAdjacency: true,
          roam: true,
          // silent:true,
          force: {
            repulsion: 200,
            edgeLength: 300
          },
          edgeSymbolSize: [20, 100],
          // symbol: 'rect',
          lineStyle: {
            color: '#09547f'
          },
          label: {
            show: true,
            position: 'insideBottom',
            padding: [this.autoSize(4), this.autoSize(8)],
            backgroundColor: 'rgba(5,40,137,.6)',
            borderRadius: this.autoSize(100),
            color: '#fff',
            fontSize: this.autoSize(14),
          },
          edgeLabel: {
            show: true,
            textStyle: {
              color: '#00d8ff',
              fontSize: this.autoSize(14)
            },
            formatter: "{c}"
          },
          data: [],
          links: [],
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
      this.option.series.data = [];
      this.option.series.links = [];
      // 数据装载
      _.each(data,function(itm,idx){
        _self.option.series.data.push({
          name: itm.name,
          draggable: true,
          symbolSize: idx!==0?_self.autoSize(40):_self.autoSize(65),
          symbol: 'image://'+itm.img
        });
        _self.option.series.links.push({
          source: 0,
          target: itm.type,
          value: itm.relation
        })
      });
      // 图标渲染
      this.myChart.setOption(this.option)
    }
  });
  module.exports = ChartBar;
});
