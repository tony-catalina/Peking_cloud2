/*TMODJS:{"version":"1.0.0"}*/
!function () {

    function template (filename, content) {
        return (
            /string|function/.test(typeof content)
            ? compile : renderFile
        )(filename, content);
    };


    var cache = template.cache = {};
    var String = this.String;

    function toString (value, type) {

        if (typeof value !== 'string') {

            type = typeof value;
            if (type === 'number') {
                value += '';
            } else if (type === 'function') {
                value = toString(value.call(value));
            } else {
                value = '';
            }
        }

        return value;

    };


    var escapeMap = {
        "<": "&#60;",
        ">": "&#62;",
        '"': "&#34;",
        "'": "&#39;",
        "&": "&#38;"
    };


    function escapeFn (s) {
        return escapeMap[s];
    }


    function escapeHTML (content) {
        return toString(content)
        .replace(/&(?![\w#]+;)|[<>"']/g, escapeFn);
    };


    var isArray = Array.isArray || function(obj) {
        return ({}).toString.call(obj) === '[object Array]';
    };


    function each (data, callback) {
        if (isArray(data)) {
            for (var i = 0, len = data.length; i < len; i++) {
                callback.call(data, data[i], i, data);
            }
        } else {
            for (i in data) {
                callback.call(data, data[i], i);
            }
        }
    };


    function resolve (from, to) {
        var DOUBLE_DOT_RE = /(\/)[^/]+\1\.\.\1/;
        var dirname = ('./' + from).replace(/[^/]+$/, "");
        var filename = dirname + to;
        filename = filename.replace(/\/\.\//g, "/");
        while (filename.match(DOUBLE_DOT_RE)) {
            filename = filename.replace(DOUBLE_DOT_RE, "/");
        }
        return filename;
    };


    var utils = template.utils = {

        $helpers: {},

        $include: function (filename, data, from) {
            filename = resolve(from, filename);
            return renderFile(filename, data);
        },

        $string: toString,

        $escape: escapeHTML,

        $each: each
        
    };


    var helpers = template.helpers = utils.$helpers;


    function renderFile (filename, data) {
        var fn = template.get(filename) || showDebugInfo({
            filename: filename,
            name: 'Render Error',
            message: 'Template not found'
        });
        return data ? fn(data) : fn; 
    };


    function compile (filename, fn) {

        if (typeof fn === 'string') {
            var string = fn;
            fn = function () {
                return new String(string);
            };
        }

        var render = cache[filename] = function (data) {
            try {
                return new fn(data, filename) + '';
            } catch (e) {
                return showDebugInfo(e)();
            }
        };

        render.prototype = fn.prototype = utils;
        render.toString = function () {
            return fn + '';
        };

        return render;
    };


    function showDebugInfo (e) {

        var type = "{Template Error}";
        var message = e.stack || '';

        if (message) {
            // 利用报错堆栈信息
            message = message.split('\n').slice(0,2).join('\n');
        } else {
            // 调试版本，直接给出模板语句行
            for (var name in e) {
                message += "<" + name + ">\n" + e[name] + "\n\n";
            }  
        }

        return function () {
            if (typeof console === "object") {
                console.error(type + "\n\n" + message);
            }
            return type;
        };
    };


    template.get = function (filename) {
        return cache[filename.replace(/^\.\//, '')];
    };


    template.helper = function (name, helper) {
        helpers[name] = helper;
    };


    if (typeof define === 'function') {define(function() {return template;});} else if (typeof exports !== 'undefined') {module.exports = template;} else {this.template = template;}
    
    /*v:1*/
template('jsdt/gsrs',function($data,$filename
) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,all=$data.all,kss=$data.kss,jls=$data.jls,jds=$data.jds,jhyl=$data.jhyl,qzyl=$data.qzyl,$out='';$out+='<div class="col-md-2 col-sm-12 col-xs-12"> <div class="board"> <div class="panel panel-primary"> <h3 class="panel-title" style="background-color:#00A2E9;">今日总入所数</h3> <div class="number"> <h3 ><a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;all&quot;}">';
$out+=$escape(all.zs);
$out+='</a></h3> </div> <div id="budget"></div> <div class="indicateNum"> <div class="rows"> 男性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;all&quot;,&quot;xb&quot;:&quot;1&quot;}">';
$out+=$escape(all.nan);
$out+='</a>人 </div> <div class="rows line"> 女性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;all&quot;,&quot;xb&quot;:&quot;2&quot;}">';
$out+=$escape(all.nv);
$out+='</a>人 </div> </div> </div> </div> </div> <div class="col-md-2 col-sm-12 col-xs-12"> <div class="board"> <div class="panel panel-primary"> <h3 class="panel-title" style="background-color:#DD4810;">看守所新收数</h3> <div class="number"> <h3><a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;kss&quot;}">';
$out+=$escape(kss.zs);
$out+='</a></h3> </div> <div id="fixation"></div> <div class="indicateNum"> <div class="rows"> 男性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;kss&quot;,&quot;xb&quot;:&quot;1&quot;}">';
$out+=$escape(kss.nan);
$out+='</a>人 </div> <div class="rows line"> 女性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;kss&quot;,&quot;xb&quot;:&quot;2&quot;}">';
$out+=$escape(kss.nv);
$out+='</a>人 </div> </div> </div> </div> </div> <div class="col-md-2 col-sm-12 col-xs-12"> <div class="board"> <div class="panel panel-primary"> <h3 class="panel-title" style="background-color:#D972E3;">拘留所新收所</h3> <div class="number"> <h3><a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jsl&quot;}">';
$out+=$escape(jls.zs);
$out+='</a></h3> </div> <div id="fixation"></div> <div class="indicateNum"> <div class="rows"> 男性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jls&quot;,&quot;xb&quot;:&quot;1&quot;}">';
$out+=$escape(jls.nan);
$out+='</a>人 </div> <div class="rows line"> 女性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jls&quot;,&quot;xb&quot;:&quot;2&quot;}">';
$out+=$escape(jls.nv);
$out+='</a>人 </div> </div> </div> </div> </div> <div class="col-md-2 col-sm-12 col-xs-12"> <div class="board"> <div class="panel panel-primary"> <h3 class="panel-title" style="background-color:#FFA300;">戒毒所新收数</h3> <div class="number"> <h3><a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jds&quot;}">';
$out+=$escape(jds.zs);
$out+='</a></h3> </div> <div id="fixation"></div> <div class="indicateNum"> <div class="rows"> 男性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jds&quot;,&quot;xb&quot;:&quot;1&quot;}">';
$out+=$escape(jds.nan);
$out+='</a>人 </div> <div class="rows line"> 女性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jds&quot;,&quot;xb&quot;:&quot;2&quot;}">';
$out+=$escape(jds.nv);
$out+='</a>人 </div> </div> </div> </div> </div> <div class="col-md-2 col-sm-12 col-xs-12"> <div class="board"> <div class="panel panel-primary"> <h3 class="panel-title" style="background-color:#6656DD;">监护医疗新收数</h3> <div class="number"> <h3><a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jhyl&quot;}">';
$out+=$escape(jhyl.zs);
$out+='</a></h3> </div> <div id="fixation"></div> <div class="indicateNum"> <div class="rows"> 男性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jhyl&quot;,&quot;xb&quot;:&quot;1&quot;}">';
$out+=$escape(jhyl.nan);
$out+='</a>人 </div> <div class="rows line"> 女性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;jhyl&quot;,&quot;xb&quot;:&quot;2&quot;}">';
$out+=$escape(jhyl.nv);
$out+='</a>人 </div> </div> </div> </div> </div> <div class="col-md-2 col-sm-12 col-xs-12"> <div class="board"> <div class="panel panel-primary"> <h3 class="panel-title" style="background-color:#56BD4E;">强制医疗新收数</h3> <div class="number"> <h3><a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;qzyl&quot;}">';
$out+=$escape(qzyl.zs);
$out+='</a></h3> </div> <div id="fixation"></div> <div class="indicateNum"> <div class="rows"> 男性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;qzyl&quot;,&quot;xb&quot;:&quot;1&quot;}">';
$out+=$escape(qzyl.nan);
$out+='</a>人 </div> <div class="rows line"> 女性<br /> <a href="#" class="jsdtlist" url="/mock/test" param="{&quot;jslx&quot;:&quot;qzyl&quot;,&quot;xb&quot;:&quot;2&quot;}">';
$out+=$escape(qzyl.nv);
$out+='</a>人 </div> </div> </div> </div> </div>';
return new String($out);
});

}()