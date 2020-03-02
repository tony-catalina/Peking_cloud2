layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tableID = "queuesTable",
        elemTableID = "queues";

    //消息列表
    var tableIns = table.render({
        elem: '#queues',
        url: '/getQueues',
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
            {field: 'vhost', title: '虚拟主机', align: "center",width: 100},
            {field: 'name', title: '队列名', align: 'center',width: 250},
            {field: 'type', title: '消息类型', align: 'center'},
            {field: 'state', title: '队列状态', align: 'center'},
            {field: 'consumers', title: '消费者', align: 'center'},
            {field: 'ready', title: '消息数', align: 'center'},
            {field: 'unacknowledged', title: '未确认数', align: 'center'},
            {field: 'persistent', title: '消息持久化数', align: 'center'},
            {title: '操作', width: 80, templet: '#queueListBar', fixed: "right", align: "center"}
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
    	var queue = $(".searchVal").val();
    	var vhost = $("#vhost_id").val();
    	table.reload(tableID, {
    		page: {
    			curr: 1 //重新从第 1 页开始
    		},
    		where: {
    			name: queue,
    			vhost:vhost
    		}
    	})
    });


    $(".addQueue_btn").click(function () {
        var index = layui.layer.open({
            title: "添加队列",
            type: 2,
            maxmin: true,
            area: ['500px', '500px'],
            content: "/queues/queueadd.html?",
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

	function del(obj) {
    	var data = obj.data;
        layer.confirm("确认删除队列 "+data.name+" 吗？", function (index) {
            $.ajax({
                url: "/deleteQueue",
                type: "post",
                dataType: "json",
                data:{
                	'vhost':data.vhost,
                	'queuename':data.name
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
    
    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'del') {
        	del(obj)
		}
    });

})