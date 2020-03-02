'use strict';

const cfg = global.cfg;

const fs = require('fs');
const path = require('path');
const gulp = require('gulp');
const gulpTmod = require('gulp-tmod');
const replace = require('gulp-replace');


module.exports = function () {

    // console.log(path.resolve(cfg.path.cwd));
    let basePath = path.join(cfg.path.cwd, cfg.options.mod+'/static/js/controller/templates');
    let files = fs.readdirSync(basePath);

    console.log(files);

    files.forEach((val, index) => {
        let dirPath = path.join(basePath, val);
        let stat = fs.statSync(dirPath);

        if (stat.isDirectory()) {
            // 判断是否是文件夹
            var fileter = cfg.options.mod+'/static/js/controller/templates/' + val + '/**/*.html';
            console.log((path.join(cfg.path.cwd)+'/'+cfg.options.mod+'/static/js/controller/templates/').replace(/\\/g,'/'));
            gulp.src(fileter)
                .pipe(gulpTmod({
                    templateBase: 'templates',
                    runtime: val + '.js',
                }))
                .pipe(replace((path.join(cfg.path.cwd)+'/'+cfg.options.mod+'/static/js/controller/templates/').replace(/\\/g,'/'), ''))
                .pipe(gulp.dest(path.join(cfg.path.cwd, cfg.options.mod+'/static/js/controller/services')));
        } else {

            var fileter = cfg.options.mod+'/static/js/controller/templates/' + val;
            console.log((path.join(cfg.path.cwd)+'/'+cfg.options.mod+'/static/js/controller/templates/').replace(/\\/g,'/'));
            gulp.src(fileter)
                .pipe(gulpTmod({
                    templateBase: 'templates',
                    runtime: val + '.js',
                }))
                .pipe(replace((path.join(cfg.path.cwd)+'/'+cfg.options.mod+'/static/js/controller/templates/').replace(/\\/g,'/'), ''))
                .pipe(gulp.dest(path.join(cfg.path.cwd, cfg.options.mod+'/static/js/controller/services')));
        }


    });
}
