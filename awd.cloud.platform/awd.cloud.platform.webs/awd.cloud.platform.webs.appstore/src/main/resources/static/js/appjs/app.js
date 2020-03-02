$(document).ready(function () {
    initEvents();
    IEVersion();
});
var initEvents = function () {
    // 一进入页面搜索框发生聚焦事件
    $("#input-search").focus();
    $("#search-input-qy").addClass("qy-active");
    //
    $("#input-search").on('focus', function () {
        $("#search-input-qy").addClass("qy-active");
    });
    // 搜索框内容实时监听
    $("#input-search").on('input', function () {
        inputSF();
    });
    // 搜索框失去焦点事件
    $("#input-search").on('blur', function () {
        $("#search-input-qy").removeClass("qy-active");
    });
    // 收索框数据改变事件
    $("#input-search").on('change', function () {
        $("#search-input-qy").removeClass("qy-active");
        inputSF();
    });
    // 点击输入框的消除文字按钮
    $("#input-emp").on("click", function () {
        $("#input-search").val('');
        $("#input-emp").hide();
        $("#input-search").focus();
    });
    function inputSF() {
        var str =  $("#input-search").val();
        str = str.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
        if (str == '' || str == undefined || str == null) {
            $("#input-emp").hide();
        } else {
            $("#input-emp").show();
        }
    }

    // 扩展程序和主题背景点击事件
    /*$("[btn-item-app]").on("click", function () {
       $(this).addClass("btn-item-app-active");
       $(this).siblings().removeClass("btn-item-app-active");
    });*/

//    // 全部下拉框点击事件
//    $("#all-btn-asd").on("click", function () {
//        if( $(this).attr("state_") === "false" ){
//            $("#slt-msg").show();
//             $(this).attr("state_","open");
//         } else {
//            $("#slt-msg").hide();
//             $(this).attr("state_","false");
//         }
//    });
//    // 全部下拉框的鼠标摸上去和移出去样式变化事件
//    $("#slt-msg .msg-item").on("mouseover", function () {
//        $(this).addClass("msg-item-active");
//        $(this).siblings().removeClass("msg-item-active");
//    }).on("mouseout",function () {
//        $(this).removeClass("msg-item-active");
//        $(this).siblings().removeClass("msg-item-active");
//    }).on("click",function () {
//        $("#slt-msg").hide();
//        $("#all-btn-asd").attr("state_","false");
//    });

    // 功能点击事件
    $("[input-d-s-gn]").on("click", function () {
       $("#gz-bdg-gn").show();
    });
    // 功能全部不打勾事件
    $("#gz-bdg-gn").on("click", function () {
        IEVersion();
        $("[input-d-s-gn] input").prop("checked",false);
        $("#gz-bdg-gn").hide();
    });

    // 评分点击事件
    $("[input-d-s-score]").on("click", function () {
        $("#gz-bdg-score").show();
    });
    // 评分全部不打勾事件
    $("#gz-bdg-score").on("click", function () {

        $("[input-d-s-score] input").prop("checked",false);
        $("#gz-bdg-score").hide();
    });
};

var initApplication = function () {
    browse();
  //获取浏览器
    var mySwiper = new Swiper ('.swiper-container', {
        direction: 'horizontal', // 水平切换
        loop: true, // 循环模式
        autoplay: {
            delay: 3000,
        }, // 自动轮播

        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination',
            clickable :true,
        },

        // 如果需要前进后退按钮
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    })

};



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
            IEInputCss();
            $('#demo2').slideBox();
            $("input[type=checkbox]+label").css({
                "background-color":"",
                "background-clip":"",
                "padding":"",
                "display":"inline-block"
            })
        } else if(fIEVersion == 9) {
            IEInputCss();
            $('#demo2').slideBox();
            $("input[type=checkbox]+label").css({
                "background-color":"",
                "background-clip":"",
                "padding":"",
                "display":"inline-block"
            })
        } else if(fIEVersion == 10) {
            IEInputCss();
            $('#demo2').slideBox();
            $("input[type=checkbox]+label").css({
                "background-color":"",
                "background-clip":"",
                "padding":"",
                "display":"inline-block"
            })
        } else if(fIEVersion == 11) {
            initApplication();
        }  else {
            return 6;//IE版本<=7
        }
    } else if(isEdge) {
        return 'edge';//edge
    } else{
        initApplication();
    }
}

var IEInputCss = function(){
    $("#input-search").css({
        "position":"absolute",
        "top":"87px",
        "left":"25px",
        "height":"30px",
        "width":"150px"
    })

   $("input[type=checkbox]+label").on("click",function(){

       if($(this).css("display") == 'inline-block'){
           $(this).css({
               "background-color":"#01cd78",
               "background-clip":"content-box",
               "display":"block"
           })

       }else if($(this).css("display") == 'block'){
           $(this).css({
               "background-color":"",
               "background-clip":"",
               "padding":"",
               "display":"inline-block"
           });
       }

   })

}

var PostType = function(code){
    if(code != 200){
        $('#unseccessfulPost').modal('show')
    }
}

