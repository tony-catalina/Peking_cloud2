/**
 * 配置信息
 */
define(function () {
    var baseinter = BASEURL;
    return {
        /**
         * 接口
         */
        interface: {
            // demo: "static/assets/json/simdata.json",
            demo:"/html/kss/dv_bj_prison/static/assets/json/simdata.json",
            box0:"/kssdp/select_fzx",//法制型
            box1:"/kssdp/select_fwx",//服务型
            box6:"/kssdp/select_aqx",//安全型
            box7:"/kssdp/select_jspf",//监所评分
            box2:"/kssdp/select_zhx",//智慧型
            box3:"/kssdp/select_ljx",//廉洁型
        },

        /**
         * 接口返回数据格式配置
         */
        ajax: {
            //  接口状态CODE
            STATE: {
                NAME: "code",
                SUCVAL: 200
            },
            // 接口状态信息
            MESSAGE: "msg",
            // 接口数据
            DATA: "data"
        },

        /**
         * 全局任务
         */
        globalTask: {
            // 性能监控
            stats: false
        },
        /**
         * 菜单
         */
        menus: [
          {
            name: "全市收入总体情况",
            url: "pages/appIndex.html"
          },
          {
            name: "管户情况",
            url: "pages/appManage.html"
          },
          {
            name: "征管质量",
            url: "pages/appCollection.html"
          },
          {
            name: "税收风险",
            url: "pages/demo-ps.html"
          },
          {
            name: "再生资源分析",
            url: "pages/demo-ps.html"
          }
        ],

        /**
         * 定时器
         */
        inter: {

        }
    };
});
