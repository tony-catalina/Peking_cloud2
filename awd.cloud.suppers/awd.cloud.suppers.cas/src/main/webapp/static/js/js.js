var cloud_time = 2e3;
var toVaild = function() {
	var username = $("#username").val();
	var loginnameValue = $("#loginname").val();
	var jsbhValue = $("input[name='jsbhchoice']").val();
	if (loginnameValue == "" || loginnameValue == undefined || username == ""
			|| username == undefined) {
		$('#loginname-tr').show();

		return false;
	} else {
		return true;
	}

}
var cloud_animate = function(a, b) {
	$("#" + a).animate({
		marginLeft : parseInt($("#" + a).css("marginLeft")) + 30 * b
	}, cloud_time, function() {
		cloud_animate(a, -b)
	})
}
var disableEmptyInputFormSubmission = function() {
	$('#loginname,[name="password"]').on(
			"change",
			function(event) {
				console.log("进来了哦——————————");
				if ($.trim($('input[name="loginname"]').val())
						&& $.trim($('input[name="password"]').val())) {
					$("#submit").removeAttr('disabled');
					event.stopPropagation();
				} else {
					$("#submit").attr('disabled', 'true');
				}
			});
}

function input_focus(e){
       $(e).css("color","#000000");
        if($(e).val()=="请输入登录账号"){
                $(e).val('');
            }
     }
function input_blur(e){
       if($(e).val()=="请输入登录账号"||!$(e).val()){
               $(e).css("color","#aaaaaa");
                $(e).val("请输入登录账号");
             }
     }

$(document)
		.ready(
				function() {
					// 创建console对象;
					window.console = window.console
							|| (function() {
								var c = {};
								c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function() {
								};
								return c;
							})();

					$("#select_experience").bind(
							"click",
							function(a) {
								a.stopPropagation(), $(".selectr").removeClass(
										"selectrFocus"), $("#box_job").hide(),
										$(".boxUpDown").hide(), $(this)
												.addClass("selectrFocus"), $(
												this).siblings(".boxUpDown")
												.show()
							}), $("#box_experience").on(
							"click",
							"ul li",
							function(a) {
								a.stopPropagation();
								var b = $.trim($(this).text());
								var value = $(this).attr("value");
								$(this).parents("#box_experience").hide()
										.siblings("#select_experience").val(b)
										.css("color", "ghostwhite").removeClass(
												"selectrFocus"), $(
										"#experience").val(value);

							}), cloud_animate("cloud_s", -1), cloud_animate(
							"cloud_m", 1),
					// $("#submit").attr('disabled', 'true'),
					// disableEmptyInputFormSubmission(),
					$('#password')
							.keypress(
									function(e) {
										console.log("进来了。。。。");
										var s = String.fromCharCode(e.which);
										var loginnameValue = $("#loginname")
												.val();
										if (loginnameValue == "") {
											$('#loginname-tr').show();
										} else {
											$('#loginname-tr').hide();
										}
										if (s.toUpperCase() === s
												&& s.toLowerCase() !== s
												&& !e.shiftKey) {
											$('#capslock-on').show();
										} else {
											$('#capslock-on').hide();
										}
									}), $('#loginname')
							.blur(
									function(e) {
										console.log("这是loginname的blur事件");
										var s = String.fromCharCode(e.which);
										var loginnameValue = $("#loginname")
												.val();
										var jsbhValue = $(
												"input[name='jsbhchoice']")
												.val();
										$("input[name='username']").val(
												loginnameValue + "@"
														+ jsbhValue);
										console.log($("input[name='username']")
												.val());
										console.log("用户名+监所编号", loginnameValue
												+ "@" + jsbhValue);
										if (loginnameValue == "") {
											$('#loginname-tr').show();
										} else {
											$('#loginname-tr').hide();
										}
										if (s.toUpperCase() === s
												&& s.toLowerCase() !== s
												&& !e.shiftKey) {
											$('#capslock-on').show();
										} else {
											$('#capslock-on').hide();
										}
									})
				})