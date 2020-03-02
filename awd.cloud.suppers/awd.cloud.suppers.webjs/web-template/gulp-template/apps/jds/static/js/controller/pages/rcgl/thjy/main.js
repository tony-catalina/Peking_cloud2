define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/static/js/controller/pages/rcgl/thjy/ywlc.js',
    '/apps/jds/static/js/controller/pages/rcgl/thjy/ywtz.js',
    '/apps/jds/static/js/controller/pages/rcgl/thjy/gfsm.js',
    '../../../modules/tabs.js'
], function (require, exports, module) {

    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');

    var tabs = require('../../../modules/tabs.js');

    var tabControl = new tabs.TabControl({
        items: [
            { name: '业务流程', icon: 'icon-liucheng', mod: require('/apps/jds/static/js/controller/pages/rcgl/thjy/ywlc.js') },
            { name: '业务台账', icon: 'icon-taizhang', mod: require('/apps/jds/static/js/controller/pages/rcgl/thjy/ywtz.js') },
            { name: '规范说明', icon: 'icon-tishishuoming', mod: require('/apps/jds/static/js/controller/pages/rcgl/thjy/gfsm.js') }
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
