$(document).ready(function () {
	
	initApp();
	var pageIndex;
	var pageSize;
	searchAPP(pageIndex,pageSize,"","");
	appSearch(pageIndex,pageSize);
	showAllApps(pageIndex,pageSize);
	//ScreenWidth();
});

function ScreenWidth(){
	if (screen.width == 1440){
		console.info("1440*900");
	}else  if (screen.width == 800){
		console.info("800*600");
	}else if (screen.width == 1024){
		console.info("1024*768");
	}else if (screen.width == 1152){
		console.info("1152*864");
	}else {
		console.info("do not know!");
	}
}


var appSearch = function (pageIndex,pageSize) {
	$("#input-search").on('input', function () {
		ycDiv();
    	searchAPP(pageIndex,pageSize,"","");
    });
	
	// 类别点击事件
    $("[btn-item-app]").off('click').on("click", function (e) {
    	//$("[input-d-s-gn] input").prop("checked",false);
    	$(this).addClass("btn-item-app-active");
        $(this).siblings().removeClass("btn-item-app-active");
        var zone = $(this).children('input[name=classfic]').val();
    	ycDiv();
    	searchAPP(pageIndex,pageSize,"","",zone);
    });
	
	// 功能点击事件
    $("[input-d-s-gn]").on("click", function () {
    	var zone = $(".btn-item-app-active").children('input[name=classfic]').val();
    	ycDiv();
    	searchAPP(pageIndex,pageSize,"","",zone);
    });
    
    // 功能全部不打勾事件
    $("#gz-bdg-gn").on("click", function () {
    	var zone = $(".btn-item-app-active").children('input[name=classfic]').val();
    	ycDiv();
    	searchAPP(pageIndex,pageSize,"","",zone);
    });
    
    // 评分点击事件
    $("[input-d-s-score]").on("click", function () {
    	ycDiv();
    	searchAPP(pageIndex,pageSize,"","");
    });
    
    //下拉框change事件
    $("#rolefm").on('change',function(){
    	ycDiv();
    	searchAPP(pageIndex,pageSize,"","");
    });
    
    // 评分全部不打勾事件
    $("#gz-bdg-score").on("click", function () {
    	ycDiv();
    	searchAPP(pageIndex,pageSize,"","");
    });
    
};

//查看全部 按钮的事件
var showAllApps = function(pageIndex,pageSize){
    //查看全部
    var width;  //窗口宽度
	$(".hh-all").on("click", function() {
		width = $("#addAppModal").width();
		var rows = Math.floor(($(window).height() - 230) / 160);
		var pageSize = Math.floor(width / 213) * rows;
		var title = $(this).attr("value");
		$('h4').html(title);
		if (title == "看守所") {
			$(".Allapps").attr('id', 'kssAllapps');
		} else if (title == "拘留所") {
			$(".Allapps").attr('id', 'jlsAllapps');
		} else if (title == "戒毒所") {
			$(".Allapps").attr('id', 'jdsAllapps');
		} else if (title == "收容教育") {
			$(".Allapps").attr('id', 'srjyAllapps');
		} else if (title == "监护医疗所") {
			$(".Allapps").attr('id', 'jhylsAllapps');
		} else if (title == "强制医疗所 ") {
			$(".Allapps").attr('id', 'qzylsAllapps');
		} else if (title == "监管总队") {
			$(".Allapps").attr('id', 'jgzdAllapps');
		} else if (title == "其他") {
			$(".Allapps").attr('id', 'qtAllapps');
		}
		var classficed = $(this).attr("classfics");
		$(".Allapps").attr('classfics', classficed);
		searchAPP(pageIndex, pageSize, classficed, "");
	});
    
    $("#queryfm").on("click", function () {
    	var rows = Math.floor(($(window).height()-230)/170);
		var pageSize = Math.floor(width/213)*rows;
    	var classficed = $('.Allapps').attr("classfics");
    	var appname = $('#appNamefm').val();
    	searchAPP(pageIndex,pageSize,classficed,appname);
    })
}



