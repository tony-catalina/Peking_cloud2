define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'uikit',
    'arttemplate',
    '/apps/jds/static/js/controller/pages/sjzx/hs/ywlc.js',
    '../../../modules/tabs.js'
], function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('uikit');
    require('arttemplate');
    var tabs = require('../../../modules/tabs.js');

    var tabControl = new tabs.TabControl({
        items: [
            { name: '业务流程', icon: 'icon-liucheng', mod: require('/apps/jds/static/js/controller/pages/sjzx/hs/ywlc.js') },
        ],
        onTabChange: function (element) {
            $.parser.parse(element);
        }
    });

    var initContent = function () {
        document.body.appendChild(tabControl.render());
    };

    var init = function () {
        initContent();
    };

    init();

});
