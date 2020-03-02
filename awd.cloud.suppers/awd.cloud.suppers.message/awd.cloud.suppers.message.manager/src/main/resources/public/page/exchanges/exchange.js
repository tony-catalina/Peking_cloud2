layui.use(['form', 'layer', 'layedit', 'laydate', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        table = layui.table,
        $ = layui.jquery,
        tableID = "messageRuleTable",
        elemTableID = "messageRule";
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
    			exchangname:vars[1]
    	};
    	return appQueue;
    }
    
    var tableIns = table.render({
        elem: '#' + elemTableID,
        url: '/getQueuesByExchange',
        where : getAppQueue(),
        //contentType: 'application/json',
        cellMinWidth: 95,
        //height: "full-125",
        height: 415,
        page: true,
        even:true,
        limit: 10,
        limits: [10, 15, 20, 50],
        id: tableID,
        cols: [[
        	{type: "numbers", fixed: "left", width: 30},
        	{type: "checkbox", fixed: "left", width: 30},
            {field: 'vhost', width: 100, title: '虚拟主机', align: "center"},
            {field: 'routingkey', title: '路由键', align: 'center'},
            {field: 'queuename', title: '对列名', align: 'center'},
            {title: '操作', width: 100, templet: '#operate', fixed: "right", align: "center"}
        ]]
        , response: {
            statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
        }
        , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.result.total, //解析数据长度
                "data": res.result.rows //解析数据列表
            };
        }
    });
    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event,
            data = obj.data,
            msg = "";
        if (layEvent === 'del') {
            layer.confirm("真的取消绑定路由键 "+ data.routingkey +" 么", function (index) {
                $.ajax({
                    url: "/unbindQueueAndExchange",
                    type: "post",
                    data:data,
                    dataType: "json",
                    success: function (data) {
                        if (data.status == 200) {
                            layer.msg("解绑成功");
                        } else {
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

    });

})