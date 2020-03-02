define("app/src/app/main", [
	"lib/jquery-lib", 
	"lib/util", 
	"lib/artDialog/jquery-artDialog",
	"../../common/core",
	"./page"
	],
function(require, exports, module) {
	require("lib/jquery-lib"),
	require("lib/util"),
	require("lib/artDialog/jquery-artDialog"),
	core = require("../../common/core"),
	App = require("./page"),
	core.init(),
	App.init()
});

