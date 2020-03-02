layui.use(['form', 'layer', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var Windowmsg = "";

    //监听提交
    form.on('submit(addform)', function (data) {
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	var id = data.field.id;
    	var url = "/msg_msgtype/addMsgType";
    	if (id == "" || id == undefined) {
    		url = "/msg_msgtype/addMsgType";
		}else {
			url = "/msg_msgtype/updateMsgType/"+id;
		}
        $.ajax({
		    url : url,
		    type:"post",
		    data:JSON.stringify(data.field),
		    dataType:"json",
		    contentType:"application/json",
		    async: false,
		    success:function(data){
		    	if (data.status == 200) {
		    		setTimeout(function(){
		    			if (data.result.code == 200) {
		    				top.layer.close(index);
		    				layer.closeAll("iframe");
		    				parent.location.reload();
						}
		    			top.layer.msg(data.result.msg);
		    			//刷新父页面
		    		},500);
				}
		    }
		});
        return false;
    });
    
    form.on('submit(reset)', function (data) {
    	$("#addform").reset();
    	return false;
    });
    
})