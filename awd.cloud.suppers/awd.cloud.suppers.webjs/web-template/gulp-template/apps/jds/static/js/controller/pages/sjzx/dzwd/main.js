define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/static/js/controller/pages/sjzx/dzwd/ywlc.js',
    '/apps/jds/static/js/controller/pages/sjzx/dzwd/ywtz.js',
    '../../../modules/tabs.js'
], function (require, exports, module) {

    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');

    var tabs = require('../../../modules/tabs.js');

    var tabControl = new tabs.TabControl({
        items: [
            { name: '被监管人员制卡', icon: 'icon-liucheng', mod: require('/apps/jds/static/js/controller/pages/sjzx/dzwd/ywlc.js') },
            { name: '民警制卡', icon: 'icon-taizhang', mod: require('/apps/jds/static/js/controller/pages/sjzx/dzwd/ywtz.js') }
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

