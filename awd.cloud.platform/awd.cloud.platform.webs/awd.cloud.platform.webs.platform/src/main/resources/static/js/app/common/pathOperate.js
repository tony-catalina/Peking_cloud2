define("app/common/pathOperate", [], function(require) {
  var x = ["/", "\\", ":", "*", "?", '"', "<", ">", "|"];
  var color = ["/", "\\"];
  var join = function(str) {
    var endsWith = function(s, value) {
      var x = value.length;
      var y = 0;
      for (;x > y;y++) {
        if (s.indexOf(value[y]) > 0) {
          return true;
        }
      }
      return false;
    };
    var value = color;
    return G.system_os && ("windows" == G.system_os && (value = x)), endsWith(str, value) ? (Tips.tips("文件名不允许出现" + ":    " + value.join(", "), false), false) : true;
  };
  var done = function(arr) {
    var list = [];
    var done = function(msg) {
      return msg ? msg.replace(/"/g, '\\\\"') : msg;
    };
    var hash = 0;
    for (;arr.length > hash;hash++) {
      list.push({
        type : done(arr[hash].type),
        path : urlEncode(done(arr[hash].path))
      });
    }
    return "data_arr=" + jsonEncode(list);
  };
  var quote = function(string, str) {
    var whitespace = "0123456789零一二三四五六七八九十百千万壹贰叁肆伍陆柒捌玖拾佰仟万";
    var i = 0;
    for (;Math.max(string.length, str.length) > i;i++) {
      if (string.charAt(i) != str.charAt(i)) {
        var bv = whitespace.indexOf(string.charAt(i));
        var av = whitespace.indexOf(str.charAt(i));
        return-1 != bv && -1 != av ? bv > av ? 1 : av > bv ? -1 : 0 : string.charAt(i) > str.charAt(i) ? 1 : string.charAt(i) < str.charAt(i) ? -1 : 0;
      }
    }
    return 0;
  };
  var strSort = function(event, options) {
    if (undefined == event || undefined == options) {
      return 0;
    }
    var r20 = /([0-9\.]+)/g;
    var str = ("" + event).toLowerCase() || "";
    var text = ("" + options).toLowerCase() || "";
    var space = String.fromCharCode(0);
    var s = str.replace(r20, space + "$1" + space).split(space);
    var b = text.replace(r20, space + "$1" + space).split(space);
    var open = (new Date(str)).getTime();
    var close = open ? (new Date(text)).getTime() : null;
    if (close) {
      if (close > open) {
        return-1;
      }
      if (open > close) {
        return 1;
      }
    }
    var p = 0;
    var li = Math.max(s.length, b.length);
    for (;li > p;p++) {
      if (oFxNcL = parseFloat(s[p]) || s[p], oFyNcL = parseFloat(b[p]) || b[p], oFxNcL != oFyNcL) {
        if ("string" == typeof oFxNcL && "string" == typeof oFyNcL) {
          var template = quote(oFxNcL, oFyNcL);
          if (0 != template) {
            return template;
          }
        } else {
          if (oFyNcL > oFxNcL) {
            return-1;
          }
          if (oFxNcL > oFyNcL) {
            return 1;
          }
        }
      }
    }
    return 0;
  };
  var handleInput = function(e, on) {
    if (e) {
      var ret = core.pathThis(e);
      return join(ret) ? ($.ajax({
        dataType : "json",
        url : "explorer/mkfile?path=" + urlEncode(e),
        beforeSend : function() {
          Tips.loading();
        },
        error : core.ajaxError,
        success : function(opts) {
          Tips.close(opts);
          if ("function" == typeof on) {
            if (opts && opts.info) {
              on(opts.info);
            } else {
              on(false);
            }
          }
        }
      }), undefined) : ("function" == typeof on && on(), undefined);
    }
  };
  var get = function(params, callback) {
    if (params) {
      var ret = core.pathThis(params);
      return join(ret) ? ($.ajax({
        dataType : "json",
        url : "explorer/mkdir?path=" + urlEncode(params),
        beforeSend : function() {
          if ("function" == typeof callback) {
            Tips.loading();
          }
        },
        error : core.ajaxError,
        success : function(item) {
          Tips.close(item);
          if ("function" == typeof callback) {
            if (item && item.info) {
              callback(item.info);
            } else {
              callback(false);
            }
          }
        }
      }), undefined) : ("function" == typeof callback && callback(), undefined);
    }
  };
  var request = function(val, t, cb) {
    return val && (t && val != t) ? join(core.pathThis(t)) ? ($.ajax({
      type : "POST",
      dataType : "json",
      url : "explorer/pathRname",
      data : "path=" + urlEncode(val) + "&rname_to=" + urlEncode(t),
      beforeSend : function() {
        Tips.loading();
      },
      error : core.ajaxError,
      success : function(opts) {
        Tips.close(opts);
        if ("function" == typeof cb) {
          if (opts && opts.info) {
            cb(opts.info);
          } else {
            cb(false);
          }
        }
      }
    }), undefined) : ("function" == typeof cb && cb(), undefined) : undefined;
  };
  var remove = function(path, fn, d, deepDataAndEvents) {
    if (d = undefined == d ? false : d, deepDataAndEvents = undefined == deepDataAndEvents ? false : deepDataAndEvents, window.event && (window.event.shiftKey && (deepDataAndEvents = true)), !(1 > path.length)) {
      var MSG_CLOSURE_CUSTOM_COLOR_BUTTON = "删除确认";
      var expected = "确认删除选中内容吗？";
      var appFrontendUrl = "explorer/pathDelete";
      var m = done(path);
      if ("share" == path[0].type && (MSG_CLOSURE_CUSTOM_COLOR_BUTTON = "取消共享", expected = "确定取消共享？公开连接将失效.", appFrontendUrl = "userShare/del"), deepDataAndEvents && (expected = "确定要永久删除此文档吗？", MSG_CLOSURE_CUSTOM_COLOR_BUTTON = "永久删除", appFrontendUrl += "&shiftDelete=1"), ("recycle_clear" == path[0].type || (G.USER_RECYCLE && G.this_path == G.USER_RECYCLE || G.this_path == core.pathFather(G.myhome) + "recycle_awd/")) && (expected = "您确定要彻底清空回收站吗？", appFrontendUrl =
      "explorer/pathDeleteRecycle", MSG_CLOSURE_CUSTOM_COLOR_BUTTON = "清空回收站", "recycle_clear" == path[0].type && (m = "post_empty=1")), path[0] && path[0].path) {
        var label = "<b>" + htmlEncode(core.pathThis(path[0].path)) + "</b>";
        expected = path.length > 1 ? label + ' ... <span class="badge">' + path.length + "项内容" + "</span><br/>" + expected : label + "<br/>" + expected;
      }
      var next = function() {
        $.ajax({
          url : appFrontendUrl,
          type : "POST",
          dataType : "json",
          data : m,
          beforeSend : function() {
            Tips.loading();
          },
          error : core.ajaxError,
          success : function(item) {
            if (Tips.close(item), ShareData.frameTop("", function(n) {
              n.ui.f5();
            }), "share" == path[0].type) {
              G.self_share = item.info;
              var srv = $.dialog.list.share_dialog;
              if (undefined != srv) {
                srv.close();
              }
            }
            if (MSG_CLOSURE_CUSTOM_COLOR_BUTTON == "清空回收站") {
              core.playSound("recycle_clear");
            } else {
              core.playSound("file_remove");
            }
            if ("function" == typeof fn) {
              fn(item);
            }
          }
        });
      };
      if (d) {
        next();
      } else {
        $.dialog({
          id : "dialog_path_remove",
          fixed : true,
          icon : "question",
          title : MSG_CLOSURE_CUSTOM_COLOR_BUTTON,
          padding : "40px 40px",
          lock : true,
          background : "#000",
          opacity : 0.1,
          content : "<div style='width:200px'>" + expected + "</div>",
          ok : next,
          cancel : true
        });
      }
    }
  };
  var post = function(url) {
    if (!(1 > url.length)) {
      $.ajax({
        url : "explorer/pathCopy",
        type : "POST",
        dataType : "json",
        data : done(url),
        error : core.ajaxError,
        success : function(textStatus) {
          Tips.tips(textStatus);
        }
      });
    }
  };
  var next = function(event) {
    var path = event.path;
    var m = core.pathPre(path);
    if (m == G.AWD_GROUP_PATH || (m == G.AWD_GROUP_SHARE || m == G.AWD_USER_SHARE)) {
      return Tips.tips("仅支持分享您自己的文档!", "warning"), undefined;
    }
    var type = "folder" == event.type ? "folder" : "file";
    if (!(1 > path.length)) {
      if (core.authCheck("userShare:set")) {
        $.ajax({
          url : "./userShare/checkByPath?path=" + urlEncode(path),
          dataType : "json",
          error : core.ajaxError,
          success : function(data) {
            if (data.code) {
              f(data.data);
            } else {
              G.self_share = data.info;
              var defaults = {
                path : path,
                type : type,
                name : core.pathThis(path)
              };
              fn(defaults, function(err) {
                if (err.code) {
                  G.self_share = err.info;
                  ui.f5();
                  f(err.data);
                } else {
                  Tips.tips(err);
                  f(undefined, function() {
                    $(".content_info input[name=type]").val(type);
                    $(".content_info input[name=path]").val(path);
                    $(".content_info input[name=name]").val(core.pathThis(path) + "(1)");
                    if ("file" == type) {
                      $(".label_code_read").addClass("hidden");
                      $(".label_can_upload").addClass("hidden");
                    }
                  });
                }
              });
            }
          }
        });
      }
    }
  };
  var runTest = function(t, done) {
    if (0 != $(".share_dialog").length) {
      $(".share_dialog").shake(3, 30, 100);
    }
    require.async(["lib/jquery.datetimepicker/jquery.datetimepicker.css", "lib/jquery.datetimepicker/jquery.datetimepicker.js"], function() {
      setup(t);
      if (undefined != done) {
        done();
      }
    });
  };
  /**
   * @param {string} s
   * @param {Object} next
   * @return {undefined}
   */
  var fn = function(s, next) {
    $.ajax({
      url : "userShare/set",
      data : s,
      type : "POST",
      dataType : "json",
      beforeSend : function() {
        $(".share_create_button").addClass("disabled");
      },
      error : function() {
        Tips.tips("操作失败", false);
      },
      success : function(responseObject) {
        $(".share_create_button").removeClass("disabled");
        if (undefined != next) {
          next(responseObject);
        }
      }
    });
  };
  var setup = function(target) {
    var html = require("./tpl/share.html");
    var isFunction = template.compile(html);
    var h = isFunction({
      LNG : LNG
    });
    $.dialog({
      id : "share_dialog",
      simple : true,
      resize : false,
      width : 425,
      title : "共享",
      padding : "0",
      fixed : true,
      content : h
    });
    var lang = "zh-CN" == G.lang ? "ch" : "en";
    $("#share_time").datetimepicker({
      format : "Y/m/d",
      formatDate : "Y/m/d",
      timepicker : false,
      lang : lang
    });
    $("#share_time").unbind("blur").bind("blur", function(deepDataAndEvents) {
      stopPP(deepDataAndEvents);
    });
    var init = function(item) {
      if ($(".share_setting_more").addClass("hidden"), undefined == item) {
        $(".share_has_url").addClass("hidden");
        $(".share_action .share_remove_button").addClass("hidden");
        $(".content_info input[name=sid]").val("");
        $(".content_info input[name=type]").val("");
        $(".content_info input[name=name]").val("");
        $(".content_info input[name=show_name]").val("");
        $(".content_info input[name=path]").val("");
        $(".content_info input[name=time_to]").val("");
        $(".content_info input[name=share_password]").val("");
        $(".share_view_info").addClass("hidden");
      } else {
        if (item.can_upload === undefined) {
          item.can_upload = "";
        }
        target = item;
        if (!item.show_name) {
          item.show_name = item.name;
        }
        $(".content_info input[name=sid]").val(item.sid);
        $(".content_info input[name=type]").val(item.type);
        $(".content_info input[name=name]").val(item.name);
        $(".content_info input[name=show_name]").val(item.show_name);
        $(".content_info input[name=path]").val(item.path);
        $(".content_info input[name=time_to]").val(item.time_to);
        $(".content_info input[name=share_password]").val(item.share_password);
        $(".share_view_info").removeClass("hidden");
        if (item.num_download === undefined) {
          item.num_download = 0;
        }
        if (item.num_view === undefined) {
          item.num_view = 0;
        }
        var distanceToUserValue = "浏览:" + item.num_view + "  " + "下载:" + item.num_download;
        $(".share_view_info").html(distanceToUserValue);
        if ("1" == item.code_read) {
          $(".content_info input[name=code_read]").attr("checked", "checked");
        } else {
          $(".content_info input[name=code_read]").removeAttr("checked");
        }
        if ("1" == item.not_download) {
          $(".content_info input[name=not_download]").attr("checked", "checked");
        } else {
          $(".content_info input[name=not_download]").removeAttr("checked");
        }
        if ("1" == item.can_upload) {
          $(".content_info input[name=can_upload]").attr("checked", "checked");
        } else {
          $(".content_info input[name=can_upload]").removeAttr("checked");
        }
        $(".share_has_url").removeClass("hidden");
        if ("file" == item.type) {
          $(".label_code_read").addClass("hidden");
          $(".label_can_upload").addClass("hidden");
        } else {
          $(".label_code_read").removeClass("hidden");
          $(".label_can_upload").removeClass("hidden");
        }
        var type = item.type;
        if ("folder" == item.type) {
          type = 1 == item.code_read ? "code_read" : "folder";
        }
        var oldPagerPosition = G.app_host + "share/" + type + "?user=" + G.user_id + "&sid=" + item.sid;
        $(".content_info .share_url").val(oldPagerPosition);
        if (item.time_to || (item.share_password || (item.can_upload || (item.code_read || item.not_download)))) {
          $(".share_setting_more").removeClass("hidden");
        }
        $(".share_remove_button").removeClass("hidden");
        $(".share_create_button").text("保存配置");
      }
    };

    var focus = function() {
      $(".share_action .share_remove_button").unbind("click").click(function() {
        d([{
          type : "share",
          path : target.sid
        }], function() {
          ui.f5();
        });
      });
      $(".content_info .share_more").unbind("click").click(function() {
        $(".share_setting_more").toggleClass("hidden");
      });
      $(".share_action .share_create_button").unbind("click").click(function() {
        var buf = "";
        $(".share_dialog .content_info input[name]").each(function() {
          var divider = urlEncode($(this).val());
          if ("checkbox" == $(this).attr("type")) {
            /** @type {string} */
            divider = $(this).attr("checked") ? "1" : "";
          }
          buf += "&" + $(this).attr("name") + "=" + divider;
        });
        fn(buf, function(message) {
          if (message.code) {
            Tips.tips("操作成功", true);
            G.self_share = message.info;
            ui.f5();
            init(message.data);
            $(".share_create_button").text("保存配置");
          } else {
            Tips.tips(message);
          }
        });
      });
      $(".content_info .open_window").unbind("click").bind("click", function() {
        window.open($("input.share_url").val());
      });
      $(".content_info .qrcode").unbind("click").bind("click", function() {
        core.qrcode($("input.share_url").val());
      });
      var target = $("input.share_url");
      var textarea = target.get(0);
      target.unbind("hover click").bind("hover click", function() {
        $(this).focus();
        var end = target.val().length;
        if ($.browser.msie) {
          var range = textarea.createTextRange();
          range.moveEnd("character", -textarea.value.length);
          range.moveEnd("character", end);
          range.moveStart("character", 0);
          range.select();
        } else {
          textarea.setSelectionRange(0, end);
        }
      });
    };
    init(target);
    focus();
  };
  var login = function(postData) {
    $.ajax({
      url : "setting/set?key=wall&v=" + urlEncode(postData),
      dataType : "json",
      success : function(textStatus) {
        Tips.tips(textStatus);
      }
    });
  };
  var ajax = function(path, url, i, options, fn) {
    if (console.log(path, url, i, options, fn), !(1 > path.length)) {
      var h;
      var base = G.my_desktop;
      if (options) {
        base = core.pathFather(path);
      }
      h = "folder" == i ? "ui.path.list(hashDecode('" + hashEncode(path) + "'));" : "ui.path.open(hashDecode('" + hashEncode(path) + "'));";
      var materialArgs = urlEncode(base + url + ".oexe");
      var o = core.getPathIcon(path);
      if ("" == o.icon) {
        o.icon = i;
      }
      $.ajax({
        url : "./explorer/mkfile?path=" + materialArgs,
        type : "POST",
        dataType : "json",
        data : {
          content : jsonEncode({
            type : "app_link",
            content : h,
            icon : o.icon
          })
        },
        success : function(result) {
          Tips.tips(result);
          if (result.code) {
            ShareData.frameTop("", function(n) {
              n.ui.f5();
            });
            if ("function" == typeof fn) {
              fn(result.info);
            }
          }
        }
      });
    }
  };
  var submit = function(params, fn) {
    if (!(1 > params.length)) {
      var queryString = core.pathThis(params);
      var url = core.pathFather(params);
      jsrun = "core.explorerCode('" + urlEncode(params) + "');";
      var materialArgs = urlEncode(url + queryString + "_project.oexe");
      $.ajax({
        url : "./explorer/mkfile?path=" + materialArgs,
        type : "POST",
        dataType : "json",
        data : 'content={"type":"app_link","content":"' + jsrun + '","icon":"folder.png"}',
        success : function(result) {
          if (result.code) {
            if ("function" == typeof fn) {
              fn(result.info);
            }
          }
        }
      });
    }
  };
  var success = function(data) {
    if (!(1 > data.length)) {
      $.ajax({
        url : "explorer/pathCute",
        type : "POST",
        dataType : "json",
        data : done(data),
        error : core.ajaxError,
        success : function(textStatus) {
          Tips.tips(textStatus);
        }
      });
    }
  };
  var onDeviceReady = function(evt, fn) {
    if (evt) {
      Tips.loading("移动操作中...");
      setTimeout(function() {
        var appFrontendUrl = "explorer/pathPast?path=" + urlEncode(evt);
        $.ajax({
          url : appFrontendUrl,
          dataType : "json",
          error : core.ajaxError,
          success : function(self) {
            Tips.close(self.data, self.code);
            if ("function" == typeof fn) {
              fn(self.info);
            }
          }
        });
      }, 50);
    }
  };

  var info = function(obj) {
    var fns = {};
    fns.file_info = require("./tpl/fileinfo/file_info.html");
    fns.path_info = require("./tpl/fileinfo/path_info.html");
    fns.path_info_more = require("./tpl/fileinfo/path_info_more.html");
    if (1 > obj.length) {
      obj = [{
        path : G.this_path,
        type : "folder"
      }];
    }
    var deep = "info";
    if (1 == obj.length) {
      deep = "file" == obj[0].type ? core.pathExt(obj[0].path) : "folder";
    }
    Tips.loading("获取中...");
    core.fileInfo(done(obj), function(data) {
      if (!data.code) {
        return Tips.close(data), undefined;
      }
      Tips.close("获取成功!", true);
      var i = "path_info_more";
      var viewName = "属性";
      if (1 == obj.length) {
        i = "folder" == obj[0].type ? "path_info" : "file_info";
        viewName = core.pathThis(obj[0].path);
        if (viewName.length > 15) {
          viewName = viewName.substr(0, 15) + "...  " + "属性";
        }
      }
      var success = template.compile(fns[i]);
      var id = UUID();
      data.data.is_root = G.is_root;
      data.data.LNG = LNG;
      data.data.atime = date("Y\/m\/d H:i:s", data.data.atime);
      data.data.ctime = date("Y\/m\/d H:i:s", data.data.ctime);
      data.data.mtime = date("Y\/m\/d H:i:s", data.data.mtime);
      data.data.size_friendly = core.fileSize(data.data.size);
      var F = $.dialog({
        id : id,
        padding : 5,
        ico : core.iconSmall(deep),
        fixed : true,
        title : viewName,
        content : success(data.data),
        ok : true
      });
      var ud = 15 * $(".aui_outer .pathinfo").length;
      F.DOM.wrap.css({
        left : "+=" + ud + "px",
        top : "+=" + ud + "px"
      });
      update(id, obj);
    });
  };
  var update = function(val, url) {
    var $e = $("." + val);
    $e.find(".open_window").bind("click", function() {
      window.open($e.find("input.download_url").val());
    });
    $e.find(".qrcode").unbind("click").bind("click", function() {
      core.qrcode($e.find("input.download_url").val(), $e.find(".qrcode").get(0));
    });
    var errors = $e.find(".file_md5_loading");
    if (1 == errors.length) {
      var result = done(url);
      result += "&get_md5=1";
      core.fileInfo(result, function(err) {
        errors.removeClass("file_md5_loading");
        if (err.code) {
          errors.html(err.data.file_md5);
        } else {
          errors.html("操作失败");
        }
      });
    }
    var elems = $e.find("input.download_url");
    var textarea = elems.get(0);
    elems.unbind("hover click").bind("hover click", function() {
      $(this).focus();
      var end = elems.val().length;
      if ($.browser.msie) {
        var range = textarea.createTextRange();
        range.moveEnd("character", -textarea.value.length);
        range.moveEnd("character", end);
        range.moveStart("character", 0);
        range.select();
      } else {
        textarea.setSelectionRange(0, end);
      }
    });
    $e.find(".edit_chmod").click(function() {
      var textEl = $(this).parent().find("input");
      var buffer = $(this);
      $.ajax({
        url : "explorer/pathChmod?mod=" + textEl.val(),
        type : "POST",
        data : done(url),
        beforeSend : function() {
          buffer.text("操作中...");
        },
        error : function() {
          buffer.text("保存");
        },
        success : function(data) {
          buffer.text(data.data).animate({
            opacity : 0.6
          }, 400, 0).delay(1E3).animate({
            opacity : 1
          }, 200, 0, function() {
            buffer.text("保存");
          });
          if (data.code) {
            ui.f5();
          }
        }
      });
    });
  };
  var send = function(message) {
    if (core.authCheck("explorer:fileDownload") && !(1 > message.length)) {
      var appFrontendUrl = "explorer/zipDownload";
      if (G.share_page !== undefined) {
        appFrontendUrl = "share/zipDownload?user=" + G.user + "&sid=" + G.sid;
      }
      $.ajax({
        url : appFrontendUrl,
        type : "POST",
        dataType : "json",
        data : done(message),
        beforeSend : function() {
          Tips.loading("压缩后会自动下载,请稍后...");
        },
        error : core.ajaxError,
        success : function(result) {
          Tips.close(result);
          Tips.tips(result);
          var i = "explorer/fileDownloadRemove?path=" + urlEncode(result.info);
          if (G.share_page !== undefined) {
            i = "share/fileDownloadRemove?user=" + G.user + "&sid=" + G.sid + "&path=" + urlEncode(result.info);
          }
          i += "&access_token=" + G.access_token;
          $.dialog({
            icon : "succeed",
            title : false,
            time : 2,
            content : "即将下载" + "..."
          });
          $('<iframe src="' + i + '" style="display:none;width:0px;height:0px;"></iframe>').appendTo("body");
        }
      });
    }
  };
  var doRequest = function(url, callback, method) {
    if (!(1 > url.length)) {
      if (!method) {
        method = "zip";
      }
      $.ajax({
        url : "explorer/zip?fileType=" + method,
        type : "POST",
        dataType : "json",
        data : done(url),
        beforeSend : function() {
          Tips.loading("正在压缩...");
        },
        error : core.ajaxError,
        success : function(result) {
          Tips.close(result);
          if (result.code) {
            core.playSound("drag_drop");
          }
          if ("function" == typeof callback) {
            callback(result.info);
          }
        }
      });
    }
  };
  var create = function(params, fn, var_args) {
    if (params) {
      var init = function(data) {
        $.ajax({
          url : data,
          beforeSend : function() {
            Tips.loading("正在解压...");
          },
          error : core.ajaxError,
          success : function(res) {
            Tips.close(res);
            if ("function" == typeof fn) {
              fn(res);
            }
          }
        });
      };
      var pdataCur = "explorer/unzip?path=" + urlEncode(params);
      if ("to_this" == var_args) {
        pdataCur += "&to_this=1";
      }
      if ("unzip_to_folder" == var_args) {
        core.api.pathSelect({
          type : "folder",
          title : "解压到..."
        }, function(dataAndEvents) {
          pdataCur += "&path_to=" + dataAndEvents;
          init(pdataCur);
        });
      } else {
        init(pdataCur);
      }
    }
  };
  var cuteDrag = function(error, path, callback) {
    if (path) {
      $.ajax({
        url : "explorer/pathCuteDrag",
        type : "POST",
        dataType : "json",
        data : done(error) + "&path=" + urlEncode(path + "/"),
        beforeSend : function() {
          Tips.loading("移动操作中...");
        },
        error : core.ajaxError,
        success : function(result) {
          Tips.close(result);
          if (result.code) {
            core.playSound("drag_drop");
          }
          if ("function" == typeof callback) {
            callback(result.info);
          }
        }
      });
    }
  };
  var callback = function(results, o, fn, stdout) {
    if (o) {
      if (undefined == stdout) {
        stdout = 0;
      }
      $.ajax({
        url : "explorer/pathCopyDrag",
        type : "POST",
        dataType : "json",
        data : done(results) + "&path=" + urlEncode(o + "/") + "&filename_auto=" + Number(stdout),
        beforeSend : function() {
          Tips.loading("移动操作中...");
        },
        error : core.ajaxError,
        success : function(result) {
          Tips.close(result);
          if (result.code) {
            core.playSound("drag_drop");
          }
          if ("function" == typeof fn) {
            fn(result.info);
          }
        }
      });
    }
  };
  var clipboard = function() {
    var log = function(obj, message) {
      var n = '<div style="padding:20px;">null!</div>';
      if (0 != obj.length) {
        n = '<div style="height:200px;overflow:auto;padding:10px;width:400px"><b>' + '剪切板状态:' + LNG[message] + "</b><br/>";
        var maxLen = 40;
        var i = 0;
        for (;obj.length > i;i++) {
          var property = obj[i];
          var path = property.path;
          path = maxLen > path.length ? path : "..." + path.substr(-maxLen);
          n += "<br/>" + property.type + ":  <a href='javascript:ui.pathOpen.open(\"" + htmlEncode(property.path) + '","' + property.type + "\");'>" + path + "</a>";
        }
        n += "</div>";
      }
      return n;
    };
    $.ajax({
      url : "explorer/clipboard",
      dataType : "json",
      error : core.ajaxError,
      success : function(result) {
        if (result.code) {
          $.dialog({
            title : "查看剪贴板",
            padding : 0,
            height : 200,
            width : 400,
            content : log(result.data, result.info)
          });
        }
      }
    });
  };
  var dialog = function(params, fn, $injector) {
    var init = function() {
      $.ajax({
        url : "fav/del&name=" + urlEncode(params),
        dataType : "json",
        async : false,
        success : function(textStatus) {
          if ("function" == typeof fn) {
            fn(textStatus);
          }
        }
      });
    };
    return $injector ? (init(), undefined) : ($.dialog({
      id : "dialog_fav_remove",
      fixed : true,
      icon : "question",
      title : "取消收藏",
      width : 200,
      padding : "40px 20px",
      content : "取消收藏" + "?",
      ok : init,
      cancel : true
    }), undefined);
  };
  var remove = function(e) {
    if (e) {
      if (-1 == trim(core.pathClear(e.path), "/").indexOf("/")) {
        var data = core.getPathIcon(e.path, e.name);
        if ("" != data.icon) {
          e.ext = data.icon;
          e.name = data.name;
        }
      }
      $.ajax({
        url : "fav/add",
        dataType : "json",
        data : e,
        success : function(status) {
          Tips.tips(status);
          if (status.code) {
            if ("desktop" != Config.pageApp) {
              ui.tree.refreshFav();
            }
          }
        }
      });
    }
  };
  var open = function(target) {
    var v = {};
    return v.type = target.find("input[type=radio]:checked").val(), v.content = target.find("textarea").val(), v.group = target.find("[name=group]").val(), target.find("input[type=text]").each(function() {
      var db = $(this).attr("name");
      v[db] = $(this).val();
    }), target.find("input[type=checkbox]").each(function() {
      var db = $(this).attr("name");
      v[db] = "checked" == $(this).attr("checked") ? 1 : 0;
    }), v;
  };
  var run = function(target) {
    target.find(".type input").change(function() {
      var apptype = $(this).attr("apptype");
      target.find("[data-type]").addClass("hidden");
      target.find("[data-type=" + apptype + "]").removeClass("hidden");
    });
    target.find(".app_edit_select_icon").unbind("click").bind("click", function() {
      var expires = G.basic_path + "static/images/file_icon/icon_app/";
      if (!G.is_root) {
        expires = "";
      }
      core.api.pathSelect({
        type : "file",
        title : "请选择文件...",
        firstPath : expires
      }, function(d) {
        d = core.path2url(d);
        target.find(".app_edit_select_icon_input").val(d);
      });
    });
    target.find(".size-full").unbind("click").bind("click", function() {
      var selected = $(this).prop("checked");
      if (selected) {
        target.find("[name=width]").val("100%");
        target.find("[name=height]").val("100%");
      } else {
        target.find("[name=width]").val("800");
        target.find("[name=height]").val("600");
      }
    });
  };
  var init = function(item, fn, method) {
    var $this;
    var href;
    var h;
    var MSG_CLOSURE_CUSTOM_COLOR_BUTTON = "创建应用";
    var uid = UUID();
    var html = require("./tpl/app_edit.html");
    var isFunction = template.compile(html);
    switch(undefined == method && (method = "user_edit"), "root_edit" == method && (item = item), "user_edit" == method || "root_edit" == method ? (MSG_CLOSURE_CUSTOM_COLOR_BUTTON = "修改应用", h = isFunction({
      LNG : LNG,
      uuid : uid,
      data : item
    })) : h = isFunction({
      LNG : LNG,
      uuid : uid,
      data : {}
    }), $.dialog({
      fixed : true,
      width : 450,
      id : uid,
      padding : 15,
      title : MSG_CLOSURE_CUSTOM_COLOR_BUTTON,
      content : h,
      button : [{
        name : "预览",
        callback : function() {
          return core.openApp(open($this)), false;
        }
      }, {
        name : "保存",
        focus : true,
        callback : function() {
          var res = open($this);
          switch(method) {
            case "user_add":
              var hash = urlEncode(G.this_path + res.name);
              href = "./app/user_app?action=add&path=" + hash;
              break;
            case "user_edit":
              href = "./app/user_app?path=" + urlEncode(item.path);
              break;
            case "root_add":
              href = "./app/add?name=" + urlEncode(res.name);
              break;
            case "root_edit":
              href = "./app/edit?name=" + urlEncode(res.name) + "&old_name=" + urlEncode(item.name);
              break;
            default:
            ;
          }
          $.ajax({
            url : href,
            type : "POST",
            dataType : "json",
            data : {
              data : urlEncode(jsonEncode(res))
            },
            beforeSend : function() {
              Tips.loading();
            },
            error : core.ajaxError,
            success : function(res) {
              if (Tips.close(res), res.code) {
                if ("root_edit" == method || "root_add" == method) {
                  if (!res.code) {
                    return;
                  }
                  ShareData.frameTop("Openapp_store", function(global) {
                    global.App.reload();
                  });
                } else {
                  if ("function" == typeof fn) {
                    fn();
                  } else {
                    ui.f5();
                  }
                }
              }
            }
          });
        }
      }]
    }), $this = $("." + uid), G.is_root || $(".appbox .appline .right a.open").remove(), item.group && $this.find("option").eq(item.group).attr("selected", 1), $this.find(".aui_content").css("overflow", "inherit"), method) {
      case "user_edit":
        $this.find(".name").addClass("hidden");
        $this.find(".desc").addClass("hidden");
        $this.find(".group").addClass("hidden");
        $this.find("option[value=" + item.group + "]").attr("checked", true);
        break;
      case "user_add":
        $this.find(".desc").addClass("hidden");
        $this.find(".group").addClass("hidden");
        $this.find("[apptype=url]").attr("checked", true);
        $this.find("[data-type=url] input[name=resize]").attr("checked", true);
        $this.find("input[name=width]").attr("value", "800");
        $this.find("input[name=height]").attr("value", "600");
        $this.find("input[name=icon]").attr("value", "oexe.png");
        break;
      case "root_add":
        $this.find("[apptype=url]").attr("checked", true);
        $this.find("[data-type=url] input[name=resize]").attr("checked", true);
        $this.find("input[name=width]").attr("value", "800");
        $this.find("input[name=height]").attr("value", "600");
        $this.find("input[name=icon]").attr("value", "oexe.png");
        break;
      case "root_edit":
        $this.find("option[value=" + item.group + "]").attr("selected", true);
        break;
      default:
      ;
    }
    run($this);
  };
  var appList = function() {
    core.appStore("AWD-MIS-APPSTORE-WEB");
  };
  var appAddURL = function(item) {
    if (!(item && (4 > item.length && "http" != item.substring(0, 4)))) {
      $.ajax({
        url : "./app/get_url_title?url=" + item,
        dataType : "json",
        beforeSend : function() {
          Tips.loading();
        },
        success : function(event) {
          var key = event.data;
          key = key.replace(/[\/\\]/g, "_");
          Tips.close(event);
          var options = {
            content : item,
            type : "url",
            desc : "",
            group : "others",
            icon : "internet.png",
            name : key,
            resize : 1,
            simple : 0,
            height : "70%",
            width : "90%"
          };
          var descm = urlEncode(G.this_path + key);
          item = "./app/user_app?action=add&path=" + descm;
          $.ajax({
            url : item,
            type : "POST",
            dataType : "json",
            data : {
              data : urlEncode(jsonEncode(options))
            },
            success : function(res) {
              Tips.close(res);
              if (res.code) {
                ui.f5();
              }
            }
          });
        }
      });
    }
  };
  return{
    strSort : strSort,
    appEdit : init,
    appList : appList,
    appAddURL : appAddURL,
    share : next,
    shareBox : runTest,
    setBackground : login,
    createLink : ajax,
    createProject : submit,
    newFile : handleInput,
    newFolder : get,
    rname : request,
    unZip : create,
    zipDownload : send,
    zip : doRequest,
    copy : post,
    cute : success,
    info : info,
    remove : remove,
    cuteDrag : cuteDrag,
    copyDrag : callback,
    past : onDeviceReady,
    clipboard : clipboard,
    fav : remove,
    favRemove : dialog
  };
});
