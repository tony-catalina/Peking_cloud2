/**
 * 配置信息
 */
define(function () {
    var baseinter = "/";
    return {
        /**
         * 接口
         */
        interface: {
            demo: baseinter + "kss/queryJbxxCount",
            // zyrs_url: baseinter + "kss/queryJbxxCount" ,//z总关押量,

            zyrs_url:"/zddp/gylQuery",//关押量
            kss_url:"/zddp/findKss_dsjPT",//看守所 拘留所
            kss_url2:"/zddp/select_Jsjy",//监所羁押
            kss_url3:"/zddp/select_Dagy",//全市监所安全管控情况
            kss_url4:"/zddp/select_QSCSJY",//就医
            kss_url5:"/zddp/select_AYFX",//案由分析
            kss_url6:"/zddp/select_JSJL",//监所警力
            kss_url7:"/zddp/select_JGRYSY",//监管人员收押
            kss_url8:"/zddp/findZfzlk",//执法质量考评
            kss_dd:"/zddp/select_GQSXX",//地图红点
            kss_jyByjbxx:"/zddp/swjyByjbxx"//地图红点
        },

        /**
         * 接口返回数据格式配置
         */
        ajax: {
            //  接口状态CODE
            STATE: {
                NAME: "result",
                SUCVAL: true
            },
            // 接口状态信息
            MESSAGE: "msg",
            // 接口数据
            DATA: "data",
        },

        /**
         * 定时器
         */
        inter: {
            // 普通模块刷新时间
            ref_: 5000,
            // 地图区域切换时间
            map_area: 5000,
            // 本年征收
            exptyer: 20000
        },

        /**
         * 图表通用配置
         */
        ecommon_option: {
            loading: {
                text: '加载中...',
                color: '#12dbff',
                textColor: '#12dbff',
                maskColor: 'rgba(0, 0, 0, 0.2)',
                zlevel: 1
            }
        }
    }
})
