define("app/src/message/main", [
	"lib/jquery-lib",
	"lib/util", 
	"lib/ztree/ztree",
	"lib/contextMenu/jquery-contextMenu",
	"lib/artDialog/jquery-artDialog",
	"lib/picasa/picasa",
	"../../common/taskTap",
	"../../common/core", 
	"../../common/rightMenu",
	"./ui", 
	"./fileContent",
	"../../common/tree",
	"../../common/pathOperate",
	"../../common/pathOpen", 
	"../../common/myPlayer", 
	"./path",
	"./fileLight", 
	"./fileSelect", 
	"./fileListResize", 
	"./headerAddress",
	//"app/src/message/ym/xxfsForm",
	//"app/src/message/ym/xjgwForm",
],
function(e) {
	var jsbhTreeId,jsbhTree;
	var xxfsForm=e("app/src/message/ym/xxfsForm");
	var xjgwForm=e("app/src/message/ym/xjgwForm");
    var deles = new Map();
    var csdwId;
    var counts;
    var isparent
    function wdxxCount() {
        $.ajax({
            url:"getUnreadMessage?state&kssj&jssj",
            type:"GET",
            data: {page:0, limit:0},
            success:function(data){
                var num=0;
                if(data.data.length != 0 )
                {
                    for(var i=0; i<data.data.length; i++){
                        if (data.data[i].state=="R2"){
                            $(".wdxx").show()
                            num++;
                        }
                    }
                    counts = num;
                    $(".wdxx").text(""+counts);
                    if (num==0){
                        $(".wdxx").css("display","none");
                    }
                }
            }
        })
    }
    Config = {
    		BodyContent: ".bodymain",
            FileBoxSelector: ".bodymain .fileContiner",
            FileBoxClass: ".bodymain .fileContiner .file",
            FileBoxClassName: "file",
            FileBoxTittleClass: ".bodymain .fileContiner .title",
            SelectClass: ".bodymain .fileContiner .file.select",
            SelectClassName: "select",
            TypeFolderClass: "folderBox",
            TypeFileClass: "fileBox",
            HoverClassName: "hover",
            TreeId: "folderList",
            pageApp: "explorer",
            treeAjaxURL: "explorer/treeList?app=explorer",
            AnimateTime: 200
    },
    e("lib/jquery-lib"),
    e("lib/util"),
    e("lib/ztree/ztree"),
    e("lib/contextMenu/jquery-contextMenu"),
    e("lib/artDialog/jquery-artDialog"),
    e("lib/picasa/picasa"),
    TaskTap = e("../../common/taskTap"),
    core = e("../../common/core"),
    rightMenu = e("../../common/rightMenu"),
    ui = e("./ui"),
    ui.tree = e("../../common/tree"),
    ui.path = e("./path"),
    ui.fileLight = e("./fileLight"),
    ui.fileSelect = e("./fileSelect"),
    ui.fileListResize = e("./fileListResize"),
    ui.headerAddress = e("./headerAddress"),

    $(document).ready(function() {
        wdxxCount();

        $("#wread").css("display","inline");
        $("#yread").css("display","inline");
        $(".sj").css("display","inline-block");
        $("#querys").css("display","inline-block");
        $("#dele").css("display","inline-block");
        $("#allread").css("display","inline");
        $("#putApp").css("display","inline");
        $("#delete").css("display","inline");
        $("#unread").addClass('layui-this');
         var op=null;
         var GwType = "";
         core.init();
         $(".init_loading").fadeOut(450).addClass("pop_fadeout")
        layui.use('table', function(){
            var table = layui.table;
            // table.render({
            //     elem: '#tab'
            //     ,url:'getUnreadMessage?state=R2'
            //     ,method:'GET'
            //     ,cellMinWidth: 60
            //     ,response: {
            //         statusName: 'code' //规定数据状态的字段名称，默认：code
            //         ,dataName: 'data' //规定数据列表的字段名称，默认：data
            //         ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
            //         ,countName: 'total' //规定数据总数的字段名称，默认：count
            //     }
            //     ,limit: 13
            //     ,limits:[13,26,39,52,65,78,91]
            //     ,cols: [[
            //         {checkbox:true}
            //         ,{field:'id', title: 'ID', align: 'center',type:'numbers'}
            //         ,{field:'fsrxm' ,width:'10%',title: '发送人', sort:true}
            //         ,{field:'xxjbString',width:'7%',title: '消息级别', sort: true}
            //         ,{field:'fssj',width:'14%',title: '发送时间',sort:true}
            //         ,{field:'fsnrString',width:'40%',title: '消息内容', sort:true}
            //         ,{field:'jsrxm',title: '接收人', sort: true}
            //     ]]
            //     ,page: {
            //         layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
            //         //,curr: 5 //设定初始在第 5 页
            //         ,groups: 1 //只显示 1 个连续页码
            //         ,first: false //不显示首页
            //         ,last: false //不显示尾页
            //     }
            // });
            table.render({
                elem: '#tab'
                ,height:635
                ,url:'getUnreadMessage?state&kssj&jssj'
                ,method:'GET'
                ,cellMinWidth: 60
                ,response: {
                statusName: 'code' //规定数据状态的字段名称，默认：code
                    ,dataName: 'data' //规定数据列表的字段名称，默认：data
                    ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    ,countName: 'total' //规定数据总数的字段名称，默认：count
            }
        ,limit: 13
                ,limits:[13,26,39,52,65,78,91]
                ,cols: [[
                {checkbox:true}
                ,{field:'id', title: '序号',align: 'center',type:'numbers',width:'10%'}
                ,{field:'fsrxm' ,width:'10%',title: '发送人'}
                ,{field:'xxjbString',width:'10%',title: '消息级别'}
                ,{field:'fssj',width:'14%',title: '发送时间'}
                ,{field:'fsnrString',width:'52%',title: '消息内容',event:'xxnr'}
                // ,{field:'jsrxm',title: '接收人', sort: true}
            ]]
                ,page: {
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                    //,curr: 5 //设定初始在第 5 页
                    , groups: 1 //只显示 1 个连续页码
                    , first: false //不显示首页
                    , last: false //不显示尾页
            },
            done: function (res, curr, count) {// 表格渲染完成之后的回调
                var that = this.elem.next();
                res.data.forEach(function (item, index) {
                    //console.log(item.empName);item表示每列显示的数据
                    that.find(".layui-table-box thead tr th[data-field='0'] div div").css("display","none");
                    var trnr = that.find(".layui-table-box tbody tr[data-index='" + index + "'] td[data-field='fsnrString']");
                    that.find(".layui-table-box tbody tr[data-index='" + index + "'] td").css("font-size","12px");
                    that.find(".layui-table-box tbody tr[data-index='" + index + "'] td").css("font-family","\"Helvetica Neue\", \"Helvetica\", \"Microsoft Yahei\", \"微软雅黑\",\n" +
                        "\t\"Lantinghei SC\", \"STXihei\", \"WenQuanYi Micro Hei\", Arial, sans-serif")
                    that.find(".layui-table-box thead tr th").css("font-size","12px");
                    that.find(".layui-table-box thead tr th").css("font-family","\"Helvetica Neue\", \"Helvetica\", \"Microsoft Yahei\", \"微软雅黑\",\n" +
                        "\t\"Lantinghei SC\", \"STXihei\", \"WenQuanYi Micro Hei\", Arial, sans-serif")
                    //鼠标悬停变成小手
                    that.find(".layui-table-box tbody tr[data-index='" + index + "'] td[data-field='fsnrString']").css("cursor","pointer");
                    trnr.mouseover(function (){
                        trnr.css("text-decoration","underline");
                    })
                    trnr.mouseout(function (){
                        trnr.css("text-decoration","none");
                    })
                    if (item.state == "R2"){
                        that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("font-weight","bold");
                        that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("color","#000");
                        var id = item.id;
                        trnr.click(function () {
                            $.ajax({
                                url:"updateMessageState?id="+id+"&dele=0",
                                type:"GET",
                                dataType: "json",
                                async:false
                            })
                        })
                    }
                    if (item.state == "R3"){
                        that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("color","#888888");
                    }
                });
            }
        });
            $("#allread").click(function(){
                $.ajax({
                    url:"updateAllMsgByUser",
                    type:"GET",
                    data:{},
                    success:function(data){
                        layer.msg("全部已读",{
                            time: 5000,
                            btn : [ '确定']}
                            ,function(){
                                wdxxCount();
                                window.location.reload();
                            });

                    },
                    error:function(err){
                        layer.msg("阅读失败，请检查网络状况");
                    }
                })
            })

            //监听表格复选框选择

            var table_data = new Array();
            var ids = new Array();
            var typechecked = 0;
            var num = 0;
            table.on('checkbox(demo)', function(obj){
                if(obj.checked==true){
                    //checked=true的data.id
                    deles.set(obj.data.id,++num);
                    if(obj.type=='one'){
                        ids.push(obj.data.id);
                    }else if(obj.type =='two'){
                        for(var i=0;i<table_data.length;i++){
                            ids.push(table_data[i].id);
                        }
                    }
                }else{
                    var id= obj.data.id;
                    deles.delete(id);
                    if(obj.type=='one'){
                        for(var i=0;i<ids.length;i++){
                            if(ids[i]==obj.data.id){
                                ids.pop();
                                ids.splice(i,1);
                            }
                        }
                    }else{
                        for(var i=0;i<ids.length;i++){
                            for(var j=0;j<table_data.length;j++){
                                if(ids[i]==table_data[j].id){
                                    ids.pop();
                                    ids.splice(i,1);
                                }
                            }
                        }
                    }
                }
                if(obj.type == 'all'){
                    if(typechecked == 0){
                        ids = [];
                        for(var i = 0 ; i < table.cache.tab.length ; i++){
                            ids.push(table.cache.tab[i]);
                        }
                        typechecked = 1;
                    }else if(typechecked == 1){
                        ids = [];
                        typechecked = 0;
                    }
                }
                 window.personid = ids;
            });

            //监听工具条
            table.on('tool(demo)', function(obj){
                var data = obj.data;
                var xxnr = data.fsnrString;
                if(obj.event === 'xxnr'){
                    if(xxnr != null ){
                        layer.open({
                            type:1
                            ,area:['50%','50%']
                            ,title:'消息内容'
                            ,content:xxnr,
                            cancel: function sa(index, layero){
                                layer.close(index)
                                if (data.state == "R2"){
                                    readState("="+"","="+"","="+"");
                                    stas = 0;
                                    wdxxCount();
                                }
                            }
                        })
                    }
                }
            });

            $('.demoTable .layui-btn').on('click', function(){
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });
        });

        layui.use(['form','laydate','layedit','upload'], function(){
            var form = layui.form,layer = layui.layer;
            var abc =uuid(20,16);
            form.render();

            /**信息中心-新建消息-时间选择方法**/
                var laydate = layui.laydate;

                //执行一个laydate实例
                laydate.render({
                    elem: '#timedata', //指定元素
                    value:new Date()
                });

                laydate.render({
                    elem:'#gwtimedata', //指定元素
                    value:new Date()
                })

            /**信息中心-新建消息-消息内容富文本方法**/
                var layedit = layui.layedit;
                var index = layedit.build('Richtxt',{
                    tool:[
                        'strong'     /**加粗**/
                        ,'italic'     /**斜线**/
                        ,'underline'  /**下划线**/
                        ,'del'        /**删除线**/
                        ,'|'          /**分割线**/
                        ,'left'       /**左对齐**/
                        ,'center'     /**居中对齐**/
                        ,'right'      /**右对齐**/
                    ]
                    ,height:170
                }); //建立编辑器
            form.verify({     /**富文本内容异步赋值给content属性**/
                content: function(value){
                    layedit.sync(index);
                }
            });

            /**新建消息重置按钮监听**/
            $(document).on('click','#reset',function(){
            	//关闭所有ztree节点
            	 var treeObj = $.fn.zTree.getZTreeObj("zTree");
            	 $("#receiptPerson1").val("")
	            $("#receiptPerson").val("")
                 treeObj.expandAll();
            	$(".layui-form input[type=checkbox]").attr('checked',false);
                $('#LAY_layedit_1').contents().find('body').html('');/**清空重置富文本内容**/
            });

            /**新建消息表单图片上传**/
            var uploadfile = layui.upload;
            var imgfile;
            var upfile = uploadfile.render({
                elem:'#imgfile'
                ,url:'document/saveFile'
                ,method:'POST'
                ,auto:false
                ,multiple:true
                ,accept:'images,file'
                ,exts:'jpg|jpeg|png|pdf|word|xls|doc'
                ,number:0
                ,data:{
                    uuid:abc
                }
                ,bindAction: '#formDemo'
                ,choose:function(obj){
                    var files = this.files = obj.pushFile();
                    imgFiles = files;
                    obj.preview(function(index, file, result){
                        var name = file.name;
                        var suffix = name.split(".")[name.split(",").length];
                        if(suffix == "doc" ){//src="../../static/images/common/doc.png"
                            $('#file2').append('<img src="../../../../images/common/doc.png" alt="'+ file.name +'" width="50" height="55" class="layui-upload-img"> ')
                        }else {
                            $('#file2').append('<img src="' + result + '" alt="' + file.name + '" width="160" height="80" class="layui-upload-img"> ')
                        }
                    })
                }
                ,allDone:function(obj){

                }
            })

            /**监听新建消息提交*/
            form.on('submit(formDemo)', function(data){
                    $.ajax({
                        type : "Post",
                        url : "kss_process/send",    //请求发送到
                        data: {"msg":JSON.stringify(data.field)},
                        dataType : "json",        //返回数据形式为json
                        success : function(result) {
                            layer.msg("发送成功", {
                                time: 5000, //5s后自动关闭
                                btn: [ '确定']
                            },function(){
                                window.location.reload();
                            });
                        },
                        error : function(errorMsg) {
                            layer.msg("发送失败，请检查网络状况",{
                                time:5000,
                                btn:['确定']
                            },function(){
                                window.location.reload();
                            });
                        }
                    });
                return false;
            });

            /**监听新建公文提交**/
            form.on('submit(OfficialSubmit)', function(data){/**"file":JSON.stringify(data.field),**/
                var TimeString = new Date().Format("hh:mm:ss");
                var gwRichtxt = $("#gwRichtxt").val();
                var imglist=$("#demo2 img");
        		if(gwRichtxt==""){
        			layer.alert('主题不能为空',{
        				icon:3,
        				skin:'layer-ext-moon'
        			});
        		}else if(imglist.length<=0){
        			layer.alert('附件不能为空',{
        				icon:3,
        				skin:'layer-ext-moon'
        			});
        		}else{
        			var flag=$("#hfbj").is(':checked');
                    if(flag){
                    	console.log(flag);
                    	$.ajax({
                            type : "Post",
                            url : "document/saveDocument",    //请求发送到
                            data: {"title":data.field.title,
                                   "theme":data.field.gwcontent,
                                   "zsdw":data.field.zsdw,
                                   "csdw":data.field.csdw,
                                   "cbdw":data.field.cbdw,
                                   "qfr":data.field.qfr,
                                   "qfrq":data.field.qfrq + " "+ TimeString,
                                   "wjzh":data.field.wjzh,
                                   "bmjb":data.field.bmjb,
                                   "hj":data.field.hj,
                                   "ngr":data.field.ngr,
                                   "ngdw":data.field.ngdw,
                                   "hfbj":"1",
                                   "id":"",
                                   "uuid":abc
                            },
                            dataType : "json",        //返回数据形式为json
                            success : function(result) {
                                // alert("发送完成！！！");
                                layer.msg("新建成功", {
                                    time: 5000, //5s后自动关闭
                                    btn: [ '确定'],
                                },function(){
                                    window.location.reload();
                                });
                            },
                            error : function(errorMsg) {
                            }
                        });
                    }else{
                    	console.log(flag);
                    	$.ajax({
                            type : "Post",
                            url : "document/saveDocument",    //请求发送到
                            data: {"title":data.field.title,
                                   "theme":data.field.gwcontent,
                                   "zsdw":data.field.zsdw,
                                   "csdw":data.field.csdw,
                                   "cbdw":data.field.cbdw,
                                   "qfr":data.field.qfr,
                                   "qfrq":data.field.qfrq + " "+ TimeString,
                                   "wjzh":data.field.wjzh,
                                   "bmjb":data.field.bmjb,
                                   "hj":data.field.hj,
                                   "ngr":data.field.ngr,
                                   "ngdw":data.field.ngdw,
                                   "hfbj":"0",
                                   "id":"",
                                   "uuid":abc
                            },
                            dataType : "json",        //返回数据形式为json
                            success : function(result) {
                                // alert("发送完成！！！");
                                layer.msg("新建成功", {
                                    time: 5000, //5s后自动关闭
                                    btn: [ '确定'],
                                },function(){
                                    window.location.reload();
                                });
                            },
                            error : function(errorMsg) {
                            }
                        });
                    }
        		}
                
                return false;
            });

            /**新建公文重置按钮监听**/
            $(document).on('click','#gwreset',function(){
                $('#LAY_layedit_2').contents().find('body').html('');/**清空重置富文本内容**/
                $("#demo2").empty();
                for(var key in imgFiles){
                    delete imgFiles[key];
                }
            });

            /**新建公文表单图片上传**/
            var upload = layui.upload;
            var imgFiles;
            var uploadInst = upload.render({
                elem:'#imgup'
                ,url:'document/saveFile'
                ,method:'POST'
                ,auto:false
                ,multiple:true
                ,accept:'images,file'
                ,exts:'jpg|jpeg|png|pdf|word|xls|doc'
                ,number:0
                ,data:{
                    uuid:abc
                }
                ,bindAction: '#OfficialSubmit'
                ,choose:function(obj){
                    var files = this.files = obj.pushFile();
                    imgFiles = files;
                    obj.preview(function(index, file, result){
                        var name = file.name;
                        var suffix = name.split(".")[name.split(",").length];
                        if(suffix == "doc" ){//src="../../static/images/common/doc.png"
                            $('#demo2').append('<img src="../../../../images/common/doc.png" alt="'+ file.name +'" width="50" height="55" class="layui-upload-img"> ')
                        }else {
                            $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" width="160" height="80" class="layui-upload-img"> ')
                        }
                    })
                }
                ,allDone:function(obj){

                }
            })


            var gwindex = layedit.build('gwRichtxt',{
                tool:[
                    'strong'     /**加粗**/
                    ,'italic'     /**斜线**/
                    ,'underline'  /**下划线**/
                    ,'del'        /**删除线**/
                    ,'|'          /**分割线**/
                    ,'left'       /**左对齐**/
                    ,'center'     /**居中对齐**/
                    ,'right'      /**右对齐**/
                ]
                ,height:150
            }); //建立编辑器
            form.verify({     /**富文本内容异步赋值给content属性**/
                gwcontent: function(value){
                    layedit.sync(gwindex);
                }
            });
        });
    })

    // $("#unread").click(function() {
    //     /**点击未读处理方法**/
    //     $("#gwhf").hide();
    //     $("#read").removeClass('layui-this');
    //     $("#send").removeClass('layui-this');
    //     $("#unread").addClass('layui-this');
    //     $(".layui-badge-dot").remove();
    //     $(this).addClass('layui-this').siblings().removeClass('layui-this');
    //     $("#allread").css("display","inline");
    //     $("#delete").css("display","inline");
    //     $("#newxjgw").css("display","none");
    //     $("#putApp").css("display","none");
    //     var op = null;
    //     core.init()
    //     $(".init_loading").fadeOut(450).addClass("pop_fadeout")
    //     layui.use('table', function () {
    //         var table = layui.table;
    //         table.render({
    //             elem: '#tab'
    //             ,height:635
    //             ,url:'getUnreadMessage?state=R2'
    //             ,method:'GET'
    //             ,cellMinWidth: 60
    //             ,response: {
    //                 statusName: 'code' //规定数据状态的字段名称，默认：code
    //                 ,dataName: 'data' //规定数据列表的字段名称，默认：data
    //                 ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
    //                 ,countName: 'total' //规定数据总数的字段名称，默认：count
    //             }
    //             ,limit: 13
    //             ,limits:[13,26,39,52,65,78,91]
    //             ,cols: [[
    //                 {checkbox:true}
    //                 ,{field:'id', title: 'ID', align: 'center',type:'numbers'}
    //                 ,{field:'fsrxm' ,width:'10%',title: '发送人', sort:true}
    //                 ,{field:'xxjbString',width:'7%',title: '消息级别', sort: true}
    //                 ,{field:'fssj',width:'14%',title: '发送时间',sort:true}
    //                 ,{field:'fsnrString',width:'30%',title: '消息内容',event:'urxxnr', sort:true}
    //                 ,{field:'jsrxm',title: '接收人', sort: true}
    //             ]]
    //             , page: {
    //                 layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
    //                 //,curr: 5 //设定初始在第 5 页
    //                 , groups: 1 //只显示 1 个连续页码
    //                 , first: false //不显示首页
    //                 , last: false //不显示尾页
    //             }
    //         });
    //         table.on('tool(demo)',function(obj){
    //             var data = obj.data;
    //             var xxnr = data.fsnrString;
    //             if(obj.event === 'xxnr'){
    //                 if(xxnr != null ){
    //                     layer.open({
    //                         type:1
    //                         ,area:['50%','50%']
    //                         ,title:'消息内容'
    //                         ,content:xxnr
    //                     })
    //                 }
    //             }
    //         })
    //     });
    // });
        var stas = 0;
        $("#yread").click(function () {
            readState("="+"","="+"","="+"");
            stas = 0;
            $(".layui-table").css('height','85%');
    		$(".layui-table-view").css('height','100%')
    		$('.layui-table-box').css('height','85%');
        })
        $("#wread").click(function () {
            readState("=R2","="+"","="+"");
            stas = 1;
            $(".layui-table").css('height','85%');
    		$(".layui-table-view").css('height','100%')
    		$('.layui-table-box').css('height','85%');
        })
        $("#read").click(function () {
            readState("="+"","="+"","="+"");
            stas = 0;
            $('#demoTable').find(".layui-table-box thead tr th[data-field='0']").css("width","48px");
        })
        $("#dele").click(function () {
            if (deles.size>0){
                deles.forEach(function (value,key,deles) {
                    $.ajax({
                        url: "updateMessageState?id=" + key + "&dele=1",
                        type: "GET",
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            Tips.tips("删除成功！", "success");
                        }
                    })
                })
                readState("=" + "", "=" + "", "=" + "");
                deles = new Map();
            } else{
                Tips.tips("请选择要删除的数据！", "error");
            }

        })
        $("#querys").click(function () {
            var kssj = $("#kssj").val();
            var jssj = $("#jssj").val();
            if (kssj!="" && jssj!=""&&kssj>jssj){
                Tips.tips("起始时间不能大于终止时间！", "error");
            }else if (kssj=="" && jssj!=""){
                Tips.tips("请选择起始时间", "error");
            }else if (kssj!="" && jssj==""){
                Tips.tips("请选择终止时间", "error");
            } else{
                if (stas == 0){
                    readState("="+"","="+kssj,"="+jssj)
                }
                if (stas == 1){
                    readState("=R2","="+kssj,"="+jssj);
                }
            }

        })
        function readState(sta,ks,js) {
        /**点击消息方法**/
        $("#gwhf").hide();
        $("#send").removeClass('layui-this');
        $("#unread").removeClass('layui-this');
        $("#yread").css("display","inline");
        $("#wread").css("display","inline");
        $(".sj").css("display","inline-block");
        $("#querys").css("display","inline-block");
        $(".gw_searchs").css("display","none")
        $("#gw_querys").css("display","none")
        $("#dele").css("display","inline-block");
        $("#read").addClass('layui-this');
        $(this).addClass('layui-this').siblings().removeClass('layui-this');
        $("#newxjgw").css("display","none");
        $("#putApp").css("display","inline");
        $("#allread").css("display","inline");
        $("#delete").css("display","inline");
        var op = null;
        core.init()
        $(".init_loading").fadeOut(450).addClass("pop_fadeout")
        layui.use('table', function () {
            var table = layui.table;
            table.render({
                elem: '#tab'
                ,url:'getUnreadMessage?state'+sta+'&kssj'+ks+'&jssj'+js
                ,method:'GET'
                ,cellMinWidth: 60
                ,response: {
                    statusName: 'code' //规定数据状态的字段名称，默认：code
                    ,dataName: 'data' //规定数据列表的字段名称，默认：data
                    ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    ,countName: 'total' //规定数据总数的字段名称，默认：count
                }
                ,limit: 13
                ,limits:[13,26,39,52,65,78,91]
                ,cols: [[
                    {checkbox:true}
                    ,{field:'id', title: '序号',align: 'center',type:'numbers',width:'10%'}
                    ,{field:'fsrxm' ,width:'10%',title: '发送人'}
                    ,{field:'xxjbString',width:'10%',title: '消息级别'}
                    ,{field:'fssj',width:'14%',title: '发送时间'}
                    ,{field:'fsnrString',width:'53%',title: '消息内容',event:'xxnr'}
                    // ,{field:'jsrxm',title: '接收人', sort: true}
                ]]
                ,page: {
                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                    //,curr: 5 //设定初始在第 5 页
                    , groups: 1 //只显示 1 个连续页码
                    , first: false //不显示首页
                    , last: false //不显示尾页
                },
                done: function (res, curr, count) {// 表格渲染完成之后的回调
                    var that = this.elem.next();
                    res.data.forEach(function (item, index) {
                        //console.log(item.empName);item表示每列显示的数据
                        that.find(".layui-table-box thead tr th[data-field='0'] div div").css("display","none");
                        var trnr = that.find(".layui-table-box tbody tr[data-index='" + index + "'] td[data-field='fsnrString']");
                        that.find(".layui-table-box tbody tr[data-index='" + index + "'] td").css("font-size","12px");
                        that.find(".layui-table-box tbody tr[data-index='" + index + "'] td").css("font-family","\"Helvetica Neue\", \"Helvetica\", \"Microsoft Yahei\", \"微软雅黑\",\n" +
                            "\t\"Lantinghei SC\", \"STXihei\", \"WenQuanYi Micro Hei\", Arial, sans-serif")
                        that.find(".layui-table-box thead tr th").css("font-size","12px");
                        that.find(".layui-table-box thead tr th").css("font-family","\"Helvetica Neue\", \"Helvetica\", \"Microsoft Yahei\", \"微软雅黑\",\n" +
                            "\t\"Lantinghei SC\", \"STXihei\", \"WenQuanYi Micro Hei\", Arial, sans-serif")
                        //鼠标悬停变成小手
                        that.find(".layui-table-box tbody tr[data-index='" + index + "'] td[data-field='fsnrString']").css("cursor","pointer");
                        trnr.mouseover(function (){
                            trnr.css("text-decoration","underline");
                        })
                        trnr.mouseout(function (){
                            trnr.css("text-decoration","none");
                        })
                        if (item.state == "R2"){
                            that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("font-weight","bold");
                            that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("color","#000");
                            var id = item.id;
                            trnr.click(function () {
                                $.ajax({
                                    url:"updateMessageState?id="+id+"&dele=0",
                                    type:"GET",
                                    dataType: "json",
                                    async:false
                                })
                            })
                        }
                        if (item.state == "R3"){
                            that.find(".layui-table-box tbody tr[data-index='" + index + "']").css("color","#888888");
                        }
                    });
                }
            });
            table.on('tool(demo)',function(obj){
                var data = obj.data;
                var xxnr = data.fsnrString;
                if(obj.event === 'xxnr'){
                    if(xxnr != null ){
                        layer.open({
                            type:1
                            ,area:['50%','50%']
                            ,title:'消息内容'
                            ,content:xxnr,
                            cancel: function sa(index, layero){
                                layer.close(index)
                                if (data.state == "R2"){
                                    readState("="+"","="+"","="+"");
                                    stas = 0;
                                    wdxxCount()
                                }
                            }
                        })
                    }
                }
            })
        });
    };


    $("#send").click(function() {
        /**点击公文发送方法**/
        $("#read").removeClass('layui-this');
        $("#unread").removeClass('layui-this');
        $("#yread").css("display","none");
        $("#wread").css("display","none");
        $("#send").addClass('layui-this');
        $(".sj").css("display","none");
        $(".gw_searchs").css("display","inline-block")
        $("#gw_querys").css("display","inline-block");
        $("#dele").css("display","none");
        $("#allread").css("display","none");
        $("#putApp").css("display","none");
        $("#delete").css("display","none");
        $("#allread").css("display","none");
        $("#newxjgw").css("display","inline");
        var op = null;
        core.init();
        $(".init_loading").fadeOut(450).addClass("pop_fadeout");
        if(Type == 1 || Type == 2 || Type == 3 || Type == 4){
        	var url = "document/getSjDocument?title=";
        	refreshSjDoc(url);
        }else if(Type == 0 || Type == 8 || Type == 9){
        	var url = "document/getZdDocument?title=&csdw=";
        	refreshZdDoc(url);
        }
    });
    $("#gw_querys").click(function () {
        var title = $("#searchs").val()
        if(Type == 1 || Type == 2 || Type == 3 || Type == 4){
            var url = "document/getSjDocument?title="+title;
            refreshSjDoc(url);
        }else if(Type == 0 || Type == 8 || Type == 9){
            if (csdwId == "" || csdwId == undefined||isparent) {
                var url = "document/getZdDocument?title="+title+"&csdw=";
                refreshZdwDoc(url);
            }else if (csdwId != ""){
                var url = "document/getZdDocument?title="+title+"&csdw="+csdwId;
                refreshZdwDoc(url);
            }
        }
    })
    var refreshSjDoc = function(url){
    	$("#newxjgw").hide();
        layui.use('table', function () {
            var table = layui.table;
            table.render({
                elem: '#tab'
                ,url: url+"&id="
                , method: 'GET'
                , response: {
                    statusName: 'code' //规定数据状态的字段名称，默认：code
                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    , countName: 'total' //规定数据总数的字段名称，默认：count
                }
                ,height:635
                ,limit: 13
                ,limits:[13,26,39,52,65,78,91]
                ,cellMinWidth: 150
                , cols: [[
                    //,{field:'id',  title: 'ID', width:200, sort: true, align: 'center'}
                    //{field:'',width:'5%',event:"gwhf",templet:'<div><input type="radio" name="typeuuid" value=""></div>'}
                      {field: '',width:'5%',event:"detail",templet:'<div><i class="layui-icon layui-icon-add-1" style="color:#00C5CD"></i></div>',align:'center'}
                    , {field: 'title', width:'25%',event:"gwzt", title: '标题', sort: true, align: 'center',templet:'<div><a>{{d.documentEntity.title}}</a></div>'}
//                    , {field: 'theme', width: '23%',title: '主题', sort: true, align: 'center',templet:'<div>{{d.documentEntity.theme}}</div>'}
//                    , {field: 'qfr', width: '8%', title: '签发人', sort: true, align: 'center',templet:'<div>{{d.documentEntity.qfr}}</div>'}
                    , {field: 'qfrq', width: '20%', title: '签发日期', sort: true, align: 'center',templet:'<div>{{d.documentEntity.qfrq}}</div>'}
                    , {field: 'cbdwString', width: '20%', title: '呈报单位', sort: true, align: 'center',templet:'<div>{{d.documentEntity.cbdwString}}</div>'}
//                    , {field: 'wjzh', width: '8%', title: '文件字号', sort: true, align: 'center',templet:'<div>{{d.documentEntity.wjzh}}</div>'}
                    , {field: 'bmjbString', width: '10%', title: '保密级别', sort: true, align: 'center',templet:'<div>{{d.documentEntity.bmjbString}}</div>'}
                    , {field: 'hjString', width: '10%', title: '缓急', sort: true, align: 'center',templet:'<div>{{d.documentEntity.hjString}}</div>'}
                    , {field: '', width: '10%', title: '附件',align: 'center',templet:function(d){
                    	if((d.hfbj === '1' || d.flag === '0') && G.lx === "true"){
                    		return '<div><button class="layui-btn  layui-btn-sm" lay-event="setSign">查看</button><span>&nbsp;&nbsp;</span><button class="layui-btn  layui-btn-sm" lay-event="gwhf">回复</button></div>'
                    	}else{
                    		return '<div><button class="layui-btn  layui-btn-sm" lay-event="setSign">查看</div>'
                    	}}}
                    ]]
                , page: {
                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                    //,curr: 5 //设定初始在第 5 页
                    , groups: 1 //只显示 1 个连续页码
                    , first: false //不显示首页
                    , last: false //不显示尾页
                }
            });
            table.on('tool(demo)',function(obj){
                var data = obj.data;
                var gwcontent = data.documentEntity.theme;
                if(obj.event === 'gwzt'){
                   if(gwcontent != null){
                    layer.open({
                        type:1
                       ,area:['50%','50%']
                       ,title:'消息内容'
                       ,content:gwcontent
                    })
                   }
                }
               if(obj.event === 'setSign'){
                    layer.open({
                        type:1
                        ,area:['50%','50%']
                        ,title:'附件'
                        ,content:'<div id="GwSign"></div>'
                        ,success:function(layero,index){
                            $.ajax({
                                url:"document/getFile",
                                type:"GET",
                                dataType: "json",
                                async:false,
                                data:{
                                    "uuid":data.uuid
                                },
                                success:function(data){
                                    for(var i = 0 ; i < data.length ; i++){
                                        url = data[i].filepath;
                                        Num = i;
                                        $("#GwSign").after(
                                            '<div class="layui-input-inline" style="padding-right:2%">' +
                                            ' <div class="layui-item"> ' +
                                            '<div class="layui-inline">' +
                                            '<img src=' +url + ' style="width:160px;height:80px;">' +
                                            '<br>'+
//                                            '<button type="button" class="layui-btn layui-btn-normal" style="margin:3% 0% 4% 34%;" onclick="downLoadimg(event)"  value='+url+'  id="a'+Num+'">下载</button>'+
                                            '</div> ' +
                                            '</div>' +
                                            '</div>'
                                        )};
                                },
                                error:function(err){
                                    layer.msg("请求失败，请检查网络");
                                }
                            })
                        }
                    });
                }
                if(obj.event === 'detail'){
                    layer.open({
                        type:1
                        ,title:'其他'
                        ,area:['70%','25%']
                        ,content:'<table id="GwDetail" lay-filter="GwDetailTable"></table>'
                        ,success:function(layero,index){
                        	table.render({
                                elem:'#GwDetail',
                                url:url,
                                methods:"GET",
                                where:{id : data.id},
                                response: {
                                    statusName: 'code' //规定数据状态的字段名称，默认：code
                                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                                    , countName: 'total' //规定数据总数的字段名称，默认：count
                                },
                                height:100,
                                cellMinWidth: 50,
                                cols:[[
                                	{field: 'theme', width: '65%',title: '主题', sort: true, align: 'center',templet:'<div>{{d.documentEntity.theme}}</div>'}
                                    , {field: 'qfr', width: '18%', title: '签发人', sort: true, align: 'center',templet:'<div>{{d.documentEntity.qfr}}</div>'}
                                    , {field: 'wjzh', width: '17%', title: '文件字号', sort: true, align: 'center',templet:'<div>{{d.documentEntity.wjzh}}</div>'}
                                ]],
                            })
                        }
                        ,error:function(err){
                            layer.msg("打开失败，请检查网络状况");
                        }
                    })
                }
                if(obj.event === 'gwhf'){
                	GwType = data.uuid;
                	var dId = data.id;
                    var NewTime = new Date().Format("yyyy-MM-dd hh:mm:ss");
                    layui.use(['layer','form'],function(){
                    var layer    = layui.layer;
                    var layedit  = layui.layedit;
                    var form     = layui.form;
                    if(typeof(GwType) == "string"){
                    layer.open({
                        type:1,
                        title:"公文回复",
                        area:['70%','70%'],
                        content:'<div class="layui-form" style="margin-left:5%;">' +
                              // '<div class="layui-form-item">' +
                              //    '<label class="layui-form-label">回复时间：</label>' +
                              //    '<div class="layui-input-inline">' +
                              //     '<input class="layui-input">' +
                              //    '</div>'+
                              // '</div>' +
                              '<div class="layui-form-item">' +
                                 '<label class="layui-form-label">回复内容：</label>'+
                                 '<div class="layui-input-inline" style="width:88%">'+
                                 '<textarea id="gwhfcontent"  name="gwhftxt" lay-verify="gwhftxt"></textarea>' +
                                 '</div>'+
                              '</div>'+
                              '<div class="layui-form-item" style="margin-left:32%;margin-top:5%">' +
                                 '<div class="layui-input-block">' +
                                   '<button class="layui-btn layui-btn-normal" id="resets" type="reset">重置</button>'+
                                   '<button class="layui-btn" lay-submit lay-filter="gwhfSubmit" style="margin-left:3%">回复</button>'+
                                 '</div>'+
                              '</div>'+
                            '</div>'
                              })
                    }else if(typeof(GwType) == "undefined"){
                        layer.msg("请选择公文消息");
                    }
                    var gwhfwb = layedit.build('gwhfcontent',{
                        height:200,
                        tool:[
                            'strong' //加粗
                            ,'italic' //斜体
                            ,'underline' //下划线
                            ,'del' //删除线
                            ,'|' //分割线
                            ,'left' //左对齐
                            ,'center' //居中对齐
                            ,'right' //右对齐
                        ]
                    });
                    form.verify({     /**富文本内容异步赋值给content属性**/
                        gwhftxt: function(value){
                            layedit.sync(gwhfwb);
                        }
                    });
                    form.on('submit(gwhfSubmit)', function(data){
                         /**data.field.gwhftxt**/
                        /**GwType**/
                         $.ajax({
                             url:"document/setDocumentDetail",
                             type:"POST",
                             data:{
                                 id: dId,
                                 hfsj: NewTime,
                                 hfnr: data.field.gwhftxt,
                                 flag: "1"			// 是否已回复 1是 0否
                             },
                             success:function(data){
                                 layer.msg("回复成功", {
                                     time: 3000, //5s后自动关闭
                                     btn: [ '确定'],
                                 },function(){
                                     window.location.reload();
                                 });
                             },
                             error:function(err){
                                 layer.msg("回复失败，请检查网络状况");
                             }
                         })
                    })
                    $(document).on('click','#resets',function(){
                        $('iframe').contents().find('body').html('');/**清空重置富文本内容**/
                    });
                })
                }
            });
        });
    }
    var refreshSwDoc = function(url){
        $("#newxjgw").hide();
        layui.use('table', function () {
            var table = layui.table;
            table.render({
                elem: '#tab'
                ,url: url+"&id="
                , method: 'GET'
                , response: {
                    statusName: 'code' //规定数据状态的字段名称，默认：code
                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    , countName: 'total' //规定数据总数的字段名称，默认：count
                }
                ,height:635
                ,limit: 13
                ,limits:[13,26,39,52,65,78,91]
                ,cellMinWidth: 150
                , cols: [[
                    //,{field:'id',  title: 'ID', width:200, sort: true, align: 'center'}
                    {field:'',width:'5%',event:"gwhf",templet:'<div><input type="radio" name="typeuuid" value=""></div>'}
                    , {field: '',width:'5%',event:"detail",templet:'<div><i class="layui-icon layui-icon-add-1" style="color:#00C5CD"></i></div>',align:'center'}
                    , {field: 'title', width:'20%',event:"gwzt", title: '标题', sort: true, align: 'center',templet:'<div><a>{{d.title}}</a></div>'}
//                    , {field: 'theme', width: '23%',title: '主题', sort: true, align: 'center',templet:'<div>{{d.documentEntity.theme}}</div>'}
//                    , {field: 'qfr', width: '8%', title: '签发人', sort: true, align: 'center',templet:'<div>{{d.documentEntity.qfr}}</div>'}
                    , {field: 'qfrq', width: '20%', title: '签发日期', sort: true, align: 'center',templet:'<div>{{d.qfrq}}</div>'}
                    , {field: 'cbdwString', width: '20%', title: '呈报单位', sort: true, align: 'center',templet:'<div>{{d.cbdw}}</div>'}
//                    , {field: 'wjzh', width: '8%', title: '文件字号', sort: true, align: 'center',templet:'<div>{{d.documentEntity.wjzh}}</div>'}
                    , {field: 'bmjbString', width: '10%', title: '保密级别', sort: true, align: 'center',templet:'<div>{{d.bmjbString}}</div>'}
                    , {field: 'hjString', width: '10%', title: '缓急', sort: true, align: 'center',templet:'<div>{{d.hjString}}</div>'}
                    , {field: '', width: '10%', title: '附件', sort: true,event: 'setSign', align: 'center',templet:'<div><button class="layui-btn  layui-btn-sm">附件查看</button></div>'}
                ]]
                , page: {
                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                    //,curr: 5 //设定初始在第 5 页
                    , groups: 1 //只显示 1 个连续页码
                    , first: false //不显示首页
                    , last: false //不显示尾页
                }
            });
            table.on('tool(demo)',function(obj){
                var data = obj.data;
                var gwcontent = data.theme;

                if(obj.event === 'gwhf'){
                    GwType = data.uuid;
                }

                if(obj.event === 'gwzt'){
                    if(gwcontent != null){
                        layer.open({
                            type:1
                            ,area:['50%','50%']
                            ,title:'消息内容'
                            ,content:gwcontent
                        })
                    }
                }

                if(obj.event === 'setSign'){
                    layer.open({
                        type:1
                        ,area:['50%','50%']
                        ,title:'附件'
                        ,content:'<div id="GwSign"></div>'
                        ,success:function(layero,index){
                            $.ajax({
                                url:"document/getFile",
                                type:"GET",
                                dataType: "json",
                                async:false,
                                data:{
                                    "uuid":data.uuid
                                },
                                success:function(data){
                                    for(var i = 0 ; i < data.length ; i++){
                                        url = data[i].filepath;
                                        Num = i;
                                        $("#GwSign").after(
                                            '<div class="layui-input-inline" style="padding-right:2%">' +
                                            ' <div class="layui-item"> ' +
                                            '<div class="layui-inline">' +
                                            '<img src=' +url + ' style="width:160px;height:80px;">' +
                                            '<br>'+
                                            //                                            '<button type="button" class="layui-btn layui-btn-normal" style="margin:3% 0% 4% 34%;" onclick="downLoadimg(event)"  value='+url+'  id="a'+Num+'">下载</button>'+
                                            '</div> ' +
                                            '</div>' +
                                            '</div>'
                                        )};
                                },
                                error:function(err){
                                    layer.msg("请求失败，请检查网络");
                                }
                            })
                        }
                    });
                }

                if(obj.event === 'detail'){
                    layer.open({
                        type:1
                        ,title:'其他'
                        ,area:['70%','25%']
                        ,content:'<table id="GwDetail" lay-filter="GwDetailTable"></table>'
                        ,success:function(layero,index){
                            table.render({
                                elem:'#GwDetail',
                                url:url,
                                methods:"GET",
                                where:{id : data.id},
                                response: {
                                    statusName: 'code' //规定数据状态的字段名称，默认：code
                                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                                    , countName: 'total' //规定数据总数的字段名称，默认：count
                                },
                                height:100,
                                cellMinWidth: 50,
                                cols:[[
                                    {field: 'theme', width: '65%',title: '主题', sort: true, align: 'center',templet:'<div>{{d.theme}}</div>'}
                                    , {field: 'qfr', width: '18%', title: '签发人', sort: true, align: 'center',templet:'<div>{{d.qfr}}</div>'}
                                    , {field: 'wjzh', width: '17%', title: '文件字号', sort: true, align: 'center',templet:'<div>{{d.wjzh}}</div>'}
                                ]],
                            })
                        }
                        ,error:function(err){
                            layer.msg("打开失败，请检查网络状况");
                        }
                    })
                }
            });
        });
    }
    var refreshZdDoc = function(url){
    	layui.use('table',function(){
            var Zdtable = layui.table;
            Zdtable.render({
                 elem:'#tab',
                 url:url,
                 methods:"GET",
                 response: {
                    statusName: 'code' //规定数据状态的字段名称，默认：code
                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    , countName: 'total' //规定数据总数的字段名称，默认：count
                },
                height:635,
                cellMinWidth: 150,
                limit: 13,
                limits:[13,26,39,52],
                cols: [[
                  {field:'id',width:'5%', title: '序号',align: 'center',type:'numbers'}
                , {field: '',width:'5%',event:"setmsg",templet:'<div><i class="layui-icon layui-icon-add-1" style="color:#00C5CD"></i></div>',align:'center'}
                , {field: 'title',width:'20%', title: '标题',event:"Zdcontent",templet: '<div><a>{{d.title}}</a></div>', sort: true, align: 'center'}
//                , {field: 'theme',width:'25%', title: '内容',sort: true, align: 'center'}
                , {field: 'qfr', width:'10%',title: '签发人', sort: true, align: 'center'}
                , {field: 'qfrq', width:"15%",title: '签发日期', sort: true, align: 'center'}
                , {field: 'flagString', width:"44%",title: '回访状态', sort: true, align: 'center'}
                       ]]
                , page: {
                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                    , groups: 1 //只显示 1 个连续页码
                    , first: false //不显示首页
                    , last: false //不显示尾页
                }
            });
            Zdtable.on('tool(demo)',function(obj){
                var data = obj.data;
                var Zdcontent = data.theme;
                if(obj.event === 'setmsg'){
                    layer.open({
                        type:1
                        ,area:['70%','60%']
                        ,content:'<table id="GwWindow" lay-filter="GwWindowTable"></table>'
                        ,success:function(layero,index){
                            Zdtable.render({
                                elem:'#GwWindow',
                                url:"document/getAllSjDocument",
                                methods:"GET",
                                where:{uuid : data.uuid},
                                response: {
                                    statusName: 'code' //规定数据状态的字段名称，默认：code
                                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                                    , countName: 'total' //规定数据总数的字段名称，默认：count
                                },
                                height:400,
                                cellMinWidth: 50,
                                limit: 7,
                                limits:[7,14,21,28,35],
                                cols:[[
                                    {field: 'hfr', title: '回复人', sort: true, align: 'center'}
                                    ,{field: 'hfrjh', title: '回复人警号', sort: true, align: 'center'}
                                    ,{field: 'hfsj', title: '回复时间', sort: true, align: 'center'}
                                    ,{field: 'hfnr', title: '回复内容', sort: true, align: 'center'}
                                    ,{field: 'flagString', title: '是否已回复', sort: true, align: 'center'}
                                ]],
                                page: {
                                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                                    , groups: 1
                                    , first: false
                                    , last: false
                                }
                            })
                        }
                        ,error:function(err){
                            layer.msg("打开失败，请检查网络状况");
                        }
                    })
                }  /**单个消息窗口 End**/
                if(obj.event === 'Zdcontent'){
                    if(Zdcontent != null){
                        layer.open({
                            type:1
                            ,area:['50%','50%']
                            ,title:'消息内容'
                            ,content:Zdcontent
                        })
                    }
                }
            })
        })
    }
    var refreshZdwDoc = function(url){
        layui.use('table',function(){
            var Zdtable = layui.table;
            Zdtable.render({
                elem:'#tab',
                url:url,
                methods:"GET",
                response: {
                    statusName: 'code' //规定数据状态的字段名称，默认：code
                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    , countName: 'total' //规定数据总数的字段名称，默认：count
                },
                height:635,
                cellMinWidth: 150,
                limit: 13,
                limits:[13,26,39,52],
                cols: [[
                    {field:'id',width:'5%', title: '序号',align: 'center',type:'numbers'}
                    , {field: '',width:'5%',event:"setmsg",templet:'<div><i class="layui-icon layui-icon-add-1" style="color:#00C5CD"></i></div>',align:'center'}
                    , {field: 'title',width:'20%', title: '标题',event:"Zdcontent",templet: '<div><a>{{d.title}}</a></div>', sort: true, align: 'center'}
               // , {field: 'theme',width:'25%', title: '内容',sort: true, align: 'center'}
                    , {field: 'qfr', width:'10%',title: '签发人', sort: true, align: 'center'}
                    , {field: 'qfrq', width:"15%",title: '签发日期', sort: true, align: 'center'}
                    , {field: 'flagString', width:"44%",title: '回访状态', sort: true, align: 'center'}
                ]]
                , page: {
                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                    , groups: 1 //只显示 1 个连续页码
                    , first: false //不显示首页
                    , last: false //不显示尾页
                }
            });
            Zdtable.on('tool(demo)',function(obj){
                var data = obj.data;
                var Zdcontent = data.theme;
                if(obj.event === 'setmsg'){
                    layer.open({
                        type:1
                        ,area:['70%','60%']
                        ,content:'<table id="GwWindow" lay-filter="GwWindowTable"></table>'
                        ,success:function(layero,index){
                            Zdtable.render({
                                elem:'#GwWindow',
                                url:"document/getAllSjDocument",
                                methods:"GET",
                                where:{uuid : data.uuid},
                                response: {
                                    statusName: 'code' //规定数据状态的字段名称，默认：code
                                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                                    , countName: 'total' //规定数据总数的字段名称，默认：count
                                },
                                height:400,
                                cellMinWidth: 50,
                                limit: 7,
                                limits:[7,14,21,28,35],
                                cols:[[
                                    {field: 'hfr', title: '回复人', sort: true, align: 'center'}
                                    ,{field: 'hfrjh', title: '回复人警号', sort: true, align: 'center'}
                                    ,{field: 'hfsj', title: '回复时间', sort: true, align: 'center'}
                                    ,{field: 'hfnr', title: '回复内容', sort: true, align: 'center'}
                                    ,{field: 'flagString', title: '是否已回复', sort: true, align: 'center'}
                                ]],
                                page: {
                                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
                                    , groups: 1
                                    , first: false
                                    , last: false
                                }
                            })
                        }
                        ,error:function(err){
                            layer.msg("打开失败，请检查网络状况");
                        }
                    })
                }  /**单个消息窗口 End**/
                if(obj.event === 'Zdcontent'){
                    if(Zdcontent != null){
                        layer.open({
                            type:1
                            ,area:['50%','50%']
                            ,title:'消息内容'
                            ,content:Zdcontent
                        })
                    }
                }
            })
        })
    }

    var bindAction = function(){

    	
    	//删除公文
    	$("#delete").on("click",function(){
            var id = window.personid;
    		$.ajax({
    			type : "GET",
    			url : "updateMessageState",
                contentType:"applicaiton/json",
                data:{
    			  "id":JSON.stringify(id)
                },
    			success : function(data){
                    layui.use('layer',function(){
                        var layer = layui.layer;
                        layer.open({
                            type:0,
                            title:false,
                            closeBtn:0,
                            skin: 'layui-layer-molv',
                            content:'删除成功',
                            btn:false
                        });
                    })
                    // window.setTimeout(function(){window.location.reload();},2000);
    			},
    			error:function(err){
    			    layui.use('layer',function(){
    			        var layer = layui.layer;
    			        layer.open({
                            type:0,
                            title:false,
                            skin: 'layui-layer-molv',
                            content:'删除失败',
                            btn:false
                        });
                    })
                }
    		})
    	})
    	
    }
    $(document).ready(function(){
    	$("#send").on("click",function(){
    		if(Type == 0 || Type == 8 || Type == 9){
    			$("#demoTable").css('width','84%');
    			$("#gwZtree").css('display','block');
    			$(".gw_searchs").css('margin-left','20px');
    			zTreeInit();
            }else{
                $(".gw_searchs").css('margin-left','0px');
            }
    	})

    	$("#read").on("click",function(){
    		$("#gwZtree").css('display','none');
    		$("#demoTable").css('width','100%');
    		$(".layui-table").css('width','100%');
    		$(".layui-table").css('height','85%');
    		$(".layui-table-view").css('height','100%')
    		$('.layui-table-box').css('height','85%');
    		
    	})
    	bindAction();
    });
    
    
    /**随机生成uuid方法**/
    function uuid(len, radix) {
        var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
        var uuid = [], i;
        radix = radix || chars.length;

        if (len) {
            // Compact form
            for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
        } else {
            // rfc4122, version 4 form
            var r;

            // rfc4122 requires these characters
            uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
            uuid[14] = '4';

            // Fill in random data.  At i==19 set the high bits of clock sequence as
            // per rfc4122, sec. 4.1.5
            for (i = 0; i < 36; i++) {
                if (!uuid[i]) {
                    r = 0 | Math.random()*16;
                    uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                }
            }
        }
        return uuid.join('');
    }

    /**时间生成方法**/
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds() //秒
        };
        if (/(y+)/.test(fmt)){ //根据y的长度来截取年
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o){
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
        return fmt;
    }

    function SelectBMJBBox(){
        $.ajax({
            url:"kss_dictionary/getDictionary",
            type:"POST",
            data:{"node":"BMJB"},
            success:function(data){
                var $html = "";
                if(data != null){
                    for(var i = 0 ; i < data.length; i++){
                        $html += "<option value='"+data[i].code+"'>"+data[i].content+"</option>";
                    }
                };
                $("#bmjb").append($html);
                form.render('select');
            }
        })
    }

    /**权限分配**/
    /**
    * 1 看守所
    * 2 拘留所
    * 3 戒毒所
    * 4 安康医院
    * 0 监管总队
    * 8 厂家
    * 9 系统管理员
    **/
    var Type = $("#UserType").val();
    if(Type == 1 || Type == 2 || Type == 3 || Type == 4){

    }else if(Type == 0 || Type == 8 || Type == 9){

    }
    var treeClick = function(e,treeNode) {
    	var id = treeNode.id;
    	csdwId = id;
    	isparent = treeNode.isParent;
    	jsbhTreeId = id;
    	jsbhTree.expandNode(treeNode,"","",false)
		var a = jsbhTree.getNodeByParam("id", id, null);
		jsbhTree.selectNode(a);
		if(!treeNode.isParent){
			if(Type == 0 || Type == 8 || Type == 9){
	        	var url = "document/getZdDocument?title=&csdw="+id;
	        	refreshZdDoc(url);
	        }
		}
	}
    function filter(treeId, parentNode, childNodes) {
    	if (!childNodes) return null;
    	for (var i=0, l=childNodes.length; i<l; i++) {
    		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    	}
    	return childNodes;
    }
    function zTreeInit(){
	    var zNodes =[
	        { id:1, pId:0, name:"总队",isParent:true,},
	        { id:2, pId:0, name:"看守所",isParent:true},
	        { id:3, pId:0, name:"拘留所",isParent:true},
	        { id:4, pId:0, name:"戒毒所",isParent:true},
	        { id:5, pId:0, name:"安康医院",isParent:true},
	    ];
    	var treeConfig = {
			view: {
				showLine: false,
				selectedMulti: false,
				dblClickExpand: true,
				addDiyDom: function(e, t) {
					var i = 12;
					$("#"+t.tId).css("width","100%")
					var a = $("#" + e + " #" + t.tId + "_switch");
					var n = $("#" + e + " #" + t.tId + "_ico");
					n.before(a).before('<span class="tree_icon button">' + core.iconSmall("groupGuest") + "</span>").remove();
					if ( t.level >= 1) {
						var o = "<span class='space' style='display: inline-block;width:" + i * t.level + "px'></span>";
						a.before(o);
					}
					$("#" + e + " #" + t.tId + "_a").addClass("menuGroup").attr("data_group_id", t.id);
				}
			},
			callback: {
				onClick: function(e, t, i) {
					treeClick(t, i);
				},
				beforeRightClick: function(e, t) {
					treeClick(e, t);
				}
			},
			async: {
				enable: true,
				url:"system_jsbh/getJsTypeByGroups",
				autoParam:["id"],
				dataFilter: filter
			},
		}
    	$.fn.zTree.init($("#jsbhTree"), treeConfig, zNodes);
		jsbhTree = $.fn.zTree.getZTreeObj("jsbhTree");
		jsbhTree.expandAll(false);
		jsbhTreeId='0';
		jsbhTree.selectNode(jsbhTree.getNodeByParam("id", jsbhTreeId, null));
    }
    

});
