define("bootstrap/awd.core", [
	"../jquery-ui.min",
	],
function(require) {	
	var _window = $(window);	
	var pageWrapper = $(".admetro-page-wrapper");
	var sideMenuArea = $(".admetro-sidemenu-area");
	
	var _init=function(){
		$(document).on("show.bs.modal", ".modal", function(){
		    $(this).draggable({
		    	handle: ".modal-header",   // 只能点击头部拖动
		        cursor: 'move'
		    });
		    $(this).css("overflow", "hidden");   
		    // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
		});
		if ($.fn.slimscroll) {
	        $('#admetroSideNav').slimscroll({
	            height: '100%',
	            size: '4px',
	            position: 'right',
	            color: '#8c8c8c',
	            alwaysVisible: false,
	            distance: '2px',
	            railVisible: false,
	            wheelStep: 15
	        });
	    }
		$("#menuCollasped").on("click", function () {
	        pageWrapper.toggleClass("menu-collasped-active");
	    });

	    $("#mobileMenuOpen").on("click", function () {
	        pageWrapper.toggleClass("mobile-menu-active");
	    });

	    sideMenuArea.on("mouseenter", function () {
	        pageWrapper.addClass("sidemenu-hover-active");
	        pageWrapper.removeClass("sidemenu-hover-deactive");
	    });

	    sideMenuArea.on("mouseleave", function () {
	        pageWrapper.removeClass("sidemenu-hover-active");
	        pageWrapper.addClass("sidemenu-hover-deactive");
	    });
	}
	
	var _preload_remove=function(id,time){
		_window.on("load", function () {
	        $("#"+id).fadeOut(time, function () {
	            $(this).remove();
	        });
	    });
	}
	var _get_html=function(url){
		
	}
	
	return{
		init:_init,
		preload_remove:_preload_remove
	}
});