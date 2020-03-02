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
template('kssform/kss_bary','');/*v:1*/
template('kssform/kss_gzry','');/*v:1*/
template('kssform/kss_jbxx','<div class="form1-column" title="人员信息"> <form id="vui_sample" class="easyui-form" method="post" data-options="novalidate:true"> <div class="form-column1"> <div class="form-column-left"> <input class="easyui-textbox" name="name" style="width:100%" data-options="label:\'申请单号:\',required:true"> </div> </div> <div class="form-column1"> <div class="form-column-left"> <select class="easyui-combobox" name="state" data-options="label:\'状态:\',required:true" labelPosition="top" style="width:100%;"> <option value="AL">新增</option> <option value="AK">已提交</option> <option value="AZ">申请中</option> <option value="AR">已入库</option> </select> </div> </div> <div class="form-column1"> <div class="form-column-left"> <input class="easyui-textbox" name="wznumber" style="width:100%" data-options="label:\'物料编号:\',required:true"> </div> </div> <div class="form-column1"> <div class="form-column-left"> <select class="easyui-combobox" name="state" data-options="label:\'物料类型:\',required:true" labelPosition="top" style="width:100%;"> <option value="AL">原材料</option> <option value="AK">辅料</option> <option value="AZ">产品</option> <option value="AR">深加工原料</option> </select> </div> </div> <div class="form-column1"> <div class="form-column-left"> <input class="easyui-textbox" name="sqkf" style="width:100%" data-options="label:\'申请库房:\',required:true"> </div> </div> <div class="form-column1"> <div class="form-column-left"> <input class="easyui-textbox" name="sqkf" style="width:100%" data-options="label:\'单位:\',required:true"> </div> </div> <div class="form-column1"> <div class="form-column-left"> <input class="easyui-textbox" name="message" style="width:100%;height:60px" data-options="label:\'备注:\',multiline:true"> </div> </div> <div class="form-btnBar pl75"> <input type="submit" name="" value="保存" class="easyui-linkbutton btnPrimary" onclick="submitForm()" style="width:80px" /> <input type="submit" name="" value="重置" class="easyui-linkbutton btnDefault" onclick="clearForm()" style="width:80px" /> </div> </form> </div>');/*v:1*/
template('kssform/kss_lsxx','');/*v:1*/
template('kssform/kss_mjxx','');

}()