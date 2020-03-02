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
                        field: 'jsh',
                        title: '监室号',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jsh == null ? '' : row.jsh) + '>' + (row.jsh == null ? '' : row.jsh) + '</span>';
                        }
          },
                    {
                        field: 'lx',
                        title: '类型',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.lx == null ? '' : row.lx) + '>' + (row.lx == null ? '' : row.lx) + '</span>';
                        }
          },
                    {
                        field: 'sfzdaj',
                        title: '是否重大案件',
                        width: 170,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.sfzdaj == null ? '' : row.sfzdaj) + '>' + (row.sfzdaj == null ? '' : row.sfzdaj) + '</span>';
                        }
          },
                    {
                        field: 'sar',
                        title: '涉案人',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.sar == null ? '' : row.sar) + '>' + (row.sar == null ? '' : row.sar) + '</span>';
                        }
          },
                    {
                        field: 'sars',
                        title: '涉案人数',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.sars == null ? '' : row.sars) + '>' + (row.sars == null ? '' : row.sars) + '</span>';
                        }
          },
                    {
                        field: 'afdd',
                        title: '案发地点',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.afdd == null ? '' : row.afdd) + '>' + (row.afdd == null ? '' : row.afdd) + '</span>';
                        }
          },
                    {
                        field: 'afsj',
                        title: '案发时间',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.afsj == null ? '' : row.afsj) + '>' + (row.afsj == null ? '' : row.afsj) + '</span>';
                        }
          },
                    {
                        field: 'saje',
                        title: '涉案金额',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.saje == null ? '' : row.saje) + '>' + (row.saje == null ? '' : row.saje) + '</span>';
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