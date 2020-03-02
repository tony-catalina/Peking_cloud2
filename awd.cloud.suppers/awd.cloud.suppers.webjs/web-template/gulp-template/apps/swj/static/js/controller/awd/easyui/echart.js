define( function(require) {
  'use strict';
  require('jquery');
  require('easyui');
  require('echarts');
  var tools=require('awd/jquery/tools');
  var main= {
    getOption:function(type,data){
      var option={};
      var labels=tools.isUndefined(data.labels)?[]:data.labels;
      var values=tools.isUndefined(data.values)?[]:data.values;
      if(type=='line'){
        option = {
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
          }]
        };
      }
      if(type=='bar'){

      }
      if(type=='pie'){

      }
      if(type=='scatter'){

      }
      if(type=='map'){

      }
      if(type=='candelstick'){

      }
      if(type=='radar'){

      }
      if(type=='boxplot'){

      }
      if(type=='heatmap'){

      }
      if(type=='graph'){

      }
      if(type=='lines'){

      }
      if(type=='tree'){

      }
      if(type=='treemap'){

      }
      if(type=='sunburst'){

      }
      if(type=='parallel'){

      }
      if(type=='sankey'){

      }
      if(type=='funnel'){

      }
      if(type=='gauge'){

      }
      if(type=='pictorialbar'){

      }
      if(type=='themeriver'){

      }
      if(type=='calendar'){

      }
      if(type=='custom'){

      }
      return option;
    },
    init: function(_id,_url,_type) {
      var myChart = echarts.init($(_id));
      $.ajax({
        url:_url,
        method:'get',
        success:function(data){
          myChart.setOption(main.getOption(_type,data));
        }
      });

    }
  };
  return main;
});
