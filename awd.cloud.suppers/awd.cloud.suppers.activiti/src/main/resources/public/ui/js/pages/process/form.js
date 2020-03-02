define("/ui/js/pages/process/form", [
        '/ui/js/pages/process/form.html.js',
        "/ui/js/common/componts",
    ],
    function (require) {
        var comonts = require("/ui/js/common/componts");
        var dialog;
        var jbxxid = "jbxxId";
        var _show = function (_jbxx, _data, _title, refresh) {
            console.info('sss');
            var tmp = require("/ui/js/pages/process/form.html");
            console.log(tmp);
            var htmlcompile = template.compile(tmp);
            var html = htmlcompile(
                {});
            $("#win").dialog('destroy')
            $("#importXML").html(html);
            $.parser.parse($("#importXML").parent());

            $('#dlg').window('open');

            bindAction(refresh);
        }

        function saveBqjl(data) {
            $.ajax({
                type: "post",
                url: "/bqjl/",
                contentType: 'application/json',
                data: data,
                success: function (result) {
                    if (result.status == '200') {
                        $('#dlg').dialog('close');
                        $.messager.alert("提示", "保存成功", "info");
                        $("#" + bqjlid).datagrid("clearSelections");
                        $('#' + bqjlid).datagrid('reload', {
                            rybh$eq: $('#' + jbxxid).datagrid('getSelected').rybh,
                            state$eq: 'R2',
                        });
                    } else {
                        $.messager.alert("提示", "保存失败", "info");
                    }
                }
            });
        }

        function updateBqjl(data) {
            var bqjl = $("#" + bqjlid).datagrid("getSelected");
            $.ajax({
                type: "put",
                url: "/bqjl/" + bqjl.id,
                contentType: 'application/json',
                data: data,
                success: function (result) {
                    if (result) {
                        $('#dlg').dialog('close');
                        $.messager.alert("提示", "修改成功", "info");
                        $('#' + bqjlid).datagrid('reload', {
                            rybh$eq: $('#' + jbxxid).datagrid('getSelected').rybh,
                            state$eq: 'R2',
                        });
                    } else {
                        $.messager.alert("提示", "修改失败", "info");
                    }
                }
            });
        }

        var bindAction = function (refresh) {

            $("#modelSave").on("click", function () {
                var filePath = $("#file").val();
                if("" != filePath){
                    var fileType = getFileType(filePath);
                    console.log("fileType",fileType);
                    //判断上传的附件是否为图片
                    if("bpmn20.xml"!=fileType){
                        $("#file").val("");
                        $.messager.alert("提示","请上传bpmn20.xml格式的文件", "info");
                    }
                    else{
                        //获取附件大小（单位：KB）
                        var fileSize = document.getElementById("file").files[0].size / 1024;
                        if(fileSize > 2048){
                            $.messager.alert("提示","文件大小不能超过2MB", "info");
                        }
                    }
                }else {
                    $.messager.alert("提示", "请先选则文件", "info");
                }
                var formData = new FormData();
                formData.append('uploadfile', $('#file')[0].files[0]);
                comonts.mask();
                $.ajax({
                    url:"/models/uploadFile",
                    type:"post",
                    data:formData,
                    processData:false,
                    contentType:false,
                    success:function(data){
                        $.messager.alert("提示", "上传成功", "info");
                        comonts.unmask();

                    },
                    error:function(e){
                        $.messager.alert("提示", "请先选则文件", "info");
                        $.easyui.loaded(true);
                        comonts.unmask();

                    }
                });
            });

            $("#modelClose").on("click", function () {
                $("#dlg").dialog("close");
            });

            //表单中的事件

            $("#sfjxgcyes").bind("click", function (event) {
                $("#jxgcDiv").hide();
                $('#wxgcjlrfm').textbox({required: false});
            });

            $("#sfjxgcno").bind("click", function (event) {
                $("#jxgcDiv").show();
                $('#wxgcjlrfm').textbox({required: true});
            });
        }

       var getFileType =  function (filePath){
            var startIndex = filePath.lastIndexOf(".");
            console.log(startIndex);
            if(startIndex != -1){
                var fileType = filePath.substring(startIndex+1, filePath.length).toLowerCase();
                if(filePath.leangth<(fileType.length+7)){
                    return "";
                }
                return filePath.substring(startIndex-6, filePath.length).toLowerCase();
            }
            else return "";
        }
        return {
            show: _show,
        }
    });