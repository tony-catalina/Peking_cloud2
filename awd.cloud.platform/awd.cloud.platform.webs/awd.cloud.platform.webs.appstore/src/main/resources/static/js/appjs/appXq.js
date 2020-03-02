
$(document).ready(function(){
	anClick();
	GetPlList();
    searchAPP(0,100);
    anzh();
	IEVersion();

});

var selectOnchang = function(obj){
	var selecttype=obj.options[obj.selectedIndex].value;
	if('01'==selecttype){
		$("#jsbhField").show();
	}
	if('02'==selecttype){
		$("#jsbhField").hide();
		$("#jsbhSelect").val('999999999');
	}
	if('03'==selecttype){
		$("#jsbhField").hide();
		$("#jsbhSelect").val('000000000');
	}
}

//查找应用
var searchAPP = function(pageIndex,pageSize) {
	var classfic = $("#classfic").val();
	if(pageSize == undefined){
		pageSize = 12;
	}
	if(pageIndex == undefined){
		pageIndex = 0;
	} 
    var querydata = 'classfic=' + classfic + '&pageIndex=' + pageIndex +'&pageSize=' + pageSize; 
    $.ajax({
        url: '/apps',// 跳转到 action
        data: querydata,
        type: 'post',
        cache: false,
        async: false,
        success: function (data) {
        	loadAppImg(data.result.data);
        },
		error:function(data){
			PostType(data.status);
		}
    });
}

var loadAppImg = function(app_data){
	$("div").remove("#xgApp div");
    for(var i=0;i < app_data.length;i++){
        var app = app_data[i];
        var id = app['id'];
        var appcode = app['appcode'];
        var appEle = '<div class="cx-kr">'+
        	'	<a class="cx-kr-wm">'+
        	'		<div class="kr-wm-s ">'+
        	'			<div class="wm-s-asd" >'+
        	'				<div class="s-asd-tp">'+
        	'					<div class="asd-tp-qy" style="margin-top: -3px;">'+
        	'						<img src="../images/icon/'+app['icon']+'" class="tp-qy-img" >'+
        	'					</div>'+
        	'				</div>'+
        	'				<div class="s-asd-pf">'+
        	'					<div class="asd-star-ft">'+
        	'						<span title="'+app['name']+'">'+ app['name'] +'</span>'+
        	'					</div>'+
        	'					<div class="pf-cx-name">'+
        	'						<span title="'+app['roleString']+'">'+ '应用岗位:'+app['roleString']+'</span>'+
        	'					</div>'+
        	'					<div class="pf-star-asd">'+
        	'						<div class="star-asd" title="">'+
        	'							<div class="pf-cx-name">'+
        	'								<div class="star-lm">'+'应用范围:'+app['zoneString']+'</div>'+
        	'							</div>'+
        	'						</div>'+
        	'					</div>'+
        	'				</div>'+
        	'			</div>'+
        	'		</div>'+
        	'	</a>'+
        	'	<div class="cx-kr-lm">'+
        	'		<div class="kr-lm-s">'+
        	'			<a class="lm-s-lk">'+
        	'				<div class="s-lk-msg">'+
        	'					<div class="lk-msg-tj">'+ app['name'] +'</div>'+
        	'					<div class="lk-msg-btn">'+
        	'						<div class="msg-btn-s">'+
        	'							<div class="btn-s-qy">'+
        	'								<div class="s-qy-fw">'+
        	'									<div  onclick="_iframeApp(\''+id+'\',\''+appcode+'\')" style="cursor: pointer" class="qy-fw-txt  webstore-test-button-label">查看详情</div>'+
        	'								</div>'+
        	'							</div>'+
        	'						</div>'+
        	'					</div>'+
        	'				</div>'+
        	'			</a>'+
        	'		</div>'+
        	'	</div>'+
        	'</div>';
//        alert(appEle)
        $("#xgApp").prepend(appEle);
    }
}

