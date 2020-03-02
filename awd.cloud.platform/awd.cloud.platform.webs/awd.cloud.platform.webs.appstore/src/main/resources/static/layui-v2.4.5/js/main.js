layui.define(["layer","jquery"],function (exports) {
	var $ = layui.jquery,
	layer = layui.layer;	
	var apiurl="";

	var bindaction=function(){
		$("#navtree a").on("click",function(){
			$("#apishow").attr("src",$(this).attr("apiurl"));
		});
	}
	var obj = {
       init : function () {
    	   bindaction();
       }
   }
   exports("main",obj);
});
