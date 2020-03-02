define("pages/tasks", [
	"../bootstrap/awd.core",
	"../bootstrap/awd.datatable",
	],
function(require) {	
	var awd_core=require("../bootstrap/awd.core");
	var awd_datatable=require("../bootstrap/awd.datatable");
	
	var initview=function(){
		awd_core.init();
		awd_core.preload_remove("preloader","1000");
		awd_datatable.init_datatable(".dataTables-tasks",
				{
					url:"json/datatable.json",
					type:'GET',
					columnDefs:[
						{
							targets: [4],
							render: function (data, type, row, meta) {
								if (type === 'display') {
							           return '<button class="btn- btn-c-gradient-1 text-white mb-4 mr-3 ">编辑</button><button class="btn- btn-c-gradient-5 text-white mb-4 mr-3">删除</button>';
							     }
							     return data;
							}
						}
					]
				}
		);
	}
	//初始化视图
	initview();
});