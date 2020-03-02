// JavaScript Document
$(document).ready(function(){
	//重置
	remove();
    if(screen.width >= 3840) {
        $(".whole").addClass("whole_3840");
    }
	else if(screen.width >= 1920){
		$(".whole").addClass("whole_1920");
	}else if(screen.width >= 1600){
		$(".whole").addClass("whole_1600");
	}else if(screen.width >= 1440){
		$(".whole").addClass("whole_1440");
	}else if(screen.width >= 1366){
        $(".whole").addClass("whole_1366");
    }else{
		$(".whole").addClass("whole_1280");
	}
});

function remove() {
    $(".whole").removeClass("whole_3840");
	$(".whole").removeClass("whole_1920");
	$(".whole").removeClass("whole_1600");
	$(".whole").removeClass("whole_1440");
	$(".whole").removeClass("whole_1366");
	$(".whole").removeClass("whole_1280");
}

function Loading(isShow){
    if(isShow == true)
    $("#loading").fadeIn(100);
    else
    $("#loading").fadeOut(100);
}

//获取url中的参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}