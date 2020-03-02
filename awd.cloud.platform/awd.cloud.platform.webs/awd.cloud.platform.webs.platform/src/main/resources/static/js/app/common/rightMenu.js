define("app/common/rightMenu", [], function(result) {
  /** @type {string} */
  var selector = ".menufile";
  /** @type {string} */
  var query = ".menufolder";
  /** @type {string} */
  var sel = ".menuMore";
  /** @type {string} */
  var definition = ".menuTreeRoot";
  /** @type {string} */
  var expr = ".menuTreeFolder";
  /** @type {string} */
  var s = ".menuTreeFile";
  /** @type {string} */
  var tag = ".menuTreeGroupRoot";
  /** @type {string} */
  var view = ".menuTreeGroup";
  /** @type {string} */
  var key = ".menuTreeUser";
  var search = {
    newfileOther : {
      name : "新建文件",
      icon : "expand-alt",
      accesskey : "w",
      className : "newfile",
      items : {
        newfile : {
          name : "txt " + "文件",
          icon : "file-text-alt x-item-file x-txt small",
          className : "newfile"
        },
//        newfile_null : {
//          name : "文件",
//          icon : "file-text-alt x-item-file x-file small",
//          className : "newfile"
//        },
//        newfile_md : {
//          name : "md " + "文件",
//          icon : "file-text-alt x-item-file x-md",
//          className : "newfile"
//        },
//        newfile_html : {
//          name : "html " + "文件",
//          icon : "file-text-alt x-item-file x-html",
//          className : "newfile"
//        },
//        newfile_php : {
//          name : "php " + "文件",
//          icon : "file-text-alt x-item-file x-php",
//          className : "newfile"
//        },
        document : {
          name : "Office Document",
          icon : "file-text-alt x-item-file x-docx",
          className : "newfile",
          items : {
            newfile_docx : {
              name : "docx " + "文件",
              icon : "file-text-alt x-item-file x-docx",
              className : "newfile"
            },
            newfile_xlsx : {
              name : "xlsx " + "文件",
              icon : "file-text-alt x-item-file x-xlsx",
              className : "newfile"
            },
            newfile_pptx : {
              name : "pptx " + "文件",
              icon : "file-text-alt x-item-file x-pptx",
              className : "newfile"
            }
          }
        }
//        sep100 : "--------",
//        app_install : {
//          name : "应用商店",
//          className : "app_install newfile",
//          icon : "tasks x-item-file x-appStore",
//          accesskey : "a"
//        },
//        app_create : {
//          name : "创建应用",
//          icon : "puzzle-piece x-item-file x-oexe",
//          className : "newfile"
//        }
      }
    },
    listIcon : {
      name : "查看",
      icon : "eye-open",
      className : "list_icon",
      items : {
        seticon : {
          name : "图标排列",
          className : "menu_seticon set_seticon"
        },
        setlist : {
          name : "列表排列",
          className : "menu_seticon set_setlist"
        },
        setlist_split : {
          name : "分栏模式",
          className : "menu_seticon set_setlist_split"
        }
      }
    },
    sortBy : {
      name : "排序方式",
      accesskey : "y",
      icon : "sort",
      className : "sort_by",
      items : {
        set_sort_name : {
          name : "名称",
          className : "menu_set_sort set_sort_name"
        },
        set_sort_ext : {
          name : "类型",
          className : "menu_set_sort set_sort_ext"
        },
        set_sort_size : {
          name : "大小",
          className : "menu_set_sort set_sort_size"
        },
        set_sort_mtime : {
          name : "修改时间",
          className : "menu_set_sort set_sort_mtime"
        },
        sep101 : "--------",
        set_sort_up : {
          name : "递增",
          className : "menu_set_desc set_sort_up"
        },
        set_sort_down : {
          name : "递减",
          className : "menu_set_desc set_sort_down"
        }
      }
    },
    setFileIconSize : {
      name : "图标大小",
      icon : "picture",
      className : "set-file-icon-size",
      items : {
        "box-size-smallx" : {
          name : "超小",
          className : "file-icon-size box-size-smallx"
        },
        "box-size-small" : {
          name : "小图标",
          className : "file-icon-size box-size-small"
        },
        "box-size-default" : {
          name : "中图标",
          className : "file-icon-size box-size-default"
        },
        "box-size-big" : {
          name : "大图标",
          className : "file-icon-size box-size-big"
        },
        "box-size-bigx" : {
          name : "超大图标",
          className : "file-icon-size box-size-bigx"
        }
      }
    }
  };
  /**
   * @return {undefined}
   */
  var f = function() {
    $('<div id="rightMenu" class="hidden"></div>').appendTo("body");
    $(".context-menu-list").die("click").live("click", function(deepDataAndEvents) {
      return stopPP(deepDataAndEvents), false;
    });
    callback();
    done();
    write();
    start();
    error();
    test();
    parse();
    refresh();
    get();
    compile();
    init();
    func();
    setup();
    filter;
    add();
    render();
    cb();
    $(".set_set" + G.user_config.list_type).addClass("selected");
    $(".set_sort_" + G.user_config.list_sort_field).addClass("selected");
    $(".set_sort_" + G.user_config.list_sort_order).addClass("selected");
    $(".context-menu-root").addClass("animated fadeIn");
  };
  /**
   * @return {undefined}
   */
  var ready = function() {
    $('<div id="rightMenu" class="hidden"></div>').appendTo("body");
    $(".context-menu-list").die("click").live("click", function(deepDataAndEvents) {
      return stopPP(deepDataAndEvents), false;
    });
    Keyboard();
    create();
    callback();
    done();
    start();
    func();
    setup();
    cb();
    $(".set_sort_" + G.user_config.list_sort_field).addClass("selected");
    $(".set_sort_" + G.user_config.list_sort_order).addClass("selected");
    $(".context-menu-root").addClass("animated fadeIn");
  };
  /**
   * @return {undefined}
   */
  var update = function() {
    $('<div id="rightMenu" class="hidden"></div>').appendTo("body");
    $(".context-menu-list").die("click").live("click", function(deepDataAndEvents) {
      return stopPP(deepDataAndEvents), false;
    });
    error();
    test();
    parse();
    show();
    refresh();
    get();
    compile();
    open();
    func();
    cb();
    $(".context-menu-root").addClass("animated fadeIn");
  };
  /**
   * @return {undefined}
   */
  var cb = function() {
    if (1 != G.is_root) {
      /** @type {string} */
      var hiddenClass = "hidden";
      if (!AUTH["explorer:fileDownload"]) {
        $(".context-menu-list .down,.context-menu-list .download").addClass(hiddenClass);
        $(".context-menu-list .share").addClass(hiddenClass);
        $(".context-menu-list .open_text").addClass(hiddenClass);
        $(".pathinfo .open_window").addClass(hiddenClass);
      }
      if (!AUTH["explorer:zip"]) {
        $(".context-menu-list .zip").addClass(hiddenClass);
      }
      if (!AUTH["explorer:search"]) {
        $(".context-menu-list .search").addClass(hiddenClass);
      }
      if (!AUTH["explorer:mkdir"]) {
        $(".context-menu-list .newfolder").addClass(hiddenClass);
      }
      if (!AUTH["userShare:set"]) {
        $(".context-menu-list .share").remove();
      }
    }
  };
  /**
   * @return {undefined}
   */
  var setup = function() {
    $('<i class="menuRecycleBody"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuRecycleBody",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        initialize(callbacks);
      },
      items : {
        refresh : {
          name : "刷新" + "<b>F5</b>",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        recycle_clear : {
          name : "清空回收站",
          icon : "trash",
          accesskey : "c"
        },
        sep1 : "--------",
        listIcon : search.listIcon,
        sortBy : search.sortBy,
        setFileIconSize : search.setFileIconSize,
        sep2 : "--------",
        info : {
          name : "属性" + "<b>Alt+I</b>",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
    $('<i class="menuRecyclePath"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuRecyclePath",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        cute : {
          name : "剪切" + "<b>Ctrl+X</b>",
          className : "cute",
          icon : "cut",
          accesskey : "k"
        },
        remove : {
          name : "彻底删除" + "<b>Del</b>",
          className : "remove",
          icon : "trash",
          accesskey : "d"
        },
        sep2 : "--------",
        down : {
          name : "下载",
          className : "down",
          icon : "cloud-download",
          accesskey : "x"
        },
        info : {
          name : "属性" + "<b>Alt+I</b>",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
    $('<i class="menuRecycleButton"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuRecycleButton",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        initialize(callbacks);
      },
      items : {
        recycle_clear : {
          name : "清空回收站",
          icon : "trash",
          accesskey : "c"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var filter = function() {
    $('<i class="menuShareBody"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuShareBody",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        initialize(callbacks);
      },
      items : {
        refresh : {
          name : "刷新" + "<b>F5</b>",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        sep1 : "--------",
        listIcon : search.listIcon,
        sortBy : search.sortBy,
        setFileIconSize : search.setFileIconSize,
        sep10 : "--------",
        info : {
          name : "属性" + "<b>Alt+I</b>",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
    $('<i class="menuSharePath"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      className : "menuSharePathMenu",
      selector : ".menuSharePath",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        share_open_path : {
          name : "进入所在目录",
          icon : "folder-open-alt",
          accesskey : "p",
          className : "open_the_path"
        },
        share_open_window : {
          name : "打开共享页面",
          icon : "globe",
          accesskey : "b"
        },
        sep0 : "--------",
        share_edit : {
          name : "编辑共享",
          icon : "edit",
          accesskey : "e",
          className : "share_edit"
        },
        remove : {
          name : "取消共享" + "<b>Del</b>",
          icon : "trash",
          accesskey : "d",
          className : "remove"
        },
        copy : {
          name : "复制" + "<b>Ctrl+C</b>",
          className : "copy",
          icon : "copy",
          accesskey : "c"
        },
        down : {
          name : "下载",
          className : "down",
          icon : "cloud-download",
          accesskey : "x"
        },
        sep2 : "--------",
        info : {
          name : "属性" + "<b>Alt+I</b>",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
    $('<i class="menuSharePathMore"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuSharePathMore",
      className : "menuSharePathMore",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        remove : {
          name : "取消共享" + "<b>Del</b>",
          icon : "trash",
          accesskey : "d",
          className : "remove"
        },
        copy : {
          name : "复制" + "<b>Ctrl+C</b>",
          className : "copy",
          icon : "copy",
          accesskey : "c"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var write = function() {
    $.contextMenu({
      selector : ".menuBodyMain",
      className : "fileContiner_menu",
      zIndex : 9999,
      /**
       * @param {?} callbacks
       * @param {?} pass
       * @return {undefined}
       */
      callback : function(callbacks, pass) {
        initialize(callbacks, pass);
      },
      items : {
        refresh : {
          name : "刷新" + "<b>F5</b>",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        newfolder : {
          name : "新建文件夹" + "<b>Alt+M</b>",
          className : "newfolder",
          icon : "folder-close-alt",
          accesskey : "n"
        },
        newfileOther : search.newfileOther,
        sep1 : "--------",
        upload : {
          name : "上传" + "<b>Ctrl+U</b>",
          className : "upload",
          icon : "upload",
          accesskey : "u"
        },
//        past : {
//          name : "粘贴" + "<b>Ctrl+V</b>",
//          className : "past",
//          icon : "paste",
//          accesskey : "p"
//        },
//        copy_see : {
//          name : "查看剪贴板",
//          className : "copy_see",
//          icon : "eye-open",
//          accesskey : "v"
//        },
        sep2 : "--------",
        listIcon : search.listIcon,
        sortBy : search.sortBy,
        setFileIconSize : search.setFileIconSize,
        sep10 : "--------",
        info : {
          name : "属性" + "<b>Alt+I</b>",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var func = function() {
    $.contextMenu({
      selector : ".menuEmpty",
      className : "hidden",
      zIndex : 9999,
      items : {
        " " : {
          name : "打开",
          className : "hidden"
        }
      },
      /**
       * @return {undefined}
       */
      callback : function() {
      }
    });
  };
  /**
   * @return {undefined}
   */
  var create = function() {
    $.contextMenu({
      selector : ".menuDefault",
      zIndex : 9999,
      items : {
        open : {
          name : "打开",
          className : "open",
          icon : "external-link",
          accesskey : "o"
        }
      },
      /**
       * @param {?} id
       * @return {undefined}
       */
      callback : function(id) {
        switch(id) {
          case "open":
            ui.path.open();
            break;
          default:
          ;
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var Keyboard = function() {
    $.contextMenu({
      selector : Config.BodyContent,
      zIndex : 9999,
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        initialize(callbacks);
      },
      items : {
        refresh : {
          name : "刷新" + "<b>F5</b>",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        sep1 : "--------",
        sortBy : search.sortBy,
        setFileIconSize : search.setFileIconSize,
        app_install : {
          name : "应用商店",
          className : "app_install",
          icon : "tasks",
          accesskey : "a"
        },
        sep2 : "--------",
        setting_wall : {
          name : "更换壁纸",
          className : "setting_wall",
          icon : "desktop",
          accesskey : "b"
        },
        setting_theme : {
          name : "主题设置",
          className : "setting_theme",
          icon : "dashboard",
          accesskey : "i"
        },
        setting : {
          name : "系统设置",
          className : "setting",
          icon : "cogs",
          accesskey : "t"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var init = function() {
    $.contextMenu({
      zIndex : 9999,
      selector : ".toolPathMore",
      className : "menuToolPath menuNotAutoHidden",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
        $(".toolPathMore").removeClass("active");
      },
      items : {
        refresh : {
          name : "刷新" + "<b>F5</b>",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        newfileOther : search.newfileOther,
        sep0 : "--------",
        open : {
          name : "打开" + "<b>Enter</b>",
          className : "open",
          icon : "folder-open-alt",
          accesskey : "o"
        },
        down : {
          name : "下载",
          className : "down",
          icon : "cloud-download",
          accesskey : "x"
        },
//        share : {
//          name : "共享",
//          className : "share",
//          icon : "share-sign",
//          accesskey : "e"
//        },
        sep1 : "--------",
//        copy : {
//          name : "复制" + "<b>Ctrl+C</b>",
//          className : "copy",
//          icon : "copy",
//          accesskey : "c"
//        },
//        cute : {
//          name : "剪切" + "<b>Ctrl+X</b>",
//          className : "cute",
//          icon : "cut",
//          accesskey : "k"
//        },
//        past : {
//          name : "粘贴" + "<b>Ctrl+V</b>",
//          className : "past",
//          icon : "paste",
//          accesskey : "p"
//        },
        rname : {
          name : "重命名" + "<b>F2</b>",
          className : "rname",
          icon : "pencil",
          accesskey : "r"
        },
        remove : {
          name : "删除" + "<b>Delete</b>",
          className : "remove",
          icon : "trash",
          accesskey : "d"
        },
//        sep2 : "--------",
//        zip : {
//          name : "压缩到...",
//          className : "zip",
//          icon : "external-link",
//          accesskey : "z",
//          items : {
//            zip_zip : {
//              name : "ZIP " + "文件",
//              className : "zip_zip",
//              icon : "external-link"
//            },
//            sep109 : "--------",
//            zip_tar : {
//              name : "TAR " + "文件",
//              className : "zip_tar",
//              icon : "external-link"
//            },
//            zip_tgz : {
//              name : "GZIP " + "文件",
//              className : "zip_tgz",
//              icon : "external-link"
//            }
//          }
//        },
//        others : {
//          name : "更多",
//          icon : "ellipsis-horizontal",
//          className : "more_action",
//          accesskey : "m",
//          items : {
//            explorer : {
//              name : "管理目录",
//              className : "explorer",
//              icon : "laptop",
//              accesskey : "v"
//            },
//            clone : {
//              name : "创建副本",
//              className : "clone",
//              icon : "external-link"
//            },
//            fav : {
//              name : "添加到收藏夹",
//              className : "fav ",
//              icon : "star",
//              accesskey : "f"
//            },
//            open_ie : {
//              name : "浏览器打开",
//              className : "open_ie",
//              icon : "globe",
//              accesskey : "b"
//            },
//            sep103 : "--------",
//            createLinkHome : {
//              name : "发送到桌面快捷方式",
//              className : "createLinkHome",
//              icon : "location-arrow",
//              accesskey : "l"
//            },
//            createLink : {
//              name : "创建快捷方式",
//              className : "createLink",
//              icon : "share-alt"
//            },
//            createProject : {
//              name : "添加到编辑器工程",
//              className : "createProject",
//              icon : "plus"
//            },
//            openProject : {
//              name : "编辑器打开项目",
//              className : "openProject",
//              icon : "edit"
//            }
//          }
//        },
        sep5 : "--------",
        info : {
          name : "属性" + "<b>Alt+I</b>",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var callback = function() {
    $('<i class="' + query.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : query,
      className : query.substr(1),
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        open : {
          name : "打开" + "<b>Enter</b>",
          className : "open",
          icon : "folder-open-alt",
          accesskey : "o"
        },
        down : {
          name : "下载",
          className : "down",
          icon : "cloud-download",
          accesskey : "x"
        },
//        share : {
//          name : "共享",
//          className : "share",
//          icon : "share-sign",
//          accesskey : "e"
//        },
//        sep1 : "--------",
//        copy : {
//          name : "复制" + "<b>Ctrl+C</b>",
//          className : "copy",
//          icon : "copy",
//          accesskey : "c"
//        },
//        cute : {
//          name : "剪切" + "<b>Ctrl+X</b>",
//          className : "cute",
//          icon : "cut",
//          accesskey : "k"
//        },
        rname : {
          name : "重命名" + "<b>F2</b>",
          className : "rname",
          icon : "pencil",
          accesskey : "r"
        },
        remove : {
          name : "删除" + "<b>Del</b>",
          className : "remove",
          icon : "trash",
          accesskey : "d"
        },
        sep2 : "--------",
//        open_ie : {
//          name : "浏览器打开",
//          className : "open_ie",
//          icon : "globe",
//          accesskey : "b"
//        },
//        zip : {
//          name : "压缩到...",
//          className : "zip",
//          icon : "external-link",
//          accesskey : "z",
//          items : {
//            zip_zip : {
//              name : "ZIP " + "文件",
//              className : "zip_zip",
//              icon : "external-link"
//            },
//            sep109 : "--------",
//            zip_tar : {
//              name : "TAR " + "文件",
//              className : "zip_tar",
//              icon : "external-link",
//              accesskey : "f"
//            },
//            zip_tgz : {
//              name : "GZIP " + "文件",
//              className : "zip_tgz",
//              icon : "external-link",
//              accesskey : "f"
//            }
//          }
//        },
        search : {
          name : "文件夹中搜索",
          className : "search",
          icon : "search",
          accesskey : "s"
        },
//        others : {
//          name : "更多",
//          icon : "ellipsis-horizontal",
//          className : "more_action",
//          accesskey : "m",
//          items : {
//            explorer : {
//              name : "管理目录",
//              className : "explorer",
//              icon : "laptop",
//              accesskey : "v"
//            },
//            clone : {
//              name : "创建副本",
//              className : "clone",
//              icon : "external-link"
//            },
//            fav : {
//              name : "添加到收藏夹",
//              className : "fav ",
//              icon : "star",
//              accesskey : "f"
//            },
//            sep103 : "--------",
//            createLinkHome : {
//              name : "发送到桌面快捷方式",
//              className : "createLinkHome",
//              icon : "location-arrow",
//              accesskey : "l"
//            },
//            createLink : {
//              name : "创建快捷方式",
//              className : "createLink",
//              icon : "share-alt"
//            },
//            createProject : {
//              name : "添加到编辑器工程",
//              className : "createProject",
//              icon : "plus"
//            },
//            openProject : {
//              name : "编辑器打开项目",
//              className : "openProject",
//              icon : "edit"
//            }
//          }
//        },
        sep5 : "--------",
        info : {
          name : "属性" + "<b>Alt+I</b>",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var done = function() {
    $('<i class="' + selector.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : selector,
      className : selector.substr(1),
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        open : {
          name : "打开" + "<b>Enter</b>",
          className : "open",
          icon : "external-link",
          accesskey : "o"
        },
//        sep1 : "--------",
//        open_ie : {
//          name : "浏览器打开",
//          className : "open_ie",
//          icon : "globe",
//          accesskey : "b"
//        },
        sep3 : "--------",
        info : {
          name : "属性" + "<b>Alt+I</b>",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var start = function() {
    $('<i class="' + sel.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : sel,
      className : sel.substr(1),
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        copy : {
          name : "复制" + "<b>Ctrl+C</b>",
          className : "copy",
          icon : "copy",
          accesskey : "c"
        },
        cute : {
          name : "剪切" + "<b>Ctrl+X</b>",
          className : "cute",
          icon : "cut",
          accesskey : "k"
        },
        remove : {
          name : "删除" + "<b>Del</b>",
          className : "remove",
          icon : "trash",
          accesskey : "d"
        },
        sep1 : "--------",
        copy_to : {
          name : "复制到...",
          className : "copy_to",
          icon : "copy"
        },
        cute_to : {
          name : "移动到...",
          className : "cute_to",
          icon : "cut"
        },
        sep2 : "--------",
        clone : {
          name : "创建副本" + "<b>Ctrl+C</b>",
          className : "clone",
          icon : "external-link",
          accesskey : "n"
        },
        playmedia : {
          name : "添加到播放列表",
          className : "playmedia",
          icon : "music",
          accesskey : "p"
        },
        zip : {
          name : "压缩到...",
          className : "zip",
          icon : "external-link",
          accesskey : "z",
          items : {
            zip_zip : {
              name : "ZIP " + "文件",
              className : "zip_zip",
              icon : "external-link"
            },
            sep109 : "--------",
            zip_tar : {
              name : "TAR " + "文件",
              className : "zip_tar",
              icon : "external-link",
              accesskey : "f"
            },
            zip_tgz : {
              name : "GZIP " + "文件",
              className : "zip_tgz",
              icon : "external-link",
              accesskey : "f"
            }
          }
        },
        down : {
          name : "下载",
          className : "down",
          icon : "cloud-download",
          accesskey : "x"
        },
        sep3 : "--------",
        info : {
          name : "属性",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var render = function() {
    $('<i class="menuGroupRoot"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuGroupRoot",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        open : {
          name : "打开" + "<b>Enter</b>",
          className : "open",
          icon : "external-link",
          accesskey : "o"
        },
        sep1 : "--------",
        fav : {
          name : "添加到收藏夹",
          className : "fav",
          icon : "star",
          accesskey : "f"
        },
        createLinkHome : {
          name : "发送到桌面快捷方式",
          className : "createLinkHome",
          icon : "location-arrow",
          accesskey : "l"
        }
      }
    });
    $('<i class="menuGroupRootMore"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuGroupRootMore",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        refresh : {
          name : "刷新" + "<b>F5</b>",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var add = function() {
    $('<i class="menuFavPath"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuFavPath",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        open : {
          name : "打开" + "<b>Enter</b>",
          className : "open",
          icon : "external-link",
          accesskey : "o"
        },
        sep0 : "--------",
        fav_remove : {
          name : "取消收藏",
          className : "fav_remove",
          icon : "trash",
          accesskey : "r"
        },
        fav_page : {
          name : "管理收藏夹",
          className : "fav_page",
          icon : "star",
          accesskey : "f"
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
    $('<i class="menuFavPathMore"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuFavPathMore",
      className : "menuFavPathMore",
      /**
       * @param {?} callbacks
       * @return {undefined}
       */
      callback : function(callbacks) {
        runCallbacks(callbacks);
      },
      items : {
        fav_remove : {
          name : "取消收藏",
          className : "fav_remove",
          icon : "trash",
          accesskey : "r"
        }
      }
    });
  };
  /**
   * @param {?} callbacks
   * @return {undefined}
   */
  var initialize = function(callbacks) {
    switch(callbacks) {
      case "refresh":
        ui.f5(true, true);
        break;
      case "back":
        ui.path.history.back();
        break;
      case "next":
        ui.path.history.next();
        break;
      case "seticon":
        ui.setListType("icon");
        break;
      case "setlist":
        ui.setListType("list");
        break;
      case "setlist_split":
        ui.setListType("list_split");
        break;
      case "set_sort_name":
        ui.setListSort("name", 0);
        break;
      case "set_sort_ext":
        ui.setListSort("ext", 0);
        break;
      case "set_sort_size":
        ui.setListSort("size", 0);
        break;
      case "set_sort_mtime":
        ui.setListSort("mtime", 0);
        break;
      case "set_sort_up":
        ui.setListSort(0, "up");
        break;
      case "set_sort_down":
        ui.setListSort(0, "down");
        break;
      case "upload":
        core.upload();
        break;
      case "recycle_clear":
        ui.path.recycleClear();
        break;
      case "box-size-smallx":
        ui.setFileIconSize(40);
        break;
      case "box-size-small":
        ui.setFileIconSize(60);
        break;
      case "box-size-default":
        ui.setFileIconSize(80);
        break;
      case "box-size-big":
        ui.setFileIconSize(100);
        break;
      case "box-size-bigx":
        ui.setFileIconSize(120);
        break;
      case "past":
        ui.path.past();
        break;
      case "copy_see":
        ui.path.clipboard();
        break;
      case "newfolder":
        ui.path.newFolder();
        break;
      case "newfile":
        ui.path.newFile("txt");
        break;
      case "newfile_null":
        ui.path.newFile("");
        break;
      case "newfile_md":
        ui.path.newFile("md");
        break;
      case "newfile_html":
        ui.path.newFile("html");
        break;
      case "newfile_php":
        ui.path.newFile("php");
        break;
      case "newfile_js":
        ui.path.newFile("js");
        break;
      case "newfile_css":
        ui.path.newFile("css");
        break;
      case "newfile_oexe":
        ui.path.newFile("oexe");
        break;
      case "newfile_docx":
        ui.path.newFile("docx");
        break;
      case "newfile_xlsx":
        ui.path.newFile("xlsx");
        break;
      case "newfile_pptx":
        ui.path.newFile("pptx");
        break;
      case "info":
        ui.path.info();
        break;
      case "open":
        ui.path.open();
        break;
      case "app_install":
        ui.path.appList();
        break;
      case "app_create":
        ui.path.appEdit(true);
        break;
      case "setting":
        core.setting();
        break;
      case "setting_theme":
        core.setting("theme");
        break;
      case "setting_wall":
        core.setting("wall");
        break;
      default:
      ;
    }
  };
  /**
   * @param {?} callbacks
   * @return {undefined}
   */
  var runCallbacks = function(callbacks) {
    switch(callbacks) {
      case "open":
        ui.path.open();
        break;
      case "down":
        ui.path.download();
        break;
      case "share":
        ui.path.share();
        break;
      case "open_ie":
        ui.path.openWindow();
        break;
      case "open_text":
        ui.path.openEditor();
        break;
      case "app_edit":
        ui.path.appEdit();
        break;
      case "playmedia":
        ui.path.play();
        break;
      case "share_edit":
        ui.path.shareEdit();
        break;
      case "share_open_window":
        ui.path.shareOpenWindow();
        break;
      case "share_open_path":
        ui.path.shareOpenPath();
        break;
      case "fav":
        ui.path.fav();
        break;
      case "search":
        ui.path.search();
        break;
      case "copy":
        ui.path.copy();
        break;
      case "clone":
        ui.path.copyDrag(G.this_path, true);
        break;
      case "cute":
        ui.path.cute();
        break;
      case "cute_to":
        ui.path.cuteTo();
        break;
      case "copy_to":
        ui.path.copyTo();
        break;
      case "remove":
        ui.path.remove();
        break;
      case "rname":
        ui.path.rname();
        break;
      case "zip_zip":
        ui.path.zip();
        break;
      case "zip_tar":
        ui.path.zip("tar");
        break;
      case "zip_tgz":
        ui.path.zip("tar.gz");
        break;
      case "unzip_folder":
        ui.path.unZip();
        break;
      case "unzip_this":
        ui.path.unZip("to_this");
        break;
      case "unzip_to":
        ui.path.unZip("unzip_to_folder");
        break;
      case "setBackground":
        ui.path.setBackground();
        break;
      case "createLinkHome":
        ui.path.createLink(false);
        break;
      case "createLink":
        ui.path.createLink(true);
        break;
      case "createProject":
        ui.path.createProject();
        break;
      case "openProject":
        ui.path.openProject();
        break;
      case "explorer":
        ui.path.explorer();
        break;
      case "explorerNew":
        ui.path.explorerNew();
        break;
      case "fav_page":
        core.setting("fav");
        break;
      case "fav_remove":
        ui.path.favRemove();
        break;
      case "info":
        ui.path.info();
        break;
      default:
        initialize(callbacks);
    }
  };
  /**
   * @return {undefined}
   */
  var error = function() {
    $('<i class="menuTreeFavRoot"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuTreeFavRoot",
      /**
       * @param {?} t
       * @return {undefined}
       */
      callback : function(t) {
        run(t);
      },
      items : {
        refresh : {
          name : "刷新",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        }
      }
    });
    $('<i class="menuTreeFav"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : ".menuTreeFav",
      /**
       * @param {?} t
       * @return {undefined}
       */
      callback : function(t) {
        run(t);
      },
      items : {
        fav_remove : {
          name : "取消收藏",
          className : "fav_remove",
          icon : "trash",
          accesskey : "r"
        },
        fav_page : {
          name : "管理收藏夹",
          className : "fav_page",
          icon : "star",
          accesskey : "f"
        },
        sep2 : "--------",
        createLinkHome : {
          name : "发送到桌面快捷方式",
          className : "createLinkHome",
          icon : "location-arrow",
          accesskey : "l"
        },
        refresh : {
          name : "刷新树目录",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        info : {
          name : "属性",
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var test = function() {
    $('<i class="' + definition.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : definition,
      /**
       * @param {?} t
       * @return {undefined}
       */
      callback : function(t) {
        run(t);
      },
      items : {
//        explorer : {
//          name : "管理目录",
//          className : "explorer",
//          icon : "laptop",
//          accesskey : "v"
//        },
        refresh : {
          name : "刷新树目录",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        sep1 : "--------",
//        past : {
//          name : "粘贴",
//          className : "past",
//          icon : "paste",
//          accesskey : "p"
//        },
        newfolder : {
          name : "新建文件夹",
          className : "newfolder",
          icon : "folder-close-alt",
          accesskey : "n"
        },
//        newfile : {
//          name : "新建文件",
//          className : "newfile",
//          icon : "file-text-alt",
//          accesskey : "j"
//        },
        sep2 : "--------",
//        fav : {
//          name : "添加到收藏夹",
//          className : "fav",
//          icon : "star",
//          accesskey : "f"
//        },
        search : {
          name : "文件夹中搜索",
          className : "search",
          icon : "search",
          accesskey : "s"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var parse = function() {
    $('<i class="menuTreeFolder"></i>').appendTo("#rightMenu");
    $('<i class="menuTreeFolderFav"></i>').appendTo("#rightMenu");
    var opts = {
      zIndex : 9999,
      selector : ".menuTreeFolder",
      /**
       * @param {?} t
       * @return {undefined}
       */
      callback : function(t) {
        run(t);
      },
      items : {
        download : {
          name : "下载",
          className : "download",
          icon : "cloud-download",
          accesskey : "x"
        },
        refresh : {
          name : "刷新树目录",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        sep1 : "--------",
//        copy : {
//          name : "复制",
//          className : "copy",
//          icon : "copy",
//          accesskey : "c"
//        },
//        cute : {
//          name : "剪切",
//          className : "cute",
//          icon : "cut",
//          accesskey : "k"
//        },
//        past : {
//          name : "粘贴",
//          className : "past",
//          icon : "paste",
//          accesskey : "p"
//        },
        rname : {
          name : "重命名",
          className : "rname",
          icon : "pencil",
          accesskey : "r"
        },
        remove : {
          name : "删除",
          className : "remove",
          icon : "trash",
          accesskey : "d"
        },
        sep2 : "--------",
        newfolder : {
          name : "新建文件夹",
          className : "newfolder",
          icon : "folder-close-alt",
          accesskey : "n"
        },
        search : {
          name : "文件夹中搜索",
          className : "search",
          icon : "search",
          accesskey : "s"
        },
//        open_ie : {
//          name : "浏览器打开",
//          className : "open_ie",
//          icon : "globe"
//        },
//        others : {
//          name : "更多",
//          icon : "ellipsis-horizontal",
//          accesskey : "m",
//          items : {
//            explorer : {
//              name : "管理目录",
//              className : "explorer",
//              icon : "laptop",
//              accesskey : "v"
//            },
//            clone : {
//              name : "创建副本",
//              className : "clone",
//              icon : "external-link",
//              accesskey : "l"
//            },
//            fav : {
//              name : "添加到收藏夹",
//              className : "fav",
//              icon : "star"
//            },
//            share : {
//              name : "共享",
//              className : "share",
//              icon : "share-sign",
//              accesskey : "e"
//            },
//            sep105 : "--------",
//            createLinkHome : {
//              name : "发送到桌面快捷方式",
//              className : "createLinkHome",
//              icon : "location-arrow",
//              accesskey : "l"
//            },
//            openProject : {
//              name : "编辑器打开项目",
//              className : "openProject",
//              icon : "edit"
//            }
//          }
//        },
        sep3 : "--------",
        info : {
          name : "属性" + '<b class="ml-20"></b>',
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    };
    $.contextMenu(opts);
    var scope = {
      fav_remove : {
        name : "取消收藏",
        className : "fav_remove",
        icon : "trash",
        accesskey : "r"
      },
      fav_page : {
        name : "管理收藏夹",
        className : "fav_page",
        icon : "star",
        accesskey : "f"
      },
      sep0 : "--------"
    };
    /** @type {string} */
    opts.selector = ".menuTreeFolderFav";
    opts.items = $.extend(scope, opts.items, true);
    $.contextMenu(opts);
  };
  /**
   * @return {undefined}
   */
  var show = function() {
    $('<i class="' + expr.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : expr,
      /**
       * @param {?} t
       * @return {undefined}
       */
      callback : function(t) {
        run(t);
      },
      items : {
        explorer : {
          name : "管理目录",
          className : "explorer",
          icon : "laptop",
          accesskey : "v"
        },
        download : {
          name : "下载",
          className : "download",
          icon : "cloud-download",
          accesskey : "x"
        },
        refresh : {
          name : "刷新树目录",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        },
        sep1 : "--------",
        copy : {
          name : "复制",
          className : "copy",
          icon : "copy",
          accesskey : "c"
        },
        cute : {
          name : "剪切",
          className : "cute",
          icon : "cut",
          accesskey : "k"
        },
        past : {
          name : "粘贴",
          className : "past",
          icon : "paste",
          accesskey : "p"
        },
        rname : {
          name : "重命名",
          className : "rname",
          icon : "pencil",
          accesskey : "r"
        },
        remove : {
          name : "删除",
          className : "remove",
          icon : "trash",
          accesskey : "d"
        },
        sep2 : "--------",
        newfolder : {
          name : "新建文件夹",
          className : "newfolder",
          icon : "folder-close-alt",
          accesskey : "n"
        },
        newfileOther : search.newfileOther,
        search : {
          name : "文件夹中搜索",
          className : "search",
          icon : "search",
          accesskey : "s"
        },
        open_ie : {
          name : "浏览器打开",
          className : "open_ie",
          icon : "globe"
        },
        others : {
          name : "更多",
          icon : "ellipsis-horizontal",
          accesskey : "m",
          className : "more_action",
          items : {
            explorer : {
              name : "管理目录",
              className : "explorer",
              icon : "laptop",
              accesskey : "v"
            },
            clone : {
              name : "创建副本",
              className : "clone",
              icon : "external-link",
              accesskey : "l"
            },
            fav : {
              name : "添加到收藏夹",
              className : "fav",
              icon : "star"
            },
            share : {
              name : "共享",
              className : "share",
              icon : "share-sign",
              accesskey : "e"
            },
            sep106 : "--------",
            createLinkHome : {
              name : "发送到桌面快捷方式",
              className : "createLinkHome",
              icon : "location-arrow",
              accesskey : "l"
            },
            openProject : {
              name : "编辑器打开项目",
              className : "openProject",
              icon : "edit"
            }
          }
        },
        sep3 : "--------",
        info : {
          name : "属性" + '<b class="ml-20">Alt+I</b>',
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var refresh = function() {
    $('<i class="' + tag.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : tag,
      /**
       * @param {?} t
       * @return {undefined}
       */
      callback : function(t) {
        run(t);
      },
      items : {
        refresh : {
          name : "刷新",
          className : "refresh",
          icon : "refresh",
          accesskey : "e"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var get = function() {
    $('<i class="' + view.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : view,
      /**
       * @param {?} t
       * @return {undefined}
       */
      callback : function(t) {
        run(t);
      },
      items : {
        fav : {
          name : "添加到收藏夹",
          className : "fav",
          icon : "star",
          accesskey : "f"
        },
        createLinkHome : {
          name : "发送到桌面快捷方式",
          className : "createLinkHome",
          icon : "location-arrow",
          accesskey : "l"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var compile = function() {
    $('<i class="' + key.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : key,
      /**
       * @param {?} callbacks
       * @param {?} self
       * @return {undefined}
       */
      callback : function(callbacks, self) {
        var current = self.$trigger;
        if (current.hasClass("file")) {
          runCallbacks(callbacks);
        } else {
          run(callbacks);
        }
      },
      items : {
        fav : {
          name : "添加到收藏夹",
          className : "fav",
          icon : "star",
          accesskey : "f"
        },
        createLinkHome : {
          name : "发送到桌面快捷方式",
          className : "createLinkHome",
          icon : "location-arrow",
          accesskey : "l"
        }
      }
    });
  };
  /**
   * @return {undefined}
   */
  var open = function() {
    $('<i class="' + s.substr(1) + '"></i>').appendTo("#rightMenu");
    $.contextMenu({
      zIndex : 9999,
      selector : s,
      /**
       * @param {?} t
       * @return {undefined}
       */
      callback : function(t) {
        run(t);
      },
      items : {
        open : {
          name : "打开",
          className : "open",
          icon : "external-link",
          accesskey : "o"
        },
        edit : {
          name : "编辑",
          className : "edit",
          icon : "edit",
          accesskey : "e"
        },
        download : {
          name : "下载",
          className : "download",
          icon : "cloud-download",
          accesskey : "x"
        },
        sep1 : "--------",
        copy : {
          name : "复制",
          className : "copy",
          icon : "copy",
          accesskey : "c"
        },
        cute : {
          name : "剪切",
          className : "cute",
          icon : "cut",
          accesskey : "k"
        },
        rname : {
          name : "重命名",
          className : "rname",
          icon : "pencil",
          accesskey : "r"
        },
        remove : {
          name : "删除",
          className : "remove",
          icon : "trash",
          accesskey : "d"
        },
        sep2 : "--------",
        open_ie : {
          name : "浏览器打开",
          className : "open_ie",
          icon : "globe"
        },
        clone : {
          name : "创建副本",
          className : "clone",
          icon : "external-link",
          accesskey : "l"
        },
        others : {
          name : "更多",
          icon : "ellipsis-horizontal",
          accesskey : "m",
          className : "more_action",
          items : {
            fav : {
              name : "添加到收藏夹",
              className : "fav",
              icon : "star"
            },
            share : {
              name : "共享",
              className : "share",
              icon : "share-sign",
              accesskey : "e"
            },
            createLinkHome : {
              name : "发送到桌面快捷方式",
              className : "createLinkHome",
              icon : "location-arrow",
              accesskey : "l"
            }
          }
        },
        sep3 : "--------",
        info : {
          name : "属性" + '<b class="ml-20">Alt+I</b>',
          className : "info",
          icon : "info",
          accesskey : "i"
        }
      }
    });
  };
  /**
   * @param {?} type
   * @return {undefined}
   */
  var run = function(type) {
    switch(type) {
      case "edit":
        ui.tree.openEditor();
        break;
      case "open":
        ui.tree.open();
        break;
      case "refresh":
        ui.tree.refresh();
        break;
      case "copy":
        ui.tree.copy();
        break;
      case "cute":
        ui.tree.cute();
        break;
      case "past":
        ui.tree.past();
        break;
      case "clone":
        ui.tree.clone();
        break;
      case "rname":
        ui.tree.rname();
        break;
      case "remove":
        ui.tree.remove();
        break;
      case "info":
        ui.tree.info();
        break;
      case "cute_to":
        ui.tree.cuteTo();
        break;
      case "copy_to":
        ui.tree.copyTo();
        break;
      case "download":
        ui.tree.download();
        break;
      case "open_ie":
        ui.tree.openWindow();
        break;
      case "search":
        ui.tree.search();
        break;
      case "share":
        ui.tree.share();
        break;
      case "search":
        ui.tree.search();
        break;
      case "newfolder":
        ui.tree.create("folder");
        break;
      case "newfile":
        ui.tree.create("txt");
        break;
      case "newfile_html":
        ui.tree.create("html");
        break;
      case "newfile_php":
        ui.tree.create("php");
        break;
      case "newfile_js":
        ui.tree.create("js");
        break;
      case "newfile_css":
        ui.tree.create("css");
        break;
      case "newfile_oexe":
        ui.tree.create("oexe");
        break;
      case "explorer":
        ui.tree.explorer();
        break;
      case "openProject":
        ui.tree.openProject();
        break;
      case "fav_page":
        core.setting("fav");
        break;
      case "fav":
        ui.tree.fav();
        break;
      case "createLinkHome":
        ui.tree.createLink(false);
        break;
      case "fav_remove":
        ui.tree.favRemove();
        break;
      case "refresh_all":
        ui.tree.init();
        break;
      case "quit":
        break;
      default:
      ;
    }
  };
  return{
    initDesktop : ready,
    initExplorer : f,
    initEditor : update,
    /**
     * @param {?} target
     * @param {number} callback
     * @param {number} y
     * @return {undefined}
     */
    show : function(target, callback, y) {
      if (target) {
        rightMenu.hidden();
        $(target).contextMenu({
          x : callback,
          y : y
        });
      }
    },
    /**
     * @return {undefined}
     */
    menuShow : function() {
      /** @type {string} */
      var activeClassName = "hidden";
      /** @type {string} */
      var lastClass = "disabled";
      var marker = $(".context-menu-list").filter(":visible");
      var $slide = $(".context-menu-active");
      if (0 != marker.length && 0 != $slide.length) {
        if (marker.find(".disable").addClass("disabled"), $slide.hasClass("menufile")) {
          var start = ui.fileLight.type(ui.fileLight.fileListSelect());
          if (inArray(core.filetype.archive, start)) {
            marker.find(".unzip").removeClass(activeClassName);
          } else {
            marker.find(".unzip").addClass(activeClassName);
          }
          if (inArray(core.filetype.image, start)) {
            marker.find(".setBackground").removeClass(activeClassName);
          } else {
            marker.find(".setBackground").addClass(activeClassName);
          }
          if ("oexe" == start) {
            marker.find(".app_edit").removeClass(activeClassName);
          } else {
            marker.find(".app_edit").addClass(activeClassName);
          }
          if (inArray(core.filetype.image, start) || (inArray(core.filetype.music, start) || (inArray(core.filetype.movie, start) || inArray(core.filetype.bindary, start)))) {
            marker.find(".open_text").addClass(activeClassName);
          } else {
            marker.find(".open_text").removeClass(activeClassName);
          }
        }
        if ($slide.hasClass("menufolder") || ($slide.hasClass("menufile") || ($slide.hasClass("menuTreeFolder") || ($slide.hasClass("menuTreeFile") || $slide.hasClass("menuTreeFolderFav"))))) {
          /** @type {string} */
          var ctx = ".cute,.rname,.remove,.zip";
          /** @type {string} */
          var active = ".open,.open_text,.down,.share,.copy,.cute,.rname,.remove,.open_ie,.zip,.unzip_this,.unzip_folder,.search,.more_action";
          if ($slide.hasClass("file_not_readable")) {
            marker.find(active).addClass(lastClass);
          } else {
            marker.find(active).removeClass(lastClass);
          }
          if ($slide.hasClass("file_not_writeable")) {
            marker.find(ctx).addClass(lastClass);
          } else {
            marker.find(ctx).removeClass(lastClass);
          }
        }
        if ($slide.hasClass("dialog_menu")) {
          var name = $slide.attr("id");
          var existingNode = $.dialog.list[name];
          /** @type {string} */
          var validClass = activeClassName;
          if (existingNode.has_frame()) {
            marker.find(".open_window").removeClass(validClass);
            marker.find(".refresh").removeClass(validClass);
            marker.find(".qrcode").removeClass(validClass);
          } else {
            marker.find(".open_window").addClass(validClass);
            marker.find(".refresh").addClass(validClass);
            marker.find(".qrcode").addClass(validClass);
          }
          if ($("." + name).hasClass("dialog-can-resize")) {
            marker.find(".dialog_max").removeClass(validClass);
          } else {
            marker.find(".dialog_max").addClass(validClass);
          }
        }
        if ($slide.hasClass("menuMore")) {
          /** @type {number} */
          var d = 0;
          ui.fileLight.fileListSelect().each(function() {
            var start = core.pathExt(ui.fileLight.name($(this)));
            if (inArray(core.filetype.music, start) || inArray(core.filetype.movie, start)) {
              d += 1;
            }
          });
          if (0 == d) {
            marker.find(".playmedia").addClass(activeClassName);
          } else {
            marker.find(".playmedia").removeClass(activeClassName);
          }
        }
        if ($slide.hasClass("menuZipListFolder") || $slide.hasClass("menuZipListFile")) {
          if (core.pathCurrentWriteable()) {
            marker.find(".unzip_this").removeClass(lastClass);
          } else {
            marker.find(".unzip_this").addClass(lastClass);
          }
        }
        if ($slide.hasClass("toolPathMore")) {
          ui.fileLight.menuResetMore();
        }
      }
        $(".context-menu-list").css("font-size","15px");
    },
    isDisplay : function() {
      return 0 == $(".context-menu-list:visible").length ? false : true;
    },
    hidden : function() {
      $(".context-menu-list").filter(":visible").filter(":not(.menuNotAutoHidden)").trigger("contextmenu:hide");
    }
  };
});
