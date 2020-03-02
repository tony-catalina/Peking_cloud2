define("demo/js/flowmap", [
	"../../framework/flowmap/awd.flowmap",
	"../../framework/easyui/awd.utils"
	],
function(require) {
	var awd_flowmap=require("../../framework/flowmap/awd.flowmap");
	var awd_utils=require("../../framework/easyui/awd.utils");
	var getMenu=function(code){
		
	}
	var initview=function(){
		awd_flowmap.init_map("main",
				{
					icon:'../../imgs/lcttx.png',
//					initstyle:function(graph,icon){
//						var style = {};
//					    // 克隆一个object				
//					    style = graph.getStylesheet().getDefaultEdgeStyle();
//					    style[mxConstants.STYLE_ROUNDED] = true;//圆角连线
//					    style = mxUtils.clone(style);
//					    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_LABEL;  // 不设置这个属性 背景图片不出来
//					    // 边框颜色
//					    style[mxConstants.STYLE_STROKECOLOR] = 'white';
//					    //节点的背景色
//					    style[mxConstants.STYLE_FILLCOLOR] = 'white';
//					    // 字体颜色
//					    style[mxConstants.STYLE_FONTCOLOR] = 'black';
//					    // 文字水平方式
//					    style[mxConstants.STYLE_ALIGN] = mxConstants.ALIGN_CENTER;
//					    // 文字垂直对齐
//					    style[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_BOTTOM;
//					    // 字体大小
//					    style[mxConstants.STYLE_FONTSIZE] = 13;
//					    // 底图水平对齐
//					    style[mxConstants.STYLE_IMAGE_ALIGN] = 0;
//					    // 底图垂直对齐
//					    style[mxConstants.STYLE_IMAGE_VERTICAL_ALIGN] = 0;
//					    // 图片路径
//					    style[mxConstants.STYLE_IMAGE] = icon;
//					    // 背景图片宽 
//					    style[mxConstants.STYLE_IMAGE_WIDTH] = 70;
//					    // 背景图片高
//					    style[mxConstants.STYLE_IMAGE_HEIGHT] = 70;
//					    // 上间距设置
//					    // 即使下边定义了全局设置，但这里单独设置上边间距仍单独有效
//					     style[mxConstants.STYLE_SPACING_TOP] = 70;
//					    // 四边间距设置
//					    style[mxConstants.STYLE_SPACING] = 10;
//					    // 把定义好的样式object push到stylesheet
//					    graph.getStylesheet().putCellStyle("style1", style);				
//					},
					initnode:function(graph,parent){	
						var ldsp_task = '1';
					    if (ldsp_task != '0') {
					        var jstz_ldsp = "<font color='red'>(" + ldsp_task + ")</font>";
					    } else {
					        var jstz_ldsp = "";
					    }
						var v1 = graph.insertVertex(parent, 'jstzbl', '监室调整登记'+ jstz_ldsp, 150, 100, 75, 100, "style1");
					    var v2 = graph.insertVertex(parent, 'ldsp',   '领导审批' + jstz_ldsp, 550, 150, 75, 100, "style1");
					    var v3 = graph.insertVertex(parent, 'zdjsyj', '中队/警署意见'+ jstz_ldsp, 350, 220, 75, 100, "style1");
					    var v4 = graph.insertVertex(parent, 'mddy',   '名单打印'+ jstz_ldsp, 750, 100, 75, 100, "style1");
					    var v5 = graph.insertVertex(parent, 'jstzqd', '监室调整确定'+ jstz_ldsp, 750, 220, 75, 100, "style1");
					    var v6 = graph.insertVertex(parent, 'zxgsz',  '主协管设置'+ jstz_ldsp, 950, 320, 75, 100, "style1");
					    var v7 = graph.insertVertex(parent, 'pljstz', '批量监室调整'+ jstz_ldsp, 150, 320, 75, 100, "style1");
					    var nodes=new Array(v1,v2,v3,v4,v5,v6,v7);
					    v1.getTooltip=function(){
					    	return "";
					    }
					    return nodes;
					},
					initthread:function(graph,nodes,parent){
					    graph.insertEdge(parent, null, '', nodes[0], nodes[2], "edgeStyle=elbowEdgeStyle;");
					    graph.insertEdge(parent, null, "<font  style='font-size:18px'>监区内</font>", nodes[2], nodes[4], "edgeStyle=elbowEdgeStyle;");
					    graph.insertEdge(parent, null, "<font  style='font-size:18px'>跨监区</font>", nodes[2], nodes[1], "edgeStyle=elbowEdgeStyle;");
					    graph.insertEdge(parent, null, '', nodes[1], nodes[3], "edgeStyle=elbowEdgeStyle;");
					    graph.insertEdge(parent, null, '', nodes[1], nodes[4], "edgeStyle=elbowEdgeStyle;");
					},
					cellclick:function(graph,sender, evt){
						var cell11 = evt.getProperty('cell');
						$.messager.alert('提示',cell11.id,'info');				        
					},
					tooltip:function(cell){
						return cell.id;
					}
				}
		);
	}
	
	initview();

});