var IEloadAppImg = function(app_data){
	$("div").remove("#xgApp div");
	for(var i=0;i < app_data.length;i++){
		var app = app_data[i];
		var id = app['id'];
		var appcode = app['appcode'];
		var appEle = '<div class="cx-kr">'+
			'	<a class="cx-kr-wm">'+
			'		<div class="kr-wm-s ">'+
			'			<div class="wm-s-asd" >'+
			'				<div class="s-asd-tp">'+
			'					<div class="asd-tp-qy" style="margin-top: -3px;">'+
			'						<img src="../images/icon/'+app['icon']+'" class="tp-qy-img" >'+
			'					</div>'+
			'				</div>'+
			'				<div class="s-asd-pf">'+
			'					<div class="asd-star-ft">'+
			'						<span title="'+app['name']+'">'+ app['name'] +'</span>'+
			'					</div>'+
			'					<div class="pf-cx-name">'+
			'						<span title="'+app['roleString']+'">'+ '应用岗位:'+app['roleString']+'</span>'+
			'					</div>'+
			'					<div class="pf-star-asd">'+
			'						<div class="star-asd" title="">'+
			'							<div class="pf-cx-name">'+
			'								<div class="star-lm">'+'应用范围:'+app['zoneString']+'</div>'+
			'							</div>'+
			'						</div>'+
			'					</div>'+
			'				</div>'+
			'			</div>'+
			'		</div>'+
			'	</a>'+
			'	<div class="cx-kr-lm">'+
			'		<div class="kr-lm-s">'+
			'			<a class="lm-s-lk" onclick="_iframeApp(\''+id+'\',\''+appcode+'\')">'+
			'				<div class="s-lk-msg">'+
			'					<div class="lk-msg-tj">'+ app['name'] +'</div>'+
			'					<div class="lk-msg-btn">'+
			'						<div class="msg-btn-s">'+
			'							<div class="btn-s-qy">'+
			'								<div class="s-qy-fw">'+
			'									<div class="qy-fw-txt  webstore-test-button-label">查看详情</div>'+
			'								</div>'+
			'							</div>'+
			'						</div>'+
			'					</div>'+
			'				</div>'+
			'			</a>'+
			'		</div>'+
			'	</div>'+
			'</div>';
//        alert(appEle)
		$("#xgApp").prepend(appEle);
	}
}

function _iframeApp(id, appcode) {
	var flag = "";
	var appcode = encodeURIComponent(appcode);
	$.ajax({
		url : "./listJsapp?appcode=" + appcode,// 跳转到 action
		type : 'post',
		cache : false,
		async : false,
		success : function(data) {
			var total = data.result.result.total;
			if (total > 0) {
				flag = "1";
			} else {
				flag = "0"
			}
			if(data){
				postToPage(id,appcode,flag);
			}
		}
	});
}

//隐藏参数调转页面
function postToPage(id,appcode,flag) {
	var type = $("#typefm").val();
	var appcode = decodeURIComponent(appcode);
    var form = $("<form>");
    form.attr("style","display:none");
    form.attr("target","");
    form.attr("method","post");
    //请求地址
    form.attr("action","/appXq");
    var input1 = $("<input>");
    input1.attr("type","hidden");
    input1.attr("name","id");
    input1.attr("value",id);
    var input2 = $("<input>");
    input2.attr("type","hidden");
    input2.attr("name","appcode");
    input2.attr("value",appcode);
    var input3 = $("<input>");
    input3.attr("type","hidden");
    input3.attr("name","flag");
    input3.attr("value",flag);
    var input4 = $("<input>");
    input4.attr("type","hidden");
    input4.attr("name","type");
    input4.attr("value",type);
    $("body").append(form);
    form.append(input1);
    form.append(input2);
    form.append(input3);
    form.append(input4);
    form.submit();
    form.remove();
}

var anClick = function() {
	$('#tjan').off('click').on('click', function() {
		var glybz = $("#glybz").val();
		var jsbh = $("#jsbh").val();
		var appcode = $("#appcode").val();
		var userType = $("#userType").val();
		var appZone = $("#classfic").val();
		var flag = false;
		if(glybz == '1' && (jsbh =="999999999" || jsbh =="000000000")){
			$("#appCodecontext").html(appcode);
			$("#AppCodemyModal").modal();
			$('h4').html("AppCode");
		}else{
		for (var i = 0; i < appZone.length; i++) {
			if (userType == appZone[i]||appZone[i]=="9") {
				flag = true;
			}
		}
		if (jsbh == "1" && glybz == "2") {
			$("#content").html("请管理员用户登陆后进行此操作");
			$("#myModal").modal();
		} else if (!flag) {
			$("#content").html("您无权使用当前应用！");
			$("#myModal").modal();
		} else {
			var an = $("#tjan").text();
			var appcode = $('#appcode').val();
			appcode = encodeURIComponent(appcode);
			if (an == "添加至桌面") {
				azyy(appcode);
			} else if (an == "卸载应用") {
				xzyy(appcode);
			}
		}
		}
	})
	
	
	//复制按钮
	$('#copyText').on('click', function() {
		var text = document.getElementById("appCodecontext").innerText;
		var input = document.getElementById("input");
	      input.value = text; // 修改文本框的内容
	      input.select(); // 选中文本
	      document.execCommand("copy"); // 执行浏览器复制命令
	      $('#AppCodemyModal').modal('hide');
	      $("#content").html("复制成功");
	      $("#myModal").modal();
	})
}

