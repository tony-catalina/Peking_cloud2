define("demo/js/tree", [
	"../../framework/easyui/awd.combo",
	"../../framework/easyui/awd.utils"
	],
function(require) {
	var awd_tree=require("../../framework/easyui/awd.combo");
	var initview=function(){
		awd_tree.init_tree("#jtjyjyfwIDD",{url:"../jsondata/tree.json",checkbox:true,multiple:true});
	}
	
	initview();

});