	function plugin0(){
        return document.getElementById('plugin0');
    }
    plugin = plugin0;
    function addEvent(obj, name, func){
        if (obj.attachEvent) {
            obj.attachEvent("on"+name, func);
        } else {
            obj.addEventListener(name, func, false); 
        }
    }
    
    
    function pluginLoaded() {
        alert("Plugin loaded!");
    }    
	
    function init(){
        if(plugin().init()==1){
            alert("This plugin seems to be working!");
        } else {
            alert("Plugin is not working :(");
        }
    }

	function getversion(){
		alert(plugin().version);
	}
	function closesdk(){
		plugin().closesdk();
		alert("closed");
	}
	function breaksdk(){
		plugin().breaksdk();
		alert("breaked");
	}
	function preview( v){
		plugin().setpreview(v);
	}
	function getmap(){
		alert(plugin().getmap());
	}
	function getfeature(){
		document.getElementsByName("yj")[0].value=plugin().getfeature();
	}
	function getscore(){
		alert(plugin().getscore());
	}
	function setoperator(op){
		plugin().setopreator(op);
	}
	function setmatchdata(){
		plugin().setmatch(document.getElementsByName("yj")[0].value);
	}
	function setpress(v){
		plugin().setpress(v);
	}
	function setscore(){
		plugin().setscore(document.getElementsByName("zl")[0].value);
	}
	function setmatchval(){
		plugin().setmatcval(document.getElementsByName("bz")[0].value);
	}
	
	var init_plugin = function(){
		if (plugin().init() == 1) {
			alert("浏览器插件初始化成功");
		}else {
			alert("插件调用失败！");
		}
	}
	
	var stop_plugin = function(){
		plugin().breaksdk();
		alert("插件已停止！");
	}
	
	var FP_Begin = function(){
		$.ajax({ 	
			type : "get",  //提交方式  
			url : "/hongda/FP_Begin",//路径  
			async:false,
			success : function(result) {
				 if (result.status == 200) {  
					alert(result.result);    
				}else{     
				    alert("");                     
			   	}      
			},
			error:function(result) {
				console.info("error");
			},
	    });
	}
	
	function Init_FP_Begin(){
		alert("初始化"); 
		$.ajax({ 	
			type : "get",  //提交方式  
			url : "/hongda/Init_FP_Begin",//路径  
			async:false,
			success : function(result) {
				console.info("success");
				 if (result.status == 200) {  
					alert(result.result);    
				}else{     
				    alert("");                     
			   	}      
			},
			error:function(result) {
				console.info("error");
			},
	    });
	}
	
	function Init_FP_End() {
		$.ajax({ 	
			type : "Post",  //提交方式  
			url : "/hongda/Init_FP_End",//路径  
			async:false,
			success : function(result) {
				console.info("success");
				 if (result.status == 200) {  
					 plugin().breaksdk();
					alert(result.result);    
				}else{     
				    alert("");                     
			   	}      
			},
			error:function(result) {
				console.info("error");
			},
	    });
	}
	
	var z_1;
	var z_2;
	var z_3;
	var y_1;
	var y_2;
	var y_3;
	var z_tzm;
	var y_tzm;
	
	var click_z_1 = function(){
		z_1 = undefined;
		z_1 = plugin().getmap();
		//alert("左手第一次指纹获取：" + z_1);
		$('#zw_Value').val(z_1);
	} 
	
	var click_z_2 = function(){
		z_2 = undefined;
		z_2 = plugin().getmap();
		//alert("左手第二次指纹获取：" + z_2);
		$('#zw_Value').val(z_2);
	} 
	
	var click_z_3 = function(){
		z_3 = undefined;
		z_3 = plugin().getmap();
		//alert("左手第三次指纹获取：" + z_3);
		$('#zw_Value').val(z_3);
	} 
	
	var click_y_1 = function(){
		y_1 = undefined;
		y_1 = plugin().getmap();
		//alert("右手第一次指纹获取：" + y_1);
		$('#zw_Value').val(y_1);
	} 
	
	var click_y_2 = function(){
		y_2 = undefined;
		y_2 = plugin().getmap();
		//alert("右手第二次指纹获取：" + y_2);
		$('#zw_Value').val(y_2);
	} 
	
	var click_y_3 = function(){
		y_3 = undefined;
		y_3 = plugin().getmap();
		//alert("右手第三次指纹获取：" + y_3);
		$('#zw_Value').val(y_3);
	} 
	
	var click_z_tzm = function(){
		z_tzm = undefined;
		z_tzm = plugin().getfeature();
		//alert("左手指纹特征码获取：" + z_tzm);
		$('#zw_Value').val(z_tzm);
	}
	
	var click_y_tzm = function(){
		y_tzm = undefined;
		y_tzm = plugin().getfeature();
		//alert("右手指纹特征码获取：" + y_tzm);
		$('#zw_Value').val(y_tzm);
	}
	
	//获取指位图像数组
	var getFingerData = function() {
		var fingerDate = [];
		if (z_1 != "" && z_1 != undefined) {
			fingerDate.push(z_1);
		}else {
			alert("左手第一次指纹图像未获取");
			return;
		}
		if (z_2 != "" && z_2 != undefined) {
			fingerDate.push(z_2);
		}else {
			alert("左手第二次指纹图像未获取");
			return;
		}
		if (z_3 != "" && z_3 != undefined) {
			fingerDate.push(z_3);
		}else {
			alert("左手第三次指纹图像未获取");
			return;
		}
		
		if (y_1 != "" && y_1 != undefined) {
			fingerDate.push(y_1);
		}else {
			alert("右手第一次指纹图像未获取");
			return;
		}
		if (y_2 != "" && y_2 != undefined) {
			fingerDate.push(y_2);
		}else {
			alert("右手第二次指纹图像未获取");
			return;
		}
		if (y_3 != "" && y_3 != undefined) {
			fingerDate.push(y_3);
		}else {
			alert("右手第三次指纹图像未获取");
			return;
		}
		console.info(fingerDate);
		return fingerDate;
	}
	
	//获取指纹特征码数组
	var getFeatureData = function(){
		var featureDate = [];
		if (z_tzm != "" && z_tzm != undefined) {
			featureDate.push(z_tzm);
		}else {
			alert("左手指纹特征码未获取");
			return;
		}
		if (y_tzm != "" && y_tzm != undefined) {
			featureDate.push(y_tzm);
		}else {
			alert("右手指纹特征码未获取");
			return;
		}
		return featureDate;
	}
	
	//获取指位数组
	var getFingerCode = function(){
		var fingerCode = [];
		fingerCode.push($('#z_fingerCode').val());
		fingerCode.push($('#y_fingerCode').val());
		return fingerCode;
	}
	
	var registOrUpdate; // 因为注册和更新的参数一样，只是url不同，为了少写一些代码，定义一个 注册还是更新的 全局变量，点击注册按钮就把 值设为 0，点击更新按钮，就把值设为1
	
	//指纹的之策和更新
	var fingerRegistOrUpdate = function(){
		var type ;//区分民警和人员 的变量
		if ($("#ry_radio").prop("checked")) {
			type = 0;	
		}else if ($("#mj_radio").prop("checked")) {
			type = 1;	
		}
		
		var rybhOrZjh = $("#rybhOrZjh").val();
		var jsbh = $("#jsbh").val();
		
		var fingercodeStr = getFingerCode().join(",");
		try {
			var fingerdataStr = getFingerData().join(",");
		} catch (e) {
			alert("获取指纹图像异常");
			return;
		}
		
		var fingerRegistOrUpdateData = {
				"rybhOrZjh" : rybhOrZjh,
				"jsbh" : jsbh,
				"fingerdata" : fingerdataStr,
				//"fingerdata" : JSON.stringify(getFingerData()),
				"fingercode" : fingercodeStr,
				//"fingercode" : JSON.stringify(getFingerCode())
		}
		
		var registOrUpdateUrl = "";
		if (type == 0 && registOrUpdate == 0) {
			registOrUpdateUrl = "/bzwtx/fingerRegist";
		}else if (type == 0 && registOrUpdate == 1) {
			registOrUpdateUrl = "/bzwtx/upateFinger";
		}else if (type == 1 && registOrUpdate == 0) {
			registOrUpdateUrl = "/mzwtx/mfingerRegister";
		}else if (type == 1 && registOrUpdate == 1) {
			registOrUpdateUrl = "/mzwtx/mupateFinger";
		}
		
		$.ajax({ 
			type : "Post",  //提交方式  
			url: registOrUpdateUrl,
			async:false,
			data : fingerRegistOrUpdateData,
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
	
	
	//指纹特征码的注册个更新
	var featureRegistOrUpdate = function(){
		
		var type ;//区分民警和人员 的变量
		if ($("#ry_radio").prop("checked")) {
			type = 0;	
		}else if ($("#mj_radio").prop("checked")) {
			type = 1;	
		}
		
		var rybhOrZjh = $("#rybhOrZjh").val();
		var jsbh = $("#jsbh").val();
		var fingercodeStr = getFingerCode().join(",");
		try {
			var featuredataStr = getFeatureData().join(",");
		} catch (e) {
			alert("获取指纹特征码异常");
			return;
		}
		
		var featureRegistOrUpdateData = {
				"rybhOrZjh" : rybhOrZjh,
				"jsbh" : jsbh,
				"featuredata" : featuredataStr,
				"fingercode" : fingercodeStr,
		}
		
		var registOrUpdateUrl = "";
		if (type == 0 && registOrUpdate == 0) {
			registOrUpdateUrl = "/bzwtzm/fingerfeatureRegist";
		}else if (type == 0 && registOrUpdate == 1) {
			registOrUpdateUrl = "/bzwtzm/upateFingerfeature";
		}else if (type == 1 && registOrUpdate == 0) {
			registOrUpdateUrl = "/mzwtzm/mfingerfeatureRegist";
		}else if (type == 1 && registOrUpdate == 1) {
			registOrUpdateUrl = "/mzwtzm/mupateFingerfeature";
		}
		
		$.ajax({ 
			type : "Post",  //提交方式  
			url: registOrUpdateUrl,
			async:false,
			data : featureRegistOrUpdateData,
			success : function(result) {
				if (result.status == 200) {   	 
					alert(result.result);    
				}else if(result.status == 500){     
					alert(result.message);                     
				}           
			},
			error:function(result) {
				alert("请求失败");  
			},
		});
	}
	
	
	var fingerRegist = function(){
		registOrUpdate = 0;
		fingerRegistOrUpdate();
	}
	
	var fingerUpdate = function(){
		registOrUpdate = 1;
		fingerRegistOrUpdate();
	}
	
	var fingerRemove = function(){
		var rybhOrZjh = $("#rybhOrZjh").val();
		var fingerRemoveUrl ;//区分民警和人员 的的删除 请求路径
		var fingerRemoveData = {};
		if ($("#ry_radio").prop("checked")) {
			fingerRemoveUrl = "/bzwtx/removeFinger";	
			fingerRemoveData = {
					"rybh" : rybhOrZjh,
			};
		}else if ($("#mj_radio").prop("checked")) {
			fingerRemoveUrl = "/mzwtx/mremoveFinger";	
			fingerRemoveData = {
					"zjh" : rybhOrZjh,
			};
		}
		$.ajax({ 
			type : "Post",  //提交方式  
			url: fingerRemoveUrl,
			async : false,
			data : fingerRemoveData ,
			success : function(result) {
				if (result.status == 200) {   	 
					alert(result.result);    
				}else if(result.status == 500){     
					alert(result.message);                     
				}          
			},
			error:function(result) {
				alert("请求失败");  
			},
		});
	}
	
	var featureRegist = function(){
		registOrUpdate = 0;
		featureRegistOrUpdate();
	}
	
	var featureUpdate = function(){
		registOrUpdate = 1;
		featureRegistOrUpdate();
	}
	
	var featureRemove = function(){
		var rybhOrZjh = $("#rybhOrZjh").val();
		
		var featureRemoveUrl ;//区分民警和人员 的的删除 请求路径
		var featureRemoveData = {};
		if ($("#ry_radio").prop("checked")) {
			featureRemoveUrl = "/bzwtzm/removeFingerfeature";	
			featureRemoveData = {
					"rybh" : rybhOrZjh,
			};
		}else if ($("#mj_radio").prop("checked")) {
			featureRemoveUrl = "/mzwtzm/removeMjFingerfeature";	
			featureRemoveData = {
					"zjh" : rybhOrZjh,
			};
		}
		
		$.ajax({ 
			type : "Post",  //提交方式  
			url: featureRemoveUrl,
			async:false,
			data : featureRemoveData,
			success : function(result) {
				if (result.status == 200) {   	 
					alert(result.result);    
				}else if(result.status == 500){     
					alert(result.message);                     
				}           
			},
			error:function(result) {
				alert("请求失败");  
			},
		});
	}
	
