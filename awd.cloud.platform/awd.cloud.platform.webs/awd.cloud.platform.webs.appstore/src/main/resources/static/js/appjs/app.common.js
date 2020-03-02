$(function () {
    resizeMh();
    $("#logo-icon").attr("src", "/assets/img/fengche.png");
    evenBind();
    linkHandle();
    $(window).on("resize", function () {
        resizeMh();
    });
});

// 页面跳转链接转换
var linkHandle = function () {
    var links = document.querySelectorAll('a');
    for (var i = 0; i < links.length; i++) {
        var link = links[i],
            link_desc = link.getAttribute("href") + "";
        if (!(link_desc.indexOf("javascript") !== -1)) {
            link.setAttribute("href", pathname + link_desc);
        }
    }
};

var resizeMh = function () {
    if((Number($('html').width())+16) < 1216){
        $(".header-nr").css("max-width", "960px");
        $(".dper-main-sy").css("width", "960px");
        $(".main-right").css("width", "720px");
    } else {
        $(".header-nr").css("max-width", "1200px");
        $(".dper-main-sy").css("width", "1200px");
        $(".main-right").css("width", "960px");
    }
};

// 全局事件绑定
var evenBind = function () {
    // 设置按钮点击事件
    var setbtn_ = $("#hs-set"),
        sltmsg_ = $("#set-sub-msg");
    setbtn_.on("click", function () {
        if (sltmsg_.attr("state_") === "close") {
            setbtn_.addClass("active");
            sltmsg_.show();
            sltmsg_.attr("state_", "open");
        } else {
            sltmsg_.hide();
            setbtn_.removeClass("active");
            sltmsg_.attr("state_", "close");
        }
    });
    $("#set-sub-msg .sub-msg-item").on("click",function () {
        sltmsg_.hide();
        setbtn_.removeClass("active");
        sltmsg_.attr("state_", "close");
    });
};
