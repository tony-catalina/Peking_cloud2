layui.use(['form', 'layer', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var Windowmsg = "";

    //监听提交
    form.on('submit(updateform)', function (data) {
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
		    url:"/msg_rabbitUsers/updateRabbitUser",
		    type:"post",
		    data:JSON.stringify(data.field),
		    dataType:"json",
		    contentType:"application/json",
		    async: false,
		    success:function(data){
		    	if (data.status == 200) {
		    		setTimeout(function(){
		    			top.layer.close(index);
		    			top.layer.msg("修改成功");
		    			layer.closeAll("iframe");
		    			//刷新父页面
		    			parent.location.reload();
		    		},500);
				}
		    }
		});
        return false;
    });
    
    form.on('submit(reset)', function (data) {
    	$("#updateuserfm").reset();
    	return false;
    });

})