layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table,
        tableID = "messageTypeTable",
        elemTableID = "messageType";

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
    
    
    //消息列表
    var tableIns = table.render({
    	elem: '#' + elemTableID,
        url: '/msg_msgtype/getUnbindMsgtypeByQueuename?queuename='+getAppQueue().queuename+"&vhost="+getAppQueue().vhost,
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        //height: "400px",
        even:true,
        limit: 10,
        limits: [10, 15, 20, 50],
        id: tableID,
        cols: [[
        	{type: "numbers", fixed: "left", width: 30},
        	{type: "checkbox", fixed: "left", width: 30},
            //{field: 'appid', title: 'ID', align: "center"},
            {field: 'appname', title: '发布名称', align: 'center'},
            {field: 'msgtype', title: '消息类型', align: 'center'},
            {field: 'rolecode', title: '角色代码', align: 'center'},
            {field: 'businessid', title: '消息编号', align: 'center'},
            {field: 'businessname', title: '消息名称', align: 'center'},
            {title: '操作', width: 80, templet: '#operate', fixed: "right", align: "center"}
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


    function addbind(obj) {
    	var msgData = obj.data;
        var paramData = {
		    	"vhost":getAppQueue().vhost,
		    	"queuename":getAppQueue().queuename,
		    	"routingkey":msgData.routingkey,
		    	"shbz":"1"
		    };
        layer.confirm('确定订阅该类型的消息？', {icon: 3, title: '提示信息'}, function (index) {
            $.ajax({
    		    url:"/msg_msgsubscription/changeQueueBindOrUnbindExchange",
    		    type:"post",
    		    data:JSON.stringify(paramData),
    		    dataType:"json",
    		    contentType:"application/json",
    		    async: true,
    		    success:function(data){
    		    	var alertMsg = "订阅成功";
    		    	if (data.code == 200) {
    		    		alertMsg = "消息 "+msgData.businessname+" 订阅成功";
    		    		obj.del();
    				}else {
    					alertMsg = "消息 "+msgData.businessname+" 订阅失败";
					}
    		    	setTimeout(function(){
    		    		top.layer.close(index);
    		    		top.layer.msg(alertMsg);
    		    		//layer.closeAll("iframe");
    		    		//刷新父页面
    		    		//parent.location.reload();
    		    	},100);
    		    }
    		});
        })
    }

    $(".bindAll_btn").click(function () {
        var checkStatus = table.checkStatus(tableID),
            data = checkStatus.data,
            msgtypeId = [];
        if (data.length > 0) {
            for (var i in data) {
            	var routingkey = data[i].appid+"_"+data[i].msgtype+"_"+data[i].rolecode+"_"+data[i].businessid;
            	msgtypeId.push(routingkey);
            }
            layer.confirm('确定订阅选中的消息类型？', {icon: 3, title: '提示信息'}, function (index) {
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

    //列表操作
    table.on('tool(' + elemTableID + ')', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'addbind') { //编辑
        	addbind(obj);
        }
    });

})