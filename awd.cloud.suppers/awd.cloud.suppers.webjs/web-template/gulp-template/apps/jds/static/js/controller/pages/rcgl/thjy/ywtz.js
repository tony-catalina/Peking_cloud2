define([
    'jquery',
    'easyui',
    'easyui_ZH',
    'arttemplate',
    '/apps/jds/views/pages/common/jxcx.html',
    '/apps/jds/views/pages/common/zpwh.html',
    '/apps/jds/static/js/controller/pages/common/searchForm.js',
    '/apps/jds/static/js/controller/pages/common/dialog.js'
], function (require, exports, module) {
    require('jquery');
    require('easyui');
    require('easyui_ZH');
    require('arttemplate');
    var searchForm = require('/apps/jds/static/js/controller/pages/common/searchForm.js');
    var dialogForm = require('/apps/jds/static/js/controller/pages/common/dialog.js');
    // 搜索栏部分的内容
    var formOptions = {
        primary: [{
            title: '姓名',
            type: 'textbox'
        }, {
            title: '出生日期',
            type: 'datetimebox'
        }],
        more: [
            [
                {title:'性别',type:'combotree'},
                {title:'戒毒期限',type:'datetimebox'}         
            ],[
              {title:'戒室号',type:'combotree'},
              {title:'文化程度',type:'combotree'}
            ]
        ]
    }

    var pageOptions = {
        jxcx: {
            title: '业务台账',
            controlButtonList: [
                 
            ]
        }
    };
    var commandHandler = {
        'new': function (el) {
            var dialogOptions = {
                title:'新建',
                styles:'width:60%',
                dialogOptions:"  modal:true,closed:true",
                content:[
                    {
                        title:"姓名",
                        type:'textbox',
                        inputOptions:{}
                    }
                ]

            };

            el.appendChild(dialogForm.render(dialogOptions));
            $.parser.parse(el);
            $('#win').window('open');
        
        }// },
        // 'print': function () {
        //     alert("打印");
        // }
    }

    var jxcxTemplate = require('/apps/jds/views/pages/common/jxcx.html');
    var zpwhHtml = require('/apps/jds/views/pages/common/zpwh.html');

    var initPageData = function (el) {
        // 数据表格部分
        var $table = $('.tmpl-table', el);
        $table.datagrid({
            url: '/mock/jds/datagrid/list',
            method: 'get',

            // 此处 fitColumns 可能会导致 easyui 的 datagrid 出现 bug，导致 .font-18 类名添加上一个样式 width: 116px
            // 此样式是通过 CSSRules 动态添加到 DOM 上的，不启用 fitColumns 或修复 easyui 的源码可解决此问题。
            fitColumns: true,

            pagination: true,
            rownumbers: true,
            columns: [[
                { field: 'ck', title: '', checkbox: true, width: '3%' },
                { field: 'name', title: '姓名', width: '7%' },
                { field: 'age', title: '谈话开始时间', width: "15%", },
                { field: 'firstTalkingTime', title: '谈话地点', width: "20%", },
                { field: 'personInCharge', title: '谈话民警', width: "13%" },
                { field: 'title', title: '谈话类型', width: "20%", },
                { field: 'content', title: '谈话内容', width: "20%", }
            ]],
        });


        // 照片维护部分
        var zpwh = $('.zpwh', el);
        var buttonTitle = $('.section-title', zpwh);
        buttonTitle.on('click', 'a', function (e) {
            $(this).addClass('active').siblings().removeClass('active');
            $('.' + this.id + '-content').addClass('show').siblings().removeClass('show');
            $table.eq(1).datagrid();
        })



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
