define(function (require, exports, module) {
    /************************************************  时间工具   *********************************************************/

    /** * 对Date的扩展，将 Date 转化为指定格式的String * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
     可以用 1-2 个占位符 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) * eg: * (new
     Date()).pattern("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423
     * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
     * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
     * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
     * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
     */
    Date.prototype.pattern=function(fmt) {
        var o = {
            "M+" : this.getMonth()+1, //月份
            "d+" : this.getDate(), //日
            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
            "H+" : this.getHours(), //小时
            "m+" : this.getMinutes(), //分
            "s+" : this.getSeconds(), //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S" : this.getMilliseconds() //毫秒
        };
        var week = {
            "0" : "天",
            "1" : "一",
            "2" : "二",
            "3" : "三",
            "4" : "四",
            "5" : "五",
            "6" : "六"
        };
        if(/(y+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        if(/(E+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "星期" : "周") : "")+week[this.getDay()+""]);
        }
        for(var k in o){
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }

    /**
     * 时间截转换为格式化时间
     * @param time		时间截
     * @param fmt		格式化字符
     */
    function coverToryDate(time, fmt) {
        return new Date(time).pattern(fmt);
    }

    /**
     * 当前日期减去多少天
     * @param date 		日期对象
     * @param days 		减去的天数
     * @param fmt 		输出格式
     */
    function reduceDays(date, days, fmt){
        return new Date(date.setDate(date.getDate() - days)).pattern(fmt);
    }


    /**
     * 秒  转换为  00+:00:00格式
     * @param seconds   秒
     */
    function timeFormatter(seconds) {
        // 时
        var h = parseInt(seconds/3600);
        h = h < 10?"0"+h:h;

        // 分
        var m = parseInt((seconds-Number(h)*3600)/60);
        m = m < 10?"0"+m:m;

        // 秒
        var s = parseInt(seconds%60);
        s = s < 10?"0"+s:s;

        return h + ":" + m + ":" + s;
    }

    // 获取当日为本月的第几周
    var getMonthWeek = function () {
        var no_week = 0;

        // 月初为一周的第几天
        var first_week = new Date();
        first_week.setDate(1);
        first_week = getWeek(first_week.getDay());

        // 今天为一周的第几天
        var today_week = getWeek(new Date().getDay());

        // 今天为当月的第几天
        var today_mouth = new Date().getDate();

        // 第一周天数计算
        var firstweek_days = Number(8 - first_week);

        // 计算除去第一周和当前周所剩的天数
        var l_day = today_mouth - firstweek_days - today_week;

        // 计算最终周数
        no_week = l_day/7 + 2;

        /**
         * 获取1开始的星期几
         * @param dindex
         */
        function getWeek(dindex) {
            var arr_num = [7, 1, 2, 3, 4, 5, 6];
            return arr_num[dindex];
        }
        return no_week;
    };
    // 时间
    var showTime = function (el, fmt) {
        !fmt && (fmt = "yyyy年MM月dd日    HH:mm:ss");
        document.querySelector(el).innerHTML = new Date().pattern(fmt);
    }

    module.exports = {
        coverToryDate: coverToryDate,
        reduceDays: reduceDays,
        timeFormatter: timeFormatter,
        getMonthWeek: getMonthWeek,
        showTime: showTime
    }
});