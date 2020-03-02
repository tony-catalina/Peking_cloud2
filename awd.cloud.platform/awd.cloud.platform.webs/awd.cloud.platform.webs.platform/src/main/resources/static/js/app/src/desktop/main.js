define("app/src/desktop/main",
		[
		 "lib/jquery-lib",
		 "lib/util",
		 "lib/contextMenu/jquery-contextMenu",
		 "lib/artDialog/jquery-artDialog",
		 "lib/jquery.cookie",
		 "lib/picasa/picasa",
		 "../../common/taskTap",
		 "../../common/core",
		 "../../common/rightMenu",
		 "../explorer/ui",
		 "../explorer/fileContent",
		 "../explorer/path",
		 "../../common/pathOperate",
		 "../../common/pathOpen",
		 "../../common/myPlayer",
		 "../explorer/fileLight",
		 "../explorer/fileSelect",
		 "../explorer/fileListResize"],
function(require, exports) {
	Config = {
		BodyContent: ".bodymain",
		FileBoxSelector: ".bodymain .fileContiner",
		FileBoxClass: ".bodymain .fileContiner .file",
		FileBoxClassName: "file",
		FileBoxTittleClass: ".bodymain .fileContiner .title",
		SelectClass: ".bodymain .fileContiner .file.select",
		SelectClassName: "select",
		TypeFolderClass: "folderBox",
		TypeFileClass: "fileBox",
		HoverClassName: "hover",
		FileOrderAttr: "number",
		pageApp: "desktop",
		navbar: "navbar",
		AnimateTime: 200
	};
	require("lib/jquery-lib");
	require("lib/util");
	require("lib/contextMenu/jquery-contextMenu");
	require("lib/artDialog/jquery-artDialog");
	require("lib/picasa/picasa");
	TaskTap = require("../../common/taskTap");
	core = require("../../common/core");
	rightMenu = require("../../common/rightMenu");
	ui = require("../explorer/ui");
	ui.path = require("../explorer/path");
	pathOpen = require("../../common/pathOpen");
	ui.fileLight = require("../explorer/fileLight");
	ui.fileSelect = require("../explorer/fileSelect");
	ui.fileListResize = require("../explorer/fileListResize");
	$(document).ready(function() {
		$("#winds").click(function(e){
			console.log(888888)
			$("#menuwin").css("z-index","auto");
			$("#menuwin").css("position","relative");
			$("#menuwin").css("display", "none");
			$("#winds").css("pointer-events","none");

		});

		//$("#winds").css("pointer-events","none");

		G.user_config.list_type = "icon";
		core.init();
		ui.init();
		ui.fileLight.init();
		ui.fileSelect.init();
		TaskTap.init();
		rightMenu.initDesktop();
		ui.fileListResize.initFileSize();
		$.ajax({
			url:'/setting/getLoginUser',
			type:'GET',
			success:function(res){
				console.log(res)
				if(res){
					//进入加载默认应用
					$.ajax({
						url:'/setting/getUserSetting',
						type:'GET',
						data:{"key":"openFirst"},
						success:function(res){
							if(res.path != "0"){
								pathOpen.open(res,"oexe");
								i=1;
								console.log("------"+i)
							}
						}
					})
				}
			}
		})
	
		$(".init_loading").fadeOut(450).addClass("pop_fadeout");
		$(".fileContiner").removeClass("hidden");
		
		
		require.async("lib/webuploader/webuploader-min",function() {
			core.uploadInit();
		});
		$(".bodymain").click(function() {
			console.log("111111")
			if("block" == $("#menuwin").css("display")){
				$("#menuwin").css("z-index","auto");
				$("#menuwin").css("position","relative");
				$("#menuwin").css("display", "none");
				$("#winds").css("pointer-events","none");
			}
			$("body").focus();
		});
		$(".start").click(function(e) {
			if("block" == $("#menuwin").css("display") ){
				$("#menuwin").css("z-index","auto");
				$("#menuwin").css("position","relative");
				$("#menuwin").css("display", "none");
				$("#winds").css("pointer-events","none");
			}else{
				$("#menuwin").css("display", "block");
				$("#menuwin").css("z-index","99999999");
				$("#menuwin").css("position","fixed");
				$("#menuwin").css("font-size","15px");
				$("#winds").css("pointer-events","auto");
			}
		});
		$("#menuwin").find("ul li").click(function() {
			$("#menuwin").css("z-index","auto");
			$("#menuwin").css("position","relative");
			$("#menuwin").css("display", "none");
			$("#winds").css("pointer-events","none");
		});

		$(".copyright").click(function() {
			core.copyright();
		});
		$(".tab_hide_all").click(function() {
			if (0 != $.dialog.list.length) {
				$(this).toggleClass("this");
				var object = !$(this).hasClass("this");
				$.each($.dialog.list,function(i, t) {
					t.display(object)
				})
			}
		});



		/*
		 * 风车换背景
		 
		var html = '<div id="randomWallpaper">	<img class="flower animated-1000" src="' + G.static_path + 'images/common/desktop/fengche.png" title="' + '更换壁纸' + '">			<div class="body-line" ></div>		</div>';
		$("body").append(html);
		var flower = $("#randomWallpaper .flower");
		flower.bind("click",function() {
			flower.addClass("moveCircle");
			core.api.randomImage(function(e) {
				var num=parseInt(12*Math.random())+1;
				var image="/images/wall_page/"+num+".jpg";
				ui.setWall(image,function() {
					setTimeout(function() {
						flower.removeClass("moveCircle")
					},100);
				});				
			});
		});*/
	})
});
