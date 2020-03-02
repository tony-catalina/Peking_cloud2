$(function () {
	initZTree();
});

var setting = {
	view : {
		showIcon : false,//设置 zTree 是否显示节点的图标。默认值：true
		showLine : false
	//设置 zTree 是否显示节点之间的连线。默认值：true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};

function onClick(e, treeId, treeNode) {
	if(treeNode.isParent){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.expandNode(treeNode);
	}else{
		$('#txtTreeSelect').val(treeNode.name);
		$('#jsbhfm').val(treeNode.id);
		hideMenu();
	}
}

function showMenu() {
	var cityObj = $('#zTree');
	var cityOffset = cityObj.offset();
	$("#menuContent").css({
		left : cityOffset.left/26 + "px",
		top : (cityOffset.top + cityObj.outerHeight())/12 + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}

var zNodes =[
    { id:1, pId:0, name:"总队",isParent:true},
    { id:2, pId:0, name:"看守所",isParent:true},
    { id:3, pId:0, name:"拘留所",isParent:true},
    { id:4, pId:0, name:"戒毒所",isParent:true},
    { id:5, pId:0, name:"安康医院",isParent:true},
];
var setting = {
		view: {
			showIcon: false,
			showLine: false,
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		async: {
			enable: true,
			url:"/getJsTypeByGroups",
			autoParam:["id"],
			dataFilter: filter
		},
		callback: {
			onClick: onClick,
		}
	};

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}



var initZTree = function() {
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
};
