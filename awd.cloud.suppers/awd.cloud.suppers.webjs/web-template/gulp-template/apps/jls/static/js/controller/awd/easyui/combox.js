define(['jquery', 'easyui', 'models/url'], function($, easyui, url) {
  'use strict';
  return {
    initId: function(id) {
      var fieldName = $('#' + id).prev().next().attr('code');
      if (fieldName == undefined) {
        fieldName = $('#' + id).attr('code');
      }
      var url = $(this).attr('url');
      $('#' + id).combobox({
        url: '',
        valueField: 'code',
        textField: 'content',
        multiple: $('#' + id).attr('checkBox') == 'true',
        onShowPanel: function() {
          if (url == '' || url == undefined || url == null) {
            $('#' + id).combobox('reload', '/kss_dictionary/getDictionary?node=' + fieldName);
            $('#' + id).combobox('panel')[0].id = 'fontModify';
          } else {
            $('#' + id).combobox('reload', url);
            $('#' + id).combobox('panel')[0].id = 'fontModify';
          }
        }
      });
    },
    initClass: function(classid) {
      $('.' + classid).each(function() {
        var fieldName = $(this).prev().next().attr('code');
        if (fieldName == undefined) {
          fieldName = $(this).attr('code');
        }
        var url = $(this).attr('url');
        $(this).combobox({
          url: '',
          valueField: 'code',
          textField: 'content',
          multiple: $(this).attr('checkBox') == 'true',
          onShowPanel: function() {
            if (url == '' || url == undefined || url == null) {
              $(this).combobox('reload', '/kss_dictionary/getDictionary?node=' + fieldName);
            } else {
              $(this).combobox('reload', url);
            }
          }
        });
      });
    }
  };
});
