define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/static/js/controller/pages/common/searchForm.js',
    '/apps/jds/views/pages/common/jxcx.html',
], function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('uikit');
    require('arttemplate');

    var templ = require('/apps/jds/static/js/controller/pages/common/searchForm.js');
    // 简项查询内容
    var pageOptions = {
        jxcx: {
            title: '简项查询',
            controlButtonList: [
                {
                    name: '批量恢复',
                    icon: ''
                },
                {
                    name: '打印',
                    icon: 'icon-iconSVG-'
                }, {
                    name: '导出',
                    icon: 'icon-daochu'
                }
            ]
        }
    };
    // 搜索栏部分的内容
    var formOptions = {
        primary: [{
            title: '姓名',
            type: 'textbox'
        }, {
            title: '出所原因',
            type: 'combotree'
        }],
        more: [
            [
                { title: '性别', type: 'combotree' },
                { title: '出所日期', type: 'datetimebox' },
                { title: '至', type: 'datetimebox' },
            ],
        ]
    };
    // 表格数据
    var initPageData = function (el) {
        // 数据表格部分
        // var table = el.querySelector('.tmpl-table');
        // 数据表格部分
        var $table = $('.tmpl-table', el);
        $table.datagrid({
            method: 'get',
            url: '/mock/jds/datagrid/wcshflist',
            // pageList: [2, 4, 6],
            // // 真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
            fitColumns: true,
            pagination: true,
            rownumbers: true,
            columns: [[
                { field: 'ck', checkbox: true, width: '2%', align: 'center' },
                { field: 'id', title: '编号', hidden: true, align: 'center' },
                { field: 'name', title: '姓名', width: '6%', align: 'center' },
                { field: 'nickname', title: '别名', width: '6%', align: 'center' },
                { field: 'gender', title: '性别', width: '4%', align: 'center' },
                { field: 'jieduRoom', title: '戒毒室', width: '6%', align: 'center' },
                { field: 'chusuoTime', title: '出所时间', width: '10%', align: 'center' },
                { field: 'chusuoReason', title: '出所原因', width: '10%', align: 'center' },
                { field: 'chusuoDirection', title: '出所去向', width: '10%', align: 'center' },
                { field: 'rusuoTime', title: '入所日期', width: '10%', align: 'center' },
                { field: 'rusuoReason', title: '入所原因', width: '10%', align: 'center' },
                { field: 'danwei', title: '办案单位', width: '14%', align: 'center' },
                { field: 'bananPerson', title: '办案人', width: '6%', align: 'center' },
                {
                    field: 'caozuo', title: '操作', width: '7%', align: 'center',
                    formatter: function (value, row, index) {
                        let html = `<span id="huifu" onclick="getHuifu()" style="
                                    display: inline-block;
                                    background: #D2423E;
                                    padding: 3px 9px;
                                    border-radius: 4px;
                                    color: #fff;">恢复</span>`

                        return html;
                    }
                }

            ]],
        })
        // var row = $table.datagrid('getSelected')
        // console.log(row);

    };

    //引入简项查询的模板
    var jxcxTemplate = require('/apps/jds/views/pages/common/jxcx.html');
    var initContent = function () {
        var element = document.createElement('div');
        element.classList.add('section-container', 'qa-container');
        element.innerHTML = template.render(jxcxTemplate, pageOptions.jxcx);
        element.querySelector(".section-content").prepend(templ.render(formOptions));
        initPageData(element);
        return element;
    };
    // // 点击恢复按钮，跳出对话框
    // $('.section-content').on('click', '#huifu', function () {
    //     alert(11)
    // })

    var init = function () {
        document.body.appendChild(initContent())
    }

    init()
});