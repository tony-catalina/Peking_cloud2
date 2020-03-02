define("default-assets/bootstrap-growl-active", [
	"../default-assets/bootstrap-growl.min"
],
function(require) {
	var notify=function(message, type) {
        $.growl({
            message: message
        }, {
            type: type,
            allow_dismiss: false,
            label: 'Cancel',
            className: 'btn-xs btn-inverse',
            placement: {
                from: 'top',
                align: 'right'
            },
            delay: 6000,
            animate: {
                enter: 'animated fadeInRight',
                exit: 'animated fadeOutRight'
            },
            offset: {
                x: 30,
                y: 80
            }
        });
    };
	
	var _init=function(){
		notify('Hi, Welcomeback! - Admetro Dashboard', 'inverse');
	}	
	return{
		init:_init
	}
});