layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tableID = "messageTypeTable",
        elemTableID = "messageType";

    //消息列表
    var tableIns = table.render({
        elem: '#'+elemTableID,
        url: '/msg_msgtype',
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
            {field: 'appname', title: '发布应用', align: 'center'},
            {field: 'msgtype', title: '消息类型', align: 'center'},
            {field: 'rolecode', title: '角色代码', align: 'center'},
            {field: 'businessid', title: '消息编号', align: 'center'},
            {field: 'businessname', title: '消息名称', align: 'center'},
            {title: '操作', width: 200, templet: '#messageListBar', fixed: "right", align: "center"}
        ]]
        , response: {
            statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
        }
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.result.total, //解析数据长度
                "data": res.result.data //解析数据列表
            };
        }
    });

    $(".search_btn").on("click", function () {
    	table.reload(tableID, {
    		page: {
    			curr: 1 //重新从第 1 页开始
    		},
    		where: {
    			businessname: $(".searchVal").val()  //搜索的关键字
    		}
    	})
    });


    function look(data) {
        var msgType = data.appid+"_"+data.msgtype+"_"+data.rolecode+"_"+data.businessid;
        var index = layui.layer.open({
            title: "订阅",
            type: 2,
            maxmin: true,
            area: ['800px', '500px'],
            //content: "/messagerule/messagerule.html?msgType=" + msgType,
            content: "/subscript/bindingGroup.html?msgType=" + msgType,
            zIndex: layer.zIndex, //重点1
            success: function (layero) {
                //layer.setTop(layero); //重点2
            }
        })
        //layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }
    
    function update(data) {
    	var index = layui.layer.open({
    		title: "订阅",
    		type: 2,
    		maxmin: true,
    		area: ['650px', '600px'],
    		content: "/message/addmessageType.html",
    		zIndex: layer.zIndex, //重点1
    		success: function (layero,index) {
    			//layer.setTop(layero); //重点2
    			for(var key in data){
    				layui.layer.getChildFrame("input[name='"+key+"']", index).val(data[key]);
    				layui.layer.getChildFrame("textarea[name='"+key+"']", index).val(data[key]);
    			}
    		}
    	})
    	//layui.layer.full(index);
    	//改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    	$(window).on("resize", function () {
    		layui.layer.full(index);
    	})
    }

    function del(obj) {
    	var data = obj.data;
        layer.confirm("确认删除消息 "+data.businessname+" 吗？", function (index) {
            $.ajax({
            	url: "/msg_msgtype/deleteMsgType",
                type: "post",
                dataType: "json",
                data:{
                	'id':data.id,
                	'routingkey':data.routingkey
                },
                //contentType:"application/json",
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

    
    $(".addMsgtype_btn").click(function () {
    	var index = layui.layer.open({
    		title: "添加消息类型",
    		type: 2,
    		maxmin: true,
    		area: ['650px', '600px'],
    		content: "/message/addmessageType.html",
    		zIndex: layer.zIndex, //重点1
    		success: function (layero) {
    			//layer.setTop(layero); //重点2
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
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        } else {
            layer.msg("请选择需要删除的文章");
        }
    })

    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'look') { //编辑
            look(data);
        }else if (layEvent === 'update') {
        	update(data);
        }else if (layEvent === 'del') {
        	del(obj);
		}
    });
    
})