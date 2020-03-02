/**
 * \* Created with Eclipse By WS
 * \* Date: 2017年12月15日 下午7:03:02
 * \
 */
	
 	//时间格式转码（yyyy-mm-dd）
	   function timeType(date) {
		    var datetime = date.getFullYear()
		        + "-"// "年"
		        + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
		            + (date.getMonth() + 1))
		        + "-"// "月"
		        + (date.getDate() < 10 ? "0" + date.getDate() : date
		            .getDate());
		    return datetime;
		  };
   //获取当月的的天数
		function getLastDay(year,month)          
		{          
			 var new_year = year;    //取当前的年份           
			 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）           
			 if(month>12)            //如果当前大于12月，则年份转到下一年           
			 {          
			  new_month -=12;        //月份减           
			  new_year++;            //年份增           
			 }          
			 var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天           
			 var date_count =   (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月的天数         
			 var last_date =   new Date(new_date.getTime()-1000*60*60*24);//获得当月最后一天的日期  
			return date_count;  
		}
 
		//开始时间减去结束时间运算
		function getDates(startDate, endDate){
			var startMonth = startDate.substring(5,startDate.lastIndexOf ('-'));
			var startDay = startDate.substring(startDate.length,startDate.lastIndexOf ('-')+1);
			var startYear = startDate.substring(0,startDate.indexOf ('-'));
			
			var endMonth = endDate.substring(5,endDate.lastIndexOf ('-'));
			var endDay = endDate.substring(endDate.length,endDate.lastIndexOf ('-')+1);
			var endYear = endDate.substring(0,endDate.indexOf ('-'));
			var data = {};
			if(Date.parse(startMonth+'/'+startDay+'/'+startYear)>=Date.parse(endMonth+'/'+endDay+'/'+endYear)){
				var month = "";
				var newMonth = "";
				var year = "";
				var day = "";
				if(startDay>=endDay){
					day = parseInt(startDay)-parseInt(endDay);
					month = startMonth;
				}else{
					month = startMonth-1;
					var dd = getLastDay(startYear,month);
					day =(parseInt(dd) +parseInt(startDay))-parseInt(endDay);
				};
				if(month>=endMonth){
					newMonth = parseInt(month) - parseInt(endMonth);
					year = startYear;
				}else{
					newMonth = parseInt(month)+12 - parseInt(endMonth);
					year = parseInt(startYear)-1;
				};
				var newYear = parseInt(year) - parseInt(endYear);
				data = {"newYear":newYear==null?0:newYear,
						 "newMonth":newMonth==null?0:newMonth,
						 "day":day==null?0:day};
				
				return data;
			};
			if(Date.parse(startMonth+'/'+startDay+'/'+startYear)<Date.parse(endMonth+'/'+endDay+'/'+endYear)){
				//getDates(endDate,startDate);
				
				return null;
			};

		};
function awdSelectTree(){
		$('.awdSelectTree').each(function(){
			var id=$(this).attr("id")
			$(this).combotree({  
			    mode:'remote',
			    url:'',
		        method:"POST",
		        //animate:true, //展开合并时动画
		        lines: true, //虚线
		        queryParams:{"type":"select"},
		        dnd:true,
		        multiple:$(this).attr("checkBox")=="true",	//定义是否多选
		        checkbox:$(this).attr("checkBox")=="true",  //定义是否在每一个借点之前都显示复选框
	            onShowPanel : function(){
	            	$(this).combotree('reload', "/kss_jq/extTreeNode")
	            },

	            onCheck : function(node,checked){
	            	var nodes = $(this).tree('getChecked');       
	            	var nodePar = $(this).tree("getParent",node.target);
	            	var n=[];
	            	for(var i = 0 ;i<nodes.length;i++){
	            		n.push(nodes[i].id)
	            	}
	            	$("#"+id+"_tree").val(n);
	            },	            
			});
		});		
	}
	
	function awdSelectPage(){
		
		$('.awdSelectPage').each(function(){
			var fieldName="";
			var url="";
			var alike="";
			var biaoti = $(this).prev().combobox().prevObject.attr("biaoti");
        	biaoti = biaoti==undefined?"内容":biaoti;
			$(this).combogrid({  
		        panelWidth: 300,  
		        panelHeight: 265,  
		        idField: 'code',        //ID字段    
		        textField: 'content',    //显示的字段    
		        mode:'remote',
		        url: "", 
		       // biaotiTitle:'biaoti',
		        fitColumns: true,  
		        striped: true,  
		        editable: true, 
		        multiple: $(this).attr("checkBox")=="true",
		        pagination: true, //是否分页   
	            loading:true,
		        rownumbers: true,           //序号  
		        collapsible: false,         //是否可折叠的  
		        fit: true,                  //自动大小  
		        method: 'post',
                    delay:250,
		        columns: [[  {field:'ck',checkbox:$(this).attr("checkBox")=="true"},
			                { field: 'fieldName', title: 'xx', width: 80, hidden: true },  
			                { field: 'code', title: '编号', width: 80, hidden: true },  
		                    { field: 'content', title: biaoti, width: 150 },  
		                ]],
	            onShowPanel : function(){
	            	$(this).prev().combobox("showPanel");	            	
	            	
	            	fieldName=$(this).prev().combobox().prevObject.attr("code");
	            	url = $(this).prev().combobox().prevObject.attr("url");
	            	url = url==undefined?"":url;
	            	alike=$(this).prev().combobox().prevObject.attr("alike");
	            	getData(1,20,fieldName,"",url,alike);
	            },

		        keyHandler: {

                    query: function (keyword) {     //【动态搜索】处理
		                //设置查询参数
		            	//获取查询参数
		                var queryParams = $('input[code="'+fieldName+'"]').combogrid("grid").datagrid('options').queryParams;
		                queryParams.keyword = keyword;

		                getData(1,20,fieldName,queryParams.keyword,url,alike)
		                $('input[code="'+fieldName+'"]').combogrid("grid").datagrid('options').queryParams = queryParams;
		                //重新加载
		                $('input[code="'+fieldName+'"]').combogrid("grid").datagrid("reload");

		                $('input[code="'+fieldName+'"]').combogrid("setValue", keyword);
		                //将查询条件存入隐藏域
		                $('#selectKeyWord').val(keyword);

		            }
		        },  
		        onSelect: function () { 
		        	//选中处理 
		        	if(alike!=""&&alike!=undefined&&alike!=null){
			        	$('#'+fieldName+'_select_alike').val($('input[alike="'+alike+'"]').combogrid('getValues'));  
		        	}else{
			        	$('#'+fieldName+'_select').val($('input[code="'+fieldName+'"]').combogrid('getValues'));  
		        	}
		        	$('#selectKeyWord').val('');
		        }
		    });  
		    //取得分页组件对象 

	        	var pager= $(this).combogrid('grid').datagrid('getPager');  
	    	    if (pager) {  
	    	        $(pager).pagination({  
	    	            pageSize: 10,               //每页显示的记录条数，默认为20  
	    	           // pageList: [100, 50, 20],       //可以设置每页记录条数的列表  
	    	            beforePageText: '第',       //页数文本框前显示的汉字  
	    	            //showPageList:false,
	    	            afterPageText: '页,共 {pages} 页',
	    	            displayMsg: '',//'当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	    	            //选择页的处理  
	    	            onSelectPage: function (pageNumber, pageSize) {  
	    	                //按分页的设置取数据  
	    	                getData(pageNumber, pageSize,fieldName,$('.selectKeyWord').val(),url,alike);  
	    	                //设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据  
	    	                $('input[code="'+fieldName+'"]').combogrid("grid").datagrid('options').pageSize = pageSize;  
	    	              
	    	                //将隐藏域中存放的查询条件显示在combogrid的文本框中  
	    	                $('input[code="'+fieldName+'"]').combogrid("setValue", $('.selectKeyWord').val());  
	    	                //$('#selectKeyWord').val(''); 
	    	            },  
	    	            
	    	            //点击刷新的处理  
	    	            onRefresh: function (pageNumber, pageSize) {  
	    	                //按分页的设置取数据  
	    	                getData(pageNumber, pageSize,fieldName,"",alike);
	    	                $('input[code="'+fieldName+'"]').combogrid("setValue", "");  
	    	                $('#selectKeyWord').val(''); 
	    	                 
	    	            }  
	    	        });  
	    	    }
	    	 	
			
		});
    	//获取数据
	    var getData = function (page, rows,fieldName,queryParams,location,alike) { 
	    	var location=location==""?"/kss_dictionary/getDictionaryPage":location;
	    	
        	var pager= $('input[code="'+fieldName+'"]').combogrid('grid').datagrid('getPager');  
        	queryParams=queryParams==undefined?"":queryParams;
	        $.ajax({  
	            type: "POST",  
	            url: location,  
	            data: "pageIndex=" + page + "&pageSize=" + rows +"&fieldname$eq="+fieldName+"&queryParams="+queryParams,
	            error: function (XMLHttpRequest, textStatus, errorThrown) {  
	                alert(textStatus);  
	                $.messager.progress('close');  
	            },  
	            success: function (data) {  
	            	if(alike!=""&&alike!=undefined&&alike!=null){
		                $('input[alike="'+alike+'"]').combogrid('grid').datagrid('loadData', data.data);
	            	}else{
		                $('input[code="'+fieldName+'"]').combogrid('grid').datagrid('loadData', data.data);
	            	}

	                pager.pagination('refresh',{	// 改变选项，并刷新分页栏信息
	                	total: data.total,
	                	pageNumber: page
	                });

	            }  
	        });  
	    }; 

	}
	
	function awdSelect(){
		$('.awdSelect').each(function(){
			var fieldName=$(this).prev().next().attr("code") ;
			if(fieldName==undefined){
				fieldName=$(this).attr("code");
			}
			var url=$(this).attr("url");
			$(this).combobox({
			    url:'',
			    valueField:'code',
			    textField:'content',
			    multiple:$(this).attr("checkBox")=="true",
		    	onShowPanel:function(){

		    		if(url==""||url==undefined||url==null){
		                $(this).combobox('reload','/kss_dictionary/getDictionary?node='+fieldName);
		    		}else{
		                $(this).combobox('reload',url);
		    		}
		          }
			});
		});	
	}
	//监室下拉
function awdSelectJs(){
    $('.awdSelectJs').each(function(){
        var fieldName=$(this).prev().next().attr("code") ;
        if(fieldName==undefined){
            fieldName=$(this).attr("code");
        }
        var url=$(this).attr("url");
        $(this).combobox({
            url:'',
            valueField:'jsh',
            textField:'jsmc',
            multiple:$(this).attr("checkBox")=="true",
            onShowPanel:function(){

                if(url==""||url==undefined||url==null){
                    $(this).combobox('reload','/kss_dictionary/getDictionary?node='+fieldName);
                }else{
                    $(this).combobox('reload',url+"?xb="+$(this).attr("xb"));
                }
            }
        });
    });
}
	/*日期组件（只获取年月）-------只需要传递一个class:nyMonth*/
	function hqNY(){
    	$('.nyMonth').datebox({
    	onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
    	span.trigger('click'); //触发click事件弹出月份层
    	if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
    	tds = p.find('div.calendar-menu-month-inner td');
    	tds.click(function (e) {
    	e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
    	var year = /\d{4}/.exec(span.html())[0]//得到年份
    	, month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
    	$('.nyMonth').datebox('hidePanel')//隐藏日期对象
    	.datebox('setValue', year + '年' + month)+'月'; //设置日期的值
    	});
    	}, 0);
    	yearIpt.unbind();//解绑年份输入框中任何事件
    	},
    	parser: function (s) {
    	if (!s) return new Date();
    	var arr = s.split('年');
    	return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
    	},
    	formatter: function (d) { return d.getFullYear() + '年' + (d.getMonth() + 1)+'月';/*getMonth返回的是0开始的，忘记了。。已修正*/ }
    	});
    	var p = $('.nyMonth').datebox('panel'), //日期选择对象
    	tds = false, //日期选择对象中月份
    	yearIpt = p.find('input.calendar-menu-year'),//年份输入框
    	span = p.find('span.calendar-text'); //显示月份层的触发控件
    	}
	


