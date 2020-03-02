requirejs.config({
    baseUrl: '/apps/swj/static/js/controller',
    shim: {
        'easyui': ['jquery'],
        'watermark':['jquery']
    },
    paths: {
        'jquery': '../../lib/jquery.min',
        'easyui': '../../lib/jquery.easyui.min',
        'mxgraph':'../../lib/flowchart/js/mxClient',
        'gridexport': '../../lib/datagrid-export',
        'pdfmake': '../../lib/pdfmake/pdfmake.min',
        'vfsfonts': '../../lib/pdfmake/vfs_fonts',
        'easyuiZh': '../../lib/easyui-lang-zh_CN',
        'echarts': '../../lib/echarts.min',
        'artTemplate':'../../lib/arttemplate/template-web',
        'bootstrap':'../../lib/bootstrap/js/bootstrap.min',
        'swiper':'../../lib/swiper-4.1.6/js/swiper.min',
        'uikit':'../../lib/uikit/dist/js/uikit.min',
        'uikiticon':'../../lib/uikit/dist/js/uikit-icons.min',
        'sha1': '../../lib/sha1',
        'cookie': '../../lib/js.cookie',
        'watermark':'../../lib/jquery.watermark',
        'layer':'../../lib/layer',
        'charts':'../../lib/charts/Chart.min',
        'time':'../../lib/time',
        'countUp':'../../lib/countUp/countUp.min',
        'dep_string':'../../lib/dper/dperUtils/string'
    }
});