//查找应用
var searchAPP = function(pageIndex,pageSize,classficed,appname,zone) {//这个classfic是首页所类别的参数
	if(pageSize == undefined){
		pageSize = 1000;
	}
	if(pageIndex == undefined){
		pageIndex = 0;
	} 
	var querydata=$("#searchForm").serialize();
	if(classficed!=null&&classficed!=undefined&&classficed!=""){
		querydata = "namelike="+appname + "&classfic="+classficed + '&pageIndex=' + pageIndex +'&pageSize=' + pageSize; 
	}else {
	    querydata = querydata + '&pageIndex=' + pageIndex +'&pageSize=' + pageSize; 
	}
	
    $.ajax({
        url: '/apps',// 跳转到 action
        data: querydata,
        type: 'post',
        cache: false,
        async: false,
        success: function (data) {
        	if(classficed!=null&&classficed!=undefined&&classficed!=""){
        		loadAppAll(data.result.data);
        		var totalRecords = data.result.total;
        		var totalPage;
        		if(totalRecords % pageSize == 0){
        			totalPage = Math.floor(totalRecords/pageSize);
        		}else{
        			totalPage = Math.floor(totalRecords/pageSize) + 1;
        		}
        		init_showAllApp_kkpager(pageSize,totalPage,totalRecords,classficed);
        	}else if (zone!=null&&zone!=undefined&&zone!="") {
        		loadAppImg(data.result.data,zone);
			}else {
        		loadAppImg(data.result.data);
        	}
        },
        error:function(data){
			PostType(data.status);
		}
    });
}


var init_showAllApp_kkpager = function(pageSize,totalPage,totalRecords,classficed){	//首页的查看更多按钮弹出的模态框的分页
	//生成分页
	kkpager.generPageHtml({
		pno : 1,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		mode : 'click',//默认值是link，可选link或者click
		click : function(click_page){
			// do something
			//手动选中按钮
			this.selectPage(click_page);
			//return false;
			var pageIndex = totalPage;
			var appname = $('#appNamefm').val();
			var querydata = "namelike="+appname + "&classfic="+classficed + '&pageIndex=' + (click_page-1) +'&pageSize=' + pageSize; 
	       $.ajax({
	            url: '/apps',// 跳转到 action
	            data: querydata,
	            type: 'post',
	            cache: false,
	            async: false,
	            success: function (data) {
	            	loadAppAll(data.result.data);
	            },
	            error:function(data){
					PostType(data.status);
				}
	        }); 
		}
	},true);
}


//init
var init_kkpager = function(pageSize,totalPage,totalRecords){
	//生成分页
	kkpager.generPageHtml({
		pno : 1,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		mode : 'click',//默认值是link，可选link或者click
		click : function(click_page){
			// do something
			//手动选中按钮
			this.selectPage(click_page);
			//return false;
			var pageIndex = totalPage;
			var querydata=$("#searchForm").serialize();
		    querydata = querydata + '&pageIndex=' + (click_page-1) +'&pageSize=' + pageSize; 
	       $.ajax({
	            url: '/apps',// 跳转到 action
	            data: querydata,
	            type: 'post',
	            cache: false,
	            async: false,
	            success: function (data) {
	            	loadAppAll(data.result.data);
	            }
	        }); 
		}
	},true);
}