/**
 * 页面基本信息 table
 * 
 * @returns
 */
	var ad;
function fLoadTable(id,toolsID) {
	//console.log("onSelect",onSelect==undefined);
	$('#'+id).datagrid({
						title : '人员基本信息',
						width : '100%',
						height : '280px',
						fitColumns : true,
						method:'post',
						border:false,
						singleSelect:true,
						fit:true,
						tools:'#'+toolsID,
						fitColumns:true,
				        onSelect:onSelect,
				        toolbar:'#toolbar',
				        url:'',
						// 对应json数据中的每一列
						columns : jbxxColumns,
						idField : 'id',
						loadMsg : '加载中, 请  稍  等  …',
						pagination : true,
						view : detailview,
						detailFormatter : function(index, rowData) {
							return '<table  style="height:60px;width:100%"><tr><td>户籍地详细地址：'
									+ (rowData.hjszd == null ? ""
											: rowData.hjszd)
									+ '</td></tr><tr><td>现住地详细地址:'
									+ (rowData.xzd == null ? "" : rowData.xzd)
									+ '</td></tr><tr><td>办案单位:'
									+ (rowData.badw == null ? "" : rowData.badw)
									+ '</td></tr><tr><td>简要案情:'
									+ (rowData.aq == null ? "" : rowData.aq)
									+ '</td></tr></table>'
						},
						onLoadSuccess: function(data){//加载完毕后获取所有的checkbox遍历
							if(data.total>0){
								$('#'+id).datagrid("selectRow", 0);
								$("input[name='ryRadio']")[0].checked = true;
								 //鼠标悬浮显示人员状态
								sbxt(id);
							}
							
					     },
					     rowStyler:function(index, record){
								
								if(record.fxdj==3){
									return  'color:#006e44;';
								}else if(record.fxdj==2){
									return  'color:#fddb38;';
								}else if(record.fxdj==1){
									return  'color:#e9540e;';
								}
							},
							//单击行选中取消事件
							   onClickRow: function (rowIndex, rowData) {
								   if(ad!=rowData.id){
							   			$("input[name='ryRadio']")[rowIndex].checked = true;
										ad=rowData.id;
							   		}else if(ad==rowData.id){
							   			$("#"+id).datagrid("unselectRow",rowIndex);
								        $("input[name='ryRadio']")[rowIndex].checked = false;
								        ad="";
							   		}
					           },
					        /*双击事件*/
					        onDblClickRow: function (rowIndex, rowData) {  
					        	$("input[name='ryRadio']")[rowIndex].checked = true;
								$("#"+id).datagrid("selectRow", rowIndex);
								ad=rowData.id;
						    	save();
							},
						queryParams : {
							"snbh$like":"",
							"xm$like":"",
							"pageSize" : function() {
								return $('#'+id).datagrid("getPager")
										.pagination("options").pageSize;
							},
							"pageIndex" : function() {
								return $('#'+id).datagrid("getPager")
										.pagination("options").pageNumber;
							}
						},
						onRowContextMenu: function(e, rowIndex, rowData) { //右键时触发事件
			                //三个参数：rowIndex 当前点击时所在行的索引，rowData 当前行的数据
							if(rowData){
								e.preventDefault(); //阻止浏览器捕获右键事件
				                $(this).datagrid("clearSelections"); //取消所有选中项
				                $("input[name='ryRadio']")[rowIndex].checked = true;
				                $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
				                $('#menu').menu('show', {
				                	//显示右键菜单
				                    left: e.pageX,//在鼠标点击处显示菜单
				                    top: e.pageY
				                });
							}
			            }
					});

	// 根据姓名或者人员编号查询
	$(window).keydown(function(event) {
		var s_xmsr = $("#s_xm").textbox('getValue');
		var s_snbhsr = $("#s_snbh").textbox('getValue');
		if (event.keyCode == 13) {
			$('#'+id).datagrid('load', {
				xm$like : s_xmsr,
				snbh$like : s_snbhsr
			});
		}
	});
	
	// 人员基本信息显示隐藏
	$(function() {
		// 隐藏列鼠标悬浮颜色变化控制
		$("#ycl_ryjbxx tr").bind('mouseover', function() {
			$(this).css("background-color", "#d6e3f2");
		});
		$("#ycl_ryjbxx tr").bind('mouseout', function() {
			$(this).css("background-color", "#ffffff");
		});
		// 隐藏列点击选中
		$("#ycl_ryjbxx tr span").bind(
				'click',
				function() {
					var input = $(this).parents("td").prev().children(
							"input[type='checkbox']");
					// var input = $("#"+$(this).attr('lang'));//获取checkbox
					// 判断当前checkbox是否为选中状态
					input.prop("checked", !input.prop("checked"));
					// 根据checkbox选中状态判断隐藏显示列
					closeGridColumn(input);
				});
		$("#ycl_ryjbxx tr input[type=checkbox]").bind("click", function() {
			closeGridColumn($(this));
		});
		var closeGridColumn = function(obj) {
			$("#"+id).datagrid(
					obj.prop("checked") ? "showColumn" : "hideColumn",
					obj.attr("id"));
		}

		$("#xsycl_ryjbxx")
				.bind(
						"click",
						function() {
							if ($("#contextMenu_ryjbxx").parent().is(":hidden")) {
								if ($("#ryjbxx_wrap")[0] === undefined) {
									$('#contextMenu_ryjbxx')
											.parent()
											.wrap(
													"<div id='ryjbxx_wrap' style='display:none;position:absolute;left:0;top:0;width:"
															+ $(window).width()
															+ "px;height:"
															+ $(window)
																	.height()
															+ "px;'></div>");
									$("#ryjbxx_wrap")
											.show()
											.bind(
													"click",
													function(event) {
														var ryjbxx_scope = $('#contextMenu_ryjbxx');
														if (event.pageX < ryjbxx_scope
																.offset().left
																|| event.pageX > (ryjbxx_scope
																		.offset().left + ryjbxx_scope
																		.width())
																|| event.pageY < ryjbxx_scope
																		.offset().top
																|| event.pageY > (ryjbxx_scope
																		.offset().top + ryjbxx_scope
																		.height())) {
															$("#ryjbxx_wrap")
																	.hide();
															$(
																	'#contextMenu_ryjbxx')
																	.window(
																			'close');
														}
													});
									$('#contextMenu_ryjbxx').window('open');
								} else {
									$("#ryjbxx_wrap").show();
									$('#contextMenu_ryjbxx').window('open');
									$("#ryjbxx_wrap").next()
											.css("zIndex", 9000);
								}
							}
						})
	})

	// 高度自适应
	/*
	 * $('#tscldjDetailId').datagrid('resize', { height :
	 * ($(window).height()-280) });
	 */
}
/**
 * 页面监区监室树
 * 
 * @returns
 */
