// JavaScript Document
$(document).ready(function(){
	$('.page2 .left-count').slide({mainCell:".bd ul",autoPlay:true,effect:"leftMarquee",vis:6,interTime:50,trigger:"click"});
    $('.page2 .info-box').slide({mainCell:".bd ul",autoPlay:true,effect:"topMarquee",vis:4,interTime:50,trigger:"click"});
    
    $('.page3 .info-box').slide({mainCell:".bd2 ul",autoPlay:true,effect:"topMarquee",vis:9,interTime:50,trigger:"click"});
    $('.page4 .info-box').slide({mainCell:".bd ul",autoPlay:true,effect:"topMarquee",vis:3,interTime:50,trigger:"click"});
});
