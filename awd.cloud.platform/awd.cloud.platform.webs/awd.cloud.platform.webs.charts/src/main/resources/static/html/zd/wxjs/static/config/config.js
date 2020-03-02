/**
 * 配置信息
 */
define(function (require) {
  require("jquery");
  var baseinter = BASEURL;
  return {
    /**
     * 接口
     */
    interface: {
      demo: "/html/zd/wxjs/static/assets/json/simdata.json",
      box0: "/Wxjsdp/select_zsry",//在所人员
      box1: "/Wxjsdp/kssdpaqgl",//安全管理
      box2: "/Wxjsdp/select_ylgl",//分所大屏医疗管理
      box7: "/Wxjsdp/select_Jqwg", //监区违规情况趋势图
      box15:"/Wxjsdp/select_jqwgryxx",//监区违规情况趋势图弹窗
      box16:"/Wxjsdp/select_wgcd_rybh",
      box12: "/Wxjsdp/select_txhj",//分所大屏提讯会见
      box13: "/Wxjsdp/select_gyqx",//分所大屏关押期限
      box3: "/Wxjsdp/select_jqbb",//分所大屏各监区抱病情况趋势图
      box8: "/Wxjsdp/select_zbld",//分所大屏值班领导 在岗民警 协警
      box10: "/Wxjsdp/select_hdsj",//分所大屏各监区滑动卡牌
      // box6: "/kssqsfx/wgCount",//分所大屏各监区滑动卡牌
      box6: "/Wxjsdp/find_wgcd",//违规程度分析 "/kssqsfx/wgCount"
      box9: "/Wxjsdp/select_zdgz",//分所大屏各监区重点人员
      box14:"/Wxjsdp/select_zdgzry",//分所大屏各监区重点人员弹窗数据
      box4: "/Wxjsdp/select_ssqq",// 诉讼情况分类与犯罪类型分析
      // box5: "http://192.168.4.76:12104/DiTuDianJi/select_ssqq",// 诉讼情况分类与犯罪类型分析
      kss_jbxx:"/Wxjsdp/selectJbxx",//在押人员基本信息
      kss_ryqk:"/Wxjsdp/selectRyqk",//在押人员人员情况
      kss_rygx:"/Wxjsdp/selectRygx",//在押人员人员关系
      kss_fxys:"/Wxjsdp/selectFxys",//在押人员风险因素
      kss_fxdj:"/Wxjsdp/selectFxdj",//在押人员风险等级
      kss_ssqk:"/kssdp/select_ssqk",//在押人员诉讼情况
      kss_jbxxList:"/Wxjsdp/jbxxlist",//在押人员基本信息
      kss_zyrjjkqk:"/Wxjsdp/dpByjkqk",//在押人员就医情况
      kss_zyrwgsjcl:"/Wxjsdp/dpBywgsjcl"//在押人员违规情况情况

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
        name: "首页",
        url: "/html/zd/mapall/index.html"
        // url: "/html/zd/wxjs/pages/appIndex.html"
      },
      {
        name: "五型监所",
        url: "/html/kss/dv_bj_prison/pages/appCollection.html"
      },
      {
        name: "在押人员",
        url: "/html/zd/wxjs/pages/appManage.html"
      },
      {
        name: "安全管控",
        url: "/html/zd/mapall/index.html"
      },
      {
        name: "队伍管理",
        url: "/html/zd/mapall/index.html"
      },
      {
        name: "民警执法",
        url: "/html/zd/mapall/index.html"
      },
      {
        name: "诉讼保障",
        url: "/html/zd/mapall/index.html"
      }
    ],

    /**
     * 定时器
     */
    inter: {

    }
  };
});