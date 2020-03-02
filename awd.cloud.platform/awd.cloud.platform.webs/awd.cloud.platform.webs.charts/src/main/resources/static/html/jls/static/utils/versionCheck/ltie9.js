define(function (require) {
    var browserV = require("browserV");
    if(browserV().lte9){
        window.location.href = window.BASEURL + '/pages/error/incomepatible.html';
    }
});