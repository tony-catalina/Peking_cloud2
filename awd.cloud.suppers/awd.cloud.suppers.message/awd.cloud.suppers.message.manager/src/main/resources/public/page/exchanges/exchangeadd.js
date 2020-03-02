layui.use(['form', 'layer', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var Windowmsg = "";

    //监听提交
    form.on('submit(addform)', function (data) {
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
		    url:"/addExchange",
		    type:"post",
		    data:data.field,
		    dataType:"json",
		    //contentType:"application/json",
		    //async: true,
		    success:function(data){
		    	if (data.status == 200) {
		    		setTimeout(function(){
		    			top.layer.close(index);
		    			top.layer.msg("添加成功");
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
    	$("#exchangefm").reset();
    	return false;
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
    	    		$("#vhost_id").append('<option value="">请选择一个虚拟主机</option>');
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
    
})