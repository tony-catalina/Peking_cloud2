define("app/src/setting/main", [
	"lib/jquery-lib", 
	"lib/util", 
	"lib/artDialog/jquery-artDialog",
	"lib/contextMenu/jquery-contextMenu",
	"lib/layui/layui.js",
	"lib/ztree/ztree", 	
	"../../common/core", 
	"./setting", 
	"./system/system_setting", 
	"./system/system",	
	"./system/system_member",
	"./system/system_group", 
	"./system/system_role", 
	"./system/system_group_role",
	"./system/system_appsetting",	
	],
function(require) {	
	require("lib/jquery-lib");
	require("lib/util");
	require("lib/artDialog/jquery-artDialog");
	require("lib/contextMenu/jquery-contextMenu");
	core = require("../../common/core");
	layui.config({
		  dir: 'js/lib/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
		  ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
		  ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
		  ,base: 'js/lib/layui/lay' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
	});
	Setting = require("./setting");
	SystemSetting=require("./system/system_setting");
	System = require("./system/system");
	AppSetting=require("./system/system_appsetting");
	core.init();
	
});








