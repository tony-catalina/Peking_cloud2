layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tableID = "awdqueuesTable",
        elemTableID = "awdqueues";

    //消息列表
    var tableIns = table.render({
        elem: '#awdqueues',
        url: '/getAwdQueues',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        even:true,
        limit: 10,
        limits: [10, 15, 20, 50],
        id: tableID,
        cols: [[
        	{type: "numbers", fixed: "left", width: 30},
        	//{type: "checkbox", fixed: "left", width: 30},
            {field: 'id', title: 'id', align: "center",hide:true},
            {field: 'appname', title: '应用名称', align: 'center'},
            {field: 'queuename', title: '队列名', align: 'center'},
            {title: '操作', width: 170, templet: '#listBar', fixed: "right", align: "center"}
        ]]
        , response: {
            statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
        }
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.result.msg, //解析提示文本
                "count": res.result.total, //解析数据长度
                "data": res.result.rows //解析数据列表
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
    

    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'addbind') {
        	addbind(data);
        }else if (layEvent === 'look') {
        	look(data);
		}
    });

})