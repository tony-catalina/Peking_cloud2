$(function () {
    initApplication();
});

var initevent = function () {
    // Tab切换
    var tab_ = $("[xy-tab]");
    tab_.on("click", function () {
        $(this).addClass('xy-tab-active');
        $(this).siblings().removeClass('xy-tab-active');
        var cnt_ = $(this).attr("tabcontent");
        $("."+ cnt_).show();
        $("."+ cnt_).siblings().hide();
    });
    // 评价中文简体下拉事件
    $("#btn-ft").on("click", function () {
        if($("#slt-sub").attr("state_") === "close"){
            $("#slt-sub").show();
            $("#slt-sub").attr("state_","open");
        } else {
            $("#slt-sub").hide();
            $("#slt-sub").attr("state_","close");
        }
    });
    // 评价中文简体下拉框中选择点击事件
    $("#slt-sub .slt-sub-item").on("click", function () {
            $("#slt-sub").hide();
            $("#slt-sub").attr("state_","close");
    });

    // 评价有用下拉事件
    $("#btn-sd").on("click", function () {
        if($("#slt-sub-s").attr("state_") === "close"){
            $("#slt-sub-s").show();
            $("#slt-sub-s").attr("state_","open");
        } else {
            $("#slt-sub-s").hide();
            $("#slt-sub-s").attr("state_","close");
        }
    });
    // 评价有用下拉框中选择点击事件
    $("#slt-sub-s .slt-sub-item").on("click", function () {
        $("#slt-sub-s").hide();
        $("#slt-sub-s").attr("state_","close");
    });

    // 支持所有框下拉事件
    $("#slt-lt").on("click", function () {
        if($("#slt-lst").attr("state_") === "close"){
            $("#slt-lst").show();
            $("#slt-lst").attr("state_","open");
        } else {
            $("#slt-lst").hide();
            $("#slt-lst").attr("state_","close");
        }
    });
    // 支持所有框下拉框中选择点击事件
    $("#slt-lst .slt-lst-item").on("click", function () {
        $("#slt-lst").hide();
        $("#slt-lst").attr("state_","close");
    });
};

var initApplication = function () {
    initevent();
    var mySwiper = new Swiper ('#swiper-container-tp', {
        direction: 'horizontal', // 水平切换
        loop: true, // 循环模式
        autoplay: {
            delay: 3000,
        }, // 自动轮播

        // 分页器
        pagination: {
            el: '.swiper-pagination',
            clickable :true,
        },

        // 前进后退按钮
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    })
    var mySwiper = new Swiper ('#swiper-container-bm', {
        direction: 'horizontal', // 水平切换
        loop: true, // 循环模式
        freeMode: true, //自由模式
        autoplay: {
            delay: 3000,
        }, // 自动轮播
        // 前进后退按钮
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    })
}

var IEVersion = function() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion == 8) {
            $('#AppPic').slideBox();
            initevent();
        } else if(fIEVersion == 9) {
            $('#AppPic').slideBox();
            initevent();
        } else if(fIEVersion == 10) {
            $('#AppPic').slideBox();
            initevent();
        } else if(fIEVersion == 11) {
        }  else {
            return 6;//IE版本<=7
        }
    } else if(isEdge) {
        return 'edge';//edge
    } else{
    }
}