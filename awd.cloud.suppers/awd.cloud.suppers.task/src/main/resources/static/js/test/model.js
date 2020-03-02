define("pages/model", [
	"../bootstrap/awd.core",
	"../bootstrap/awd.dialog",
	],
function(require) {	
	var awd_core=require("../bootstrap/awd.core");
	var awd_dialog=require("../bootstrap/awd.dialog");
	var initview=function(){
		awd_core.init();
		awd_core.preload_remove("preloader","1000");
	}
	//初始化视图
	initview();
});