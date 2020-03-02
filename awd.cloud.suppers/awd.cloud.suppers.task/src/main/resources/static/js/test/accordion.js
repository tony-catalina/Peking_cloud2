define("test/accordion", [
	"../bootstrap/awd.accordion"
	],
function(require) {
	var awd_accordion=require("../bootstrap/awd.accordion");
	initview(){
		awd_accordion.init_accordion(".js-accordionTrigger);
	}();
});