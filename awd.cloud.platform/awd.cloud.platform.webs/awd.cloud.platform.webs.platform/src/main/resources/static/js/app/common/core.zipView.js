define("app/common/core.zipView", [], function() {
  return function(completeEvent) {
    var group;
    var options = {
      view : {
        showLine : false,
        selectedMulti : false,
        expandSpeed : "fast",
        dblClickExpand : false,
        /**
         * @param {?} dataAndEvents
         * @param {Object} self
         * @return {undefined}
         */
        addDiyDom : function(dataAndEvents, self) {
          /** @type {number} */
          var index = 15;
          var next = $("#" + self.tId + "_switch");
          var $this = $("#" + self.tId + "_ico");
          next.remove();
          self.iconSkin = self.tree_icon;
          var result = self.tree_icon;
          if (self.ext ? result = self.ext : self.tree_icon || (result = self.type), $this.before(next).before('<span id="' + self.tId + '_my_ico"  class="tree_icon button">' + core.iconSmall(result) + "</span>").remove(), undefined != self.ext && $this.attr("class", "").addClass("file " + self.ext).removeAttr("style"), self.level >= 1) {
            /** @type {string} */
            var error = "<span class='space' style='display: inline-block;width:" + index * self.level + "px'></span>";
            next.before(error);
          }
          /** @type {string} */
          var grape = '<span class="time">' + date("Y\/m\/d H:i:s", self.mtime) + "</span>";
          grape += '<span class="size">' + core.fileSize(self.size) + "</span>";
          grape += '<span class="menu_more icon-ellipsis-vertical"></span>';
          $("#" + self.tId + "_span").after(grape);
          next.parent().addClass(self.menuType);
        }
      },
      callback : {
        /**
         * @param {Event} e
         * @param {?} event
         * @param {Object} node
         * @return {undefined}
         */
        onClick : function(e, event, node) {
          if (!$(e.target).hasClass("menu_more")) {
            group.selectNode(node);
            set(node);
            if ("folder" == node.type) {
              $("#" + node.tId + "_switch").click();
            }
          }
        },
        /**
         * @param {?} node
         * @param {?} id
         * @return {undefined}
         */
        onCollapse : function(node, id) {
          show(id);
        },
        /**
         * @param {?} e
         * @param {?} id
         * @return {undefined}
         */
        onExpand : function(e, id) {
          show(id);
        },
        /**
         * @param {?} dataAndEvents
         * @param {Object} b
         * @return {undefined}
         */
        beforeRightClick : function(dataAndEvents, b) {
          if (b) {
            set(b);
            group.selectNode(b);
          }
        },
        /**
         * @param {Event} ev
         * @param {?} item
         * @param {Event} event
         * @return {undefined}
         */
        onDblClick : function(ev, item, event) {
          if (!$(ev.target).hasClass(".menu_more")) {
            if ("file" == event.type) {
              hide("open", group);
            }
          }
        }
      }
    };
    /**
     * @param {Array} items
     * @return {?}
     */
    var init = function(items) {
      /**
       * @param {Array} files
       * @return {undefined}
       */
      var init = function(files) {
        /** @type {number} */
        var i = 0;
        for (;files.length > i;i++) {
          if (undefined != files[i]) {
            var data = files[i];
            files[i] = {
              name : core.pathThis(data.filename),
              path : data.filename,
              isParent : !!data.child,
              type : data.folder ? "folder" : "file",
              menuType : data.folder ? "menuZipListFolder" : "menuZipListFile",
              ext : core.pathExt(data.filename),
              mtime : data.mtime,
              index : data.index,
              size : data.size,
              child : data.child
            };
            if (data.folder) {
              delete files[i].ext;
            }
            if (files[i].child) {
              files[i].children = files[i].child;
              delete files[i].child;
              init(files[i].children);
            } else {
              delete files[i].child;
            }
          } else {
            delete files[i];
          }
        }
      };
      var result = {};
      /** @type {number} */
      var index = 0;
      for (;items.length > index;index++) {
        if ("string" != typeof items[index].filename) {
          if (items[index].stored_filename) {
            items[index].filename = items[index].stored_filename;
          }
        }
        if ("string" == typeof items[index].filename) {
          items[index].filename = items[index].filename.replace(/\\/g, "/");
          result[items[index].filename] = items[index];
        }
      }
      var key;
      for (key in result) {
        if (!result[key].folder) {
          var alias = core.pathFather(result[key].filename);
          for (;"" != alias && ("/" != alias && (!result[alias] && !result[rtrim(alias, "/")]));) {
            result[alias] = {
              filename : alias,
              folder : true,
              mitme : 0,
              size : 0,
              index : -1
            };
            alias = core.pathFather(alias);
          }
        }
      }
      /** @type {Array} */
      var dir = [];
      for (key in result) {
        var data = result[key];
        var i = core.pathFather(data.filename);
        if (result[i] && (i = core.pathFather(data.filename)), result[rtrim(i, "/")] && (i = rtrim(i, "/")), result[i]) {
          if (!result[i].child) {
            /** @type {Array} */
            result[i].child = [];
          }
          result[i].child.push(result[data.filename]);
        } else {
          var id = result[data.filename];
          if (id) {
            dir.push(id);
          }
        }
      }
      return init(dir), dir;
    };
    /**
     * @return {undefined}
     */
    var run = function() {
      $.contextMenu({
        selector : ".menuZipListFolder",
        className : "menuZipListFolder",
        zIndex : 9999,
        /**
         * @param {?} _
         * @return {undefined}
         */
        callback : function(_) {
          hide(_);
        },
        items : {
          unzip_this : {
            name : "解压到当前",
            className : "unzip_this",
            icon : "external-link"
          },
          unzip_to : {
            name : "解压到...",
            className : "unzip_to",
            icon : "external-link"
          },
          sep1 : "--------",
          info : {
            name : "属性",
            className : "info",
            icon : "info",
            accesskey : "i"
          }
        }
      });
      $.contextMenu({
        selector : ".menuZipListFile",
        className : "menuZipListFile",
        zIndex : 9999,
        /**
         * @param {?} _
         * @return {undefined}
         */
        callback : function(_) {
          hide(_);
        },
        items : {
          open : {
            name : "打开",
            className : "open",
            icon : "external-link",
            accesskey : "o"
          },
          down : {
            name : "下载",
            className : "down",
            icon : "cloud-download",
            accesskey : "x"
          },
          sep1 : "--------",
          unzip_this : {
            name : "解压到当前",
            className : "unzip_this",
            icon : "external-link"
          },
          unzip_to : {
            name : "解压到...",
            className : "unzip_to",
            icon : "external-link"
          },
          sep2 : "--------",
          info : {
            name : "属性",
            className : "info",
            icon : "info",
            accesskey : "i"
          }
        }
      });
      $(".menuZipListFile .menu_more,.menuZipListFolder .menu_more").die("click").live("click", function(touches) {
        var from = $(this).offset();
        from.top += $(this).outerHeight();
        $(this).contextMenu({
          x : touches.pageX,
          y : from.top
        });
      });
    };
    /**
     * @param {?} direction
     * @param {number} e
     * @return {undefined}
     */
    var hide = function(direction, e) {
      if (undefined == e) {
        var $slide = $(".context-menu-active").parents(".ztree");
        if (0 == $slide.length) {
          return;
        }
        e = $.fn.zTree.getZTreeObj($slide.attr("id"));
      }
      var res = e.getSelectedNodes()[0];
      switch(direction) {
        case "open":
          callback(e, res);
          break;
        case "down":
          blur(e, res);
          break;
        case "unzip_this":
          next(e, res);
          break;
        case "unzip_to":
          error(e, res);
          break;
        case "info":
          fn(e, res);
          break;
        default:
        ;
      }
    };
    var obj = {
      file_num : 0,
      folder_num : 0,
      size : 0
    };
    /**
     * @param {Object} frame
     * @return {undefined}
     */
    var render = function(frame) {
      if ("folder" == frame.type) {
        if (obj.folder_num++, frame.children) {
          /** @type {number} */
          var id = 0;
          for (;frame.children.length > id;id++) {
            render(frame.children[id]);
          }
        }
      } else {
        obj.file_num++;
        obj.size += parseInt(frame.size);
      }
    };
    /**
     * @param {number} e
     * @param {Object} o
     * @return {undefined}
     */
    var blur = function(e, o) {
      e.setting.filePath;
      var objectName = e.setting.fileUrl;
      /** @type {string} */
      var r20 = objectName + "&download=1&index=" + o.index;
      ui.pathOpen.downloadUrl(r20);
    };
    /**
     * @param {number} e
     * @param {Object} o
     * @return {undefined}
     */
    var callback = function(e, o) {
      e.setting.filePath;
      var objectName = e.setting.fileUrl;
      var editLocationWin = objectName + "&index=" + o.index + "&name=/" + urlEncode(o.path);
      var filename = o.ext;
      if ("zip" == filename) {
        /** @type {string} */
        filename = "unknow";
      }
      if (o.size >= 31457280) {
        Tips.tips("该文件太大，请解压后再进行预览操作！", "warning");
        /** @type {string} */
        filename = "unknow";
      }
      ui.pathOpen.open(editLocationWin, filename);
    };
    /**
     * @param {number} e
     * @param {Object} res
     * @return {undefined}
     */
    var error = function(e, res) {
      core.api.pathSelect({
        type : "folder",
        title : "解压到..."
      }, function(name) {
        next(e, res, name);
      });
    };
    /**
     * @param {number} e
     * @param {Object} d
     * @param {number} name
     * @return {undefined}
     */
    var next = function(e, d, name) {
      var path = e.setting.filePath;
      e.setting.fileUrl;
      if (undefined == name) {
        name = G.this_path;
        if (undefined == name) {
          name = core.pathFather(path);
        }
      }
      /** @type {string} */
      var appFrontendUrl = "./explorer/unzip";
      $.ajax({
        url : appFrontendUrl,
        data : {
          path : path,
          path_to : name,
          unzip_part : d.index
        },
        type : "POST",
        dataType : "json",
        /**
         * @return {undefined}
         */
        beforeSend : function() {
          Tips.loading("正在解压...");
        },
        error : core.ajaxError,
        /**
         * @param {?} res
         * @return {?}
         */
        success : function(res) {
          return Tips.close(res), "editor" == Config.pageApp ? (ui.tree.refreshPath(core.pathFather(path)), undefined) : (ui.f5(true, true, function() {
            var nameEQ = name + core.pathThis(d.path);
            ui.path.setSelectByFilename(nameEQ);
          }), undefined);
        }
      });
    };
    /**
     * @param {Object} obj
     * @return {undefined}
     */
    var set = function(obj) {
      var font = update(obj);
      /** @type {string} */
      var escaped = "大小" + " " + font.size_friendly + " (" + font.size + " Byte)";
      if ("folder" == obj.type) {
        /** @type {string} */
        escaped = font.file_num + "文件" + "," + font.folder_num + "文件夹" + ", " + escaped;
      }
      $("#" + obj.tId).parents(".zipViewContent").find(".bottom .info").html(escaped);
    };
    /**
     * @param {Object} data
     * @return {?}
     */
    var update = function(data) {
      var params = {
        name : data.name,
        path : data.path,
        size : data.size,
        size_friendly : core.fileSize(data.size),
        mtime : date("Y\/m\/d H:i:s", data.mtime)
      };
      return 0 == data.level && (params.path = params.name), "folder" == data.type && (obj = {
        file_num : 0,
        folder_num : 0,
        size : 0
      }, render(data), $.extend(params, obj), params.size_friendly = core.fileSize(params.size)), params;
    };
    /**
     * @param {number} arg
     * @param {Object} data
     * @return {undefined}
     */
    var fn = function(arg, data) {
      var deep = "folder" == data.type ? "folder" : core.pathExt(data.path);
      var regex = "folder" == data.type ? tpl_path_info : tpl_file_info;
      var jQuery = template.compile(regex);
      var html = update(data);
      html.LNG = LNG;
      $.dialog({
        padding : 5,
        ico : core.iconSmall(deep),
        fixed : true,
        title : core.pathThis(data.path),
        content : jQuery(html),
        ok : true
      });
    };
    /**
     * @param {?} id
     * @param {string} options
     * @param {string} source
     * @return {undefined}
     */
    var destroy = function(id, options, source) {
      var keys = template.compile(tpl_zipview);
      var properties = keys({
        LNG : LNG,
        treeID : id
      });
      var F = $.dialog({
        className : "zipViewDialog",
        id : "zip_view_" + md5(source),
        ico : core.icon("zip"),
        title : options,
        width : 550,
        height : 420,
        content : properties,
        resize : true,
        padding : 0,
        fixed : true
      });
      /** @type {number} */
      var ud = 15 * $(".zipViewContent").length;
      F.DOM.wrap.css({
        left : "+=" + ud + "px",
        top : "+=" + ud + "px"
      });
    };
    /**
     * @param {?} id
     * @return {undefined}
     */
    var show = function(id) {
      $("#" + id).find("ul:visible > li > a").each(function(dataAndEvents) {
        $(this).removeClass("odd");
        if (0 == dataAndEvents % 2) {
          $(this).addClass("odd");
        }
      });
    };
    /**
     * @param {string} opts
     * @param {Array} list
     * @param {string} arg
     * @return {undefined}
     */
    var done = function(opts, list, arg) {
      var data = init(list);
      var id = "folderListZip" + UUID();
      data = ui.tree.treeDataSort(data);
      destroy(id, opts, arg);
      run();
      data = {
        name : opts,
        ext : "zip",
        mtime : "",
        isParent : true,
        open : true,
        children : data,
        type : "folder",
        path : "",
        index : "-1",
        menuType : "menuZipListFolder"
      };
      $.fn.zTree.init($("#" + id), options, data);
      group = $.fn.zTree.getZTreeObj(id);
      show(id);
      set(group.getNodeByParam("index", "-1", null));
    };
    /**
     * @param {string} e
     * @return {?}
     */
    var handleInput = function(e) {
      var target = $(".zip_view_" + md5(e));
      if (target.length > 0) {
        return target.shake(3, 20, 80), undefined;
      }
      var location = "explorer/unzipList&access_token=" + G.access_token + "&path=" + urlEncode(e);
      return G.share_page !== undefined ? (location = "share/unzipList&user=" + G.user + "&sid=" + G.sid + "&path=" + urlEncode(e), ui.pathOpen.openUnknow(e), undefined) : (location = G.app_host + "" + location, $.ajax({
        url : location,
        dataType : "json",
        /**
         * @return {undefined}
         */
        beforeSend : function() {
          Tips.loading("操作中...");
        },
        error : core.ajaxError,
        /**
         * @param {Object} res
         * @return {undefined}
         */
        success : function(res) {
          Tips.close(res);
          if (res.code) {
            done(core.pathThis(e), res.data, e);
            /** @type {string} */
            group.setting.filePath = e;
            group.setting.fileUrl = location;
          } else {
            ui.pathOpen.openUnknow(e, res.data);
          }
        }
      }), undefined);
    };
    handleInput(completeEvent);
  };
});
