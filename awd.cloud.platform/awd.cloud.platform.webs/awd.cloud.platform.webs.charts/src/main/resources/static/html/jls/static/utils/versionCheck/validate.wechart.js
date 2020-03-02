/**
 * 是否为微信客户端
*/
(function (undefined) {
    isWechart = function () {
        var rst = true
        if(window.navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)){
            var ua = navigator.userAgent.toLowerCase();
            if (!(ua.match(/MicroMessenger/i) == "micromessenger")) {
                rst = false;
            }
        }else {
            rst = false;
        }
        return rst;
    }
})();