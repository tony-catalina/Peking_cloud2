define([
  "jquery",
  "easyui",
  "easyui_ZH",
  "arttemplate",
  "/apps/jds/static/js/controller/pages/common/searchForm.js",
  "/apps/jds/views/pages/common/jxcx.html",
  "/apps/jds/views/pages/common/addForm.html"
], function(require, exports, module) {
  require("jquery");
  require("easyui");
  require("easyui_ZH");
  require("uikit");
  require("arttemplate");

  var templ = require("/apps/jds/static/js/controller/pages/common/searchForm.js");
  var temp_addForm = require("/apps/jds/views/pages/common/addForm.html");
  // 简项查询内容
  var pageOptions = {
    jxcx: {
      title: "简项查询",
      controlButtonList: [
        {
          name: "增加",
          icon: "icon-jiahao"
        },
        {
          name: "删除",
          icon: ""
        },
        {
          name: "批量强制",
          icon: ""
        },
        {
          name: "打印",
          icon: "icon-iconSVG-"
        },
        {
          name: "导出",
          icon: "icon-daochu"
        }
      ]
    }
  };

  // 搜索栏部分的内容
  var formOptions = {
    primary: [
      {
        title: "姓名",
        type: "textbox"
      },
      {
        title: "戒毒室",
        type: "combotree"
      }
    ],
    more: [
      [
        { title: "戒毒起始时间", type: "datetimebox" },
        { title: "戒毒截止时间", type: "datetimebox" },
        { title: "别名", type: "textbox" }
      ],
      [
        { title: "入所原因", type: "textbox" },
        { title: "决定机关", type: "textbox" },
        { title: "入所文件", type: "textbox" }
      ]
    ]
  };

  // 表格数据
  var initPageData = function(el) {
    // 数据表格部分
    var $table = $(".tmpl-table", el);
    $table.datagrid({
      method: "get",
      url: "/mock/jds/datagrid/djzqzlist",
      // pageList: [2, 4, 6],
      // // 真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
      // rownumbers: true,
      fitColumns: true,
      pagination: true,
      rownumbers: true,
      singleSelect: true,
      columns: [
        [
          { field: "ck", checkbox: true, width: "2%", align: "center" },
          { field: "id", title: "编号", hidden: true, align: "center" },
          { field: "name", title: "姓名", width: "6%", align: "center" },
          { field: "nickname", title: "别名", width: "6%", align: "center" },
          { field: "gender", title: "性别", width: "4%", align: "center" },
          { field: "jieduRoom", title: "戒毒室", width: "6%", align: "center" },
          { field: "time", title: "入所时间", width: "8%", align: "center" },
          { field: "reason", title: "入所原因", width: "6%", align: "center" },
          { field: "unit", title: "办案单位", width: "10%", align: "center" },
          {
            field: "deadTimeLimit",
            title: "戒毒期限",
            width: "6%",
            align: "center"
          },
          {
            field: "decideOffice",
            title: "决定机关",
            width: "10%",
            align: "center"
          },
          {
            field: "rusuoFile",
            title: "入所文件",
            width: "8%",
            align: "center"
          },
          {
            field: "jieduStart",
            title: "戒毒起始日期",
            width: "11%",
            align: "center"
          },
          {
            field: "jieduEnd",
            title: "戒毒截止日期",
            width: "11%",
            align: "center"
          },
          {
            field: "caozuo",
            title: "操作",
            width: "7%",
            align: "center",
            formatter: function(value, row, index) {
              return ` <span onclick="getQiangjie()" id="qiangjie_sure" style="
                                    display: inline-block;
                                    background: #D2423E;
                                    padding: 3px 9px;
                                    border-radius: 4px;
                                    color: #fff;
                                ">强戒</span>`;
            }
          }
        ]
      ]
    });
  };

  //增加表单的数据
  var addFormData = {
    formData: [
      {
        title_left: "姓名",
        name_left: "name",
        class_left: "textbox",
        title_right: "别名",
        name_right: "nickname",
        class_right: "textbox"
      },
      {
        title_left: "性别",
        name_left: "gender",
        class_left: "combobox",
        options: [
          { value: "male", text: "男" },
          { value: "female", text: "女" }
        ],
        title_right: "戒毒室",
        name_right: "jieduRoom",
        class_right: "textbox"
      },
      {
        title_left: "入所时间",
        name_left: "time",
        class_left: "datebox",
        title_right: "入所原因",
        name_right: "reason",
        class_right: "textbox"
      },
      {
        title_left: "办案单位",
        name_left: "unit",
        class_left: "textbox",
        title_right: "戒毒期限",
        name_right: "deadline",
        class_right: "textbox"
      },
      {
        title_left: "决定机关",
        name_left: "organization",
        class_left: "textbox",
        title_right: "入所文件",
        name_right: "intoFile",
        class_right: "textbox"
      },
      {
        title_left: "戒毒期限起始时间",
        name_left: "start",
        class_left: "datebox",
        title_right: "戒毒期限截止时间",
        name_right: "end",
        class_right: "datebox"
      }
    ]
  };

  //引入简项查询的模板
  var jxcxTemplate = require("/apps/jds/views/pages/common/jxcx.html");
  var initContent = function() {
    var element = document.createElement("div");
    element.classList.add("section-container", "qa-container");
    element.innerHTML = template.render(jxcxTemplate, pageOptions.jxcx);
    element
      .querySelector(".section-content")
      .prepend(templ.render(formOptions));
    initPageData(element);
    return element;
  };

  // 点增添加按钮，录入个人信息点击
  var clickEvent = function() {
    // console.log(template.render(temp_addForm, addFormData));
    $("#add_dialog").html(template.render(temp_addForm, addFormData));

    //增加人员信息
    $(".section-controls button")
      .eq(0)
      .on("click", function() {
        $("#add_dialog").show();
        $("#add_dialog").dialog({
          title: "增加人员信息",
          width: 800,
          height: 500,
          closed: false,
          cache: false,
          modal: true
        });
      });

    // 增加人员信息对话框，点击取消按钮，关闭页面
    $(".addInfo_table .cancel").on("click", function() {
      $("#add_dialog").dialog({
        closed: true
      });
    });
    //保存
    $(".addInfo_table .save").on("click", function() {
      $("#add_dialog").dialog({
        closed: true
      });
    });
  };

  var init = function() {
    console.log(initContent());
    document.body.appendChild(initContent());
    clickEvent();
  };
  init();
});
