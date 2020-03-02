define('browserV', function () {
    return function () {
        var ver = "";
        var userA = window.navigator.userAgent;
        // < IE11
        var isIE = userA.indexOf("compatible") > -1 && userA.indexOf("MSIE") > -1;
        // Edge
        var isEdge = userA.indexOf("Edge") > -1 && !isIE;
        // IE11
        var isIE11 = userA.indexOf("Trident") > -1 && userA.indexOf("rv:11.0") > -1;
        // isChrome
        var isChrome = userA.indexOf("Chrome") > -1 && userA.indexOf("360") === -1;
        if(isIE){
            new RegExp("MSIE (\\d+\\.\\d+);").test(userA);
            ver = parseFloat(RegExp["$1"]);
        }else if(isEdge) {
            ver = "edge";
        } else if(isIE11) {
            ver = 11;
        }else if(isChrome){
            ver = "chrome";
        }else{
            ver = -1;
        }
        return {
            version: ver,
            isIE: isIE,
            isEdge: isEdge,
            isNotIe: !(isIE || isEdge || isIE11),
            isChrome: isChrome,
            lte9: isIE && ver < 10
        };
    }
});