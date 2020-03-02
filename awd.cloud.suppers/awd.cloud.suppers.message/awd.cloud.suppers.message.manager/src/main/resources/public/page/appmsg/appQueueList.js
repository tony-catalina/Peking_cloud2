layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tableID = "appqueuesTable",
        elemTableID = "appqueues";

    //消息列表
    var tableIns = table.render({
        elem: '#appqueues',
        url: '/msg_rabbitQueues',
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
            {field: 'id', title: 'id', align: "center",hide:true},
            {field: 'appname', title: '应用名称', align: 'center'},
            {field: 'queuename', title: '队列名', align: 'center'},
            {field: 'autodelete', title: '是否自动删除', align: 'center',
            	templet: function(d){
            		if (d.autodelete == 1) {
						return "是";
					}else {
						return "否";
					}
            	}
            },
            {field: 'istemp', title: '是否临时', align: 'center',
            	templet: function(d){
            		if (d.istemp == 1) {
            			return "是";
            		}else {
            			return "否";
            		}
            	}
            },
            {title: '操作', width: 230, templet: '#appListBar', fixed: "right", align: "center"}
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


    function addbind(data) {
    	var bindingdata = data.vhost+"$"+data.queuename;
    	var index = layui.layer.open({
    		title: " 消息类型",
    		type: 2,
    		maxmin: true,
    		area: ['800px', '550px'],
    		content: "/subscript/messageType.html?bindingdata=" + bindingdata,
    		zIndex: layer.zIndex, //重点1
    		success: function (layero) {
    			//layer.setTop(layero); //重点2
    		}
    	})
    	//layui.layer.full(index);
    	$(window).on("resize", function () {
    		layui.layer.full(index);
    	})
    }
    
    function look(data) {
        var bindingdata = data.vhost+"$"+data.queuename;
        var index = layui.layer.open({
            title: data.appname + " 订阅的消息",
            type: 2,
            maxmin: true,
            area: ['800px', '550px'],
            content: "/subscript/messagebinding.html?bindingdata=" + bindingdata,
            zIndex: layer.zIndex, //重点1
            success: function (layero) {
                //layer.setTop(layero); //重点2
            }
        })
        //layui.layer.full(index);
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }
    
    function del(obj) {
    	var data = obj.data;
        layer.confirm("确认删除应用 "+data.appname+" 的消息队列吗？", function (index) {
            $.ajax({
            	url: "/msg_rabbitQueues/deleteQueueEntity",
                type: "post",
                dataType: "json",
                data:{
                	'id':data.id,
                	'vhost':data.vhost,
                	'queuename':data.queuename
                },
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

    $(".addAppQueue_btn").click(function () {
    	var index = layui.layer.open({
    		title: "添加应用队列",
    		type: 2,
    		maxmin: true,
    		area: ['500px', '450px'],
    		content: "/appmsg/addAppQueue.html",
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
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的队列？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        } else {
            layer.msg("请选择需要删除的队列");
        }
    })

    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'addbind') {
        	addbind(data);
        }else if (layEvent === 'look') {
        	look(data);
        }else if (layEvent === 'del') {
        	del(obj);
		}
    });

})