define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/views/pages/common/zpwh.html'
], function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');

    //var zpwhHtml = require('/apps/jds/views/pages/common/zpwh.html');

    exports.html = '<div class="section-container qa-container"><span style="text-align:center;font-size:150%;margin-top:2%;">规范说明</span><span style="font-size: 120%;margin-left: 2%;margin-top: 2%;">1，严格按照规定时间完成谈话。</span>'
    +'<span style="font-size: 120%;margin-left: 2%;margin-top: 1%;">2，认真记录谈话内容。</span></div>';
});

