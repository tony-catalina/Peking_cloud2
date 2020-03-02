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
                        field: 'rq',
                        title: '日期',
                        width: 80,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.rq == null ? '' : row.rq) + '>' + (row.rq == null ? '' : row.rq) + '</span>';
                        }
          },
                    {
                        field: 'dryl',
                        title: '当日押量',
                        width: 100,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.dryl == null ? '' : row.dryl) + '>' + (row.dryl == null ? '' : row.dryl) + '</span>';
                        }
          },
                    {
                        field: 'xb',
                        title: '性别',
                        width: 20,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xb == null ? '' : row.xb) + '>' + (row.xb == null ? '' : row.xb) + '</span>';
                        }
          },
                    {
                        field: 'ywry',
                        title: '有执业资质驻所医务人员',
                        width: 150,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.ywry == null ? '' : row.ywry) + '>' + (row.ywry == null ? '' : row.ywry) + '</span>';
                        }
          },
                    {
                        field: 'hbryzs',
                        title: '患病人员总数',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.hbryzs == null ? '' : row.hbryzs) + '>' + (row.hbryzs == null ? '' : row.hbryzs) + '</span>';
                        }
          },
                    {
                        field: 'fxry',
                        title: '因病列控重大安全风险人员',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.fxry == null ? '' : row.fxry) + '>' + (row.fxry == null ? '' : row.fxry) + '</span>';
                        }
          },
                    {
                        field: 'sjlk',
                        title: '四级列控',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.sjlk == null ? '' : row.sjlk) + '>' + (row.sjlk == null ? '' : row.sjlk) + '</span>';
                        }
          },
                    {
                        field: 'xnxgjb',
                        title: '心脑血管疾病',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.xnxgjb == null ? '' : row.xnxgjb) + '>' + (row.xnxgjb == null ? '' : row.xnxgjb) + '</span>';
                        }
          },
                    {
                        field: 'tnb',
                        title: '糖尿病',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.tnb == null ? '' : row.tnb) + '>' + (row.tnb == null ? '' : row.tnb) + '</span>';
                        }
          },
                    {
                        field: 'zhz',
                        title: '严重毒瘾戒断综合征',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.zhz == null ? '' : row.zhz) + '>' + (row.zhz == null ? '' : row.zhz) + '</span>';
                        }
          },
                    {
                        field: 'jsycrs',
                        title: '精神异常人数',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.jsycrs == null ? '' : row.jsycrs) + '>' + (row.jsycrs == null ? '' : row.jsycrs) + '</span>';
                        }
          },
                    {
                        field: 'crbrs',
                        title: '传染病人数',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.crbrs == null ? '' : row.crbrs) + '>' + (row.crbrs == null ? '' : row.crbrs) + '</span>';
                        }
          },
                    {
                        field: 'zdgz',
                        title: '重点关注',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.zdgz == null ? '' : row.zdgz) + '>' + (row.zdgz == null ? '' : row.zdgz) + '</span>';
                        }
          },
                    {
                        field: 'zyrs',
                        title: '住院人数',
                        width: 90,
                        sortable: 'true',
                        formatter: function (value, row) {
                            return '<span title=' + (row.zyrs == null ? '' : row.zyrs) + '>' + (row.zyrs == null ? '' : row.zyrs) + '</span>';
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