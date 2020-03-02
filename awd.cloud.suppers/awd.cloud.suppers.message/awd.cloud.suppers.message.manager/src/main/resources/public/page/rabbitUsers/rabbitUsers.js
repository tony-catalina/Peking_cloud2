layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tableID = "rabbitUsersTable",
        elemTableID = "rabbitUsers";

    //消息列表
    var tableIns = table.render({
        elem: '#'+elemTableID,
        url: '/msg_rabbitUsers',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        even:true,
        limit: 10,
        limits: [10, 15, 20, 50],
        id: tableID,
        cols: [[
        	{type: "numbers", fixed: "left", width: 30},
        	{type: "checkbox", fixed: "left", width: 30},
            {field: 'id', title: 'appid', align: "center",hide:true},
            {field: 'appname', title: '应用名称', align: 'center'},
            {field: 'username', title: '用户名', align: 'center'},
            {field: 'password', title: '密码', align: 'center'},
            {title: '操作', width: 230, templet: '#rabbitUserListBar', fixed: "right", align: "center"}
        ]]
        , response: {
            statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
        }
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.status, //解析接口状态
                "msg": "成功", //解析提示文本
                "count": res.result.total, //解析数据长度
                "data": res.result.data //解析数据列表
            };
        }
    });
    
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
    	table.reload(tableID, {
    		page: {
    			curr: 1 //重新从第 1 页开始
    		},
    		where: {
    			appname: $(".searchVal").val()  //搜索的关键字
    		}
    	})
    });


    function update(obj) {
    	var data = obj.data;
    	var appbindingdata = data.username+"$"+data.password+"$"+data.queuename;
    	var index = layui.layer.open({
    		title: "消息类型",
    		type: 2,
    		maxmin: true,
    		area: ['500px', '500px'],
    		content: "/rabbitUsers/updateRabbitUser.html",
    		zIndex: layer.zIndex, //重点1
    		success: function (layero,index) {
    			//layer.setTop(layero); //重点2
    			var id = layui.layer.getChildFrame("input[name='id']", index);
    			id.val(data.id);
    			var username = layui.layer.getChildFrame("input[name='username']", index);
    			username.val(data.username);
    			var password = layui.layer.getChildFrame("input[name='password']", index);
    			password.val(data.password);
    			var queuename = layui.layer.getChildFrame("input[id='queuename']", index);
    			queuename.val(data.queuename);
    		}
    	})
    	//layui.layer.full(index);
    	$(window).on("resize", function () {
    		layui.layer.full(index);
    	})
    }
    
    
    function del(obj) {
    	var data = obj.data;
        // layer.confirm("确认删除应用 "+data.appname+" 的消息队列吗？", function (index) {
        layer.confirm("确认删除用户 "+data.username+" 吗？", function (index) {
            $.ajax({
            	url: "/msg_rabbitUsers/deleteUser",
                type: "delete",
                dataType: "json",
                data:JSON.stringify(data),
                contentType:"application/json",
                success: function (data) {
                    if (data.status == 200) {
                        layer.msg("删除成功");
                    } else if (data.status == 500) {
                        layer.msg("数据已经被删除，或参数错误");
                    }
                    obj.del();
                    layer.close(index);
                },
                error: function (data) {
                    $.messager.alert('错误', data.msg);
                }
            });
            //向服务端发送删除指令
        });
    }

    $(".addUserAndQueue_btn").click(function () {
    	var index = layui.layer.open({
    		title: "添加用户与消息队列",
    		type: 2,
    		maxmin: true,
    		area: ['500px', '550px'],
    		content: "/rabbitUsers/addUserAndQueue.html",
    		zIndex: layer.zIndex, //重点1
    		success: function (layero,index1) {
    			layui.layer.setTop(layero); //重点2
    		}
    	})
    	//layui.layer.full(index);
    	//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    	$(window).on("resize", function () {
    		layui.layer.full(index);
    	})
    })
    
    //绑定
    $(".bindingMsgtype_btn").click(function () {
    	var index = layui.layer.open({
    		title: "添加用户",
    		type: 2,
    		maxmin: true,
    		area: ['500px', '450px'],
    		content: "/rabbitUsers/addRabbitUser.html",
    		zIndex: layer.zIndex, //重点1
    		success: function (layero,index1) {
    			layui.layer.setTop(layero); //重点2
    		}
    	})
    	//layui.layer.full(index);
    	//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    	$(window).on("resize", function () {
    		layui.layer.full(index);
    	})
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus(tableID),
            data = checkStatus.data,
            userList = [];
        if (data.length > 0) {
            for (var i in data) {
            	//userList.push(data[i]);
            	userList.push({
            		id:data[i].id,
            		username:data[i].username
            	});
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	console.info(userList)
            	$.ajax({
	            	url: "/msg_rabbitUsers/deleteByList",
	                type: "delete",
	                dataType: "json",
	                data:JSON.stringify(userList),
	                contentType:"application/json",
	                success: function (data) {
	                    if (data.status == 200) {
	                        layer.msg("删除成功");
	                    } else if (data.status == 500) {
	                        layer.msg("数据已经被删除，或参数错误");
	                    }
	                    //obj.del();
	                    //layer.close(index);
	                    tableIns.reload();
	                    layer.close(index);
	                },
	                error: function (data) {
	                    $.messager.alert('错误', data.msg);
	                }
	            });
            })
        } else {
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event;
        if (layEvent === 'update') {
        	update(obj);
        }else if (layEvent === 'del') {
        	del(obj);
		}
    });

})