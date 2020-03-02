define("framework/flowmap/awd.flowmap", [
	"../../plugins/jquery/jquery-1.9.1.min", 
	"../../plugins/mxClient/js/mxClient-chrome.js"
	],
function(require) {
	var mxBasePath = '../../pluings/mxClient-chrome';
	mxClient.imageBasePath="../../plugins/mxClient/images";
	var _defaultStyle=function(graph,icon){
		var style = {};
	    // 克隆一个object				
	    style = graph.getStylesheet().getDefaultEdgeStyle();
	    style[mxConstants.STYLE_ROUNDED] = true;//圆角连线
	    style = mxUtils.clone(style);
	    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_LABEL;  // 不设置这个属性 背景图片不出来
	    // 边框颜色
	    style[mxConstants.STYLE_STROKECOLOR] = 'white';
	    //节点的背景色
	    style[mxConstants.STYLE_FILLCOLOR] = 'white';
	    // 字体颜色
	    style[mxConstants.STYLE_FONTCOLOR] = 'black';
	    // 文字水平方式
	    style[mxConstants.STYLE_ALIGN] = mxConstants.ALIGN_CENTER;
	    // 文字垂直对齐
	    style[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_BOTTOM;
	    // 字体大小
	    style[mxConstants.STYLE_FONTSIZE] = 13;
	    // 底图水平对齐
	    style[mxConstants.STYLE_IMAGE_ALIGN] = 0;
	    // 底图垂直对齐
	    style[mxConstants.STYLE_IMAGE_VERTICAL_ALIGN] = 0;
	    // 图片路径
	    style[mxConstants.STYLE_IMAGE] = icon;
	    // 背景图片宽 
	    style[mxConstants.STYLE_IMAGE_WIDTH] = 70;
	    // 背景图片高
	    style[mxConstants.STYLE_IMAGE_HEIGHT] = 70;
	    // 上间距设置
	    // 即使下边定义了全局设置，但这里单独设置上边间距仍单独有效
	     style[mxConstants.STYLE_SPACING_TOP] = 70;
	    // 四边间距设置
	    style[mxConstants.STYLE_SPACING] = 10;
	    // 把定义好的样式object push到stylesheet
	    graph.getStylesheet().putCellStyle("style1", style);	
	}
	var _initmap=function(id,option){	
		var container = document.getElementById(id);
		var graph = new mxGraph(container);
	    var parent = graph.getDefaultParent();	    
		var _icon=(option==null||option.icon==undefined?"":option.icon);
		var _initstyle=(option==null||option.initstyle==undefined?_defaultStyle:option.initstyle);
		var _initnode=(option==null||option.initnode==undefined?function(){}:option.initnode);
		var _initthread=(option==null||option.initthread==undefined?function(){}:option.initthread);
		var _cellclick=(option==null||option.cellclick==undefined?function(){}:option.cellclick);
		var _tooltip=(option==null||option.tooltip==undefined?null:option.tooltip);
	    //设置节点解析html标签
	    graph.setHtmlLabels(true);
	    // 禁用选择和单元格处理
	    graph.setEnabled(false);
	    //开启tooltip提示
	    graph.setTooltips(true);
	    /* style1 */
	    _initstyle(graph,_icon);	    
	    // 画人物
	    var _nodes=_initnode(graph,parent);   
	    // 连线
	    _initthread(graph,_nodes,parent);
	    //添加事件
	    graph.addListener(mxEvent.CLICK, function (sender, evt) {
	    	_cellclick(graph,sender,evt);
	    });
	    //鼠标悬浮效果
	    graph.getTooltipForCell=function(cell){
	    	if(_tooltip==null){
	    		var tip = null;
		        if (cell.getTooltip != null) {
		            tip = cell.getTooltip();
		        } else {
		            tip = this.convertValueToString(cell);
		        }
		        return tip;
	    	}else{
	    		return _tooltip(cell);
	    	}	    	
	    };
	}
	
	return {
		init_map:_initmap		
	}

});