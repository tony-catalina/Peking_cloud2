define("awd/bootstrap_growl", [
	"../default-assets/bootstrap-growl.min"
	],
function(require) {
	var _notify=function(message, type) {
        $.growl({
            message: message
        }, {
            type: type,
            allow_dismiss: false,
            label: '取消',
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
    
	return{
		notify:_notify
	}
});