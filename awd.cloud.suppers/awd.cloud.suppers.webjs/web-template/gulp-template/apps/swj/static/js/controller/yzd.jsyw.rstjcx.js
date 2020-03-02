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
                        width: 100,
                        title: '监所编号',
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jsbh == null ? '' : row.jsbh) + '>' + (row.jsbh == null ? '' : row.jsbh) + '</span>';
                        }
          },
                    {
                        field: 'jsmc',
                        title: '监所名称',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jsmc == null ? '' : row.jsmc) + '>' + (row.jsmc == null ? '' : row.jsmc) + '</span>';
                        }
          },
                    {
                        field: 'rybh',
                        title: '人员编号',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.rybh == null ? '' : row.rybh) + '>' + (row.rybh == null ? '' : row.rybh) + '</span>';
                        }
          },
                    {
                        field: 'xm',
                        title: '姓名',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xm == null ? '' : row.xm) + '>' + (row.xm == null ? '' : row.xm) + '</span>';
                        }
          },
                    {
                        field: 'xb',
                        title: '性别',
                        width: 40,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xb == null ? '' : row.xb) + '>' + (row.xb == null ? '' : row.xb) + '</span>';
                        }
          },
                    {
                        field: 'jcrq',
                        title: '检查日期',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jcrq == null ? '' : row.jcrq) + '>' + (row.jcrq == null ? '' : row.jcrq) + '</span>';
                        }
          },
                    {
                        field: 'xy',
                        title: '血压',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xy == null ? '' : row.xy) + '>' + (row.xy == null ? '' : row.xy) + '</span>';
                        }
          },
                    {
                        field: 'xcg',
                        title: '血常规',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xcg == null ? '' : row.xcg) + '>' + (row.xcg == null ? '' : row.xcg) + '</span>';
                        }
          },
                    {
                        field: 'xdt',
                        title: '心电图',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xdt == null ? '' : row.xdt) + '>' + (row.xdt == null ? '' : row.xdt) + '</span>';
                        }
          },
                    {
                        field: 'xp',
                        title: '胸片',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xp == null ? '' : row.xp) + '>' + (row.xp == null ? '' : row.xp) + '</span>';
                        }
          },
                    {
                        field: 'bc',
                        title: 'B超',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.bc == null ? '' : row.bc) + '>' + (row.bc == null ? '' : row.bc) + '</span>';
                        }
          },
                    {
                        field: 'jkqk',
                        title: '健康情况',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jkqk == null ? '' : row.jkqk) + '>' + (row.jkqk == null ? '' : row.jkqk) + '</span>';
                        }
          },
                    {
                        field: 'jcys',
                        title: '检查医生',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jcys == null ? '' : row.jcys) + '>' + (row.jcys == null ? '' : row.jcys) + '</span>';
                        }
          },
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