//查看更多
var loadAppAll = function(app_data){
		$("div").remove("#kssAllapps div");
		$("div").remove("#jlsAllapps div");
		$("div").remove("#jdsAllapps div");
		$("div").remove("#srjyAllapps div");
		$("div").remove("#jhylsAllapps div");
		$("div").remove("#qzylsAllapps div");
		$("div").remove("#jgzdAllapps div");
		$("div").remove("#qtAllapps div");
    for(var i=0;i < app_data.length;i++){
        var app = app_data[i];
        var id = app['id'];
        var appcode = app['appcode'];
        var icon = 'L_'+app['icon'];
        var info = app['info'];
        var appInfo;
        if(info == ""){
        	appInfo = "查看详情"
        }else{
        	appInfo = info;
        }
        var appEle = '<div class="module-nt">'+
        	'                        <a class="nt-lk"'+
        	'                           href="#" onclick="_iframe(\''+id+'\',\''+appcode+'\')">'+
        	'                            <div class="lk-nr">'+
        	'                                <div class="nr-df"></div>'+
        	'                                <div class="nr-zt">'+
        	'									<div align="center" class="asd-tp-qy">'+
        	'                                   	<img style="width:213px;height:100px" class="tp-qy-img" src="../images/icon/'+icon+'">'+
        	'									</div>'+
        	'                                    <div class="zt-txt" style="font-size:15px">'+appInfo+'</div>'+
        	'                                    <div align="center" style="margin-top:22px" class="zt-kz">'+
        	'                                        <div class="kz-txt" style="font-size:15px;" title="'+app['name']+'">'+app['name']+
        	'                                        </div>'+
        	'                                    </div>'+
        	'                                </div>'+
        	'                            </div>'+
        	'                        </a>'+
        	'                    </div>';
        if(app['zone'].indexOf('1') != -1){
        	$("#kssAllapps").prepend(appEle);
        }
        if(app['zone'].indexOf('2') != -1){
        	$("#jlsAllapps").prepend(appEle);
        }
        if(app['zone'].indexOf('3') != -1){
        	$("#jdsAllapps").prepend(appEle);
        }
        if(app['zone'].indexOf('4') != -1){
        	$("#srjyAllapps").prepend(appEle);
        }
        if(app['zone'].indexOf('5') != -1){
        	$("#jhylsAllapps").prepend(appEle);
        }
        if(app['zone'].indexOf('6') != -1){
        	$("#qzylsAllapps").prepend(appEle);
        }
        if(app['zone'].indexOf('0') != -1){
        	$("#jgzdAllapps").prepend(appEle);
        }
        if(app['zone'].indexOf('9') != -1){
        	$("#qtAllapps").prepend(appEle);
        }
    }
    
    
    var isChange = $('#addAppModal').find('.Allapps').attr('isChange');//定义一个属性，记录modal加载时高度是否改变，其他人不用管，除非命名冲突
    if (screen.height > 800) {
    	if (isChange == undefined) {
    		console.info("根据样式调整分页组件位置");
    		var _ele = $('#addAppModal').find('.Allapps');
    		var _height = _ele[0].style.height;
    		if (_height.indexOf("%") != -1) {
    			_height = (Number(_height.replace("%","")) + 3) + "%";
    		}
    		_ele.height(_height);
    		$('#addAppModal').find('.Allapps').attr('isChange',"true")
    	}
	}
}


