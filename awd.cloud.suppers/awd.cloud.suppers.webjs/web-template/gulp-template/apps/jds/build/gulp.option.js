/**
 * 业务模块gulp独立配置文件
 */


// 需要操作的文件
exports.path = {
    dist: './dist/apps/'
};
exports.src = {
    copy: [
        'apps/jds/static/js/seajs.config.js',
        'apps/swj/static/js/controller/**/*.js',
        'apps/jds/static/lib/**/*',
        'apps/jds/static/fonts/**/*',
        'apps/jds/static/images/**/*',
        ]
};

// 不需要编译的文件
exports.filter = {
    tmp: ['apps/jds/static/controller/templates/**/*.html'],
    js: ['apps/jds/static/lib/**/*.js'],
    css: ['!apps/jds/static/css/**/*.css']
};
