define( function (require) {
    'use strict';
    require('jquery');
    require('easyui');
    require('gridexport');
    require('layer')
    require('awd/jquery/compSearchBox');
    var grid=require('awd/easyui/grid');
    var tools=require('awd/jquery/tools');
    var util=require('awd/easyui/util');
    var layutils=require('awd/layui/utils');
    var clumns=require('models/main/clumns');
    var url=require('models/url');

    var jbxxTable;
    var main = {
        init: function () {
            $.parser.parse();
            util.watermark(['张三','北京市第一看守所']);
            main.toolsInit();
            main.gridInit();
        },
        toolsInit: function () {
            $('.comp-search-box').compSearchBox(); //高级查询的条件查询
            $('#searchBtn').on('click',function(){
                alert(util.json2Str(util.form2Json('searchForm')));
                jbxxTable.datagrid('reload');
            });
            $('#excelData').on('click',function(){
                jbxxTable.datagrid('toExcel','检查督导.xls');
            });
            $('#printData').on('click',function(){
                jbxxTable.datagrid('print','检查督导');
            });
        },
        gridInit:function(){
            jbxxTable=grid.init('ryxxTable', {
                url: '/mock/swj/common/count',
                title: '',
                method:'get',
                fit: false,
                width: '100%',
                pagination:true,
                detailviewshow:true,
                height: window.innerHeight - 120,
                firstLoad: false,
                columns: clumns.jcdd()
            });
        }
    };
    $(main.init);
});