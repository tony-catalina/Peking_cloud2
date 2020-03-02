define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/static/js/controller/pages/common/searchForm.js',
    '/apps/jds/views/pages/rcgl/txjl/datas.html',
], function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');
    var templ = require('/apps/jds/static/js/controller/pages/common/searchForm.js');
    // 搜索栏部分的内容
    var formOptions = {
        primary:[{
            title:'姓名',
            type:'textbox'
        },{
            title:'内部管理编号',
            type:'textbox',
            width:'80px'
        },{
            title:'别名',
            type:'textbox'
        }],
        more:[
            [
                {title:'出生日期',type:'datetimebox'},
                {title:'性别',type:'combobox'},
                {title:'文化程度',type:'textbox', },
            ],[
              {title:'戒室号',type:'textbox'},
              {title:'戒毒期限',type:'datetimebox'},
              {title:'入所日期',type:'datetimebox'}
            ]
        ]
    }

    var jxcxTemplate = require('/apps/jds/views/pages/rcgl/txjl/datas.html');

    var render = function() {
        var element = document.createElement('div');
        element.classList.add('section-container', 'qa-container');
        element.innerHTML =  jxcxTemplate ;
        element.querySelector(".section-content").prepend(templ.render(formOptions));
        return element;
    };

    exports.render = render;
});
