/*easyui样式初始化*/
$.fn.tabs.defaults.tabHeight = 32; //tab标签条高度
$.fn.linkbutton.defaults.height = 32; //按钮高度
$.fn.menu.defaults.itemHeight = 28; //menu高度

$.fn.validatebox.defaults.height = 32;
$.fn.textbox.defaults.height = 32;
$.fn.textbox.defaults.iconWidth = 24;
$.fn.datebox.defaults.height = 32;
$.fn.numberspinner.defaults.height = 32;
$.fn.timespinner.defaults.height = 32;
$.fn.numberbox.defaults.height = 32;
$.fn.combobox.defaults.height = 32;
$.fn.passwordbox.defaults.height = 32;
$.fn.filebox.defaults.height = 32;

$.fn.menu.defaults.noline = true
$.fn.progressbar.defaults.height = 18; //进度条



$(function() {
	/*左侧导航分类选中样式*/
	$(".panel-body .accordion-body>ul>li").on('click', function() {
		$(this).siblings().removeClass('super-accordion-selected');
		$(this).addClass('super-accordion-selected');

		//新增一个选项卡
		var tabUrl = $(this).data('url');
		var tabTitle = $(this).text();
		//tab是否存在
		if($("#tt").tabs('exists', tabTitle)) {
			$("#tt").tabs('select', tabTitle);
		} else {
			$('#tt').tabs('add', {
				title: tabTitle,
				href: tabUrl,
				closable: true
			});
		}		
	});
	/* 设置tab 右键关闭菜单*/
	$(".tabs-header").bind('contextmenu',function(e){
        e.preventDefault();
        $('#rcmenu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    });
	
	//关闭当前标签页
    $("#closecur").bind("click",function(){
        var tab = $('#tt').tabs('getSelected');
        var index = $('#tt').tabs('getTabIndex',tab);
        if(index==0)return;
        $('#tt').tabs('close',index);
    });
    //关闭所有标签页
    $("#closeall").bind("click",function(){
        var tablist = $('#tt').tabs('tabs');
        for(var i=tablist.length-1;i>=1;i--){
            $('#tt').tabs('close',i);
        }
    });
    //关闭非当前标签页（先关闭右侧，再关闭左侧）
    $("#closeother").bind("click",function(){
        var tablist = $('#tt').tabs('tabs');
        var tab = $('#tt').tabs('getSelected');
        var index = $('#tt').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
        	if(i==0)continue;
            $('#tt').tabs('close',i);
        }
        var num = index-1;
        for(var i=num;i>=0;i--){
        	if(i==0)continue;
            $('#tt').tabs('close',i);
        }
    });
    //关闭当前标签页右侧标签页
    $("#closeright").bind("click",function(){
        var tablist = $('#tt').tabs('tabs');
        var tab = $('#tt').tabs('getSelected');
        var index = $('#tt').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#tt').tabs('close',i);
        }
    });
    //关闭当前标签页左侧标签页
    $("#closeleft").bind("click",function(){
        var tab = $('#tt').tabs('getSelected');
        var index = $('#tt').tabs('getTabIndex',tab);
        var num = index-1;
        for(var i=0;i<=num;i++){
        	if(i==0)continue;
            $('#tt').tabs('close',i);
        }
    });
	
	

	/*设置按钮的下拉菜单*/
	$('.super-setting-icon').on('click', function() {
		$('#mm').menu('show', {
			top: 50,
			left: document.body.scrollWidth - 130
		});
	});

	/*修改主题*/
	$('#themeSetting').on('click', function() {
		var themeWin = $('#winTheme').dialog({
			width: 460,
			height: 260,
			modal: true,
			title: '主题设置',
			buttons: [{
				text: '保存',
				id: 'btn-sure',
				handler: function() {
					var themeName=$('.themeActive').find('div').attr('color');
					$.cookie('easyuiThemeName', themeName, {  
				        expires : 7  
				    }); 
//					alert(themeName)
					themeWin.panel('close');
				}
			}, {
				text: '关闭',
				handler: function() {
					themeWin.panel('close');
				}
			}],
			onOpen: function() {
				$(".themeItem").show();
			}
		});
	});
	
	$(".themeItem ul li").on('click', function() {
		$(".themeItem ul li").removeClass('themeActive');		
		$(this).addClass('themeActive');
		var themeName=$(this).find('div').attr('color'); 
		changeTheme(themeName);
	});
	

	/*退出系统*/
	$("#logout").on('click', function() {
		$.messager.confirm('提示', '确定退出系统？', function(r) {
			if(r) {
				console.log('确定退出')
			}
		});
	});
	
});

$.parser.onComplete = function() {
	$("#index").css('opacity', 1);
}

function changeTheme(themeName) {  
    var $easyuiTheme = $('#easyuiTheme');  
    var url = $easyuiTheme.attr('href');  
    var href = '/css/' + themeName + '.css';  
    $easyuiTheme.attr('href', href);  
    var $iframe = $('iframe');  
    if ($iframe.length > 0) {  
        for ( var i = 0; i < $iframe.length; i++) {  
            var ifr = $iframe[i];  
            $(ifr).contents().find('#easyuiTheme').attr('href', href);  
        }  
    } 
     
};

//changeTheme($.cookie('easyuiThemeName')==undefined?"superBlue":$.cookie('easyuiThemeName'));

function opentab(title,url){
	//新增一个选项卡
	var tabUrl = url;
	var tabTitle = title;
	//tab是否存在
	if($("#tt").tabs('exists', tabTitle)) {
		$("#tt").tabs('select', tabTitle);
	} else {
		$('#tt').tabs('add', {
			title: tabTitle,
			href: tabUrl,
			closable: true
		});
	}
}



/**
 * 初始化示例
 */
function initDemo() {
	/*初始化示例div*/
	var demoPanelId = 'demoPanel' + (new Date()).getTime();
	$('#demoPanel').attr('id', demoPanelId);
	var demoPaneCodeId = 'demoPanelCode' + (new Date()).getTime();
	$('#demoPanelCode').attr('id', demoPaneCodeId);

	/*示例导航选中样式*/
	$(".demo-list>ul>li").on('click', function() {
		$('#et-demo').tabs('select', '预览');

		$(this).siblings().removeClass('super-accordion-selected');
		$(this).addClass('super-accordion-selected');
		//加载页面
		$('#' + demoPanelId).panel('open').panel('refresh', $(this).data('url'));
	});
}

$.namespace = function() {
    var a=arguments, o=null, i, j, d;
    for (i=0; i<a.length; i=i+1) {
        d=a[i].split(".");
        o=window;
        for (j=0; j<d.length; j=j+1) {
            o[d[j]]=o[d[j]] || {};
            o=o[d[j]];
        }
    }
    return o;
};

$.fn.serializeJson = function() {
	var serializeObj = {};
	$(this.serializeArray()).each(function() {
		serializeObj[this.name] = this.value;
	});
	return serializeObj;
};



