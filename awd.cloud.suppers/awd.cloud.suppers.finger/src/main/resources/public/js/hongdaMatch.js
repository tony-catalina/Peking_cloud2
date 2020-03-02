
var matchFeature;

var getMatchFeature = function(){
	matchFeature = undefined;
	matchFeature = plugin().getfeature();
	if (matchFeature == undefined || matchFeature == "") {
		alert("未获取到指纹特征码");
	}
	$('#zw_Value').val(matchFeature);
}

var matchAll = function() {
	if (matchFeature == "" || matchFeature == undefined) {
		alert("要比对的指纹特征码未获取，请重新获取");
		return;
	}
	
	$.ajax({ 
		type : "Post",  //提交方式  
		url: "/hongda/fingerMatchHongda",
		async:false,
		data : {
			"rybh" : rybhOrZjh,
			"fingerData" : matchFeature
		},
		success : function(result) {
			if (result.status == 200) {   	 
				alert(result.result);    
			}else if(result.status == 500){     
				alert(result.message);                     
			}      
		},
		error:function(result) {
			alert("请求错误");  
		},
	});
}

var matchByRybhOrZjh = function() {
	var rybhOrZjh = $("#pp_rybhOrZjh").val();
	if (rybhOrZjh == "" || rybhOrZjh == undefined) {
		alert("请输入人员编号或证件号");
		return;
	}
	if (matchFeature == "" || matchFeature == undefined) {
		alert("要比对的指纹特征码未获取，请重新获取");
		return;
	}
	
	$.ajax({ 
		type : "Post",  //提交方式  
		url: "/hongda/fingerMatchHongdaByOne",
		async:false,
		data : {
			"rybhOrZjh" : rybhOrZjh,
			"fingerData" : matchFeature
		},
		success : function(result) {
			if (result.status == 200) {   	 
				alert(result.result);    
			}else if(result.status == 500){     
				alert(result.message);                     
			}      
		},
		error:function(result) {
			alert("请求错误");  
		},
	});
}


var matchByJsbh = function() {
	var jspp_jsbh = $("#jspp_jsbh").val();
	if (jspp_jsbh == "" || jspp_jsbh == undefined) {
		alert("请输入监所");
		return;
	}
	if (matchFeature == "" || matchFeature == undefined) {
		alert("要比对的指纹特征码未获取，请重新获取");
		return;
	}
	
	var type;
	if ($("#bd_ry_radio").prop("checked")) {
		type = 0;
	}else if ($("#bd_mj_radio").prop("checked")) {
		type = 1;
	}
	$.ajax({ 
		type : "Post",  //提交方式  
		url: "/hongda/fingerMultiMatchHongda",
		async:false,
		data : {
			"jsbh" : jspp_jsbh,
			"type" : type ,
			"fingerData" : matchFeature
		},
		success : function(result) {
			if (result.status == 200) {   	 
				alert(result.result);    
			}else if(result.status == 500){     
				alert(result.message);                     
			}      
		},
		error:function(result) {
			alert("请求错误");  
		},
	});
}

var matchByRybhs = function() {
	var bhpp_jsbh = $("#bhpp_jsbh").val();
	if (bhpp_jsbh == "" || bhpp_jsbh == undefined) {
		alert("请输入监所");
		return;
	}
	var bhpp_rybhs = $("#bhpp_rybhs").val();
	if (bhpp_rybhs == "" || bhpp_rybhs == undefined) {
		alert("请输入人员编号");
		return;
	}
	if (matchFeature == "" || matchFeature == undefined) {
		alert("要比对的指纹特征码未获取，请重新获取");
		return;
	}
	
	$.ajax({ 
		type : "Post",  //提交方式  
		url: "/hongda/fingerMultiMatchHongdaByRybhs",
		async:false,
		data : {
			"jsbh" : bhpp_jsbh,
			"rybhOrZjhList" : bhpp_rybhs,
			"fingerData" : matchFeature
		},
		success : function(result) {
			if (result.status == 200) {   	 
				alert(result.result);    
			}else if(result.status == 500){     
				alert(result.message);                     
			}      
		},
		error:function(result) {
			alert("请求错误");  
		},
	});
}