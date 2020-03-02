define("app/common/core.search", [], function() {
  return function(data, path) {
    var t;
    var location;
    var search = function() {
      var value = trim(core.pathClear(path), "/");
      if (0 == value.indexOf(G.AWD_USER_SHARE) 
    		  && -1 == value.indexOf("/") 
    		  || (value == G.AWD_USER_FAV 
    				  || value == G.AWD_GROUP_ROOT_ALL)) {
    	  Tips.tips("该目录不支持搜索！", false);
    	  return;
      }
      template.helper("searchResultPrase", format);
      var failureCallback = template.compile(tpl_search_box);
      if (0 == $(".dialog_do_search").length) {
        t = $.dialog({
          id : "dialog_do_search",
          padding : 0,
          fixed : true,
          ico : core.icon("search"),
          resize : true,
          title : "搜索",
          width : 440,
          height : 480,
          content : failureCallback({
            LNG : LNG
          })
        });
        location = send();
        location.path = path;
        if ("" != data) {
          location.search = data;
        }
        $("#search_path").val(location.path);
        $("#search_value").val(location.search);
        init();
      } else {
        $.dialog.list.dialog_do_search.display(true);
        if (data) {
          $("#search_value").val(data);
        }
        $("#search_path").val(path);
        callback();
      }
    };
    var show = function() {
      return location = {
        search : $("#search_value").val(),
        path : $("#search_path").val(),
        is_content : Number($("#search_is_content").is(":checked")),
        is_case : Number($("#search_is_case").is(":checked")),
        ext : $("#search_ext").val()
      };
    };
    var callback = function() {
      show();
      load(location);
    };
    var format = function(v) {
      var c = htmlEncode($("#search_value").val());
      if (v = htmlEncode(v), location.is_case) {
        v = v.replace(c, '<span class="keyword">' + c + "</span>");
      } else {
        var t = v.toLowerCase().indexOf(c.toLowerCase());
        v = v.substr(0, t) + '<span class="keyword">' + v.substr(t, c.length) + "</span>" + v.substr(t + c.length);
      }
      return v;
    };
    var init = function() {
      $("#search_value").die("keyup").live("keyup", function() {
        if ("editor" == !Config.pageApp) {
          ui.path.setSearchByStr($(this).val());
        }
      });
      $("#search_value,#search_ext,#search_path").keyEnter(callback);
      $(".search_header .btn").die("click").live("click", callback);
      $(".search_result .file-item .file-info").die("click").live("click", function(deepDataAndEvents) {
        var $dropdown = $(this).parent();
        $dropdown.toggleClass("open");
        $dropdown.find(".result-item").slideToggle(200);
        stopPP(deepDataAndEvents);
        return  false;
      });
      $(".search_result .file-item .file-info .goto").die("click").live("click", function(deepDataAndEvents) {
        var image = $(this).parent().parent();
        var deep = pathHashDecode(image.attr("data-path"));
        var newNode = core.pathFather(deep);
        core.openPath(newNode);
        setTimeout(function() {
          if (Config) {
            if ("explorer" == Config.pageApp) {
              ui.path.setSelectByFilename(deep);
            }
          }
        }, 200);
        stopPP(deepDataAndEvents);
        return false;
      });
      $(".search_result .file-item .file-info .title").die("click").live("click", function(deepDataAndEvents) {
        var image = $(this).parent().parent();
        var editLocationWin = pathHashDecode(image.attr("data-path"));
        ui.pathOpen.open(editLocationWin, image.attr("data-ext"));
        stopPP(deepDataAndEvents);
        return false;
      });
      $(".search_result .file-item .result-info").die("click").live("click", function(deepDataAndEvents) {
        var image = $(this).parent().parent();
        var r20 = pathHashDecode(image.attr("data-path"));
        $(".search_result .file-item .result-info.this").removeClass("this");
        $(this).addClass("this");
        var fLine = parseInt($(this).find(".line").attr("data-line"));
        ShareData.data("FILE_SEARCH_AT", {
            search : $("#search_value").val(),
            line : fLine,
            lineIndex : $(this).parent().find("[data-line=" + fLine + "]").index($(this).find(".line"))
          });
        ui.pathOpen.openEditorForce(r20, image.attr("data-ext"));
        stopPP(deepDataAndEvents);
        return false;
      });
      $(".search_header input[type=checkbox]").on("click", function() {
        show();
        send(location);
      });
    };
    var send = function(data) {
      var filePath = "box_search_config";
      if (undefined == data) {
        data = LocalData.getConfig(filePath);
        return data || (data = {
          search : "",
          is_content : 0,
          is_case : 0,
          ext : ""
        }), $("#search_value").val(data.search).textSelect(), data.is_content ? $("#search_is_content").attr("checked", "checked") : $("#search_is_content").removeAttr("checked"), data.is_case ? $("#search_is_case").attr("checked", "checked") : $("#search_is_case").removeAttr("checked"), $("#search_ext").val(data.ext), data;
      }
      return LocalData.setConfig(filePath, data);
    };
    var parse = function(response) {
      var results = $(".file-items");
      var def = $(".search_desc");
      if (!response.code) {
        return def.html(response.data), results.html(""), undefined;
      }
      if (0 == response.data.filelist.length && 0 == response.data.folderlist.length) {
        return def.html("没有搜索结果!"), results.html(""), undefined;
      }
      var cb = template.compile(tpl_search_list);
      if (results.html(cb({
        code : response.code,
        data : response.data,
        LNG : LNG
      })), location.is_content) {
        var codeSegments = response.data.filelist;
        var prevChunksLen = 0;
        var i = 0;
        for (;codeSegments.length > i;i++) {
          if (codeSegments[i].search_info) {
            prevChunksLen += codeSegments[i].search_info.length;
          }
        }
        def.html("搜索结果" + ": <b>" + prevChunksLen + "(in " + codeSegments.length + " files)</b>");
        if (response.data.error_info) {
          def.html("<span>" + "搜索结 果太多，建议换一个目录或词语" + "</span>");
        }
      } else {
        def.html(response.data.filelist.length + " " + "文件" + ", " + response.data.folderlist.length + "文件夹" + ".");
      }
    };
    var load = function(message) {
      send(message);
      $("#search_value").textFocus();
      var $title = $(".file-items");
      var errors = $(".search_desc");
      if (!message.search || !message.path) {
        return errors.html("请输入搜索词和路径进行搜索！"), $title.html(""), undefined;
      }
      var appFrontendUrl = "explorer/search";
      if (G.share_page !== undefined) {
        appFrontendUrl = "share/search?user=" + G.user + "&sid=" + G.sid;
      }
      $.ajax({
        url : appFrontendUrl,
        dataType : "json",
        type : "POST",
        data : message,
        beforeSend : function() {
          errors.hide().html("搜索中..." + '<img src="' + G.static_path + 'images/common/loading.gif">').fadeIn(100);
        },
        error : function() {
          core.ajaxError();
          errors.html("操作失败");
        },
        success : function(response) {
          parse(response);
        }
      });
    };
    search();
  };
});
