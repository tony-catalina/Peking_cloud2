define("/ui/js/pages/deployedProcess/form", [
        '/ui/js/pages/deployedProcess/form.html.js',
        "/ui/js/common/componts",
    ],
    function (require) {
        var comonts = require("/ui/js/common/componts");
        var dialog;
        var jbxxid = "jbxxId";
        var _show = function (_jbxx, _data, _title, refresh) {
            console.info('sss');
            var tmp = require("/ui/js/pages/deployedProcess/form.html");
            var htmlcompile = template.compile(tmp);
            var html = htmlcompile(
                {
                    jbxx: _jbxx,
                    data: _data,
                    title: _title,
                });
            $("#dlg").dialog('destroy')
            $("#cache").html(html);
            $.parser.parse($("#cache").parent());

            $('#dlg').window('open');

            bindAction(refresh);
        }

        function saveBqjl(jsbh) {
            comonts.mask();
            $.ajax({
                type: "get",
                url: "/activiti/updateFlowAndsetProcessNumCache",
                contentType: 'application/json',
                data: {'jsbh': jsbh},
                success: function (result) {
                    comonts.unmask();
                    if (result == "success") {
                        $.messager.alert("提示", '初始化成功！', "info");
                        $("#dlg").dialog("close");
                    } else {
                        $.messager.alert("提示", result, "info");
                        return;
                    }
                },
                error: function () {
                    comonts.unmask();
                    $.messager.alert("提示", '初始化失败！', "info");
                }
            });
        }

        var bindAction = function (refresh) {
            $("#deployedProcessSave").on("click", function () {
                var jsbh = $("#jsbhfm").textbox('getValue');
                if (jsbh == undefined || jsbh == "" || jsbh == null) {
                    saveBqjl("");

                } else {
                    saveBqjl(jsbh);
                }
            });

            $("#deployedProcessClose").on("click", function () {
                $("#dlg").dialog("close");
            });
        }
        return {
            show: _show,
        }
    });