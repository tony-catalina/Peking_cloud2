	(function($) {
				function pagerFilter(data) {
					if($.isArray(data)) { // is array
						data = {
							total: data.length,
							rows: data
						}
					}
					var target = this;
					var dg = $(target);
					var state = dg.data('datagrid');
					var opts = dg.datagrid('options');
					if(!state.allRows) {
						state.allRows = (data.rows);
					}
					if(!opts.remoteSort && opts.sortName) {
						var names = opts.sortName.split(',');
						var orders = opts.sortOrder.split(',');
						state.allRows.sort(function(r1, r2) {
							var r = 0;
							for(var i = 0; i < names.length; i++) {
								var sn = names[i];
								var so = orders[i];
								var col = $(target).datagrid('getColumnOption', sn);
								var sortFunc = col.sorter || function(a, b) {
									return a == b ? 0 : (a > b ? 1 : -1);
								};
								r = sortFunc(r1[sn], r2[sn]) * (so == 'asc' ? 1 : -1);
								if(r != 0) {
									return r;
								}
							}
							return r;
						});
					}
					var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
					var end = start + parseInt(opts.pageSize);
					data.rows = state.allRows.slice(start, end);
					return data;
				}

				var loadDataMethod = $.fn.datagrid.methods.loadData;
				var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
				$.extend($.fn.datagrid.methods, {
					clientPaging: function(jq) {
						return jq.each(function() {
							var dg = $(this);
							var state = dg.data('datagrid');
							var opts = state.options;
							opts.loadFilter = pagerFilter;
							var onBeforeLoad = opts.onBeforeLoad;
							opts.onBeforeLoad = function(param) {
								state.allRows = null;
								return onBeforeLoad.call(this, param);
							}
							var pager = dg.datagrid('getPager');
							pager.pagination({
								onSelectPage: function(pageNum, pageSize) {
									opts.pageNumber = pageNum;
									opts.pageSize = pageSize;
									pager.pagination('refresh', {
										pageNumber: pageNum,
										pageSize: pageSize
									});
									dg.datagrid('loadData', state.allRows);
								}
							});
							$(this).datagrid('loadData', state.data);
							if(opts.url) {
								$(this).datagrid('reload');
							}
						});
					},
					loadData: function(jq, data) {
						jq.each(function() {
							$(this).data('datagrid').allRows = null;
						});
						return loadDataMethod.call($.fn.datagrid.methods, jq, data);
					},
					deleteRow: function(jq, index) {
						return jq.each(function() {
							var row = $(this).datagrid('getRows')[index];
							deleteRowMethod.call($.fn.datagrid.methods, $(this), index);
							var state = $(this).data('datagrid');
							if(state.options.loadFilter == pagerFilter) {
								for(var i = 0; i < state.allRows.length; i++) {
									if(state.allRows[i] == row) {
										state.allRows.splice(i, 1);
										break;
									}
								}
								$(this).datagrid('loadData', state.allRows);
							}
						});
					},
					getAllRows: function(jq) {
						return jq.data('datagrid').allRows;
					}
				})
			})(jQuery);

			function getData() {
				var rows = [];
				for(var i = 1; i <= 80; i++) {
					rows.push({
						code_1: ' <input type="radio" name="radio-btn" />',
						code_2: '  ',
						code_3: '0201',
						name: '冉冉',
						level: '0201',
						provide: '20171017  9:22',
						full: '一审',
						issubmit: '本省市转入',
						status: '20171012 19:46',
						note: '入监异常谈话'
					});
				}
				return rows;
			}

			$(function() {
				$('#dg').datagrid({ data: getData() }).datagrid('clientPaging');
			});
			$(function() {
				$('#dg1').datagrid({ data: getData() }).datagrid('clientPaging');
			});

			$(function() {
				$('#dg2').datagrid({ data: getData() }).datagrid('clientPaging');
			});
			$(function() {
				$('#dg3').datagrid({ data: getData() }).datagrid('clientPaging');
			});
			$(function() {
				$('#dg4').datagrid({ data: getData() }).datagrid('clientPaging');
			});
			$(function() {
				$('#dg5').datagrid({ data: getData() }).datagrid('clientPaging');
			});
			