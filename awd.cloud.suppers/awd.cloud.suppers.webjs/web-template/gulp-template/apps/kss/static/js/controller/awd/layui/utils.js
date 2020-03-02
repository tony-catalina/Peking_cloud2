define(function(require){
    require('layer');
    var tools=require('awd/jquery/tools');
    var main={
        showWin:function(option){
            var laywin=layer.open({
                title:tools.isUndefined(option.title)?'信息':option.title,
                area:tools.isUndefined(option.area)?'auto':option.area,
                type: tools.isUndefined(option.type)?1:option.type,
                maxmin: tools.isUndefined(option.maxmin)?true:option.maxmin,
                content: tools.isUndefined(option.content)?'':option.content,
                success:tools.isUndefined(option.success)?function(){
                    }:option.success,
                zIndex: 1000,
                btn:['确定', '取消']
            });
            if(tools.isTrue(option.max)){
                layer.full(laywin);
            }
            return laywin;
        },
        showForm:function(id,option){

        }
    };
    return main;
});