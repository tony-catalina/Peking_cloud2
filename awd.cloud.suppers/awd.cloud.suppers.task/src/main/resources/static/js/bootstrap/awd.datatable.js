define("bootstrap/awd.datatable", [
	"../seajs/seajs-css",
	"../default-assets/data-table.min"
	],
function(require) {
	var _init_datatable=function(select,option){
		var _url=(option.url==undefined?"#":option.url);
		var _type=(option.type==undefined?"POST":option.type);
		var _columnDefs=(option.columnDefs==undefined?[]:option.columnDefs);
		$(select).DataTable({
			 processing: true,
			 serverSide: true,
			 defaultContent: "--",  
	         ajax:{
	        	   url: _url,
	        	   type: _type,
	        	   data: function (param) {                       // 传给服务器的数据，可以添加我们自己的查询参数
	        		 	console.log(param);
	        	        return param;
	        	   }
	         },
	         pageLength: 25,
	         responsive: true,
	         dom: '<"html5buttons-tables"B>lTfgitp',
	         buttons: [
	             {
	                 extend: 'copy',
	                 text:'复制'
	             },
	             {
	                 extend: 'csv',
		             text:'导出csv'
	             },
	             {
	                 extend: 'excel',
	                 text:'导出excel'
	             },
	             {
	                 extend: 'pdf',
	                 text:'导出pdf'
	             },
	             {
	                 extend: 'print',
	                 text:'打印',
	                 customize: function (win) {
	                     $(win.document.body).addClass('white-bg');
	                     $(win.document.body).css('font-size', '10px');

	                     $(win.document.body).find('table')
	                         .addClass('compact')
	                         .css('font-size', 'inherit');
	                 }
	            }
	        ],
	        language: {
	            "sProcessing": "处理中...",
	            "sLengthMenu": "显示 _MENU_ 项结果",
	            "sZeroRecords": "没有匹配结果",
	            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
	            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
	            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
	            "sInfoPostFix": "",
	            "sSearch": "搜索:",
	            "sUrl": "",
	            "sEmptyTable": "表中数据为空",
	            "sLoadingRecords": "载入中...",
	            "sInfoThousands": ",",
	            "oPaginate": {
	                "sFirst": "首页",
	                "sPrevious": "上页",
	                "sNext": "下页",
	                "sLast": "末页"
	            },
	            "oAria": {
	                "sSortAscending": ": 以升序排列此列",
	                "sSortDescending": ": 以降序排列此列"
	            }
	        },
	        createdRow: function (row, data, dataIndex) {
	        	   //console.log(data);
	        },
	        columnDefs:_columnDefs
	     });
	}
	return{
		init_datatable:_init_datatable
	}
});