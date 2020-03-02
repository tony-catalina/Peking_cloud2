define( function (require) {
    'use strict';
    require('jquery');
    require('easyui');
    require('layer')
    require('awd/jquery/compSearchBox');
    var grid=require('awd/easyui/grid');
    var tools=require('awd/jquery/tools');
    var util=require('awd/easyui/util');
    var layutils=require('awd/layui/utils');
    var clumns=require('models/countClumns');
    var kssclumns=require('models/kss/clumns');
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
        },
        gridInit:function(){
            jbxxTable=grid.init('ryxxTable', {
                url: '/mock/swj/common/count',
                title: '在押人员',
                method:'get',
                fit: false,
                width: '100%',
                pagination:false,
                detailviewshow:false,
                height: window.innerHeight - 78,
                firstLoad: false,
                columns: clumns.clumns(),
                onDblClickRow:function(rowIndex, rowData){
                    main.showPersion({
                        title:'人员信息详情',
                        url:'/mock/swj/common/test',
                        data:rowData,
                        clumns:kssclumns.jbxx({})
                    });
                }
            });
        },
        showPersion:function(option){
            if(!tools.isUndefined(option.url)){
                $('#laywin').html('<table id="jbxxlist"></table>');
                var laywin=layutils.showWin({
                    title:'人员详细信息',
                    area:['100%','100%'],
                    content:$('#laywin'),
                    max:true,
                    success:function(){
                        var jbxxlistgrid=grid.init('jbxxlist',{
                            url: option.url,
                            title: option.title,
                            fit: false,
                            height:'600px',
                            method:'get',
                            firstLoad: true,
                            columns: option.clumns
                        });
                    }
                });
            }
        }
    };
    $(main.init);
});