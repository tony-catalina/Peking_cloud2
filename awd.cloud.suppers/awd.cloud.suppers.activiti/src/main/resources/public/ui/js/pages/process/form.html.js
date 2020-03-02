define("/ui/js/pages/process/form.html", [],
    '<div id="dlg" class="easyui-window" title="{{title}}"\
            style="width: 600px; height: 130px; padding: 10px 20px; padding-top: 10px; " modal="true" closed="true" >\
    <div class="easyui-layout" data-options="fit:true">\
        <div data-options="region:\'center\'" style="padding:10px;">\
            <form id="importXML" method="post" novalidate >\
                <table>\
                     <tr>\
                         <td>后缀为<span style="color:red">.bpmn20.xml</span>文件:</td>\
                         \
                         <td><input type="file" name="file" id="file" style="width:300px"></td>\
                     </tr>\
                 </table>\
                </form>\
            </div>\
            <div data-options="region:\'south\',border:false" style="text-align:right;padding:5px 0 0;">\
                <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="modelSave">保存</a>\
                <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" id="modelClose">关闭</a>\
            </div>\
        </div>\
        </div>');