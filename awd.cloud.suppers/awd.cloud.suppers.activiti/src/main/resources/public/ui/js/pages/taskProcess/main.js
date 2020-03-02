define("/ui/js/pages/taskProcess/main", [
        "/ui/js/plugins/easyui/jquery-easyui-1.5.1/jquery.easyui.min",
        "/ui/js/plugins/easyui/jquery-easyui-1.5.1/easyloader",
        "/ui/js/plugins/artTemplate/artTemplate",
        "/ui/js/common/tools",
        "/ui/js/common/componts",
        "/ui/js/pages/taskProcess/form",
    ],
    function (require) {
        var comonts = require("/ui/js/common/componts");
        var tools = require("/ui/js/common/tools");
        var rcfxpgdialog = require("/ui/js/pages/taskProcess/form");
        var tableID = "taskProcessID";
        var columns = [
            {
                field: 'ooid', title: '', width: '', formatter: function (value, rowData, rowIndex) {
                    return '<input type="radio" name="ryRadio" id="ryRadio' + rowIndex + '" value="' + rowData.ooid + '" />'
                }
            },
            {
                field: 'rybh', title: '人员编号', width: 100, sortable: 'true', formatter: function (value, row, index) {
                    return '<span title=' + (row.rybh == null ? "" : row.rybh) + '>' + (row.rybh == null ? "" : row.rybh) + '</span>'
                }
            },
            {
                field: 'processInstanceId',
                title: '实例ID',
                width: 100,
                sortable: 'true',
                formatter: function (value, row, index) {
                    return '<span title=' + (row.processInstanceId == null ? "" : row.processInstanceId) + '>' + (row.processInstanceId == null ? "" : row.processInstanceId) + '</span>'
                }
            },
            {
                field: 'processDefinitionId',
                title: '流程ID',
                width: 200,
                sortable: 'true',
                formatter: function (value, row, index) {
                    return '<span title=' + (row.id == null ? "" : row.id) + '>' + (row.processDefinitionId == null ? "" : row.processDefinitionId) + '</span>'
                }
            },
            {
                field: 'taskDefinitionKey',
                title: '任务KEY',
                width: 200,
                sortable: 'true',
                formatter: function (value, row, index) {
                    return '<span title=' + (row.taskDefinitionKey == null ? "" : row.taskDefinitionKey) + '>' + (row.taskDefinitionKey == null ? "" : row.taskDefinitionKey) + '</span>'
                }
            },
            {
                field: 'name', title: '任务名称', width: 200, sortable: 'true', formatter: function (value, row, index) {
                    return '<span title=' + (row.name == null ? "" : row.name) + '>' + (row.name == null ? "" : row.name) + '</span>'
                }
            },
            {
                field: 'taskId', title: '任务ID', width: 150, sortable: 'true', formatter: function (value, row, index) {
                    return '<span title=' + (row.taskId == null ? "" : row.taskId) + '>' + (row.taskId == null ? "" : row.taskId) + '</span>'
                }
            },
        ];
        var selectid;

        var initTable = function () {
            comonts.initTable(tableID, {
                title: "运行中实例",
                url: "/workflow/findTaskList",
                method: "get",
                firstLoad: true,
                toolbar: "#toolbar_yxzsl",
                onDblClickRow: function (rowIndex, rowData) {
                    if (rowData.rybh != undefined) {
                        $("input[name='ryRadio']")[rowIndex].checked = true;
                        $("#" + tableID).datagrid("selectRow", rowIndex);
                        var jbxxS = $("#" + tableID).datagrid('getSelected');
                        rcfxpgdialog.show(jbxxS, {}, '新增', refreshTable);
                    }
                },
                onClickRow: function (rowIndex, rowData) {
                    if (selectid != rowData.id) {
                        $("input[name='ryRadio']")[rowIndex].checked = true;
                        selectid = rowData.id;
                    } else {
                        $("#" + tableID).datagrid("unselectRow", rowIndex);
                        $("input[name='ryRadio']")[rowIndex].checked = false;
                        $('#' + tableID).datagrid('clearSelections');
                        selectid = "";
                    }
                },
                onSelect: function (rowIndex, rowData) {
                    var jbxx = $('#' + tableID).datagrid('getSelected');
                    var flag = $("input[name='ryRadio']")[rowIndex].checked;

                },
                rightmenu: function () {
                    //kss.initJbxxRightMenu(jbxxrightmenuid);
                },
                columns: columns
            });
            var opts = $('#' + tableID).datagrid("options");//点击查询加载数据
            opts.url = '/workflow/findTaskList';
            hh = ($(window).height());
        }


        var query = function () {
            $('#' + tableID).datagrid('clearSelections');
            var data = $("#wwww").combogrid("getValues");
            console.info(data);

            $('#' + tableID).datagrid('load', {"key": data[0]});
        }

        var initToolAction = function () {


            //回车事件
            $(window).keydown(function (event) {
                if (event.keyCode == 13) {
                    fingJbxx();
                }
            });
            //点击“查询”按钮事件
            $("#query_taskProcess").on("click", function () {
                query();
            });
            //导出到excel
            $("#report_taskProcess").on("click", function () {
                $.messager.alert("提示", '导出到excel！');
            });
            //新增
            $("#add_taskProcess").on("click", function () {
                // addTab("新增流程实例","/models/0");
                $.messager.alert("提示", '已禁用！');

                //rcfxpgdialog.show({},{},'新增',refreshTable);

            });
            //修改
            $("#edit_taskProcess").on("click", function () {
                var row = $('#' + tableID).datagrid('getSelected');
                if (row) {
                    var id = row.id;
                    //  addTab(row.name+"修改","/models/"+id);
                    $.messager.alert('提示', '已禁用!', 'info');
                } else {
                    $.messager.alert('提示', '请选择要修改的记录!', 'info');
                }
            });
            $("#deploy_taskProcess").on("click", function () {
                var data = $('#' + tableID).datagrid('getSelected');
                var id = data.id;//JSON.stringify();
                console.log(id);
                if (data.deploymentId != null) {
                    $.messager.alert('提示', '该流程已部署!', 'info');
                    return;
                }
                if (data) {
                    $.ajax({
                        type: "POST",
                        url: "/models/" + id + "/deployment",
                        data: {},
                        mimeType: "multipart/form-data",
                        contentType: false,
                        cache: false,
                        taskProcessData: false
                    }).success(function () {
                        //成功提交
                        alert("保存成功");
                        query();
                    }).fail(function (jqXHR, textStatus, errorThrown) {
                        alert("保存失败");
                        //错误信息
                    });
                } else {
                    $.messager.alert('提示', '请选择要部署的数据!', 'info');

                }
            });
            //删除
            $("#delete_taskProcess").on("click", function () {
                var jbxxS = $('#' + tableID).datagrid('getSelected');
                if (jbxxS) {
                    $.messager.confirm('提示', '确定要删除所选中的记录吗？', function (r) {
                        $.messager.alert('提示', '成功!', 'info');
                    })
                } else {
                    $.messager.alert('提示', '请选择要删除的数据!', 'info');
                }
            });

        }

        //添加tab
        var addTab = function (title, url) {
            var jq = top.jQuery;
            if (jq("#tt").tabs('exists', title)) {
                jq("#tt").tabs('select', title);
            } else {
                var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                jq("#tt").tabs('add', {
                    title: title,
                    content: content,
                    closable: true
                });
            }
        }
        var initView = function () {
            initTable();
            initToolAction();
            comonts.initSelectPage(null, query);

        }
        initView();

    });