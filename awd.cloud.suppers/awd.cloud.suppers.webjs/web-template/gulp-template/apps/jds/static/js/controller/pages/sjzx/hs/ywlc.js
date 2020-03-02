define([
    '/apps/jds/views/pages/common/jxcx.html',
    '/apps/jds/views/pages/common/zpwh.html',
    '/apps/jds/static/js/controller/pages/common/searchForm.js',
    '/apps/jds/static/js/controller/pages/common/dialog.js'
], function (require, exports, module) {
    var searchForm = require('/apps/jds/static/js/controller/pages/common/searchForm.js');
    var dialogForm = require('/apps/jds/static/js/controller/pages/common/dialog.js');
    // 搜索栏部分的内容
    var formOptions = {
        primary: [{
            title: '姓名',
            type: 'textbox'
        }, {
            title: '性别',
            type: 'combotree'
        }],
        more: [
            [
                { title: '出所开始时间', type: 'datetimebox' },
                { title: '出所结束时间', type: "datetimebox" },
                { title: '内部管理编号', type: 'textbox' },
            ], [
                 { title: '出所原因', type: 'textbox' },
            ]
        ]
    }

    var pageOptions = {
        jxcx: {
            title: '简项查询',
            controlButtonList: [
                 {
                    name: '办理',
                    icon: 'icon-jiahao',
                    command: 'new'
                }, {
                    name: '打印',
                    icon: 'icon-iconSVG-',
                    command: 'print'
                }, {
                    name: '导出',
                    icon: 'icon-daochu',
                    command: 'export'
                }
            ]
        }
    };
    var commandHandler = {
        'new': function (el) {
            var dialogOptions = {
                title:'收回信息',
                styles:'width:60%',
                dialogOptions:"  modal:true,closed:true",
                content:[
                    {
                        title:"病室好",
                        type:'textbox',
                        inputOptions:{}
                    },
                    {
                        title:"内部管理编号",
                        type:'textbox',
                        inputOptions:{}
                    },
                    {
                        title:"入所日期",
                        type:'datetimebox',
                        inputOptions:{}
                    },
                    {
                        title:"入所原因",
                        type:'combotree',
                        inputOptions:{}
                    },
                    {
                        title:"入所时间",
                        type:'datetimebox',
                        inputOptions:{}
                    },
                    {
                        title:"入所原因",
                        type:"textbox",
                        inputOptions:{}
                    },{
                        title:"入所法律文书号",
                        type:"textbox",
                        inputOptions:{}
                    }
                ]

            };

            el.appendChild(dialogForm.render(dialogOptions));
            $.parser.parse(el);
            $('#win').window('open');
        
        },
        'print': function () {
            alert("打印");
        },
        'export': function () {
            alert("导入");
        },
    }

    var jxcxTemplate = require('/apps/jds/views/pages/common/jxcx.html');

    var initPageData = function (el) {
        // 数据表格部分
        var $table = $('.tmpl-table', el);
        $table.datagrid({
            url: '/mock/jds/datagrid/hs',
            method: 'get',
            // 此处 fitColumns 可能会导致 easyui 的 datagrid 出现 bug，导致 .font-18 类名添加上一个样式 width: 116px
            // 此样式是通过 CSSRules 动态添加到 DOM 上的，不启用 fitColumns 或修复 easyui 的源码可解决此问题。
            fitColumns: true,
            height: window.innerHeight-170,  //窗口自适应
            pagination: true,
            rownumbers: true,
            singleSelect:true,
            columns: [[
                { field: 'ck', title: '', width: '3%',formatter: function(value, rowData, rowIndex){
                    return '<input type="radio" style="margin:0 auto" name="selectRadio" id="selectRadio"' + rowIndex + '    value="' + rowData.oid + '" />';
                } },
                { field: 'xm', title: '姓名', width: '5%' },
                { field: 'bm', title: '别名', width: "5%", },
                { field: 'xb', title: '性别', width: "5%", },
                { field: 'xzdabh', title: '内部管理编号', width: "12%" },
                { field: 'jsh', title: '戒室号', width: "5%", },
                { field: 'cssj', title: '出所日期', width: "13%", },
                { field: 'rsrq', title: '入所日期', width: "10%", },
                { field: 'csqx', title: '出所去向', width: "15%", },
                { field: 'rsyy', title: '入所原因', width: "15%",},
                { field: 'badw', title: '办案单位',  width: "12%",}
            ]],
            //单击行选中事件
            onClickRow: function(rowIndex, rowData){
                //加载完毕后获取所有的checkbox遍历
                var radio = $("input[type='radio']")[rowIndex].disabled;
                //如果当前的单选框不可选，则不让其选中
                if (radio!= true) {
                    //让点击的行单选按钮选中
                    $("input[type='radio']")[rowIndex].checked = true;
                }
                else {
                    $("input[type='radio']")[rowIndex].checked = false;
                }
            },
            //默认行选中事件
            onLoadSuccess:function(data){
                if(data.total>0){
                    $('.tmpl-table').datagrid("selectRow", 0);
                    $("input[name='selectRadio']")[0].checked = true;
                }
            },
            //查询参数
            queryParams: {
        		name: 'easyui',
        		subject: 'datagrid'
        	}
        });
    }


    var initPageEvents = function (el) {
        // 清空dialog-form
        var dialogForm = el.querySelector('.dialog-form');
        dialogForm.innerHTML = '';

        $('.section-controls', el).on('click', 'button', function () {
            var command = this.dataset.command;
            commandHandler[command](dialogForm);
        })
    }
    //表格宽度自适应
    $(window).resize(function(){
    	initPageData();
    });
    var render = function () {
        var element = document.createElement('div');
        element.classList.add('section-container', 'qa-container');
        element.innerHTML = template.render(jxcxTemplate, pageOptions.jxcx);
        element.querySelector(".section-content").prepend(searchForm.render(formOptions));
        initPageData(element);
        // 添加diglog div
        var dialogElement = document.createElement('div');
        dialogElement.className = 'dialog-form';
        element.appendChild(dialogElement);
        initPageEvents(element);
        return element;
    };
    exports.render = render;
});
