define("common/componts", [
	"./easyui/jquery-easyui-1.5.1/jquery.easyui.min",
	"../easyui/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN"
	],
function(require) {
	var _initTable=function(id,options){
		var table;
		var _title=options['title']!=undefined?options['title']:'';
		var _url=options['url']!=undefined?options['url']:'';
		var _width=options['width']!=undefined?options['width']:'100%';
		var _height=options['height']!=undefined?options['height']:'100%';
		var _fitColumns=options['fitColumns']!=undefined?options['fitColumns']:true;
		var _singleSelect=options['singleSelect']!=undefined?options['singleSelect']:true;
		var _rownumbers=options['rownumbers']!=undefined?options['rownumbers']:true;
		var _method=options['method']!=undefined?options['method']:'post';
		var _toolbar=options['toolbar']!=undefined?options['toolbar']:'';
		var _columns=options['columns']!=undefined?options['columns']:[];
		var _idField=options['id']!=undefined?options['id']:'id';
		var _loadMsg=options['loadMsg']!=undefined?options['loadMsg']:'加载中, 请  稍  等  …';
		var _pagination=options['pagination']!=undefined?options['pagination']:true;
		var _sortName=options['sortName']!=undefined?options['sortName']:'id';
		var _sortOrder=options['sortOrder']!=undefined?options['sortOrder']:'desc';
		var _rowStyler=options['rowStyler']!=undefined?options['rowStyler']:function(){};
		var _onLoadSuccess=options['onLoadSuccess']!=undefined?options['onLoadSuccess']:function(){
			if(_singleSelect){
				var selectRow = $('#'+id).datagrid("getSelected");
				if (selectRow != null && selectRow != undefined) {
					var rowIndex = $('#'+id).datagrid("getRowIndex",selectRow);
					if (rowIndex >= 0) {
						$("input[name='selectRadio']")[rowIndex].checked = true;
					}
				}
			}
		};
		var _pageSize=options['pageSize']!=undefined?options['pageSize']:'20';
		var _queryParams=options['queryParams']!=undefined?options['queryParams']:{};
		var _detailview=options['detailview']!=undefined?options['detailview']:detailview();
		var _detailFormatter=options['detailFormatter']!=undefined?options['detailFormatter']:function(){};
		var _onClickRow=options['onClickRow']!=undefined?options['onClickRow']:function(rowIndex, rowData){
			if(_singleSelect){
				//加载完毕后获取所有的checkbox遍历		    	
	            var radio = $("input[name='selectRadio']")[rowIndex].disabled;
	            //如果当前的单选框不可选，则不让其选中
	            if (radio!= true) {
	                //让点击的行单选按钮选中
	                $("input[name='selectRadio']")[rowIndex].checked = true;
	            }
	            else {
	                $("input[name='selectRadio']")[rowIndex].checked = false;
	            }
			}
            
        };
		var _onDblClickRow=options['onDblClickRow']!=undefined?options['onDblClickRow']:function(){};
		var _firstLoad=options['firstLoad']!=undefined?options['firstLoad']:false;
		var _onSelect=options['onSelect']!=undefined?options['onSelect']:function(){};
		var _onRowContextMenu=options['onRowContextMenu']!=undefined?options['onRowContextMenu']:function(){};
		var _onSortColumn=options['onSortColumn']!=undefined?options['onSortColumn']:function(){};
		var _onBeforeLoad=options['onBeforeLoad']!=undefined?options['onBeforeLoad']:function(){};
		var _getUrl=function(){
			if(_firstLoad){				
				return _url;
			}else{
				return "";
			}				
		}
		table=$('#'+id).datagrid({
		    title: _title,
		    width: _width,
		    height: _height,
		    fitColumns: _fitColumns,
	        singleSelect:_singleSelect,
	        rownumbers:_rownumbers,
	        method:_method,
	        url:_getUrl(),
	        toolbar:_toolbar,
	        //对应json数据中的每一列
	        columns : [_columns],
	        idField:_idField,
	        loadMsg:_loadMsg,
	        pagination:_pagination,
	        sortName:_sortName,
	        sortOrder:_sortOrder,
	        onLoadSuccess: _onLoadSuccess,
	        queryParams:_queryParams,
	        onClickRow: _onClickRow,
	        onSortColumn: _onSortColumn,
	        onBeforeLoad: _onBeforeLoad,
	        rowStyler: _rowStyler,
	        onSelect:_onSelect,
	        pageSize:_pageSize,
	        view: _detailview,
	        detailFormatter:_detailFormatter,
	        onDblClickRow:_onDblClickRow,
	        onRowContextMenu:_onRowContextMenu,
			});
			var opts = $('#'+id).datagrid("options");//点击查询加载数据
			opts.url = _url;
			return table;
	}
	
	
	
    /**
	 * 显示隐藏列
	 * tmp:业务表的显示隐藏地址
	 */
	var _ShowHide = function (name,tmp){
		var htmlcompile = template.compile(tmp);
		var html = htmlcompile({});
		$("#"+name+"yc").html(html);		
	    $.parser.parse($("#"+name+"dialog").parent());	
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
				closeGridColumn(input);
	    	});
			$("#ycl_"+name+" tr input[type=checkbox]").bind("click", function() {
				closeGridColumn($(this));
        	});
        	var closeGridColumn = function(obj) {
        		$("#"+name+"Id").datagrid(
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
	
	var _initSelect=function(id,form,classid){
		if(id!=undefined){
			var fieldName=$("#"+id).prev().next().attr("code") ;
			if(fieldName==undefined){
				fieldName=$("#"+id).attr("code");
			}
			var url=$(this).attr("url");
			$("#"+id).combobox({
			    url:'',
			    valueField:'code',
			    textField:'content',
			    multiple:$("#"+id).attr("checkBox")=="true",
		    	onShowPanel:function(){
		    		if(url==""||url==undefined||url==null){
		                $("#"+id).combobox('reload','/kss_dictionary/getDictionary?node='+fieldName);
		    		}else{
		                $("#"+id).combobox('reload',url);
		    		}
		          }
			});
		}
		if(form!=undefined||classid!=undefined){
			var inputs;
			if(form!=undefined){
				inputs=$('from .'+form);
				inputs.find('.'+classid).each(function(){
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
			}else{
				$('.'+classid).each(function(){
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
			
			
		}else{
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
		
	}
	var _initSelectPage=function(id){
		var select;
		if(id!=undefined){
			select="#"+id;
		}else{
			select='.awdSelectPage';
		}
		$(select).each(function(){
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
	var detailview=function(){
		$.extend($.fn.datagrid.defaults, {
			autoUpdateDetail: true  // Define if update the row detail content when update a row
		});

		var detailview = $.extend({}, $.fn.datagrid.defaults.view, {
			render: function(target, container, frozen){
				var state = $.data(target, 'datagrid');
				var opts = state.options;
				if (frozen){
					if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))){
						return;

					}
				}

				var rows = state.data.rows;
				var fields = $(target).datagrid('getColumnFields', frozen);
				var table = [];
				table.push('<table class="datagrid-btable" cellspacing="0" cellpadding="0" border="0"><tbody>');
				for(var i=0; i<rows.length; i++) {
					// get the class and style attributes for this row
					var css = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : '';
					var classValue = '';
					var styleValue = '';
					if (typeof css == 'string'){
						styleValue = css;
					} else if (css){
						classValue = css['class'] || '';
						styleValue = css['style'] || '';
					}

					var cls = 'class="datagrid-row ' + (i % 2 && opts.striped ? 'datagrid-row-alt ' : ' ') + classValue + '"';
					var style = styleValue ? 'style="' + styleValue + '"' : '';
					var rowId = state.rowIdPrefix + '-' + (frozen?1:2) + '-' + i;
					table.push('<tr id="' + rowId + '" datagrid-row-index="' + i + '" ' + cls + ' ' + style + '>');
					table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
					table.push('</tr>');

					table.push('<tr style="display:none;">');
					if (frozen){
						table.push('<td colspan=' + (fields.length+(opts.rownumbers?1:0)) + ' style="border-right:0">');
					} else {
						table.push('<td colspan=' + (fields.length) + '>');
					}

					table.push('<div class="datagrid-row-detail">');
					if (frozen){
						table.push('&nbsp;');
					} else {
						table.push(opts.detailFormatter.call(target, i, rows[i]));
					}
					table.push('</div>');

					table.push('</td>');
					table.push('</tr>');

				}
				table.push('</tbody></table>');

				$(container).html(table.join(''));
			},

			renderRow: function(target, fields, frozen, rowIndex, rowData){
				var opts = $.data(target, 'datagrid').options;

				var cc = [];
				if (frozen && opts.rownumbers){
					var rownumber = rowIndex + 1;
					if (opts.pagination){
						rownumber += (opts.pageNumber-1)*opts.pageSize;
					}
					cc.push('<td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber">'+rownumber+'</div></td>');
				}
				for(var i=0; i<fields.length; i++){
					var field = fields[i];
					var col = $(target).datagrid('getColumnOption', field);
					if (col){
						var value = rowData[field];	// the field value
						var css = col.styler ? (col.styler(value, rowData, rowIndex)||'') : '';
						var classValue = '';
						var styleValue = '';
						if (typeof css == 'string'){
							styleValue = css;
						} else if (cc){
							classValue = css['class'] || '';
							styleValue = css['style'] || '';
						}
						var cls = classValue ? 'class="' + classValue + '"' : '';
						var style = col.hidden ? 'style="display:none;' + styleValue + '"' : (styleValue ? 'style="' + styleValue + '"' : '');

						cc.push('<td field="' + field + '" ' + cls + ' ' + style + '>');

						if (col.checkbox){
							style = '';
						} else if (col.expander){
							style = "text-align:center;height:16px;";
						} else {
							style = styleValue;
							if (col.align){style += ';text-align:' + col.align + ';'}
							if (!opts.nowrap){
								style += ';white-space:normal;height:auto;';
							} else if (opts.autoRowHeight){
								style += ';height:auto;';
							}
						}

						cc.push('<div style="' + style + '" ');
						if (col.checkbox){
							cc.push('class="datagrid-cell-check ');
						} else {
							cc.push('class="datagrid-cell ' + col.cellClass);
						}
						cc.push('">');

						if (col.checkbox){
							cc.push('<input type="checkbox" name="' + field + '" value="' + (value!=undefined ? value : '') + '">');
						} else if (col.expander) {
							//cc.push('<div style="text-align:center;width:16px;height:16px;">');
							cc.push('<span class="datagrid-row-expander datagrid-row-expand" style="display:inline-block;width:16px;height:16px;cursor:pointer;" />');
							//cc.push('</div>');
						} else if (col.formatter){
							cc.push(col.formatter(value, rowData, rowIndex));
						} else {
							cc.push(value);
						}

						cc.push('</div>');
						cc.push('</td>');
					}
				}
				return cc.join('');
			},

			insertRow: function(target, index, row){
				var opts = $.data(target, 'datagrid').options;
				var dc = $.data(target, 'datagrid').dc;
				var panel = $(target).datagrid('getPanel');
				var view1 = dc.view1;
				var view2 = dc.view2;

				var isAppend = false;
				var rowLength = $(target).datagrid('getRows').length;
				if (rowLength == 0){
					$(target).datagrid('loadData',{total:1,rows:[row]});
					return;
				}

				if (index == undefined || index == null || index >= rowLength) {
					index = rowLength;
					isAppend = true;
					this.canUpdateDetail = false;
				}

				$.fn.datagrid.defaults.view.insertRow.call(this, target, index, row);

				_insert(true);
				_insert(false);

				this.canUpdateDetail = true;

				function _insert(frozen){
					var tr = opts.finder.getTr(target, index, 'body', frozen?1:2);
					if (isAppend){
						var detail = tr.next();
						var newDetail = tr.next().clone();
						tr.insertAfter(detail);
					} else {
						var newDetail = tr.next().next().clone();
					}
					newDetail.insertAfter(tr);
					newDetail.hide();
					if (!frozen){
						newDetail.find('div.datagrid-row-detail').html(opts.detailFormatter.call(target, index, row));
					}
				}
			},

			deleteRow: function(target, index){
				var opts = $.data(target, 'datagrid').options;
				var dc = $.data(target, 'datagrid').dc;
				var tr = opts.finder.getTr(target, index);
				tr.next().remove();
				$.fn.datagrid.defaults.view.deleteRow.call(this, target, index);
				dc.body2.triggerHandler('scroll');
			},

			updateRow: function(target, rowIndex, row){
				var dc = $.data(target, 'datagrid').dc;
				var opts = $.data(target, 'datagrid').options;
				var cls = $(target).datagrid('getExpander', rowIndex).attr('class');
				$.fn.datagrid.defaults.view.updateRow.call(this, target, rowIndex, row);
				$(target).datagrid('getExpander', rowIndex).attr('class',cls);

				// update the detail content
				if (opts.autoUpdateDetail && this.canUpdateDetail){
					var row = $(target).datagrid('getRows')[rowIndex];
					var detail = $(target).datagrid('getRowDetail', rowIndex);
					detail.html(opts.detailFormatter.call(target, rowIndex, row));
				}
			},

			bindEvents: function(target){
				var state = $.data(target, 'datagrid');

				if (state.ss.bindDetailEvents){return;}
				state.ss.bindDetailEvents = true;

				var dc = state.dc;
				var opts = state.options;
				var body = dc.body1.add(dc.body2);
				var clickHandler = ($.data(body[0],'events')||$._data(body[0],'events')).click[0].handler;
				body.unbind('click').bind('click', function(e){
					var tt = $(e.target);
					var tr = tt.closest('tr.datagrid-row');
					if (!tr.length){return}
					if (tt.hasClass('datagrid-row-expander')){
						var rowIndex = parseInt(tr.attr('datagrid-row-index'));
						if (tt.hasClass('datagrid-row-expand')){
							$(target).datagrid('expandRow', rowIndex);
						} else {
							$(target).datagrid('collapseRow', rowIndex);
						}
						$(target).datagrid('fixRowHeight');

					} else {
						clickHandler(e);
					}
					e.stopPropagation();
				});
			},

			onBeforeRender: function(target){
				var state = $.data(target, 'datagrid');
				var opts = state.options;
				var dc = state.dc;
				var t = $(target);
				var hasExpander = false;
				var fields = t.datagrid('getColumnFields',true).concat(t.datagrid('getColumnFields'));
				for(var i=0; i<fields.length; i++){
					var col = t.datagrid('getColumnOption', fields[i]);
					if (col.expander){
						hasExpander = true;
						break;
					}
				}
				if (!hasExpander){
					if (opts.frozenColumns && opts.frozenColumns.length){
						opts.frozenColumns[0].splice(0,0,{field:'_expander',expander:true,width:24,resizable:false,fixed:true});
					} else {
						opts.frozenColumns = [[{field:'_expander',expander:true,width:24,resizable:false,fixed:true}]];
					}

					var t = dc.view1.children('div.datagrid-header').find('table');
					var td = $('<td rowspan="'+opts.frozenColumns.length+'"><div class="datagrid-header-expander" style="width:24px;"></div></td>');
					if ($('tr',t).length == 0){
						td.wrap('<tr></tr>').parent().appendTo($('tbody',t));
					} else if (opts.rownumbers){
						td.insertAfter(t.find('td:has(div.datagrid-header-rownumber)'));
					} else {
						td.prependTo(t.find('tr:first'));
					}
				}

				// if (!state.bindDetailEvents){
				// 	state.bindDetailEvents = true;
				// 	var that = this;
				// 	setTimeout(function(){
				// 		that.bindEvents(target);
				// 	},0);
				// }
			},

			onAfterRender: function(target){
				var that = this;
				var state = $.data(target, 'datagrid');
				var dc = state.dc;
				var opts = state.options;
				var panel = $(target).datagrid('getPanel');

				$.fn.datagrid.defaults.view.onAfterRender.call(this, target);

				if (!state.onResizeColumn){
					state.onResizeColumn = opts.onResizeColumn;
					opts.onResizeColumn = function(field, width){
						if (!opts.fitColumns){
							resizeDetails();
						}
						var rowCount = $(target).datagrid('getRows').length;
						for(var i=0; i<rowCount; i++){
							$(target).datagrid('fixDetailRowHeight', i);
						}

						// call the old event code
						state.onResizeColumn.call(target, field, width);
					};
				}
				if (!state.onResize){
					state.onResize = opts.onResize;
					opts.onResize = function(width, height){
						if (opts.fitColumns){
							resizeDetails();
						}
						state.onResize.call(panel, width, height);
					};
				}

				// function resizeDetails(){
				// 	var details = dc.body2.find('>table.datagrid-btable>tbody>tr>td>div.datagrid-row-detail:visible');
				// 	if (details.length){
				// 		var ww = 0;
				// 		dc.header2.find('.datagrid-header-check:visible,.datagrid-cell:visible').each(function(){
				// 			ww += $(this).outerWidth(true) + 1;
				// 		});
				// 		if (ww != details.outerWidth(true)){
				// 			details._outerWidth(ww);
				// 			details.find('.easyui-fluid').trigger('_resize');
				// 		}
				// 	}
				// }
				function resizeDetails(){
					var details = dc.body2.find('>table.datagrid-btable>tbody>tr>td>div.datagrid-row-detail:visible');
					if (details.length){
						var ww = 0;
						// dc.header2.find('.datagrid-header-check:visible,.datagrid-cell:visible').each(function(){
						// 	ww += $(this).outerWidth(true) + 1;
						// });
						dc.body2.find('>table.datagrid-btable>tbody>tr:visible:first').find('.datagrid-cell-check:visible,.datagrid-cell:visible').each(function(){
							ww += $(this).outerWidth(true) + 1;
						});
						if (ww != details.outerWidth(true)){
							details._outerWidth(ww);
							details.find('.easyui-fluid').trigger('_resize');
						}
					}
				}


				this.canUpdateDetail = true;	// define if to update the detail content when 'updateRow' method is called;

				var footer = dc.footer1.add(dc.footer2);
				footer.find('span.datagrid-row-expander').css('visibility', 'hidden');
				$(target).datagrid('resize');

				this.bindEvents(target);
				var detail = dc.body1.add(dc.body2).find('div.datagrid-row-detail');
				detail.unbind().bind('mouseover mouseout click dblclick contextmenu scroll', function(e){
					e.stopPropagation();
				});
			}
		});

		$.extend($.fn.datagrid.methods, {
			fixDetailRowHeight: function(jq, index){
				return jq.each(function(){
					var opts = $.data(this, 'datagrid').options;
					if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))){
						return;
					}
					var dc = $.data(this, 'datagrid').dc;
					var tr1 = opts.finder.getTr(this, index, 'body', 1).next();
					var tr2 = opts.finder.getTr(this, index, 'body', 2).next();
					// fix the detail row height
					if (tr2.is(':visible')){
						tr1.css('height', '');
						tr2.css('height', '');
						var height = Math.max(tr1.height(), tr2.height());
						tr1.css('height', height);
						tr2.css('height', height);
					}
					dc.body2.triggerHandler('scroll');
				});
			},
			getExpander: function(jq, index){	// get row expander object
				var opts = $.data(jq[0], 'datagrid').options;
				return opts.finder.getTr(jq[0], index).find('span.datagrid-row-expander');
			},
			// get row detail container
			getRowDetail: function(jq, index){
				var opts = $.data(jq[0], 'datagrid').options;
				var tr = opts.finder.getTr(jq[0], index, 'body', 2);
				// return tr.next().find('div.datagrid-row-detail');
				return tr.next().find('>td>div.datagrid-row-detail');
			},
			expandRow: function(jq, index){
				return jq.each(function(){
					var opts = $(this).datagrid('options');
					var dc = $.data(this, 'datagrid').dc;
					var expander = $(this).datagrid('getExpander', index);
					if (expander.hasClass('datagrid-row-expand')){
						expander.removeClass('datagrid-row-expand').addClass('datagrid-row-collapse');
						var tr1 = opts.finder.getTr(this, index, 'body', 1).next();
						var tr2 = opts.finder.getTr(this, index, 'body', 2).next();
						tr1.show();
						tr2.show();
						$(this).datagrid('fixDetailRowHeight', index);
						if (opts.onExpandRow){
							var row = $(this).datagrid('getRows')[index];
							opts.onExpandRow.call(this, index, row);
						}
					}
				});
			},
			collapseRow: function(jq, index){
				return jq.each(function(){
					var opts = $(this).datagrid('options');
					var dc = $.data(this, 'datagrid').dc;
					var expander = $(this).datagrid('getExpander', index);
					if (expander.hasClass('datagrid-row-collapse')){
						expander.removeClass('datagrid-row-collapse').addClass('datagrid-row-expand');
						var tr1 = opts.finder.getTr(this, index, 'body', 1).next();
						var tr2 = opts.finder.getTr(this, index, 'body', 2).next();
						tr1.hide();
						tr2.hide();
						dc.body2.triggerHandler('scroll');
						if (opts.onCollapseRow){
							var row = $(this).datagrid('getRows')[index];
							opts.onCollapseRow.call(this, index, row);
						}
					}
				});
			}
		});

		$.extend($.fn.datagrid.methods, {
			subgrid: function(jq, conf){
				return jq.each(function(){
					createGrid(this, conf);

					function createGrid(target, conf, prow){
						var queryParams = $.extend({}, conf.options.queryParams||{});
						// queryParams[conf.options.foreignField] = prow ? prow[conf.options.foreignField] : undefined;
						if (prow){
							var fk = conf.options.foreignField;
							if ($.isFunction(fk)){
								$.extend(queryParams, fk.call(conf, prow));
							} else {
								queryParams[fk] = prow[fk];
							}
						}

						var plugin = conf.options.edatagrid ? 'edatagrid' : 'datagrid';

						$(target)[plugin]($.extend({}, conf.options, {
							subgrid: conf.subgrid,
							view: (conf.subgrid ? detailview : undefined),
							queryParams: queryParams,
							detailFormatter: function(index, row){
								return '<div><table class="datagrid-subgrid"></table></div>';
							},
							onExpandRow: function(index, row){
								var opts = $(this).datagrid('options');
								var rd = $(this).datagrid('getRowDetail', index);
								var dg = getSubGrid(rd);
								if (!dg.data('datagrid')){
									createGrid(dg[0], opts.subgrid, row);
								}
								rd.find('.easyui-fluid').trigger('_resize');
								setHeight(this, index);
								if (conf.options.onExpandRow){
									conf.options.onExpandRow.call(this, index, row);
								}
							},
							onCollapseRow: function(index, row){
								setHeight(this, index);
								if (conf.options.onCollapseRow){
									conf.options.onCollapseRow.call(this, index, row);
								}
							},
							onResize: function(){
								var dg = $(this).children('div.datagrid-view').children('table')
								setParentHeight(this);
							},
							onResizeColumn: function(field, width){
								setParentHeight(this);
								if (conf.options.onResizeColumn){
									conf.options.onResizeColumn.call(this, field, width);
								}
							},
							onLoadSuccess: function(data){
								setParentHeight(this);
								if (conf.options.onLoadSuccess){
									conf.options.onLoadSuccess.call(this, data);
								}
							}
						}));
					}
					function getSubGrid(rowDetail){
						var div = $(rowDetail).children('div');
						if (div.children('div.datagrid').length){
							return div.find('>div.datagrid>div.panel-body>div.datagrid-view>table.datagrid-subgrid');
						} else {
							return div.find('>table.datagrid-subgrid');
						}
					}
					function setParentHeight(target){
						var tr = $(target).closest('div.datagrid-row-detail').closest('tr').prev();
						if (tr.length){
							var index = parseInt(tr.attr('datagrid-row-index'));
							var dg = tr.closest('div.datagrid-view').children('table');
							setHeight(dg[0], index);
						}
					}
					function setHeight(target, index){
						$(target).datagrid('fixDetailRowHeight', index);
						$(target).datagrid('fixRowHeight', index);
						var tr = $(target).closest('div.datagrid-row-detail').closest('tr').prev();
						if (tr.length){
							var index = parseInt(tr.attr('datagrid-row-index'));
							var dg = tr.closest('div.datagrid-view').children('table');
							setHeight(dg[0], index);
						}
					}
				});
			},
			getSelfGrid: function(jq){
				var grid = jq.closest('.datagrid');
				if (grid.length){
					return grid.find('>.datagrid-wrap>.datagrid-view>.datagrid-f');
				} else {
					return null;
				}
			},
			getParentGrid: function(jq){
				var detail = jq.closest('div.datagrid-row-detail');
				if (detail.length){
					return detail.closest('.datagrid-view').children('.datagrid-f');
				} else {
					return null;
				}
			},
			getParentRowIndex: function(jq){
				var detail = jq.closest('div.datagrid-row-detail');
				if (detail.length){
					var tr = detail.closest('tr').prev();
					return parseInt(tr.attr('datagrid-row-index'));
				} else {
					return -1;
				}
			}
		});
		return detailview;
	}
	var _initTree=function(id){
				var select;
				if(id!=undefined){
					select="#"+id;
				}else{
					select='.awdSelectTree';
				}
				$(select).each(function(){
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
	var _awdSelectJs = function(){
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
	var _initSelectJs=function(id){
		
	}
	
	//监室编号下拉框获取
	var _awdJsxxSelect=function(){
		var fieldName=$('.jsxxSelect').prev().next().attr("code") ;
		if(fieldName==undefined){
			fieldName=$('.jsxxSelect').attr("code");
		}
		$('.jsxxSelect').combobox({
			    valueField:'jsh',
			    textField:'jsmc',
		    	onShowPanel:function(){
		    		var data ;
		    		$.ajax({
		    			type : "POST", 
		    	    	url : "/kss_js/jsList",
		    	    	async:false,
		    	     	data : '{}',
		    	        success:function(jq_result){
		    	        	data = jq_result.rows;
		    	        	return data;
		    	        }
		    		});
		    		$('.jsxxSelect').combobox('loadData',data);
		          },
		        onSelect: function () { 
			        	//选中处理
		        	  if($('.jsxxSelect').attr("checkBox")=="true"){
				        	$('.jsxxSelect').val($('input[code="'+fieldName+'"]').combobox('getValues'));  
		        	  }else{
				        	$('.jsxxSelect').val($('input[code="'+fieldName+'"]').combobox('getValue'));  
		        	  }
		        }
			});
	}
	function toHtml(target, rows){rows = rows || getRows(target);
    var dg = $(target);
    var data = ['<table border="1" rull="all" style="border-collapse:collapse">'];
    var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields',false));
    var trStyle = 'height:32px';
    var tdStyle0 = 'vertical-align:middle;padding:0 4px';
    data.push('<tr style="'+trStyle+'">');
    for(var i=0; i<fields.length; i++){
        var col = dg.datagrid('getColumnOption', fields[i]);
        var tdStyle = tdStyle0 + ';width:'+col.boxWidth+'px;';
        data.push('<th style="'+tdStyle+'">'+col.title+'</th>');
    }
    data.push('</tr>');
    $.map(rows, function(row){
        data.push('<tr style="'+trStyle+'">');
        for(var i=0; i<fields.length; i++){
            var field = fields[i];
            console.log(field)
            if(field == "_expander" ){
            	continue;
            }
            if(field == "oid"){
            	continue;
            }
            if(field == null){
            	continue;
            }
            data.push(
                '<td style="'+tdStyle0+'">'+row.field+'</td>'
            );
        }
        data.push('</tr>');
    });
    data.push('</table>');
    return data.join('');
    }

    function toArray(target, rows){
        rows = rows ;
        var dg = $(target);
        var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields',false));
        var data = [];
        var r = [];
        for(var i=0; i<fields.length; i++){
            var col = dg.datagrid('getColumnOption', fields[i]);
            r.push(col.title);
        }
        data.push(r);
        $.map(rows, function(row){
            var r = [];
            for(var i=0; i<fields.length; i++){
                r.push(row[fields[i]]);
            }
            data.push(r);
        });
        return data;
    }

    function print(target, param){
        var title = null;
        var rows = null;
        if (typeof param == 'string'){
            title = param;
        } else {
            title = param['title'];
            rows = param['rows'];
        }
        var newWindow = window.open('', '', 'width=800, height=500');
        var document = newWindow.document.open();
        var content = 
            '<!doctype html>' +
            '<html>' +
            '<head>' +
            '<meta charset="utf-8">' +
            '<title>'+title+'</title>' +
            '</head>' +
            '<body>' + toHtml(target, rows) + '</body>' +
            '</html>';
        document.write(content);
        document.close();
        newWindow.print();
    }
	 function toExcel(target, param){
	        var filename = null;
	        var rows = null;
	        var worksheet = 'Worksheet';
	        if (typeof param == 'string'){
	            filename = param;
	        } else {
	            filename = param['filename'];
	            rows = param['rows'];
	            worksheet = param['worksheet'] || 'Worksheet';
	        }
	        var dg = $(target);
	        var uri = 'data:application/vnd.ms-excel;base64,'
	        , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body>{table}</body></html>'
	        , base64 = function (s) { return window.btoa(unescape(encodeURIComponent(s))) }
	        , format = function (s, c) { return s.replace(/{(\w+)}/g, function (m, p) { return c[p]; }) }

	        var table = toHtml(target, rows);
	        var ctx = { worksheet: worksheet, table: table };
	        var data = base64(format(template, ctx));
	        if (window.navigator.msSaveBlob){
	            var blob = b64toBlob(data);
	            window.navigator.msSaveBlob(blob, filename);
	        } else {
	            var alink = $('<a style="display:none"></a>').appendTo('body');
	            
	            alink[0].href = uri + data;
	            alink[0].download = filename;
	            alink[0].click();
	            alink.remove();
	        }
	    }
	    $.extend($.fn.datagrid.methods, {
	        toHtml: function(jq, rows){
	            return toHtml(jq[0], rows);
	        },
	        toArray: function(jq, rows){
	            return toArray(jq[0], rows);
	        },
	        toExcel: function(jq, param){
	            return jq.each(function(){
	                toExcel(this, param);
	            });
	        },
	        print: function(jq, param){
	            return jq.each(function(){
	                print(this, param);
	            });
	        }
	    });
	    
	return{
		initTable:_initTable,
		ShowHide:_ShowHide,
		initSelect:_initSelect,
		initSelectPage:_initSelectPage,
		initTree:_initTree,
		initSelectJs:_initSelectJs,
		awdSelectJs:_awdSelectJs,
		awdJsxxSelect:_awdJsxxSelect
	}
});

