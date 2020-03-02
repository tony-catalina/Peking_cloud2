layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tableID = "exchangesTable",
        elemTableID = "exchanges";

    //消息列表
    var tableIns = table.render({
        elem: '#exchanges',
        url: '/getExchanges',
        cellMinWidth: 95,
        height: "full-125",
        page: true,
        even:true,
        limit: 10,
        limits: [10, 15, 20, 50],
        id: tableID,
        cols: [[
        	{type: "numbers", fixed: "left", width: 30},
        	{type: "checkbox", fixed: "left", width: 30},
            {field: 'vhost', title: '虚拟主机', align: "center",width: 100},
            {field: 'name', title: '交换机名', align: 'center',width: 250},
            {field: 'type', title: '交换机类型', align: 'center'},
            {field: 'internal', title: '内部使用', align: 'center',
            	templet: function(d){
            		if (d.internal) {
            			return "是";
            		}else {
            			return "否";
            		}
            	}
            },
            {field: 'auto_delete', title: '自动删除', align: 'center',
            	templet: function(d){
            		if (d.auto_delete) {
						return "是";
					}else {
						return "否";
					}
            	}
            },
            {field: 'durable', title: '是否持久化', align: 'center',
            	templet: function(d){
            		if (d.durable) {
            			return "是";
            		}else {
            			return "否";
            		}
            	}
            },
            {field: 'user_who_performed_action', title: '操作用户', align: 'center'},
            {title: '操作', width: 180, templet: '#exchangeListBar', fixed: "right", align: "center"}
        ]]
        , response: {
            statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
        }
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.result.code, //解析接口状态
                "msg": res.result.msg, //解析提示文本
                "count": res.result.total, //解析数据长度
                "data": res.result.rows //解析数据列表
            };
        }
    });
    
    $('body').ready(function(){
    	setTimeout(function(){
    		$.ajax({
    		    url:"/getVhosts",
    		    type:"GET",
    		    dataType:"json",
    		    async: true,
    		    success:function(data){
    	    		$("#vhost_id").empty();
    	    		$("#vhost_id").append('<option value="">全部虚拟主机</option>');
    		        var ResData = data.result.rows;
    		        for(i = 0 ; i < ResData.length ; i++){
    		            option = "<option value='"+ResData[i].name+"'>"+ResData[i].name+"</option>";
    		            $("#vhost_id").append(option);
    		        };
    		        form.render('select',"vhost_id");
    		    }
    		});
    		//form.render();
    		form.render('select',"vhost_id");
    	},200)
    });
    
    $(".search_btn").on("click", function () {
    	var exchange = $(".searchVal").val();
    	var vhost = $("#vhost_id").val();
    	table.reload(tableID, {
    		page: {
    			curr: 1 //重新从第 1 页开始
    		},
    		where: {
    			name: exchange,
    			vhost:vhost
    		}
    	})
    });

    function look(data) {
    	var bindingdata = data.vhost+"$"+data.name;
        var index = layui.layer.open({
            title: data.name + " 绑定的路由键",
            type: 2,
            maxmin: true,
            area: ['800px', '500px'],
            content: "/exchanges/exchange.html?bindingdata="+bindingdata,
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

    $(".addExchange_btn").click(function () {
    	var index = layui.layer.open({
            title: "添加交换机",
            type: 2,
            maxmin: true,
            area: ['500px', '500px'],
            content: "/exchanges/exchangeadd.html",
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
            layer.confirm('确定删除选中的交换机？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        } else {
            layer.msg("请选择需要删除的交换机");
        }
    })

    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'look') { //编辑
            look(data);
        }else if (layEvent === 'del') {
            layer.confirm("确认删除交换机 "+data.name+" 吗？", function (index) {
                $.ajax({
                    url: "/deleteExchange",
                    type: "post",
                    dataType: "json",
                    data:{
                    	'vhost':data.vhost,
                    	'exchange':data.name
                    },
                    success: function (data) {
                        if (data.result.code == 200) {
                            layer.msg("删除成功");
                            obj.del();
                        } else {
                            layer.msg(data.result.msg);
                        }
                        layer.close(index);
                    },
                    error: function (data) {
                        $.messager.alert('错误', data.msg);
                    }
                });
                //向服务端发送删除指令
            });
		}
    });

})