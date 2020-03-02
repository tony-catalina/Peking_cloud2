layui.use(['form', 'layer', 'layedit', 'laydate', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        table = layui.table,
        $ = layui.jquery,
        tableID = "awdbindingTable",
        elemTableID = "awdbinding";
    var Windowmsg = "";

    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        query = decodeURIComponent(query);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return (false);
    }

    var getAppQueue = function(){
    	var bindingdata = getQueryVariable("bindingdata");
    	var vars = bindingdata.split("$");
    	var appQueue = {
    			vhost:vars[0],
    			queuename:vars[1]
    	};
    	return appQueue;
    }
    
    var tableIns = table.render({
        elem: '#' + elemTableID,
        url: '/msg_msgsubscription/getMsgtypeByQueuename?queuename='+getAppQueue().queuename,
        cellMinWidth: 95,
        height: "full-125",
        //height: "400px",
        page: true,
        even:true,
        limit: 10,
        limits: [10, 15, 20, 50],
        id: tableID,
        cols: [[
        	{type: "numbers", fixed: "left", width: 30},
        	{type: "checkbox", fixed: "left", width: 30},
            //{field: 'appid', title: 'ID', align: "center",hide:true},
            {field: 'appname', title: '发布名称', align: 'center'},
            {field: 'msgtype', title: '消息类型', align: 'center'},
            {field: 'rolecode', title: '角色代码',width: 90, align: 'center'},
            {field: 'businessid', title: '消息编号', align: 'center'},
            {field: 'businessname', title: '消息名称', align: 'center'},
            {title: '操作', width: 100, templet: '#operate', fixed: "right", align: "center"}
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
    			businessname: $(".searchVal").val()  //搜索的关键字
    		}
    	})
    });
    
	$(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus(tableID),
            data = checkStatus.data,
            msgtypeId = [];
        if (data.length > 0) {
            for (var i in data) {
            	var routingkey = data[i].appid+"_"+data[i].msgtype+"_"+data[i].rolecode+"_"+data[i].businessid;
            	msgtypeId.push(routingkey);
            }
            layer.confirm('确定退订选中的消息类型？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        } else {
            layer.msg("请选择需要订阅的消息类型");
        }
    })
    
    
    function del(obj) {
        layer.confirm("确认退订 "+obj.data.businessname+" 消息么", function (index) {
            $.ajax({
                url: "/msg_msgsubscription/changeQueueBindOrUnbindExchange",
                data:JSON.stringify(obj.data),
                type: "post",
                dataType: "json",
                contentType:"application/json",
                success: function (data) {
                    if (data.result == 1) {
                        layer.msg("删除成功");
                    } else if (data.result == 0) {
                        layer.msg("数据已经被删除，或参数错误");
                    }
                    obj.del();
                    layer.close(index);
                },
                error: function (data) {
                    $.messager.alert('错误', data.msg);
                }
            });
        });
	}
    
    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event;
        if (layEvent === 'del') {
        	del(obj);
        }

    });
})