// 安装应用
var azyy = function(appcode) {
	$.ajax({
		url : "./addJsapp?appcode=" + appcode,// 跳转到 action
		type : 'post',
		cache : false,
		async : false,
		success : function(data) {
			// alert("应用安装成功！");
			$("#content").html("应用安装成功!");
			$("#myModal").modal();
			$("#tjan").text("卸载应用");
		}
	});
}

// 卸载应用
var xzyy = function(appcode) {
	$.ajax({
		url : "./delJsapp?appcode=" + appcode,// 跳转到 action
		type : 'post',
		cache : false,
		async : false,
		success : function(data) {
			// alert("应用卸载成功！");
			$("#content").html("应用卸载成功!");
			$("#myModal").modal();
			$("#tjan").text("添加至桌面");
		}
	});
}

var GetPlList = function(){  /** 获取App页面 评论* */
	var name = $("#userName").val();
	var appcode = $("#appcode").val();
	var pageIndex = 0;
	var pageSize = 20;
	appcode = encodeURIComponent(appcode);
	var querydata = 'appcode=' + appcode + '&pageIndex=' + pageIndex +'&pageSize=' + pageSize;
	$.ajax({
		type: 'POST',
		url: '/getAppPl',
		cache: false,
		async: false,
		data: querydata,
		success:function(data){
			GetPl(data.result.data);
		},
		error:function(data){
			PostType(data.status);
		}
	});
	return false;
}



var GetPl = function(List){ /**加载App页面 评论**/
	var divEle;
	for(var i = 0 ; i < List.length ; i++){
       divEle = $("<div class=\"zt-msg\"></div>").appendTo($(".nr-area-zt"));
       var Plcontent = List[i].content;
       var Pltime = List[i].createtime;
       if(Pltime == null || Pltime == ""){
       	 Pltime = "未知"
	   };
       var PlName = List[i].plr;
       if(PlName == null || PlName == ""){
       	 PlName = "匿名"
	   };
       var Plhtml =
		   '<div class="zt-msg">' +
		   '<div class="zt-msg-user">' +
		   '<img src="../../assets/img/extendC/photopj.1.jpg" class="user-tx">' +
		   '</div>' +
		   '<div class="zt-msg-info">' +
		   '<div class="info-user">' +
		   '<span class="user-name">'+ PlName +'</span>' +
		   '<span class="info-time">发表时间：'+Pltime +'</span>' +
		   '<div class="info-pf"></div>' +
		   '<br>' +
		   '<div class="info-ts">' + Plcontent +' </div>' +
		   '</div>' +
		   '<div class="info-btn-zs"></div>' +
		   '<div class="info-clear"></div>' +
		   '<div class="info-more"><a>加载更多回复</a></div>' +
		   '</div>' +
		   '</div>';
		var Pl_Html = $(Plhtml).appendTo(divEle);
	}
}

//按钮置灰
var anzh = function(){
	var glybz =$("#glybz").val();
	var jsbh =$("#jsbh").val();
	var flag = $("#flag").val();
	if(glybz == '1' && jsbh !="999999999" && jsbh !="000000000"){
		$("#anyc").css('display','block');
		if(flag == "1"){
			$("#tjan").text("卸载应用");
		}
	}else if(glybz == '1' && (jsbh =="999999999" || jsbh =="000000000")){
		//$("#appCode").css('display','block');
		$("#anyc").css('display','block');
		$("#tjan").text("查看AppCode");
	}
	
}

function dateFormat (date,format) { /** CST时间格式转换成Gbt格式方法函数 **/
	date = new Date(date);
	date.setHours(date.getHours()-14);
	var o = {
		'M+' : date.getMonth() + 1, //month
		'd+' : date.getDate(), //day
		'H+' : date.getHours(), //hour
		'm+' : date.getMinutes(), //minute
		's+' : date.getSeconds(), //second
		'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
		'S' : date.getMilliseconds() //millisecond
	};

	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));

	for (var k in o)
		if (new RegExp('(' + k + ')').test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));

	return format;
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

var IEAppimg = function() {
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
	var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
	var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
	if(isIE) {
		var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
		reIE.test(userAgent);
		var fIEVersion = parseFloat(RegExp["$1"]);
		if(fIEVersion == 8) {
              IEloadAppImg();
		} else if(fIEVersion == 9) {
			  IEloadAppImg();
		} else if(fIEVersion == 10) {

		} else if(fIEVersion == 11) {
		}  else {
			return 6;//IE版本<=7
		}
	} else if(isEdge) {
		return 'edge';//edge
	} else{
	}
}

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
