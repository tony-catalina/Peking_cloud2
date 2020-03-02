define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/views/pages/common/jxcx.html'
], function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');

    var jxcxHtml = require('/apps/jds/views/pages/common/jxcx.html');

    exports.render = function () {
        var element = document.createElement('div');
        element.classList.add('section-container', 'qa-container');
        element.innerHTML = jxcxHtml;
        return element;
    };
});
