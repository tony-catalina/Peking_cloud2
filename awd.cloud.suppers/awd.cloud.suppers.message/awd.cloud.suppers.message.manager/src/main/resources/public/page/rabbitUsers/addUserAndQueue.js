layui.use(['form', 'layer', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var Windowmsg = "";

    //监听提交
    form.on('submit(addform)', function (data) {
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
		    url:"/msg_rabbitUsers/addUserAndQueue",
		    type:"post",
		    data:JSON.stringify(data.field),
		    //data:data.field,
		    dataType:"json",
		    contentType:"application/json",
		    async: false,
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
    	$("#queuefm").reset();
    	return false;
    });

    $('body').ready(function(){
    	setTimeout(function(){
    		//form.render('select',"vhost_id");
    		form.render();
    	},500)
    });
    
	/*form.on('select(vhost_select)', function(data){
		$("#exchange_id").empty();
		$("#exchange_id").append('<option value="">请选择一个交换机</option>');
		$.ajax({
		    url:"/getExchangesGroupByVhost",
		    type:"post",
		    data:{"vhost":data.value},
		    dataType:"json",
		    async: true,
		    success:function(data){
		        var ResData = data.result.rows;
		        for(i = 0 ; i < ResData.length ; i++){
		            option = "<option value='"+ResData[i].name+"'>"+ResData[i].name+"</option>";
		            $("#exchange_id").append(option);
		        };
		        form.render('select',"exchange_id");
		    }
		});
   });*/
   
})