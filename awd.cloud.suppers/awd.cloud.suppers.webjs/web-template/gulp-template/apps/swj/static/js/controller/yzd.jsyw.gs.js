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
                        field: 'jsbh',
                        width: 150,
                        title: '监所编号',
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jsbh == null ? '' : row.jsbh) + '>' + (row.jsbh == null ? '' : row.jsbh) + '</span>';
                        }
          },
                    {
                        field: 'jsmc',
                        title: '监所名称',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jsmc == null ? '' : row.jsmc) + '>' + (row.jsmc == null ? '' : row.jsmc) + '</span>';
                        }
          },
                    {
                        field: 'mjxm',
                        title: '民警姓名',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.mjxm == null ? '' : row.mjxm) + '>' + (row.mjxm == null ? '' : row.mjxm) + '</span>';
                        }
          },
                    {
                        field: 'sj',
                        title: '时间',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.sj == null ? '' : row.sj) + '>' + (row.sj == null ? '' : row.sj) + '</span>';
                        }
          },
                    {
                        field: 'dd',
                        title: '地点',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.dd == null ? '' : row.dd) + '>' + (row.dd == null ? '' : row.dd) + '</span>';
                        }
          },
                    {
                        field: 'wt',
                        title: '问题',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.wt == null ? '' : row.wt) + '>' + (row.wt == null ? '' : row.wt) + '</span>';
                        }
          },
                    {
                        field: 'zj',
                        title: '证据',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.zj == null ? '' : row.zj) + '>' + (row.zj == null ? '' : row.zj) + '</span>';
                        }
          },
                    {
                        field: 'gsr',
                        title: '公示人',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.gsr == null ? '' : row.gsr) + '>' + (row.gsr == null ? '' : row.gsr) + '</span>';
                        }
          },
                    {
                        field: 'gssj',
                        title: '公示时间',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.gssj == null ? '' : row.gssj) + '>' + (row.gssj == null ? '' : row.gssj) + '</span>';
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