var jqtree = function(id,idu) {

	$('#jqjsTree').tree({
		url : "/kss_jq/getJqTree",
		method : "get",
		loadFilter : function(data) {
			if (data.success) {
				return data.result;
			} else {
				$.messager.alert("提示", "监区树数据获取失败", "info");
				return {};
			}

		},

		//鼠标移上事件获取获取监区人数
		onLoadSuccess:function(node,data){
			$(".tree-hit").parent().addClass("treet");
			$(".tree-indent").parent().addClass("treet1");
			var rows = $('#jqjsTree').tree('getRoots');
			var row = $('#jqjsTree').tree('getRoot');
			var childrenNodes = $('#jqjsTree').tree('getChildren',row.target);
			var trId;
			var trrId;
			$(".treet").bind("mouseover",function(){
				trId=$(this).attr("id");
				trId = trId.replace("_easyui_tree_","");
				for(i=0;i<rows.length;i++){

	 				if(trId==i){
	 					var text1 =rows[i-1].text;
	 					var	num=text1.substring(text1.length-3,text1.length-2);
	 					
	 					$('.treet').tooltip({
		 					trackMouse:'true',
		 					content : '<div style="height: 50px;width:100px">'+
	 							  '<div class="top" style="font-size: 12px;margin-bottom: 5px;"></div>'+
		 							  '<div>人数:'+num+'人</div></div>'
		 					});
	 					
		 				}
		 			}
			});
	
	$(".treet1").bind("mouseover",function(){
			trrId=$(this).attr("id");
			trrId = trrId.replace(trrId,"");
			for(i=0;i<rows.length;i++){

 				if(trId==i){
 					var text1 =rows[i-1].text;
 					var	num=text1.substring(text1.length-3,text1.length-2);
 					
 					$('.treet1').tooltip({
	 					trackMouse:'true',
	 					content : '<div style="height: 50px;width:100px">'+
 							  '<div class="top" style="font-size: 12px;margin-bottom: 5px;"></div>'+
	 							  '<div>人数:'+num+'人</div></div>'
	 					});
 					
	 				}
	 			}
					

				});
			},


		onClick : function(node) {
			
			var data = {};
			var pageSize=$('#' +id).datagrid("getPager").pagination("options").pageSize;
			var pageIndex = $('#'+id).datagrid("getPager").pagination("options").pageNumber; 
			if (node.id.length == "2") {
				data = {
					"jsh$rightlike" : node.id,
				};

			} else {
				data = {
					"jsh$eq" : node.id,
				}
			}
			
			 $('#'+id).datagrid('clearSelections');
			 $('#'+idu).datagrid('clearSelections');
			 $('#'+idu).datagrid('loadData', { total: 0, rows: [] });
			 $('#'+idu).datagrid("selectRow", 0);
			 $('#'+id).datagrid('load', data);

		}
	});
}


