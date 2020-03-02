function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }

//日期框后一个比前一个大
function dateValidate() {
	$('#startTime').datebox({
		onSelect:function(beginDate){
			$('#endTime').datebox().datebox('calendar').calendar({ 
                validator: function(date){ 
                   var d1 = new Date(beginDate.getFullYear(), beginDate.getMonth(), beginDate.getDate());
                   return d1<=date; //&& date<=d2;
                } 
			}); 
		}
	})
}

//日期框后一个比前一个大2
function dateValidate2() {
	$('#startTime2').datebox({
		onSelect:function(beginDate){
			$('#endTime2').datebox().datebox('calendar').calendar({ 
				validator: function(date){ 
					var d1 = new Date(beginDate.getFullYear(), beginDate.getMonth(), beginDate.getDate());
					return d1<=date; //&& date<=d2;
				} 
			}); 
		}
	})
}

//日期天数加法
function datePlus(oldDate , day) {
	if (oldDate != '') {
		var old_time = new Date(oldDate);
		var date = old_time.getDate();
		var new_date = Number(date) + Number(day);
		var new_time = new Date(old_time.setDate(new_date));
		return new_time.toLocaleDateString();
	}else {
		return oldDate;
	}
}

//combobox下拉值匹配与清空
var comboboxValidate = function(id){
	var data = [];
	var flag ; 
	$('#' + id).combobox({
		onLoadSuccess:function(){
			data = $('#' +id).combobox('getData');
		},
		onHidePanel:function(){
			flag = false ;
			var newValue = $('#' + id).combobox('getText');
			for (var i = 0; i < data.length; i++) {
				if (newValue == data[i].content) {
					return flag = true;
				}
			}
			if (!flag) {
				$('#' + id).combobox('setValue', '');
			}
		}
	});
}

//combogrid下拉值匹配与清空
var combogridValidate = function(id){
	var data = {};
	var flag ; 
	$('#' + id).combogrid({
		onLoadSuccess:function(){
			var g = $('#' + id).combogrid('grid');	
			// data = JSON.stringify(g.datagrid('getData').rows);
			data = g.datagrid('getData').rows;
		},
		onHidePanel:function(){
			flag = false ;
			var newValue = $('#' + id).combo('getText');
			for (var i = 0; i < data.length; i++) {
				if (newValue == data[i].content) {
					return flag = true;
				}
			}
			if (!flag) {
				$('#' + id).combogrid('setValue', '');
			}
		}
	});
}

//表单提交提示正在提交中
var submitLoad = function(){
//	var _PageHeight = document.documentElement.clientHeight,
//    	_PageWidth = document.documentElement.clientWidth;
	var _PageHeight = 60,
		_PageWidth = 120;
	
	var _DivTop = (document.documentElement.clientHeight - _PageHeight) / 2,
		_DivLeft = (document.documentElement.clientWidth - _PageWidth) / 2;
	
	var _LoadingTop = _PageHeight > 60 ? (_PageHeight - 60) / 2 : 0,
		_LoadingLeft = _PageWidth > 120 ? (_PageWidth - 120) / 2 : 0;
		
	var Loadimagerul="../../images/loading.gif",
		linkImg = '<img src="'+Loadimagerul+'">';
	
	var _LoadingHtml = 
		  '<div id="loadingDiv" style="position:absolute;left:'+
		+ _DivLeft +'px;top:' 
		+ _DivTop + 'px;height:'
		+ _PageHeight + 'px;width:120px;opacity:1;filter:alpha(opacity=80);z-index:10000;">'
		+ '<div style="position: absolute; cursor1: wait; left: ' 
		+ _LoadingLeft + 'px; top:' 
		+ _LoadingTop + 'px; width:200px; height: 57px; line-height: 57px; padding-left: 10px; '
		+ 'padding-right: 5px; border: 0px solid #95B8E7; color: #696969;">'
		+ '<i class="fa fa-spinner fa-pulse fa-fw"></i><span>正在提交<span>...</div>';
	
	$('body').append('<div id="loadDiv"></div>');
	
	$('#loadDiv').html(_LoadingHtml);
	
}
//移除正在提交中
var removeLoad = function(){
	var flag = $('#loadDiv').length;
	if (flag == 1) {
		$('#loadDiv').remove();
	}
}


	function timeType(date) { // 短日期格式化
	    var datetime = date.getFullYear()
	        + "-"// "年"
	        + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
	            + (date.getMonth() + 1))
	        + "-"// "月"
	        + (date.getDate() < 10 ? "0" + date.getDate() : date
	            .getDate());
	    return datetime;
	};
  
  
  function longTimeType(date) { //转换为 类似 2018-04-21 16:08:52 等格式的长日期
	  var sfm = date.toTimeString().split(' ')[0];
	  var datetime = date.getFullYear() 
	  + "-" 
	  + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
            + (date.getMonth() + 1)) 
	  + "-"
	  + (date.getDate() < 10 ? "0" + date.getDate() : date
	            .getDate()) 
	  + " " 
	  + sfm;
	  return datetime;
  }
  
  //用一个开始时间加上天数得出结果时间 -- 返回短日期格式
function getShortDatePlus(startDate , dayCount) {
	var ksDate = new Date(startDate);
	thisDate = new Date((ksDate/1000+(86400*dayCount))*1000);
	return timeType(thisDate);
}  

//用一个开始时间加上天数得出结果时间 -- 返回长日期格式 ------还没测试
function getLongDatePlus(startDate , dayCount) {
	var sfm = startDate.split(' ')[1]; //切割获取时分秒
	var nyr = startDate.split(' ')[0]; //切割获取年月日
	var ksDate = new Date(nyr);
	thisDate = new Date((ksDate/1000+(86400*dayCount))*1000);
	new_date = timeType(thisDate) + ' ' + sfm ;
	return new_date;
}

