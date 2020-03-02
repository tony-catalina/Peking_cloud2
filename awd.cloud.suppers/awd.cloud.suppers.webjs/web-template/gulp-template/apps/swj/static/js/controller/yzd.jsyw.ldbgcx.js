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
                        field: 'dw',
                        width: 80,
                        title: '单位',
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.dw == null ? '' : row.dw) + '>' + (row.dw == null ? '' : row.dw) + '</span>';
                        }
          },
                    {
                        field: 'xm',
                        title: '姓名',
                        width: 70,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xm == null ? '' : row.xm) + '>' + (row.xm == null ? '' : row.xm) + '</span>';
                        }
          },
                    {
                        field: 'xrzw',
                        title: '现任职务',
                        width: 80,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xrzw == null ? '' : row.xrzw) + '>' + (row.xrzw == null ? '' : row.xrzw) + '</span>';
                        }
          },
                    {
                        field: 'rzsj',
                        title: '任职时间',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.rzsj == null ? '' : row.rzsj) + '>' + (row.rzsj == null ? '' : row.rzsj) + '</span>';
                        }
          },
                    {
                        field: 'bgdh',
                        title: '办公电话',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.bgdh == null ? '' : row.bgdh) + '>' + (row.bgdh == null ? '' : row.bgdh) + '</span>';
                        }
          },
                    {
                        field: 'sjhm',
                        title: '手机号码',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.sjhm == null ? '' : row.sjhm) + '>' + (row.sjhm == null ? '' : row.sjhm) + '</span>';
                        }
          },
                    {
                        field: 'zggz',
                        title: '主/分管工作',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.zggz == null ? '' : row.zggz) + '>' + (row.zggz == null ? '' : row.zggz) + '</span>';
                        }
          },
                    {
                        field: 'ygzdw',
                        title: '原工作单位',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.ygzdw == null ? '' : row.ygzdw) + '>' + (row.ygzdw == null ? '' : row.ygzdw) + '</span>';
                        }
          },
                    {
                        field: 'yzw',
                        title: '原职务',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.yzw == null ? '' : row.yzw) + '>' + (row.yzw == null ? '' : row.yzw) + '</span>';
                        }
          },
                    {
                        field: 'ywjgjy',
                        title: '有无监管工作经验',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.ywjgjy == null ? '' : row.ywjgjy) + '>' + (row.ywjgjy == null ? '' : row.ywjgjy) + '</span>';
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