/**
 * 高级查询监区监室树
 * 
 * @returns
 */
var jqtree1 = function(id,idu) {

	$('#jqjsTree1').tree({
		url : "/kss_jq/getJqTree",
		method : "get",
		loadFilter : function(data) {
			if (data.success) {
				return data.result;
			} else {
				$.messager.alert("提示", "监区树数据获取失败", "info");
				return {};
			}

		},
		
		
		onClick : function(node) {
			var data = {};
			var pageSize=$('#'+id).datagrid("getPager").pagination("options").pageSize;
			var pageIndex = $('#'+id).datagrid("getPager").pagination("options").pageNumber; 
			if (node.id.length == "2") {
				data = {
					"jsh$rightlike" : node.id,
				};

			} else {
				data = {
					"jsh$eq" : node.id,
				}
			}
			
			 $('#'+id).datagrid('clearSelections');
			 $('#'+idu).datagrid('clearSelections');
			 $('#'+idu).datagrid('loadData', { total: 0, rows: [] });
			 $('#'+idu).datagrid("selectRow", 0);
			 $('#'+id).datagrid('load', data);

		}
	});
}

	/**
	 * 显示隐藏列
	 */
	
	var ShowHide = function (name){
		$("#xsycl_"+name)
		.bind(
				"click",
				function() {
					if ($("#contextMenu_"+name).parent().is(":hidden")) {
						if ($("#"+name+"_wrap")[0] === undefined) {
							$('#contextMenu_'+name)
									.parent()
									.wrap(
											"<div id='"+name+"_wrap' style='display:none;position:absolute;left:0;top:0;width:"
													+ $(window).width()
													+ "px;height:"
													+ $(window).height()
													+ "px;'></div>");
							$("#"+name+"_wrap")
									.show()
									.bind(
											"click",
											function(event) {
												var _scope = $('#contextMenu_'+name);
												if (event.pageX < _scope
														.offset().left
														|| event.pageX > (_scope
																.offset().left + _scope
																.width())
														|| event.pageY < _scope
																.offset().top
														|| event.pageY > (_scope
																.offset().top + _scope
																.height())) {
													$("#"+name+"_wrap").hide();
													$('#contextMenu_'+name)
															.window('close');
												}
											});
							$('#contextMenu_'+name).window('open');
						} else {
							$("#"+name+"_wrap").show();
							$('#contextMenu_'+name).window('open');
							$("#"+name+"_wrap").next().css("zIndex", 9000);
						}
					}
				});

			$("#ycl_"+name+" tr span").bind('click',function(){
				var input=$(this).parents("td").prev().children("input[type='checkbox']");
		        //判断当前checkbox是否为选中状态
				input.prop("checked",!input.prop("checked"));
		       //根据checkbox选中状态判断隐藏显示列
				//closeJjxGridColumn(input);
				closeGridColumn(input);
	    	});
			$("#ycl_"+name+" tr input[type=checkbox]").bind("click", function() {
				closeGridColumn($(this));
        	});
        	var closeGridColumn = function(obj) {
        		$("#"+name+"DetailId").datagrid(
        				obj.prop("checked") ? "showColumn" : "hideColumn",
        				obj.attr("id"));
        	};
        	//鼠标变化
        	$(".change").hover(function(){
        		$(this).css("cursor","pointer");
        	})
        	
        	//点击显示隐藏列，需在主table添加class="xsyc"
        	$(".change tr").hover(
        			function(){$(this).css("background-color", "#d6e3f2")},
        			function(){$(this).css("background-color", "#ffffff")}				
        	).on('click',function(){
        		//获取checkbox
        		var input = $(this).find('input[type = checkbox]') 
        		
        		var name = input.prop('name')
        		//判断是否选中
        		if(input.prop("checked")){
        			input.prop("checked",false);
        		}else{
        			input.prop("checked",true);
        		};
        		
        		if(input.prop("checked")){
        			$(".xsyc").datagrid("showColumn",name);
        		}else{
        			$(".xsyc").datagrid("hideColumn",name);
        		};
        	});
	       
	}
	function wwjdt() { 
		setTimeout(function() {
			$(".datebox").click(function() {
				$(".datebox-calendar-inner .calendar-day:not(.calendar-other-month)").click(function() {
				$(".datebox-button-a:eq(1)").click();
				});
				$(".datebox-calendar-inner  .calendar-other-month,.calendar-header .calendar-nav").click(function() {
					setTimeout(function() {
						$(".datebox-calendar-inner .calendar-day:not(.calendar-other-month)").click(function() {
							$(".datebox-button-a:eq(1)").click();
							});
						$(".datebox-calendar-inner  .calendar-other-month,.calendar-header .calendar-nav").click(function() {
							setTimeout(function() {
								$(".datebox-calendar-inner .calendar-day:not(.calendar-other-month)").click(function() {
									$(".datebox-button-a:eq(1)").click();
									});
							},400);
						});
					},400);
				});
			});
		},500);
	}
	
	//获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