var loadAppImg = function(app_data,zone){
	var querydata=$("#searchForm").serialize();
	var str = "namelike=&classfic=";
	var width = $("#kssfm").width();
	var size = Math.floor(width/213)*2;
		$("div").remove("#kssAppList div");
		$("div").remove("#jlsAppList div");
		$("div").remove("#jdsAppList div");
		$("div").remove("#srjyAppList div");
		$("div").remove("#jhylsAppList div");
		$("div").remove("#qzylsAppList div");
		$("div").remove("#jgzdAppList div");
		$("div").remove("#qtAppList div");
		
	var kss=0;
	var jls=0;
	var jds=0;
	var srjy=0;
	var jhyls=0;
	var qzyls=0;
	var jgzd=0;
	var qt=0;
	var syjy = 0;
	var akyy = 0;
    for(var i=0;i < app_data.length;i++){
        var app = app_data[i];
        var id = app['id'];
        var appcode = app['appcode'];
        var icon = 'L_'+app['icon'];
        var info = app['info'];
        var appInfo;
        if(info == ""){
        	appInfo = "查看详情"
        }else{
        	appInfo = info;
        }
        var appEle = '<div class="module-nt">'+
        	'                        <a class="nt-lk"'+
        	'                           href="#" onclick="_iframe(\''+id+'\',\''+appcode+'\')">'+
        	'                            <div class="lk-nr">'+
        	'                                <div class="nr-df"></div>'+
        	'                                <div class="nr-zt">'+
        	'									<div align="center" class="asd-tp-qy">'+
        	'                                   	<img style="width:213px;height:100px" class="tp-qy-img" src="../images/icon/'+icon+'">'+
        	'									</div>'+
        	'                                    <div class="zt-txt" style="font-size:15px">'+appInfo+'</div>'+
        	'                                    <div align="center" style="margin-top:22px" class="zt-kz">'+
        	'                                        <div class="kz-txt" style="font-size:15px;" title="'+app['name']+'">'+app['name']+
        	'                                        </div>'+
        	'                                    </div>'+
        	'                                </div>'+
        	'                            </div>'+
        	'                        </a>'+
        	'                    </div>';
        
        if(app['zone'].indexOf('1') != -1){
        	kss += 1;
        	if(querydata == str){
	        	if(kss<size+1){
	        		$("#kssAppList").prepend(appEle);
					$('#kssAppList')[0].selectedIndex = 0;
					$('#kssAppList').change();
	        	}
        	}else{
        		if(kss<size*2+1){
	        		$("#kssAppList").prepend(appEle);
					$('#kssAppList')[0].selectedIndex = 0;
					$('#kssAppList').change();
	        	}
        	}
        }
        
        if(app['zone'].indexOf('2') != -1){
        	jls += 1;
        	if(querydata == str){
        		if(jls<size+1){
        			$("#jlsAppList").prepend(appEle);
        			$('#jlsAppList')[0].selectedIndex = 0;
        			$('#jlsAppList').change();
        		}
        	}else{
        		if(jls<size*2+1){
        			$("#jlsAppList").prepend(appEle);
        			$('#jlsAppList')[0].selectedIndex = 0;
        			$('#jlsAppList').change();
        		}
        	}
        }
        
        if(app['zone'].indexOf('3') != -1){
        	jds += 1;
        	if(querydata == str){
        		if(jds<size+1){
        			$("#jdsAppList").prepend(appEle);
        			$('#jdsAppList')[0].selectedIndex = 0;
        			$('#jdsAppList').change();
        		}
        	}else{
        		if(jds<size*2+1){
        			$("#jdsAppList").prepend(appEle);
        			$('#jdsAppList')[0].selectedIndex = 0;
        			$('#jdsAppList').change();
        		}
        	}
        }
        
        if(app['zone'].indexOf('4') != -1){
        	srjy += 1;
        	if(querydata == str){
	        	if(akyy<size+1){
	        		$("#srjyAppList").prepend(appEle);
					$('#srjyAppList')[0].selectedIndex = 0;
					$('#srjyAppList').change();
	        	}
        	}else{
        		if(srjy<size*2+1){
	        		$("#srjyAppList").prepend(appEle);
					$('#srjyAppList')[0].selectedIndex = 0;
					$('#srjyAppList').change();
	        	}
        	}
        }
        
        if(app['zone'].indexOf('5') != -1){
        	jhyls += 1;
        	if(querydata == str){
        		if(jhyls<size+1){
        			$("#jhylsAppList").prepend(appEle);
        			$('#jhylsAppList')[0].selectedIndex = 0;
        			$('#jhylsAppList').change();
        		}
        	}else{
        		if(jhyls<size*2+1){
        			$("#akyyAppList").prepend(appEle);
        			$('#akyyAppList')[0].selectedIndex = 0;
        			$('#akyyAppList').change();
        		}
        	}
        }	
        
        if(app['zone'].indexOf('6') != -1){
        	qzyls += 1;
        	if(querydata == str){
        		if(qzyls<size+1){
        			$("#qzylsAppList").prepend(appEle);
        			$('#qzylsAppList')[0].selectedIndex = 0;
        			$('#qzylsAppList').change();
        		}
        	}else{
        		if(qzyls<size*2+1){
        			$("#qzylsAppList").prepend(appEle);
        			$('#qzylsAppList')[0].selectedIndex = 0;
        			$('#qzylsAppList').change();
        		}
        	}
        }
        
        if(app['zone'].indexOf('0') != -1){
        	jgzd += 1;
        	if(querydata == str){
	        	if(jgzd<size+1){
	        		$("#jgzdAppList").prepend(appEle);
					$('#jgzdAppList')[0].selectedIndex = 0;
					$('#jgzdAppList').change();
	        	}
        	}else{
        		if(jgzd<size*2+1){
	        		$("#jgzdAppList").prepend(appEle);
					$('#jgzdAppList')[0].selectedIndex = 0;
					$('#jgzdAppList').change();
	        	}
        	}
        }
        
        if(app['zone'].indexOf('9') != -1){
        	qt += 1;
        	if(querydata == str){
	        	if(qt<size+1){
	        		$("#qtAppList").prepend(appEle);
					$('#qtAppList')[0].selectedIndex = 0;
					$('#qtAppList').change();
	        	}
        	}else{
        		if(qt<size*2+1){
	        		$("#qtAppList").prepend(appEle);
					$('#qtAppList')[0].selectedIndex = 0;
					$('#qtAppList').change();
	        	}
        	}
        }
    }
    
    
    if(kss>0 && (zone == 1 || zone == undefined)){
    	$("#kssfm").css('display','block');
    	$("#kssAppList").css('display','block');
    }
    if(jls>0 && (zone == 2 || zone == undefined)){
    	$("#jlsfm").css('display','block');
    	$("#jlsAppList").css('display','block');
    }
    if(jds>0 && (zone == 3 || zone == undefined)){
    	$("#jdsfm").css('display','block');
    	$("#jdsAppList").css('display','block');
    }
    if(srjy>0 && (zone == 4 || zone == undefined)){
    	$("#srjyfm").css('display','block');
    	$("#srjyAppList").css('display','block');
    }
    if(jhyls>0 && (zone == 5 || zone == undefined)){
    	$("#jhylsfm").css('display','block');
    	$("#jhylsAppList").css('display','block');
    }
    if(qzyls>0 && (zone == 6 || zone == undefined)){
    	$("#qzylsfm").css('display','block');
    	$("#qzylsAppList").css('display','block');
    }
    if(jgzd>0 && (zone == 0 || zone == undefined)){
    	$("#jgzdfm").css('display','block');
    	$("#jgzdAppList").css('display','block');
    }
    if(qt>0 && (zone == 9 || zone == undefined)){
    	$("#qtfm").css('display','block');
    	$("#qtAppList").css('display','block');
    }
}

