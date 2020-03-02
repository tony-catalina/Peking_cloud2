define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/static/js/controller/pages/sjzx/zpwh/zpwh.js'
], function (require, exports, module) {

    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');

    var tabControl = require('/apps/jds/static/js/controller/pages/sjzx/zpwh/zpwh.js')

    var initContent = function () {
        document.body.appendChild(tabControl.render());
    };

    var init = function () {
        initContent();
    };

    init();

});

