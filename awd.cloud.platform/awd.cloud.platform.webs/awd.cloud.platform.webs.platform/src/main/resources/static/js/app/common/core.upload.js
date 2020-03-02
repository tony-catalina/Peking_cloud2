define("app/common/core.upload", [], function(dataAndEvents, deepDataAndEvents) {
  var upload = function() {
    var explorerfileUpload = "/explorer/fileUpload";
    return "share" == G.share_page && ("1" == G.share_info.can_upload && (explorerfileUpload = G.app_host + "share/fileUpload?user=" + G.user + "&sid=" + G.sid)), explorerfileUpload;
  };
  return{
    serverDwonload : function(params, dataAndEvents) {
      core.uploadCheck("explorer:serverDownload");
      var $page = $(".download_box");
      var rule = $page.find(".download_list");
      if ($page.find("input").val(""), !params) {
        return Tips.tips("url false!", false), undefined;
      }
      if ("ftp" != params.substr(0, 3)) {
        if ("http" != params.substr(0, 4)) {
          params = "http://" + params;
        }
      }
      var id = UUID();
      var item = '<div id="' + id + '" class="item">' + '<div class="info"><span class="title" tytle="' + params + '">' + core.pathThis(params) + "</span>" + '<span class="size">0b</span>' + '<span class="state">' + "等待上传" + "</span>" + '<a class="remove font-icon icon-remove" href="javascript:void(0)"></a>' + '<div style="clear:both"></div></div></div>';
      if (rule.find(".item").length > 0) {
        $(item).insertBefore(rule.find(".item:eq(0)"));
      } else {
        rule.append(item);
      }
      var scrollIntervalId;
      var tref;
      var b;
      var text = 0;
      var $$ = $("#" + id);
      var self = $("#" + id + " .state").text("即将下载");
      var output = $('<div class="progress progress-striped active"><div class="progress-bar" role="progressbar" style="width: 0%;text-align:right;"></div></div>').appendTo("#" + id).find(".progress-bar");
      $("#" + id + " .remove").bind("click", function() {
        clearInterval(scrollIntervalId);
        scrollIntervalId = false;
        clearTimeout(tref);
        tref = false;
        $.get("./explorer/serverDownload&type=remove&uuid=" + id);
        $(this).parent().parent().slideUp(function() {
          $(this).remove();
          ui.f5();
        });
      });
      var going;
      var trigger = function(extra) {
        clearTimeout(going);
        going = false;
        going = setTimeout(function() {
          ui.f5Callback(function() {
            ui.path.setSelectByFilename(extra);
          });
        }, 600);
      };
      /**
       * @return {undefined}
       */
      var poll = function() {
        $.ajax({
          url : "./explorer/serverDownload&type=download&save_path=" + dataAndEvents + "&url=" + urlEncode(params) + "&uuid=" + id + "&time=" + time(),
          dataType : "json",
          error : function(jqXHR, textStatus, errorThrown) {
            var selector = $$.data("progcess");
            return 200 != jqXHR.status && (selector && selector.support_range) ? (setTimeout(function() {
              poll();
            }, 1E3), undefined) : (core.ajaxError(jqXHR, textStatus, errorThrown), 200 == jqXHR.status && (clearInterval(scrollIntervalId), scrollIntervalId = false, clearTimeout(tref), tref = false, output.parent().remove(), self.addClass("error").text("下载失败！")), undefined);
          },
          success : function(data) {
            return 0 == data.code && "downloading" == data.data ? (setTimeout(function() {
              poll();
            }, 1E3), undefined) : (data.code ? (trigger(data.info), self.text("下载成功！"), $("#" + id + " .info .title").text(core.pathThis(data.info)), $("#" + id + " .info .title").attr("title", data.info), self.parent().parent().addClass("success")) : (self.addClass("error").text(data.data), self.parent().parent().addClass("error")), clearInterval(scrollIntervalId), scrollIntervalId = false, clearTimeout(tref), tref = false, output.parent().remove(), undefined);
          }
        });
      };
      poll();
      /**
       * @return {undefined}
       */
      var fetchData = function() {
        $.ajax({
          url : "./explorer/serverDownload&type=percent&uuid=" + id,
          dataType : "json",
          success : function(res) {
            var i = "";
            var data = res.data;
            if (scrollIntervalId) {
              if (!res.code) {
                return self.text("操作中..."), undefined;
              }
              if (data) {
                if (data.size = parseFloat(data.size), data.time = parseFloat(data.time), b) {
                  var a = data.size - b.size;
                  var file = a / (data.time - b.time);
                  if (text > 0.2 * file) {
                    var raw = text;
                    text = file;
                    file = raw;
                  } else {
                    text = file;
                  }
                  var dir = core.fileSize(file);
                  dir = dir ? dir : 0;
                  i = dir + "/s";
                }
                if ($$.data("progcess", data), 0 == data.length) {
                  $$.find(".progress-bar").css("width", "100%");
                  self.text(i);
                  $$.find(".size").text(core.fileSize(data.size));
                } else {
                  var ratio = 100 * (data.size / data.length);
                  $$.find(".progress-bar").css("width", ratio + "%");
                  self.text(ratio.toFixed(1) + "%(" + i + ")");
                  $$.find(".size").text(core.fileSize(data.length));
                }
                $$.find(".title").text(data.name);
                b = data;
              }
            }
          }
        });
      };
      tref = setTimeout(function() {
        fetchData();
        scrollIntervalId = setInterval(function() {
          fetchData();
        }, 1E3);
      }, 100);
    },
    /**
     * @return {?}
     */
    upload : function() {
      var optLabel = upload();
      if (uploader.option("server", optLabel), uploader.option("method", "POST"), 0 != $(".dialog_file_upload").length) {
        return $.dialog.list.dialog_file_upload.display(true), undefined;
      }
      var createIframe = template.compile(tpl_upload);
      var size = WebUploader.Base.formatSize(G.upload_max);
      $.dialog({
        padding : 5,
        resize : true,
        ico : core.icon("upload"),
        id : "dialog_file_upload",
        fixed : true,
        title : "多文件上传",
        content : createIframe({
          LNG : LNG,
          maxsize : size
        }),
        /**
         * @return {undefined}
         */
        close : function() {
          $.each(uploader.getFiles(), function(dataAndEvents, file) {
            uploader.skipFile(file);
            uploader.removeFile(file);
          });
          $.each($(".download_list .item"), function() {
            $(this).find(".remove").click();
          });
        }
      });
      $(".file_upload .top_nav a.menu").unbind("click").bind("click", function() {
        if ($(this).hasClass("tab_upload")) {
          $(".file_upload .tab_upload").addClass("this");
          $(".file_upload .tab_download").removeClass("this");
          $(".file_upload .upload_box").removeClass("hidden");
          $(".file_upload .download_box").addClass("hidden");
        } else {
          $(".file_upload .tab_upload").removeClass("this");
          $(".file_upload .tab_download").addClass("this");
          $(".file_upload .upload_box").addClass("hidden");
          $(".file_upload .download_box").removeClass("hidden");
        }
      });
      $(".download_box [name=url]").keyEnter(function() {
        core.serverDwonload($(".download_box input").val(), G.this_path);
      });
      $(".file_upload .download_box .download_start").unbind("click").bind("click", function() {
        core.serverDwonload($(".download_box input").val(), G.this_path);
      });
      $(".file_upload .download_box .download_start_all").unbind("click").bind("click", function() {
        $.dialog({
          id : "server_dwonload_textarea",
          fixed : true,
          resize : false,
          ico : core.icon("upload"),
          width : "420px",
          height : "270px",
          padding : 10,
          title : "下载",
          content : "<textarea style='width:410px;height:260px;'></textarea>",
          ok : function() {
            var codeSegments = $(".server_dwonload_textarea textarea").val().split("\n");
            var i = 0;
            for (;codeSegments.length > i;i++) {
              core.serverDwonload(codeSegments[i], G.this_path);
            }
          }
        });
      });
      uploader.addButton({
        id : "#picker"
      });
      uploader.addButton({
        id : "#picker_folder"
      });
      var init = function() {
        if (isWap()) {
          return false;
        }
        var file_input = document.createElement("input");
        return file_input.type = "file", file_input.webkitdirectory !== undefined || file_input.directory !== undefined;
      };
      if (init()) {
        $(".upload_cert_box").removeClass("hidden");
        $(".file_upload .drag_upload_folder").unbind("click").bind("click", function() {
          $("#picker_folder input").attr("webkitdirectory", "").attr("directory", "");
          $("#picker_folder label").click();
        });
      }
    },
    init : function() {
      var chunkSize = G.upload_max;
      WebUploader.Uploader.register({
        "before-send" : "checkChunk"
      }, {
        checkChunk : function(info) {
          if (!canvasSupport()) {
            return $.Deferred().resolve(), undefined;
          }
          var owner = this.owner;
          var imageDfd = (info.blob.getSource(), $.Deferred());
          return owner.md5File(info.blob).fail(function() {
            imageDfd.resolve();
          }).then(function(hide) {
            if (1 == info.chunks) {
              return imageDfd.resolve(), undefined;
            }
            if (0 == info.chunk) {
              $.ajax({
                url : upload(),
                dataType : "json",
                data : {
                  upload_to : info.file.upload_to,
                  file_name : info.file.name,
                  check_md5 : hide,
                  chunk : info.chunk,
                  chunks : info.chunks
                },
                error : function() {
                  imageDfd.resolve();
                },
                success : function(resp) {
                  if (resp.code) {
                    imageDfd.reject();
                    info.file.checkChunk = resp.info;
                  } else {
                    imageDfd.resolve();
                  }
                }
              });
            } else {
              var prop = info.file.checkChunk;
              if (prop && prop["part_" + info.chunk] == hide) {
                var parent = info.end / info.total;
                uploader.trigger("uploadProgress", info.file, parent);
                imageDfd.reject();
              } else {
                imageDfd.resolve();
              }
            }
          }), imageDfd.promise();
        }
      });
      uploader = WebUploader.create({
        swf : G.static_path + "js/lib/webuploader/Uploader.swf",
        dnd : "body",
        threads : 5,
        compress : false,
        resize : false,
        prepareNextFile : true,
        duplicate : true,
        chunkRetry : 10,
        chunked : true,
        chunkSize : chunkSize
      });
      $(".uploader-content .success").die("click").live("click", function() {
        var userCfg = $(this).find("span.title").attr("data-name");
        if (userCfg) {
          if ("explorer" == Config.pageApp) {
            ui.path.list(core.pathFather(userCfg), "tips", function() {
              ui.path.setSelectByFilename(userCfg);
            });
          } else {
            core.explorer(core.pathFather(userCfg));
          }
        }
      });
      $(".uploader-content .open").die("click").live("click", function(deepDataAndEvents) {
        var type = $(this).parent().find("span.title").attr("data-name");
        ui.pathOpen.open(type);
        stopPP(deepDataAndEvents);
      });
      $(".upload_box_clear").die("click").live("click", function() {
        $(".uploader-list .item.success,.uploader-list .item.error").each(function() {
          $(this).slideUp(300, function() {
            $(this).remove();
          });
        });
      });
      $(".upload_box_clear_all").die("click").live("click", function() {
        $.each(uploader.getFiles(), function(dataAndEvents, file) {
          uploader.skipFile(file);
          uploader.removeFile(file);
        });
        $(".uploader-list .item").each(function() {
          $(this).remove();
        });
      });
      $(".uploader-content .remove").die("click").live("click", function(deepDataAndEvents) {
        var button = $(this).parent().parent().attr("id");
        $(this).parent().parent().slideUp(function() {
          $(this).remove();
        });
        uploader.skipFile(button);
        uploader.removeFile(button, true);
        stopPP(deepDataAndEvents);
      });
      var to = 0;
      var open = 0;
      var h = "0B/s";
      var preTime = 0;
      var render = function(player, dt) {
        if (0.3 >= timeFloat() - preTime) {
          return h;
        }
        preTime = timeFloat();
        var offset = player.size * dt;
        var num = 5;
        if (player.speed === undefined) {
          player.speed = [[timeFloat() - 0.5, 0], [timeFloat(), offset]];
        } else {
          if (num >= player.speed.length) {
            player.speed.push([timeFloat(), offset]);
          } else {
            player.speed = player.speed.slice(1, num);
            player.speed.push([timeFloat(), offset]);
          }
        }
        var a = player.speed[player.speed.length - 1];
        var b = player.speed[0];
        var e = (a[1] - b[1]) / (a[0] - b[0]);
        if (0 >= e) {
          e = 0;
        }
        var i = core.fileSize(e);
        return i = i ? i : 0, e = i + "/s", h = e, e;
      };
      var r = [];
      var tref;
      var cleanup = function(recurring) {
        clearTimeout(tref);
        tref = false;
        tref = setTimeout(function() {
          var R = r;
          ui.f5Callback(function() {
            if (ui.path.setSelectByFilename(R), recurring && (r = [], "explorer" == Config.pageApp)) {
              if ("share" == G.share_page) {
                return;
              }
              ui.tree.checkIfChange(G.this_path);
            }
          });
        }, 600);
      };
      uploader.on("fileQueued", function(data) {
        if (!core.uploadCheck()) {
          return uploader.skipFile(data), uploader.removeFile(data), undefined;
        }
        var fullPath;
        try {
          fullPath = data.source.source.fullPath;
          if (undefined != data.source.source.webkitRelativePath) {
            if ("" != data.source.source.webkitRelativePath) {
              fullPath = data.source.source.webkitRelativePath;
            }
          }
        } catch (t) {
        }
        if (data.fullPath = fullPath, data.source && (data.source.source && (1 == data.source.source.isDirectory && data.source.source.fullPath))) {
          return ui.pathOperate.newFolder(G.this_path + data.fullPath), uploader.skipFile(data), uploader.removeFile(data), undefined;
        }
        var n = data.fullPath;
        data.finished = false;
        data.upload_to = G.this_path;
        if (undefined == n || "undefined" == n) {
          n = data.name;
        }
        to++;
        var $head = $(".uploader-list");
        var thSelect = '<div id="' + data.id + '" class="item"><div class="info">' + '<span class="title" title="' + htmlEncode(data.upload_to + n) + '" data-name="' + htmlEncode(data.upload_to + n) + '">' + htmlEncode(core.pathThis(n)) + "</span>" + '<span class="size">' + core.fileSize(data.size) + "</span>" + '<span class="state">' + "等待上传" + "</span>" + '<a class="remove font-icon icon-remove" href="javascript:void(0)"></a>' + '<div style="clear:both"></div></div></div>';
        if (1E3 == to || 2E3 == to) {
          Tips.tips("文件过多,建议压缩后上传,然后在线解压！", "warning");
        }
        var init = function() {
          if (0 == data.size && n) {
            ui.pathOperate.newFile(data.upload_to + n);
            uploader.skipFile(data);
            uploader.removeFile(data);
            open++;
            to++;
            var message = $("#" + data.id);
            message.addClass("success").find(".state").text("上传成功").parent().find(".remove").addClass("icon-ok").addClass("open").removeClass("icon-remove").removeClass("remove");
          }
        };
        var handler = function() {
          uploader.upload();
          setTimeout(function() {
            init();
          }, 200);
        };
        if (0 == $head.length) {
          setTimeout(function() {
            $(".uploader-list").prepend(thSelect);
            handler();
          }, 200);
        } else {
          $head.prepend(thSelect);
          handler();
        }
      }).on("uploadBeforeSend", function(currentDocument, result, dataAndEvents) {
        var to = urlEncode(currentDocument.file.fullPath);
        if (undefined == to || "undefined" == to) {
          to = "";
        }
        result.fullPath = to;
        result.upload_to = currentDocument.file.upload_to;
        dataAndEvents["X-CSRF-TOKEN"] = Cookie.get("X-CSRF-TOKEN");
      }).on("uploadProgress", function(item, dt) {
        $(".dialog_file_upload .aui_title").text("上传中" + ": " + open + "/" + to + " (" + h + ")");
        var result = render(item, dt);
        var $this = $("#" + item.id);
        var $slide = $this.find(".progress .progress-bar");
        if (!$slide.length) {
          $slide = $('<div class="progress progress-striped active"><div class="progress-bar" role="progressbar" style="width: 0%"></div></div>').appendTo($this).find(".progress-bar");
        }
        $this.find(".state").text((100 * dt).toFixed(1) + "%(" + result + ")");
        $slide.css("width", 100 * dt + "%");
      }).on("uploadAccept", function(currentDocument, error) {
        if (currentDocument.file.serverData = error, !error.code) {
          return currentDocument.serverNeedRetry = true, false;
        }
        try {
          if (!currentDocument.file.fullPath) {
            r.push(error.info);
          }
        } catch (t) {
        }
      }).on("uploadSuccess", function(file) {
        var message = $("#" + file.id);
        if (!message.inScreen()) {
          var oldScrollTop = 36 * message.index(".item");
          $(".uploader-content").scrollTop(oldScrollTop);
        }
        open++;
        var info = file.serverData;
        if (info && info.data) {
          var newValue = LNG[info.data];
          if (info.code) {
            if (message.addClass("success"), message.find(".state").text(newValue), message.find(".remove").addClass("icon-ok").addClass("open").removeClass("icon-remove").removeClass("remove"), info.info) {
              var d = "/" + ltrim(htmlEncode(info.info), "/");
              message.find(".info .title").html(core.pathThis(d)).attr("title", d).attr("data-name", d);
            }
          } else {
            message.addClass("error").find(".state").addClass("error");
            message.find(".state").text(newValue).attr("title", newValue);
          }
        }
        uploader.removeFile(file);
        message.find(".progress").fadeOut();
        if (!file.fullPath) {
          cleanup(false);
        }
      }).on("uploadError", function(item, dataAndEvents) {
        var name = "上传失败" + "(" + dataAndEvents + ")";
        if (item.serverData) {
          var start = 5;
          if ((-1 !== item.serverData._raw.indexOf("[Error Code:1001]") || (-1 !== item.serverData._raw.indexOf("[Error Code:1002]") || -1 !== item.serverData._raw.indexOf("[Error Code:1010]"))) && (item.errorNum || (item.errorNum = 0), item.errorNum++, start >= item.errorNum)) {
            return uploader.retry(item), undefined;
          }
          if (-1 !== item.serverData._raw.indexOf("\x3c!--user login--\x3e")) {
            return $.each(uploader.getFiles(), function(dataAndEvents, file) {
              uploader.skipFile(file);
              uploader.removeFile(file);
            }), Tips.tips("login error!", false), undefined;
          }
          if (item.serverData.data) {
            var param = item.serverData.data;
            name = LNG[param] ? LNG[param] : param;
          } else {
            if (item.serverData._raw) {
              name = item.serverData._raw;
            }
          }
        }
        if ("http" == dataAndEvents) {
          name = "网络或防火墙错误";
        }
        if ("abort" == dataAndEvents) {
          if (undefined == item.serverData) {
            name = "暂不支持！" + "(support on chrome)";
          }
        }
        open++;
        $("#" + item.id).find(".progress").fadeOut();
        $("#" + item.id).addClass("error").find(".state").addClass("error");
        $("#" + item.id).find(".state").html(name).attr("title", name);
      }).on("uploadFinished", function() {
        $(".dialog_file_upload .aui_title").text("上传成功" + ": " + open + "/" + to);
        to = 0;
        open = 0;
        uploader.reset();
        cleanup(true);
      }).on("error", function(deepDataAndEvents) {
        Tips.tips(deepDataAndEvents, false);
      });
      var timeoutTimer;
      inState = false;
      dragOver = function() {
        if (0 == inState) {
          if (inState = true, !core.uploadCheck(undefined, false)) {
            return;
          }
          var r20 = '<div class="upload-tips">\t\t\t\t\t\t<div>\t\t\t\t\t\t\t<i class="icon-cloud cloud1 moveLeftLoop"></i>\t\t\t\t\t\t\t<i class="icon-cloud cloud2"></i>\t\t\t\t\t\t\t<i class="icon-cloud cloud3 moveLeftLoop"></i>\t\t\t\t\t\t</div>\t\t\t\t\t\t<div class="cloud-moveup"><i class="moveTopLoop icon-circle-arrow-up"></i></div>\t\t\t\t\t\t<div class="msg">' + "松开即可上传!" + "</div>\t\t\t\t\t</div>";
          MaskView.tips(r20);
          $("#windowMaskView").css({
            background : "#4285f4",
            opacity : "0.8"
          });
        }
        if (timeoutTimer) {
          window.clearTimeout(timeoutTimer);
        }
      };
      dragLeave = function(e) {
        stopPP(e);
        if (timeoutTimer) {
          window.clearTimeout(timeoutTimer);
        }
        timeoutTimer = window.setTimeout(function() {
          inState = false;
          MaskView.close();
        }, 100);
      };
      dragDrop = function(e) {
        try {
          if (e = e.originalEvent || e, core.uploadCheck()) {
            if (e.dataTransfer.files.length > 0 && e.dataTransfer.files[0].name) {
              core.upload();
              core.playSound("drag_upload");
            } else {
              var _fmt = e.dataTransfer.getData("text/plain");
              if (_fmt) {
                if ("http" == _fmt.substring(0, 4)) {
                  ui.pathOperate.appAddURL(_fmt);
                }
              }
            }
          }
          stopPP(e);
        } catch (e) {
        }
        if (inState) {
          inState = false;
          MaskView.close();
        }
      };
    }
  };
});