function _iframe(id, appcode) {
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
    $("body").append(form);
    form.append(input1);
    form.append(input2);
    form.append(input3);
    form.submit();
    form.remove();
}

function selectOnchang(obj){
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

var ycDiv = function(){
	$("#kssfm").css('display','none');
	$("#kssAppList").css('display','none');
	$("#kssAppList").empty();
	$("#jlsfm").css('display','none');
	$("#jlsAppList").css('display','none');
	$("#jlsAppList").empty();
	$("#jdsfm").css('display','none');
	$("#jdsAppList").css('display','none');
	$("#jdsAppList").empty();
	$("#srjyfm").css('display','none');
	$("#srjyAppList").css('display','none');
	$("#srjyAppList").empty();
	$("#jhylsfm").css('display','none');
	$("#jhylsAppList").css('display','none');
	$("#jhylsAppList").empty();
	$("#qzylsfm").css('display','none');
	$("#qzylsAppList").css('display','none');
	$("#qzylsAppList").empty();
	$("#jgzdfm").css('display','none');
	$("#jgzdAppList").css('display','none');
	$("#jgzdAppList").empty();
	$("#qtfm").css('display','none');
	$("#qtAppList").css('display','none');
	$("#qtAppList").empty();
}

var initApp = function() {
	//appSearch();
	//获取浏览器
	browse();
};