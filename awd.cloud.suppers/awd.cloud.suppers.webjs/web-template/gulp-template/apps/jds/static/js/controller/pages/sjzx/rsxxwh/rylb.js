define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/views/pages/sjzx/rsxxwh/indexContent.html',
    '/apps/jds/views/pages/sjzx/rsxxwh/indexContentForm.html',
    '/apps/jds/static/js/controller/pages/common/searchForm.js'
],function(require, exports, module){
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');

    var jxcxTemplate = require('/apps/jds/views/pages/sjzx/rsxxwh/indexContent.html');
    var FormTemplate = require('/apps/jds/views/pages/sjzx/rsxxwh/indexContentForm.html');
    
    var render = function() {
        var element = document.createElement('div');
        element.classList.add('section-container', 'qa-container');
        element.innerHTML =  jxcxTemplate + FormTemplate;
        return element;
    };

    exports.render = render;
})