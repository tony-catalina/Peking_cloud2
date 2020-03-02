define("app/common/core.api", [], function() {
  var init = function(opts, state, w) {
    var $e = $(w.DOM.wrap);
    var options = state.frames.OpenpathSelectApi;
    var imageFieldHTML = '<input type="text" class="path_select_input" readonly="true" disabled="true" />';
    if ("file" == opts.type) {
      imageFieldHTML += '<span class="label label-primary">' + opts.allowExt + "</span>";
    }
    $(imageFieldHTML).insertBefore($e.find(".aui_state_highlight"));
    var parse = function(e) {
      var elems = opts.allowExt.split("|");
      var index = core.pathExt(e);
      return "" == opts.allowExt || "" != opts.allowExt && -1 != $.inArray(index, elems) ? true : false;
    };
    var next = function() {
      var fields = options.ui.fileLight.fileListSelect();
      var data = [];
      if (opts.single) {
        var target = $(fields.get(0));
        if ("all" == opts.type && 0 == fields.length) {
          data = {
            file : [],
            folder : []
          };
        } else {
          if ("file" == opts.type && 0 == fields.length) {
            data = [];
          } else {
            if ("folder" == opts.type) {
              data = [options.G.this_path];
              if (target.hasClass("folderBox")) {
                data = [options.ui.fileLight.path(target)];
              }
            } else {
              if ("file" == opts.type) {
                if (target.hasClass("fileBox")) {
                  var targets = options.ui.fileLight.path(target);
                  if (parse(targets)) {
                    data = [targets];
                  }
                }
              } else {
                if ("all" == opts.type) {
                  if (target.hasClass("folderBox")) {
                    targets = options.ui.fileLight.path(target);
                    data = [{
                      file : [],
                      folder : [targets]
                    }];
                  } else {
                    if (target.hasClass("fileBox")) {
                      targets = options.ui.fileLight.path(target);
                      if (parse(targets)) {
                        data = {
                          file : [targets],
                          folder : []
                        };
                      }
                    }
                  }
                }
              }
            }
          }
        }
      } else {
        var tmp = [];
        var transformed = [];
        fields.each(function() {
          if ($(this).hasClass("fileBox")) {
            var val = options.ui.fileLight.path($(this));
            if (parse(val)) {
              tmp.push(val);
            }
          } else {
            if ($(this).hasClass("folderBox")) {
              transformed.push(options.ui.fileLight.path($(this)));
            }
          }
        });
        if ("folder" == opts.type) {
          data = transformed;
        } else {
          if ("file" == opts.type) {
            data = tmp;
          } else {
            if ("all" == opts.type) {
              data = {
                file : tmp,
                folder : transformed
              };
            }
          }
        }
      }
      init(data);
    };
    var filter = function(expr) {
      expr = trim(expr, "/");
      return expr == G.AWD_GROUP_ROOT_SELF || (expr == G.AWD_GROUP_ROOT_ALL || (expr == G.AWD_USER_FAV || expr == G.AWD_USER_SHARE)) ? false : true;
    };
    var init = function(data) {
      var results = $e.find(".path_select_input");
      var _info = $e.find(".aui_state_highlight");
      if ("all" != opts.type) {
        var tmp = [];
        var i = 0;
        for (;data.length > i;i++) {
          if (filter(data[i])) {
            tmp.push(data[i]);
          }
        }
        data = tmp;
      }
      if (0 == data.length || "all" == opts.type && (0 == data.file.length && 0 == data.folder.length)) {
        _info.addClass("disable");
        results.attr("result", "");
        results.val("");
      } else {
        var value = hashEncode(jsonEncode(data));
        var result = "";
        if (opts.single) {
          result = options.core.pathThis(data[0]);
        } else {
          var nodes = data;
          if ("all" == opts.type) {
            nodes = data.folder.concat(data.file);
          }
          $.each(nodes, function(dataAndEvents, deepDataAndEvents) {
            result += options.core.pathThis(deepDataAndEvents) + ", ";
          });
        }
        _info.removeClass("disable");
        results.attr("result", value);
        results.val(result);
      }
    };
    var select = function() {
      options.ui.fileLight.select.hook("select", options.ui.fileLight, {
        before : function() {
        },
        after : function() {
          next();
        }
      });
    };
    if (!options.awdReady) {
      options.awdReady = [];
    }
    options.awdReady.push(function() {
      select();
      next();
    });
  };
  return{
    pathSelect : function(opts, fn) {
      var defaults = {
        type : "file",
        title : "请选择文件...",
        single : true,
        allowExt : "",
        firstPath : false
      };
      var url = "./explorer?type=iframe";
      opts = $.extend(defaults, opts);
      if (opts.firstPath) {
        url += "&path=" + opts.firstPath;
      }
      var doc = ShareData.frameTop();
      var w = doc.$.dialog.open(url, {
        id : "pathSelectApi",
        resize : true,
        fixed : true,
        ico : core.icon("folder"),
        title : opts.title,
        lock : true,
        background : "#000",
        opacity : 0.1,
        width : 840,
        height : 420,
        ok : function() {
          if ("function" == typeof fn) {
            var wrap = w.DOM.wrap;
            var context = wrap.find(".path_select_input").attr("result");
            context = jsonDecode(hashDecode(context));
            if (context) {
              if (opts.single && "all" != opts.type) {
                fn(context[0]);
              } else {
                fn(context);
              }
            } else {
              Tips.tips("操作失败", false);
            }
          }
        },
        cancel : true
      });
      init(opts, doc, w);
    },
    randomImage : function(dataAndEvents) {
    	dataAndEvents();
    }

  };
});
