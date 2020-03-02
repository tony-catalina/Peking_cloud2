define("app/common/core.playSound", ["./myPlayer"], function(next) {
  var error = {
    file_remove : "file_remove.mp3",
    recycle_clear : "recycle_clear.mp3",
    folder_open : "folder_open.mp3",
    window_min : "window_min.mp3",
    error : "error_tips.mp3",
    drag_upload : "drag_upload.mp3",
    drag_drop : "drag_drop.mp3"
  };
  var play = function(file) {
    var i = "x-play-sound";
    if (0 == $("." + i).length) {
      $('<div style="width:0px;height:0px;" class="' + i + '"></div>').appendTo("body");
    }
    var path = G.static_path + "others/sound/" + file;
    var that = next("./myPlayer");
    that.playSound(path, $("." + i));
  };
  return{
    playSoundFile : play,
    playSound : function(name) {
      if (G) {
        if (G.user_config) {
          if ("1" == G.user_config.sound_open) {
            setTimeout(function() {
            	play(error[name]);
            }, 100);
          }
        }
      }
    }
  };
});
