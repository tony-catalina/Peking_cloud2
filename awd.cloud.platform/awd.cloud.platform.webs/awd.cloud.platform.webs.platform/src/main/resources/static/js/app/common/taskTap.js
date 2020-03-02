define("app/common/taskTap", [], function() {
  var tasks = {};
  var exception = "";
  var none = 160;
  var bindEvents = function() {
    $(".task_tab .tab").die("mouseenter").live("mouseenter", function() {
      if (!$(this).hasClass("this")) {
        $(this).addClass("hover");
      }
    });
    $(".task_tab .tab").die("mouseleave").live("mouseleave", function() {
      $(this).removeClass("hover");
    });
  };
  var destroy = function($el) {
    var p = $el.attr("id");
    var o = $.dialog.list[p];
    if (undefined == o) {
    	start(p);
        return ;
    }
    var $pre = $("." + p);
    if ("hidden" == $pre.css("visibility")) {
      o.display(true).zIndex();
    } else {
      if ($pre.hasClass("aui_state_focus")) {
        o.display(false);
      } else {
        o.zIndex();
      }
    }
  };
  var create = function() {
    var $el;
    var $this;
    var content;
    var resIndex;
    var s = false;
    var r = false;
    var startX = 0;
    var c = 0;
    var right = 0;
    var n = 0;
    var rangeLength = 0;
    var min = 0;
    $(".task_tab .tab").die("mousedown").live("mousedown", function(o) {
      if (1 == o.which) {
        $el = $(this);
        render(o);
        if (this.setCapture) {
          this.setCapture();
        }
        $(document).mousemove(function(cb) {
          init(cb);
        });
        $(document).one("mouseup", function(first) {
          reset();
          if (this.releaseCapture) {
            this.releaseCapture();
          }
          if (10 > Math.abs(first.pageX - startX)) {
            destroy($el);
          }
        });
      }
    });
    var render = function(t) {
      s = true;
      r = true;
      startX = t.pageX;
      $tab_parent = $(".task_tab");
      $this = $(".task_tab .tab");
      $(".tasktab-dragging").remove();
      content = $el.clone().addClass("tasktab-dragging").prependTo("body");
      n = $sizeInt($this.css("margin-right"));
      rangeLength = $tab_parent.width();
      min = $tab_parent.get(0).getBoundingClientRect().left;
      min += $(window).scrollLeft();
      c = $el.get(0).getBoundingClientRect().left;
      right = $sizeInt($this.css("width"));
      var pickWinTop = $el.get(0).getBoundingClientRect().top - $sizeInt($el.css("margin-top"));
      var value = t.clientX - startX + c;
      $("body").prepend("<div class='dragMaskView'></div>");
      content.css({
        width : right + "px",
        top : pickWinTop,
        left : value
      });
      $el.css("opacity", 0);
    };

    var init = function(data) {
      if (r) {
        if (window.getSelection) {
          window.getSelection().removeAllRanges();
        } else {
          document.selection.empty();
        }
        if (0 == s) {
          render(data);
        }
        var value = data.clientX - startX + c;
        if (!(min > value)) {
          if (!(value > min + rangeLength - right)) {
            content.css("left", value);
            $this.each(function() {
              var i = $(this).get(0).getBoundingClientRect().left;
              if (value > i && i + right / 2 + n > value) {
                if ($el.attr("id") == $(this).attr("id")) {
                  return;
                }
                start($(this).attr("id"), "left");
              }
              if (value > i - right / 2 + n && i > value) {
                if ($el.attr("id") == $(this).attr("id")) {
                  return;
                }
                start($(this).attr("id"), "right");
              }
            });
          }
        }
      }
    };

    var start = function(index, direction) {
      if (!$el.is(":animated") || resIndex != index) {
        resIndex = index;
        $el.stop(true, true);
        $(".insertTemp").remove();
        $this = $(".task_tab .tab");
        var sf_width = $el.width();
        var content = $(".task_tab #" + index);
        var ul = $el.clone(true).insertAfter($el).css({
          "margin-right" : "0px",
          border : "none"
        }).addClass("insertTemp");
        if ("left" == direction) {
          $el.after(content).css("width", "0px");
        } else {
          $el.before(content).css("width", "0px");
          content.before(ul);
        }
        $el.animate({
          width : sf_width + "px"
        }, none);
        ul.animate({
          width : "0px"
        }, none, function() {
          $(this).remove();
          $this = $(".task_tab .tab");
        });
      }
    };

    var reset = function() {
      r = false;
      s = false;
      startTime = 0;
      $(".dragMaskView").remove();
      if (undefined != content) {
        c = $el.get(0).getBoundingClientRect().left;
        content.animate({
          left : c + "px"
        }, none, function() {
          $el.css("opacity", 1);
          $(this).remove();
        });
      }
    };
  };

  var check = function(element) {
    var number = 110;
    var size = number;
    var s = number + 12;
    var self = $(".task_tab .tab");
    var c = $(".task_tab .tabs").width() - 10;
    var a = self.length;
    var b = Math.floor(c / s);
    switch(a > b && (size = Math.floor(c / a) - 12), element) {
      case "add":
        $(".task_tab .tabs .this").css("width", "0").animate({
          width : size + "px"
        }, none);
      case "close":
        self.animate({
          width : size + "px"
        }, none);
        break;
      case "resize":
        self.css("width", size + "px");
        break;
      default:
      ;
    }
  };

  var add = function(id, set) {
    $(".task_tab").removeClass("hidden");
    var pagerNum = set.replace(/<[^>]+>/g, "");
    var imageFieldHTML = '<div class="tab taskBarMenu" id="' + id + '" title="' + pagerNum + '">' + set + "</div>";
    $(imageFieldHTML).insertBefore(".task_tab .last");
    check("add");
    tasks[id] = {
      id : id,
      name : name
    };
  };

  var focus = function(e) {
    $(".task_tab .this").removeClass("this");
    $(".task_tab #" + e).addClass("this");
    exception = e;
  };

  var start = function(index) {
    $(".task_tab #" + index).animate({
      width : 0
    }, none, function() {
      if ($(".task_tab #" + index).remove(), check("close"), 0 == $(".tabs .tab").length && "desktop" != Config.pageApp) {
        var distance = 31;
        $(".task_tab").animate({
          bottom : "-" + distance + "px"
        }, 200, 0, function() {
          $(this).css({
            bottom : "0px"
          }).addClass("hidden");
        });
      }
    });
    delete tasks[index];
  };

  var init = function() {
    $('<i class="dialog_menu"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".dialog_menu",
      items : {
        dialog_quite : {
          name : "关闭",
          className : "dialog_quite",
          icon : "remove",
          accesskey : "q"
        },
        dialog_max : {
          name : "最大化",
          className : "dialog_max",
          icon : "resize-full",
          accesskey : "a"
        },
        dialog_min : {
          name : "最小化",
          className : "dialog_min",
          icon : "minus",
          accesskey : "i"
        },
        sep1 : "--------",
        refresh : {
          name : "刷新",
          className : "refresh",
          icon : "refresh",
          accesskey : "r"
        },
        open_window : {
          name : "浏览器打开",
          className : "open_window",
          icon : "globe",
          accesskey : "b"
        },
        qrcode : {
          name : "URL 二维码",
          className : "qrcode",
          icon : "qrcode",
          accesskey : "c"
        }
      },
      callback : function(__, self) {
        var name = self.$trigger.attr("id");
        var w = $.dialog.list[name];
        switch(__) {
          case "dialog_quite":
            w.close();
            break;
          case "dialog_min":
            w._clickMin(false);
            break;
          case "dialog_max":
            w._clickMax();
            break;
          case "refresh":
            w.refresh();
            break;
          case "open_window":
            w.open_window();
            break;
          case "qrcode":
            core.qrcode(w.DOM.wrap.find("iframe").attr("src"));
            break;
          default:
          ;
        }
      }
    });
    $(".aui_title img,.aui_title .x-item-file").die("click").live("click", function(touches) {
      var from = $(this).offset();
      from.top += $(this).outerHeight();
      $(this).parent().parent().contextMenu({
        x : touches.pageX,
        y : from.top
      });
    }).die("dblclick").live("dblclick", function() {
      var name = $(this).parent().parent().attr("id");
      var ref = $.dialog.list[name];
      ref.close();
      rightMenu.hidden();
    });
  };
  var open = function() {
    $('<i class="taskBarMenu"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".taskBarMenu",
      items : {
        quitOthers : {
          name : "关闭其他",
          className : "quitOthers",
          icon : "remove-circle",
          accesskey : "o"
        },
        quit : {
          name : "关闭",
          className : "quit",
          icon : "remove",
          accesskey : "q"
        }
      },
      callback : function(__, self) {
        var name = self.$trigger.attr("id");
        var ref = $.dialog.list[name];
        switch(__) {
          case "quitOthers":
            $.each($.dialog.list, function(error, gridStore) {
              if (name != error) {
                gridStore.close();
              }
            });
            break;
          case "quit":
            ref.close();
        }
      }
    });
  };
  var render = function() {
    $.contextMenu({
      zIndex : 9999,
      selector : ".task_tab",
      items : {
        closeAll : {
          name : "关闭所有",
          icon : "remove-circle",
          accesskey : "q"
        },
        showAll : {
          name : "显示所有窗口",
          icon : "th-large",
          accesskey : "s"
        },
        hideAll : {
          name : "最小化所有",
          icon : "remove",
          accesskey : "h"
        }
      },
      callback : function(__, self) {
        var name = self.$trigger.attr("id");
        switch($.dialog.list[name], __) {
          case "showAll":
            $.each($.dialog.list, function(dataAndEvents, result) {
              result.display(true);
            });
            break;
          case "hideAll":
            $.each($.dialog.list, function(dataAndEvents, result) {
              result.display(false);
            });
            break;
          case "closeAll":
            $.each($.dialog.list, function(dataAndEvents, gridStore) {
              gridStore.close();
            });
            break;
          default:
          ;
        }
      }
    });
  };
  return{
    add : add,
    focus : focus,
    close : start,
    init : function() {
      var statsTemplate = '<div class="task_tab"><div class="tabs"><div class="last" style="clear:both;"></div></div></div>';
      $(statsTemplate).appendTo("body");
      if ("desktop" != Config.pageApp) {
        $(".task_tab").addClass("hidden");
      }
      $(window).bind("resize", function() {
        check("resize");
      });
      bindEvents();
      init();
      open();
      render();
      create();
    }
  };
});
