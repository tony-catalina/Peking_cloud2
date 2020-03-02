define(function (require) {
    require('jquery');
    require('easyui');
    require('easyuiZh');
    require('awd/jquery/compSearchBox');
    require('layer');
    var grid=require('awd/easyui/grid');
    var util=require('awd/easyui/util');
    var url=require('models/url');
    var main = {
        'init': function () {
            $.parser.parse();
            //            $('#txtNewPass').parent().hide();
            main.gridBind();
            main.toolBind();
            $('.comp-search-box').compSearchBox(); //高级查询的条件查询
        },
        'gridBind': function () {
            grid.init('test_table', {
                url: url['/kss_jssw/QueryJsswByYwzt'],
                title: '',
                fit: false,
                width: '100%',
                height: window.innerHeight - 140,
                toolbar: '#toolbar',
                firstLoad: false,
                columns: [{
                        field: 'oid',
                        margin:'0',
                        title: '序号',
                        formatter: function (value, rowData, rowIndex) {
                            return '<input type="radio" name="ryRadio" id="ryRadio' + rowIndex + '" value="' + rowData.oid + '" />';
                        }
          },
                    {
                        field: 'wjmc',
                        width: 150,
                        title: '文件名称',
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.wjmc == null ? '' : row.wjmc) + '>' + (row.wjmc == null ? '' : row.wjmc) + '</span>';
                        }
          },
                    {
                        field: 'wjlx',
                        title: '文件类型',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.wjlx == null ? '' : row.wjlx) + '>' + (row.wjlx == null ? '' : row.wjlx) + '</span>';
                        }
          },
                    {
                        field: 'wjms',
                        title: '文件描述',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.wjms == null ? '' : row.wjms) + '>' + (row.wjms == null ? '' : row.wjms) + '</span>';
                        }
          },
                    {
                        field: 'drsj',
                        title: '导入时间',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.drsj == null ? '' : row.drsj) + '>' + (row.drsj == null ? '' : row.drsj) + '</span>';
                        }
          },
                    {
                        field: 'drr',
                        title: '导入人',
                        width: 160,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.drr == null ? '' : row.drr) + '>' + (row.drr == null ? '' : row.drr) + '</span>';
                        }
          }
        ]
            });
        },
        'toolBind': function () {
            $('#newData').on('click', function () {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    maxmin: true,
                    content: $('#newData-wrapper'),
                    zIndex: 1000
                });
            });
        }
    };
    $(main.init);
});