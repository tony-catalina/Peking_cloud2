define("app/common/myPlayer", [], function(options) {
  var destination = "";
  var _result = "";
  var elementID = "music_player";
  var object = "movie_player";
  var setup = function(id) {
    var deep = id == elementID ? "mp3" : "mp4";
    var sourceContainer = "." + id + "_dialog";
    var result = destination;
    var dims = {
      width : "70%",
      height : "60%"
    };
    if (id == elementID) {
      result = _result;
      dims = {
        width : "320px",
        height : "420px"
      };
    }
    var F = $.dialog({
      id : id + "_dialog",
      simple : true,
      ico : core.icon(deep),
      title : "player",
      width : dims.width,
      height : dims.height,
      content : result,
      resize : true,
      padding : 0,
      fixed : true,
      close : function() {
        var element = destroy(id);
        element.jPlayer("destroy");
      }
    });
    return F.DOM.wrap.addClass("myJPlayer"), $(sourceContainer).find(".jPlayer-container");
  };
  var getter = function(plugin) {
    return "music" == plugin ? elementID : (undefined == plugin && (plugin = "mp3"), inArray(core.filetype.music, plugin) ? elementID : object);
  };
  var destroy = function(id) {
    var statsTemplate = "." + id + "_dialog";
    var stat = $(statsTemplate);
    return 0 == stat.length ? false : stat.find(".jPlayer-container");
  };
  var callback = function(item) {
    var codecs = {
      mp4 : "m4v",
      m4v : "m4v",
      mov : "m4v",
      ogv : "ogv",
      webm : "webmv",
      webmv : "webmv",
      flv : "flv",
      f4v : "flv",
      f4a : "flv",
      mp3 : "mp3",
      wav : "wav",
      m4a : "mp3",
      aac : "mp3",
      ogg : "oga",
      oga : "oga",
      webma : "webma"
    };
    var ext = item.ext;
    var id = codecs[ext];
    var entry = {
      extType : id,
      title : item.name,
      url : item.url,
      solution : "flv" == ext || "f4v" == ext ? "flash" : "html,flash"
    };
    return entry[id] = item.url, entry;
  };
  var init = function($, options) {
    if (options) {
      var $input = $.parents(".jPlayer");
      var script2 = {
        solution : options.solution,
        swfPath : G.static_path + "js/lib/jPlayer/jquery.jplayer.swf"
      };
      $input.attr("id", UUID());
      $.jPlayer("destroy");
      $.find(".jPlayer-container").children().remove();
      $.jPlayer(jPlayerConfigInit($input, script2));
      if ($.find("object").length > 0) {
        $input.addClass("flashPlayer");
      } else {
        $input.removeClass("flashPlayer");
      }
      $.jPlayer("setMedia", options);
      setTimeout(function() {
        $.jPlayer("play");
      }, 150);
      jPlayerBindControl($input);
      setTimeout(function() {
        var name = $input.parents(".dialog-simple").find(".aui_titleBar").attr("id");
        var test = $.dialog.list[name];
        if (test) {
          test.title(options.title);
        }
      }, 100);
    }
  };
  var get = function(index) {
    var self = index[0].ext;
    var opts = getter(self);
    var options = destroy(opts);
    var current = callback(index[0]);
    if (!options) {
      options = setup(opts);
      if (opts == elementID) {
        el.init();
      }
    }
    if (opts == elementID) {
      current = el.insert(options, index, self);
    }
    init(options, current);
    try {
      $.dialog.list[opts + "_dialog"].display(true);
    } catch (u) {
    }
  };
  var el = function() {
    var tabs = [];
    var i = 0;
    var element = null;
    var id = "circle";
    var fail = function(positionError, array) {
      element = positionError;
      var numberOfRequests = tabs.length;
      var index = 0;
      for (;array.length > index;index++) {
        var c = false;
        var e = 0;
        e = 0;
        for (;tabs.length > e;e++) {
          if (tabs[e].url == array[index].url) {
            c = true;
            break;
          }
        }
        if (c) {
          if (index == array.length - 1) {
            return i != e && done(e), false;
          }
        } else {
          tabs.push(callback(array[index]));
        }
      }
      return tabs.length == numberOfRequests ? false : (i = tabs.length - 1, destroy(true), tabs[i]);
    };
    var done = function(d) {
      d = 0 >= d ? 0 : d;
      d = d >= tabs.length - 1 ? tabs.length - 1 : d;
      i = d;
      var data = tabs[d];
      init(element, data);
      destroy(false);
    };
    var animate = function(end) {
      switch(id) {
        case "circle":
          if ("next" == end) {
            if (tabs.length - 1 > i) {
              done(i + 1);
            } else {
              done(0);
            }
          } else {
            if (0 > i - 1) {
              done(tabs.length - 1);
            } else {
              done(i - 1);
            }
          }
          break;
        case "rand":
          done(roundFromTo(0, tabs.length) - 1);
          break;
        case "one":
          done(i);
          break;
        default:
        ;
      }
    };
    var cb = function(i) {
      tabs.remove(i);
      done(i);
      destroy(true);
    };
    var addImage = function(name) {
      var tab = tabs[name];
      var r20 = tab.url + "&download=1";
      ui.pathOpen.downloadUrl(r20);
    };
    var init = function() {
      i = 0;
      tabs = [];
      id = "circle";
      var $e = $(".jPlayer-music");
      var arr = [{
        icon : "icon-retweet",
        loop : "circle"
      }, {
        icon : "icon-random",
        loop : "rand"
      }, {
        icon : "icon-refresh loop-one",
        loop : "one"
      }];
      $e.find(".change-loop").unbind("click").bind("click", function() {
        var i = parseInt($(this).attr("data-loop")) + 1;
        i = 0 > i ? 0 : i;
        i = i >= arr.length ? 0 : i;
        var o = arr[i];
        $(this).attr("data-loop", i).find("i").attr("class", o.icon);
        id = o.loop;
      });
      $e.find(".play-backward").unbind("click").bind("click", function() {
        animate("prev");
      });
      $e.find(".play-forward").unbind("click").bind("click", function() {
        animate("next");
      });
      $e.find(".show-list").unbind("click").bind("click", function(deepDataAndEvents) {
        $e.parents(".music_player_dialog").toggleClass("hide-play-list");
        stopPP(deepDataAndEvents);
      });
      $e.find(".play-list .item").die("click").live("click", function(deepDataAndEvents) {
        var out = $(this).index();
        done(out);
        stopPP(deepDataAndEvents);
      });
      $e.find(".play-list .remove").die("click").live("click", function(deepDataAndEvents) {
        var e = $(this).parents(".item");
        var classNames = e.index();
        return e.remove(), cb(classNames), stopPP(deepDataAndEvents), false;
      });
      $e.find(".play-list .download").die("click").live("click", function(deepDataAndEvents) {
        var rvar = $(this).parents(".item").index();
        return addImage(rvar), stopPP(deepDataAndEvents), false;
      });
    };
    var destroy = function(recurring) {
      var rule = $(element).parents(".jPlayer");
      if (recurring) {
        var escaped = "";
        $.each(tabs, function(dataAndEvents, suite) {
          escaped += '<li class="item">\t\t\t\t\t\t<span class="name">' + suite.title + '</span>\t\t\t\t\t\t<div class="action-right">\t\t\t\t\t\t\t<span class="download"><i class="icon-download-alt"></i></span>\t\t\t\t\t\t\t<span class="remove"><i class="icon-remove"></i></span>\t\t\t\t\t\t</div>\t\t\t\t\t</li>';
        });
        rule.find(".play-list .content").html(escaped);
      }
      return 0 != tabs.length && tabs[i] ? (rule.find(".item-title").html(tabs[i].title), rule.find(".item").removeClass("this"), rule.find(".item:eq(" + i + ")").addClass("this"), style(rule.find(".player-bg")), undefined) : (i = 0, rule.find(".item-title").html("&nbsp;  "), element.jPlayer("destroy"), element.find(".jPlayer-container").children().remove(), undefined);
    };
    var style = function(element) {
      var socket = create();
      var resolver = create();
      var a = "160deg";
      var dataUrl = "background-image: -webkit-linear-gradient(" + a + ", " + socket + ", " + resolver + ");\t\t\tbackground-image: -moz-linear-gradient(" + a + ", " + socket + ", " + resolver + ");\t\t\tbackground-image: -o-linear-gradient(" + a + ", " + socket + ", " + resolver + ");\t\t\tbackground-image: -ms-linear-gradient(" + a + ", " + socket + ", " + resolver + ");\t\t\tbackground-image: linear-gradient(" + a + ", " + socket + ", " + resolver + ");";
      element.attr("style", dataUrl);
    };
    var create = function() {
      return "#" + (16777215 * Math.random() << 0).toString(16);
    };
    return{
      insert : fail,
      init : init
    };
  }();
  /**
   * @param {Array} node
   * @return {undefined}
   */
  var position = function(node) {
    var value = getter(node[0].ext);
    if (value == object) {
      options.async(["lib/jPlayer/awd.flat/template.js", "lib/jPlayer/jquery.jplayer.min.js", "lib/jPlayer/awd.flat/control.js", "lib/jPlayer/awd.flat/style.css"], function() {
        destination = jplayerTemplateMovie;
        get(node);
      });
    } else {
      options.async(["lib/jPlayer/awd.flat/template.js", "lib/jPlayer/jquery.jplayer.min.js", "lib/jPlayer/awd.flat/control.js", "lib/jPlayer/awd.flat/style.css"], function() {
        _result = jplayerTemplateMusic;
        get(node);
      });
    }
  };
  var postLink = function(attrs, element) {
    options.async(["lib/jPlayer/jquery.jplayer.min.js"], function() {
      var self = {
        solution : "html",
        swfPath : G.static_path + "js/lib/jPlayer/jquery.jplayer.swf",
        media : {
          title : "",
          mp3 : attrs
        },
        ready : function() {
          element.jPlayer("setMedia", self.media).jPlayer("play");
        }
      };
      element.jPlayer("destroy").children().remove();
      element.jPlayer(self);
    });
  };
  return{
    play : position,
    playSound : postLink
  };
});
