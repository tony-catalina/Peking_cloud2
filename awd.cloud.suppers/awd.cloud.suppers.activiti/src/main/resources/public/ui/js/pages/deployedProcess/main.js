define("/ui/js/pages/deployedProcess/main", [
        "/ui/js/plugins/easyui/jquery-easyui-1.5.1/jquery.easyui.min",
        "/ui/js/plugins/easyui/jquery-easyui-1.5.1/easyloader",
        "/ui/js/plugins/artTemplate/artTemplate",
        "/ui/js/common/tools",
        "/ui/js/common/componts",
        "/ui/js/pages/deployedProcess/form",
    ],
    function (require) {
        var comonts = require("/ui/js/common/componts");
        var tools = require("/ui/js/common/tools");
        var deployedProcessForm = require("/ui/js/pages/deployedProcess/form");
        var tableID = "deployedProcessID";
        var data_right = {};
        var columns = [
            {
                field: 'ooid', title: '', width: '', formatter: function (value, rowData, rowIndex) {
                    return '<input type="radio" name="ryRadio" id="ryRadio' + rowIndex + '" value="' + rowData.ooid + '" />'
                }
            },
            {
                field: 'id', title: '流程ID', width: 200, sortable: 'true', formatter: function (value, row, index) {
                    return '<span title=' + (row.id == null ? "" : row.id) + '>' + (row.id == null ? "" : row.id) + '</span>'
                }
            },
            {
                field: 'deploymentId',
                title: '部署ID',
                width: 200,
                sortable: 'true',
                formatter: function (value, row, index) {
                    return '<span title=' + (row.deploymentId == null ? "未部署" : row.deploymentId) + '>' + (row.deploymentId == null ? "未部署" : row.deploymentId) + '</span>'
                }
            },
            {
                field: 'name', title: '流程名称', width: 200, sortable: 'true', formatter: function (value, row, index) {
                    return '<span title=' + (row.name == null ? "" : row.name) + '>' + (row.name == null ? "" : row.name) + '</span>'
                }
            },
            {
                field: 'key', title: '流程KEY', width: 150, sortable: 'true', formatter: function (value, row, index) {
                    return '<span title=' + (row.key == null ? "" : row.key) + '>' + (row.key == null ? "" : row.key) + '</span>'
                }
            },
            {
                field: 'version', title: '版本号', width: 100, sortable: 'true', formatter: function (value, row, index) {
                    return '<span title=' + (row.version == null ? "" : row.version) + '>' + (row.version == null ? "" : row.version) + '</span>'
                }
            },
            {
                field: 'resourceName',
                title: 'XML资源名称',
                width: 100,
                sortable: 'true',
                formatter: function (value, row, index) {
                    return '<span title=' + (row.resourceName == null ? "" : row.resourceName) + '>' + (row.resourceName == null ? "" : row.resourceName) + '</span>'
                }
            },
            {
                field: 'diagramResourceName',
                title: '图片资源名称',
                width: 100,
                sortable: 'true',
                formatter: function (value, row, index) {
                    return '<span title=' + (row.diagramResourceName == null ? "" : row.diagramResourceName) + '>' + (row.diagramResourceName == null ? "" : row.diagramResourceName) + '</span>'
                }
            }
        ];
        var selectid;

        var initTable = function () {
            comonts.initTable(tableID, {
                title: "模块管理",
                url: "/deployment/process-list",
                toolbar: '#toolbar',
                firstLoad: true,
                //双击
                onDblClickRow: function (rowIndex, rowData) {
                    if (rowData.rybh != undefined) {
                        $("input[name='ryRadio']")[rowIndex].checked = true;
                        $("#" + tableID).datagrid("selectRow", rowIndex);
                        var jbxxS = $("#" + tableID).datagrid('getSelected');
                        rcfxpgdialog.show(jbxxS, {}, '新增日常风险评估', refreshTable);
                    }
                },
                //单击
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
                //选中
                onSelect: function (rowIndex, rowData) {
                    var jbxx = $('#' + tableID).datagrid('getSelected');
                    var flag = $("input[name='ryRadio']")[rowIndex].checked;

                },
                rightmenu: function () {
                    //kss.initJbxxRightMenu(jbxxrightmenuid);
                },
                //右击
                onRowContextMenu: function (e, rowIndex, rowData) {
                    data_right = rowData.id;

                    $('#mm').menu('show', {left: e.pageX, top: e.pageY});

                    e.preventDefault();
                },
                columns: columns
            });
            var opts = $('#' + tableID).datagrid("options");//点击查询加载数据
            opts.url = '/deployment/process-list';
            hh = ($(window).height());
        }


        //日常风险评估的查询功能
        var query = function () {
            var key = $("#key").textbox('getValue');
            var lc_name = $("#lc_name").textbox('getValue');
            $('#' + tableID).datagrid('load', {"key": key, "name": lc_name});

        }

        var initToolAction = function () {


            //回车事件
            $(window).keydown(function (event) {
                if (event.keyCode == 13) {
                    fingJbxx();
                }
            });
            //点击“查询”按钮事件
            $("#query_deployedProcess").on("click", function () {
                query();
            });

            $("#deploy_deployedProcess").on("click", function () {
                deployedProcessForm.show();
            });

            $("#delete_deployedProcess").on("click", function () {
                var data = $('#' + tableID).datagrid('getSelected');
                if (data) {
                    $.messager.confirm('警告⚠️', '确定要删除所选中的记录吗？该操作<div color ="red">不可逆！</div>', function (r) {
                        if (r) {
                            var key = data.key;
                            if (data) {
                                $.ajax({
                                    type: "GET",
                                    url: "/workflow/deleteProcess",
                                    data: {'processDefinitionKey': key},
                                }).success(function (m) {
                                    //成功提交
                                    var code = m.map['code'];
                                    if (code == '1') {
                                        $.messager.alert('提示', '删除成功', 'info');
                                        query();
                                    } else {
                                        $.messager.alert('提示', '删除失败', 'info');
                                    }

                                }).fail(function (jqXHR, textStatus, errorThrown) {
                                    $.messager.alert('提示', '删除失败', 'info');
                                    //错误信息
                                });
                            } else {
                                $.messager.alert('提示', '请选择要部署的数据!', 'info');

                            }
                        }
                    })
                } else {
                    $.messager.alert('提示', '请选择要删除的数据!', 'info');
                }
            });

            $("#cache_deployedProcess").on("click", function () {
                var data = $('#' + tableID).datagrid('getSelected');
                deployedProcessForm.show();
            });

            $("#queryMore").on("click", function () {
                var data = $('#' + tableID).datagrid('getSelected');
                addTab("执行中流程", "/taskProcess?processId=" + data_right);
                console.log(data_right);
            });

            //查看节点列表
            $("#queryNode").on("click", function () {
                var data = $('#' + tableID).datagrid('getSelected');
                addTab(data.name + "流程节点信息", "/flowElement?processId=" + data_right);
                console.log(data_right);
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
            console.log("00");

        }
        initView();

    });