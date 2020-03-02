define(function (require, exports, module) {
    var menus =require('app_config').menus,
        DperDate = require("dperUtils/date")   // 时间工具
    ;
    // 不进行渲染的菜单
    var unRender = "管理中心";
    var initMenu = function (el_l, el_r) {
        var mark_ = 0;
        for(var i = 0; i < menus.length; i++){
            var itm = menus[i];
            var ms = "";
            if(itm.name !== unRender){
                mark_++;
                var isActive = (location.pathname.indexOf(itm.url) != -1)?" active":"";
                if(i<3){
                    ms = "            <li class=\"dper-menu font-16 " + isActive + "\" v-href='" + itm.url + "'>\n" +
                        "                <div class=\"dper-bg-ms malign-center bg-img-full dper-amt\"></div>\n" +
                        "                <a href=\"javascript:void(0);\" class=\"malign-center-text align-center dper-fw\">" + itm.name + "</a>\n" +
                        "            </li>";
                }else {
                    ms = "            <li class=\"dper-menu font-16 " + isActive + "\" v-href=''>\n" +
                        "                <div class=\"dper-bg-ms malign-center bg-img-full dper-amt\"></div>\n" +
                        "                <a href=\"#;\" class=\"malign-center-text align-center dper-fw\">" + itm.name + "</a>\n" +
                        "            </li>";
                }

                if(mark_ < 5){
                    el_l.insertAdjacentHTML("beforeend", ms);
                }else {
                    el_r.insertAdjacentHTML("beforeend", ms);
                }
            }
        }
        var els_ = document.querySelectorAll('.dper-menu');
        for (var i = 0; i < els_.length; i++) {
            var itm = els_[i];
            itm.addEventListener("click", function (event) {
                // var url="";
                    var url = this.getAttribute("v-href");
                if(!(location.pathname.indexOf(url) != -1)){
                    if($(this).children("a").eq(0).text()=="首页"){
                        $.ajax({
                            url:"/getusers",
                            success:function(reult){
                               console.log(reult)
                               if(reult.jsbh=="110000000"||reult.yjsbh=="110000000"){
                                src="/html/zd/mapall/index.html"
                               }else{
                                src="/html/zd/wxjs/pages/appIndex.html"
                               }
                               transitionRouteLeave(function () {
                                window.location.href = /*window.BASEURL + "/" +*/src;
                            });
                           }
            
                           })
                           
                    }else{
                        transitionRouteLeave(function () {
                            window.location.href = /*window.BASEURL + "/" +*/url;
                        });
                    }
                   
                }
            })
        }

        // 首页点击  回家0.0
        // document.querySelector('.dper-home').addEventListener("click", function () {
        //     transitionRouteLeave(function () {
        //         window.location.href = location.origin + "/" + (location.pathname.split('/')[1]) + "/";
        //     });
        // });
        // 时间
        showTimes();
    };


    // 时间
    var showTimes = function () {
        function st() {
            DperDate.showTime(".dper-time-t", "yyyy年M月d日");
            DperDate.showTime(".dper-time-b", "HH:mm:ss");
        }
        st();
        setInterval(st, 1000);
    }

    /**
     * 页面跳转动画 离开
     * @param cb 回调函数
     */
    var transitionRouteLeave = function (cb) {
      document.body.style.opacity = 0
      document.body.style.transition = 'all .3s'
      setTimeout(cb, 250);
    }

    /**
     * 页面跳转动画 进入
     */
    var transitionRouteEnter = function () {
      document.body.style.opacity = 1
    }

    // 进入
    transitionRouteEnter();

    module.exports = {
        initMenu: initMenu,
        transitionRouteLeave: transitionRouteLeave
    };
});
