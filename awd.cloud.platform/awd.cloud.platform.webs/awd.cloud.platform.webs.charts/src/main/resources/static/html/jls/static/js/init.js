define(function (require, exports, module) {
    var globalTask = require('app_config').globalTask,
        Stats = require("Stats");                       // 性能监控

    // 浏览器版本校验
    require("versionCheck");

    // 性能监控
    var StatsControll = function () {
        var stats = new Stats();
        stats.setMode(0);
        stats.domElement.style.position = "absolute";
        stats.domElement.style.left = "2%";
        stats.domElement.style.top = "2%";
        document.body.appendChild(stats.domElement);
        stats.begin();
        stats.update();


        setInterval(function () {
            stats.update();
        }, 1000 / 60);
    };
    if(globalTask.stats)StatsControll();
});
