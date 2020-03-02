seajs.config({
  base: '/apps/jds/static/js/controller',
  plugins: ['shim'],
  alias: {
    jquery: '/apps/jds/static/lib/jquery/jquery.min',
    easyui: '/apps/jds/static/lib/jquery/jquery.easyui.min',
    easyui_ZH: '/apps/jds/static/lib/jquery/easyui-lang-zh_CN',
    easyui_bufferview: '/apps/jds/static/lib/jquery/datagrid-bufferview',
    easyui_detailview: '/apps/jds/static/lib/jquery/datagrid-detailview',
    easyui_groupview: '/apps/jds/static/lib/jquery/datagrid-groupview',
    easyui_scrollview: '/apps/jds/static/lib/jquery/datagrid-scrollview',
    arttemplate: '/apps/jds/static/lib/arttemplate/template-web',
    Swiper: '/apps/jds/static/lib/swiper/swiper.min',
    echarts: '/apps/jds/static/lib/echarts/echarts.min',
    sJI: '/apps/jds/static/lib/class/extends',
    yer: '/apps/jds/static/lib/dper/yer.min',
    uikit: '/apps/jds/static/lib/uikit/dist/js/uikit.min',
    watermark:'/apps/jds/static/lib/jquery/jquery.watermark',
    // 数字滚动
    CountUp: '/apps/jds/static/lib/countUp/countUp.min',
    UnderScore: '/apps/jds/static/lib/underscore/underscore-min',
    mxclient:'/apps/jds/static/lib/flowchart/js/mxClient.min'
  }
});
