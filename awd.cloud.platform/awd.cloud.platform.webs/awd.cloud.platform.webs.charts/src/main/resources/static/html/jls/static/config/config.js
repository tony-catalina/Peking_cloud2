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
      demo: "/html/jls/static/assets/json/simdata.json",
      box0: "/jls/zsryCount",//在所人员
      box1: "/jls/jlsdpaqgl",//安全管理
      box2: "/jls/jlsdpylgl",//分所大屏医疗管理
      box7: "/jls/jlsdpjqwgqst", //监区违规情况趋势图
      box12: "/jls/jlsdptxhj",//分所大屏提讯会见
      box13: "/jls/jlsdpcqjy",//分所大屏关押期限
      box8:"/jls/jlsdpzbld",
      box9:"",
      box3: "/jls/jlsdpjqbb",//分所大屏各监区抱病情况趋势图
      box10: "/jls/jlsdpjqhdkp",//分所大屏各监区滑动卡牌
      box6: "/jls/jlsdpjqwgry",//违规程度分析
      //box9: "/Wxjsdp/select_zdgzry",//分所大屏各监区重点人员
      box4: "/jls/jlsdprsxz",// 诉讼情况分类与犯罪类型分析
      jls_ajlb: "/jls/jlsdpajlb",// 犯罪类型分析
      box14: "/jls/sjxsShow",
      jls_jbxxByphotot:"/jls/jbxxlistByp",//在押人员
      jls_jbxx:"/jls/jbxxlist",//在押人员基本信息
      xsjllist:"/jls/xsjllist",//违规人员信息
      //kss_rygx:"/Wxjsdp/selectRygx",//在押人员人员关系
      //kss_fxys:"/Wxjsdp/selectFxys",//在押人员风险因素
      jls_zyjbxx:"/jls/jlsgrxx",//在押人员基本信息
      jls_ryqk:"/jls/ryqk",//在押人员人员情况
      jls_rygx:"/jls/findRygx",//在押人员人员关系
      jls_fxys:"/jls/dpzyryFxys",//在押人员风险因素
      jls_flsxbg:"/jls/jlsflsxbg",
      jls_zyrjjkqk:"/jls/select_jy",//在押人员就医情况
      jls_zyrwgsjcl:"/jls/select_ajfx",//在押人员违规情况情况
      jls_fxdj:"/jls/jlsFxdj"//在押人员风险等级
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
        url: "/html/jls/index.html"
      },
      {
        name: "五型监所",
        url: "/html/kss/dv_bj_prison/pages/appCollection.html"
      },
      {
        name: "在押人员",
        url: "/html/jls/wxjs/pages/appManage.html"
      },
      {
        name: "安全管控",
        url: "/html/jls/index.html"
      },
      {
        name: "队伍管理",
        url: "/html/jls/index.html"
      },
      {
        name: "民警执法",
        url: "/html/jls/index.html"
      },
      {
        name: "诉讼保障",
        url: "/html/jls/index.html"
      }
    ],

    /**
     * 定时器
     */
    inter: {

    }
  };
});
