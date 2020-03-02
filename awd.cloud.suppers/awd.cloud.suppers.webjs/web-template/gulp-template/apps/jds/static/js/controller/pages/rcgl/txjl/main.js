define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/static/js/controller/pages/rcgl/txjl/ywlc.js',
    '/apps/jds/static/js/controller/pages/rcgl/txjl/ywtz.js',
    '/apps/jds/static/js/controller/pages/rcgl/txjl/gfsm.js',
    '../../../modules/tabs.js'
], function (require, exports, module) {

    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');

    var tabs = require('../../../modules/tabs.js');

    var tabControl = new tabs.TabControl({
        items: [
            { name: '业务流程', icon: 'icon-liucheng', mod: require('/apps/jds/static/js/controller/pages/rcgl/txjl/ywlc.js') },
            { name: '业务台账', icon: 'icon-taizhang', mod: require('/apps/jds/static/js/controller/pages/rcgl/txjl/ywtz.js') },
            { name: '规范说明', icon: 'icon-tishishuoming', mod: require('/apps/jds/static/js/controller/pages/rcgl/txjl/gfsm.js') }
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
