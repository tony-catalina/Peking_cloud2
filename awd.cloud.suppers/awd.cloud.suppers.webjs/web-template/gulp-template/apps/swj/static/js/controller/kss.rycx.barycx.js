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
    var clumns=require('models/kss/clumns');
    var url=require('models/url');

    var jbxxTable;
    var main = {
        init: function () {
            $.parser.parse();
            main.toolsInit();
            main.gridInit();
        },
        toolsInit: function () {
            $('.comp-search-box').compSearchBox(); //高级查询的条件查询
            $('#addBtn').on('click', function () {
                layutils.showWin({
                    title:'人员信息新增',
                    area:['600px','500px'],
                    content:$('#laywin')
                });
            });
            // $('#searchForm').form({
            //     url:'/mock/swj/xsrs/data',
            //     onSubmit: function(){
            //         var isValid = $(this).form('validate');
            //         if (!isValid){
            //             $.messager.progress('close');	// hide progress bar while the form is invalid
            //         }
            //         return isValid;	// return false will stop the form submission
            //     },
            //     success:function(data){
            //         //alert(data);
            //     }
            // });
            $('#searchBtn').on('click',function(){
                alert(util.json2Str(util.form2Json('searchForm')));
                jbxxTable.datagrid('reload');
            });
        },
        gridInit:function(){
            jbxxTable=grid.init('ryxxTable', {
                url: url['/kss_jssw/QueryJsswByYwzt'],
                title: '人员信息',
                fit: false,
                width: '100%',
                height: window.innerHeight - 78,
                toolbar: '#toolbar',
                firstLoad: false,
                columns: clumns.jbxx()
            });
        }
    };
    $(main.init);
});