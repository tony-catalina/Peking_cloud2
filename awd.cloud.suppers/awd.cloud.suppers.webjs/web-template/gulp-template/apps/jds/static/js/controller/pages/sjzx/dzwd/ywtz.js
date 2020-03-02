define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/static/js/controller/pages/sjzx/dzwd/searchTable.js',
    '/apps/jds/static/js/controller/pages/sjzx/dzwd/search.js',
    '/apps/jds/static/js/controller/pages/sjzx/dzwd/searchYj.js',
    '/apps/jds/views/pages/sjzx/dzwd/jxdz.html',
    '/apps/jds/views/pages/sjzx/dzwd/mjzk.html'
], function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');
    var templ = require('/apps/jds/static/js/controller/pages/sjzx/dzwd/searchTable.js');
    var templ1 = require('/apps/jds/static/js/controller/pages/sjzx/dzwd/search.js');
    var temply = require('/apps/jds/static/js/controller/pages/sjzx/dzwd/searchYj.js');
     // 搜索栏部分的内容
    var formOptions = {
        primary:[{
            title:'民警姓名',
            type:'textbox'
        },{
            title:'未制ID卡',
            type:'checkbox',
            options:{labelPosition:'after'}
        }],
        more:[
            [
                {title:'ID卡号',type:'textbox'},
                {title:'远距卡号',type:'textbox'},
                {title:'未制远距卡',type:"checkbox", options:{labelPosition:'after'}},
            ]
        ]
    }
    var formSearchiOptions = {
        primary:[{
            title:'已有ID卡号',
            type:'textbox'
        },{
            title:'ID卡号',
            type:'textbox'
        }],

    }
    var formSearchyOptions = {
        primary:[{
            title:'已有远距卡号',
            type:'textbox'
        },{
            title:'新远距卡号',
            type:'textbox'
        }],

    }

    var initPageData = function (el) {
        // 数据表格部分
        // var table = el.querySelector('.tmpl-table');
        // 数据表格部分
        var $table = $('.tmpl-table', el);
        $table.datagrid({
            method: 'get',
            url: '/mock/jds/datagrid/dzwdlist',
            // pageList: [2, 4, 6],
            // // 真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
            // rownumbers: true,
            fitColumns: true,
            pagination: true,
            rownumbers: true,
            columns: [[
                { field: 'ck', checkbox: true, width: '5%', align: 'center' },
                { field: 'id', title: '番号', hidden: true, align: 'center' },
                { field: 'name', title: '姓名', width: '10%', align: 'center' },
                { field: 'gender', title: '性别', width: '10%', align: 'center' },
                { field: 'leibie', title: '案件类别', width: '24%', align: 'center' },
                { field: 'jushiRoom', title: '戒毒室', width: '26%', align: 'center' },
                { field: 'birthTime', title: '出生日期', width: '28%', align: 'center' }
            ]],
        })
    };

    var jxcxHtml = require('/apps/jds/views/pages/sjzx/dzwd/mjzk.html');
    var jxcxTemplate = require('/apps/jds/views/pages/sjzx/dzwd/jxdz.html');

    exports.render = function () {
        var element = document.createElement('div');
        element.classList.add('section-container', 'qa-container');
        element.innerHTML = jxcxTemplate + jxcxHtml;
        element.querySelector(".section-content").prepend(templ.render(formOptions));
        element.querySelector(".section-search").prepend(templ1.render(formSearchiOptions));
        element.querySelector(".section-searchy").prepend(temply.render(formSearchyOptions));
        initPageData(element)
        return element;
    };
});
