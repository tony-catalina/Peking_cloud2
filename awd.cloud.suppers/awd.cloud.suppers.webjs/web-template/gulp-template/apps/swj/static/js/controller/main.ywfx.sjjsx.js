define(function(require){
    require('jquery');
    require('easyui');
    require('easyuiZh');
    var util = require('awd/easyui/util');
    var grid = require('awd/easyui/grid');
    var echart = require('awd/echart/echart');
    var clumns=require('models/main/clumns');
    var main={
        'init':function () {
            util.watermark(['张三','北京市第一看守所']);
            echart.init('Canvas3',{url:'/mock/echart/linedata',type:'bar',config:{}});
            grid.init('test_table',{
                width: '100%',
                pagination:true,
                detailviewshow:true,
                height: window.innerHeight - 78,
                columns: clumns.sjjsx()
            });
        }
    };
    $(function() {
        main.init();
    });
});
