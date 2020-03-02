$(document).ready(function(){
if (Modernizr.touch) {
  console.log("===================sehgnxiao======================");
  // show the close overlay button
  $(".close-overlay").removeClass("hidden");
  // handle the adding of hover class when clicked
  $(".role img").click(function(e){
    if (!$(this).hasClass("hover")) {
      $(this).addClass("hover");
    }
  });
  // handle the closing of the overlay
  $(".close-overlay").click(function(e){
    e.preventDefault();
    e.stopPropagation();
    if ($(this).closest(".role img").hasClass("hover")) {
      $(this).closest(".role img").removeClass("hover");
    }
  });
} else {
  // handle the mouseenter functionality
  $(".role img").mouseenter(function(){
    console.log("===================添加遮罩======================");
    $(this).addClass("hover");
  })
  // handle the mouseleave functionality
  .mouseleave(function(){
    console.log("===================移除遮罩======================");
    $(this).removeClass("hover");
  });
}
});
