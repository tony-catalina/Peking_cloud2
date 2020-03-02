define(function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('uikit');
    // var combox=require('awd/easyui/combox');
    // var grid=require('awd/easyui/grid');
    // var url=require('modules/url');
    var initTable=function(){
        grid.init('ryxx_table', {
            url: url['/kss_jssw/QueryJsswByYwzt'],
            title: '',
            width: '100%',
            height: '100%',
            toolbar: '#toolbar',
            firstLoad: false,
            columns: [{
                field: 'oid',
                title: '序号',
                formatter: function (value, rowData, rowIndex) {
                    return '<input type="radio" name="ryRadio" id="ryRadio' + rowIndex + '" value="' + rowData.oid + '" />';
                }
            },
            {
                field: 'jsbh',
                width: 150,
                title: '监所编号',
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.jsbh == null ? '' : row.jsbh) + '>' + (row.jsbh == null ? '' : row.jsbh) + '</span>';
                }
            },
            {
                field: 'rybh',
                title: '人员编号',
                width: 150,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.rybh == null ? '' : row.rybh) + '>' + (row.rybh == null ? '' : row.rybh) + '</span>';
                }
            },
            {
                field: 'xm',
                title: '姓名',
                width: 150,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.xm == null ? '' : row.xm) + '>' + (row.xm == null ? '' : row.xm) + '</span>';
                }
            },
            {
                field: 'sqwp',
                title: '申请物品',
                width: 150,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.sqwp == null ? '' : row.sqwp) + '>' + (row.sqwp == null ? '' : row.sqwp) + '</span>';
                }
            },
            {
                field: 'sqyy',
                title: '申请原因',
                width: 150,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.sqyy == null ? '' : row.sqyy) + '>' + (row.sqyy == null ? '' : row.sqyy) + '</span>';
                }
            },
            {
                field: 'sqsj',
                title: '申请时间',
                width: 150,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.sqsj == null ? '' : row.sqsj) + '>' + (row.sqsj == null ? '' : row.sqsj) + '</span>';
                }
            },
            {
                field: 'qtblr',
                title: '前台办理人',
                width: 150,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.qtblr == null ? '' : row.qtblr) + '>' + (row.qtblr == null ? '' : row.qtblr) + '</span>';
                }
            },
            {
                field: 'qtblsj',
                title: '前台办理时间',
                width: 150,
                sortable: 'true',
                formatter: function (value, row) {
                    return '<span title=' + (row.qtblsj == null ? '' : row.qtblsj) + '>' + (row.qtblsj == null ? '' : row.qtblsj) + '</span>';
                }
            }
            ]
        });
    };
    var initSearchForm=function(){
        combox.initDefault();
    };
    $(function () {
        // initSearchForm();
    });
});
