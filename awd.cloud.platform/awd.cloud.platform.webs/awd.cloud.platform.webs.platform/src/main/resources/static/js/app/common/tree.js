define("app/common/tree", ["./pathOperate", "./pathOpen", "./myPlayer"], function(jQuery) {
  var update;
  var item = jQuery("./pathOperate");
  var self = jQuery("./pathOpen");
  var n = false;
  ui.pathOpen = self;
  ui.pathOperate = item;
  var view;
  var open = function() {
    if (0 != $("#windowMaskView").length) {
      if ("block" == $("#windowMaskView").css("display")) {
        if (inArray(core.filetype.image, get().type)) {
          self.open(get().path, get().type);
        }
      }
    }
  };
  var next = function(value, shallow) {
    var elems = ["menuTreeGroup", "menuTreeFav", "menuTreeFolderFav"];
    if (value && (value[0] && -1 !== $.inArray(value[0].menuType, elems))) {
      return value;
    }
    var results = [];
    var array = [];
    var i = 0;
    for (;value.length > i;i++) {
      value[i].drop = false;
      value[i].drag = false;
      value[i].name = value[i].name;
      if (value[i].isParent) {
        if (value[i].children) {
          value[i].children = next(value[i].children);
        }
      }
      value[i].is_writeable;
      if ("folder" == value[i].type) {
        array.push(value[i]);
      } else {
        results.push(value[i]);
      }
    }
    return shallow ? value : (array = array.sort(function(module, curr) {
      module = module.name;
      curr = curr.name;
      return ui.pathOperate.strSort(module, curr);
    }), results = results.sort(function(module, curr) {
      module = module.name;
      curr = curr.name;
      return ui.pathOperate.strSort(module, curr);
    }), array.concat(results));
  };
  var record = function() {
    var value = {};
    var animation = "tree_open_" + md5(Config.pageApp);
    var iterator = function(callback) {
      if (!LocalData.support()) {
        return{};
      }
      if (undefined == callback) {
        var el = LocalData.getConfig(animation);
        return 0 == el ? {} : el;
      }
      LocalData.setConfig(animation, callback);
    };
    var setup = function(data) {
      var i = 0;
      for (;data.length > i;i++) {
        var p = data[i].path;
        if (undefined !== value[p]) {
          data[i].open = value[p];
        }
      }
      return data;
    };
    var _forEach = function() {
      var codeSegments = view.getNodesByFilter(function(o) {
        return 0 == o.level ? true : false;
      });
      var def = {};
      var i = 0;
      for (;codeSegments.length > i;i++) {
        def[codeSegments[i].path] = codeSegments[i].open;
      }
      return value = def, iterator(value), value;
    };
    return value = iterator(), {
      list : function() {
        return value;
      },
      reset : setup,
      save : _forEach
    };
  }();
  var init = function() {
    $.ajax({
      url : Config.treeAjaxURL + "&type=init",
      dataType : "json",
      error : function() {
        $("#folderList").html('<div style="text-align:center;">' + "系统错误" + "</div>");
      },
      success : function(result) {
        if (!result.code) {
          return $("#folderList").html('<div style="text-align:center;">' + "系统错误" + "</div>"), undefined;
        }
        var data = next(result.data, true);
        data = record.reset(data);
        
        $.fn.zTree.init($("#folderList"), options, data);
        view = $.fn.zTree.getZTreeObj("folderList");
      }
    });

	 
	  
    $(".ztree .switch").die("mouseenter").live("mouseenter", function() {
      $(this).addClass("switch_hover");
    }).die("mouseleave").live("mouseleave", function() {
      $(this).removeClass("switch_hover");
    });
    if ("editor" == Config.pageApp) {
      Mousetrap.bind("up", function(selection) {
        mergeCells(selection, "up");
      }).bind("down", function(selection) {
        mergeCells(selection, "down");
      }).bind("left", function(selection) {
        mergeCells(selection, "left");
      }).bind("right", function(selection) {
        mergeCells(selection, "right");
      });
      Mousetrap.bind("enter", function() {
        tree.open();
      }).bind(["del", "command+backspace"], function() {
        tree.remove();
      }).bind("f2", function(deepDataAndEvents) {
        stopPP(deepDataAndEvents);
        tree.rname();
      }).bind(["ctrl+f", "command+f"], function(deepDataAndEvents) {
        stopPP(deepDataAndEvents);
        tree.search();
      }).bind(["ctrl+c", "command+c"], function() {
        tree.copy();
      }).bind(["ctrl+x", "command+x"], function() {
        tree.cute();
      }).bind(["ctrl+v", "command+v"], function() {
        tree.past();
      }).bind("alt+m", function() {
        tree.create("folder");
      }).bind("alt+n", function() {
        tree.create("file");
      });
    }
  };
  var mergeCells = function(selection, direction) {
    stopPP(selection);
    var node = view.getSelectedNodes()[0];
    if (node) {
      switch(direction) {
        case "up":
          var d = node.getPreNode();
          if (d) {
            if (d.open && d.children.length > 0) {
              for (;d.open && (d.children && d.children.length >= 1);) {
                d = d.children[d.children.length - 1];
              }
            }
          } else {
            d = node.getParentNode();
          }
          view.selectNode(d);
          break;
        case "down":
          if (node.open && node.children.length >= 1) {
            d = node.children[0];
          } else {
            var n = node;
            d = n.getNextNode() || n.getParentNode().getNextNode();
            try {
              for (;!d;) {
                n = n.getParentNode();
                d = n.getNextNode() || n.getParentNode().getNextNode();
              }
            } catch (e) {
            }
          }
          view.selectNode(d);
          break;
        case "left":
          if (node.isParent) {
            if (node.open) {
              view.expandNode(node, false);
            } else {
              view.selectNode(node.getParentNode());
            }
          } else {
            view.selectNode(node.getParentNode());
          }
          break;
        case "right":
          if (node.open) {
            view.selectNode(node.children[0]);
          } else {
            view.expandNode(node, true);
          }
          break;
        default:
        ;
      }
      open();
    }
  };
  var initAce = function() {
    return "editor" == Config.pageApp ? false : true;
  };
  var options = {
    async : {
      enable : true,
      dataType : "json",
      url : Config.treeAjaxURL,
      autoParam : ["ajax_path=path", "tree_icon=tree_icon"],
      dataFilter : function(data, type, self) {
        return self.code ? next(self.data) : null;
      }
    },
    edit : {
      enable : true,
      showRemoveBtn : false,
      showRenameBtn : false,
      drag : {
        isCopy : false,
        isMove : false
      }
    },
    view : {
      showLine : false,
      selectedMulti : false,
      expandSpeed : "fast",
      dblClickExpand : false,
      addDiyDom : function(dataAndEvents, self) {
        var index = 15;
        var next = $("#" + self.tId + "_switch");
        var $this = $("#" + self.tId + "_ico");
        next.remove();
        self.iconSkin = self.tree_icon;
        var result = self.tree_icon;
        if (self.ext ? result = self.ext : self.tree_icon || (result = self.type), $this.before(next).before('<span id="' + self.tId + '_my_ico"  class="tree_icon button">' + core.iconSmall(result) + "</span>").remove(), undefined != self.ext && $this.attr("class", "").addClass("file " + self.ext).removeAttr("style"), self.level >= 1) {
          var error = "<span class='space' style='display: inline-block;width:" + index * self.level + "px'></span>";
          next.before(error);
        }
        var img = "";
        if (undefined != self.menuType) {
          img = self.menuType;
        } else {
          if ("file" == self.type || "oexe" == self.ext) {
            img = "menuTreeFile";
          }
          if ("folder" == self.type) {
            img = "menuTreeFolder";
          }
        }
        var text = "名称" + ":" + self.name + "\n" + "大小" + ":" + core.fileSize(self.size) + "\n" + "修改时间" + ":" + self.mtime;
        if ("file" != self.type) {
          text = self.name;
        }
        next.parent().addClass(img).attr("title", text);
        if (0 == self.is_writeable) {
          next.parent().addClass("file_not_writeable");
        }
        if (0 == self.is_readable) {
          next.parent().addClass("file_not_readable");
        }
        if (0 === self.exists) {
          next.parent().addClass("file_not_readable");
        }
      }
    },
    callback : {
      onClick : function(e, event, node) {
        return view.selectNode(node), "editor" == Config.pageApp && "folder" == node.type ? (view.expandNode(node), undefined) : ("editor" == Config.pageApp || "folder" != node.type ? ui.tree.openEditor() : ui.path.list(node.path), undefined);
      },
      beforeDblClick : function() {
        return true;
      },
      onCollapse : function(node, model, o) {
        if (0 == o.level) {
          record.save();
        }
      },
      onExpand : function(e, node, o) {
        if (0 == o.level) {
          record.save();
        }
      },
      onDblClick : function(ev, item, node) {
        return $(ev.target).hasClass("switch") || !initAce() ? false : (view.expandNode(node), undefined);
      },
      beforeRightClick : function(dataAndEvents, selector) {
        view.selectNode(selector);
      },
      beforeAsync : function(advice, self) {
        self.ajax_name = self.name;
        self.ajax_path = self.path;
        $("#" + self.tId + "_my_ico").addClass("ico_loading");
      },
      onAsyncSuccess : function(dataAndEvents, deepDataAndEvents, setting, imageData) {
        return $("#" + setting.tId + "_my_ico").removeClass("ico_loading"), 0 == imageData.data.length ? (view.removeChildNodes(setting), undefined) : ("function" == typeof update && (update(), update = undefined), undefined);
      },
      onRename : function(dataAndEvents, deepDataAndEvents, node) {
        var me = node.getParentNode();
        if (view.getNodesByParam("name", node.name, me).length > 1) {
          return Tips.tips("出错了,该名称已存在！", false), view.removeNode(node), undefined;
        }
        if (node.create) {
          var m = node.path + "/" + node.name;
          if ("folder" == node.type) {
            item.newFolder(m, function() {
              update = function() {
                var selector = view.getNodesByParam("name", node.name, me)[0];
                view.selectNode(selector);
                successCallback();
              };
              callback(me);
            });
          } else {
            item.newFile(m, function() {
              update = function() {
                var selector = view.getNodesByParam("name", node.name, me)[0];
                view.selectNode(selector);
                successCallback();
              };
              callback(me);
            });
          }
        } else {
          var color = rtrim(node.path, "/");
          var LEVEL_ACCESS = core.pathFather(node.path) + node.name;
          item.rname(color, LEVEL_ACCESS, function(val) {
            node.path = val;
            update = function() {
              var selector = view.getNodesByParam("name", node.name, me)[0];
              view.selectNode(selector);
              successCallback();
              if ("folder" == node.type) {
                ui.path.list(node.path);
              }
            };
            callback(me);
          });
        }
      },
      beforeDrag : function(dataAndEvents, employees) {
        var i = 0;
        var l = employees.length;
        for (;l > i;i++) {
          if (employees[i].drag === false) {
            return false;
          }
        }
        return true;
      },
      beforeDrop : function(dataAndEvents, params, collection) {
        return collection ? collection.drop !== false : true;
      },
      onDrop : function(transferData, dataTransferObj, obj, e) {
        var ps = "";
        var later = "";
        var meta = obj[0];
        if (meta.father || meta.this_path) {
          ps = meta.father + urlEncode(meta.name);
          later = e.father + urlEncode(e.name);
          item.cuteDrag([{
            path : ps,
            type : meta.type
          }], later, function() {
            callback(meta);
          });
        }
      }
    }
  };
  var get = function(dataAndEvents) {
    if (view) {
      var item = view.getSelectedNodes()[0];
      var type = "";
      return item ? (type = item.type, ("_null_" == type || undefined == type) && (type = "folder"), "file" == type && (type = item.ext), dataAndEvents ? [{
        path : item.path,
        type : type,
        node : item
      }] : {
        path : item.path,
        type : type,
        node : item
      }) : {
        path : "",
        type : ""
      };
    }
  };
  var callback = function(node) {
	  if(node==undefined){
		  node = view.getSelectedNodes()[0];
	  }
	  if(node.isParent || (node = node.getParentNode())){
		  view.reAsyncChildNodes(node, "refresh");
		  return ;
	  }else{
		  ui.tree.init();
		  return;
	  }
  };
  var onSuccess = function() {
    parse(G.AWD_USER_FAV);
    successCallback();
  };
  var onload = function() {
    onSuccess();
    parse(G.AWD_GROUP_ROOT_SELF);
    parse(G.AWD_GROUP_ROOT_ALL);
  };
  var parse = function(end) {
    var arr = view.getNodesByParam("path", end, null);
    callback(arr[0]);
  };
  var successCallback = function() {
    if ("explorer" == Config.pageApp) {
      ui.f5();
    }
  };
  return{
    treeOpenHistory : record,
    pathOpen : self,
    treeDataSort : next,
    init : init,
    refresh : callback,
    refreshPath : parse,
    refreshFav : onSuccess,
    refreshGroup : onload,
    zTree : function() {
      return view;
    },
    openEditor : function() {
      self.openEditor(get().path);
    },
    openWindow : function() {
      self.openWindow(get().path);
    },
    share : function() {
      item.share(get());
    },
    download : function() {
      if ("folder" == get().type) {
        item.zipDownload(get(true));
      } else {
        self.download(get().path);
      }
    },
    setSelect : function(source) {
      if (view) {
        var urls = view.getSelectedNodes();
        if (!$.isArray(urls) || (1 != urls.length || trim(urls[0].path, "/") != trim(source, "/"))) {
          var selector = view.getNodesByFilter(function(rule) {
            return trim(rule.path, "/") == trim(source, "/") ? true : false;
          }, true);
          if (selector) {
            view.selectNode(selector, false);
          }
        }
      }
    },
    open : function() {
      if (!($(".dialog_path_remove").length >= 1)) {
        var data = get();
        if ("oexe" == data.type) {
          data.path = data.node;
        }
        self.open(data.path, data.type);
      }
    },
    fav : function() {
      var result = get();
      result.name = result.node.name;
      result.node = "null";
      item.fav(result);
    },
    createLink : function(opt_onClick) {
      var value = get();
      item.createLink(value.path, value.node.name, value.type, opt_onClick, successCallback);
    },
    search : function() {
      core.search("", get().path);
    },
    appEdit : function() {
      var self = get();
      var file = self.node;
      file.path = self.path;
      item.appEdit(file, function() {
        callback(self.node.getParentNode());
      });
    },
    info : function() {
      item.info(get(true));
    },
    copy : function() {
      item.copy(get(true));
    },
    cute : function() {
      item.cute(get(true));
    },
    copyTo : function() {
      core.api.pathSelect({
        type : "folder",
        title : "复制到..."
      }, function(done) {
        item.copyDrag(get(true), done, "", false);
      });
    },
    cuteTo : function() {
      core.api.pathSelect({
        type : "folder",
        title : "移动到..."
      }, function(done) {
        item.cuteDrag(get(true), done, function() {
          parse();
        });
      });
    },
    favRemove : function() {
      item.favRemove(get().node.name, function(deepDataAndEvents) {
        Tips.tips(deepDataAndEvents);
        onSuccess();
      });
    },
    past : function() {
      var value = get();
      if (!value.node.isParent) {
        value.node = value.node.getParentNode();
      }
      item.past(value.path, function() {
        successCallback();
        callback(value.node);
      });
    },
    clone : function() {
      var view = get();
      if (!view.node.isParent) {
        view.node = view.node.getParentNode();
      }
      item.copyDrag(get(true), core.pathFather(view.path), function() {
        successCallback();
        if ("folder" == view.type) {
          callback(view.node.getParentNode());
        } else {
          callback(view.node);
        }
      }, true);
    },
    remove : function() {
      var path = get(true);
      var seg = path[0].node.getParentNode();
      path[0].type = path[0].node.type;
      path[0].type = "folder" == path[0].type ? "folder" : "file";
      item.remove(path, function() {
        successCallback();
        callback(seg);
      });
    },
    checkIfChange : function(deep) {
      if (!n) {
        n = true;
        if (view) {
          view.getNodesByFilter(function(hash) {
            var _path = hash.path;
            if("folder" == hash.type){
            	if(core.pathClear(_path) == core.pathClear(deep)){
            		callback(hash);
            	}
            }
            return false;
          }, true);
          setTimeout(function() {
            n = false;
          }, 500);
        }
      }
    },
    explorer : function() {
      var codeSegments = view.getSelectedNodes();
      if (0 >= codeSegments.length) {
        var element = view.getNodes();
        view.selectNode(element[0]);
      }
      var _path = get().path;
      if ("folder" != get().type) {
        _path = core.pathFather(_path);
      }
      core.explorer(_path);
    },
    openProject : function() {
      core.explorerCode(get().path);
    },
    create : function(dir) {
      var nodes = view.getSelectedNodes();
      if (0 >= nodes.length) {
        var element = view.getNodes();
        view.selectNode(element[0]);
      } else {
        if ("file" == nodes[0].type) {
          view.selectNode(nodes[0].getParentNode());
        }
      }
      var target = get();
      var node = target.node;
      var callback = node.getParentNode();
      var message = "newfile";
      var expected = 0;
      var label = "新建文件夹";
      if ("folder" == dir) {
        for (;view.getNodesByParam("name", label + "(" + expected + ")", callback).length > 0;) {
          expected++;
        }
        newNode = {
          name : label + "(" + expected + ")",
          ext : "",
          type : "folder",
          create : true,
          path : target.path
        };
      } else {
        var ext = dir;
        for (;view.getNodesByParam("name", message + "(" + expected + ")." + ext, callback).length > 0;) {
          expected++;
        }
        newNode = {
          name : message + "(" + expected + ")." + ext,
          ext : ext,
          type : "file",
          create : true,
          path : target.path
        };
      }
      if (undefined != node.children) {
        var isDefault = view.addNodes(node, newNode)[0];
        view.editName(isDefault);
      } else {
        if ("folder" != node.type) {
          node = node.getParentNode();
        }
        update = function() {
          var isDefault = view.addNodes(node, newNode)[0];
          view.editName(isDefault);
        };
        if (node.isParent) {
          view.expandNode(node);
        } else {
          update();
        }
      }
    },
    showFile : function() {
      var dat = "./share/file?sid=" + G.sid + "&user=" + G.user + "&path=" + get().path;
      window.open(dat);
    },
    rname : function() {
      var selectnode = view.getSelectedNodes()[0];
      view.editName(selectnode);
      selectnode.beforeName = selectnode.name;
    }
  };
});
