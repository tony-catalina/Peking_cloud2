define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    './rylb.js',
    '/apps/jds/static/js/controller/pages/sjzx/rsxxwh/rylb.js',
    '/apps/jds/static/js/controller/pages/sjzx/rsxxwh/cxtj.js',
    '../../../modules/tabs.js'
],function(require,exports,module){
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');

    var tabs = require('../../../modules/tabs.js');

    var tabControl = new tabs.TabControl({
        items: [
            { name: '人员列表', icon: 'icon-liucheng', mod: require('/apps/jds/static/js/controller/pages/sjzx/rsxxwh/rylb.js') },
            { name: '查询条件', icon: 'icon-taizhang', mod: require('/apps/jds/static/js/controller/pages/sjzx/rsxxwh/cxtj.js') },
        ],
        onTabChange: function (element) {
            $.parser.parse(element);
        }
    });

    /* Templete模板函数 */
    var initContent = function(){
        document.body.appendChild(tabControl.render());
    }

    /* Templete模板函数 End*/

    /*  页面初始化执行函数 */
    var init = function(){
        initContent();
    }

    init();

    /* 页面初始化执行函数 End */

})