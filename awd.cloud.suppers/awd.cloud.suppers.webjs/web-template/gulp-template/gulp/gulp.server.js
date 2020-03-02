'use strict';

const cfg = global.cfg;

const path = require('path');
const gulp = require('gulp');
var webserver = require('gulp-webserver');

function gulpServer() {
  var options = {
    port: 8080,
    /*自定义端口*/
    livereload: true,
    /*实现自动刷新，从此不再需要手动刷新页面了*/
    open: './' + cfg.options.mod + '/views/index.html',
    /*服务器启动时自动打开网页*/
    proxies: [{
        source: '/mock',
        target: 'http://localhost:4000'
      },
      {
        source: '/api',
        target: 'http://awdnj.cross.echosite.cn'
      },
      {
        source: '/fwf',
        target: 'http://192.168.4.24:92'
      }
    ]
  };
  //    console.log(cfg.path.dist);
  return gulp.src(cfg.path.dist)
    .pipe(webserver(options))
}

module.exports = gulpServer;
