define("app/common/core", ["./tpl/search.html.js",
	"./tpl/search_list.html.js",
	"./tpl/upload.html.js",
	"./tpl/fileinfo/file_info.html.js",
	"./tpl/fileinfo/path_info.html.js",
	"./tpl/zipview.html.js",
	"./tpl/copyright.html.js",
	"./core.search",
	"./core.tools",
	"./core.upload",
	"./core.api",
	"./core.playSound",
	"./core.zipView"
	],
function(require, dataAndEvents) {
  tpl_search_box = require("./tpl/search.html");
  tpl_search_list = require("./tpl/search_list.html");
  tpl_upload = require("./tpl/upload.html");
  tpl_file_info = require("./tpl/fileinfo/file_info.html");
  tpl_path_info = require("./tpl/fileinfo/path_info.html");
  tpl_zipview = require("./tpl/zipview.html");
  var search = require("./core.search");
  var tools = require("./core.tools");
  var module = require("./core.upload");
  var api = require("./core.api");
  var Client = require("./core.playSound");
  var Block = require("./core.zipView");
  pathHashEncode = function(deepDataAndEvents) {
    return hashEncode(deepDataAndEvents);
  };
  pathHashDecode = function(deepDataAndEvents) {
    return hashDecode(deepDataAndEvents);
  };
  var init = function() {
    if (template.config("escape", false), 
    	template.config("compress", true), 
    	template.helper("core", core), 
    	template.helper("window", window), 
    	"undefined" != typeof G && (1 != G.is_root &&"0"!=G.user_type&& $(".menu_system_setting").remove(), 
    			G.is_root || (1 == AUTH["system_member:get"] || (1 == AUTH["system_group:get"] || $(".menu_system_group").remove())), 
    			G.user_config && ("0" == G.user_config.animate_open && ($.dialog.defaults.animate = false)),
    			Parser()), 
    	$("html").bind("click", function(ev) {
    		if (0 == $(ev.target).parents(".context-menu-list").length) {
    			try {
    				rightMenu.hidden();
    			} catch (e) {}
    		}
    	}),
    	$.dialog.defaults.animate && loadRipple(
			 ["a","button",".context-menu-item","#picker",".menuShareButton",".menuRecycleButton",".section " + ".list"], 
			  [".disabled",".disable",".ztree",".disable-ripple"]), 
			  $("a,img").attr("draggable", "false"), $.ajaxSetup({
				  headers : {
					  "X-CSRF-TOKEN" : Cookie.get("X-CSRF-TOKEN")
				  }
			  }), 
	  $(".common_footer [forceWap]").click(function() {
      var pdataOld = $(this).attr("forceWap");
      Cookie.set("forceWap", pdataOld);
      window.location.reload();
    }),
    core.setSkinDiy(), 
    core.tools.init(),
    SoundManager(), 
    "array" == $.type(window.awdReady)) {
      var s = 0;
      for (;window.awdReady.length > s;s++) {
        window.awdReady[s]();
      }
    }
    run();
  };
  var Parser = function() {
    var icon = window.location.port ? ":" + window.location.port : "";
    G.web_host = window.location.protocol + "//" + window.location.hostname + icon + "/";
    G.app_host = rtrim(G.web_host, "/") + window.location.pathname.replace("", "");
  };
  var SoundManager = function() {
    var browser = {
      ie : {
        music : ["mp3", "m4a", "aac"],
        movie : ["mp4", "m4v", "flv", "mov", "f4v"]
      },
      chrome : {
        music : ["mp3", "wav", "m4a", "aac", "oga", "ogg", "webma"],
        movie : ["mp4", "m4v", "flv", "mov", "f4v", "ogv", "webm", "webmv"]
      }
    };
    var isIE = !!window.ActiveXObject || "ActiveXObject" in window;
    var platform = "chrome";
    if (isIE) {
      platform = "ie";
    }
    core.filetypes.music = browser[platform].music;
    core.filetypes.movie = browser[platform].movie;
  };
  var run = function() {
    if (!isWap()) {
      require.async(["lib/poshytip/jquery.poshytip.js", "lib/poshytip/skin.css"], function() {
        var dom = $("[title]");
        dom.poshytip({
          className : "ptips-skin",
          liveEvents : true,
          slide : false,
          alignTo : "cursor",
          alignX : "right",
          alignY : "bottom",
          showAniDuration : 150,
          hideAniDuration : 200,
          offsetY : 10,
          offsetX : 20,
          showTimeout : function() {
            var nDigit = 1500;
            return $(this).attr("title-timeout") && (nDigit = parseInt($(this).attr("title-timeout"))), nDigit;
          },
          content : function() {
            var comment = $(this).data("title.poshytip");
            if ($(this).attr("title-data")) {
              var target = $($(this).attr("title-data"));
              comment = target.is("input") || target.is("textarea") ? target.val() : target.html();
            }
            return comment = comment ? comment : "", comment.replace(/\n/g, "<br/>");
          }
        });
        $("body").bind("mousedown click", function() {
          $(dom).poshytip("hide");
          $(".ptips-skin").remove();
        });
        $("input,textarea").live("focus", function() {
          $(dom).poshytip("hide");
          $(".ptips-skin").remove();
        });
      });
    }
  };
  return{
    search : search,
    init : init,
    serverDwonload : module.serverDwonload,
    upload : module.upload,
    uploadInit : module.init,
    playSound : Client.playSound,
    playSoundFile : Client.playSoundFile,
    zipView : function(startLine) {
      new Block(startLine);
    },
    tools : tools,
    api : api,
    filetypes : {
      image : ["jpg", "jpeg", "png", "bmp", "gif", "ico", "svg", "cur", "webp"],
      music : ["mp3", "wav", "m4a", "aac", "oga", "ogg", "webma"],
      movie : ["mp4", "m4v", "flv", "mov", "f4v", "ogv", "webm", "webmv"],
      doc : ["doc", "docx", "docm", "xls", "xlsx", "xlsb", "xlsm", "ppt", "pptx", "pptm"],
      archive : ["zip", "tar", "gz", "tgz", "ipa", "apk", "rar", "7z", "iso", "bz2", "zx", "z", "arj"],
      archive_list : ["zip", "tar", "gz", "tgz", "ipa", "apk", "rar", "7z", "iso", "zx", "z", "arj"],
      text : ["txt", "textile", "oexe", "inc", "csv", "log", "asc", "tsv", "lnk", "url", "webloc", "meta", "localized", "xib", "xsd", "storyboard", "plist", "csproj", "pch", "pbxproj", "local", "xcscheme", "manifest", "vbproj", "strings", "jshintrc", "sublime-project", "readme", "changes", "changelog", "version", "license", "changelog", "abap", "abc", "as", "asp", "aspx", "ada", "adb", "htaccess", "htgroups", "htgroups", "htpasswd", "asciidoc", "adoc", "asm", "a", "ahk", "bat", "cmd", "cpp", "c",
      "cc", "cxx", "h", "hh", "hpp", "ino", "c9search_results", "cirru", "cr", "clj", "cljs", "cbl", "cob", "coffee", "cf", "cson", "cakefile", "cfm", "cs", "css", "curly", "d", "di", "dart", "diff", "patch", "dockerfile", "dot", "dummy", "dummy", "e", "ge", "ejs", "ex", "exs", "elm", "erl", "hrl", "frt", "fs", "ldr", "ftl", "gcode", "feature", ".gitignore", "glsl", "frag", "vert", "gbs", "go", "groovy", "haml", "hbs", "handlebars", "tpl", "mustache", "hs", "hx", "html", "hta", "htm", "xhtml", "eex",
      "html.eex", "erb", "rhtml", "html.erb", "ini", "inf", "conf", "cfg", "prefs", "io", "jack", "jade", "java", "ji", "jl", "jq", "js", "jsm", "json", "jsp", "jsx", "latex", "ltx", "bib", "lean", "hlean", "less", "liquid", "lisp", "ls", "logic", "lql", "lsl", "lua", "lp", "lucene", "Makefile", "makemakefile", "gnumakefile", "makefile", "ocamlmakefile", "make", "md", "markdown", "mask", "matlab", "mz", "mel", "mc", "mush", "mysql", "nix", "nsi", "nsh", "m", "mm", "ml", "mli", "pas", "p", "pl", "pm",
      "pgsql", "php", "phtml", "shtml", "php3", "php4", "php5", "phps", "phpt", "aw", "ctp", "module", "ps1", "praat", "praatscript", "psc", "proc", "plg", "prolog", "properties", "proto", "py", "r", "cshtml", "rd", "rhtml", "rst", "rb", "ru", "gemspec", "rake", "guardfile", "rakefile", "gemfile", "rs", "sass", "scad", "scala", "scm", "sm", "rkt", "oak", "scheme", "scss", "sh", "bash", "bashrc", "sjs", "smarty", "tpl", "snippets", "soy", "space", "sql", "sqlserver", "styl", "stylus", "svg", "swift",
      "tcl", "tex", "toml", "twig", "swig", "ts", "typescript", "str", "vala", "vbs", "vb", "vm", "v", "vh", "sv", "svh", "vhd", "vhdl", "wlk", "wpgm", "wtest", "xml", "rdf", "rss", "wsdl", "xslt", "atom", "mathml", "mml", "xul", "xbl", "xaml", "xq", "yaml", "yml", "vcproj", "vcxproj", "filters", "cer", "reg", "config", "pem", "srt", "ass"],
      bindary : ["bin", "hex", "zip", "pdf", "swf", "gzip", "rar", "arj", "tar", "gz", "cab", "tbz", "tbz2", "lzh", "uue", "bz2", "ace", "exe", "so", "dll", "chm", "rtf", "odp", "odt", "pages", "class", "psd", "ttf", "fla", "7z", "dmg", "iso", "dat", "ipa", "lib", "a", "apk", "so", "o"]
    },
    fileOpenMode : {
      ini : ["inc", "inf", "strings"],
      xml : ["xib", "xsd", "storyboard", "plist", "csproj", "pch", "pbxproj", "xcscheme", "config", "vcproj", "vcxproj", "filters", "webloc"],
      json : ["oexe", "jshintrc", "sublime-project"],
      markdown : ["readme", "changes", "version", "license", "changelog"]
    },
    getPathIcon : function(source, t) {
      if (t = undefined == t ? "" : t, "string" == $.type(source)) {
        var name = trim(trim(source), "/");
        if (source = {}, "{" != name.substring(0, 1) || name.split("/").length > 1) {
          return{
            icon : "",
            name : ""
          };
        }
        source.path_type = name.match(/\{.*\}/);
        source.id = name.split(":")[1];
      }
      var json = {};
      json[G.AWD_USER_SHARE] = {
        icon : "userSelf",
        name : "我的共享"
      };
      json[G.AWD_GROUP_PATH] = {
        icon : "groupSelfOwner"
      };
      json[G.AWD_GROUP_SHARE] = {
        icon : "groupGuest"
      };
      json[G.AWD_USER_RECYCLE] = {
        icon : "recycle",
        name : "回收站"
      };
      json[G.AWD_USER_FAV] = {
        icon : "treeFav",
        name : "收藏夹"
      };
      json[G.AWD_GROUP_ROOT_SELF] = {
        icon : "groupSelfRoot",
        name : "我所在的组"
      };
      json[G.AWD_GROUP_ROOT_ALL] = {
        icon : "groupRoot",
        name : "组织架构"
      };
      var data = json[source.path_type];
      return source.path_type == G.AWD_USER_SHARE && G.user_id != source.id ? data = {
        icon : "user",
        name : t
      } : source.path_type == G.AWD_GROUP_PATH && ("owner" == source.role && (data = {
        icon : "groupSelfOwner"
      })), undefined == data && (data = {
        icon : "",
        name : ""
      }), undefined == data.name && (data.name = t), data;
    },
    isSystemPath : function(source) {
      source = trim(trim(source), "/");
      if (undefined == source || ("{" != source.substring(0, 1) || source.split("/").length > 1)) {
        return false;
      }
      var filtered = source.match(/\{.*\}/);
      var elems = [G.AWD_USER_SHARE, G.AWD_GROUP_SHARE, G.AWD_USER_RECYCLE, G.AWD_USER_FAV, G.AWD_GROUP_ROOT_SELF, G.AWD_GROUP_ROOT_ALL];
      var a = false;
      return-1 !== $.inArray(filtered[0], elems) && (a = true), a;
    },
    pathPre : function(source) {
      if (source = trim(trim(source), "/"), undefined == source || "{" != source.substring(0, 1)) {
        return "";
      }
      var i = source.match(/\{.*\}/);
      return i[0];
    },
    contextmenu : function(e) {
      try {
        rightMenu.hidden();
      } catch (i) {
      }
      var ev = e || window.event;
      return ev ? ev && $(ev.target).is("textarea") || ($(ev.target).is("input") || ($(ev.target).is("p") || ($(ev.target).is("pre") || (0 != $(ev.target).parents(".can_right_menu").length || (0 != $(ev.target).parents(".topbar").length || (0 != $(ev.target).parents(".edit_body").length || 0 != $(ev.target).parents(".aui_state_focus").length)))))) ? true : false : true;
    },
    pathThis : function(value) {
      if (!value || "/" == value) {
        return "";
      }
      var baseName = rtrim(this.pathClear(value), "/");
      var idx = baseName.lastIndexOf("/");
      var key = baseName.substr(idx + 1);
      if (0 == key.search("fileProxy")) {
        key = urlDecode(key.substr(key.search("&path=")));
        var keys = key.split("/");
        key = keys[keys.length - 1];
        if ("" == key) {
          key = keys[keys.length - 2];
        }
      }
      return key;
    },
    pathClear : function(attr) {
      if (!attr) {
        return "";
      }
      var filtered = attr.replace(/\\/g, "/");
      return filtered = filtered.replace(/\/+/g, "/"), filtered = filtered.replace(/\.+\//g, "/");
    },
    pathFather : function(protoProps) {
      var baseName = rtrim(this.pathClear(protoProps), "/");
      var lastSlashIndex = baseName.lastIndexOf("/");
      return baseName.substr(0, lastSlashIndex + 1);
    },
    pathExt : function(source) {
      var name = trim(source, "/");
      return-1 != name.lastIndexOf("/") && (name = name.substr(name.lastIndexOf("/") + 1)), -1 != name.lastIndexOf(".") ? name.substr(name.lastIndexOf(".") + 1).toLowerCase() : name.toLowerCase();
    },

    path2url : function(attr, is_root) {
      if ("http" == attr.substr(0, 4)) {
        return attr;
      }
      if (undefined == is_root) {
        is_root = true;
      }
      var t;
      var val = this.pathClear(attr);
      this.pathExt(val);

      var trim = function(params) {
        if (!params) {
          return "";
        }
        var qs = urlEncode(params);
        return qs = qs.replace(/%2F/g, "/");
      };
      return G.is_root && (is_root && val.substring(0, G.web_root.length) == G.web_root) ? t = G.web_host + trim(val.replace(G.web_root, "")) : (t = G.app_host + "explorer/fileProxy?access_token=" + G.access_token + "&path=" + urlEncode(val), G.share_page !== undefined && (t = G.app_host + "share/fileProxy?user=" + G.user + "&sid=" + G.sid + "&path=" + urlEncode(val))), t;
    },
    pathReadable : function(new_path) {
      if ("object" != typeof G.json_data) {
        return true;
      }
      var codeSegments;
      codeSegments = G.json_data.filelist;
      var i = 0;
      for (;codeSegments.length > i;i++) {
        if (codeSegments[i].path == new_path) {
          return undefined == codeSegments[i].is_readable || 1 == codeSegments[i].is_readable ? true : false;
        }
      }
      codeSegments = G.json_data.folderlist;
      i = 0;
      for (;codeSegments.length > i;i++) {
        if (codeSegments[i].path == new_path) {
          return undefined == codeSegments[i].is_readable || 1 == codeSegments[i].is_readable ? true : false;
        }
      }
      return true;
    },
    pathCurrentWriteable : function() {
      return "editor" == Config.pageApp ? false : G.json_data.info ? G.json_data.info.can_upload : false;
    },
    authCheck : function(service, userObj) {
      return G.is_root ? true : AUTH.hasOwnProperty(service) ? AUTH[service] ? true : (undefined == userObj && (userObj = "管理员禁止了此权限!"), Tips.tips(userObj, false), false) : true;
    },
    ajaxError : function(jqXHR) {
      var html = jqXHR.responseText;
      var contents = $.dialog.list.ajaxErrorDialog;
      return Tips.close("系统错误", false), "\x3c!--user login--\x3e" == html.substr(0, 17) ? (setTimeout(function() {
        var wnd = ShareData.frameTop();
        wnd.location.reload();
      }, 500), undefined) : (0 == jqXHR.status && ("" == html && (html = "网络连接错误 (net::ERR_CONNECTION_RESET)，连接已重置<br/>请联系主机商或网管，检查防火墙配置！")),
      html = '<div class="ajaxError">' + html + "</div>", contents ? contents.content(html) : $.dialog({
        id : "ajaxErrorDialog",
        padding : 0,
        width : "60%",
        height : "50%",
        fixed : true,
        resize : true,
        ico : core.icon("error"),
        title : "ajax error",
        content : html
      }), undefined);
    },
    fileGet : function(x, callback, fn) {
      var file = "filename";
      if ("http" == x.substr(0, 4)) {
        file = "file_url";
      }
      var arg = "./editor/fileGet?" + file + "=" + urlEncode(x);
      if (G.share_page !== undefined) {
        arg = "./share/fileGet?user=" + G.user + "&sid=" + G.sid + "&" + file + "=" + urlEncode(x);
      }
      if ("./?" == x.substr(0, "./?".length)) {
        arg = x;
      }
      $.ajax({
        url : arg,
        dataType : "json",
        error : function(jqXHR, textStatus, errorThrown) {
          core.ajaxError(jqXHR, textStatus, errorThrown);
          if ("function" == typeof fn) {
            fn();
          }
        },
        success : function(result) {
          if (result.code) {
            if ("function" == typeof callback) {
              if (1 == result.data.base64) {
                result.data.content = base64Decode(result.data.content);
              }
              callback(result.data.content, result, arg);
            }
          }
        }
      });
    },
    fileInfo : function(el, fn) {
      var appFrontendUrl = "explorer/pathInfo";
//      if (G.share_page !== undefined) {
//        appFrontendUrl = "share/pathInfo?user=" + G.user + "&sid=" + G.sid;
//      }
      $.ajax({
        url : appFrontendUrl,
        type : "POST",
        dataType : "json",
        data : el,
        error : core.ajaxError,
        success : function(textStatus) {
          if ("function" == typeof fn) {
            fn(textStatus);
          }
        }
      });
    },
    fileLink : function(postData, on) {
      var failuresLink = 'list=[{"type":"file","path":"' + urlEncode(postData) + '"}]&viewPage=1';
      this.fileInfo(failuresLink, function(err) {
        var failuresLink = err.code ? err.data.download_path : false;
        return failuresLink ? ("function" == typeof on && on(failuresLink), undefined) : (Tips.tips("您没有此权限，请联系管理员!" + "==>" + "文件属性", false), undefined);
      });
    },
    setting : function(name) {
      if (undefined == name) {
        name = G.is_admin=="1"? "system" : "user";
      }
      if (ShareData.frameTop("Opensetting_mode")) {
        ShareData.frameTop("Opensetting_mode", function(dataAndEvents) {
          dataAndEvents.Setting.setGoto(name);
          $.dialog.list.setting_mode.display(true);
        });
      } else {
        $.dialog.open("./setting#" + name, {
          id : "setting_mode",
          fixed : true,
          ico : core.icon("setting"),
          resize : true,
          title : "系统设置",
          width : 1100,
          height : 660
        });
      }
    },
    copyright : function() {
      var data = require("./tpl/copyright.html");
      var conf = template.compile(data);
      var evt = ShareData.frameTop();
      evt.art.dialog({
        id : "copyright_dialog",
        bottom : 0,
        right : 0,
        simple : true,
        resize : false,
        title : "关于",
        width : 425,
        padding : "0",
        fixed : true,
        content : conf({
          LNG : LNG,
          G : G
        })
      });
      evt.$(".copyright_dialog").addClass("animated-700 zoomIn");
    },
    qrcode : function(params, follow) {
      if ("./" == params.substr(0, 2)) {
        params = G.app_host + params.substr(2);
      }
      var t = "./user/qrcode?url=" + quoteHtml(urlEncode(params));
      var h = "<a href='" + quoteHtml(params) + "' s='" + params + "' target='_blank'><img src='" + t + "' style='border:1px solid #eee;'/></a>";
      $.dialog({
        follow : follow,
        fixed : true,
        resize : false,
        title : "URL 二维码",
        padding : 30,
        content : h
      });
    },
    appStore : function(url) {
      var evt = ShareData.frameTop();
      $.ajax({
            url: "/getWebURL",
            data:{serverName:url},
			dataType: "json",
			beforeSend: function() {
				$(".tools-left .msg").stop(true, true).fadeIn(200);
			},
			success: function(result) {                
				evt.$.dialog.open(result.url, {
                    id : "app_store",
                    fixed : true,
                    ico : core.icon("appStore"),
                    resize : true,
                    title : "应用商店",
                    width : '100%',
                    height : '100%'
                });
			},
			error: function(e, i, a) {
				$(".tools-left .msg").fadeOut(300);
				core.ajaxError(e, i, a);
			}
		})
      
    },
    openWindow : function(element) {
      var evt = ShareData.frameTop();
      var elementRect = evt.$.dialog.open(element, {
        fixed : true,
        resize : true,
        width : "80%",
        height : "75%"
      });
      return elementRect;
    },
    openDialog : function(url, template, currentSection, evt) {
      if (url) {
        if (undefined == evt) {
          evt = "openDialog" + UUID();
        }
        var h = "<iframe frameborder='0' name='Open" + evt + "' src='" + htmlEncode(url) + "' style='width:100%;height:100%;border:0;'></iframe>";
        var widget = ShareData.frameTop();
        var dialog = widget.$.dialog({
          id : evt,
          fixed : true,
          title : currentSection,
          ico : template,
          width : "75%",
          height : "70%",
          padding : 0,
          content : h,
          resize : true
        });
        return dialog;
      }
    },
    openApp : function(self) {
      if ("url" == self.type) {
        var icon = self.icon;
        if (-1 == self.icon.search(G.static_path)) {
          if ("http" != self.icon.substring(0, 4)) {
            icon = G.static_path + "images/file_icon/icon_app/" + self.icon;
          }
        }
        if ("number" != typeof self.width) {
          if (-1 === self.width.search("%")) {
            self.width = parseInt(self.width);
          }
        }
        if ("number" != typeof self.height) {
          if (-1 === self.height.search("%")) {
            self.height = parseInt(self.height);
          }
        }
        if (!self.width) {
          self.width = "100%";
        }
        if (!self.height) {
          self.height = "100%";
        }
        var options = {
          resize : self.resize,
          fixed : true,
          ico : core.iconSrc(icon),
          title : self.name.replace(".oexe", ""),
          width : self.width,
          height : self.height,
          simple : self.simple,
          padding : 0
        };
        var evt = ShareData.frameTop();
        if ("swf" == core.pathExt(self.content)) {
          options.content = core.createFlash(self.content);
          evt.$.dialog(options);
        } else {
        	evt.$.dialog.open(self.content, options);
        }
      } else {
        var o = self.content;
        eval("{" + o + "}");
      }
    },
    openPath : function(item) {
      if ("undefined" != typeof Config && "explorer" == Config.pageApp) {
        ui.path.list(item, "tips");
      } else {
        core.explorer(item);
      }
    },
    explorer : function(query, number) {
      if (undefined == query) {
        query = "";
      }
      if (undefined == number) {
        number = core.pathThis(query);
      }
      var url = "./explorer?type=iframe&path=" + query;
      var evt = ShareData.frameTop();
      var F = evt.$.dialog.open(url, {
        className : "dialogExplorer",
        resize : true,
        fixed : true,
        ico : core.icon("folder"),
        title : number,
        width : "80%",
        height : "75%"
      });
      var ud = 20 * evt.$(".dialogExplorer").length;
      F.DOM.wrap.css({
        left : "+=" + ud + "px",
        top : "+=" + ud + "px"
      });
    },
    explorerCode : function(dirname) {
      if (undefined == dirname) {
        dirname = "";
      }
      var url = "editor?project=" + dirname;
      if (G.share_page !== undefined) {
        url = "./share/code_read?user=" + G.user + "&sid=" + G.sid + "&project=" + dirname;
      }
      window.open(url);
    },
    setSkinFinished : function() {
      var thumbnailSrc = $(".setSkin_finished").attr("src");
      if (thumbnailSrc) {
        $("#link_css_list").attr("href", thumbnailSrc);
        $(".setSkin_finished").remove();
      }
    },
    setSkin : function(index) {
      LocalData.set("theme", index);
      G.user_config.theme = index;
      var linkVal = G.static_path + "style/skin/" + index + ".css";
      if (linkVal != $("#link_css_list").attr("href")) {
        $("body").append('<img src="' + linkVal + '" onload="core.setSkinFinished();" onerror="core.setSkinFinished();" class="hidden setSkin_finished">');
      }
      this.setSkinDiy();
    },
    setSkinDiy : function() {
      if (G.user_config) {
        var theme = LocalData.get("theme");
        var styles = "awd_diy_style";
        var data = LocalData.getConfig(styles);
        if ("object" != typeof data) {
          if ("object" == typeof G.user_config.theme_diy) {
            data = G.user_config.theme_diy;
          }
        }
        if ("object" != typeof data) {
          data = {
            bg_blur : 1,
            bg_image : G.static_path + "images/wall_page/9.jpg",
            bg_type : "color",
            start_color : "#456",
            end_color : "#000",
            color_rotate : "200"
          };
          LocalData.setConfig(styles, data);
        }
        G.user_config.theme_diy = data;
        var node = "";
        if ("diy" == theme && data) {
          var script = require("./tpl/theme_diy.html");
          var compiled = template.compile(script);
          node = compiled(data);
        }
        $.setStyle(node, styles);
      }
    },
    editorFull : function() {
      var $span = $("iframe[name=OpenopenEditor]");
      $span.toggleClass("frame_fullscreen");
    },
    language : function(id) {
      Cookie.set("awd_user_language", id, 8760);
      window.location.reload();
    },
    fullScreen : function() {
      if ("true" == $("body").attr("fullScreen")) {
        core.exitfullScreen();
      }
      $("body").attr("fullScreen", "true");
      var w = ShareData.frameTop();
      var docElm = w.document.documentElement;
      if (docElm.requestFullscreen) {
        docElm.requestFullscreen();
      } else {
        if (docElm.mozRequestFullScreen) {
          docElm.mozRequestFullScreen();
        } else {
          if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
          }
        }
      }
    },
    exitfullScreen : function() {
      $("body").attr("fullScreen", "false");
      if (document.exitFullscreen) {
        document.exitFullscreen();
      } else {
        if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else {
          if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
          }
        }
      }
    },
    createFlash : function(dataAndEvents, settings, value) {
      var name = UUID();
      if (value === undefined || "" == value) {
        value = name;
      }
      var optsData = "";
      if ($.browser.msie) {
        if (9 > parseInt($.browser.version)) {
          optsData = 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"';
        }
      }
      var o = '<object type="application/x-shockwave-flash" class="' + name + '" ' + optsData + ' name="' + value + '" id="' + value + '" data="' + dataAndEvents + '" width="100%" height="100%" tabindex="-1" >' + '<param name="movie" value="' + dataAndEvents + '"/>' + '<param name="allowfullscreen" value="true" />' + '<param name="allowscriptaccess" value="always" />' + '<param name="allowScriptAccess" value="always" />' + '<param name="flashvars" value="' + settings + '" />' + '<param name="wmode" value="transparent" />' +
      '</object><div class="aui_loading" id="' + name + '_loading"><span>loading..</span></div>';
      return setTimeout(function() {
        var $input = $("." + name);
        if (1 != $input.length) {
          var object = ShareData.frameTop();
          $input = object.$("." + name);
        }
        if (1 == $input.length) {
          var t = 0;
          var input = $input[0];
          var poll = setInterval(function() {
            try {
              t++;
              if (100 == Math.floor(input.PercentLoaded())) {
                $input.next(".aui_loading").remove();
                clearInterval(poll);
                poll = null;
              } else {
                if (t > 100) {
                  $input.next(".aui_loading").remove();
                  clearInterval(poll);
                  poll = null;
                }
              }
            } catch (i) {
            }
          }, 100);
        }
      }, 50), o;
    },
    userSpaceHtml : function(baseName) {
      var test = baseName.split("/");
      var dy = parseFloat(test[0]);
      var d = 1073741824 * parseFloat(test[1]);
      var o = core.fileSize(parseFloat(test[0]));
      var dir = core.fileSize(d);
      var i = o + "/";
      var t = 100 * dy / d;
      if (t >= 100) {
        t = 100;
      }
      var addition = "";
      return t >= 80 && (addition = "warning"), 0 == d || isNaN(d) ? (i += "不限制", t = "0%") : (i += dir, t += "%"), i = "<div class='space_info_bar'><div class='space_process'><div class='space_process_use " + addition + "' style='width:" + t + "'></div></div>" + "<div class='space_info'>" + i + "</div>" + "</div>";
    },
    fileSize : function(c, nDecimalDigits) {
      if (undefined == c || "" == c) {
        return "";
      }
      if (undefined == nDecimalDigits && (nDecimalDigits = 1), 1024 >= c) {
        return parseInt(c) + "B";
      }
      c = parseInt(c);
      var map = {
        G : 1073741824,
        M : 1048576,
        K : 1024,
        B : 1
      };
      var letter;
      for (letter in map) {
        if (c >= map[letter]) {
          return(c / map[letter]).toFixed(nDecimalDigits) + letter;
        }
      }
    },
    uploadCheck : function(service, deepDataAndEvents) {
      return deepDataAndEvents = undefined == deepDataAndEvents ? true : deepDataAndEvents, "share" == G.share_page ? "1" == G.share_info.can_upload : (undefined == service && (service = "explorer:fileUpload"), !G.is_root && (AUTH.hasOwnProperty(service) && 1 != AUTH[service]) ? (deepDataAndEvents && Tips.tips("管理员禁止了此权限!", false), false) : G.json_data && !G.json_data.info.can_upload ? (deepDataAndEvents && (core.isSystemPath(G.this_path) ? Tips.tips("此目录不支持该操作!", false) : Tips.tips("该目录没有写权限",
      false)), false) : true);
    }
  };
});
