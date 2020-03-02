define("app/common/pathOpen", ["./myPlayer"], function(done) {
  var _open = function(data, name) {
    if (undefined != data) {
      if (!core.pathReadable(data)) {
    	  Tips.tips("该文件或目录没有读权限", false);
    	  core.playSound("error");
    	  return;
      }
      if ("folder" == name) {
    	  if("explorer" == Config.pageApp){
    		  ui.path.list(data + "/");
    	  }else{
    		  core.explorer(data);
    	  }
        return;
      }
      if ("oexe" != name) {
    	if(name){
    		name = core.pathExt(data);
    	}
        if (hasClass(name)) {
        	update(data);
        	return;
        }
        if ("swf" == name) {
        	$.dialog({
                resize : true,
                fixed : true,
                ico : core.icon("swf"),
                title : core.pathThis(data),
                width : "75%",
                height : "65%",
                padding : 0,
                content : core.createFlash(core.path2url(data))
              });
          return ;
        }
        if (inArray(core.filetype.archive_list, name)) {
        	core.zipView(data);
          return ;
        }
        if ("url" == name) {
        	core.fileGet(data, function(optionsString) {
                var aParts = optionsString.match(/URL=(.*)/);
                if (aParts.length >= 2) {
                  window.open(aParts[1]);
                } else {
                  _openEditor(data);
                }
              });
          return;
        }
        if ("webloc" == name) {
        	core.fileGet(data, function(s) {
                try {
                  var strip = $($.parseXML(s));
                  var dat = strip.find("string").text();
                  window.open(dat);
                } catch (n) {
                  _openEditor(data);
                }
              });
          return ;
        }
        if ("html" == name || "htm" == name) {
          var doc = core.path2url(data);
          core.openDialog(doc, core.icon("html"), core.pathThis(data));
          return;
        }
        if (inArray(core.filetype.image, name)) {
          doc = data;
          if(-1 == doc.indexOf("http:")){
        	  doc = core.path2url(doc)
          }
          MaskView.image(doc);
          return ;
        }
        if (inArray(core.filetype.music, name) || inArray(core.filetype.movie, name)) {
          doc = core.path2url(data, false);
          _play([{
              url : doc,
              name : urlDecode(core.pathThis(data)),
              ext : name
            }]);
          return;
        }
        if ("pdf" != name) {
        	if(inArray(core.filetype.doc, name)){
        		update(data);
        		return ;
        	}else{
        		if(inArray(core.filetype.text, name)){
        			_openEditor(data);
        			return;
        		}else{
        			if("editor" == Config.pageApp){
        				Tips.tips(name + "不是文本文件", false);
        			}else{
        				_openUnknow(data, "");
        			}        			
        		}
        		 
        	}
          return ;
        }
        var postData = core.path2url(data);
        doc = "./lib/plugins/pdfjs/web/viewer.html?file=" + urlEncode(postData);
        if (canvasSupport()) {
          core.openDialog(doc, core.icon(name), htmlEncode(core.pathThis(data)));
        } else {
          var res = "pdf" + UUID();
          var h = '<div id="' + res + '" style="height:100%;">\t\t\t\t<a href="' + postData + '" target="_blank" style="display:block;margin:0 auto;margin-top:80px;font-size:16px;text-align:center;">' + "操作失败" + " " + "下载" + " PDF</a></div>";
          $.dialog({
            resize : true,
            fixed : true,
            ico : core.icon(name),
            title : core.pathThis(data),
            width : 800,
            height : 400,
            padding : 0,
            content : h
          });
          PDFObject.embed(postData, "#" + res);
        }
      } else {
        if ("string" == typeof data) {
          var config = data;
          if ("string" != typeof data) {
            config = data.content.split("'")[1];
          }
          core.fileGet(config, function(argv) {
            var options = jsonDecode(argv);
            options.name = core.pathThis(config);
            core.openApp(options);
          });
        } else {
          if(data.content.indexOf("http:")==-1&&data.content.indexOf("core")==-1){
        	  $.ajax({
                  url: "/getWebURL",
                  data:{serverName:data.content},
      			dataType: "json",
      			beforeSend: function() {
      				$(".tools-left .msg").stop(true, true).fadeIn(200);
      			},
      			success: function(result) {   
      				data.content=result.url;
      				core.openApp(data);
      			},
      			error: function(e, i, a) {
      				$(".tools-left .msg").fadeOut(300);
      				core.ajaxError(e, i, a);
      			}
      		});
          }else{
        	  core.openApp(data);
          }	
          
        }
      }
    }
  };
  var _openUnknow = function(extraArgs, path) {
    if (undefined == path) {
      path = "";
    }
    var h = '<div class="unknow_file can_select" style="width:260px;word-break: break-all;"><span>' + "暂不支持打开" + "<br/>" + path + "</span><br/><br/>" + '<a class="btn btn-default btn-nomal" href="javascript:ui.pathOpen.openEditorForce(pathHashDecode(\'' + pathHashEncode(extraArgs) + "'));\"> " + "编辑" + " </a>&nbsp;" + '<a class="btn btn-success btn-nomal ml-15" href="javascript:ui.pathOpen.download(pathHashDecode(\'' + pathHashEncode(extraArgs) + "'));\"> " + "下载" +
    " </a></div>";
    $.dialog({
      id : "open_unknow_dialog",
      fixed : true,
      icon : "warning",
      title : "文件打开提示!",
      padding : 30,
      content : h,
      cancel : true
    });
    $(".unknow_file a").unbind("click").bind("click", function() {
      $.dialog.list.open_unknow_dialog.close();
    });
  };
  var _downloadUrl = function(url) {
    $.dialog({
      icon : "succeed",
      title : false,
      time : 1.5,
      content : "即将下载" + "..."
    });
    if (isWap()) {
      window.open(url);
    } else {
      $('<iframe src="' + url + '" style="display:none;width:0px;height:0px;"></iframe>').appendTo("body");
    }
  };
  var _download = function(params) {
    if (core.authCheck("explorer:fileDownload", "你无限下载！") && params) {
      if (!core.pathReadable(params)) {
        return Tips.tips("该文件或目录没有读权限", false), core.playSound("error"), undefined;
      }
      var result = "explorer/fileDownload?access_token=" + G.access_token + "&path=" + urlEncode(params);
      if (G.share_page !== undefined) {
        result = "share/fileDownload?user=" + G.user + "&sid=" + G.sid + "&path=" + urlEncode(params);
      }
      if ("http" == params.substr(0, 4)) {
        result = params;
      }
      _downloadUrl(result);
    }
  };
  var _openWindow = function(config) {
    if (undefined != config) {
      if (!core.pathReadable(config)) {
    	  Tips.tips("该文件或目录没有读权限", false);
    	  core.playSound("error");
    	  return;
      }
	    if(config.indexOf("http:")==-1){
		  $.ajax({
	        url: "/getWebURL",
	        data:{serverName:config},
				dataType: "json",
				beforeSend: function() {
					$(".tools-left .msg").stop(true, true).fadeIn(200);
				},
				success: function(result) {   
					config=result.url;
					var src = core.path2url(config);
					if(src.indexOf("http:")==-1&&src.indexOf("core")==-1){
			        	  $.ajax({
			                  url: "/getWebURL",
			                  data:{serverName:data.content},
			      			dataType: "json",
			      			beforeSend: function() {
			      				$(".tools-left .msg").stop(true, true).fadeIn(200);
			      			},
			      			success: function(result) {   
			      				window.open(result.url);
			      			},
			      			error: function(e, i, a) {
			      				$(".tools-left .msg").fadeOut(300);
			      				core.ajaxError(e, i, a);
			      			}
			      		});
					}else{
						window.open(src);
					}	  
			        
				},
				error: function(e, i, a) {
					$(".tools-left .msg").fadeOut(300);
					core.ajaxError(e, i, a);
				}
			});      
	  }
    }
    
  };
  var _openEditor = function(id) {
    if (id) {
      if (!core.pathReadable(id)) {
    	  Tips.tips("该文件或目录没有读权限", false);
    	  core.playSound("error");
    	  return ;
      }
      var data = core.pathExt(id);
      return "Win32" == navigator.platform || "Windows" == navigator.platform, inArray(core.filetype.doc, data) ? (update(id, true), undefined) : (core.pathThis(id), inArray(core.filetype.bindary, data) || (inArray(core.filetype.music, data) || (inArray(core.filetype.image, data) || (inArray(core.filetype.movie, data) || inArray(core.filetype.doc, data)))) ? (_open(id, data), undefined) : (_openEditorForce(id), undefined));
    }
  };
  var _openEditorForce = function(params) {
    var exports = ShareData.frameTop();
    if (exports.Editor !== undefined) {
    	exports.Editor.add(urlEncode(params));
    	return 	;
    }
    if ("editor" == Config.pageApp) {
    	ShareData.frameChild("OpenopenEditor", function(exports) {
            exports.Editor.add(urlEncode(params));
          });    	
    	return;
    }
    if (ShareData.frameTop("OpenopenEditor")) {
      var o = exports.$.dialog.list.openEditor;
      var backoff = 0;
      if (o) {
        if ("hidden" == $(o.DOM.wrap).css("visibility")) {
          backoff = 200;
          o.display(true).zIndex().focus();
        }
      }
      setTimeout(function() {
        ShareData.frameTop("OpenopenEditor", function(exports) {
          exports.Editor.add(urlEncode(params));
        });
      }, backoff);
    } else {
      var doc = "./editor/edit?filename=" + urlEncode(params);
      if (G.share_page !== undefined) {
        doc = "./share/edit?user=" + G.user + "&sid=" + G.sid + "#filename=" + urlEncode(params);
      }
      var undef = htmlEncode(urlDecode(core.pathThis(params)));
      core.openDialog(doc, core.icon("edit"), undef, "openEditor");
    }
  };
  var hasClass = function(name) {
    if (!G.awdOffice) {
      return false;
    }
    var excludes = ".docx.doc.odt.rtf.djvu.fb2.epub.xps.//.docm.dotm.dot.dotx.mht.//.wps.wpt.";
    	excludes += ".xls.xlsx.ods.csv.//.xlt.xltx.xlsb.xlsm.//.et.ett.";
    	excludes += ".pps.ppsx.ppt.pptx.odp.//.pot.potx.pptm.ppsm.potm.//.dps.dpt.";
    if(-1 === excludes.indexOf("." + name + ".")){
    	return false;
    }else{
    	return true;
    }
  };
  var update = function(val, dataAndEvents) {
    var url = "./explorer/officeView?access_token=" + G.access_token + "&path=" + urlEncode(val);
    if(G.share_page !== undefined){
    	url = G.app_host + "share/officeView?user=" + G.user + "&sid=" + G.sid + "&path=" + urlEncode(val);
    }
    if(dataAndEvents !== undefined){
    	url += "&is_edit=1";
    }
    if("page" == G.awdOffice){
    	window.open(url);
    	return;
    }else{
    	core.openDialog(url, core.icon(core.pathExt(val)),htmlEncode(core.pathThis(val)));
    	return;
    }
  };
  var _playerInstance = function(type) {
    if (type) {
      if ("string" == typeof type) {
        type = [type];
      }
      var inst = done("./myPlayer");
      inst.play(type);
    }
  };
  var _play = function(type) {
    var n = ShareData.frameTop();
    n.ui.pathOpen.playerInstance(type);
  };
  return{
    open : _open,
    playerInstance : _playerInstance,
    play : _play,
    openEditor : _openEditor,
    openEditorForce : _openEditorForce,
    openWindow : _openWindow,
    openUnknow : _openUnknow,
    downloadUrl : _downloadUrl,
    download : _download
  };
});
