define("bootstrap/awd.dialog", [
	"../bootstrap.min",
	],
function(require) {	
	var _init=function(id,options){		
		Object.assign(options,{
			backdrop:true,
			keyboard:true,			
			show:false
			});
		return $("#"+id).modal(options);
	}
	return{
		init:_init
	}
});