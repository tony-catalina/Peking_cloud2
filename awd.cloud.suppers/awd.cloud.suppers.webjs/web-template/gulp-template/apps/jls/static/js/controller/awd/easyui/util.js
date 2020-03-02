define(['jquery','watermark'],function($,watermark) {
  'use strict';
  return {
    watermark:function(text){
      $('body').watermark({
        texts: text, //水印文字
        textColor: '#d2d2d2', //文字颜色
        textFont: '14px 微软雅黑', //字体
        width: 100, //水印文字的水平间距
        height: 100, //水印文字的高度间距（低于文字高度会被替代）
        textRotate: -30 //-90到0， 负数值，不包含-90
      });
    }

  };
});
