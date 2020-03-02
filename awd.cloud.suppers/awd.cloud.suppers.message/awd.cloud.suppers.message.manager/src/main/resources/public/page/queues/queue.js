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

    var tableIns = table.render({
        elem: '#' + elemTableID,
        url: '/jls_msgffgz?terms[0].column=msgType&terms[0].value=' + getQueryVariable("msgType") + '&paging=false',
        cellMinWidth: 95,
        height: "full-125",
        limit: 10,
        limits: [10, 15, 20, 50],
        id: tableID,
        cols: [[
        	{type: "numbers", fixed: "left", width: 30},
        	{type: "checkbox", fixed: "left", width: 30},
            {field: 'msgType', title: '消息类型', align: "center"},
            {field: 'queueName', title: '名称', align: 'center'},
            {title: '操作', width: 170, templet: '#operate', fixed: "right", align: "center"}
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
    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event,
            data = layui.table.cache[tableID],
            msg = "";
        for (let i = 0; i < data.length; i++) {
            msg += data[i].queueName + ",";
        }
        if (layEvent === 'add') {
            var option = "";
            layer.open({
                type: 2 //此处以iframe举例
                , title: '当你选择该窗体时，即会在最顶端'
                , area: ['390px', '560px']
                , shade: 0
                , maxmin: true
                , content: '/page/messagerule/messageruleadd.html?msg=' + msg
                , btn: ['继续弹出', '全部关闭'] //只是为了演示
                , yes: function () {
                    $(that).click();
                }
                , btn2: function () {
                    layer.closeAll();
                }
                , success: function (layero) {
                }
            });

        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.ajax({
                    url: "/jls_msgffgz/" + obj.data.id,
                    type: "delete",
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
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
                //向服务端发送删除指令
            });


        }

    });

    //监听提交
    form.on('submit(demo1)', function (data) {
        layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        })
        return false;
    });


    function LoadSelectData(){
        $("#interest").empty();
        var option = "";
            $.ajax({
                url:"/getQueues",
                type:"post",
                data:{msgType: Windowmsg},
                dataType:"json",
                async: true,
                success:function(data){
                    var ResData = data.result;
                    for(i = 0 ; i < ResData.length ; i++){
                        option = "<option value="+ResData[i].queue+">"+ResData[i].name+"</option>";
                        $("#interest").append(option);
                    };
                    form.render('select');
                }
            });
    };

   $(".layui-select-title").on("click",function(){
       //LoadSelectData();
	   console.info("ewqewqe");
   })


})